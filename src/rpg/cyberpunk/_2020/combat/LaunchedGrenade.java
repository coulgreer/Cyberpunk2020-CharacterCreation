package rpg.cyberpunk._2020.combat;

import java.util.Objects;
import rpg.general.combat.Ammunition;
import rpg.util.Measurement;
import rpg.util.Probability;

/**
 * An ammunition that uses a payload in order to get derived stats such as damage and description.
 */
public class LaunchedGrenade implements Ammunition {
  private static final long serialVersionUID = 1L;

  public static final Type _40MM = new Type("40mm");

  private Type type;
  private Payload load;
  private Measurement weight;

  /**
   * Constructs a basic grenade for use in launcher type weapons.
   * 
   * @param type the caliber used to check if this ammunition is compatible with a weapon
   * @param load the contents inside this ammunition to give the damage and effects
   * @param cost the amount of money needed to obtain this ammunition
   * @param weight the heaviness of this ammunition
   */
  public LaunchedGrenade(Type type, Payload load, Measurement weight) {
    setType(type);
    setPayLoad(load);
    setWeight(weight);
  }

  private void setType(Type type) {
    if (type == null) {
      throw new NullPointerException();
    } else {
      this.type = type;
    }
  }

  private void setPayLoad(Payload load) {
    if (load == null) {
      throw new NullPointerException();
    } else {
      this.load = load;
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
    return type.getName() + " " + load.getName().getValue() + " grenade";
  }

  @Override
  public String getDescription() {
    return load.getEffects();
  }

  @Override
  public Measurement getWeight() {
    return weight;
  }

  @Override
  public double getCost() {
    return load.getCost();
  }

  @Override
  public Type getType() {
    return type;
  }

  @Override
  public Probability getDamage() {
    return load.getDamge();
  }

  protected Payload getPayload() {
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

    if (!(o instanceof LaunchedGrenade)) {
      return false;
    }

    LaunchedGrenade grenade = (LaunchedGrenade) o;
    return getPayload().equals(grenade.getPayload()) //
        && getType().equals(grenade.getType());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getPayload(), getType());
  }

}
