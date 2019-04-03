package rpg.util;

public class NullProbability extends Probability {
	private static NullProbability uniqueInstance;

	private NullProbability() {
		super(new Die(0, 0), 0);
	}

	public static NullProbability getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NullProbability();
		}

		return uniqueInstance;
	}

}
