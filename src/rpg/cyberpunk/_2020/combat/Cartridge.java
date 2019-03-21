package rpg.cyberpunk._2020.combat;

import java.util.Objects;

import rpg.general.combat.Ammunition;

public class Cartridge implements Ammunition {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8990165631272366924L;

	public static final String AMMUNITION_TYPE_5MM = "5mm";
	public static final String AMMUNITION_TYPE_6MM = "6mm";
	public static final String AMMUNITION_TYPE_9MM = "9mm";
	public static final String AMMUNITION_TYPE_10MM = "10mm";
	public static final String AMMUNITION_TYPE_11MM = "11mm";
	public static final String AMMUNITION_TYPE_12MM = "12mm";
	public static final String AMMUNITION_TYPE_556 = "5.56";
	public static final String AMMUNITION_TYPE_762 = "7.62";
	public static final String AMMUNITION_TYPE_12_GAUGE = "12ga";
	public static final String AMMUNITION_TYPE_20MM = "20mm";
	public static final int AMMO_PER_BOX = 10;

	public static enum CaseMaterial {
		CASELESS("Caseless", "The powder is the casing of the projectile.", 1.0), //
		PLASTIC("Plastic", "A basic case made of plastic.", 1.0), //
		COPPER("Copper", "A basic copper case.", 2.0);

		private String name;
		private String description;
		private double costMultiplier;

		CaseMaterial(String name, String description, double costMultiplier) {
			this.name = name;
			this.description = description;
			this.costMultiplier = costMultiplier;
		}

		public String getName() {
			return name;
		}

		public String getDescription() {
			return description;
		}

		public double getCostMultiplier() {
			return costMultiplier;
		}
	}

	public static enum BulletType {
		SOFT_POINT("Soft Point", "These are the standard bullets that all guns fire.", 1.0, 1.0, 1.0, 1.0), //
		ARMOR_PIERCING("Armor Piercing",
				"These bullets have steel cores under a copper or gilding metal jacket, counting armor SP as half and damage as half.",
				3.0, 0.5, 0.5, 0.5), //
		BUCKSHOT("Buckshot", " Small balls or pellets, often made of lead.", 1.0, 1.0, 1.0, 1.0), //
		SHOTGUN_SLUG("Shotgun slug",
				"A heavy projectile made of lead, copper, or other material and fired from a shotgun.", 1.0, 1.0, 0.5,
				1.0);

		private String name;
		private String description;
		private double costMultiplier;
		private double damageMultiplier;
		private double hardArmorMultiplier;
		private double softArmorMultiplier;

		BulletType(String name, String description, double costMultiplier, double damageMultiplier,
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

		public double getCostMultiplier() {
			return costMultiplier;
		}
	}

	private String ammoType;
	private BulletType bulletType;
	private CaseMaterial caseMaterial;
	private double cost;

	public Cartridge(String ammoType, BulletType bulletType, CaseMaterial caseMaterial, double cost) {
		this.ammoType = ammoType;
		this.bulletType = bulletType;
		this.caseMaterial = caseMaterial;
		this.cost = cost;
	}

	@Override
	public String getName() {
		return ammoType + " " + bulletType.getName() + " " + caseMaterial.getName();
	}

	@Override
	public String getDescription() {
		return "A cartridge with a " + bulletType.getName() + " bullet and a " + caseMaterial.getName() + "case./n/n   " //
				+ bulletType.getName() + ": " + bulletType.getDescription() + "/n   " //
				+ caseMaterial.getName() + ": " + caseMaterial.getDescription();
	}

	@Override
	public double getCost() {
		return cost * bulletType.getCostMultiplier() * caseMaterial.getCostMultiplier();
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
	public String getAmmunitionType() {
		return ammoType;
	}

	protected BulletType getBullet() {
		return bulletType;
	}

	protected CaseMaterial getCaseMaterial() {
		return caseMaterial;
	}

	@Override
	public String printBonuses() {
		return "Damage multiplier: " + bulletType.getDamageMultiplier() + "/n" //
				+ "Soft Armor multiplier: " + bulletType.getSoftArmorMultiplier() + "/n"//
				+ "Hard Armor multiplier: " + bulletType.getHardArmorMultiplier();
	}

	@Override
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
		return cartridge.getAmmunitionType().equals(getAmmunitionType()) && cartridge.getBullet().equals(getBullet())
				&& cartridge.getCaseMaterial().equals(getCaseMaterial());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getAmmunitionType(), bulletType, caseMaterial);
	}
}
