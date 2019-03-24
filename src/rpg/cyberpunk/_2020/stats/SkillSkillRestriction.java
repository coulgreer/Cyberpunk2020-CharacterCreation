package rpg.cyberpunk._2020.stats;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import rpg.general.stats.SkillRestriction;

public class SkillSkillRestriction implements SkillRestriction, PropertyChangeListener {
	private CyberpunkSkill skill;
	private int minimumLevel;
	private boolean isRestricted;

	public SkillSkillRestriction(CyberpunkSkill skill, int minimumLevel) {
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
