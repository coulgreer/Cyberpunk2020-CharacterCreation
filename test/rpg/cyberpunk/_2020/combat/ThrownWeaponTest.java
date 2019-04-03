package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import rpg.Player;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Availability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Concealability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Reliability;
import rpg.general.combat.Ammunition;
import rpg.general.combat.AmmunitionContainer;
import rpg.util.Die;
import rpg.util.Probability;

public class ThrownWeaponTest {
	private static String weaponName;
	private static String description;
	private static int weaponAccuracy;
	private static Concealability concealability;
	private static Availability availability;
	private static Probability damage;
	private static String ammoType;
	private static Reliability reliability;
	private static double cost;
	private static double weight;

	@BeforeClass
	public static void setupBeforeClass() {
		weaponName = "Test Weapon";
		description = "Description for Test Weapon";
		weaponAccuracy = -1;
		concealability = Concealability.LONG_COAT;
		availability = Availability.COMMON;
		damage = new Probability(new Die(1, 10), 1);
		ammoType = CyberpunkWeapon.NO_WEAPON_TYPE;
		reliability = Reliability.VERY_RELIABLE;
		weight = 0.5;
		cost = 999.0;
	}

	@Test
	public void testThrownWeaponCannotStoreAmmo() {
		Ammunition mockedAmmunition = mock(Ammunition.class);
		when(mockedAmmunition.getAmmunitionType()).thenReturn(ammoType);

		int containerCapacity = 2;
		AmmunitionContainer ammunitionContainer = new HomogeneousMagazine(ammoType, containerCapacity);
		for (int i = 0; i < containerCapacity; i++) {
			ammunitionContainer.depositAmmunition(mockedAmmunition);
		}

		ThrownWeapon weapon = new ThrownWeapon(weaponName, description, CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL,
				weaponAccuracy, concealability, availability, damage, reliability, cost, weight);

		List<Ammunition> remainingAmmunition = weapon.reload(ammunitionContainer);

		assertTrue(remainingAmmunition.contains(mockedAmmunition));
		assertEquals(containerCapacity, remainingAmmunition.size());
		assertEquals(ThrownWeapon.AMMUNITION_CAPACITY, weapon.getAmmunitionCount());
	}

	@Test
	public void testFiringThrownWeaponChangesToUnarmedWeapon() {
		Player player = new Player();
		Player spyPlayer = spy(player);

		ThrownWeapon weapon = new ThrownWeapon(weaponName, description, CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL,
				weaponAccuracy, concealability, availability, damage, reliability, cost, weight);
		spyPlayer.arm(CyberpunkCombatant.PRIMARY_SLOT, weapon);

		weapon.fire(1);

		// TODO Make the test check for what weapon type is help in each slot, not the
		// ammo capacity. The ammo capacity can be very misleading.
		assertEquals(0, player.getAmmoCount(CyberpunkCombatant.PRIMARY_SLOT));
		assertEquals(0, player.getAmmoCount(CyberpunkCombatant.SECONDARY_SLOT));
	}
}
