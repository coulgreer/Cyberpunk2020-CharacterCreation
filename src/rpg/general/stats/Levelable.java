package rpg.general.stats;

public interface Levelable {
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

	/**
	 * Returns the current level.
	 * 	
	 * @return the current level.
	 */
	public int getLevel();
}
