package rpg.cyberpunk._2020.stats;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GeneralCyberpunkSkillTest {
	private static CyberpunkAttribute mockedAttribute;

	private BroadSkill generalSkill;

	@BeforeClass
	public static void setUpBeforeClass() {
		mockedAttribute = mock(CyberpunkAttribute.class);
		when(mockedAttribute.getModifier()).thenReturn(2);
	}

	@Before
	public void setUp() {
		generalSkill = new BroadSkill(CyberpunkSkill.MARTIAL_ART, "A parent skill.");
		generalSkill.add(new SpecializedSkill(mockedAttribute, CyberpunkSkill.AIKIDO, "", 1));
		generalSkill.add(new SpecializedSkill(mockedAttribute, CyberpunkSkill.ANIMAL_KUNG_FU, "", 1));
		generalSkill.add(new SpecializedSkill(mockedAttribute, CyberpunkSkill.CAPOERIA, "", 1));
		generalSkill.add(new SpecializedSkill(mockedAttribute, CyberpunkSkill.JUDO, "", 1));
		generalSkill.add(new SpecializedSkill(mockedAttribute, CyberpunkSkill.KARATE, "", 1));
	}

	@After
	public void tearDown() {
		generalSkill = null;
	}

	@Test
	public void testIncreaseLevelOfChildren() {
		generalSkill.increaseLevel();

		Iterator<Map.Entry<String, CyberpunkSkill>> iterator = generalSkill.getIterator();
		while (iterator.hasNext()) {
			CyberpunkSkill skill = iterator.next().getValue();
			assertEquals(1, skill.getLevel());
		}
	}

	@Test
	public void testIncreaseLevelOfChildrenBy2ThenDecreaseBy1() {
		generalSkill.increaseLevel();
		generalSkill.increaseLevel();
		generalSkill.decreaseLevel();

		Iterator<Map.Entry<String, CyberpunkSkill>> iterator = generalSkill.getIterator();
		while (iterator.hasNext()) {
			CyberpunkSkill skill = iterator.next().getValue();
			assertEquals(1, skill.getLevel());
		}
	}

	@Test
	public void testIncreaseLevelOfChildrenBy2ThenResetTo0() {
		generalSkill.increaseLevel();
		generalSkill.increaseLevel();
		generalSkill.resetLevel();

		Iterator<Map.Entry<String, CyberpunkSkill>> iterator = generalSkill.getIterator();
		while (iterator.hasNext()) {
			CyberpunkSkill skill = iterator.next().getValue();
			assertEquals(0, skill.getLevel());
		}
	}

	@Test
	public void testIncreaseImprovementPointsOfChildrenBy2() {
		generalSkill.increaseCurrentImprovementPoints(2);

		Iterator<Map.Entry<String, CyberpunkSkill>> iterator = generalSkill.getIterator();
		while (iterator.hasNext()) {
			CyberpunkSkill skill = iterator.next().getValue();
			assertEquals(2, skill.getCurrentImprovementPoints());
		}
	}

	@Test
	public void testGettingNonexistantChildReturnsNullCyberpunkSkill() {
		assertEquals(NullCyberpunkSkill.getInstance(), generalSkill.getChild(null));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testUnsupportedOperationExceptionThrownByGetLevel() {
		generalSkill.getLevel();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testUnsupportedOperationExceptionThrownByGetCurrentImprovementPoints() {
		generalSkill.getCurrentImprovementPoints();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testUnsupportedOperationExceptionThrownByGetNeededImprovementPoints() {
		generalSkill.getNeededImprovementPoints();
	}
}
