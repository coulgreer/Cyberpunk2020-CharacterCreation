package rpg.cyberpunk._2020.stats;

import rpg.general.stats.Attribute;
import rpg.util.Observer;

public class NullCyberpunkAttribute implements Attribute {
	private static NullCyberpunkAttribute uniqueInstance;

	private NullCyberpunkAttribute() {
	}

	public static NullCyberpunkAttribute getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NullCyberpunkAttribute();
		}

		return uniqueInstance;
	}

	@Override
	public void increaseLevel() {
	}

	@Override
	public void decreaseLevel() {
	}

	@Override
	public void resetLevel() {
	}

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public void registerObserver(Observer observer) {
	}

	@Override
	public void unregisterObserver(Observer observer) {
	}

	@Override
	public void notifyObserver() {
	}

	@Override
	public String getName() {
		return CyberpunkAttribute.NOT_AVAILABLE;
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public int getModifier() {
		return 0;
	}

}
