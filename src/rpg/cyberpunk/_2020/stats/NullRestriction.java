package rpg.cyberpunk._2020.stats;

import rpg.general.stats.Restriction;

public class NullRestriction implements Restriction {
	private static NullRestriction uniqueInstance;

	private NullRestriction() {
	}

	public static NullRestriction getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NullRestriction();
		}

		return uniqueInstance;
	}

	@Override
	public boolean isRestricted() {
		return false;
	}

}
