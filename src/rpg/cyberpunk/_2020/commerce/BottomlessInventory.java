package rpg.cyberpunk._2020.commerce;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import rpg.cyberpunk._2020.combat.CyberpunkArmor;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.general.combat.Ammunition;
import rpg.general.commerce.Item;

/**
 * An Inventory without any limit to its storage. This includes weight and Item
 * count.
 * 
 * @author Coul Greer
 */
public class BottomlessInventory implements Inventory {
	private ArrayList<CyberpunkWeapon> weapons;
	private ArrayList<CyberpunkArmor> armors;
	private ArrayList<Ammunition> ammunitions;
	private ArrayList<Item> items;
	private double weight;
	private PropertyChangeSupport changeSupport;

	/**
	 * Construct an empty Inventory that creates an empty list for CyberpunkWeapon,
	 * CyberpunkArmor, Ammunition, and general items as well as initializing the
	 * starting weight of the inventory to zero.
	 */
	public BottomlessInventory() {
		weapons = new ArrayList<>();
		armors = new ArrayList<>();
		ammunitions = new ArrayList<>();
		items = new ArrayList<>();
		weight = 0.0;
		changeSupport = new PropertyChangeSupport(this);
	}

	@Override
	public boolean contains(Object o) {
		return items.contains(o);
	}

	@Override
	public void add(CyberpunkWeapon weapon) {
		if (weapon == null) {
			throw new IllegalArgumentException("Null CyberpunkWeapon cannot be added to BottomlessInventory.");
		} else {
			weapons.add(weapon);
			items.add(weapon);

			calculateWeight();
		}
	}

	private void calculateWeight() {
		double oldValue = weight;
		weight = items.stream().mapToDouble(i -> i.getWeight()).sum();

		changeSupport.firePropertyChange(PROPERTY_NAME_TOTAL_WEIGHT, oldValue, weight);
	}

	@Override
	public void add(CyberpunkArmor armor) {
		if (armor == null) {
			throw new IllegalArgumentException("Null CyberpunkArmor cannot be added to BottomlessInventory.");
		} else {
			armors.add(armor);
			items.add(armor);

			calculateWeight();
		}
	}

	@Override
	public void add(Ammunition ammunition) {
		if (ammunition == null) {
			throw new IllegalArgumentException("Null Ammunition cannot be added to BottomlessInventory.");
		} else {
			ammunitions.add(ammunition);
			items.add(ammunition);

			calculateWeight();
		}
	}

	@Override
	public void addItem(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("Null Item cannot be added to BottomlessInventory.");
		} else {
			items.add(item);

			calculateWeight();
		}
	}

	@Override
	public void remove(CyberpunkWeapon weapon) {
		weapons.remove(weapon);
		items.remove(weapon);

		calculateWeight();
	}

	@Override
	public void remove(CyberpunkArmor armor) {
		armors.remove(armor);
		items.remove(armor);

		calculateWeight();
	}

	@Override
	public void remove(Ammunition ammunition) {
		ammunitions.remove(ammunition);
		items.remove(ammunition);

		calculateWeight();
	}

	@Override
	public void removeItem(Item item) {
		weapons.remove(item);
		armors.remove(item);
		ammunitions.remove(item);
		items.remove(item);
	}

	@Override
	public Set<CyberpunkWeapon> createWeaponSet() {
		return new HashSet<CyberpunkWeapon>(weapons);
	}

	@Override
	public Set<CyberpunkArmor> createArmorSet() {
		return new HashSet<CyberpunkArmor>(armors);
	}

	@Override
	public Set<Ammunition> createAmmunitionSet() {
		return new HashSet<Ammunition>(ammunitions);
	}

	@Override
	public Set<Item> createItemSet() {
		return new HashSet<Item>(items);
	}

	@Override
	public int getQuantity(Object o) {
		return Collections.frequency(items, o);
	}

	@Override
	public double getTotalWeight() {
		return weight;
	}

	@Override
	public int size() {
		return items.size();
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	@Override
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}

	@Override
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(propertyName, listener);
	}
}
