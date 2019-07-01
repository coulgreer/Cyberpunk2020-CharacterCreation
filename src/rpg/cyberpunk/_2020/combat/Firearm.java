package rpg.cyberpunk._2020.combat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.general.combat.Ammunition;
import rpg.general.combat.AmmunitionContainer;
import rpg.general.combat.WeaponAttachment;
import rpg.util.Die;
import rpg.util.NullProbability;
import rpg.util.Probability;

/**
 * A Cyberpunk weapon that uses its held ammunition to determine the damage output. Also, uses the
 * combatant wielding the weapon to determine accuracy buy not range.
 */
public class Firearm extends CyberpunkWeapon {
  private static final int minAttackCount = 1;
  private static final int minRateOfFire = 1;
  private static final int minRangeModifier = 0;
  private static final double minWeight = 0.0;
  private static final double minCost = 0.0;
  private static final long serialVersionUID = 1L;

  private String weaponName;
  private String description;
  private String weaponType;
  private String skillName;
  private int weaponAccuracy;
  private Concealability concealability;
  private Availability availability;
  private AmmunitionContainer ammunitionContainer;
  private int rateOfFire;
  private Reliability reliability;
  private int rangeModifier;
  private double cost;
  private double weight;
  private Set<String> attachmentPoints;
  private Map<String, WeaponAttachment> attachments;

  /**
   * Constructs a firearm that does not require a skillName.
   * 
   * @param weaponName the name used to identify this weapon
   * @param description a blurb used to give an idea of what this weapon is/can do
   * @param weaponType the category of weapon that this weapon belongs to
   * @param weaponAccuracy the flat modifier to accuracy based solely on the weapon
   * @param concealability the rating representing the ability to hide this weapon
   * @param availability the rating representing how easy this weapon is to obtain
   * @param ammunitionContainer the holder of the ammunition put into this weapon as well as giving
   *        the maximum capacity
   * @param rateOfFire the amount of attacks that can be made in one turn with this weapon
   * @param reliability the rating representing the probability of a weapon jamming or
   *        malfunctioning
   * @param rangeModifier the average distance that this weapon can make an attack without suffering
   *        penalties to accuracy
   * @param cost the amount of money this weapon is worth
   * @param weight the heaviness of this weapon
   * @param attachmentPoints a set of points where modifiers can be attached to this weapon
   * 
   * @see #Firearm(String, String, String, String, int, Concealability, Availability,
   *      AmmunitionContainer, int, Reliability, int, double, double, Set)
   */
  public Firearm( //
      String weaponName, String description, //
      String weaponType, //
      int weaponAccuracy, //
      Concealability concealability, Availability availability, //
      AmmunitionContainer ammunitionContainer, int rateOfFire, //
      Reliability reliability, //
      int rangeModifier, //
      double cost, double weight, //
      Set<String> attachmentPoints) {

    this(weaponName, description, weaponType, parseSkillFromWeaponType(weaponType), weaponAccuracy,
        concealability, availability, ammunitionContainer, rateOfFire, reliability, rangeModifier,
        cost, weight, attachmentPoints);
  }

  private static String parseSkillFromWeaponType(String weaponType) {
    switch (weaponType) {
      case WEAPON_TYPE_LIGHT_PISTOL:
      case WEAPON_TYPE_MEDIUM_PISTOL:
      case WEAPON_TYPE_HEAVY_PISTOL:
      case WEAPON_TYPE_VERY_HEAVY_PISTOL:
        return CyberpunkSkill.HANDGUN;
      case WEAPON_TYPE_LIGHT_SUBMACHINEGUN:
      case WEAPON_TYPE_MEDIUM_SUBMACHINEGUN:
      case WEAPON_TYPE_HEAVY_SUBMACHINEGUN:
        return CyberpunkSkill.SUBMACHINEGUN;
      case WEAPON_TYPE_RIFLE:
      case WEAPON_TYPE_SHOTGUN:
        return CyberpunkSkill.RIFLE;
      case WEAPON_TYPE_HEAVY_WEAPON:
        return CyberpunkSkill.HEAVY_WEAPONS;
      case WEAPON_TYPE_MELEE_WEAPON:
        return CyberpunkSkill.MELEE;
      default:
        return CyberpunkSkill.NONE;
    }
  }

  /**
   * Constructs a weapon that uses the given skill name to add to this weapons flat hit modifier.
   * 
   * @param weaponName the name used to identify this weapon
   * @param description a blurb used to give an idea of what this weapon is/can do
   * @param weaponType the category of weapon that this weapon belongs to
   * @param skillName the identifier used for the Cyberpunk skill associated with this weapon
   * @param weaponAccuracy the flat modifier to accuracy based solely on the weapon
   * @param concealability the rating representing the ability to hide this weapon
   * @param availability the rating representing how easy this weapon is to obtain
   * @param ammunitionContainer the holder of the ammunition put into this weapon as well as giving
   *        the maximum capacity
   * @param rateOfFire the amount of attacks that can be made in one turn with this weapon
   * @param reliability the rating representing the probability of a weapon jamming or
   *        malfunctioning
   * @param rangeModifier the average distance that this weapon can make an attack without suffering
   *        penalties to accuracy
   * @param cost the amount of money this weapon is worth
   * @param weight the heaviness of this weapon
   * @param attachmentPoints a set of points where modifiers can be attached to this weapon
   */
  public Firearm( //
      String weaponName, String description, //
      String weaponType, String skillName, //
      int weaponAccuracy, //
      Concealability concealability, Availability availability, //
      AmmunitionContainer ammunitionContainer, int rateOfFire, //
      Reliability reliability, //
      int rangeModifier, //
      double cost, double weight, //
      Set<String> attachmentPoints) {

    setWeaponName(weaponName);
    setDescription(description);
    setWeaponType(weaponType);
    setSkillName(skillName);
    this.weaponAccuracy = weaponAccuracy;
    setConcealability(concealability);
    setAvailability(availability);
    setAmmunitionContainer(ammunitionContainer);
    setRateOfFire(rateOfFire);
    setReliability(reliability);
    setRangeModifier(rangeModifier);
    setCost(cost);
    setWeight(weight);
    setAttachmentsAndAttachmentPoints(attachmentPoints);
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

  private void setSkillName(String skillName) {
    if (skillName == null) {
      throw new NullPointerException();
    } else {
      this.skillName = skillName;
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

  private void setAmmunitionContainer(AmmunitionContainer ammunitionContainer) {
    if (ammunitionContainer == null) {
      throw new NullPointerException();
    } else {
      this.ammunitionContainer = ammunitionContainer;
    }
  }

  private void setRateOfFire(int rateOfFire) {
    if (rateOfFire < minRateOfFire) {
      throw new IllegalArgumentException(
          "rate of fire = " + rateOfFire + "; min = " + minRateOfFire);
    } else {
      this.rateOfFire = rateOfFire;
    }
  }

  private void setReliability(Reliability reliability) {
    if (reliability == null) {
      throw new NullPointerException();
    } else {
      this.reliability = reliability;
    }
  }

  private void setRangeModifier(int rangeModifier) {
    if (rangeModifier < minRangeModifier) {
      throw new IllegalArgumentException(
          "range modifier = " + rangeModifier + "; min = " + minRangeModifier);
    } else {
      this.rangeModifier = rangeModifier;
    }
  }

  private void setCost(double cost) {
    if (cost < minCost) {
      throw new IllegalArgumentException("cost = " + cost + "; min = " + minCost);
    } else {
      this.cost = cost;
    }
  }

  private void setWeight(double weight) {
    if (weight < minWeight) {
      throw new IllegalArgumentException("weight = " + weight + "; min = " + minWeight);
    } else {
      this.weight = weight;
    }
  }

  private void setAttachmentsAndAttachmentPoints(Set<String> attachmentPoints) {
    if (attachmentPoints == null) {
      throw new NullPointerException();
    } else {
      this.attachmentPoints = attachmentPoints;
      this.attachments = new HashMap<String, WeaponAttachment>();
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
    String targetAttachmentPoint = attachment.getAttachmentPoint();
    if (attachmentPoints.contains(targetAttachmentPoint)) {
      return attachments.put(targetAttachmentPoint, attachment);
    } else {
      return attachment;
    }
  }

  @Override
  public Iterator<WeaponAttachment> getAttachments() {
    return attachments.values().iterator();
  }

  /**
   * {@inheritDoc}
   * 
   * Also, does not allow for <code>attackCount</code> to be greater than the amount of ammunition
   * available.
   * 
   * @throws IllegalArgumentException if attackCount is less than {@value #minAttackCount}
   */
  @Override
  public boolean attack(int attackCount) {
    if (attackCount < minAttackCount) {
      throw new IllegalArgumentException(
          "attack count = " + attackCount + "; min = " + minAttackCount);
    }

    attackCount = attackCount > getAmmunitionCount() ? getAmmunitionCount() : attackCount;
    ammunitionContainer.withdrawAmmunition(attackCount);

    return attackCount > AmmunitionContainer.EMPTY
        || getAmmunitionCapacity() == AmmunitionContainer.EMPTY;
  }

  @Override
  public List<Ammunition> reload(List<Ammunition> ammunition) {
    return ammunitionContainer.depositAmmunition(ammunition);
  }

  @Override
  public int getAmmunitionCount() {
    return ammunitionContainer.getAmmunitionCount();
  }

  @Override
  public int getAmmunitionCapacity() {
    return ammunitionContainer.getAmmunitionCapacity();
  }

  @Override
  public int getRateOfFire() {
    return rateOfFire;
  }

  @Override
  public String getAmmunitionType() {
    return ammunitionContainer.getAmmunitionType();
  }

  @Override
  public int getAttackModifier() {
    return weaponAccuracy;
  }

  @Override
  public Die getDamageDice() {
    return getDamage().getDice();
  }

  private Probability getDamage() {
    Ammunition ammunition = ammunitionContainer.getAmmunition();
    Probability damage;

    if (ammunition == null) {
      damage = NullProbability.getInstance();
    } else {
      damage = ammunition.getDamage();
    }

    return damage;
  }

  @Override
  public int getDamageModifier() {
    return getDamage().getModifier();
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
