package rpg.cyberpunk._2020.combat;

import java.util.Objects;

import rpg.general.combat.Ammunition;
import rpg.util.NullProbability;
import rpg.util.Probability;

/**
 * This is an implementation of <code>Ammunition</code> that uses a
 * <code>Tip</code> in order to get the description of this ammunition. The
 * description in this case displays the special effects such as armor
 * penetration and damage multiplication.
 * 
 * @author Coul Greer
 */
public class Arrow implements Ammunition {
	/**
	 * The default weight of a single arrow.
	 */
	public static final double WEIGHT = 0.05;

	public static final String AMMUNITION_TYPE_ARROW = "Arrow";

	/**
	 * The part of an <code>Arrow</code> that determines the cost, description, and
	 * modifies the arrow's name.
	 * 
	 * @author Coul Greer
	 */
	public static enum Tip {
		TARGET("Target", "The basic arrow/quarrel (bows use arrows, crossbows use quarrels)." //
				+ " Halves all armor SP, does normal damage.", 2.0),
		BROADHEAD("Broadhead", "A head consisting of two or more razor-sharp blades." //
				+ " Acts as a knife for armor penetration, penetrating damage is doubled.", 3.5);

		private String name;
		private String description;
		private double cost;

		/**
		 * Constructs a value that belongs to a set of known <code>Tips</code> that
		 * provides an <code>Arrow</code> with its name, description, and its base cost.
		 * 
		 * @param name        the identifier for this tip
		 * @param description a blurb used to give an idea of what the given tip does
		 * @param cost        the amount of money that the tip is worth
		 */
		Tip(String name, String description, double cost) {
			this.name = name;
			this.description = description;
			this.cost = cost;
		}

		/**
		 * Returns the identifier of this tip.
		 * 
		 * @return the identifier of this tip
		 */
		public String getName() {
			return name;
		}

		/**
		 * Returns the blurb giving an idea of what the tip does when used.
		 * 
		 * @return the blurb giving an idea of what the tip does when used
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * Returns the flat value used when getting the cost of an arrow as a whole.
		 * 
		 * @return the flat value used when getting the cost of an arrow
		 */
		public double cost() {
			return cost;
		}
	}

	private Tip arrowTip;

	/**
	 * Constructs an <code>Arrow</code> instance with a <code>Tip</code> that
	 * dictates this arrow's name, description, and cost.
	 * 
	 * @param tip one of the tips used to derived the arrow's name, description, and
	 *            base cost
	 */
	public Arrow(Tip tip) {
		setTip(tip);
	}

	private void setTip(Tip arrowTip) {
		if (arrowTip == null) {
			throw new IllegalArgumentException("The field 'arrowTip' cannot be null.");
		} else {
			this.arrowTip = arrowTip;
		}
	}

	@Override
	public String getName() {
		return arrowTip.getName() + " " + AMMUNITION_TYPE_ARROW;
	}

	@Override
	public String getDescription() {
		return "An arrow with a " + arrowTip.getName() + "./n/n   " //
				+ "Tip: " + arrowTip.getDescription();
	}

	@Override
	public double getCost() {
		return arrowTip.cost();
	}

	@Override
	public String getAmmunitionType() {
		return AMMUNITION_TYPE_ARROW;
	}

	@Override
	public double getWeight() {
		return WEIGHT;
	}

	/**
	 * An arrow does not provide the damage for a weapon. It only augments the bows
	 * raw damage.
	 * 
	 * @return a pool of 0 dice
	 */
	@Override
	public Probability getDamage() {
		return NullProbability.getInstance();
	}

	protected Tip getArrowTip() {
		return arrowTip;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (o == null) {
			return false;
		}

		if (!(o instanceof Arrow)) {
			return false;
		}

		Arrow arrow = (Arrow) o;
		return getAmmunitionType().equals(arrow.getAmmunitionType()) && getArrowTip().equals(arrow.getArrowTip());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getAmmunitionType(), getArrowTip());
	}
}
