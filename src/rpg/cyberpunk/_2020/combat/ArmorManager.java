package rpg.cyberpunk._2020.combat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rpg.general.combat.BodyLocation;

/**
 * A class that manages if a piece of armor can be worn and then can display the
 * protective score for each body location.
 * 
 * @author Coul Greer
 */
public class ArmorManager {
	/**
	 * A constant representing how many pieces of hard armor can be placed on a body
	 * location.
	 */
	public static final int MAX_HARD_ARMOR_LAYERS = 1;

	/**
	 * The maximum amount of armor that can be placed on a body location regardless
	 * of armor type.
	 */
	public static final int MAX_TOTAL_ARMOR_LAYERS = 3;

	public static final int NO_STOPPING_POWER_MODIFIER = 0;
	public static final int VERY_LOW_STOPPING_POWER_MODIFIER = 1;
	public static final int LOW_STOPPING_POWER_MODIFIER = 2;
	public static final int AVERAGE_STOPPING_POWER_MODIFIER = 3;
	public static final int HIGH_STOPPING_POWER_MODIFIER = 4;
	public static final int VERY_HIGH_STOPPING_POWER_MODIFIER = 5;

	private int totalEncumbranceValue;
	private List<CyberpunkArmor> armors;
	private Map<BodyLocation, List<CyberpunkArmor>> armorsByBodyLocation;
	private Map<BodyLocation, Integer> stoppingPowerByBodyLocation;

	/**
	 * Constructs an ArmorManager that initializes the total encumbrance to be 0.
	 */
	public ArmorManager() {
		totalEncumbranceValue = 0;
		armors = new ArrayList<CyberpunkArmor>();
		initializeLayerTracker();
		initializeLocalizedDurabilities();
	}

	private void initializeLayerTracker() {
		armorsByBodyLocation = new HashMap<BodyLocation, List<CyberpunkArmor>>();
		BodyLocation[] locations = BodyLocation.values();
		for (int i = 0; i < locations.length; i++) {
			armorsByBodyLocation.put(locations[i], new ArrayList<CyberpunkArmor>());
		}
	}

	private void initializeLocalizedDurabilities() {
		stoppingPowerByBodyLocation = new HashMap<BodyLocation, Integer>();
		Iterator<BodyLocation> setIterator = BodyLocation.createIterator();
		while (setIterator.hasNext()) {
			stoppingPowerByBodyLocation.put(setIterator.next(), CyberpunkArmor.DEFAULT_STOPPING_POWER);
		}
	}

	/**
	 * Puts the armor in the manager and updates the data where the armor is
	 * covering.
	 * 
	 * @param armor the item to keep track of
	 * @return true, if the armor was accepted into the collection
	 */
	public boolean add(CyberpunkArmor armor) {
		boolean hasAdded = false;

		if (!isArmorTypeRestricted(armor) && !hasMaxLayers(armor)) {
			addToLocalArmors(armor);
			armors.add(armor);

			updateLocalizedDurabilities();
			calculateEncumbranceValue();
			hasAdded = true;
		}

		return hasAdded;
	}

	private boolean isArmorTypeRestricted(CyberpunkArmor armor) {
		boolean isRestricted = false;

		if (isHardArmor(armor) && !isRestricted) {
			Iterator<CyberpunkArmor> iterator = armors.iterator();
			while (iterator.hasNext() && !isRestricted) {
				CyberpunkArmor tempArmor = iterator.next();
				if (isHardArmor(tempArmor) && isSharingLocation(armor, tempArmor)) {
					isRestricted = true;
				}
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
		Iterator<BodyLocation> iterator = BodyLocation.createIterator();
		while (iterator.hasNext()) {
			BodyLocation location = iterator.next();
			List<CyberpunkArmor> localArmors = armorsByBodyLocation.get(location);
			if (armor.isCovering(location) && localArmors.size() >= MAX_TOTAL_ARMOR_LAYERS) {
				return true;
			}
		}
		return false;
	}

	private void updateLocalizedDurabilities() {
		Iterator<BodyLocation> iterator = BodyLocation.createIterator();
		while (iterator.hasNext()) {
			BodyLocation location = iterator.next();
			stoppingPowerByBodyLocation.replace(location, calculateStoppingPower(location));
		}
	}

	private int calculateStoppingPower(BodyLocation location) {
		int stoppingPower = 0;

		List<CyberpunkArmor> localArmors = armorsByBodyLocation.get(location);
		for (int i = 0; i < localArmors.size(); i++) {
			CyberpunkArmor armor = localArmors.get(i);
			if (i == 0) {
				stoppingPower = armor.getDurabilityAt(location);
			} else {
				int greatestStoppingPower = Math.max(stoppingPower, armor.getDurabilityAt(location));
				stoppingPower = greatestStoppingPower + getArmorModifier(stoppingPower, armor.getDurabilityAt(location));
			}
		}

		return stoppingPower;
	}

	private int getArmorModifier(int stoppingPower1, int stoppingPower2) {
		int result = Math.abs(stoppingPower1 - stoppingPower2);
		if (result <= 4) {
			return VERY_HIGH_STOPPING_POWER_MODIFIER;
		} else if (5 <= result && result <= 8) {
			return HIGH_STOPPING_POWER_MODIFIER;
		} else if (9 <= result && result <= 14) {
			return AVERAGE_STOPPING_POWER_MODIFIER;
		} else if (15 <= result && result <= 20) {
			return LOW_STOPPING_POWER_MODIFIER;
		} else if (21 <= result && result <= 26) {
			return VERY_LOW_STOPPING_POWER_MODIFIER;
		} else {
			return NO_STOPPING_POWER_MODIFIER;
		}
	}

	private void addToLocalArmors(CyberpunkArmor armor) {
		Iterator<BodyLocation> iterator = BodyLocation.createIterator();
		while (iterator.hasNext()) {
			BodyLocation location = iterator.next();
			if (armor.isCovering(location)) {
				List<CyberpunkArmor> localArmors = armorsByBodyLocation.get(location);

				localArmors.add(armor);
			}
		}
	}

	private void calculateEncumbranceValue() {
		totalEncumbranceValue = totalEncumbranceValue
				+ getEncumbranceBonus(armorsByBodyLocation.get(BodyLocation.HEAD).size())
				+ getEncumbranceBonus(armorsByBodyLocation.get(BodyLocation.TORSO).size())
				+ (getEncumbranceBonus(armorsByBodyLocation.get(BodyLocation.RIGHT_ARM).size())
						+ getEncumbranceBonus(armorsByBodyLocation.get(BodyLocation.LEFT_ARM).size())) / 2
				+ (getEncumbranceBonus(armorsByBodyLocation.get(BodyLocation.RIGHT_LEG).size())
						+ getEncumbranceBonus(armorsByBodyLocation.get(BodyLocation.LEFT_LEG).size())) / 2;

		Iterator<CyberpunkArmor> iterator = armors.iterator();
		while (iterator.hasNext()) {
			CyberpunkArmor armor = iterator.next();
			totalEncumbranceValue += armor.getEncumbranceValue();
		}
	}

	private int getEncumbranceBonus(int layers) {
		if (layers <= 1) {
			return 0;
		} else if (2 == layers) {
			return 1;
		} else if (3 == layers) {
			return 3;
		} else {
			return -1;
		}
	}

	/**
	 * Takes the armor out of the manager and updates the data where the armor was
	 * covering.
	 * 
	 * @param armor the item to take out
	 * @return true, if the armor was taken out of the collection
	 */
	public boolean remove(CyberpunkArmor armor) {
		boolean hasRemoved = false;

		if (armors.contains(armor)) {
			removeFromLocalArmors(armor);
			armors.remove(armor);
			hasRemoved = true;
		}

		return hasRemoved;
	}

	private void removeFromLocalArmors(CyberpunkArmor armor) {
		Iterator<BodyLocation> iterator = BodyLocation.createIterator();
		while (iterator.hasNext()) {
			BodyLocation location = iterator.next();
			List<CyberpunkArmor> localArmors = armorsByBodyLocation.get(location);
			if (armor.isCovering(location) && localArmors.contains(armor)) {
				localArmors.remove(armor);
			}
		}
	}

	/**
	 * Returns the current durability of all armor pieces combined in the given body
	 * location.
	 * 
	 * @param location the area used to get the specific durability value
	 * @return the summation of all armor pieces at the <code>BodyLocation</code>
	 */
	public int getLocationDurability(BodyLocation location) {
		return stoppingPowerByBodyLocation.get(location);
	}

	public int getEncumbranceValue() {
		return totalEncumbranceValue;
	}
}