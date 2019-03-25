package rpg.cyberpunk._2020.stats;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NullSkill implements CyberpunkSkill {
	private static NullSkill uniqueInstance;

	private NullSkill() {
		uniqueInstance = null;
	}

	public static NullSkill getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NullSkill();
		}
		return uniqueInstance;
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
