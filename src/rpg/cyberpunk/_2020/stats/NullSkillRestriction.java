package rpg.cyberpunk._2020.stats;

import rpg.general.stats.SkillRestriction;

public class NullSkillRestriction implements SkillRestriction {
	private static NullSkillRestriction uniqueInstance;

	private NullSkillRestriction() {
	}

	public static NullSkillRestriction getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NullSkillRestriction();
		}

		return uniqueInstance;
	}

	@Override
	public boolean isRestricted() {
		return false;
	}

}
