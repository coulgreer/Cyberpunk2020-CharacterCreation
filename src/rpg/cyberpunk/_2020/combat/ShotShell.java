package rpg.cyberpunk._2020.combat;

import java.io.Serializable;
import java.util.Objects;
import rpg.general.combat.Ammunition;
import rpg.util.Measurement;
import rpg.util.Name;
import rpg.util.Probability;

/**
 * This is an implementation of <code>Ammunition</code> that uses <code>Load</code> in order to
 * determine the cost multiplier. Also, partially delegates to Load in order to the ShotShell's name
 * as well as its description.
 */
public class ShotShell implements Ammunition {

  /**
   * The default weight of a single shot shell.
   */
  public static final Measurement WEIGHT = new Measurement( //
      Measurement.Type.MASS, //
      0.05, //
      Measurement.Unit.KILOGRAM);

  /**
   * The part of the ShotShell that provides the base cost of this ammunition and gives a blurb of
   * how the ammunition modifies the behavior of a <code>CyberpunkWeapon</code>.
   */
  public static class Load implements Serializable {
    public static final Load SHOT = new Load( //
        new Name("Shot"), //
        "Small balls or pellets, often made of lead.", //
        1.25);
    public static final Load APFSDS = new Load( //
        new Name("Armor-Piercing Fin-Stabilized Descarding Sabot"),
        "A sub-caliber round, firing a 5.5mm projectile from a 12 or 10ga shotgun. This"
            + " sub-munition moves at accelerated velocities and easily punches through armor. It"
            + " costs 10eb per round, and does 6D6 AP damage. It has an effective range of 25"
            + " meters.",
        10.0);
    public static final Load FLARE = new Load( //
        new Name("Flare"), //
        "Lights up an area of 30m diam. Causes 2D6+2 and 1D6/2 per round fire damage if fired as a"
            + " regular shell.",
        1.08);
    public static final Load FLASH_BANG = new Load( //
        new Name("Flash-Bang"), //
        "A small time-delay charge of blasting explosive and flash poweder, the flash-bang round"
            + " acts as a small flash-bang grenade. All people within two meters of the vlast (5"
            + " meters if indoors) must make a Stun/Shock Save and a Difficulty +20 REF test to"
            + " avoid being stunned for one turn and being blinded for two turns. Anti-dazzle"
            + " protection negates the flash effect and makes the REF test unnecessary. The"
            + " flash-bang round has a maximum range of 25 meters; if it has not impacted something"
            + " solid (exploding on impact) by that time, it explodes automatically.",
        1.28);
    public static final Load FLECHETTES = new Load( //
        new Name("Flechettes"),
        "Works like a regular shotgun shotshell, erxcept that it fires needles instead of shot,"
            + " doing 4D6 AP damage (armor and damage is x1/4)",
        8.0);
    public static final Load SLUG = new Load( //
        new Name("Slug"), //
        "A solid shotgun round, using one large, rigid-core bullet instead of shot. The slug does"
            + " 3D6+1 (20-gauge), 4D6+2 (12-gauge), 5D6+3 (10-gauge) damage at all ranges, and has"
            + " no area effect. The round is AP; any damage that penetrates hard armor is not"
            + " halved.",
        1.25);

    private static final long serialVersionUID = 1L;
    private static final double minCost = 0.0;

    private Name name;
    private String description;
    private double cost;

    /**
     * Constructs the contents used inside a shotshell to determine the cost multiplier and the
     * description.
     * 
     * @param name the identifier for this load
     * @param description the blurb used to give an idea of what contents are used in the load
     * @param cost the monetary value
     */
    public Load(Name name, String description, double cost) {
      setName(name);
      setDescription(description);
      setCost(cost);
    }

    private void setName(Name name) {
      if (name == null) {
        throw new NullPointerException();
      } else {
        this.name = name;
      }
    }

    private void setDescription(String description) {
      if (description == null) {
        throw new NullPointerException();
      } else {
        this.description = description;
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
     * @return the identifier of this load
     */
    public Name getName() {
      return name;
    }

    /**
     * @return the blurb giving an idea of what the load does when used
     */
    public String getDescription() {
      return description;
    }

    /**
     * @return the modifier used on a flat cost
     */
    public double getCost() {
      return cost;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }

      if (o == null) {
        return false;
      }

      if (!(o instanceof Load)) {
        return false;
      }

      Load load = (Load) o;
      return getName().equals(load.getName()) //
          && getDescription().equals(load.getDescription()) //
          && getCost() == load.getCost();
    }

    @Override
    public int hashCode() {
      return Objects.hash(getName(), getDescription(), getCost());
    }

  }

  public static final Type _10_GAUGE = new Type("10ga");
  public static final Type _12_GAUGE = new Type("12ga");
  public static final Type _20_GAUGE = new Type("20ga");

  private static final long serialVersionUID = 1L;

  private Type gauge;
  private Load load;
  private Probability damage;

  /**
   * Constructs a ShotShell composed of a gauge and a load used to derive the description, name, and
   * cost.
   * 
   * @param gauge the size of the shotshell that is used when seeing if shell is compatible with
   *        <code>CyberpunkWeapon</code>
   * @param load the delegate used to modify the base stats
   * @param damage the potential hit points lost when hit by this ammunition
   */
  public ShotShell(Type gauge, Load load, Probability damage) {
    setGauge(gauge);
    setLoad(load);
    setDamage(damage);
  }

  private void setGauge(Type gauge) {
    if (gauge == null) {
      throw new NullPointerException();
    } else {
      this.gauge = gauge;
    }
  }

  private void setLoad(Load load) {
    if (load == null) {
      throw new NullPointerException();
    } else {
      this.load = load;
    }
  }

  private void setDamage(Probability damage) {
    if (damage == null) {
      throw new NullPointerException();
    } else {
      this.damage = damage;
    }
  }

  @Override
  public String getName() {
    return gauge.getName() + " " + load.getName().getValue();
  }

  @Override
  public String getDescription() {
    return "A " + gauge.getName() + " shotgunshell loaded with " + load.getName();
  }

  @Override
  public Measurement getWeight() {
    return WEIGHT;
  }

  @Override
  public double getCost() {
    return load.getCost();
  }

  @Override
  public Type getType() {
    return gauge;
  }

  @Override
  public Probability getDamage() {
    return damage;
  }

  protected Type getGauge() {
    return gauge;
  }

  protected Load getLoad() {
    return load;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (o == null) {
      return false;
    }

    if (!(o instanceof ShotShell)) {
      return false;
    }

    ShotShell shell = (ShotShell) o;
    return getGauge().equals(shell.getGauge()) //
        && getLoad().equals(shell.getLoad()) //
        && getDamage().equals(shell.getDamage());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getGauge(), getLoad(), getDamage());
  }

}
