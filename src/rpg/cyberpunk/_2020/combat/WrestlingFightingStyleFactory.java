package rpg.cyberpunk._2020.combat;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;

public class WrestlingFightingStyleFactory implements AbstractFightingStyleFactory {
	public static final String DESCRIPTION = "This form combines techniques of Olympic and Professional wrestling." //
			+ " The style uses a wide variety of throws and holds to incapacitate the opponent.";

	private static WrestlingFightingStyleFactory uniqueInstance;

	private WrestlingFightingStyleFactory() {
	}

	public static WrestlingFightingStyleFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new WrestlingFightingStyleFactory();
		}
		return uniqueInstance;
	}

	public CyberpunkWeapon createStrike() {
		return new MartialWeapon("Wrestling Strike", DESCRIPTION, CyberpunkSkill.WRESTLING, 0, CONCEALABILITY,
				AVAILABILITY, STRIKE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createKick() {
		return new MartialWeapon("Wrestling Kick", DESCRIPTION, CyberpunkSkill.WRESTLING, 0, CONCEALABILITY,
				AVAILABILITY, KICK_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createBlock() {
		return new MartialWeapon("Wrestling Block", DESCRIPTION, CyberpunkSkill.WRESTLING, 0, CONCEALABILITY,
				AVAILABILITY, BLOCK_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createDodge() {
		return new MartialWeapon("Wrestling Dodge", DESCRIPTION, CyberpunkSkill.WRESTLING, 0, CONCEALABILITY,
				AVAILABILITY, DODGE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createDisarm() {
		// TODO Auto-generated method stub
		return null;
	}

	public CyberpunkWeapon createThrow() {
		return new MartialWeapon("Wrestling Throw", DESCRIPTION, CyberpunkSkill.WRESTLING, 3, CONCEALABILITY,
				AVAILABILITY, THROW_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createHold() {
		return new MartialWeapon("Wrestling Hold", DESCRIPTION, CyberpunkSkill.WRESTLING, 4, CONCEALABILITY,
				AVAILABILITY, HOLD_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createEscape() {
		return new MartialWeapon("Wrestling Escape", DESCRIPTION, CyberpunkSkill.WRESTLING, 4, CONCEALABILITY,
				AVAILABILITY, ESCAPE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createChoke() {
		return new MartialWeapon("Wrestling Choke", DESCRIPTION, CyberpunkSkill.WRESTLING, 2, CONCEALABILITY,
				AVAILABILITY, CHOKE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createSweep() {
		return new MartialWeapon("Wrestling Sweep", DESCRIPTION, CyberpunkSkill.WRESTLING, 2, CONCEALABILITY,
				AVAILABILITY, SWEEP_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createGrapple() {
		return new MartialWeapon("Wrestling Grapple", DESCRIPTION, CyberpunkSkill.WRESTLING, 4, CONCEALABILITY,
				AVAILABILITY, GRAPPLE_DAMAGE, RELIABILITY);
	}
}
