package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Test;
import rpg.general.combat.BodyLocation;
import rpg.util.Measurement;

public class ArmorManagerTest {

  @Test(expected = NullPointerException.class)
  public void Should_ThrowException_When_NullArmorIsAdded() {
    CyberpunkArmor nullArmor = null;
    ArmorManager manager = new ArmorManager();

    manager.add(nullArmor);
  }

  @Test
  public void Should_ReturnTrue_When_AddingArmor_While_ManagerHasSpaceForHardArmorAndManagerHasLessThanThreeLayers() {
    CyberpunkArmor mockArmor = mock(CyberpunkArmor.class);
    ArmorManager manager = new ArmorManager();

    assertTrue(manager.add(mockArmor));
  }

  @Test
  public void Should_ReturnFalse_When_AddingNewArmorThatIsHardAndCoversTheSameLocationsAsTheOldArmor_While_ManagerHasOldArmorThatIsHardAndManagerHasLessThanThreeLayers() {
    CyberpunkArmor newArmor = mock(CyberpunkArmor.class);
    when(newArmor.isCovering(BodyLocation.HEAD)).thenReturn(true);
    when(newArmor.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_HARD);

    CyberpunkArmor oldArmor = mock(CyberpunkArmor.class);
    when(oldArmor.isCovering(BodyLocation.HEAD)).thenReturn(true);
    when(oldArmor.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_HARD);

    ArmorManager manager = new ArmorManager();
    manager.add(oldArmor);

    assertFalse(manager.add(newArmor));
  }

  @Test
  public void Should_ReturnFalse_When_AddingNewArmorThatCoversTheSameLocationAsThreeOtherOldArmors_While_ManagerHasOldArmorThatIsSoftAndManagerHasThreeLayers() {
    CyberpunkArmor newArmor = mock(CyberpunkArmor.class);
    when(newArmor.isCovering(BodyLocation.HEAD)).thenReturn(true);
    when(newArmor.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_SOFT);

    CyberpunkArmor oldArmor1 = mock(CyberpunkArmor.class);
    when(oldArmor1.isCovering(BodyLocation.HEAD)).thenReturn(true);
    when(oldArmor1.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_SOFT);

    CyberpunkArmor oldArmor2 = mock(CyberpunkArmor.class);
    when(oldArmor2.isCovering(BodyLocation.HEAD)).thenReturn(true);
    when(oldArmor2.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_SOFT);

    CyberpunkArmor oldArmor3 = mock(CyberpunkArmor.class);
    when(oldArmor3.isCovering(BodyLocation.HEAD)).thenReturn(true);
    when(oldArmor3.getArmorType()).thenReturn(CyberpunkArmor.ARMOR_TYPE_SOFT);

    ArmorManager manager = new ArmorManager();
    manager.add(oldArmor1);
    manager.add(oldArmor2);
    manager.add(oldArmor3);

    assertFalse(manager.add(newArmor));
  }

  @Test
  public void Should_ReturnTrue_When_RemovingArmor_While_ArmorIsTrackedByManager() {
    CyberpunkArmor mockArmor = mock(CyberpunkArmor.class);
    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor);

    assertTrue(manager.remove(mockArmor));
  }

  @Test
  public void Should_ReturnFalse_When_RemovingArmor_While_ArmorIsNotTrackedByManager() {
    CyberpunkArmor mockArmor = mock(CyberpunkArmor.class);
    ArmorManager manager = new ArmorManager();

    assertFalse(manager.remove(mockArmor));
  }

  @Test
  public void Should_ReturnDurabilityAsSix_When_TheDifferenceBetweenTwoArmorsDurabilityIsZero_While_BothArmorsHaveADurabilityOfOneAndTheyCoverTheSameLocation() {
    BodyLocation location = BodyLocation.HEAD;

    CyberpunkArmor mockArmor1 = mock(CyberpunkArmor.class);
    when(mockArmor1.getDurabilityAt(location)).thenReturn(1);
    when(mockArmor1.isCovering(location)).thenReturn(true);

    CyberpunkArmor mockArmor2 = mock(CyberpunkArmor.class);
    when(mockArmor2.getDurabilityAt(location)).thenReturn(1);
    when(mockArmor2.isCovering(location)).thenReturn(true);

    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor1);
    manager.add(mockArmor2);

    assertEquals(6, manager.getDurabilityAt(location));
  }

  @Test
  public void Should_ReturnDurabilityAsSeven_When_TheDifferenceBetweenTwoArmorsDurabilityIsOne_While_OneArmorHasADurabilityOfOneAndTheOtherArmorHasADurabilityOfTwoAndTheyCoverTheSameLocation() {
    BodyLocation location = BodyLocation.HEAD;

    CyberpunkArmor mockArmor1 = mock(CyberpunkArmor.class);
    when(mockArmor1.getDurabilityAt(location)).thenReturn(1);
    when(mockArmor1.isCovering(location)).thenReturn(true);

    CyberpunkArmor mockArmor2 = mock(CyberpunkArmor.class);
    when(mockArmor2.getDurabilityAt(location)).thenReturn(2);
    when(mockArmor2.isCovering(location)).thenReturn(true);

    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor1);
    manager.add(mockArmor2);

    assertEquals(7, manager.getDurabilityAt(location));
  }

  @Test
  public void Should_ReturnDurabilityAsTen_When_TheDifferenceBetweenTwoArmorsDurabilityIsFour_While_OneArmorHasADurabilityOfOneAndTheOtherArmorHasADurabilityOfFiveAndTheyCoverTheSameLocation() {
    BodyLocation location = BodyLocation.HEAD;

    CyberpunkArmor mockArmor1 = mock(CyberpunkArmor.class);
    when(mockArmor1.getDurabilityAt(location)).thenReturn(1);
    when(mockArmor1.isCovering(location)).thenReturn(true);

    CyberpunkArmor mockArmor2 = mock(CyberpunkArmor.class);
    when(mockArmor2.getDurabilityAt(location)).thenReturn(5);
    when(mockArmor2.isCovering(location)).thenReturn(true);

    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor1);
    manager.add(mockArmor2);

    assertEquals(10, manager.getDurabilityAt(location));
  }

  @Test
  public void Should_ReturnDurabilityAsTen_When_TheDifferenceBetweenTwoArmorsDurabilityIsFive_While_OneArmorHasADurabilityOfOneAndTheOtherArmorHasADurabilityOfSixAndTheyCoverTheSameLocation() {
    BodyLocation location = BodyLocation.HEAD;

    CyberpunkArmor mockArmor1 = mock(CyberpunkArmor.class);
    when(mockArmor1.getDurabilityAt(location)).thenReturn(1);
    when(mockArmor1.isCovering(location)).thenReturn(true);

    CyberpunkArmor mockArmor2 = mock(CyberpunkArmor.class);
    when(mockArmor2.getDurabilityAt(location)).thenReturn(6);
    when(mockArmor2.isCovering(location)).thenReturn(true);

    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor1);
    manager.add(mockArmor2);

    assertEquals(10, manager.getDurabilityAt(location));
  }

  @Test
  public void Should_ReturnDurabilityAsEleven_When_TheDifferenceBetweenTwoArmorsDurabilityIsSix_While_OneArmorHasADurabilityOfOneAndTheOtherArmorHasADurabilityOfSevenAndTheyCoverTheSameLocation() {
    BodyLocation location = BodyLocation.HEAD;

    CyberpunkArmor mockArmor1 = mock(CyberpunkArmor.class);
    when(mockArmor1.getDurabilityAt(location)).thenReturn(1);
    when(mockArmor1.isCovering(location)).thenReturn(true);

    CyberpunkArmor mockArmor2 = mock(CyberpunkArmor.class);
    when(mockArmor2.getDurabilityAt(location)).thenReturn(7);
    when(mockArmor2.isCovering(location)).thenReturn(true);

    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor1);
    manager.add(mockArmor2);

    assertEquals(11, manager.getDurabilityAt(location));
  }

  @Test
  public void Should_ReturnDurabilityAsThirteen_When_TheDifferenceBetweenTwoArmorsDurabilityIsEight_While_OneArmorHasADurabilityOfOneAndTheOtherArmorHasADurabilityOfNineAndTheyCoverTheSameLocation() {
    BodyLocation location = BodyLocation.HEAD;

    CyberpunkArmor mockArmor1 = mock(CyberpunkArmor.class);
    when(mockArmor1.getDurabilityAt(location)).thenReturn(1);
    when(mockArmor1.isCovering(location)).thenReturn(true);

    CyberpunkArmor mockArmor2 = mock(CyberpunkArmor.class);
    when(mockArmor2.getDurabilityAt(location)).thenReturn(9);
    when(mockArmor2.isCovering(location)).thenReturn(true);

    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor1);
    manager.add(mockArmor2);

    assertEquals(13, manager.getDurabilityAt(location));
  }

  @Test
  public void Should_ReturnDurabilityAsThirteen_When_TheDifferenceBetweenTwoArmorsDurabilityIsNine_While_OneArmorHasADurabilityOfOneAndTheOtherArmorHasADurabilityOfTenAndTheyCoverTheSameLocation() {
    BodyLocation location = BodyLocation.HEAD;

    CyberpunkArmor mockArmor1 = mock(CyberpunkArmor.class);
    when(mockArmor1.getDurabilityAt(location)).thenReturn(1);
    when(mockArmor1.isCovering(location)).thenReturn(true);

    CyberpunkArmor mockArmor2 = mock(CyberpunkArmor.class);
    when(mockArmor2.getDurabilityAt(location)).thenReturn(10);
    when(mockArmor2.isCovering(location)).thenReturn(true);

    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor1);
    manager.add(mockArmor2);

    assertEquals(13, manager.getDurabilityAt(location));
  }

  @Test
  public void Should_ReturnDurabilityAsFourteen_When_TheDifferenceBetweenTwoArmorsDurabilityIsTen_While_OneArmorHasADurabilityOfOneAndTheOtherArmorHasADurabilityOfElevenAndTheyCoverTheSameLocation() {
    BodyLocation location = BodyLocation.HEAD;

    CyberpunkArmor mockArmor1 = mock(CyberpunkArmor.class);
    when(mockArmor1.getDurabilityAt(location)).thenReturn(1);
    when(mockArmor1.isCovering(location)).thenReturn(true);

    CyberpunkArmor mockArmor2 = mock(CyberpunkArmor.class);
    when(mockArmor2.getDurabilityAt(location)).thenReturn(11);
    when(mockArmor2.isCovering(location)).thenReturn(true);

    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor1);
    manager.add(mockArmor2);

    assertEquals(14, manager.getDurabilityAt(location));
  }

  @Test
  public void Should_ReturnDurabilityAsEighteen_When_TheDifferenceBetweenTwoArmorsDurabilityIsFourteen_While_OneArmorHasADurabilityOfOneAndTheOtherArmorHasADurabilityOfFifteenAndTheyCoverTheSameLocation() {
    BodyLocation location = BodyLocation.HEAD;

    CyberpunkArmor mockArmor1 = mock(CyberpunkArmor.class);
    when(mockArmor1.getDurabilityAt(location)).thenReturn(1);
    when(mockArmor1.isCovering(location)).thenReturn(true);

    CyberpunkArmor mockArmor2 = mock(CyberpunkArmor.class);
    when(mockArmor2.getDurabilityAt(location)).thenReturn(15);
    when(mockArmor2.isCovering(location)).thenReturn(true);

    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor1);
    manager.add(mockArmor2);

    assertEquals(18, manager.getDurabilityAt(location));
  }

  @Test
  public void Should_ReturnDurabilityAsEighteen_When_TheDifferenceBetweenTwoArmorsDurabilityIsFifteen_While_OneArmorHasADurabilityOfOneAndTheOtherArmorHasADurabilityOfSixteenAndTheyCoverTheSameLocation() {
    BodyLocation location = BodyLocation.HEAD;

    CyberpunkArmor mockArmor1 = mock(CyberpunkArmor.class);
    when(mockArmor1.getDurabilityAt(location)).thenReturn(1);
    when(mockArmor1.isCovering(location)).thenReturn(true);

    CyberpunkArmor mockArmor2 = mock(CyberpunkArmor.class);
    when(mockArmor2.getDurabilityAt(location)).thenReturn(16);
    when(mockArmor2.isCovering(location)).thenReturn(true);

    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor1);
    manager.add(mockArmor2);

    assertEquals(18, manager.getDurabilityAt(location));
  }

  @Test
  public void Should_ReturnDurabilityAsNineteen_When_TheDifferenceBetweenTwoArmorsDurabilityIsSixteen_While_OneArmorHasADurabilityOfOneAndTheOtherArmorHasADurabilityOfSeventeenAndTheyCoverTheSameLocation() {
    BodyLocation location = BodyLocation.HEAD;

    CyberpunkArmor mockArmor1 = mock(CyberpunkArmor.class);
    when(mockArmor1.getDurabilityAt(location)).thenReturn(1);
    when(mockArmor1.isCovering(location)).thenReturn(true);

    CyberpunkArmor mockArmor2 = mock(CyberpunkArmor.class);
    when(mockArmor2.getDurabilityAt(location)).thenReturn(17);
    when(mockArmor2.isCovering(location)).thenReturn(true);

    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor1);
    manager.add(mockArmor2);

    assertEquals(19, manager.getDurabilityAt(location));
  }

  @Test
  public void Should_ReturnDurabilityAsTwentyThree_When_TheDifferenceBetweenTwoArmorsDurabilityIsTwenty_While_OneArmorHasADurabilityOfOneAndTheOtherArmorHasADurabilityOfTwentyOneAndTheyCoverTheSameLocation() {
    BodyLocation location = BodyLocation.HEAD;

    CyberpunkArmor mockArmor1 = mock(CyberpunkArmor.class);
    when(mockArmor1.getDurabilityAt(location)).thenReturn(1);
    when(mockArmor1.isCovering(location)).thenReturn(true);

    CyberpunkArmor mockArmor2 = mock(CyberpunkArmor.class);
    when(mockArmor2.getDurabilityAt(location)).thenReturn(21);
    when(mockArmor2.isCovering(location)).thenReturn(true);

    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor1);
    manager.add(mockArmor2);

    assertEquals(23, manager.getDurabilityAt(location));
  }

  @Test
  public void Should_ReturnDurabilityAsTwentyThree_When_TheDifferenceBetweenTwoArmorsDurabilityIsTwentyOne_While_OneArmorHasADurabilityOfOneAndTheOtherArmorHasADurabilityOfTwentyTwoAndTheyCoverTheSameLocation() {
    BodyLocation location = BodyLocation.HEAD;

    CyberpunkArmor mockArmor1 = mock(CyberpunkArmor.class);
    when(mockArmor1.getDurabilityAt(location)).thenReturn(1);
    when(mockArmor1.isCovering(location)).thenReturn(true);

    CyberpunkArmor mockArmor2 = mock(CyberpunkArmor.class);
    when(mockArmor2.getDurabilityAt(location)).thenReturn(22);
    when(mockArmor2.isCovering(location)).thenReturn(true);

    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor1);
    manager.add(mockArmor2);

    assertEquals(23, manager.getDurabilityAt(location));
  }

  @Test
  public void Should_ReturnDurabilityAsTwentyFour_When_TheDifferenceBetweenTwoArmorsDurabilityIsTwentyTwo_While_OneArmorHasADurabilityOfOneAndTheOtherArmorHasADurabilityOfTwentyThreeAndTheyCoverTheSameLocation() {
    BodyLocation location = BodyLocation.HEAD;

    CyberpunkArmor mockArmor1 = mock(CyberpunkArmor.class);
    when(mockArmor1.getDurabilityAt(location)).thenReturn(1);
    when(mockArmor1.isCovering(location)).thenReturn(true);

    CyberpunkArmor mockArmor2 = mock(CyberpunkArmor.class);
    when(mockArmor2.getDurabilityAt(location)).thenReturn(23);
    when(mockArmor2.isCovering(location)).thenReturn(true);

    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor1);
    manager.add(mockArmor2);

    assertEquals(24, manager.getDurabilityAt(location));
  }

  @Test
  public void Should_ReturnDurabilityAsTwentyEight_When_TheDifferenceBetweenTwoArmorsDurabilityIsTwentySix_While_OneArmorHasADurabilityOfOneAndTheOtherArmorHasADurabilityOfTwentySevenAndTheyCoverTheSameLocation() {
    BodyLocation location = BodyLocation.HEAD;

    CyberpunkArmor mockArmor1 = mock(CyberpunkArmor.class);
    when(mockArmor1.getDurabilityAt(location)).thenReturn(1);
    when(mockArmor1.isCovering(location)).thenReturn(true);

    CyberpunkArmor mockArmor2 = mock(CyberpunkArmor.class);
    when(mockArmor2.getDurabilityAt(location)).thenReturn(27);
    when(mockArmor2.isCovering(location)).thenReturn(true);

    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor1);
    manager.add(mockArmor2);

    assertEquals(28, manager.getDurabilityAt(location));
  }

  @Test
  public void Should_ReturnDurabilityAsTwentyEight_When_TheDifferenceBetweenTwoArmorsDurabilityIsTwentySeven_While_OneArmorHasADurabilityOfOneAndTheOtherArmorHasADurabilityOfTwentyEightAndTheyCoverTheSameLocation() {
    BodyLocation location = BodyLocation.HEAD;

    CyberpunkArmor mockArmor1 = mock(CyberpunkArmor.class);
    when(mockArmor1.getDurabilityAt(location)).thenReturn(1);
    when(mockArmor1.isCovering(location)).thenReturn(true);

    CyberpunkArmor mockArmor2 = mock(CyberpunkArmor.class);
    when(mockArmor2.getDurabilityAt(location)).thenReturn(28);
    when(mockArmor2.isCovering(location)).thenReturn(true);

    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor1);
    manager.add(mockArmor2);

    assertEquals(28, manager.getDurabilityAt(location));
  }

  @Test
  public void Should_ReturnDurabilityAsTwentyNine_When_TheDifferenceBetweenTwoArmorsDurabilityIsTwentyEight_While_OneArmorHasADurabilityOfOneAndTheOtherArmorHasADurabilityOfTwentyNineAndTheyCoverTheSameLocation() {
    BodyLocation location = BodyLocation.HEAD;

    CyberpunkArmor mockArmor1 = mock(CyberpunkArmor.class);
    when(mockArmor1.getDurabilityAt(location)).thenReturn(1);
    when(mockArmor1.isCovering(location)).thenReturn(true);

    CyberpunkArmor mockArmor2 = mock(CyberpunkArmor.class);
    when(mockArmor2.getDurabilityAt(location)).thenReturn(29);
    when(mockArmor2.isCovering(location)).thenReturn(true);

    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor1);
    manager.add(mockArmor2);

    assertEquals(29, manager.getDurabilityAt(location));
  }

  @Test
  public void Should_ReturnDurabilityAsOne_When_OneArmorHasDurabilityOfOneAndAnotherArmorHasDurabilityOfZeroAndTheyCoverTheSameLocation() {
    BodyLocation location = BodyLocation.HEAD;

    CyberpunkArmor mockArmor1 = mock(CyberpunkArmor.class);
    when(mockArmor1.getDurabilityAt(location)).thenReturn(1);
    when(mockArmor1.isCovering(location)).thenReturn(true);

    CyberpunkArmor mockArmor2 = mock(CyberpunkArmor.class);
    when(mockArmor2.getDurabilityAt(location)).thenReturn(0);
    when(mockArmor2.isCovering(location)).thenReturn(true);

    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor1);
    manager.add(mockArmor2);

    assertEquals(1, manager.getDurabilityAt(location));
  }

  @Test
  public void Should_ReturnEncumbrancePenaltyAsZero_When_ThereAreZeroArmorsLayered() {
    ArmorManager manager = new ArmorManager();

    assertEquals(0, manager.getEncumbrancePenalty());
  }

  @Test
  public void Should_ReturnEncumbrancePenaltyAsZero_When_ThereIsOneArmorLayered_While_ArmorHasABaseEncumbranceOfZeroAndCoversAtLeastOneLocation() {
    CyberpunkArmor mockArmor = mock(CyberpunkArmor.class);
    when(mockArmor.isCovering(BodyLocation.HEAD)).thenReturn(true);
    when(mockArmor.getEncumbranceValue()).thenReturn(0);
    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor);

    assertEquals(0, manager.getEncumbrancePenalty());
  }

  @Test
  public void Should_ReturnEncumbrancePenaltyAsOne_When_ThereAreTwoArmorsLayered_While_BothArmorsHaveABaseEncumbranceOfZeroAndCoverTheSameLocation() {
    BodyLocation location = BodyLocation.HEAD;

    CyberpunkArmor mockArmor1 = mock(CyberpunkArmor.class);
    when(mockArmor1.isCovering(location)).thenReturn(true);
    when(mockArmor1.getEncumbranceValue()).thenReturn(0);

    CyberpunkArmor mockArmor2 = mock(CyberpunkArmor.class);
    when(mockArmor2.isCovering(location)).thenReturn(true);
    when(mockArmor2.getEncumbranceValue()).thenReturn(0);

    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor1);
    manager.add(mockArmor2);

    assertEquals(1, manager.getEncumbrancePenalty());
  }

  @Test
  public void Should_ReturnEncumbrancePenaltyAsThree_When_ThereAreThreeArmorsLayered_While_AllArmorsHaveABaseEncumbranceOfZeroAndCoverTheSameLocation() {
    BodyLocation location = BodyLocation.HEAD;

    CyberpunkArmor mockArmor1 = mock(CyberpunkArmor.class);
    when(mockArmor1.isCovering(location)).thenReturn(true);
    when(mockArmor1.getEncumbranceValue()).thenReturn(0);

    CyberpunkArmor mockArmor2 = mock(CyberpunkArmor.class);
    when(mockArmor2.isCovering(location)).thenReturn(true);
    when(mockArmor2.getEncumbranceValue()).thenReturn(0);

    CyberpunkArmor mockArmor3 = mock(CyberpunkArmor.class);
    when(mockArmor3.isCovering(location)).thenReturn(true);
    when(mockArmor3.getEncumbranceValue()).thenReturn(0);

    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor1);
    manager.add(mockArmor2);
    manager.add(mockArmor3);

    assertEquals(3, manager.getEncumbrancePenalty());
  }

  @Test
  public void Should_ReturnEncumbrancePenaltyAsFour_When_TwoArmorsAreEquipped_While_OneArmorCoversTorsoRightLegLeftLegWithAnEncumbranceValueOfTwoAndAnotherArmorCoversTorsoAndWithEncumbranceValueOfOne() {
    CyberpunkArmor mockShirt = mock(CyberpunkArmor.class);
    when(mockShirt.isCovering(BodyLocation.TORSO)).thenReturn(true);
    when(mockShirt.isCovering(BodyLocation.RIGHT_ARM)).thenReturn(true);
    when(mockShirt.isCovering(BodyLocation.LEFT_ARM)).thenReturn(true);
    when(mockShirt.getEncumbranceValue()).thenReturn(2);

    CyberpunkArmor mockVest = mock(CyberpunkArmor.class);
    when(mockVest.isCovering(BodyLocation.TORSO)).thenReturn(true);
    when(mockVest.getEncumbranceValue()).thenReturn(1);

    ArmorManager manager = new ArmorManager();
    manager.add(mockShirt);
    manager.add(mockVest);

    assertEquals(4, manager.getEncumbrancePenalty());
  }

  @Test
  public void Should_ReturnWeightAsTwo_When_TwoArmorsAreEquipped_While_BothArmorsWeighOne() {
    CyberpunkArmor mockArmor1 = mock(CyberpunkArmor.class);
    when(mockArmor1.getWeight()) //
        .thenReturn(new Measurement( //
            Measurement.Type.MASS, //
            1.0, //
            Measurement.Unit.KILOGRAM));
    CyberpunkArmor mockArmor2 = mock(CyberpunkArmor.class);
    when(mockArmor2.getWeight()) //
        .thenReturn(new Measurement( //
            Measurement.Type.MASS, //
            1.0, //
            Measurement.Unit.KILOGRAM));
    ArmorManager manager = new ArmorManager();
    manager.add(mockArmor1);
    manager.add(mockArmor2);

    Measurement weight = manager.getTotalWeight();
    assertEquals(2.0, weight.getAmount(), 0.0);
  }

}
