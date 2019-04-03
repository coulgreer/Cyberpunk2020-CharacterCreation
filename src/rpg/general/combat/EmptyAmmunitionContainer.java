package rpg.general.combat;

import rpg.cyberpunk._2020.combat.CyberpunkWeapon;

public class EmptyAmmunitionContainer implements AmmunitionContainer {
	@Override
	public Ammunition withdrawAmmunition() {
		throw new UnsupportedOperationException(
				"No ammunition is stored in this container, therefore, no ammunition may be withdrawn.");
	}

	@Override
	public boolean depositAmmunition(Ammunition cartridge) {
		return false;
	}

	@Override
	public Ammunition getAmmunition() {
		return null;
	}

	@Override
	public int getAmmunitionCount() {
		return AmmunitionContainer.EMPTY;
	}

	@Override
	public int getAmmunitionCapacity() {
		return AmmunitionContainer.EMPTY;
	}

	@Override
	public String getAmmunitionType() {
		return CyberpunkWeapon.NO_AMMUNITION_TYPE;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public boolean isFull() {
		return true;
	}

}
