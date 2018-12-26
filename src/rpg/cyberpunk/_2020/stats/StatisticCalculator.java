package rpg.cyberpunk._2020.stats;

import rpg.general.stats.Levelable;

public interface StatisticCalculator {
	public double calculate();
	
	public void setStatistic(Levelable statistic);
}
