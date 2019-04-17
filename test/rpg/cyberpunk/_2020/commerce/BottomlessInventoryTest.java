package rpg.cyberpunk._2020.commerce;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import rpg.cyberpunk._2020.combat.CyberpunkArmor;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.general.combat.Ammunition;

public class BottomlessInventoryTest {

	// add(Item) tests
	@Test
	public void testIsContainedIfCyberpunkWeaponWasAdded() {
		CyberpunkWeapon mockWeapon = mock(CyberpunkWeapon.class);

		BottomlessInventory inventory = new BottomlessInventory();

		inventory.add(mockWeapon);

		assertTrue(inventory.contains(mockWeapon));
	}

	@Test(expected = NullPointerException.class)
	public void testExceptionThrownIfNullCyberpunkWeaponIsAdded() {
		CyberpunkWeapon nullWeapon = null;

		BottomlessInventory inventory = new BottomlessInventory();

		inventory.add(nullWeapon);
	}

	@Test
	public void testIsContainedIfCyberpunkArmorWasAdded() {
		CyberpunkArmor mockArmor = mock(CyberpunkArmor.class);

		BottomlessInventory inventory = new BottomlessInventory();

		inventory.add(mockArmor);

		assertTrue(inventory.contains(mockArmor));
	}

	@Test(expected = NullPointerException.class)
	public void testExceptionThrownIfNullCyberpunkArmorIsAdded() {
		CyberpunkArmor nullArmor = null;

		BottomlessInventory inventory = new BottomlessInventory();

		inventory.add(nullArmor);
	}

	@Test
	public void testIsContainedIfAmmunitionWasAdded() {
		Ammunition mockAmmunition = mock(Ammunition.class);

		BottomlessInventory inventory = new BottomlessInventory();

		inventory.add(mockAmmunition);

		assertTrue(inventory.contains(mockAmmunition));
	}

	@Test(expected = NullPointerException.class)
	public void testExceptionThrownIfNullAmmunitionIsAdded() {
		Ammunition nullAmmunition = null;

		BottomlessInventory inventory = new BottomlessInventory();

		inventory.add(nullAmmunition);
	}
	// add(Item) tests --end

	// remove(Item) tests
	@Test
	public void testIsNotContainedIfExistingCyberpunkWeaponWasRemoved() {
		CyberpunkWeapon mockWeapon = mock(CyberpunkWeapon.class);

		BottomlessInventory inventory = new BottomlessInventory();

		inventory.add(mockWeapon);
		inventory.remove(mockWeapon);

		assertFalse(inventory.contains(mockWeapon));
	}

	@Test
	public void testIsNotContainedIfExistingCyberpunkArmorWasRemoved() {
		CyberpunkArmor mockArmor = mock(CyberpunkArmor.class);

		BottomlessInventory inventory = new BottomlessInventory();

		inventory.add(mockArmor);
		inventory.remove(mockArmor);

		assertFalse(inventory.contains(mockArmor));
	}

	@Test
	public void testIsNotContainedIfExistingAmmunitionWasRemoved() {
		Ammunition mockAmmunition = mock(Ammunition.class);

		BottomlessInventory inventory = new BottomlessInventory();

		inventory.add(mockAmmunition);
		inventory.remove(mockAmmunition);

		assertFalse(inventory.contains(mockAmmunition));
	}
	// remove(Item) tests --end

	@Test
	public void testQuantityEqualsTwoIfTwoEqualItemsAreAdded() {
		CyberpunkWeapon mockWeapon = mock(CyberpunkWeapon.class);

		BottomlessInventory inventory = new BottomlessInventory();

		inventory.add(mockWeapon);
		inventory.add(mockWeapon);

		assertEquals(2, inventory.getQuantity(mockWeapon));
	}

	@Test
	public void testTotalWeightEqualsNineIfCyberpunkWeaponWeightEqualsThreeAndCyberpunkArmorWeightEqualsFourAndAmmunitionWeightEqualsTwo() {
		CyberpunkWeapon mockWeapon = mock(CyberpunkWeapon.class);
		when(mockWeapon.getWeight()).thenReturn(4.0);

		CyberpunkArmor mockArmor = mock(CyberpunkArmor.class);
		when(mockArmor.getWeight()).thenReturn(3.0);

		Ammunition mockAmmunition = mock(Ammunition.class);
		when(mockAmmunition.getWeight()).thenReturn(2.0);

		BottomlessInventory inventory = new BottomlessInventory();
		inventory.add(mockWeapon);
		inventory.add(mockArmor);
		inventory.add(mockAmmunition);

		assertEquals(9.0, inventory.getTotalWeight(), 0.0);
	}

	@Test
	public void testTotalWeightEqualsTwoIfCyberpunkWeaponWeightEqualsFourAndCyberpunkArmorWeightEqualsTwoAndTheCyberpunkWeaponWasRemoved() {
		CyberpunkWeapon mockWeapon = mock(CyberpunkWeapon.class);
		when(mockWeapon.getWeight()).thenReturn(4.0);

		CyberpunkArmor mockArmor = mock(CyberpunkArmor.class);
		when(mockArmor.getWeight()).thenReturn(2.0);

		BottomlessInventory inventory = new BottomlessInventory();
		inventory.add(mockWeapon);
		inventory.add(mockArmor);
		inventory.remove(mockWeapon);

		assertEquals(2.0, inventory.getTotalWeight(), 0.0);
	}

}
