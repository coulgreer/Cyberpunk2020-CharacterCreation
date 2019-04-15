package rpg.general.combat;

import rpg.cyberpunk._2020.combat.CyberpunkWeapon;

/**
 * An instance of AmmunitionContainer that models itself after the Null Object
 * pattern. There is no unique instance, however, this is still a do-nothing
 * AmmunitionContainer used for weapons that cannot hold ammunition.
 * 
 * @author Coul Greer
 */
public class EmptyAmmunitionContainer implements AmmunitionContainer {
	/**
	 * @throws UnsupportedOperationException because no ammunition can be contained
	 *                                       within
	 */
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
