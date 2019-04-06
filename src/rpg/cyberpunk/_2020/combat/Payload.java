package rpg.cyberpunk._2020.combat;

import rpg.util.Die;
import rpg.util.NullProbability;
import rpg.util.Probability;

/**
 * The dictionary of contents that can be put into a grenade.
 * 
 * @author Coul Greer
 *
 */
public enum Payload {
	/**
	 * A payload that does damage in a radius with 7D6 damage.
	 */
	HIGH_EXPLOSIVES(
			"This round does 7D6 damage in a 5 meter radius."
					+ " It will not detonate until it has traveled 10 meters from the weapon after firing.",
			new Probability(new Die(7, 6), 0)),

	/**
	 * A payload that does damage in a radius with 4D6 damage and burns for 3 turns.
	 */
	WHITE_PHOSPHOROUS(
			"This nasty round throws a cloud of burning white phosphous."
					+ " Anyone within 10 meters of the explosion takes 4D6 damage for three turns.",
			new Probability(new Die(4, 6), 0)),

	/**
	 * A payload that debilitates a targets hearing and sight.
	 */
	FLASHBANG(
			"All people within 5 meters of the blast (15 meters if indoors) must make a Stun Save at -2 to avoid"
					+ " being stunned and deafened for 4 turns (40 sec.) and a Difficulty 20+ REF test to avoid"
					+ " being blinded for 2 turns (20sec.) Anti-dazzle protection negates the flash effect and"
					+ " make the REF test unnecessary. Other versions have little discernable flash, but concussive"
					+ " effect (no blinding effect; -5 to Stun Save). Soft armor gives no protection vs. the effects.",
			NullProbability.getInstance()),

	/**
	 * A payload that debilitates a target by inflicting nausea.
	 */
	NAUSEA_GAS(
			"All targets within the pattern must make a 25+ BOD check to avoid disorientation, headaches and nausea.\n"
					+ "\tIf successful: Debilitation (-4 to all actions for 1D6 rounds)\n"
					+ "\tIf failed by 1-3 points: Incapacitation (REF and MA reduced to 1 for 1D6 rounds)\n"
					+ "\tIf failed by 4+ points: Serious Incapacitation (unconscious for 1D6 minutes)",
			NullProbability.getInstance()),

	/**
	 * A payload that debilitates a target by reducing their stats.
	 */
	TEARGAS("The effects of tear gas are unpleasant at best, inhalation of tear gas will reduce INT, REF, COOL,"
			+ " and MA by half unless a difficult(20) BOD roll is made each round of exposure. Anyone without"
			+ " optics will also receive a -5 to awareness from tearing that will last for D6 minutes after exposure.",
			NullProbability.getInstance()),

	/**
	 * A payload that puts a target to sleep.
	 */
	SLEEP_DRUGS("On a failed save, puts target asleep for 1D10 mins."
			+ " On a successful save, the target is made drowsy with a -2 to all stats."
			+ " Any target struck must make a Very Difficult BOD check (plus Resist Drugs skill) to avoid it's effects.",
			NullProbability.getInstance()),

	/**
	 * A payload that attacks the body with chemicals dealing 4D6 damage.
	 */
	BIOTOXIN_I_GAS("4D6 damage as nerve endings flare up and disrupt", new Probability(new Die(4, 6), 0)),

	/**
	 * A payload that attacks the body with chemicals dealing 8D6 damage.
	 */
	BIOTOXIN_II_GAS("8D6 damage as nerve and muscle clusters tear themselves up and disrupt",
			new Probability(new Die(8, 6), 0)),

	/**
	 * A payload that attacks the body with chemicals dealing 8D10 damage.
	 */
	NERVE_GAS("8D10 damage internal bleeding, clotting, and organ disintegration.", new Probability(new Die(8, 10), 0));

	private String effects;
	private Probability damage;

	/**
	 * Constructs contents that can be inserted into a container to get damage and
	 * effects created by payloads contents.
	 * 
	 * @param effects the status ailments afflicted to the target
	 * @param damage  the probability of the amount of HP lost when hit by this
	 *                payload
	 */
	Payload(String effects, Probability damage) {
		this.effects = effects;
		this.damage = damage;
	}

	/**
	 * Returns the status ailments caused.
	 * 
	 * @return the status ailments caused when hit
	 */
	public String getEffects() {
		return effects;
	}

	/**
	 * Returns the probable damage caused.
	 * 
	 * @return the probable damage caused
	 */
	public Probability getDamge() {
		return damage;
	}
}
