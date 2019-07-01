package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import rpg.cyberpunk._2020.combat.Cartridge.Bullet;
import rpg.cyberpunk._2020.combat.Cartridge.CaseMaterial;
import rpg.util.Die;
import rpg.util.Probability;

public class CartridgeTests {

  private String caliber;
  private Bullet bullet;
  private CaseMaterial caseMaterial;
  private double baseCost;
  private double weight;

  @Before
  public void setUp() {
    caliber = "";
    bullet = Bullet.SOFT_POINT;
    caseMaterial = CaseMaterial.CASELESS;
    baseCost = 0.0;
    weight = 0.0;
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_CaliberIsNull() {
    @SuppressWarnings("unused")
    Cartridge cartridge = new Cartridge(null, bullet, caseMaterial, baseCost, weight);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_BulletIsNull() {
    @SuppressWarnings("unused")
    Cartridge cartridge = new Cartridge(caliber, null, caseMaterial, baseCost, weight);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_CaseMaterialIsNull() {
    @SuppressWarnings("unused")
    Cartridge cartridge = new Cartridge(caliber, bullet, null, baseCost, weight);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_BaseCostIsLessThanZero() {
    @SuppressWarnings("unused")
    Cartridge cartridge = new Cartridge(caliber, bullet, caseMaterial, -1.0, weight);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_WeightIsLessThanZero() {
    @SuppressWarnings("unused")
    Cartridge cartridge = new Cartridge(caliber, bullet, caseMaterial, baseCost, -1.0);
  }

  @Test
  public void Should_ReturnSix_When_BaseCostIsOneAndHasCopperAndArmorPiercing() {
    Cartridge cartridge =
        new Cartridge(caliber, Bullet.ARMOR_PIERCING, CaseMaterial.COPPER, 1.0, weight);

    assertEquals(6.0, cartridge.getCost(), 0.0);
  }

  @Test
  public void Should_ReturnExpectedProbability_When_CaliberEquals5mm() {
    Probability expectedProbability = new Probability(new Die(1, 6), 0);
    Cartridge cartridge =
        new Cartridge(Cartridge.AMMUNITION_TYPE_5MM, bullet, caseMaterial, baseCost, weight);

    assertEquals(expectedProbability, cartridge.getDamage());
  }

  @Test
  public void Should_ReturnExpectedProbability_When_CaliberEquals6mm() {
    Probability expectedProbability = new Probability(new Die(1, 6), 1);
    Cartridge cartridge =
        new Cartridge(Cartridge.AMMUNITION_TYPE_6MM, bullet, caseMaterial, baseCost, weight);

    assertEquals(expectedProbability, cartridge.getDamage());
  }

  @Test
  public void Should_ReturnExpectedProbability_When_CaliberEquals9mm() {
    Probability expectedProbability = new Probability(new Die(2, 6), 1);
    Cartridge cartridge =
        new Cartridge(Cartridge.AMMUNITION_TYPE_9MM, bullet, caseMaterial, baseCost, weight);

    assertEquals(expectedProbability, cartridge.getDamage());
  }

  @Test
  public void Should_ReturnExpectedProbability_When_CaliberEquals10mm() {
    Probability expectedProbability = new Probability(new Die(2, 6), 3);
    Cartridge cartridge =
        new Cartridge(Cartridge.AMMUNITION_TYPE_10MM, bullet, caseMaterial, baseCost, weight);

    assertEquals(expectedProbability, cartridge.getDamage());
  }

  @Test
  public void Should_ReturnExpectedProbability_When_CaliberEquals11mm() {
    Probability expectedProbability = new Probability(new Die(3, 6), 0);
    Cartridge cartridge =
        new Cartridge(Cartridge.AMMUNITION_TYPE_11MM, bullet, caseMaterial, baseCost, weight);

    assertEquals(expectedProbability, cartridge.getDamage());
  }

  @Test
  public void Should_ReturnExpectedProbability_When_CaliberEquals12mm() {
    Probability expectedProbability = new Probability(new Die(4, 6), 1);
    Cartridge cartridge =
        new Cartridge(Cartridge.AMMUNITION_TYPE_12MM, bullet, caseMaterial, baseCost, weight);

    assertEquals(expectedProbability, cartridge.getDamage());
  }

  @Test
  public void Should_ReturnExpectedProbability_When_CaliberEquals556() {
    Probability expectedProbability = new Probability(new Die(5, 6), 0);
    Cartridge cartridge =
        new Cartridge(Cartridge.AMMUNITION_TYPE_556, bullet, caseMaterial, baseCost, weight);

    assertEquals(expectedProbability, cartridge.getDamage());
  }

  @Test
  public void Should_ReturnExpectedProbability_When_CaliberEquals762() {
    Probability expectedProbability = new Probability(new Die(6, 6), 2);
    Cartridge cartridge =
        new Cartridge(Cartridge.AMMUNITION_TYPE_762, bullet, caseMaterial, baseCost, weight);

    assertEquals(expectedProbability, cartridge.getDamage());
  }

  @Test
  public void Should_ReturnExpectedProbability_When_CaliberEquals20mm() {
    Probability expectedProbability = new Probability(new Die(4, 10), 0);
    Cartridge cartridge =
        new Cartridge(Cartridge.AMMUNITION_TYPE_20MM, bullet, caseMaterial, baseCost, weight);

    assertEquals(expectedProbability, cartridge.getDamage());
  }

  @Test
  public void Should_ReturnExpectedProbability_When_CaliberIsNotExpected() {
    Probability expectedProbability = new Probability(new Die(0, 1), 0);
    Cartridge cartridge = new Cartridge(caliber, bullet, caseMaterial, baseCost, weight);

    assertEquals(expectedProbability, cartridge.getDamage());
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_CatridgeIsComparedToItself() {
    Cartridge cartridge = new Cartridge(caliber, bullet, caseMaterial, baseCost, weight);

    assertTrue(cartridge.equals(cartridge));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_CatridgeIsComparedToNull() {
    Cartridge cartridge = new Cartridge(caliber, bullet, caseMaterial, baseCost, weight);

    assertFalse(cartridge.equals(null));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_CatridgeIsComparedToAnObjectOfAnotherInstance() {
    Cartridge cartridge = new Cartridge(caliber, bullet, caseMaterial, baseCost, weight);

    assertFalse(cartridge.equals(new Object()));
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_TwoCartridgesAreCompared_While_BothHaveSameState() {
    Cartridge cartridge1 = new Cartridge(caliber, bullet, caseMaterial, baseCost, weight);
    Cartridge cartridge2 = new Cartridge(caliber, bullet, caseMaterial, baseCost, weight);

    assertTrue(cartridge1.equals(cartridge2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_TwoCartridgesAreCompared_While_BothHaveDifferentCalibers() {
    Cartridge cartridge1 =
        new Cartridge(Cartridge.AMMUNITION_TYPE_10MM, bullet, caseMaterial, baseCost, weight);
    Cartridge cartridge2 =
        new Cartridge(Cartridge.AMMUNITION_TYPE_11MM, bullet, caseMaterial, baseCost, weight);

    assertFalse(cartridge1.equals(cartridge2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_TwoCartridgesAreCompared_While_BothHaveDifferentBullets() {
    Cartridge cartridge1 =
        new Cartridge(caliber, Bullet.ARMOR_PIERCING, caseMaterial, baseCost, weight);
    Cartridge cartridge2 =
        new Cartridge(caliber, Bullet.HOLLOW_POINT, caseMaterial, baseCost, weight);

    assertFalse(cartridge1.equals(cartridge2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_TwoCartridgesAreCompared_While_BothHaveDifferentCaseMaterials() {
    Cartridge cartridge1 = new Cartridge(caliber, bullet, CaseMaterial.CASELESS, baseCost, weight);
    Cartridge cartridge2 = new Cartridge(caliber, bullet, CaseMaterial.COPPER, baseCost, weight);

    assertFalse(cartridge1.equals(cartridge2));
  }

}
