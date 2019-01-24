package rpg;

import java.util.HashMap;
import java.util.Map;

import rpg.cyberpunk._2020.AttributeName;
import rpg.cyberpunk._2020.combat.CyberpunkCombatant;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.cyberpunk._2020.combat.FightingMove;
import rpg.cyberpunk._2020.combat.FightingStyle;
import rpg.cyberpunk._2020.commerce.PlayerTrader;
import rpg.cyberpunk._2020.stats.AttributeManager;
import rpg.cyberpunk._2020.stats.SkillManager;
import rpg.general.commerce.Cashier;
import rpg.general.commerce.Trader;
import rpg.general.commerce.Product;
import rpg.general.commerce.QuantifiableProduct;
import rpg.general.commerce.TradeProduct;
import rpg.general.commerce.Vendor;
import rpg.util.Probability;

public class Player {
	private Trader customer;
	private CyberpunkCombatant combatant;
	private AttributeManager attributeManager;
	private SkillManager skillManager;

	private double money;
	private Map<String, QuantifiableProduct> inventory = new HashMap<String, QuantifiableProduct>();

	public Player() {
		customer = new PlayerTrader(this);
		combatant = new CyberpunkCombatant(this);
		attributeManager = new AttributeManager();
		skillManager = new SkillManager(attributeManager);

		money = 0.0;
	}

	public void arm(int slot, CyberpunkWeapon weapon) {
		combatant.arm(slot, weapon);
	}
	
	public void disarm(int slot) {
		combatant.disarm(slot);
	}
	
	public Probability getTotalHitChance(int slot) {
		return combatant.getTotalHitChance(slot);
	}

	public int getAttributeValue(AttributeName attributeName) {
		return attributeManager.getLevel(attributeName);
	}

	public int getSkillValue(String skillName) {
		return skillManager.getTotalValue(skillName);
	}
	
	public int getRangeScore(int slot) {
		return combatant.getRangeScore(slot);
	}

	public void increaseSkillLevel(String skillName) {
		skillManager.increaseSkill(skillName);
	}

	public void attack(int slot, int shotsFired) {
		combatant.attack(slot, shotsFired);
	}

	// TODO Implement this method once Inventory is implemented.
	public void reload(int slot) {
		// combatant.reload(slot);
	}

	public void setFightingStance(FightingStyle style, FightingMove move) {
		combatant.setUnarmedStance(style, move);
	}

	public int getAmmoCount(int slot) {
		return combatant.getAmmoCount(slot);
	}

	public void trade(Cashier cashier, Vendor<?> vendor, Product product, int quantity) {
		cashier.setCommand(new TradeProduct(vendor, customer, product, quantity));
		cashier.checkout();
	}

	public void addToInventory(QuantifiableProduct item) {
		if (inventory.containsValue(item)) {
			QuantifiableProduct existingItem = inventory.get(item.getName());
			existingItem.increaseQuantity(item.getQuantity());
			inventory.put(existingItem.getName(), existingItem);
		} else {
			inventory.put(item.getName(), item);
		}
	}

	public void removeFromInventory(Product item, int quantity) {
		if (inventory.containsValue(item)) {
			QuantifiableProduct existingItem = inventory.get(item.getName());
			existingItem.decreaseQuantity(quantity);
			if (existingItem.getQuantity() > 0) {
				inventory.put(existingItem.getName(), existingItem);
			} else {
				inventory.remove(existingItem.getName());
			}
		}
	}

	public QuantifiableProduct getProduct(String productName) {
		return inventory.get(productName);
	}

	public void increaseMoney(double money) {
		this.money += money;
	}

	public void decreaseMoney(double money) {
		this.money -= money;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
}
