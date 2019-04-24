package rpg.cyberpunk._2020.commerce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.SerializationUtils;

import rpg.cyberpunk._2020.combat.Arrow;
import rpg.cyberpunk._2020.combat.Arrow.Tip;
import rpg.cyberpunk._2020.combat.Bow;
import rpg.cyberpunk._2020.combat.Cartridge;
import rpg.cyberpunk._2020.combat.Cartridge.Bullet;
import rpg.cyberpunk._2020.combat.Cartridge.CaseMaterial;
import rpg.cyberpunk._2020.combat.CyberpunkArmor;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Availability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Concealability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Reliability;
import rpg.cyberpunk._2020.combat.CyberpunkWeaponModifier;
import rpg.cyberpunk._2020.combat.ExoticFirearm;
import rpg.cyberpunk._2020.combat.Firearm;
import rpg.cyberpunk._2020.combat.HomogeneousMagazine;
import rpg.cyberpunk._2020.combat.LaunchedGrenade;
import rpg.cyberpunk._2020.combat.MeleeWeapon;
import rpg.cyberpunk._2020.combat.Payload;
import rpg.cyberpunk._2020.combat.ShotShell;
import rpg.cyberpunk._2020.combat.ShotShell.Load;
import rpg.cyberpunk._2020.combat.ThrownWeapon;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.general.combat.Ammunition;
import rpg.general.combat.BodyLocation;
import rpg.general.commerce.Item;
import rpg.general.commerce.Trader;
import rpg.util.Die;
import rpg.util.NullProbability;
import rpg.util.Probability;

/**
 * A class that handles the Items a Player can buy.
 * <p>
 * Unless otherwise mentioned, passing a <code>null</code> parameter into any
 * method of a <code>CyberpunkVendor</code> will cause a
 * <code>NullPointerException</code> to be thrown.
 * 
 * @author Coul Greer
 *
 */
public class CyberpunkVendor {
	private Trader trader;
	private Inventory startingInventory;
	private List<Box<Ammunition>> boxes;

	/**
	 * Constructs a CyberpunkVendor that creates a base stock of inventory to buy
	 * from. Uses a BottomlessInventory as its defalut way to store items and a List
	 * of Box<Ammunition>.
	 * 
	 * @param trader the handler of the money exchanged
	 */
	public CyberpunkVendor(Trader trader) {
		this.trader = trader;
		startingInventory = new BottomlessInventory();
		boxes = new ArrayList<Box<Ammunition>>();
		addWeaponsToVendor();
		addArmorToVendor();
		addAmmunitionToVendor();
	}

	private void addWeaponsToVendor() {
		// Light Pistols
		Set<String> lightPistolAttachmentPoints = new HashSet<String>();
		lightPistolAttachmentPoints.add(CyberpunkWeaponModifier.ATTACHMENT_POINT_BARREL);
		lightPistolAttachmentPoints.add(CyberpunkWeaponModifier.ATTACHMENT_POINT_OPTIC);
		lightPistolAttachmentPoints.add(CyberpunkWeaponModifier.ATTACHMENT_POINT_GRIP);

		startingInventory
				.add(new Firearm("BudgetArms C-13", "A light duty autopistol used as a holdout and \"lady's gun\".",
						CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, -1, Concealability.POCKET, Availability.EXCELLENT,
						new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_5MM, 8), 2, Reliability.STANDARD, 50, 75.0,
						0.5, lightPistolAttachmentPoints));
		startingInventory.add(new Firearm("Dai Lung Cybermag 15",
				"Cheap Hong Kong knockoff, often used by boosters and other street trash.",
				CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, -1, Concealability.POCKET, Availability.COMMON,
				new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_6MM, 10), 2, Reliability.UNRELIABLE, 50, 50.0, 0.5,
				lightPistolAttachmentPoints));
		startingInventory.add(new Firearm("Federated Arms X-22",
				"The ubiquitous \"Polymer-one-shot\" cheap plastic pistol. Available in designer colors.",
				CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, 0, Concealability.JACKET, Availability.EXCELLENT,
				new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_6MM, 10), 2, Reliability.STANDARD, 50, 150.0, 0.5,
				lightPistolAttachmentPoints));

		// Medium Pistols
		startingInventory.add(new Firearm("Militech Arms Avenger",
				"A well-made autopistol with good range and accuracy. A professional's gun.",
				CyberpunkWeapon.WEAPON_TYPE_MEDIUM_PISTOL, 0, Concealability.POCKET, Availability.EXCELLENT,
				new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_9MM, 10), 2, Reliability.VERY_RELIABLE, 50, 250.0,
				1.0, lightPistolAttachmentPoints));
		startingInventory.add(new Firearm("Dai Lung Streetmaster", "Another Dai Lung cheapie, built for the Street.",
				CyberpunkWeapon.WEAPON_TYPE_MEDIUM_PISTOL, 0, Concealability.JACKET, Availability.EXCELLENT,
				new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_10MM, 12), 2, Reliability.UNRELIABLE, 50, 250.0, 1.0,
				lightPistolAttachmentPoints));
		startingInventory.add(new Firearm("Federated Arms X-9mm",
				"A sturdy Solo's gun, used as a standard military sidearm in the U.S. and E.C.C.",
				CyberpunkWeapon.WEAPON_TYPE_MEDIUM_PISTOL, 0, Concealability.JACKET, Availability.EXCELLENT,
				new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_9MM, 12), 2, Reliability.STANDARD, 50, 300.0, 1.0,
				lightPistolAttachmentPoints));

		// Heavy Pistols
		startingInventory.add(new Firearm("BudgetArms Auto 3",
				"It's cheap. It's powerful. It blows up sometimes. What else do you want?",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_PISTOL, -1, Concealability.JACKET, Availability.EXCELLENT,
				new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_11MM, 8), 2, Reliability.UNRELIABLE, 50, 350.0, 1.0,
				lightPistolAttachmentPoints));
		startingInventory.add(new Firearm("Sternmeyer Type 35",
				"Rugged, reliable, with excellent stopping power. Another fine E.C.C. product from the United Germanies.",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_PISTOL, 0, Concealability.JACKET, Availability.COMMON,
				new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_11MM, 8), 2, Reliability.VERY_RELIABLE, 50, 400.0,
				1.0, lightPistolAttachmentPoints));

		// Very Heavy Pistols
		startingInventory.add(new Firearm("Armalite 44",
				"Designed as an alternate to the 1998 U.S. Army sidearm trials. A solid contender.",
				CyberpunkWeapon.WEAPON_TYPE_VERY_HEAVY_PISTOL, 0, Concealability.JACKET, Availability.COMMON,
				new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_12MM, 8), 1, Reliability.STANDARD, 50, 450.0, 3.0,
				lightPistolAttachmentPoints));
		startingInventory.add(new Firearm("Colt AMT Model 2000",
				"Now the standard officer's sidearm for the U.S. Army, the M-2000 served well in the Central American Wars.",
				CyberpunkWeapon.WEAPON_TYPE_VERY_HEAVY_PISTOL, 0, Concealability.JACKET, Availability.COMMON,
				new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_12MM, 8), 1, Reliability.VERY_RELIABLE, 50, 500.0,
				3.0, lightPistolAttachmentPoints));

		// Light Submachineguns
		Set<String> lightSMGAttachmentPoints = new HashSet<String>();
		lightSMGAttachmentPoints.add(CyberpunkWeaponModifier.ATTACHMENT_POINT_BARREL);
		lightSMGAttachmentPoints.add(CyberpunkWeaponModifier.ATTACHMENT_POINT_OPTIC);
		lightSMGAttachmentPoints.add(CyberpunkWeaponModifier.ATTACHMENT_POINT_GRIP);

		startingInventory.add(new Firearm("Uzi Miniauto 9",
				"Uzi's entry into the 21st century, all plastic, with a rotarty electric clip and adjustable trigger. "
						+ "The choice for many security Solos.",
				CyberpunkWeapon.WEAPON_TYPE_LIGHT_SUBMACHINEGUN, 1, Concealability.JACKET, Availability.EXCELLENT,
				new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_9MM, 30), 35, Reliability.VERY_RELIABLE, 150, 475.0,
				3.0, lightSMGAttachmentPoints));
		startingInventory.add(new Firearm("H&K MP-2013",
				"Heckler & Koch's updating of the MP-5K classic, with compound plastics and built in silencing.",
				CyberpunkWeapon.WEAPON_TYPE_LIGHT_SUBMACHINEGUN, 1, Concealability.JACKET, Availability.EXCELLENT,
				new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_10MM, 35), 32, Reliability.STANDARD, 150, 475.0, 3.0,
				lightSMGAttachmentPoints));
		startingInventory.add(new Firearm("Fed. Arms Tech Assault II",
				"An updated version of the venerable Tech Assault I, features larger clip, better autofire, no melting. Honest.",
				CyberpunkWeapon.WEAPON_TYPE_LIGHT_SUBMACHINEGUN, 1, Concealability.JACKET, Availability.COMMON,
				new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_6MM, 50), 25, Reliability.STANDARD, 150, 400.0, 3.0,
				lightSMGAttachmentPoints));

		// Medium Submachineguns
		startingInventory.add(new Firearm("Arasaka Minami 10",
				"The standard Arasaka Security weapon, found worldwide. A good, all round weapon.",
				CyberpunkWeapon.WEAPON_TYPE_MEDIUM_SUBMACHINEGUN, 0, Concealability.JACKET, Availability.EXCELLENT,
				new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_10MM, 40), 20, Reliability.VERY_RELIABLE, 200, 500.0,
				3.0, lightSMGAttachmentPoints));
		startingInventory.add(new Firearm("H&K MPK-9",
				"A light composite submachinegun with integral sights. Used by many Euro Solos.",
				CyberpunkWeapon.WEAPON_TYPE_MEDIUM_SUBMACHINEGUN, 1, Concealability.JACKET, Availability.COMMON,
				new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_9MM, 35), 25, Reliability.STANDARD, 200, 520.0, 3.0,
				lightSMGAttachmentPoints));

		// Heavy Submachineguns
		startingInventory.add(new Firearm("Sternmeyer SMG 21",
				"Sternmeyer's best entry in the anti-terrorist category, with wide use on C-SWAT teams and PsychoSquads.",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_SUBMACHINEGUN, -1, Concealability.LONG_COAT, Availability.EXCELLENT,
				new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_11MM, 30), 15, Reliability.VERY_RELIABLE, 200, 500.0,
				3.0, lightSMGAttachmentPoints));
		startingInventory.add(new Firearm("H&K MPK-11",
				"Possibly the most used Solo's gun in existence, the MPK-11 can be modified into four different designs, "
						+ "including a bullpup configuration, standard SMG, an assault carbine, and a grenade launcher mount.",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_SUBMACHINEGUN, 0, Concealability.LONG_COAT, Availability.COMMON,
				new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_12MM, 30), 20, Reliability.STANDARD, 200, 700.0, 3.0,
				lightSMGAttachmentPoints));
		startingInventory.add(
				new Firearm("Ingram MAC 14", "Updated MAC-10, with composite body and cylindrical feeding magazine.",
						CyberpunkWeapon.WEAPON_TYPE_HEAVY_SUBMACHINEGUN, -2, Concealability.LONG_COAT,
						Availability.EXCELLENT, new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_12MM, 20), 10,
						Reliability.STANDARD, 200, 650.0, 3.0, lightSMGAttachmentPoints));

		// Assault Rifles
		Set<String> assaultRifleAttachmentPoints = new HashSet<String>();
		assaultRifleAttachmentPoints.add(CyberpunkWeaponModifier.ATTACHMENT_POINT_BARREL);
		assaultRifleAttachmentPoints.add(CyberpunkWeaponModifier.ATTACHMENT_POINT_OPTIC);
		assaultRifleAttachmentPoints.add(CyberpunkWeaponModifier.ATTACHMENT_POINT_GRIP);

		startingInventory
				.add(new Firearm("Militech Ronin Light Assault", "A light, all purpose update, similar to the M-16B.",
						CyberpunkWeapon.WEAPON_TYPE_RIFLE, 1, Concealability.CANNOT_HIDE, Availability.COMMON,
						new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_556, 35), 30, Reliability.STANDARD, 400,
						450.0, 3.0, assaultRifleAttachmentPoints));
		startingInventory.add(new Firearm("AKR-20 Medium Assault",
				"A plastic and carbon fiber update of the AKM, distributed throughout the remains of the Soviet Bloc.",
				CyberpunkWeapon.WEAPON_TYPE_RIFLE, 0, Concealability.CANNOT_HIDE, Availability.COMMON,
				new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_556, 30), 30, Reliability.STANDARD, 400, 500.0, 3.0,
				assaultRifleAttachmentPoints));
		startingInventory.add(new Firearm("FN-RAL Heavy Assault Rifle",
				"The standard NATO assault weapon for battlefield work. Bullpup design, collapsing stock.",
				CyberpunkWeapon.WEAPON_TYPE_RIFLE, -1, Concealability.CANNOT_HIDE, Availability.COMMON,
				new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_762, 30), 30, Reliability.VERY_RELIABLE, 400, 600.0,
				3.0, assaultRifleAttachmentPoints));
		startingInventory.add(new Firearm("Kalishnikov A-80 Hvy. Rifle",
				"Another Soviet retread, with improved sighting and lightened with composites.",
				CyberpunkWeapon.WEAPON_TYPE_RIFLE, -1, Concealability.CANNOT_HIDE, Availability.EXCELLENT,
				new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_762, 35), 25, Reliability.STANDARD, 400, 550.0, 3.0,
				assaultRifleAttachmentPoints));

		// Shotguns
		Set<String> shotgunAttachmentPoints = new HashSet<String>();
		shotgunAttachmentPoints.add(CyberpunkWeaponModifier.ATTACHMENT_POINT_BARREL);
		shotgunAttachmentPoints.add(CyberpunkWeaponModifier.ATTACHMENT_POINT_OPTIC);
		shotgunAttachmentPoints.add(CyberpunkWeaponModifier.ATTACHMENT_POINT_GRIP);

		startingInventory.add(new Firearm("Araska Rapid Assault 12",
				"A high powered auto-shotgun with lethal firepower. Used by Arasaka worldwide. Another good reason to "
						+ "avoid the Boys in Black.",
				CyberpunkWeapon.WEAPON_TYPE_SHOTGUN, -1, Concealability.CANNOT_HIDE, Availability.COMMON,
				new HomogeneousMagazine(ShotShell.AMMUNITION_TYPE_12_GAUGE, 20), 10, Reliability.STANDARD, 50, 900.0,
				3.0, shotgunAttachmentPoints));
		startingInventory.add(
				new Firearm("Sternmeyer Stakeout 10", "Light duty stakeout shotgun, used by city police departments.",
						CyberpunkWeapon.WEAPON_TYPE_SHOTGUN, -2, Concealability.CANNOT_HIDE, Availability.RARE,
						new HomogeneousMagazine(ShotShell.AMMUNITION_TYPE_12_GAUGE, 10), 2, Reliability.STANDARD, 50,
						450.0, 0.5, shotgunAttachmentPoints));

		// TODO Actually make attachment points for all weapon types.
		// Heavy Weapons
		startingInventory.add(new Firearm("Barrett-Arasaka Light 20mm",
				"The cyberpsycho hunter's favorite. Almost 2 meters long, this \"cannon\" fires a depleted uranium shell "
						+ "at supersonic speeds. Heavy AP sub-caliber penetrator damages armor 2pts/hit.",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, 0, Concealability.CANNOT_HIDE, Availability.RARE,
				new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_20MM, 10), 1, Reliability.VERY_RELIABLE, 450, 2000.0,
				25.0, shotgunAttachmentPoints));
		startingInventory.add(new Firearm("Scorpion 16 Missile Launcher",
				"The third generation of the Stinger missile launcher, this shoulder arm fires one missile.",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, -1, Concealability.CANNOT_HIDE, Availability.RARE,
				new HomogeneousMagazine(Cartridge.NO_AMMUNITION_TYPE, 1), 1, Reliability.VERY_RELIABLE, 1000, 3000.0,
				10.0, shotgunAttachmentPoints));
		startingInventory.add(new Firearm("Militech Arms RPG-A",
				"Shoulder-mounted, rocket-powered grenade launcher. Heavily used in the Central American conflicts under "
						+ "the name RPG-A.",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, -2, Concealability.CANNOT_HIDE, Availability.RARE,
				new HomogeneousMagazine(Cartridge.NO_AMMUNITION_TYPE, 1), 1, Reliability.VERY_RELIABLE, 750, 1500.0,
				10.0, shotgunAttachmentPoints));
		startingInventory.add(
				new ThrownWeapon("Fragmentation Grenade", ThrownWeapon.WEAPON_TYPE_GRENADE, 0, Concealability.POCKET,
						Availability.POOR, Payload.HIGH_EXPLOSIVES, Reliability.VERY_RELIABLE, 30.0, 0.5));
		startingInventory
				.add(new ThrownWeapon("Incendiary Grenade", ThrownWeapon.WEAPON_TYPE_GRENADE, 0, Concealability.POCKET,
						Availability.POOR, Payload.WHITE_PHOSPHOROUS, Reliability.VERY_RELIABLE, 30.0, 0.5));
		startingInventory.add(new ThrownWeapon("Flashbang Grenade", ThrownWeapon.WEAPON_TYPE_GRENADE, 0,
				Concealability.POCKET, Availability.POOR, Payload.FLASHBANG, Reliability.VERY_RELIABLE, 30.0, 0.5));
		startingInventory.add(new ThrownWeapon("Sonic Grenade",
				"An experimental type, popular in the EuroThater. Essentially, a mini-voder box, with a 1-second play "
						+ "time and a one-use power source that fuses the unit into a lump. The burst of high decibels mixed with"
						+ " super- or sub-sonics causes all within a 6m radius to make a Stun Save at +1; if the save is made, "
						+ "make a Difficult BOD check or suffer deafness and disorientation (-2 all skill rolls) for 40 seconds. "
						+ "Noise-resistant headphones and various editing cyberaudio options allow you to resist the effects.",
				CyberpunkWeapon.WEAPON_TYPE_GRENADE, 0, Concealability.POCKET, Availability.POOR,
				NullProbability.getInstance(), Reliability.VERY_RELIABLE, 30.0, 0.5));
		startingInventory.add(new ThrownWeapon("Nausea Grenade", ThrownWeapon.WEAPON_TYPE_GRENADE, 0,
				Concealability.POCKET, Availability.POOR, Payload.NAUSEA_GAS, Reliability.VERY_RELIABLE, 30.0, 0.5));
		startingInventory.add(new ThrownWeapon("Teargas Grenade", ThrownWeapon.WEAPON_TYPE_GRENADE, 0,
				Concealability.POCKET, Availability.POOR, Payload.TEARGAS, Reliability.VERY_RELIABLE, 30.0, 0.5));
		startingInventory.add(new ThrownWeapon("Sleep Grenade", ThrownWeapon.WEAPON_TYPE_GRENADE, 0,
				Concealability.POCKET, Availability.POOR, Payload.SLEEP_DRUGS, Reliability.VERY_RELIABLE, 30.0, 0.5));
		startingInventory
				.add(new ThrownWeapon("Biotoxin I Grenade", ThrownWeapon.WEAPON_TYPE_GRENADE, 0, Concealability.POCKET,
						Availability.POOR, Payload.BIOTOXIN_I_GAS, Reliability.VERY_RELIABLE, 30.0, 0.5));
		startingInventory
				.add(new ThrownWeapon("Biotoxin II Grenade", ThrownWeapon.WEAPON_TYPE_GRENADE, 0, Concealability.POCKET,
						Availability.POOR, Payload.BIOTOXIN_II_GAS, Reliability.VERY_RELIABLE, 30.0, 0.5));
		startingInventory.add(new ThrownWeapon("Nerve Gas Grenade", ThrownWeapon.WEAPON_TYPE_GRENADE, 0,
				Concealability.POCKET, Availability.POOR, Payload.NERVE_GAS, Reliability.VERY_RELIABLE, 30.0, 0.5));
		startingInventory.add(new Firearm("Grenade Launchers",
				"These come from manufacturers worldwide, and may be attached to any assault rifle (under the barrel). "
						+ "Some can be given a simple shoulder stock for separate use.",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, 0, Concealability.LONG_COAT, Availability.RARE,
				new HomogeneousMagazine(LaunchedGrenade.AMMUNITION_TYPE_40MM, 1), 1, Reliability.STANDARD, 225, 150.0,
				1.0, shotgunAttachmentPoints));
		startingInventory.add(new Firearm("C-6 \"Flatfire\" Plastic Explosive",
				"Grey block of plastique, can be detonated by timer, tripwire or signal.",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, 0, Concealability.POCKET, Availability.POOR,
				new HomogeneousMagazine(Ammunition.NO_AMMUNITION_TYPE, 1), 1, Reliability.VERY_RELIABLE, 0, 100.0, 1.0,
				shotgunAttachmentPoints));
		// TODO Add mines to the vendor inventory.
		// TODO Add flamethrower reloads and let ammunition decide the damage allowing
		// the removal of probability for all weapons that use ammunition.
		startingInventory.add(new Firearm("Kenshiri Adachi F-253 Flamethrower",
				"Liquified napalm sprayer. Back mounted and bulky. Does extra damage following initial hit (see FNFF)",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, -2, Concealability.CANNOT_HIDE, Availability.RARE,
				new HomogeneousMagazine(Cartridge.NO_AMMUNITION_TYPE, 10), 1, Reliability.STANDARD, 50, 1500.0, 32.0,
				shotgunAttachmentPoints));

		// Exotic Weapons
		startingInventory.add(new ExoticFirearm("Techtronica 15 Microwaver",
				"Flashlight sized microwave projector. See FNFF for details.", CyberpunkWeapon.WEAPON_TYPE_EXOTIC,
				CyberpunkSkill.HANDGUN, 0, Concealability.JACKET, Availability.POOR, new Probability(new Die(1, 6), 0),
				new HomogeneousMagazine(CyberpunkWeapon.AMMUNITION_TYPE_RECHARGABLE_BATTERY, 10), 2,
				Reliability.VERY_RELIABLE, 20, 400.0, 2.0, shotgunAttachmentPoints));
		startingInventory.add(new ExoticFirearm("Militech Electronics LaserCannon",
				"Milspec laser cannon, rarely seen. See FNFF for details.", CyberpunkWeapon.WEAPON_TYPE_EXOTIC,
				CyberpunkSkill.RIFLE, 0, Concealability.CANNOT_HIDE, Availability.RARE,
				new Probability(new Die(5, 6), 0),
				new HomogeneousMagazine(CyberpunkWeapon.AMMUNITION_TYPE_RECHARGABLE_BATTERY, 20), 2,
				Reliability.UNRELIABLE, 200, 8000.0, 3.0, shotgunAttachmentPoints));
		startingInventory.add(new Firearm("Avante P-1135 Needlegun",
				"Lightweight, plastic, compressed air powered. Can be doped with drugs, poison. See FNFF for details.",
				CyberpunkWeapon.WEAPON_TYPE_EXOTIC, CyberpunkSkill.HANDGUN, 0, Concealability.POCKET, Availability.POOR,
				new HomogeneousMagazine(CyberpunkWeapon.AMMUNITION_TYPE_DRUGS, 15), 2, Reliability.STANDARD, 40, 200.0,
				1.0, shotgunAttachmentPoints));
		startingInventory.add(new Firearm("Enertex AKM Power Squirt",
				"A squirtgun. Yes, a powered squirtgun. See FNFF before you laugh.", CyberpunkWeapon.WEAPON_TYPE_EXOTIC,
				CyberpunkSkill.HANDGUN, -2, Concealability.JACKET, Availability.COMMON,
				new HomogeneousMagazine(CyberpunkWeapon.AMMUNITION_TYPE_DRUGS, 50), 1, Reliability.VERY_RELIABLE, 10,
				15.0, 1.0, shotgunAttachmentPoints));
		startingInventory.add(new Firearm("Nelspot \"Wombat\" Airpistol",
				"Paintball gun from hell. Can fire acid, paint, drugs, poison. See FNFF.",
				CyberpunkWeapon.WEAPON_TYPE_EXOTIC, CyberpunkSkill.HANDGUN, -1, Concealability.JACKET,
				Availability.COMMON, new HomogeneousMagazine(CyberpunkWeapon.AMMUNITION_TYPE_DRUGS, 20), 2,
				Reliability.UNRELIABLE, 40, 200.0, 1.0, shotgunAttachmentPoints));
		startingInventory.add(new ExoticFirearm("Militech Electronics Taser",
				"Zap. About the size of a small hand flashlight. See FNFF for details.",
				CyberpunkWeapon.WEAPON_TYPE_EXOTIC, CyberpunkSkill.HANDGUN, -1, Concealability.JACKET,
				Availability.COMMON, NullProbability.getInstance(),
				new HomogeneousMagazine(CyberpunkWeapon.AMMUNITION_TYPE_STUN, 10), 1, Reliability.STANDARD, 10, 60.0,
				0.5, shotgunAttachmentPoints));
		startingInventory.add(
				new Bow("EagleTech \"Tomcat\" Compound Bow", "Gyrobalanced, stabilized compound bow. Silent & deadly",
						0, Concealability.CANNOT_HIDE, Availability.COMMON, new Probability(new Die(4, 6), 0),
						new HomogeneousMagazine(Arrow.AMMUNITION_TYPE_ARROW, 12), 1, Reliability.VERY_RELIABLE, 150,
						150.0, 3.0, shotgunAttachmentPoints));
		startingInventory.add(new Bow("EagleTech \"Stryker\" Crossbow",
				"Plastic and bimetal crossbow. Silent, deadly, and you usually get your ammo back.", -1,
				Concealability.CANNOT_HIDE, Availability.COMMON, new Probability(new Die(3, 6), 3),
				new HomogeneousMagazine(Arrow.AMMUNITION_TYPE_ARROW, 12), 1, Reliability.VERY_RELIABLE, 50, 220.0, 3.0,
				shotgunAttachmentPoints));

		// Melee Weapons
		startingInventory.add(new MeleeWeapon("Kendachi Monoknife",
				"Mono-sectional crystal blade. Incredibly sharp. "
						+ "In the Japanese \"tanto\" style. Also available in a nadinata form for 100.00 extra.",
				CyberpunkWeapon.WEAPON_TYPE_MELEE_WEAPON, CyberpunkSkill.MELEE, 1, Concealability.POCKET,
				Availability.POOR, new Probability(new Die(2, 6), 0), true, Reliability.VERY_RELIABLE, 1, 200.0, 0.5,
				shotgunAttachmentPoints));
		startingInventory.add(new MeleeWeapon("Kendachi MonoKatana",
				"Sword length version of monoblade. "
						+ "Resembles a hightech katana with a milky, nearly transparent blade.",
				CyberpunkWeapon.WEAPON_TYPE_MELEE_WEAPON, CyberpunkSkill.MELEE, 1, Concealability.CANNOT_HIDE,
				Availability.RARE, new Probability(new Die(4, 6), 0), true, Reliability.VERY_RELIABLE, 1, 600.0, 1.0,
				shotgunAttachmentPoints));
		startingInventory.add(new MeleeWeapon("SPM-1 Battleglove",
				"This is a large gauntlet covering the hand and forearm."
						+ " It does 3D6 in crush damage, 2D6 punch damage,"
						+ " and has three spaces which can be used to store any standard cyberarm option.",
				CyberpunkWeapon.WEAPON_TYPE_MELEE_WEAPON, CyberpunkSkill.MELEE, -2, Concealability.CANNOT_HIDE,
				Availability.POOR, new Probability(new Die(2, 6), 0), false, Reliability.VERY_RELIABLE, 1, 900.0, 1.0,
				shotgunAttachmentPoints));

		startingInventory
				.add(new MeleeWeapon("Club", "", CyberpunkWeapon.WEAPON_TYPE_MELEE_WEAPON, CyberpunkSkill.MELEE, 0,
						Concealability.LONG_COAT, Availability.COMMON, new Probability(new Die(1, 6), 0), false,
						Reliability.VERY_RELIABLE, 1, 0.0, 1.0, shotgunAttachmentPoints));
		startingInventory.add(new MeleeWeapon("Knife", "", CyberpunkWeapon.WEAPON_TYPE_MELEE_WEAPON,
				CyberpunkSkill.MELEE, 0, Concealability.POCKET, Availability.COMMON, new Probability(new Die(1, 6), 0),
				true, Reliability.VERY_RELIABLE, 1, 20.0, 0.5, shotgunAttachmentPoints));
		startingInventory
				.add(new MeleeWeapon("Sword", "", CyberpunkWeapon.WEAPON_TYPE_MELEE_WEAPON, CyberpunkSkill.MELEE, 0,
						Concealability.CANNOT_HIDE, Availability.COMMON, new Probability(new Die(2, 6), 2), true,
						Reliability.VERY_RELIABLE, 1, 200.0, 1.0, shotgunAttachmentPoints));
		startingInventory.add(new MeleeWeapon("Axe", "", CyberpunkWeapon.WEAPON_TYPE_MELEE_WEAPON, CyberpunkSkill.MELEE,
				-1, Concealability.CANNOT_HIDE, Availability.COMMON, new Probability(new Die(2, 6), 3), true,
				Reliability.VERY_RELIABLE, 1, 20.0, 0.0, shotgunAttachmentPoints));
		startingInventory
				.add(new MeleeWeapon("Nunchaku", "", CyberpunkWeapon.WEAPON_TYPE_MELEE_WEAPON, CyberpunkSkill.MELEE, 0,
						Concealability.LONG_COAT, Availability.COMMON, new Probability(new Die(3, 6), 0), false,
						Reliability.VERY_RELIABLE, 1, 15.0, 0.0, shotgunAttachmentPoints));
		startingInventory
				.add(new MeleeWeapon("Naginata", "", CyberpunkWeapon.WEAPON_TYPE_MELEE_WEAPON, CyberpunkSkill.MELEE, 0,
						Concealability.CANNOT_HIDE, Availability.POOR, new Probability(new Die(3, 6), 0), false,
						Reliability.VERY_RELIABLE, 2, 100.0, 0.5, shotgunAttachmentPoints));
		// TODO (Coul Greer): Add a Thrown Weapon type.
		startingInventory.add(new ThrownWeapon("Shiriken", "", CyberpunkWeapon.WEAPON_TYPE_MELEE_WEAPON, 0,
				Concealability.POCKET, Availability.COMMON, new Probability(new Die(1, 6, 3), 0),
				Reliability.VERY_RELIABLE, 3.0, 0.5));
		startingInventory
				.add(new MeleeWeapon("Switchblade", "", CyberpunkWeapon.WEAPON_TYPE_MELEE_WEAPON, CyberpunkSkill.MELEE,
						0, Concealability.POCKET, Availability.COMMON, new Probability(new Die(1, 6, 2), 0), true,
						Reliability.VERY_RELIABLE, 1, 15.0, 0.5, shotgunAttachmentPoints));
		startingInventory.add(new MeleeWeapon("Brass knuckles", "", CyberpunkWeapon.WEAPON_TYPE_MELEE_WEAPON,
				CyberpunkSkill.MELEE, 0, Concealability.POCKET, Availability.COMMON, new Probability(new Die(1, 6), 2),
				false, Reliability.VERY_RELIABLE, 1, 10.0, 0.5, shotgunAttachmentPoints));
		startingInventory
				.add(new MeleeWeapon("Sledgehammer", "", CyberpunkWeapon.WEAPON_TYPE_MELEE_WEAPON, CyberpunkSkill.MELEE,
						-1, Concealability.CANNOT_HIDE, Availability.COMMON, new Probability(new Die(4, 6), 0), false,
						Reliability.VERY_RELIABLE, 1, 20.0, 5.0, shotgunAttachmentPoints));
		startingInventory
				.add(new MeleeWeapon("Chainsaw", "", CyberpunkWeapon.WEAPON_TYPE_MELEE_WEAPON, CyberpunkSkill.MELEE, -3,
						Concealability.CANNOT_HIDE, Availability.COMMON, new Probability(new Die(4, 6), 0), true,
						Reliability.VERY_RELIABLE, 2, 80.0, 5.0, shotgunAttachmentPoints));
	}

	private void addArmorToVendor() {
		BodyLocation headgear[] = { BodyLocation.HEAD };
		BodyLocation vest[] = { BodyLocation.TORSO };
		BodyLocation shirt[] = { BodyLocation.TORSO, BodyLocation.LEFT_ARM, BodyLocation.RIGHT_ARM };
		BodyLocation pants[] = { BodyLocation.LEFT_LEG, BodyLocation.RIGHT_LEG };
		BodyLocation bodySuit[] = { BodyLocation.HEAD, BodyLocation.TORSO, BodyLocation.LEFT_ARM,
				BodyLocation.RIGHT_ARM, BodyLocation.LEFT_LEG, BodyLocation.RIGHT_LEG };

		startingInventory.add(new CyberpunkArmor("Generic Chic Pants",
				"This is the standard Streetwear, made up of colorful modular components in many colors."
						+ " Belts, coats, sashes, boots predominate.",
				Arrays.stream(pants).iterator(), CyberpunkArmor.ARMOR_TYPE_SOFT, 0, 0, 20.0, 0.5));
		startingInventory.add(new CyberpunkArmor("Generic Chic Top",
				"This is the standard Streetwear, made up of colorful modular components in many colors."
						+ " Belts, coats, sashes, boots predominate.",
				Arrays.stream(shirt).iterator(), CyberpunkArmor.ARMOR_TYPE_SOFT, 0, 0, 15.0, 0.5));
		startingInventory.add(new CyberpunkArmor("Generic Chic Jacket",
				"This is the standard Streetwear, made up of colorful modular components in many colors."
						+ " Belts, coats, sashes, boots predominate.",
				Arrays.stream(shirt).iterator(), CyberpunkArmor.ARMOR_TYPE_SOFT, 0, 0, 35.0, 0.5));
		startingInventory.add(new CyberpunkArmor("Leisurewear Pants",
				"This is the equivalent of 21st century athletic wear."
						+ " Padded fleece, corporate and athletic logos.",
				Arrays.stream(pants).iterator(), CyberpunkArmor.ARMOR_TYPE_SOFT, 0, 0, 40.0, 0.5));
		startingInventory.add(new CyberpunkArmor("Leisurewear Top",
				"This is the equivalent of 21st century athletic wear."
						+ " Padded fleece, corporate and athletic logos.",
				Arrays.stream(shirt).iterator(), CyberpunkArmor.ARMOR_TYPE_SOFT, 0, 0, 30.0, 0.5));
		startingInventory.add(new CyberpunkArmor("Leisurewear Jacket",
				"This is the equivalent of 21st century athletic wear."
						+ " Padded fleece, corporate and athletic logos.",
				Arrays.stream(shirt).iterator(), CyberpunkArmor.ARMOR_TYPE_SOFT, 0, 0, 70.0, 0.5));
		startingInventory.add(new CyberpunkArmor("Businesswear Pants",
				"This is the equivalent of the standard business suit; understated colors, pinstripes, real leather shoes etc."
						+ " Wool and other natural fabrics are considered the proper outfitting for the up and coming Corp.",
				Arrays.stream(pants).iterator(), CyberpunkArmor.ARMOR_TYPE_SOFT, 0, 0, 60.0, 0.5));
		startingInventory.add(new CyberpunkArmor("Businesswear Top",
				"This is the equivalent of the standard business suit; understated colors, pinstripes, real leather shoes etc."
						+ " Wool and other natural fabrics are considered the proper outfitting for the up and coming Corp.",
				Arrays.stream(shirt).iterator(), CyberpunkArmor.ARMOR_TYPE_SOFT, 0, 0, 45.0, 0.5));
		startingInventory.add(new CyberpunkArmor("Businesswear Jacket",
				"This is the equivalent of the standard business suit; understated colors, pinstripes, real leather shoes etc."
						+ " Wool and other natural fabrics are considered the proper outfitting for the up and coming Corp.",
				Arrays.stream(shirt).iterator(), CyberpunkArmor.ARMOR_TYPE_SOFT, 0, 0, 105.0, 0.5));
		startingInventory.add(new CyberpunkArmor("High Fashion Pants",
				"Sophisticated and expensive dressing for the upper class."
						+ " Designer labels like Miyake, Si-fui Yan, and Anne Calvin.",
				Arrays.stream(pants).iterator(), CyberpunkArmor.ARMOR_TYPE_SOFT, 0, 0, 80.0, 0.5));
		startingInventory.add(new CyberpunkArmor("High Fashion Top",
				"Sophisticated and expensive dressing for the upper class."
						+ " Designer labels like Miyake, Si-fui Yan, and Anne Calvin.",
				Arrays.stream(shirt).iterator(), CyberpunkArmor.ARMOR_TYPE_SOFT, 0, 0, 60.0, 0.5));
		startingInventory.add(new CyberpunkArmor("High Fashion Jacket",
				"Sophisticated and expensive dressing for the upper class."
						+ " Designer labels like Miyake, Si-fui Yan, and Anne Calvin.",
				Arrays.stream(shirt).iterator(), CyberpunkArmor.ARMOR_TYPE_SOFT, 0, 0, 140.0, 0.5));
		startingInventory.add(new CyberpunkArmor("Urban Flash Pants",
				"Video jackets, colorshift fabrics, cammo, leathers, metal spikes, Logowear, jeans, leather skirts, boots."
						+ " The wildest and most utterly chilled in cyberfashion.",
				Arrays.stream(pants).iterator(), CyberpunkArmor.ARMOR_TYPE_SOFT, 0, 0, 40.0, 0.5));
		startingInventory.add(new CyberpunkArmor("Urban Flash Top",
				"Video jackets, colorshift fabrics, cammo, leathers, metal spikes, Logowear, jeans, leather skirts, boots."
						+ " The wildest and most utterly chilled in cyberfashion.",
				Arrays.stream(shirt).iterator(), CyberpunkArmor.ARMOR_TYPE_SOFT, 0, 0, 30.0, 0.5));
		startingInventory.add(new CyberpunkArmor("Urban Flash Jacket",
				"Video jackets, colorshift fabrics, cammo, leathers, metal spikes, Logowear, jeans, leather skirts, boots."
						+ " The wildest and most utterly chilled in cyberfashion.",
				Arrays.stream(shirt).iterator(), CyberpunkArmor.ARMOR_TYPE_SOFT, 0, 0, 70.0, 0.5));
		startingInventory.add(new CyberpunkArmor("Heavy leather jacket",
				"Good for road rash, stopping knives, etc. A good .38 slug will probably rip you to bits, however.",
				Arrays.stream(shirt).iterator(), CyberpunkArmor.ARMOR_TYPE_SOFT, 4, 0, 50.0, 1.0));
		startingInventory.add(new CyberpunkArmor("Heavy leather pants",
				"Good for road rash, stopping knives, etc. A good .38 slug will probably rip you to bits, however.",
				Arrays.stream(pants).iterator(), CyberpunkArmor.ARMOR_TYPE_SOFT, 4, 0, 50.0, 1.0));
		startingInventory.add(new CyberpunkArmor("Kevlar vest",
				"Can be worn unnoticably under most street clothes. Will stop most rounds up to a .45 ACP.",
				Arrays.stream(vest).iterator(), CyberpunkArmor.ARMOR_TYPE_SOFT, 10, 0, 90.0, 1.0));
		startingInventory.add(new CyberpunkArmor("Light Kevlar armor jacket",
				"Personal protection for the fashion conscious, these lightweight Kevlar jackets have nylon coverings that resemble normal jackets.",
				Arrays.stream(shirt).iterator(), CyberpunkArmor.ARMOR_TYPE_SOFT, 14, 0, 150.0, 3.0));
		startingInventory.add(new CyberpunkArmor("Medium Kevlar armor jacket",
				"Personal protection for the fashion conscious, these lightweight Kevlar jackets have nylon coverings that resemble normal jackets.",
				Arrays.stream(shirt).iterator(), CyberpunkArmor.ARMOR_TYPE_SOFT, 18, 1, 200.0, 3.0));
		startingInventory.add(new CyberpunkArmor("Heavy Kevlar armor jacket",
				"Personal protection for the fashion conscious, these lightweight Kevlar jackets have nylon coverings that resemble normal jackets.",
				Arrays.stream(shirt).iterator(), CyberpunkArmor.ARMOR_TYPE_SOFT, 20, 2, 250.0, 3.0));
		startingInventory.add(new CyberpunkArmor("Steel helmet",
				"Heavy duty protection for the head, standard for most military."
						+ " Some are made of steel, others of kevlar and high impact plastics."
						+ " Most (90%) have face shields with 1/2 the SP level as the rest of the helmet.",
				Arrays.stream(headgear).iterator(), CyberpunkArmor.ARMOR_TYPE_HARD, 14, 0, 20.0, 0.5));
		startingInventory.add(new CyberpunkArmor("Nylon helmet",
				"Heavy duty protection for the head, standard for most military."
						+ " Some are made of steel, others of kevlar and high impact plastics."
						+ " Most (90%) have face shields with 1/2 the SP level as the rest of the helmet.",
				Arrays.stream(headgear).iterator(), CyberpunkArmor.ARMOR_TYPE_HARD, 20, 0, 100.0, 0.5));
		startingInventory.add(new CyberpunkArmor("Flack vest",
				"Standard protection for combat soldiers, the flack vest is designed to stop small arms fire, grenade shrapnel, but only slow up assault rifle rounds.",
				Arrays.stream(vest).iterator(), CyberpunkArmor.ARMOR_TYPE_HARD, 20, 1, 200.0, 3.0));
		startingInventory.add(new CyberpunkArmor("Flack pants",
				"Standard protection for combat soldiers, the flack vest is designed to stop small arms fire, grenade shrapnel, but only slow up assault rifle rounds.",
				Arrays.stream(pants).iterator(), CyberpunkArmor.ARMOR_TYPE_HARD, 20, 1, 200.0, 3.0));
		startingInventory.add(new CyberpunkArmor("Doorgunner's vest",
				"Heavy duty protection for stationary positions, like machinegun nests, helicopter doors, etc.",
				Arrays.stream(vest).iterator(), CyberpunkArmor.ARMOR_TYPE_HARD, 25, 3, 250.0, 4.0));
		startingInventory.add(new CyberpunkArmor("MetalGear", "Laminated expoxide plate armor."
				+ " Bulky and designed in modular sections, with helmet, arm & leg coverings, torso and back clamshell",
				Arrays.stream(bodySuit).iterator(), CyberpunkArmor.ARMOR_TYPE_HARD, 25, 2, 600.0, 8.0));
	}

	private void addAmmunitionToVendor() {
		addRegularCartridges();
		boxes.add(new AmmunitionBox(new Cartridge(Cartridge.AMMUNITION_TYPE_20MM, Bullet.SOFT_POINT,
				CaseMaterial.CASELESS, 25.0, AmmunitionBox.WEIGHT), 1));
		boxes.add(new AmmunitionBox(new Cartridge(Cartridge.AMMUNITION_TYPE_20MM, Bullet.ARMOR_PIERCING,
				CaseMaterial.CASELESS, 25.0, AmmunitionBox.WEIGHT), 1));
		addShotshells();
		addArrows();
		addLaunchedGrenades();
	}

	private void addRegularCartridges() {
		createCartridgeVarients(Cartridge.AMMUNITION_TYPE_5MM, 15.0);
		createCartridgeVarients(Cartridge.AMMUNITION_TYPE_6MM, 15.0);
		createCartridgeVarients(Cartridge.AMMUNITION_TYPE_9MM, 30.0);
		createCartridgeVarients(Cartridge.AMMUNITION_TYPE_10MM, 30.0);
		createCartridgeVarients(Cartridge.AMMUNITION_TYPE_11MM, 36.0);
		createCartridgeVarients(Cartridge.AMMUNITION_TYPE_12MM, 40.0);
		createCartridgeVarients(Cartridge.AMMUNITION_TYPE_556, 40.0);
		createCartridgeVarients(Cartridge.AMMUNITION_TYPE_762, 40.0);
	}

	private void createCartridgeVarients(String caliber, double cost) {
		int ammoPerBox = 100;
		CaseMaterial[] materials = CaseMaterial.values();
		Bullet[] bullets = Bullet.values();

		for (int materialIndex = 0; materialIndex < materials.length; materialIndex++) {
			for (int bulletIndex = 0; bulletIndex < bullets.length; bulletIndex++) {
				boxes.add(new AmmunitionBox(new Cartridge(caliber, bullets[bulletIndex], materials[materialIndex],
						cost / ammoPerBox, AmmunitionBox.WEIGHT / ammoPerBox), ammoPerBox));
			}
		}
	}

	private void addShotshells() {
		createShotshellVarients(ShotShell.AMMUNITION_TYPE_10_GAUGE);
		createShotshellVarients(ShotShell.AMMUNITION_TYPE_12_GAUGE);
		createShotshellVarients(ShotShell.AMMUNITION_TYPE_20_GAUGE);
	}

	private void createShotshellVarients(String gauge) {
		int ammoPerBox = 12;
		Load[] loads = Load.values();

		for (int loadIndex = 0; loadIndex < loads.length; loadIndex++) {
			boxes.add(new AmmunitionBox(new ShotShell(gauge, loads[loadIndex], 15.0 / ammoPerBox), ammoPerBox));
		}
	}

	private void addArrows() {
		int ammoPerBox = 12;
		Tip[] tips = Tip.values();

		for (int tipIndex = 0; tipIndex < tips.length; tipIndex++) {
			boxes.add(new AmmunitionBox(new Arrow(tips[tipIndex]), ammoPerBox));
		}
	}

	private void addLaunchedGrenades() {
		int ammoPerBox = 1;
		Payload[] loads = Payload.values();

		for (int loadIndex = 0; loadIndex < loads.length; loadIndex++) {
			boxes.add(new AmmunitionBox(new LaunchedGrenade(LaunchedGrenade.AMMUNITION_TYPE_40MM, loads[loadIndex],
					50.0, AmmunitionBox.WEIGHT), ammoPerBox));
		}
	}

	/**
	 * Clones and returns a weapon.
	 * 
	 * @param weapon the thing to clone
	 * @return the cloned weapon
	 * @throws NullPointerException if weapon is null
	 */
	public CyberpunkWeapon sellWeapon(CyberpunkWeapon weapon) {
		if (weapon == null) {
			throw new NullPointerException();
		} else {
			CyberpunkWeapon clonedWeapon = (CyberpunkWeapon) SerializationUtils.clone(weapon);
			return clonedWeapon;
		}
	}

	/**
	 * Clones and returns an armor.
	 * 
	 * @param armor the thing to clone
	 * @return the cloned armor
	 * @throws NullPointerException if armor is null
	 */
	public CyberpunkArmor sellArmor(CyberpunkArmor armor) {
		if (armor == null) {
			throw new NullPointerException();
		}
		CyberpunkArmor clonedArmor = (CyberpunkArmor) SerializationUtils.clone(armor);

		return clonedArmor;
	}

	/**
	 * Clones and returns a list of Ammunition.
	 * 
	 * @param ammunition the thing to clone
	 * @return the cloned list of Ammunition
	 * @throws NullPointerException if ammunition is null
	 */
	public List<Ammunition> sellBoxOfAmmunition(Box<Ammunition> ammunition) {
		if (ammunition == null) {
			throw new NullPointerException();
		}
		Box<Ammunition> clonedBox = (Box<Ammunition>) SerializationUtils.clone(ammunition);

		return clonedBox.getItems();
	}

	/**
	 * Delegates to the Trader for an ask price of an item then returns the price.
	 * 
	 * @param item the item to derive the buying price from
	 * @return the derived ask price of the given Item
	 * @see Trader#getAskPrice(rpg.general.commerce.Tradeable)
	 */
	public double getAskPrice(Item item) {
		return trader.getAskPrice(item);
	}

	/**
	 * Delegates to the Trader for a bid price of an item then returns the price.
	 * 
	 * @param item the provider of the price to manipulate into getting the bid
	 *             price
	 * @return the derived bid price of the given Item
	 * @see Trader#getBidPrice(rpg.general.commerce.Tradeable)
	 */
	public double getBidPrice(Item item) {
		return trader.getBidPrice(item);
	}

	/**
	 * Delegates to the Inventory to make a set of weapons and return it.
	 * 
	 * @return a new set of weapons created by the Inventory
	 * @see Inventory#createWeaponCollection()
	 */
	public Set<CyberpunkWeapon> getStoredWeapons() {
		return new HashSet<CyberpunkWeapon>(startingInventory.createWeaponCollection());
	}

	/**
	 * Delegates to the Inventory to make a set of armor and return it.
	 * 
	 * @return a new set of armor created by the Inventory
	 * @see Inventory#createArmorCollection()
	 */
	public Set<CyberpunkArmor> getStoredArmors() {
		return new HashSet<CyberpunkArmor>(startingInventory.createArmorCollection());
	}

	/**
	 * Returns a Set created from the Boxes of Ammunition stored and return it.
	 * 
	 * @return a Set created from the Boxes of Ammunition stored
	 */
	public Set<Box<Ammunition>> getStoredAmmunition() {
		return new HashSet<Box<Ammunition>>(boxes);
	}

	/**
	 * Delegates to the Inventory to make a set of items and return it.
	 * 
	 * @return a Set of items created by the Inventory
	 * @see Inventory#createItemCollection()
	 */
	public Set<Item> getStoredItems() {
		return new HashSet<Item>(startingInventory.createItemCollection());
	}

}
