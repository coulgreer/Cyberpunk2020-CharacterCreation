package rpg.cyberpunk._2020.combat;

import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Availability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Concealability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Reliability;
import rpg.util.Die;
import rpg.util.Probability;

public interface AbstractFightingStyleFactory {
	public static final Concealability CONCEALABILITY = Concealability.POCKET;
	public static final Availability AVAILABILITY = Availability.EXCELLENT;
	public static final Reliability RELIABILITY = Reliability.VERY_RELIABLE;
	public static final Probability STRIKE_DAMAGE = new Probability(new Die(1, 6, 2), 0);
	public static final Probability KICK_DAMAGE = new Probability(new Die(1, 6), 0);
	public static final Probability BLOCK_DAMAGE = new Probability(new Die(0, 0), 0);
	public static final Probability DODGE_DAMAGE = new Probability(new Die(0, 0), 0);
	public static final Probability THROW_DAMAGE = new Probability(new Die(1, 6), 0);
	public static final Probability HOLD_DAMAGE = new Probability(new Die(0, 0), 0);
	public static final Probability ESCAPE_DAMAGE = new Probability(new Die(0, 0), 0);
	public static final Probability CHOKE_DAMAGE = new Probability(new Die(1, 6), 0);
	public static final Probability SWEEP_DAMAGE = new Probability(new Die(0, 0), 0);
	public static final Probability GRAPPLE_DAMAGE = new Probability(new Die(0, 0), 0);

	public CyberpunkWeapon createStrike();

	public CyberpunkWeapon createKick();

	public CyberpunkWeapon createBlock();

	public CyberpunkWeapon createDodge();

	public CyberpunkWeapon createDisarm();

	public CyberpunkWeapon createThrow();

	public CyberpunkWeapon createHold();

	public CyberpunkWeapon createEscape();

	public CyberpunkWeapon createChoke();

	public CyberpunkWeapon createSweep();

	public CyberpunkWeapon createGrapple();
}
