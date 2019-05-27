package rpg.cyberpunk._2020;

import org.apache.commons.text.WordUtils;

/**
 * A Value Object that represents a sibling to the player. Purely used to transfer data to the UI.
 */
public final class Sibling {
  /**
   * Representation of the sex of a Sibling.
   */
  public enum Sex {
    MALE, FEMALE;

    @Override
    public String toString() {
      String str = name();

      str = str.replace("_", " ");
      str = WordUtils.capitalizeFully(str);
      return str;
    }
  }

  /**
   * Representation of the relative age of the Player.
   */
  public enum RelativeAge {
    OLDER, YOUNGER, TWIN;

    @Override
    public String toString() {
      String str = name();

      str = str.replace("_", " ");
      str = WordUtils.capitalizeFully(str);
      return str;
    }
  }

  private String name;
  private Sex sex;
  private RelativeAge age;
  private String relationship;

  /**
   * Constructs an instance of Sibling that holds the data for name, sex, age, and relationship.
   * 
   * @param name the name of the Sibling
   * @param sex the data representing the sex of the Sibling
   * @param age the age relative to the Player
   * @param relationship the representation of the relationship related towards the Player
   */
  public Sibling(String name, Sex sex, RelativeAge age, String relationship) {
    setName(name);
    setSex(sex);
    setAge(age);
    setRelationship(relationship);
  }

  private void setName(String name) {
    if (name == null) {
      throw new NullPointerException();
    } else if ("".equals(name)) {
      throw new IllegalArgumentException("The name cannot be an empty String");
    } else {
      this.name = name;
    }
  }

  private void setSex(Sex sex) {
    if (sex == null) {
      throw new NullPointerException();
    } else {
      this.sex = sex;
    }
  }

  private void setAge(RelativeAge age) {
    if (age == null) {
      throw new NullPointerException();
    } else {
      this.age = age;
    }
  }

  private void setRelationship(String relationship) {
    if (relationship == null) {
      throw new NullPointerException();
    } else {
      this.relationship = relationship;
    }
  }

  /**
   * @return the name identifying this Sibling
   */
  public String getName() {
    return name;
  }

  /**
   * @return the sex of this Sibling represented as a String
   */
  public String getSex() {
    return sex.toString();
  }

  /**
   * @return the relative age to the Player represented as a String
   */
  public String getAge() {
    return age.toString();
  }

  /**
   * @return a String telling how this sibling feels towards a Player
   */
  public String getRelationship() {
    return relationship;
  }
}
