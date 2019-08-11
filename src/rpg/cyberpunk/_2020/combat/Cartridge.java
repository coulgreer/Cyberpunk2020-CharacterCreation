package rpg.cyberpunk._2020.combat;

import java.io.Serializable;
import java.util.Objects;
import rpg.general.combat.Ammunition;
import rpg.util.Die;
import rpg.util.Measurement;
import rpg.util.Name;
import rpg.util.Probability;

/**
 * This is an implementation of <code>Ammunition</code> that uses <code>CaseMaterial</code>,
 * <code>Bullet</code>, and a String representing the weapons caliber. A cartridge uses both the
 * case material and a bullet to determine the overall cost while also using the bullet to get the
 * damage modifiers and armor penetration.
 */
public class Cartridge implements Ammunition {

  /**
   * A representation of the size and by extension the damage that an <code>Ammunition</code> can
   * use.
   */
  public static class Caliber implements Serializable {
    public static final Caliber _5MM = new Caliber( //
        new Type("5mm"), //
        new Probability( //
            new Die(1, 6)), //
        0.1);
    public static final Caliber _6MM = new Caliber( //
        new Type("6mm"), //
        new Probability( //
            new Die(1, 6), //
            1),
        0.16);
    public static final Caliber _9MM = new Caliber( //
        new Type("9mm"), //
        new Probability( //
            new Die(2, 6), //
            1),
        0.24);
    public static final Caliber _10MM = new Caliber( //
        new Type("10mm"), //
        new Probability( //
            new Die(2, 6), //
            3),
        0.32);
    public static final Caliber _11MM = new Caliber( //
        new Type("11mm"), //
        new Probability( //
            new Die(3, 6)), //
        0.4);
    public static final Caliber _12MM = new Caliber( //
        new Type("12mm"), //
        new Probability( //
            new Die(4, 6), //
            1),
        0.6);
    public static final Caliber _556 = new Caliber( //
        new Type("5.56"), //
        new Probability( //
            new Die(5, 6)), //
        0.7);
    public static final Caliber _762 = new Caliber( //
        new Type("7.62"), //
        new Probability( //
            new Die(6, 6), //
            2),
        1.3);
    public static final Caliber _20MM = new Caliber( //
        new Type("20mm"), //
        new Probability( //
            new Die(4, 10)), //
        75);

    private static final long serialVersionUID = 1L;
    private static final double minCost = 0.0;

    private Type type;
    private Probability damage;
    private double cost;

    /**
     * Constructs a new instance of a caliber with at least a cost of {@value #minCost}.
     * 
     * @param type the size of the caliber
     * @param damage the potential amount of hit points lost
     * @param cost the base monetary value
     */
    public Caliber(Type type, Probability damage, double cost) {
      setType(type);
      setDamage(damage);
      setCost(cost);
    }

    private void setType(Type type) {
      if (type == null) {
        throw new NullPointerException();
      } else {
        this.type = type;
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
     * @return the size of this Cartridge reprented as an instance of <code>Type</code>
     */
    public Type getType() {
      return type;
    }

    /**
     * @return the potential amount of hit points lost
     */
    public Probability getDamage() {
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
      if (o == this) {
        return true;
      }

      if (o == null) {
        return false;
      }

      if (!(o instanceof Caliber)) {
        return false;
      }

      Caliber caliber = (Caliber) o;
      return (getType().equals(caliber.getType())) //
          && (getDamage().equals(caliber.getDamage())) //
          && (getCost() == caliber.getCost());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getType(), getDamage(), getCost());
    }
  }

  /**
   * The part of the cartridge that determines what the material used for the cartridge is made of.
   * The default material being the caseless variant.
   */
  public static class CaseMaterial implements Serializable {
    public static final CaseMaterial CASELESS = new CaseMaterial( //
        new Name("Caseless"), //
        "The powder is the casing of the projectile.", //
        1.0);
    public static final CaseMaterial COPPER = new CaseMaterial( //
        new Name("Copper"), //
        "A basic copper case for older weapons.", //
        2.0);

    private static final long serialVersionUID = 1L;
    private static final double minCostMultiplier = 0.0;

    private Name name;
    private String description;
    private double costMultiplier;

    /**
     * Constructs a <code>CaseMaterial</code> used to modify the cost of the cartridge as well as
     * giving data for the name and description.
     * 
     * @param name the identifier used to find the material used on a cartridge
     * @param description a blurb used to give an idea of what the case material is made of
     * @param costMultiplier the amount to multiply the base cost of a cartridge by
     */
    public CaseMaterial(Name name, String description, double costMultiplier) {
      setName(name);
      setDescription(description);
      setCostMultiplier(costMultiplier);
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

    private void setCostMultiplier(double costMultiplier) {
      if (costMultiplier < minCostMultiplier) {
        throw new IllegalArgumentException(
            "cost multiplier = " + costMultiplier + "; min = " + minCostMultiplier);
      } else {
        this.costMultiplier = costMultiplier;
      }
    }

    /**
     * @return the identifier of this case material
     */
    public String getName() {
      return name.getValue();
    }

    /**
     * @return the blurb representing what the case material is made of
     */
    public String getDescription() {
      return description;
    }

    /**
     * @return the value to multiply the base cost by
     */
    public double getCostMultiplier() {
      return costMultiplier;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }

      if (o == null) {
        return false;
      }

      if (!(o instanceof CaseMaterial)) {
        return false;
      }

      CaseMaterial material = (CaseMaterial) o;
      return (getName().equals(material.getName())) //
          && (getDescription().equals(material.getDescription())) //
          && (getCostMultiplier() == material.getCostMultiplier());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getName(), getDescription(), getCostMultiplier());
    }
  }

  /**
   * The part of the cartridge that dictates the damage modifier and the armor penetration. Also,
   * modifies the base cost of a cartridge.
   */
  public static class Bullet implements Serializable {
    public static final Bullet SOFT_POINT = new Bullet( //
        new Name("Soft Point"), //
        "These are the standard bullets that all guns fire.", //
        1.0);
    public static final Bullet ARMOR_PIERCING = new Bullet( //
        new Name("Armor Piercing"), //
        "AP bullets have a steel jacket or core meant to penetrate various forms of armor. The"
            + " effects are armor SP x1/2 and penetrating damage x1/2 as well. This is because"
            + " such bullets have little or no expansion, and therefore reduce true damage.",
        3.0);
    public static final Bullet HOLLOW_POINT = new Bullet( //
        new Name("Hollow Point"), //
        "Special hollow-nosed ammo made of soft, quickly mushrooming lead. When these rounds hit"
            + " armor, the lead flattens bluntly and does mostly bruising damage. However, when"
            + " these rounds hit flesh, the lead squashes out to cause a massive wound cavity. In"
            + " effect, these rounds treat all armor as having 2x normal SP, but damage that"
            + " penetrates is x1.5.",
        1.125);

    private static final long serialVersionUID = 1L;
    private static final double minCostMultiplier = 0.0;

    private Name name;
    private String description;
    private double costMultiplier;

    /**
     * Constructs a <code>Bullet</code> used to modify the cost of the cartridge as well as give
     * part of the name and the basic description related to damage modification.
     * 
     * @param name the identifier used to find the bullet used in a cartridge
     * @param description a blurb used to give an idea of what the bullet does when damaging
     * @param costMultiplier the amount to multiply the base cost of a cartridge by
     */
    public Bullet(Name name, String description, double costMultiplier) {
      setName(name);
      setDescription(description);
      setCostMultiplier(costMultiplier);
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

    private void setCostMultiplier(double costMultiplier) {
      if (costMultiplier < minCostMultiplier) {
        throw new IllegalArgumentException(
            "cost multiplier = " + costMultiplier + "; min = " + minCostMultiplier);
      } else {
        this.costMultiplier = costMultiplier;
      }
    }

    /**
     * @return the identifier of the bullet
     */
    public String getName() {
      return name.getValue();
    }

    /**
     * @return a blurb giving the multipliers used in damage manipulation
     */
    public String getDescription() {
      return description;
    }

    /**
     * @return the value to multiply the base cost of a <code>Cartridge</code> by
     */
    public double getCostMultiplier() {
      return costMultiplier;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }

      if (o == null) {
        return false;
      }

      if (!(o instanceof Bullet)) {
        return false;
      }

      Bullet bullet = (Bullet) o;
      return (getName().equals(bullet.getName())) //
          && (getDescription().equals(bullet.getDescription())) //
          && (getCostMultiplier() == bullet.getCostMultiplier());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getName(), getDescription(), getCostMultiplier());
    }
  }

  private static final long serialVersionUID = 1L;

  private Caliber caliber;
  private Bullet bullet;
  private CaseMaterial caseMaterial;
  private Measurement weight;

  /**
   * Constructs a Cartridge with a given caliber used by <code>CyberpunkWeapon</code>s to determine
   * if they are compatible. Also, uses <code>Bullet</code> and <code>CaseMaterial</code> to
   * determine compatibility and cost.
   * 
   * @param caliber the identifier used to check if this cartridge will fit inside a
   *        <code>CyberpunkWeapon</code> that expects this caliber of cartridge
   * @param bullet the delegate used to get the damage modifiers and multiply the cost
   * @param caseMaterial a modifier to cost of this cartridge
   * @param weight the heaviness of a cartridge
   * @throws IllegalArgumentException if weight is less than {@value #minWeight}
   */
  public Cartridge( //
      Caliber caliber, Bullet bullet, CaseMaterial caseMaterial, //
      Measurement weight) {

    setCaliber(caliber);
    setBullet(bullet);
    setCaseMaterial(caseMaterial);
    setWeight(weight);
  }

  private void setCaliber(Caliber caliber) {
    if (caliber == null) {
      throw new NullPointerException();
    } else {
      this.caliber = caliber;
    }
  }

  private void setBullet(Bullet bullet) {
    if (bullet == null) {
      throw new NullPointerException();
    } else {
      this.bullet = bullet;
    }
  }

  private void setCaseMaterial(CaseMaterial caseMaterial) {
    if (caseMaterial == null) {
      throw new NullPointerException();
    } else {
      this.caseMaterial = caseMaterial;
    }
  }

  private void setWeight(Measurement weight) {
    if (weight == null) {
      throw new NullPointerException();
    } else {
      this.weight = weight;
    }
  }

  @Override
  public String getName() {
    return caseMaterial.getName() + " " + caliber.getType().getName() + " " + bullet.getName();
  }

  @Override
  public String getDescription() {
    return "A " + caliber.getType() + " cartridge with a " + bullet.getName() + " bullet and a "
        + caseMaterial.getName() + "case./n/n   " //
        + "Bullet: " + bullet.getDescription() + "/n   " //
        + "Case Mat.: " + caseMaterial.getDescription();
  }

  @Override
  public double getCost() {
    return (caliber.getCost() * bullet.getCostMultiplier() * caseMaterial.getCostMultiplier());
  }

  @Override
  public Measurement getWeight() {
    return weight;
  }

  @Override
  public Type getType() {
    return caliber.getType();
  }

  @Override
  public Probability getDamage() {
    return caliber.getDamage();
  }

  protected Caliber getCaliber() {
    return caliber;
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
    return (getCaliber().equals(cartridge.getCaliber())) //
        && (getBullet().equals(cartridge.getBullet())) //
        && (getCaseMaterial().equals(cartridge.getCaseMaterial()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(getType(), getBullet(), getCaseMaterial());
  }
}
