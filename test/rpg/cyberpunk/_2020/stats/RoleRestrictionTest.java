package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import rpg.cyberpunk._2020.Player;

public class RoleRestrictionTest {
  private Player mockPlayer;
  private Role mockRole;
  private String skillName;

  @Before
  public void setUp() {
    mockPlayer = mock(Player.class);
    mockRole = mock(Role.class);
    skillName = "Skill Name";
    when(mockPlayer.getRole()).thenReturn(mockRole);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_PlayerIsNull() {
    @SuppressWarnings("unused")
    RoleRestriction restriction = new RoleRestriction(null, skillName);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_SkillNameIsNull() {
    @SuppressWarnings("unused")
    RoleRestriction restriction = new RoleRestriction(mockPlayer, null);
  }

  @Test
  public void Should_ReturnRestrictedAsTrue_When_PlayerSpecialSkillNameDoesNotMatchSkillName() {
    RoleRestriction restriction = new RoleRestriction(mockPlayer, skillName);
    when(mockRole.getSpecialAbilityName()).thenReturn("Wrong Skill Name");

    assertTrue(restriction.isRestricted());
  }

  @Test
  public void Should_ReturnRestrictedAsFalse_When_PlayerSpecialSkillNameMatchesSkillName() {
    RoleRestriction restriction = new RoleRestriction(mockPlayer, skillName);

    when(mockRole.getSpecialAbilityName()).thenReturn(skillName);

    assertFalse(restriction.isRestricted());
  }

}
