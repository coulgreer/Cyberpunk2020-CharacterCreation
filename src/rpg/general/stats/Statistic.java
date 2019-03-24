package rpg.general.stats;

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
