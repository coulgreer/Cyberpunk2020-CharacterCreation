package rpg.cyberpunk._2020.stats;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * An instance of CyberpunkSkill that does nothing. Follows the Null Object
 * pattern.
 * 
 * @author Coul Greer
 */
public class NullSkill implements CyberpunkSkill {
	private static NullSkill uniqueInstance;

	/**
	 * Constructs a new NullSkill if one has not already been created. This is the
	 * only way to get an instance of this class, and this should represent that
	 * this will be the only instance of this class.
	 * 
	 * @return the one and only instance of NullSkill
	 */
	public static NullSkill getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NullSkill();
		}
		return uniqueInstance;
	}

	private NullSkill() {
		uniqueInstance = null;
	}

	@Override
	public String getName() {
		return CyberpunkSkill.NONE;
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public int getTotalValue() {
		return 0;
	}

	@Override
	public void increaseLevel() {
	}

	@Override
	public void decreaseLevel() {
	}

	@Override
	public void resetLevel() {
	}

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void increaseCurrentImprovementPoints(int improvementPoints) {
	}

	@Override
	public int getCurrentImprovementPoints() {
		return 0;
	}

	@Override
	public int getNeededImprovementPoints() {
		return 0;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
	}

	@Override
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
	}

	@Override
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
	}
}
