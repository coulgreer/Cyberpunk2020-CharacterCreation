package rpg.cyberpunk._2020.combat;

import java.util.Objects;

import rpg.general.combat.Ammunition;
import rpg.util.Probability;

/**
 * An ammunition that uses a payload in order to get derived stats such as
 * damage and description.
 * 
 * @author Coul Greer
 */
public class LaunchedGrenade implements Ammunition {
	public static final String AMMUNITION_TYPE_40MM = "40mm";

	private static final long serialVersionUID = 1L;

	private double weight;
	private double cost;
	private String ammunitionType;
	private Payload load;

	/**
	 * Constructs a basic grenade for use in launcher type weapons.
	 * 
	 * @param ammunitionType the caliber used to check if this ammunition is
	 *                       compatible with a weapon
	 * @param load           the contents inside this ammunition to give the damage
	 *                       and effects
	 * @param cost           the amount of money needed to obtain this ammunition
	 * @param weight         the heaviness of this ammunition
	 */
	public LaunchedGrenade(String ammunitionType, Payload load, double cost, double weight) {
		this.ammunitionType = ammunitionType;
		this.load = load;
		this.cost = cost;
		this.weight = weight;
	}

	@Override
	public String getName() {
		return ammunitionType + " " + load.toString() + " grenade";
	}

	@Override
	public String getDescription() {
		return load.getEffects();
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
	public Probability getDamage() {
		return load.getDamge();
	}

	protected Payload getPayload() {
		return load;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (o == null) {
			return false;
		}

		if (!(o instanceof LaunchedGrenade)) {
			return false;
		}

		LaunchedGrenade grenade = (LaunchedGrenade) o;
		return getPayload().equals(grenade.getPayload()) && getAmmunitionType().equals(grenade.getAmmunitionType());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPayload(), getAmmunitionType());
	}

}
