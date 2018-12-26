package rpg.general.stats;

import rpg.cyberpunk._2020.AttributeName;
import rpg.util.Observable;

/**
 * This class lays down the groundwork for any Attribute used in an RPG.
 * 
 * @author Coul Greer
 * @see Levelable
 * @see Observable
 */
public abstract class AbstractAttribute implements Levelable, Observable {

	/**
	 * Return the name of an Attribute as an Enum.
	 * 
	 * @return the AttributeName Enum.
	 */
	public abstract AttributeName getName();

	/**
	 * Return the description of the Attribute.
	 * 
	 * @return the description of the Attribute.
	 */
	public abstract String getDescription();

	/**
	 * Return the modifier for calculations. This will need to have an Enum known as
	 * AttributeName because of this the import may change from project to project.
	 * 
	 * @return the modifier used in calculation.
	 */
	public abstract int getModifier();
}
