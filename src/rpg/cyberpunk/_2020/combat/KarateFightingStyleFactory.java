package rpg.cyberpunk._2020.combat;

import java.util.Collections;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;

public class KarateFightingStyleFactory implements AbstractFightingStyleFactory {
	protected static final String DESCRIPTION = "The Japanese version of kung fu, this style uses straight line movements and powerful blows." //
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

	@Override
	public CyberpunkWeapon createStrike() {
		return new MeleeWeapon("Karate Strike", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.KARATE,
				2, CONCEALABILITY, AVAILABILITY, STRIKE_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createKick() {
		return new MeleeWeapon("Karate Kick", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.KARATE,
				2, CONCEALABILITY, AVAILABILITY, KICK_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createBlock() {
		return new MeleeWeapon("Karate Block", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.KARATE,
				2, CONCEALABILITY, AVAILABILITY, BLOCK_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createDodge() {
		return new MeleeWeapon("Karate Dodge", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.KARATE,
				0, CONCEALABILITY, AVAILABILITY, DODGE_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createDisarm() {
		return new MeleeWeapon("Karate Disarm", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.KARATE,
				0, CONCEALABILITY, AVAILABILITY, DISARM_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createThrow() {
		return new MeleeWeapon("Karate Throw", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.KARATE,
				0, CONCEALABILITY, AVAILABILITY, THROW_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createHold() {
		return new MeleeWeapon("Karate Hold", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.KARATE,
				0, CONCEALABILITY, AVAILABILITY, HOLD_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createEscape() {
		return new MeleeWeapon("Karate Escape", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.KARATE,
				0, CONCEALABILITY, AVAILABILITY, ESCAPE_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createChoke() {
		return new MeleeWeapon("Karate Choke", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.KARATE,
				0, CONCEALABILITY, AVAILABILITY, CHOKE_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createSweep() {
		return new MeleeWeapon("Karate Sweep", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.KARATE,
				0, CONCEALABILITY, AVAILABILITY, SWEEP_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createGrapple() {
		return new MeleeWeapon("Karate Grapple", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.KARATE, 0, CONCEALABILITY, AVAILABILITY, GRAPPLE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}
}
