package rpg.cyberpunk._2020.combat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import rpg.general.combat.BodyLocation;

/**
 * A class that manages if a piece of armor can be worn and then can display the protective score
 * for each body location.
 */
public class ArmorManager {
  public static final int NO_STOPPING_POWER_MODIFIER = 0;
  public static final int VERY_LOW_STOPPING_POWER_MODIFIER = 1;
  public static final int LOW_STOPPING_POWER_MODIFIER = 2;
  public static final int AVERAGE_STOPPING_POWER_MODIFIER = 3;
  public static final int HIGH_STOPPING_POWER_MODIFIER = 4;
  public static final int VERY_HIGH_STOPPING_POWER_MODIFIER = 5;

  private static final int minLayerCount = 0;
  private static final int maxLayerCount = 3;
  private static final int minDurability = 0;

  private List<CyberpunkArmor> armors;

  /**
   * Constructs an ArmorManager that initializes the total encumbrance to be 0.
   */
  public ArmorManager() {
    armors = new ArrayList<CyberpunkArmor>();
  }

  /**
   * Puts the armor in the manager and updates the data where the armor is covering.
   * 
   * @param armor the item to keep track of
   * @return true, if the armor was accepted into the collection
   */
  public boolean add(CyberpunkArmor armor) {
    if (armor == null) {
      throw new NullPointerException();
    }

    boolean hasAdded = false;

    if (!isArmorTypeRestricted(armor) && !hasMaxLayers(armor)) {
      hasAdded = armors.add(armor);
    }

    return hasAdded;
  }

  private boolean isArmorTypeRestricted(CyberpunkArmor armor) {
    boolean isRestricted = false;

    if (isHardArmor(armor)) {
      Iterator<CyberpunkArmor> iterator = armors.iterator();
      while (iterator.hasNext() && !isRestricted) {
        CyberpunkArmor tempArmor = iterator.next();
        isRestricted = isHardArmor(tempArmor) && isSharingLocation(armor, tempArmor);
      }
    }

    return isRestricted;
  }

  private boolean isHardArmor(CyberpunkArmor armor) {
    return CyberpunkArmor.ARMOR_TYPE_HARD.equals(armor.getArmorType());
  }

  private boolean isSharingLocation(CyberpunkArmor armor1, CyberpunkArmor armor2) {
    boolean isSharing = false;

    BodyLocation[] locations = BodyLocation.values();
    for (int i = 0; i < locations.length && !isSharing; i++) {
      isSharing = armor1.isCovering(locations[i]) && armor2.isCovering(locations[i]);
    }

    return isSharing;
  }

  private boolean hasMaxLayers(CyberpunkArmor armor) {
    for (BodyLocation location : BodyLocation.values()) {
      int layerCount = 0;

      if (armor.isCovering(location)) {
        layerCount = getLayerCountAt(location);
      }

      if (layerCount == maxLayerCount) {
        return true;
      }
    }

    return false;
  }

  private int getLayerCountAt(BodyLocation location) {
    int layerCount = 0;

    for (CyberpunkArmor a : armors) {
      if (a.isCovering(location)) {
        layerCount++;
      }
    }

    return layerCount;
  }

  /**
   * Takes the armor out of the manager and updates the data where the armor was covering.
   * 
   * @param armor the item to take out
   * @return true, if the armor was taken out of the collection
   */
  public boolean remove(CyberpunkArmor armor) {
    return armors.remove(armor);
  }

  /**
   * Returns the current durability of all armor pieces combined in the given body location.
   * 
   * @param location the area used to get the specific durability value
   * @return the summation of all armor pieces at the <code>BodyLocation</code>
   */
  public int getDurabilityAt(BodyLocation location) {
    int totalDurability = 0;
    List<CyberpunkArmor> coveringArmors = //
        armors.stream() //
            .filter(a -> a.isCovering(location)) //
            .collect(Collectors.toList());

    for (int i = 0; i < coveringArmors.size(); i++) {
      int armorDurability = coveringArmors.get(i).getDurabilityAt(location);

      if (i == 0) {
        totalDurability = armorDurability;
      } else if (armorDurability > 0) {
        int greatestDurability = Math.max(totalDurability, armorDurability);
        totalDurability = greatestDurability + getDurabilityBonus(totalDurability, armorDurability);
      }
    }

    return totalDurability;
  }

  private int getDurabilityBonus(int durability1, int durability2) {
    if (durability1 < minDurability) {
      throw new IllegalArgumentException(
          "durability 1 = " + durability1 + "; min = " + minDurability);
    }

    if (durability2 < minDurability) {
      throw new IllegalArgumentException(
          "durability 2 = " + durability2 + "; min = " + minDurability);
    }

    int difference = Math.abs(durability1 - durability2);

    if (0 <= difference && difference < 5) {
      return VERY_HIGH_STOPPING_POWER_MODIFIER;
    } else if (5 <= difference && difference < 9) {
      return HIGH_STOPPING_POWER_MODIFIER;
    } else if (9 <= difference && difference < 15) {
      return AVERAGE_STOPPING_POWER_MODIFIER;
    } else if (15 <= difference && difference < 21) {
      return LOW_STOPPING_POWER_MODIFIER;
    } else if (21 <= difference && difference < 27) {
      return VERY_LOW_STOPPING_POWER_MODIFIER;
    } else {
      return NO_STOPPING_POWER_MODIFIER;
    }
  }

  public int getEncumbrancePenalty() {
    int baseEncumbranceValue = 0;
    for (CyberpunkArmor a : armors) {
      baseEncumbranceValue += a.getEncumbranceValue();
    }

    int bonusEncumbranceValue = 0;
    for (BodyLocation location : BodyLocation.values()) {
      bonusEncumbranceValue += getEncumbranceBonusAt(location);
    }

    return baseEncumbranceValue + bonusEncumbranceValue;
  }

  private int getEncumbranceBonusAt(BodyLocation location) {
    int layerCount = minLayerCount;

    for (CyberpunkArmor a : armors) {
      if (a.isCovering(location)) {
        layerCount++;
      }
    }

    return getEncumbranceBonus(layerCount);
  }

  private int getEncumbranceBonus(int layerCount) {
    layerCount = layerCount < minLayerCount ? minLayerCount : layerCount;

    if (2 == layerCount) {
      return 1;
    } else if (maxLayerCount <= layerCount) {
      return 3;
    } else {
      return 0;
    }
  }

  public double getTotalWeight() {
    return armors.stream() //
        .mapToDouble(a -> a.getWeight()) //
        .sum();
  }

  public Collection<CyberpunkArmor> createArmorCollection() {
    return Collections.unmodifiableCollection(armors);
  }

}
