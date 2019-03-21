package rpg.cyberpunk._2020.combat;

import java.util.Collections;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;

public class AnimalKungFuFightingStyleFactory implements AbstractFightingStyleFactory {
	protected static final String DESCRIPTION = "These forms based on animal movements, such as crane, mantis, tiger, leopard and dragon forms." //
			+ " These attacks are fast and dangerous, with a style that is exciting and flashy.";
	private static AnimalKungFuFightingStyleFactory uniqueInstance;

	private AnimalKungFuFightingStyleFactory() {
	}

	public static AnimalKungFuFightingStyleFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new AnimalKungFuFightingStyleFactory();
		}
		return uniqueInstance;
	}

	@Override
	public CyberpunkWeapon createStrike() {
		return new MeleeWeapon("Animal Kung Fu Strike", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.ANIMAL_KUNG_FU, 2, CONCEALABILITY, AVAILABILITY, STRIKE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createKick() {
		return new MeleeWeapon("Animal Kung Fu Kick", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.ANIMAL_KUNG_FU, 2, CONCEALABILITY, AVAILABILITY, KICK_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createBlock() {
		return new MeleeWeapon("Animal Kung Fu Block", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.ANIMAL_KUNG_FU, 2, CONCEALABILITY, AVAILABILITY, BLOCK_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createDodge() {
		return new MeleeWeapon("Animal Kung Fu Dodge", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.ANIMAL_KUNG_FU, 0, CONCEALABILITY, AVAILABILITY, DODGE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createDisarm() {
		return new MeleeWeapon("Animal Kung Fu Disarm", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.ANIMAL_KUNG_FU, 0, CONCEALABILITY, AVAILABILITY, DISARM_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createThrow() {
		return new MeleeWeapon("Animal Kung Fu Throw", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.ANIMAL_KUNG_FU, 0, CONCEALABILITY, AVAILABILITY, THROW_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createHold() {
		return new MeleeWeapon("Animal Kung Fu Hold", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.ANIMAL_KUNG_FU, 0, CONCEALABILITY, AVAILABILITY, HOLD_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createEscape() {
		return new MeleeWeapon("Animal Kung Fu Escape", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.ANIMAL_KUNG_FU, 0, CONCEALABILITY, AVAILABILITY, ESCAPE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createChoke() {
		return new MeleeWeapon("Animal Kung Fu Choke", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.ANIMAL_KUNG_FU, 0, CONCEALABILITY, AVAILABILITY, CHOKE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createSweep() {
		return new MeleeWeapon("Animal Kung Fu Sweep", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.ANIMAL_KUNG_FU, 1, CONCEALABILITY, AVAILABILITY, SWEEP_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

	@Override
	public CyberpunkWeapon createGrapple() {
		return new MeleeWeapon("Animal Kung Fu Grapple", DESCRIPTION, CyberpunkWeapon.WEAPON_TYPE_UNARMED,
				CyberpunkSkill.ANIMAL_KUNG_FU, 0, CONCEALABILITY, AVAILABILITY, GRAPPLE_DAMAGE, false, RELIABILITY,
				RANGE_MODIFIER, COST, WEIGHT, Collections.emptySet());
	}

}
