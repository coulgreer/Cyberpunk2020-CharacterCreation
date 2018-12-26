package rpg.cyberpunk._2020.combat;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;

public class JudoFightingStyleFactory implements AbstractFightingStyleFactory {
	public static final String DESCRIPTION = "This system was designed as a sport form, but is very effective in combat as well." //
			+ " It uses throws and sweeps to knock down the opponent.";

	private static JudoFightingStyleFactory uniqueInstance;

	private JudoFightingStyleFactory() {
	}

	public static JudoFightingStyleFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new JudoFightingStyleFactory();
		}
		return uniqueInstance;
	}

	public CyberpunkWeapon createStrike() {
		return new MartialWeapon("Judo Strike", DESCRIPTION, CyberpunkSkill.JUDO, 0, CONCEALABILITY, AVAILABILITY,
				STRIKE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createKick() {
		return new MartialWeapon("Judo Kick", DESCRIPTION, CyberpunkSkill.JUDO, 0, CONCEALABILITY, AVAILABILITY,
				KICK_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createBlock() {
		return new MartialWeapon("Judo Block", DESCRIPTION, CyberpunkSkill.JUDO, 0, CONCEALABILITY, AVAILABILITY,
				BLOCK_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createDodge() {
		return new MartialWeapon("Judo Dodge", DESCRIPTION, CyberpunkSkill.JUDO, 1, CONCEALABILITY, AVAILABILITY,
				DODGE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createDisarm() {
		// TODO Auto-generated method stub
		return null;
	}

	public CyberpunkWeapon createThrow() {
		return new MartialWeapon("Judo Throw", DESCRIPTION, CyberpunkSkill.JUDO, 3, CONCEALABILITY, AVAILABILITY,
				THROW_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createHold() {
		return new MartialWeapon("Judo Hold", DESCRIPTION, CyberpunkSkill.JUDO, 2, CONCEALABILITY, AVAILABILITY,
				HOLD_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createEscape() {
		return new MartialWeapon("Judo Escape", DESCRIPTION, CyberpunkSkill.JUDO, 2, CONCEALABILITY, AVAILABILITY,
				ESCAPE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createChoke() {
		return new MartialWeapon("Judo Choke", DESCRIPTION, CyberpunkSkill.JUDO, 0, CONCEALABILITY, AVAILABILITY,
				CHOKE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createSweep() {
		return new MartialWeapon("Judo Sweep", DESCRIPTION, CyberpunkSkill.JUDO, 2, CONCEALABILITY, AVAILABILITY,
				SWEEP_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createGrapple() {
		return new MartialWeapon("Judo Grapple", DESCRIPTION, CyberpunkSkill.JUDO, 2, CONCEALABILITY, AVAILABILITY,
				GRAPPLE_DAMAGE, RELIABILITY);
	}
}
