package rpg.general.combat;

import rpg.cyberpunk._2020.combat.Ammunition;

public class Magazine {
	public static final int EMPTY = 0;

	private String ammoType;
	private Ammunition ammunition;
	private int ammoCount;
	private int ammoCapacity;

	public Magazine(String ammoType, int ammoCapacity) {
		this.ammoType = ammoType;
		this.ammoCapacity = ammoCapacity;
		ammoCount = EMPTY;
	}

	public boolean storeAmmunition(Ammunition ammunition) {
		boolean hasStored = false;
		if (isAmmunitionCompatible(ammunition) && ammoCount < ammoCapacity) {
			if (ammoCount <= EMPTY) {
				this.ammunition = ammunition;
			}
			ammoCount++;
			hasStored = true;
		}
		return hasStored;
	}

	private boolean isAmmunitionCompatible(Ammunition ammunition) {
		boolean hasMatchingAmmoType = this.ammoType == ammunition.getAmmoType();
		boolean hasMatchingAmmunition = !this.isEmpty() ? (this.ammunition.equals(ammunition)) : true;
		return hasMatchingAmmoType && hasMatchingAmmunition;
	}

	public boolean isEmpty() {
		return !(ammoCount > EMPTY);
	}

	public boolean spendAmmunition() {
		if (ammoCount > EMPTY) {
			ammoCount--;
			return true;
		}
		return false;
	}

	public String getAmmoType() {
		return ammoType;
	}

	public Ammunition getAmmunition() {
		return ammunition;
	}

	public int getAmmoCount() {
		return ammoCount;
	}

	public int getAmmoCapacity() {
		return ammoCapacity;
	}
}
