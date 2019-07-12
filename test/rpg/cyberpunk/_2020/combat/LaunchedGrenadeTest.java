package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import org.junit.Before;
import org.junit.Test;
import rpg.general.combat.Ammunition.Type;

public class LaunchedGrenadeTest {
  private Type mockType;
  private Payload mockLoad;
  private double weight;

  @Before
  public void setUp() {
    mockType = mock(Type.class);
    mockLoad = mock(Payload.class);
    weight = 0.0;
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_TypeIsNull() {
    LaunchedGrenade grenade = new LaunchedGrenade(null, mockLoad, weight);
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_PayloadIsNull() {
    LaunchedGrenade grenade = new LaunchedGrenade(mockType, null, weight);
  }

  @SuppressWarnings("unused")
  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_WeightIsNegativeOneTenth() {
    LaunchedGrenade grenade = new LaunchedGrenade(mockType, mockLoad, -0.1);
  }



  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparedToNull() {
    LaunchedGrenade grenade = new LaunchedGrenade(mockType, mockLoad, weight);

    assertFalse(grenade.equals(null));
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_ComparedToItself() {
    LaunchedGrenade grenade = new LaunchedGrenade(mockType, mockLoad, weight);

    assertTrue(grenade.equals(grenade));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_NotComparedToInstanceOfLaunchedGrenade() {
    LaunchedGrenade grenade = new LaunchedGrenade(mockType, mockLoad, weight);

    assertFalse(grenade.equals(new Object()));
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_TwoLaunchedGrenadesShareState() {
    LaunchedGrenade grenade1 = new LaunchedGrenade(mockType, mockLoad, weight);
    LaunchedGrenade grenade2 = new LaunchedGrenade(mockType, mockLoad, weight);

    assertTrue(grenade1.equals(grenade2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_TwoLaunchedGrenadesHaveInequivalentTypes() {
    LaunchedGrenade grenade1 = new LaunchedGrenade(mock(Type.class), mockLoad, weight);
    LaunchedGrenade grenade2 = new LaunchedGrenade(mock(Type.class), mockLoad, weight);

    assertFalse(grenade1.equals(grenade2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_TwoLaunchedGrenadesHaveInequivalentPayload() {
    LaunchedGrenade grenade1 = new LaunchedGrenade(mockType, mock(Payload.class), weight);
    LaunchedGrenade grenade2 = new LaunchedGrenade(mockType, mock(Payload.class), weight);

    assertFalse(grenade1.equals(grenade2));
  }

}
