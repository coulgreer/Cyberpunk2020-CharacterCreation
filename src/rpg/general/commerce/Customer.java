package rpg.general.commerce;

public abstract class Customer {
	public final void performTransaction(Product product, int quantity) {
		if (quantity > 0) {
			buy(product, quantity);
			addToInventory(product, quantity);
		}
	}

	public abstract boolean hasEnoughMoney(Product product, int quantity);

	protected abstract void buy(Buyable product, int quantity);

	protected abstract void addToInventory(Product product, int quantity);
}
