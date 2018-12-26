package rpg.cyberpunk._2020.stats;

import rpg.general.stats.Levelable;
import rpg.general.stats.NullLevelable;

public class LeapCalculator implements StatisticCalculator {

	Levelable statistic;

	public LeapCalculator() {
		statistic = new NullLevelable();
	}

	public void setStatistic(Levelable statistic) {
		this.statistic = statistic;
	}

	public double calculate() {
		double result = statistic.getLevel() / 4.0;
		return result;
	}
}
