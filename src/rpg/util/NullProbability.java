package rpg.util;

/**
 * An instance of Probability that does nothing. Follows the Null Object
 * pattern.
 * 
 * @author Coul Greer
 */
public class NullProbability extends Probability {
	private static NullProbability uniqueInstance;

	/**
	 * Constructs a new NullProbability if one has not already been created. This is
	 * the only way to get an instance of this class, and this should represent that
	 * this will be the only instance of this class.
	 * 
	 * @return the one and only instance of NullProbability
	 */
	public static NullProbability getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NullProbability();
		}

		return uniqueInstance;
	}

	private NullProbability() {
		super(new Die(0, 0), 0);
	}

}
