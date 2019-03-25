package rpg.cyberpunk._2020.stats;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A skill used to label other skills. Simply, a placeholder used to categorize
 * other skills used in a TreeNode structure.
 * 
 * @author Coul Greer
 */
public class BroadSkill implements CyberpunkSkill {
	private String name;
	private String description;

	/**
	 * Constructs a placeholder skill for categorization.
	 * 
	 * @param name        the identifier for this skill
	 * @param description a blurb giving an overview of what this skill does/is
	 */
	public BroadSkill(String name, String description) {
		setName(name);
		setDescription(description);
	}

	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("The field 'name' is cannot be null.");
		} else {
			this.name = name;
		}
	}

	private void setDescription(String description) {
		if (description == null) {
			throw new IllegalArgumentException("The field 'description' is cannot be null.");
		} else {
			this.description = description;
		}
	}

	@Override
	public void increaseLevel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void decreaseLevel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void resetLevel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getLevel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getTotalValue() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void increaseCurrentImprovementPoints(int improvementPoints) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getCurrentImprovementPoints() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getNeededImprovementPoints() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		throw new UnsupportedOperationException();
	}

	// TODO Think about throwing an exception for all propertyChange methods.
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// Do nothing. This class is immutable so properties never change
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// Do nothing. This class is immutable so properties never change
	}

	@Override
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		// Do nothing. This class is immutable so properties never change
	}

	@Override
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		// Do nothing. This class is immutable so properties never change
	}

}
