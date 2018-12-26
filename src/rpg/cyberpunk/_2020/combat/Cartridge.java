package rpg.cyberpunk._2020.combat;

import java.util.Objects;

import rpg.general.commerce.Product;

public class Cartridge extends Product implements Ammunition {
	public static enum CaseMaterial {
		CASELESS("Caseless", "The powder is the casing of the projectile.") {
			public double calculateCost(double cost) {
				return cost * 1.0;
			}
		},
		PLASTIC("Plastic", "A basic case made of plastic.") {
			public double calculateCost(double cost) {
				return cost * 1.0;
			}
		},
		COPPER("Copper", "A basic copper case.") {
			public double calculateCost(double cost) {
				return cost * 2.0;
			}
		};

		private String name;
		private String description;

		CaseMaterial(String name, String description) {
			this.name = name;
			this.description = description;
		}

		public String getName() {
			return name;
		}

		public String getDescription() {
			return description;
		}

		public abstract double calculateCost(double cost);
	}

	public static enum BulletType {
		SOFT_POINT("Soft Point", "These are the standard bullets that all guns fire.", 1.0, 1.0, 1.0) {
			public double calculateCost(double cost) {
				return cost * 1.0;
			}
		},
		ARMOR_PIERCING("Armor Piercing",
				"These bullets have steel cores under a copper or gilding metal jacket, counting armor SP as half and damage as half.",
				0.5, 0.5, 0.5) {
			public double calculateCost(double cost) {
				return cost * 3.0;
			}
		},
		SHOT("Shot", " Small balls or pellets, often made of lead.", 1.0, 1.0, 1.0) {
			public double calculateCost(double cost) {
				return cost * 1.0;
			}
		},
		SHOTGUN_SLUG("Shotgun slug",
				"A heavy projectile made of lead, copper, or other material and fired from a shotgun.", 1.0, 0.5, 1.0) {
			public double calculateCost(double cost) {
				return cost * 1.0;
			}
		};

		private String name;
		private String description;
		private double damageMultiplier;
		private double hardArmorMultiplier;
		private double softArmorMultiplier;

		BulletType(String name, String description, double damageMultiplier, double hardArmorMultiplier,
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

	public static final int AMMO_PER_BOX = 10;

	private AmmoType ammoType;
	private BulletType bulletType;
	private CaseMaterial caseMaterial;

	public Cartridge(AmmoType ammoType, BulletType bulletType, CaseMaterial caseMaterial, String description,
			double cost, double weight) {
		super(ammoType.toString(), description, cost, weight);
		this.ammoType = ammoType;
		this.bulletType = bulletType;
		this.caseMaterial = caseMaterial;
	}

	public String getName() {
		return super.getName() + " " + bulletType.getName() + " " + caseMaterial.getName();
	}

	public String getDescription() {
		return super.getDescription() + " " + bulletType.getDescription() + " " + caseMaterial.getDescription();
	}

	public double getCost() {
		return bulletType.calculateCost(caseMaterial.calculateCost(super.getCost()));
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

		Cartridge cartridge = (Cartridge) o;
		return cartridge.getAmmoType() == getAmmoType() && cartridge.getBullet() == getBullet()
				&& cartridge.getCaseMaterial() == getCaseMaterial();
	}

	public int hashCode() {
		return Objects.hash(getAmmoType(), bulletType, caseMaterial);
	}

	public double getSoftArmorMultiplier() {
		return bulletType.getSoftArmorMultiplier();
	}

	public double getHardArmorMultiplier() {
		return bulletType.getHardArmorMultiplier();
	}

	public double getDamageMultiplier() {
		return bulletType.getDamageMultiplier();
	}

	public AmmoType getAmmoType() {
		return ammoType;
	}

	protected BulletType getBullet() {
		return bulletType;
	}

	protected CaseMaterial getCaseMaterial() {
		return caseMaterial;
	}
}
