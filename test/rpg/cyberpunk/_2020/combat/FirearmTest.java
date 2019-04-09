package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rpg.cyberpunk._2020.combat.Cartridge.Bullet;
import rpg.cyberpunk._2020.combat.Cartridge.CaseMaterial;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Availability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Concealability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Reliability;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.general.combat.Ammunition;
import rpg.general.combat.AmmunitionContainer;

public class FirearmTest {
	private static String weaponName;
	private static String description;
	private static int weaponAccuracy;
	private static Concealability concealability;
	private static Availability availability;
	private static Ammunition cartridge;
	private static String ammunitionType;
	private static int numberOfShots;
	private static int rateOfFire;
	private static Reliability reliability;
	private static int range;
	private static double cost;
	private static double weight;
	private static Set<String> attachmentPoints;
	private AmmunitionContainer ammunitionContainer;

	@BeforeClass
	public static void setupBeforeClass() {
		weaponName = "Test Weapon";
		description = "Description for Test Weapon";
		weaponAccuracy = -1;
		concealability = Concealability.LONG_COAT;
		availability = Availability.COMMON;
		ammunitionType = Cartridge.AMMUNITION_TYPE_9MM;
		cartridge = new Cartridge(ammunitionType, Bullet.SOFT_POINT, CaseMaterial.CASELESS, 0.0, 0.0);
		numberOfShots = 10;
		rateOfFire = 2;
		reliability = Reliability.VERY_RELIABLE;
		range = 30;
		cost = 999.0;
		weight = 0.5;
		attachmentPoints = new HashSet<String>();
	}

	@Before
	public void setUp() {
		ammunitionContainer = new HomogeneousMagazine(ammunitionType, numberOfShots);
	}

	@Test
	public void testLightPistolWeaponTypeSetsSkillToHandgun() {
		Firearm weapon = new Firearm(weaponName, description, CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, weaponAccuracy,
				concealability, availability, ammunitionContainer, rateOfFire, reliability, range, cost, weight,
				attachmentPoints);

		assertEquals(CyberpunkSkill.HANDGUN, weapon.getSkillName());
	}

	@Test
	public void testMediumPistolWeaponTypeSetsSkillToHandgun() {
		Firearm weapon = new Firearm(weaponName, description, CyberpunkWeapon.WEAPON_TYPE_MEDIUM_PISTOL, weaponAccuracy,
				concealability, availability, ammunitionContainer, rateOfFire, reliability, range, cost, weight,
				attachmentPoints);

		assertEquals(CyberpunkSkill.HANDGUN, weapon.getSkillName());
	}

	@Test
	public void testHeavyPistolWeaponTypeSetsSkillToHandgun() {
		Firearm weapon = new Firearm(weaponName, description, CyberpunkWeapon.WEAPON_TYPE_HEAVY_PISTOL, weaponAccuracy,
				concealability, availability, ammunitionContainer, rateOfFire, reliability, range, cost, weight,
				attachmentPoints);

		assertEquals(CyberpunkSkill.HANDGUN, weapon.getSkillName());
	}

	@Test
	public void testVeryHeavyPistolWeaponTypeSetsSkillToHandgun() {
		Firearm weapon = new Firearm(weaponName, description, CyberpunkWeapon.WEAPON_TYPE_VERY_HEAVY_PISTOL,
				weaponAccuracy, concealability, availability, ammunitionContainer, rateOfFire, reliability, range, cost,
				weight, attachmentPoints);

		assertEquals(CyberpunkSkill.HANDGUN, weapon.getSkillName());
	}

	@Test
	public void testLightSubmachinegunWeaponTypeSetsSkillToSubmachinegun() {
		Firearm weapon = new Firearm(weaponName, description, CyberpunkWeapon.WEAPON_TYPE_LIGHT_SUBMACHINEGUN,
				weaponAccuracy, concealability, availability, ammunitionContainer, rateOfFire, reliability, range, cost,
				weight, attachmentPoints);

		assertEquals(CyberpunkSkill.SUBMACHINEGUN, weapon.getSkillName());
	}

	@Test
	public void testMediumSubmachinegunWeaponTypeSetsSkillToSubmachinegun() {
		Firearm weapon = new Firearm(weaponName, description, CyberpunkWeapon.WEAPON_TYPE_MEDIUM_SUBMACHINEGUN,
				weaponAccuracy, concealability, availability, ammunitionContainer, rateOfFire, reliability, range, cost,
				weight, attachmentPoints);

		assertEquals(CyberpunkSkill.SUBMACHINEGUN, weapon.getSkillName());
	}

	@Test
	public void testHeavySubmachinegunWeaponTypeSetsSkillToSubmachinegun() {
		Firearm weapon = new Firearm(weaponName, description, CyberpunkWeapon.WEAPON_TYPE_HEAVY_SUBMACHINEGUN,
				weaponAccuracy, concealability, availability, ammunitionContainer, rateOfFire, reliability, range, cost,
				weight, attachmentPoints);

		assertEquals(CyberpunkSkill.SUBMACHINEGUN, weapon.getSkillName());
	}

	@Test
	public void testRifleWeaponTypeSetsSkillToRifle() {
		Firearm weapon = new Firearm(weaponName, description, CyberpunkWeapon.WEAPON_TYPE_RIFLE, weaponAccuracy,
				concealability, availability, ammunitionContainer, rateOfFire, reliability, range, cost, weight,
				attachmentPoints);

		assertEquals(CyberpunkSkill.RIFLE, weapon.getSkillName());
	}

	@Test
	public void testShotgunWeaponTypeSetsSkillToRifle() {
		Firearm weapon = new Firearm(weaponName, description, CyberpunkWeapon.WEAPON_TYPE_SHOTGUN, weaponAccuracy,
				concealability, availability, ammunitionContainer, rateOfFire, reliability, range, cost, weight,
				attachmentPoints);

		assertEquals(CyberpunkSkill.RIFLE, weapon.getSkillName());
	}

	@Test
	public void testHeavyWeaponTypeSetsSkillToHeavyWeapons() {
		Firearm weapon = new Firearm(weaponName, description, CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, weaponAccuracy,
				concealability, availability, ammunitionContainer, rateOfFire, reliability, range, cost, weight,
				attachmentPoints);

		assertEquals(CyberpunkSkill.HEAVY_WEAPONS, weapon.getSkillName());
	}

	@Test
	public void testMeleeWeaponTypeSetsSkillToMelee() {
		Firearm weapon = new Firearm(weaponName, description, CyberpunkWeapon.WEAPON_TYPE_MELEE_WEAPON, weaponAccuracy,
				concealability, availability, ammunitionContainer, rateOfFire, reliability, range, cost, weight,
				attachmentPoints);

		assertEquals(CyberpunkSkill.MELEE, weapon.getSkillName());
	}

	@Test
	public void testDefaultWeaponTypeSetsSkillToNotAvailable() {
		Firearm weapon = new Firearm(weaponName, description, CyberpunkWeapon.NO_WEAPON_TYPE, weaponAccuracy,
				concealability, availability, ammunitionContainer, rateOfFire, reliability, range, cost, weight,
				attachmentPoints);

		assertEquals(CyberpunkSkill.NONE, weapon.getSkillName());
	}

	@Test
	public void testExoticWeaponSetsSkillToGivenAndTypeToExotic() {
		Firearm weapon = new Firearm(weaponName, description, CyberpunkWeapon.WEAPON_TYPE_EXOTIC,
				CyberpunkSkill.ARCHERY, weaponAccuracy, concealability, availability, ammunitionContainer, rateOfFire,
				reliability, range, cost, weight, attachmentPoints);

		assertEquals(CyberpunkSkill.ARCHERY, weapon.getSkillName());
		assertEquals(CyberpunkWeapon.WEAPON_TYPE_EXOTIC, weapon.getWeaponType());
	}

	@Test
	public void testReloadingTwice() {
		Firearm weapon = new Firearm(weaponName, description, CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, weaponAccuracy,
				concealability, availability, ammunitionContainer, rateOfFire, reliability, range, cost, weight,
				attachmentPoints);

		AmmunitionContainer magazine = new HomogeneousMagazine(ammunitionType, numberOfShots);

		fillMagazine(magazine, numberOfShots);
		List<Ammunition> spareAmmunition = weapon.reload(magazine);
		assertEquals(numberOfShots, weapon.getAmmunitionCount());
		assertEquals(0, spareAmmunition.size());

		fillMagazine(magazine, numberOfShots);
		spareAmmunition = weapon.reload(magazine);
		assertEquals(numberOfShots, weapon.getAmmunitionCount());
		assertEquals(numberOfShots, spareAmmunition.size());
	}

	private void fillMagazine(AmmunitionContainer magazine, int amount) {
		for (int i = 0; i < amount && !magazine.isFull(); i++) {
			magazine.depositAmmunition(cartridge);
		}
	}

	@Test
	public void testReloadThenFireASingleRound() {
		Firearm weapon = new Firearm(weaponName, description, CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, weaponAccuracy,
				concealability, availability, ammunitionContainer, rateOfFire, reliability, range, cost, weight,
				attachmentPoints);

		AmmunitionContainer magazine = new HomogeneousMagazine(ammunitionType, numberOfShots);
		fillMagazine(magazine, numberOfShots);

		weapon.reload(magazine);
		weapon.fire(1);

		assertEquals(9, weapon.getAmmunitionCount());
	}

	@Test
	public void testReloadThenFireThreeRoundBurst() {
		Firearm weapon = new Firearm(weaponName, description, CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, weaponAccuracy,
				concealability, availability, ammunitionContainer, rateOfFire, reliability, range, cost, weight,
				attachmentPoints);

		AmmunitionContainer magazine = new HomogeneousMagazine(ammunitionType, numberOfShots);
		fillMagazine(magazine, numberOfShots);

		weapon.reload(magazine);
		weapon.fire(3);

		assertEquals(7, weapon.getAmmunitionCount());
	}

	@Test
	public void testReloadThenFireAllRounds() {
		Firearm weapon = new Firearm(weaponName, description, CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, weaponAccuracy,
				concealability, availability, ammunitionContainer, rateOfFire, reliability, range, cost, weight,
				attachmentPoints);

		AmmunitionContainer magazine = new HomogeneousMagazine(ammunitionType, numberOfShots);
		fillMagazine(magazine, numberOfShots);

		weapon.reload(magazine);
		weapon.fire(numberOfShots);

		assertEquals(0, weapon.getAmmunitionCount());
	}

	@Test
	public void testAmmoCannotBeANegativeValue() {
		Firearm weapon = new Firearm(weaponName, description, CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, weaponAccuracy,
				concealability, availability, ammunitionContainer, rateOfFire, reliability, range, cost, weight,
				attachmentPoints);

		weapon.fire(1);

		assertEquals(0, weapon.getAmmunitionCount());
	}
}
