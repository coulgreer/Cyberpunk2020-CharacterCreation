package rpg.cyberpunk._2020;

public class Age {
  public static final int MIN_VALUE = 0;

  private int value;

  public Age(int value) {
    if (value < MIN_VALUE) {
      throw new IllegalArgumentException("value = " + value + "; min = " + MIN_VALUE);
    } else {
      this.value = value;
    }
  }

  public int toInt() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }

    if (o == this) {
      return true;
    }

    if (!(o instanceof Age)) {
      return false;
    }

    Age other = (Age) o;
    return toInt() == other.toInt();
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(value);
  }

}
