package rpg.cyberpunk._2020.systems;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import rpg.general.stats.Attribute;

public class RandomCharacterPointsManagerTests {

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_AttributesIsNull() {
    @SuppressWarnings("unused")
    RandomCharacterPointsManager manager = new RandomCharacterPointsManager(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_AttributesIsAdded_While_AttributesHasANullValue() {
    List<Attribute> list = new ArrayList<Attribute>();
    list.add(null);

    @SuppressWarnings("unused")
    RandomCharacterPointsManager manager = new RandomCharacterPointsManager(list);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void Should_ThrowException_When_AnyIntegerIsAddedToReturnedRolledPoints() {
    RandomCharacterPointsManager manager = new RandomCharacterPointsManager();
    List<Integer> rolledPoints = manager.getRolledPoints();
    rolledPoints.add(4);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void Should_ThrowException_When_AnyIntegerIsRemovedFromReturnedRolledPoints() {
    RandomCharacterPointsManager manager = new RandomCharacterPointsManager();
    List<Integer> rolledPoints = manager.getRolledPoints();
    rolledPoints.remove(4);
  }

  @Test
  public void Should_IncreaseReturnedAttributesSize_When_AttributeIsAddedToManager() {
    RandomCharacterPointsManager manager = new RandomCharacterPointsManager();
    Attribute mockAttribute = mock(Attribute.class);
    List<Attribute> attributes = manager.getAttributes();

    int initialSize = attributes.size();
    manager.add(mockAttribute);

    assertTrue(initialSize < attributes.size());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void Should_ThrowException_When_AnyAttributeIsAddedToReturnedAttributes() {
    RandomCharacterPointsManager manager = new RandomCharacterPointsManager();
    List<Attribute> attributes = manager.getAttributes();

    attributes.add(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void Should_ThrowException_When_AnyAttributeIsRemovedFromReturnedAttributes() {
    RandomCharacterPointsManager manager = new RandomCharacterPointsManager();
    List<Attribute> attributes = manager.getAttributes();

    attributes.remove(null);
  }

}
