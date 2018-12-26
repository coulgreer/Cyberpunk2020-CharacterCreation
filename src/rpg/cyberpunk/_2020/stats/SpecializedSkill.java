package rpg.cyberpunk._2020.stats;

import rpg.general.stats.AbstractAttribute;
import rpg.general.stats.Levelable;

public class SpecializedSkill extends CyberpunkSkill {
	private String name;
	private String description;
	private int difficultyModifier;
	private int currentImprovementPoints;
	private int neededImprovementPoints;
	private int baseValue;
	private int totalValue;

	public SpecializedSkill(AbstractAttribute attribute, String name, String description, int difficultyModifier) {
		this.name = name;
		this.description = description;
		this.difficultyModifier = difficultyModifier;
		currentImprovementPoints = INITIAL_IP;
		neededImprovementPoints = INITIAL_IP_GOAL;
		baseValue = INITIAL_VALUE;
		totalValue = baseValue + attribute.getModifier();
		attribute.registerObserver(this);
	}

	public void increaseLevel() {
		if (baseValue < MAX_LEVEL) {
			baseValue++;
			totalValue++;
		}
	}

	public void decreaseLevel() {
		if (baseValue > MIN_LEVEL) {
			baseValue--;
			totalValue--;
		}
	}

	public void resetLevel() {
		baseValue = MIN_LEVEL;
	}

	public int getLevel() {
		return baseValue;
	}

	public int getTotalValue() {
		if (baseValue > MIN_LEVEL) {
			return totalValue;
		} else {
			return MIN_LEVEL;
		}
	}

	public void update(Levelable statistic) {
		if (statistic instanceof CyberpunkAttribute) {
			int modifier = ((CyberpunkAttribute) statistic).getModifier();
			totalValue = baseValue + modifier;
		}
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void increaseCurrentImprovementPoints(int improvementPoints) {
		this.currentImprovementPoints += improvementPoints;
		checkForLevelUp();
	}

	private void checkForLevelUp() {
		if (currentImprovementPoints >= neededImprovementPoints) {
			currentImprovementPoints -= neededImprovementPoints;
			increaseLevel();
			calculateImprovementPointGoal();
		}
	}

	private void calculateImprovementPointGoal() {
		// TODO Create a object for calculation
		neededImprovementPoints = 10 * baseValue * difficultyModifier;
	}

	public int getCurrentImprovementPoints() {
		return currentImprovementPoints;
	}

	public int getNeededImprovementPoints() {
		return neededImprovementPoints;
	}

	public CyberpunkSkill accept(SkillVisitor visitor) {
		return visitor.visit(this);
	}
}
