package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import rpg.Player;
import rpg.general.combat.BodyLocation;

public class ArmorManagerTest {

	@Test
	public void testAddingThreeSoftOverlappingArmors() {
		CyberpunkArmor clothShirt = mock(CyberpunkArmor.class);
		when(clothShirt.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_SOFT);
		when(clothShirt.getEncumbranceValue()).thenReturn(0);
		when(clothShirt.isCovering(BodyLocation.HEAD)).thenReturn(false);
		when(clothShirt.isCovering(BodyLocation.TORSO)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.RIGHT_ARM)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.LEFT_ARM)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.RIGHT_LEG)).thenReturn(false);
		when(clothShirt.isCovering(BodyLocation.LEFT_LEG)).thenReturn(false);
		when(clothShirt.getDurabilityAt(BodyLocation.HEAD)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.TORSO)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.RIGHT_ARM)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.LEFT_ARM)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.RIGHT_LEG)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.LEFT_LEG)).thenReturn(0);

		CyberpunkArmor heavyLeatherJacket = mock(CyberpunkArmor.class);
		when(heavyLeatherJacket.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_SOFT);
		when(heavyLeatherJacket.getEncumbranceValue()).thenReturn(0);
		when(heavyLeatherJacket.isCovering(BodyLocation.HEAD)).thenReturn(false);
		when(heavyLeatherJacket.isCovering(BodyLocation.TORSO)).thenReturn(true);
		when(heavyLeatherJacket.isCovering(BodyLocation.RIGHT_ARM)).thenReturn(true);
		when(heavyLeatherJacket.isCovering(BodyLocation.LEFT_ARM)).thenReturn(true);
		when(heavyLeatherJacket.isCovering(BodyLocation.RIGHT_LEG)).thenReturn(false);
		when(heavyLeatherJacket.isCovering(BodyLocation.LEFT_LEG)).thenReturn(false);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.HEAD)).thenReturn(0);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.TORSO)).thenReturn(4);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.RIGHT_ARM)).thenReturn(4);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.LEFT_ARM)).thenReturn(4);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.RIGHT_LEG)).thenReturn(0);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.LEFT_LEG)).thenReturn(0);

		CyberpunkArmor kevlarVest = mock(CyberpunkArmor.class);
		when(kevlarVest.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_SOFT);
		when(kevlarVest.getEncumbranceValue()).thenReturn(0);
		when(kevlarVest.isCovering(BodyLocation.HEAD)).thenReturn(false);
		when(kevlarVest.isCovering(BodyLocation.TORSO)).thenReturn(true);
		when(kevlarVest.isCovering(BodyLocation.RIGHT_ARM)).thenReturn(false);
		when(kevlarVest.isCovering(BodyLocation.LEFT_ARM)).thenReturn(false);
		when(kevlarVest.isCovering(BodyLocation.RIGHT_LEG)).thenReturn(false);
		when(kevlarVest.isCovering(BodyLocation.LEFT_LEG)).thenReturn(false);
		when(kevlarVest.getDurabilityAt(BodyLocation.HEAD)).thenReturn(0);
		when(kevlarVest.getDurabilityAt(BodyLocation.TORSO)).thenReturn(10);
		when(kevlarVest.getDurabilityAt(BodyLocation.RIGHT_ARM)).thenReturn(0);
		when(kevlarVest.getDurabilityAt(BodyLocation.LEFT_ARM)).thenReturn(0);
		when(kevlarVest.getDurabilityAt(BodyLocation.RIGHT_LEG)).thenReturn(0);
		when(kevlarVest.getDurabilityAt(BodyLocation.LEFT_LEG)).thenReturn(0);

		Player player = mock(Player.class);

		ArmorManager manager = new ArmorManager(player);
		manager.add(clothShirt);
		manager.add(heavyLeatherJacket);
		manager.add(kevlarVest);

		assertEquals(0, manager.getLocationDurability(BodyLocation.HEAD));
		assertEquals(15, manager.getLocationDurability(BodyLocation.TORSO));
		assertEquals(9, manager.getLocationDurability(BodyLocation.RIGHT_ARM));
		assertEquals(9, manager.getLocationDurability(BodyLocation.LEFT_ARM));
		assertEquals(0, manager.getLocationDurability(BodyLocation.RIGHT_LEG));
		assertEquals(0, manager.getLocationDurability(BodyLocation.LEFT_LEG));
	}

	@Test
	public void testMaxAmountOfArmorLayers() {
		CyberpunkArmor clothShirt = mock(CyberpunkArmor.class);
		when(clothShirt.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_SOFT);
		when(clothShirt.getEncumbranceValue()).thenReturn(0);
		when(clothShirt.isCovering(BodyLocation.HEAD)).thenReturn(false);
		when(clothShirt.isCovering(BodyLocation.TORSO)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.RIGHT_ARM)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.LEFT_ARM)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.RIGHT_LEG)).thenReturn(false);
		when(clothShirt.isCovering(BodyLocation.LEFT_LEG)).thenReturn(false);
		when(clothShirt.getDurabilityAt(BodyLocation.HEAD)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.TORSO)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.RIGHT_ARM)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.LEFT_ARM)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.RIGHT_LEG)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.LEFT_LEG)).thenReturn(0);

		Player player = mock(Player.class);

		ArmorManager manager = new ArmorManager(player);
		for (int i = 0; i < ArmorManager.MAX_TOTAL_ARMOR_LAYERS; i++) {
			manager.add(clothShirt);
		}

		boolean isAdded = manager.add(clothShirt);

		assertFalse(isAdded);
	}

	@Test
	public void testMaxAmountOfHardArmorLayers() {
		CyberpunkArmor flackVest = mock(CyberpunkArmor.class);
		when(flackVest.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_HARD);
		when(flackVest.getEncumbranceValue()).thenReturn(1);
		when(flackVest.isCovering(BodyLocation.HEAD)).thenReturn(false);
		when(flackVest.isCovering(BodyLocation.TORSO)).thenReturn(true);
		when(flackVest.isCovering(BodyLocation.RIGHT_ARM)).thenReturn(false);
		when(flackVest.isCovering(BodyLocation.LEFT_ARM)).thenReturn(false);
		when(flackVest.isCovering(BodyLocation.RIGHT_LEG)).thenReturn(false);
		when(flackVest.isCovering(BodyLocation.LEFT_LEG)).thenReturn(false);
		when(flackVest.getDurabilityAt(BodyLocation.HEAD)).thenReturn(0);
		when(flackVest.getDurabilityAt(BodyLocation.TORSO)).thenReturn(20);
		when(flackVest.getDurabilityAt(BodyLocation.RIGHT_ARM)).thenReturn(0);
		when(flackVest.getDurabilityAt(BodyLocation.LEFT_ARM)).thenReturn(0);
		when(flackVest.getDurabilityAt(BodyLocation.RIGHT_LEG)).thenReturn(0);
		when(flackVest.getDurabilityAt(BodyLocation.LEFT_LEG)).thenReturn(0);

		Player player = mock(Player.class);

		ArmorManager manager = new ArmorManager(player);
		for (int i = 0; i < ArmorManager.MAX_HARD_ARMOR_LAYERS; i++) {
			manager.add(flackVest);
		}

		boolean isAdded = manager.add(flackVest);

		assertFalse(isAdded);
	}

	@Test
	public void testEncumbranceBonusOfTwoLayersOfArmor() {
		CyberpunkArmor clothShirt = mock(CyberpunkArmor.class);
		when(clothShirt.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_SOFT);
		when(clothShirt.getEncumbranceValue()).thenReturn(0);
		when(clothShirt.isCovering(BodyLocation.HEAD)).thenReturn(false);
		when(clothShirt.isCovering(BodyLocation.TORSO)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.RIGHT_ARM)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.LEFT_ARM)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.RIGHT_LEG)).thenReturn(false);
		when(clothShirt.isCovering(BodyLocation.LEFT_LEG)).thenReturn(false);
		when(clothShirt.getDurabilityAt(BodyLocation.HEAD)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.TORSO)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.RIGHT_ARM)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.LEFT_ARM)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.RIGHT_LEG)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.LEFT_LEG)).thenReturn(0);

		Player player = mock(Player.class);

		ArmorManager manager = new ArmorManager(player);
		manager.add(clothShirt);
		manager.add(clothShirt);

		assertEquals(2, manager.getEncumbranceValue());
	}

	@Test
	public void testGettingVeryHighStoppingPowerModifier() {
		CyberpunkArmor clothShirt = mock(CyberpunkArmor.class);
		when(clothShirt.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_SOFT);
		when(clothShirt.getEncumbranceValue()).thenReturn(0);
		when(clothShirt.isCovering(BodyLocation.HEAD)).thenReturn(false);
		when(clothShirt.isCovering(BodyLocation.TORSO)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.RIGHT_ARM)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.LEFT_ARM)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.RIGHT_LEG)).thenReturn(false);
		when(clothShirt.isCovering(BodyLocation.LEFT_LEG)).thenReturn(false);
		when(clothShirt.getDurabilityAt(BodyLocation.HEAD)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.TORSO)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.RIGHT_ARM)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.LEFT_ARM)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.RIGHT_LEG)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.LEFT_LEG)).thenReturn(0);

		Player player = mock(Player.class);

		ArmorManager manager = new ArmorManager(player);
		manager.add(clothShirt);
		manager.add(clothShirt);

		assertEquals(5, manager.getLocationDurability(BodyLocation.TORSO));
	}

	@Test
	public void testGettingHighStoppingPowerModifier() {
		CyberpunkArmor clothShirt = mock(CyberpunkArmor.class);
		when(clothShirt.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_SOFT);
		when(clothShirt.getEncumbranceValue()).thenReturn(0);
		when(clothShirt.isCovering(BodyLocation.HEAD)).thenReturn(false);
		when(clothShirt.isCovering(BodyLocation.TORSO)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.RIGHT_ARM)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.LEFT_ARM)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.RIGHT_LEG)).thenReturn(false);
		when(clothShirt.isCovering(BodyLocation.LEFT_LEG)).thenReturn(false);
		when(clothShirt.getDurabilityAt(BodyLocation.HEAD)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.TORSO)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.RIGHT_ARM)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.LEFT_ARM)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.RIGHT_LEG)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.LEFT_LEG)).thenReturn(0);

		CyberpunkArmor heavyLeatherJacket = mock(CyberpunkArmor.class);
		when(heavyLeatherJacket.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_SOFT);
		when(heavyLeatherJacket.getEncumbranceValue()).thenReturn(0);
		when(heavyLeatherJacket.isCovering(BodyLocation.HEAD)).thenReturn(false);
		when(heavyLeatherJacket.isCovering(BodyLocation.TORSO)).thenReturn(true);
		when(heavyLeatherJacket.isCovering(BodyLocation.RIGHT_ARM)).thenReturn(true);
		when(heavyLeatherJacket.isCovering(BodyLocation.LEFT_ARM)).thenReturn(true);
		when(heavyLeatherJacket.isCovering(BodyLocation.RIGHT_LEG)).thenReturn(false);
		when(heavyLeatherJacket.isCovering(BodyLocation.LEFT_LEG)).thenReturn(false);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.HEAD)).thenReturn(0);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.TORSO)).thenReturn(5);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.RIGHT_ARM)).thenReturn(5);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.LEFT_ARM)).thenReturn(5);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.RIGHT_LEG)).thenReturn(0);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.LEFT_LEG)).thenReturn(0);

		Player player = mock(Player.class);

		ArmorManager manager = new ArmorManager(player);
		manager.add(clothShirt);
		manager.add(heavyLeatherJacket);

		assertEquals(9, manager.getLocationDurability(BodyLocation.TORSO));
	}

	@Test
	public void testGettingAverageStoppingPowerModifier() {
		CyberpunkArmor clothShirt = mock(CyberpunkArmor.class);
		when(clothShirt.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_SOFT);
		when(clothShirt.getEncumbranceValue()).thenReturn(0);
		when(clothShirt.isCovering(BodyLocation.HEAD)).thenReturn(false);
		when(clothShirt.isCovering(BodyLocation.TORSO)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.RIGHT_ARM)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.LEFT_ARM)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.RIGHT_LEG)).thenReturn(false);
		when(clothShirt.isCovering(BodyLocation.LEFT_LEG)).thenReturn(false);
		when(clothShirt.getDurabilityAt(BodyLocation.HEAD)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.TORSO)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.RIGHT_ARM)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.LEFT_ARM)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.RIGHT_LEG)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.LEFT_LEG)).thenReturn(0);

		CyberpunkArmor heavyLeatherJacket = mock(CyberpunkArmor.class);
		when(heavyLeatherJacket.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_SOFT);
		when(heavyLeatherJacket.getEncumbranceValue()).thenReturn(0);
		when(heavyLeatherJacket.isCovering(BodyLocation.HEAD)).thenReturn(false);
		when(heavyLeatherJacket.isCovering(BodyLocation.TORSO)).thenReturn(true);
		when(heavyLeatherJacket.isCovering(BodyLocation.RIGHT_ARM)).thenReturn(true);
		when(heavyLeatherJacket.isCovering(BodyLocation.LEFT_ARM)).thenReturn(true);
		when(heavyLeatherJacket.isCovering(BodyLocation.RIGHT_LEG)).thenReturn(false);
		when(heavyLeatherJacket.isCovering(BodyLocation.LEFT_LEG)).thenReturn(false);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.HEAD)).thenReturn(0);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.TORSO)).thenReturn(9);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.RIGHT_ARM)).thenReturn(9);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.LEFT_ARM)).thenReturn(9);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.RIGHT_LEG)).thenReturn(0);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.LEFT_LEG)).thenReturn(0);

		Player player = mock(Player.class);

		ArmorManager manager = new ArmorManager(player);
		manager.add(clothShirt);
		manager.add(heavyLeatherJacket);

		assertEquals(12, manager.getLocationDurability(BodyLocation.TORSO));
	}

	@Test
	public void testGettingLowStoppingPowerModifier() {
		CyberpunkArmor clothShirt = mock(CyberpunkArmor.class);
		when(clothShirt.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_SOFT);
		when(clothShirt.getEncumbranceValue()).thenReturn(0);
		when(clothShirt.isCovering(BodyLocation.HEAD)).thenReturn(false);
		when(clothShirt.isCovering(BodyLocation.TORSO)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.RIGHT_ARM)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.LEFT_ARM)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.RIGHT_LEG)).thenReturn(false);
		when(clothShirt.isCovering(BodyLocation.LEFT_LEG)).thenReturn(false);
		when(clothShirt.getDurabilityAt(BodyLocation.HEAD)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.TORSO)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.RIGHT_ARM)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.LEFT_ARM)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.RIGHT_LEG)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.LEFT_LEG)).thenReturn(0);

		CyberpunkArmor heavyLeatherJacket = mock(CyberpunkArmor.class);
		when(heavyLeatherJacket.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_SOFT);
		when(heavyLeatherJacket.getEncumbranceValue()).thenReturn(0);
		when(heavyLeatherJacket.isCovering(BodyLocation.HEAD)).thenReturn(false);
		when(heavyLeatherJacket.isCovering(BodyLocation.TORSO)).thenReturn(true);
		when(heavyLeatherJacket.isCovering(BodyLocation.RIGHT_ARM)).thenReturn(true);
		when(heavyLeatherJacket.isCovering(BodyLocation.LEFT_ARM)).thenReturn(true);
		when(heavyLeatherJacket.isCovering(BodyLocation.RIGHT_LEG)).thenReturn(false);
		when(heavyLeatherJacket.isCovering(BodyLocation.LEFT_LEG)).thenReturn(false);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.HEAD)).thenReturn(0);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.TORSO)).thenReturn(15);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.RIGHT_ARM)).thenReturn(15);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.LEFT_ARM)).thenReturn(15);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.RIGHT_LEG)).thenReturn(0);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.LEFT_LEG)).thenReturn(0);

		Player player = mock(Player.class);

		ArmorManager manager = new ArmorManager(player);
		manager.add(clothShirt);
		manager.add(heavyLeatherJacket);

		assertEquals(17, manager.getLocationDurability(BodyLocation.TORSO));
	}

	@Test
	public void testGettingVeryLowStoppingPowerModifier() {
		CyberpunkArmor clothShirt = mock(CyberpunkArmor.class);
		when(clothShirt.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_SOFT);
		when(clothShirt.getEncumbranceValue()).thenReturn(0);
		when(clothShirt.isCovering(BodyLocation.HEAD)).thenReturn(false);
		when(clothShirt.isCovering(BodyLocation.TORSO)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.RIGHT_ARM)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.LEFT_ARM)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.RIGHT_LEG)).thenReturn(false);
		when(clothShirt.isCovering(BodyLocation.LEFT_LEG)).thenReturn(false);
		when(clothShirt.getDurabilityAt(BodyLocation.HEAD)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.TORSO)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.RIGHT_ARM)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.LEFT_ARM)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.RIGHT_LEG)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.LEFT_LEG)).thenReturn(0);

		CyberpunkArmor heavyLeatherJacket = mock(CyberpunkArmor.class);
		when(heavyLeatherJacket.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_SOFT);
		when(heavyLeatherJacket.getEncumbranceValue()).thenReturn(0);
		when(heavyLeatherJacket.isCovering(BodyLocation.HEAD)).thenReturn(false);
		when(heavyLeatherJacket.isCovering(BodyLocation.TORSO)).thenReturn(true);
		when(heavyLeatherJacket.isCovering(BodyLocation.RIGHT_ARM)).thenReturn(true);
		when(heavyLeatherJacket.isCovering(BodyLocation.LEFT_ARM)).thenReturn(true);
		when(heavyLeatherJacket.isCovering(BodyLocation.RIGHT_LEG)).thenReturn(false);
		when(heavyLeatherJacket.isCovering(BodyLocation.LEFT_LEG)).thenReturn(false);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.HEAD)).thenReturn(0);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.TORSO)).thenReturn(21);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.RIGHT_ARM)).thenReturn(21);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.LEFT_ARM)).thenReturn(21);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.RIGHT_LEG)).thenReturn(0);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.LEFT_LEG)).thenReturn(0);

		Player player = mock(Player.class);

		ArmorManager manager = new ArmorManager(player);
		manager.add(clothShirt);
		manager.add(heavyLeatherJacket);

		assertEquals(22, manager.getLocationDurability(BodyLocation.TORSO));
	}

	@Test
	public void testGettingNoStoppingPowerModifier() {
		CyberpunkArmor clothShirt = mock(CyberpunkArmor.class);
		when(clothShirt.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_SOFT);
		when(clothShirt.getEncumbranceValue()).thenReturn(0);
		when(clothShirt.isCovering(BodyLocation.HEAD)).thenReturn(false);
		when(clothShirt.isCovering(BodyLocation.TORSO)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.RIGHT_ARM)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.LEFT_ARM)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.RIGHT_LEG)).thenReturn(false);
		when(clothShirt.isCovering(BodyLocation.LEFT_LEG)).thenReturn(false);
		when(clothShirt.getDurabilityAt(BodyLocation.HEAD)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.TORSO)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.RIGHT_ARM)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.LEFT_ARM)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.RIGHT_LEG)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.LEFT_LEG)).thenReturn(0);

		CyberpunkArmor heavyLeatherJacket = mock(CyberpunkArmor.class);
		when(heavyLeatherJacket.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_SOFT);
		when(heavyLeatherJacket.getEncumbranceValue()).thenReturn(0);
		when(heavyLeatherJacket.isCovering(BodyLocation.HEAD)).thenReturn(false);
		when(heavyLeatherJacket.isCovering(BodyLocation.TORSO)).thenReturn(true);
		when(heavyLeatherJacket.isCovering(BodyLocation.RIGHT_ARM)).thenReturn(true);
		when(heavyLeatherJacket.isCovering(BodyLocation.LEFT_ARM)).thenReturn(true);
		when(heavyLeatherJacket.isCovering(BodyLocation.RIGHT_LEG)).thenReturn(false);
		when(heavyLeatherJacket.isCovering(BodyLocation.LEFT_LEG)).thenReturn(false);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.HEAD)).thenReturn(0);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.TORSO)).thenReturn(27);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.RIGHT_ARM)).thenReturn(27);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.LEFT_ARM)).thenReturn(27);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.RIGHT_LEG)).thenReturn(0);
		when(heavyLeatherJacket.getDurabilityAt(BodyLocation.LEFT_LEG)).thenReturn(0);

		Player player = mock(Player.class);

		ArmorManager manager = new ArmorManager(player);
		manager.add(clothShirt);
		manager.add(heavyLeatherJacket);

		assertEquals(27, manager.getLocationDurability(BodyLocation.TORSO));
	}

	@Test
	public void testAddingThenRemovingArmor() {
		CyberpunkArmor clothShirt = mock(CyberpunkArmor.class);
		when(clothShirt.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_SOFT);
		when(clothShirt.getEncumbranceValue()).thenReturn(0);
		when(clothShirt.isCovering(BodyLocation.HEAD)).thenReturn(false);
		when(clothShirt.isCovering(BodyLocation.TORSO)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.RIGHT_ARM)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.LEFT_ARM)).thenReturn(true);
		when(clothShirt.isCovering(BodyLocation.RIGHT_LEG)).thenReturn(false);
		when(clothShirt.isCovering(BodyLocation.LEFT_LEG)).thenReturn(false);
		when(clothShirt.getDurabilityAt(BodyLocation.HEAD)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.TORSO)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.RIGHT_ARM)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.LEFT_ARM)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.RIGHT_LEG)).thenReturn(0);
		when(clothShirt.getDurabilityAt(BodyLocation.LEFT_LEG)).thenReturn(0);

		Player player = mock(Player.class);

		ArmorManager manager = new ArmorManager(player);
		manager.add(clothShirt);

		assertTrue(manager.remove(clothShirt));
	}
}
