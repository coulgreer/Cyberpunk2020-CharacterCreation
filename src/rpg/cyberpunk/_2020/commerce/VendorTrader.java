package rpg.cyberpunk._2020.commerce;

import rpg.general.commerce.Tradeable;
import rpg.general.commerce.Trader;

/**
 * A Trader that does not keep track of money. This Trader can always buy or sell and does not
 * change the price when bidding or asking.
 */
public class VendorTrader implements Trader {

  @Override
  public void buy(double price) {
    // Do Nothing. This trader does not keep track of its money.
  }

  @Override
  public void sell(double price) {
    // Do Nothing. This trader does not keep track of its money.
  }

  @Override
  public boolean canBuy(double price) {
    return true;
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
    return 0.0;
  }

}
