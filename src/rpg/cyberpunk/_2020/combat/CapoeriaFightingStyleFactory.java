package rpg.cyberpunk._2020.combat;

import java.util.Collections;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;

public class CapoeriaFightingStyleFactory implements FightingStyleFactory {
	protected static final String DESCRIPTION = "Created by Carribean slaves, this form combines dancelike movements with fast kicks and low line sweeps." //
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

	@Override
	public CyberpunkWeapon createStrike() {
		return new MeleeWeapon("Capoeria Strike", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.MARTIAL_ARTS_CAPOERIA, 1, CONCEALABILITY, AVAILABILITY, STRIKE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createKick() {
		return new MeleeWeapon("Capoeria Kick", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.MARTIAL_ARTS_CAPOERIA, 2, CONCEALABILITY, AVAILABILITY, KICK_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createBlock() {
		return new MeleeWeapon("Capoeria Block", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.MARTIAL_ARTS_CAPOERIA, 2, CONCEALABILITY, AVAILABILITY, BLOCK_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createDodge() {
		return new MeleeWeapon("Capoeria Dodge", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.MARTIAL_ARTS_CAPOERIA, 2, CONCEALABILITY, AVAILABILITY, DODGE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createDisarm() {
		return new MeleeWeapon("Capoeria Disarm", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.MARTIAL_ARTS_CAPOERIA, 0, CONCEALABILITY, AVAILABILITY, DISARM_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createThrow() {
		return new MeleeWeapon("Capoeria Throw", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.MARTIAL_ARTS_CAPOERIA, 0, CONCEALABILITY, AVAILABILITY, THROW_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createHold() {
		return new MeleeWeapon("Capoeria Hold", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.MARTIAL_ARTS_CAPOERIA, 0, CONCEALABILITY, AVAILABILITY, HOLD_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createEscape() {
		return new MeleeWeapon("Capoeria Escape", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.MARTIAL_ARTS_CAPOERIA, 0, CONCEALABILITY, AVAILABILITY, ESCAPE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createChoke() {
		return new MeleeWeapon("Capoeria Choke", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.MARTIAL_ARTS_CAPOERIA, 0, CONCEALABILITY, AVAILABILITY, CHOKE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createSweep() {
		return new MeleeWeapon("Capoeria Sweep", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.MARTIAL_ARTS_CAPOERIA, 3, CONCEALABILITY, AVAILABILITY, SWEEP_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createGrapple() {
		return new MeleeWeapon("Capoeria Grapple", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.MARTIAL_ARTS_CAPOERIA, 0, CONCEALABILITY, AVAILABILITY, GRAPPLE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

}
