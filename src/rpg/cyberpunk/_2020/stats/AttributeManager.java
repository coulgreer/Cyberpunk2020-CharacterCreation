package rpg.cyberpunk._2020.stats;

import java.util.HashMap;
import java.util.Map;

import rpg.cyberpunk._2020.AttributeName;
import rpg.general.stats.AbstractAttribute;

public class AttributeManager {
	private Map<AttributeName, AbstractAttribute> attributes;
	private AbstractAttribute intelligence;
	private AbstractAttribute reflexes;
	private AbstractAttribute cool;
	private AbstractAttribute technicalAbility;
	private AbstractAttribute luck;
	private AbstractAttribute attractiveness;
	private AbstractAttribute movementAllowance;
	private AbstractAttribute run;
	private AbstractAttribute leap;
	private AbstractAttribute empathy;
	private AbstractAttribute bodyType;
	private AbstractAttribute carry;

	public AttributeManager() {
		attributes = new HashMap<AttributeName, AbstractAttribute>();
		intelligence = new CyberpunkAttribute(AttributeName.INTELLIGENCE,
				"This is a measure of your problem solving ability; figuring out problems, noticing things, remembering information." //
						+ " Almost every character type will need a high Intelligence, with Netrunners and Corporates requiring the highest of all.");
		attributes.put(intelligence.getName(), intelligence);
		reflexes = new CyberpunkAttribute(AttributeName.REFLEXES,
				"This is a combined index, covering not only your basic dexterity," //
						+ " but also how your level of physical coordination will affect feats of driving, piloting, fighting, and athletics." //
						+ " Characters who intend to engage in a great deal of combat (such as Solos, Nomads, or Rockerboys) should always invest in the highest possible Reflex.");
		attributes.put(reflexes.getName(), reflexes);
		cool = new CyberpunkAttribute(AttributeName.COOL,
				"This index measures how well the character stands up to stress, fear, pressure, physical pain and/or torture." //
						+ " In determining your willingness to fight on despite wounds or your fighting ability under fire, Cool is essential." //
						+ " It is also the measure of how \"together\" your character is and how tough he appears to others."//
						+ " Rockerboys and Fixers should always have a high Cool, with Solos and Nomads having the highest of all.");
		attributes.put(cool.getName(), cool);
		technicalAbility = new CyberpunkAttribute(AttributeName.TECHNICAL_ABILITY,
				"This is an index of how well you relate to hardware and other technically oriented things." //
						+ " In Cyberpunk, the abililty to use and repair technology is of paramount importance--TECH will be the Stat used when fixing, repairing and attempting to use unfamiliar tech." //
						+ " While all characters should have a decent Tech Stat, potential Techies should always opt for the highest possible score in this area.");
		attributes.put(technicalAbility.getName(), technicalAbility);
		luck = new CyberpunkAttribute(AttributeName.LUCK,
				"This is the intangible \"something\" that throws the balance of events into your favor." //
						+ " Your luck represents how many points you may use each game to influence the outcome of a critical event." //
						+ " To use Luck, you may add any or all of the points of luck a character has to a critical die roll (declaring your intention to use Luck before the roll is made) until all of your Luck stat is used up." //
						+ " Luck is always restored at the end of each game session.");
		attributes.put(luck.getName(), luck);
		attractiveness = new CyberpunkAttribute(AttributeName.ATTRACTIVENESS, "This is how good-looking you are." //
				+ " In Cyberpunk, it's not enough to be good--you have to look good while you're doing it (Attitude is Everything)." //
				+ " Attractiveness is especially important to Medias and Rockerboys, as being good-looking is part of their jobs.");
		attributes.put(attractiveness.getName(), attractiveness);
		movementAllowance = new CyberpunkAttribute(AttributeName.MOVEMENT_ALLOWANCE,
				"This is an index of how fast your character can run (important in combat situations)." //
						+ " The higher your Movement Allowance, the more distance you can cover in a turn.");
		attributes.put(movementAllowance.getName(), movementAllowance);
		run = new DerivedAttribute(AttributeName.RUN,
				"To determine how far your character can run in a single combat round (@3.2 seconds) in meters, multiply your MA by 3." //
						+ " The character can run three times this distance in a full 10 second turn.",
				movementAllowance, new RunCalculator());
		attributes.put(run.getName(), run);
		leap = new DerivedAttribute(AttributeName.LEAP,
				"To determine how far your character can leap (from a running start), divide your RUN by 4.", run,
				new LeapCalculator());
		attributes.put(leap.getName(), leap);
		empathy = new CyberpunkAttribute(AttributeName.EMPATHY,
				"This Stat represents how well you relate to other living things--a measure of charisma and sympathetic emotions." //
						+ " In a world of alienated, future-shocked survivors, the ability to be \"human\" can no longer be taken for granted." //
						+ " Empathy is critical when leading, convincing, seducing or perceiving emotional undercurrents." //
						+ " Empathy is also a measure of how close he/she is to the line between feeling human being and cold blooded cyber-monster.");
		attributes.put(empathy.getName(), empathy);
		bodyType = new CyberpunkAttribute(AttributeName.BODY_TYPE,
				"Strength, Endurance and Constitution are all based on the character's Body Type." //
						+ " Body Type determines how much damage you can take in wounds, how much you can lift or carry, how far you can throw, how well you recover from shock, and how much additional damage you cause with physical attacks." //
						+ " Body Type is important to all character types, but to Solos, Rockerboys and Nomads most of all.");
		attributes.put(bodyType.getName(), bodyType);
		carry = new DerivedAttribute(AttributeName.CARRY, "The amount of weight you can carry on your back.", bodyType,
				new CarryCalculator());
		attributes.put(carry.getName(), carry);
	}

	public AbstractAttribute get(AttributeName attributeName) {
		AbstractAttribute attribute = attributes.get(attributeName);
		if (attribute == null) {
			return NullCyberpunkAttribute.getInstance();
		} else {
			return attribute;
		}
	}

	public int getLevel(AttributeName attributeName) {
		AbstractAttribute attribute = attributes.get(attributeName);
		if (attribute == null) {
			return CyberpunkAttribute.MIN_LEVEL - CyberpunkAttribute.MIN_LEVEL;
		} else {
			return attribute.getLevel();
		}
	}

}
