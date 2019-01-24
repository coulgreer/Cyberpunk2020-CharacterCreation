package rpg.general.stats;

public class NullLevelable implements Levelable {
	@Override
	public void increaseLevel() { }

	@Override
	public void decreaseLevel() { }

	@Override
	public void resetLevel() { }

	@Override
	public int getLevel() {
		return 0;
	}
}
