package rpg.cyberpunk._2020.combat;

import java.util.Collections;

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

	@Override
	public CyberpunkWeapon createStrike() {
		return new MeleeWeapon("Wrestling Strike", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.WRESTLING, 0, CONCEALABILITY, AVAILABILITY, STRIKE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createKick() {
		return new MeleeWeapon("Wrestling Kick", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.WRESTLING, 0, CONCEALABILITY, AVAILABILITY, KICK_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createBlock() {
		return new MeleeWeapon("Wrestling Block", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.WRESTLING, 0, CONCEALABILITY, AVAILABILITY, BLOCK_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createDodge() {
		return new MeleeWeapon("Wrestling Dodge", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.WRESTLING, 0, CONCEALABILITY, AVAILABILITY, DODGE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createDisarm() {
		return new MeleeWeapon("Wrestling Disarm", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.WRESTLING, 0, CONCEALABILITY, AVAILABILITY, DISARM_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createThrow() {
		return new MeleeWeapon("Wrestling Throw", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.WRESTLING, 3, CONCEALABILITY, AVAILABILITY, THROW_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createHold() {
		return new MeleeWeapon("Wrestling Hold", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.WRESTLING, 4, CONCEALABILITY, AVAILABILITY, HOLD_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createEscape() {
		return new MeleeWeapon("Wrestling Escape", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.WRESTLING, 4, CONCEALABILITY, AVAILABILITY, ESCAPE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createChoke() {
		return new MeleeWeapon("Wrestling Choke", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.WRESTLING, 2, CONCEALABILITY, AVAILABILITY, CHOKE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createSweep() {
		return new MeleeWeapon("Wrestling Sweep", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.WRESTLING, 2, CONCEALABILITY, AVAILABILITY, SWEEP_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createGrapple() {
		return new MeleeWeapon("Wrestling Grapple", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.WRESTLING, 4, CONCEALABILITY, AVAILABILITY, GRAPPLE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}
}
