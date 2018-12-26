package rpg.cyberpunk._2020.combat;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;

public class AnimalKungFuFightingStyleFactory implements AbstractFightingStyleFactory {
	public static final String DESCRIPTION = "These forms based on animal movements, such as crane, mantis, tiger, leopard and dragon forms." //
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

	public CyberpunkWeapon createStrike() {
		return new MartialWeapon("Animal Kung Fu Strike", DESCRIPTION, CyberpunkSkill.ANIMAL_KUNG_FU, 2,
				CONCEALABILITY, AVAILABILITY, STRIKE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createKick() {
		return new MartialWeapon("Animal Kung Fu Kick", DESCRIPTION, CyberpunkSkill.ANIMAL_KUNG_FU, 2,
				CONCEALABILITY, AVAILABILITY, KICK_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createBlock() {
		return new MartialWeapon("Animal Kung Fu Block", DESCRIPTION, CyberpunkSkill.ANIMAL_KUNG_FU, 2,
				CONCEALABILITY, AVAILABILITY, BLOCK_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createDodge() {
		return new MartialWeapon("Animal Kung Fu Dodge", DESCRIPTION, CyberpunkSkill.ANIMAL_KUNG_FU, 0,
				CONCEALABILITY, AVAILABILITY, DODGE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createDisarm() {
		// TODO Auto-generated method stub
		return null;
	}

	public CyberpunkWeapon createThrow() {
		return new MartialWeapon("Animal Kung Fu Throw", DESCRIPTION, CyberpunkSkill.ANIMAL_KUNG_FU, 0,
				CONCEALABILITY, AVAILABILITY, THROW_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createHold() {
		return new MartialWeapon("Animal Kung Fu Hold", DESCRIPTION, CyberpunkSkill.ANIMAL_KUNG_FU, 0,
				CONCEALABILITY, AVAILABILITY, HOLD_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createEscape() {
		return new MartialWeapon("Animal Kung Fu Escape", DESCRIPTION, CyberpunkSkill.ANIMAL_KUNG_FU, 0,
				CONCEALABILITY, AVAILABILITY, ESCAPE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createChoke() {
		return new MartialWeapon("Animal Kung Fu Choke", DESCRIPTION, CyberpunkSkill.ANIMAL_KUNG_FU, 0,
				CONCEALABILITY, AVAILABILITY, CHOKE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createSweep() {
		return new MartialWeapon("Animal Kung Fu Sweep", DESCRIPTION, CyberpunkSkill.ANIMAL_KUNG_FU, 1,
				CONCEALABILITY, AVAILABILITY, SWEEP_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createGrapple() {
		return new MartialWeapon("Animal Kung Fu Grapple", DESCRIPTION, CyberpunkSkill.ANIMAL_KUNG_FU, 0,
				CONCEALABILITY, AVAILABILITY, GRAPPLE_DAMAGE, RELIABILITY);
	}

}
