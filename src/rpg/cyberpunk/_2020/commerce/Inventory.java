package rpg.cyberpunk._2020.commerce;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Set;

import rpg.cyberpunk._2020.combat.CyberpunkArmor;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.general.combat.Ammunition;
import rpg.general.commerce.Item;

/**
 * Used to keep track of multiple <code>Item</code> objects and its many
 * subclasses that will be used in the desired game. In this case, the game
 * would be Cyberpunk 2020. When a subclass of <code>Item</code> is added to
 * this inventory it should also be added to the collection of <code>Item</code>
 * as well so that all <code>Item</code> objects may be accessed from a set.
 * When adding a new item type to this inventory, be sure to add the following
 * functions: add, remove, get, and createSet.
 * 
 * @author Coul Greer
 */
public interface Inventory extends Serializable {
	/**
	 * Name of the property to listen to that represents total weight. Used for
	 * PropertyChangeEvent.
	 */
	public static String PROPERTY_NAME_TOTAL_WEIGHT = "Total Weight";

	/**
	 * Name of the property to listen to that represents the collection of Weapons.
	 * Used for PropertyChangeEvent.
	 */
	public static String PROPERTY_NAME_WEAPONS = "Weapons";

	/**
	 * Name of the property to listen to that represents the collection of Armor.
	 * Used for PropertyChangeEvent.
	 */
	public static String PROPERTY_NAME_ARMORS = "Armors";

	/**
	 * Name of the property to listen to that represents the collection of
	 * Ammunition. Used for PropertyChangeEvent.
	 */
	public static String PROPERTY_NAME_AMMUNITION = "Ammunition";

	/**
	 * Name of the property to listen to that represents the collection of Item.
	 * Used for PropertyChangeEvent.
	 */
	public static String PROPERTY_NAME_ITEMS = "Items";

	/**
	 * Returns <code>true</code> if the given object is held within this inventory.
	 * 
	 * @param o the object to find in this inventory
	 * @return <code>true</code>, if the object is held by this inventory
	 */
	public boolean contains(Object o);

	/**
	 * Stores a <code>CyberpunkWeapon</code> in this inventory.
	 * 
	 * @param weapon a <code>CyberpunkWeapon</code> to store to this inventory
	 */
	public void add(CyberpunkWeapon weapon);

	/**
	 * Stores a <code>CyberpunkArmor</code> piece in this inventory.
	 * 
	 * @param armor a <code>CyberpunkArmor</code> piece to store in this inventory
	 */
	public void add(CyberpunkArmor armor);

	/**
	 * Stores an <code>Ammunition</code> in this inventory.
	 * 
	 * @param ammunition an <code>Ammunition</code> to store in this inventory
	 */
	public void add(Ammunition ammunition);

	/**
	 * Throws away a <code>CyberpunkWeapon</code> if the item is stored in this
	 * inventory.
	 * 
	 * @param weapon a <code>CyberpunkWeapon</code> to throw out of this inventory
	 */
	public void remove(CyberpunkWeapon weapon);

	/**
	 * Throws away a <code>CyberpunkArmor</code> piece if the item is stored in this
	 * inventory.
	 * 
	 * @param armor a <code>CyberpunkArmor</code> piece to throw out of this
	 *              inventory
	 */
	public void remove(CyberpunkArmor armor);

	/**
	 * Throws away an <code>Ammunition</code> if the item is stored in this
	 * inventory.
	 * 
	 * @param ammunition an <code>Ammunition</code> to throw out of this inventory
	 */
	public void remove(Ammunition ammunition);

	/**
	 * Makes a new set that contains a collection of <code>CyberpunkWeapon</code>.
	 * 
	 * @return a new set that contains a collection of <code>CyberpunkWeapon</code>
	 */
	public Set<CyberpunkWeapon> createWeaponSet();

	/**
	 * Makes a new set that contains a collection of <code>CyberpunkArmor</code>.
	 * 
	 * @return a new set that contains a collection of <code>CyberpunkArmor</code>
	 */
	public Set<CyberpunkArmor> createArmorSet();

	/**
	 * Makes a new set that contains a collection of <code>Ammunition</code>.
	 * 
	 * @return a new set that contains a collection of <code>Ammunition</code>
	 */
	public Set<Ammunition> createAmmunitionSet();

	/**
	 * Makes a new set that contains a collection of <code>Item</code>. The
	 * collection is indirectly mutated when objects are added or removed from this
	 * inventory
	 * 
	 * @return a new set that contains a collection of <code>Item</code>
	 */
	public Set<Item> createItemSet();

	/**
	 * Returns the amount of o that is held in this inventory.
	 * 
	 * @param o the object to count
	 * @return the count of the given object.
	 */
	public int getQuantity(Object o);

	/**
	 * Returns the collected weight of all held items.
	 * 
	 * @return the sum of all the weight from the held items
	 */
	public double getTotalWeight();

	/**
	 * Returns the total count of items stored.
	 * 
	 * @return the total count of items stored
	 */
	public int size();

	/**
	 * Add a PropertyChangeListener to the listener list. The listener is registered
	 * for all properties. The same listener object may be added more than once, and
	 * will be called as many times as it is added. If listener is null, no
	 * exception is thrown and no action is taken.
	 * 
	 * @param listener the PropertyChangeListner to be added
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(PropertyChangeListener)
	 *      PropertyChangeSupport
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Remove a PropertyChangeListener from the listener list. This removes a
	 * PropertyChangeListener that was registered for all properties. If listener
	 * was added more than once to the same event source, it will be notified one
	 * less time after being removed. If listener is null, or was never added, no
	 * exception is thrown and no action is taken.
	 * 
	 * @param listener the PropertyChangeListener to be removed
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(PropertyChangeListener)
	 *      PropertyChangeSupport
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Add a PropertyChangeListener for a specific property. The listener will be
	 * invoked only when a call on firePropertyChange names that specific property.
	 * The same listener object may be added more than once. For each property, the
	 * listener will be invoked the number of times it was added for that property.
	 * If propertyName or listener is null, no exception is thrown and no action is
	 * taken.
	 * 
	 * @param propertyName the name of the property to listen on
	 * @param listener     the PropertyChangeListner to be added
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(String,
	 *      PropertyChangeListener) PropertyChangeSupport
	 */
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);

	/**
	 * Remove a PropertyChangeListener for a specific property. If listener was
	 * added more than once to the same event source for the specified property, it
	 * will be notified one less time after being removed. If propertyName is null,
	 * no exception is thrown and no action is taken. If listener is null, or was
	 * never added for the specified property, no exception is thrown and no action
	 * is taken.
	 * 
	 * @param propertyName the name of the property that was listened on
	 * @param listener     the PropertyChangeListner to be removed
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(String,
	 *      PropertyChangeListener) PropertyChangeSupport
	 */
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);
}
