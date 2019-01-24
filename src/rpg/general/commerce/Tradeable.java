package rpg.general.commerce;

public interface Tradeable {
	/**
	 * Gets the cost of an item. The returned cost is used in <code>Trader</code>
	 * for <code>buy(Tradeable t)</code> and <code>sell(Tradeable t)</code>.
	 *
	 * @return the cost of an object.
	 */
	public double getCost();
}
