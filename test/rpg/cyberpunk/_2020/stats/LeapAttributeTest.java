package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import rpg.general.stats.Attribute;

public class LeapAttributeTest {
  private String name;
  private String description;
  private Attribute mockAttribute;

  @Before
  public void setUp() {
    name = "Name";
    description = "Description";
    mockAttribute = mock(Attribute.class);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_NameIsNull() {
    @SuppressWarnings("unused")
    LeapAttribute attribute = new LeapAttribute(null, description, mockAttribute);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_DescriptionIsNull() {
    @SuppressWarnings("unused")
    LeapAttribute attribute = new LeapAttribute(name, null, mockAttribute);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_ParentAttributeIsNull() {
    @SuppressWarnings("unused")
    LeapAttribute attribute = new LeapAttribute(name, description, null);
  }

  @Test
  public void Should_ReturnLevelAsSevenAndModifierAsSeven_When_ParentAttributeModifierIsTen() {
    when(mockAttribute.getModifier()).thenReturn(10);
    LeapAttribute attribute = new LeapAttribute(name, description, mockAttribute);

    assertEquals(7, attribute.getLevel());
    assertEquals(7, attribute.getModifier());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void Should_ThrowException_When_LevelIncreased() {
    LeapAttribute attribute = new LeapAttribute(name, description, mockAttribute);

    attribute.incrementLevel();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void Should_ThrowException_When_LevelDecreased() {
    LeapAttribute attribute = new LeapAttribute(name, description, mockAttribute);

    attribute.decrementLevel();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void Should_ThrowException_When_LevelReset() {
    LeapAttribute attribute = new LeapAttribute(name, description, mockAttribute);

    attribute.resetLevel();
  }

}
