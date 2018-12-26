package rpg.general.stats;

import rpg.util.Observable;
import rpg.util.Observer;

/**
 * This class lays down the groundwork for any Skill used in an RPG.
 * 
 * @author Coul Greer
 * @see Levelable
 * @see Observable
 * @see Observer
 */

public abstract class AbstractSkill implements Levelable, Observer {

	/**
	 * Return the name of an Skill as an Enum.
	 * 
	 * @return the SkillName Enum.
	 */

	public abstract String getName();

	/**
	 * Return the description of the Attribute.
	 * 
	 * @return the description of the Attribute.
	 */

	public abstract String getDescription();

	/**
	 * Return the total value.
	 * 
	 * @return the total value between the Skills base score plus whatever its
	 *         associated modifier is.
	 */

	public abstract int getTotalValue();
}
