package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import rpg.general.stats.Attribute;

public class CarryAttributeTest {
	private Attribute mockAttribute;

	@Before
	public void setUp() {
		mockAttribute = mock(Attribute.class);
		when(mockAttribute.getModifier()).thenReturn(10);
	}

	@Test
	public void testCarryLevelEqualsOneHundredIfParentAttributeModifierEqualsTen() {
		CarryAttribute attribute = new CarryAttribute("Carrying", "Attribute for Carrying", mockAttribute);

		assertEquals(100, attribute.getLevel());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testExceptionThrownIfIncreaseLevel() {
		CarryAttribute attribute = new CarryAttribute("Carrying", "Attribute for Carrying", mockAttribute);

		attribute.increaseLevel();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testExceptionThrownIfDecreaseLevel() {
		CarryAttribute attribute = new CarryAttribute("Carrying", "Attribute for Carrying", mockAttribute);

		attribute.decreaseLevel();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testExceptionThrownIfResetLevel() {
		CarryAttribute attribute = new CarryAttribute("Carrying", "Attribute for Carrying", mockAttribute);

		attribute.resetLevel();
	}

}
