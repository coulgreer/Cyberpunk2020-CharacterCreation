package rpg.general.commerce;

public abstract class Vendor<T extends Product> {
	public final void performTransaction(Product product, int quantity) {
		if (quantity > 0) {
			sell(product, quantity);
			removeFromInventory(product, quantity);
		}
	}

	public abstract boolean isInStock(Product product, int quantity);

	protected abstract void sell(Buyable product, int quantity);

	protected abstract void removeFromInventory(Product product, int quantity);
}
