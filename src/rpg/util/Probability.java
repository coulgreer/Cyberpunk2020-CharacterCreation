package rpg.util;

import java.io.Serializable;

/**
 * An object holding the die notation and a modifier to get the range of values
 * possible with the given die and flat modifier.
 * 
 * @author Coul Greer
 */
public class Probability implements Comparable<Probability>, Serializable {
	/**
	 * The neutral modifier for the die roll associated with the Probability.
	 */
	public static final int DEFAULT_MODIFIER = 0;

	private static final long serialVersionUID = 1L;

	private Die die;
	private int modifier;

	/**
	 * Constructs a default probability with the {@link Die#MIN_NUMBER_OF_DICE} and
	 * a modifier of {@link #DEFAULT_MODIFIER}.
	 * 
	 * @see #Probability(Die, int)
	 */
	public Probability() {
		this(new Die(Die.MIN_NUMBER_OF_DICE, Die.MIN_NUMBER_OF_FACES), DEFAULT_MODIFIER);
	}

	/**
	 * Constructs an object that has a die notation data structure and a flat
	 * modifier.
	 * 
	 * @param die      the amount of sides and the amount of objects with those
	 *                 sides that represent a set of probable values
	 * @param modifier the flat value to attach to the result of the die roll
	 */
	public Probability(Die die, int modifier) {
		setDice(die);
		this.modifier = modifier;
	}

	/**
	 * Returns the die data structure used to get the probable values from the die
	 * roll.
	 * 
	 * @return the die notation data structure used to get the range of possible
	 *         values
	 */
	public Die getDice() {
		return die;
	}

	/**
	 * Replaces the old die data structure with the new given data structure.
	 * 
	 * @param die the new data structure to use
	 */
	public void setDice(Die die) {
		if (die == null) {
			throw new NullPointerException();
		} else {
			this.die = die;
		}
	}

	/**
	 * Returns the flat value used to change the results from the die notation.
	 * 
	 * @return the flat value used to change the die notation's results
	 */
	public int getModifier() {
		return modifier;
	}

	/**
	 * Replaces the old value used to modify the results from the die notation.
	 * 
	 * @param modifier the new flat value to be used to change the die notation
	 *                 results
	 */
	public void setModifier(int modifier) {
		this.modifier = modifier;
	}

	@Override
	public String toString() {
		if (modifier == DEFAULT_MODIFIER) {
			return die.toString();
		} else {
			return die.toString() + "+" + modifier;
		}
	}

	@Override
	public int compareTo(Probability probability) {
		int thisProbabilityValue = die.getDieCount() * die.getFaceCount() / die.getDividend() + modifier;
		int thatProbabilityValue = probability.getDice().getDieCount() * probability.getDice().getFaceCount()
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
