package rpg.cyberpunk._2020.stats;

import rpg.cyberpunk._2020.Player;
import rpg.general.stats.Restriction;

/**
 * A restriction that uses a Player and its current Role to determine if a given Skill is restricted
 * or not.
 */
public class RoleRestriction implements Restriction {
  private Player player;
  private String skillName;

  /**
   * Constructs a limiter that uses the state of a player and the given role name to determine if a
   * something can be accessed/manipulated.
   * 
   * @param player the owner of a role that has a special ability
   * @param skillName the String representation used to compare against the player's current special
   *        ability
   */
  public RoleRestriction(Player player, String skillName) {
    setPlayer(player);
    setSkillName(skillName);
  }

  private void setPlayer(Player player) {
    if (player == null) {
      throw new NullPointerException();
    } else {
      this.player = player;
    }
  }

  private void setSkillName(String skillName) {
    if (skillName == null) {
      throw new NullPointerException();
    } else {
      this.skillName = skillName;
    }
  }

  @Override
  public boolean isRestricted() {
    String skillName = player.getRole().getSpecialAbilityName();
    return !this.skillName.equals(skillName);
  }

}
