package rpg.general.combat;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import rpg.general.commerce.Product;
import rpg.util.Die;
import rpg.util.Probability;

public class WeaponModifier extends Product implements Modifier, Weaponizable {
	private String type;
	private Probability hitChance;
	private Probability damageChance;
	private int rangeModifier;
	private List<String> bonuses;

	public WeaponModifier(String name, String description, String type, Probability hitChance, Probability damageChance,
			int rangeModifier, List<String> bonusIterator, double cost, double weight) {
		super(name, description, cost, weight);

		this.type = type;
		this.hitChance = hitChance;
		this.damageChance = damageChance;
		this.rangeModifier = rangeModifier;
		this.bonuses = bonusIterator;
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof WeaponModifier)) {
			return false;
		}

		WeaponModifier modifier = (WeaponModifier) o;
		return ((modifier.getName()).equals(getName())) && (modifier.getType().equals(getType()));
	}

	public int hashCode() {
		return Objects.hash(getName(), getType());
	}

	public Die getHitDice() {
		return hitChance.getDice();
	}

	public int getHitModifier() {
		return hitChance.getModifier();
	}

	public int getHitScore() {
		return getHitModifier();
	}

	public Die getDamageDice() {
		return damageChance.getDice();
	}

	public int getDamageModifier() {
		return damageChance.getModifier();
	}

	public int getDamageScore() {
		return getDamageModifier();
	}

	public int getRangeModifier() {
		return rangeModifier;
	}

	public int getRangeScore() {
		return getRangeModifier();
	}

	public String getType() {
		return type;
	}

	public String getBonus() {
		String bonusString = "";
		Iterator<String> iterator = bonuses.iterator();
		while (iterator.hasNext()) {
			bonusString += iterator.next() + "\n";
		}
		return bonusString;
	}
}
