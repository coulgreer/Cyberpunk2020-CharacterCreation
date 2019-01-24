package rpg.general.commerce;

public interface Trader {
	/**
	 * Allows a <code>Tradeable</code> to be bought. Usually used in tandem with
	 * <code>sell(Tradeable t)</code> to represent a transaction.
	 * 
	 * @param t
	 *            a tradeable that can be acquired through a transaction
	 * @param quantity
	 *            the amount of tradeables to buy
	 */
	public void buy(Tradeable t, int quantity);

	/**
	 * Returns whether or not <code>Tradeable</code> can be bought by a trader.
	 * 
	 * @param t
	 *            the tradeable to test for
	 * @param quantity
	 *            the desired amount of tradeables to test for
	 * @return <code>true</code>, if this trader can buy t
	 */
	public boolean canBuy(Tradeable t, int quantity);

	/**
	 * Allows a <code>Tradeable</code> to be sold. Usually used in tandem with
	 * <code>buy(Tradeable t)</code> to represent the transaction.
	 * 
	 * @param t
	 *            an object that can be given through a transaction
	 * @param quantity
	 *            the amount of tradeables to sell
	 */
	public void sell(Tradeable t, int quantity);

	/**
	 * Returns whether or not <code>Tradeable</code> can be sold by a trader.
	 * 
	 * @param t
	 *            the tradeable to test for
	 * @param quantity
	 *            the desired amount of tradeables to test for
	 * @return <code>true</code>, if this trader can sell t
	 */
	public boolean canSell(Tradeable t, int quantity);

	/**
	 * Returns the total amount of money of this object.
	 * 
	 * @return the total amount of money this object has for transactions
	 */
	public double getMoney();
}
