package rpg.cyberpunk._2020.stats;

import rpg.general.stats.Levelable;
import rpg.general.stats.NullLevelable;

public class RunCalculator implements StatisticCalculator {

	Levelable statistic;
	
	public RunCalculator() {
		statistic = new NullLevelable();
	}
	
	public void setStatistic(Levelable statistic) {
		this.statistic = statistic;
	}
	
	public double calculate() {
		double result = 3.0 * statistic.getLevel();
		return result;
	}
}
