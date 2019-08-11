package rpg.cyberpunk._2020.combat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import rpg.general.combat.BodyLocation;
import rpg.general.combat.Protective;
import rpg.general.commerce.Item;
import rpg.util.Measurement;

/**
 * This class creates armor that a player can equip for protection as well as be bought and stored.
 * The available body locations include but may not be limited to:
 * <ul>
 * <li>Head</li>
 * <li>Torso</li>
 * <li>Right Arm</li>
 * <li>Left Arm</li>
 * <li>Right Leg</li>
 * <li>Left Leg</li>
 * </ul>
 */
public class CyberpunkArmor implements Protective, Item {
  /**
   * Constant that represents that a piece of armor covers nothing when worn.
   */
  public static final String COVERING_NOTHING = "Covering: nothing";

  /**
   * Constant that represents that a piece of armor covers only the Head when worn.
   */
  public static final String COVERING_HEAD = "Covering: head";

  /**
   * Constant that represents that a piece of armor covers Left Arm, Right Arm, and Torso when worn.
   * For example, a T-shirt.
   */
  public static final String COVERING_ARMS_AND_TORSO = "Covering: arms & torso";

  /**
   * Constant that represents that a piece of armor covers only the Torso when worn. For example, an
   * A-shirt.
   */
  public static final String COVERING_TORSO = "Covering: torso";

  /**
   * Constant that represents that a piece of armor covers only Right Arm and Left Arm when worn.
   * For example, elbow guards.
   */
  public static final String COVERING_ARMS = "Covering: arms";

  /**
   * Constant that represents that a piece of armor covers Right Arm and Left Arm when worn. For
   * example, a pair of pants.
   */
  public static final String COVERING_LEGS = "Covering: legs";

  /**
   * Constant that represents that a piece of armor covers everything when worn.
   */
  public static final String COVERING_EVERYTHING = "Covering: everything";

  /**
   * Constant that represents that an armor is made of soft material.
   */
  public static final String ARMOR_TYPE_SOFT = "Soft Armor";

  /**
   * Constant that represents that an armor is made of hard material.
   */
  public static final String ARMOR_TYPE_HARD = "Hard Armor";

  /**
   * The minimum value that an armors Stopping Power can reach.
   */
  public static final int DEFAULT_STOPPING_POWER = 0;

  private static final int minDelta = 0;
  private static final int minStoppingPower = 0;
  private static final int minEncumbranceValue = 0;
  private static final double minCost = 0.0;
  private static final long serialVersionUID = 1L;

  private String name;
  private String description;
  private double cost;
  private Measurement weight;
  private Map<BodyLocation, Durability> localizedDurabilities;
  private String armorType;
  private int stoppingPower;
  private int encumbranceValue;

  /**
   * Constructs CyberpunkArmor that covers specific parts of the body as well as offering protection
   * based on the passed stopping power.
   * 
   * @param name the identifier used
   * @param description a blurb used to give an idea of what this armor can do
   * @param coveredLocationIterator a collection of the locations covered
   * @param armorType the class used calculating specific penetration of damage
   * @param stoppingPower the starting amount of damage mitigation
   * @param encumbrance the cost of wearing this armor
   * @param cost the base value used when transacting
   * @param weight the heaviness of this armor
   */
  public CyberpunkArmor( //
      String name, String description, //
      Iterator<BodyLocation> coveredLocationIterator, //
      String armorType, int stoppingPower, int encumbranceValue, //
      double cost, Measurement weight) {

    setName(name);
    setDescription(description);
    setArmorType(armorType);
    setStoppingPower(stoppingPower);
    setEncumbranceValue(encumbranceValue);
    initializeArmorDurability(coveredLocationIterator, stoppingPower);
    setCost(cost);
    setWeight(weight);
  }

  private void setName(String name) {
    if (name == null) {
      throw new NullPointerException();
    } else {
      this.name = name;
    }
  }

  private void setDescription(String description) {
    if (description == null) {
      throw new NullPointerException();
    } else {
      this.description = description;
    }
  }

  private void setArmorType(String armorType) {
    if (armorType == null) {
      throw new NullPointerException();
    } else {
      this.armorType = armorType;
    }
  }

  private void setStoppingPower(int stoppingPower) {
    if (stoppingPower < minStoppingPower) {
      throw new IllegalArgumentException(
          "stopping power = " + stoppingPower + "; min = " + minStoppingPower);
    } else {
      this.stoppingPower = stoppingPower;
    }
  }

  private void setEncumbranceValue(int encumbranceValue) {
    if (encumbranceValue < minEncumbranceValue) {
      throw new IllegalArgumentException(
          "encumbrance value = " + encumbranceValue + "; min = " + minEncumbranceValue);
    } else {
      this.encumbranceValue = encumbranceValue;
    }
  }

  private void initializeArmorDurability( //
      Iterator<BodyLocation> coveredLocationIterator, //
      int stoppingPower) {

    localizedDurabilities = new HashMap<BodyLocation, Durability>();
    while (coveredLocationIterator.hasNext()) {
      BodyLocation tempLocation = coveredLocationIterator.next();
      localizedDurabilities.put(tempLocation, new Durability(stoppingPower));
    }
  }

  private void setCost(double cost) {
    if (cost < minCost) {
      throw new IllegalArgumentException("cost = " + cost + "; min = " + minCost);
    } else {
      this.cost = cost;
    }
  }

  private void setWeight(Measurement weight) {
    if (weight == null) {
      throw new NullPointerException();
    } else {
      this.weight = weight;
    }
  }

  @Override
  public double getCost() {
    return cost;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public Measurement getWeight() {
    return weight;
  }

  @Override
  public String getArmorType() {
    return armorType;
  }

  @Override
  public boolean isCovering(BodyLocation location) {
    return localizedDurabilities.containsKey(location);
  }

  @Override
  public int getEncumbranceValue() {
    return encumbranceValue;
  }

  @Override
  public int getProtectionScore() {
    return stoppingPower;
  }

  @Override
  public int getDurabilityAt(BodyLocation location) {
    if (isCovering(location)) {
      Durability durability = localizedDurabilities.get(location);
      return durability.getCurrentDurability();
    } else {
      return DEFAULT_STOPPING_POWER;
    }
  }

  @Override
  public void damage(BodyLocation location, int points) {
    if (points < minDelta) {
      throw new IllegalArgumentException("points = " + points + "; min = " + minDelta);
    }

    Durability durability = localizedDurabilities.get(location);
    int currentDurabilityPoints = durability.getCurrentDurability() - points;
    durability.setCurrentDurability(currentDurabilityPoints);
  }

  @Override
  public void damageAll(int points) {
    for (BodyLocation location : localizedDurabilities.keySet()) {
      damage(location, points);
    }
  }

  @Override
  public void repair(BodyLocation location, int points) {
    if (points < minDelta) {
      throw new IllegalArgumentException("points = " + points + "; min = " + minDelta);
    }

    Durability durability = localizedDurabilities.get(location);
    int currentDurabilityPoints = durability.getCurrentDurability() + points;
    durability.setCurrentDurability(currentDurabilityPoints);
  }

  @Override
  public void repairAll(int points) {
    for (BodyLocation location : localizedDurabilities.keySet()) {
      repair(location, points);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof CyberpunkArmor)) {
      return false;
    }

    CyberpunkArmor armor = (CyberpunkArmor) o;
    return getName().equals(armor.getName()) && isArmorStatsEqual(armor);
  }

  private boolean isArmorStatsEqual(CyberpunkArmor armor) {
    Iterator<BodyLocation> iterator = BodyLocation.createIterator();
    while (iterator.hasNext()) {
      BodyLocation location = iterator.next();
      if (isCovering(location) != armor.isCovering(location)
          || getDurabilityAt(location) != armor.getDurabilityAt(location)) {
        return false;
      }
    }

    return (getProtectionScore() == armor.getProtectionScore())
        && (getEncumbranceValue() == armor.getEncumbranceValue());
  }

  @Override
  public int hashCode() {
    ArrayList<Object> objectsToHash = new ArrayList<>();

    objectsToHash.add(getName());
    addIsCoveringTo(objectsToHash);
    addGetDurabilityTo(objectsToHash);
    objectsToHash.add(getProtectionScore());
    objectsToHash.add(getEncumbranceValue());

    return Arrays.hashCode(objectsToHash.toArray());
  }

  private void addIsCoveringTo(List<Object> list) {
    Iterator<BodyLocation> iterator = BodyLocation.createIterator();
    while (iterator.hasNext()) {
      BodyLocation location = iterator.next();
      list.add(isCovering(location));
    }
  }

  private void addGetDurabilityTo(List<Object> list) {
    Iterator<BodyLocation> iterator = BodyLocation.createIterator();
    while (iterator.hasNext()) {
      BodyLocation location = iterator.next();
      list.add(getDurabilityAt(location));
    }
  }

}
