package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import rpg.general.stats.Attribute;
import rpg.general.stats.Restriction;

public class SpecializedSkillTest {
  private static Attribute mockAttribute;
  private String name;
  private String description;
  private Restriction mockRestriction;

  @Before
  public void setUp() {
    mockAttribute = mock(Attribute.class);
    when(mockAttribute.getModifier()).thenReturn(2);
    name = "Name";
    description = "Description";
    mockRestriction = mock(Restriction.class);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_ParentAttributeIsNull() {
    @SuppressWarnings("unused")
    SpecializedSkill skill = //
        new SpecializedSkill.Builder(name, description) //
            .withParent(null) //
            .build();
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_NameIsNull() {
    @SuppressWarnings("unused")
    SpecializedSkill skill = //
        new SpecializedSkill.Builder(null, description) //
            .build();
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_DescriptionIsNull() {
    @SuppressWarnings("unused")
    SpecializedSkill skill = //
        new SpecializedSkill.Builder(name, null) //
            .build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_DifficultyModifierIsLessThanOrEqualToZero() {
    @SuppressWarnings("unused")
    SpecializedSkill skill = //
        new SpecializedSkill.Builder(name, description) //
            .withDifficultyOf(0) //
            .build();
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_RestrictionIsNull() {
    @SuppressWarnings("unused")
    SpecializedSkill skill = //
        new SpecializedSkill.Builder(name, description) //
            .withRestriction(null) //
            .build();
  }

  @Test
  public void Should_ReturnLevelAsZeroAndTotalValueAsTwo_When_ParentAttributeModifierIsTwo() {
    when(mockAttribute.getModifier()).thenReturn(2);
    SpecializedSkill skill = //
        new SpecializedSkill.Builder(name, description)//
            .withParent(mockAttribute) //
            .build();

    assertEquals(0, skill.getLevel());
    assertEquals(2, skill.getTotalValue());
  }

  @Test
  public void Should_ReturnLevelAsOneAndTotalValueAsThree_When_LevelIncrementedOnceAndParentAttributeModifierIsTwo() {
    when(mockAttribute.getModifier()).thenReturn(2);
    SpecializedSkill skill = //
        new SpecializedSkill.Builder(name, description)//
            .withParent(mockAttribute) //
            .build();

    skill.incrementLevel();

    assertEquals(1, skill.getLevel());
    assertEquals(3, skill.getTotalValue());
  }

  @Test
  public void Should_ReturnLevelAsOne_When_LevelIsIncrementedOnce_While_LevelIsLessThanTenAndSkillIsNotRestricted() {
    SpecializedSkill skill = new SpecializedSkill.Builder(name, description).build();

    skill.incrementLevel();

    assertEquals(1, skill.getLevel());
  }

  @Test
  public void Should_ReturnLevelAsTen_When_LevelIsIncrementedOnce_While_LevelIsAtLeastTenAndSkillIsNotRestricted() {
    SpecializedSkill skill = new SpecializedSkill.Builder(name, description).build();
    while (skill.getLevel() < 10) {
      skill.incrementLevel();
    }

    skill.incrementLevel();

    assertEquals(10, skill.getLevel());
  }

  @Test
  public void Should_ReturnLevelAsZero_When_LevelIsIncrementedOnce_While_LevelIsLessThanTenAndSkillIsRestricted() {
    SpecializedSkill skill = //
        new SpecializedSkill.Builder(name, description) //
            .withRestriction(mockRestriction) //
            .build();
    when(mockRestriction.isRestricted()).thenReturn(true);

    skill.incrementLevel();

    assertEquals(0, skill.getLevel());
  }

  @Test
  public void Should_ReturnLevelAsZeroAndTotalValueAsTwo_When_LevelDecrementedOnceAndParentAttributeModifierIsTwo() {
    SpecializedSkill skill = //
        new SpecializedSkill.Builder(name, description)//
            .withParent(mockAttribute) //
            .build();
    when(mockAttribute.getModifier()).thenReturn(2);

    skill.decrementLevel();

    assertEquals(0, skill.getLevel());
    assertEquals(2, skill.getTotalValue());
  }

  @Test
  public void Should_ReturnLevelAsZero_When_LevelIsDecrementedOnce_While_LevelIsOneAndSkillIsNotRestricted() {
    SpecializedSkill skill = new SpecializedSkill.Builder(name, description).build();
    skill.incrementLevel();

    skill.decrementLevel();

    assertEquals(0, skill.getLevel());
  }

  @Test
  public void Should_ReturnLevelAsZero_When_LevelIsDecrementedOnce_While_LevelIsLessThanOneAndSkillIsNotRestricted() {
    SpecializedSkill skill = new SpecializedSkill.Builder(name, description).build();

    skill.decrementLevel();

    assertEquals(0, skill.getLevel());
  }

  @Test
  public void Should_ReturnLevelAsOne_When_LevelIsDecrementedOnce_While_LevelIsOneAndSkillIsRestricted() {
    SpecializedSkill skill = //
        new SpecializedSkill.Builder(name, description)//
            .withParent(mockAttribute) //
            .withRestriction(mockRestriction) //
            .build();
    when(mockRestriction.isRestricted()).thenReturn(false);
    skill.incrementLevel();
    when(mockRestriction.isRestricted()).thenReturn(true);

    skill.decrementLevel();

    assertEquals(1, skill.getLevel());
  }

  @Test
  public void Should_ReturnLevelAsZeroAndTotalValueAsTwo_WhenLevelIsResetAndParentAttributeModifierIsTwo() {
    when(mockAttribute.getModifier()).thenReturn(2);
    SpecializedSkill skill = //
        new SpecializedSkill.Builder(name, description)//
            .withParent(mockAttribute) //
            .build();

    skill.resetLevel();

    assertEquals(0, skill.getLevel());
    assertEquals(2, skill.getTotalValue());
  }

  @Test
  public void Should_ReturnLevelAsZero_WhenLevelIsResetFromANonZeroLevel_While_SkillIsNotRestricted() {
    SpecializedSkill skill = new SpecializedSkill.Builder(name, description).build();
    skill.incrementLevel();

    skill.resetLevel();

    assertEquals(0, skill.getLevel());
  }

  @Test
  public void Should_ReturnLevelAsZero_When_LevelIsResetFromANonZeroLevel_While_SkillIsRestricted() {
    SpecializedSkill skill = //
        new SpecializedSkill.Builder(name, description) //
            .withRestriction(mockRestriction) //
            .build();
    when(mockRestriction.isRestricted()).thenReturn(false);
    skill.incrementLevel();
    when(mockRestriction.isRestricted()).thenReturn(true);

    skill.resetLevel();

    assertEquals(1, skill.getLevel());
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_ImproventPointsAreIncreasedByNegativeOne() {
    SpecializedSkill skill = new SpecializedSkill.Builder(name, description).build();

    skill.increaseCurrentImprovementPoints(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_ImproventPointsAreIncreasedByZero() {
    SpecializedSkill skill = new SpecializedSkill.Builder(name, description).build();

    skill.increaseCurrentImprovementPoints(0);
  }

  @Test
  public void Should_ReturnImprovementPointsAsOne_When_ImprovementPointsAreIncreasedByOne_While_SkillIsNotRestricted() {
    SpecializedSkill skill = new SpecializedSkill.Builder(name, description).build();

    skill.increaseCurrentImprovementPoints(1);

    assertEquals(1, skill.getCurrentImprovementPoints());
  }

  @Test
  public void Should_ReturnImprovementPointsAsZero_When_ImprovementPointsAreIncreasedByOne_While_SkillIsRestricted() {
    SpecializedSkill skill = //
        new SpecializedSkill.Builder(name, description) //
            .withRestriction(mockRestriction) //
            .build();

    when(mockRestriction.isRestricted()).thenReturn(true);
    skill.increaseCurrentImprovementPoints(1);

    assertEquals(0, skill.getCurrentImprovementPoints());
  }

  @Test
  public void Should_ReturnLevelAsTwo_When_ImprovementPointsAreIncreasedByNeededImprovementPoints() {
    SpecializedSkill skill = new SpecializedSkill.Builder(name, description).build();

    int neededPoints = skill.getTargetImprovementPoints() - skill.getCurrentImprovementPoints();
    skill.increaseCurrentImprovementPoints(neededPoints);

    assertEquals(2, skill.getLevel());
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_ImproventPointsAreDecreasedByNegativeOne() {
    SpecializedSkill skill = new SpecializedSkill.Builder(name, description).build();

    skill.decreaseCurrentImprovementPoints(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_ImproventPointsAreDecreasedByZero() {
    SpecializedSkill skill = new SpecializedSkill.Builder(name, description).build();

    skill.decreaseCurrentImprovementPoints(0);
  }

  @Test
  public void Should_ReturnImprovementPointsAsZero_When_ImproventPointsAreDecreasedByOne_While_ImprovementPointsAreOneAndSkillIsNotRestricted() {
    SpecializedSkill skill = new SpecializedSkill.Builder(name, description).build();

    skill.increaseCurrentImprovementPoints(1);
    skill.decreaseCurrentImprovementPoints(1);

    assertEquals(0, skill.getCurrentImprovementPoints());
  }

  @Test
  public void Should_ReturnImprovementPointsAsOne_When_ImproventPointsAreDecreasedByOne_While_ImprovementPointsAreOneAndSkillIsRestricted() {
    SpecializedSkill skill = //
        new SpecializedSkill.Builder(name, description) //
            .withRestriction(mockRestriction) //
            .build();
    when(mockRestriction.isRestricted()).thenReturn(false);
    skill.increaseCurrentImprovementPoints(1);
    when(mockRestriction.isRestricted()).thenReturn(true);

    skill.decreaseCurrentImprovementPoints(1);

    assertEquals(1, skill.getCurrentImprovementPoints());
  }

  @Test
  public void Should_ReturnImprovementPointsAsZero_When_ImprovementPointsAreDecreasedByThree_While_ImprovementPointsAreTwoAndSkillIsNotRestricted() {
    SpecializedSkill skill = new SpecializedSkill.Builder(name, description).build();
    skill.increaseCurrentImprovementPoints(2);

    skill.decreaseCurrentImprovementPoints(3);

    assertEquals(0, skill.getCurrentImprovementPoints());
  }

  @Test
  public void Should_ReturnLevelAsZero_When_DecreasedByCurrentImprovementPoints_While_ImprovementPointsAreIncreasedByNeededImprovementPointsTwice() {
    SpecializedSkill skill = new SpecializedSkill.Builder(name, description).build();
    int neededPoints = skill.getTargetImprovementPoints() - skill.getCurrentImprovementPoints();
    skill.increaseCurrentImprovementPoints(neededPoints);
    neededPoints = skill.getTargetImprovementPoints() - skill.getCurrentImprovementPoints();
    skill.increaseCurrentImprovementPoints(neededPoints);

    skill.decreaseCurrentImprovementPoints(skill.getCurrentImprovementPoints());

    assertEquals(0, skill.getLevel());
  }

  @Test
  public void Should_ReturnLevelAsTen_When_ImprovementPointsAreIncreasedBy100ThenOne_While_DifficultyModifierIsOne() {
    SpecializedSkill skill = new SpecializedSkill.Builder(name, description).build();
    int maxLevelPoints = (10 * (CyberpunkSkill.MAX_LEVEL - 1)) + 1;

    skill.increaseCurrentImprovementPoints(maxLevelPoints);

    assertEquals(10, skill.getLevel());
  }

  @Test
  public void Should_ReturnTargetPointsAsTwenty_When_LevelIsOneAndDifficultyModifierIsTwo() {
    SpecializedSkill skill = //
        new SpecializedSkill.Builder(name, description) //
            .withDifficultyOf(2) //
            .build();

    skill.incrementLevel();

    assertEquals(20, skill.getTargetImprovementPoints());
  }

}
