package rpg.cyberpunk._2020.commerce;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import rpg.cyberpunk._2020.combat.CyberpunkArmor;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.general.combat.Ammunition;
import rpg.general.commerce.Item;

/**
 * An Inventory without any limit to its storage. This includes weight and Item count.
 */
public class BottomlessInventory implements Inventory {
  private static final long serialVersionUID = 1L;

  private ArrayList<CyberpunkWeapon> weapons;
  private ArrayList<CyberpunkArmor> armors;
  private ArrayList<Ammunition> ammunitions;
  private ArrayList<Item> items;

  /**
   * Construct an empty Inventory that creates an empty list for CyberpunkWeapon, CyberpunkArmor,
   * Ammunition, and general items as well as initializing the starting weight of the inventory to
   * zero.
   */
  public BottomlessInventory() {
    weapons = new ArrayList<>();
    armors = new ArrayList<>();
    ammunitions = new ArrayList<>();
    items = new ArrayList<>();
  }

  @Override
  public boolean contains(Object o) {
    return items.contains(o);
  }

  /**
   * @throws NullPointerException if weapon is null
   */
  @Override
  public void add(CyberpunkWeapon weapon) {
    if (weapon == null) {
      throw new NullPointerException();
    } else {
      weapons.add(weapon);
      items.add(weapon);
    }
  }

  /**
   * @throws NullPointerException if armor is null
   */
  @Override
  public void add(CyberpunkArmor armor) {
    if (armor == null) {
      throw new NullPointerException();
    } else {
      armors.add(armor);
      items.add(armor);
    }
  }

  /**
   * @throws NullPointerException if ammunition is null
   */
  @Override
  public void add(Ammunition ammunition) {
    if (ammunition == null) {
      throw new NullPointerException();
    } else {
      ammunitions.add(ammunition);
      items.add(ammunition);
    }
  }

  /**
   * @throws NullPointerException if item is null
   */
  @Override
  public void addItem(Item item) {
    if (item == null) {
      throw new NullPointerException();
    } else {
      items.add(item);
    }
  }

  /**
   * @throws NullPointerException if weapon is null
   */
  @Override
  public void remove(CyberpunkWeapon weapon) {
    if (weapon == null) {
      throw new NullPointerException();
    } else {
      weapons.remove(weapon);
      items.remove(weapon);
    }
  }

  /**
   * @throws NullPointerException if armor is null
   */
  @Override
  public void remove(CyberpunkArmor armor) {
    if (armor == null) {
      throw new NullPointerException();
    } else {
      armors.remove(armor);
      items.remove(armor);
    }
  }

  /**
   * @throws NullPointerException if ammunition is null
   */
  @Override
  public void remove(Ammunition ammunition) {
    if (ammunition == null) {
      throw new NullPointerException();
    } else {
      ammunitions.remove(ammunition);
      items.remove(ammunition);
    }
  }

  /**
   * @throws NullPointerException if item is null
   */
  @Override
  public void removeItem(Item item) {
    if (item == null) {
      throw new NullPointerException();
    } else {
      weapons.remove(item);
      armors.remove(item);
      ammunitions.remove(item);
      items.remove(item);
    }
  }

  @Override
  public Collection<CyberpunkWeapon> createWeaponCollection() {
    return new ArrayList<CyberpunkWeapon>(weapons);
  }

  @Override
  public Collection<CyberpunkArmor> createArmorCollection() {
    return new ArrayList<CyberpunkArmor>(armors);
  }

  @Override
  public Collection<Ammunition> createAmmunitionCollection() {
    return new ArrayList<Ammunition>(ammunitions);
  }

  @Override
  public Collection<Item> createItemCollection() {
    return new ArrayList<Item>(items);
  }

  @Override
  public int getQuantity(Object o) {
    return Collections.frequency(items, o);
  }

  @Override
  public double getTotalWeight() {
    return items.stream().mapToDouble(i -> i.getWeight()).sum();
  }

  @Override
  public int getTotalItemCount() {
    return items.size();
  }

}
