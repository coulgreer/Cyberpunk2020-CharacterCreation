package rpg;

import org.apache.commons.text.WordUtils;

public enum Gender {
  MALE, FEMALE, OTHER;

  @Override
  public String toString() {
    String str = name();

    str = str.replace("_", " ");
    str = WordUtils.capitalizeFully(str);
    return str;
  }
}
