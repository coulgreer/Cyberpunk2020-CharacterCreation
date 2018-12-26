package rpg.cyberpunk._2020;

public enum AttributeName {
	NOT_AVAILABLE("NA"), //
	INTELLIGENCE("INT"), //
	REFLEXES("REF"), //
	COOL("CL"), //
	TECHNICAL_ABILITY("TECH"), //
	LUCK("LK"), //
	ATTRACTIVENESS("ATT"), //
	MOVEMENT_ALLOWANCE("MA"), //
	RUN("RUN"), //
	LEAP("LP"), //
	EMPATHY("EMP"), //
	BODY_TYPE("BT"), //
	CARRY("CAR");

	private String abbreviation;

	AttributeName(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getAbbreviation() {
		return abbreviation;
	}
}
