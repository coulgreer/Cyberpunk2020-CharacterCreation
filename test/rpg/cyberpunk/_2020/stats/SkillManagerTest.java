package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import rpg.Player;
import rpg.general.stats.Attribute;

public class SkillManagerTest {

	@Test
	public void testSkillTotalLevelEqualsThreeIfSkillLevelIncreasedOnce() {
		Player mockPlayer = mock(Player.class);

		Attribute mockAttribute = mock(CyberpunkAttribute.class);
		when(mockAttribute.getModifier()).thenReturn(2);

		AttributeManager mockAttributeManager = mock(AttributeManager.class);
		when(mockAttributeManager.get(any(String.class))).thenReturn(mockAttribute);

		SkillManager skillManager = new SkillManager(mockAttributeManager, mockPlayer);

		skillManager.increaseSkill(CyberpunkSkill.ACCOUNTING);

		assertEquals(3, skillManager.getTotalValue(CyberpunkSkill.ACCOUNTING));
	}

	@Test
	public void testSkillTotalLevelEqualsThreeIfSkillLevelIncreasedTwiceThenDecreasedOnce() {
		Player mockPlayer = mock(Player.class);

		Attribute mockAttribute = mock(CyberpunkAttribute.class);
		when(mockAttribute.getModifier()).thenReturn(2);

		AttributeManager mockAttributeManager = mock(AttributeManager.class);
		when(mockAttributeManager.get(any(String.class))).thenReturn(mockAttribute);

		SkillManager skillManager = new SkillManager(mockAttributeManager, mockPlayer);

		skillManager.increaseSkill(CyberpunkSkill.ACCOUNTING);
		skillManager.increaseSkill(CyberpunkSkill.ACCOUNTING);
		skillManager.decreaseSkill(CyberpunkSkill.ACCOUNTING);

		assertEquals(3, skillManager.getTotalValue(CyberpunkSkill.ACCOUNTING));
	}

	@Test
	public void testSkillReturnedIfSkillIsAddedToSkillManager() {
		Player mockPlayer = mock(Player.class);

		Attribute mockAttribute = mock(CyberpunkAttribute.class);

		AttributeManager mockAttributeManager = mock(AttributeManager.class);
		when(mockAttributeManager.get(any(String.class))).thenReturn(mockAttribute);

		CyberpunkSkill mockSkill = mock(CyberpunkSkill.class);
		when(mockSkill.getName()).thenReturn("Mock Skill");

		SkillManager skillManager = new SkillManager(mockAttributeManager, mockPlayer);

		skillManager.add(mockSkill);

		assertEquals(mockSkill, skillManager.get(mockSkill.getName()));
	}

	@Test(expected = NullPointerException.class)
	public void testNullReturnedIfSkillIsAddedThenRemovedFromSkillManager() {
		Player mockPlayer = mock(Player.class);

		Attribute mockAttribute = mock(CyberpunkAttribute.class);

		AttributeManager mockAttributeManager = mock(AttributeManager.class);
		when(mockAttributeManager.get(any(String.class))).thenReturn(mockAttribute);

		CyberpunkSkill mockSkill = mock(CyberpunkSkill.class);
		when(mockSkill.getName()).thenReturn("Mock Skill");

		SkillManager skillManager = new SkillManager(mockAttributeManager, mockPlayer);

		skillManager.add(mockSkill);
		skillManager.remove(mockSkill);
		
		skillManager.get(mockSkill.getName());
	}

}
