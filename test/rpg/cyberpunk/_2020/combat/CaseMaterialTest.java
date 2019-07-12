package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import rpg.cyberpunk._2020.combat.Cartridge.CaseMaterial;
import rpg.util.Name;

public class CaseMaterialTest {
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
    CaseMaterial material = new CaseMaterial(null, description, costMultiplier);
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_WhenDescriptionIsNull() {
    CaseMaterial material = new CaseMaterial(mockName, null, costMultiplier);
  }

  @SuppressWarnings("unused")
  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_WhenCostMultiplierIsNegativeOneTenth() {
    CaseMaterial material = new CaseMaterial(mockName, description, -0.1);
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_ComparedToItself() {
    CaseMaterial material = new CaseMaterial(mockName, description, costMultiplier);

    assertTrue(material.equals(material));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparedToNull() {
    CaseMaterial material = new CaseMaterial(mockName, description, costMultiplier);

    assertFalse(material.equals(null));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_NotComparedToAnInstanceOfCaseMaterial() {
    CaseMaterial material = new CaseMaterial(mockName, description, costMultiplier);

    assertFalse(material.equals(new Object()));
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_TwoCaseMaterialsShareTheSameState() {
    when(mockName.getValue()).thenReturn("Name");
    CaseMaterial material1 = new CaseMaterial(mockName, description, costMultiplier);
    CaseMaterial material2 = new CaseMaterial(mockName, description, costMultiplier);

    assertTrue(material1.equals(material2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_TwoCaseMaterialsHaveInequivalentNames() {
    Name mockName1 = mock(Name.class);
    when(mockName1.getValue()).thenReturn("Name 1");
    Name mockName2 = mock(Name.class);
    when(mockName2.getValue()).thenReturn("Name 2");
    CaseMaterial material1 = new CaseMaterial(mockName1, description, costMultiplier);
    CaseMaterial material2 = new CaseMaterial(mockName2, description, costMultiplier);

    assertFalse(material1.equals(material2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_TwoCaseMaterialsHaveInequivalentDescriptions() {
    when(mockName.getValue()).thenReturn("Name");
    CaseMaterial material1 = new CaseMaterial(mockName, "Description 1", costMultiplier);
    CaseMaterial material2 = new CaseMaterial(mockName, "Description 2", costMultiplier);

    assertFalse(material1.equals(material2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_TwoCaseMaterialsHaveInequivalentCostMultiplier() {
    when(mockName.getValue()).thenReturn("Name");
    CaseMaterial material1 = new CaseMaterial(mockName, description, 0.0);
    CaseMaterial material2 = new CaseMaterial(mockName, description, 0.1);

    assertFalse(material1.equals(material2));
  }

}
