package rpg.util;

public class NullProbability extends Probability {
	private static NullProbability uniqueInstance;

	private NullProbability() {
		super(NullDie.getInstance(), 0);
	}

	public static NullProbability getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NullProbability();
		}

		return uniqueInstance;
	}
}
