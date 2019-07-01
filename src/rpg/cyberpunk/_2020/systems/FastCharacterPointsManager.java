package rpg.cyberpunk._2020.systems;

import java.beans.PropertyChangeEvent;
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
  private static final int firstIndex = 0;
  private static final int lastIndex = StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT - 1;

  private int index;
  private List<Attribute> attributes;
  private List<Integer> points;
  private PropertyChangeSupport changeSupport;

  /**
   * Constructs an instance of FastCharacterPointsManager without any starting Attributes. Rolling
   * an initial pool of points to be distributed among all managed Attributes.
   * 
   * @see #FastCharacterPointsManager(List)
   */
  public FastCharacterPointsManager() {
    this(Collections.emptyList());
  }

  /**
   * Constructs a FastCharacterPointsManager that keeps track of a list of <code>Attribute</code>s
   * and rolling the first pool of points. Also, wraps all the attributes within the given list as
   * {@link FastCharacterPointsManager.FastAttribute FastAttributes}
   * 
   * @param attributes the initial list of Attributes to wrap and manage
   */
  public FastCharacterPointsManager(List<Attribute> attributes) {
    index = firstIndex;
    changeSupport = new PropertyChangeSupport(this);
    setAttributes(attributes);

    int minPoints = CyberpunkAttribute.MIN_LEVEL * StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT;
    int maxPoints = CyberpunkAttribute.MAX_LEVEL * StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT;
    rollPoints(minPoints, maxPoints);
  }

  private void setAttributes(List<Attribute> attributes) {
    if (attributes == null) {
      throw new NullPointerException();
    } else if (attributes.contains(null)) {
      throw new IllegalArgumentException("Null values are not allowed to be in attributes");
    } else {
      this.attributes = new ArrayList<Attribute>(attributes.size());

      for (Attribute a : attributes) {
        this.attributes.add(new FastAttribute(a, this));
      }
    }
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

    changeSupport.firePropertyChange(PROPERTY_NAME_POINTS, null, points);
  }

  @Override
  public List<Integer> getRolledPoints() {
    return Collections.unmodifiableList(points);
  }

  /**
   * Sets the available points then increments to the next index. Afterwards, returns the previously
   * set available points.
   * 
   * @return the available set of points
   */
  @Override
  public int getCurrentlyAvailablePoints() {
    int availablePoints = points.get(index);

    if (index >= lastIndex) {
      index = firstIndex;
    } else {
      index++;
    }

    return availablePoints;
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
    return Collections.unmodifiableList(attributes);
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

  /**
   * An <code>Attribute</code> that uses an instance of <code>CharacterPointsManager</code> to help
   * reset the wrapped Attribute according to the available points to choose from.
   */
  public static class FastAttribute implements Attribute, PropertyChangeListener {
    private Attribute attribute;
    private CharacterPointsManager manager;

    /**
     * Constructs an instance of FastAttribute that wraps another <code>Attribute</code> and uses
     * the given <code>CharacterPointsManager</code> to help add logic when reseting the level.
     * 
     * @param attribute an object to wrap and delegate most behavior to
     * @param manager the CharacterPointsManager used to add functionality when an Attribute needs
     *        to be reset
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
        manager.addPropertyChangeListener(PROPERTY_NAME_POINTS, this);

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
    public void incrementLevel() {
      attribute.incrementLevel();
    }

    @Override
    public void decrementLevel() {
      attribute.decrementLevel();
    }

    /**
     * Sets this attribute's level to a value held in the CharacterPointsManager's point pool. The
     * value is determined by the current index up to {@link #lastIndex} and then resets the index
     * to <code>0</code> if the <code>lastIndex</code> is reached.
     */
    @Override
    public void resetLevel() {
      updateLevel(manager.getCurrentlyAvailablePoints());
    }

    private void updateLevel(int value) {
      while (attribute.getLevel() < value) {
        attribute.incrementLevel();
      }

      while (attribute.getLevel() > value) {
        attribute.decrementLevel();
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
      Object source = evt.getSource();

      if (source == manager) {
        resetLevel();
      }
    }

  }

}
