package rpg.general.combat;

public interface Wearable {
	public boolean isCovering(BodyLocation location);

	public int getProtectionScore();

	public int getEncumbranceValue();
}
