package rpg.cyberpunk._2020.commerce;

import rpg.general.commerce.Tradeable;
import rpg.general.commerce.Trader;

/**
 * A Trader that can buy or sell goods. The can buy has a floor so that the
 * PlayerTrader can prevent money from reaching a negative value, and
 * PlayerTrader can always sell. There is also no markup or markdown on the cost
 * of an item when getting ask and bid price.
 * 
 * @author Coul Greer
 */
public class PlayerTrader implements Trader {
	/**
	 * Ease-of-use constant. Starts money at zero for if no initial value is given
	 * for the money.
	 */
	public static final double STARTING_MONEY = 0.0;

	private double money;

	/**
	 * Construct a PlayerTrader with a default money starting value of
	 * {@link #STARTING_MONEY}.
	 */
	public PlayerTrader() {
		this(STARTING_MONEY);
	}

	/**
	 * Construct a PlayerTrader with a starting amount of <code>m</code> money.
	 * 
	 * @param m the initial amount of money
	 */
	public PlayerTrader(double m) {
		money = m;
	}

	@Override
	public void buy(double price) {
		money = money - price;
	}

	@Override
	public void sell(double price) {
		money += price;
	}

	@Override
	public boolean canBuy(double price) {
		return money >= price;
	}

	@Override
	public boolean canSell(double price) {
		return true;
	}

	@Override
	public double getBidPrice(Tradeable t) {
		return t.getCost();
	}

	@Override
	public double getAskPrice(Tradeable t) {
		return t.getCost();
	}

	@Override
	public double getMoney() {
		return money;
	}
}
