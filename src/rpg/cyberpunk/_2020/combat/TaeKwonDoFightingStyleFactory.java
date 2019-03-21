package rpg.cyberpunk._2020.combat;

import java.util.Collections;

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

	@Override
	public CyberpunkWeapon createStrike() {
		return new MeleeWeapon("Tae Kwon Do Strike", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.TAE_KWON_DO, 3, CONCEALABILITY, AVAILABILITY, STRIKE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createKick() {
		return new MeleeWeapon("Tae Kwon Do Kick", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.TAE_KWON_DO, 3, CONCEALABILITY, AVAILABILITY, KICK_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createBlock() {
		return new MeleeWeapon("Tae Kwon Do Block", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.TAE_KWON_DO, 2, CONCEALABILITY, AVAILABILITY, BLOCK_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createDodge() {
		return new MeleeWeapon("Tae Kwon Do Dodge", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.TAE_KWON_DO, 1, CONCEALABILITY, AVAILABILITY, DODGE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createDisarm() {
		return new MeleeWeapon("Tae Kwon Do Disarm", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.TAE_KWON_DO, 0, CONCEALABILITY, AVAILABILITY, DISARM_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createThrow() {
		return new MeleeWeapon("Tae Kwon Do Throw", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.TAE_KWON_DO, 0, CONCEALABILITY, AVAILABILITY, THROW_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createHold() {
		return new MeleeWeapon("Tae Kwon Do Hold", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.TAE_KWON_DO, 0, CONCEALABILITY, AVAILABILITY, HOLD_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createEscape() {
		return new MeleeWeapon("Tae Kwon Do Escape", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.TAE_KWON_DO, 0, CONCEALABILITY, AVAILABILITY, ESCAPE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createChoke() {
		return new MeleeWeapon("Tae Kwon Do Choke", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.TAE_KWON_DO, 0, CONCEALABILITY, AVAILABILITY, CHOKE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createSweep() {
		return new MeleeWeapon("Tae Kwon Do Sweep", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.TAE_KWON_DO, 2, CONCEALABILITY, AVAILABILITY, SWEEP_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createGrapple() {
		return new MeleeWeapon("Tae Kwon Do Grapple", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.TAE_KWON_DO, 0, CONCEALABILITY, AVAILABILITY, GRAPPLE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

}
