package rpg.general.stats;

public class NullLevelable implements Levelable {
	public void increaseLevel() { }

	public void decreaseLevel() { }

	public void resetLevel() { }

	public int getLevel() {
		return 0;
	}
}
