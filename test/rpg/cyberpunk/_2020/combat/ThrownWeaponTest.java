package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.Test;

import rpg.Player;
import rpg.cyberpunk.CyberpunkCombatant;
import rpg.cyberpunk._2020.combat.Ammunition.AmmoType;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Availability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Concealability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Reliability;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.util.Die;
import rpg.util.Probability;

public class ThrownWeaponTest {
	private static String weaponName;
	private static String description;
	private static int weaponAccuracy;
	private static Concealability concealability;
	private static Availability availability;
	private static Probability damage;
	private static AmmoType ammoType;
	private static int rateOfFire;
	private static Reliability reliability;
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
		ammoType = AmmoType.NONE;
		rateOfFire = 1;
		reliability = Reliability.VERY_RELIABLE;
		weight = 0.5;
		cost = 999;
	}

	@Test
	public void testLightPistolWeaponTypeSetsSkillToHandgun() {
		ThrownWeapon weapon = new ThrownWeapon(weaponName, description, CyberpunkWeapon.LIGHT_PISTOL, weaponAccuracy,
				concealability, availability, damage, rateOfFire, reliability, weight, cost);

		assertEquals(CyberpunkSkill.HANDGUN, weapon.getSkillName());
	}

	@Test
	public void testMediumPistolWeaponTypeSetsSkillToHandgun() {
		ThrownWeapon weapon = new ThrownWeapon(weaponName, description, CyberpunkWeapon.MEDIUM_PISTOL, weaponAccuracy,
				concealability, availability, damage, rateOfFire, reliability, weight, cost);

		assertEquals(CyberpunkSkill.HANDGUN, weapon.getSkillName());
	}

	@Test
	public void testHeavyPistolWeaponTypeSetsSkillToHandgun() {
		ThrownWeapon weapon = new ThrownWeapon(weaponName, description, CyberpunkWeapon.HEAVY_PISTOL, weaponAccuracy,
				concealability, availability, damage, rateOfFire, reliability, weight, cost);

		assertEquals(CyberpunkSkill.HANDGUN, weapon.getSkillName());
	}

	@Test
	public void testVeryHeavyPistolWeaponTypeSetsSkillToHandgun() {
		ThrownWeapon weapon = new ThrownWeapon(weaponName, description, CyberpunkWeapon.VERY_HEAVY_PISTOL,
				weaponAccuracy, concealability, availability, damage, rateOfFire, reliability, weight, cost);

		assertEquals(CyberpunkSkill.HANDGUN, weapon.getSkillName());
	}

	@Test
	public void testLightSubmachinegunWeaponTypeSetsSkillToSubmachinegun() {
		ThrownWeapon weapon = new ThrownWeapon(weaponName, description, CyberpunkWeapon.LIGHT_SUBMACHINEGUN,
				weaponAccuracy, concealability, availability, damage, rateOfFire, reliability, weight, cost);

		assertEquals(CyberpunkSkill.SUBMACHINEGUN, weapon.getSkillName());
	}

	@Test
	public void testMediumSubmachinegunWeaponTypeSetsSkillToSubmachinegun() {
		ThrownWeapon weapon = new ThrownWeapon(weaponName, description, CyberpunkWeapon.MEDIUM_SUBMACHINEGUN,
				weaponAccuracy, concealability, availability, damage, rateOfFire, reliability, weight, cost);

		assertEquals(CyberpunkSkill.SUBMACHINEGUN, weapon.getSkillName());
	}

	@Test
	public void testHeavySubmachinegunWeaponTypeSetsSkillToSubmachinegun() {
		ThrownWeapon weapon = new ThrownWeapon(weaponName, description, CyberpunkWeapon.HEAVY_SUBMACHINEGUN,
				weaponAccuracy, concealability, availability, damage, rateOfFire, reliability, weight, cost);

		assertEquals(CyberpunkSkill.SUBMACHINEGUN, weapon.getSkillName());
	}

	@Test
	public void testRifleWeaponTypeSetsSkillToRifle() {
		ThrownWeapon weapon = new ThrownWeapon(weaponName, description, CyberpunkWeapon.RIFLE, weaponAccuracy,
				concealability, availability, damage, rateOfFire, reliability, weight, cost);

		assertEquals(CyberpunkSkill.RIFLE, weapon.getSkillName());
	}

	@Test
	public void testShotgunWeaponTypeSetsSkillToRifle() {
		ThrownWeapon weapon = new ThrownWeapon(weaponName, description, CyberpunkWeapon.SHOTGUN, weaponAccuracy,
				concealability, availability, damage, rateOfFire, reliability, weight, cost);

		assertEquals(CyberpunkSkill.RIFLE, weapon.getSkillName());
	}

	@Test
	public void testHeavyWeaponTypeSetsSkillToHeavyWeapons() {
		ThrownWeapon weapon = new ThrownWeapon(weaponName, description, CyberpunkWeapon.HEAVY_WEAPON, weaponAccuracy,
				concealability, availability, damage, rateOfFire, reliability, weight, cost);

		assertEquals(CyberpunkSkill.HEAVY_WEAPONS, weapon.getSkillName());
	}

	@Test
	public void testEdgedWeaponTypeSetsSkillToMelee() {
		ThrownWeapon weapon = new ThrownWeapon(weaponName, description, CyberpunkWeapon.EDGED_WEAPON, weaponAccuracy,
				concealability, availability, damage, rateOfFire, reliability, weight, cost);

		assertEquals(CyberpunkSkill.MELEE, weapon.getSkillName());
	}

	@Test
	public void testBluntWeaponTypeSetsSkillToMelee() {
		ThrownWeapon weapon = new ThrownWeapon(weaponName, description, CyberpunkWeapon.BLUNT_WEAPON, weaponAccuracy,
				concealability, availability, damage, rateOfFire, reliability, weight, cost);

		assertEquals(CyberpunkSkill.MELEE, weapon.getSkillName());
	}

	@Test
	public void testUnknownWeaponTypeSetsSkillToNotAvailable() {
		ThrownWeapon weapon = new ThrownWeapon(weaponName, description, CyberpunkWeapon.NONE, weaponAccuracy,
				concealability, availability, damage, rateOfFire, reliability, weight, cost);

		assertEquals(CyberpunkSkill.NONE, weapon.getSkillName());
	}

	@Test
	public void testThrownWeaponCannotStoreAmmo() {
		Ammunition mockedAmmunition = mock(Ammunition.class);
		when(mockedAmmunition.getAmmoType()).thenReturn(ammoType);
		Magazine magazine = new Magazine(ammoType, 2);
		ThrownWeapon weapon = new ThrownWeapon(weaponName, description, CyberpunkWeapon.LIGHT_PISTOL, weaponAccuracy,
				concealability, availability, damage, rateOfFire, reliability, weight, cost);

		assertEquals(magazine, weapon.reload(magazine));
		assertEquals(0, weapon.getAmmoCount());
	}

	@Test
	public void testFiringThrownWeaponChangesToUnarmedWeapon() {
		Player player = new Player();
		Player spyPlayer = spy(player);

		// TODO
		ThrownWeapon weapon = new ThrownWeapon(weaponName, description, CyberpunkWeapon.LIGHT_PISTOL, weaponAccuracy,
				concealability, availability, damage, rateOfFire, reliability, weight, cost);
		spyPlayer.arm(CyberpunkCombatant.PRIMARY_SLOT, weapon);

		weapon.fire(1);

		assertEquals(0, weapon.getAmmoCount());
	}
}
