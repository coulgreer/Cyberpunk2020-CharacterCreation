package rpg.util;

import static org.junit.Assert.*;

import org.junit.Test;

import rpg.util.Die;

public class DieTest {

	@Test
	public void testToString() {
		Die die;

		die = new Die(4, 20);
		assertEquals("4D20", die.toString());

		die = new Die(4, 20, 1);
		assertEquals("4D20", die.toString());

		die = new Die(4, 20, 2);
		assertEquals("4D20/2", die.toString());
	}
}
