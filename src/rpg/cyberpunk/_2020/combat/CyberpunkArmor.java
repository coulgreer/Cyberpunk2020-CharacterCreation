package rpg.cyberpunk._2020.combat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rpg.general.combat.BodyLocation;
import rpg.general.combat.Protective;
import rpg.general.commerce.Item;

public class CyberpunkArmor implements Protective, Item, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1511256497126217581L;
	
	public static final String ARMOR_TYPE_SOFT = "Soft Armor";
	public static final String ARMOR_TYPE_HARD = "Hard Armor";
	public static final int DEFAULT_STOPPING_POWER = 0;

	private String name;
	private String description;
	private double cost;
	private double weight;
	private Map<BodyLocation, Durability> localizedDurabilities;
	private String armorType;
	private int stoppingPower;
	private int encumbranceValue;

	public CyberpunkArmor(String name, String description, double cost, double weight,
			Iterator<BodyLocation> coveredLocationIterator, String armorType, int stoppingPower, int encumbrance) {
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.weight = weight;
		this.armorType = armorType;
		this.stoppingPower = stoppingPower;
		this.encumbranceValue = encumbrance;
		initializeArmorDurability(coveredLocationIterator, stoppingPower);
	}

	private void initializeArmorDurability(Iterator<BodyLocation> coveredLocationIterator, int stoppingPower) {
		localizedDurabilities = new HashMap<BodyLocation, Durability>();
		while (coveredLocationIterator.hasNext()) {
			BodyLocation tempLocation = coveredLocationIterator.next();
			localizedDurabilities.put(tempLocation, new Durability(stoppingPower));
		}
	}

	@Override
	public double getCost() {
		return cost;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public String getArmorType() {
		return armorType;
	}

	@Override
	public boolean isCovering(BodyLocation location) {
		return localizedDurabilities.containsKey(location);
	}

	@Override
	public int getEncumbranceValue() {
		return encumbranceValue;
	}

	@Override
	public int getProtectionScore() {
		return stoppingPower;
	}

	@Override
	public int getDurabilityAt(BodyLocation location) {
		if (isCovering(location)) {
			Durability durability = localizedDurabilities.get(location);
			return durability.getCurrentDurability();
		} else {
			return DEFAULT_STOPPING_POWER;
		}
	}

	@Override
	public void damage(BodyLocation location, int damagePoints) {
		Durability durability = localizedDurabilities.get(location);
		int currentDurabilityPoints = durability.getCurrentDurability() - damagePoints;
		durability.setCurrentDurability(currentDurabilityPoints);
	}

	@Override
	public void repair(BodyLocation location, int repairPoints) {
		Durability durability = localizedDurabilities.get(location);
		int currentDurabilityPoints = durability.getCurrentDurability() + repairPoints;
		durability.setCurrentDurability(currentDurabilityPoints);
	}

	@Override
	public void damageAll(int damagePoints) {
		for (Map.Entry<BodyLocation, Durability> entry : localizedDurabilities.entrySet()) {
			int currentDurabilityPoints = entry.getValue().getCurrentDurability() - damagePoints;
			entry.getValue().setCurrentDurability(currentDurabilityPoints);
		}
	}

	@Override
	public void repairAll(int repairPoints) {
		for (Map.Entry<BodyLocation, Durability> entry : localizedDurabilities.entrySet()) {
			int currentDurabilityPoints = repairPoints + entry.getValue().getCurrentDurability();
			entry.getValue().setCurrentDurability(currentDurabilityPoints);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof CyberpunkArmor)) {
			return false;
		}

		CyberpunkArmor armor = (CyberpunkArmor) o;
		return isArmorEqual(armor);
	}

	private boolean isArmorEqual(CyberpunkArmor armor) {
		Iterator<BodyLocation> iterator = BodyLocation.createIterator();
		while (iterator.hasNext()) {
			BodyLocation location = iterator.next();
			if (isCovering(location) != armor.isCovering(location)
					|| getDurabilityAt(location) != armor.getDurabilityAt(location)) {
				return false;
			}
		}

		return (getProtectionScore() == armor.getProtectionScore())
				&& (getEncumbranceValue() == armor.getEncumbranceValue());
	}

	@Override
	public int hashCode() {
		ArrayList<Object> objectsToHash = new ArrayList<>();
		addIsCoveringTo(objectsToHash);
		addGetDurabilityTo(objectsToHash);
		objectsToHash.add(getProtectionScore());
		objectsToHash.add(getEncumbranceValue());

		return Arrays.hashCode(objectsToHash.toArray());
	}

	private void addIsCoveringTo(List<Object> list) {
		Iterator<BodyLocation> iterator = BodyLocation.createIterator();
		while (iterator.hasNext()) {
			BodyLocation location = iterator.next();
			list.add(isCovering(location));
		}
	}

	private void addGetDurabilityTo(List<Object> list) {
		Iterator<BodyLocation> iterator = BodyLocation.createIterator();
		while (iterator.hasNext()) {
			BodyLocation location = iterator.next();
			list.add(getDurabilityAt(location));
		}
	}

}
