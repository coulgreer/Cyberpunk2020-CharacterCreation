package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import rpg.cyberpunk._2020.combat.ShotShell.Load;
import rpg.util.Name;

public class LoadTest {
  private Name mockName;
  private String description;
  private double costMultiplier;

  @Before
  public void setUp() {
    mockName = mock(Name.class);
    description = "Description";
    costMultiplier = 0.0;
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_NameIsNull() {
    Load load = new Load(null, description, costMultiplier);
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_DescriptionIsNull() {
    Load load = new Load(mockName, null, costMultiplier);
  }

  @SuppressWarnings("unused")
  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_CostMultiplierIsNegativeOneTenth() {
    Load load = new Load(mockName, description, -0.1);
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_ComparedToItself() {
    Load load = new Load(mockName, description, costMultiplier);

    assertTrue(load.equals(load));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparedToNull() {
    Load load = new Load(mockName, description, costMultiplier);

    assertFalse(load.equals(null));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_NotComparedToAnInstanceOfLoad() {
    Load load = new Load(mockName, description, costMultiplier);

    assertFalse(load.equals(new Object()));
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_ComparingTwoInstancesOfLoad_While_TheyHaveSameState() {
    Load load1 = new Load(mockName, description, costMultiplier);
    Load load2 = new Load(mockName, description, costMultiplier);

    assertTrue(load1.equals(load2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparingTwoInstancesOfLoad_While_TheyHaveInequivalentNames() {
    Name mockName1 = mock(Name.class);
    when(mockName1.getValue()).thenReturn("Name 1");
    
    Name mockName2 = mock(Name.class);
    when(mockName2.getValue()).thenReturn("Name 2");
    
    Load load1 = new Load(mockName1, description, costMultiplier);
    Load load2 = new Load(mockName2, description, costMultiplier);

    assertFalse(load1.equals(load2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparingTwoInstancesOfLoad_While_TheyHaveInequivalentDescriptions() {
    Load load1 = new Load(mockName, "Descritpion 1", costMultiplier);
    Load load2 = new Load(mockName, "Description 2", costMultiplier);

    assertFalse(load1.equals(load2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparingTwoInstancesOfLoad_While_TheyHaveInequivalentCostMultipliers() {
    Load load1 = new Load(mockName, description, 1.0);
    Load load2 = new Load(mockName, description, 2.0);

    assertFalse(load1.equals(load2));
  }

}
