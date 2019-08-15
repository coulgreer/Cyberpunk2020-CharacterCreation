package rpg.util;

import java.io.Serializable;
import java.util.Objects;

/**
 * An identifier used when referencing an object.
 */
public class Name implements Serializable {
  public static final int MIN_NAME_LENGTH = 1;
  
  private static final long serialVersionUID = 1L;

  private String name;

  /**
   * Constructs an instance of Name that cannot be null and must be at least one character long.
   * 
   * @param name the string representation used to identify objects
   */
  public Name(String name) {
    setName(name);
  }

  private void setName(String name) {
    if (name == null) {
      throw new NullPointerException();
    } else if (name.length() < MIN_NAME_LENGTH) {
      throw new IllegalArgumentException("length = " + name.length() + "; min = " + MIN_NAME_LENGTH);
    } else {
      this.name = name;
    }
  }

  public String getValue() {
    return name;
  }
  
  @Override
  public String toString() {
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

    if (!(o instanceof Name)) {
      return false;
    }

    Name name = (Name) o;
    return getValue().equals(name.getValue());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getValue());
  }

}
