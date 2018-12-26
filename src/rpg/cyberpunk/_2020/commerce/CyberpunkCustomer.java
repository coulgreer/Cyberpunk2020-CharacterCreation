package rpg.cyberpunk._2020.commerce;

import rpg.Player;
import rpg.general.commerce.Buyable;
import rpg.general.commerce.Customer;
import rpg.general.commerce.Product;
import rpg.general.commerce.QuantifiableProduct;

public class CyberpunkCustomer extends Customer {
	private Player player;

	public CyberpunkCustomer(Player player) {
		this.player = player;
	}

	public boolean hasEnoughMoney(Product product, int quantity) {
		double totalCost = product.getCost() * quantity;
		return player.getMoney() >= totalCost;
	}

	protected void buy(Buyable product, int quantity) {
		double totalCost = product.getCost() * quantity;
		player.decreaseMoney(totalCost);
	}

	protected void addToInventory(Product product, int quantity) {
		QuantifiableProduct quantifiableProduct = new QuantifiableProduct(product, quantity);
		player.addToInventory(quantifiableProduct);
	}
}
