package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rpg.general.combat.Combatant;

public class StaticRangeCombatCalculatorTest {
	private StaticRangeCombatCalculator calculator;

	@Before
	public void setUp() {
		calculator = new StaticRangeCombatCalculator();
	}

	@After
	public void tearDown() {
		calculator.setCombatant(NullCombatant.getInstance());
		calculator.setWeapon(NullCyberpunkWeapon.getInstance());
	}

	@Test
	public void testGetRange() {
		assertEquals(0, calculator.getRangeScore());
	}

	@Test
	public void testCalculateHitScore() {
		Combatant mockedCombatant = mock(CyberpunkCombatant.class);
		CyberpunkWeapon mockedWeapon = mock(MissileWeapon.class);

		when(mockedCombatant.getHitModifier(any(CyberpunkWeapon.class))).thenReturn(3);
		when(mockedWeapon.getHitModifier()).thenReturn(-1);

		calculator.setCombatantThenCalculate(mockedCombatant);
		assertEquals(3, calculator.getHitScore());

		calculator.setWeaponThenCalculate(mockedWeapon);
		assertEquals(2, calculator.getHitScore());

	}

	@Test
	public void testCalculateDamageScore() {
		Combatant mockedCombatant = mock(CyberpunkCombatant.class);
		CyberpunkWeapon mockedWeapon = mock(MissileWeapon.class);

		when(mockedCombatant.getDamageModifier(any(CyberpunkWeapon.class))).thenReturn(1);
		when(mockedWeapon.getDamageModifier()).thenReturn(1);

		calculator.setCombatantThenCalculate(mockedCombatant);
		assertEquals(1, calculator.getDamageScore());

		calculator.setWeaponThenCalculate(mockedWeapon);
		assertEquals(2, calculator.getDamageScore());
	}

	@Test
	public void testCalculateRangeNeverChanges() {
		Combatant mockedCombatant = mock(CyberpunkCombatant.class);
		CyberpunkWeapon mockedWeapon = mock(MissileWeapon.class);

		when(mockedCombatant.getRangeModifier(any())).thenReturn(999);
		when(mockedWeapon.getRangeModifier()).thenReturn(99);

		calculator.setCombatantThenCalculate(mockedCombatant);
		assertEquals(0, calculator.getRangeScore());

		calculator.setWeaponThenCalculate(mockedWeapon);
		assertEquals(99, calculator.getRangeScore());
	}
}
