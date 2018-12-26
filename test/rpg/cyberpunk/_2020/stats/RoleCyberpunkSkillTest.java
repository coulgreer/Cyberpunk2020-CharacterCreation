package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rpg.general.stats.AbstractAttribute;

public class RoleCyberpunkSkillTest {
	private static AbstractAttribute mockedAttribute;

	private RoleCyberpunkSkill skill;

	@BeforeClass
	public static void setUpBeforeClass() {
		mockedAttribute = mock(CyberpunkAttribute.class);
		when(mockedAttribute.getModifier()).thenReturn(2);
	}

	@Before
	public void setUp() {
		skill = new RoleCyberpunkSkill(mockedAttribute, CyberpunkSkill.CRYOTANK_OPERATION, "Works on cryotank");
	}

	@After
	public void tearDown() {
		skill = null;
	}

	@Test
	public void testIncreaseLevelIncreasesTotalValue() {
		skill.increaseLevel();

		assertEquals(3, skill.getTotalValue());
	}

	@Test
	public void testSkillLevelMax() {
		for (int i = 0; i < SpecializedSkill.MAX_LEVEL; i++) {
			skill.increaseLevel();
		}

		assertEquals(12, skill.getTotalValue());
	}

	@Test
	public void testSkillIncreasedBy2ThenDecreasedBy1() {
		skill.increaseLevel();
		skill.increaseLevel();
		skill.decreaseLevel();

		assertEquals(3, skill.getTotalValue());

	}

	@Test
	public void testSkillLevelMin() {
		skill.decreaseLevel();

		assertEquals(0, skill.getTotalValue());
	}

	@Test
	public void testImprovementPointsIncreaseBy2() {
		skill.increaseCurrentImprovementPoints(2);

		assertEquals(2, skill.getCurrentImprovementPoints());
	}

	@Test
	public void testLevelUpWhenNeededImprovementPointsIsReached() {
		for (int i = 0; i < skill.getNeededImprovementPoints(); i++) {
			skill.increaseCurrentImprovementPoints(1);
		}

		assertEquals(1, skill.getLevel());
	}
}
