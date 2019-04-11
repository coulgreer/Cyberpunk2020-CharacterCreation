package rpg.cyberpunk._2020.combat;

import java.util.Objects;

import rpg.general.combat.Ammunition;
import rpg.util.Die;
import rpg.util.Probability;

/**
 * This is an implementation of <code>Ammunition</code> that uses
 * <code>CaseMaterial</code>, <code>Bullet</code>, and a String representing the
 * weapons caliber. A cartridge uses both the case material and a bullet to
 * determine the overall cost while also using the bullet to get the damage
 * modifiers and armor penetration.
 * 
 * @author Coul Greer
 */
public class Cartridge implements Ammunition {
	public static final String AMMUNITION_TYPE_5MM = "5mm";
	public static final String AMMUNITION_TYPE_6MM = "6mm";
	public static final String AMMUNITION_TYPE_9MM = "9mm";
	public static final String AMMUNITION_TYPE_10MM = "10mm";
	public static final String AMMUNITION_TYPE_11MM = "11mm";
	public static final String AMMUNITION_TYPE_12MM = "12mm";
	public static final String AMMUNITION_TYPE_556 = "5.56";
	public static final String AMMUNITION_TYPE_762 = "7.62";
	public static final String AMMUNITION_TYPE_20MM = "20mm";

	/**
	 * The part of the cartridge that determines what the material used for the
	 * cartridge is made of. The default material being the caseless value.
	 * 
	 * @author Coul Greer
	 */
	public static enum CaseMaterial {
		CASELESS("Caseless", "The powder is the casing of the projectile.", 1.0), //
		COPPER("Copper", "A basic copper case for older weapons.", 2.0);

		private String name;
		private String description;
		private double costMultiplier;

		/**
		 * Constructs a <code>CaseMaterial</code> used to modify the cost of the
		 * cartridge as well as giving data for the name and description.
		 * 
		 * @param name           the identifier used to find the material used on a
		 *                       cartridge
		 * @param description    a blurb used to give an idea of what the case material
		 *                       is made of
		 * @param costMultiplier the amount to multiply the base cost of a cartridge by
		 */
		CaseMaterial(String name, String description, double costMultiplier) {
			this.name = name;
			this.description = description;
			this.costMultiplier = costMultiplier;
		}

		/**
		 * Returns the identifier of this case material.
		 * 
		 * @return the identifier of this case material
		 */
		public String getName() {
			return name;
		}

		/**
		 * Returns the blurb giving an idea of what the case material is made of.
		 * 
		 * @return the blurb giving an idea of what the case material is made of
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * Returns the value to multiply the base cost of a <code>Cartridge</code> by.
		 * 
		 * @return the value to multiply the base cost of a <code>Cartridge</code> by
		 */
		public double getCostMultiplier() {
			return costMultiplier;
		}
	}

	/**
	 * The part of the cartridge that dictates the damage modifier and the armor
	 * penetration. Also, modifies the base cost of a cartridge.
	 * 
	 * @author Coul Greer
	 */
	public static enum Bullet {
		SOFT_POINT("Soft Point", "These are the standard bullets that all guns fire.", 1.0),
		ARMOR_PIERCING("Armor Piercing",
				"AP bullets have a steel jacket or core meant to penetrate various forms of armor." //
						+ " The effects are armor SP x1/2 and penetrating damage x1/2 as well." //
						+ " This is because such bullets have little or no expansion, and therefore reduce true damage.",
				3.0),
		HOLLOW_POINT("Hollow Point", "Special hollow-nosed ammo made of soft, quickly mushrooming lead." //
				+ " When these rounds hit armor, the lead flattens bluntly and does mostly bruising damage." //
				+ " However, when these rounds hit flesh, the lead squashes out to cause a massive wound cavity." //
				+ " In effect, these rounds treat all armor as having 2x normal SP, but damage that penetrates is x1.5.",
				1.125);

		private String name;
		private String description;
		private double costMultiplier;

		/**
		 * Constructs a <code>Bullet</code> used to modify the cost of the cartridge as
		 * well as give part of the name and the basic description related to damage
		 * modification.
		 * 
		 * @param name           the identifier used to find the bullet used in a
		 *                       cartridge
		 * @param description    a blurb used to give an idea of what the bullet does
		 *                       when damaging
		 * @param costMultiplier the amount to multiply the base cost of a cartridge by
		 */
		Bullet(String name, String description, double costMultiplier) {
			this.name = name;
			this.description = description;
			this.costMultiplier = costMultiplier;
		}

		/**
		 * Returns the identifier of the bullet used by this <code>Cartridge</code>
		 * 
		 * @return the identifier of the bullet used by this <code>Cartridge</code>
		 */
		public String getName() {
			return name;
		}

		/**
		 * Returns a blurb giving the multipliers used in damage manipulation.
		 * 
		 * @return a blurb giving the multipliers used in damage manipulation
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * Returns the value to multiply the base cost of a <code>Cartridge</code> by.
		 * 
		 * @return the value to multiply the base cost of a <code>Cartridge</code> by
		 */
		public double getCostMultiplier() {
			return costMultiplier;
		}
	}

	private String caliber;
	private Bullet bullet;
	private CaseMaterial caseMaterial;
	private double baseCost;
	private double weight;

	/**
	 * Constructs a <code>Cartridge</code> with a given caliber used by
	 * <code>CyberpunkWeapon</code>s to determine if they are compatible. Also, uses
	 * <code>Bullet</code> and <code>CaseMaterial</code> to determine compatibility
	 * and cost.
	 * 
	 * @param caliber      the identifier used to check if this cartridge will fit
	 *                     inside a <code>CyberpunkWeapon</code> that expects this
	 *                     caliber of cartridge
	 * @param bullet       the delegate used to get the damage modifiers and
	 *                     multiply the cost
	 * @param caseMaterial a modifier to cost of this cartridge
	 * @param baseCost     the value that is multiplied by the <code>Bullet</code>
	 *                     and <code>CaseMaterial</code>
	 * @param weight       the heaviness of a cartridge
	 */
	public Cartridge(String caliber, Bullet bullet, CaseMaterial caseMaterial, double baseCost, double weight) {
		setCaliber(caliber);
		setBullet(bullet);
		setCost(baseCost);
		setWeight(weight);
		setCaseMaterial(caseMaterial);
	}

	private void setCaliber(String caliber) {
		if (caliber == null) {
			throw new IllegalArgumentException("The field 'caliber' cannot be null.");
		} else {
			this.caliber = caliber;
		}
	}

	private void setBullet(Bullet bullet) {
		if (bullet == null) {
			throw new IllegalArgumentException("The field 'bullet' cannot be null.");
		} else {
			this.bullet = bullet;
		}
	}

	private void setCaseMaterial(CaseMaterial caseMaterial) {
		if (caseMaterial == null) {
			throw new IllegalArgumentException("The field 'caseMaterial' cannot be null.");
		} else {
			this.caseMaterial = caseMaterial;
		}
	}

	private void setCost(double baseCost) {
		if (baseCost < 0) {
			throw new IllegalArgumentException("The field 'cost' cannot be a negative number.");
		} else {
			this.baseCost = baseCost;
		}
	}

	private void setWeight(double weight) {
		if (weight < 0) {
			throw new IllegalArgumentException("The field 'weight' cannot be a negative number.");
		} else {
			this.weight = weight;
		}
	}

	@Override
	public String getName() {
		return caseMaterial.getName() + " " + caliber + " " + bullet.getName();
	}

	@Override
	public String getDescription() {
		return "A " + caliber + " cartridge with a " + bullet.getName() + " bullet and a " + caseMaterial.getName()
				+ "case./n/n   " //
				+ "Bullet: " + bullet.getDescription() + "/n   " //
				+ "Case Mat.: " + caseMaterial.getDescription();
	}

	@Override
	public double getCost() {
		return (baseCost * bullet.getCostMultiplier() * caseMaterial.getCostMultiplier());
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public String getAmmunitionType() {
		return caliber;
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

	protected Bullet getBullet() {
		return bullet;
	}

	protected CaseMaterial getCaseMaterial() {
		return caseMaterial;
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
		return getAmmunitionType().equals(cartridge.getAmmunitionType()) && getBullet().equals(cartridge.getBullet())
				&& getCaseMaterial().equals(cartridge.getCaseMaterial());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getAmmunitionType(), getBullet(), getCaseMaterial());
	}
}
