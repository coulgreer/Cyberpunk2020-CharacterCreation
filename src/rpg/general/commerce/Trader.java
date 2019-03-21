package rpg.general.commerce;

/**
 * Keeps track of money by updating cash whenever a sell or buy action happens.
 * Also, allows a pre-check if Trader can buy or sell an item and gets a bidding
 * or asking price for Tradables.
 * <p>
 * Bid Price - the maximum price that a buyer is willing to pay for a security.
 * <p>
 * Ask Price - the minimum price that a seller is willing to receive for a
 * security.
 * 
 * @author Coul Greer
 */
public interface Trader {
	/**
	 * Allows a <code>Tradeable</code> to be bought.
	 * 
	 * @param price the amount needed to be paid for, for the exchange to succeed
	 */
	public void buy(double price);

	/**
	 * Allows a <code>Tradeable</code> to be sold.
	 *
	 * @param price the amount at which a <code>Tradeable</code> is sold at
	 */
	public void sell(double price);

	/**
	 * Returns whether or not a <code>Tradeable</code> can be bought by a trader
	 * given the price for sell.
	 * 
	 * @param price the amount needed to be paid for the transaction to succeed
	 * @return <code>true</code>, if this trader can spend <code>price</code>
	 */
	public boolean canBuy(double price);

	/**
	 * Returns whether or not a <code>Tradeable</code> can be sold by a trader given
	 * the price.
	 * 
	 * @param price the amount at which a <code>Tradeable</code> is sold at
	 * @return <code>true</code>, if this trader will sell at <code>price</code>
	 */
	public boolean canSell(double price);

	/**
	 * Returns the highest price at which a <code>Tradeable</code> is bought at by
	 * this <code>Trader</code>.
	 * 
	 * @param t the <code>Tradeable</code> used to derive the buying price
	 * @return the highest desired price when buying a <code>Tradeable</code>
	 */
	public double getBidPrice(Tradeable t);

	/**
	 * Returns the lowest price at which a <code>Tradeable</code> is sold by this
	 * <code>Trader</code>.
	 * 
	 * @param t the <code>Tradeable</code> used to derive the selling price
	 * @return the lowest desired price when selling a <code>Tradeable</code>
	 */
	public double getAskPrice(Tradeable t);

	/**
	 * Returns the total amount of money of this <code>Trader</code>.
	 * 
	 * @return the total amount of money this <code>Trader</code> has for
	 *         transactions
	 */
	public double getMoney();
}
