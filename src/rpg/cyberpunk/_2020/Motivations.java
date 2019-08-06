package rpg.cyberpunk._2020;

public class Motivations {
  private String personalityTrait;
  private String valuedPerson;
  private String valuedConcept;
  private String feelingsTowardOthers;
  private String valuedPosession;
  
  public Motivations() {
    personalityTrait = "";
    valuedPerson = "";
    valuedConcept = "";
    feelingsTowardOthers = "";
    valuedPosession = "";
  }

  public String getPersonalityTrait() {
    return personalityTrait;
  }

  public void setPersonalityTrait(String personalityTrait) {
    if (personalityTrait == null) {
      throw new NullPointerException();
    } else {
      this.personalityTrait = personalityTrait;
    }
  }

  public String getValuedPerson() {
    return valuedPerson;
  }

  public void setValuedPerson(String valuedPerson) {
    if (valuedPerson == null) {
      throw new NullPointerException();
    } else {
      this.valuedPerson = valuedPerson;
    }
  }

  public String getValuedConcept() {
    return valuedConcept;
  }

  public void setValuedConcept(String valuedConcept) {
    if (valuedConcept == null) {
      throw new NullPointerException();
    } else {
      this.valuedConcept = valuedConcept;
    }
  }

  public String getFeelingsTowardOthers() {
    return feelingsTowardOthers;
  }

  public void setFeelingsTowardOthers(String feelingsTowardOthers) {
    if (feelingsTowardOthers == null) {
      throw new NullPointerException();
    } else {
      this.feelingsTowardOthers = feelingsTowardOthers;
    }
  }

  public String getValuedPosession() {
    return valuedPosession;
  }

  public void setValuedPosession(String valuedPosession) {
    if (valuedPosession == null) {
      throw new NullPointerException();
    } else {
      this.valuedPosession = valuedPosession;
    }
  }

}
