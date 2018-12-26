package rpg.cyberpunk._2020.combat;

public class Durability implements Maintainable {
	private int maxDurability;
	private int currentDurability;

	public Durability(int maxDurability) {
		this.maxDurability = maxDurability;
		this.currentDurability = maxDurability;
	}

	public void damage(int damagePoints) {
		currentDurability -= damagePoints;
		if (currentDurability < 0) {
			currentDurability = 0;
		}
	}

	public void repair(int repairPoints) {
		currentDurability += repairPoints;
		if (currentDurability > maxDurability) {
			currentDurability = maxDurability;
		}
	}

	public int getCurrentDurability() {
		return currentDurability;
	}

	public int getMaxDurability() {
		return maxDurability;
	}
}
