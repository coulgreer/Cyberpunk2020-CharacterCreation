package rpg.cyberpunk._2020.combat;

import java.io.Serializable;

/**
 * A JavaBean designed to represent the damage an item can take while keeping
 * track of its max value and its current value.
 * 
 * @author Coul Greer
 */
public class Durability implements Serializable {
	/**
	 * The lowest value for durability to be at.
	 */
	public static final int MIN_DURABILITY = 0;

	private int maxDurability;
	private int currentDurability;

	/**
	 * Constructs a durability object that has a max durability using
	 * {@link #MIN_DURABILITY}
	 */
	public Durability() {
		this(MIN_DURABILITY);
	}

	/**
	 * Constructs a durability with the highest value allowed being
	 * <code>maxDurability</code>, and initializes the current durability to
	 * maxDurability as well.
	 * 
	 * @param maxDurability the highest value for durability to be at
	 */
	public Durability(int maxDurability) {
		this.maxDurability = maxDurability;
		this.currentDurability = maxDurability;
	}

	/**
	 * Returns the durability at the time of call.
	 * 
	 * @return the durability at the time of call
	 * @see #setCurrentDurability(int)
	 */
	public int getCurrentDurability() {
		return currentDurability;
	}

	/**
	 * Returns the highest value that durability can be at.
	 * 
	 * @return the highest value that durability can be at
	 * @see #setMaxDurability(int)
	 */
	public int getMaxDurability() {
		return maxDurability;
	}

	/**
	 * Replaces the value of the currentDurability with the new value.
	 * 
	 * @param currentDurability the value to replace the old value with
	 * @see #getCurrentDurability()
	 */
	public void setCurrentDurability(int currentDurability) {
		this.currentDurability = currentDurability;
	}

	/**
	 * Replaces the value of the maxDurability with the new given maximum value.
	 * 
	 * @param maxDurability the value to replace the old maximum value with
	 * @see #getMaxDurability()
	 */
	public void setMaxDurability(int maxDurability) {
		this.maxDurability = maxDurability;
	}
}
