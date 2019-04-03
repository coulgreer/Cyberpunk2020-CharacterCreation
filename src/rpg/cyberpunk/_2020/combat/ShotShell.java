package rpg.cyberpunk._2020.combat;

import rpg.general.combat.Ammunition;
import rpg.util.Die;
import rpg.util.Probability;

public class ShotShell implements Ammunition {
	public static final String AMMUNITION_TYPE_20_GAUGE = "20ga";
	public static final String AMMUNITION_TYPE_12_GAUGE = "12ga";
	public static final String AMMUNITION_TYPE_10_GAUGE = "10ga";
	public static final int AMMO_PER_BOX = 12;

	public static enum Load {
		BUCKSHOT("Buckshot", " Small balls or pellets, often made of lead.", 1.0, 1.0, 1.0, 1.0), //
		SLUG("Shotgun slug", "A solid shotgun round, using one large, rigid-core bullet instead of shot." //
				+ " The slug does 3D6+1 (20-gauge), 4D6+2 (12-gauge), 5D6+3 (10-gauge) damage at all ranges, and has no area effect."//
				+ " The round is AP; any damage that penetrates hard armor is not halved.", 1.0, 1.0, 0.5, 1.0);

		private String name;
		private String description;
		private double costMultiplier;
		private double damageMultiplier;
		private double hardArmorMultiplier;
		private double softArmorMultiplier;

		Load(String name, String description, double costMultiplier, double damageMultiplier,
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

	private String gauge;
	private Load load;
	private int cost;

	public ShotShell(String gauge, Load load, int cost) {
		this.gauge = gauge;
		this.load = load;
		this.cost = cost;
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
		return WEIGHT_OF_BOX / AMMO_PER_BOX;
	}

	@Override
	public double getCost() {
		return cost;
	}

	@Override
	public String getAmmunitionType() {
		return gauge;
	}

	@Override
	public int getAmmunitionPerBox() {
		return AMMO_PER_BOX;
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

	@Override
	public String printBonuses() {
		return "Damage multiplier: " + load.getDamageMultiplier() + "/n" //
				+ "Soft Armor multiplier: " + load.getSoftArmorMultiplier() + "/n"//
				+ "Hard Armor multiplier: " + load.getHardArmorMultiplier();
	}

}
