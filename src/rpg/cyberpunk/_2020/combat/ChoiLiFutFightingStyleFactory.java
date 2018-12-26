package rpg.cyberpunk._2020.combat;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;

public class ChoiLiFutFightingStyleFactory implements AbstractFightingStyleFactory {
	public static final String DESCRIPTION = "Descended directly from the ancient Shaolin temples, this form combines powerful roundhouse blows and sweeping kicks into a dynamic fighting style.";

	private static ChoiLiFutFightingStyleFactory uniqueInstance;

	private ChoiLiFutFightingStyleFactory() {
	}

	public static ChoiLiFutFightingStyleFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ChoiLiFutFightingStyleFactory();
		}
		return uniqueInstance;
	}

	public CyberpunkWeapon createStrike() {
		return new MartialWeapon("Choi Li Fut Strike", DESCRIPTION, CyberpunkSkill.CHOI_LI_FUT, 2, CONCEALABILITY,
				AVAILABILITY, STRIKE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createKick() {
		return new MartialWeapon("Choi Li Fut Kick", DESCRIPTION, CyberpunkSkill.CHOI_LI_FUT, 2, CONCEALABILITY,
				AVAILABILITY, KICK_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createBlock() {
		return new MartialWeapon("Choi Li Fut Block", DESCRIPTION, CyberpunkSkill.CHOI_LI_FUT, 2, CONCEALABILITY,
				AVAILABILITY, BLOCK_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createDodge() {
		return new MartialWeapon("Choi Li Fut Dodge", DESCRIPTION, CyberpunkSkill.CHOI_LI_FUT, 1, CONCEALABILITY,
				AVAILABILITY, DODGE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createDisarm() {
		// TODO Auto-generated method stub
		return null;
	}

	public CyberpunkWeapon createThrow() {
		return new MartialWeapon("Choi Li Fut Throw", DESCRIPTION, CyberpunkSkill.CHOI_LI_FUT, 1, CONCEALABILITY,
				AVAILABILITY, THROW_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createHold() {
		return new MartialWeapon("Choi Li Fut Hold", DESCRIPTION, CyberpunkSkill.CHOI_LI_FUT, 0, CONCEALABILITY,
				AVAILABILITY, HOLD_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createEscape() {
		return new MartialWeapon("Choi Li Fut Escape", DESCRIPTION, CyberpunkSkill.CHOI_LI_FUT, 0, CONCEALABILITY,
				AVAILABILITY, ESCAPE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createChoke() {
		return new MartialWeapon("Choi Li Fut Choke", DESCRIPTION, CyberpunkSkill.CHOI_LI_FUT, 0, CONCEALABILITY,
				AVAILABILITY, CHOKE_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createSweep() {
		return new MartialWeapon("Choi Li Fut Sweep", DESCRIPTION, CyberpunkSkill.CHOI_LI_FUT, 2, CONCEALABILITY,
				AVAILABILITY, SWEEP_DAMAGE, RELIABILITY);
	}

	public CyberpunkWeapon createGrapple() {
		return new MartialWeapon("Choi Li Fut Grapple", DESCRIPTION, CyberpunkSkill.CHOI_LI_FUT, 0, CONCEALABILITY,
				AVAILABILITY, GRAPPLE_DAMAGE, RELIABILITY);
	}

}
