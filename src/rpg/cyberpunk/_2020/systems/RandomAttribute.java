package rpg.cyberpunk._2020.systems;

import java.beans.PropertyChangeListener;
import rpg.general.stats.Attribute;

/**
 * An instance of <code>Attribute</code> that abides by the rules of the Random Character Point
 * method of getting points at character creation.
 */
public class RandomAttribute implements Attribute {
  private Attribute attribute;
  private CharacterPointsManager manager;

  /**
   * Constructs a <code>RandomAttribute</code> that wraps another instance of <code>Attribute</code>
   * while using a shared manager that keeps track of available points.
   * 
   * @param attribute the object that most all functions delegate to
   * @param manager the tracker used to allow or disallow manipulation of this RandomAttribute's
   *        level
   */
  public RandomAttribute(Attribute attribute, CharacterPointsManager manager) {
    setAttribute(attribute);
    setManager(manager);
  }

  private void setAttribute(Attribute attribute) {
    if (attribute == null) {
      throw new NullPointerException();
    } else {
      this.attribute = attribute;
    }
  }

  private void setManager(CharacterPointsManager manager) {
    if (manager == null) {
      throw new NullPointerException();
    } else {
      this.manager = manager;
    }
  }

  @Override
  public String getName() {
    return attribute.getName();
  }

  @Override
  public String getDescription() {
    return attribute.getDescription();
  }

  @Override
  public int getLevel() {
    return attribute.getLevel();
  }

  @Override
  public void increaseLevel() {
    if (manager.getCurrentlyAvailablePoints() > 0) {
      attribute.increaseLevel();
    }
  }

  @Override
  public void decreaseLevel() {
    attribute.decreaseLevel();
  }

  @Override
  public void resetLevel() {
    attribute.resetLevel();
  }

  @Override
  public int getModifier() {
    return attribute.getModifier();
  }

  @Override
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    attribute.addPropertyChangeListener(listener);
  }

  @Override
  public void removePropertyChangeListener(PropertyChangeListener listener) {
    attribute.removePropertyChangeListener(listener);
  }

  @Override
  public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    attribute.addPropertyChangeListener(propertyName, listener);
  }

  @Override
  public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    attribute.removePropertyChangeListener(propertyName, listener);
  }

}
