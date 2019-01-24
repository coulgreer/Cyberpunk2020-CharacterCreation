package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import rpg.cyberpunk._2020.AttributeName;
import rpg.general.stats.Attribute;

public class SkillManagerTest {
	@Test
	public void testSkillLevelIncreasedForLeaf() {
		AttributeManager mockedAttributeManager = mock(AttributeManager.class);
		Attribute mockedAttribute = mock(CyberpunkAttribute.class);
		when(mockedAttribute.getModifier()).thenReturn(2);
		when(mockedAttributeManager.get(any(AttributeName.class))).thenReturn(mockedAttribute);
		SkillManager skillManager = new SkillManager(mockedAttributeManager);

		skillManager.increaseSkill(CyberpunkSkill.ACCOUNTING);

		assertEquals(3, skillManager.getTotalValue(CyberpunkSkill.ACCOUNTING));
	}

	@Test
	public void testSkillLevelIncreasedForBranchAndChildren() {
		AttributeManager mockedAttributeManager = mock(AttributeManager.class);
		Attribute mockedAttribute = mock(CyberpunkAttribute.class);
		when(mockedAttribute.getModifier()).thenReturn(2);
		when(mockedAttributeManager.get(any(AttributeName.class))).thenReturn(mockedAttribute);
		SkillManager skillManager = new SkillManager(mockedAttributeManager);

		skillManager.increaseSkill(CyberpunkSkill.ATTRACTIVENESS_SKILLS);

		assertEquals(6, skillManager.getTotalValue(CyberpunkSkill.ATTRACTIVENESS_SKILLS));
	}
}
