package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CyberpunkAttributeTest {
	private CyberpunkAttribute attribute;

	@Before
	public void setUp() {
		attribute = new CyberpunkAttribute(CyberpunkAttribute.ATTRACTIVENESS, "This is how good-looking you are.");
	}

	@After
	public void tearDown() {
		attribute = null;
	}

	@Test
	public void testIncreaseLevelBy2() {
		attribute.increaseLevel();
		attribute.increaseLevel();
		assertEquals(4, attribute.getLevel());
		assertEquals(4, attribute.getModifier());
	}

	@Test
	public void testCannotIncreaseLevelOverMax() {
		for (int i = 0; i < CyberpunkAttribute.MAX_LEVEL + 2; i++) {
			attribute.increaseLevel();
		}

		assertEquals(10, attribute.getLevel());
		assertEquals(10, attribute.getModifier());
	}

	@Test
	public void testCannotDecreaseValueUnderMin() {
		attribute.decreaseLevel();
		attribute.decreaseLevel();
		assertEquals(2, attribute.getLevel());
		assertEquals(2, attribute.getModifier());
	}

	@Test
	public void testIncreaseLevelBy2ThenDecreaseBy1() {
		attribute.increaseLevel();
		attribute.increaseLevel();
		attribute.decreaseLevel();
		assertEquals(3, attribute.getLevel());
		assertEquals(3, attribute.getModifier());
	}

	@Test
	public void testIncreasePastInitialLevelThenResetLevel() {
		attribute.increaseLevel();
		attribute.resetLevel();
		assertEquals(2, attribute.getLevel());
		assertEquals(2, attribute.getModifier());
	}
}
