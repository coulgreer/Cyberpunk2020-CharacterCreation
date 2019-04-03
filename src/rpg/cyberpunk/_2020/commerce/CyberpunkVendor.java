package rpg.cyberpunk._2020.commerce;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.SerializationUtils;

import rpg.cyberpunk._2020.combat.Arrow;
import rpg.cyberpunk._2020.combat.Bow;
import rpg.cyberpunk._2020.combat.Cartridge;
import rpg.cyberpunk._2020.combat.CyberpunkArmor;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Availability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Concealability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Reliability;
import rpg.cyberpunk._2020.combat.CyberpunkWeaponModifier;
import rpg.cyberpunk._2020.combat.ExoticFirearm;
import rpg.cyberpunk._2020.combat.Firearm;
import rpg.cyberpunk._2020.combat.HomogeneousMagazine;
import rpg.cyberpunk._2020.combat.ShotShell;
import rpg.cyberpunk._2020.combat.ThrownWeapon;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.general.combat.Ammunition;
import rpg.general.combat.AmmunitionContainer;
import rpg.general.commerce.Item;
import rpg.general.commerce.Trader;
import rpg.util.Die;
import rpg.util.NullProbability;
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
		startingInventory.add(new ThrownWeapon("Fragmentation Grenade",
				"A grenade that launches shrapnel on detonation dealing damage in an area.",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, 0, Concealability.POCKET, Availability.POOR,
				new Probability(new Die(7, 6), 0), Reliability.VERY_RELIABLE, 30.0, 0.5));
		startingInventory.add(new ThrownWeapon("Incendiary Grenade",
				"This grenade does 4D6 damage for 3 turns to all targets within 5 meters, and sets fires very well. "
						+ "Damage is done by fragments of white phosphorous. Any soft armor attacked is reduced 2SP per round.",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, 0, Concealability.POCKET, Availability.POOR,
				new Probability(new Die(4, 6), 0), Reliability.VERY_RELIABLE, 30.0, 0.5));
		startingInventory.add(new ThrownWeapon("Stun Grenade",
				"A grenade that, when detonated, causes temporary vision and hearing loss as well as loss of balance.",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, 0, Concealability.POCKET, Availability.POOR,
				NullProbability.getInstance(), Reliability.VERY_RELIABLE, 30.0, 0.5));
		startingInventory.add(new ThrownWeapon("Dazzle Grenade",
				"A grenade that emits infrared and visual lights to cause temporary vision loss for organic and "
						+ "mechanical targets.",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, 0, Concealability.POCKET, Availability.POOR,
				NullProbability.getInstance(), Reliability.VERY_RELIABLE, 30.0, 0.5));
		startingInventory.add(new ThrownWeapon("Sonic Grenade",
				"An experimental type, popular in the EuroThater. Essentially, a mini-voder box, with a 1-second play "
						+ "time and a one-use power source that fuses the unit into a lump. The burst of high decibels mixed with"
						+ " super- or sub-sonics causes all within a 6m radius to make a Stun Save at +1; if the save is made, "
						+ "make a Difficult BOD check or suffer deafness and disorientation (-2 all skill rolls) for 40 seconds. "
						+ "Noise-resistant headphones and various editing cyberaudio options allow you to resist the effects.",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, 0, Concealability.POCKET, Availability.POOR,
				NullProbability.getInstance(), Reliability.VERY_RELIABLE, 30.0, 0.5));
		startingInventory.add(new ThrownWeapon("Nausea Grenade",
				"Causes Illness in a 3m radius resulting in -4 REF. On a successful Save, take half the effects.",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, 0, Concealability.POCKET, Availability.POOR,
				NullProbability.getInstance(), Reliability.VERY_RELIABLE, 30.0, 0.5));
		startingInventory.add(new ThrownWeapon("Teargas Grenade",
				"Tear gas causes tearing & -2 REF in a 10m radius. On a successful Save, take half the effects.",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, 0, Concealability.POCKET, Availability.POOR,
				NullProbability.getInstance(), Reliability.VERY_RELIABLE, 30.0, 0.5));
		startingInventory.add(new ThrownWeapon("Sleep Grenade",
				"Causes sleep. On a successful Save, causes drowsiness (-2 to all stats).",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, 0, Concealability.POCKET, Availability.POOR,
				NullProbability.getInstance(), Reliability.VERY_RELIABLE, 30.0, 0.5));
		startingInventory.add(new ThrownWeapon("Biotoxin I Grenade", "Deals 4D6 for each turn in the gas.",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, 0, Concealability.POCKET, Availability.POOR,
				NullProbability.getInstance(), Reliability.VERY_RELIABLE, 30.0, 0.5));
		startingInventory.add(new ThrownWeapon("Biotoxin II Grenade", "Deals 8D6 for each turn in the gas.",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, 0, Concealability.POCKET, Availability.POOR,
				NullProbability.getInstance(), Reliability.VERY_RELIABLE, 30.0, 0.5));
		startingInventory.add(new ThrownWeapon("Nerve Gas Grenade", "Deals 8D10 for each turn in the gas.",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, 0, Concealability.POCKET, Availability.POOR,
				NullProbability.getInstance(), Reliability.VERY_RELIABLE, 30.0, 0.5));
		// TODO Change ammunition type to a Launched Grenade type.
		startingInventory.add(new Firearm("Grenade Launchers",
				"These come from manufacturers worldwide, and may be attached to any assault rifle (under the barrel). "
						+ "Some can be given a simple shoulder stock for separate use.",
				CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, 0, Concealability.LONG_COAT, Availability.RARE,
				new HomogeneousMagazine(Cartridge.AMMUNITION_TYPE_GRENADE, 1), 1, Reliability.STANDARD, 225, 150.0, 1.0,
				shotgunAttachmentPoints));
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
		startingInventory.add(new ExoticFirearm("Avante P-1135 Needlegun",
				"Lightweight, plastic, compressed air powered. Can be doped with drugs, poison. See FNFF for details.",
				CyberpunkWeapon.WEAPON_TYPE_EXOTIC, CyberpunkSkill.HANDGUN, 0, Concealability.POCKET, Availability.POOR,
				NullProbability.getInstance(), new HomogeneousMagazine(CyberpunkWeapon.AMMUNITION_TYPE_DRUGS, 15), 2,
				Reliability.STANDARD, 40, 200.0, 1.0, shotgunAttachmentPoints));
		startingInventory.add(new ExoticFirearm("Enertex AKM Power Squirt",
				"A squirtgun. Yes, a powered squirtgun. See FNFF before you laugh.", CyberpunkWeapon.WEAPON_TYPE_EXOTIC,
				CyberpunkSkill.HANDGUN, -2, Concealability.JACKET, Availability.COMMON, NullProbability.getInstance(),
				new HomogeneousMagazine(CyberpunkWeapon.AMMUNITION_TYPE_DRUGS, 50), 1, Reliability.VERY_RELIABLE, 10,
				15.0, 1.0, shotgunAttachmentPoints));
		startingInventory.add(new ExoticFirearm("Nelspot \"Wombat\" Airpistol",
				"Paintball gun from hell. Can fire acid, paint, drugs, poison. See FNFF.",
				CyberpunkWeapon.WEAPON_TYPE_EXOTIC, CyberpunkSkill.HANDGUN, -1, Concealability.JACKET,
				Availability.COMMON, NullProbability.getInstance(),
				new HomogeneousMagazine(CyberpunkWeapon.AMMUNITION_TYPE_DRUGS, 20), 2, Reliability.UNRELIABLE, 40,
				200.0, 1.0, shotgunAttachmentPoints));
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
	}

	public CyberpunkWeapon sellWeapon(CyberpunkWeapon weapon) {
		CyberpunkWeapon clonedWeapon = (CyberpunkWeapon) SerializationUtils.clone(weapon);

		return clonedWeapon;
	}

	public CyberpunkArmor sellArmor(CyberpunkArmor armor) {
		CyberpunkArmor clonedArmor = (CyberpunkArmor) SerializationUtils.clone(armor);

		return clonedArmor;
	}

	public List<Ammunition> sellBoxOfAmmunition(Ammunition ammunition) {
		List<Ammunition> clonedAmmunition = new ArrayList<Ammunition>();
		for (int i = 0; i < ammunition.getAmmunitionPerBox(); i++) {
			clonedAmmunition.add((Ammunition) SerializationUtils.clone(ammunition));
		}

		return clonedAmmunition;
	}

	public double getAskPrice(CyberpunkWeapon weapon) {
		return trader.getAskPrice(weapon);
	}

	public double getAskPrice(CyberpunkArmor armor) {
		return trader.getAskPrice(armor);
	}

	public double getAskPrice(Ammunition ammunition) {
		return trader.getAskPrice(ammunition);
	}

	public double getBidPrice(CyberpunkWeapon weapon) {
		return trader.getBidPrice(weapon);
	}

	public double getBidPrice(CyberpunkArmor armor) {
		return trader.getBidPrice(armor);
	}

	public double getBidPrice(Ammunition ammunition) {
		return trader.getBidPrice(ammunition);
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
