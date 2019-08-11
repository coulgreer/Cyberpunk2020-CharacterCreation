package rpg.cyberpunk._2020.combat;

import rpg.cyberpunk._2020.Player;
import rpg.cyberpunk._2020.stats.CyberpunkAttribute;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.general.combat.Combatant;
import rpg.general.stats.Attribute;
import rpg.util.Die;
import rpg.util.Measurement;
import rpg.util.Probability;

/**
 * An instance of Combatant that uses Skills from Cyberpunk 2020 in order to get the scores and
 * modifiers for hit, attack, and damage. Weapons can also be manipulated through this instance as
 * well.
 */
public class CyberpunkCombatant implements Combatant<CyberpunkWeapon> {
  private static final int DEFAULT_RANGE_MODIFIER = 0;
  private static final int DEFAULT_DAMAGE_MODIFIER = 0;
  private static final long serialVersionUID = 1L;

  private final Player player;

  /**
   * Constructs a CyberpunkCombatant that requires a player to get the modifiers, and initializes
   * this combatant to start of with Brawling Strikes equipped in both slots.
   * 
   * @param player the owner of the stats used to derive scores and modifiers
   */
  public CyberpunkCombatant(Player player) {
    this.player = player;
  }

  @Override
  public Measurement getRangeScore(CyberpunkWeapon weapon) {
    return Measurement.add(weapon.getRangeModifier(), getRangeModifier(weapon));
  }

  @Override
  public Probability getTotalDamageChance(CyberpunkWeapon weapon) {
    Die die = weapon.getDamageDice();
    int score = weapon.getDamageModifier() + getDamageModifier(weapon);

    return new Probability(die, score);
  }

  @Override
  public Probability getTotalAttackChance(CyberpunkWeapon weapon) {
    Die die = weapon.getHitDice();
    int score = weapon.getAttackModifier() + getAttackModifier(weapon);

    return new Probability(die, score);
  }

  @Override
  public int getAttackModifier(CyberpunkWeapon weapon) {
    CyberpunkSkill skill = player.getSkill(weapon.getSkillName());
    return skill.getTotalValue();
  }

  @Override
  public int getDamageModifier(CyberpunkWeapon weapon) {
    int modifier;

    boolean isUnarmed = (CyberpunkWeapon.WEAPON_TYPE_UNARMED).equals(weapon.getWeaponType());
    boolean isAMartialArtSkill = (weapon.getSkillName()).startsWith("Martial Arts:");
    if (isUnarmed && isAMartialArtSkill) {
      CyberpunkSkill skill = player.getSkill(weapon.getSkillName());
      modifier = skill.getTotalValue();
    } else {
      modifier = DEFAULT_DAMAGE_MODIFIER;
    }

    return modifier;
  }

  @Override
  public Measurement getRangeModifier(CyberpunkWeapon weapon) {
    int modifier;

    if (CyberpunkWeapon.WEAPON_TYPE_THROWN.equals(weapon.getWeaponType())) {
      Attribute attribute = player.getAttribute(CyberpunkAttribute.BODY_TYPE);
      modifier = attribute.getModifier() * 10;
    } else {
      modifier = DEFAULT_RANGE_MODIFIER;
    }

    return new Measurement( //
        Measurement.Type.LENGTH, //
        modifier, //
        Measurement.Unit.KILOGRAM);
  }

}
