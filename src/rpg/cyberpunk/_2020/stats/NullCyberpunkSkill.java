package rpg.cyberpunk._2020.stats;

import rpg.general.stats.Levelable;
import rpg.util.Observer;

public class NullCyberpunkSkill extends CyberpunkSkill {
	private static NullCyberpunkSkill uniqueInstance;

	private NullCyberpunkSkill() {
		uniqueInstance = null;
	}

	public static NullCyberpunkSkill getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NullCyberpunkSkill();
		}
		return uniqueInstance;
	}

	public CyberpunkSkill accept(SkillVisitor visitor) {
		return this;
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

	public void update(Levelable statistic) {
	}

	public void increaseCurrentImprovementPoints(int improvementPoints) {
	}

	public int getCurrentImprovementPoints() {
		return 0;
	}

	public int getNeededImprovementPoints() {
		return 0;
	}

	public String getName() {
		return CyberpunkSkill.NONE;
	}

	public String getDescription() {
		return "";
	}

	public int getTotalValue() {
		return 0;
	}
}
