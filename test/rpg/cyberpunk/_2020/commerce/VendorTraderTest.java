package rpg.cyberpunk._2020.commerce;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class VendorTraderTest {
  @Test
  public void Should_ReturnMoneyAsZero_When_BuyingAnything() {
    VendorTrader trader = new VendorTrader();

    trader.buy(10);

    assertEquals(0.0, trader.getMoney(), 0.0);
  }

  @Test
  public void Should_ReturnMoneyAsZero_When_SellingAnything() {
    VendorTrader trader = new VendorTrader();

    trader.sell(10);

    assertEquals(0.0, trader.getMoney(), 0.0);
  }

  @Test
  public void Should_ReturnCanBuyAsTrue_When_GivenAnyPrice() {
    VendorTrader trader = new VendorTrader();

    assertTrue(trader.canBuy(10));
  }

  @Test
  public void Should_ReturnCanSellAsTrue_When_GivenAnyPrice() {
    VendorTrader trader = new VendorTrader();

    assertTrue(trader.canSell(10));
  }

}
