package rpg.general.stats;

public interface Levelable {
	public void increaseLevel();

	public void decreaseLevel();

	public void resetLevel();

	public int getLevel();
}
