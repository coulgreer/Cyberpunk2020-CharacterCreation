package rpg.cyberpunk._2020.combat;

import java.util.Objects;

import rpg.general.combat.Ammunition;
import rpg.util.Die;
import rpg.util.Probability;

/**
 * This is an implementation of <code>Ammunition</code> that uses
 * <code>Load</code> in order to determine the cost multiplier. Also, partially
 * delegates to Load in order to the ShotShell's name as well as its
 * description.
 * 
 * @author Coul Greer
 */
public class ShotShell implements Ammunition {
	/**
	 * The default weight of a single shot shell.
	 */
	public static final double WEIGHT = 0.05;

	public static final String AMMUNITION_TYPE_20_GAUGE = "20ga";
	public static final String AMMUNITION_TYPE_12_GAUGE = "12ga";
	public static final String AMMUNITION_TYPE_10_GAUGE = "10ga";

	/**
	 * The part of the ShotShell that multiplies the base cost of this ammunition
	 * and gives a blurb of how the ammunition modifies the behavior of a
	 * <code>CyberpunkWeapon</code>.
	 * 
	 * @author Coul Greer
	 */
	public static enum Load {
		BUCKSHOT("Buckshot", " Small balls or pellets, often made of lead.", 1.0), //
		SLUG("Slug", "A solid shotgun round, using one large, rigid-core bullet instead of shot." //
				+ " The slug does 3D6+1 (20-gauge), 4D6+2 (12-gauge), 5D6+3 (10-gauge) damage at all ranges, and has no area effect."//
				+ " The round is AP; any damage that penetrates hard armor is not halved.", 1.0);

		private String name;
		private String description;
		private double costMultiplier;

		/**
		 * Constructs the contents used inside a shotshell to determine the cost
		 * multiplier and the description.
		 * 
		 * @param name           the identifier for this load
		 * @param description    the blurb used to give an idea of what contents are
		 *                       used in the load
		 * @param costMultiplier the multiplier used to modify the value of an
		 *                       <code>Item</code>
		 */
		Load(String name, String description, double costMultiplier) {
			this.name = name;
			this.description = description;
			this.costMultiplier = costMultiplier;
		}

		/**
		 * Returns the identifier of this load.
		 * 
		 * @return the identifier of this load
		 */
		public String getName() {
			return name;
		}

		/**
		 * Returns the blurb giving an idea of what the load does when used.
		 * 
		 * @return the blurb giving an idea of what the load does when used
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * Returns the modifier used on a flat cost.
		 * 
		 * @return the modifier used on a flat cost
		 */
		public double getCostMultiplier() {
			return costMultiplier;
		}
	}

	private String gauge;
	private Load load;
	private double baseCost;

	/**
	 * Constructs a ShotShell composed of a gauge represented as String and a
	 * <code>Load</code> used to derive the description, name, and cost.
	 * 
	 * @param gauge    the size of the shotshell that is used when seeing if shell
	 *                 is compatible with <code>CyberpunkWeapon</code>
	 * @param load     the delegate used to modify the base stats
	 * @param baseCost the cost before the load modifies
	 */
	public ShotShell(String gauge, Load load, double baseCost) {
		setGauge(gauge);
		setLoad(load);
		setCost(baseCost);
	}

	private void setGauge(String gauge) {
		if (gauge == null) {
			throw new IllegalArgumentException("The field 'gauge' cannot be null.");
		} else {
			this.gauge = gauge;
		}
	}

	private void setLoad(Load load) {
		if (load == null) {
			throw new IllegalArgumentException("The field 'load' cannot be null.");
		} else {
			this.load = load;
		}
	}

	private void setCost(double baseCost) {
		if (baseCost < 0) {
			throw new IllegalArgumentException("The field 'cost' cannot be a negative number.");
		} else {
			this.baseCost = baseCost;
		}
	}

	@Override
	public String getName() {
		return gauge + " " + load.getName();
	}

	@Override
	public String getDescription() {
		return "A " + gauge + " shotgunshell loaded with " + load.getName();
	}

	@Override
	public double getWeight() {
		return WEIGHT;
	}

	@Override
	public double getCost() {
		return baseCost;
	}

	@Override
	public String getAmmunitionType() {
		return gauge;
	}

	@Override
	public Probability getDamage() {
		Die die = null;
		int modifier = 0;

		switch (gauge) {
		case AMMUNITION_TYPE_20_GAUGE:
			die = new Die(3, 6);
			if (load == Load.SLUG) {
				modifier = 1;
			}
			break;
		case AMMUNITION_TYPE_12_GAUGE:
			die = new Die(4, 6);
			if (load == Load.SLUG) {
				modifier = 2;
			}
			break;
		case AMMUNITION_TYPE_10_GAUGE:
			die = new Die(5, 6);
			if (load == Load.SLUG) {
				modifier = 3;
			}
			break;
		default:
			die = new Die(0, 0);
			break;
		}

		return new Probability(die, modifier);
	}

	protected Load getLoad() {
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

		if (!(o instanceof ShotShell)) {
			return false;
		}

		ShotShell shell = (ShotShell) o;
		return getAmmunitionType().equals(shell.getAmmunitionType()) && getLoad().equals(shell.getLoad());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getAmmunitionType(), getLoad());
	}

}
