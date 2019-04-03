package rpg.cyberpunk._2020.gui;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import rpg.cyberpunk._2020.combat.CyberpunkWeapon;

/**
 * Compares weapon types provided by CyberpunkWeapon.
 * 
 * @author Coul Greer
 */
public class WeaponTypeComparator implements Comparator<String> {
	private Map<String, Integer> orderedDictionary;

	/**
	 * Constructs a comparator that has an ordered dictionary giving numerical
	 * values to all weapon types that is then used to compare one type to another.
	 */
	public WeaponTypeComparator() {
		orderedDictionary = new HashMap<>();
		orderedDictionary.put(CyberpunkWeapon.NO_WEAPON_TYPE, 0);
		orderedDictionary.put(CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, 1);
		orderedDictionary.put(CyberpunkWeapon.WEAPON_TYPE_MEDIUM_PISTOL, 2);
		orderedDictionary.put(CyberpunkWeapon.WEAPON_TYPE_HEAVY_PISTOL, 3);
		orderedDictionary.put(CyberpunkWeapon.WEAPON_TYPE_VERY_HEAVY_PISTOL, 4);
		orderedDictionary.put(CyberpunkWeapon.WEAPON_TYPE_LIGHT_SUBMACHINEGUN, 5);
		orderedDictionary.put(CyberpunkWeapon.WEAPON_TYPE_MEDIUM_SUBMACHINEGUN, 6);
		orderedDictionary.put(CyberpunkWeapon.WEAPON_TYPE_HEAVY_SUBMACHINEGUN, 7);
		orderedDictionary.put(CyberpunkWeapon.WEAPON_TYPE_RIFLE, 8);
		orderedDictionary.put(CyberpunkWeapon.WEAPON_TYPE_SHOTGUN, 9);
		orderedDictionary.put(CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, 10);
		orderedDictionary.put(CyberpunkWeapon.WEAPON_TYPE_MELEE_WEAPON, 11);
		orderedDictionary.put(CyberpunkWeapon.WEAPON_TYPE_EXOTIC, 12);
		orderedDictionary.put(CyberpunkWeapon.WEAPON_TYPE_UNARMED, 13);
	}

	@Override
	public int compare(String type1, String type2) {
		int type1Index = orderedDictionary.get(type1) == null ? -1 : orderedDictionary.get(type1);
		int type2Index = orderedDictionary.get(type2) == null ? -1 : orderedDictionary.get(type2);

		if (type1Index < type2Index) {
			return -1;
		} else if (type1Index > type2Index) {
			return 1;
		} else {
			return 0;
		}
	}

}
