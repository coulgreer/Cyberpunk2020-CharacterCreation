package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import rpg.cyberpunk._2020.AttributeName;
import rpg.general.stats.AbstractAttribute;

public class DerivedStatisticTest {
	private StatisticCalculator mockedCalculator;
	private AbstractAttribute mockedConcreteAttribute;

	@Before
	public void setUp() {
		mockedCalculator = mock(StatisticCalculator.class);
		mockedConcreteAttribute = mock(CyberpunkAttribute.class);
	}

	@Test
	public void testIncreasingConcreteStatIncreasesDerivedStat() {
		AbstractAttribute parentStatistic = new CyberpunkAttribute(AttributeName.MOVEMENT_ALLOWANCE, "");
		DerivedAttribute derivedStatistic = new DerivedAttribute(AttributeName.RUN,
				"To determine how far your character can run in a single combat round in meters.", parentStatistic,
				mockedCalculator);
		when(mockedCalculator.calculate()).thenReturn(3.0);

		parentStatistic.increaseLevel();

		assertEquals(3, derivedStatistic.getLevel());
	}
	
	@Test
	public void testIncreasingConcreteStatIncreasesParentDerivedStatAndItsChild() {
		AbstractAttribute parentConcreteStatistic = new CyberpunkAttribute(AttributeName.MOVEMENT_ALLOWANCE, "");
		DerivedAttribute parentDerivedStatistic = new DerivedAttribute(AttributeName.RUN,
				"To determine how far your character can run in a single combat round in meters.", parentConcreteStatistic,
				mockedCalculator);
		DerivedAttribute childDerivedStatistic = new DerivedAttribute(AttributeName.RUN,
				"To determine how far your character can run in a single combat round in meters.", parentDerivedStatistic,
				mockedCalculator);
		when(mockedCalculator.calculate()).thenReturn(3.0);

		parentConcreteStatistic.increaseLevel();

		assertEquals(3, childDerivedStatistic.getLevel());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testIncreaseValueThrowsUnsupportedOperationException() {
		DerivedAttribute derivedStatistic = new DerivedAttribute(AttributeName.RUN,
				"To determine how far your character can run in a single combat round in meters.",
				mockedConcreteAttribute, mockedCalculator);
		derivedStatistic.increaseLevel();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testDecreaseValueThrowsUnsupportedOperationException() {
		DerivedAttribute derivedStatistic = new DerivedAttribute(AttributeName.RUN,
				"To determine how far your character can run in a single combat round in meters.",
				mockedConcreteAttribute, mockedCalculator);
		derivedStatistic.decreaseLevel();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testResetValueThrowsUnsupportedOperationException() {
		DerivedAttribute derivedStatistic = new DerivedAttribute(AttributeName.RUN,
				"To determine how far your character can run in a single combat round in meters.",
				mockedConcreteAttribute, mockedCalculator);
		derivedStatistic.resetLevel();
	}
}
