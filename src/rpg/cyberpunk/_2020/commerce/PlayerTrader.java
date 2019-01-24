package rpg.cyberpunk._2020.commerce;

import rpg.Player;
import rpg.general.commerce.Tradeable;
import rpg.general.commerce.Trader;
import rpg.general.commerce.Product;
import rpg.general.commerce.QuantifiableProduct;

public class PlayerTrader implements Trader {
	private Player player;
	private double money;

	public PlayerTrader(Player p, double m) {
		if(p != null) {
			player = p;
		} else {
			throw new NullPointerException("The Player argument for type PlayerTrader is not allowed to be null.");
		}

		money = m;
	}

	@Override
	public void buy(Tradeable t, int quantity) {
		if(canBuy(t, quantity)) {
			money = money - t.getCost() * quantity;
		}
	}

	@Override
	public boolean canBuy(Tradeable t, int quantity) {
		return money >= t.getCost() * quantity;
	}

	@Override
	public void sell(Tradeable t, int quantity) {
		if(canSell(t, quantity)) {
			money = money + t.getCost() * quantity;
		}
	}

	@Override
	public boolean canSell(Tradeable t, int quantity) {
		return player.hasProducts(t, quantity);
	}

	@Override
	public double getMoney() {
		return money;
	}
}
