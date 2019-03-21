package rpg.general.stats;

import java.beans.PropertyChangeListener;

public class NullLevelable implements Statistic {

	@Override
	public String getName() {
		return "";
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public int getLevel() {
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
