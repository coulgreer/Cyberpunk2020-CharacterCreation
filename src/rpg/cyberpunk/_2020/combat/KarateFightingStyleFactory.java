package rpg.cyberpunk._2020.combat;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;

public class KarateFightingStyleFactory implements AbstractFightingStyleFactory {
	public static final String DESCRIPTION = "The Japanese version of kung fu, this style uses straight line movements and powerful blows." //
			+ " Variations include shotokan and kenpo, each with their own special moves.";

	private static KarateFightingStyleFactory uniqueInstance;

	private KarateFightingStyleFactory() {
	}

	public static KarateFightingStyleFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new KarateFightingStyleFactory();
		}
		return uniqueInstance;
	}

	public CyberpunkWeapon createStrike() {
		return new MartialWeapon("Karate Strike", DESCRIPTION, CyberpunkSkill.KARATE, 2, CONCEALABILITY,
				AVAILABILITY, STRIKE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createKick() {
		return new MartialWeapon("Karate Kick", DESCRIPTION, CyberpunkSkill.KARATE, 2, CONCEALABILITY, AVAILABILITY,
				KICK_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createBlock() {
		return new MartialWeapon("Karate Block", DESCRIPTION, CyberpunkSkill.KARATE, 2, CONCEALABILITY,
				AVAILABILITY, BLOCK_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createDodge() {
		return new MartialWeapon("Karate Dodge", DESCRIPTION, CyberpunkSkill.KARATE, 0, CONCEALABILITY,
				AVAILABILITY, DODGE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createDisarm() {
		// TODO Auto-generated method stub
		return null;
	}

	public CyberpunkWeapon createThrow() {
		return new MartialWeapon("Karate Throw", DESCRIPTION, CyberpunkSkill.KARATE, 0, CONCEALABILITY,
				AVAILABILITY, THROW_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createHold() {
		return new MartialWeapon("Karate Hold", DESCRIPTION, CyberpunkSkill.KARATE, 0, CONCEALABILITY, AVAILABILITY,
				HOLD_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createEscape() {
		return new MartialWeapon("Karate Escape", DESCRIPTION, CyberpunkSkill.KARATE, 0, CONCEALABILITY,
				AVAILABILITY, ESCAPE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createChoke() {
		return new MartialWeapon("Karate Choke", DESCRIPTION, CyberpunkSkill.KARATE, 0, CONCEALABILITY,
				AVAILABILITY, CHOKE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createSweep() {
		return new MartialWeapon("Karate Sweep", DESCRIPTION, CyberpunkSkill.KARATE, 0, CONCEALABILITY,
				AVAILABILITY, SWEEP_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createGrapple() {
		return new MartialWeapon("Karate Grapple", DESCRIPTION, CyberpunkSkill.KARATE, 0, CONCEALABILITY,
				AVAILABILITY, GRAPPLE_DAMAGE, RELIABILITY);
	}
}
