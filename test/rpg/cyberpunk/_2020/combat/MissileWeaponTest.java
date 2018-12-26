package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.Test;

import rpg.cyberpunk._2020.combat.Ammunition.AmmoType;
import rpg.cyberpunk._2020.combat.Cartridge.BulletType;
import rpg.cyberpunk._2020.combat.Cartridge.CaseMaterial;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Availability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Concealability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Reliability;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.general.combat.Modifier.ModifierType;
import rpg.general.combat.WeaponModifier;
import rpg.util.Die;
import rpg.util.Probability;

public class MissileWeaponTest {
	private static String weaponName;
	private static String description;
	private static int weaponAccuracy;
	private static Concealability concealability;
	private static Availability availability;
	private static Probability damage;
	private static Cartridge cartridge;
	private static AmmoType ammoType;
	private static int numberOfShots;
	private static int rateOfFire;
	private static Reliability reliability;
	private static int range;
	private static double weight;
	private static int cost;

	@BeforeClass
	public static void setupBeforeClass() {
		weaponName = "Test Weapon";
		description = "Description for Test Weapon";
		weaponAccuracy = -1;
		concealability = Concealability.LONG_COAT;
		availability = Availability.COMMON;
		damage = new Probability(new Die(1, 10), 1);
		ammoType = AmmoType._9MM;
		cartridge = new Cartridge(ammoType, BulletType.SOFT_POINT, CaseMaterial.CASELESS, "", 0.0, 0.0);
		numberOfShots = 10;
		rateOfFire = 2;
		reliability = Reliability.VERY_RELIABLE;
		range = 30;
		weight = 0.5;
		cost = 999;
	}

	@Test
	public void testLightPistolWeaponTypeSetsSkillToHandgun() {
		MissileWeapon weapon = MissileWeapon.createTypeBasedWeapon(weaponName, description,
				CyberpunkWeapon.LIGHT_PISTOL, weaponAccuracy, concealability, availability, damage, ammoType,
				numberOfShots, rateOfFire, reliability, range, weight, cost);

		assertEquals(CyberpunkSkill.HANDGUN, weapon.getSkillName());
	}

	@Test
	public void testMediumPistolWeaponTypeSetsSkillToHandgun() {
		MissileWeapon weapon = MissileWeapon.createTypeBasedWeapon(weaponName, description,
				CyberpunkWeapon.MEDIUM_PISTOL, weaponAccuracy, concealability, availability, damage, ammoType,
				numberOfShots, rateOfFire, reliability, range, weight, cost);

		assertEquals(CyberpunkSkill.HANDGUN, weapon.getSkillName());
	}

	@Test
	public void testHeavyPistolWeaponTypeSetsSkillToHandgun() {
		MissileWeapon weapon = MissileWeapon.createTypeBasedWeapon(weaponName, description,
				CyberpunkWeapon.HEAVY_PISTOL, weaponAccuracy, concealability, availability, damage, ammoType,
				numberOfShots, rateOfFire, reliability, range, weight, cost);

		assertEquals(CyberpunkSkill.HANDGUN, weapon.getSkillName());
	}

	@Test
	public void testVeryHeavyPistolWeaponTypeSetsSkillToHandgun() {
		MissileWeapon weapon = MissileWeapon.createTypeBasedWeapon(weaponName, description,
				CyberpunkWeapon.VERY_HEAVY_PISTOL, weaponAccuracy, concealability, availability, damage, ammoType,
				numberOfShots, rateOfFire, reliability, range, weight, cost);

		assertEquals(CyberpunkSkill.HANDGUN, weapon.getSkillName());
	}

	@Test
	public void testLightSubmachinegunWeaponTypeSetsSkillToSubmachinegun() {
		MissileWeapon weapon = MissileWeapon.createTypeBasedWeapon(weaponName, description,
				CyberpunkWeapon.LIGHT_SUBMACHINEGUN, weaponAccuracy, concealability, availability, damage, ammoType,
				numberOfShots, rateOfFire, reliability, range, weight, cost);

		assertEquals(CyberpunkSkill.SUBMACHINEGUN, weapon.getSkillName());
	}

	@Test
	public void testMediumSubmachinegunWeaponTypeSetsSkillToSubmachinegun() {
		MissileWeapon weapon = MissileWeapon.createTypeBasedWeapon(weaponName, description,
				CyberpunkWeapon.MEDIUM_SUBMACHINEGUN, weaponAccuracy, concealability, availability, damage, ammoType,
				numberOfShots, rateOfFire, reliability, range, weight, cost);

		assertEquals(CyberpunkSkill.SUBMACHINEGUN, weapon.getSkillName());
	}

	@Test
	public void testHeavySubmachinegunWeaponTypeSetsSkillToSubmachinegun() {
		MissileWeapon weapon = MissileWeapon.createTypeBasedWeapon(weaponName, description,
				CyberpunkWeapon.HEAVY_SUBMACHINEGUN, weaponAccuracy, concealability, availability, damage, ammoType,
				numberOfShots, rateOfFire, reliability, range, weight, cost);

		assertEquals(CyberpunkSkill.SUBMACHINEGUN, weapon.getSkillName());
	}

	@Test
	public void testRifleWeaponTypeSetsSkillToRifle() {
		MissileWeapon weapon = MissileWeapon.createTypeBasedWeapon(weaponName, description, CyberpunkWeapon.RIFLE,
				weaponAccuracy, concealability, availability, damage, ammoType, numberOfShots, rateOfFire, reliability,
				range, weight, cost);

		assertEquals(CyberpunkSkill.RIFLE, weapon.getSkillName());
	}

	@Test
	public void testShotgunWeaponTypeSetsSkillToRifle() {
		MissileWeapon weapon = MissileWeapon.createTypeBasedWeapon(weaponName, description, CyberpunkWeapon.SHOTGUN,
				weaponAccuracy, concealability, availability, damage, ammoType, numberOfShots, rateOfFire, reliability,
				range, weight, cost);

		assertEquals(CyberpunkSkill.RIFLE, weapon.getSkillName());
	}

	@Test
	public void testHeavyWeaponTypeSetsSkillToHeavyWeapons() {
		MissileWeapon weapon = MissileWeapon.createTypeBasedWeapon(weaponName, description,
				CyberpunkWeapon.HEAVY_WEAPON, weaponAccuracy, concealability, availability, damage, ammoType,
				numberOfShots, rateOfFire, reliability, range, weight, cost);

		assertEquals(CyberpunkSkill.HEAVY_WEAPONS, weapon.getSkillName());
	}

	@Test
	public void testEdgedWeaponTypeSetsSkillToMelee() {
		MissileWeapon weapon = MissileWeapon.createTypeBasedWeapon(weaponName, description,
				CyberpunkWeapon.EDGED_WEAPON, weaponAccuracy, concealability, availability, damage, ammoType,
				numberOfShots, rateOfFire, reliability, range, weight, cost);

		assertEquals(CyberpunkSkill.MELEE, weapon.getSkillName());
	}

	@Test
	public void testBluntWeaponTypeSetsSkillToMelee() {
		MissileWeapon weapon = MissileWeapon.createTypeBasedWeapon(weaponName, description,
				CyberpunkWeapon.BLUNT_WEAPON, weaponAccuracy, concealability, availability, damage, ammoType,
				numberOfShots, rateOfFire, reliability, range, weight, cost);

		assertEquals(CyberpunkSkill.MELEE, weapon.getSkillName());
	}

	@Test
	public void testUnknownWeaponTypeSetsSkillToNotAvailable() {
		MissileWeapon weapon = MissileWeapon.createTypeBasedWeapon(weaponName, description, CyberpunkWeapon.NONE,
				weaponAccuracy, concealability, availability, damage, ammoType, numberOfShots, rateOfFire, reliability,
				range, weight, cost);

		assertEquals(CyberpunkSkill.NONE, weapon.getSkillName());
	}

	@Test
	public void testExoticWeaponSetsSkillToGivenAndTypeToExotic() {
		MissileWeapon weapon = MissileWeapon.createSkillBasedWeapon(weaponName, description, CyberpunkSkill.ARCHERY,
				weaponAccuracy, concealability, availability, damage, ammoType, numberOfShots, rateOfFire, reliability,
				range, weight, cost);

		assertEquals(CyberpunkSkill.ARCHERY, weapon.getSkillName());
		assertEquals(CyberpunkWeapon.EXOTIC, weapon.getWeaponType());
	}

	@Test
	public void testReloadingTwice() {
		Magazine magazine = new Magazine(ammoType, numberOfShots);
		for (int i = 0; i < numberOfShots; i++) {
			magazine.storeAmmunition(cartridge);
		}

		MissileWeapon weapon = MissileWeapon.createTypeBasedWeapon(weaponName, description,
				CyberpunkWeapon.LIGHT_PISTOL, weaponAccuracy, concealability, availability, damage, ammoType,
				numberOfShots, rateOfFire, reliability, range, weight, cost);

		Magazine spare = weapon.reload(magazine);
		assertEquals(10, weapon.getAmmoCount());
		assertEquals(0, spare.getAmmoCount());

		for (int i = 0; i < numberOfShots; i++) {
			magazine.storeAmmunition(cartridge);
		}
		spare = weapon.reload(magazine);
		assertEquals(10, weapon.getAmmoCount());
		assertEquals(10, spare.getAmmoCount());
	}

	@Test
	public void testReloadThenFireASingleRound() {
		Magazine magazine = mock(Magazine.class);
		when(magazine.getAmmoCount()).thenReturn(numberOfShots);
		when(magazine.getAmmunition()).thenReturn(cartridge);

		MissileWeapon weapon = MissileWeapon.createTypeBasedWeapon(weaponName, description,
				CyberpunkWeapon.LIGHT_PISTOL, weaponAccuracy, concealability, availability, damage, ammoType,
				numberOfShots, rateOfFire, reliability, range, weight, cost);

		weapon.reload(magazine);
		weapon.fire(1);

		assertEquals(9, weapon.getAmmoCount());
	}

	@Test
	public void testReloadThenFireThreeRoundBurst() {
		Magazine magazine = mock(Magazine.class);
		when(magazine.getAmmoCount()).thenReturn(numberOfShots);
		when(magazine.getAmmunition()).thenReturn(cartridge);

		MissileWeapon weapon = MissileWeapon.createTypeBasedWeapon(weaponName, description,
				CyberpunkWeapon.LIGHT_PISTOL, weaponAccuracy, concealability, availability, damage, ammoType,
				numberOfShots, rateOfFire, reliability, range, weight, cost);

		weapon.reload(magazine);
		weapon.fire(3);

		assertEquals(7, weapon.getAmmoCount());
	}

	@Test
	public void testReloadThenFireAllRounds() {
		Magazine magazine = mock(Magazine.class);
		when(magazine.getAmmoCount()).thenReturn(numberOfShots);
		when(magazine.getAmmunition()).thenReturn(cartridge);

		MissileWeapon weapon = MissileWeapon.createTypeBasedWeapon(weaponName, description,
				CyberpunkWeapon.LIGHT_PISTOL, weaponAccuracy, concealability, availability, damage, ammoType,
				numberOfShots, rateOfFire, reliability, range, weight, cost);

		weapon.reload(magazine);
		weapon.fire(numberOfShots);

		assertEquals(0, weapon.getAmmoCount());
	}

	@Test
	public void testAmmoCannotBeANegativeValue() {
		MissileWeapon weapon = MissileWeapon.createTypeBasedWeapon(weaponName, description,
				CyberpunkWeapon.LIGHT_PISTOL, weaponAccuracy, concealability, availability, damage, ammoType,
				numberOfShots, rateOfFire, reliability, range, weight, cost);

		weapon.fire(1);

		assertEquals(0, weapon.getAmmoCount());
	}

	@Test
	public void testWeaponModifiersEffectOnHitModifier() {
		MissileWeapon weapon = MissileWeapon.createTypeBasedWeapon(weaponName, description,
				CyberpunkWeapon.LIGHT_PISTOL, weaponAccuracy, concealability, availability, damage, ammoType,
				numberOfShots, rateOfFire, reliability, range, weight, cost);
		WeaponModifier mod1 = mock(WeaponModifier.class);
		when(mod1.getHitModifier()).thenReturn(3);
		when(mod1.getType()).thenReturn(ModifierType.SIGHT);
		weapon.setModifier(mod1);
		WeaponModifier mod2 = mock(WeaponModifier.class);
		when(mod2.getHitModifier()).thenReturn(4);
		when(mod2.getType()).thenReturn(ModifierType.UNDERBARREL);
		weapon.setModifier(mod2);

		assertEquals(6, weapon.getHitScore());
	}

	@Test
	public void testWeaponModifiersEffectOnDamageModifier() {
		MissileWeapon weapon = MissileWeapon.createTypeBasedWeapon(weaponName, description,
				CyberpunkWeapon.LIGHT_PISTOL, weaponAccuracy, concealability, availability, damage, ammoType,
				numberOfShots, rateOfFire, reliability, range, weight, cost);
		WeaponModifier mod = mock(WeaponModifier.class);
		when(mod.getDamageModifier()).thenReturn(4);
		when(mod.getType()).thenReturn(ModifierType.UNDERBARREL);
		weapon.setModifier(mod);

		assertEquals(5, weapon.getDamageScore());
	}
}
