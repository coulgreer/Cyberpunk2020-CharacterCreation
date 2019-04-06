package rpg.cyberpunk._2020.combat;

import rpg.general.combat.Ammunition;
import rpg.util.Probability;

/**
 * An ammunition that uses a payload in order to get derived stats such as
 * damage and description.
 * 
 * @author Coul Greer
 */
public class LaunchedGrenade implements Ammunition {
	/**
	 * The default amount of ammunition held in one box when purchasing.
	 */
	public static final int AMMO_PER_BOX = 1;

	public static final String AMMUNITION_TYPE_40MM = "40mm";

	private String name;
	private String description;
	private double weight;
	private double cost;
	private String ammunitionType;
	private Payload load;

	/**
	 * Constructs a basic grenade for use in launcher type weapons.
	 * 
	 * @param name           the name to identify this ammunition
	 * @param description    the blurb used to help give an idea of what this
	 *                       ammunition does
	 * @param ammunitionType the caliber used to check if this ammunition is
	 *                       compatible with a weapon
	 * @param load           the contents inside this ammunition to give the damage
	 *                       and effects
	 * @param cost           the amount of money needed to obtain this ammunition
	 * @param weight         the heaviness of this ammunition
	 */
	public LaunchedGrenade(String name, String description, String ammunitionType, Payload load, double cost,
			double weight) {
		this.name = name;
		this.description = description;
		this.ammunitionType = ammunitionType;
		this.load = load;
		this.cost = cost;
		this.weight = weight;
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
	public double getCost() {
		return cost;
	}

	@Override
	public String getAmmunitionType() {
		return ammunitionType;
	}

	@Override
	public int getAmmunitionPerBox() {
		return AMMO_PER_BOX;
	}

	@Override
	public Probability getDamage() {
		return load.getDamge();
	}

	@Override
	public String printBonuses() {
		return "Effects: " + load.getEffects();
	}

}
