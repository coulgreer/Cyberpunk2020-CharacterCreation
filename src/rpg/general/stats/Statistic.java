package rpg.general.stats;

/**
 * An interface that has a name, description, and the ability to be incremented and decremented in
 * order to manipulate a single held value with an associated meaning.
 */
public interface Statistic {

  /**
   * @return the name of this statistic.
   */
  public String getName();

  /**
   * @return a blurb giving a basic idea of what this statistic represents.
   */
  public String getDescription();

  /**
   * @return the current level.
   */
  public int getLevel();

  /**
   * Increments current level.
   */
  public void incrementLevel();

  /**
   * Decrements current level.
   */
  public void decrementLevel();

  /**
   * Resets level to starting value.
   */
  public void resetLevel();

}
