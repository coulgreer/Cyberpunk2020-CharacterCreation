package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import rpg.cyberpunk._2020.Player;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.general.stats.Attribute;

public class CyberpunkCombatantTest {
  private Player mockPlayer;

  @Before
  public void setUp() {
    mockPlayer = mock(Player.class);
  }

  @Test
  public void Should_ReturnDamageModifierAsThree_When_WeaponTypeIsUnarmedAndSkillNameStartsWithMartialArts_While_WeaponSkillTotalValueIsThree() {
    CyberpunkWeapon mockWeapon = mock(CyberpunkWeapon.class);
    when(mockWeapon.getWeaponType()).thenReturn("Unarmed");
    when(mockWeapon.getSkillName()).thenReturn("Martial Arts:");
    CyberpunkSkill mockSkill = mock(CyberpunkSkill.class);
    when(mockSkill.getTotalValue()).thenReturn(3);
    when(mockPlayer.getSkill("Martial Arts:")).thenReturn(mockSkill);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockPlayer);

    assertEquals(3, combatant.getDamageModifier(mockWeapon));
  }

  @Test
  public void Should_ReturnDamageModifierAsZero_When_WeaponTypeIsNotUnarmedAndSkillNameStartsWithMartialArts() {
    CyberpunkWeapon mockWeapon = mock(CyberpunkWeapon.class);
    when(mockWeapon.getWeaponType()).thenReturn("Weapon Type");
    when(mockWeapon.getSkillName()).thenReturn("Martial Arts:");
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockPlayer);

    assertEquals(0, combatant.getDamageModifier(mockWeapon));
  }

  @Test
  public void Should_ReturnDamageModifierAsZero_When_WeaponTypeIsUnarmedAndSkillNameDoesNotStartWithMartialArts() {
    CyberpunkWeapon mockWeapon = mock(CyberpunkWeapon.class);
    when(mockWeapon.getWeaponType()).thenReturn("Unarmed");
    when(mockWeapon.getSkillName()).thenReturn("Skill Name");
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockPlayer);

    assertEquals(0, combatant.getDamageModifier(mockWeapon));
  }

  @Test
  public void Should_ReturnRangeModifierAsTwenty_When_WeaponTypeIsThrown_While_AttributeModifierIsTwo() {
    CyberpunkWeapon mockWeapon = mock(CyberpunkWeapon.class);
    when(mockWeapon.getWeaponType()).thenReturn("Thrown");
    Attribute mockAttribute = mock(Attribute.class);
    when(mockAttribute.getModifier()).thenReturn(2);
    when(mockPlayer.getAttribute(any())).thenReturn(mockAttribute);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockPlayer);

    assertEquals(20, combatant.getRangeModifier(mockWeapon));
  }

  @Test
  public void Should_ReturnRangeModifierAsZero_When_WeaponTypeIsNotThrown() {
    CyberpunkWeapon mockWeapon = mock(CyberpunkWeapon.class);
    when(mockWeapon.getWeaponType()).thenReturn("Weapon Type");
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockPlayer);

    assertEquals(0, combatant.getRangeModifier(mockWeapon));
  }

}
