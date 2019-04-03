package rpg.general.combat;

import java.util.List;

/**
 * Allows an object to store and use ammunition while also getting the amount of
 * ammunition that can be and is stored within.
 * 
 * @author Coul Greer
 */
public interface Shootable extends Weaponizable {
	/**
	 * Allows this <code>Shootable</code> to discharge/remove ammunition from
	 * itself. It is recommended that <code>numberOfShots</code> should be a
	 * positive value, and any addition of ammunition should be handled by
	 * <code>reload</code>.
	 * 
	 * @param numberOfShots the amount of ammunition/shots/etc. spent
	 * @return <code>true</code> if the <code>Shootable</code> discharged all
	 *         desired ammunition
	 */
	public boolean fire(int numberOfShots);

	/**
	 * Allows this <code>Shootable</code> to store/add ammunition into itself.
	 * 
	 * @param newStorageDevice the storing unit used to refill this
	 *                         <code>Shootable</code>
	 * @return any ammunition leftover after this <code>Shootable</code> has reached
	 *         its ammunition capacity or all ammunition in the storage unit if the
	 *         ammunition in <code>newStorageDevice</code> is not compatible
	 */
	public List<Ammunition> reload(AmmunitionContainer newStorageDevice);

	/**
	 * Gets the current amount of ammunition stored.
	 * 
	 * @return the current amount of ammunition
	 */
	public int getAmmunitionCount();

	/**
	 * Gets the maximum amount of ammunition that can be stored.
	 * 
	 * @return the maximum amount of ammunition
	 */
	public int getAmmunitionCapacity();

	/**
	 * Gets the amount of attacks per round that this <code>Shootable</code> can
	 * make without penalty.
	 * 
	 * @return the number of attacks that can be made per round without penalty
	 */
	public int getRateOfFire();

	/**
	 * Gets the type of ammunition that can be stored. Some examples would be 5mm,
	 * .50cal, shotgun shell, arrow, etc. For example, assume that a .50cal
	 * cartridge would not fit in a 9mm magazine or in a receiver that fits a
	 * shotgun shell.
	 * 
	 * @return the type of ammunition that is stored
	 */
	public String getAmmunitionType();
}
