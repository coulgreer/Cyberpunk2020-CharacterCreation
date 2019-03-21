package rpg.cyberpunk._2020.combat;

import java.io.Serializable;

public class Durability implements Serializable {
	public static final int MIN_DURABILITY = 0;

	private int maxDurability;
	private int currentDurability;

	public Durability() {
		this(MIN_DURABILITY);
	}

	public Durability(int maxDurability) {
		this.maxDurability = maxDurability;
		this.currentDurability = maxDurability;
	}

	public int getCurrentDurability() {
		return currentDurability;
	}

	public int getMaxDurability() {
		return maxDurability;
	}

	public void setCurrentDurability(int currentDurability) {
		this.currentDurability = currentDurability;
	}

	public void setMaxDurability(int maxDurability) {
		this.maxDurability = maxDurability;
	}
}
