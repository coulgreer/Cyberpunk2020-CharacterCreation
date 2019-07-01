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
  private static final int minImprovementPointDelta = 1;

  private Attribute parentAttribute;
  private String name;
  private String description;
  private int baseValue;
  private int ipValue;
  private int totalValue;
  private int difficultyModifier;
  private int currentImprovementPoints;
  private Restriction restriction;
  private PropertyChangeSupport changeSupport;

  /**
   * Constructs a skill that depends on an attribute and has a restriction.
   * 
   * @param parentAttribute the statistic used for calculating total skill level
   * @param name the identifier for this skill
   * @param description a blurb giving an overview of what this skill does/is
   * @param difficultyModifier a modifier used to multiply how many improvement points are needed to
   *        level
   * @param restriction a constraint used to prevent modification of a skill
   */
  public SpecializedSkill( //
      Attribute parentAttribute, //
      String name, String description, //
      int difficultyModifier, //
      Restriction restriction) {

    changeSupport = new PropertyChangeSupport(this);
    baseValue = MIN_LEVEL;
    ipValue = MIN_LEVEL;
    currentImprovementPoints = INITIAL_IP;
    setName(name);
    setDescription(description);
    setRestriction(restriction);
    setDifficultyModifier(difficultyModifier);
    setParentAttribute(parentAttribute);
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
    if (difficultyModifier < DEFAULT_DIFFICULTY_MODIFIER) {
      throw new IllegalArgumentException("modifier =  " + difficultyModifier
          + "; exclusive min modifier = " + DEFAULT_DIFFICULTY_MODIFIER);
    } else {
      this.difficultyModifier = difficultyModifier;
    }
  }

  private void setParentAttribute(Attribute parentAttribute) {
    if (parentAttribute == null) {
      throw new NullPointerException();
    } else {
      parentAttribute.addPropertyChangeListener(Attribute.PROPERTY_NAME_ATTRIBUTE_MODIFIER, this);
      this.parentAttribute = parentAttribute;
      updateTotalValue();
    }
  }

  private void updateTotalValue() {
    int oldValue = totalValue;

    totalValue = baseValue + parentAttribute.getModifier();
    changeSupport.firePropertyChange(PROPERTY_NAME_SKILL_VALUE, oldValue, totalValue);
  }

  @Override
  public void incrementLevel() {
    if (baseValue < MAX_LEVEL && !(restriction.isRestricted())) {
      int oldValue = baseValue;
      baseValue++;
      updateTotalValue();
      changeSupport.firePropertyChange(PROPERTY_NAME_SKILL_LEVEL, oldValue, baseValue);
    }
  }

  @Override
  public void decrementLevel() {
    if ((baseValue > MIN_LEVEL) && !(restriction.isRestricted())) {
      int oldValue = baseValue;
      baseValue--;
      updateTotalValue();
      changeSupport.firePropertyChange(PROPERTY_NAME_SKILL_LEVEL, oldValue, baseValue);
    }
  }

  @Override
  public void resetLevel() {
    if (!(restriction.isRestricted())) {
      int oldValue = baseValue;
      baseValue = MIN_LEVEL;
      updateTotalValue();
      changeSupport.firePropertyChange(PROPERTY_NAME_SKILL_LEVEL, oldValue, baseValue);
    }
  }

  @Override
  public int getLevel() {
    return baseValue > ipValue ? baseValue : ipValue;
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
  public int getDifficultyModifier() {
    return difficultyModifier;
  }

  @Override
  public void increaseCurrentImprovementPoints(int improvementPoints) {
    if (improvementPoints < minImprovementPointDelta) {
      throw new IllegalArgumentException("improvementPoints = " + improvementPoints
          + "; min improvementPoints = " + minImprovementPointDelta);
    }

    if (!restriction.isRestricted()) {
      int oldValue = currentImprovementPoints;
      currentImprovementPoints += improvementPoints;
      checkForLevelUp();
      changeSupport.firePropertyChange(PROPERTY_NAME_SKILL_IMPROVEMENT_POINTS, oldValue,
          currentImprovementPoints);
    }
  }

  private void checkForLevelUp() {
    while (currentImprovementPoints >= getTargetImprovementPoints() && ipValue < MAX_LEVEL) {
      ipValue++;
    }
  }

  /**
   * If improvementPoints is greater than the current improvement points this skill has then the
   * skills improvement points are decreased by current improvement points instead.
   */
  @Override
  public void decreaseCurrentImprovementPoints(int improvementPoints) {
    if (improvementPoints < minImprovementPointDelta) {
      throw new IllegalArgumentException(
          "points = " + improvementPoints + "; min = " + minImprovementPointDelta);
    }

    if (!restriction.isRestricted()) {
      improvementPoints = improvementPoints <= currentImprovementPoints ? improvementPoints
          : currentImprovementPoints;
      int oldValue = currentImprovementPoints;
      currentImprovementPoints -= improvementPoints;
      checkForLevelDown();
      changeSupport.firePropertyChange(PROPERTY_NAME_SKILL_IMPROVEMENT_POINTS, oldValue,
          currentImprovementPoints);
    }
  }

  private void checkForLevelDown() {
    while (currentImprovementPoints <= calculateTargetImprovementPoints(ipValue - 1)
        && ipValue > MIN_LEVEL) {
      ipValue--;
    }
  }

  @Override
  public int getCurrentImprovementPoints() {
    return currentImprovementPoints;
  }

  @Override
  public int getTargetImprovementPoints() {
    return calculateTargetImprovementPoints(getLevel());
  }

  private int calculateTargetImprovementPoints(int level) {
    if (level == 0) {
      return INITIAL_IP_GOAL;
    } else {
      return 10 * level * difficultyModifier;
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    Object source = evt.getSource();

    if (parentAttribute == source) {
      updateTotalValue();
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

  public static class Builder {
    private String name;
    private String description;
    private int difficultyModifier;
    private Attribute parentAttribute;
    private Restriction restriction;

    public Builder(String name, String description) {
      this.name = name;
      this.description = description;
      difficultyModifier = DEFAULT_DIFFICULTY_MODIFIER;
      parentAttribute = NullAttribute.getInstance();
      restriction = NullRestriction.getInstance();
    }

    public Builder withDifficultyOf(int difficultyModifier) {
      this.difficultyModifier = difficultyModifier;

      return this;
    }

    public Builder withParent(Attribute parentAttribute) {
      this.parentAttribute = parentAttribute;

      return this;
    }

    public Builder withRestriction(Restriction restriction) {
      this.restriction = restriction;

      return this;
    }

    public SpecializedSkill build() {
      return new SpecializedSkill(parentAttribute, name, description, difficultyModifier,
          restriction);
    }

  }

}
