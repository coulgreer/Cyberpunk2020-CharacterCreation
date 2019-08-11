package rpg.util;

import java.io.Serializable;
import java.util.Objects;

/**
 * A value object used to keep track of different types of measurements such as: Length and Mass.
 * Also, keeps track of the measurement's unit and the value amount when using the provided unit of
 * measurement.
 */
public class Measurement implements Serializable, Comparable<Measurement> {

  private static final long serialVersionUID = 1L;

  /**
   * The types of measurement used to help with calculations of unit/measurement to its respective
   * counterpart. For example, a Measurement that tracks length cannot be converted into a
   * Measurement that tracks mass.
   */
  public enum Type {
    LENGTH, //
    MASS, //
    UNKNOWN;
  }

  /**
   * A value object that tracks a unit belonging to a <code>Measurement.Type</code>. The unit
   * provides a name to display as well as a conversion factor used to get the unit to the base unit
   * so that the Measurement can be converted and used in calculations.
   */
  public static class Unit implements Serializable {
    public static final Unit METER = new Unit(Type.LENGTH, new Name("m"), 1.0);
    public static final Unit MILLIMETER = new Unit(Type.LENGTH, new Name("mm"), 0.001);
    public static final Unit KILOMETER = new Unit(Type.LENGTH, new Name("km"), 1000.0);

    public static final Unit KILOGRAM = new Unit(Type.MASS, new Name("kg"), 1.0);
    public static final Unit GRAM = new Unit(Type.MASS, new Name("g"), 1000.0);

    private static final double MIN_CONVERSION_FACTOR = 0.0;
    private static final long serialVersionUID = 1L;

    private Type type;
    private Name name;
    private double conversionFactor;

    /**
     * Constructs an instance of Unit to allow calculations and conversions between Measurements.
     * The conversionFactor must be greater than {@value #MIN_CONVERSION_FACTOR}.
     * 
     * @param type the category of measurement that this unit can be used for
     * @param name the identifier used to show what unit is being used
     * @param conversionFactor the multiplier used to get this unit to the base unit
     */
    public Unit(Type type, Name name, double conversionFactor) {
      setType(type);
      setName(name);
      setConversionFactor(conversionFactor);
    }

    private void setType(Type type) {
      if (type == null) {
        throw new NullPointerException();
      } else {
        this.type = type;
      }
    }

    private void setName(Name name) {
      if (name == null) {
        throw new NullPointerException();
      } else {
        this.name = name;
      }
    }

    private void setConversionFactor(double conversionFactor) {
      if (conversionFactor <= MIN_CONVERSION_FACTOR) {
        throw new IllegalArgumentException(
            "conversion factor = " + conversionFactor + "; min = " + MIN_CONVERSION_FACTOR);
      } else {
        this.conversionFactor = conversionFactor;
      }
    }

    /**
     * @return the category of measurement that this unit can be used for
     */
    public Type getType() {
      return type;
    }

    /**
     * @return the identifier used to show what unit is being used
     */
    public Name getName() {
      return name;
    }

    /**
     * @return the multiplier used to get this unit to the base unit
     */
    public double getConversionFactor() {
      return conversionFactor;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }

      if (o == null) {
        return false;
      }

      if (!(o instanceof Unit)) {
        return false;
      }

      Unit unit = (Unit) o;
      return this.getType().equals(unit.getType()) //
          && this.getName().equals(unit.getName()) //
          && this.getConversionFactor() == unit.getConversionFactor();
    }

    @Override
    public int hashCode() {
      return Objects.hash( //
          getType(), //
          getName(), //
          getConversionFactor());
    }

  }

  /**
   * Adds two measurements together using the first measurements units. Both measurements must be of
   * the same type.
   * 
   * @param m1 a measurement used to add to another measurement. Also, provides the unit to convert
   *        the result to.
   * @param m2 a measurement used to add to another measurement
   * @return the result of adding two values together
   */
  public static Measurement add(Measurement m1, Measurement m2) {
    if (m1 == null || m2 == null) {
      throw new NullPointerException();
    } else if (!(m1.getType().equals(m2.getType()))) {
      throw new IllegalArgumentException("Both measurements are not of the same type. Type 1: "
          + m1.getType() + "; Type 2: " + m2.getType());
    }

    m2 = m2.convertTo(m1.getUnit());

    return new Measurement(//
        m1.getType(), //
        m1.getAmount() + m2.getAmount(), //
        m1.getUnit());
  }

  /**
   * Subtracts two measurements using the first measurements units. Both measurements must be of the
   * same type.
   * 
   * @param m1 a measurement used to subtract to another measurement. Also, provides the unit to
   *        convert the result to.
   * @param m2 a measurement used to subtract to another measurement
   * @return the result of subtracting two values together
   */
  public static Measurement subtract(Measurement m1, Measurement m2) {
    if (m1 == null || m2 == null) {
      throw new NullPointerException();
    } else if (!(m1.getType().equals(m2.getType()))) {
      throw new IllegalArgumentException("Both measurements are not of the same type. Type 1: "
          + m1.getType() + "; Type 2: " + m2.getType());
    }

    m2 = m2.convertTo(m1.getUnit());

    return new Measurement( //
        m1.getType(), //
        m1.getAmount() - m2.getAmount(), //
        m1.getUnit());
  }

  private Type type;
  private double amount;
  private Unit unit;

  /**
   * Constructs an instance of Measurement. The amount must be at least {@value #MIN_AMOUNT}.
   * 
   * @param type the category of measurement
   * @param amount the numerical value of the measurement
   * @param unit the marker of what multiplier to use to get to the base unit
   */
  public Measurement(Type type, double amount, Unit unit) {
    setType(type);
    setAmount(amount);
    setUnit(unit);
  }

  private void setType(Type type) {
    if (type == null) {
      throw new NullPointerException();
    } else {
      this.type = type;
    }
  }

  private void setAmount(double amount) {
    this.amount = amount;
  }

  private void setUnit(Unit unit) {
    if (unit == null) {
      throw new NullPointerException();
    } else {
      this.unit = unit;
    }
  }

  /**
   * Converts this measurement to another unit. The provided Unit must be of the same type as this
   * Measurement.
   * 
   * @param unit the desired unit to convert this instance of Measurement to
   * @return a new instance of Measurement that uses the given unit
   */
  public Measurement convertTo(Unit unit) {
    if (unit == null) {
      throw new NullPointerException();
    } else if (!this.type.equals(unit.getType())) {
      throw new IllegalArgumentException("Measurement types do not match");
    } else {
      double amount = (this.amount / this.unit.getConversionFactor()) * unit.getConversionFactor();

      return new Measurement(type, amount, unit);
    }
  }

  /**
   * @return the category of measurement
   */
  public Type getType() {
    return type;
  }

  /**
   * @return the numerical value of the measurement
   */
  public double getAmount() {
    return amount;
  }

  /**
   * @return the marker of what multiplier to use to get to the base unit
   */
  public Unit getUnit() {
    return unit;
  }

  @Override
  public int compareTo(Measurement m) {
    if (getBaseAmount(this) < getBaseAmount(m)) {
      return -1;
    } else if (getBaseAmount(this) > getBaseAmount(m)) {
      return 1;
    } else {
      return 0;
    }
  }

  @Override
  public String toString() {
    return amount + unit.getName().getValue();
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (o == null) {
      return false;
    }

    if (!(o instanceof Measurement)) {
      return false;
    }

    Measurement measurement = (Measurement) o;
    return this.getType() == measurement.getType() //
        && hasEquivalentAmount(this, measurement);
  }

  private boolean hasEquivalentAmount(Measurement m1, Measurement m2) {
    double amount1 = getBaseAmount(m1);
    double amount2 = getBaseAmount(m2);

    return amount1 == amount2;
  }

  private double getBaseAmount(Measurement measurement) {
    Unit unit = measurement.getUnit();
    return measurement.getAmount() * unit.getConversionFactor();
  }

  @Override
  public int hashCode() {
    return Objects.hash( //
        getType(), //
        getBaseAmount(this));
  }

}
