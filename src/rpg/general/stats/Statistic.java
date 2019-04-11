package rpg.general.stats;

/**
 * An interface that has a name, description, and the ability to be incremented
 * and decremented in order to manipulate a single held value with an associated
 * meaning.
 * 
 * @author cagreer
 *
 */
public interface Statistic {

	/**
	 * Returns a String that represents this statistic's name.
	 * 
	 * @return the name of this statistic.
	 */
	public String getName();

	/**
	 * Returns a String that represents this statistic's description.
	 * 
	 * @return the description of this statistic.
	 */
	public String getDescription();

	/**
	 * Returns the current level.
	 * 
	 * @return the current level.
	 */
	public int getLevel();

	/**
	 * Increments current level.
	 */
	public void increaseLevel();

	/**
	 * Decrements current level.
	 */
	public void decreaseLevel();

	/**
	 * Resets level to starting value.
	 */
	public void resetLevel();

}
