package rpg.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import rpg.util.Measurement.Unit;

public class MeasurementTest {
  private Measurement.Type type;
  private double amount;
  private Measurement.Unit mockUnit;

  @Before
  public void setUp() {
    type = Measurement.Type.UNKNOWN;
    amount = 2;
    mockUnit = mock(Measurement.Unit.class);
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Given_TypeIsNull_When_AnInstanceOfMeasurementIsCreated_Then_AnExceptionIsThrown() {
    Measurement m = new Measurement(null, amount, mockUnit);
  }

  @SuppressWarnings("unused")
  @Test(expected = IllegalArgumentException.class)
  public void Given_AmountIsNegativeOneTenth_When_AnInstanceOfMeasurementIsCreated_Then_AnExceptionIsThrown() {
    Measurement m = new Measurement(type, -0.1, mockUnit);
  }

  @Test
  public void Given_AmountIsZero_When_AnInstanceOfMeasurementIsCreated_Then_AmountIsZero() {
    Measurement m = new Measurement(type, 0.0, mockUnit);

    assertEquals(0.0, m.getAmount(), 0.0);
  }

  @Test
  public void Given_AmountIsOneTenth_When_AnInstanceOfMeasurementIsCreated_Then_AmountIsOneTenth() {
    Measurement m = new Measurement(type, 0.1, mockUnit);

    assertEquals(0.1, m.getAmount(), 0.0);
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Given_UnitIsNull_When_AnInstanceOfMeasurementIsCreated_Then_AnExceptionIsThrown() {
    Measurement m = new Measurement(type, amount, null);
  }

  @Test(expected = NullPointerException.class)
  public void Given_TheConvertedToUnitIsNull_When_ConvertingUnits_Then_AnExceptionIsThrown() {
    Measurement m = new Measurement(type, amount, mockUnit);

    m.convertTo(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Given_TheConvertedToUnitHasAnUnequivalentType_When_ConvertingUnits_Then_AnExceptionIsThrown() {
    Measurement.Type t1 = Measurement.Type.UNKNOWN;
    Measurement.Type t2 = Measurement.Type.LENGTH;
    Measurement m = new Measurement(t1, amount, mockUnit);
    Unit mockUnit2 = mock(Unit.class);
    when(mockUnit2.getType()).thenReturn(t2);

    m.convertTo(mockUnit2);
  }

  @Test
  public void Given_TheOriginalMeasurementHasAnAmountOfTwoAndTheOriginalUnitHasAConversionFactorOfOneAndTheConvertedToUnitHasAnEquivalentTypeAndHasAConversionFactorOfTen_When_ConvertingUnits_Then_TheReturnedMeasurementsAmountIsTwenty() {
    when(mockUnit.getType()).thenReturn(type);
    when(mockUnit.getConversionFactor()).thenReturn(1.0);
    Measurement measurement = new Measurement(type, 2, mockUnit);
    Name mockName = mock(Name.class);

    Measurement newMeasurement = measurement.convertTo(new Measurement.Unit(type, mockName, 10.0));
    assertEquals(20.0, newMeasurement.getAmount(), 0.0);
  }

  @Test
  public void Given_MeasurementHasLowerBaseAmountThanAnotherMeasurement_When_BeingCompared_Then_CompareToReturnsANegativeValue() {
    when(mockUnit.getType()).thenReturn(type);
    when(mockUnit.getConversionFactor()).thenReturn(1.0);
    Measurement m1 = new Measurement(type, 1, mockUnit);
    Measurement m2 = new Measurement(type, 2, mockUnit);

    assertTrue(m1.compareTo(m2) < 0);
  }

  @Test
  public void Given_MeasurementHasHigherBaseAmountThanAnotherMeasurement_When_BeingCompared_Then_CompareToReturnsAPositiveValue() {
    when(mockUnit.getType()).thenReturn(type);
    when(mockUnit.getConversionFactor()).thenReturn(1.0);
    Measurement m1 = new Measurement(type, 2, mockUnit);
    Measurement m2 = new Measurement(type, 1, mockUnit);

    assertTrue(m1.compareTo(m2) > 0);
  }

  @Test
  public void Given_MeasurementHasEqualBaseAmountToAnotherMeasurement_When_BeingCompared_Then_CompareToReturnsZero() {
    when(mockUnit.getType()).thenReturn(type);
    when(mockUnit.getConversionFactor()).thenReturn(1.0);
    Measurement m1 = new Measurement(type, 1, mockUnit);
    Measurement m2 = new Measurement(type, 1, mockUnit);

    assertTrue(m1.compareTo(m2) == 0);
  }

  @Test
  public void Given_OtherInstanceIsSelf_When_AskingIfEquals_Then_ReturnTrue() {
    Measurement m = new Measurement(type, amount, mockUnit);

    assertTrue(m.equals(m));
  }

  @Test
  public void Given_OtherInstanceIsNull_When_AskingIfEquals_Then_ReturnFalse() {
    Measurement m = new Measurement(type, amount, mockUnit);

    assertFalse(m.equals(null));
  }

  @Test
  public void Given_OtherInstanceIsNotAnInstanceOfMeasurement_When_AskingIfEquals_Then_ReturnFalse() {
    Measurement m = new Measurement(type, amount, mockUnit);

    assertFalse(m.equals(new Object()));
  }

  @Test
  public void Given_OtherInstanceIsNotOfEquivalentType_When_AskingIfEquals_Then_ReturnFalse() {
    Measurement m1 = new Measurement(Measurement.Type.LENGTH, amount, mockUnit);
    Measurement m2 = new Measurement(Measurement.Type.MASS, amount, mockUnit);

    assertFalse(m1.equals(m2));
  }

  @Test
  public void Given_OtherInstanceIsNotOfEquivalentBaseValue_When_AskingIfEquals_Then_ReturnFalse() {
    when(mockUnit.getConversionFactor()).thenReturn(1.0);
    Measurement m1 = new Measurement(type, 1, mockUnit);
    Measurement m2 = new Measurement(type, 2, mockUnit);

    assertFalse(m1.equals(m2));
  }

  @Test
  public void Given_OtherInstanceHasEquivalentBaseValueAndEquivalentType_When_AskingIfEquals_Then_ReturnTrue() {
    Measurement m1 = new Measurement(type, amount, mockUnit);
    Measurement m2 = new Measurement(type, amount, mockUnit);

    assertTrue(m1.equals(m2));
  }

}
