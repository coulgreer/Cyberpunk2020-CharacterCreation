package rpg.general.combat;

import java.io.Serializable;
import java.util.Objects;
import rpg.general.commerce.Item;
import rpg.util.Probability;

/**
 * An object representing ammunition that is put inside of a ranged weapon in order for it to be
 * effectively used.
 */
public interface Ammunition extends Item {

  /**
   * A representation of the category name of ammunition. Used to represent compatibility between
   * ammunition and weapons.
   */
  public static class Type implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int minNameLength = 1;

    private String name;

    /**
     * Constructs an instance of Type that does not allow the name to be null and has to be at least
     * one character long.
     * 
     * @param name the identifier of the type
     */
    public Type(String name) {
      setName(name);
    }

    private void setName(String name) {
      if (name == null) {
        throw new NullPointerException();
      } else if (name.length() < minNameLength) {
        throw new IllegalArgumentException(
            "length = " + name.length() + "; min = " + minNameLength);
      } else {
        this.name = name;
      }
    }

    /**
     * @return the identifier
     */
    public String getName() {
      return name;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }

      if (o == null) {
        return false;
      }

      if (!(o instanceof Type)) {
        return false;
      }

      Type type = (Type) o;
      return getName().equals(type.getName());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getName());
    }
  }

  /**
   * @return the name of the overarching category
   */
  public Type getType();

  /**
   * @return the damage potential represented as a die and a flat modifier
   */
  public Probability getDamage();
}
