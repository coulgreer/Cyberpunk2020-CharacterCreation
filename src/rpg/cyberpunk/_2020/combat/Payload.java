package rpg.cyberpunk._2020.combat;

import java.io.Serializable;
import rpg.util.Die;
import rpg.util.Name;
import rpg.util.NullProbability;
import rpg.util.Probability;

/**
 * A class that represents a component of ammunition that damage/ bonus effects are derived from.
 */
public class Payload implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final double minCost = 0.0;

  public static final Payload HIGH_EXPLOSIVES = new Payload( //
      new Name("High Explosives"),
      "This round does 7D6 damage in a 5 meter radius. It will not detonate until it has traveled" //
          + " 10 meters from the weapon after firing.",
      new Probability(new Die(7, 6), 0), //
      30.0);
  public static final Payload WHITE_PHOSPHOROUS = new Payload( //
      new Name("White Phosphorous"),
      "This nasty round throws a cloud of burning white phosphous. Anyone within 10 meters of the" //
          + " explosion takes 4D6 damage for three turns.",
      new Probability(new Die(4, 6), 0), //
      30.0);
  public static final Payload FLASHBANG = new Payload( //
      new Name("Flashbang"),
      "All people within 5 meters of the blast (15 meters if indoors) must make a Stun Save at -2"
          + " to avoid being stunned and deafened for 4 turns (40 sec.) and a Difficulty 20+ REF"
          + " test to avoid being blinded for 2 turns (20sec.) Anti-dazzle protection negates the"
          + " flash effect and make the REF test unnecessary. Other versions have little"
          + " discernable flash, but concussive effect (no blinding effect; -5 to Stun Save). Soft"
          + " armor gives no protection vs. the effects.",
      NullProbability.getInstance(), //
      30.0);
  public static final Payload NAUSEA_GAS = new Payload( //
      new Name("Nausea Gas"),
      "All targets within the pattern must make a 25+ BOD check to avoid disorientation, headaches"
          + " and nausea.\n\tIf successful: Debilitation (-4 to all actions for 1D6 rounds)\n\tIf"
          + " failed by 1-3 points: Incapacitation (REF and MA reduced to 1 for 1D6 rounds)\n\tIf"
          + " failed by 4+ points: Serious Incapacitation (unconscious for 1D6 minutes)",
      NullProbability.getInstance(), //
      30.0);
  public static final Payload TEARGAS = new Payload( //
      new Name("Teargas"),
      "The effects of tear gas are unpleasant at best, inhalation of tear gas will reduce INT,"
          + " REF, COOL, and MA by half unless a difficult(20) BOD roll is made each round of"
          + " exposure. Anyone without optics will also receive a -5 to awareness from tearing"
          + " that will last for D6 minutes after exposure.",
      NullProbability.getInstance(), //
      30.0);
  public static final Payload SLEEP_DRUGS = new Payload( //
      new Name("Sleep Drugs"),
      "On a failed save, puts target asleep for 1D10 mins. On a successful save, the target is"
          + " made drowsy with a -2 to all stats. Any target struck must make a Very Difficult BOD"
          + " check (plus Resist Drugs skill) to avoid it's effects.",
      NullProbability.getInstance(), //
      30.0);
  public static final Payload BIOTOXIN_I_GAS = new Payload( //
      new Name("Biotoxin I Gas"), "4D6 damage as nerve endings flare up and disrupt", //
      new Probability(new Die(4, 6), 0), //
      30.0);
  public static Payload BIOTOXIN_II_GAS = new Payload( //
      new Name("Biotoxin II Gas"),
      "8D6 damage as nerve and muscle clusters tear themselves up and disrupt",
      new Probability(new Die(8, 6), 0), //
      30.0);
  public static Payload NERVE_GAS = new Payload( //
      new Name("Nerve Gas"), "8D10 damage internal bleeding, clotting, and organ disintegration.",
      new Probability(new Die(8, 10), 0), //
      30.0);

  private Name name;
  private String effects;
  private Probability damage;
  private double cost;

  /**
   * Constructs contents that can be inserted into a container to get damage and effects created by
   * payloads contents.
   * 
   * @param name the identifier of a payload
   * @param effects the status ailments afflicted to the target
   * @param damage the probability of the amount of HP lost when hit by this payload
   * @param cost the monetary value
   */
  Payload(Name name, String effects, Probability damage, double cost) {
    setName(name);
    setEffects(effects);
    setDamage(damage);
    setCost(cost);
  }

  private void setName(Name name) {
    if (name == null) {
      throw new NullPointerException();
    } else {
      this.name = name;
    }
  }

  private void setEffects(String effects) {
    if (effects == null) {
      throw new NullPointerException();
    } else {
      this.effects = effects;
    }
  }

  private void setDamage(Probability damage) {
    if (damage == null) {
      throw new NullPointerException();
    } else {
      this.damage = damage;
    }
  }

  private void setCost(double cost) {
    if (cost < minCost) {
      throw new IllegalArgumentException("cost = " + cost + "; min = " + minCost);
    } else {
      this.cost = cost;
    }
  }

  /**
   * @return the identifier of this payload
   */
  public Name getName() {
    return name;
  }

  /**
   * @return the status ailments caused when hit
   */
  public String getEffects() {
    return effects;
  }

  /**
   * @return the probable damage caused
   */
  public Probability getDamge() {
    return damage;
  }

  /**
   * @return the monetary value
   */
  public double getCost() {
    return cost;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }

    if (o == this) {
      return true;
    }

    if (!(o instanceof Payload)) {
      return false;
    }

    Payload load = (Payload) o;
    return getName().equals(load.getName()) //
        && getEffects().equals(load.getEffects()) //
        && getDamge().equals(load.getDamge()) //
        && getCost() == load.getCost();
  }

}
