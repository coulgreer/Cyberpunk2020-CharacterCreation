package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import rpg.general.stats.Attribute;

public class AttributeManagerTest {

	@Test
	public void testNullIsReturnedIfNullAttributeNameIsGiven() {
		AttributeManager manager = new AttributeManager();

		assertEquals(null, manager.get(null));
	}

	@Test(expected = NullPointerException.class)
	public void testExceptionThrownIfNullNameIsGivenWhenGettingLevel() {
		AttributeManager manager = new AttributeManager();

		manager.getLevel(null);
	}

	@Test
	public void testMockAttributeReturnedIfAddedToAttributeManager() {
		Attribute mockAttribute = mock(Attribute.class);
		when(mockAttribute.getName()).thenReturn("Mock Attribute");

		AttributeManager manager = new AttributeManager();

		manager.add(mockAttribute);

		assertEquals(mockAttribute, manager.get(mockAttribute.getName()));
	}

	@Test
	public void testNullReturnedIfMockAttributeAddedThenRemoved() {
		Attribute mockAttribute = mock(Attribute.class);
		when(mockAttribute.getName()).thenReturn("Mock Attribute");

		AttributeManager manager = new AttributeManager();

		manager.add(mockAttribute);
		manager.remove(mockAttribute.getName());

		assertEquals(null, manager.get(mockAttribute.getName()));
	}

}
