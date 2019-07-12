package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import org.junit.Before;
import org.junit.Test;
import rpg.cyberpunk._2020.combat.ShotShell.Load;
import rpg.general.combat.Ammunition.Type;
import rpg.util.Probability;

public class ShotshellTest {
  private Type mockGauge;
  private Load mockLoad;
  private Probability mockDamage;

  @Before
  public void setUp() {
    mockGauge = mock(Type.class);
    mockLoad = mock(Load.class);
    mockDamage = mock(Probability.class);
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_GaugeIsNull() {
    ShotShell shell = new ShotShell(null, mockLoad, mockDamage);
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_LoadIsNull() {
    ShotShell shell = new ShotShell(mockGauge, null, mockDamage);
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_DamageIsNull() {
    ShotShell shell = new ShotShell(mockGauge, mockLoad, null);
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_ComparedToItself() {
    ShotShell shell = new ShotShell(mockGauge, mockLoad, mockDamage);

    assertTrue(shell.equals(shell));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparedToNull() {
    ShotShell shell = new ShotShell(mockGauge, mockLoad, mockDamage);

    assertFalse(shell.equals(null));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_NotComparedToAnInstanceOfShotShell() {
    ShotShell shell = new ShotShell(mockGauge, mockLoad, mockDamage);

    assertFalse(shell.equals(new Object()));
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_ComparingTwoShotShells_While_TheuHaveSameState() {
    ShotShell shell1 = new ShotShell(mockGauge, mockLoad, mockDamage);
    ShotShell shell2 = new ShotShell(mockGauge, mockLoad, mockDamage);

    assertTrue(shell1.equals(shell2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparingTwoShotShells_While_TheyHaveInequivalentGauges() {
    Type gauge1 = mock(Type.class);
    Type gauge2 = mock(Type.class);

    ShotShell shell1 = new ShotShell(gauge1, mockLoad, mockDamage);
    ShotShell shell2 = new ShotShell(gauge2, mockLoad, mockDamage);

    assertFalse(shell1.equals(shell2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparingTwoShotShells_While_TheyHaveInequivalentLoads() {
    Load load1 = mock(Load.class);
    Load load2 = mock(Load.class);

    ShotShell shell1 = new ShotShell(mockGauge, load1, mockDamage);
    ShotShell shell2 = new ShotShell(mockGauge, load2, mockDamage);

    assertFalse(shell1.equals(shell2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ComparingTwoShotShells_While_TheyHaveInequivalentDamage() {
    Probability gauge1 = mock(Probability.class);
    Probability gauge2 = mock(Probability.class);

    ShotShell shell1 = new ShotShell(mockGauge, mockLoad, gauge1);
    ShotShell shell2 = new ShotShell(mockGauge, mockLoad, gauge2);

    assertFalse(shell1.equals(shell2));
  }

}
