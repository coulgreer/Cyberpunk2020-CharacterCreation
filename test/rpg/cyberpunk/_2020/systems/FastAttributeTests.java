package rpg.cyberpunk._2020.systems;

import static org.mockito.Mockito.mock;
import org.junit.Before;
import org.junit.Test;
import rpg.cyberpunk._2020.systems.FastCharacterPointsManager.FastAttribute;
import rpg.general.stats.Attribute;

public class FastAttributeTests {
  private Attribute mockAttribute;
  private CharacterPointsManager mockManager;

  @Before
  public void setUp() {
    mockAttribute = mock(Attribute.class);
    mockManager = mock(CharacterPointsManager.class);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_CharacterPointsManagerIsNull() {
    @SuppressWarnings("unused")
    FastAttribute attribute = new FastAttribute(mockAttribute, null);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_AttributeIsNull() {
    @SuppressWarnings("unused")
    FastAttribute attribute = new FastAttribute(null, mockManager);
  }

}
