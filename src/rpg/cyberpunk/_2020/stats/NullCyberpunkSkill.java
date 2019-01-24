package rpg.cyberpunk._2020.stats;

import rpg.general.stats.Levelable;
import rpg.util.Observer;

public class NullCyberpunkSkill implements CyberpunkSkill {
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

	@Override
	public CyberpunkSkill accept(SkillVisitor visitor) {
		return this;
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

	public void registerObserver(Observer observer) {
	}

	public void unregisterObserver(Observer observer) {
	}

	public void notifyObserver() {
	}

	@Override
	public void update(Levelable statistic) {
	}

	@Override
	public void increaseCurrentImprovementPoints(int improvementPoints) {
	}

	@Override
	public int getCurrentImprovementPoints() {
		return 0;
	}

	@Override
	public int getNeededImprovementPoints() {
		return 0;
	}

	@Override
	public String getName() {
		return CyberpunkSkill.NONE;
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public int getTotalValue() {
		return 0;
	}
}
