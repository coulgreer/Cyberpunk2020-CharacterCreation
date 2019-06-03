package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.BeforeClass;
import org.junit.Test;
import rpg.cyberpunk._2020.Player;
import rpg.general.stats.Attribute;

public class RoleSkillTest {
  private static final String skillName = "Test Skill";
  private static Attribute mockAttribute;
  private static Player mockPlayer;
  private static Role mockRole;

  @BeforeClass
  public static void setUpBeforeClass() {
    mockAttribute = mock(Attribute.class);
    when(mockAttribute.getModifier()).thenReturn(2);

    mockRole = mock(Role.class);
    when(mockRole.getSpecialAbilityName()).thenReturn(skillName);

    mockPlayer = mock(Player.class);
    when(mockPlayer.getRole()).thenReturn(mockRole);
  }

  @Test
  public void testSkillLevelEqualsOneIfLevelIncreasesOnce() {
    RoleSkill skill =
        new RoleSkill(mockAttribute, skillName, "A skill for a players role", mockPlayer);

    skill.increaseLevel();

    assertEquals(1, skill.getLevel());
  }

  @Test
  public void testSkillLevelCannotExceedMax() {
    RoleSkill skill =
        new RoleSkill(mockAttribute, skillName, "A skill for a players role", mockPlayer);

    for (int i = 0; i < SpecializedSkill.MAX_LEVEL; i++) {
      skill.increaseLevel();
    }

    assertEquals(10, skill.getLevel());
  }

  @Test
  public void testSkillLevelEqualsOneIfIncreasedTwiceThenDecreasedOnce() {
    RoleSkill skill =
        new RoleSkill(mockAttribute, skillName, "A skill for a players role", mockPlayer);

    skill.increaseLevel();
    skill.increaseLevel();
    skill.decreaseLevel();

    assertEquals(1, skill.getLevel());

  }

  @Test
  public void testSkillLevelCannotBeLowerThanMinLevel() {
    RoleSkill skill =
        new RoleSkill(mockAttribute, skillName, "A skill for a players role", mockPlayer);

    skill.decreaseLevel();

    assertEquals(0, skill.getLevel());
  }

  @Test
  public void testImprovementPointsEqualsTwoIfIncreasedByTwo() {
    RoleSkill skill =
        new RoleSkill(mockAttribute, skillName, "A skill for a players role", mockPlayer);

    skill.increaseCurrentImprovementPoints(2);

    assertEquals(2, skill.getCurrentImprovementPoints());
  }

  @Test
  public void testSkillLevelEqualsOneIfNeededImprovementPointsIsReached() {
    RoleSkill skill =
        new RoleSkill(mockAttribute, skillName, "A skill for a players role", mockPlayer);

    skill.increaseCurrentImprovementPoints(skill.getNeededImprovementPoints());

    assertEquals(1, skill.getLevel());
  }

  @Test
  public void testSkillTotalLevelEqualsThreeIfIncreasedLevelOnce() {
    RoleSkill skill =
        new RoleSkill(mockAttribute, skillName, "A skill for a players role", mockPlayer);

    skill.increaseLevel();

    assertEquals(3, skill.getTotalValue());
  }

  @Test
  public void testSkillTotalLevelEqualsTwoIfSkillLevelEqualsZero() {
    RoleSkill skill =
        new RoleSkill(mockAttribute, skillName, "A skill for a players role", mockPlayer);

    assertEquals(2, skill.getTotalValue());
  }

  @Test
  public void testSkillTotalLevelEqualsThreeIfSkillLevelIncreasedOnce() {
    RoleSkill skill =
        new RoleSkill(mockAttribute, skillName, "A skill for a players role", mockPlayer);

    skill.increaseLevel();

    assertEquals(3, skill.getTotalValue());
  }

  @Test
  public void testSkillLevelEqualsZeroIfSkillLevelIncreasedOnceThenReset() {
    RoleSkill skill =
        new RoleSkill(mockAttribute, skillName, "A skill for a players role", mockPlayer);

    skill.increaseLevel();
    skill.resetLevel();

    assertEquals(0, skill.getLevel());
  }
}
