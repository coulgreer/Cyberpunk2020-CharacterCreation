package rpg.cyberpunk._2020.combat;

import java.util.ListIterator;

public class WeaponIterator implements ListIterator<CyberpunkWeapon> {
	public static final int STARTING_POSITION = 0;

	private CyberpunkWeapon[] weapons;
	private int position;

	public WeaponIterator(CyberpunkWeapon[] weapons) {
		this.weapons = weapons;
		position = STARTING_POSITION;
	}

	public void add(CyberpunkWeapon weapon) {
		// Do nothing. The array is immutable.
	}

	public boolean hasNext() {
		if (position >= weapons.length || weapons[position] == null) {
			return false;
		} else {
			return true;
		}
	}

	public CyberpunkWeapon next() {
		CyberpunkWeapon weapon = weapons[position];
		position++;
		return weapon;
	}

	public boolean hasPrevious() {
		if (position < STARTING_POSITION || weapons[position] == null) {
			return false;
		} else {
			return true;
		}
	}

	public int nextIndex() {
		return position + 1;
	}

	public CyberpunkWeapon previous() {
		CyberpunkWeapon weapon = weapons[position];
		position--;
		return weapon;
	}

	public int previousIndex() {
		return position - 1;
	}

	public void remove() {
		// Do nothing. The array is immutable.
	}

	public void set(CyberpunkWeapon weapon) {
		weapons[position] = weapon;
	}

}
