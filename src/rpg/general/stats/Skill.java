package rpg.general.stats;

import java.beans.PropertyChangeListener;

/**
 * Lays down the groundwork for any Skill used in an RPG. Skill has a dependency upon
 * <code>Attribute</code>.
 */
public interface Skill extends Statistic, PropertyChangeListener {

  /**
   * Returns the total derived value of this skill. Based on if this Skill is dependent upon a
   * <code>Attribute</code>
   * 
   * @return the total value between the Skills base score plus whatever its associated modifier is.
   */
  public int getTotalValue();

}
