package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import rpg.cyberpunk._2020.combat.Cartridge.Bullet;
import rpg.cyberpunk._2020.combat.Cartridge.Caliber;
import rpg.cyberpunk._2020.combat.Cartridge.CaseMaterial;

public class CartridgeTests {

  private Caliber mockCaliber;
  private Bullet mockBullet;
  private CaseMaterial mockCaseMaterial;
  private double weight;

  @Before
  public void setUp() {
    mockCaliber = mock(Caliber.class);
    mockBullet = mock(Bullet.class);
    mockCaseMaterial = mock(CaseMaterial.class);
    weight = 0.0;
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_CaliberIsNull() {
    Cartridge cartridge = new Cartridge(null, mockBullet, mockCaseMaterial, weight);
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_BulletIsNull() {
    Cartridge cartridge = new Cartridge(mockCaliber, null, mockCaseMaterial, weight);
  }

  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_CaseMaterialIsNull() {
    Cartridge cartridge = new Cartridge(mockCaliber, mockBullet, null, weight);
  }

  @SuppressWarnings("unused")
  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_WeightIsLessThanZero() {
    Cartridge cartridge = new Cartridge(mockCaliber, mockBullet, mockCaseMaterial, -1.0);
  }

  @Test
  public void Should_ReturnCostAsSix_When_CaliberHasACostOfOneAndBulletHasACostMultiplierOfTwoAndCaseMaterialHasACostMultiplierOfThree() {
    when(mockCaliber.getCost()).thenReturn(1.0);
    when(mockBullet.getCostMultiplier()).thenReturn(2.0);
    when(mockCaseMaterial.getCostMultiplier()).thenReturn(3.0);
    Cartridge cartridge = new Cartridge(mockCaliber, mockBullet, mockCaseMaterial, weight);

    assertEquals(6.0, cartridge.getCost(), 0.0);
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_CatridgeIsComparedToItself() {
    Cartridge cartridge = new Cartridge(mockCaliber, mockBullet, mockCaseMaterial, weight);

    assertTrue(cartridge.equals(cartridge));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_CatridgeIsComparedToNull() {
    Cartridge cartridge = new Cartridge(mockCaliber, mockBullet, mockCaseMaterial, weight);

    assertFalse(cartridge.equals(null));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_CatridgeIsComparedToAnObjectOfAnotherInstance() {
    Cartridge cartridge = new Cartridge(mockCaliber, mockBullet, mockCaseMaterial, weight);

    assertFalse(cartridge.equals(new Object()));
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_TwoCartridgesAreCompared_While_BothHaveSameState() {
    Cartridge cartridge1 = new Cartridge(mockCaliber, mockBullet, mockCaseMaterial, weight);
    Cartridge cartridge2 = new Cartridge(mockCaliber, mockBullet, mockCaseMaterial, weight);

    assertTrue(cartridge1.equals(cartridge2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_TwoCartridgesAreCompared_While_BothHaveInequivalentCalibers() {
    Cartridge cartridge1 = new Cartridge(mock(Caliber.class), mockBullet, mockCaseMaterial, weight);
    Cartridge cartridge2 = new Cartridge(mock(Caliber.class), mockBullet, mockCaseMaterial, weight);

    assertFalse(cartridge1.equals(cartridge2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_TwoCartridgesAreCompared_While_BothHaveInequivalentBullets() {
    Cartridge cartridge1 = new Cartridge(mockCaliber, mock(Bullet.class), mockCaseMaterial, weight);
    Cartridge cartridge2 = new Cartridge(mockCaliber, mock(Bullet.class), mockCaseMaterial, weight);

    assertFalse(cartridge1.equals(cartridge2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_TwoCartridgesAreCompared_While_BothHaveInequivalentCaseMaterials() {
    Cartridge cartridge1 = new Cartridge(mockCaliber, mockBullet, mock(CaseMaterial.class), weight);
    Cartridge cartridge2 = new Cartridge(mockCaliber, mockBullet, mock(CaseMaterial.class), weight);

    assertFalse(cartridge1.equals(cartridge2));
  }

}
