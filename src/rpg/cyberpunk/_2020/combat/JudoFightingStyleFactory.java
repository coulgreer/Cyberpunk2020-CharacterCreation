package rpg.cyberpunk._2020.combat;

import java.util.Collections;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;

public class JudoFightingStyleFactory implements AbstractFightingStyleFactory {
	protected static final String DESCRIPTION = "This system was designed as a sport form, but is very effective in combat as well." //
			+ " It uses throws and sweeps to knock down the opponent.";

	private static JudoFightingStyleFactory uniqueInstance;

	private JudoFightingStyleFactory() {
	}

	public static JudoFightingStyleFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new JudoFightingStyleFactory();
		}
		return uniqueInstance;
	}

	@Override
	public CyberpunkWeapon createStrike() {
		return new MeleeWeapon("Judo Strike", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.JUDO, 0,
				CONCEALABILITY, AVAILABILITY, STRIKE_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createKick() {
		return new MeleeWeapon("Judo Kick", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.JUDO, 0,
				CONCEALABILITY, AVAILABILITY, KICK_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createBlock() {
		return new MeleeWeapon("Judo Block", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.JUDO, 0,
				CONCEALABILITY, AVAILABILITY, BLOCK_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createDodge() {
		return new MeleeWeapon("Judo Dodge", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.JUDO, 1,
				CONCEALABILITY, AVAILABILITY, DODGE_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createDisarm() {
		return new MeleeWeapon("Judo Disarm", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.JUDO, 0,
				CONCEALABILITY, AVAILABILITY, DISARM_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createThrow() {
		return new MeleeWeapon("Judo Throw", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.JUDO, 3,
				CONCEALABILITY, AVAILABILITY, THROW_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createHold() {
		return new MeleeWeapon("Judo Hold", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.JUDO, 2,
				CONCEALABILITY, AVAILABILITY, HOLD_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createEscape() {
		return new MeleeWeapon("Judo Escape", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.JUDO, 2,
				CONCEALABILITY, AVAILABILITY, ESCAPE_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createChoke() {
		return new MeleeWeapon("Judo Choke", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.JUDO, 0,
				CONCEALABILITY, AVAILABILITY, CHOKE_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createSweep() {
		return new MeleeWeapon("Judo Sweep", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.JUDO, 2,
				CONCEALABILITY, AVAILABILITY, SWEEP_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createGrapple() {
		return new MeleeWeapon("Judo Grapple", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED, CyberpunkSkill.JUDO, 2,
				CONCEALABILITY, AVAILABILITY, GRAPPLE_DAMAGE, false, RELIABILITY, RANGE_MODIFIER, COST, WEIGHT,
				Collections.emptySet());
	}
}
