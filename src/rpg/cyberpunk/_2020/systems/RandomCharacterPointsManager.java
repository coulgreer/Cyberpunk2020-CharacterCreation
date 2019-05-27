package rpg.cyberpunk._2020.systems;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import rpg.cyberpunk._2020.stats.CyberpunkAttribute;
import rpg.cyberpunk._2020.stats.StatisticFactory;
import rpg.general.stats.Attribute;
import rpg.util.Die;

/**
 * Allows the managing of points during character creation. The user can roll a point pool as well
 * as spend and refund the points awarded from the roll.
 */
public class RandomCharacterPointsManager implements CharacterPointsManager {
  private static final int minAvailablePoints = 0;

  private List<Attribute> attributes;
  private List<Integer> points;
  private int availablePoints;
  private PropertyChangeSupport changeSupport;

  /**
   * Constructs an instance of RandomCharacterPointsManager that rolls a starting amount of points
   * between {@link CyberpunkAttribute#MIN_LEVEL} multiplied by {@link #independentAttributeCount}
   * and {@link CyberpunkAttribute#MAX_LEVEL} multiplied by {@link #independentAttributeCount} minus
   * the minimum amount of points allowed.
   */
  public RandomCharacterPointsManager() {
    attributes = new ArrayList<Attribute>(StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT);
    changeSupport = new PropertyChangeSupport(this);

    int minPoints = CyberpunkAttribute.MIN_LEVEL * StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT;
    int maxPoints = CyberpunkAttribute.MAX_LEVEL * StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT;
    rollPoints(minPoints, maxPoints);
  }

  /**
   * Resets all <code>Attribute</code>s managed and gets a random amount of points between
   * <code>minPoints</code> and <code>maxPoints</code> inclusively.
   */
  @Override
  public void rollPoints(int minPoints, int maxPoints) {
    for (Attribute a : attributes) {
      a.resetLevel();
    }

    points = new ArrayList<Integer>();
    points.add(ThreadLocalRandom.current().nextInt(minPoints, maxPoints + 1));
    availablePoints = points.stream().mapToInt(Integer::intValue).sum();

    changeSupport.firePropertyChange(PROPERTY_NAME_POINTS, null, points);
  }

  @Override
  public void rollPoints(Die die) {
    for (Attribute a : attributes) {
      a.resetLevel();
    }

    int dieCount = die.getDieCount();
    int faceCount = die.getFaceCount();

    points = new ArrayList<Integer>(dieCount);
    for (int i = 0; i < dieCount; i++) {
      points.add(ThreadLocalRandom.current().nextInt(CyberpunkAttribute.MIN_LEVEL, faceCount));
    }
    availablePoints = points.stream().mapToInt(Integer::intValue).sum();

    changeSupport.firePropertyChange(PROPERTY_NAME_POINTS, null, points);
  }

  @Override
  public List<Integer> getRolledPoints() {
    return points;
  }

  @Override
  public int getCurrentlyAvailablePoints() {
    int spentPoints = 0;

    for (Attribute a : attributes) {
      spentPoints += a.getLevel();
    }

    return availablePoints - spentPoints;
  }

  /**
   * @return <code>true</code> if the current available points are greater than or equal to the
   *         accumulation of all managed <code>Attribute</code> levels
   */
  @Override
  public boolean isValid() {
    return !(getCurrentlyAvailablePoints() < minAvailablePoints);
  }

  @Override
  public void add(Attribute attribute) {
    attributes.add(attribute);
  }

  @Override
  public void remove(Attribute attribute) {
    attributes.remove(attribute);
  }

  @Override
  public List<Attribute> getAttributes() {
    return attributes;
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    changeSupport.addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
    changeSupport.removePropertyChangeListener(listener);
  }

  public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    changeSupport.addPropertyChangeListener(propertyName, listener);
  }

  public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    changeSupport.removePropertyChangeListener(propertyName, listener);
  }

}
