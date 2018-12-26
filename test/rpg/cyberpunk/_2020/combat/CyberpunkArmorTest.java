package rpg.cyberpunk._2020.combat;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import rpg.cyberpunk._2020.combat.CyberpunkArmor.ArmorType;
import rpg.general.combat.BodyLocation;

public class CyberpunkArmorTest {
	private String name;
	private String description;
	private double cost;
	private double weight;
	private List<BodyLocation> areCovered;
	private Iterator<BodyLocation> iterator;
	private ArmorType armorType;
	private int stoppingPower;
	private int encumbranceValue;

	@Before
	public void setUp() {
		name = "Test Armor";
		description = "This is simple test armor";
		cost = 200.0;
		weight = 10.0;
		areCovered = new ArrayList<BodyLocation>();
		areCovered.add(BodyLocation.TORSO);
		areCovered.add(BodyLocation.LEFT_ARM);
		areCovered.add(BodyLocation.RIGHT_ARM);
		iterator = areCovered.iterator();
		armorType = ArmorType.SOFT;
		stoppingPower = 18;
		encumbranceValue = 1;
	}

	@Test
	public void testDamagingTheArmor() {
		CyberpunkArmor armor = new CyberpunkArmor(name, description, cost, weight, iterator, armorType, stoppingPower,
				encumbranceValue);

		armor.damage(BodyLocation.TORSO, 1);
		armor.damage(BodyLocation.LEFT_ARM, 5);
		armor.damage(BodyLocation.RIGHT_ARM, 3);

		assertEquals(0, armor.getDurability(BodyLocation.HEAD));
		assertEquals(17, armor.getDurability(BodyLocation.TORSO));
		assertEquals(13, armor.getDurability(BodyLocation.LEFT_ARM));
		assertEquals(15, armor.getDurability(BodyLocation.RIGHT_ARM));
		assertEquals(0, armor.getDurability(BodyLocation.LEFT_LEG));
		assertEquals(0, armor.getDurability(BodyLocation.RIGHT_LEG));
	}

	@Test
	public void testArmorDoesNotGoBelowZeroDurability() {
		CyberpunkArmor armor = new CyberpunkArmor(name, description, cost, weight, iterator, armorType, stoppingPower,
				encumbranceValue);
		armor.damage(BodyLocation.TORSO, stoppingPower + 1);

		assertEquals(0, armor.getDurability(BodyLocation.HEAD));
		assertEquals(0, armor.getDurability(BodyLocation.TORSO));
		assertEquals(18, armor.getDurability(BodyLocation.LEFT_ARM));
		assertEquals(18, armor.getDurability(BodyLocation.RIGHT_ARM));
		assertEquals(0, armor.getDurability(BodyLocation.LEFT_LEG));
		assertEquals(0, armor.getDurability(BodyLocation.RIGHT_LEG));
	}

	@Test
	public void testArmorGetsDamagedThenRepairedToMaxDurability() {
		CyberpunkArmor armor = new CyberpunkArmor(name, description, cost, weight, iterator, armorType, stoppingPower,
				encumbranceValue);
		armor.damage(BodyLocation.TORSO, 1);
		armor.damage(BodyLocation.LEFT_ARM, 5);
		armor.damage(BodyLocation.RIGHT_ARM, 3);
		armor.repair();

		assertEquals(0, armor.getDurability(BodyLocation.HEAD));
		assertEquals(18, armor.getDurability(BodyLocation.TORSO));
		assertEquals(18, armor.getDurability(BodyLocation.LEFT_ARM));
		assertEquals(18, armor.getDurability(BodyLocation.RIGHT_ARM));
		assertEquals(0, armor.getDurability(BodyLocation.LEFT_LEG));
		assertEquals(0, armor.getDurability(BodyLocation.RIGHT_LEG));
	}
}
