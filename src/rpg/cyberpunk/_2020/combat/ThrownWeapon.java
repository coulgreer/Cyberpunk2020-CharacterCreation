package rpg.cyberpunk._2020.combat;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.general.combat.Ammunition;
import rpg.general.combat.AmmunitionContainer;
import rpg.general.combat.Combatant;
import rpg.general.combat.EmptyAmmunitionContainer;
import rpg.general.combat.WeaponAttachment;
import rpg.util.Die;
import rpg.util.Probability;

/**
 * A Cyberpunk weapon that determines its range based on the weight of the
 * weapon and the combatant wielding it. Uses the athletics skill to determine
 * the bonus for accuracy from the wielding combatant
 * 
 * @author Coul Greer
 */
public class ThrownWeapon extends CyberpunkWeapon {
	/**
	 * The default rate of fire of any thrown weapon.
	 */
	public static final int RATE_OF_FIRE = 1;

	/**
	 * The default ammunition of a thrown weapon. Once this weapon is thrown it must
	 * be \"reloaded\" by picking it back up unless it is a grenade.
	 */
	public static final int AMMUNITION_CAPACITY = 1;

	/**
	 * The minimum weight amount allowed without suffering a range penalty.
	 */
	private static final int WEIGHT_THRESHOLD = 1;

	private String weaponName;
	private String description;
	private String weaponType;
	private String skillName;
	private int weaponAccuracy;
	private Concealability concealability;
	private Availability availability;
	private Probability damage;
	private AmmunitionContainer ammunitionContainer;
	private Reliability reliability;
	private int rangeModifier;
	private double cost;
	private double weight;
	private Combatant combatant;

	/**
	 * Constructs a thrown weapon with a payload. The payload is used to get the
	 * damage and description for this weapon. This constructor is akin to a firearm
	 * and a thrown weapon.
	 * 
	 * @param weaponName     the name used to identify this weapon
	 * @param weaponType     the category of weapon that this weapon belongs to
	 * @param weaponAccuracy the flat modifier to accuracy based solely on the
	 *                       weapon
	 * @param concealability the rating representing the ability to hide this weapon
	 * @param availability   the rating representing how easy this weapon is to
	 *                       obtain
	 * @param load           the payload held inside the thrown weapon to derive
	 *                       damage and description
	 * @param reliability    the rating representing the probability of a weapon
	 *                       jamming or malfunctioning
	 * @param cost           the amount of money this weapon is worth
	 * @param weight         the heaviness of this weapon
	 */
	public ThrownWeapon(String weaponName, String weaponType, int weaponAccuracy, Concealability concealability,
			Availability availability, Payload load, Reliability reliability, double cost, double weight) {
		this(weaponName, load.getEffects(), weaponType, weaponAccuracy, concealability, availability, load.getDamge(),
				reliability, cost, weight);
	}

	/**
	 * Constructs a thrown weapon that does not allow null values or non-negative
	 * values excluding weaponAccuracy.
	 * 
	 * @param weaponName     the name used to identify this weapon
	 * @param description    a blurb used to give an idea of what this weapon is/can
	 *                       do
	 * @param weaponType     the category of weapon that this weapon belongs to
	 * @param weaponAccuracy the flat modifier to accuracy based solely on the
	 *                       weapon
	 * @param concealability the rating representing the ability to hide this weapon
	 * @param availability   the rating representing how easy this weapon is to
	 *                       obtain
	 * @param damage         the probability of dealing damage represented as die
	 * @param reliability    the rating representing the probability of a weapon
	 *                       jamming or malfunctioning
	 * @param cost           the amount of money this weapon is worth
	 * @param weight         the heaviness of this weapon
	 */
	public ThrownWeapon(String weaponName, String description, String weaponType, int weaponAccuracy,
			Concealability concealability, Availability availability, Probability damage, Reliability reliability,
			double cost, double weight) {
		setWeaponName(weaponName);
		setDescription(description);
		setWeaponType(weaponType);
		this.skillName = CyberpunkSkill.ATHLETICS;
		this.weaponAccuracy = weaponAccuracy;
		setConcealability(concealability);
		setAvailability(availability);
		setDamage(damage);
		ammunitionContainer = new EmptyAmmunitionContainer();
		setReliability(reliability);
		setWeight(weight);
		setCost(cost);
		this.combatant = NullCombatant.getInstance();
	}

	private void setWeaponName(String weaponName) {
		if (weaponName != null) {
			this.weaponName = weaponName;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setDescription(String description) {
		if (description != null) {
			this.description = description;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setWeaponType(String weaponType) {
		if (weaponType != null) {
			this.weaponType = weaponType;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setConcealability(Concealability concealability) {
		if (concealability != null) {
			this.concealability = concealability;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setAvailability(Availability availability) {
		if (availability != null) {
			this.availability = availability;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setDamage(Probability damage) {
		if (damage != null) {
			this.damage = damage;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setReliability(Reliability reliability) {
		if (reliability != null) {
			this.reliability = reliability;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setWeight(double weight) {
		if (weight >= 0.0) {
			this.weight = weight;
			this.rangeModifier = calculateRange(weight);
		} else {
			throw new IllegalArgumentException();
		}
	}

	private int calculateRange(double weight) {
		int modifier;

		if (weight > WEIGHT_THRESHOLD) {
			modifier = ((int) Math.round(weight) - WEIGHT_THRESHOLD) * (-10);
		} else {
			modifier = 0;
		}

		return modifier;
	}

	private void setCost(double cost) {
		if (cost >= 0.0) {
			this.cost = cost;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setCombatant(Combatant c) {
		if (c != null) {
			combatant = c;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String getWeaponType() {
		return weaponType;
	}

	@Override
	public String getSkillName() {
		return skillName;
	}

	@Override
	public int getRangeScore() {
		return getRangeModifier() + combatant.getRangeModifier(true);
	}

	@Override
	public int getDamageScore() {
		return getDamageModifier() + combatant.getDamageModifier(this);
	}

	@Override
	public int getHitScore() {
		return getHitModifier() + combatant.getHitModifier(this);
	}

	@Override
	public Die getDamageDice() {
		return damage.getDice();
	}

	@Override
	public WeaponAttachment addAttachment(WeaponAttachment attachment) {
		return attachment;
	}

	@Override
	public Iterator<WeaponAttachment> getAttachments() {
		return Collections.emptyIterator();
	}

	@Override
	public boolean fire(int numberOfShots) {
		return true;
	}

	@Override
	public List<Ammunition> reload(AmmunitionContainer newContainer) {
		return ammunitionContainer.transferAmmunitionFrom(newContainer);
	}

	@Override
	public int getAmmunitionCount() {
		return AMMUNITION_CAPACITY;
	}

	@Override
	public int getAmmunitionCapacity() {
		return AMMUNITION_CAPACITY;
	}

	@Override
	public int getRateOfFire() {
		return RATE_OF_FIRE;
	}

	@Override
	public String getAmmunitionType() {
		return CyberpunkWeapon.NO_AMMUNITION_TYPE;
	}

	@Override
	public int getHitModifier() {
		return weaponAccuracy;
	}

	@Override
	public int getDamageModifier() {
		return damage.getModifier();
	}

	@Override
	public int getRangeModifier() {
		return rangeModifier;
	}

	@Override
	public String getName() {
		return weaponName;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public double getCost() {
		return cost;
	}

	@Override
	public Concealability getConcealability() {
		return concealability;
	}

	@Override
	public Availability getAvailability() {
		return availability;
	}

	@Override
	public Reliability getReliability() {
		return reliability;
	}

}
