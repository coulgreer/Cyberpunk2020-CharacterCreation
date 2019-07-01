package rpg.cyberpunk._2020.stats;

import rpg.general.stats.Restriction;

/**
 * A restriction that uses a skill to dictate if another object is or is not limited in its
 * abilities.
 */
public class SkillRestriction implements Restriction {
  private CyberpunkSkill skill;
  private int minimumLevel;

  /**
   * Constructs a limiter that uses a skill and a minimum level.
   * 
   * @param skill the object to watch and get level from
   * @param minimumLevel the lowest score that a level can be for the given skill
   */
  public SkillRestriction(CyberpunkSkill skill, int minimumLevel) {
    setSkill(skill);
    setMinimumLevel(minimumLevel);
  }

  private void setSkill(CyberpunkSkill skill) {
    if (skill == null) {
      throw new NullPointerException();
    } else {
      this.skill = skill;
    }
  }

  private void setMinimumLevel(int minimumLevel) {
    if (minimumLevel < CyberpunkSkill.MIN_LEVEL) {
      throw new IllegalArgumentException(
          "minLevel = " + minimumLevel + "; minimum minLevel = " + CyberpunkSkill.MIN_LEVEL);
    } else {
      this.minimumLevel = minimumLevel;
    }
  }

  @Override
  public boolean isRestricted() {
    return skill.getLevel() < minimumLevel;
  }

}
