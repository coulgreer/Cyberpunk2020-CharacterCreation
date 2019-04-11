package rpg.cyberpunk._2020.combat;

import java.util.Collections;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;

public class AikidoFightingStyleFactory implements FightingStyleFactory {
	protected static final String DESCRIPTION = "This form relies on using the opponent's strength and momentum against him." //
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

	@Override
	public CyberpunkWeapon createStrike() {
		return new MeleeWeapon("Aikido Strike", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.AIKIDO,
				0, CONCEALABILITY, AVAILABILITY, STRIKE_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createKick() {
		return new MeleeWeapon("Aikido Kick", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.AIKIDO,
				0, CONCEALABILITY, AVAILABILITY, KICK_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createBlock() {
		return new MeleeWeapon("Aikido Block", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.AIKIDO,
				4, CONCEALABILITY, AVAILABILITY, BLOCK_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createDodge() {
		return new MeleeWeapon("Aikido Dodge", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.AIKIDO,
				3, CONCEALABILITY, AVAILABILITY, DODGE_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createDisarm() {
		return new MeleeWeapon("Aikido Disarm", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.AIKIDO,
				0, CONCEALABILITY, AVAILABILITY, DISARM_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createThrow() {
		return new MeleeWeapon("Aikido Throw", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.AIKIDO,
				3, CONCEALABILITY, AVAILABILITY, THROW_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createHold() {
		return new MeleeWeapon("Aikido Hold", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.AIKIDO,
				3, CONCEALABILITY, AVAILABILITY, HOLD_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createEscape() {
		return new MeleeWeapon("Aikido Escape", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.AIKIDO,
				3, CONCEALABILITY, AVAILABILITY, ESCAPE_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createChoke() {
		return new MeleeWeapon("Aikido Choke", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.AIKIDO,
				1, CONCEALABILITY, AVAILABILITY, CHOKE_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createSweep() {
		return new MeleeWeapon("Aikido Sweep", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.AIKIDO,
				3, CONCEALABILITY, AVAILABILITY, SWEEP_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createGrapple() {
		return new MeleeWeapon("Aikido Grapple", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.AIKIDO, 2, CONCEALABILITY, AVAILABILITY, GRAPPLE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

}
