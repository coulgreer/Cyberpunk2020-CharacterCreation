package rpg.cyberpunk._2020.stats;

import java.util.ArrayList;
import java.util.List;

import rpg.cyberpunk._2020.AttributeName;
import rpg.general.stats.Attribute;
import rpg.general.stats.Levelable;
import rpg.util.Observable;
import rpg.util.Observer;

public class DerivedAttribute extends Attribute implements Observer {
	private AttributeName name;
	private String description;
	private int value;
	private StatisticCalculator calculator;
	private List<Observer> observers = new ArrayList<Observer>();

	public DerivedAttribute(AttributeName name, String description, Observable parentStatistic,
			StatisticCalculator calculator) {
		this.name = name;
		this.description = description;
		this.calculator = calculator;
		parentStatistic.registerObserver(this);
		parentStatistic.notifyObserver();
	}

	public void update(Levelable parentStatistic) {
		calculator.setStatistic(parentStatistic);
		value = (int) calculator.calculate();
		notifyObserver();
	}

	public void increaseLevel() {
		throw new UnsupportedOperationException();
	}

	public void decreaseLevel() {
		throw new UnsupportedOperationException();
	}

	public void resetLevel() {
		throw new UnsupportedOperationException();
	}

	public AttributeName getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getLevel() {
		return value;
	}

	public int getModifier() {
		return value;
	}

	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	public void unregisterObserver(Observer observer) {
		observers.remove(observer);
	}

	public void notifyObserver() {
		for (Observer observer : observers) {
			observer.update(this);
		}
	}
}
