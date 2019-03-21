package rpg.cyberpunk._2020.commerce;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import rpg.general.commerce.Tradeable;

public class VendorTraderTest {

	@Test
	public void testMoneyEqualsNegativeOneIfBuyingAtPriceTen() {
		VendorTrader trader = new VendorTrader();

		trader.buy(10.0);

		assertEquals(-1.0, trader.getMoney(), 0.0);
	}

	@Test
	public void testMoneyEqualsNegativeOneIfBuyingAtPriceFive() {
		VendorTrader trader = new VendorTrader();

		trader.buy(5.0);

		assertEquals(-1.0, trader.getMoney(), 0.0);
	}

	@Test
	public void testMoneyEqualsNegativeOneIfSellingAtPriceTen() {
		VendorTrader trader = new VendorTrader();

		trader.sell(10.0);

		assertEquals(-1.0, trader.getMoney(), 0.0);
	}

	@Test
	public void testMoneyEqualsNegativeOneIfSellingAtPriceFive() {
		VendorTrader trader = new VendorTrader();

		trader.sell(5.0);

		assertEquals(-1.0, trader.getMoney(), 0.0);
	}

	@Test
	public void testCanSellAlwaysTrue() {
		VendorTrader trader = new VendorTrader();

		assertTrue(trader.canSell(-5.0));
		assertTrue(trader.canSell(100.0));
	}

	@Test
	public void testCanBuyAlwaysTrue() {
		VendorTrader trader = new VendorTrader();

		assertTrue(trader.canBuy(-5.0));
		assertTrue(trader.canBuy(100.0));
	}

	@Test
	public void testBidPriceEqualsTradeableCost() {
		Tradeable mockTradeable = mock(Tradeable.class);
		when(mockTradeable.getCost()).thenReturn(10.0);

		VendorTrader trader = new VendorTrader();

		assertEquals(10.0, trader.getBidPrice(mockTradeable), 0.0);
	}

	@Test
	public void testAskPriceEqualsTradeableCost() {
		Tradeable mockTradeable = mock(Tradeable.class);
		when(mockTradeable.getCost()).thenReturn(10.0);

		VendorTrader trader = new VendorTrader();

		assertEquals(10.0, trader.getAskPrice(mockTradeable), 0.0);
	}

}
