package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import rpg.general.stats.Attribute;

public class RunAttributeTest {
	private Attribute mockAttribute;

	@Before
	public void setUp() {
		mockAttribute = mock(Attribute.class);
		when(mockAttribute.getModifier()).thenReturn(10);
	}

	@Test
	public void testRunLevelEqualsThirtyIfParentAttributeModifierEqualsTen() {
		RunAttribute attribute = new RunAttribute("Running", "Attribute for Running", mockAttribute);

		assertEquals(30, attribute.getLevel());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testExceptionThrownIfIncreaseLevel() {
		RunAttribute attribute = new RunAttribute("Running", "Attribute for Running", mockAttribute);

		attribute.increaseLevel();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testExceptionThrownIfDecreaseLevel() {
		RunAttribute attribute = new RunAttribute("Running", "Attribute for Running", mockAttribute);

		attribute.decreaseLevel();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testExceptionThrownIfResetLevel() {
		RunAttribute attribute = new RunAttribute("Running", "Attribute for Running", mockAttribute);

		attribute.resetLevel();
	}

}
