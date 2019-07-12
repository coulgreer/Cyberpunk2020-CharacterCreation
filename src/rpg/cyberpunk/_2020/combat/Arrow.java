package rpg.cyberpunk._2020.combat;

import java.io.Serializable;
import java.util.Objects;
import rpg.general.combat.Ammunition;
import rpg.util.Name;
import rpg.util.NullProbability;
import rpg.util.Probability;

/**
 * This is an implementation of <code>Ammunition</code> that uses a <code>Tip</code> in order to get
 * the description of this ammunition. The description in this case displays the special effects
 * such as armor penetration and damage multiplication.
 */
public class Arrow implements Ammunition {

  private static final long serialVersionUID = 1L;

  /**
   * The default weight of a single arrow.
   */
  public static final double WEIGHT = 0.05;

  /**
   * The part of an <code>Arrow</code> that determines the cost, description, and modifies the
   * arrow's name.
   */
  public static class Tip implements Serializable {
    public static final Tip TARGET = new Tip( //
        new Name("Target"), //
        "The basic arrow. Halves all armor SP, does normal damage.", //
        0.5);
    public static final Tip BROADHEAD = new Tip( //
        new Name("Broadhead"), //
        "A head consisting of two or more razor-sharp blades. Acts as a knife for armor"
            + " penetration, pentrating damage is doubled.",
        0.3);

    private static final long serialVersionUID = 1L;
    private static final double minCost = 0.0;

    private Name name;
    private String description;
    private double cost;

    /**
     * Constructs a value that belongs to a set of known <code>Tips</code> that provides an
     * <code>Arrow</code> with its name, description, and its base cost.
     * 
     * @param name the identifier for this tip
     * @param description a blurb used to give an idea of what the given tip does
     * @param cost the amount of money that the tip is worth
     */
    public Tip(Name name, String description, double cost) {
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
     * @return the identifier of this tip
     */
    public String getName() {
      return name.getValue();
    }

    /**
     * @return the blurb giving an idea of what the tip does when used
     */
    public String getDescription() {
      return description;
    }

    /**
     * @return the flat value used when getting the cost of an arrow
     */
    public double cost() {
      return cost;
    }
  }

  public static final Tip TARGET = new Tip( //
      new Name("Target"), //
      "The basic arrow/quarrel (bows use arrows, crossbows use quarrels). Halves all armor SP," //
          + " does normal damage.",
      2.0);
  public static final Tip BROADHEAD = new Tip( //
      new Name("Broadhead"),
      "A head consisting of two or more razor-sharp blades. Acts as a knife for armor" //
          + " penetration, penetrating damage is doubled.",
      3.5);

  private Tip tip;

  /**
   * Constructs an <code>Arrow</code> instance with a <code>Tip</code> that dictates this arrow's
   * name, description, and cost.
   * 
   * @param tip one of the tips used to derived the arrow's name, description, and base cost
   */
  public Arrow(Tip tip) {
    setTip(tip);
  }

  private void setTip(Tip tip) {
    if (tip == null) {
      throw new NullPointerException();
    } else {
      this.tip = tip;
    }
  }

  @Override
  public String getName() {
    return tip.getName() + " " + getType().getName();
  }

  @Override
  public String getDescription() {
    return "An arrow with a " + tip.getName() + "./n/n   " //
        + "Tip: " + tip.getDescription();
  }

  @Override
  public double getCost() {
    return tip.cost();
  }

  @Override
  public Type getType() {
    return CyberpunkWeapon.ARROW;
  }

  @Override
  public double getWeight() {
    return WEIGHT;
  }

  /**
   * An arrow does not provide the damage for a weapon. It only augments the bows raw damage.
   * 
   * @return a pool of 0 dice
   */
  @Override
  public Probability getDamage() {
    return NullProbability.getInstance();
  }

  protected Tip getTip() {
    return tip;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (o == null) {
      return false;
    }

    if (!(o instanceof Arrow)) {
      return false;
    }

    Arrow arrow = (Arrow) o;
    return getTip().equals(arrow.getTip());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTip());
  }
}
