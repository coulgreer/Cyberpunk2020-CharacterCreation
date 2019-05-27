package rpg.cyberpunk._2020.stats;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import rpg.general.stats.Attribute;
import rpg.general.stats.Restriction;

/**
 * A generic skill to be represented. This skill may have restrictions and will always depend on an
 * attribute.
 */
public class SpecializedSkill implements CyberpunkSkill {
  private Attribute attribute;
  private String name;
  private String description;
  private int baseValue;
  private int totalValue;
  private int difficultyModifier;
  private int currentImprovementPoints;
  private int neededImprovementPoints;
  private Restriction restriction;
  private PropertyChangeSupport changeSupport;

  /**
   * Constructs a skill that depends on an attribute.
   * 
   * @param attribute the statistic used for calculating total skill level
   * @param name the identifier for this skill
   * @param description a blurb giving an overview of what this skill does/is
   * @param difficultyModifier a modifier used to multiply how many improvement points are needed to
   *        level
   * @see rpg.cyberpunk._2020.stats.SpecializedSkill#SpecializedSkill(Attribute, String, String,
   *      int, Restriction)
   */
  public SpecializedSkill(Attribute attribute, String name, String description,
      int difficultyModifier) {
    this(attribute, name, description, difficultyModifier, NullRestriction.getInstance());
  }

  /**
   * Constructs a skill that depends on an attribute and has a restriction.
   * 
   * @param attribute the statistic used for calculating total skill level
   * @param name the identifier for this skill
   * @param description a blurb giving an overview of what this skill does/is
   * @param difficultyModifier a modifier used to multiply how many improvement points are needed to
   *        level
   * @param restriction a constraint used to prevent modification of a skill
   */
  public SpecializedSkill(Attribute attribute, String name, String description,
      int difficultyModifier, Restriction restriction) {
    setName(name);
    setDescription(description);
    setRestriction(restriction);
    baseValue = MIN_LEVEL;
    setDifficultyModifier(difficultyModifier);
    currentImprovementPoints = INITIAL_IP;
    neededImprovementPoints = INITIAL_IP_GOAL;
    changeSupport = new PropertyChangeSupport(this);
    initializeParent(attribute);
  }

  private void setName(String name) {
    if (name == null) {
      throw new NullPointerException();
    } else {
      this.name = name;
    }
  }

  private void setDescription(String description) {
    if (description == null) {
      throw new NullPointerException();
    } else {
      this.description = description;
    }
  }

  private void setRestriction(Restriction restriction) {
    if (restriction == null) {
      throw new NullPointerException();
    } else {
      this.restriction = restriction;
    }
  }

  private void setDifficultyModifier(int difficultyModifier) {
    if (difficultyModifier < MINIMUM_DIFFICULTY_MODIFIER) {
      throw new IllegalArgumentException(
          "The field 'difficultyModifier' must be a value greater than "
              + MINIMUM_DIFFICULTY_MODIFIER + ".");
    } else {
      this.difficultyModifier = difficultyModifier;
    }
  }

  private void initializeParent(Attribute attribute) {
    if (attribute == null) {
      throw new NullPointerException();
    } else {
      this.attribute = attribute;
      attribute.addPropertyChangeListener(Attribute.PROPERTY_NAME_ATTRIBUTE_MODIFIER, this);
      calculateTotalValue();
    }
  }

  private void calculateTotalValue() {
    int oldValue = totalValue;

    totalValue = baseValue + attribute.getModifier();
    changeSupport.firePropertyChange(PROPERTY_NAME_SKILL_VALUE, oldValue, totalValue);
  }

  @Override
  public void increaseLevel() {
    if (baseValue < MAX_LEVEL && isEnabled()) {
      int oldValue = baseValue;
      baseValue++;
      calculateTotalValue();
      changeSupport.firePropertyChange(PROPERTY_NAME_SKILL_LEVEL, oldValue, baseValue);
    }
  }

  @Override
  public void decreaseLevel() {
    if (baseValue > MIN_LEVEL && isEnabled()) {
      int oldValue = baseValue;
      baseValue--;
      calculateTotalValue();
      changeSupport.firePropertyChange(PROPERTY_NAME_SKILL_LEVEL, oldValue, baseValue);
    }
  }

  @Override
  public void resetLevel() {
    if (isEnabled()) {
      int oldValue = baseValue;
      baseValue = MIN_LEVEL;
      calculateTotalValue();
      changeSupport.firePropertyChange(PROPERTY_NAME_SKILL_LEVEL, oldValue, baseValue);
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
      changeSupport.firePropertyChange(PROPERTY_NAME_SKILL_IMPROVEMENT_POINTS, oldValue,
          currentImprovementPoints);
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
  public void propertyChange(PropertyChangeEvent evt) {
    Object source = evt.getSource();

    if (attribute == source) {
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
    changeSupport.removePropertyChangeListener(propertyName, listener);
  }

}
