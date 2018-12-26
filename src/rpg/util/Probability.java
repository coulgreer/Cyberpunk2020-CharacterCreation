package rpg.util;

public class Probability {
	private Die die;
	private int modifier;

	public Probability(Die die, int modifier) {
		this.die = die;
		this.modifier = modifier;
	}

	public Die getDice() {
		return die;
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
