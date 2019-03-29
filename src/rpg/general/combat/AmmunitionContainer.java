package rpg.general.combat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface AmmunitionContainer extends Serializable {
	public static final int EMPTY = 0;

	/**
	 * Decrements the amount of ammunition that is stored within this ammunition
	 * container and returns an <code>Ammunition</code>.
	 * 
	 * @return an ammunition that was stored in this
	 *         <code>AmmunitionContainer</code>
	 */
	public Ammunition withdrawAmmunition();

	/**
	 * Adds an <code>Ammunition</code> into this ammunition container.
	 * 
	 * @param cartridge the ammunition to be stored and managed
	 * @return <code>true</code>, if <code>cartridge</code> has been removed
	 */
	public boolean depositAmmunition(Ammunition cartridge);

	/**
	 * Takes <code>Ammunition</code> out of the given container and adds it to this
	 * ammunition container if compatible. Incompatible <code>Ammunition</code> will
	 * be taken out of the given container and returned as a list of
	 * <code>Ammunition</code> instead of another ammunition container.
	 * 
	 * @param container the reserve of ammunition to add to this ammunition
	 *                  container
	 * @return a list of leftover ammunition that has not been put into this
	 *         ammunition container
	 */
	public default List<Ammunition> transferAmmunitionFrom(AmmunitionContainer container) {
		ArrayList<Ammunition> remainingAmmunition = new ArrayList<>();

		while (!container.isEmpty()) {
			remainingAmmunition.add(container.withdrawAmmunition());
		}

		return remainingAmmunition;
	}

	/**
	 * Gets an <code>Ammunition</code> that is stored inside this
	 * <code>AmmunitionStorage</code>. This does not include the amount of that
	 * ammunition type.
	 * 
	 * @return an <code>Ammunition</code> currently stored
	 */
	public Ammunition getAmmunition();

	/**
	 * Gets the current amount of ammunition stored.
	 * 
	 * @return the current amount of ammunition
	 */
	public int getAmmunitionCount();

	/**
	 * Gets the maximum amount of ammunition that can be stored.
	 * 
	 * @return the maximum amount of ammunition that can be stored
	 */
	public int getAmmunitionCapacity();

	/**
	 * Gets the type of ammunition that can be stored.
	 * <p>
	 * Some examples would be 5mm, .50cal, shotgun shell, arrow, etc. For example,
	 * assume that a .50cal cartridge would not fit in a 9mm magazine or in a
	 * receiver that fits a shotgun shell.
	 * 
	 * @return the type of ammunition that is stored
	 */
	public String getAmmunitionType();

	/**
	 * Returns if there is not any ammunition being stored.
	 * 
	 * @return <code>true</code>, if no ammunition is being stored
	 */
	public boolean isEmpty();

	/**
	 * Returns if this container has reached its capacity.
	 * 
	 * @return <code>true</code>, if the capacity has been reached for this
	 *         container
	 */
	public boolean isFull();
}
