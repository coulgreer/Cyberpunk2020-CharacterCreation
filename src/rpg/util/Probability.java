package rpg.util;

import java.io.Serializable;

public class Probability implements Serializable {
	private Die die;
	private int modifier;

	public Probability() {
		this(new Die(0, 0), 0);
	}

	public Probability(Die die, int modifier) {
		this.die = die;
		this.modifier = modifier;
	}

	public Die getDice() {
		return die;
	}

	public void setDice(Die die) {
		this.die = die;
	}

	public int getModifier() {
		return modifier;
	}

	public void setModifier(int modifier) {
		this.modifier = modifier;
	}

	public String toString() {
		return die.toString() + "+" + modifier;
	}
}
