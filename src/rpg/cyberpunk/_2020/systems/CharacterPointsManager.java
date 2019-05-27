package rpg.cyberpunk._2020.systems;

import java.beans.PropertyChangeListener;
import java.util.List;
import rpg.general.stats.Attribute;
import rpg.util.Die;

/**
 * An interface used to help in the initialization of a Player's points upon creation.
 */
public interface CharacterPointsManager {
  public static final String PROPERTY_NAME_POINTS = "Points";

  /**
   * Sets the available points that can be spent between <code>minPoints</code> and
   * <code>maxPoints</code> inclusively.
   * 
   * @param minPoints the lower bound of points allowed to be spent
   * @param maxPoints the upper bound of points allowed to be spent
   */
  public void rollPoints(int minPoints, int maxPoints);

  /**
   * Sets the available points that can be spent using a simulated die roll to get total points.
   * 
   * @param die the object used to get the upper bound of a roll and how many times to roll for
   *        points using that upper bound
   */
  public void rollPoints(Die die);

  /**
   * @return a list of the values rolled based on the results of <code>rollPoints</code>
   */
  public List<Integer> getRolledPoints();

  /**
   * @return the amount of points available to be distributed
   */
  public int getCurrentlyAvailablePoints();

  /**
   * @return <code>true</code> if the points are distributed properly
   */
  public boolean isValid();

  /**
   * Appends an attribute for a manager to track.
   * 
   * @param attribute an Attribute to append so that its level is tracked and used for validation
   */
  public void add(Attribute attribute);

  /**
   * Removes an attribute that a manager no longer needs to track.
   * 
   * @param attribute an Attribute that the manager is no longer interested in
   */
  public void remove(Attribute attribute);

  /**
   * @return the attributes tracked by a manager
   */
  public List<Attribute> getAttributes();

  /**
   * Adds a PropertyChangeListener to the listener list.
   * 
   * @param listener the property change listener to be added
   */
  public void addPropertyChangeListener(PropertyChangeListener listener);

  /**
   * Removes a PropertyChangeListener from the listener list.
   * 
   * @param listener the PropertyChangeListener to be removed
   */
  public void removePropertyChangeListener(PropertyChangeListener listener);

  /**
   * Adds a PropertyChangeListener to the listener list for a specific property.
   * 
   * @param propertyName one of the property names to update upon receiving
   * @param listener the property change listener to be added
   */
  public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);

  /**
   * Removes a PropertyChangeListener from the listener list for a specific property.
   * 
   * @param propertyName a valid property name
   * @param listener the PropertyChangeListener to be removed
   */
  public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);
}
