package rpg.general.combat;

import rpg.util.Die;

public interface Weaponizable {
	public Die getHitDice();

	public int getHitModifier();

	public int getHitScore();

	public Die getDamageDice();

	public int getDamageModifier();

	public int getDamageScore();

	public int getRangeModifier();

	public int getRangeScore();
}
