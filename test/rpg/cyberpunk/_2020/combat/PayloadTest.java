package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import org.junit.Before;
import org.junit.Test;
import rpg.util.Name;
import rpg.util.Probability;

public class PayloadTest {

  private Name mockName;
  private String effects;
  private Probability mockDamage;
  private double cost;

  @Before
  public void setUp() {
    mockName = mock(Name.class);
    effects = "Effects";
    mockDamage = mock(Probability.class);
    cost = 0.0;
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_NameIsNull() {
    Payload load = new Payload(null, effects, mockDamage, cost);
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_EffectsAreNull() {
    Payload load = new Payload(mockName, null, mockDamage, cost);
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_DamageIsNull() {
    Payload load = new Payload(mockName, effects, null, cost);
  }

  @SuppressWarnings("unused")
  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_CostIsNegativeOneTenth() {
    Payload load = new Payload(mockName, effects, mockDamage, -0.1);
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparedToNull() {
    Payload load = new Payload(mockName, effects, mockDamage, cost);

    assertFalse(load.equals(null));
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_ComparedToItself() {
    Payload load = new Payload(mockName, effects, mockDamage, cost);

    assertTrue(load.equals(load));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_NotComparedToInstanceOfPayload() {
    Payload load = new Payload(mockName, effects, mockDamage, cost);

    assertFalse(load.equals(new Object()));
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_TwoPayloadsShareState() {
    Payload load1 = new Payload(mockName, effects, mockDamage, cost);
    Payload load2 = new Payload(mockName, effects, mockDamage, cost);

    assertTrue(load1.equals(load2));
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_TwoPayloadsHaveInequivalentNames() {
    Payload load1 = new Payload(mock(Name.class), effects, mockDamage, cost);
    Payload load2 = new Payload(mock(Name.class), effects, mockDamage, cost);

    assertFalse(load1.equals(load2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_TwoPayloadsHaveInequivalentEffects() {
    Payload load1 = new Payload(mockName, "Effect A", mockDamage, cost);
    Payload load2 = new Payload(mockName, "Effect B", mockDamage, cost);

    assertFalse(load1.equals(load2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_TwoPayloadsHaveInequivalentDamage() {
    Payload load1 = new Payload(mockName, effects, mock(Probability.class), cost);
    Payload load2 = new Payload(mockName, effects, mock(Probability.class), cost);

    assertFalse(load1.equals(load2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_TwoPayloadsHaveInequivalentCost() {
    Payload load1 = new Payload(mockName, effects, mockDamage, 0.0);
    Payload load2 = new Payload(mockName, effects, mockDamage, 1.0);

    assertFalse(load1.equals(load2));
  }

}
