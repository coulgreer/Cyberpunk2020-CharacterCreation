package rpg.cyberpunk._2020.stats;

import rpg.general.stats.Levelable;
import rpg.general.stats.NullLevelable;

public class CarryCalculator implements StatisticCalculator {
	Levelable statistic;
	
	public CarryCalculator() {
		statistic = new NullLevelable();
	}
	
	public void setStatistic(Levelable statistic) {
		this.statistic = statistic;
	}
	
	public double calculate() {
		double result = 10.0 * statistic.getLevel();
		return result;
	}
}
