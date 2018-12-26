package rpg.cyberpunk._2020.combat;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;

public class ThaiKickBoxingFightingStyleFactory implements AbstractFightingStyleFactory {
	public static final String DESCRIPTION = "One of the deadliest forms in existence, this style is known for blinding kicks delivered with incredible power.";

	private static ThaiKickBoxingFightingStyleFactory uniqueInstance;

	private ThaiKickBoxingFightingStyleFactory() {
	}

	public static ThaiKickBoxingFightingStyleFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ThaiKickBoxingFightingStyleFactory();
		}
		return uniqueInstance;
	}

	public CyberpunkWeapon createStrike() {
		return new MartialWeapon("Thai Boxing Strike", DESCRIPTION, CyberpunkSkill.THAI_KICK_BOXING, 3,
				CONCEALABILITY, AVAILABILITY, STRIKE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createKick() {
		return new MartialWeapon("Thai Boxing Kick", DESCRIPTION, CyberpunkSkill.THAI_KICK_BOXING, 3,
				CONCEALABILITY, AVAILABILITY, KICK_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createBlock() {
		return new MartialWeapon("Thai Boxing Block", DESCRIPTION, CyberpunkSkill.THAI_KICK_BOXING, 2,
				CONCEALABILITY, AVAILABILITY, BLOCK_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createDodge() {
		return new MartialWeapon("Thai Boxing Dodge", DESCRIPTION, CyberpunkSkill.THAI_KICK_BOXING, 0,
				CONCEALABILITY, AVAILABILITY, DODGE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createDisarm() {
		// TODO Auto-generated method stub
		return null;
	}

	public CyberpunkWeapon createThrow() {
		return new MartialWeapon("Thai Boxing Throw", DESCRIPTION, CyberpunkSkill.THAI_KICK_BOXING, 0,
				CONCEALABILITY, AVAILABILITY, THROW_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createHold() {
		return new MartialWeapon("Thai Boxing Hold", DESCRIPTION, CyberpunkSkill.THAI_KICK_BOXING, 0,
				CONCEALABILITY, AVAILABILITY, HOLD_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createEscape() {
		return new MartialWeapon("Thai Boxing Escape", DESCRIPTION, CyberpunkSkill.THAI_KICK_BOXING, 0,
				CONCEALABILITY, AVAILABILITY, ESCAPE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createChoke() {
		return new MartialWeapon("Thai Boxing Choke", DESCRIPTION, CyberpunkSkill.THAI_KICK_BOXING, 0,
				CONCEALABILITY, AVAILABILITY, CHOKE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createSweep() {
		return new MartialWeapon("Thai Boxing Sweep", DESCRIPTION, CyberpunkSkill.THAI_KICK_BOXING, 0,
				CONCEALABILITY, AVAILABILITY, SWEEP_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createGrapple() {
		return new MartialWeapon("Thai Boxing Grapple", DESCRIPTION, CyberpunkSkill.THAI_KICK_BOXING, 1,
				CONCEALABILITY, AVAILABILITY, GRAPPLE_DAMAGE, RELIABILITY);
	}

}
