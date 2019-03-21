package rpg.cyberpunk._2020.combat;

import java.util.Collections;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;

public class BrawlingFightingStyleFactory implements AbstractFightingStyleFactory {
	protected static final String DESCRIPTION = "The skill of fighting man to man with fist, feet and other parts of the body.";

	private static BrawlingFightingStyleFactory uniqueInstance;

	private BrawlingFightingStyleFactory() {
	}

	public static BrawlingFightingStyleFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new BrawlingFightingStyleFactory();
		}
		return uniqueInstance;
	}

	@Override
	public CyberpunkWeapon createStrike() {
		return new MeleeWeapon("Brawling Strike", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.BRAWLING, 0, CONCEALABILITY, AVAILABILITY, STRIKE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createKick() {
		return new MeleeWeapon("Brawling Kick", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.BRAWLING, 0, CONCEALABILITY, AVAILABILITY, KICK_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createBlock() {
		return new MeleeWeapon("Brawling Block", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.BRAWLING, 0, CONCEALABILITY, AVAILABILITY, BLOCK_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createDodge() {
		return new MeleeWeapon("Brawling Dodge", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.BRAWLING, 0, CONCEALABILITY, AVAILABILITY, DODGE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createDisarm() {
		return new MeleeWeapon("Brawling Disarm", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.BRAWLING, 0, CONCEALABILITY, AVAILABILITY, DISARM_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createThrow() {
		return new MeleeWeapon("Brawling Throw", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.BRAWLING, 0, CONCEALABILITY, AVAILABILITY, THROW_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createHold() {
		return new MeleeWeapon("Brawling Hold", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.BRAWLING, 0, CONCEALABILITY, AVAILABILITY, HOLD_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createEscape() {
		return new MeleeWeapon("Brawling Escape", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.BRAWLING, 0, CONCEALABILITY, AVAILABILITY, ESCAPE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createChoke() {
		return new MeleeWeapon("Brawling Choke", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.BRAWLING, 0, CONCEALABILITY, AVAILABILITY, CHOKE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createSweep() {
		return new MeleeWeapon("Brawling Sweep", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.BRAWLING, 0, CONCEALABILITY, AVAILABILITY, SWEEP_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createGrapple() {
		return new MeleeWeapon("Brawling Grapple", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.BRAWLING, 0, CONCEALABILITY, AVAILABILITY, GRAPPLE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

}
