package rpg.cyberpunk._2020.stats;

import rpg.cyberpunk._2020.AttributeName;
import rpg.general.stats.AbstractAttribute;
import rpg.util.Observer;

public class NullCyberpunkAttribute extends AbstractAttribute {
	private static NullCyberpunkAttribute uniqueInstance;

	private NullCyberpunkAttribute() {
	}

	public static NullCyberpunkAttribute getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NullCyberpunkAttribute();
		}

		return uniqueInstance;
	}

	public void increaseLevel() {
	}

	public void decreaseLevel() {
	}

	public void resetLevel() {
	}

	public int getLevel() {
		return 0;
	}

	public void registerObserver(Observer observer) {
	}

	public void unregisterObserver(Observer observer) {
	}

	public void notifyObserver() {
	}

	public AttributeName getName() {
		return AttributeName.NOT_AVAILABLE;
	}

	public String getDescription() {
		return "";
	}

	public int getModifier() {
		return 0;
	}

}
