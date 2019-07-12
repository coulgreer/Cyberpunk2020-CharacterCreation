package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import rpg.cyberpunk._2020.combat.Cartridge.Bullet;
import rpg.util.Name;

public class BulletTest {
  private Name mockName;
  private String description;
  private double costMultiplier;

  @Before
  public void setUp() {
    mockName = mock(Name.class);
    description = "Description";
    costMultiplier = 1.0;
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_WhenNameIsNull() {
    Bullet material = new Bullet(null, description, costMultiplier);
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_WhenDescriptionIsNull() {
    Bullet material = new Bullet(mockName, null, costMultiplier);
  }

  @SuppressWarnings("unused")
  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_WhenCostMultiplierIsNegativeOneTenth() {
    Bullet material = new Bullet(mockName, description, -0.1);
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_ComparedToItself() {
    Bullet material = new Bullet(mockName, description, costMultiplier);

    assertTrue(material.equals(material));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparedToNull() {
    Bullet material = new Bullet(mockName, description, costMultiplier);

    assertFalse(material.equals(null));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_NotComparedToAnInstanceOfBullet() {
    Bullet material = new Bullet(mockName, description, costMultiplier);

    assertFalse(material.equals(new Object()));
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_TwoBulletsShareTheSameState() {
    when(mockName.getValue()).thenReturn("Name");
    Bullet material1 = new Bullet(mockName, description, costMultiplier);
    Bullet material2 = new Bullet(mockName, description, costMultiplier);

    assertTrue(material1.equals(material2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_TwoBulletsHaveInequivalentNames() {
    Name mockName1 = mock(Name.class);
    when(mockName1.getValue()).thenReturn("Name 1");
    Name mockName2 = mock(Name.class);
    when(mockName2.getValue()).thenReturn("Name 2");
    Bullet material1 = new Bullet(mockName1, description, costMultiplier);
    Bullet material2 = new Bullet(mockName2, description, costMultiplier);

    assertFalse(material1.equals(material2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_TwoBulletsHaveInequivalentDescriptions() {
    when(mockName.getValue()).thenReturn("Name");
    Bullet material1 = new Bullet(mockName, "Description 1", costMultiplier);
    Bullet material2 = new Bullet(mockName, "Description 2", costMultiplier);

    assertFalse(material1.equals(material2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_TwoBulletsHaveInequivalentCostMultiplier() {
    when(mockName.getValue()).thenReturn("Name");
    Bullet material1 = new Bullet(mockName, description, 0.0);
    Bullet material2 = new Bullet(mockName, description, 0.1);

    assertFalse(material1.equals(material2));
  }

}
