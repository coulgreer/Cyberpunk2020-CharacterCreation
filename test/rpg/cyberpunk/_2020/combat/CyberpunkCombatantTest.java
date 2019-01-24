package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ListIterator;

import org.junit.Test;

import rpg.Player;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;

public class CyberpunkCombatantTest {
	@Test
	public void testEquippingTwoWeaponsThenUnequipAllWeapons() {
		Player mockedPlayer = mock(Player.class);
		Cyberpunk2020Combatant combatant = new Cyberpunk2020Combatant(mockedPlayer);
		CyberpunkWeapon mockedPrimaryCyberpunkWeapon = mock(CyberpunkWeapon.class);
		when(mockedPrimaryCyberpunkWeapon.getName()).thenReturn("Primary Cyberpunk Weapon");
		CyberpunkWeapon mockedSecondaryCyberpunkWeapon = mock(CyberpunkWeapon.class);
		when(mockedSecondaryCyberpunkWeapon.getName()).thenReturn("Secondary Cyberpunk Weapon");

		combatant.arm(CyberpunkCombatant.PRIMARY_SLOT, mockedPrimaryCyberpunkWeapon);
		combatant.arm(CyberpunkCombatant.SECONDARY_SLOT, mockedSecondaryCyberpunkWeapon);
		ListIterator<CyberpunkWeapon> iterator = combatant.createIterator();

		assertEquals("Primary Cyberpunk Weapon", iterator.next().getName());
		assertEquals("Secondary Cyberpunk Weapon", iterator.next().getName());

		combatant.disarm(CyberpunkCombatant.PRIMARY_SLOT);
		combatant.disarm(CyberpunkCombatant.SECONDARY_SLOT);
		iterator = combatant.createIterator();

		assertEquals("Brawling Strike", iterator.next().getName());
		assertEquals("Brawling Strike", iterator.next().getName());
	}

	@Test
	public void testGettingDamageModifierOfAkido() {
		Player mockedPlayer = mock(Player.class);
		when(mockedPlayer.getSkillValue(CyberpunkSkill.AIKIDO)).thenReturn(3);
		CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
		when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.UNARMED);
		when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.AIKIDO);
		Cyberpunk2020Combatant combatant = new Cyberpunk2020Combatant(mockedPlayer);

		assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
	}

	@Test
	public void testGettingDamageModifierOfAnimalKungFu() {
		Player mockedPlayer = mock(Player.class);
		when(mockedPlayer.getSkillValue(CyberpunkSkill.ANIMAL_KUNG_FU)).thenReturn(3);
		CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
		when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.UNARMED);
		when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.ANIMAL_KUNG_FU);
		Cyberpunk2020Combatant combatant = new Cyberpunk2020Combatant(mockedPlayer);

		assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
	}

	@Test
	public void testGettingDamageModifierOfBoxing() {
		Player mockedPlayer = mock(Player.class);
		when(mockedPlayer.getSkillValue(CyberpunkSkill.BOXING)).thenReturn(3);
		CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
		when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.UNARMED);
		when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.BOXING);
		Cyberpunk2020Combatant combatant = new Cyberpunk2020Combatant(mockedPlayer);

		assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
	}

	@Test
	public void testGettingDamageModifierOfCapoeria() {
		Player mockedPlayer = mock(Player.class);
		when(mockedPlayer.getSkillValue(CyberpunkSkill.CAPOERIA)).thenReturn(3);
		CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
		when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.UNARMED);
		when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.CAPOERIA);
		Cyberpunk2020Combatant combatant = new Cyberpunk2020Combatant(mockedPlayer);

		assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
	}

	@Test
	public void testGettingDamageModifierOfChoiLiFut() {
		Player mockedPlayer = mock(Player.class);
		when(mockedPlayer.getSkillValue(CyberpunkSkill.CHOI_LI_FUT)).thenReturn(3);
		CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
		when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.UNARMED);
		when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.CHOI_LI_FUT);
		Cyberpunk2020Combatant combatant = new Cyberpunk2020Combatant(mockedPlayer);

		assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
	}

	@Test
	public void testGettingDamageModifierOfJudo() {
		Player mockedPlayer = mock(Player.class);
		when(mockedPlayer.getSkillValue(CyberpunkSkill.JUDO)).thenReturn(3);
		CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
		when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.UNARMED);
		when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.JUDO);
		Cyberpunk2020Combatant combatant = new Cyberpunk2020Combatant(mockedPlayer);

		assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
	}

	@Test
	public void testGettingDamageModifierOfKarate() {
		Player mockedPlayer = mock(Player.class);
		when(mockedPlayer.getSkillValue(CyberpunkSkill.KARATE)).thenReturn(3);
		CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
		when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.UNARMED);
		when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.KARATE);
		Cyberpunk2020Combatant combatant = new Cyberpunk2020Combatant(mockedPlayer);

		assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
	}

	@Test
	public void testGettingDamageModifierOfTaeKwonDo() {
		Player mockedPlayer = mock(Player.class);
		when(mockedPlayer.getSkillValue(CyberpunkSkill.TAE_KWON_DO)).thenReturn(3);
		CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
		when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.UNARMED);
		when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.TAE_KWON_DO);
		Cyberpunk2020Combatant combatant = new Cyberpunk2020Combatant(mockedPlayer);

		assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
	}

	@Test
	public void testGettingDamageModifierOfThaiKickBoxing() {
		Player mockedPlayer = mock(Player.class);
		when(mockedPlayer.getSkillValue(CyberpunkSkill.THAI_KICK_BOXING)).thenReturn(3);
		CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
		when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.UNARMED);
		when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.THAI_KICK_BOXING);
		Cyberpunk2020Combatant combatant = new Cyberpunk2020Combatant(mockedPlayer);

		assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
	}

	@Test
	public void testGettingDamageModifierOfWrestling() {
		Player mockedPlayer = mock(Player.class);
		when(mockedPlayer.getSkillValue(CyberpunkSkill.WRESTLING)).thenReturn(3);
		CyberpunkWeapon mockedCyberpunkWeapon = mock(CyberpunkWeapon.class);
		when(mockedCyberpunkWeapon.getWeaponType()).thenReturn(CyberpunkWeapon.UNARMED);
		when(mockedCyberpunkWeapon.getSkillName()).thenReturn(CyberpunkSkill.WRESTLING);
		Cyberpunk2020Combatant combatant = new Cyberpunk2020Combatant(mockedPlayer);

		assertEquals(3, combatant.getDamageModifier(mockedCyberpunkWeapon));
	}
}
