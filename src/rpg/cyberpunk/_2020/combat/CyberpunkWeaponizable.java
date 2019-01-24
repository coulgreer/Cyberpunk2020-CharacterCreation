package rpg.cyberpunk._2020.combat;

public interface CyberpunkWeaponizable {
	public enum Availability {
		UNAVAILABLE("N/A"), //
		EXCELLENT("E"), //
		COMMON("C"), //
		POOR("P"), //
		RARE("R");

		private String abbreviation;

		Availability(String abbreviation) {
			this.abbreviation = abbreviation;
		}

		public String getAbbreviation() {
			return abbreviation;
		}
	}

	public enum Reliability {
		VERY_RELIABLE("VR"), STANDARD("ST"), UNRELIABLE("UR");

		private String abbreviation;

		Reliability(String abbreviation) {
			this.abbreviation = abbreviation;
		}

		public String getAbbreviation() {
			return abbreviation;
		}
	}

	public enum Concealability {
		POCKET("P"), //
		JACKET("J"), //
		LONG_COAT("L"), //
		CANNOT_HIDE("N");

		private String abbreviation;

		Concealability(String abbreviation) {
			this.abbreviation = abbreviation;
		}

		public String getAbbreviation() {
			return abbreviation;
		}
	}

	public Concealability getConcealability();

	public Availability getAvailability();

	public Reliability getReliability();
}
