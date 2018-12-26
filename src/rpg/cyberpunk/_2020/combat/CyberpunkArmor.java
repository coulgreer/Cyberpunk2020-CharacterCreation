package rpg.cyberpunk._2020.combat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import rpg.general.combat.BodyLocation;
import rpg.general.combat.Wearable;
import rpg.general.commerce.Product;

public class CyberpunkArmor extends Product implements Wearable {
	public enum ArmorType {
		HARD, SOFT;
	}

	public static final int DEFAULT_STOPPING_POWER = 0;
	public static final Durability DEFAULT_DURABILITY = new Durability(DEFAULT_STOPPING_POWER);

	private Map<BodyLocation, Boolean> areCovered;
	private Map<BodyLocation, Durability> localizedDurabilities;
	private ArmorType armorType;
	private int stoppingPower;
	private int encumbranceValue;

	public CyberpunkArmor(String name, String description, double cost, double weight,
			Iterator<BodyLocation> locationIterator, ArmorType armorType, int stoppingPower, int encumbrance) {
		super(name, description, cost, weight);
		this.armorType = armorType;
		this.stoppingPower = stoppingPower;
		this.encumbranceValue = encumbrance;
		initializeAreCovered();
		initializeArmorDurability();
		setUpBodyLocationValues(locationIterator, stoppingPower);
	}

	private void initializeAreCovered() {
		areCovered = new HashMap<BodyLocation, Boolean>();
		Iterator<BodyLocation> setIterator = BodyLocation.createIterator();
		while (setIterator.hasNext()) {
			areCovered.put(setIterator.next(), false);
		}
	}

	private void initializeArmorDurability() {
		localizedDurabilities = new HashMap<BodyLocation, Durability>();
		Iterator<BodyLocation> setIterator = BodyLocation.createIterator();
		while (setIterator.hasNext()) {
			localizedDurabilities.put(setIterator.next(), DEFAULT_DURABILITY);
		}
	}

	private void setUpBodyLocationValues(Iterator<BodyLocation> locationIterator, int stoppingPower) {
		while (locationIterator.hasNext()) {
			BodyLocation tempLocation = locationIterator.next();
			areCovered.replace(tempLocation, true);
			localizedDurabilities.replace(tempLocation, new Durability(stoppingPower));
		}
	}

	public boolean isCovering(BodyLocation location) {
		return areCovered.get(location);
	}

	public int getProtectionScore() {
		return stoppingPower;
	}

	public int getEncumbranceValue() {
		return encumbranceValue;
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof CyberpunkArmor)) {
			return false;
		}

		CyberpunkArmor armor = (CyberpunkArmor) o;
		return (isCovering(BodyLocation.HEAD) == armor.isCovering(BodyLocation.HEAD))
				&& (isCovering(BodyLocation.TORSO) == armor.isCovering(BodyLocation.TORSO))
				&& (isCovering(BodyLocation.RIGHT_ARM) == armor.isCovering(BodyLocation.RIGHT_ARM))
				&& (isCovering(BodyLocation.LEFT_ARM) == armor.isCovering(BodyLocation.LEFT_ARM))
				&& (isCovering(BodyLocation.RIGHT_LEG) == armor.isCovering(BodyLocation.RIGHT_LEG))
				&& (isCovering(BodyLocation.LEFT_LEG) == armor.isCovering(BodyLocation.LEFT_LEG))
				&& (getDurability(BodyLocation.HEAD) == armor.getDurability(BodyLocation.HEAD))
				&& (getDurability(BodyLocation.TORSO) == armor.getDurability(BodyLocation.TORSO))
				&& (getDurability(BodyLocation.RIGHT_ARM) == armor.getDurability(BodyLocation.RIGHT_ARM))
				&& (getDurability(BodyLocation.LEFT_ARM) == armor.getDurability(BodyLocation.LEFT_ARM))
				&& (getDurability(BodyLocation.RIGHT_LEG) == armor.getDurability(BodyLocation.RIGHT_LEG))
				&& (getDurability(BodyLocation.LEFT_LEG) == armor.getDurability(BodyLocation.LEFT_LEG))
				&& (getProtectionScore() == armor.getProtectionScore())
				&& (getEncumbranceValue() == armor.getEncumbranceValue());
	}

	public int hashCode() {
		return Objects.hash(isCovering(BodyLocation.HEAD), isCovering(BodyLocation.TORSO),
				isCovering(BodyLocation.RIGHT_ARM), isCovering(BodyLocation.LEFT_ARM),
				isCovering(BodyLocation.RIGHT_LEG), isCovering(BodyLocation.LEFT_LEG), getDurability(BodyLocation.HEAD),
				getDurability(BodyLocation.TORSO), getDurability(BodyLocation.RIGHT_ARM),
				getDurability(BodyLocation.LEFT_ARM), getDurability(BodyLocation.RIGHT_LEG),
				getDurability(BodyLocation.LEFT_LEG), getProtectionScore(), getEncumbranceValue());
	}

	public void damage(BodyLocation location, int damagePoints) {
		Durability durability = localizedDurabilities.get(location);
		durability.damage(damagePoints);
	}

	public void repair() {
		Iterator<Durability> iterator = localizedDurabilities.values().iterator();
		while (iterator.hasNext()) {
			Durability durability = iterator.next();
			durability.repair(durability.getMaxDurability());
		}
	}

	public ArmorType getArmorType() {
		return armorType;
	}

	public int getDurability(BodyLocation location) {
		Durability durability = localizedDurabilities.get(location);
		return durability.getCurrentDurability();
	}
}
