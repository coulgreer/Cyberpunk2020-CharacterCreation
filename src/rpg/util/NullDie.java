package rpg.util;

public class NullDie extends Die {
	private static NullDie uniqueInstance;

	private NullDie() {
		super(0, 0);
	}

	public static NullDie getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NullDie();
		}

		return uniqueInstance;
	}
}
