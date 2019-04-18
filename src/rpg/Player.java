package rpg;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import rpg.cyberpunk._2020.combat.ArmorManager;
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
import rpg.general.combat.BodyLocation;
import rpg.general.commerce.Item;
import rpg.general.commerce.Trader;
import rpg.general.stats.Attribute;
import rpg.general.stats.StatisticManager;
import rpg.util.Probability;

public class Player {
	public static final String PROPERTY_NAME_MONEY = "Money";
	public static final String PROPERTY_NAME_INVENTORY_WEIGHT = "Inventory: Weight";
	public static final String PROPERTY_NAME_INVENTORY_WEAPON_MANIPULATED = "Inventory: Weapon Manipulated";
	public static final String PROPERTY_NAME_INVENTORY_ARMOR_MANIPULATED = "Inventory: Armor Manipulated";
	public static final String PROPERTY_NAME_INVENTORY_AMMUNITION_MANIPULATED = "Inventory: Ammunition Manipulated";
	public static final String PROPERTY_NAME_INVENTORY_ITEM_MANIPULATED = "Inventory: Item Manipulated";
	public static final String PROPERTY_NAME_WEAPON_EQUIPPED = "Equipped: Weapon";
	public static final String PROPERTY_NAME_ARMOR_EQUIPPED = "Equipped: Armor";
	public static final String PROPERTY_NAME_ROLE = "Role";

	private PropertyChangeSupport changeSupport;
	private Inventory pocketInventory = new BottomlessInventory();
	private Inventory equippedInventory = new BottomlessInventory();
	private Role role;
	private Trader trader;
	private CyberpunkCombatant combatant;
	private ArmorManager armorManager;
	private StatisticManager<Attribute> attributeManager;
	private StatisticManager<CyberpunkSkill> skillManager;

	public Player() {
		changeSupport = new PropertyChangeSupport(this);
		trader = new PlayerTrader(0.0);
		combatant = new CyberpunkCombatant(this);
		armorManager = new ArmorManager();
		attributeManager = new AttributeManager();
		skillManager = new SkillManager(attributeManager, this);
	}

	public void buy(CyberpunkWeapon weapon, double price) {
		double oldMoney = trader.getMoney();

		try {
			trader.buy(price);
			addToInventory(weapon);
		} catch (Exception ex) {
			trader.sell(price);
			throw ex;
		}

		changeSupport.firePropertyChange(PROPERTY_NAME_MONEY, oldMoney, trader.getMoney());
	}

	public void buy(CyberpunkArmor armor, double price) {
		double oldMoney = trader.getMoney();

		try {
			trader.buy(price);
			addToInventory(armor);
		} catch (Exception ex) {
			trader.sell(price);
			throw ex;
		}

		changeSupport.firePropertyChange(PROPERTY_NAME_MONEY, oldMoney, trader.getMoney());
	}

	public void buy(List<Ammunition> ammunition, double price) {
		double oldMoney = trader.getMoney();

		try {
			trader.buy(price);

			Iterator<Ammunition> iterator = ammunition.iterator();
			while (iterator.hasNext()) {
				addToInventory(iterator.next());
			}
		} catch (Exception ex) {
			trader.sell(price);
			throw ex;
		}

		changeSupport.firePropertyChange(PROPERTY_NAME_MONEY, oldMoney, trader.getMoney());
	}

	public void sell(CyberpunkWeapon weapon, double price) {
		double oldMoney = trader.getMoney();

		try {
			trader.sell(price);
			removeFromInventory(weapon, 1);
		} catch (Exception ex) {
			trader.buy(price);
			throw ex;
		}

		changeSupport.firePropertyChange(PROPERTY_NAME_MONEY, oldMoney, trader.getMoney());
	}

	public void sell(CyberpunkArmor armor, double price) {
		double oldMoney = trader.getMoney();

		try {
			trader.sell(price);
			removeFromInventory(armor, 1);
		} catch (Exception ex) {
			trader.buy(price);
			throw ex;
		}

		changeSupport.firePropertyChange(PROPERTY_NAME_MONEY, oldMoney, trader.getMoney());
	}

	public void sell(Ammunition ammunition, double price) {
		double oldMoney = trader.getMoney();

		try {
			trader.sell(price);
			removeFromInventory(ammunition, 1);
		} catch (Exception ex) {
			trader.buy(price);
			throw ex;
		}

		changeSupport.firePropertyChange(PROPERTY_NAME_MONEY, oldMoney, trader.getMoney());
	}

	public void sell(Item item, double price) {
		double oldMoney = trader.getMoney();

		trader.sell(price);
		try {
			removeFromInventory(item, 1);
		} catch (Exception ex) {
			trader.buy(price);
			throw ex;
		}

		changeSupport.firePropertyChange(PROPERTY_NAME_MONEY, oldMoney, trader.getMoney());

	}

	public double getMoney() {
		return trader.getMoney();
	}

	public void equip(int slot, CyberpunkWeapon weapon) {
		CyberpunkWeapon tempWeapon = (CyberpunkWeapon) combatant.getWeapon(slot);

		unequip(slot);
		try {
			removeFromInventory(weapon, 1);
			addToEquipped(weapon);
			combatant.arm(slot, weapon);
		} catch (NullPointerException ex) {
			equip(slot, tempWeapon);
			throw ex;
		}

		changeSupport.firePropertyChange(PROPERTY_NAME_WEAPON_EQUIPPED, tempWeapon, weapon);
	}

	// TODO Think of throwing an exception to allow propagation of an error to the
	// UI.
	public void equip(CyberpunkArmor armor) {
		if (armorManager.add(armor)) {
			removeFromInventory(armor, 1);
			addToEquipped(armor);

			changeSupport.firePropertyChange(PROPERTY_NAME_ARMOR_EQUIPPED, null, armor);
		}
	}

	public void unequip(int slot) {
		CyberpunkWeapon weapon = (CyberpunkWeapon) combatant.getWeapon(slot);

		addToInventory(weapon);
		removeFromEquipped(weapon);
		combatant.disarm(slot);
	}

	// TODO Think of throwing an exception to allow propagation of an error to the
	// UI.
	public void unequip(CyberpunkArmor armor) {
		if (armorManager.remove(armor)) {
			addToInventory(armor);
			removeFromEquipped(armor);
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

	public List<Ammunition> reload(int slot, AmmunitionContainer ammunitionStorage) {
		List<Ammunition> spareAmmunition = combatant.reload(slot, ammunitionStorage);
		spareAmmunition.stream().forEach(a -> addToInventory(a));
		return spareAmmunition;
	}

	public void setFightingStance(FightingStyle style, FightingMove move) {
		combatant.setUnarmedStance(style, move);
	}

	public CyberpunkWeapon getWeapon(int slot) {
		return (CyberpunkWeapon) combatant.getWeapon(slot);
	}

	public boolean hasItem(Object o) {
		return pocketInventory.contains(o);
	}

	public int getQuantity(Item item) {
		return pocketInventory.getQuantity(item);
	}

	public void addToInventory(CyberpunkWeapon weapon) {
		double oldWeight = getTotalWeight();

		if (!CyberpunkWeapon.WEAPON_TYPE_UNARMED.equals(weapon.getWeaponType())) {
			pocketInventory.add(weapon);
		}

		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEAPON_MANIPULATED, null, weapon);
		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ITEM_MANIPULATED, null, weapon);
		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEIGHT, oldWeight, getTotalWeight());
	}

	public void addToInventory(CyberpunkArmor armor) {
		double oldWeight = getTotalWeight();

		pocketInventory.add(armor);

		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ARMOR_MANIPULATED, null, armor);
		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ITEM_MANIPULATED, null, armor);
		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEIGHT, oldWeight, getTotalWeight());
	}

	public void addToInventory(Ammunition ammunition) {
		double oldWeight = getTotalWeight();

		pocketInventory.add(ammunition);

		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_AMMUNITION_MANIPULATED, null, ammunition);
		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ITEM_MANIPULATED, null, ammunition);
		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEIGHT, oldWeight, getTotalWeight());
	}

	public void addToEquipped(CyberpunkWeapon weapon) {
		if (!CyberpunkWeapon.WEAPON_TYPE_UNARMED.equals(weapon.getWeaponType())) {
			equippedInventory.add(weapon);
		}

		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEAPON_MANIPULATED, null, weapon);
		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ITEM_MANIPULATED, null, weapon);
	}

	public void addToEquipped(CyberpunkArmor armor) {
		equippedInventory.add(armor);

		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ARMOR_MANIPULATED, null, armor);
		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ITEM_MANIPULATED, null, armor);
	}

	public void removeFromInventory(CyberpunkWeapon weapon, int quantity) {
		double oldWeight = getTotalWeight();

		if (!CyberpunkWeapon.WEAPON_TYPE_UNARMED.equals(weapon.getWeaponType())) {
			for (int i = 0; i < quantity; i++) {
				pocketInventory.remove(weapon);
			}
		}

		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEAPON_MANIPULATED, weapon, null);
		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ITEM_MANIPULATED, weapon, null);
		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEIGHT, oldWeight, getTotalWeight());
	}

	public void removeFromInventory(CyberpunkArmor armor, int quantity) {
		double oldWeight = getTotalWeight();

		for (int i = 0; i < quantity; i++) {
			pocketInventory.remove(armor);
		}

		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ARMOR_MANIPULATED, armor, null);
		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ITEM_MANIPULATED, armor, null);
		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEIGHT, oldWeight, getTotalWeight());
	}

	public void removeFromInventory(Ammunition ammunition, int quantity) {
		double oldWeight = getTotalWeight();

		for (int i = 0; i < quantity; i++) {
			pocketInventory.remove(ammunition);
		}

		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_AMMUNITION_MANIPULATED, ammunition, null);
		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ITEM_MANIPULATED, ammunition, null);
		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEIGHT, oldWeight, getTotalWeight());
	}

	public void removeFromInventory(Item item, int quantity) {
		double oldWeight = getTotalWeight();

		for (int i = 0; i < quantity; i++) {
			pocketInventory.removeItem(item);
		}

		if (item instanceof CyberpunkWeapon) {
			changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEAPON_MANIPULATED, item, null);
		}

		if (item instanceof CyberpunkArmor) {
			changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ARMOR_MANIPULATED, item, null);
		}

		if (item instanceof Ammunition) {
			changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_AMMUNITION_MANIPULATED, item, null);
		}

		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ITEM_MANIPULATED, item, null);
		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEIGHT, oldWeight, getTotalWeight());
	}

	public void removeFromEquipped(CyberpunkWeapon weapon) {
		if (!CyberpunkWeapon.WEAPON_TYPE_UNARMED.equals(weapon.getWeaponType())) {
			equippedInventory.remove(weapon);
		}

		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_WEAPON_MANIPULATED, weapon, null);
		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ITEM_MANIPULATED, weapon, null);
	}

	public void removeFromEquipped(CyberpunkArmor armor) {
		equippedInventory.remove(armor);

		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ARMOR_MANIPULATED, armor, null);
		changeSupport.firePropertyChange(PROPERTY_NAME_INVENTORY_ITEM_MANIPULATED, armor, null);
	}

	public Collection<CyberpunkWeapon> createCarriedWeaponCollection() {
		return pocketInventory.createWeaponCollection();
	}

	public Collection<CyberpunkArmor> createCarriedArmorCollection() {
		return pocketInventory.createArmorCollection();
	}

	public Collection<Ammunition> createCarriedAmmunitionCollection() {
		return pocketInventory.createAmmunitionCollection();
	}

	public Collection<Item> createCarriedItemCollection() {
		return pocketInventory.createItemCollection();
	}

	public Collection<CyberpunkArmor> createEquippedArmorCollection() {
		return equippedInventory.createArmorCollection();
	}

	public double getTotalWeight() {
		return pocketInventory.getTotalWeight() + equippedInventory.getTotalWeight();
	}

	public int getLocationDurability(BodyLocation location) {
		return armorManager.getLocationDurability(location);
	}

	public int getEncumbranceValue() {
		return armorManager.getEncumbranceValue();
	}

	public Role getRole() {
		return role;
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
}
