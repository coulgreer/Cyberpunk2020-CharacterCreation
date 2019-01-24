package rpg.cyberpunk._2020.stats;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rpg.general.stats.Attribute;
import rpg.util.Observer;

public class CyberpunkAttribute implements Attribute {
	public static final String NOT_AVAILABLE = "Not Available";
	public static final String INTELLIGENCE = "Intelligence";
	public static final String REFLEXES = "Reflex";
	public static final String COOL = "Cool";
	public static final String TECHNICAL_ABILITY = "Technical Ability";
	public static final String LUCK = "Luck";
	public static final String ATTRACTIVENESS = "Attractiveness";
	public static final String MOVEMENT_ALLOWANCE = "Movement Allowance";
	public static final String RUN = "Run";
	public static final String LEAP = "Leap";
	public static final String EMPATHY = "Empathy";
	public static final String BODY_TYPE = "Body Type";
	public static final String CARRY = "Carry";

	public static final int MIN_LEVEL = 2;
	public static final int MAX_LEVEL = 10;

	private String name;
	private String description;
	private int level;
	private List<Observer> observers;

	public CyberpunkAttribute(String name, String description) {
		this.name = name;
		this.description = description;
		level = MIN_LEVEL;
		observers = new ArrayList<Observer>();
	}

	public void increaseLevel() {
		if (level < MAX_LEVEL) {
			level++;
			notifyObserver();
		}
	}

	public void decreaseLevel() {
		if (level > MIN_LEVEL) {
			level--;
			notifyObserver();
		}
	}

	public void resetLevel() {
		level = MIN_LEVEL;
		notifyObserver();
	}

	public int getLevel() {
		return level;
	}

	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	public void unregisterObserver(Observer observer) {
		observers.remove(observer);
	}

	public void notifyObserver() {
		Iterator<Observer> iterator = observers.iterator();
		while (iterator.hasNext()) {
			Observer observer = iterator.next();
			observer.update(this);
		}
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getModifier() {
		return level;
	}

}
