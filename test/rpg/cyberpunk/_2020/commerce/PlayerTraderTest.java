package rpg.cyberpunk._2020.commerce;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PlayerTraderTest {
  private double money;

  @Before
  public void setUp() {
    money = 0.0;
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_BuyingAtAPriceOfNegativeOneTenth() {
    PlayerTrader trader = new PlayerTrader(money);

    trader.buy(-0.1);
  }

  @Test
  public void Should_ReturnMoneyAsEighty_When_BuyingAtAPriceOfTwenty_While_MoneyIsOneHundred() {
    PlayerTrader trader = new PlayerTrader(100.0);

    trader.buy(20.0);

    assertEquals(80.0, trader.getMoney(), 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_SellingAtAPriceOfNegativeOneTenth() {
    PlayerTrader trader = new PlayerTrader(money);

    trader.sell(-0.1);
  }

  @Test
  public void Should_ReturnMoneyAsOneHundredTwenty_When_SellingAtAPriceOfTwenty_While_MoneyIsOneHundred() {
    PlayerTrader trader = new PlayerTrader(100.0);

    trader.sell(20.0);

    assertEquals(120.0, trader.getMoney(), 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_CheckingCanBuyAtAPriceOfNegativeOneTenth() {
    PlayerTrader trader = new PlayerTrader(money);

    trader.canBuy(-0.1);
  }

  @Test
  public void Should_ReturnCanBuyAsTrue_When_MoneyIsAtLeastPrice_While_MoneyIsOneHundred() {
    PlayerTrader trader = new PlayerTrader(100.0);

    assertTrue(trader.canBuy(100.0));
  }

  @Test
  public void Should_ReturnCanBuyAsFalse_When_MoneyIsLessThanPrice_While_MoneyIsOneHundred() {
    PlayerTrader trader = new PlayerTrader(100.0);

    assertFalse(trader.canBuy(101.0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_CheckingCanSellAtAPriceOfNegativeOneTenth() {
    PlayerTrader trader = new PlayerTrader(money);

    trader.canSell(-0.1);
  }

  @Test
  public void Should_ReturnCanSellAsTrue_When_PriceIsAtLeastZero_While_MoneyIsOneHundred() {
    PlayerTrader trader = new PlayerTrader(100.0);

    assertTrue(trader.canSell(1.0));
  }

}
