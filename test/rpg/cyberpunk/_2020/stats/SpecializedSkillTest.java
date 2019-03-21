package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.Test;

import rpg.general.stats.Attribute;

public class SpecializedSkillTest {
	private static Attribute mockAttribute;

	@BeforeClass
	public static void setUpBeforeClass() {
		mockAttribute = mock(CyberpunkAttribute.class);
		when(mockAttribute.getModifier()).thenReturn(2);
	}

	@Test
	public void testSkillLevelEqualsOneIfLevelIncreasedOnce() {
		SpecializedSkill skill = new SpecializedSkill(mockAttribute, "Specialized Skill", "A skill", 1);

		skill.increaseLevel();

		assertEquals(1, skill.getLevel());
	}

	@Test
	public void testSkillLevelCannotExceedMaxLevel() {
		SpecializedSkill skill = new SpecializedSkill(mockAttribute, "Specialized Skill", "A skill", 1);

		for (int i = 0; i < SpecializedSkill.MAX_LEVEL; i++) {
			skill.increaseLevel();
		}

		assertEquals(10, skill.getLevel());
	}

	@Test
	public void testSkillLevelEqualsOneIfLevelIncreasedTwiceThenDecreasedOnce() {
		SpecializedSkill skill = new SpecializedSkill(mockAttribute, "Specialized Skill", "A skill", 1);

		skill.increaseLevel();
		skill.increaseLevel();
		skill.decreaseLevel();

		assertEquals(1, skill.getLevel());
	}

	@Test
	public void testSkillLevelCannotBeLowerThanMinLevel() {
		SpecializedSkill skill = new SpecializedSkill(mockAttribute, "Specialized Skill", "A skill", 1);

		skill.decreaseLevel();

		assertEquals(CyberpunkSkill.MIN_LEVEL, skill.getLevel());
	}

	@Test
	public void testImprovementPointsEqualsTwoIfIncreaseByTwo() {
		SpecializedSkill skill = new SpecializedSkill(mockAttribute, "Specialized Skill", "A skill", 1);

		skill.increaseCurrentImprovementPoints(2);

		assertEquals(2, skill.getCurrentImprovementPoints());
	}

	@Test
	public void testSkillLevelEqualsOneIfNeededImprovementPointsIsReached() {
		SpecializedSkill skill = new SpecializedSkill(mockAttribute, "Specialized Skill", "A skill", 1);

		skill.increaseCurrentImprovementPoints(skill.getNeededImprovementPoints());

		assertEquals(1, skill.getLevel());
	}

	@Test
	public void testSkillTotalLevelEqualsThreeIfIncreasedLevelOnce() {
		SpecializedSkill skill = new SpecializedSkill(mockAttribute, "Specialized Skill", "A skill", 1);

		skill.increaseLevel();

		assertEquals(3, skill.getTotalValue());
	}

	@Test
	public void testSkillTotalLevelEqualsTwoIfSkillLevelEqualsZero() {
		SpecializedSkill skill = new SpecializedSkill(mockAttribute, "Specialized Skill", "A skill", 1);

		assertEquals(2, skill.getTotalValue());
	}

	@Test
	public void testSkillTotalLevelEqualsThreeIfSkillLevelIncreasedOnce() {
		SpecializedSkill skill = new SpecializedSkill(mockAttribute, "Specialized Skill", "A skill", 1);

		skill.increaseLevel();

		assertEquals(3, skill.getTotalValue());
	}

	@Test
	public void testSkillLevelEqualsZeroIfSkillLevelIncreasedOnceThenReset() {
		SpecializedSkill skill = new SpecializedSkill(mockAttribute, "Specialized Skill", "A skill", 1);

		skill.increaseLevel();
		skill.resetLevel();

		assertEquals(0, skill.getLevel());
	}
}
