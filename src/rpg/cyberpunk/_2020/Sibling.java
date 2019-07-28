package rpg.cyberpunk._2020;

import org.apache.commons.text.WordUtils;
import rpg.Gender;

/**
 * A Value Object that represents a sibling to the player. Purely used to transfer data to the UI.
 */
public final class Sibling {

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
  private Gender gender;
  private RelativeAge age;
  private String relationship;

  /**
   * Constructs an instance of Sibling that holds the data for name, sex, age, and relationship.
   * 
   * @param name the name of the Sibling
   * @param gender the data representing the gender of the Sibling
   * @param age the age relative to the Player
   * @param relationship the representation of the relationship related towards the Player
   */
  public Sibling(String name, Gender gender, RelativeAge age, String relationship) {
    setName(name);
    setGender(gender);
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

  private void setGender(Gender gender) {
    if (gender == null) {
      throw new NullPointerException();
    } else {
      this.gender = gender;
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
   * @return the gender of this Sibling represented as a String
   */
  public Gender getGender() {
    return gender;
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
