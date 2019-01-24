package rpg.cyberpunk._2020.commerce;

import java.util.HashMap;
import java.util.Map;

import rpg.cyberpunk._2020.combat.Ammunition.AmmoType;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Availability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Concealability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Reliability;
import rpg.cyberpunk._2020.combat.MissileWeapon;
import rpg.general.commerce.Tradeable;
import rpg.general.commerce.Product;
import rpg.general.commerce.Vendor;
import rpg.util.Die;
import rpg.util.Probability;

public class CyberpunkWeaponVendor extends Vendor<CyberpunkWeapon> {
	private Map<String, CyberpunkWeapon> inventory;

	public CyberpunkWeaponVendor() {
		inventory = new HashMap<String, CyberpunkWeapon>();
		addWeaponsToVendor();
	}

	private void addWeaponsToVendor() {
		// TODO Add inventory from Playerbook.

		// Light Pistols
		add(MissileWeapon.createTypeBasedWeapon("BudgetArms C-13",
				"A light duty autopistol used as a holdout and \"lady's gun\".", CyberpunkWeapon.LIGHT_PISTOL, -1,
				Concealability.POCKET, Availability.EXCELLENT, new Probability(new Die(1, 6), 0), AmmoType._5MM, 8, 2,
				Reliability.STANDARD, 50, 0.5, 75));
		add(MissileWeapon.createTypeBasedWeapon("Dai Lung Cybermag 15",
				"Cheap Hong Kong knockoff, often used by boosters and other street trash.",
				CyberpunkWeapon.LIGHT_PISTOL, -1, Concealability.POCKET, Availability.COMMON,
				new Probability(new Die(1, 6), 1), AmmoType._6MM, 10, 2, Reliability.UNRELIABLE, 50, 0.5, 50));
		add(MissileWeapon.createTypeBasedWeapon("Federated Arms X-22",
				"The ubiquitous \"Polymer-one-shot\" cheap plastic pistol. Available in designer colors.",
				CyberpunkWeapon.LIGHT_PISTOL, 0, Concealability.POCKET, Availability.EXCELLENT,
				new Probability(new Die(1, 6), 1), AmmoType._6MM, 10, 2, Reliability.STANDARD, 50, 0.5, 150));

		// Medium Pistols
	}

	private void add(CyberpunkWeapon weapon) {
		inventory.put(weapon.getName(), weapon);
	}

	public boolean isInStock(Product product, int quantity) {
		return inventory.containsKey(product.getName());
	}

	protected void sell(Tradeable product, int quantity) {
		// Do nothing because the duty of this class is just to keep track of the
		// weapons that the Player can buy, not the amount of money the vendor has.
	}

	protected void removeFromInventory(Product product, int quantity) {
		// Do nothing because the duty of this class is just to keep track of the
		// weapons that the Player can buy, not the amount of items a vendor has.
	}
}
