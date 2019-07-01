package rpg.cyberpunk._2020.systems;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import rpg.cyberpunk._2020.systems.RandomCharacterPointsManager.RandomAttribute;
import rpg.general.stats.Attribute;

public class RandomAttributeTests {
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
    RandomAttribute attribute = new RandomAttribute(mockAttribute, null);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_AttributeIsNull() {
    @SuppressWarnings("unused")
    RandomAttribute attribute = new RandomAttribute(null, mockManager);
  }

  @Test
  public void Should_IncreaseLevelOnce_When_AvailablePointsGreaterThanZero() {
    when(mockManager.getCurrentlyAvailablePoints()).thenReturn(1);
    RandomAttribute attribute = new RandomAttribute(mockAttribute, mockManager);

    attribute.incrementLevel();

    verify(mockAttribute, times(1)).incrementLevel();
  }

}
