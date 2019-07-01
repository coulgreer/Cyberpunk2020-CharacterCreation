package rpg.cyberpunk._2020.stats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Role {
  public static final int OPTION_COUNT = 10;

  private String name;
  private String description;
  private List<List<String>> skillNameOptions;

  public Role(String name, String description, List<List<String>> skillNameOptions) {
    setName(name);
    setDescription(description);
    setSkillNameOptions(skillNameOptions);
  }

  private void setName(String name) {
    int minLength = 1;

    if (name == null) {
      throw new NullPointerException();
    } else if (name.length() < minLength) {
      throw new IllegalArgumentException("length = " + name.length() + "; min length = " + minLength);
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

  private void setSkillNameOptions(List<List<String>> skillNameOptions) {
    if (skillNameOptions == null) {
      throw new NullPointerException();
    } else if (skillNameOptions.size() != OPTION_COUNT) {
      throw new IllegalArgumentException(
          "length: " + skillNameOptions.size() + "; length must be " + OPTION_COUNT);
    } else {
      for (List<String> list : skillNameOptions) {
        validateOptions(list);
      }
      this.skillNameOptions = skillNameOptions;
    }
  }

  private void validateOptions(List<String> options) {
    if (options == null) {
      throw new NullPointerException();
    } else if (options.contains(null)) {
      throw new NullPointerException(options + " cannot hold null values.");
    }
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public List<List<String>> getSkillNameOptions() {
    List<List<String>> tempLists = new ArrayList<>(skillNameOptions.size());

    for (List<String> list : skillNameOptions) {
      tempLists.add(Collections.unmodifiableList(list));
    }

    return Collections.unmodifiableList(tempLists);
  }

  public String getSpecialAbilityName() {
    return skillNameOptions.get(0).get(0);
  }

}
