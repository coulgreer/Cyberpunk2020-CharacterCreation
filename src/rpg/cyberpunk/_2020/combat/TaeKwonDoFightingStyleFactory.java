package rpg.cyberpunk._2020.combat;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;

public class TaeKwonDoFightingStyleFactory implements AbstractFightingStyleFactory {
	public static final String DESCRIPTION = "A very fast and precise form, with graceful movements and some aerial kicks.";

	private static TaeKwonDoFightingStyleFactory uniqueInstance;

	private TaeKwonDoFightingStyleFactory() {
	}

	public static TaeKwonDoFightingStyleFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new TaeKwonDoFightingStyleFactory();
		}
		return uniqueInstance;
	}

	public CyberpunkWeapon createStrike() {
		return new MartialWeapon("Tae Kwon Do Strike", DESCRIPTION, CyberpunkSkill.TAE_KWON_DO, 3, CONCEALABILITY,
				AVAILABILITY, STRIKE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createKick() {
		return new MartialWeapon("Tae Kwon Do Kick", DESCRIPTION, CyberpunkSkill.TAE_KWON_DO, 3, CONCEALABILITY,
				AVAILABILITY, KICK_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createBlock() {
		return new MartialWeapon("Tae Kwon Do Block", DESCRIPTION, CyberpunkSkill.TAE_KWON_DO, 2, CONCEALABILITY,
				AVAILABILITY, BLOCK_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createDodge() {
		return new MartialWeapon("Tae Kwon Do Dodge", DESCRIPTION, CyberpunkSkill.TAE_KWON_DO, 1, CONCEALABILITY,
				AVAILABILITY, DODGE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createDisarm() {
		// TODO Auto-generated method stub
		return null;
	}

	public CyberpunkWeapon createThrow() {
		return new MartialWeapon("Tae Kwon Do Throw", DESCRIPTION, CyberpunkSkill.TAE_KWON_DO, 0, CONCEALABILITY,
				AVAILABILITY, THROW_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createHold() {
		return new MartialWeapon("Tae Kwon Do Hold", DESCRIPTION, CyberpunkSkill.TAE_KWON_DO, 0, CONCEALABILITY,
				AVAILABILITY, HOLD_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createEscape() {
		return new MartialWeapon("Tae Kwon Do Escape", DESCRIPTION, CyberpunkSkill.TAE_KWON_DO, 0, CONCEALABILITY,
				AVAILABILITY, ESCAPE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createChoke() {
		return new MartialWeapon("Tae Kwon Do Choke", DESCRIPTION, CyberpunkSkill.TAE_KWON_DO, 0, CONCEALABILITY,
				AVAILABILITY, CHOKE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createSweep() {
		return new MartialWeapon("Tae Kwon Do Sweep", DESCRIPTION, CyberpunkSkill.TAE_KWON_DO, 2, CONCEALABILITY,
				AVAILABILITY, SWEEP_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createGrapple() {
		return new MartialWeapon("Tae Kwon Do Grapple", DESCRIPTION, CyberpunkSkill.TAE_KWON_DO, 0, CONCEALABILITY,
				AVAILABILITY, GRAPPLE_DAMAGE, RELIABILITY);
	}

}
