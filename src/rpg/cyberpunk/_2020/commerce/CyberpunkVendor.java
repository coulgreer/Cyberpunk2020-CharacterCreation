package rpg.cyberpunk._2020.commerce;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.SerializationUtils;

import rpg.cyberpunk._2020.combat.Cartridge;
import rpg.cyberpunk._2020.combat.CyberpunkArmor;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Availability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Concealability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Reliability;
import rpg.cyberpunk._2020.combat.CyberpunkWeaponModifier;
import rpg.cyberpunk._2020.combat.HomogeneousMagazine;
import rpg.cyberpunk._2020.combat.MissileWeapon;
import rpg.general.combat.Ammunition;
import rpg.general.commerce.Item;
import rpg.general.commerce.Trader;
import rpg.util.Die;
import rpg.util.Probability;

public class CyberpunkVendor {
	private Trader trader;
	private Inventory startingInventory;

	public CyberpunkVendor(Trader trader) {
		this.trader = trader;
		startingInventory = new BottomlessInventory();
		addWeaponsToVendor();
	}

	private void addWeaponsToVendor() {
		// TODO Add inventory from Playerbook.

		// Light Pistols
		Set<String> lightPistolAttachmentPoints = new HashSet<String>();
		lightPistolAttachmentPoints.add(CyberpunkWeaponModifier.ATTACHMENT_POINT_BARREL);
		lightPistolAttachmentPoints.add(CyberpunkWeaponModifier.ATTACHMENT_POINT_OPTIC);
		lightPistolAttachmentPoints.add(CyberpunkWeaponModifier.ATTACHMENT_POINT_GRIP);

		startingInventory.add(
				new MissileWeapon("BudgetArms C-13", "A light duty autopistol used as a holdout and \"lady's gun\".",
						CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, -1, Concealability.POCKET, Availability.EXCELLENT,
						new Probability(new Die(1, 6), 0), new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_5MM, 8), 2,
						Reliability.STANDARD, 50, 75.0, 0.5, lightPistolAttachmentPoints));
		startingInventory.add(new MissileWeapon("Dai Lung Cybermag 15",
				"Cheap Hong Kong knockoff, often used by boosters and other street trash.",
				CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, -1, Concealability.POCKET, Availability.COMMON,
				new Probability(new Die(1, 6), 1), new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_6MM, 10), 2,
				Reliability.UNRELIABLE, 50, 50.0, 0.5, lightPistolAttachmentPoints));
		startingInventory.add(new MissileWeapon("Federated Arms X-22",
				"The ubiquitous \"Polymer-one-shot\" cheap plastic pistol. Available in designer colors.",
				CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, 0, Concealability.POCKET, Availability.EXCELLENT,
				new Probability(new Die(1, 6), 1), new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_6MM, 10), 2,
				Reliability.STANDARD, 50, 150.0, 0.5, lightPistolAttachmentPoints));

		// Medium Pistols
	}

	public CyberpunkWeapon buyWeapon(CyberpunkWeapon weapon) {
		CyberpunkWeapon clonedWeapon = (CyberpunkWeapon) SerializationUtils.clone(weapon);

		return clonedWeapon;
	}

	public CyberpunkArmor buyArmor(CyberpunkArmor armor) {
		CyberpunkArmor clonedArmor = (CyberpunkArmor) SerializationUtils.clone(armor);

		return clonedArmor;
	}

	public List<Ammunition> buyBoxOfAmmunition(Ammunition ammunition) {
		List<Ammunition> clonedAmmunition = new ArrayList<Ammunition>();
		for (int i = 0; i < ammunition.getAmmunitionPerBox(); i++) {
			clonedAmmunition.add((Ammunition) SerializationUtils.clone(ammunition));
		}

		return clonedAmmunition;
	}

	public Set<CyberpunkWeapon> getStoredWeapons() {
		return startingInventory.createWeaponSet();
	}

	public Set<CyberpunkArmor> getStoredArmors() {
		return startingInventory.createArmorSet();
	}

	public Set<Ammunition> getStoredAmmunition() {
		return startingInventory.createAmmunitionSet();
	}

	public Set<Item> getStoredItems() {
		return startingInventory.createItemSet();
	}

}
