package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rpg.cyberpunk._2020.AttributeName;
import rpg.general.stats.Attribute;

public class StatisticCalculatorTest {
	private Attribute attribute;

	@Before
	public void setUp() {
		attribute = new CyberpunkAttribute(AttributeName.MOVEMENT_ALLOWANCE,
				"This is an index of how fast your character can run.");
	}

	@After
	public void tearDown() {
		attribute = null;
	}

	@Test
	public void testRunCalculator() {
		StatisticCalculator calculator = new RunCalculator();
		calculator.setStatistic(attribute);
		assertEquals(6.0, calculator.calculate(), 0);
	}

	@Test
	public void testLeapCalculator() {
		StatisticCalculator calculator = new LeapCalculator();
		calculator.setStatistic(attribute);
		assertEquals(0.5, calculator.calculate(), 0);
	}

	@Test
	public void testCarryCalculator() {
		StatisticCalculator calculator = new CarryCalculator();
		calculator.setStatistic(attribute);
		assertEquals(20.0, calculator.calculate(), 0);
	}

}
