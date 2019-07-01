package rpg.util;

import java.io.Serializable;
import java.util.Objects;

/**
 * A value object that wraps 2 <code>int</code> values -- representing die count and face count
 * respectively -- and an optional int value -- representing the dividend.
 */
public class Die implements Serializable {
  private static final int minDieCount = 0;
  private static final int minFaceCount = 1;
  private static final int defaultDividend = 1;
  private static final long serialVersionUID = 1L;

  private int dieCount;
  private int faceCount;
  private int dividend;

  /**
   * Constructs a pool of die with the same face amount. Sets the dividend to
   * {@link #defaultDividend}.
   * 
   * @param dieCount the amount of dice used in a roll
   * @param faceCount the amount of sides on the rolled dice
   * @see #Die(int, int, int)
   */
  public Die(int dieCount, int faceCount) {
    this(dieCount, faceCount, defaultDividend);
  }

  /**
   * Constructs a pool of die with the same face amount that is then divided by a given amount after
   * rolling.
   * 
   * @param dieCount the amount of dice used in a roll
   * @param faceCount the amount of sides on the rolled dice
   * @param dividend the number to dived the result of the dice roll
   */
  public Die(int dieCount, int faceCount, int dividend) {
    setDieCount(dieCount);
    setFaceCount(faceCount);
    setDividend(dividend);
  }

  private void setDieCount(int dieCount) {
    if (dieCount < minDieCount) {
      throw new IllegalArgumentException(
          "die count = " + dieCount + "; min = " + minDieCount);
    } else {
      this.dieCount = dieCount;
    }
  }

  private void setFaceCount(int faceCount) {
    if (faceCount < minFaceCount) {
      throw new IllegalArgumentException(
          "face count = " + faceCount + "; min = " + minFaceCount);
    } else {
      this.faceCount = faceCount;
    }
  }

  private void setDividend(int dividend) {
    if (dividend < defaultDividend) {
      throw new IllegalArgumentException(
          "dividend = " + dividend + "; min = " + defaultDividend);
    } else {
      this.dividend = dividend;
    }
  }

  /**
   * Returns the amount of die used in a roll.
   * 
   * @return the die amount for a roll
   */
  public int getDieCount() {
    return dieCount;
  }

  /**
   * Returns the amount of sides of the die involved in a roll.
   * 
   * @return the amount of sides for the die in a roll
   */
  public int getFaceCount() {
    return faceCount;
  }

  /**
   * Returns the value to divide the roll results by.
   * 
   * @return the value to divide the roll results by
   */
  public int getDividend() {
    return dividend;
  }


  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (o == null) {
      return false;
    }

    if (!(o instanceof Die)) {
      return false;
    }

    Die die = (Die) o;
    return getDieCount() == die.getDieCount() //
        && getFaceCount() == die.getFaceCount() //
        && getDividend() == die.getDividend();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getDieCount(), getFaceCount(), getDividend());
  }

  @Override
  public String toString() {
    if (dieCount == minDieCount) {
      return String.valueOf(minDieCount);
    } else if (dividend == defaultDividend) {
      return dieCount + "D" + faceCount;
    } else {
      return dieCount + "D" + faceCount + "/" + dividend;
    }
  }

}
