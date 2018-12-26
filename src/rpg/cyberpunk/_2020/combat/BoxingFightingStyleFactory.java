package rpg.cyberpunk._2020.combat;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;

public class BoxingFightingStyleFactory implements AbstractFightingStyleFactory {
	public static final String DESCRIPTION = "The manly art of fisticuffs, this form delivers lightning punches and a tight blocking defense.";

	private static BoxingFightingStyleFactory uniqueInstance;

	private BoxingFightingStyleFactory() {
	}

	public static BoxingFightingStyleFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new BoxingFightingStyleFactory();
		}
		return uniqueInstance;
	}

	public CyberpunkWeapon createStrike() {
		return new MartialWeapon("Boxing Strike", DESCRIPTION, CyberpunkSkill.BOXING, 3, CONCEALABILITY, AVAILABILITY,
				STRIKE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createKick() {
		return new MartialWeapon("Boxing Kick", DESCRIPTION, CyberpunkSkill.BOXING, 0, CONCEALABILITY, AVAILABILITY,
				KICK_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createBlock() {
		return new MartialWeapon("Boxing Block", DESCRIPTION, CyberpunkSkill.BOXING, 3, CONCEALABILITY, AVAILABILITY,
				BLOCK_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createDodge() {
		return new MartialWeapon("Boxing Dodge", DESCRIPTION, CyberpunkSkill.BOXING, 1, CONCEALABILITY, AVAILABILITY,
				DODGE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createDisarm() {
		// TODO Auto-generated method stub
		return null;
	}

	public CyberpunkWeapon createThrow() {
		return new MartialWeapon("Boxing Throw", DESCRIPTION, CyberpunkSkill.BOXING, 0, CONCEALABILITY, AVAILABILITY,
				THROW_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createHold() {
		return new MartialWeapon("Boxing Hold", DESCRIPTION, CyberpunkSkill.BOXING, 0, CONCEALABILITY, AVAILABILITY,
				HOLD_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createEscape() {
		return new MartialWeapon("Boxing Escape", DESCRIPTION, CyberpunkSkill.BOXING, 0, CONCEALABILITY, AVAILABILITY,
				ESCAPE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createChoke() {
		return new MartialWeapon("Boxing Choke", DESCRIPTION, CyberpunkSkill.BOXING, 0, CONCEALABILITY, AVAILABILITY,
				CHOKE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createSweep() {
		return new MartialWeapon("Boxing Sweep", DESCRIPTION, CyberpunkSkill.BOXING, 0, CONCEALABILITY, AVAILABILITY,
				SWEEP_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createGrapple() {
		return new MartialWeapon("Boxing Grapple", DESCRIPTION, CyberpunkSkill.BOXING, 0, CONCEALABILITY, AVAILABILITY,
				GRAPPLE_DAMAGE, RELIABILITY);
	}

}
