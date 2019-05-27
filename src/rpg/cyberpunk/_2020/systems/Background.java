package rpg.cyberpunk._2020.systems;

import java.util.Collections;
import java.util.List;
import rpg.cyberpunk._2020.Sibling;

/**
 * A simple POJO used to store data pertaining to a Player's background.
 */
public class Background {
  private String familyRanking;
  private String parentStatus;
  private boolean hasParentTragedy;
  private String parentTragedy;
  private String familyStatus;
  private boolean hasFamilyTragedy;
  private String familyTragedy;
  private String childhoodEnvironment;
  private List<Sibling> siblings;

  /**
   * Constructs an instance of Background that initializes all String fields as "" and boolean
   * fields to false. Also, the list of Siblings is initialized as an empty list using
   * {@link Collections#emptyList()}
   */
  public Background() {
    familyRanking = "";
    parentStatus = "";
    hasParentTragedy = false;
    parentTragedy = "";
    familyStatus = "";
    hasFamilyTragedy = false;
    familyTragedy = "";
    childhoodEnvironment = "";
    siblings = Collections.emptyList();
  }

  /**
   * @return a String representation of the quality of living a Player's character has
   */
  public String getFamilyRanking() {
    return familyRanking;
  }

  /**
   * @param familyRanking a String representation of the quality of living a Player's character has
   */
  public void setFamilyRanking(String familyRanking) {
    this.familyRanking = familyRanking;
  }

  /**
   * @return the current status of a Player's parents
   */
  public String getParentStatus() {
    return parentStatus;
  }

  /**
   * @param parentStatus the current status of a Player's parents
   */
  public void setParentStatus(String parentStatus) {
    this.parentStatus = parentStatus;
  }

  /**
   * @return <code>true</code>, if a Player's parents suffered a tragedy
   */
  public boolean hasParentTragedy() {
    return hasParentTragedy;
  }

  /**
   * @param hasParentTragedy if <code>true</code> a Player's parents suffered a tragedy
   */
  public void setParentTragedy(boolean hasParentTragedy) {
    this.hasParentTragedy = hasParentTragedy;
  }

  /**
   * @return the blurb representing the tragedy a Player's parents suffered
   */
  public String getParentTragedy() {
    return parentTragedy;
  }

  /**
   * @param parentTragedy the blurb telling the tragedy of a Player's parents
   */
  public void setParentTragedy(String parentTragedy) {
    this.parentTragedy = parentTragedy;
  }

  /**
   * @return the current status of a Player's family
   */
  public String getFamilyStatus() {
    return familyStatus;
  }

  /**
   * @param familyStatus the current status of a Player's family
   */
  public void setFamilyStatus(String familyStatus) {
    this.familyStatus = familyStatus;
  }

  /**
   * @return <code>true</code> if, a Player's family suffered any tragedy
   */
  public boolean hasFamilyTragedy() {
    return hasFamilyTragedy;
  }

  /**
   * @param hasFamilyTragedy if <code>true</code> a Player's family suffered tragedy
   */
  public void setFamilyTragedy(boolean hasFamilyTragedy) {
    this.hasFamilyTragedy = hasFamilyTragedy;
  }

  /**
   * @return the tragedy that a Player's family has suffered
   */
  public String getFamilyTragedy() {
    return familyTragedy;
  }

  /**
   * @param familyTragedy the tragedy that a Player's family has suffered
   */
  public void setFamilyTragedy(String familyTragedy) {
    this.familyTragedy = familyTragedy;
  }

  /**
   * @return the kind of environment that a Player grew up in
   */
  public String getChildhoodEnvironment() {
    return childhoodEnvironment;
  }

  /**
   * @param childhoodEnvironment the kind of environment a Player grew up in
   */
  public void setChildhoodEnvironment(String childhoodEnvironment) {
    this.childhoodEnvironment = childhoodEnvironment;
  }

  /**
   * @return a copy of the siblings a Player has
   */
  public List<Sibling> getSiblings() {
    return Collections.unmodifiableList(siblings);
  }

  /**
   * @param siblings a collection of the siblings a Player has
   */
  public void setSiblings(List<Sibling> siblings) {
    this.siblings = siblings;
  }
}
