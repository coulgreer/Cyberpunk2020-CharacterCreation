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
import rpg.general.commerce.Item;

public class BottomlessInventoryTest {

  // add(Item) tests
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_NullCyberpunkWeaponIsAdded() {
    CyberpunkWeapon nullWeapon = null;
    BottomlessInventory inventory = new BottomlessInventory();

    inventory.add(nullWeapon);
  }

  @Test
  public void Should_ReturnContainsAsTrue_When_CyberpunkWeaponIsAdded() {
    CyberpunkWeapon mockWeapon = mock(CyberpunkWeapon.class);
    BottomlessInventory inventory = new BottomlessInventory();

    inventory.add(mockWeapon);

    assertTrue(inventory.contains(mockWeapon));
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_NullCyberpunkArmorIsAdded() {
    CyberpunkArmor nullArmor = null;
    BottomlessInventory inventory = new BottomlessInventory();

    inventory.add(nullArmor);
  }

  @Test
  public void Should_ReturnContainsAsTrue_When_CyberpunkArmorIsAdded() {
    CyberpunkArmor mockArmor = mock(CyberpunkArmor.class);
    BottomlessInventory inventory = new BottomlessInventory();

    inventory.add(mockArmor);

    assertTrue(inventory.contains(mockArmor));
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_NullAmmunitionIsAdded() {
    Ammunition nullAmmunition = null;
    BottomlessInventory inventory = new BottomlessInventory();

    inventory.add(nullAmmunition);
  }

  @Test
  public void Should_ReturnContainsAsTrue_When_AmmunitionIsAdded() {
    Ammunition mockAmmunition = mock(Ammunition.class);
    BottomlessInventory inventory = new BottomlessInventory();

    inventory.add(mockAmmunition);

    assertTrue(inventory.contains(mockAmmunition));
  }
  
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_NullItemIsAdded() {
    Item nullItem = null;
    BottomlessInventory inventory = new BottomlessInventory();
    
    inventory.addItem(nullItem);
  }
  
  @Test
  public void Should_ReturnContainsAsTrue_When_ItemIsAdded() {
    Item mockItem = mock(Item.class);
    BottomlessInventory inventory = new BottomlessInventory();
    
    inventory.addItem(mockItem);
    
    assertTrue(inventory.contains(mockItem));
  }
  // add(Item) tests --end

  // remove(Item) tests
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_NullCyberpunkWeaponIsRemoved() {
    CyberpunkWeapon nullWeapon = null;
    BottomlessInventory inventory = new BottomlessInventory();

    inventory.remove(nullWeapon);
  }

  @Test
  public void Should_ReturnContainsAsFalse_When_CyberpunkWeaponIsRemoved_While_CyberpunkWeaponIsInInventory() {
    CyberpunkWeapon mockWeapon = mock(CyberpunkWeapon.class);
    BottomlessInventory inventory = new BottomlessInventory();
    inventory.add(mockWeapon);

    inventory.remove(mockWeapon);

    assertFalse(inventory.contains(mockWeapon));
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_NullCyberpunkArmorIsRemoved() {
    CyberpunkArmor nullArmor = null;
    BottomlessInventory inventory = new BottomlessInventory();

    inventory.remove(nullArmor);
  }

  @Test
  public void Should_ReturnContainsAsFalse_When_CyberpunkArmorIsRemoved_While_CyberpunkArmorIsInInventory() {
    CyberpunkArmor mockArmor = mock(CyberpunkArmor.class);
    BottomlessInventory inventory = new BottomlessInventory();
    inventory.add(mockArmor);
    
    inventory.remove(mockArmor);

    assertFalse(inventory.contains(mockArmor));
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_NullAmmunitionIsRemoved() {
    Ammunition nullAmmunition = null;
    BottomlessInventory inventory = new BottomlessInventory();
    
    inventory.remove(nullAmmunition);
  }

  @Test
  public void Should_ReturnContainsAsFalse_When_AmmunitionIsRemoved_While_AmmunitionIsInInventory() {
    Ammunition mockAmmunition = mock(Ammunition.class);
    BottomlessInventory inventory = new BottomlessInventory();
    inventory.add(mockAmmunition);
    
    inventory.remove(mockAmmunition);

    assertFalse(inventory.contains(mockAmmunition));
  }
  
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_ItemIsRemoved() {
    Item nullItem = null;
    BottomlessInventory inventory = new BottomlessInventory();
    
    inventory.removeItem(nullItem);
  }
  
  @Test
  public void Should_ReturnContainsAsFalse_When_ItemIsRemoved_WhileItemIsInInventory() {
    Item mockInventory = mock(Item.class);
    BottomlessInventory inventory = new BottomlessInventory();
    
    inventory.removeItem(mockInventory);
    
    assertFalse(inventory.contains(mockInventory));
  }
  // remove(Item) tests --end

  @Test
  public void Should_ReturnQuantityAsTwo_When_TwoEquivalentItemsAreAdded() {
    Item mockItem = mock(Item.class);
    BottomlessInventory inventory = new BottomlessInventory();
    inventory.addItem(mockItem);
    inventory.addItem(mockItem);

    assertEquals(2, inventory.getQuantity(mockItem));
  }

  @Test
  public void Should_ReturnTotalWeightAsSummedWeightOfAllItems_When_AllItemsAreContainedInInventory() {
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

}
