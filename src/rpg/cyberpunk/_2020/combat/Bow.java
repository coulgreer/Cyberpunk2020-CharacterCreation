package rpg.cyberpunk._2020.combat;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.general.combat.Ammunition;
import rpg.general.combat.AmmunitionContainer;
import rpg.general.combat.WeaponAttachment;
import rpg.util.Die;
import rpg.util.Probability;

/**
 * An instance of <code>CyberpunkWeapon</code> that provides its own flat damage probability, and
 * uses <code>Ammunition</code> of the type {@link Arrow#AMMUNITION_TYPE_ARROW Arrow} in order to
 * deal the flat damage to a target.
 */
public class Bow extends CyberpunkWeapon {
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
  private Probability damage;
  private AmmunitionContainer ammunitionContainer;
  private int rateOfFire;
  private Reliability reliability;
  private int rangeModifier;
  private double cost;
  private double weight;
  private Set<String> attachmentPoints;
  private Map<String, WeaponAttachment> attachmentsByAttachmentPoint;

  /**
   * Constructs a default Bow that uses the {@link CyberpunkSkill#ARCHERY archery} skill and has a
   * weapon type of {@link CyberpunkWeapon#WEAPON_TYPE_BOW Bow}.
   * 
   * @param weaponName the identifier of this weapon
   * @param description a blurb used to help give an idea of the given weapon
   * @param weaponAccuracy the flat modifier for the hit score
   * @param concealability the rating score for how easy this weapon can be hidden
   * @param availability the rating score for how easy this weapon can be found on the market
   * @param damage the probability of damage represented in die notation
   * @param ammunitionContainer the holder of all ammunition
   * @param rateOfFire the amount of attacks that can be made per turn
   * @param reliability the rating score for how likely this weapon is to fail in combat
   * @param rangeModifier the flat modifier for how far this weapon can make an attack
   * @param cost the base value used when transacting
   * @param weight the heaviness value
   * @param attachmentPoints the slots that allow for weapon modifiers to be attached to
   */
  public Bow( //
      String weaponName, String description, //
      int weaponAccuracy, //
      Concealability concealability, Availability availability, //
      Probability damage, AmmunitionContainer ammunitionContainer, int rateOfFire, //
      Reliability reliability, int rangeModifier, //
      double cost, double weight, //
      Set<String> attachmentPoints) {

    setWeaponName(weaponName);
    setDescription(description);
    this.weaponType = CyberpunkWeapon.WEAPON_TYPE_BOW;
    this.skillName = CyberpunkSkill.ARCHERY;
    this.weaponAccuracy = weaponAccuracy;
    setConcealability(concealability);
    setAvailability(availability);
    setDamage(damage);
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

  private void setDamage(Probability damage) {
    if (damage == null) {
      throw new NullPointerException();
    } else {
      this.damage = damage;
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
      this.attachmentsByAttachmentPoint =
          attachmentPoints.stream().collect(Collectors.toMap(str -> str, x -> null));
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
  public Iterator<WeaponAttachment> getAttachments() {
    return attachmentsByAttachmentPoint.values().iterator();
  }

  @Override
  public WeaponAttachment addAttachment(WeaponAttachment attachment) {
    String targetAttachmentPoint = attachment.getAttachmentPoint();
    if (attachmentPoints.contains(targetAttachmentPoint)) {
      return attachmentsByAttachmentPoint.put(targetAttachmentPoint, attachment);
    } else {
      return attachment;
    }
  }

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
    return damage.getDice();
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
