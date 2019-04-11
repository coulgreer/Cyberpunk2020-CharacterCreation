package rpg.cyberpunk._2020.stats;

import rpg.general.stats.Restriction;

/**
 * An instance of Restriction that does nothing. Follows the Null Object
 * pattern.
 * 
 * @author Coul Greer
 */
public class NullRestriction implements Restriction {
	private static NullRestriction uniqueInstance;

	/**
	 * Constructs a new NullRestriction if one has not already been created. This is
	 * the only way to get an instance of this class, and this should represent that
	 * this will be the only instance of this class.
	 * 
	 * @return the one and only instance of NullRestriction
	 */
	public static NullRestriction getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NullRestriction();
		}

		return uniqueInstance;
	}

	private NullRestriction() {
	}

	@Override
	public boolean isRestricted() {
		return false;
	}

}
