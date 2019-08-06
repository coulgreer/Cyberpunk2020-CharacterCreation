package rpg.cyberpunk._2020;

import rpg.Gender;

public class Characteristics {
  public static final int MIN_AGE = 16;

  // Physical Characteristics
  private String eyes;
  private String height;
  private String hair;
  private String skinTone;
  private String weight;
  private String markings;

  // Basic Characteristics
  private Age age;
  private Gender gender;

  public Characteristics() {
    age = new Age(MIN_AGE);
    gender = Gender.OTHER;

    eyes = "";
    height = "";
    hair = "";
    skinTone = "";
    weight = "";
    markings = "";
  }

  public Age getAge() {
    return age;
  }

  public void setAge(Age age) {
    if (age.toInt() < MIN_AGE) {
      throw new IllegalArgumentException("age = " + age + "; min = " + MIN_AGE);
    } else {
      this.age = age;
    }
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    if (gender == null) {
      throw new NullPointerException();
    } else {
      this.gender = gender;
    }
  }

  public String getEyes() {
    return eyes;
  }

  public void setEyes(String eyes) {
    if (eyes == null) {
      throw new NullPointerException();
    } else {
      this.eyes = eyes;
    }
  }

  public String getHeight() {
    return height;
  }

  public void setHeight(String height) {
    if (height == null) {
      throw new NullPointerException();
    } else {
      this.height = height;
    }
  }

  public String getHair() {
    return hair;
  }

  public void setHair(String hair) {
    if (hair == null) {
      throw new NullPointerException();
    } else {
      this.hair = hair;
    }
  }

  public String getSkinTone() {
    return skinTone;
  }

  public void setSkinTone(String skinTone) {
    if (skinTone == null) {
      throw new NullPointerException();
    } else {
      this.skinTone = skinTone;
    }
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    if (weight == null) {
      throw new NullPointerException();
    } else {
      this.weight = weight;
    }
  }

  public String getMarkings() {
    return markings;
  }

  public void setMarkings(String markings) {
    if (markings == null) {
      throw new NullPointerException();
    } else {
      this.markings = markings;
    }
  }

}
