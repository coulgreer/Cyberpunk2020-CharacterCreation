package rpg.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import org.junit.Before;
import org.junit.Test;

public class ProbabilityTests {
  private Die mockDie;
  private int modifier;

  @Before
  public void setUp() {
    mockDie = mock(Die.class);
    modifier = 0;
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_DieIsNull() {
    @SuppressWarnings("unused")
    Probability probability = new Probability(null, modifier);
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_ComparedToItself() {
    Probability probability = new Probability(mockDie, modifier);

    assertTrue(probability.equals(probability));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparedToNull() {
    Probability probability = new Probability(mockDie, modifier);

    assertFalse(probability.equals(null));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparingTwoProbabilities_While_NotComparedToAnInstanceOfProbability() {
    Probability probability = new Probability(mockDie, modifier);

    assertFalse(probability.equals(new Object()));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparingTwoProbabilities_While_ProbabilityHasDifferentDice() {
    Probability probability1 = new Probability(mock(Die.class), modifier);
    Probability probability2 = new Probability(mock(Die.class), modifier);

    assertFalse(probability1.equals(probability2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparingTwoProbabilities_While_ProbabilityHasDifferentModifier() {
    Probability probability1 = new Probability(mockDie, 0);
    Probability probability2 = new Probability(mockDie, 1);

    assertFalse(probability1.equals(probability2));
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_ComparingTwoProbabilities_While_ProbabilityHasSameState() {
    Probability probability1 = new Probability(mockDie, modifier);
    Probability probability2 = new Probability(mockDie, modifier);

    assertTrue(probability1.equals(probability2));
  }

}
