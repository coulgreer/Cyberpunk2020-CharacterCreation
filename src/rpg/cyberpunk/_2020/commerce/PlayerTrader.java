package rpg.cyberpunk._2020.commerce;

import rpg.general.commerce.Tradeable;
import rpg.general.commerce.Trader;

/**
 * A Trader that can buy or sell goods. The can buy has a floor so that the PlayerTrader can prevent
 * money from reaching a negative value, and PlayerTrader can always sell. There is also no markup
 * or markdown on the cost of an item when getting ask and bid price.
 */
public class PlayerTrader implements Trader {
  private static final double minPriceDelta = 0.0;

  private double money;

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
    if (price < minPriceDelta) {
      throw new IllegalArgumentException("price = " + price + "; min = " + minPriceDelta);
    }

    money -= price;
  }

  @Override
  public void sell(double price) {
    if (price < minPriceDelta) {
      throw new IllegalArgumentException("price = " + price + "; min = " + minPriceDelta);
    }

    money += price;
  }

  @Override
  public boolean canBuy(double price) {
    if (price < minPriceDelta) {
      throw new IllegalArgumentException("price = " + price + "; min = " + minPriceDelta);
    }

    return money >= price;
  }

  @Override
  public boolean canSell(double price) {
    if (price < minPriceDelta) {
      throw new IllegalArgumentException("price = " + price + "; min = " + minPriceDelta);
    }

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
