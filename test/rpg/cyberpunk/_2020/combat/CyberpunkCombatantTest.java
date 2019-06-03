package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import rpg.cyberpunk._2020.Player;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;

public class CyberpunkCombatantTest {
  private static CyberpunkSkill mockSkill;

  @Before
  public void setUp() {
    mockSkill = mock(CyberpunkSkill.class);
    when(mockSkill.getTotalValue()).thenReturn(3);
  }

  @Test
  public void testGettingDamageModifierOfAkido() {
    Player mockedPlayer = mock(Player.class);
    when(mockedPlayer.getSkill(CyberpunkSkill.MARTIAL_ARTS_AIKIDO)).thenReturn(mockSkill);
    CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
    when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.WEAPON_TYPE_UNARMED);
    when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.MARTIAL_ARTS_AIKIDO);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockedPlayer);

    assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
  }

  @Test
  public void testGettingDamageModifierOfAnimalKungFu() {
    Player mockedPlayer = mock(Player.class);
    when(mockedPlayer.getSkill(CyberpunkSkill.MARTIAL_ARTS_ANIMAL_KUNG_FU)).thenReturn(mockSkill);
    CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
    when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.WEAPON_TYPE_UNARMED);
    when(mockedCyberpunkWeapon.getSkillName())
        .thenReturn(CyberpunkSkill.MARTIAL_ARTS_ANIMAL_KUNG_FU);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockedPlayer);

    assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
  }

  @Test
  public void testGettingDamageModifierOfBoxing() {
    Player mockedPlayer = mock(Player.class);
    when(mockedPlayer.getSkill(CyberpunkSkill.MARTIAL_ARTS_BOXING)).thenReturn(mockSkill);
    CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
    when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.WEAPON_TYPE_UNARMED);
    when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.MARTIAL_ARTS_BOXING);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockedPlayer);

    assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
  }

  @Test
  public void testGettingDamageModifierOfCapoeria() {
    Player mockedPlayer = mock(Player.class);
    when(mockedPlayer.getSkill(CyberpunkSkill.MARTIAL_ARTS_CAPOERIA)).thenReturn(mockSkill);
    CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
    when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.WEAPON_TYPE_UNARMED);
    when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.MARTIAL_ARTS_CAPOERIA);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockedPlayer);

    assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
  }

  @Test
  public void testGettingDamageModifierOfChoiLiFut() {
    Player mockedPlayer = mock(Player.class);
    when(mockedPlayer.getSkill(CyberpunkSkill.MARTIAL_ARTS_CHOI_LI_FUT)).thenReturn(mockSkill);
    CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
    when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.WEAPON_TYPE_UNARMED);
    when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.MARTIAL_ARTS_CHOI_LI_FUT);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockedPlayer);

    assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
  }

  @Test
  public void testGettingDamageModifierOfJudo() {
    Player mockedPlayer = mock(Player.class);
    when(mockedPlayer.getSkill(CyberpunkSkill.MARTIAL_ARTS_JUDO)).thenReturn(mockSkill);
    CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
    when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.WEAPON_TYPE_UNARMED);
    when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.MARTIAL_ARTS_JUDO);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockedPlayer);

    assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
  }

  @Test
  public void testGettingDamageModifierOfKarate() {
    Player mockedPlayer = mock(Player.class);
    when(mockedPlayer.getSkill(CyberpunkSkill.MARTIAL_ARTS_KARATE)).thenReturn(mockSkill);
    CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
    when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.WEAPON_TYPE_UNARMED);
    when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.MARTIAL_ARTS_KARATE);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockedPlayer);

    assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
  }

  @Test
  public void testGettingDamageModifierOfTaeKwonDo() {
    Player mockedPlayer = mock(Player.class);
    when(mockedPlayer.getSkill(CyberpunkSkill.MARTIAL_ARTS_TAE_KWON_DO)).thenReturn(mockSkill);
    CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
    when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.WEAPON_TYPE_UNARMED);
    when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.MARTIAL_ARTS_TAE_KWON_DO);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockedPlayer);

    assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
  }

  @Test
  public void testGettingDamageModifierOfThaiKickBoxing() {
    Player mockedPlayer = mock(Player.class);
    when(mockedPlayer.getSkill(CyberpunkSkill.MARTIAL_ARTS_THAI_KICK_BOXING)).thenReturn(mockSkill);
    CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
    when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.WEAPON_TYPE_UNARMED);
    when(mockedCyberpunkWeapon.getSkillName())
        .thenReturn(CyberpunkSkill.MARTIAL_ARTS_THAI_KICK_BOXING);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockedPlayer);

    assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
  }

  @Test
  public void testGettingDamageModifierOfWrestling() {
    Player mockedPlayer = mock(Player.class);
    when(mockedPlayer.getSkill(CyberpunkSkill.MARTIAL_ARTS_WRESTLING)).thenReturn(mockSkill);
    CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
    when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.WEAPON_TYPE_UNARMED);
    when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.MARTIAL_ARTS_WRESTLING);
    CyberpunkCombatant combatant = new CyberpunkCombatant(mockedPlayer);

    assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
  }
}
