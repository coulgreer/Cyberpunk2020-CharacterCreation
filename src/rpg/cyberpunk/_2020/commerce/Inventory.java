package rpg.cyberpunk._2020.commerce;

import java.io.Serializable;
import java.util.Collection;
import rpg.cyberpunk._2020.combat.CyberpunkArmor;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.general.combat.Ammunition;
import rpg.general.commerce.Item;
import rpg.util.Measurement;

/**
 * Used to keep track of multiple <code>Item</code> objects and its many subclasses that will be
 * used in the desired game. In this case, the game would be Cyberpunk 2020. When a subclass of
 * <code>Item</code> is added to this inventory it should also be added to the collection of
 * <code>Item</code> as well so that all <code>Item</code> objects may be accessed from a set. When
 * adding a new item type to this inventory, be sure to add the following functions: add, remove,
 * get, and createSet.
 */
public interface Inventory extends Serializable {
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
   * Stores a generic item.
   * 
   * @param item a generic element
   */
  public void addItem(Item item);

  /**
   * Throws away a <code>CyberpunkWeapon</code> if the item is stored in this inventory.
   * 
   * @param weapon a <code>CyberpunkWeapon</code> to throw out of this inventory
   */
  public void remove(CyberpunkWeapon weapon);

  /**
   * Throws away a <code>CyberpunkArmor</code> piece if the item is stored in this inventory.
   * 
   * @param armor a <code>CyberpunkArmor</code> piece to throw out of this inventory
   */
  public void remove(CyberpunkArmor armor);

  /**
   * Throws away an <code>Ammunition</code> if the item is stored in this inventory.
   * 
   * @param ammunition an <code>Ammunition</code> to throw out of this inventory
   */
  public void remove(Ammunition ammunition);

  /**
   * Throws away a generic item from all categories.
   * 
   * @param item a generic stored element
   */
  public void removeItem(Item item);

  /**
   * Returns a new collection that contains <code>CyberpunkWeapon</code>.
   * 
   * @return a new collection that contains <code>CyberpunkWeapon</code>
   */
  public Collection<CyberpunkWeapon> createWeaponCollection();

  /**
   * Returns a new collection that contains <code>CyberpunkArmor</code>.
   * 
   * @return a new collection that contains <code>CyberpunkArmor</code>
   */
  public Collection<CyberpunkArmor> createArmorCollection();

  /**
   * Returns a new collection that contains <code>Ammunition</code>.
   * 
   * @return a new collection that contains <code>Ammunition</code>
   */
  public Collection<Ammunition> createAmmunitionCollection();

  /**
   * Returns a new collection that contains <code>Item</code>. The collection is indirectly mutated
   * when objects are added or removed from this inventory
   * 
   * @return a new list that contains a collection of <code>Item</code>
   */
  public Collection<Item> createItemCollection();

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
  public Measurement getTotalWeight();

  /**
   * Returns the total count of items stored.
   * 
   * @return the total count of items stored
   */
  public int getTotalItemCount();

}
