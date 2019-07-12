package rpg.util;

import java.io.Serializable;

/**
 * An identifier used when referencing an object.
 */
public class Name implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final int minNameLength = 1;

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
    } else if (name.length() < minNameLength) {
      throw new IllegalArgumentException("length = " + name.length() + "; min = " + minNameLength);
    } else {
      this.name = name;
    }
  }

  public String getValue() {
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

}
