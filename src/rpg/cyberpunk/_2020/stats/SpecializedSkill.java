package rpg.cyberpunk._2020.stats;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import rpg.general.stats.Attribute;
import rpg.general.stats.SkillRestriction;

public class SpecializedSkill implements CyberpunkSkill {
	public static final int MINIMUM_DIFFICULTY_MODIFIER = 1;

	private Attribute attribute;
	private String name;
	private String description;
	private int baseValue;
	private int totalValue;
	private int difficultyModifier;
	private int currentImprovementPoints;
	private int neededImprovementPoints;
	private SkillRestriction restriction;
	private PropertyChangeSupport changeSupport;

	public SpecializedSkill(Attribute attribute, String name, String description, int difficultyModifier) {
		this(attribute, name, description, difficultyModifier, NullSkillRestriction.getInstance());
	}

	public SpecializedSkill(Attribute attribute, String name, String description, int difficultyModifier,
			SkillRestriction restriction) {
		setName(name);
		setDescription(description);
		setRestriction(restriction);
		baseValue = MIN_LEVEL;
		setDifficultyModifier(difficultyModifier);
		currentImprovementPoints = INITIAL_IP;
		neededImprovementPoints = INITIAL_IP_GOAL;
		initializeParent(attribute);
		changeSupport = new PropertyChangeSupport(this);
	}

	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("The field 'name' is not allowed to be null.");
		} else {
			this.name = name;
		}
	}

	private void setDescription(String description) {
		if (description == null) {
			throw new IllegalArgumentException("The field 'description' is not allowed to be null.");
		} else {
			this.description = description;
		}
	}

	private void setRestriction(SkillRestriction restriction) {
		if (restriction == null) {
			throw new IllegalArgumentException("The field 'restriction' is not allowed to be null.");
		} else {
			this.restriction = restriction;
		}
	}

	private void setDifficultyModifier(int difficultyModifier) {
		if (difficultyModifier < MINIMUM_DIFFICULTY_MODIFIER) {
			throw new IllegalArgumentException(
					"The field 'difficultyModifier' must be a value greater than " + MINIMUM_DIFFICULTY_MODIFIER + ".");
		} else {
			this.difficultyModifier = difficultyModifier;
		}
	}

	private void initializeParent(Attribute attribute) {
		if (attribute == null) {
			throw new IllegalArgumentException("The field 'attribute' is not allowed to be null.");
		} else {
			this.attribute = attribute;
			attribute.addPropertyChangeListener(Attribute.PROPERTY_NAME_LEVEL, this);
			calculateTotalValue();
		}
	}

	private void calculateTotalValue() {
		totalValue = baseValue + attribute.getModifier();
	}

	@Override
	public void increaseLevel() {
		if (baseValue < MAX_LEVEL && isEnabled()) {
			int oldValue = baseValue;
			baseValue++;
			calculateTotalValue();
			changeSupport.firePropertyChange(PROPERTY_NAME_LEVEL, oldValue, baseValue);
		}
	}

	@Override
	public void decreaseLevel() {
		if (baseValue > MIN_LEVEL && isEnabled()) {
			int oldValue = baseValue;
			baseValue--;
			calculateTotalValue();
			changeSupport.firePropertyChange(PROPERTY_NAME_LEVEL, oldValue, baseValue);
		}
	}

	@Override
	public void resetLevel() {
		if (isEnabled()) {
			int oldValue = baseValue;
			baseValue = MIN_LEVEL;
			calculateTotalValue();
			changeSupport.firePropertyChange(PROPERTY_NAME_LEVEL, oldValue, baseValue);
		}
	}

	@Override
	public int getLevel() {
		return baseValue;
	}

	@Override
	public int getTotalValue() {
		return totalValue;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void increaseCurrentImprovementPoints(int improvementPoints) {
		if (isEnabled()) {
			int oldValue = currentImprovementPoints;
			currentImprovementPoints += improvementPoints;
			checkForLevelUp();
			changeSupport.firePropertyChange(PROPERTY_NAME_IMPROVEMENT_POINTS, oldValue, currentImprovementPoints);
		}
	}

	private void checkForLevelUp() {
		if (currentImprovementPoints >= neededImprovementPoints) {
			currentImprovementPoints -= neededImprovementPoints;
			increaseLevel();
			calculateImprovementPointGoal();
		}
	}

	private void calculateImprovementPointGoal() {
		neededImprovementPoints = 10 * baseValue * difficultyModifier;
	}

	@Override
	public int getCurrentImprovementPoints() {
		return currentImprovementPoints;
	}

	@Override
	public int getNeededImprovementPoints() {
		return neededImprovementPoints;
	}

	@Override
	public CyberpunkSkill accept(SkillVisitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (attribute == evt.getSource()) {
			calculateTotalValue();
		}
	}

	@Override
	public boolean isEnabled() {
		return !restriction.isRestricted();
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	@Override
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}

	@Override
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}
}
