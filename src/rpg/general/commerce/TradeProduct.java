package rpg.general.commerce;

public class TradeProduct implements ShoppingCommand {
	private Vendor<?> vendor;
	private Customer customer;
	private Product product;
	private int quantity;

	public TradeProduct(Vendor<?> vendor, Customer customer, Product product, int quantity) {
		this.vendor = vendor;
		this.customer = customer;
		this.product = product;
		this.quantity = quantity;
	}

	public void execute() {
		if (vendor.isInStock(product, quantity) && customer.hasEnoughMoney(product, quantity)) {
			vendor.performTransaction(product, quantity);
			customer.performTransaction(product, quantity);
		}
	}
}
