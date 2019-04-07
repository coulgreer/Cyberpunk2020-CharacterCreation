package rpg.util;

import java.io.Serializable;

/**
 * Holds the data for dice notation used in many RPGs. The data being number of
 * faces and the pool of chance.
 * 
 * @author Coul Greer
 */
public class Die implements Serializable {
	protected static final int MIN_NUMBER_OF_DICE = 0;
	protected static final int MIN_NUMBER_OF_FACES = 1;
	protected static final int MIN_DIVIDEND = 1;

	private int numberOfDie;
	private int numberOfFaces;
	private int dividend;

	/**
	 * Constructs a pool of die with the same face amount.
	 * 
	 * @param numberOfDie   the amount of dice used in a roll
	 * @param numberOfFaces the amount of sides on the rolled dice
	 * @see rpg.util.Die#Die(int, int, int)
	 */
	public Die(int numberOfDie, int numberOfFaces) {
		this(numberOfDie, numberOfFaces, MIN_DIVIDEND);
	}

	/**
	 * Constructs a pool of die with the same face amount that is then divided by a
	 * given amount after rolling.
	 * 
	 * @param numberOfDie   the amount of dice used in a roll
	 * @param numberOfFaces the amount of sides on the rolled dice
	 * @param dividend      the number to dived the result of the dice roll
	 */
	public Die(int numberOfDie, int numberOfFaces, int dividend) {
		if (numberOfDie < MIN_NUMBER_OF_DICE) {
			this.numberOfDie = MIN_NUMBER_OF_DICE;
		} else {
			this.numberOfDie = numberOfDie;
		}

		if (numberOfFaces < MIN_NUMBER_OF_FACES) {
			this.numberOfFaces = MIN_NUMBER_OF_FACES;
		} else {
			this.numberOfFaces = numberOfFaces;
		}

		if (dividend > MIN_DIVIDEND) {
			this.dividend = dividend;
		} else {
			this.dividend = MIN_DIVIDEND;
		}
	}

	/**
	 * Returns the amount of die used in a roll.
	 * 
	 * @return the die amount for a roll
	 */
	public int getNumberOfDie() {
		return numberOfDie;
	}

	/**
	 * Returns the amount of sides of the die involved in a single roll.
	 * 
	 * @return the amount of sides for the die in a roll
	 */
	public int getNumberOfFaces() {
		return numberOfFaces;
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
	public String toString() {
		if (dividend > MIN_DIVIDEND) {
			return numberOfDie + "D" + numberOfFaces + "/" + dividend;
		} else {
			return numberOfDie + "D" + numberOfFaces;
		}
	}

}
