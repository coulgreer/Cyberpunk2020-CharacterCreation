package rpg.cyberpunk._2020.combat;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;

public class CapoeriaFightingStyleFactory implements AbstractFightingStyleFactory {
	public static final String DESCRIPTION = "Created by Carribean slaves, this form combines dancelike movements with fast kicks and low line sweeps." //
			+ " It is a relatively unknown form and can be combined with dance moves to disguise it's true power.";

	private static CapoeriaFightingStyleFactory uniqueInstance;

	private CapoeriaFightingStyleFactory() {
	}

	public static CapoeriaFightingStyleFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new CapoeriaFightingStyleFactory();
		}
		return uniqueInstance;
	}

	public CyberpunkWeapon createStrike() {
		return new MartialWeapon("Capoeria Strike", DESCRIPTION, CyberpunkSkill.CAPOERIA, 1, CONCEALABILITY,
				AVAILABILITY, STRIKE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createKick() {
		return new MartialWeapon("Capoeria Kick", DESCRIPTION, CyberpunkSkill.CAPOERIA, 2, CONCEALABILITY,
				AVAILABILITY, KICK_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createBlock() {
		return new MartialWeapon("Capoeria Block", DESCRIPTION, CyberpunkSkill.CAPOERIA, 2, CONCEALABILITY,
				AVAILABILITY, BLOCK_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createDodge() {
		return new MartialWeapon("Capoeria Dodge", DESCRIPTION, CyberpunkSkill.CAPOERIA, 2, CONCEALABILITY,
				AVAILABILITY, DODGE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createDisarm() {
		// TODO Auto-generated method stub
		return null;
	}

	public CyberpunkWeapon createThrow() {
		return new MartialWeapon("Capoeria Throw", DESCRIPTION, CyberpunkSkill.CAPOERIA, 0, CONCEALABILITY,
				AVAILABILITY, THROW_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createHold() {
		return new MartialWeapon("Capoeria Hold", DESCRIPTION, CyberpunkSkill.CAPOERIA, 0, CONCEALABILITY,
				AVAILABILITY, HOLD_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createEscape() {
		return new MartialWeapon("Capoeria Escape", DESCRIPTION, CyberpunkSkill.CAPOERIA, 0, CONCEALABILITY,
				AVAILABILITY, ESCAPE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createChoke() {
		return new MartialWeapon("Capoeria Choke", DESCRIPTION, CyberpunkSkill.CAPOERIA, 0, CONCEALABILITY,
				AVAILABILITY, CHOKE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createSweep() {
		return new MartialWeapon("Capoeria Sweep", DESCRIPTION, CyberpunkSkill.CAPOERIA, 3, CONCEALABILITY,
				AVAILABILITY, SWEEP_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createGrapple() {
		return new MartialWeapon("Capoeria Grapple", DESCRIPTION, CyberpunkSkill.CAPOERIA, 0, CONCEALABILITY,
				AVAILABILITY, GRAPPLE_DAMAGE, RELIABILITY);
	}

}
