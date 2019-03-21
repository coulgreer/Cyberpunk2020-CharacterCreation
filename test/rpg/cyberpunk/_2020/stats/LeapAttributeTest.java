package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import rpg.general.stats.Attribute;

public class LeapAttributeTest {
	private Attribute mockAttribute;

	@Before
	public void setUp() {
		mockAttribute = mock(Attribute.class);
		when(mockAttribute.getModifier()).thenReturn(10);
	}

	@Test
	public void testLeapLevelEqualsTwoIfParentAttributeModifierEqualsTen() {
		LeapAttribute attribute = new LeapAttribute("Leaping", "Attribute for Leaping", mockAttribute);

		assertEquals(2, attribute.getLevel());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testExceptionThrownIfIncreaseLevel() {
		LeapAttribute attribute = new LeapAttribute("Leaping", "Attribute for Leaping", mockAttribute);

		attribute.increaseLevel();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testExceptionThrownIfDecreaseLevel() {
		LeapAttribute attribute = new LeapAttribute("Leaping", "Attribute for Leaping", mockAttribute);

		attribute.decreaseLevel();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testExceptionThrownIfResetLevel() {
		LeapAttribute attribute = new LeapAttribute("Leaping", "Attribute for Leaping", mockAttribute);

		attribute.resetLevel();
	}

}
