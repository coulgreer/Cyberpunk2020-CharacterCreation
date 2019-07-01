package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import rpg.general.stats.Attribute;

public class RunAttributeTest {
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
    RunAttribute attribute = new RunAttribute(null, description, mockAttribute);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_DescriptionIsNull() {
    @SuppressWarnings("unused")
    RunAttribute attribute = new RunAttribute(name, null, mockAttribute);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_ParentAttributeIsNull() {
    @SuppressWarnings("unused")
    RunAttribute attribute = new RunAttribute(name, description, null);
  }

  @Test
  public void Should_ReturnLevelAsThirtyAndModifierAsThirty_WhenParentAttributeModifierIsTen() {
    when(mockAttribute.getModifier()).thenReturn(10);
    RunAttribute attribute = new RunAttribute(name, description, mockAttribute);

    assertEquals(30, attribute.getLevel());
    assertEquals(30, attribute.getModifier());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void Should_ThrowException_When_LevelIncreased() {
    RunAttribute attribute = new RunAttribute(name, description, mockAttribute);

    attribute.incrementLevel();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void Should_ThrowException_When_LevelDecreased() {
    RunAttribute attribute = new RunAttribute(name, description, mockAttribute);

    attribute.decrementLevel();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void Should_ThrowException_When_LevelReset() {
    RunAttribute attribute = new RunAttribute(name, description, mockAttribute);

    attribute.resetLevel();
  }

}
