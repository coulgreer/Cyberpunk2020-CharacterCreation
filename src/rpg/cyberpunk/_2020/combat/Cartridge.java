package rpg.cyberpunk._2020.combat;

import java.util.Objects;

import rpg.general.combat.Ammunition;
import rpg.util.Die;
import rpg.util.Probability;

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
	public static final String AMMUNITION_TYPE_20MM = "20mm";
	public static final String AMMUNITION_TYPE_GRENADE = "Grenade";
	public static final int AMMO_PER_BOX = 100;

	public static enum CaseMaterial {
		CASELESS("Caseless", "The powder is the casing of the projectile.", 1.0), //
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
		SOFT_POINT("Soft Point", "These are the standard bullets that all guns fire.", 1.0, 1.0, 1.0, 1.0),
		ARMOR_PIERCING("Armor Piercing",
				"AP bullets have a steel jacket or core meant to penetrate various forms of armor." //
						+ " The effects are armor SP x1/2 and penetrating damage x1/2 as well." //
						+ " This is because such bullets have little or no expansion, and therefore reduce true damage.",
				3.0, 0.5, 0.5, 0.5),
		HOLLOW_POINT("Hollow Point", "Special hollow-nosed ammo made of soft, quickly mushrooming lead." //
				+ " When these rounds hit armor, the lead flattens bluntly and does mostly bruising damage." //
				+ " However, when these rounds hit flesh, the lead squashes out to cause a massive wound cavity." //
				+ " In effect, these rounds treat all armor as having 2x normal SP, but damage that penetrates is x1.5.",
				1.125, 1.5, 2.0, 2.0);

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

	private String caliber;
	private BulletType bulletType;
	private CaseMaterial caseMaterial;
	private double cost;

	public Cartridge(String caliber, BulletType bulletType, CaseMaterial caseMaterial, double cost) {
		this.caliber = caliber;
		this.bulletType = bulletType;
		this.caseMaterial = caseMaterial;
		this.cost = cost;
	}

	@Override
	public String getName() {
		return caliber + " " + bulletType.getName() + " " + caseMaterial.getName();
	}

	@Override
	public String getDescription() {
		return "A cartridge with a " + bulletType.getName() + " bullet and a " + caseMaterial.getName() + "case./n/n   " //
				+ bulletType.getName() + ": " + bulletType.getDescription() + "/n   " //
				+ caseMaterial.getName() + ": " + caseMaterial.getDescription();
	}

	@Override
	public double getCost() {
		return (cost * bulletType.getCostMultiplier() * caseMaterial.getCostMultiplier()) / AMMO_PER_BOX;
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
		return caliber;
	}

	protected BulletType getBullet() {
		return bulletType;
	}

	protected CaseMaterial getCaseMaterial() {
		return caseMaterial;
	}

	@Override
	public Probability getDamage() {
		Die die = null;
		int modifier = 0;

		switch (caliber) {
		case AMMUNITION_TYPE_5MM:
			die = new Die(1, 6);
			break;
		case AMMUNITION_TYPE_6MM:
			die = new Die(1, 6);
			modifier = 1;
			break;
		case AMMUNITION_TYPE_9MM:
			die = new Die(2, 6);
			modifier = 1;
			break;
		case AMMUNITION_TYPE_10MM:
			die = new Die(2, 6);
			modifier = 3;
			break;
		case AMMUNITION_TYPE_11MM:
			die = new Die(3, 6);
			break;
		case AMMUNITION_TYPE_12MM:
			die = new Die(4, 6);
			modifier = 1;
			break;
		case AMMUNITION_TYPE_556:
			die = new Die(5, 6);
			break;
		case AMMUNITION_TYPE_762:
			die = new Die(6, 6);
			modifier = 2;
			break;
		case AMMUNITION_TYPE_20MM:
			die = new Die(4, 10);
			break;
		default:
			die = new Die(0, 0);
			break;
		}

		return new Probability(die, modifier);
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
