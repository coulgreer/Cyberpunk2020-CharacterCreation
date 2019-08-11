package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Collections;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Availability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Concealability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Reliability;
import rpg.general.combat.AmmunitionContainer;
import rpg.util.Measurement;
import rpg.util.Probability;

public class BowTest {
  private String weaponName;
  private String description;
  private int weaponAccuracy;
  private Concealability concealability;
  private Availability availability;
  private Probability mockDamage;
  private AmmunitionContainer mockAmmunitionContainer;
  private int rateOfFire;
  private Reliability reliability;
  private Measurement rangeModifier;
  private double cost;
  private Measurement weight;
  private Set<String> attachmentPoints;

  @Before
  public void setUp() {
    weaponName = "Weapon Name";
    description = "Description";
    weaponAccuracy = 0;
    concealability = Concealability.CANNOT_HIDE;
    availability = Availability.COMMON;
    mockDamage = mock(Probability.class);
    mockAmmunitionContainer = mock(AmmunitionContainer.class);
    rateOfFire = 1;
    reliability = Reliability.STANDARD;
    rangeModifier = new Measurement( //
        Measurement.Type.LENGTH, //
        0, //
        Measurement.Unit.METER);
    cost = 0.0;
    weight = new Measurement( //
        Measurement.Type.MASS, //
        1.0, //
        Measurement.Unit.KILOGRAM);
    // TODO (Coul Greer): Actually initialize the proceeding field properly.
    attachmentPoints = Collections.emptySet();
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_WeaponNameIsNull() {
    @SuppressWarnings("unused")
    Bow bow = new Bow( //
        null, description, //
        weaponAccuracy, //
        concealability, availability, //
        mockDamage, mockAmmunitionContainer, rateOfFire, //
        reliability, rangeModifier, //
        cost, weight, //
        attachmentPoints);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_DescriptionIsNull() {
    @SuppressWarnings("unused")
    Bow bow = new Bow( //
        weaponName, null, //
        weaponAccuracy, //
        concealability, availability, //
        mockDamage, mockAmmunitionContainer, rateOfFire, //
        reliability, rangeModifier, //
        cost, weight, //
        attachmentPoints);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_ConcealabilityIsNull() {
    @SuppressWarnings("unused")
    Bow bow = new Bow( //
        weaponName, description, //
        weaponAccuracy, //
        null, availability, //
        mockDamage, mockAmmunitionContainer, rateOfFire, //
        reliability, rangeModifier, //
        cost, weight, //
        attachmentPoints);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_AvailabilityIsNull() {
    @SuppressWarnings("unused")
    Bow bow = new Bow( //
        weaponName, description, //
        weaponAccuracy, //
        concealability, null, //
        mockDamage, mockAmmunitionContainer, rateOfFire, //
        reliability, rangeModifier, //
        cost, weight, //
        attachmentPoints);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_DamageIsNull() {
    @SuppressWarnings("unused")
    Bow bow = new Bow( //
        weaponName, description, //
        weaponAccuracy, //
        concealability, availability, //
        null, mockAmmunitionContainer, rateOfFire, //
        reliability, rangeModifier, //
        cost, weight, //
        attachmentPoints);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_AmmunitionContainerIsNull() {
    @SuppressWarnings("unused")
    Bow bow = new Bow( //
        weaponName, description, //
        weaponAccuracy, //
        concealability, availability, //
        mockDamage, null, rateOfFire, //
        reliability, rangeModifier, //
        cost, weight, //
        attachmentPoints);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_RateOfFireIsNegativeOne() {
    @SuppressWarnings("unused")
    Bow bow = new Bow( //
        weaponName, description, //
        weaponAccuracy, //
        concealability, availability, //
        mockDamage, mockAmmunitionContainer, -1, //
        reliability, rangeModifier, //
        cost, weight, //
        attachmentPoints);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_RateOfFireIsZero() {
    @SuppressWarnings("unused")
    Bow bow = new Bow( //
        weaponName, description, //
        weaponAccuracy, //
        concealability, availability, //
        mockDamage, mockAmmunitionContainer, 0, //
        reliability, rangeModifier, //
        cost, weight, //
        attachmentPoints);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_ReliabilityIsNull() {
    @SuppressWarnings("unused")
    Bow bow = new Bow( //
        weaponName, description, //
        weaponAccuracy, //
        concealability, availability, //
        mockDamage, mockAmmunitionContainer, rateOfFire, //
        null, rangeModifier, //
        cost, weight, //
        attachmentPoints);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_RangeModifierIsNull() {
    @SuppressWarnings("unused")
    Bow bow = new Bow( //
        weaponName, description, //
        weaponAccuracy, //
        concealability, availability, //
        mockDamage, mockAmmunitionContainer, rateOfFire, //
        reliability, null, //
        cost, weight, //
        attachmentPoints);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_CostIsNegativeOneTenth() {
    @SuppressWarnings("unused")
    Bow bow = new Bow( //
        weaponName, description, //
        weaponAccuracy, //
        concealability, availability, //
        mockDamage, mockAmmunitionContainer, rateOfFire, //
        reliability, rangeModifier, //
        -0.1, weight, //
        attachmentPoints);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_WeightIsNull() {
    @SuppressWarnings("unused")
    Bow bow = new Bow( //
        weaponName, description, //
        weaponAccuracy, //
        concealability, availability, //
        mockDamage, mockAmmunitionContainer, rateOfFire, //
        reliability, rangeModifier, //
        cost, null, //
        attachmentPoints);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_AttachmentPointsAreNull() {
    @SuppressWarnings("unused")
    Bow bow = new Bow( //
        weaponName, description, //
        weaponAccuracy, //
        concealability, availability, //
        mockDamage, mockAmmunitionContainer, rateOfFire, //
        reliability, rangeModifier, //
        cost, weight, //
        null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_AttackingZeroTimes() {
    Bow bow = new Bow( //
        weaponName, description, //
        weaponAccuracy, //
        concealability, availability, //
        mockDamage, mockAmmunitionContainer, rateOfFire, //
        reliability, rangeModifier, //
        cost, weight, //
        attachmentPoints);

    bow.attack(0);
  }

  @Test
  public void Should_ReturnTrue_When_WeaponAttacksOnce_While_AmmunitionCountIsAtLeastOneAndAmmunitionCapacityIsNotZero() {
    when(mockAmmunitionContainer.getAmmunitionCount()).thenReturn(1);
    when(mockAmmunitionContainer.getAmmunitionCapacity()).thenReturn(1);
    Bow bow = new Bow( //
        weaponName, description, //
        weaponAccuracy, //
        concealability, availability, //
        mockDamage, mockAmmunitionContainer, rateOfFire, //
        reliability, rangeModifier, //
        cost, weight, //
        attachmentPoints);

    assertTrue(bow.attack(1));
  }

  @Test
  public void Should_ReturnTrue_When_WeaponAttacksTwice_While_AmmunitionCountIsAtLeastTwoAndAmmunitionCapacityIsNotZero() {
    when(mockAmmunitionContainer.getAmmunitionCount()).thenReturn(2);
    when(mockAmmunitionContainer.getAmmunitionCapacity()).thenReturn(2);
    Bow bow = new Bow( //
        weaponName, description, //
        weaponAccuracy, //
        concealability, availability, //
        mockDamage, mockAmmunitionContainer, rateOfFire, //
        reliability, rangeModifier, //
        cost, weight, //
        attachmentPoints);

    assertTrue(bow.attack(2));
  }

  @Test
  public void Should_ReturnTrue_When_WeaponAttacksAtLeastOnce_While_AmmunitionCountIsAtLeastOneAndAmmunitionCapacityIsNotZero() {
    when(mockAmmunitionContainer.getAmmunitionCount()).thenReturn(1);
    when(mockAmmunitionContainer.getAmmunitionCapacity()).thenReturn(1);
    Bow bow = new Bow( //
        weaponName, description, //
        weaponAccuracy, //
        concealability, availability, //
        mockDamage, mockAmmunitionContainer, rateOfFire, //
        reliability, rangeModifier, //
        cost, weight, //
        attachmentPoints);

    assertTrue(bow.attack(1));
  }

  @Test
  public void Should_ReturnFalse_When_WeaponAttacksAtLeastOnce_While_AmmunitionCountIsAtMostOneAndAmmunitionCapacityIsNotZero() {
    when(mockAmmunitionContainer.getAmmunitionCount()).thenReturn(0);
    when(mockAmmunitionContainer.getAmmunitionCapacity()).thenReturn(1);
    Bow bow = new Bow( //
        weaponName, description, //
        weaponAccuracy, //
        concealability, availability, //
        mockDamage, mockAmmunitionContainer, rateOfFire, //
        reliability, rangeModifier, //
        cost, weight, //
        attachmentPoints);

    assertFalse(bow.attack(1));
  }

  @Test
  public void Should_ReturnTrue_When_WeaponAttacksAtLeastOnce_While_AmmunitionCountIsAtMostZeroAndAmmunitionCapacityIsZero() {
    when(mockAmmunitionContainer.getAmmunitionCount()).thenReturn(0);
    when(mockAmmunitionContainer.getAmmunitionCapacity()).thenReturn(0);
    Bow bow = new Bow( //
        weaponName, description, //
        weaponAccuracy, //
        concealability, availability, //
        mockDamage, mockAmmunitionContainer, rateOfFire, //
        reliability, rangeModifier, //
        cost, weight, //
        attachmentPoints);

    assertTrue(bow.attack(1));
  }

}
