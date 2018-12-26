package rpg.cyberpunk._2020.commerce;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import rpg.Player;
import rpg.general.commerce.Cashier;
import rpg.general.commerce.Product;
import rpg.general.commerce.Vendor;

public class CyberpunkCustomerTest {
	private Player player;

	@Before
	public void setUp() {
		player = new Player();
	}

	@Test
	public void testMoneyDecreasesAndProductAddedToInventoryOnPurchase() {
		Product product = mock(Product.class);
		when(product.getCost()).thenReturn(75.0);
		when(product.getName()).thenReturn("Test Weapon");

		Vendor<?> vendor = mock(Vendor.class);
		when(vendor.isInStock(product, 1)).thenReturn(true);

		Cashier cashier = new Cashier();

		player.setMoney(100.0);
		player.trade(cashier, vendor, product, 1);

		assertFalse(null == player.getProduct(product.getName()));
		assertEquals(25.0, player.getMoney(), 0);
	}

	@Test
	public void testProductNotAddedToInventoryIfNotInVendorInventory() {
		Product product = mock(Product.class);
		when(product.getCost()).thenReturn(75.0);
		when(product.getName()).thenReturn("Test Weapon");

		Vendor<?> vendor = mock(Vendor.class);
		when(vendor.isInStock(any(Product.class), anyInt())).thenReturn(false);

		Cashier cashier = new Cashier();

		player.setMoney(100.0);
		player.trade(cashier, vendor, product, 1);

		assertEquals(null, player.getProduct(product.getName()));
		assertEquals(100.0, player.getMoney(), 0);
	}

	@Test
	public void testCustomerDoesNotHaveEnoughMoneyAndDoesNotBuyProduct() {
		Product product = mock(Product.class);
		when(product.getCost()).thenReturn(75.0);
		when(product.getName()).thenReturn("Test Weapon");

		Vendor<?> vendor = mock(Vendor.class);
		when(vendor.isInStock(any(Product.class), anyInt())).thenReturn(true);

		Cashier cashier = new Cashier();

		player.setMoney(10.0);
		player.trade(cashier, vendor, product, 1);

		assertEquals(null, player.getProduct(product.getName()));
		assertEquals(10.0, player.getMoney(), 0);
	}
}
