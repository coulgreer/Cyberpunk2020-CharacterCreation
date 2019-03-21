package rpg.general.stats;

public interface SkillRestriction {
	/**
	 * Returns whether or not the prerequisites to interact with a
	 * <code>Skill</code> are met or not.
	 * 
	 * @return <code>true</code> if the requirements have not been met
	 */
	public boolean isRestricted();
}
