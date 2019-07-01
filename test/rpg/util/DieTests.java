package rpg.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class DieTests {
  private int dieCount;
  private int faceCount;
  private int dividend;

  @Before
  public void setUp() {
    dieCount = 1;
    faceCount = 2;
    dividend = 2;
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_DieCountIsLessThanZero() {
    @SuppressWarnings("unused")
    Die die = new Die(-1, faceCount, dividend);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_FaceCountIsLessThanOne() {
    @SuppressWarnings("unused")
    Die die = new Die(dieCount, 0, dividend);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_DividendIsLessThanOne() {
    @SuppressWarnings("unused")
    Die die = new Die(dieCount, faceCount, 0);
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_DieIsComparedToItself() {
    Die die = new Die(dieCount, faceCount);

    assertTrue(die.equals(die));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_DieIsComparedToNull() {
    Die die = new Die(dieCount, faceCount);

    assertFalse(die.equals(null));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_DieIsNotComparedToAnotherInstanceOfDie() {
    Die die = new Die(dieCount, faceCount);

    assertFalse(die.equals(new Object()));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparingTwoDie_While_BothDieHaveDifferentDieCount() {
    Die die1 = new Die(1, faceCount);
    Die die2 = new Die(2, faceCount);

    assertFalse(die1.equals(die2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparingTwoDie_While_BothDieHaveDifferentFaceCount() {
    Die die1 = new Die(dieCount, 2);
    Die die2 = new Die(dieCount, 3);

    assertFalse(die1.equals(die2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparingTwoDie_While_BothDieHaveDifferentDivedends() {
    Die die1 = new Die(dieCount, faceCount, 1);
    Die die2 = new Die(dieCount, faceCount, 2);

    assertFalse(die1.equals(die2));
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_ComparingTwoDie_While_BothDieHaveTheSameState() {
    Die die1 = new Die(dieCount, faceCount);
    Die die2 = new Die(dieCount, faceCount);

    assertTrue(die1.equals(die2));
  }

}
