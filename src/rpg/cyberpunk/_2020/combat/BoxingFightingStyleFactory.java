package rpg.cyberpunk._2020.combat;

import java.util.Collections;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;

public class BoxingFightingStyleFactory implements AbstractFightingStyleFactory {
	protected static final String DESCRIPTION = "The manly art of fisticuffs, this form delivers lightning punches and a tight blocking defense.";

	private static BoxingFightingStyleFactory uniqueInstance;

	private BoxingFightingStyleFactory() {
	}

	public static BoxingFightingStyleFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new BoxingFightingStyleFactory();
		}
		return uniqueInstance;
	}

	@Override
	public CyberpunkWeapon createStrike() {
		return new MeleeWeapon("Boxing Strike", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.BOXING,
				3, CONCEALABILITY, AVAILABILITY, STRIKE_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createKick() {
		return new MeleeWeapon("Boxing Kick", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.BOXING,
				0, CONCEALABILITY, AVAILABILITY, KICK_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createBlock() {
		return new MeleeWeapon("Boxing Block", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.BOXING,
				3, CONCEALABILITY, AVAILABILITY, BLOCK_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createDodge() {
		return new MeleeWeapon("Boxing Dodge", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.BOXING,
				1, CONCEALABILITY, AVAILABILITY, DODGE_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createDisarm() {
		return new MeleeWeapon("Boxing Disarm", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.BOXING,
				0, CONCEALABILITY, AVAILABILITY, DISARM_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createThrow() {
		return new MeleeWeapon("Boxing Throw", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.BOXING,
				0, CONCEALABILITY, AVAILABILITY, THROW_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createHold() {
		return new MeleeWeapon("Boxing Hold", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.BOXING,
				0, CONCEALABILITY, AVAILABILITY, HOLD_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createEscape() {
		return new MeleeWeapon("Boxing Escape", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.BOXING,
				0, CONCEALABILITY, AVAILABILITY, ESCAPE_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createChoke() {
		return new MeleeWeapon("Boxing Choke", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.BOXING,
				0, CONCEALABILITY, AVAILABILITY, CHOKE_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createSweep() {
		return new MeleeWeapon("Boxing Sweep", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.BOXING,
				0, CONCEALABILITY, AVAILABILITY, SWEEP_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createGrapple() {
		return new MeleeWeapon("Boxing Grapple", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.BOXING, 0, CONCEALABILITY, AVAILABILITY, GRAPPLE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

}
