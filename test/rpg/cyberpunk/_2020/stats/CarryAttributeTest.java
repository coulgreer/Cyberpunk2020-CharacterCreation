package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import rpg.general.stats.Attribute;

public class CarryAttributeTest {
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
    CarryAttribute attribute = new CarryAttribute(null, description, mockAttribute);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_DescriptionIsNull() {
    @SuppressWarnings("unused")
    CarryAttribute attribute = new CarryAttribute(name, null, mockAttribute);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_ParentAttributeIsNull() {
    @SuppressWarnings("unused")
    CarryAttribute attribute = new CarryAttribute(name, description, null);
  }

  @Test
  public void Should_ReturnLevelAsOneHundredAndModifierAsOneHundred_When_ParentAttributeModifierIsTen() {
    when(mockAttribute.getModifier()).thenReturn(10);
    CarryAttribute attribute = new CarryAttribute(name, description, mockAttribute);

    assertEquals(100, attribute.getLevel());
    assertEquals(100, attribute.getModifier());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void Should_ThrowException_When_LevelIncreased() {
    CarryAttribute attribute = new CarryAttribute(name, description, mockAttribute);

    attribute.incrementLevel();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void Should_ThrowException_When_LevelDecreased() {
    CarryAttribute attribute = new CarryAttribute(name, description, mockAttribute);

    attribute.decrementLevel();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void Should_ThrowException_When_LevelReset() {
    CarryAttribute attribute = new CarryAttribute(name, description, mockAttribute);

    attribute.resetLevel();
  }

}
