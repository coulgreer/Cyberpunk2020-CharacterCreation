package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class CyberpunkAttributeTest {
  private String name;
  private String description;

  @Before
  public void setUp() {
    name = "Name";
    description = "Description";
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_NameIsNull() {
    @SuppressWarnings("unused")
    CyberpunkAttribute attribute = new CyberpunkAttribute(null, description);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_DescriptionIsNull() {
    @SuppressWarnings("unused")
    CyberpunkAttribute attribute = new CyberpunkAttribute(name, null);
  }

  @Test
  public void Should_ReturnLevelAsTwoAndModifierAsTwo_When_AttributeCreated() {
    CyberpunkAttribute attribute = new CyberpunkAttribute(name, description);

    assertEquals(2, attribute.getLevel());
    assertEquals(2, attribute.getModifier());
  }

  @Test
  public void Should_ReturnLevelAsThreeAndModifierAsThree_When_LevelIsIncrementedOnce() {
    CyberpunkAttribute attribute = new CyberpunkAttribute(name, description);

    attribute.incrementLevel();

    assertEquals(3, attribute.getLevel());
    assertEquals(3, attribute.getModifier());
  }

  @Test
  public void Should_ReturnLevelAsTen_When_LevelIsIncrementedTenTimes() {
    CyberpunkAttribute attribute = new CyberpunkAttribute(name, description);

    for (int i = 0; i < 10; i++) {
      attribute.incrementLevel();
    }

    assertEquals(10, attribute.getLevel());
  }

  @Test
  public void Should_ReturnLevelAsTen_When_LevelIsIncrementedElevenTimes() {
    CyberpunkAttribute attribute = new CyberpunkAttribute(name, description);

    for (int i = 0; i < 11; i++) {
      attribute.incrementLevel();
    }

    assertEquals(10, attribute.getLevel());
  }

  @Test
  public void Should_ReturnLevelAsTwoAndModifierAsTwo_When_LevelIsDecrementedOnce() {
    CyberpunkAttribute attribute = new CyberpunkAttribute(name, description);

    attribute.decrementLevel();

    assertEquals(2, attribute.getLevel());
    assertEquals(2, attribute.getModifier());
  }

  @Test
  public void Should_ReturnLevelAsTwo_When_LevelIsDecrementedOnce_While_LevelIsThree() {
    CyberpunkAttribute attribute = new CyberpunkAttribute(name, description);
    attribute.incrementLevel();

    attribute.decrementLevel();

    assertEquals(2, attribute.getLevel());
  }

  @Test
  public void Should_ReturnLevelAsTwoAndModifierAsTwo_When_LevelIsReset() {
    CyberpunkAttribute attribute = new CyberpunkAttribute(name, description);

    attribute.resetLevel();

    assertEquals(2, attribute.getLevel());
    assertEquals(2, attribute.getModifier());
  }

  @Test
  public void Should_ReturnLevelAsTwo_When_LevelIsReset_While_LevelIsThree() {
    CyberpunkAttribute attribute = new CyberpunkAttribute(name, description);
    attribute.incrementLevel();

    attribute.resetLevel();

    assertEquals(2, attribute.getLevel());
  }

}
