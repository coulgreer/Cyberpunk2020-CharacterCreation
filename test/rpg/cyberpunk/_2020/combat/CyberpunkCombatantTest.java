package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Test;
import rpg.cyberpunk._2020.Player;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;

public class CyberpunkCombatantTest {
  @Test
  public void testGettingDamageModifierOfAkido() {
    Player mockedPlayer = mock(Player.class);
    when(mockedPlayer.getSkill(CyberpunkSkill.AIKIDO).getTotalValue()).thenReturn(3);
    CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
    when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.WEAPON_TYPE_UNARMED);
    when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.AIKIDO);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockedPlayer);

    assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
  }

  @Test
  public void testGettingDamageModifierOfAnimalKungFu() {
    Player mockedPlayer = mock(Player.class);
    when(mockedPlayer.getSkill(CyberpunkSkill.ANIMAL_KUNG_FU).getTotalValue()).thenReturn(3);
    CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
    when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.WEAPON_TYPE_UNARMED);
    when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.ANIMAL_KUNG_FU);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockedPlayer);

    assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
  }

  @Test
  public void testGettingDamageModifierOfBoxing() {
    Player mockedPlayer = mock(Player.class);
    when(mockedPlayer.getSkill(CyberpunkSkill.BOXING).getTotalValue()).thenReturn(3);
    CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
    when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.WEAPON_TYPE_UNARMED);
    when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.BOXING);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockedPlayer);

    assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
  }

  @Test
  public void testGettingDamageModifierOfCapoeria() {
    Player mockedPlayer = mock(Player.class);
    when(mockedPlayer.getSkill(CyberpunkSkill.CAPOERIA).getTotalValue()).thenReturn(3);
    CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
    when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.WEAPON_TYPE_UNARMED);
    when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.CAPOERIA);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockedPlayer);

    assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
  }

  @Test
  public void testGettingDamageModifierOfChoiLiFut() {
    Player mockedPlayer = mock(Player.class);
    when(mockedPlayer.getSkill(CyberpunkSkill.CHOI_LI_FUT).getTotalValue()).thenReturn(3);
    CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
    when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.WEAPON_TYPE_UNARMED);
    when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.CHOI_LI_FUT);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockedPlayer);

    assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
  }

  @Test
  public void testGettingDamageModifierOfJudo() {
    Player mockedPlayer = mock(Player.class);
    when(mockedPlayer.getSkill(CyberpunkSkill.JUDO).getTotalValue()).thenReturn(3);
    CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
    when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.WEAPON_TYPE_UNARMED);
    when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.JUDO);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockedPlayer);

    assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
  }

  @Test
  public void testGettingDamageModifierOfKarate() {
    Player mockedPlayer = mock(Player.class);
    when(mockedPlayer.getSkill(CyberpunkSkill.KARATE).getTotalValue()).thenReturn(3);
    CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
    when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.WEAPON_TYPE_UNARMED);
    when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.KARATE);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockedPlayer);

    assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
  }

  @Test
  public void testGettingDamageModifierOfTaeKwonDo() {
    Player mockedPlayer = mock(Player.class);
    when(mockedPlayer.getSkill(CyberpunkSkill.TAE_KWON_DO).getTotalValue()).thenReturn(3);
    CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
    when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.WEAPON_TYPE_UNARMED);
    when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.TAE_KWON_DO);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockedPlayer);

    assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
  }

  @Test
  public void testGettingDamageModifierOfThaiKickBoxing() {
    Player mockedPlayer = mock(Player.class);
    when(mockedPlayer.getSkill(CyberpunkSkill.THAI_KICK_BOXING).getTotalValue()).thenReturn(3);
    CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
    when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.WEAPON_TYPE_UNARMED);
    when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.THAI_KICK_BOXING);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockedPlayer);

    assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
  }

  @Test
  public void testGettingDamageModifierOfWrestling() {
    Player mockedPlayer = mock(Player.class);
    when(mockedPlayer.getSkill(CyberpunkSkill.WRESTLING).getTotalValue()).thenReturn(3);
    CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
    when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.WEAPON_TYPE_UNARMED);
    when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.WRESTLING);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockedPlayer);

    assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
  }
}
