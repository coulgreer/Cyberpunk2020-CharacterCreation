package rpg.cyberpunk._2020.combat;

import java.util.Objects;

import rpg.general.combat.Ammunition;

public class Arrow implements Ammunition {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8916026780282546741L;

	public static final String AMMUNITION_TYPE_ARROW = "Arrow";
	public static final int AMMO_PER_BOX = 12;

	public static enum ArrowTip {
		TARGET_POINT("Target point",
				"Made to penetrate archery targets easily, but it is just as easy to pull them out.", 1.0, 1.0, 0.5,
				0.5);

		private String name;
		private String description;
		private double costMultiplier;
		private double damageMultiplier;
		private double hardArmorMultiplier;
		private double softArmorMultiplier;

		ArrowTip(String name, String description, double costMultiplier, double damageMultiplier,
				double hardArmorMultiplier, double softArmorMultiplier) {
			this.name = name;
			this.description = description;
			this.costMultiplier = costMultiplier;
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

		public double costMultiplier() {
			return costMultiplier;
		}
	}

	private ArrowTip arrowTip;
	private double cost;

	public Arrow(ArrowTip arrowTip, double cost) {
		setArrowTip(arrowTip);
		setCost(cost);
	}

	private void setArrowTip(ArrowTip arrowTip) {
		if (arrowTip != null) {
			this.arrowTip = arrowTip;
		} else {
			throw new NullPointerException();
		}
	}

	private void setCost(double cost) {
		if (cost >= 0.0) {
			this.cost = cost;
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
		return (cost * arrowTip.costMultiplier()) / AMMO_PER_BOX;
	}

	@Override
	public String getAmmunitionType() {
		return AMMUNITION_TYPE_ARROW;
	}

	protected ArrowTip getArrowTip() {
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
