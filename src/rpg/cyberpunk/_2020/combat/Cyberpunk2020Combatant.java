package rpg.cyberpunk._2020.combat;

import rpg.Player;
import rpg.cyberpunk.CyberpunkCombatant;
import rpg.cyberpunk._2020.AttributeName;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.general.combat.Weapon;

public class Cyberpunk2020Combatant extends CyberpunkCombatant {
	private Player player;
	
	public Cyberpunk2020Combatant(Player player) {
		super(player);
		this.player = player;
	}

	public int getHitModifier(Weapon weapon) {
		return player.getSkillValue(weapon.getSkillName());
	}

	public int getDamageModifier(Weapon weapon) {
		switch (weapon.getWeaponType()) {
		case CyberpunkWeapon.UNARMED:
			return getMiscellaneousDamageModifier(weapon);
		default:
			return 0;
		}
	}

	private int getMiscellaneousDamageModifier(Weapon weapon) {
		switch (weapon.getSkillName()) {
		case CyberpunkSkill.AIKIDO:
			return player.getSkillValue(CyberpunkSkill.AIKIDO);
		case CyberpunkSkill.ANIMAL_KUNG_FU:
			return player.getSkillValue(CyberpunkSkill.ANIMAL_KUNG_FU);
		case CyberpunkSkill.BOXING:
			return player.getSkillValue(CyberpunkSkill.BOXING);
		case CyberpunkSkill.CAPOERIA:
			return player.getSkillValue(CyberpunkSkill.CAPOERIA);
		case CyberpunkSkill.CHOI_LI_FUT:
			return player.getSkillValue(CyberpunkSkill.CHOI_LI_FUT);
		case CyberpunkSkill.JUDO:
			return player.getSkillValue(CyberpunkSkill.JUDO);
		case CyberpunkSkill.KARATE:
			return player.getSkillValue(CyberpunkSkill.KARATE);
		case CyberpunkSkill.TAE_KWON_DO:
			return player.getSkillValue(CyberpunkSkill.TAE_KWON_DO);
		case CyberpunkSkill.THAI_KICK_BOXING:
			return player.getSkillValue(CyberpunkSkill.THAI_KICK_BOXING);
		case CyberpunkSkill.WRESTLING:
			return player.getSkillValue(CyberpunkSkill.WRESTLING);
		default:
			return 0;
		}
	}

	public int getRangeModifier(Weapon weapon) {
		// TODO make a case to catch non thrown.
		return player.getAttributeValue(AttributeName.BODY_TYPE);
	}
}
