package rpg.util;

import java.io.Serializable;

/**
 * Holds the data for dice notation used in many RPGs. The data being number of
 * faces and the pool of chance.
 * 
 * @author Coul Greer
 */
public class Die implements Serializable {
	/**
	 * The minimum amount of dice allowed to be in a dice pool.
	 */
	public static final int MIN_NUMBER_OF_DICE = 0;

	/**
	 * The minimum amount of sides a die is allowed to have.
	 */
	public static final int MIN_NUMBER_OF_FACES = 1;

	/**
	 * The minimum amount that a pool of die is allowed to be divided by.
	 */
	public static final int DEFAULT_DIVIDEND = 1;

	private int numberOfDie;
	private int numberOfFaces;
	private int dividend;

	/**
	 * Constructs a pool of die with the same face amount. Sets the dividend to
	 * {@link #DEFAULT_DIVIDEND}.
	 * 
	 * @param numberOfDie   the amount of dice used in a roll
	 * @param numberOfFaces the amount of sides on the rolled dice
	 * @see #Die(int, int, int)
	 */
	public Die(int numberOfDie, int numberOfFaces) {
		this(numberOfDie, numberOfFaces, DEFAULT_DIVIDEND);
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
		setNumberOfDie(numberOfDie);
		setNumberOfFaces(numberOfFaces);
		setDividend(dividend);
	}

	private void setNumberOfDie(int numberOfDie) {
		if (numberOfDie < MIN_NUMBER_OF_DICE) {
			throw new IllegalArgumentException(
					"number of dice: " + numberOfDie + "; minimum die: " + MIN_NUMBER_OF_DICE);
		} else {
			this.numberOfDie = numberOfDie;
		}
	}

	private void setNumberOfFaces(int numberOfFaces) {
		if (numberOfFaces < MIN_NUMBER_OF_FACES) {
			throw new IllegalArgumentException(
					"number of faces: " + numberOfFaces + "; minimum faces: " + MIN_NUMBER_OF_FACES);
		} else {
			this.numberOfFaces = numberOfFaces;
		}
	}

	private void setDividend(int dividend) {
		if (dividend < DEFAULT_DIVIDEND) {
			throw new IllegalArgumentException("dividend: " + dividend + "; minimum dividend:" + DEFAULT_DIVIDEND);
		} else {
			this.dividend = dividend;
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
	 * Returns the amount of sides of the die involved in a roll.
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
		if (numberOfDie == MIN_NUMBER_OF_DICE) {
			return String.valueOf(MIN_NUMBER_OF_DICE);
		} else if (dividend == DEFAULT_DIVIDEND) {
			return numberOfDie + "D" + numberOfFaces;
		} else {
			return numberOfDie + "D" + numberOfFaces + "/" + dividend;
		}
	}

}
