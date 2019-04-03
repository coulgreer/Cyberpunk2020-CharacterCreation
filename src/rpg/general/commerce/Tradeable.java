package rpg.general.commerce;

/**
 * A functional interface used to get the value of an item for monetary use.
 * Namely to buy and sell implementing objects.
 * 
 * @author Coul Greer
 *
 */
public interface Tradeable {
	/**
	 * Gets the cost of an item. The returned cost is used in <code>Trader</code>
	 * for <code>buy(Tradeable t)</code> and <code>sell(Tradeable t)</code>.
	 *
	 * @return the cost of an object.
	 */
	public double getCost();
}
