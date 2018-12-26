package rpg.util;

public class Die implements Comparable<Die> {
	protected int MIN_NUMBER_OF_DICE = 0;
	protected int MIN_NUMBER_OF_FACES = 1;
	protected int MIN_DIVIDEND = 1;

	private int numberOfDie;
	private int numberOfFaces;
	private int dividend;

	public Die(int numberOfDie, int numberOfFaces) {
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

		dividend = MIN_DIVIDEND;
	}

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
			dividend = MIN_DIVIDEND;
		}
	}

	public int getNumberOfDie() {
		return numberOfDie;
	}

	public int getNumberOfFaces() {
		return numberOfFaces;
	}

	public String toString() {
		if (dividend > MIN_DIVIDEND) {
			return numberOfDie + "D" + numberOfFaces + "/" + dividend;
		} else {
			return numberOfDie + "D" + numberOfFaces;
		}
	}

	public int compareTo(Die o) {
		int die = numberOfFaces * numberOfDie;
		int anotherDie = o.getNumberOfFaces() * o.getNumberOfDie();
		int result = 0;

		if (die < anotherDie) {
			result = -1;
		} else if (die == anotherDie) {
			result = 0;
		} else if (die > anotherDie) {
			result = 1;
		}

		return result;
	}
}
