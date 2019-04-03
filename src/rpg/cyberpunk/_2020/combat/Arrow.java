package rpg.cyberpunk._2020.combat;

import java.util.Objects;

import rpg.general.combat.Ammunition;
import rpg.util.Probability;

public class Arrow implements Ammunition {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8916026780282546741L;

	public static final String AMMUNITION_TYPE_ARROW = "Arrow";
	public static final int AMMO_PER_BOX = 12;

	public static enum Tip {
		TARGET_POINT("Target point", "The basic arrow/quarrel (bows use arrows, crossbows use quarrels)." //
				+ " Halves all armor SP, does normal damage.", 24.0, 1.0, 0.5, 0.5),
		BROADHEAD_POINT("Broadhead point", "A head consisting of two or more razor-sharp blades." //
				+ " Acts as a knife for armor penetration, penetrating damage is doubled.", 40.0, 2.0, 1.0, 0.5);

		private String name;
		private String description;
		private double cost;
		private double damageMultiplier;
		private double hardArmorMultiplier;
		private double softArmorMultiplier;

		Tip(String name, String description, double cost, double damageMultiplier, double hardArmorMultiplier,
				double softArmorMultiplier) {
			this.name = name;
			this.description = description;
			this.cost = cost;
			this.damageMultiplier = damageMultiplier;
			this.hardArmorMultiplier = hardArmorMultiplier;
			this.softArmorMultiplier = softArmorMultiplier;
		}

		public String getName() {
			return name;
		}

		public String getDescription() {
			return description;
		}

		public double getDamageMultiplier() {
			return damageMultiplier;
		}

		public double getHardArmorMultiplier() {
			return hardArmorMultiplier;
		}

		public double getSoftArmorMultiplier() {
			return softArmorMultiplier;
		}

		public double cost() {
			return cost;
		}
	}

	private Tip arrowTip;

	public Arrow(Tip arrowTip) {
		setArrowTip(arrowTip);
	}

	private void setArrowTip(Tip arrowTip) {
		if (arrowTip != null) {
			this.arrowTip = arrowTip;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String getName() {
		return AMMUNITION_TYPE_ARROW + " with " + arrowTip.getName();
	}

	@Override
	public String getDescription() {
		return "An arrow with a " + arrowTip.getName() + " tip./n/n   " //
				+ arrowTip.getName() + ": " + arrowTip.getDescription();
	}

	@Override
	public double getCost() {
		return arrowTip.cost() / AMMO_PER_BOX;
	}

	@Override
	public String getAmmunitionType() {
		return AMMUNITION_TYPE_ARROW;
	}

	protected Tip getArrowTip() {
		return arrowTip;
	}

	@Override
	public double getWeight() {
		return WEIGHT_OF_BOX / AMMO_PER_BOX;
	}

	@Override
	public int getAmmunitionPerBox() {
		return AMMO_PER_BOX;
	}

	/**
	 * An arrow does not provide the damage for a weapon. It only augments the bows
	 * raw damage.
	 * 
	 * @return null
	 */
	@Override
	public Probability getDamage() {
		return null;
	}

	@Override
	public String printBonuses() {
		return "Damage multiplier: " + arrowTip.getDamageMultiplier() + "/n" //
				+ "Soft Armor multiplier: " + arrowTip.getSoftArmorMultiplier() + "/n"//
				+ "Hard Armor multiplier: " + arrowTip.getHardArmorMultiplier();
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
		return arrow.getAmmunitionType().equals(getAmmunitionType()) && arrow.getArrowTip().equals(getArrowTip());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getAmmunitionType(), arrowTip);
	}
}
