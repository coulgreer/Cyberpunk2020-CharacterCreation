package rpg.cyberpunk._2020.combat;

import static org.mockito.Mockito.mock;
import org.junit.Before;
import org.junit.Test;
import rpg.cyberpunk._2020.combat.Arrow.Tip;
import rpg.util.Name;

public class TipTest {
  private Name mockName;
  private String description;
  private double cost;

  @Before
  public void setUp() {
    mockName = mock(Name.class);
    description = "Description";
    cost = 0.0;
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_NameIsNull() {
    Tip tip = new Tip(null, description, cost);
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_DescriptionIsNull() {
    Tip tip = new Tip(mockName, null, cost);
  }

  @SuppressWarnings("unused")
  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_CostIsNegativeOneTenth() {
    Tip tip = new Tip(mockName, description, -0.1);
  }
}
