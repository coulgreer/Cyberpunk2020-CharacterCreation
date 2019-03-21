package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.Test;

import rpg.general.stats.Attribute;
import rpg.general.stats.SkillRestriction;

public class RoleSkillTest {
	private static Attribute mockAttribute;
	private static SkillRestriction mockSkillRestriction;

	@BeforeClass
	public static void setUpBeforeClass() {
		mockAttribute = mock(Attribute.class);
		when(mockAttribute.getModifier()).thenReturn(2);

		mockSkillRestriction = mock(SkillRestriction.class);
		when(mockSkillRestriction.isRestricted()).thenReturn(false);
	}

	@Test
	public void testSkillLevelEqualsOneIfLevelIncreasesOnce() {
		RoleSkill skill = new RoleSkill(mockAttribute, "Test Skill", "A skill for a players role",
				mockSkillRestriction);

		skill.increaseLevel();

		assertEquals(1, skill.getLevel());
	}

	@Test
	public void testSkillLevelCannotExceedMax() {
		RoleSkill skill = new RoleSkill(mockAttribute, "Test Skill", "A skill for a players role",
				mockSkillRestriction);

		for (int i = 0; i < SpecializedSkill.MAX_LEVEL; i++) {
			skill.increaseLevel();
		}

		assertEquals(10, skill.getLevel());
	}

	@Test
	public void testSkillLevelEqualsOneIfIncreasedTwiceThenDecreasedOnce() {
		RoleSkill skill = new RoleSkill(mockAttribute, "Test Skill", "A skill for a players role",
				mockSkillRestriction);

		skill.increaseLevel();
		skill.increaseLevel();
		skill.decreaseLevel();

		assertEquals(1, skill.getLevel());

	}

	@Test
	public void testSkillLevelCannotBeLowerThanMinLevel() {
		RoleSkill skill = new RoleSkill(mockAttribute, "Test Skill", "A skill for a players role",
				mockSkillRestriction);

		skill.decreaseLevel();

		assertEquals(0, skill.getLevel());
	}

	@Test
	public void testImprovementPointsEqualsTwoIfIncreasedByTwo() {
		RoleSkill skill = new RoleSkill(mockAttribute, "Test Skill", "A skill for a players role",
				mockSkillRestriction);

		skill.increaseCurrentImprovementPoints(2);

		assertEquals(2, skill.getCurrentImprovementPoints());
	}

	@Test
	public void testSkillLevelEqualsOneIfNeededImprovementPointsIsReached() {
		RoleSkill skill = new RoleSkill(mockAttribute, "Test Skill", "A skill for a players role",
				mockSkillRestriction);

		skill.increaseCurrentImprovementPoints(skill.getNeededImprovementPoints());

		assertEquals(1, skill.getLevel());
	}

	@Test
	public void testSkillTotalLevelEqualsThreeIfIncreasedLevelOnce() {
		RoleSkill skill = new RoleSkill(mockAttribute, "Test Skill", "A skill for a players role",
				mockSkillRestriction);

		skill.increaseLevel();

		assertEquals(3, skill.getTotalValue());
	}

	@Test
	public void testSkillTotalLevelEqualsTwoIfSkillLevelEqualsZero() {
		RoleSkill skill = new RoleSkill(mockAttribute, "Test Skill", "A skill for a players role",
				mockSkillRestriction);

		assertEquals(2, skill.getTotalValue());
	}

	@Test
	public void testSkillTotalLevelEqualsThreeIfSkillLevelIncreasedOnce() {
		RoleSkill skill = new RoleSkill(mockAttribute, "Test Skill", "A skill for a players role",
				mockSkillRestriction);

		skill.increaseLevel();

		assertEquals(3, skill.getTotalValue());
	}

	@Test
	public void testSkillLevelEqualsZeroIfSkillLevelIncreasedOnceThenReset() {
		RoleSkill skill = new RoleSkill(mockAttribute, "Test Skill", "A skill for a players role",
				mockSkillRestriction);

		skill.increaseLevel();
		skill.resetLevel();

		assertEquals(0, skill.getLevel());
	}
}
