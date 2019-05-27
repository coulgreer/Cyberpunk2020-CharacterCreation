package rpg.cyberpunk._2020.systems;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import rpg.cyberpunk._2020.stats.CyberpunkAttribute;
import rpg.cyberpunk._2020.stats.StatisticFactory;
import rpg.general.stats.Attribute;
import rpg.util.Die;

/**
 * Manages the points that a character rolls. The points rolled are kept separate such as every die
 * rolled is kept unique, and the points are then allowed to be distributed to an Attribute as the
 * user sees fit as long as all points are used.
 */
public class FastCharacterPointsManager implements CharacterPointsManager {
  private List<Attribute> attributes;
  private List<Integer> points;
  private PropertyChangeSupport changeSupport;

  /**
   * Constructs a FastCharacterPointsManager that keeps track of a list of <code>Attribute</code>s
   * and rolling the first pool of points.
   * 
   * @param attributes a list of Attributes to check whenever <code>isValid</code> is called
   */
  public FastCharacterPointsManager() {
    attributes = new ArrayList<Attribute>(StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT);
    changeSupport = new PropertyChangeSupport(this);

    int minPoints = CyberpunkAttribute.MIN_LEVEL * StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT;
    int maxPoints = CyberpunkAttribute.MAX_LEVEL * StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT;
    rollPoints(minPoints, maxPoints);
  }

  /**
   * Divides the minPoints and the maxPoints by {@link #independentAttributeCount} and uses the
   * quotients as the new upper and lower bound when getting the {@value #independentAttributeCount}
   * values to be distributed. The bounds will be truncated resulting in the loss of decimals. For
   * example, 20 / 9 will result in 2 instead of 2.22...
   */
  @Override
  public void rollPoints(int minPoints, int maxPoints) {
    int derivedMinPoints = minPoints / StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT;
    int derivedMaxPoints = maxPoints / StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT;

    points = new ArrayList<Integer>(StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT);
    for (int i = 0; i < StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT; i++) {
      points //
          .add(ThreadLocalRandom.current().nextInt(derivedMinPoints, derivedMaxPoints + 1));
    }

    changeSupport.firePropertyChange(PROPERTY_NAME_POINTS, null, points);
  }

  @Override
  public void rollPoints(Die die) {
    int dieCount = die.getDieCount();
    int faceCount = die.getFaceCount();

    points = new ArrayList<Integer>(dieCount);
    for (int i = 0; i < dieCount; i++) {
      points.add(ThreadLocalRandom.current().nextInt(CyberpunkAttribute.MIN_LEVEL, faceCount + 1));
    }

    for (Attribute a : attributes) {
      a.resetLevel();
    }

    changeSupport.firePropertyChange(PROPERTY_NAME_POINTS, null, points);
  }

  @Override
  public List<Integer> getRolledPoints() {
    return Collections.unmodifiableList(points);
  }

  /**
   * @return 0 because all points should be spent based on <code>isValid</code>
   */
  @Override
  public int getCurrentlyAvailablePoints() {
    return 0;
  }

  /**
   * @return <code>true</code> if all available points are used to set <code>Attribute</code> levels
   */
  @Override
  public boolean isValid() {
    boolean isValid = true;
    Set<Integer> set = new HashSet<Integer>(points);

    Iterator<Integer> iterator = set.iterator();
    while (iterator.hasNext() && isValid) {
      Integer value = iterator.next();
      int frequency = Collections.frequency(points, value);
      int count = 0;

      for (Attribute a : attributes) {
        if (a.getLevel() == value) {
          count++;
        }
      }

      isValid = frequency == count;
    }

    return isValid;
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
