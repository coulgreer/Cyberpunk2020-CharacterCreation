package rpg.cyberpunk._2020.commerce;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import rpg.general.commerce.Tradeable;

public class PlayerTraderTest {

	@Test
	public void testMoneyEqualsEightIfStartingMoneyIsTwentyAndThenTwelveIsSpent() {
		PlayerTrader trader = new PlayerTrader(20.0);

		trader.buy(12.0);

		assertEquals(8.0, trader.getMoney(), 0.0);
	}

	@Test
	public void testMoneyEqualsTenIfStartingMoneyIsZeroAndThenTenIsAquired() {
		PlayerTrader trader = new PlayerTrader(0.0);

		trader.sell(10.0);

		assertEquals(10.0, trader.getMoney(), 0.0);
	}

	@Test
	public void testCanBuyIfCurrentMoneyIsFiveAndPriceIsFive() {
		PlayerTrader trader = new PlayerTrader(5.0);

		assertTrue(trader.canBuy(5.0));
	}

	@Test
	public void testCannotBuyIfCurrentMoneyIsFiveAndPriceIsSix() {
		PlayerTrader trader = new PlayerTrader(5.0);

		assertFalse(trader.canBuy(6.0));
	}

	@Test
	public void testCanSellAlways() {
		PlayerTrader trader = new PlayerTrader();

		assertTrue(trader.canSell(-5.0));
		assertTrue(trader.canSell(100.0));
	}

	@Test
	public void testBidPriceEqualsTradeableCost() {
		Tradeable mockTradeable = mock(Tradeable.class);
		when(mockTradeable.getCost()).thenReturn(10.0);

		PlayerTrader trader = new PlayerTrader();

		assertEquals(10.0, trader.getBidPrice(mockTradeable), 0.0);
	}

	@Test
	public void testAskPriceEqualsTradeableCost() {
		Tradeable mockTradeable = mock(Tradeable.class);
		when(mockTradeable.getCost()).thenReturn(10.0);

		PlayerTrader trader = new PlayerTrader();

		assertEquals(10.0, trader.getAskPrice(mockTradeable), 0.0);
	}

}
