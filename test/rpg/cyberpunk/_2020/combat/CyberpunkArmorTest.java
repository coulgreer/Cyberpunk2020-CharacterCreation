package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import rpg.general.combat.BodyLocation;
import rpg.util.Measurement;

public class CyberpunkArmorTest {
  private String name;
  private String description;
  private double cost;
  private Measurement weight;
  private List<BodyLocation> areCovered;
  private Iterator<BodyLocation> iterator;
  private String armorType;
  private int stoppingPower;
  private int encumbranceValue;

  @Before
  public void setUp() {
    name = "Name";
    description = "Description";
    cost = 1.0;
    weight = new Measurement( //
        Measurement.Type.MASS, //
        0.0, //
        Measurement.Unit.KILOGRAM);
    areCovered = Collections.emptyList();
    iterator = areCovered.iterator();
    armorType = CyberpunkArmor.ARMOR_TYPE_SOFT;
    stoppingPower = 0;
    encumbranceValue = 0;
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_NameIsNull() {
    @SuppressWarnings("unused")
    CyberpunkArmor armor = new CyberpunkArmor( //
        null, description, //
        iterator, //
        armorType, stoppingPower, encumbranceValue, //
        cost, weight);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_DescriptionIsNull() {
    @SuppressWarnings("unused")
    CyberpunkArmor armor = new CyberpunkArmor( //
        name, null, //
        iterator, //
        armorType, stoppingPower, encumbranceValue, //
        cost, weight);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_IteratorIsNull() {
    @SuppressWarnings("unused")
    CyberpunkArmor armor = new CyberpunkArmor( //
        name, description, //
        null, //
        armorType, stoppingPower, encumbranceValue, //
        cost, weight);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_ArmorTypeIsNull() {
    @SuppressWarnings("unused")
    CyberpunkArmor armor = new CyberpunkArmor( //
        name, description, //
        iterator, //
        null, stoppingPower, encumbranceValue, //
        cost, weight);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_StoppingPowerIsNegativeOne() {
    @SuppressWarnings("unused")
    CyberpunkArmor armor = new CyberpunkArmor( //
        name, description, //
        iterator, //
        armorType, -1, encumbranceValue, //
        cost, weight);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_EncumbranceValueIsNegativeOne() {
    @SuppressWarnings("unused")
    CyberpunkArmor armor = new CyberpunkArmor( //
        name, description, //
        iterator, //
        armorType, stoppingPower, -1, //
        cost, weight);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_CostIsNegativeOneTenth() {
    @SuppressWarnings("unused")
    CyberpunkArmor armor = new CyberpunkArmor( //
        name, description, //
        iterator, //
        armorType, stoppingPower, encumbranceValue, //
        -0.1, weight);
  }

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_WeightIsNull() {
    @SuppressWarnings("unused")
    CyberpunkArmor armor = new CyberpunkArmor( //
        name, description, //
        iterator, //
        armorType, stoppingPower, encumbranceValue, //
        cost, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_DamagePointsAreNegativeOne() {
    CyberpunkArmor armor = new CyberpunkArmor( //
        name, description, //
        iterator, //
        armorType, stoppingPower, encumbranceValue, //
        cost, weight);

    armor.damage(BodyLocation.TORSO, -1);
  }

  @Test
  public void Should_ReturnTorsoDurabilityAsTen_When_TorsoIsDamagedByZero_While_StoppingPowerIsTenAndCoversTorso() {
    List<BodyLocation> areCovered = new ArrayList<BodyLocation>();
    areCovered.add(BodyLocation.TORSO);
    CyberpunkArmor armor = new CyberpunkArmor( //
        name, description, //
        areCovered.iterator(), //
        armorType, 10, encumbranceValue, //
        cost, weight);

    armor.damage(BodyLocation.TORSO, 0);

    assertEquals(10, armor.getDurabilityAt(BodyLocation.TORSO));
  }

  @Test
  public void Should_ReturnTorsoDurabilityAsNine_When_TorsoIsDamagedByOne_While_StoppingPowerIsTenAndCoversTorso() {
    List<BodyLocation> areCovered = new ArrayList<BodyLocation>();
    areCovered.add(BodyLocation.TORSO);
    CyberpunkArmor armor = new CyberpunkArmor( //
        name, description, //
        areCovered.iterator(), //
        armorType, 10, encumbranceValue, //
        cost, weight);

    armor.damage(BodyLocation.TORSO, 1);

    assertEquals(9, armor.getDurabilityAt(BodyLocation.TORSO));
  }

  @Test
  public void Should_LowerOnlyTorsoDurability_When_TorsoIsDamagedByOne_While_StoppingPowerIsTenAndCoversTorsoWithLeftArmWithRightArm() {
    List<BodyLocation> areCovered = new ArrayList<BodyLocation>();
    areCovered.add(BodyLocation.TORSO);
    areCovered.add(BodyLocation.RIGHT_ARM);
    areCovered.add(BodyLocation.LEFT_ARM);
    CyberpunkArmor armor = new CyberpunkArmor( //
        name, description, //
        areCovered.iterator(), //
        armorType, 10, encumbranceValue, //
        cost, weight);

    armor.damage(BodyLocation.TORSO, 1);

    assertEquals(0, armor.getDurabilityAt(BodyLocation.HEAD));
    assertEquals(9, armor.getDurabilityAt(BodyLocation.TORSO));
    assertEquals(10, armor.getDurabilityAt(BodyLocation.RIGHT_ARM));
    assertEquals(10, armor.getDurabilityAt(BodyLocation.LEFT_ARM));
    assertEquals(0, armor.getDurabilityAt(BodyLocation.RIGHT_LEG));
    assertEquals(0, armor.getDurabilityAt(BodyLocation.LEFT_LEG));
  }

  @Test
  public void Should_LowerAllCoveredDurability_When_DamagingAllByOne_While_StoppingPowerIsTen() {
    List<BodyLocation> areCovered = new ArrayList<BodyLocation>();
    areCovered.add(BodyLocation.TORSO);
    areCovered.add(BodyLocation.RIGHT_ARM);
    areCovered.add(BodyLocation.LEFT_ARM);
    CyberpunkArmor armor = new CyberpunkArmor( //
        name, description, //
        areCovered.iterator(), //
        armorType, 10, encumbranceValue, //
        cost, weight);

    armor.damageAll(1);

    assertEquals(0, armor.getDurabilityAt(BodyLocation.HEAD));
    assertEquals(9, armor.getDurabilityAt(BodyLocation.TORSO));
    assertEquals(9, armor.getDurabilityAt(BodyLocation.RIGHT_ARM));
    assertEquals(9, armor.getDurabilityAt(BodyLocation.LEFT_ARM));
    assertEquals(0, armor.getDurabilityAt(BodyLocation.RIGHT_LEG));
    assertEquals(0, armor.getDurabilityAt(BodyLocation.LEFT_LEG));
  }

  @Test(expected = IllegalArgumentException.class)
  public void Should_ThrowException_When_RepairPointsAreNegativeOne() {
    CyberpunkArmor armor = new CyberpunkArmor( //
        name, description, //
        iterator, //
        armorType, stoppingPower, encumbranceValue, //
        cost, weight);

    armor.repair(BodyLocation.TORSO, -1);
  }

  @Test
  public void Should_ReturnTorsoDurabilityAsTen_When_TorsoIsRepairedByZero_While_StoppingPowerIsTenAndCoversTorso() {
    List<BodyLocation> areCovered = new ArrayList<BodyLocation>();
    areCovered.add(BodyLocation.TORSO);
    CyberpunkArmor armor = new CyberpunkArmor( //
        name, description, //
        areCovered.iterator(), //
        armorType, 10, encumbranceValue, //
        cost, weight);

    armor.repair(BodyLocation.TORSO, 0);

    assertEquals(10, armor.getDurabilityAt(BodyLocation.TORSO));
  }

  @Test
  public void Should_ReturnTorsoDurabilityAsEleven_When_TorsoIsRepairedByOne_While_StoppingPowerIsTenAndCoversTorso() {
    List<BodyLocation> areCovered = new ArrayList<BodyLocation>();
    areCovered.add(BodyLocation.TORSO);
    CyberpunkArmor armor = new CyberpunkArmor( //
        name, description, //
        areCovered.iterator(), //
        armorType, 10, encumbranceValue, //
        cost, weight);

    armor.repair(BodyLocation.TORSO, 1);

    assertEquals(11, armor.getDurabilityAt(BodyLocation.TORSO));
  }

  @Test
  public void Should_HeightenAllCoveredDurability_When_RepairingAllByOne_While_StoppingPowerIsTen() {
    List<BodyLocation> areCovered = new ArrayList<BodyLocation>();
    areCovered.add(BodyLocation.TORSO);
    areCovered.add(BodyLocation.RIGHT_ARM);
    areCovered.add(BodyLocation.LEFT_ARM);
    CyberpunkArmor armor = new CyberpunkArmor( //
        name, description, //
        areCovered.iterator(), //
        armorType, 10, encumbranceValue, //
        cost, weight);

    armor.repairAll(1);

    assertEquals(0, armor.getDurabilityAt(BodyLocation.HEAD));
    assertEquals(11, armor.getDurabilityAt(BodyLocation.TORSO));
    assertEquals(11, armor.getDurabilityAt(BodyLocation.RIGHT_ARM));
    assertEquals(11, armor.getDurabilityAt(BodyLocation.LEFT_ARM));
    assertEquals(0, armor.getDurabilityAt(BodyLocation.RIGHT_LEG));
    assertEquals(0, armor.getDurabilityAt(BodyLocation.LEFT_LEG));
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_ArmorIsComparedWithSelf() {
    CyberpunkArmor armor = new CyberpunkArmor( //
        name, description, //
        iterator, //
        armorType, stoppingPower, encumbranceValue, //
        cost, weight);

    assertTrue(armor.equals(armor));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ArmorIsComparedToNonarmor() {
    CyberpunkArmor armor = new CyberpunkArmor( //
        name, description, //
        iterator, //
        armorType, stoppingPower, encumbranceValue, //
        cost, weight);

    assertFalse(armor.equals(new Object()));
  }

  @Test
  public void Should_ReturnEqualsAsTrue_When_ArmorsHaveEquivalentNamesAndEquivalentStates() {
    CyberpunkArmor armor1 = new CyberpunkArmor( //
        name, description, //
        iterator, //
        armorType, stoppingPower, encumbranceValue, //
        cost, weight);
    CyberpunkArmor armor2 = new CyberpunkArmor( //
        name, description, //
        iterator, //
        armorType, stoppingPower, encumbranceValue, //
        cost, weight);

    assertTrue(armor1.equals(armor2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ArmorsHaveInequivalentNamesAndEquivalentStates() {
    CyberpunkArmor armor1 = new CyberpunkArmor( //
        "Armor 1", description, //
        iterator, //
        armorType, stoppingPower, encumbranceValue, //
        cost, weight);
    CyberpunkArmor armor2 = new CyberpunkArmor( //
        "Armor 2", description, //
        iterator, //
        armorType, stoppingPower, encumbranceValue, //
        cost, weight);

    assertFalse(armor1.equals(armor2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ArmorsHaveEquivalentNamesAndInequivalentProtectionScores() {
    CyberpunkArmor armor1 = new CyberpunkArmor( //
        name, description, //
        iterator, //
        armorType, 0, encumbranceValue, //
        cost, weight);
    CyberpunkArmor armor2 = new CyberpunkArmor( //
        name, description, //
        iterator, //
        armorType, 1, encumbranceValue, //
        cost, weight);

    assertFalse(armor1.equals(armor2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ArmorsHaveEquivalentNamesAndInequivalentEncumbranceValues() {
    CyberpunkArmor armor1 = new CyberpunkArmor( //
        name, description, //
        iterator, //
        armorType, stoppingPower, 0, //
        cost, weight);
    CyberpunkArmor armor2 = new CyberpunkArmor( //
        name, description, //
        iterator, //
        armorType, stoppingPower, 1, //
        cost, weight);

    assertFalse(armor1.equals(armor2));
  }

  @Test
  public void Should_ReturnEqualsAsFalse_When_ArmorsHaveEquivalentNamesAndInequivalentDurabilities() {
    List<BodyLocation> areCovered = new ArrayList<BodyLocation>();
    areCovered.add(BodyLocation.TORSO);
    CyberpunkArmor armor1 = new CyberpunkArmor( //
        name, description, //
        areCovered.iterator(), //
        armorType, stoppingPower, encumbranceValue, //
        cost, weight);
    CyberpunkArmor armor2 = new CyberpunkArmor( //
        name, description, //
        areCovered.iterator(), //
        armorType, stoppingPower, encumbranceValue, //
        cost, weight);

    armor1.damage(BodyLocation.TORSO, 1);

    assertFalse(armor1.equals(armor2));
  }

}
