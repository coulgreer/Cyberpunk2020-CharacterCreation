package rpg.cyberpunk._2020.combat;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.general.combat.Ammunition;
import rpg.general.combat.Ammunition.Type;
import rpg.general.combat.AmmunitionContainer;
import rpg.general.combat.EmptyAmmunitionContainer;
import rpg.general.combat.Weapon;
import rpg.general.combat.WeaponAttachment;
import rpg.util.Die;
import rpg.util.Measurement;
import rpg.util.Probability;

/**
 * A Cyberpunk weapon that determines its range based on the weight of the weapon and the combatant
 * wielding it. Uses the athletics skill to determine the bonus for accuracy provided by the
 * wielding combatant.
 */
public class ThrownWeapon extends CyberpunkWeapon {

  /**
   * The minimum weight amount allowed without suffering a range penalty.
   */
  public static final Measurement WEIGHT_THRESHOLD = new Measurement( //
      Measurement.Type.MASS, //
      1.0, //
      Measurement.Unit.KILOGRAM);

  private static final int AMMUNITION_CAPACITY = 1;
  private static final int RATE_OF_FIRE = 1;
  private static final double MIN_COST = 0.0;
  private static final long serialVersionUID = 1L;

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
  private Measurement rangeModifier;
  private double cost;
  private Measurement weight;

  /**
   * Constructs a thrown weapon with a payload. The payload is used to get the damage and
   * description for this weapon. This constructor is akin to a firearm and a thrown weapon.
   * 
   * @param weaponName the name used to identify this weapon
   * @param weaponType the category of weapon that this weapon belongs to
   * @param weaponAccuracy the flat modifier to accuracy based solely on the weapon
   * @param concealability the rating representing the ability to hide this weapon
   * @param availability the rating representing how easy this weapon is to obtain
   * @param load the payload held inside the thrown weapon to derive damage and description
   * @param reliability the rating representing the probability of a weapon jamming or
   *        malfunctioning
   * @param cost the amount of money this weapon is worth
   * @param weight the heaviness of this weapon
   */
  public ThrownWeapon(String weaponName, String weaponType, int weaponAccuracy,
      Concealability concealability, Availability availability, Payload load,
      Reliability reliability, double cost, Measurement weight) {

    this(weaponName, load.getEffects(), weaponType, weaponAccuracy, concealability, availability,
        load.getDamge(), reliability, cost, weight);
  }

  /**
   * Constructs a thrown weapon that does not allow null values or non-negative values excluding
   * weaponAccuracy.
   * 
   * @param weaponName the name used to identify this weapon
   * @param description a blurb used to give an idea of what this weapon is/can do
   * @param weaponType the category of weapon that this weapon belongs to
   * @param weaponAccuracy the flat modifier to accuracy based solely on the weapon
   * @param concealability the rating representing the ability to hide this weapon
   * @param availability the rating representing how easy this weapon is to obtain
   * @param damage the probability of dealing damage represented as die
   * @param reliability the rating representing the probability of a weapon jamming or
   *        malfunctioning
   * @param cost the amount of money this weapon is worth
   * @param weight the heaviness of this weapon
   */
  public ThrownWeapon(String weaponName, String description, String weaponType, int weaponAccuracy,
      Concealability concealability, Availability availability, Probability damage,
      Reliability reliability, double cost, Measurement weight) {

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
  }

  private void setWeaponName(String weaponName) {
    if (weaponName == null) {
      throw new NullPointerException();
    } else {
      this.weaponName = weaponName;
    }
  }

  private void setDescription(String description) {
    if (description == null) {
      throw new NullPointerException();
    } else {
      this.description = description;
    }
  }

  private void setWeaponType(String weaponType) {
    if (weaponType == null) {
      throw new NullPointerException();
    } else {
      this.weaponType = weaponType;
    }
  }

  private void setConcealability(Concealability concealability) {
    if (concealability == null) {
      throw new NullPointerException();
    } else {
      this.concealability = concealability;
    }
  }

  private void setAvailability(Availability availability) {
    if (availability == null) {
      throw new NullPointerException();
    } else {
      this.availability = availability;
    }
  }

  private void setDamage(Probability damage) {
    if (damage == null) {
      throw new NullPointerException();
    } else {
      this.damage = damage;
    }
  }

  private void setReliability(Reliability reliability) {
    if (reliability == null) {
      throw new NullPointerException();
    } else {
      this.reliability = reliability;
    }
  }

  private void setWeight(Measurement weight) {
    if (weight == null) {
      throw new NullPointerException();
    } else {
      this.weight = weight;
      this.rangeModifier = calculateRange(weight);
    }
  }

  private Measurement calculateRange(Measurement weight) {
    int modifier = 0;

    if (weight.compareTo(WEIGHT_THRESHOLD) == -1) {
      modifier = ((int) Measurement.subtract(weight, WEIGHT_THRESHOLD).getAmount()) * (-10);
    }

    return new Measurement( //
        Measurement.Type.LENGTH, //
        modifier, //
        Measurement.Unit.METER);
  }

  private void setCost(double cost) {
    if (cost < MIN_COST) {
      throw new IllegalArgumentException("cost = " + cost + "; min = " + MIN_COST);
    } else {
      this.cost = cost;
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
  public WeaponAttachment addAttachment(WeaponAttachment attachment) {
    return attachment;
  }

  @Override
  public Iterator<WeaponAttachment> getAttachments() {
    return Collections.emptyIterator();
  }

  @Override
  public boolean attack(int numberOfShots) {
    return true;
  }

  @Override
  public List<Ammunition> reload(List<Ammunition> ammunition) {
    return ammunitionContainer.depositAmmunition(ammunition);
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
  public Type getAmmunitionType() {
    return Weapon.NO_AMMUNITION_TYPE;
  }

  @Override
  public int getAttackModifier() {
    return weaponAccuracy;
  }

  @Override
  public Die getDamageDice() {
    return damage.getDice();
  }

  @Override
  public int getDamageModifier() {
    return damage.getModifier();
  }

  @Override
  public Measurement getRangeModifier() {
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
  public Measurement getWeight() {
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
