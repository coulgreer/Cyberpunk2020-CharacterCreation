package rpg.general.stats;

public class NullStatistic implements Statistic {

	@Override
	public String getName() {
		return "";
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public void increaseLevel() {
	}

	@Override
	public void decreaseLevel() {
	}

	@Override
	public void resetLevel() {
	}
}
