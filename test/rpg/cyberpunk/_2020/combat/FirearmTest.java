package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Availability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Concealability;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon.Reliability;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.general.combat.AmmunitionContainer;

public class FirearmTest {
  private String weaponName;
  private String description;
  private String weaponType;
  private int weaponAccuracy;
  private Concealability concealability;
  private Availability availability;
  private AmmunitionContainer mockAmmunitionContainer;
  private int rateOfFire;
  private Reliability reliability;
  private int range;
  private double cost;
  private double weight;
  private Set<String> attachmentPoints;

  @Before
  public void setUp() {
    weaponName = "Weapon Name";
    description = "Description";
    weaponType = CyberpunkWeapon.NO_WEAPON_TYPE;
    weaponAccuracy = 0;
    concealability = Concealability.LONG_COAT;
    availability = Availability.COMMON;
    mockAmmunitionContainer = mock(AmmunitionContainer.class);
    rateOfFire = 2;
    reliability = Reliability.VERY_RELIABLE;
    range = 30;
    cost = 10.0;
    weight = 0.5;
    attachmentPoints = new HashSet<String>();
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_WeaponNameIsNull() {
    @SuppressWarnings("unused")
    Firearm weapon = new Firearm( //
        null, description, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_DescriptionIsNull() {
    @SuppressWarnings("unused")
    Firearm weapon = new Firearm( //
        weaponName, null, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_WeaponTypeIsNull() {
    @SuppressWarnings("unused")
    Firearm weapon = new Firearm( //
        weaponName, description, //
        null, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_SkillNameIsNull() {
    @SuppressWarnings("unused")
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, null, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_ConcealabilityIsNull() {
    @SuppressWarnings("unused")
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, //
        weaponAccuracy, //
        null, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_AvailabilityIsNull() {
    @SuppressWarnings("unused")
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, //
        weaponAccuracy, //
        concealability, null, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_AmmunitionContainerIsNull() {
    @SuppressWarnings("unused")
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, //
        weaponAccuracy, //
        concealability, availability, //
        null, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_RateOfFireIsZero() {
    @SuppressWarnings("unused")
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, 0, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);
  }

  @Test
  public void Should_ReturnRateOfFireAsOne_When_RateOfFireIsOne() {
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, 1, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);

    assertEquals(1, weapon.getRateOfFire());
  }

  @Test
  public void Should_ReturnRateOfFireAsTwo_When_RateOfFireIsTwo() {
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, 2, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);

    assertEquals(2, weapon.getRateOfFire());
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_ReliabilityIsNull() {
    @SuppressWarnings("unused")
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        null, //
        range, //
        cost, weight, //
        attachmentPoints);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_RangeModifierIsNegativeOne() {
    @SuppressWarnings("unused")
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        -1, //
        cost, weight, //
        attachmentPoints);
  }

  @Test
  public void Should_ReturnRangeModifierAsZero_When_RangeModifierIsZero() {
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        0, //
        cost, weight, //
        attachmentPoints);

    assertEquals(0, weapon.getRangeModifier());
  }

  @Test
  public void Should_ReturnRangeModifierAsOne_When_RangeModifierIsOne() {
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        1, //
        cost, weight, //
        attachmentPoints);

    assertEquals(1, weapon.getRangeModifier());
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_CostIsNegativeOneTenth() {
    @SuppressWarnings("unused")
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        -0.1, weight, //
        attachmentPoints);
  }

  @Test
  public void Should_ReturnCostAsZero_When_CostIsZero() {
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        0.0, weight, //
        attachmentPoints);

    assertEquals(0.0, weapon.getCost(), 0.0);
  }

  @Test
  public void Should_ReturnCostAsOneTenth_When_CostIsOneTenth() {
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        0.1, weight, //
        attachmentPoints);

    assertEquals(0.1, weapon.getCost(), 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_WeightIsNegativeOneTenth() {
    @SuppressWarnings("unused")
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, -0.1, //
        attachmentPoints);
  }

  @Test
  public void Should_ReturnWeightAsZero_When_WeightIsZero() {
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, 0.0, //
        attachmentPoints);

    assertEquals(0.0, weapon.getWeight(), 0.0);
  }

  @Test
  public void Should_ReturnWeightAsOneTenth_When_WeightIsOneTenth() {
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, 0.1, //
        attachmentPoints);

    assertEquals(0.1, weapon.getWeight(), 0.0);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_AttachmentPointsAreNull() {
    @SuppressWarnings("unused")
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        null);
  }

  @Test
  public void Should_ReturnSkillNameAsHandgun_When_WeaponTypeIsLightPistol() {
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_PISTOL, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);

    assertEquals(CyberpunkSkill.HANDGUN, weapon.getSkillName());
  }

  @Test
  public void Should_ReturnSkillNameAsHandgun_When_WeaponTypeIsMediumPistol() {
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_MEDIUM_PISTOL, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);

    assertEquals(CyberpunkSkill.HANDGUN, weapon.getSkillName());
  }

  @Test
  public void Should_ReturnSkillNameAsHandgun_When_WeaponTypeIsHeavyPistol() {
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_HEAVY_PISTOL, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);

    assertEquals(CyberpunkSkill.HANDGUN, weapon.getSkillName());
  }

  @Test
  public void Should_ReturnSkillNameAsHandgun_When_WeaponTypeIsVeryHeavyPistol() {
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_VERY_HEAVY_PISTOL, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);

    assertEquals(CyberpunkSkill.HANDGUN, weapon.getSkillName());
  }

  @Test
  public void Should_ReturnSkillNameAsSubmachinegun_When_WeaponTypeIsLightSubmachinegun() {
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_LIGHT_SUBMACHINEGUN, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);

    assertEquals(CyberpunkSkill.SUBMACHINEGUN, weapon.getSkillName());
  }

  @Test
  public void Should_ReturnSkillNameAsSubmachinegun_When_WeaponTypeIsMediumSubmachinegun() {
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_MEDIUM_SUBMACHINEGUN, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);

    assertEquals(CyberpunkSkill.SUBMACHINEGUN, weapon.getSkillName());
  }

  @Test
  public void Should_ReturnSkillNameAsSubmachinegun_When_WeaponTypeIsHeavySubmachinegun() {
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_HEAVY_SUBMACHINEGUN, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);

    assertEquals(CyberpunkSkill.SUBMACHINEGUN, weapon.getSkillName());
  }

  @Test
  public void Should_ReturnSkillNameAsRifle_When_WeaponTypeIsRifle() {
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_RIFLE, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);

    assertEquals(CyberpunkSkill.RIFLE, weapon.getSkillName());
  }

  @Test
  public void Should_ReturnSkillNameAsRifle_When_WeaponTypeIsShotgun() {
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_SHOTGUN, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);

    assertEquals(CyberpunkSkill.RIFLE, weapon.getSkillName());
  }

  @Test
  public void Should_ReturnSkillNameAsHeavyWeapons_When_WeaponTypeIsHeavyWeapon() {
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_HEAVY_WEAPON, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);

    assertEquals(CyberpunkSkill.HEAVY_WEAPONS, weapon.getSkillName());
  }

  @Test
  public void Should_ReturnSkillNameAsMelee_When_WeaponTypeIsMeleeWeapon() {
    Firearm weapon = new Firearm( //
        weaponName, description, //
        CyberpunkWeapon.WEAPON_TYPE_MELEE_WEAPON, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);

    assertEquals(CyberpunkSkill.MELEE, weapon.getSkillName());
  }

  @Test
  public void Should_ReturnSkillNameAsNone_When_WeaponTypeHasNoKnownWeaponType() {
    Firearm weapon = new Firearm( //
        weaponName, description, //
        "", //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);

    assertEquals(CyberpunkSkill.NONE, weapon.getSkillName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_WeaponAttacksZeroTimes() {
    Firearm weapon = new Firearm( //
        weaponName, description, //
        weaponType, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);

    assertTrue(weapon.attack(0));
  }

  @Test
  public void Should_ReturnTrue_When_WeaponAttacksOnce_While_AmmunitionCountIsAtLeastOneAndAmmunitionCapacityIsNotZero() {
    when(mockAmmunitionContainer.getAmmunitionCount()).thenReturn(1);
    when(mockAmmunitionContainer.getAmmunitionCapacity()).thenReturn(1);
    Firearm weapon = new Firearm( //
        weaponName, description, //
        weaponType, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);

    assertTrue(weapon.attack(1));
  }

  @Test
  public void Should_ReturnTrue_When_WeaponAttacksTwice_While_AmmunitionCountIsAtLeastTwoAndAmmunitionCapacityIsNotZero() {
    when(mockAmmunitionContainer.getAmmunitionCount()).thenReturn(2);
    when(mockAmmunitionContainer.getAmmunitionCapacity()).thenReturn(2);
    Firearm weapon = new Firearm( //
        weaponName, description, //
        weaponType, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);

    assertTrue(weapon.attack(2));
  }

  @Test
  public void Should_ReturnTrue_When_WeaponAttacksAtLeastOnce_While_AmmunitionCountIsAtLeastOneAndAmmunitionCapacityIsNotZero() {
    when(mockAmmunitionContainer.getAmmunitionCount()).thenReturn(1);
    when(mockAmmunitionContainer.getAmmunitionCapacity()).thenReturn(1);
    Firearm weapon = new Firearm( //
        weaponName, description, //
        weaponType, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);

    assertTrue(weapon.attack(1));
  }

  @Test
  public void Should_ReturnFalse_When_WeaponAttacksAtLeastOnce_While_AmmunitionCountIsAtMostOneAndAmmunitionCapacityIsNotZero() {
    when(mockAmmunitionContainer.getAmmunitionCount()).thenReturn(0);
    when(mockAmmunitionContainer.getAmmunitionCapacity()).thenReturn(1);
    Firearm weapon = new Firearm( //
        weaponName, description, //
        weaponType, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);

    assertFalse(weapon.attack(1));
  }

  @Test
  public void Should_ReturnTrue_When_WeaponAttacksAtLeastOnce_While_AmmunitionCountIsAtMostZeroAndAmmunitionCapacityIsZero() {
    when(mockAmmunitionContainer.getAmmunitionCount()).thenReturn(0);
    when(mockAmmunitionContainer.getAmmunitionCapacity()).thenReturn(0);
    Firearm weapon = new Firearm( //
        weaponName, description, //
        weaponType, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);

    assertTrue(weapon.attack(1));
  }

  @Test
  public void Should_ReturnDieCountAsZeroAndDamageModifierAsZero_When_AmmunitionContainerHasNullAmmunition() {
    when(mockAmmunitionContainer.getAmmunition()).thenReturn(null);
    Firearm weapon = new Firearm( //
        weaponName, description, //
        weaponType, //
        weaponAccuracy, //
        concealability, availability, //
        mockAmmunitionContainer, rateOfFire, //
        reliability, //
        range, //
        cost, weight, //
        attachmentPoints);

    assertEquals(0, weapon.getDamageDice().getDieCount());
    assertEquals(0, weapon.getDamageModifier());
  }

}
