package rpg.cyberpunk._2020.commerce;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import rpg.Player;
import rpg.general.commerce.Product;
import rpg.general.commerce.QuantifiableProduct;

public class QuantifiableProductTest {

	@Test
	public void testAddingItemsStackWithExistingItemsInPlayerInventory() {
		Product product = mock(Product.class);

		QuantifiableProduct quantifiableProduct = new QuantifiableProduct(product, 3);
		Player player = new Player();
		player.addToInventory(quantifiableProduct);
		player.addToInventory(quantifiableProduct);

		assertEquals(6, quantifiableProduct.getQuantity());
	}

	@Test
	public void testRemovingItemsThatExistInPlayerInventory() {
		Product product = mock(Product.class);

		QuantifiableProduct quantifiableProduct = new QuantifiableProduct(product, 3);
		Player player = new Player();
		player.addToInventory(quantifiableProduct);
		player.removeFromInventory(quantifiableProduct, 1);

		assertEquals(2, quantifiableProduct.getQuantity());
	}
}
