package rpg.cyberpunk._2020.stats;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rpg.cyberpunk._2020.AttributeName;
import rpg.general.stats.AbstractAttribute;
import rpg.util.Observer;

public class CyberpunkAttribute extends AbstractAttribute {
	protected static final int MIN_LEVEL = 2;
	protected static final int MAX_LEVEL = 10;

	private AttributeName name;
	private String description;
	private int level;
	private List<Observer> observers;

	public CyberpunkAttribute(AttributeName name, String description) {
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

	public AttributeName getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	
	public int getModifier() {
		return level;
	}

}
