package rpg.cyberpunk._2020.combat;

import java.util.Objects;

import rpg.general.commerce.Product;

public class Arrow extends Product implements Ammunition {
	public static enum ArrowHead {
		TARGET_POINT("Target point",
				"Made to penetrate archery targets easily, but it is just as easy to pull them out.", 1.0, 0.5, 0.5) {
			public double calculateCost(double cost) {
				return cost * 1.0;
			}
		};

		private String name;
		private String description;
		private double damageMultiplier;
		private double hardArmorMultiplier;
		private double softArmorMultiplier;

		ArrowHead(String name, String description, double damageMultiplier, double hardArmorMultiplier,
				double softArmorMultiplier) {
			this.name = name;
			this.description = description;
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

		public abstract double calculateCost(double cost);
	}

	public static final int AMMO_PER_BOX = 12;

	private ArrowHead arrowHead;

	public Arrow(String name, String description, ArrowHead arrowHead, double cost, double weight) {
		super(name, description, cost, weight);
		this.arrowHead = arrowHead;
	}

	public String getName() {
		return super.getName() + " " + arrowHead.getName();
	}

	public String getDescription() {
		return super.getDescription() + " " + arrowHead.getDescription();
	}

	public double getCost() {
		return arrowHead.calculateCost(super.getCost());
	}

	public double getSoftArmorMultiplier() {
		return arrowHead.getSoftArmorMultiplier();
	}

	public double getHardArmorMultiplier() {
		return arrowHead.getHardArmorMultiplier();
	}

	public double getDamageMultiplier() {
		return arrowHead.getDamageMultiplier();
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (o == null) {
			return false;
		}

		if (!(o instanceof Cartridge)) {
			return false;
		}

		Arrow arrow = (Arrow) o;
		return arrow.getAmmoType() == getAmmoType() && arrow.getArrowHead() == getArrowHead();
	}

	public int hashCode() {
		return Objects.hash(getAmmoType(), arrowHead);
	}

	public AmmoType getAmmoType() {
		return AmmoType.ARROW;
	}

	protected ArrowHead getArrowHead() {
		return arrowHead;
	}
}
