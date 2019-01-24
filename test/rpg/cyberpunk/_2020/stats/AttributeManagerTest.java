package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import rpg.cyberpunk._2020.AttributeName;
import rpg.general.stats.Attribute;

public class AttributeManagerTest {
	@Test
	public void testExistingAttributeNameReturnsAnInstanceOfAbstractAttribute() {
		AttributeManager manager = new AttributeManager();
		assertTrue(manager.get(AttributeName.ATTRACTIVENESS) instanceof Attribute);
	}

	@Test
	public void testNonexistantAttributeNameReturnsNullCyberpunkAttribute() {
		AttributeManager manager = new AttributeManager();

		assertEquals(NullCyberpunkAttribute.getInstance(), manager.get(null));
	}

	@Test
	public void testExistingAttributeNameReturnsLevel() {
		AttributeManager manager = new AttributeManager();

		assertEquals(CyberpunkAttribute.MIN_LEVEL, manager.getLevel(AttributeName.ATTRACTIVENESS));
	}

	@Test
	public void testNonexistingAttributeNameReturnsLevelLessThanMinLevel() {
		AttributeManager manager = new AttributeManager();

		assertTrue(CyberpunkAttribute.MIN_LEVEL > manager.getLevel(null));
	}
}
