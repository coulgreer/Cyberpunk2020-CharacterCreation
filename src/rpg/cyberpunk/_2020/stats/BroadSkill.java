package rpg.cyberpunk._2020.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rpg.general.stats.Levelable;
import rpg.util.Observable;
import rpg.util.Observer;

// TODO Take in a display container and display the values once the get methods are called.
public class BroadSkill extends CyberpunkSkill implements Observable {
	private Map<String, CyberpunkSkill> skills;
	private String name;
	private String description;
	private List<Observer> observers;

	public BroadSkill(String name, String description) {
		skills = new HashMap<String, CyberpunkSkill>();
		this.name = name;
		this.description = description;
		observers = new ArrayList<Observer>();
	}

	public String getDescription() {
		return description;
	}

	public int getTotalValue() {
		int totalValue = 0;
		Iterator<Map.Entry<String, CyberpunkSkill>> iterator = getIterator();
		while (iterator.hasNext()) {
			CyberpunkSkill skill = iterator.next().getValue();
			totalValue += skill.getTotalValue();
		}
		return totalValue;
	}

	public void increaseLevel() {
		Iterator<Map.Entry<String, CyberpunkSkill>> iterator = getIterator();
		while (iterator.hasNext()) {
			CyberpunkSkill skill = iterator.next().getValue();
			skill.increaseLevel();
		}
	}

	public void decreaseLevel() {
		Iterator<Map.Entry<String, CyberpunkSkill>> iterator = getIterator();
		while (iterator.hasNext()) {
			CyberpunkSkill skill = iterator.next().getValue();
			skill.decreaseLevel();
		}
	}

	public void resetLevel() {
		Iterator<Map.Entry<String, CyberpunkSkill>> iterator = getIterator();
		while (iterator.hasNext()) {
			CyberpunkSkill skill = iterator.next().getValue();
			skill.resetLevel();
		}
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		throw new UnsupportedOperationException();
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

	public void update(Levelable statistic) {
		throw new UnsupportedOperationException();
	}

	public void increaseCurrentImprovementPoints(int improvementPoints) {
		Iterator<Map.Entry<String, CyberpunkSkill>> iterator = getIterator();
		while (iterator.hasNext()) {
			CyberpunkSkill skill = iterator.next().getValue();
			skill.increaseCurrentImprovementPoints(improvementPoints);
		}
	}

	public int getCurrentImprovementPoints() {
		throw new UnsupportedOperationException();
	}

	public int getNeededImprovementPoints() {
		throw new UnsupportedOperationException();
	}

	public CyberpunkSkill accept(SkillVisitor visitor) {
		return visitor.visit(this);
	}

	public void add(CyberpunkSkill skill) {
		skills.put(skill.getName(), skill);
	}

	public void remove(CyberpunkSkill skill) {
		skills.remove(skill.getName());
	}

	public CyberpunkSkill getChild(String skillName) {
		SkillVisitor visitor = new GetSkillVisitor(skillName);
		Iterator<Map.Entry<String, CyberpunkSkill>> iterator = getIterator();
		while (iterator.hasNext()) {
			Map.Entry<String, CyberpunkSkill> child = iterator.next();
			CyberpunkSkill childValue = child.getValue().accept(visitor);
			if (!(childValue.equals(NullCyberpunkSkill.getInstance()))) {
				return childValue;
			}
		}
		return NullCyberpunkSkill.getInstance();
	}

	public Iterator<Map.Entry<String, CyberpunkSkill>> getIterator() {
		return skills.entrySet().iterator();
	}
}
