package rpg.general.commerce;

public class TradeProduct implements ShoppingCommand {
	private Trader vendor;
	private Trader customer;
	private Tradeable tradeable;
	private int quantity;

	public TradeProduct(Trader vendor, Trader customer, Tradeable tradeable, int quantity) {
		this.vendor = vendor;
		this.customer = customer;
		this.tradeable = tradeable;
		this.quantity = quantity;
	}

	@Override
	public void execute() {
		if (vendor.canSell(tradeable, quantity) && customer.canBuy(tradeable, quantity)) {
			vendor.sell(tradeable, quantity);
			customer.buy(tradeable, quantity);
		}
	}

	@Override
	public void undo() {
		if (vendor.canBuy(tradeable, quantity) && customer.canSell(tradeable, quantity)) {
			vendor.buy(tradeable, quantity);
			customer.buy(tradeable, quantity);
		}
	}
}
