package rpg.cyberpunk._2020.systems;

import java.beans.PropertyChangeListener;
import rpg.cyberpunk._2020.stats.StatisticFactory;
import rpg.general.stats.Attribute;

/**
 * An <code>Attribute</code> that uses an instance of <code>CharacterPointsManager</code> to help
 * reset the wrapped Attribute according to the available points to choose from.
 */
public class FastAttribute implements Attribute {
  private static final int lastIndex = StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT - 1;
  private static int index;

  private Attribute attribute;
  private CharacterPointsManager manager;

  /**
   * Constructs an instance of FastAttribute that wraps another <code>Attribute</code> and uses the
   * given <code>CharacterPointsManager</code> to help add logic when reseting the level.
   * 
   * @param attribute an object to wrap and delegate most behavior to
   * @param manager the CharacterPointsManager used to add functionality when an Attribute needs to
   *        be reset
   */
  public FastAttribute(Attribute attribute, CharacterPointsManager manager) {
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
    attribute.increaseLevel();
  }

  @Override
  public void decreaseLevel() {
    attribute.decreaseLevel();
  }

  /**
   * Sets this attribute's level to a value held in the CharacterPointsManager's point pool. The
   * value is determined by the current index up to {@link #lastIndex} and then resets the index to
   * <code>0</code> if the <code>lastIndex</code> is reached.
   */
  @Override
  public void resetLevel() {
    updateLevel(manager.getRolledPoints().get(index));

    index++;
    if (index > lastIndex) {
      index = 0;
    }
  }

  private void updateLevel(Integer value) {
    while (attribute.getLevel() < value) {
      attribute.increaseLevel();
    }

    while (attribute.getLevel() > value) {
      attribute.decreaseLevel();
    }
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
