package rpg.general.stats;

/**
 * A restriction that can be applied to another object. For example, a skill
 * needs to be at level 5, so we can add this to another object to disable it
 * until its dependent meets the necessary requirements.
 * 
 * @author Coul Greer
 */
public interface Restriction {
	/**
	 * Returns whether or not the prerequisites to interact with a
	 * <code>Skill</code> are met or not.
	 * 
	 * @return <code>true</code> if the requirements have not been met
	 */
	public boolean isRestricted();
}
