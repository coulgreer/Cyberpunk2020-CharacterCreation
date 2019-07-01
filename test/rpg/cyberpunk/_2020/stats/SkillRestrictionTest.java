package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;

public class SkillRestrictionTest {
  private CyberpunkSkill mockSkill;
  private int minLevel;

  @Before
  public void setUp() {
    mockSkill = mock(CyberpunkSkill.class);
    minLevel = 4;
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_SkillIsNull() {
    @SuppressWarnings("unused")
    SkillRestriction restriction = new SkillRestriction(null, minLevel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_MinimumLevelIsNegativeOne() {
    @SuppressWarnings("unused")
    SkillRestriction restriction = new SkillRestriction(mockSkill, -1);
  }

  @Test
  public void Should_ReturnRestrictedAsTrue_When_LevelIsLessThanMinimumLevel() {
    SkillRestriction restriction = new SkillRestriction(mockSkill, minLevel);

    when(mockSkill.getLevel()).thenReturn(minLevel - 1);

    assertTrue(restriction.isRestricted());
  }

  @Test
  public void Should_ReturnRestrictedAsFalse_When_LevelIsGreaterThanOrEqualToMinimumLevel() {
    SkillRestriction restriction = new SkillRestriction(mockSkill, minLevel);

    when(mockSkill.getLevel()).thenReturn(minLevel);

    assertFalse(restriction.isRestricted());
  }

}
