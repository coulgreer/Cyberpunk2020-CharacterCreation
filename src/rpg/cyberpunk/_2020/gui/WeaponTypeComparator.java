package rpg.cyberpunk._2020.gui;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import rpg.cyberpunk._2020.combat.CyberpunkWeapon;

/**
 * Compares weapon types provided by a <code>CyberpunkWeapon</code> using
 * indices assigned by this comparator.
 */
public class WeaponTypeComparator implements Comparator<String> {
	private Map<String, Integer> indexByWeaponType;

	/**
	 * Constructs a comparator that creates an ordered dictionary by giving indices
	 * to all weapon types. This then allows one weapon type to be compared to
	 * another weapon type. The indices are assigned by the order of appearance when
	 * <code>values()</code> is called and iterated through.
	 */
	public WeaponTypeComparator() {
		indexByWeaponType = new HashMap<String, Integer>();
		// TODO (Coul Greer): Consider making all the weapon types into Enums. The only
		// reason to give pause is when custom weapons are allowed to be created, but
		// this may be remedied by creating a parser in the CustomWeapon class later in
		// the development process.
		indexByWeaponType.put(CyberpunkWeapon.NO_WEAPON_TYPE, 0);
		indexByWeaponType.put(CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, 1);
		indexByWeaponType.put(CyberpunkWeapon.WEAPON_TYPE_MEDIUM_PISTOL, 2);
		indexByWeaponType.put(CyberpunkWeapon.WEAPON_TYPE_HEAVY_PISTOL, 3);
		indexByWeaponType.put(CyberpunkWeapon.WEAPON_TYPE_VERY_HEAVY_PISTOL, 4);
		indexByWeaponType.put(CyberpunkWeapon.WEAPON_TYPE_LIGHT_SUBMACHINEGUN, 5);
		indexByWeaponType.put(CyberpunkWeapon.WEAPON_TYPE_MEDIUM_SUBMACHINEGUN, 6);
		indexByWeaponType.put(CyberpunkWeapon.WEAPON_TYPE_HEAVY_SUBMACHINEGUN, 7);
		indexByWeaponType.put(CyberpunkWeapon.WEAPON_TYPE_RIFLE, 8);
		indexByWeaponType.put(CyberpunkWeapon.WEAPON_TYPE_SHOTGUN, 9);
		indexByWeaponType.put(CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, 10);
		indexByWeaponType.put(CyberpunkWeapon.WEAPON_TYPE_MELEE_WEAPON, 11);
		indexByWeaponType.put(CyberpunkWeapon.WEAPON_TYPE_BOW, 12);
		indexByWeaponType.put(CyberpunkWeapon.WEAPON_TYPE_THROWN, 13);
		indexByWeaponType.put(CyberpunkWeapon.WEAPON_TYPE_EXOTIC, 14);
		indexByWeaponType.put(CyberpunkWeapon.WEAPON_TYPE_UNARMED, 15);
	}

	@Override
	public int compare(String weaponType1, String weaponType2) {
		int weponType1Index = indexByWeaponType.get(weaponType1) == null ? -1 : indexByWeaponType.get(weaponType1);
		int weaponType2Index = indexByWeaponType.get(weaponType2) == null ? -1 : indexByWeaponType.get(weaponType2);

		if (weponType1Index < weaponType2Index) {
			return -1;
		} else if (weponType1Index > weaponType2Index) {
			return 1;
		} else {
			return 0;
		}
	}

}
