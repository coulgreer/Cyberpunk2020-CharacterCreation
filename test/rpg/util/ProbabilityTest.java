package rpg.util;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rpg.util.Die;
import rpg.util.Probability;

public class ProbabilityTest {
	private Die die;

	@Before
	public void setUp() {
		die = new Die(1, 10);
	}

	@After
	public void tearDown() {
		die = null;
	}

	@Test
	public void testGetDice() {
		Probability dmg = new Probability(die, 0);
		assertEquals(die, dmg.getDice());
	}

	@Test
	public void testGetModifier() {
		int expectedModifier = 4;

		Probability dmg = new Probability(die, expectedModifier);
		assertEquals(expectedModifier, dmg.getModifier());
		
		expectedModifier = 1;
		dmg.setModifier(expectedModifier);
		assertEquals(expectedModifier, dmg.getModifier());
	}

	@Test
	public void testToString() {
		Probability dmg = new Probability(die, 2);
		assertEquals("1D10+2", dmg.toString());
	}
}
