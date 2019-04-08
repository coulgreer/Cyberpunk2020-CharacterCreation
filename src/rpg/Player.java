package rpg;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import rpg.cyberpunk._2020.combat.CyberpunkArmor;
import rpg.cyberpunk._2020.combat.CyberpunkCombatant;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.cyberpunk._2020.combat.FightingMove;
import rpg.cyberpunk._2020.combat.FightingStyle;
import rpg.cyberpunk._2020.commerce.BottomlessInventory;
import rpg.cyberpunk._2020.commerce.Inventory;
import rpg.cyberpunk._2020.commerce.PlayerTrader;
import rpg.cyberpunk._2020.stats.AttributeManager;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.cyberpunk._2020.stats.Role;
import rpg.cyberpunk._2020.stats.SkillManager;
import rpg.general.combat.Ammunition;
import rpg.general.combat.AmmunitionContainer;
import rpg.general.commerce.Item;
import rpg.general.commerce.Trader;
import rpg.general.stats.Attribute;
import rpg.general.stats.StatisticManager;
import rpg.util.Probability;

public class Player {
	public static final String PROPERTY_NAME_MONEY = "Money";
	public static final String PROPERTY_NAME_INVENTORY_WEIGHT = "Inventory";
	public static final String PROPERTY_NAME_INVENTORY_WEAPON_MANIPULATED = "Inventory: Weapon Manipulated";
	public static final String PROPERTY_NAME_INVENTORY_ARMOR_MANIPULATED = "Inventory: Armor Manipulated";
	public static final String PROPERTY_NAME_ROLE = "Role";

	private PropertyChangeSupport changeSupport;
	private Inventory pocketInventory = new BottomlessInventory();
	private Inventory equippedInventory = new BottomlessInventory();
	private Role role;
	private Trader trader;
	private CyberpunkCombatant combatant;
	private StatisticManager<Attribute> attributeManager;
	private StatisticManager<CyberpunkSkill> skillManager;

	public Player() {
		changeSupport = new PropertyChangeSupport(this);
		trader = new PlayerTrader(0.0);
		combatant = new CyberpunkCombatant(this);
		attributeManager = new AttributeManager();
		skillManager = new SkillManager(attributeManager, this);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void arm(int slot, CyberpunkWeapon weapon) {
		if (pocketInventory.contains(weapon)) {
			pocketInventory.remove(weapon);
			equippedInventory.add(weapon);
			combatant.arm(slot, weapon);
		}
	}

	public void disarm(int slot) {
		// TODO Think of a solution that does not require casting. Casting is not
		// polymorphic however Combatant hinders our polymorphism by returning Weapon
		// instead of CyberpunkWeapon.
		CyberpunkWeapon weapon = (CyberpunkWeapon) combatant.getWeapon(slot);
		if (equippedInventory.contains(weapon)) {
			pocketInventory.add(weapon);
			equippedInventory.remove(weapon);
			combatant.disarm(slot);
		}
	}

	public Probability getTotalHitChance(int slot) {
		return combatant.getTotalHitChance(slot);
	}

	public int getAttributeValue(String attributeName) {
		return attributeManager.getBaseLevel(attributeName);
	}

	public int getSkillValue(String skillName) {
		CyberpunkSkill skill = skillManager.getStatistic(skillName);
		return skill.getTotalValue();
	}

	public int getRangeScore(int slot) {
		return combatant.getRangeScore(slot);
	}

	public void increaseSkillLevel(String skillName) {
		skillManager.increaseLevel(skillName);
	}

	public void attack(int slot, int shotsFired) {
		combatant.attack(slot, shotsFired);
	}

	// TODO Find a clean way to transfer ammunition from inventory to weapon.
	public void reload(int slot, AmmunitionContainer ammunitionStorage) {
		combatant.reload(slot, ammunitionStorage);
	}

	public void setFightingStance(FightingStyle style, FightingMove move) {
		combatant.setUnarmedStance(style, move);
	}

	public int getAmmoCount(int slot) {
		return combatant.getAmmoCount(slot);
	}

	public boolean hasItem(Object o) {
		return pocketInventory.contains(o);
	}

	public void addToInventory(CyberpunkWeapon weapon) {
		double oldWeight = getTotalWeight();

		pocketInventory.add(weapon);

		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEAPON_MANIPULATED, null, weapon);
		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEIGHT, oldWeight, getTotalWeight());
	}

	public void addToInventory(CyberpunkArmor armor) {
		double oldWeight = getTotalWeight();

		pocketInventory.add(armor);

		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ARMOR_MANIPULATED, null, armor);
		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEIGHT, oldWeight, getTotalWeight());
	}

	public void addToInventory(Ammunition ammunition) {
		pocketInventory.add(ammunition);
	}

	public void removeFromInventory(CyberpunkWeapon weapon, int quantity) {
		double oldWeight = getTotalWeight();

		pocketInventory.remove(weapon);

		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEAPON_MANIPULATED, null, weapon);
		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEIGHT, oldWeight, getTotalWeight());
	}

	public void removeFromInventory(CyberpunkArmor armor, int quantity) {
		double oldWeight = getTotalWeight();

		pocketInventory.remove(armor);

		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ARMOR_MANIPULATED, null, armor);
		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEIGHT, oldWeight, getTotalWeight());
	}

	public void removeFromInventory(Ammunition ammunition, int quantity) {
		pocketInventory.remove(ammunition);
	}

	public Set<CyberpunkWeapon> getCarriedWeapons() {
		return pocketInventory.createWeaponSet();
	}

	public Set<CyberpunkArmor> getCarriedArmors() {
		return pocketInventory.createArmorSet();
	}

	public Set<Ammunition> getCarriedAmmunition() {
		return pocketInventory.createAmmunitionSet();
	}

	public Set<Item> getCarriedItems() {
		return pocketInventory.createItemSet();
	}

	public double getTotalWeight() {
		return pocketInventory.getTotalWeight() + equippedInventory.getTotalWeight();
	}

	public boolean canBuy(double price) {
		return trader.canBuy(price);
	}

	public boolean canSell(Item item, int quantity) {
		boolean hasQuantity = pocketInventory.getQuantity(item) >= quantity;
		return pocketInventory.contains(item) && hasQuantity;
	}

	public void buy(CyberpunkWeapon weapon, double price) {
		double oldMoney = trader.getMoney();

		trader.buy(price);
		addToInventory(weapon);

		changeSupport.firePropertyChange(PROPERTY_NAME_MONEY, oldMoney, trader.getMoney());
	}

	public void buy(CyberpunkArmor armor, double price) {
		double oldMoney = trader.getMoney();

		trader.buy(price);
		addToInventory(armor);

		changeSupport.firePropertyChange(PROPERTY_NAME_MONEY, oldMoney, trader.getMoney());
	}

	public void buy(List<Ammunition> ammunition, double price) {
		trader.buy(price);

		Iterator<Ammunition> iterator = ammunition.iterator();
		while (iterator.hasNext()) {
			addToInventory(iterator.next());
		}
	}

	public void sell(CyberpunkWeapon weapon, double price) {
		double oldMoney = trader.getMoney();

		trader.sell(price);
		removeFromInventory(weapon, 1);

		changeSupport.firePropertyChange(PROPERTY_NAME_MONEY, oldMoney, trader.getMoney());
	}

	public void sell(CyberpunkArmor armor, double price) {
		double oldMoney = trader.getMoney();

		trader.sell(price);
		removeFromInventory(armor, 1);

		changeSupport.firePropertyChange(PROPERTY_NAME_MONEY, oldMoney, trader.getMoney());
	}

	public void sell(Ammunition ammunition, double price) {
		double oldMoney = trader.getMoney();

		trader.sell(price);
		pocketInventory.remove(ammunition);

		changeSupport.firePropertyChange(PROPERTY_NAME_MONEY, oldMoney, trader.getMoney());
	}

	public double getMoney() {
		return trader.getMoney();
	}

	public Role getRole() {
		return role;
	}
}
