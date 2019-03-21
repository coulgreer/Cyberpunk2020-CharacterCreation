package rpg.cyberpunk._2020.stats;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BroadSkill implements CyberpunkSkill {
	private Map<String, CyberpunkSkill> skills;
	private String name;
	private String description;
	private PropertyChangeSupport changeSupport;

	public BroadSkill(String name, String description) {
		setName(name);
		setDescription(description);
		skills = new HashMap<String, CyberpunkSkill>();
		changeSupport = new PropertyChangeSupport(this);
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
		int totalValue = 0;
		Iterator<Map.Entry<String, CyberpunkSkill>> iterator = getIterator();
		while (iterator.hasNext()) {
			CyberpunkSkill skill = iterator.next().getValue();
			totalValue += skill.getTotalValue();
		}
		return totalValue;
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
	public CyberpunkSkill accept(SkillVisitor visitor) {
		return visitor.visit(this);
	}

	// TODO Remove composite pattern methods due to the creation of TreeNode<T>
	public void add(CyberpunkSkill skill) {
		skills.put(skill.getName(), skill);
	}

	public void remove(CyberpunkSkill skill) {
		skills.remove(skill.getName());
	}

	public CyberpunkSkill getChild(String skillName) {
		SkillVisitor visitor = new GetSkillVisitor(skillName);
		for (CyberpunkSkill skill : skills.values()) {
			CyberpunkSkill child = skill.accept(visitor);
			if (!(child.equals(NullSkill.getInstance()))) {
				return child;
			}
		}
		return NullSkill.getInstance();
	}

	public Iterator<Map.Entry<String, CyberpunkSkill>> getIterator() {
		return skills.entrySet().iterator();
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	@Override
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}

	@Override
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}
}
