package rpg.general.stats;

import rpg.util.Observer;

/**
 * Lays down the groundwork for any Skill used in an RPG. Skill has a dependency
 * upon <code>Attribute</code>.
 */
public interface Skill extends Levelable, Observer {

	/**
	 * Returns a String that represents this Skill's name.
	 * 
	 * @return the name of this Skill.
	 */
	public String getName();

	/**
	 * Returns the description of this Skill.
	 * 
	 * @return the description of this Skill.
	 */
	public String getDescription();

	/**
	 * Returns the total derived value of this skill. Based on if this Skill is
	 * dependent upon a <code>Attribute</code>
	 * 
	 * @return the total value between the Skills base score plus whatever its
	 *         associated modifier is.
	 */
	public int getTotalValue();
}
