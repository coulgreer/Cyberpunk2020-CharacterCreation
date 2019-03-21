package rpg.general.stats;

public interface StatisticManager<T extends Statistic> {

	/**
	 * Puts the given skill into this manager.
	 * 
	 * @param statistic the element to put in this manager
	 */
	public void add(T statistic);

	/**
	 * Deletes a statistic from this manager if it exists with the given name.
	 * 
	 * @param name the tag of the element to be removed
	 */
	public void remove(String name);

	/**
	 * Exposes a statistic that is contained in this manager
	 * 
	 * @param name the tag of the target statistic
	 * @return a statistic object
	 */
	public T getStatistic(String name);

	/**
	 * Returns a blurb that gives an idea what the returned statistic does/is.
	 * 
	 * @param name the tag of the target statistic
	 * @return a blurb representing the target statistic
	 */
	public String getDescription(String name);

	/**
	 * Returns the level that some data may be derived from.
	 * 
	 * @param name the tag of the target statistic
	 * @return the level in which stats can be derived from
	 */
	public int getBaseLevel(String name);

	/**
	 * Increment the current level of the statistic with the given name.
	 * 
	 * @param name the tag of the target statistic
	 */
	public void increaseLevel(String name);

	/**
	 * Decrement the current level of the statistic with the given name.
	 * 
	 * @param name the tag of the target statistic
	 */
	public void decreaseLevel(String name);

	/**
	 * Resets the current level of the statistic with the given name.
	 * 
	 * @param name the tag of the target statistic
	 */
	public void resetLevel(String name);

}
