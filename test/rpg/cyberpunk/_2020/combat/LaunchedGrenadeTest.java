package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import org.junit.Before;
import org.junit.Test;
import rpg.general.combat.Ammunition.Type;
import rpg.util.Measurement;

public class LaunchedGrenadeTest {
  private Type mockType;
  private Payload mockLoad;
  private Measurement weight;

  @Before
  public void setUp() {
    mockType = mock(Type.class);
    mockLoad = mock(Payload.class);
    weight = new Measurement( //
        Measurement.Type.MASS, //
        0.0, //
        Measurement.Unit.KILOGRAM);
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
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_WeightIsNullh() {
    LaunchedGrenade grenade = new LaunchedGrenade(mockType, mockLoad, null);
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
