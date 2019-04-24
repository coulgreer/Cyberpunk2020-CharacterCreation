package rpg.cyberpunk._2020.combat;

import rpg.Player;
import rpg.cyberpunk._2020.stats.CyberpunkAttribute;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.general.combat.Combatant;
import rpg.general.combat.Weapon;
import rpg.util.Probability;

/**
 * An instance of Combatant that uses Skills from Cyberpunk 2020 in order to get
 * the scores and modifiers for hit, attack, and damage. Weapons can also be
 * manipulated through this instance as well.
 * 
 * @author Coul Greer
 */
public class CyberpunkCombatant implements Combatant<CyberpunkWeapon> {

	/**
	 * The range modifier used when no range modifier is provided or an error would
	 * occur.
	 */
	public static final int DEFAULT_RANGE_MODIFIER = 0;

	/**
	 * The damage modifier used when no damage modifier is provided or an error
	 * would occur.
	 */
	public static final int DEFAULT_DAMAGE_MODIFIER = 0;

	private static final long serialVersionUID = 1L;

	private final Player player;

	/**
	 * Constructs a CyberpunkCombatant that requires a player to get the modifiers,
	 * and initializes this combatant to start of with Brawling Strikes equipped in
	 * both slots.
	 * 
	 * @param player the owner of the stats used to derive scores and modifiers
	 */
	public CyberpunkCombatant(Player player) {
		this.player = player;
	}

	@Override
	public int getRangeScore(CyberpunkWeapon weapon) {
		return weapon.getRangeModifier() + getRangeModifier(weapon);
	}

	@Override
	public Probability getTotalDamageChance(CyberpunkWeapon weapon) {
		return new Probability(weapon.getDamageDice(), weapon.getDamageModifier() + getDamageModifier(weapon));
	}

	@Override
	public Probability getTotalAttackChance(CyberpunkWeapon weapon) {
		return new Probability(weapon.getHitDice(), weapon.getAttackModifier() + getAttackModifier(weapon));
	}

	@Override
	public int getAttackModifier(CyberpunkWeapon weapon) {
		return player.getSkillValue(weapon.getSkillName());
	}

	@Override
	public int getDamageModifier(CyberpunkWeapon weapon) {
		switch (weapon.getWeaponType()) {
		case CyberpunkWeapon.WEAPON_TYPE_UNARMED:
			return getMiscellaneousDamageModifier(weapon);
		default:
			return DEFAULT_DAMAGE_MODIFIER;
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
			return DEFAULT_DAMAGE_MODIFIER;
		}
	}

	@Override
	public int getRangeModifier(CyberpunkWeapon weapon) {
		// TODO (Coul Greer): Add a Thrown Weapon type.
		if (weapon != null //
				&& ThrownWeapon.WEAPON_TYPE_GRENADE.equals(weapon.getWeaponType())) {
			return player.getAttributeValue(CyberpunkAttribute.BODY_TYPE) * 10;
		} else {
			return DEFAULT_RANGE_MODIFIER;
		}
	}

}
