package rpg.general.commerce;

public class QuantifiableProduct extends Product {
	private Product product;
	private int quantity;

	public QuantifiableProduct(Product product, int quantity) {
		super(product.getName(), product.getDescription(), product.getCost(), product.getWeight());
		this.product = product;
		this.quantity = quantity;
	}

	public void increaseQuantity(int quantity) {
		this.quantity += quantity;
	}

	public void decreaseQuantity(int quantity) {
		this.quantity -= quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public boolean equals(Object o) {
		return product.equals(o);
	}

	public int hashCode() {
		return product.hashCode();
	}
}
