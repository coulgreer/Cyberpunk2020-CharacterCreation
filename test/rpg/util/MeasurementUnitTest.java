package rpg.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import org.junit.Before;
import org.junit.Test;

public class MeasurementUnitTest {
  private Measurement.Type type;
  private Name mockName;
  private double conversionFactor;

  @Before
  public void setUp() {
    type = Measurement.Type.LENGTH;
    mockName = mock(Name.class);
    conversionFactor = 1.0;
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Given_TypeIsNull_When_ConstructingANewInstance_Then_AnExceptionIsThrown() {
    Measurement.Unit u = new Measurement.Unit(null, mockName, conversionFactor);
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Given_NameIsNull_When_ConstructingANewInstance_Then_AnExceptionIsThrown() {
    Measurement.Unit u = new Measurement.Unit(type, null, conversionFactor);
  }

  @SuppressWarnings("unused")
  @Test(expected = IllegalArgumentException.class)
  public void Given_ConversionFactorIsNegativeOneTenth_When_ConstructingANewInstance_Then_AnExceptionIsThrown() {
    Measurement.Unit u = new Measurement.Unit(type, mockName, -0.1);
  }

  @SuppressWarnings("unused")
  @Test(expected = IllegalArgumentException.class)
  public void Given_ConversionFactorIsZero_When_ConstructingANewInstance_Then_AnExceptionIsThrown() {
    Measurement.Unit u = new Measurement.Unit(type, mockName, 0.0);
  }

  @Test
  public void Given_ConversionFactorIsOneTenth_When_ConstructingANewInstance_Then_ConversionFactorIsOneTenth() {
    Measurement.Unit u = new Measurement.Unit(type, mockName, 0.1);

    assertEquals(0.1, u.getConversionFactor(), 0.0);
  }

  @Test
  public void Given_OtherInstanceIsSelf_When_AskingIfEquals_Then_ReturnsTrue() {
    Measurement.Unit u = new Measurement.Unit(type, mockName, conversionFactor);

    assertTrue(u.equals(u));
  }

  @Test
  public void Given_OtherInstanceIsNull_When_AskingIfEquals_Then_ReturnsFalse() {
    Measurement.Unit u = new Measurement.Unit(type, mockName, conversionFactor);

    assertFalse(u.equals(null));
  }

  @Test
  public void Given_OtherInstanceIsNotAnInstanceOfMeasurement_When_AskingIfEquals_Then_ReturnsFalse() {
    Measurement.Unit u = new Measurement.Unit(type, mockName, conversionFactor);

    assertFalse(u.equals(new Object()));
  }

  @Test
  public void Given_OtherInstanceHasInequivalentType_When_AskingIfEquals_Then_ReturnsFalse() {
    Measurement.Unit u1 = new Measurement.Unit(Measurement.Type.LENGTH, mockName, conversionFactor);
    Measurement.Unit u2 = new Measurement.Unit(Measurement.Type.MASS, mockName, conversionFactor);

    assertFalse(u1.equals(u2));
  }

  @Test
  public void Given_OtherInstanceHasInequivalentName_When_AskingIfEquals_Then_ReturnsFalse() {
    Measurement.Unit u1 = new Measurement.Unit(type, new Name("1"), conversionFactor);
    Measurement.Unit u2 = new Measurement.Unit(type, new Name("2"), conversionFactor);

    assertFalse(u1.equals(u2));
  }

  @Test
  public void Given_OtherInstanceHasInequivalentConversionFactor_When_AskingIfEquals_Then_ReturnsFalse() {
    Measurement.Unit u1 = new Measurement.Unit(type, mockName, 0.1);
    Measurement.Unit u2 = new Measurement.Unit(type, mockName, 1.0);

    assertFalse(u1.equals(u2));
  }

  @Test
  public void Given_OtherInstanceHasEquivalentTypeAndHasEquivealentNameAndHasEquivalentConversionType_When_AskingIfEquals_Then_ReturnsTrue() {
    Measurement.Unit u1 = new Measurement.Unit(type, mockName, conversionFactor);
    Measurement.Unit u2 = new Measurement.Unit(type, mockName, conversionFactor);

    assertTrue(u1.equals(u2));
  }

}
