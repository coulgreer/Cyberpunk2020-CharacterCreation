package rpg.cyberpunk._2020.stats;

import rpg.general.stats.AbstractAttribute;
import rpg.general.stats.Levelable;

public class RoleCyberpunkSkill extends CyberpunkSkill implements Enablable {
	public static final int DEFAULT_DIFFICULTY_MODIFIER = 1;

	private String name;
	private String description;
	private int baseValue;
	private int difficultyModifier;
	private int currentImprovementPoints;
	private int neededImprovementPoints;
	private int totalValue;
	private boolean isEnabled;

	public RoleCyberpunkSkill(AbstractAttribute attribute, String name, String description) {
		this.name = name;
		this.description = description;
		baseValue = INITIAL_VALUE;
		totalValue = baseValue + attribute.getModifier();
		difficultyModifier = DEFAULT_DIFFICULTY_MODIFIER;
		currentImprovementPoints = INITIAL_IP;
		neededImprovementPoints = INITIAL_IP_GOAL;
		isEnabled = false;
		attribute.registerObserver(this);
	}

	public RoleCyberpunkSkill(String name, String description) {
		this.name = name;
		this.description = description;
		baseValue = INITIAL_VALUE;
		totalValue = baseValue;
		difficultyModifier = DEFAULT_DIFFICULTY_MODIFIER;
		currentImprovementPoints = INITIAL_IP;
		neededImprovementPoints = INITIAL_IP_GOAL;
		isEnabled = false;
	}

	public String getDescription() {
		return description;
	}

	// TODO Look for a way to update total value when the baseValue changes instead
	// of hardcoding a solution.
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

	public String getName() {
		return name;
	}

	public int getLevel() {
		return baseValue;
	}

	public int getTotalValue() {
		if (baseValue > 0) {
			return totalValue;
		} else {
			return 0;
		}
	}

	public void update(Levelable statistic) {
		if (statistic instanceof CyberpunkAttribute) {
			int modifier = ((CyberpunkAttribute) statistic).getModifier();
			totalValue = baseValue + modifier;
		}
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
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

	@Override
	public CyberpunkSkill accept(SkillVisitor visitor) {
		return visitor.visit(this);
	}
}
