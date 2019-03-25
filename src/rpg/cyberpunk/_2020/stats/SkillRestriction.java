package rpg.cyberpunk._2020.stats;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import rpg.general.stats.Restriction;

/**
 * A restriction that uses a skill to dictate if another object is or is not
 * limited in its abilities.
 * 
 * @author Coul Greer
 */
public class SkillRestriction implements Restriction, PropertyChangeListener {
	private CyberpunkSkill skill;
	private int minimumLevel;
	private boolean isRestricted;

	/**
	 * Constructs a limit that uses a skill and a minimum level.
	 * 
	 * @param skill        the object to watch and get level from
	 * @param minimumLevel the lowest score that a level can be for the given skill
	 */
	public SkillRestriction(CyberpunkSkill skill, int minimumLevel) {
		setSkill(skill);
		setMinimumLevel(minimumLevel);
		setRestricted(!hasMetMinimumLevel());
	}

	private void setSkill(CyberpunkSkill skill) {
		if (skill == null) {
			throw new IllegalArgumentException("The field 'skill' must not be null.");
		} else {
			this.skill = skill;
			skill.addPropertyChangeListener(CyberpunkSkill.PROPERTY_NAME_SKILL_LEVEL, this);
		}
	}

	private void setMinimumLevel(int minimumLevel) {
		if (minimumLevel < CyberpunkSkill.MIN_LEVEL) {
			this.minimumLevel = CyberpunkSkill.MIN_LEVEL;
		} else {
			this.minimumLevel = minimumLevel;
		}
	}

	private void setRestricted(boolean isRestricted) {
		this.isRestricted = isRestricted;
	}

	private boolean hasMetMinimumLevel() {
		return (minimumLevel <= skill.getLevel());
	}

	@Override
	public boolean isRestricted() {
		return isRestricted;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (skill == evt.getSource()) {
			setRestricted(!hasMetMinimumLevel());
		}
	}
}
