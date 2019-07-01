package rpg.cyberpunk._2020.systems;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
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
   * Constructs an instance of RandomCharacterPointsManager without any starting Attributes. Rolling
   * an initial pool of points to be distributed among all managed Attributes.
   * 
   * @see #RandomCharacterPointsManager(List)
   */
  public RandomCharacterPointsManager() {
    this(Collections.emptyList());
  }

  /**
   * Constructs an instance of RandomCharacterPointsManager that rolls a starting amount of points.
   * Also, wraps all the attributes in the given list as a
   * {@link RandomCharacterPointsManager.RandomAttribute RandomAttribute}
   * 
   * @param attributes the initial list of Attributes to wrap and manage
   */
  public RandomCharacterPointsManager(List<Attribute> attributes) {
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
        this.attributes.add(new RandomAttribute(a, this));
      }
    }
  }

  /**
   * Resets all <code>Attribute</code>s managed and gets a random amount of points between
   * <code>minPoints</code> and <code>maxPoints</code> inclusively.
   */
  @Override
  public void rollPoints(int minPoints, int maxPoints) {
    points = new ArrayList<Integer>();
    points.add(ThreadLocalRandom.current().nextInt(minPoints, maxPoints + 1));
    availablePoints = points.stream().mapToInt(Integer::intValue).sum();

    changeSupport.firePropertyChange(PROPERTY_NAME_POINTS, null, points);
  }

  @Override
  public void rollPoints(Die die) {
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
    return Collections.unmodifiableList(points);
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
    return Collections.unmodifiableList(attributes);
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

  /**
   * An instance of <code>Attribute</code> that abides by the rules of the Random Character Point
   * method of getting points at character creation.
   */
  public static class RandomAttribute implements Attribute, PropertyChangeListener {
    private Attribute attribute;
    private CharacterPointsManager manager;

    /**
     * Constructs a <code>RandomAttribute</code> that wraps another instance of
     * <code>Attribute</code> while using a shared manager that keeps track of available points.
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
      if (manager.getCurrentlyAvailablePoints() > 0) {
        attribute.incrementLevel();
      }
    }

    @Override
    public void decrementLevel() {
      attribute.decrementLevel();
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
      Object source = evt.getSource();

      if (source == manager) {
        resetLevel();
      }
    }

  }

}
