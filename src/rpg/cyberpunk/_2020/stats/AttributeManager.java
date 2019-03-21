package rpg.cyberpunk._2020.stats;

import java.util.HashMap;
import java.util.Map;

import rpg.general.stats.Attribute;
import rpg.general.stats.StatisticManager;

public class AttributeManager implements StatisticManager<Attribute> {
	private Map<String, Attribute> attributes;
	private Attribute intelligence;
	private Attribute reflexes;
	private Attribute cool;
	private Attribute technicalAbility;
	private Attribute luck;
	private Attribute attractiveness;
	private Attribute movementAllowance;
	private Attribute run;
	private Attribute leap;
	private Attribute empathy;
	private Attribute bodyType;
	private Attribute carry;

	/**
	 * Constructs a manager with the default attributes from the Tabletop RPG
	 * Cyberpunk 2020.
	 */
	public AttributeManager() {
		attributes = new HashMap<String, Attribute>();
		addIndependentAttributes();
		addDependentAttributes();

	}

	private void addIndependentAttributes() {
		intelligence = new CyberpunkAttribute(CyberpunkAttribute.INTELLIGENCE,
				"This is a measure of your problem solving ability; figuring out problems, noticing things, remembering information." //
						+ " Almost every character type will need a high Intelligence, with Netrunners and Corporates requiring the highest of all.");
		attributes.put(intelligence.getName(), intelligence);
		reflexes = new CyberpunkAttribute(CyberpunkAttribute.REFLEXES,
				"This is a combined index, covering not only your basic dexterity," //
						+ " but also how your level of physical coordination will affect feats of driving, piloting, fighting, and athletics." //
						+ " Characters who intend to engage in a great deal of combat (such as Solos, Nomads, or Rockerboys) should always invest in the highest possible Reflex.");
		attributes.put(reflexes.getName(), reflexes);
		cool = new CyberpunkAttribute(CyberpunkAttribute.COOL,
				"This index measures how well the character stands up to stress, fear, pressure, physical pain and/or torture." //
						+ " In determining your willingness to fight on despite wounds or your fighting ability under fire, Cool is essential." //
						+ " It is also the measure of how \"together\" your character is and how tough he appears to others."//
						+ " Rockerboys and Fixers should always have a high Cool, with Solos and Nomads having the highest of all.");
		attributes.put(cool.getName(), cool);
		technicalAbility = new CyberpunkAttribute(CyberpunkAttribute.TECHNICAL_ABILITY,
				"This is an index of how well you relate to hardware and other technically oriented things." //
						+ " In Cyberpunk, the abililty to use and repair technology is of paramount importance--TECH will be the Stat used when fixing, repairing and attempting to use unfamiliar tech." //
						+ " While all characters should have a decent Tech Stat, potential Techies should always opt for the highest possible score in this area.");
		attributes.put(technicalAbility.getName(), technicalAbility);
		luck = new CyberpunkAttribute(CyberpunkAttribute.LUCK,
				"This is the intangible \"something\" that throws the balance of events into your favor." //
						+ " Your luck represents how many points you may use each game to influence the outcome of a critical event." //
						+ " To use Luck, you may add any or all of the points of luck a character has to a critical die roll (declaring your intention to use Luck before the roll is made) until all of your Luck stat is used up." //
						+ " Luck is always restored at the end of each game session.");
		attributes.put(luck.getName(), luck);
		attractiveness = new CyberpunkAttribute(CyberpunkAttribute.ATTRACTIVENESS, "This is how good-looking you are." //
				+ " In Cyberpunk, it's not enough to be good--you have to look good while you're doing it (Attitude is Everything)." //
				+ " Attractiveness is especially important to Medias and Rockerboys, as being good-looking is part of their jobs.");
		attributes.put(attractiveness.getName(), attractiveness);
		movementAllowance = new CyberpunkAttribute(CyberpunkAttribute.MOVEMENT_ALLOWANCE,
				"This is an index of how fast your character can run (important in combat situations)." //
						+ " The higher your Movement Allowance, the more distance you can cover in a turn.");
		attributes.put(movementAllowance.getName(), movementAllowance);
		empathy = new CyberpunkAttribute(CyberpunkAttribute.EMPATHY,
				"This Stat represents how well you relate to other living things--a measure of charisma and sympathetic emotions." //
						+ " In a world of alienated, future-shocked survivors, the ability to be \"human\" can no longer be taken for granted." //
						+ " Empathy is critical when leading, convincing, seducing or perceiving emotional undercurrents." //
						+ " Empathy is also a measure of how close he/she is to the line between feeling human being and cold blooded cyber-monster.");
		attributes.put(empathy.getName(), empathy);
		bodyType = new CyberpunkAttribute(CyberpunkAttribute.BODY_TYPE,
				"Strength, Endurance and Constitution are all based on the character's Body Type." //
						+ " Body Type determines how much damage you can take in wounds, how much you can lift or carry, how far you can throw, how well you recover from shock, and how much additional damage you cause with physical attacks." //
						+ " Body Type is important to all character types, but to Solos, Rockerboys and Nomads most of all.");
		attributes.put(bodyType.getName(), bodyType);
	}

	private void addDependentAttributes() {
		run = new RunAttribute(CyberpunkAttribute.RUN,
				"To determine how far your character can run in a single combat round (@3.2 seconds) in meters, multiply your MA by 3." //
						+ " The character can run three times this distance in a full 10 second turn.",
				movementAllowance);
		attributes.put(run.getName(), run);
		leap = new LeapAttribute(CyberpunkAttribute.LEAP,
				"To determine how far your character can leap (from a running start), divide your RUN by 4.", run);
		attributes.put(leap.getName(), leap);
		carry = new CarryAttribute(CyberpunkAttribute.CARRY, "The amount of weight you can carry on your back.",
				bodyType);
		attributes.put(carry.getName(), carry);

	}

	@Override
	public void add(Attribute attribute) {
		attributes.put(attribute.getName(), attribute);
	}

	@Override
	public void remove(String name) {
		attributes.remove(name);
	}

	public Attribute getStatistic(String name) {
		return attributes.get(name);
	}

	@Override
	public String getDescription(String name) {
		Attribute attribute = attributes.get(name);
		return attribute.getDescription();
	}

	@Override
	public int getBaseLevel(String name) {
		Attribute attribute = attributes.get(name);
		return attribute.getLevel();
	}

	@Override
	public void increaseLevel(String name) {
		Attribute attribute = attributes.get(name);
		attribute.increaseLevel();
	}

	@Override
	public void decreaseLevel(String name) {
		Attribute attribute = attributes.get(name);
		attribute.decreaseLevel();
	}

	@Override
	public void resetLevel(String name) {
		Attribute attribute = attributes.get(name);
		attribute.resetLevel();
	}

}
