package rpg.cyberpunk._2020.combat;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;

public class BrawlingFightingStyleFactory implements AbstractFightingStyleFactory {
	public static final String DESCRIPTION = "The skill of fighting man to man with fist, feet and other parts of the body.";
	public static final int DEFAULT_RANGE = 1;
	public static final double DEFAULT_WEIGHT = 0.0;
	public static final int DEFAULT_COST = 0;

	private static BrawlingFightingStyleFactory uniqueInstance;

	private BrawlingFightingStyleFactory() {
	}

	public static BrawlingFightingStyleFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new BrawlingFightingStyleFactory();
		}
		return uniqueInstance;
	}

	public CyberpunkWeapon createStrike() {
		return SimpleWeapon.createSkillBasedWeapon("Brawling Strike", DESCRIPTION, CyberpunkSkill.BRAWLING, 0,
				CONCEALABILITY, AVAILABILITY, STRIKE_DAMAGE, RELIABILITY, DEFAULT_RANGE, DEFAULT_WEIGHT, DEFAULT_COST);
	}

	public CyberpunkWeapon createKick() {
		return SimpleWeapon.createSkillBasedWeapon("Brawling Kick", DESCRIPTION, CyberpunkSkill.BRAWLING, 0,
				CONCEALABILITY, AVAILABILITY, KICK_DAMAGE, RELIABILITY, DEFAULT_RANGE, DEFAULT_WEIGHT, DEFAULT_COST);
	}

	public CyberpunkWeapon createBlock() {
		return SimpleWeapon.createSkillBasedWeapon("Brawling Block", DESCRIPTION, CyberpunkSkill.BRAWLING, 0,
				CONCEALABILITY, AVAILABILITY, BLOCK_DAMAGE, RELIABILITY, DEFAULT_RANGE, DEFAULT_WEIGHT, DEFAULT_COST);
	}

	public CyberpunkWeapon createDodge() {
		return SimpleWeapon.createSkillBasedWeapon("Brawling Dodge", DESCRIPTION, CyberpunkSkill.BRAWLING, 0,
				CONCEALABILITY, AVAILABILITY, DODGE_DAMAGE, RELIABILITY, DEFAULT_RANGE, DEFAULT_WEIGHT, DEFAULT_COST);
	}

	public CyberpunkWeapon createDisarm() {
		// TODO Auto-generated method stub
		return null;
	}

	public CyberpunkWeapon createThrow() {
		return SimpleWeapon.createSkillBasedWeapon("Brawling Throw", DESCRIPTION, CyberpunkSkill.BRAWLING, 0,
				CONCEALABILITY, AVAILABILITY, THROW_DAMAGE, RELIABILITY, DEFAULT_RANGE, DEFAULT_WEIGHT, DEFAULT_COST);
	}

	public CyberpunkWeapon createHold() {
		return SimpleWeapon.createSkillBasedWeapon("Brawling Hold", DESCRIPTION, CyberpunkSkill.BRAWLING, 0,
				CONCEALABILITY, AVAILABILITY, HOLD_DAMAGE, RELIABILITY, DEFAULT_RANGE, DEFAULT_WEIGHT, DEFAULT_COST);
	}

	public CyberpunkWeapon createEscape() {
		return SimpleWeapon.createSkillBasedWeapon("Brawling Escape", DESCRIPTION, CyberpunkSkill.BRAWLING, 0,
				CONCEALABILITY, AVAILABILITY, ESCAPE_DAMAGE, RELIABILITY, DEFAULT_RANGE, DEFAULT_WEIGHT, DEFAULT_COST);
	}

	public CyberpunkWeapon createChoke() {
		return SimpleWeapon.createSkillBasedWeapon("Brawling Choke", DESCRIPTION, CyberpunkSkill.BRAWLING, 0,
				CONCEALABILITY, AVAILABILITY, CHOKE_DAMAGE, RELIABILITY, DEFAULT_RANGE, DEFAULT_WEIGHT, DEFAULT_COST);
	}

	public CyberpunkWeapon createSweep() {
		return SimpleWeapon.createSkillBasedWeapon("Brawling Sweep", DESCRIPTION, CyberpunkSkill.BRAWLING, 0,
				CONCEALABILITY, AVAILABILITY, SWEEP_DAMAGE, RELIABILITY, DEFAULT_RANGE, DEFAULT_WEIGHT, DEFAULT_COST);
	}

	public CyberpunkWeapon createGrapple() {
		return SimpleWeapon.createSkillBasedWeapon("Brawling Grapple", DESCRIPTION, CyberpunkSkill.BRAWLING, 0,
				CONCEALABILITY, AVAILABILITY, GRAPPLE_DAMAGE, RELIABILITY, DEFAULT_RANGE, DEFAULT_WEIGHT, DEFAULT_COST);
	}

}
