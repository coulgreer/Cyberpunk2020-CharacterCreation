package rpg.general.combat;

import java.util.EnumSet;
import java.util.Iterator;

public enum BodyLocation {
	HEAD, TORSO, LEFT_ARM, RIGHT_ARM, LEFT_LEG, RIGHT_LEG;

	public static Iterator<BodyLocation> createIterator() {
		return EnumSet.allOf(BodyLocation.class).iterator();
	}
}
