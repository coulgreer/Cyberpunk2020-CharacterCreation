package rpg.cyberpunk._2020.combat;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;

public class AikidoFightingStyleFactory implements AbstractFightingStyleFactory {
	public static final String DESCRIPTION = "This form relies on using the opponent's strength and momentum against him." //
			+ " It is a perfect form for tstopping an opponent peacefully while making yourself very hard to hit.";

	private static AikidoFightingStyleFactory uniqueInstance;

	private AikidoFightingStyleFactory() {
	}

	public static AikidoFightingStyleFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new AikidoFightingStyleFactory();
		}
		return uniqueInstance;
	}

	public CyberpunkWeapon createStrike() {
		return new MartialWeapon("Aikido Strike", DESCRIPTION, CyberpunkSkill.AIKIDO, 0, CONCEALABILITY, AVAILABILITY,
				STRIKE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createKick() {
		return new MartialWeapon("Aikido Kick", DESCRIPTION, CyberpunkSkill.AIKIDO, 0, CONCEALABILITY, AVAILABILITY,
				KICK_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createBlock() {
		return new MartialWeapon("Aikido Block", DESCRIPTION, CyberpunkSkill.AIKIDO, 4, CONCEALABILITY, AVAILABILITY,
				BLOCK_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createDodge() {
		return new MartialWeapon("Aikido Dodge", DESCRIPTION, CyberpunkSkill.AIKIDO, 3, CONCEALABILITY, AVAILABILITY,
				DODGE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createDisarm() {
		// TODO Auto-generated method stub
		return null;
	}

	public CyberpunkWeapon createThrow() {
		return new MartialWeapon("Aikido Throw", DESCRIPTION, CyberpunkSkill.AIKIDO, 3, CONCEALABILITY, AVAILABILITY,
				THROW_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createHold() {
		return new MartialWeapon("Aikido Hold", DESCRIPTION, CyberpunkSkill.AIKIDO, 3, CONCEALABILITY, AVAILABILITY,
				HOLD_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createEscape() {
		return new MartialWeapon("Aikido Escape", DESCRIPTION, CyberpunkSkill.AIKIDO, 3, CONCEALABILITY, AVAILABILITY,
				ESCAPE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createChoke() {
		return new MartialWeapon("Aikido Choke", DESCRIPTION, CyberpunkSkill.AIKIDO, 1, CONCEALABILITY, AVAILABILITY,
				CHOKE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createSweep() {
		return new MartialWeapon("Aikido Sweep", DESCRIPTION, CyberpunkSkill.AIKIDO, 3, CONCEALABILITY, AVAILABILITY,
				SWEEP_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createGrapple() {
		return new MartialWeapon("Aikido Grapple", DESCRIPTION, CyberpunkSkill.AIKIDO, 2, CONCEALABILITY, AVAILABILITY,
				GRAPPLE_DAMAGE, RELIABILITY);
	}

}
