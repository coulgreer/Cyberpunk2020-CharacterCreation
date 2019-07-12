package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import rpg.cyberpunk._2020.combat.Cartridge.Caliber;
import rpg.general.combat.Ammunition.Type;
import rpg.util.Probability;

public class CaliberTest {
  private Type mockType;
  private Probability mockDamage;
  private double cost;

  @Before
  public void setUp() {
    mockType = mock(Type.class);
    mockDamage = mock(Probability.class);
    cost = 0.0;
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_WhenNameIsNull() {
    Caliber caliber = new Caliber(null, mockDamage, cost);
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_WhenDamageIsNull() {
    Caliber caliber = new Caliber(mockType, null, cost);
  }

  @SuppressWarnings("unused")
  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_WhenCostIsNegativeOneTenth() {
    Caliber caliber = new Caliber(mockType, mockDamage, -0.1);
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_ComparedToItself() {
    Caliber caliber = new Caliber(mockType, mockDamage, cost);

    assertTrue(caliber.equals(caliber));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparedToNull() {
    Caliber caliber = new Caliber(mockType, mockDamage, cost);

    assertFalse(caliber.equals(null));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_NotComparedToAnInstanceOfCaliber() {
    Caliber caliber = new Caliber(mockType, mockDamage, cost);

    assertFalse(caliber.equals(new Object()));
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_TwoCalibersShareTheSameState() {
    when(mockType.getName()).thenReturn("Name");
    Caliber caliber1 = new Caliber(mockType, mockDamage, cost);
    Caliber caliber2 = new Caliber(mockType, mockDamage, cost);

    assertTrue(caliber1.equals(caliber2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_TwoCalibersHaveInequivalentNames() {
    Type mockName1 = mock(Type.class);
    when(mockName1.getName()).thenReturn("Name 1");
    Type mockName2 = mock(Type.class);
    when(mockName2.getName()).thenReturn("Name 2");
    Caliber caliber1 = new Caliber(mockName1, mockDamage, cost);
    Caliber caliber2 = new Caliber(mockName2, mockDamage, cost);

    assertFalse(caliber1.equals(caliber2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_TwoCalibersHaveInequivalentDamage() {
    when(mockType.getName()).thenReturn("Name");
    Caliber caliber1 = new Caliber(mockType, mock(Probability.class), cost);
    Caliber caliber2 = new Caliber(mockType, mock(Probability.class), cost);

    assertFalse(caliber1.equals(caliber2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_TwoCalibersHaveInequivalentCost() {
    when(mockType.getName()).thenReturn("Name");
    Caliber caliber1 = new Caliber(mockType, mockDamage, 0.0);
    Caliber caliber2 = new Caliber(mockType, mockDamage, 0.1);

    assertFalse(caliber1.equals(caliber2));
  }

}
