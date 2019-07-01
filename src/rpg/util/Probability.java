package rpg.util;

import java.io.Serializable;
import java.util.Objects;

/**
 * A value object that wraps a <code>Die</code> and an <code>int</code> that then gives the range of
 * values that can be rolled.
 */
public class Probability implements Comparable<Probability>, Serializable {
  private static final int defaultModifier = 0;
  private static final long serialVersionUID = 1L;

  private Die die;
  private int modifier;

  /**
   * Constructs an object that has a die notation data structure and a flat modifier.
   * 
   * @param die the amount of sides and the amount of objects with those sides that represent a set
   *        of probable values
   * @param modifier the flat value to attach to the result of the die roll
   */
  public Probability(Die die, int modifier) {
    setDice(die);
    setModifier(modifier);
  }

  private void setDice(Die die) {
    if (die == null) {
      throw new NullPointerException();
    } else {
      this.die = die;
    }
  }

  private void setModifier(int modifier) {
    this.modifier = modifier;
  }

  /**
   * Returns the die data structure used to get the probable values from the die roll.
   * 
   * @return the die notation data structure used to get the range of possible values
   */
  public Die getDice() {
    return die;
  }

  /**
   * Returns the flat value used to change the results from the die notation.
   * 
   * @return the flat value used to change the die notation's results
   */
  public int getModifier() {
    return modifier;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (o == null) {
      return false;
    }

    if (!(o instanceof Probability)) {
      return false;
    }

    Probability probability = (Probability) o;
    return getDice().equals(probability.getDice()) && getModifier() == probability.getModifier();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getDice(), getModifier());
  }

  @Override
  public String toString() {
    if (modifier == defaultModifier) {
      return die.toString();
    } else {
      return die.toString() + "+" + modifier;
    }
  }

  @Override
  public int compareTo(Probability probability) {
    int thisProbabilityValue =
        die.getDieCount() * die.getFaceCount() / die.getDividend() + modifier;
    int thatProbabilityValue =
        probability.getDice().getDieCount() * probability.getDice().getFaceCount()
            / probability.getDice().getDividend() + probability.getModifier();
    int result = 0;

    if (thisProbabilityValue < thatProbabilityValue) {
      result = -1;
    } else if (thisProbabilityValue == thatProbabilityValue) {
      result = 0;
    } else if (thisProbabilityValue > thatProbabilityValue) {
      result = 1;
    }

    return result;
  }

}
