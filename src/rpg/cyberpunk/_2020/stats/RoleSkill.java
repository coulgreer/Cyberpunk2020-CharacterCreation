package rpg.cyberpunk._2020.stats;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import rpg.general.stats.Attribute;
import rpg.general.stats.Restriction;

/**
 * A skill that has no difficulty modifier and is enabled only when a specific requirement is met.
 * The requirement usually points to a role that player has selected.
 * 
 * @author Coul Greer
 */
public class RoleSkill implements CyberpunkSkill {
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
   * Constructs a skill that does not require an attribute to get the total score.
   * 
   * @param name the identifier for this skill
   * @param description a blurb giving an overview of what this skill does/is
   * @param restriction a constraint used to stop leveling until requirements are met
   * @see rpg.cyberpunk._2020.stats.RoleSkill#RoleSkill(Attribute, String, String, Restriction)
   */
  public RoleSkill(String name, String description, Restriction restriction) {
    this(NullAttribute.getInstance(), name, description, restriction);
  }

  /**
   * Constructs a skill that does not require an attribute to get the total score.
   * 
   * @param attribute the statistic observed to calculate total value
   * @param name the identifier for this skill
   * @param description a blurb giving an overview of what this skill does/is
   * @param restriction a constraint used to stop leveling until requirements are met
   * @see rpg.cyberpunk._2020.stats.RoleSkill#RoleSkill(Attribute, String, String, Restriction)
   */
  public RoleSkill(Attribute attribute, String name, String description, Restriction restriction) {
    setName(name);
    setDescription(description);
    setRestriction(restriction);
    baseValue = MIN_LEVEL;
    difficultyModifier = MINIMUM_DIFFICULTY_MODIFIER;
    currentImprovementPoints = INITIAL_IP;
    neededImprovementPoints = INITIAL_IP_GOAL;
    setParentAttribute(attribute);
    calculateTotalValue();
    changeSupport = new PropertyChangeSupport(this);
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

  private void setParentAttribute(Attribute attribute) {
    if (attribute == null) {
      throw new NullPointerException();
    } else {
      this.attribute = attribute;
      attribute.addPropertyChangeListener(Attribute.PROPERTY_NAME_ATTRIBUTE_MODIFIER, this);
      calculateTotalValue();
    }
  }

  private void calculateTotalValue() {
    totalValue = baseValue + attribute.getModifier();
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
  public void increaseLevel() {
    if (baseValue < MAX_LEVEL && isEnabled()) {
      int oldValue = baseValue;
      baseValue++;
      calculateTotalValue();
      changeSupport.firePropertyChange(CyberpunkSkill.PROPERTY_NAME_SKILL_LEVEL, oldValue,
          baseValue);
    }
  }

  @Override
  public void decreaseLevel() {
    if (baseValue > MIN_LEVEL && isEnabled()) {
      int oldValue = baseValue;
      baseValue--;
      calculateTotalValue();
      changeSupport.firePropertyChange(CyberpunkSkill.PROPERTY_NAME_SKILL_LEVEL, oldValue,
          baseValue);
    }
  }

  @Override
  public void resetLevel() {
    int oldValue = baseValue;
    baseValue = MIN_LEVEL;
    calculateTotalValue();
    changeSupport.firePropertyChange(CyberpunkSkill.PROPERTY_NAME_SKILL_LEVEL, oldValue, baseValue);
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
  public boolean isEnabled() {
    return !restriction.isRestricted();
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
      int oldValue = totalValue;

      calculateTotalValue();
      changeSupport.firePropertyChange(PROPERTY_NAME_SKILL_VALUE, oldValue, totalValue);
    }
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
