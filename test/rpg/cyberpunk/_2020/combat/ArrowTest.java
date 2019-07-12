package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import org.junit.Before;
import org.junit.Test;
import rpg.cyberpunk._2020.combat.Arrow.Tip;

public class ArrowTest {
  private Tip mockTip;

  @Before
  public void setUp() {
    mockTip = mock(Tip.class);
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_TipIsNull() {
    Arrow arrow = new Arrow(null);
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_ComparedToItself() {
    Arrow arrow = new Arrow(mockTip);

    assertTrue(arrow.equals(arrow));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparedToNull() {
    Arrow arrow = new Arrow(mockTip);

    assertFalse(arrow.equals(null));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparedToAnObjectThatIsNotAnInstanceOfArrow() {
    Arrow arrow = new Arrow(mockTip);

    assertFalse(arrow.equals(new Object()));
  }
  
  @Test
  public void Should_ReturnEqualsAsTrue_When_ComparingTwoArrowsWithTheSameState() {
    Arrow arrow1 = new Arrow(mockTip);
    Arrow arrow2 = new Arrow(mockTip);
    
    assertTrue(arrow1.equals(arrow2));
  }
  
  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparingTwoArrowsWithInequivalentTips() {
    Arrow arrow1 = new Arrow(mock(Tip.class));
    Arrow arrow2 = new Arrow(mock(Tip.class));
    
    assertFalse(arrow1.equals(arrow2));
  }

}
