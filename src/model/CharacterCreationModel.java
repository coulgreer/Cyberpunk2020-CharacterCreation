package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import view.CharacterCreationView;

public class CharacterCreationModel {
	public static final int DEFAULT_STAT_LEVEL = 2;
	public static final int MINIMUM_INJURY_POINTS = 0;
	public static final int MAXIMUM_INJURY_POINTS = 40;
	public static final String INT = "INTELLIGENCE";
	public static final String REF = "REFLEXES";
	public static final String CL = "COOL";
	public static final String TECH = "TECHNICAL ABILITY";
	public static final String LK = "LUCK";
	public static final String ATT = "ATTRACTIVENESS";
	public static final String MA = "MOVEMENT ALLOWANCE";
	public static final String EMP = "EMPATHY";
	public static final String SPEC = "SPECIAL ABILITIES";
	public static final String BOD = "BODY";

	private String characterName;
	private Role role;
	private int characterPoints;

	private int intelligenceLevel;
	private int generalEncumbranceValue;
	private int headEncumbranceValue;
	private int torsoEncumbranceValue;
	private int rightArmEncumbranceValue;
	private int leftArmEncumbranceValue;
	private int rightLegEncumbranceValue;
	private int leftLegEncumbranceValue;
	private int modifiedReflexesLevel;
	private int unmodifiedReflexesLevel;
	private int technicalAbilityLevel;
	private int coolLevel;
	private int attractivenessLevel;
	private int luckLevel;
	private int movementAllowanceLevel;
	private int bodyLevel;
	private int currentEmpathyLevel;
	private int totalEmpathyLevel;
	private double runDistance;
	private double leapDistance;
	private double carryCapacity;
	private double liftCapacity;

	private int headArmorSP;
	private int torsoArmorSP;
	private int rightArmArmorSP;
	private int leftArmArmorSP;
	private int rightLegArmorSP;
	private int leftLegArmorSP;

	private int saveModifier;
	private int bodyTypeModifier;

	private double injuryPoints;

	private Map<String, Map<String, Skill>> skillCatelog = new TreeMap<String, Map<String, Skill>>();
	private Map<String, Skill> specialAbilitySkills = new TreeMap<String, Skill>();
	private Map<String, Skill> attractivenessSkills = new TreeMap<String, Skill>();
	private Map<String, Skill> bodySkills = new TreeMap<String, Skill>();
	private Map<String, Skill> coolSkills = new TreeMap<String, Skill>();
	private Map<String, Skill> empathySkills = new TreeMap<String, Skill>();
	private Map<String, Skill> intelligenceSkills = new TreeMap<String, Skill>();
	private Map<String, Skill> reflexesSkills = new TreeMap<String, Skill>();
	private Map<String, Skill> technicalAbilitySkills = new TreeMap<String, Skill>();
	private Map<String, Role> roles = new TreeMap<String, Role>();
	private Map<String, Weapon> weapons = new TreeMap<String, Weapon>();
	private Map<String, Gear> gear = new TreeMap<String, Gear>();
	private Map<String, Armor> armors = new TreeMap<String, Armor>();
	private Map<String, Ammo> ammos = new TreeMap<String, Ammo>();

	private Skill authority = new Skill.SkillBuilder() //
			.withSkillName("Authority") //
			.withDescription("The ability to intimidate or control others "
					+ "through your position as a lawman. This attribute represents "
					+ "the Cop's ability to call on the forces of the Law and "
					+ "Government to get what he wants. Cops can use Authority to "
					+ "question suspects, arrest wrongdoers, and defend innocents."
					+ " Backed by the power of Authority, a cop can arrest, detain, "
					+ "confiscate and enter nearly anywhere, as long as he has the "
					+ "proper arrest or search warrants to back his play. "
					+ "However, authority is only as good as the guy holding the "
					+ "badge-if the cop appears uncertain of his Authority, there's a "
					+ "good chance he'll get nailed by the people he's trying to "
					+ "confront. The higher your Authority, the more able you are "
					+ "to face down criminals, particularly high level mobsters and "
					+ "officials. Authority is applied to your Cool stat.") //
			.withType(SPEC) //
			.build();
	private Skill charismaticLeadership = new Skill.SkillBuilder() //
			.withSkillName("Charismatic Leadership") //
			.withDescription("This skill allows the Rocker to sway crowds equal to "
					+ "his level squared times 200. This ability (added to your Cool "
					+ "stat) allows the Rockerboy to control, incite and charm large "
					+ "number of people through his or her performance skills. When "
					+ "under the Rocker's control, this group can easily be persuaded "
					+ "to act on his suggestions; for example, a Rocker could convince "
					+ "a concert crowd to riot in the streets or attack a heavily "
					+ "fortified police line. Charismatic Leadership will only work "
					+ "with groups of ten or more people as it is primarily a mob "
					+ "leadership ability. The higher your Charismatic Leadership, "
					+ "the larger a crowd you can control and the more direct and "
					+ "complex the instructions you can get them to follow. For "
					+ "example, a Level +3 Leadership could incite a nightclub crowd "
					+ "to get rowdy. A Level +5 or+6 could provoke a concert crowd "
					+ "of thousands to trash a neighborhood, if the area wasn't too "
					+ "far from the hall. At Level +9, and higher, you have the same "
					+ "sort of mesmeric ability as an Adolph Hitler -- you can raise "
					+ "armies, start movements, and destroy nations.") //
			.withType("SPECIAL ABILITIES") //
			.build();
	private Skill combatSense = new Skill.SkillBuilder() //
			.withSkillName("Combat Sense") //
			.withDescription("This ability is based on the Solo's constant training "
					+ "and professionalism. Combat Sense allows the Solo to perceive "
					+ "danger, notice traps, and have an almost unearthly ability to "
					+ "avoid harm. Your Combat Sense gives you a bonus on both your "
					+ "Awareness skill and your Initiative equal to your level in the Combat Sense skill.") //
			.withType(SPEC) //
			.build();
	private Skill credibility = new Skill.SkillBuilder() //
			.withSkillName("Credibility") //
			.withDescription("This is the ability to be believed: by your viewers, by "
					+ "the police, by important and powerful people. This is critical "
					+ "to getting your story heard and acted upon, as well as "
					+ "convincing people to tell you things, give you information, or"
					+ " get you into where the story is really happening. The higher "
					+ "your Credibility, the more people you can convince, and the "
					+ "easier it is to convince high level authorities of the truth "
					+ "of your information. With a level +3 Credibility, you can "
					+ "convince most people of minor scandals. With a level +5 or +6,"
					+ " you can convince local officials of military atrocities, "
					+ "undercover dealings and other front page stuff. At level +9, "
					+ "you can successfully expose a scandal of Watergate "
					+ "proportions, or convince the President of the EuroMarket "
					+ "Finance Board that aliens are secretly influencing world "
					+ "leaders. Credibility applies to your INT stat.") //
			.withType(SPEC) //
			.build();
	private Skill family = new Skill.SkillBuilder() //
			.withSkillName("Family") //
			.withDescription("This is the ability to call upon the resources and help"
					+ " of any of the members of the Nomad's large, extended tribal"
					+ " family. This can be in the form of weapons, cash, information,"
					+ " or a small army of relatives. The threat of a Nomad family's "
					+ "vengeance may in itself stop harm to the Nomad. The higher "
					+ "your Family ability, the more important you are to the Family "
					+ "and the more help you can call upon. With a Family status of +2,"
					+ " you might be able to get several of the Pack to help you "
					+ "wreck a town, for example. With a status of +7 or +8, you are "
					+ "able to make major Pack decisions and lead troops. At + 10, "
					+ "you may be the Leader of your Pack. Family is applied to your INT Stat.") //
			.withType(SPEC) //
			.build();
	private Skill interf = new Skill.SkillBuilder() //
			.withSkillName("Interface") //
			.withDescription("This skill reflects the Netrunner's ability to manipulate"
					+ " Interface programs, and is the Skill used when operating Menu "
					+ "functions such as Locate Remote, Run Software, Control Remote, "
					+ "Downlink, Load, Create and Delete. Other players can enter the"
					+ " Net, but cannot use the Menu. Interfavce is based on the INT Stat. Not for Cyberpunk") //
			.withType(SPEC) //
			.build();
	private Skill juryRig = new Skill.SkillBuilder() //
			.withSkillName("Jury Rig") //
			.withDescription("This general repair skill allows the Techie to "
					+ "temporarily repair or alter anything for 1 D6 turns per level "
					+ "of skill. This is not a permanent repair; after the elapsed "
					+ "time, the jury rig will break down.") //
			.withType(SPEC) //
			.build();
	private Skill medicalTech = new Skill.SkillBuilder() //
			.withSkillName("Medical Tech") //
			.withDescription("This is the skill used to perform major surgery and "
					+ "medical repairs.\n\nMedical Tech skill assumes that the character"
					+ " has studied medicine in a professional setting. This gives "
					+ "him the ability to perform surgery, prescribe drugs, and know "
					+ "the proper treatment of injuries. He can replace damaged organs"
					+ " with vatgrown pieces, graft on new limbs, or install "
					+ "cyberlimbs. You cannot perform Medical Tech skills on "
					+ "yourself.\n\nA character with Medical Tech skills makes a "
					+ "check as if using the First Aid skill, however, with Medical "
					+ "Tech, the patient will recover at the rate of 1 point per day. "
					+ "For example, a light wound would be healed in 4 days. A Mortal"
					+ " 3 wound would heal in 28 days. Using Medical Tech skills "
					+ "supersedes the use of First Aid skills; a patient on which"
					+ " both have been successfully performed regains points "
					+ "at the rate of 1 per day, not 1.5! As with First Aid,"
					+ " the patient reagains no points until a successful roll "
					+ "has been made. However, second attempts may be made once"
					+ " per day until a successful roll is made.") //
			.withType(SPEC) //
			.build();
	private Skill resources = new Skill.SkillBuilder() //
			.withSkillName("Resources") //
			.withDescription("This represents the Corporate's ability to command "
					+ "corporation resources. It is used as a persuasion skill, "
					+ "based on the scale of resources requested. This could include"
					+ " bodyguards, weapons, vehicles, buildings, money, etc. "
					+ "Obviously, the more powerful the Corporate, the more he can "
					+ "call upon at any one time. Your level of Resources determines"
					+ " exactly how much you can request from the Corporation without"
					+ " overreaching yourself. A Resource ability of +2 might get you"
					+ " access to a Company car. An ability of +6 might allow you to "
					+ "use a Company jet or hire a Solo team from the Corporate "
					+ "Security Division. A Resource of +9 would allow you access to "
					+ "almost all levels of the Corporation, as well as the ability "
					+ "to requisition almost any Company resource. Your Resource "
					+ "ability is applied to your INT stat.") //
			.withType(SPEC) //
			.build();
	private Skill streetdeal = new Skill.SkillBuilder() //
			.withSkillName("Streetdeal") //
			.withDescription("This is the ability to deal with the underground "
					+ "information network. With Streetdeal, a Fixer can uncover "
					+ "rumors and information, locate missing persons or things, "
					+ "put gossip out on the Street, pick up clues and score big "
					+ "deals. Thehigheryour Streetdealability, the more information "
					+ "you can gather about things happening around you, the more "
					+ "informants you have, and the more secretive the information "
					+ "you can dig up. A level +3 Streetdeal can get you contacts for"
					+ " weapons, tools, or minor illegal operations. At level +5, you "
					+ "can penetrate the secrets of all but the most powerful crime "
					+ "families. At level +9, you are the equivalent of a Mafia crime "
					+ "lord yourself, privy to every secret that's on the Street. "
					+ "Apply Streetdeal to your Cool stat.") //
			.withType(SPEC) //
			.build();
	private Skill personalGrooming = new Skill.SkillBuilder() //
			.withSkillName("Personal Grooming") //
			.withDescription("This is the skill of knowing proper grooming, hair "
					+ "styling, etc., to maximize your physical attractiveness. "
					+ "Use of this skill allows players to increase their "
					+ "Attractiveness, and thus their chances of successful "
					+ "Relationships or Persuasions. A basically good looking person "
					+ "would be at +2. A fashion model might have a Personal Grooming "
					+ "of +5 or +6. At +8 or better, you could be a major fashion "
					+ "model, film star, or trendsetter. You are always 'together'. And you know it.") //
			.withType(ATT) //
			.build();
	private Skill wardrobeAndStyle = new Skill.SkillBuilder() //
			.withSkillName("Wardrobe and Style") //
			.withDescription("The skill of knowing the right clothes to wear, when to "
					+ "wear them, and how to look cool even in a spacesuit. With a "
					+ "Wardrobe of +2 or better, you are good at choosing clothes off "
					+ "the rack. At +6, your friends ask you for wardrobe tips, and you"
					+ " never buy anything off the rack. At +8 or better, you are "
					+ "one of those rare people whose personal style influences major fashion trends.") //
			.withType(ATT) //
			.build();
	private Skill endurance = new Skill.SkillBuilder() //
			.withSkillName("Endurance") //
			.withDescription("This is the ability to withstand pain or hardship, "
					+ "particularly over long periods of time, by knowing the best "
					+ "ways to conserve strength and energy. Endurance Skill checks "
					+ "would be made whenever a character must continue to be active "
					+ "after a long period without food, sleep or water.") //
			.withType(BOD) //
			.build();
	private Skill strengthFeat = new Skill.SkillBuilder() //
			.withSkillName("Strength Feat") //
			.withDescription("The user of this skill has practiced the art of bending "
					+ "bars, crushing objects, ripping phone books apart and other "
					+ "useful parlor tricks. At +2 you can crush cans, rip thin books "
					+ "in half, and bend thin rods. At +8, no phonebook is safe, you "
					+ "can bend thin rebar, and snap handcuffs. At +10, you can bend "
					+ "prison bars, rip up the Gutenberg Bible, and dent car fenders with one blow.") //
			.withType(BOD) //
			.build();
	private Skill swimming = new Skill.SkillBuilder() //
			.withSkillName("Swimming") //
			.withDescription("This skill is required to know how to swim (see Athletics under Reflexes for details).") //
			.withType(BOD) //
			.build();
	private Skill interrogation = new Skill.SkillBuilder() //
			.withSkillName("Interrogation") //
			.withDescription("The skill of drawing information from a subject and "
					+ "forcing his secrets into the open. An Interrogation of +2 or "
					+ "better will allow you to infallibly find out if your boyfriend "
					+ "is lying to you. At +5, you are a professional level "
					+ "interrogator- equivalent to a skilled detective grilling a "
					+ "suspect. Mike Wallace of 60 Minutes has an Interrogation of +9, "
					+ "allowing him to make even the most powerful people squirm.") //
			.withType(CL) //
			.build();
	private Skill intimidate = new Skill.SkillBuilder() //
			.withSkillName("Intimidate") //
			.withDescription("The skill of getting people to do what you want by "
					+ "force of personality or physical coercion. At +3, you can "
					+ "frighten almost any typical citizen, politician or low-level "
					+ "thug. At +6, you can intimidate Sylvester Stallone or any "
					+ "moderate 'tough guy'. At +9, you could intimidate Arnold Schwartzenegger.") //
			.withType(CL) //
			.build();
	private Skill oratory = new Skill.SkillBuilder() //
			.withSkillName("Oratory") //
			.withDescription("The skill of public speaking. At +2, you can wing high "
					+ "school speech contests. At +6, you can be paid to speak in "
					+ "public. At + 1 0, you are capable of delivering a speech to "
					+ "rival Kennedy's 'lch Bin Ein Berliner' or Lincoln's Gettysburg "
					+ "Address. Rockers with an Oratory Skill of +6 or better "
					+ "can add + 1 when using their Charismatic Leadership ability.") //
			.withType(CL) //
			.build();
	private Skill resistTortureAndDrugs = new Skill.SkillBuilder() //
			.withSkillName("Resist Torture/Drugs") //
			.withDescription("Characters with this skill are especially toughened "
					+ "against interrogation, torture and mind control drugs. A "
					+ "successful use of this skill will automatically increase the "
					+ "difficulty of any interrogation attempt made by another party by one level.") //
			.withType(CL) //
			.build();
	private Skill streetwise = new Skill.SkillBuilder() //
			.withSkillName("Streetwise") //
			.withDescription("The knowledge of the 'seamy' side of life-where to get "
					+ "illegal and contraband things, how to talk to the criminal "
					+ "element, and avoiding bad situations in bad neighborhoods. "
					+ "With a Streetwise of +2 or better, you can get 'hot' items, "
					+ "score drugs, etc. A Streetwise of +5 would allow you to arrange "
					+ "a murder contract, know a few mobsters who might owe you favors, "
					+ "and be able to call on muscle when you need it. At +8 or better, "
					+ "you could become a major crimelord yourself and skip the middlemen.") //
			.withType(CL) //
			.build();
	private Skill humanPerception = new Skill.SkillBuilder() //
			.withSkillName("Human Perception") //
			.withDescription("The skill of detecting lies, evasions, moods and other "
					+ "emotional clues from others. At +2, you can usually tell when "
					+ "you're not getting the whole truth. At +6, you can detect subtle "
					+ "evasions and mood swings. At +8, you can not only detect subtle "
					+ "emotional clues, but can usually tell what the subject is hiding in a general way.") //
			.withType(EMP) //
			.build();
	private Skill interview = new Skill.SkillBuilder() //
			.withSkillName("Interview") //
			.withDescription("The skill of eliciting interesting annecdotes from an "
					+ "interview subject. This information will be of a more "
					+ "nonspecific and personal nature rather than specific knowledge "
					+ "(distinguishing this skill from the skill of Interrogation, "
					+ "where the user is trying to extract exact information. Example: "
					+ "Barbara Walters interviews, Mike Wallace interrogates). At +3 "
					+ "or better, the subject will usually tell you only information "
					+ "relating to what he/she is well known for. At +6 or better, "
					+ "the subject will tell you anecdotes about the past, pontificate "
					+ "about favorite interests and philosophies, etc. At +9 or "
					+ "better, he/she tells you everything-including personal "
					+ "information about their illegitimate son, the time they stole a "
					+ "cookie at age 4, and the fact that no one ever loved them.") //
			.withType(EMP) //
			.build();
	private Skill leadership = new Skill.SkillBuilder() //
			.withSkillName("Leadership") //
			.withDescription("The skill of leading and convincing people to follow "
					+ "you. A leader with a skill of +2 can manage a small office "
					+ "successfully and be respected for it. A leader with a skill of "
					+ "+4 or better can lead a small band of troops into battle and "
					+ "not get backshot. A leader with a skill of +7 or better can lead "
					+ "the entire Gamelon Empire into battle and look good doing it. "
					+ "James Kirk of Star Trek has a Leadership of +11, but you never will.") //
			.withType(EMP) //
			.build();
	private Skill seduction = new Skill.SkillBuilder() //
			.withSkillName("Seduction") //
			.withDescription("The skill of forming and maintaining romantic "
					+ "relationships (this includes your abilities as a lover). "
					+ "This skill may be used to determine whether or not players can "
					+ "form relationships with other non-player characters and the "
					+ "intensity of these relationships. In certain cases, Referees "
					+ "may want to average this skill with a player's "
					+ "Attractiveness to get a more realistic outcome.") //
			.withType(EMP) //
			.build();
	private Skill social = new Skill.SkillBuilder() //
			.withSkillName("Social") //
			.withDescription("The ability to deal with social situations, like "
					+ "knowing the right fork to use or when not to tell the joke "
					+ "about the farmer's daughter and the travelling cyberware salesman."
					+ " A Social Skill of +2 or better will allow you to get by at any "
					+ "fine restaurant or social function. At +5, you can lunch with "
					+ "the President with aplomb. No social situation will faze you, no "
					+ "matter what. At +8 or above, you can lecture Emily Post on what's proper.") //
			.withType(EMP) //
			.build();
	private Skill persuasionAndFastTalk = new Skill.SkillBuilder() //
			.withSkillName("Persuasion And Fast Talk") //
			.withDescription("The ability to talk others into doing what you want. This "
					+ "may be used individually or on large groups. At +3, you can win "
					+ "most debates or convince your girlfriend that the blonde you were "
					+ "with was your sister. At +5, you are a smooth talker of professional "
					+ "caliber. Ronald Reagan has a Persuasion of +7. Hitler had a Persuasion of +9.") //
			.withType(EMP) //
			.build();
	private Skill perform = new Skill.SkillBuilder() //
			.withSkillName("Perform") //
			.withDescription("The skill of trained acting, singing, etc. A trained "
					+ "performer of +4 or greater can successfully sing for payment at "
					+ "weddings or small clubs. Performers +6 or greater will be "
					+ "considered to be of professional caliber, and may have lucrative "
					+ "contracts and fans. Performers of +9 or greater are of 'star' "
					+ "caliber, have a large number of fans, and may be recognized on the street.") //
			.withType(EMP) //
			.build();
	private Skill accounting = new Skill.SkillBuilder() //
			.withSkillName("Accounting") //
			.withDescription("The ability to balance books (or create false books), "
					+ "juggle numbers, create budgets and handle day to day business operations.") //
			.withType(INT) //
			.build();
	private Skill anthropology = new Skill.SkillBuilder() //
			.withSkillName("Anthropology") //
			.withDescription("The knowledge of human cultures, habits and customs. "
					+ "Unlike Streetwise (which covers only the cultures and customs of "
					+ "the Street), or Social (which covers only what you should do in "
					+ "a given situation), Anthropology covers general customs and "
					+ "background of a culture. For example, with Streetwise, you know "
					+ "what alleys to avoid and what gangs are dangerous. With Social, "
					+ "you know the proper forms of address for a high ranking Japanese "
					+ "zaibatsu head. With Anthropology, you know that the customs of a "
					+ "N'Tanga tribesman require that a young man kill a lion in order "
					+ "to be accepted as an adult male.") //
			.withType(INT) //
			.build();
	private Skill awarenessAndNotice = new Skill.SkillBuilder() //
			.withSkillName("Awareness/Notice") //
			.withDescription("This is the equivalent of a 'trained observer' skill, "
					+ "allowing characters to notice or be aware of clues, shadowers "
					+ "and other events. With an Awareness of +2 you will usually spot "
					+ "small pieces of paper with notes on them, doors left ajar, and "
					+ "obvious expressions of lying or dislike. An Awareness of +5 or "
					+ "better allows you to spot fairly well hidden clues, notice small "
					+ "changes in expression, and fairly sophisticated attempts to "
					+ "'shadow' you. With an Awareness of +8 or greater, you routinely "
					+ "perform the sorts of deductive reasoning seen in the average TV "
					+ "cop show ('The murderer was left handed because this knife has a "
					+ "specialized handle'). Sherlock Holmes has a +10 Awareness. "
					+ "Players without this skill may only use their Intelligence Stat.") //
			.withType(INT) //
			.build();
	private Skill biology = new Skill.SkillBuilder() //
			.withSkillName("Biology") //
			.withDescription("General knowledge of animals, plants, and other "
					+ "biological systems. At level +3, you know most types of common "
					+ "animals, plants. At +6, you have a general understanding of "
					+ "genetics, cellular biology, etc. At + 10, you can perform most "
					+ "bio-lab procedures, including gene mapping and splicing.") //
			.withType(INT) //
			.build();
	private Skill botany = new Skill.SkillBuilder() //
			.withSkillName("Botany") //
			.withDescription("The general knowledge of plants and plant identification. "
					+ "At level +3, you know most common plants and can identify which "
					+ "ones are dangerous and why. At a +6, you can identify most important "
					+ "plants found worldwide and have a working knowledge of their uses. "
					+ "At +8, you have the equivalent of a doctorate in Botany and "
					+ "know all about rare poisons, exotic orchids and other useful plants.") //
			.withType(INT) //
			.build();
	private Skill chemistry = new Skill.SkillBuilder() //
			.withSkillName("Chemistry") //
			.withDescription("The required skill for mixing chemicals and creating "
					+ "various compounds. A level +2 Chemistry is equal to high school "
					+ "chemistry. A level +4 is equal to a trained pharmacist or "
					+ "college level chemist. A +8 is a trained laboratory chemist.") //
			.withType(INT) //
			.build();
	private Skill composition = new Skill.SkillBuilder() //
			.withSkillName("Composition") //
			.withDescription("The required skill for writing songs, articles, or "
					+ "stories. A Composing Skill of +4 or greater gives your character "
					+ "the ability to produce salable work. A Skill of +8 or more "
					+ "produces work of such a high caliber that the creator may have "
					+ "a strong literary following and not a little critical acclaim.") //
			.withType("INTELLIGENCE") //
			.build();
	private Skill diagnoseIllness = new Skill.SkillBuilder() //
			.withSkillName("Diagnose Illness") //
			.withDescription("The skill of clinically diagnosing symptoms and medical "
					+ "problems. A +3 is the equivalent of a high school nurse-you can "
					+ "recognize most common injuries and complaints. At +6, you would "
					+ "be equivalent to a trained intern; you can recognize many "
					+ "uncommon illnesses and know ?how to treat most common ones. "
					+ "A +9 is the equivalent of a skilled diagnostician; "
					+ "other physicians come to you to get a diagnosis.") //
			.withType(INT) //
			.build();
	private Skill educationAndGeneralKnowledge = new Skill.SkillBuilder() //
			.withSkillName("Education and General Knowledge") //
			.withDescription("This skill is the equivalent of a basic public school "
					+ "education, allowing you to know how to read, write, use basic "
					+ "math, and know enough history to get by. In effect, it is a 'lore' "
					+ "or trivia skill. A level of + 1 is a basic grade school education. "
					+ "A skill of +2 is equal to a high school equivalency. A Knowledge "
					+ "Skill of +3 is equal to a college education, +4 or higher is "
					+ "equal to a Masters or Doctorate. At +7, you are an extremely well educated "
					+ "person, and are asked to play Trivial Pursuit a lot. At +9 and above, "
					+ "you are one of those people who knows a lot about everything (and "
					+ "hopefully has the good sense to keep his mouth shut).") //
			.withType(INT) //
			.build();
	private Skill expert = new Skill.SkillBuilder() //
			.withSkillName("Expert*") //
			.withDescription("You may use this skill to be an expert on one specific "
					+ "subject, such as rare postage stamps, obscure weapons, a foreign "
					+ "language, etc. At +3, you are the local expert. At +6, you know "
					+ "enough to publish a few books on the subject. At +8 or better, "
					+ "your books are recognized as major texts on the subject, and "
					+ "you could do the talk-show circuit if you wanted to.") //
			.withType(INT) //
			.build();
	private Skill gamble = new Skill.SkillBuilder() //
			.withSkillName("Gamble") //
			.withDescription("The skill of knowing how to make bets, figure odds, and play "
					+ "games of chance successfully. As any professional gambler knows, this "
					+ "is not a luck skill. At +2, you are the local card shark at the "
					+ "Saturday night poker game. At +6, you can make a living at the "
					+ "tables in Vegas and Monte Carlo. At +9 or better, you can take on "
					+ "James Bond at Baccarat and stand a good chance of breaking the bank.") //
			.withType(INT) //
			.build();
	private Skill geology = new Skill.SkillBuilder() //
			.withSkillName("Geology") //
			.withDescription("A functional knowledge of rocks, minerals and geologic "
					+ "structures. At +3, you can identify most common rocks and minerals. "
					+ "At +6, you have the equivalent of a college degree in Geology "
					+ "and can identify minerals and geological structures with ease. "
					+ "At +8, you can teach geology in high school.") //
			.withType(INT) //
			.build();
	private Skill hideAndEvade = new Skill.SkillBuilder() //
			.withSkillName("Hide/Evade") //
			.withDescription("The skill of losing pursuers, covering tracks and "
					+ "otherwise evading people on your trail. At +3, you can lose most "
					+ "booster-gangers on the rampage. At +6, you can ditch cops and "
					+ "private eyes. At +8, you can ditch most Solos.") //
			.withType(INT) //
			.build();
	private Skill history = new Skill.SkillBuilder() //
			.withSkillName("History") //
			.withDescription("The knowledge of facts and figures of past events. In "
					+ "game play, this might be used to determine if a character is "
					+ "familiar with a particular clue related to a past event. At +2, "
					+ "you have the equivalent of a grade school history education. At "
					+ "+6, you would have the equivalent of a college grasp on the "
					+ "subject. At +8, you could teach history in high school. At +9, "
					+ "you may have written a few of the most oftused texts on a particular "
					+ "historical personage or epoch.") //
			.withType(INT) //
			.build();
	private Skill language1 = new Skill.SkillBuilder() //
			.withSkillName("Language 1*") //
			.withDescription("The knowledge of a foreign tongue. At +2, you can 'get by' "
					+ "with speaking the language. At +3, you can actually read a written "
					+ "form of it. At +6 and above, you are fairly fluent, although no "
					+ "native will be fooled by your ability. At +8 and "
					+ "above, you speak and read the language like a native.\n\n    "
					+ "AFRICAN (2): Bantu, Fante, Ashanti, Kongo, Zulu, Swahili, Blackfolk"
					+ "\n    BALTIC (2): Lithuanian, Extonian, Latvian, Finnish"
					+ "\n    CELTIC (2): Gaelic, Welsh, Breton\n    FARSI (2)\n    "
					+ "GERMANIC (1): Danish, Dutch, English, German, Norwegian, Swedish, Yiddish"
					+ "\n    GREEK (2)\n    JAPANESE (2)\n    KOREAN (2)\n    PACIFIC ISLAND"
					+ "GROUP (2): Micronesian, Tagalog, Polynesian, Javanese, Malayan, Sudanese, "
					+ "Indonesian, Hawaiian\n    ROMANTIC (1): French, Italian, Spanish, Portuguese,"
					+ " Latin\n    SEMETIC (1): Arabic, Hebrew\n    SINO-TIBETAN and SE ASIAN (3):"
					+ " Burmese, Cantonese, Mandarin, Thai, Tibetan, Vietnamese\n    SLAVIC (3): "
					+ "Bulgarian, Russian, Czech, Polish, Ukranian, Slovak") //
			.withType(INT) //
			.build();
	private Skill language2 = new Skill.SkillBuilder() //
			.withSkillName("Language 2*") //
			.withDescription("The knowledge of a foreign tongue. At +2, you can 'get by' "
					+ "with speaking the language. At +3, you can actually read a written "
					+ "form of it. At +6 and above, you are fairly fluent, although no "
					+ "native will be fooled by your ability. At +8 and "
					+ "above, you speak and read the language like a native.\n\n    "
					+ "AFRICAN (2): Bantu, Fante, Ashanti, Kongo, Zulu, Swahili, Blackfolk"
					+ "\n    BALTIC (2): Lithuanian, Extonian, Latvian, Finnish"
					+ "\n    CELTIC (2): Gaelic, Welsh, Breton\n    FARSI (2)\n    "
					+ "GERMANIC (1): Danish, Dutch, English, German, Norwegian, Swedish, Yiddish"
					+ "\n    GREEK (2)\n    JAPANESE (2)\n    KOREAN (2)\n    PACIFIC ISLAND"
					+ "GROUP (2): Micronesian, Tagalog, Polynesian, Javanese, Malayan, Sudanese, "
					+ "Indonesian, Hawaiian\n    ROMANTIC (1): French, Italian, Spanish, Portuguese,"
					+ " Latin\n    SEMETIC (1): Arabic, Hebrew\n    SINO-TIBETAN and SE ASIAN (3):"
					+ " Burmese, Cantonese, Mandarin, Thai, Tibetan, Vietnamese\n    SLAVIC (3): "
					+ "Bulgarian, Russian, Czech, Polish, Ukranian, Slovak") //
			.withType(INT) //
			.build();
	private Skill language3 = new Skill.SkillBuilder() //
			.withSkillName("Language 3*") //
			.withDescription("The knowledge of a foreign tongue. At +2, you can 'get by' "
					+ "with speaking the language. At +3, you can actually read a written "
					+ "form of it. At +6 and above, you are fairly fluent, although no "
					+ "native will be fooled by your ability. At +8 and "
					+ "above, you speak and read the language like a native.\n\n    "
					+ "AFRICAN (2): Bantu, Fante, Ashanti, Kongo, Zulu, Swahili, Blackfolk"
					+ "\n    BALTIC (2): Lithuanian, Extonian, Latvian, Finnish"
					+ "\n    CELTIC (2): Gaelic, Welsh, Breton\n    FARSI (2)\n    "
					+ "GERMANIC (1): Danish, Dutch, English, German, Norwegian, Swedish, Yiddish"
					+ "\n    GREEK (2)\n    JAPANESE (2)\n    KOREAN (2)\n    PACIFIC ISLAND"
					+ "GROUP (2): Micronesian, Tagalog, Polynesian, Javanese, Malayan, Sudanese, "
					+ "Indonesian, Hawaiian\n    ROMANTIC (1): French, Italian, Spanish, Portuguese,"
					+ " Latin\n    SEMETIC (1): Arabic, Hebrew\n    SINO-TIBETAN and SE ASIAN (3):"
					+ " Burmese, Cantonese, Mandarin, Thai, Tibetan, Vietnamese\n    SLAVIC (3): "
					+ "Bulgarian, Russian, Czech, Polish, Ukranian, Slovak") //
			.withType(INT) //
			.build();
	private Skill librarySearch = new Skill.SkillBuilder() //
			.withSkillName("Library Search") //
			.withDescription("The skill of using databases, DataTerms?, libraries and "
					+ "other compiled information sources to find facts. With a skill of "
					+ "+2 you can use most simple databases. With a skill of +6, you can "
					+ "easily access the Library Congress. At +9, you can comprehend "
					+ "almost any public database and find very obscure facts.") //
			.withType(INT) //
			.build();
	private Skill mathematics = new Skill.SkillBuilder() //
			.withSkillName("Mathematics") //
			.withDescription("The skill of understanding calculations and mathematical "
					+ "formulas. At +3, you have the ability to add, subtract, divide and "
					+ "multiply. At +4, you can do algebra and geometry. At +6, you can "
					+ "perform calculus. At +9 you can deduce your own mathematical formulas.") //
			.withType(INT) //
			.build();
	private Skill physics = new Skill.SkillBuilder() //
			.withSkillName("Physics") //
			.withDescription("The ability to calculate physical principles, such as gas pressures, "
					+ "mechanical energies, etc. This skill requires a basic Mathematics Skill of +4.") //
			.withType(INT) //
			.build();
	private Skill programming = new Skill.SkillBuilder() //
			.withSkillName("Programming") //
			.withDescription("The required skill to write programs and to re-program computer "
					+ "systems. This skill does not allow players to actually do repairs "
					+ "on a computer(this requires Electronics). With a Programming Skill "
					+ "of + 1, you can do simple EBASIC programs. A Programming Skill of +3 or "
					+ "better allows you to know some higher level languages and be able to "
					+ "write reasonably complex programs (including video games). Players with "
					+ "a Programming Skill +6 or better are considered to be professionals, "
					+ "who can build operating software, design mainframe systems, and hold "
					+ "down a steady job at your average Silicon Valley firm. With a "
					+ "Programming Skill of +9 or better, other programmers speak your name "
					+ "with reverence ('You invented Q? Wow!'), young hackers set out to crack "
					+ "your systems, and any computer software you design instantly gets used "
					+ "by every business application in the world.") //
			.withType(INT) //
			.build();
	private Skill shadowAndTrack = new Skill.SkillBuilder() //
			.withSkillName("Shadow/Track") //
			.withDescription("The skill of shadowing and following people. This skill is "
					+ "primarily used in urban or inhabited areas rather than in wilderness "
					+ "(where the skill of Survival incorporates tracking game in the wilds).") //
			.withType(INT) //
			.build();
	private Skill stockMarket = new Skill.SkillBuilder() //
			.withSkillName("Stock Market") //
			.withDescription("The ability to play the stock market, engage in routine "
					+ "stock transactions and manipulate stocks profitably. At +2, you know "
					+ "enough to invest in junk bonds and lose your shirt. At +6, your "
					+ "investments pay off 75% of the time. At +9, you are a major heavy on "
					+ "the Market, routinely dabble in international stocks, and can "
					+ "write learned articles on the subject of investment.") //
			.withType(INT) //
			.build();
	private Skill systemKnowledge = new Skill.SkillBuilder() //
			.withSkillName("System Knowledge") //
			.withDescription("Basic knowledge of the geography of the Net, it's lore and "
					+ "history, as well as knowledge of the important computer systems, "
					+ "their strengths and their weaknesses. At +2, you can generally "
					+ "navigate around the Net and know where all the local places are. "
					+ "At +6, you know the locations of most places in the Net, and have "
					+ "a working understanding of its largest and most well known systems. "
					+ "At +9, you know the entire Net like the back of your hand, know the "
					+ "general layouts of the important systems cold, and are aware of the "
					+ "layouts for the rest of them.") //
			.withType(INT) //
			.build();
	private Skill teaching = new Skill.SkillBuilder() //
			.withSkillName("Teaching") //
			.withDescription("The skill of imparting knowledge to someone else (if you "
					+ "don't think this is a skill, you ought to try it sometime). Players "
					+ "may not teach any skill unless they have a higher skill level than "
					+ "the student. The referee is the final arbiter of how long it takes "
					+ "to teach a skill. At a Teaching Skill of +3 or better, you can "
					+ "professionally teach students up to High School. At +6, you know "
					+ "enough to be a college professor (if you wanted). At +9 or greater, "
					+ "you are recognized by others in the field as good enough to "
					+ "guest lecture at MIT or Cal Tech; your texts on the subject are "
					+ "quoted as the major references, and you might have a TV show on "
					+ "the equivalent of the PBS channel.") //
			.withType(INT) //
			.build();
	private Skill wildernessSurvival = new Skill.SkillBuilder() //
			.withSkillName("Wilderness Survival") //
			.withDescription("The required skill for knowing how to survive in the wilds. "
					+ "Knowledge includes how to set traps, forage for wood, track "
					+ "game, build shelters, make fires. The average Boy Scout has a "
					+ "Survival of +3. A Special Forces Green Beret has a Survival of "
					+ "+6 or above. Grizzly Adams, Mountain Man of the Wilderness, "
					+ "would have a +9 or +10 Survival Skill.") //
			.withType(INT) //
			.build();
	private Skill zoology = new Skill.SkillBuilder() //
			.withSkillName("Zoology") //
			.withDescription("Knowledge of lifeforms, biological processes and their "
					+ "relation to the environment. At +2, you know most common animals. "
					+ "At +5, you know not only well known animals, but also about many "
					+ "exotics and endangered species. At +8, you are knowledgable on "
					+ "almost all animals, know their habits well, and have a +1 advantage "
					+ "to any Wilderness Survival Skills (you know where to find the game).") //
			.withType(INT) //
			.build();
	private Skill archery = new Skill.SkillBuilder() //
			.withSkillName("Archery") //
			.withDescription("The skill required to use bows, crossbows and other "
					+ "arrow-based ranged weapons. See Handgun under Reflexes for details.") //
			.withType(REF) //
			.build();
	private Skill athletics = new Skill.SkillBuilder() //
			.withSkillName("Athletics") //
			.withDescription("This skill is required for accurate throwing, climbing, "
					+ "and balancing. It combines the basic elements of any high school level "
					+ "sports program. At +3 and above, you are the equivalent of a real "
					+ "high school 'jock'. At +5 and above, you can perform in college level "
					+ "competitions. At +8 and above, you are of Olympic or Professional caliber.") //
			.withType(REF) //
			.build();
	private Skill brawling = new Skill.SkillBuilder() //
			.withSkillName("Brawling") //
			.withDescription("The skill of fighting man to man with fist, feet and other "
					+ "parts of the body. Brawling is not a trained skill -- it is learned on "
					+ "the Street by getting into a lot of fights. Unlike Martial Arts, there "
					+ "are no specialized attacks and no damage bonuses based on level of mastery.") //
			.withType(REF) //
			.build();
	private Skill dance = new Skill.SkillBuilder() //
			.withSkillName("Dance") //
			.withDescription("The specific skill needed to become a professional dancer. A "
					+ "trained dancer +4 or greater can successfully dance for payment in "
					+ "small clubs or dance troupes. Dancers +6 or greater will be considered "
					+ "to be of professional caliber, and regularly give performances and have "
					+ "fans. Dancers +9 or greater are of 'star' caliber, have a large number "
					+ "of fans, and may be recognized on the street.") //
			.withType(REF) //
			.build();
	private Skill dodgeAndEscape = new Skill.SkillBuilder() //
			.withSkillName("Dodge and Escape") //
			.withDescription("This skill is required to dodge attacks and escape grapples "
					+ "and holds. If an attack is made without your knowledge, you may not "
					+ "apply this skill to your Defense roll.") //
			.withType(REF) //
			.build();
	private Skill driving = new Skill.SkillBuilder() //
			.withSkillName("Driving") //
			.withDescription("This skill allows you to pilot all ground vehicles like cars, "
					+ "trucks, tanks and hovercraft. This skill is not useable for "
					+ "piloting aircraft. A skill of +3 is equal to that of a very good "
					+ "non-professional driver. A skill of +6 allows you to drive with the "
					+ "skill of a moderately skilled race driver. An driver with a skill of "
					+ "+8 or greater will be nationally known as a racer, regularly win "
					+ "championship races, and possibly have access to the most advanced "
					+ "ground vehicles available (as long as he makes an endorsement).") //
			.withType(REF) //
			.build();
	private Skill fencing = new Skill.SkillBuilder() //
			.withSkillName("Fencing") //
			.withDescription("The mastery of swords, rapiers and monoblades. A Fencing Skill "
					+ "of +3 allows you to be competent with a blade. A Skill of +5 makes "
					+ "you fairly skilled. A Fencing Skill of +6 might win you the National "
					+ "Fencing Competitions. A Skill of +8 will get you a reputation for "
					+ "being a true swordsman of duellist caliber. People like D'Artagnan "
					+ "or Miyamoto Musashi have Skills of+ 1 0. They are legendary masters "
					+ "of the blade; the mention of whom will cause all but the "
					+ "stupidest young bravo to run for cover.") //
			.withType(REF) //
			.build();
	private Skill handgun = new Skill.SkillBuilder() //
			.withSkillName("Handgun") //
			.withDescription("You must have this skill to effectively use handguns of any "
					+ "type, including cyberwear types. At +2, you can use a handgun effectively "
					+ "on a target range, though combat will still rattle you. At +5, you are "
					+ "as skilled as most military officers or policemen. At +7, you can do "
					+ "the sort of fancy shooting you see on TV, and have begun to get a "
					+ "reputation of being 'good with a gun'. At +8, you are a recognized "
					+ "gunslinger with a 'rep'. The very sound of your name makes some people "
					+ "back down in fear. At +10, you are a legendary gunslinger, feared by all "
					+ "except the stupid young punks who keep trying to 'take' you in "
					+ "innumerable gunfight challenges.") //
			.withType(REF) //
			.build();
	private Skill heavyWeapons = new Skill.SkillBuilder() //
			.withSkillName("Heavy Weapons") //
			.withDescription("The required skill for using grenade launchers, autocannon, mortars, "
					+ "heavy machine guns, missiles and rocket launchers. A Level +5 skill "
					+ "would be equivalent to a general military 'Heavy Weapons' training course, "
					+ "giving the user the ability to use any or all of these weapon types.") //
			.withType(REF) //
			.build();
	private Skill martialArt1 = new Skill.SkillBuilder() //
			.withSkillName("Martial Art 1*") //
			.withDescription("This skill covets any type of trained fighting style using "
					+ "hands, feet, or specialized 'martial arts' weapons. You must elect "
					+ "a style of martial art and take a separate skill for each style "
					+ "(for example, you would have to take Karate and "
					+ "Judo separately, spending points for each one.) Difficulty "
					+ "modifiers are listed in () next to each skill listed below.\n\nThe "
					+ "primary advantage to martial arts styles is that each one has "
					+ "what are called key attacks; attacks that reflect particular strengths "
					+ "of the style. When a key attack is used, there is a to-hit bonus based "
					+ "on the attack type and martial arts style.\n\nThe second advantage "
					+ "to martial arts styles is that there is a damage bonus on attacks "
					+ "equal to the level of the Martial Arts skill; for example, a master "
					+ "with a +10 Kung Fu Skill would add 10 points to his damage. This can "
					+ "be a formidable advantage, particularly in head strikes (which double "
					+ "damage). Martial Arts forms include:\n    Aikido (3): This form relies "
					+ "on using the opponent's strength and momentum against him. It is a "
					+ "perfect form for stopping an opponent peacefully while making yourself "
					+ "very hard to hit. Key attacks are: blocks & parries, dodges, throws, "
					+ "holds, escapes, chokes, sweeps, trips & sweets, trips & sweeps, "
					+ "grapples.\n    Animal Kung Fu (3): These are forms based on animal "
					+ "movements, such as crane, mantis, tiger, leopard and dragon forms. "
					+ "These attacks are fast and dangerous, with a style that is exciting "
					+ "and flashy. Key attacks include: strikes, punches, kicks, blocks "
					+ "& parries, sweeps & trips.\n    Boxing (1): The manly art of fisticuffs, "
					+ "this form delivers lightning punches and a tight blocking defense. "
					+ "Key attacks are: punches, blocks & parries.\n    Capoeria (3): Created "
					+ "by Carribean slaves this form combines dancelike movements with fast "
					+ "kicks and low line sweeps & trips.\n    Choi Li Fut (3): Descended "
					+ "directly from the ancient Shaolin temples, this form combines powerful "
					+ "roundhouse blows and sweeping kicks into a dynamic fighting style. "
					+ "Key attacks are: strikes, punches, kicks, blocks & parries, dodges, "
					+ "throws, and sweeps & trips.\n    Judo (1): This system was designed "
					+ "as a sport form, but is very effective in combat as well. It uses "
					+ "throws and sweeps to knock down the opponent. Key attacks include: "
					+ "dodges, throws, holds, escapes, sweeps & trips and grappling.\n    "
					+ "Karate (2): The Japanese version of kung fu, this style uses straight "
					+ "line movements and powerful blows. Variations includ shotokan and "
					+ "kenpo, each with their own special moves. Key attacks are: punches, "
					+ "kicks, and blocks & parries.\n    Tae Kwon Do (3): A very fast and "
					+ "precise form with graceful movements and some aerial kicks. Key "
					+ "attacks include: strikes, punches, kicks, block & parries, dodges."
					+ "\n    Thai Kick Boxing (4): One of the deadliest forms in existence, "
					+ "this style is known for blinding kicks delivered with incredible power. "
					+ "Key moves include: strikes, punches, kicks, blocks & parries, and grapples."
					+ "\n    Wrestling (1): This form combines techniques of Olympic and "
					+ "Professional wrestling. The style uses a wide variety of throws and holds "
					+ "to incapacitate the opponent. Key attacks include: throws, holds, escapes, "
					+ "chokes, sweeps, trips, and grapples.") //
			.withType(REF) //
			.build();
	private Skill martialArt2 = new Skill.SkillBuilder() //
			.withSkillName("Martial Art 2*") //
			.withDescription("This skill covets any type of trained fighting style using "
					+ "hands, feet, or specialized 'martial arts' weapons. You must elect "
					+ "a style of martial art and take a separate skill for each style "
					+ "(for example, you would have to take Karate and "
					+ "Judo separately, spending points for each one.) Difficulty "
					+ "modifiers are listed in () next to each skill listed below.\n\nThe "
					+ "primary advantage to martial arts styles is that each one has "
					+ "what are called key attacks; attacks that reflect particular strengths "
					+ "of the style. When a key attack is used, there is a to-hit bonus based "
					+ "on the attack type and martial arts style.\n\nThe second advantage "
					+ "to martial arts styles is that there is a damage bonus on attacks "
					+ "equal to the level of the Martial Arts skill; for example, a master "
					+ "with a +10 Kung Fu Skill would add 10 points to his damage. This can "
					+ "be a formidable advantage, particularly in head strikes (which double "
					+ "damage). Martial Arts forms include:\n    Aikido (3): This form relies "
					+ "on using the opponent's strength and momentum against him. It is a "
					+ "perfect form for stopping an opponent peacefully while making yourself "
					+ "very hard to hit. Key attacks are: blocks & parries, dodges, throws, "
					+ "holds, escapes, chokes, sweeps, trips & sweets, trips & sweeps, "
					+ "grapples.\n    Animal Kung Fu (3): These are forms based on animal "
					+ "movements, such as crane, mantis, tiger, leopard and dragon forms. "
					+ "These attacks are fast and dangerous, with a style that is exciting "
					+ "and flashy. Key attacks include: strikes, punches, kicks, blocks "
					+ "& parries, sweeps & trips.\n    Boxing (1): The manly art of fisticuffs, "
					+ "this form delivers lightning punches and a tight blocking defense. "
					+ "Key attacks are: punches, blocks & parries.\n    Capoeria (3): Created "
					+ "by Carribean slaves this form combines dancelike movements with fast "
					+ "kicks and low line sweeps & trips.\n    Choi Li Fut (3): Descended "
					+ "directly from the ancient Shaolin temples, this form combines powerful "
					+ "roundhouse blows and sweeping kicks into a dynamic fighting style. "
					+ "Key attacks are: strikes, punches, kicks, blocks & parries, dodges, "
					+ "throws, and sweeps & trips.\n    Judo (1): This system was designed "
					+ "as a sport form, but is very effective in combat as well. It uses "
					+ "throws and sweeps to knock down the opponent. Key attacks include: "
					+ "dodges, throws, holds, escapes, sweeps & trips and grappling.\n    "
					+ "Karate (2): The Japanese version of kung fu, this style uses straight "
					+ "line movements and powerful blows. Variations includ shotokan and "
					+ "kenpo, each with their own special moves. Key attacks are: punches, "
					+ "kicks, and blocks & parries.\n    Tae Kwon Do (3): A very fast and "
					+ "precise form with graceful movements and some aerial kicks. Key "
					+ "attacks include: strikes, punches, kicks, block & parries, dodges."
					+ "\n    Thai Kick Boxing (4): One of the deadliest forms in existence, "
					+ "this style is known for blinding kicks delivered with incredible power. "
					+ "Key moves include: strikes, punches, kicks, blocks & parries, and grapples."
					+ "\n    Wrestling (1): This form combines techniques of Olympic and "
					+ "Professional wrestling. The style uses a wide variety of throws and holds "
					+ "to incapacitate the opponent. Key attacks include: throws, holds, escapes, "
					+ "chokes, sweeps, trips, and grapples.") //
			.withType(REF) //
			.build();
	private Skill martialArt3 = new Skill.SkillBuilder() //
			.withSkillName("Martial Art 3*") //
			.withDescription("This skill covets any type of trained fighting style using "
					+ "hands, feet, or specialized 'martial arts' weapons. You must elect "
					+ "a style of martial art and take a separate skill for each style "
					+ "(for example, you would have to take Karate and "
					+ "Judo separately, spending points for each one.) Difficulty "
					+ "modifiers are listed in () next to each skill listed below.\n\nThe "
					+ "primary advantage to martial arts styles is that each one has "
					+ "what are called key attacks; attacks that reflect particular strengths "
					+ "of the style. When a key attack is used, there is a to-hit bonus based "
					+ "on the attack type and martial arts style.\n\nThe second advantage "
					+ "to martial arts styles is that there is a damage bonus on attacks "
					+ "equal to the level of the Martial Arts skill; for example, a master "
					+ "with a +10 Kung Fu Skill would add 10 points to his damage. This can "
					+ "be a formidable advantage, particularly in head strikes (which double "
					+ "damage). Martial Arts forms include:\n    Aikido (3): This form relies "
					+ "on using the opponent's strength and momentum against him. It is a "
					+ "perfect form for stopping an opponent peacefully while making yourself "
					+ "very hard to hit. Key attacks are: blocks & parries, dodges, throws, "
					+ "holds, escapes, chokes, sweeps, trips & sweets, trips & sweeps, "
					+ "grapples.\n    Animal Kung Fu (3): These are forms based on animal "
					+ "movements, such as crane, mantis, tiger, leopard and dragon forms. "
					+ "These attacks are fast and dangerous, with a style that is exciting "
					+ "and flashy. Key attacks include: strikes, punches, kicks, blocks "
					+ "& parries, sweeps & trips.\n    Boxing (1): The manly art of fisticuffs, "
					+ "this form delivers lightning punches and a tight blocking defense. "
					+ "Key attacks are: punches, blocks & parries.\n    Capoeria (3): Created "
					+ "by Carribean slaves this form combines dancelike movements with fast "
					+ "kicks and low line sweeps & trips.\n    Choi Li Fut (3): Descended "
					+ "directly from the ancient Shaolin temples, this form combines powerful "
					+ "roundhouse blows and sweeping kicks into a dynamic fighting style. "
					+ "Key attacks are: strikes, punches, kicks, blocks & parries, dodges, "
					+ "throws, and sweeps & trips.\n    Judo (1): This system was designed "
					+ "as a sport form, but is very effective in combat as well. It uses "
					+ "throws and sweeps to knock down the opponent. Key attacks include: "
					+ "dodges, throws, holds, escapes, sweeps & trips and grappling.\n    "
					+ "Karate (2): The Japanese version of kung fu, this style uses straight "
					+ "line movements and powerful blows. Variations includ shotokan and "
					+ "kenpo, each with their own special moves. Key attacks are: punches, "
					+ "kicks, and blocks & parries.\n    Tae Kwon Do (3): A very fast and "
					+ "precise form with graceful movements and some aerial kicks. Key "
					+ "attacks include: strikes, punches, kicks, block & parries, dodges."
					+ "\n    Thai Kick Boxing (4): One of the deadliest forms in existence, "
					+ "this style is known for blinding kicks delivered with incredible power. "
					+ "Key moves include: strikes, punches, kicks, blocks & parries, and grapples."
					+ "\n    Wrestling (1): This form combines techniques of Olympic and "
					+ "Professional wrestling. The style uses a wide variety of throws and holds "
					+ "to incapacitate the opponent. Key attacks include: throws, holds, escapes, "
					+ "chokes, sweeps, trips, and grapples.") //
			.withType(REF) //
			.build();
	private Skill melee = new Skill.SkillBuilder() //
			.withSkillName("Melee") //
			.withDescription("The ability to use knives, axes, clubs and other hand to "
					+ "hand weapons in combat. Note: when using non-ranged cyberweapons "
					+ "such as rippers, scratchers, slice n' dices, cyberbeasts, and "
					+ "battlegloves, you must use this skill.") //
			.withType(REF) //
			.build();
	private Skill motorcycle = new Skill.SkillBuilder() //
			.withSkillName("Motorcycle") //
			.withDescription("The required skill to operate motorcycles, cyberbikes and "
					+ "other two and three-wheeled vehicles.") //
			.withType(REF) //
			.build();
	private Skill operateHeavyMachinery = new Skill.SkillBuilder() //
			.withSkillName("Operate Heavy Machinery") //
			.withDescription(
					"The required skill to operate tractors, tanks, very large trucks and construction equipment.") //
			.withType(REF) //
			.build();
	private Skill pilotGyro = new Skill.SkillBuilder() //
			.withSkillName("Pilot Gyro") //
			.withDescription("In general, this is the skill of controlling aircraft. "
					+ "Aircraft are broken into categories: Gyro and Rotorcraft, fixed "
					+ "Wing Aircraft, Dirigibles and Vectored Thrust Aerodynes (AV-s). "
					+ "A Piloting Skill of + 1 allows you to take off and land safely in "
					+ "good weather conditions. A Piloting Skill of +3 or more makes "
					+ "you a trained pilot, able to engage in most combat situations or "
					+ "bad weather. Pilots with a Skill of +6 or greater are veteran pilots, "
					+ "able to handle themselves in almost any situation, including aerobatic "
					+ "manuevers. Pilots with a Skill of +9 or greater are so good, they "
					+ "have a rep as pilots, and are widely known among the piloting "
					+ "fraternity for having the 'right stuff'. The ability to pilot all "
					+ "types of rotorwing aircraft, including gyros, copters and Ospreys."
					+ "\n\n\tPilot Gyro (3): The ability to pilot all types of rotorwing "
					+ "aircraft, including gyros, copters and Ospreys.") //
			.withType(REF) //
			.build();
	private Skill pilotFixedWing = new Skill.SkillBuilder() //
			.withSkillName("Pilot Fixed Wing") //
			.withDescription("In general, this is the skill of controlling aircraft. "
					+ "Aircraft are broken into categories: Gyro and Rotorcraft, fixed "
					+ "Wing Aircraft, Dirigibles and Vectored Thrust Aerodynes (AV-s). "
					+ "A Piloting Skill of + 1 allows you to take off and land safely in "
					+ "good weather conditions. A Piloting Skill of +3 or more makes "
					+ "you a trained pilot, able to engage in most combat situations or "
					+ "bad weather. Pilots with a Skill of +6 or greater are veteran pilots, "
					+ "able to handle themselves in almost any situation, including aerobatic "
					+ "manuevers. Pilots with a Skill of +9 or greater are so good, they "
					+ "have a rep as pilots, and are widely known among the piloting "
					+ "fraternity for having the 'right stuff'. The ability to pilot all "
					+ "types of rotorwing aircraft, including gyros, copters and Ospreys."
					+ "\n\n\tPilot Fixed Wind (2): The ability to pilot fixed wing jets "
					+ "and light aircraft. Ospreys may be flown with this skill, but only "
					+ "in the straight ahead (non-hover) mode.") //
			.withType(REF) //
			.build();
	private Skill pilotDirigible = new Skill.SkillBuilder() //
			.withSkillName("Pilot Dirigible") //
			.withDescription("In general, this is the skill of controlling aircraft. "
					+ "Aircraft are broken into categories: Gyro and Rotorcraft, fixed "
					+ "Wing Aircraft, Dirigibles and Vectored Thrust Aerodynes (AV-s). "
					+ "A Piloting Skill of + 1 allows you to take off and land safely in "
					+ "good weather conditions. A Piloting Skill of +3 or more makes "
					+ "you a trained pilot, able to engage in most combat situations or "
					+ "bad weather. Pilots with a Skill of +6 or greater are veteran pilots, "
					+ "able to handle themselves in almost any situation, including aerobatic "
					+ "manuevers. Pilots with a Skill of +9 or greater are so good, they "
					+ "have a rep as pilots, and are widely known among the piloting "
					+ "fraternity for having the 'right stuff'. The ability to pilot all "
					+ "types of rotorwing aircraft, including gyros, copters and Ospreys."
					+ "\n\n\tPilot Dirigible (2): The ability to pilot pilot all lighter than "
					+ "air vehicles, including cargo dirigibles, blimps and powered balloons.") //
			.withType(REF) //
			.build();
	private Skill pilotVectorThrustVehicle = new Skill.SkillBuilder() //
			.withSkillName("Pilot Vector Thrust Vehicle") //
			.withDescription("In general, this is the skill of controlling aircraft. "
					+ "Aircraft are broken into categories: Gyro and Rotorcraft, fixed "
					+ "Wing Aircraft, Dirigibles and Vectored Thrust Aerodynes (AV-s). "
					+ "A Piloting Skill of + 1 allows you to take off and land safely in "
					+ "good weather conditions. A Piloting Skill of +3 or more makes "
					+ "you a trained pilot, able to engage in most combat situations or "
					+ "bad weather. Pilots with a Skill of +6 or greater are veteran pilots, "
					+ "able to handle themselves in almost any situation, including aerobatic "
					+ "manuevers. Pilots with a Skill of +9 or greater are so good, they "
					+ "have a rep as pilots, and are widely known among the piloting fraternity "
					+ "for having the 'right stuff'. The ability to pilot all types of rotorwing "
					+ "aircraft, including gyros, copters and Ospreys.\n\n\tPilot Vectored "
					+ "Thrust Vehicle (3): The skill of piloting all types of vectored "
					+ "thrust vehicles, including hovercars, hover rafts and AV-4, -6 and -7 vehicles.") //
			.withType(REF) //
			.build();
	private Skill rifle = new Skill.SkillBuilder() //
			.withSkillName("Rifle") //
			.withDescription("You must have this skill to use rifles/shotguns effectively "
					+ "(see Handguns under Reflexes for limitations and modifiers).") //
			.withType(REF) //
			.build();
	private Skill stealth = new Skill.SkillBuilder() //
			.withSkillName("Stealth (2)") //
			.withDescription("The skill of hiding in shadows, moving silently, evading guards, "
					+ "etc. A Stealth Skill of+ 1 is about the level of a very sneaky 10 "
					+ "year old stealing cookies. At +3, you are able to get past most "
					+ "guards, or your parents if you've been grounded. At +6, you are "
					+ "good enough to slip smoothly from shadow to shadow and not make any "
					+ "noise. At +8, you are the equal of most Ninja warriors. At +10, you "
					+ "move as silently as a shadow, making the Ninja sound like elephants.") //
			.withType(REF) //
			.build();
	private Skill submachinegun = new Skill.SkillBuilder() //
			.withSkillName("Submachingun") //
			.withDescription("You must have this skill to use any type of submachine gun "
					+ "effectively (see Handguns under Reflexes for limitations and modifiers).") //
			.withType(REF) //
			.build();
	private Skill aeroTech = new Skill.SkillBuilder() //
			.withSkillName("Aero Tech (2)") //
			.withDescription("The required skill for repairing fixed wing aircraft, including "
					+ "Ospreys, jets, and light aircraft. With a Skill of +3, you can perform "
					+ "most routine maintenance tasks. With a Skill of +6, you can do engine "
					+ "teardowns and major structural repairs. With a Skill of +9 or "
					+ "better, you are capable of designing and building your own aircraft.") //
			.withType(TECH) //
			.build();
	private Skill avTech = new Skill.SkillBuilder() //
			.withSkillName("AV Tech (3)") //
			.withDescription("The required skill for repairing all ducted fan aerodyne "
					+ "vehicles. At +3, you can perform routine maintenance. At +6, you "
					+ "can tear down engines and modify an AV. At+ I 0, you can design "
					+ "your own AVs on common airframes.") //
			.withType(TECH) //
			.build();
	private Skill basicTech = new Skill.SkillBuilder() //
			.withSkillName("Basic Tech (2)") //
			.withDescription("The required skills for building or repairing simple "
					+ "mechanical and electrical devices, such as car engines, "
					+ "television sets, etc. With a Basic Tech Skill of +3 or better, "
					+ "you can fix minor car problems, repair basic wiring, etc. A Basic "
					+ "Tech Skill of +6 or better can repair stereos and TVs, rebuild an "
					+ "engine, etc. A Basic Tech Skill of +9 or better can build a simple "
					+ "computer from scratch, put together a race car engine, and maintain "
					+ "any kind of industrial machinery. However, they do not know enough "
					+ "specialized knowledge to apply it to complex things such as aircraft "
					+ "(just like Mr. Goodwrench doesn't know how to build and service an F-16).") //
			.withType(TECH) //
			.build();
	private Skill cryotankOperation = new Skill.SkillBuilder() //
			.withSkillName("Cryotank Operation") //
			.withDescription("The required skill for operating, repairing and maintaining "
					+ "life suspension and body chilling devices. A minimum skill of +4 is "
					+ "required to chill down a healthy person. A minumum skill of +6 for "
					+ "chilling a wounded person.") //
			.withType(TECH) //
			.build();
	private Skill cyberdeckDesign = new Skill.SkillBuilder() //
			.withSkillName("Cyberdeck Design (2)") //
			.withDescription("The required skill for designing cyberdecks. At level +4, you "
					+ "can modify an existing cyberdeck for greater speed or memory. At "
					+ "level +6, you can design a deck equal to most existing designs. At +8, "
					+ "you can design decks that are substantially improved over existing designs.") //
			.withType(TECH) //
			.build();
	private Skill cyberTech = new Skill.SkillBuilder() //
			.withSkillName("CyberTech (2)") //
			.withDescription("The required skill for repairing and maintaining cyberwear. "
					+ "At level +2, you can keep your cyberwear tuned up and can replace "
					+ "its power batteries. At level +6, you can strip down most cyberwear "
					+ "and even make simple modifications. At level +8, you can design your "
					+ "own cyberwear to order.") //
			.withType(TECH) //
			.build();
	private Skill demolition = new Skill.SkillBuilder() //
			.withSkillName("Demolition (2)") //
			.withDescription("This skill allows the character to be knowledgeable in the "
					+ "use of explosives, as well as knowing the best explosives to use "
					+ "for which jobs, how to set timers and detonators, and how much "
					+ "explosive to use to accomplish a desired result.") //
			.withType(TECH) //
			.build();
	private Skill disguise = new Skill.SkillBuilder() //
			.withSkillName("Disguise") //
			.withDescription("The skill of disguising your character to resemble someone "
					+ "else, whether real or fictitious. This skill incorporates elements "
					+ "of both makeup and acting, although it is not the same as the "
					+ "ability to actually be an actor.") //
			.withType(TECH) //
			.build();
	private Skill electronics = new Skill.SkillBuilder() //
			.withSkillName("Electronics (2)") //
			.withDescription("The required skill for maintaining, repairing and modifying "
					+ "electronic instruments such as computers, personal electronics "
					+ "hardware, electronic security systems, cameras and monitors.") //
			.withType(TECH) //
			.build();
	private Skill electronicSecurity = new Skill.SkillBuilder() //
			.withSkillName("Electronic Security") //
			.withDescription("The skill of installing or countering electronic eyes, electronic "
					+ "locks, bugs and tracers, security cameras, pressure plates, etc. At "
					+ "level +3, you can jimmy or install most apartment locks and security "
					+ "cams. At +6, you can override most corporate office locks and traps. "
					+ "At +9, you can enter most high security areas with impunity.") //
			.withType(TECH) //
			.build();
	private Skill firstAid = new Skill.SkillBuilder() //
			.withSkillName("First Aid") //
			.withDescription("This skill allows the user to bind wounds, stop bleeding, "
					+ "and revive a stunned patient.\n\nFirst Aid involves cleaning and "
					+ "dressing the wounds, administering medication, setting broken "
					+ "limbs and putting on splints. When a character makes a successful "
					+ "First Aid skill check, the patient will recover at the rate of "
					+ "0.5 points per day. Example: A Light wound would be healed in 8 "
					+ "days. A Critical wound would heal in 24 days, a Mortal 3 wound in "
					+ "56 days. Only one check need be made. You may (within reason and "
					+ "at Referee's discretion), perform first aid on yourself. On an "
					+ "unsuccessful roll, the patient regains no points. New attempts "
					+ "may be made once per day until a successful roll is made.") //
			.withType(TECH) //
			.build();
	private Skill forgery = new Skill.SkillBuilder() //
			.withSkillName("Forgery") //
			.withDescription("The skill of copying and creating false documents and "
					+ "identifications. This skill may also be applied to the detection "
					+ "of same; if you can fake it, you can usually tell a fake as well.") //
			.withType(TECH) //
			.build();
	private Skill gyroTech = new Skill.SkillBuilder() //
			.withSkillName("Gyro Tech (3)") //
			.withDescription("The skill of repairing and maintaining rotorwing aircraft "
					+ "such as helicopters and gyrocopters.") //
			.withType(TECH) //
			.build();
	private Skill paintOrDraw = new Skill.SkillBuilder() //
			.withSkillName("Paint or Draw") //
			.withDescription("The skill of producing professional drawings. A Skill of "
					+ "+3 allows you to produce salable 'modern' art. A Skill of +6 will "
					+ "produce artwork that is recognizable and extremely pleasant to "
					+ "the eye-as well as salable. An artist with a Skill of +8 or greater "
					+ "will be nationally known, have exhibits in galleries, and have "
					+ "other lesser artists studying his style in art school.") //
			.withType(TECH) //
			.build();
	private Skill photoAndFilm = new Skill.SkillBuilder() //
			.withSkillName("Photo and Film") //
			.withDescription("The skill of producing professional-caliber photographs or "
					+ "motion pictures. A Skill of +2 allows you to make decent home movies. "
					+ "A Skill of +4 or better creates work capable of winning amateur "
					+ "contests. A Skill of +6 or better will produce work of the level of "
					+ "the average Playboy cover or rock video. A photographer or cinematographer "
					+ "with a Skill of +8 or better will be nationally known and probably famous.") //
			.withType(TECH) //
			.build();
	private Skill pharmacuticals = new Skill.SkillBuilder() //
			.withSkillName("Pharmacuticals (2)") //
			.withDescription("The skill of designing and manufacturing drugs and medicines. "
					+ "A minimum Chemistry skill of +4 is required. At +4, you can make "
					+ "asprin. At +6, you can make hallucinogenics or antibiotics. At level "
					+ "+9 you can build designer drugs tailored to individual body chemistries.") //
			.withType(TECH) //
			.build();
	private Skill pickLock = new Skill.SkillBuilder() //
			.withSkillName("Pick Lock") //
			.withDescription("The skill required to pick locks and break into sealed "
					+ "containers and rooms. At +3, you can jimmy most simple locks. At "
					+ "+6 you can crack most safes. At +9 or better, you have a rep as a "
					+ "master cracksman, and are known to all the major players in the Cyberpunk world.") //
			.withType(TECH) //
			.build();
	private Skill pickPocket = new Skill.SkillBuilder() //
			.withSkillName("Pick Pocket") //
			.withDescription("The required skill for picking pockets without being noticed, "
					+ "as well as 'shoplifting' small items. For ideas on levels of ability, "
					+ "see Pick Lock, above.") //
			.withType(TECH) //
			.build();
	private Skill playInstrument = new Skill.SkillBuilder() //
			.withSkillName("Play Instrument") //
			.withDescription("The skill of knowing how to play a musical instrument. You "
					+ "must take this skill separately for each type of instrument played. "
					+ "A Skill of +4 or higher will qualify your character to play professional "
					+ "'gigs'. A Skill of +8 and above will gain the musician some professional "
					+ "acclaim, possibly with recording contracts and command performances. "
					+ "At +10, you are widely acclaimed, have lots of Grammys, and regularly "
					+ "jam with Kerry Eurodyne.") //
			.withType(TECH) //
			.build();
	private Skill weaponsmith = new Skill.SkillBuilder() //
			.withSkillName("Weaponsmith (2)") //
			.withDescription("The required skill for repairing and maintaining weapons of "
					+ "all types. At level +2, you can do repairs and field stripping. At "
					+ "level +6, you can repair all types of weapons and make simple "
					+ "modifications. At level +8, you can design your own weapons to order.") //
			.withType(TECH) //
			.build();

	private Role cop = new Role.RoleBuilder() //
			.withName("Cop") //
			.withBlurb("Cops can range from private detectives, to beat cops, to government agents. "
					+ "As figures of authority, they have the ability to intimidate or control others through their position as lawmen.") //
			.withDescription(
					"In the old days, they only used to shoot at cops. Now you're lucky if you only take a slug. "
							+ "The Street is mean these days, filled with new drugs, new gangs, and new weapons that make an M-16 look like a kid's toy. "
							+ "If you're on a City Force, you know how bad it is. You're carrying at least four high caliber weapons, most of them full-auto types, "
							+ "wearing a Kevlar vest that'll stop 850 ft/lbs per square inch - and you're still outgunned and outflanked. Half the gangs are cyber to"
							+ " begin with - super speed, super reflexes, can see in the dark, carry weapons in their arms... The other half are freelance Corporate "
							+ "mercs - gangs hired by the Corps to enforce their policies on the Street. And there you are - a beat cop or detective in an armored "
							+ "squadcar, patrolling this jungle with the heavy predators.\r\n\r\n"
							+ "The Corporate Cops - now that's the life. Heavy weapons, full combat armor, Trauma Team backup, AV-4 assault vehicles and gyrocopters "
							+ "with miniguns. But they only patrol the sectors of the City that the Government's licensed them for. The nice, clean sectors full of "
							+ "new office buildings and fancy restaurants - where no jacked up psychopunk is going to ever go on a killing spree with an AK-47. You "
							+ "get the bad sections. Burned out buildings and abadoned cars, where every night is a new firefight and another great opportunity for "
							+ "a messy death.\r\n\r\n"
							+ "If you're really unlucky, you might draw PsychoSqaud detail. PsychoSquad guys get the job of hunting down heavily armed and armored "
							+ "cyborgs who've flipped out. Sure the PS guys have access to railguns, gyros and AVs. But a cyberpsycho can walk through machine gun "
							+ "fire and not feel it. A lot of the PsychoSquad detectives are crazy themselves. They load up with boosted reflexes, get some "
							+ "monstrously huge guns, and go hunt the cyborgs solo. But you're not that crazy.\r\n\r\n"
							+ "Yet.") //
			.addCareerSkill(authority) //
			.addCareerSkill(awarenessAndNotice) //
			.addCareerSkill(handgun) //
			.addCareerSkill(humanPerception) //
			.addCareerSkill(athletics) //
			.addCareerSkill(educationAndGeneralKnowledge) //
			.addCareerSkill(brawling) //
			.addCareerSkill(melee) //
			.addCareerSkill(interrogation) //
			.addCareerSkill(streetwise) //
			.build();
	private Role corporate = new Role.RoleBuilder() //
			.withName("Corporate") //
			.withBlurb(
					"Corporates are the Armani-wearing, Machiavellian mega-yuppies you see in the RoboCop films. Being wealthy and persuasive, they can muster "
							+ "favors and resources beyond what most people can even hope.\r\n\r\n"
							+ "\"Money. Yeah I got money: a new BMW aerodyne, and a penthouse flat in the Corporate Zone. All the money ain't worth frack. You "
							+ "play this game for power. The power to get things done; to make big decisions; to affect things. You make a phone call, and the next"
							+ " thing you know, you're telling the president of some bushleague Euronation that he'd better play it your way, or he's history. "
							+ "That's why you play. That's why I'm with the Company.\" -- An Unidentified Corporate.") //
			.withDescription(
					"In the old days, they would have called you a yuppie - a hard driven, fast-track MBA on his way up the Corporate ladder. Sure, it's selling your"
							+ " soul to the Company, but lets face it, the Corporations rule the cyberpunk world. They control governments, markets, nations, armies -"
							+ " you name it. You know that whoever controls the Corporations controls everything else.\r\n\r\n"
							+ "Right now, your life as a junior executive is anything but easy. There are guys underneath you who'd kill for a shot at your job. There"
							+ " are guys over you who'd kill to keep you out of their jobs. And they're not kidding about the killing - every up and comer in the "
							+ "Corporation has his own crew of Solos and Netrunners to cover his pet projects. Sabotage? Constantly. Bribery? Routine. Blackmail? "
							+ "Common. Promotion by assassination? Always a possibility. The stakes are that high - one slip and you could be out on the Street with the"
							+ " rest of the trash or dead.\r\n\r\n"
							+ "And the projects your supervisors give you! Some are pretty straightforward; design a new productivity schedule for the Corporation's "
							+ "medical subsidiary. Some are pretty raw - send a \"black operations\" team into the City to spread a designer plague so the Marketing team"
							+ " can clean up selling the vaccine. Last week, you led a mixed team of Solos, 'Runners and Techies on a headhunting run to kidnap a researcher"
							+ " from a rival company. The week before, your project was to steal plans for a new suborbital shuttle from the EuroSpace Agency (so that the "
							+ "Aerospace Division could copy the design and sell it to the Soviets).\r\n\r\n"
							+ "You told yourself you joined the Corporation to make it better place - work from the inside, you said. But now you're not sure. Your ideals "
							+ "are a little tarnished and things are getting pretty bleak. But you can't worry about ethics now. You've got a report due in an hour, and it "
							+ "looks like that guy in Sales is planning to ice your database for good. You're gonna ice him first.") //
			.addCareerSkill(resources) //
			.addCareerSkill(awarenessAndNotice) //
			.addCareerSkill(humanPerception) //
			.addCareerSkill(educationAndGeneralKnowledge) //
			.addCareerSkill(librarySearch) //
			.addCareerSkill(social) //
			.addCareerSkill(persuasionAndFastTalk) //
			.addCareerSkill(stockMarket) //
			.addCareerSkill(wardrobeAndStyle) //
			.addCareerSkill(personalGrooming) //
			.build();
	private Role fixer = new Role.RoleBuilder() //
			.withName("Fixer") //
			.withBlurb(
					"Fixers are the well-connected fencers, smugglers, and information brokers who apply their trade on the black market - like Lenny Nero from the movie "
							+ "Strange Days. As they are so well-connected to comings and going on the streets, they can locate, acquire and know about a desired person, "
							+ "place or thing within their area of operation. Protocol for exfiltration of payload from target ecosystem covers all traces through decoys "
							+ "and fakes so as to maximize" + " confusion.") //
			.withDescription(
					"You realized fast that you weren't ever going to get into a Corporate job. And you didn't think your were tough enough or crazy enough to be a Solo "
							+ "either. But as a small time punk, you knew you had a knack for figuring out what other people wanted, and how to get it for them. For a price,"
							+ " of course.\r\n\r\n"
							+ "Now your deals have moved past the nickle-and-dime stuff into the big time. Maybe you move illegal weapons over the border. Or steal and resell"
							+ " medical supplies from the Corporations. Perhaps you're a skill broker - acting as an agent for high priced Solos and Runners or even hiring a "
							+ "whole Nomad pack to back a client's contacts. You buy and sell favors like an old-style Mafia godfather. You have connections into all kinds of "
							+ "businesses, deals and political groups. You don't do this directly, of course - no, you see your contacts and allies as a part of vast web of "
							+ "intrigue and coercion. If there's a hot nightclub in the City, you've bought into it. If there are new military-class weapons on the Street, you "
							+ "smuggled'em in. If there's a Corporate war going down, you're negotiating between sides with an eye on the main chance.\r\n\r\n"
							+ "But you're not entirely in it for the bucks. If someone needs to get the heat off, you'll hide them. You get people housing when there isn't any, "
							+ "and you bring in food when the neighborhoods are blockaded. Maybe you do it because you know they'll owe you later, but you're not sure. You're "
							+ "one part Robin Hood and two parts Al Capone. Back in the 90's, they would have called you a crimelord. But this is the fragmented, deadly 2020s. "
							+ "Now they call you a Fixer.") //
			.addCareerSkill(streetdeal) //
			.addCareerSkill(awarenessAndNotice) //
			.addCareerSkill(brawling) //
			.addCareerSkill(forgery) //
			.addCareerSkill(handgun) //
			.addCareerSkill(intimidate) //
			.addCareerSkill(melee) //
			.addCareerSkill(persuasionAndFastTalk) //
			.addCareerSkill(pickLock) //
			.addCareerSkill(pickPocket) //
			.build();
	private Role media = new Role.RoleBuilder() //
			.withName("Media") //
			.withBlurb(
					"Media can range from desperate, attention-loving sensationalists to demagogues, but the playable ones are usually the creditable and outspoken mavericks in "
							+ "a world overrun by corporate-owned media straw-men. As long as they maintain their credibility as relevant journalists, people believe what they "
							+ "are saying, even if there are no facts to back-up what they are saying.") //
			.withDescription(
					"They're bending truth out there and you're going to stop them. Someone has to do it. The Corporations rule the world. They dump toxins, destabilize"
							+ " economies and commit murder with equal impunity. The Government won't stop them - they own the Government. The only thing between the Corporations"
							+ " and world domination is the Media and that's you.\r\n\r\n"
							+ "You've got a videocam and press pass - and you're not afraid to use them. You're a national figure, seen nightly on a million TV sets worldwide. "
							+ "You've got fans, contacts and your own Corporation backing you. They can't make you disappear. When you dig down for the dirt and slime the corrupt"
							+ " officials and Corporate lapdogs try to cover up, you can dig deep. The next morning, you can put the details of their crimes all over the "
							+ "screamsheets and vidscreens. Then the Government has to act.\r\n\r\n"
							+ "A week ago, you followed a hot lead and discovered a medical corporation dumping illegal drugs on the Street. This week, you're uncovered a secret "
							+ "Corporate war in South America - a war with jets, bombs, and cybertroops that's killed almost seven thousand innocent people. Each new story you get"
							+ " to the air is one more blow for freedom and justice. Not to mention ratings.\r\n\r\n"
							+ "It isn't easy. They've tried to pressure your Mediacorp dozens of times. You've had stories suppressed - once, Corporate pressure forced them to "
							+ "cancel your news show. Each time, you went to the top, backed by your news director and crew, and fought to get the story out. Three of four times, "
							+ "they tried to kill you - that's why your backup's a crack Solo bodyguard and you've got one of the top Runners in the business digging through the Net"
							+ " to back your stories. You have to be good, or else.\r\n\r\n"
							+ "Your Runner's just phoned in with a hot lead. He's found a line on twenty tons of illegal weapons being shifted to a port in Bolivia - possibly "
							+ "nuclear. You grab your gear and flag your backup. You're going to break those bastards.\r\n\r\n"
							+ "This time, for sure.") //
			.addCareerSkill(credibility) //
			.addCareerSkill(awarenessAndNotice) //
			.addCareerSkill(composition) //
			.addCareerSkill(educationAndGeneralKnowledge) //
			.addCareerSkill(persuasionAndFastTalk) //
			.addCareerSkill(humanPerception) //
			.addCareerSkill(social) //
			.addCareerSkill(streetwise) //
			.addCareerSkill(photoAndFilm) //
			.addCareerSkill(interview) //
			.build();
	private Role netrunner = new Role.RoleBuilder() //
			.withName("Netrunner") //
			.withBlurb(
					"Netrunners are the types of savvy computer hackers you would find in the movie Hackers, but with a cybernetically augmented interface system implanted into "
							+ "their body. Using their brain-computer interface implants, they roam the Internet, looking for systems to hack and information to sell to Fixers. "
							+ "Although anyone can enter the Net, most people can't use the \"Menu.\" The Menu is a group of Applications (Apps) that are Interface programs that "
							+ "allows a Netrunner to Locate Remote, Run Software, Control Remote, LDL Link, Load, Create and Delete.") //
			.withDescription(
					"At three, your parents bought you an old Apple IV GS with a Radius 241 wall screen, and your life was changed. By fifth grade, you'd already mastered everything "
							+ "the school computer literacy lab could throw at you — you were already using C++ and META-LINGUA to crack into the district's mainframe and change your "
							+ "grades. When you were thirteen, you shifted enough funds out of unprotected TransAmerican Bank accounts to finance your first neural interface plugs.\r\n\r\n"
							+ "Now, nothing can stop you with your direct mental link to the computer, you can plunge headfirst into the dizzying data-winds of the Net; the worldwide "
							+ "telecommunications system that joins humanity together. As an electronic wraith, you are the ultimate \"hacker\", your brain wired into special modems and "
							+ "computer links. You slip into the \"hardest\" mainframe systems with ease. Your defense and offense programs are arrayed at a touch of your mental fingertips "
							+ "— a quick jolt of Demon or Vampire and the data fortresses fall. EBM. ITT. Sony-Matsushita-Ford. You've tackled them all, buying, trading and selling their  "
							+ "deepest secrets at will.\r\n\r\n"
							+ "Sometimes you uncover important things — Corporate treachery or deadly secrets. But that's not why you Netrun. You live for the new program, the next "
							+ "satellite downlink — the next piece of hot data that comes your way. It's only a matter of time, you think — every year, the counter intrusion programs "
							+ "get better, the Artificial Intelligences smarter. Sooner or later, a faster program or programmer's going to catch up; reach out with electronic fingers through"
							+ " your interface plugs, and stop your heart. But time's on your side, and until the ride runs out, you'll be there, barebrained and headfirst in the Net") //
			.addCareerSkill(interf) //
			.addCareerSkill(awarenessAndNotice) //
			.addCareerSkill(basicTech) //
			.addCareerSkill(educationAndGeneralKnowledge) //
			.addCareerSkill(systemKnowledge) //
			.addCareerSkill(cyberTech) //
			.addCareerSkill(cyberdeckDesign) //
			.addCareerSkill(composition) //
			.addCareerSkill(electronics) //
			.addCareerSkill(programming) //
			.build();
	private Role nomad = new Role.RoleBuilder() //
			.withName("Nomad") //
			.withBlurb(
					"Nomads were once corporate wage-slaves, who got fired and blackballed from employment, and now they roam the highways as Gypsies and motor-gangs like something out of "
							+ "a Mad Max movie. As life on the road is hard, they maintain strong family bonds. If a Nomad is in trouble, he can count on members of his family to watch "
							+ "his back.") //
			.withDescription(
					"They drove your family off the Farm ten years ago. The Corporations rolled in, took over the land, and put rent-a-cops all over the place. It wasn't the first time "
							+ "it'd happened and it wouldn't be the last. Gradually, your family fell in with a bunch of other homeless families, and they met another group... until "
							+ "you'd create a Nomad pack of nearly two hundred members.\r\n\r\n"
							+ "Now, crammed into a huge, ragtag fleet of cars, vans, busses and RV's, your Nomad pack roams the freeways. You look for supplies, odd jobs and spare parts "
							+ "in the world where society has fragmented. The pack is your home - it has teachers, Med Techs, leaders, and mechanics - it's virtually a town on wheels in "
							+ "which everyone is related by marriage or kinship. Sometimes the Pack pulls into a town just to fuel up or get grub. Other times, it swings south to follow "
							+ "the harvest; you pick crops in trade for cash or food. Less terrorizing cities and hiring out as muscle in Corporate wars. For obvious reasons, the cops "
							+ "don't like Nomads. But it doesn't matter - your vehicle are usually well armored and bristling with stolen weapons; mini guns, rocket launchers and the like."
							+ " Every kid knows how to use rifle, and everyone packs a knife. Being homeless in the 2000's isn't easy.\r\n\r\n"
							+ "The most visible members of the Pack are the Scouts - leather armored riders on bikes or in fast muscle cars, who protect the convoy from attacks and hunt "
							+ "up safe campsites. As a Scout, you're on the lookout for trouble, and you usually can find enough of it, with rival Nomad Packs, the Law, and the cowboy, "
							+ "you ride the hard trail. You've got a gun, a bike and that's all you need. You're a Nomad.") //
			.addCareerSkill(family) //
			.addCareerSkill(athletics) //
			.addCareerSkill(awarenessAndNotice) //
			.addCareerSkill(basicTech) //
			.addCareerSkill(brawling) //
			.addCareerSkill(driving) //
			.addCareerSkill(endurance) //
			.addCareerSkill(melee) //
			.addCareerSkill(rifle) //
			.addCareerSkill(wildernessSurvival) //
			.build();
	private Role rockerboy = new Role.RoleBuilder() //
			.withName("Rockerboy") //
			.withBlurb(
					"Rockerboys are rebellious musicians who use music and revolt to fight authority. They are a lot like '80s punk rockers who look down on corporate \"sellouts\" as "
							+ "the traitors to the craft. Because they are so charismatic, they can sway, incite and charm a large number of people through musical performances.") //
			.withDescription(
					"If you live to rock, this is where you belong. Rockerboys are the street poets, social consciences and rebels of 2000's. With the advent of digital porta-studios and"
							+ " garage laser disk mastering, every Rocker with a message can take it to the street; put in the record stores, bounce it off the comsats.\r\n\r\n"
							+ "Sometimes, this message isn't something the Corporations or Government wants to hear. Sometimes what you say is going to get right in the faces of the "
							+ "powerful people who really run this world. But you don't care, because as a Rockerboy, you know it's your place to challenge. Whether in straight-out protest"
							+ " songs that tell it like it is or just by playing kick-ass rock'n'roll to get the people away from TV sets and into the Streets. You have a proud history "
							+ "as a Rockerboy - Dylan, Springsteen, Who, Elvis, the Stones - the legions of hardrock heroes who told the truth with screaming guitars and gut-honest "
							+ "lyrics.\r\n\r\n"
							+ "As a Rockerboy, you have the power to get the people up - to lead, inspire and inform. A song from you can give the timid courage, the weak strength, and "
							+ "blind vision. Rockerboy legends have led armies against Corporations and Governments. Rockerboy songs have exposed corruption, brought down dictators. It's "
							+ "a lot of power for a guy doing gigs every night in another city. But you can handle it. After all - you came to play!") //
			.addCareerSkill(charismaticLeadership) //
			.addCareerSkill(awarenessAndNotice) //
			.addCareerSkill(perform) //
			.addCareerSkill(wardrobeAndStyle) //
			.addCareerSkill(composition) //
			.addCareerSkill(brawling) //
			.addCareerSkill(playInstrument) //
			.addCareerSkill(streetwise) //
			.addCareerSkill(persuasionAndFastTalk) //
			.addCareerSkill(seduction) //
			.build();
	private Role solo = new Role.RoleBuilder() //
			.withName("Solo") //
			.withBlurb(
					"Solos are hired hit-men, bodyguards, and mercenaries - a lot like Jayne Cobb from Firefly. Due to their professionalism and constant training, they have "
							+ "the ability to perceive danger, notice traps, and have an almost unearthly ability to avoid harm.") //
			.withDescription(
					"You were reborn with a gun in your hand - the flesh and blood hand, not the metallic weapon factory that covers most of your arm. Whether as a freelance guard"
							+ " and killer-for-hire, or as one of the Corporate cybersoldiers that enforce business deals and the Company's \"black operations\", you're one of the "
							+ "elite fighting machines of the Cyberpunk world.\r\n\r\n"
							+ "Most Solos have put in military time, either in a Corporate army or in one of the Government's continual \"police actions\" around the world. As the "
							+ "battle damage piles up, you start to rely more and more upon hardware - cyberlimbs for weapons and armor, bio-program chips to increase your reflexes "
							+ "and awareness, combat drugs to give you that edge over your opponents. When you're the best of the best, you might even leave the ranks of Corporate "
							+ "samurai and go ronin - freelancing your lethal talents as killer, bodyguard or enforcer to whoever can pay your very high fees.\r\n\r\n"
							+ "Sounds good? There's a price - a heavy one. You're lost so much of your original meat body that you're almost a machine, Your killing reflexes are so "
							+ "jacked up that you have to restrain yourself from going berserk at any moment. Years of combat drugs taken to keep the edge have given you terrifying "
							+ "addictions. You can't trust anyone - your mother, your friends, your lovers - no one. One night you sleep in penthouse condo in the City - the next in a "
							+ "filthy alley on the Street. But that's the price of being the best.\r\n\r\n"
							+ "And you're willing to pay it. Because you're a Solo.") //
			.addCareerSkill(combatSense) //
			.addCareerSkill(awarenessAndNotice) //
			.addCareerSkill(handgun) //
			.addCareerSkill(brawling, martialArt1) //
			.addCareerSkill(melee) //
			.addCareerSkill(weaponsmith) //
			.addCareerSkill(rifle) //
			.addCareerSkill(athletics) //
			.addCareerSkill(submachinegun) //
			.addCareerSkill(stealth) //
			.build();
	private Role techie = new Role.RoleBuilder() //
			.withName("Techie") //
			.withBlurb(
					"Techies range from technicians to cybernetic specialists. They are usually underground techies, who do \"off-the-record\" work.") //
			.withDescription(
					"You can't leave anything alone - if it sits near you for more than five minutes, you've disassembled it and made it into something new. You've always got "
							+ "at least two screwdrivers and wrench in your pockets. Computer down? No problem. Hydrogen burner out in your Metrocar? No problem. Can't get the "
							+ "video to run or your interface plugs feedbacking? No problem.\r\n\r\n"
							+ "You make your living building, fixing and modifying - a crucial occupation in a technological world where no one person really knows how half the stuff "
							+ "works. You can make some good bucks fixing everyday stuff, but for the serious money, you need to tackle the big jobs. Illegal weapons. Illegal or "
							+ "stolen cybertech. Corporate espionage and counterespionage gear for big boys' \"black operations\". Neat little gadgets like thermite bombs and the "
							+ "hunter-killer robots for the occasional \"termination\".\r\n\r\n"
							+ "If you're any good, you're making a lot of money. And that money goes into new gadgets, hardware and information. You'll buy almost any new thing - "
							+ "because it might have dozen side applications you can use. Of course, your black market work isn't just making you friends - it's also racking you up "
							+ "an impressive number of enemies as well; people who've run into your handywork and resented it. So you'll invest a lot in defense systems and, if really "
							+ "pushed to the wall, call a few new markers on a Solo or two.\r\n\r\n"
							+ "Your cousin down the street is like you, but he's a Medtechie. In world where half of medicine is related to mechanics, it makes sense. He can do a black"
							+ " market surgical technique faster than you can fix a toaster and the Solos are always running to him to patch up wounds or install new illegal cybernetics."
							+ " He's got a lot of the same problems you have, but he's hoping his new job with Trauma Team Inc. Tm will loosen things up. You hope he's right. "
							+ "You may be needing his services sooner than you think.") //
			.addCareerSkill(juryRig) //
			.addCareerSkill(awarenessAndNotice) //
			.addCareerSkill(basicTech) //
			.addCareerSkill(cyberTech) //
			.addCareerSkill(educationAndGeneralKnowledge) //
			.addCareerSkill(electronics) //
			.addCareerSkill(teaching) //
			.addCareerSkill(aeroTech, electronicSecurity, gyroTech, weaponsmith) //
			.addCareerSkill(aeroTech, electronicSecurity, gyroTech, weaponsmith) //
			.addCareerSkill(aeroTech, electronicSecurity, gyroTech, weaponsmith) //
			.build();

	private Weapon budgetArmsC13 = new Weapon.WeaponBuilder() //
			.withName("BudgetArms C-13") //
			.withType("P") //
			.withCategory("LP") //
			.withAccuracy(-1) //
			.withConcealability("P") //
			.withAvailability("E") //
			.withDamageAndAmmo("1D6 (5mm)") //
			.withNumberOfShots(8) //
			.withRateOfFire(2) //
			.withReliability("ST") //
			.withRange("50m") //
			.withWeight(0.5) //
			.withCost(75) //
			.build();
	private Weapon daiLungCybermag15 = new Weapon.WeaponBuilder() //
			.withName("Dai Lung Cybermag") //
			.withType("P") //
			.withCategory("LP") //
			.withAccuracy(-1) //
			.withConcealability("P") //
			.withAvailability("C") //
			.withDamageAndAmmo("1D6+1 (6mm)") //
			.withNumberOfShots(10) //
			.withRateOfFire(2) //
			.withReliability("UR") //
			.withRange("50m") //
			.withWeight(0.5) //
			.withCost(50) //
			.build();
	private Weapon federatedArmsX22 = new Weapon.WeaponBuilder() //
			.withName("Federated Arms X-22") //
			.withType("P") //
			.withCategory("LP") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("E") //
			.withDamageAndAmmo("1D6+1 (6mm)") //
			.withNumberOfShots(10) //
			.withRateOfFire(2) //
			.withReliability("ST") //
			.withWeight(0.5) //
			.withRange("50m") //
			.withCost(150) //
			.build();
	private Weapon militechArmsAvenger = new Weapon.WeaponBuilder() //
			.withName("Militech Arms Avenger") //
			.withType("P") //
			.withCategory("MP") //
			.withAccuracy(0) //
			.withConcealability("J") //
			.withAvailability("E") //
			.withDamageAndAmmo("2D6+1 (9mm)") //
			.withNumberOfShots(10) //
			.withRateOfFire(2) //
			.withReliability("VR") //
			.withRange("50m") //
			.withWeight(1) //
			.withCost(250) //
			.build();
	private Weapon daiLungStreetmaster = new Weapon.WeaponBuilder() //
			.withName("Dai Lung Streetmaster") //
			.withType("P") //
			.withCategory("MP") //
			.withAccuracy(0) //
			.withConcealability("J") //
			.withAvailability("E") //
			.withDamageAndAmmo("2D6+3 (10mm)") //
			.withNumberOfShots(12) //
			.withRateOfFire(2) //
			.withReliability("UR") //
			.withRange("50m") //
			.withWeight(1) //
			.withCost(250) //
			.build();
	private Weapon federatedArmsX9mm = new Weapon.WeaponBuilder() //
			.withName("Federated Arms X-9mm") //
			.withType("P") //
			.withCategory("MP") //
			.withAccuracy(0) //
			.withConcealability("J") //
			.withAvailability("E") //
			.withDamageAndAmmo("2D6+1 (9mm)") //
			.withNumberOfShots(12) //
			.withRateOfFire(2) //
			.withReliability("ST") //
			.withRange("50m") //
			.withWeight(1) //
			.withCost(300) //
			.build();
	private Weapon budgetArmsAuto3 = new Weapon.WeaponBuilder() //
			.withName("Budget Arms Auto 3") //
			.withType("P") //
			.withCategory("HP") //
			.withAccuracy(-1) //
			.withConcealability("J") //
			.withAvailability("E") //
			.withDamageAndAmmo("3D6 (11mm)") //
			.withNumberOfShots(8) //
			.withRateOfFire(2) //
			.withReliability("UR") //
			.withRange("50m") //
			.withWeight(1) //
			.withCost(350) //
			.build();
	private Weapon sternmeyerType35 = new Weapon.WeaponBuilder() //
			.withName("Sternmeyer Type 35") //
			.withType("P") //
			.withCategory("HP") //
			.withAccuracy(0) //
			.withConcealability("J") //
			.withAvailability("C") //
			.withDamageAndAmmo("3D6 (11mm)") //
			.withNumberOfShots(8) //
			.withRateOfFire(2) //
			.withReliability("VR") //
			.withRange("50m") //
			.withWeight(1) //
			.withCost(400) //
			.build();
	private Weapon armalite44 = new Weapon.WeaponBuilder() //
			.withName("Armalite 44") //
			.withType("P") //
			.withCategory("VHP") //
			.withAccuracy(0) //
			.withConcealability("J") //
			.withAvailability("E") //
			.withDamageAndAmmo("4D6+1 (12mm)") //
			.withNumberOfShots(8) //
			.withRateOfFire(1) //
			.withReliability("ST") //
			.withRange("50m") //
			.withWeight(3) //
			.withCost(450) //
			.build();
	private Weapon coltAmtModel2000 = new Weapon.WeaponBuilder() //
			.withName("Colt AMT Model 2000") //
			.withType("P") //
			.withCategory("VHP") //
			.withAccuracy(0) //
			.withConcealability("J") //
			.withAvailability("C") //
			.withDamageAndAmmo("4D6+1 (12mm)") //
			.withNumberOfShots(8) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("50m") //
			.withWeight(3) //
			.withCost(500) //
			.build();
	private Weapon uziMiniauto9 = new Weapon.WeaponBuilder() //
			.withName("Uzi Miniauto 9") //
			.withType("SMG") //
			.withCategory("LSMG") //
			.withAccuracy(1) //
			.withConcealability("J") //
			.withAvailability("E") //
			.withDamageAndAmmo("2D6+1 (9mm)") //
			.withNumberOfShots(30) //
			.withRateOfFire(35) //
			.withReliability("VR") //
			.withRange("150m") //
			.withWeight(3) //
			.withCost(475) //
			.build();
	private Weapon hkMp2013 = new Weapon.WeaponBuilder() //
			.withName("H&K MP-2013") //
			.withType("SMG") //
			.withCategory("LSMG") //
			.withAccuracy(1) //
			.withConcealability("J") //
			.withAvailability("C") //
			.withDamageAndAmmo("2D6+3 (10mm)") //
			.withNumberOfShots(35) //
			.withRateOfFire(32) //
			.withReliability("ST") //
			.withRange("150m") //
			.withWeight(3) //
			.withCost(450) //
			.build();
	private Weapon fedArmsTechAssaultII = new Weapon.WeaponBuilder() //
			.withName("Federated Arms Tech Assault II") //
			.withType("SMG") //
			.withCategory("LSMG") //
			.withAccuracy(1) //
			.withConcealability("J") //
			.withAvailability("C") //
			.withDamageAndAmmo("1D6+1 (6mm)") //
			.withNumberOfShots(50) //
			.withRateOfFire(25) //
			.withReliability("ST") //
			.withRange("150m") //
			.withWeight(3) //
			.withCost(400) //
			.build();
	private Weapon arasakaMinami10 = new Weapon.WeaponBuilder() //
			.withName("Arasaka Minami 10") //
			.withType("SMG") //
			.withCategory("MSMG") //
			.withAccuracy(0) //
			.withConcealability("J") //
			.withAvailability("E") //
			.withDamageAndAmmo("2D6+3 (10mm)") //
			.withNumberOfShots(40) //
			.withRateOfFire(20) //
			.withReliability("VR") //
			.withRange("200m") //
			.withWeight(3) //
			.withCost(500) //
			.build();
	private Weapon hkMpk9 = new Weapon.WeaponBuilder() //
			.withName("H&K MPK-9") //
			.withType("SMG") //
			.withCategory("MSMG") //
			.withAccuracy(1) //
			.withConcealability("J") //
			.withAvailability("C") //
			.withDamageAndAmmo("2D6+1 (9mm)") //
			.withNumberOfShots(35) //
			.withRateOfFire(25) //
			.withReliability("ST") //
			.withRange("200m") //
			.withWeight(3) //
			.withCost(520) //
			.build();
	private Weapon sternmeyerSmg21 = new Weapon.WeaponBuilder() //
			.withName("Sternmeyer SMG 21") //
			.withType("SMG") //
			.withCategory("HSMG") //
			.withAccuracy(-1) //
			.withConcealability("L") //
			.withAvailability("E") //
			.withDamageAndAmmo("3D6 (11mm)") //
			.withNumberOfShots(30) //
			.withRateOfFire(15) //
			.withReliability("VR") //
			.withRange("200m") //
			.withWeight(3) //
			.withCost(500) //
			.build();
	private Weapon hkMpk11 = new Weapon.WeaponBuilder() //
			.withName("H&K MPK-11") //
			.withType("SMG") //
			.withCategory("HSMG") //
			.withAccuracy(0) //
			.withConcealability("L") //
			.withAvailability("E") //
			.withDamageAndAmmo("3D6 (11mm)") //
			.withNumberOfShots(30) //
			.withRateOfFire(15) //
			.withReliability("ST") //
			.withRange("200m") //
			.withWeight(3) //
			.withCost(700) //
			.build();
	private Weapon ingramMac14 = new Weapon.WeaponBuilder() //
			.withName("Ingram MAC 14") //
			.withType("SMG") //
			.withCategory("HSMG") //
			.withAccuracy(-2) //
			.withConcealability("L") //
			.withAvailability("E") //
			.withDamageAndAmmo("4D6+1 (12mm)") //
			.withNumberOfShots(20) //
			.withRateOfFire(10) //
			.withReliability("ST") //
			.withRange("200m") //
			.withWeight(3) //
			.withCost(650) //
			.build();
	private Weapon militechRoninLightAssault = new Weapon.WeaponBuilder() //
			.withName("Militech Ronin Light Assault") //
			.withType("RIF") //
			.withCategory("RIF") //
			.withAccuracy(1) //
			.withConcealability("N") //
			.withAvailability("C") //
			.withDamageAndAmmo("5D6 (5.56)") //
			.withNumberOfShots(35) //
			.withRateOfFire(30) //
			.withReliability("VR") //
			.withRange("400m") //
			.withWeight(4) //
			.withCost(450) //
			.build();
	private Weapon akr20MediumAssault = new Weapon.WeaponBuilder() //
			.withName("AKR-20 Medium Assault") //
			.withType("RIF") //
			.withCategory("RIF") //
			.withAccuracy(0) //
			.withConcealability("N") //
			.withAvailability("C") //
			.withDamageAndAmmo("5D6 (5.56)") //
			.withNumberOfShots(30) //
			.withRateOfFire(30) //
			.withReliability("ST") //
			.withRange("400m") //
			.withWeight(4) //
			.withCost(500) //
			.build();
	private Weapon fnRalHeavyAssaultRifle = new Weapon.WeaponBuilder() //
			.withName("FN-RAL Heavy Assault Rifle") //
			.withType("RIF") //
			.withCategory("RIF") //
			.withAccuracy(-1) //
			.withConcealability("N") //
			.withAvailability("C") //
			.withDamageAndAmmo("6D6+2 (7.62)") //
			.withNumberOfShots(30) //
			.withRateOfFire(30) //
			.withReliability("VR") //
			.withRange("400m") //
			.withWeight(4) //
			.withCost(600) //
			.build();
	private Weapon kalishnikovA80HeavyRifle = new Weapon.WeaponBuilder() //
			.withName("Kalishnikov A-80 Heavy Rifle") //
			.withType("RIF") //
			.withCategory("RIF") //
			.withAccuracy(-1) //
			.withConcealability("N") //
			.withAvailability("E") //
			.withDamageAndAmmo("6D6+2 (7.62)") //
			.withNumberOfShots(35) //
			.withRateOfFire(25) //
			.withReliability("ST") //
			.withRange("400m") //
			.withWeight(4) //
			.withCost(550) //
			.build();
	private Weapon arasakaRapidAssault12 = new Weapon.WeaponBuilder() //
			.withName("Arasaka Rapid Assault 12") //
			.withType("SHG") //
			.withCategory("SHG") //
			.withAccuracy(-1) //
			.withConcealability("N") //
			.withAvailability("C") //
			.withDamageAndAmmo("4D6 (00)") //
			.withNumberOfShots(20) //
			.withRateOfFire(10) //
			.withReliability("ST") //
			.withRange("50m") //
			.withWeight(3) //
			.withCost(900) //
			.build();
	private Weapon sternmeyerStakeout10 = new Weapon.WeaponBuilder() //
			.withName("Sternmeyer Stakeout 10") //
			.withType("SHG") //
			.withCategory("SHG") //
			.withAccuracy(-2) //
			.withConcealability("N") //
			.withAvailability("R") //
			.withDamageAndAmmo("4D6 (00)") //
			.withNumberOfShots(10) //
			.withRateOfFire(2) //
			.withReliability("ST") //
			.withRange("50m") //
			.withWeight(3) //
			.withCost(450) //
			.build();
	private Weapon barrettArasakaLight20mm = new Weapon.WeaponBuilder() //
			.withName("Barrett-Arasaka Light 20mm") //
			.withType("HVY") //
			.withCategory("HVY") //
			.withAccuracy(0) //
			.withConcealability("N") //
			.withAvailability("R") //
			.withDamageAndAmmo("4D10AP (20mm)") //
			.withNumberOfShots(10) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("450m") //
			.withCost(2000) //
			.build();
	private Weapon scorpion16MissileLauncher = new Weapon.WeaponBuilder() //
			.withName("Scorpion 16 Missile Launcher") //
			.withType("HVY") //
			.withCategory("HVY") //
			.withAccuracy(-1) //
			.withConcealability("N") //
			.withAvailability("R") //
			.withDamageAndAmmo("7D10") //
			.withNumberOfShots(1) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("1000m") //
			.withCost(3000) //
			.build();
	private Weapon militechArmsRpgA = new Weapon.WeaponBuilder() //
			.withName("Militech Arms RPG-A") //
			.withType("HVY") //
			.withCategory("HVY") //
			.withAccuracy(-2) //
			.withConcealability("N") //
			.withAvailability("R") //
			.withDamageAndAmmo("6D10") //
			.withNumberOfShots(1) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("750m") //
			.withCost(1500) //
			.build();
	private Weapon fragmentationGrenade = new Weapon.WeaponBuilder() //
			.withName("Fragmentation Grenade") //
			.withType("HVY") //
			.withCategory("HVY") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("P") //
			.withDamageAndAmmo("7D6") //
			.withNumberOfShots(1) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("Throw") //
			.withCost(30) //
			.build();
	private Weapon incendiaryGrenade = new Weapon.WeaponBuilder() //
			.withName("Incendiary Grenade") //
			.withType("HVY") //
			.withCategory("HVY") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("P") //
			.withDamageAndAmmo("4D6 for 3 turns") //
			.withNumberOfShots(1) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("Throw") //
			.withCost(30) //
			.build();
	private Weapon stunGrenade = new Weapon.WeaponBuilder() //
			.withName("Stun Grenade") //
			.withType("HVY") //
			.withCategory("HVY") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("P") //
			.withDamageAndAmmo("-5 to Stun") //
			.withNumberOfShots(1) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("Throw") //
			.withCost(30) //
			.build();
	private Weapon dazzleGrenade = new Weapon.WeaponBuilder() //
			.withName("Dazzle Grenade") //
			.withType("HVY") //
			.withCategory("HVY") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("P") //
			.withDamageAndAmmo("Blind for 4 turns") //
			.withNumberOfShots(1) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("Throw") //
			.withCost(30) //
			.build();
	private Weapon sonicGrenade = new Weapon.WeaponBuilder() //
			.withName("Sonic Grenade") //
			.withType("HVY") //
			.withCategory("HVY") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("P") //
			.withDamageAndAmmo("Deafened for 4 turns") //
			.withNumberOfShots(1) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("Throw") //
			.withCost(30) //
			.build();
	private Weapon nauseaGasGrenade = new Weapon.WeaponBuilder() //
			.withName("Nausea Gas Grenade") //
			.withType("HVY") //
			.withCategory("HVY") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("P") //
			.withDamageAndAmmo("-4 REF") //
			.withNumberOfShots(1) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("Throw") //
			.withCost(30) //
			.build();
	private Weapon teargasGrenade = new Weapon.WeaponBuilder() //
			.withName("Teargas Grenade") //
			.withType("HVY") //
			.withCategory("HVY") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("P") //
			.withDamageAndAmmo("-2 REF") //
			.withNumberOfShots(1) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("Throw") //
			.withCost(30) //
			.build();
	private Weapon sleepDrugGrenade = new Weapon.WeaponBuilder() //
			.withName("Sleep Drug Grenade") //
			.withType("HVY") //
			.withCategory("HVY") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("P") //
			.withDamageAndAmmo("Sleep / -2 to all stats") //
			.withNumberOfShots(1) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("Throw") //
			.withCost(30) //
			.build();
	private Weapon biotoxin1Grenade = new Weapon.WeaponBuilder() //
			.withName("Biotoxin 1 Grenade") //
			.withType("HVY") //
			.withCategory("HVY") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("P") //
			.withDamageAndAmmo("4D6") //
			.withNumberOfShots(1) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("Throw") //
			.withCost(30) //
			.build();
	private Weapon biotoxinIIGrenade = new Weapon.WeaponBuilder() //
			.withName("Biotoxin II Grenade") //
			.withType("HVY") //
			.withCategory("HVY") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("P") //
			.withDamageAndAmmo("8D6") //
			.withNumberOfShots(1) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("Throw") //
			.withCost(30) //
			.build();
	private Weapon nerveGasGrenade = new Weapon.WeaponBuilder() //
			.withName("Nerve Gas Grenade") //
			.withType("HVY") //
			.withCategory("HVY") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("P") //
			.withDamageAndAmmo("8D10") //
			.withNumberOfShots(1) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("Throw") //
			.withCost(30) //
			.build();
	private Weapon plastiqueExplosive = new Weapon.WeaponBuilder() //
			.withName("Plastique Explosive") //
			.withType("HVY") //
			.withCategory("HVY") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("P") //
			.withDamageAndAmmo("7D10 per kg.") //
			.withNumberOfShots(1) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("4m") //
			.withCost(100) //
			.build();
	private Weapon c6PlasticExplosive = new Weapon.WeaponBuilder() //
			.withName("C-6 Plastic Explosive") //
			.withType("HVY") //
			.withCategory("HVY") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("P") //
			.withDamageAndAmmo("8D10 per kg.") //
			.withNumberOfShots(1) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("5m") //
			.withCost(100) //
			.build();
	private Weapon tnt = new Weapon.WeaponBuilder() //
			.withName("TNT") //
			.withType("HVY") //
			.withCategory("HVY") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("P") //
			.withDamageAndAmmo("4D10 per stick") //
			.withNumberOfShots(1) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("3m") //
			.withCost(100) //
			.build();
	private Weapon molotovCocktail = new Weapon.WeaponBuilder() //
			.withName("Molotov Cocktail") //
			.withType("HVY") //
			.withCategory("HVY") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("P") //
			.withDamageAndAmmo("2D10") //
			.withNumberOfShots(1) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("2m") //
			.withCost(100) //
			.build();
	private Weapon landMine = new Weapon.WeaponBuilder() //
			.withName("Land Mine") //
			.withType("HVY") //
			.withCategory("HVY") //
			.withAccuracy(0) //
			.withConcealability("J") //
			.withAvailability("P") //
			.withDamageAndAmmo("4D10") //
			.withNumberOfShots(1) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("NA") //
			.withCost(350) //
			.build();
	private Weapon claymore = new Weapon.WeaponBuilder() //
			.withName("Claymore") //
			.withType("HVY") //
			.withCategory("HVY") //
			.withAccuracy(0) //
			.withConcealability("J") //
			.withAvailability("P") //
			.withDamageAndAmmo("4D10") //
			.withNumberOfShots(1) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("NA") //
			.withCost(350) //
			.build();
	private Weapon kaF253Flamethrower = new Weapon.WeaponBuilder() //
			.withName("K-A F-253 Flamethrower") //
			.withType("HVY") //
			.withCategory("HVY") //
			.withAccuracy(-2) //
			.withConcealability("N") //
			.withAvailability("R") //
			.withDamageAndAmmo("2D10+") //
			.withNumberOfShots(10) //
			.withRateOfFire(1) //
			.withReliability("ST") //
			.withRange("50m") //
			.withCost(1500) //
			.build();
	private Weapon techtronica15Microwaver = new Weapon.WeaponBuilder() //
			.withName("Techtronica 15 Microwaver") //
			.withType("P") //
			.withCategory("P") //
			.withAccuracy(0) //
			.withConcealability("J") //
			.withAvailability("P") //
			.withDamageAndAmmo("1D6") //
			.withNumberOfShots(10) //
			.withRateOfFire(2) //
			.withReliability("VR") //
			.withRange("20m") //
			.withCost(400) //
			.build();
	private Weapon militechElectricLaserCannon = new Weapon.WeaponBuilder() //
			.withName("Militech Electrics Laser Cannon") //
			.withType("RIF") //
			.withCategory("RIF") //
			.withAccuracy(0) //
			.withConcealability("N") //
			.withAvailability("R") //
			.withDamageAndAmmo("1-5D6") //
			.withNumberOfShots(10) //
			.withRateOfFire(2) //
			.withReliability("UR") //
			.withRange("200m") //
			.withWeight(4) //
			.withCost(8000) //
			.build();
	private Weapon avanteP1135Needlegun = new Weapon.WeaponBuilder() //
			.withName("Avante P-1135 Needlegun") //
			.withType("P") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("P") //
			.withDamageAndAmmo("Drugs") //
			.withNumberOfShots(15) //
			.withRateOfFire(2) //
			.withReliability("ST") //
			.withRange("40m") //
			.withCost(200) //
			.build();
	private Weapon enertexAkmPowerSquirt = new Weapon.WeaponBuilder() //
			.withName("Enertex AKM Power Squirt") //
			.withType("P") //
			.withAccuracy(-2) //
			.withConcealability("J") //
			.withAvailability("C") //
			.withDamageAndAmmo("Drugs") //
			.withNumberOfShots(50) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("10m") //
			.withCost(15) //
			.build();
	private Weapon nelspotWombat = new Weapon.WeaponBuilder() //
			.withName("Nelspot \"Wombat\"") //
			.withType("P") //
			.withCategory("P") //
			.withAccuracy(-1) //
			.withConcealability("J") //
			.withAvailability("C") //
			.withDamageAndAmmo("Drugs") //
			.withNumberOfShots(20) //
			.withRateOfFire(2) //
			.withReliability("UR") //
			.withRange("40m") //
			.withCost(200) //
			.build();
	private Weapon militechElectronicsTaser = new Weapon.WeaponBuilder() //
			.withName("Militech Electronics Taser") //
			.withType("P") //
			.withCategory("P") //
			.withAccuracy(-1) //
			.withConcealability("J") //
			.withAvailability("C") //
			.withDamageAndAmmo("Stun") //
			.withNumberOfShots(10) //
			.withRateOfFire(1) //
			.withReliability("ST") //
			.withRange("10m") //
			.withCost(60) //
			.build();
	private Weapon eagleTechTomcatCBow = new Weapon.WeaponBuilder() //
			.withName("EagleTech \"Tomcat\" C-Bow") //
			.withType("EX") //
			.withAccuracy(0) //
			.withConcealability("N") //
			.withAvailability("C") //
			.withDamageAndAmmo("4D6") //
			.withNumberOfShots(12) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("150m") //
			.withCost(150) //
			.build();
	private Weapon eagleTechStrykerXBow = new Weapon.WeaponBuilder() //
			.withName("EagleTech \"Stryker\" X-Bow") //
			.withType("EX") //
			.withAccuracy(-1) //
			.withConcealability("N") //
			.withAvailability("C") //
			.withDamageAndAmmo("3D6+3") //
			.withNumberOfShots(12) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("50m") //
			.withCost(220) //
			.build();
	private Weapon kendachiMonoknife = new Weapon.WeaponBuilder() //
			.withName("Kendachi Monoknife") //
			.withType("Melee") //
			.withCategory("Melee") //
			.withAccuracy(1) //
			.withConcealability("P") //
			.withAvailability("P") //
			.withDamageAndAmmo("2D6") //
			.withNumberOfShots(0) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("1m") //
			.withWeight(0.5) //
			.withCost(200) //
			.build();
	private Weapon kendachiMonoKatana = new Weapon.WeaponBuilder() //
			.withName("Kendachi MonoKatana") //
			.withType("Melee") //
			.withCategory("Melee") //
			.withAccuracy(1) //
			.withConcealability("N") //
			.withAvailability("P") //
			.withDamageAndAmmo("4D6") //
			.withNumberOfShots(0) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("1m") //
			.withWeight(1) //
			.withCost(600) //
			.build();
	private Weapon spm1Battleglove = new Weapon.WeaponBuilder() //
			.withName("SPM-1 Battleglove") //
			.withType("Melee") //
			.withCategory("Melee") //
			.withAccuracy(-2) //
			.withConcealability("N") //
			.withAvailability("P") //
			.withDamageAndAmmo("3D6/2D6") //
			.withNumberOfShots(0) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("1m") //
			.withWeight(2) //
			.withCost(900) //
			.build();
	private Weapon club = new Weapon.WeaponBuilder() //
			.withName("Club") //
			.withType("Melee") //
			.withCategory("Melee") //
			.withAccuracy(0) //
			.withConcealability("L") //
			.withAvailability("C") //
			.withDamageAndAmmo("1D6") //
			.withNumberOfShots(0) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("1m") //
			.withWeight(1) //
			.withCost(20) //
			.build();
	private Weapon knife = new Weapon.WeaponBuilder() //
			.withName("Knife") //
			.withType("Melee") //
			.withCategory("Melee") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("C") //
			.withDamageAndAmmo("1D6") //
			.withNumberOfShots(0) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("1m") //
			.withWeight(0.5) //
			.withCost(20) //
			.build();
	private Weapon sword = new Weapon.WeaponBuilder() //
			.withName("Sword") //
			.withType("Melee") //
			.withCategory("Melee") //
			.withAccuracy(0) //
			.withConcealability("N") //
			.withAvailability("C") //
			.withDamageAndAmmo("2D6+2") //
			.withNumberOfShots(0) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("1m") //
			.withWeight(1) //
			.withCost(200) //
			.build();
	private Weapon axe = new Weapon.WeaponBuilder() //
			.withName("Axe") //
			.withType("Melee") //
			.withCategory("Melee") //
			.withAccuracy(-1) //
			.withConcealability("N") //
			.withAvailability("C") //
			.withDamageAndAmmo("2D6+3") //
			.withNumberOfShots(0) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("1m") //
			.withWeight(1) //
			.withCost(20) //
			.build();
	private Weapon nunchaku = new Weapon.WeaponBuilder() //
			.withName("Nunchaku") //
			.withType("Melee") //
			.withCategory("Melee") //
			.withAccuracy(0) //
			.withConcealability("L") //
			.withAvailability("C") //
			.withDamageAndAmmo("3D6") //
			.withNumberOfShots(0) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("1m") //
			.withWeight(0.5) //
			.withCost(15) //
			.build();
	private Weapon naginata = new Weapon.WeaponBuilder() //
			.withName("Naginata") //
			.withType("Melee") //
			.withCategory("Melee") //
			.withAccuracy(0) //
			.withConcealability("N") //
			.withAvailability("P") //
			.withDamageAndAmmo("3D6") //
			.withNumberOfShots(0) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("2m") //
			.withWeight(0.5) //
			.withCost(100) //
			.build();
	private Weapon shiriken = new Weapon.WeaponBuilder() //
			.withName("Shiriken") //
			.withType("Melee") //
			.withCategory("Melee") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("C") //
			.withDamageAndAmmo("1D6/3") //
			.withNumberOfShots(0) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("Throw") //
			.withWeight(0.5) //
			.withCost(3) //
			.build();
	private Weapon switchblade = new Weapon.WeaponBuilder() //
			.withName("Switchblade") //
			.withType("Melee") //
			.withCategory("Melee") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("C") //
			.withDamageAndAmmo("1D6/2") //
			.withNumberOfShots(0) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("1m") //
			.withWeight(0.5) //
			.withCost(15) //
			.build();
	private Weapon brassKnuckles = new Weapon.WeaponBuilder() //
			.withName("Brass Knuckles") //
			.withType("Melee") //
			.withCategory("Melee") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("C") //
			.withDamageAndAmmo("1D6+2") //
			.withNumberOfShots(0) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("1m") //
			.withWeight(0.5) //
			.withCost(10) //
			.build();
	private Weapon sledgehammer = new Weapon.WeaponBuilder() //
			.withName("Sledgehammer") //
			.withType("Melee") //
			.withCategory("Melee") //
			.withAccuracy(-1) //
			.withConcealability("N") //
			.withAvailability("C") //
			.withDamageAndAmmo("4D6") //
			.withNumberOfShots(0) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("1m") //
			.withWeight(4) //
			.withCost(20) //
			.build();
	private Weapon chainsaw = new Weapon.WeaponBuilder() //
			.withName("Chainsaw") //
			.withType("Melee") //
			.withCategory("Melee") //
			.withAccuracy(-3) //
			.withConcealability("N") //
			.withAvailability("C") //
			.withDamageAndAmmo("4D6") //
			.withNumberOfShots(0) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("2m") //
			.withWeight(4) //
			.withCost(80) //
			.build();
	private Weapon swCombatMagnum = new Weapon.WeaponBuilder() //
			.withName("S&W Combat Magnum") //
			.withType("P") //
			.withCategory("HP") //
			.withAccuracy(1) //
			.withConcealability("J") //
			.withAvailability("C") //
			.withDamageAndAmmo("2D6+3 (.357)") //
			.withNumberOfShots(6) //
			.withRateOfFire(2) //
			.withReliability("VR") //
			.withRange("50m") //
			.withWeight(1) //
			.withCost(500) //
			.build();
	private Weapon llamaCommanche = new Weapon.WeaponBuilder() //
			.withName("Llama Commanche") //
			.withType("P") //
			.withCategory("HP") //
			.withAccuracy(0) //
			.withConcealability("J") //
			.withAvailability("C") //
			.withDamageAndAmmo("4D6 (.44)") //
			.withNumberOfShots(6) //
			.withRateOfFire(1) //
			.withReliability("ST") //
			.withRange("50m") //
			.withWeight(1) //
			.withCost(400) //
			.build();
	private Weapon colt45Peacemaker = new Weapon.WeaponBuilder() //
			.withName("Colt .45 \"Peacemaker\"") //
			.withType("P") //
			.withCategory("VHP") //
			.withAccuracy(0) //
			.withConcealability("J") //
			.withAvailability("C") //
			.withDamageAndAmmo("2D6+2 (.45)") //
			.withNumberOfShots(6) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("50m") //
			.withWeight(3) //
			.withCost(100) //
			.build();
	private Weapon colt38Detective = new Weapon.WeaponBuilder() //
			.withName("Colt .38 \"Detective\"") //
			.withType("P") //
			.withCategory("LP") //
			.withAccuracy(1) //
			.withConcealability("J") //
			.withAvailability("C") //
			.withDamageAndAmmo("1D6+2 (.38)") //
			.withNumberOfShots(6) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("50m") //
			.withWeight(0.5) //
			.withCost(200) //
			.build();
	private Weapon cop357Derringer = new Weapon.WeaponBuilder() //
			.withName("C.O.P. .357 Derringer") //
			.withType("P") //
			.withCategory("HP") //
			.withAccuracy(0) //
			.withConcealability("P") //
			.withAvailability("C") //
			.withDamageAndAmmo("2D6+3 (.357)") //
			.withNumberOfShots(4) //
			.withRateOfFire(2) //
			.withReliability("VR") //
			.withRange("50m") //
			.withWeight(1) //
			.withCost(250) //
			.build();
	private Weapon uzi = new Weapon.WeaponBuilder() //
			.withName("UZI") //
			.withType("SMG") //
			.withCategory("MSMG") //
			.withAccuracy(2) //
			.withConcealability("J") //
			.withAvailability("C") //
			.withDamageAndAmmo("2D6+1 (9mm)") //
			.withNumberOfShots(30) //
			.withRateOfFire(20) //
			.withReliability("VR") //
			.withRange("200m") //
			.withWeight(3) //
			.withCost(250) //
			.build();
	private Weapon vz61Skorpion = new Weapon.WeaponBuilder() //
			.withName("Vz61 Skorpion") //
			.withType("SMG") //
			.withCategory("LSMG") //
			.withAccuracy(2) //
			.withConcealability("J") //
			.withAvailability("P") //
			.withDamageAndAmmo("1D6 (.25)") //
			.withNumberOfShots(20) //
			.withRateOfFire(25) //
			.withReliability("VR") //
			.withRange("150m") //
			.withWeight(3) //
			.withCost(150) //
			.build();
	private Weapon ingramMac10 = new Weapon.WeaponBuilder() //
			.withName("Ingram MAC 10") //
			.withType("SMG") //
			.withCategory("MSMG") //
			.withAccuracy(-1) //
			.withConcealability("J") //
			.withAvailability("C") //
			.withDamageAndAmmo("2D6+2 (.45)") //
			.withNumberOfShots(30) //
			.withRateOfFire(5) //
			.withReliability("UR") //
			.withRange("200m") //
			.withWeight(3) //
			.withCost(225) //
			.build();
	private Weapon hkMp5 = new Weapon.WeaponBuilder() //
			.withName("H&K MP5") //
			.withType("SMG") //
			.withCategory("MSMG") //
			.withAccuracy(1) //
			.withConcealability("L") //
			.withAvailability("C") //
			.withDamageAndAmmo("2D6+1 (9mm)") //
			.withNumberOfShots(30) //
			.withRateOfFire(20) //
			.withReliability("VR") //
			.withRange("100m") //
			.withWeight(3) //
			.withCost(320) //
			.build();
	private Weapon thompsonM1 = new Weapon.WeaponBuilder() //
			.withName("Thompson M1") //
			.withType("SMG") //
			.withCategory("MSMG") //
			.withAccuracy(2) //
			.withConcealability("N") //
			.withAvailability("C") //
			.withDamageAndAmmo("2D6+2 (.45)") //
			.withNumberOfShots(30) //
			.withRateOfFire(20) //
			.withReliability("VR") //
			.withRange("200m") //
			.withWeight(3) //
			.withCost(300) //
			.build();
	private Weapon bushmaster = new Weapon.WeaponBuilder() //
			.withName("Bushmaster") //
			.withType("SMG") //
			.withCategory("HSMG") //
			.withAccuracy(0) //
			.withConcealability("N") //
			.withAvailability("R") //
			.withDamageAndAmmo("4D6 (5.56)") //
			.withNumberOfShots(30) //
			.withRateOfFire(20) //
			.withReliability("ST") //
			.withRange("200m") //
			.withWeight(3) //
			.withCost(300) //
			.build();
	private Weapon fnFal = new Weapon.WeaponBuilder() //
			.withName("FN-FAL") //
			.withType("RIF") //
			.withCategory("RIF") //
			.withAccuracy(0) //
			.withConcealability("N") //
			.withAvailability("E") //
			.withDamageAndAmmo("6D6+2 (7.56)") //
			.withNumberOfShots(20) //
			.withRateOfFire(21) //
			.withReliability("VR") //
			.withRange("400m") //
			.withWeight(4) //
			.withCost(450) //
			.build();
	private Weapon ak47 = new Weapon.WeaponBuilder() //
			.withName("AK 47") //
			.withType("RIF") //
			.withCategory("RIF") //
			.withAccuracy(0) //
			.withConcealability("N") //
			.withAvailability("E") //
			.withDamageAndAmmo("5D6 (7.565)") //
			.withNumberOfShots(30) //
			.withRateOfFire(20) //
			.withReliability("VR") //
			.withRange("400m") //
			.withWeight(4) //
			.withCost(200) //
			.build();
	private Weapon m16A = new Weapon.WeaponBuilder() //
			.withName("M-16A") //
			.withType("RIF") //
			.withCategory("RIF") //
			.withAccuracy(2) //
			.withConcealability("N") //
			.withAvailability("C") //
			.withDamageAndAmmo("4D6 (5.56)") //
			.withNumberOfShots(30) //
			.withRateOfFire(25) //
			.withReliability("UR") //
			.withRange("400m") //
			.withWeight(4) //
			.withCost(200) //
			.build();
	private Weapon styerAug = new Weapon.WeaponBuilder() //
			.withName("Styer AUG") //
			.withType("RIF") //
			.withCategory("RIF") //
			.withAccuracy(2) //
			.withConcealability("N") //
			.withAvailability("C") //
			.withDamageAndAmmo("4D6 (5.56)") //
			.withNumberOfShots(30) //
			.withRateOfFire(20) //
			.withReliability("VR") //
			.withRange("400m") //
			.withWeight(4) //
			.withCost(400) //
			.build();
	private Weapon winchesterM70 = new Weapon.WeaponBuilder() //
			.withName("Winchester M70") //
			.withType("RIF") //
			.withCategory("RIF") //
			.withAccuracy(3) //
			.withConcealability("N") //
			.withAvailability("C") //
			.withDamageAndAmmo("5D6+1 (30-06)") //
			.withNumberOfShots(5) //
			.withRateOfFire(1) //
			.withReliability("VR") //
			.withRange("500m") //
			.withWeight(4) //
			.withCost(250) //
			.build();
	private Weapon caws = new Weapon.WeaponBuilder() //
			.withName("CAWS") //
			.withType("SHG") //
			.withCategory("SHG") //
			.withAccuracy(0) //
			.withConcealability("N") //
			.withAvailability("R") //
			.withDamageAndAmmo("4D6 (00)") //
			.withNumberOfShots(10) //
			.withRateOfFire(10) //
			.withReliability("ST") //
			.withRange("50m") //
			.withWeight(3) //
			.withCost(500) //
			.build();

	private Gear techscanner = new Gear.GearBuilder() //
			.withType("Techscanner") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(600) //
			.build();
	private Gear cuttingTorch = new Gear.GearBuilder() //
			.withType("Cutting Torch") //
			.withQuantitiy(0) //
			.withWeight(1) //
			.withCost(40) //
			.build();
	private Gear techToolKit = new Gear.GearBuilder() //
			.withType("Tech Toolkit") //
			.withQuantitiy(0) //
			.withWeight(1) //
			.withCost(100) //
			.build();
	private Gear electronicsToolKit = new Gear.GearBuilder() //
			.withType("Electronics Toolkit") //
			.withQuantitiy(0) //
			.withWeight(1) //
			.withCost(100) //
			.build();
	private Gear protectiveGoggles = new Gear.GearBuilder() //
			.withType("Protective Goggles") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(20) //
			.build();
	private Gear pocketFlashlight = new Gear.GearBuilder() //
			.withType("Pocket Flashlight (25-30ft)") //
			.withQuantitiy(0) //
			.withWeight(0) //
			.withCost(1) //
			.build();
	private Gear flashlight = new Gear.GearBuilder() //
			.withType("Flashlight (100-120ft)") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(2) //
			.build();
	private Gear glowstik = new Gear.GearBuilder() //
			.withType("Glowstik") //
			.withQuantitiy(0) //
			.withWeight(0) //
			.withCost(1) //
			.build();
	private Gear flashPaint = new Gear.GearBuilder() //
			.withType("Flash Paint (1 pt)") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(10) //
			.build();
	private Gear flashTape = new Gear.GearBuilder() //
			.withType("Flash Tape (1 ft)") //
			.withQuantitiy(0) //
			.withWeight(0) //
			.withCost(10) //
			.build();
	private Gear rope = new Gear.GearBuilder() //
			.withType("Rope (1ft)") //
			.withQuantitiy(0) //
			.withWeight(0) //
			.withCost(2) //
			.build();
	private Gear breathingMask = new Gear.GearBuilder() //
			.withType("Breathing Mask") //
			.withQuantitiy(0) //
			.withWeight(1) //
			.withCost(30) //
			.build();
	private Gear breathingMaskFilter = new Gear.GearBuilder() //
			.withType("Breathing Mask Filter (10)") //
			.withQuantitiy(0) //
			.withWeight(0) //
			.withCost(1) //
			.build();
	private Gear holoGenerator = new Gear.GearBuilder() //
			.withType("Holo Generator") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(500) //
			.build();
	private Gear videoBoard = new Gear.GearBuilder() //
			.withType("Video Board (1 sq ft)") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(100) //
			.build();
	private Gear dataChip = new Gear.GearBuilder() //
			.withType("Data Chip") //
			.withQuantitiy(0) //
			.withWeight(0) //
			.withCost(10) //
			.build();
	private Gear logcompass = new Gear.GearBuilder() //
			.withType("Logcompass") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(50) //
			.build();
	private Gear digitalRecorder = new Gear.GearBuilder() //
			.withType("Digital Recorder") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(300) //
			.build();
	private Gear digitalCamera = new Gear.GearBuilder() //
			.withType("Digital Camera") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(150) //
			.build();
	private Gear videoCam = new Gear.GearBuilder() //
			.withType("VideoCam") //
			.withQuantitiy(0) //
			.withWeight(1) //
			.withCost(800) //
			.build();
	private Gear videoAudioTapePlayer = new Gear.GearBuilder() //
			.withType("Video/Audio Tape Player") //
			.withQuantitiy(0) //
			.withWeight(1) //
			.withCost(40) //
			.build();
	private Gear videoTape = new Gear.GearBuilder() //
			.withType("VideoTape") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(4) //
			.build();
	private Gear pocketTV = new Gear.GearBuilder() //
			.withType("Pocket TV") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(80) //
			.build();
	private Gear digitalChipPlayer = new Gear.GearBuilder() //
			.withType("Digital Chip Player") //
			.withQuantitiy(0) //
			.withWeight(1) //
			.withCost(150) //
			.build();
	private Gear digitalMusicChip = new Gear.GearBuilder() //
			.withType("Digital Music Chip") //
			.withQuantitiy(0) //
			.withWeight(0) //
			.withCost(20) //
			.build();
	private Gear electricGuitar = new Gear.GearBuilder() //
			.withType("Electric Guitar") //
			.withQuantitiy(0) //
			.withWeight(3) //
			.withCost(300) //
			.build();
	private Gear electronicKeyboard = new Gear.GearBuilder() //
			.withType("Electronic Keyboard") //
			.withQuantitiy(0) //
			.withWeight(4) //
			.withCost(550) //
			.build();
	private Gear drumSynthesizer = new Gear.GearBuilder() //
			.withType("Drum Synthesizer") //
			.withQuantitiy(0) //
			.withWeight(3) //
			.withCost(500) //
			.build();
	private Gear amplifier = new Gear.GearBuilder() //
			.withType("Amplifier") //
			.withQuantitiy(0) //
			.withWeight(4) //
			.withCost(750) //
			.build();
	private Gear mastoidCommo = new Gear.GearBuilder() //
			.withType("Mastoid Commo") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(100) //
			.build();
	private Gear pocketCommo = new Gear.GearBuilder() //
			.withType("Pocket Commo") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(50) //
			.build();
	private Gear cellularPhone = new Gear.GearBuilder() //
			.withType("Cellular Phone") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(400) //
			.build();
	private Gear miniCellPhone = new Gear.GearBuilder() //
			.withType("Mini Cell Phone") //
			.withQuantitiy(0) //
			.withWeight(0) //
			.withCost(800) //
			.build();
	private Gear binoglasses = new Gear.GearBuilder() //
			.withType("Binoglasses") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(200) //
			.build();
	private Gear binoculars = new Gear.GearBuilder() //
			.withType("Binoculars") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(20) //
			.build();
	private Gear lightBoosterGoggles = new Gear.GearBuilder() //
			.withType("Light Booster Goggles") //
			.withQuantitiy(200) //
			.withWeight(0.5) //
			.withCost(200) //
			.build();
	private Gear irGoggles = new Gear.GearBuilder() //
			.withType("IR Goggles") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(250) //
			.build();
	private Gear irFlash = new Gear.GearBuilder() //
			.withType("IR Flash") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(50) //
			.build();
	private Gear dermalStapler = new Gear.GearBuilder() //
			.withType("Dermal Stapler") //
			.withQuantitiy(0) //
			.withWeight(1) //
			.withCost(1000) //
			.build();
	private Gear spraySkin = new Gear.GearBuilder() //
			.withType("Spray Skin (1 can)") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(50) //
			.build();
	private Gear medkit = new Gear.GearBuilder() //
			.withType("Medkit") //
			.withQuantitiy(0) //
			.withWeight(1) //
			.withCost(50) //
			.build();
	private Gear surgicalKit = new Gear.GearBuilder() //
			.withType("Surgical Kit") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(400) //
			.build();
	private Gear firstAidKit = new Gear.GearBuilder() //
			.withType("First Aid Kit") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(10) //
			.build();
	private Gear medscanner = new Gear.GearBuilder() //
			.withType("Medscanner") //
			.withQuantitiy(0) //
			.withWeight(1) //
			.withCost(300) //
			.build();
	private Gear drugAnalyser = new Gear.GearBuilder() //
			.withType("Drug Analyser") //
			.withQuantitiy(0) //
			.withWeight(1) //
			.withCost(75) //
			.build();
	private Gear airhypo = new Gear.GearBuilder() //
			.withType("Airhypo") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(100) //
			.build();
	private Gear nylonCarrybag = new Gear.GearBuilder() //
			.withType("Nylon Carrybag") //
			.withQuantitiy(0) //
			.withWeight(0.5) //
			.withCost(5) //
			.build();
	private Gear sleepingBag = new Gear.GearBuilder() //
			.withType("Sleeping Bag") //
			.withQuantitiy(0) //
			.withWeight(1) //
			.withCost(25) //
			.build();
	private Gear inflatableBed = new Gear.GearBuilder() //
			.withType("Inflatable Bed") //
			.withQuantitiy(0) //
			.withWeight(3) //
			.withCost(25) //
			.build();

	private Armor cloth = new Armor.ArmorBuilder() //
			.withType("Cloth") //
			.withArmorClass("Soft Armor") //
			.thatCovers("Arms, Torso") //
			.withStoppingPower(0) //
			.withEncumbranceValue(0) //
			.withWeight(0.5) //
			.withCost(15) //
			.build();
	private Armor heavyLeatherJacket = new Armor.ArmorBuilder() //
			.withType("Heavy Leather Jacket") //
			.withArmorClass("Soft Armor") //
			.thatCovers("Arms, Torso") //
			.withStoppingPower(4) //
			.withEncumbranceValue(0) //
			.withWeight(0.5) //
			.withCost(50) //
			.build();
	private Armor heavyLeatherPants = new Armor.ArmorBuilder() //
			.withType("Heavy Leather Pants") //
			.withArmorClass("Soft Armor") //
			.thatCovers("Legs") //
			.withStoppingPower(4) //
			.withEncumbranceValue(0) //
			.withWeight(0.5) //
			.withCost(50) //
			.build();
	private Armor kevlarVest = new Armor.ArmorBuilder() //
			.withType("Kevlar Vest") //
			.withArmorClass("Soft Armor") //
			.thatCovers("Torso") //
			.withStoppingPower(10) //
			.withEncumbranceValue(0) //
			.withWeight(1) //
			.withCost(90) //
			.build();
	private Armor steelHelmet = new Armor.ArmorBuilder() //
			.withType("Steel Helmet") //
			.withArmorClass("Hard Armor") //
			.thatCovers("Head") //
			.withStoppingPower(14) //
			.withEncumbranceValue(0) //
			.withWeight(1) //
			.withCost(20) //
			.build();
	private Armor lightArmorJacket = new Armor.ArmorBuilder() //
			.withType("Light Armor Jacket") //
			.withArmorClass("Soft Armor") //
			.thatCovers("Torso, Arms") //
			.withStoppingPower(14) //
			.withEncumbranceValue(0) //
			.withWeight(3) //
			.withCost(150) //
			.build();
	private Armor mediumArmorJacket = new Armor.ArmorBuilder() //
			.withType("Medium Armor Jacket") //
			.withArmorClass("Soft Armor") //
			.thatCovers("Torso, Arms") //
			.withStoppingPower(18) //
			.withEncumbranceValue(1) //
			.withWeight(3) //
			.withCost(200) //
			.build();
	private Armor flackVest = new Armor.ArmorBuilder() //
			.withType("Flack Vest") //
			.withArmorClass("Hard Armor") //
			.thatCovers("Torso") //
			.withStoppingPower(20) //
			.withEncumbranceValue(1) //
			.withWeight(3) //
			.withCost(200) //
			.build();
	private Armor nylonHelmet = new Armor.ArmorBuilder() //
			.withType("Nylon Helmet") //
			.withArmorClass("Hard Armor") //
			.thatCovers("Head") //
			.withStoppingPower(20) //
			.withEncumbranceValue(0) //
			.withWeight(0.5) //
			.withCost(100) //
			.build();
	private Armor heavyArmorJacket = new Armor.ArmorBuilder() //
			.withType("Heavy Armor Jacket") //
			.withArmorClass("Soft Armor") //
			.thatCovers("Torso, Arms") //
			.withStoppingPower(20) //
			.withEncumbranceValue(2) //
			.withWeight(3) //
			.withCost(250) //
			.build();
	private Armor doorGunnersVest = new Armor.ArmorBuilder() //
			.withType("Door Gunner's Vest") //
			.withArmorClass("Hard Armor") //
			.thatCovers("Torso") //
			.withStoppingPower(25) //
			.withEncumbranceValue(3) //
			.withWeight(4) //
			.withCost(250) //
			.build();
	private Armor metalGear = new Armor.ArmorBuilder() //
			.withType("MetalGear") //
			.withArmorClass("Hard Armor") //
			.thatCovers("Torso, Arms, Head, Legs") //
			.withStoppingPower(25) //
			.withEncumbranceValue(2) //
			.withWeight(3) //
			.withCost(600) //
			.build();

	private Ammo regularLightAmmo = new Ammo.AmmoBuilder() //
			.withType("Reg Lt Pistol / Lt SMG Ammo") //
			.isCaseless(true) //
			.withQuantityPerBox(100) //
			.withCost(15) //
			.withWeight(0.5) //
			.build();
	private Ammo armorPiercingLightAmmo = new Ammo.AmmoBuilder() //
			.withType("AP Lt Pistol / Lt SMG Ammo") //
			.withQuantityPerBox(100) //
			.isCaseless(true) //
			.withCost(45) //
			.withWeight(0.5) //
			.build();
	private Ammo regularLightBrassAmmo = new Ammo.AmmoBuilder() //
			.withType("Brass Reg Lt Pistol / Lt SMG Ammo") //
			.isCaseless(false) //
			.withQuantityPerBox(100) //
			.withCost(30) //
			.withWeight(0.5) //
			.build();
	private Ammo armorPiercingLightBrassAmmo = new Ammo.AmmoBuilder() //
			.withType("Brass AP Lt Pistol / Lt SMG Ammo") //
			.withQuantityPerBox(100) //
			.isCaseless(false) //
			.withCost(90) //
			.withWeight(0.5) //
			.build();
	private Ammo regularMediumAmmo = new Ammo.AmmoBuilder() //
			.withType("Reg Med Pistol / Med SMG Ammo") //
			.isCaseless(true) //
			.withQuantityPerBox(50) //
			.withCost(15) //
			.withWeight(0.5) //
			.build();
	private Ammo armorPiercingMediumAmmo = new Ammo.AmmoBuilder() //
			.withType("AP Med Pistol / Med SMG Ammo") //
			.withQuantityPerBox(50) //
			.isCaseless(true) //
			.withCost(45) //
			.withWeight(0.5) //
			.build();
	private Ammo regularMediumBrassAmmo = new Ammo.AmmoBuilder() //
			.withType("Brass Reg Med Pistol / Med SMG Ammo") //
			.isCaseless(false) //
			.withQuantityPerBox(50) //
			.withCost(30) //
			.withWeight(0.5) //
			.build();
	private Ammo armorPiercingMediumBrassAmmo = new Ammo.AmmoBuilder() //
			.withType("Brass AP Med Pistol / Med SMG Ammo") //
			.withQuantityPerBox(50) //
			.isCaseless(false) //
			.withCost(90) //
			.withWeight(0.5) //
			.build();
	private Ammo regularHeavyAmmo = new Ammo.AmmoBuilder() //
			.withType("Reg Hvy Pistol / Hvy SMG Ammo") //
			.isCaseless(true) //
			.withQuantityPerBox(50) //
			.withCost(18) //
			.withWeight(0.5) //
			.build();
	private Ammo armorPiercingHeavyAmmo = new Ammo.AmmoBuilder() //
			.withType("AP Hvy Pistol / Hvy SMG Ammo") //
			.withQuantityPerBox(50) //
			.isCaseless(true) //
			.withCost(54) //
			.withWeight(0.5) //
			.build();
	private Ammo regularHeavyBrassAmmo = new Ammo.AmmoBuilder() //
			.withType("Brass Reg Hvy Pistol / Hvy SMG Ammo") //
			.isCaseless(false) //
			.withQuantityPerBox(50) //
			.withCost(36) //
			.withWeight(0.5) //
			.build();
	private Ammo armorPiercingHeavyBrassAmmo = new Ammo.AmmoBuilder() //
			.withType("Brass AP Hvy Pistol / Hvy SMG Ammo") //
			.withQuantityPerBox(50) //
			.isCaseless(false) //
			.withCost(108) //
			.withWeight(0.5) //
			.build();
	private Ammo regularVeryHeavyAmmo = new Ammo.AmmoBuilder() //
			.withType("Reg Very Hvy Pistol / Very Hvy SMG Ammo") //
			.isCaseless(true) //
			.withQuantityPerBox(50) //
			.withCost(20) //
			.withWeight(0.5) //
			.build();
	private Ammo armorPiercingVeryHeavyAmmo = new Ammo.AmmoBuilder() //
			.withType("AP Very Hvy Pistol / Very Hvy SMG Ammo") //
			.withQuantityPerBox(50) //
			.isCaseless(true) //
			.withCost(60) //
			.withWeight(0.5) //
			.build();
	private Ammo regularVeryHeavyBrassAmmo = new Ammo.AmmoBuilder() //
			.withType("Brass Reg Very Hvy Pistol / Very Heavy SMG Ammo") //
			.isCaseless(false) //
			.withQuantityPerBox(50) //
			.withCost(40) //
			.withWeight(0.5) //
			.build();
	private Ammo armorPiercingVeryHeavyBrassAmmo = new Ammo.AmmoBuilder() //
			.withType("Brass AP Very Hvy Pistol / Very Hvy SMG Ammo") //
			.withQuantityPerBox(50) //
			.isCaseless(false) //
			.withCost(120) //
			.withWeight(0.5) //
			.build();
	private Ammo regularAssaultRifleAmmo = new Ammo.AmmoBuilder() //
			.withType("Reg AR Ammo") //
			.isCaseless(true) //
			.withQuantityPerBox(100) //
			.withCost(40) //
			.withWeight(0.5) //
			.build();
	private Ammo armorPiercingAssaultRifleAmmo = new Ammo.AmmoBuilder() //
			.withType("AP AR Ammo") //
			.withQuantityPerBox(100) //
			.isCaseless(true) //
			.withCost(120) //
			.withWeight(0.5) //
			.build();
	private Ammo regularAssaultRifleBrassAmmo = new Ammo.AmmoBuilder() //
			.withType("Brass Reg AR Ammo") //
			.isCaseless(false) //
			.withQuantityPerBox(100) //
			.withCost(80) //
			.withWeight(0.5) //
			.build();
	private Ammo armorPiercingAssaultRifleBrassAmmo = new Ammo.AmmoBuilder() //
			.withType("Brass AR Ammo") //
			.withQuantityPerBox(100) //
			.isCaseless(false) //
			.withCost(240) //
			.withWeight(0.5) //
			.build();
	private Ammo regularShotgunAmmo = new Ammo.AmmoBuilder() //
			.withType("Reg SHG Ammo") //
			.isCaseless(true) //
			.withQuantityPerBox(12) //
			.withCost(15) //
			.withWeight(0.5) //
			.build();
	private Ammo regularShotgunPlasticAmmo = new Ammo.AmmoBuilder() //
			.withType("Plastic Reg SHG Ammo") //
			.isCaseless(false) //
			.withQuantityPerBox(12) //
			.withCost(30) //
			.withWeight(0.5) //
			.build();
	private Ammo cannonRound = new Ammo.AmmoBuilder() //
			.withType("20mm Cannon Round") //
			.isCaseless(true) //
			.withQuantityPerBox(1) //
			.withCost(25) //
			.withWeight(0.5) //
			.build();
	private Ammo arrow = new Ammo.AmmoBuilder() //
			.withType("Arrow(s)") //
			.isCaseless(true) //
			.withQuantityPerBox(20) //
			.withCost(24) //
			.withWeight(0.5) //
			.build();
	private Ammo crossbowBolt = new Ammo.AmmoBuilder() //
			.withType("Crossbow Bolt(s)") //
			.isCaseless(true) //
			.withQuantityPerBox(12) //
			.withCost(30) //
			.withWeight(0.5) //
			.build();
	private Ammo airgunPaintPellet = new Ammo.AmmoBuilder() //
			.withType("Airgun Paint Pellet") //
			.isCaseless(true) //
			.withQuantityPerBox(100) //
			.withCost(6) //
			.withWeight(0.5) //
			.build();
	private Ammo airgunHallucinogenPellet = new Ammo.AmmoBuilder() //
			.withType("Airgun Hallucinogen Pellet") //
			.isCaseless(true) //
			.withQuantityPerBox(100) //
			.withCost(6) //
			.withWeight(0.5) //
			.build();
	private Ammo airgunNauseaDrugPellet = new Ammo.AmmoBuilder() //
			.withType("Airgun Nausea Drug Pellet") //
			.isCaseless(true) //
			.withQuantityPerBox(100) //
			.withCost(6) //
			.withWeight(0.5) //
			.build();
	private Ammo airgunSleepDrugPellet = new Ammo.AmmoBuilder() //
			.withType("Airgun Sleep Drug Pellet") //
			.isCaseless(true) //
			.withQuantityPerBox(100) //
			.withCost(6) //
			.withWeight(0.5) //
			.build();
	private Ammo airgunBiotoxinIPellet = new Ammo.AmmoBuilder() //
			.withType("Airgun Biotoxin I Pellet") //
			.isCaseless(true) //
			.withQuantityPerBox(100) //
			.withCost(6) //
			.withWeight(0.5) //
			.build();
	private Ammo airgunBiotoxinIIPellet = new Ammo.AmmoBuilder() //
			.withType("Airgun Biotoxin II Pellet") //
			.isCaseless(true) //
			.withQuantityPerBox(100) //
			.withCost(6) //
			.withWeight(0.5) //
			.build();
	private Ammo airgunNerveGasPellet = new Ammo.AmmoBuilder() //
			.withType("Airgun Nerve Gas Pellet") //
			.isCaseless(true) //
			.withQuantityPerBox(100) //
			.withCost(6) //
			.withWeight(0.5) //
			.build();
	private Ammo airgunAcidPellet = new Ammo.AmmoBuilder() //
			.withType("Airgun Acid Pellet") //
			.isCaseless(true) //
			.withQuantityPerBox(100) //
			.withCost(30) //
			.withWeight(0.5) //
			.build();
	private Ammo needlegunHallucinogenRound = new Ammo.AmmoBuilder() //
			.withType("Needlegun Hallucinogen Round") //
			.isCaseless(true) //
			.withQuantityPerBox(50) //
			.withCost(25) //
			.withWeight(0.5) //
			.build();
	private Ammo needlegunNauseaDrugRound = new Ammo.AmmoBuilder() //
			.withType("Needlegun Nausea Drug Round") //
			.isCaseless(true) //
			.withQuantityPerBox(50) //
			.withCost(25) //
			.withWeight(0.5) //
			.build();
	private Ammo needlegunSleepDrugRound = new Ammo.AmmoBuilder() //
			.withType("Needlegun Sleep Drug Round") //
			.isCaseless(true) //
			.withQuantityPerBox(50) //
			.withCost(25) //
			.withWeight(0.5) //
			.build();
	private Ammo needlegunBiotoxinIRound = new Ammo.AmmoBuilder() //
			.withType("Needlegun Biotoxin I Round") //
			.isCaseless(true) //
			.withQuantityPerBox(50) //
			.withCost(25) //
			.withWeight(0.5) //
			.build();
	private Ammo needlegunBiotoxinIIRound = new Ammo.AmmoBuilder() //
			.withType("Needlegun Biotoxin II Round") //
			.isCaseless(true) //
			.withQuantityPerBox(50) //
			.withCost(25) //
			.withWeight(0.5) //
			.build();
	private Ammo needlegunNerveGasRound = new Ammo.AmmoBuilder() //
			.withType("Needlegun Nerve Gas Round") //
			.isCaseless(true) //
			.withQuantityPerBox(50) //
			.withCost(25) //
			.withWeight(0.5) //
			.build();
	private Ammo flamethrowerReload = new Ammo.AmmoBuilder() //
			.withType("Flamethrower Reload") //
			.isCaseless(true) //
			.withQuantityPerBox(1) //
			.withCost(50) //
			.withWeight(0.5) //
			.build();
	private Ammo microMissileReload = new Ammo.AmmoBuilder() //
			.withType("Micro Missile Reload") //
			.isCaseless(true) //
			.withQuantityPerBox(4) //
			.withCost(100) //
			.withWeight(0.5) //
			.build();

	public CharacterCreationModel() {
		characterName = "Unknown";
		role = cop;
		characterPoints = DEFAULT_STAT_LEVEL * 9;

		intelligenceLevel = DEFAULT_STAT_LEVEL;
		generalEncumbranceValue = 0;
		headEncumbranceValue = 0;
		torsoEncumbranceValue = 0;
		rightArmEncumbranceValue = 0;
		leftArmEncumbranceValue = 0;
		rightLegEncumbranceValue = 0;
		leftLegEncumbranceValue = 0;
		modifiedReflexesLevel = DEFAULT_STAT_LEVEL;
		unmodifiedReflexesLevel = DEFAULT_STAT_LEVEL;
		technicalAbilityLevel = DEFAULT_STAT_LEVEL;
		coolLevel = DEFAULT_STAT_LEVEL;
		attractivenessLevel = DEFAULT_STAT_LEVEL;
		luckLevel = DEFAULT_STAT_LEVEL;
		movementAllowanceLevel = DEFAULT_STAT_LEVEL;
		bodyLevel = DEFAULT_STAT_LEVEL;
		currentEmpathyLevel = DEFAULT_STAT_LEVEL;
		totalEmpathyLevel = DEFAULT_STAT_LEVEL;
		runDistance = calculateRunDistance();
		leapDistance = calculateLeapDistance();
		carryCapacity = calculateCarryCapacity();
		liftCapacity = calculateLiftCapacity();

		headArmorSP = 0;
		torsoArmorSP = 0;
		rightArmArmorSP = 0;
		leftArmArmorSP = 0;
		rightLegArmorSP = 0;
		leftLegArmorSP = 0;

		saveModifier = calculateSaveModifier();
		bodyTypeModifier = calculateBodyTypeModifier();

		injuryPoints = 0;

		specialAbilitySkills.put(authority.getSkillName(), authority);
		specialAbilitySkills.put(charismaticLeadership.getSkillName(), charismaticLeadership);
		specialAbilitySkills.put(combatSense.getSkillName(), combatSense);
		specialAbilitySkills.put(credibility.getSkillName(), credibility);
		specialAbilitySkills.put(family.getSkillName(), family);
		specialAbilitySkills.put(interf.getSkillName(), interf);
		specialAbilitySkills.put(juryRig.getSkillName(), juryRig);
		specialAbilitySkills.put(medicalTech.getSkillName(), medicalTech);
		specialAbilitySkills.put(resources.getSkillName(), resources);
		specialAbilitySkills.put(streetdeal.getSkillName(), streetdeal);
		skillCatelog.put(SPEC, specialAbilitySkills);

		attractivenessSkills.put(personalGrooming.getSkillName(), personalGrooming);
		attractivenessSkills.put(wardrobeAndStyle.getSkillName(), wardrobeAndStyle);
		skillCatelog.put(ATT, attractivenessSkills);

		bodySkills.put(endurance.getSkillName(), endurance);
		bodySkills.put(strengthFeat.getSkillName(), strengthFeat);
		bodySkills.put(swimming.getSkillName(), swimming);
		skillCatelog.put(BOD, bodySkills);

		coolSkills.put(interrogation.getSkillName(), interrogation);
		coolSkills.put(intimidate.getSkillName(), intimidate);
		coolSkills.put(oratory.getSkillName(), oratory);
		coolSkills.put(resistTortureAndDrugs.getSkillName(), resistTortureAndDrugs);
		coolSkills.put(streetwise.getSkillName(), streetwise);
		skillCatelog.put(CL, coolSkills);

		empathySkills.put(humanPerception.getSkillName(), humanPerception);
		empathySkills.put(interview.getSkillName(), interview);
		empathySkills.put(leadership.getSkillName(), leadership);
		empathySkills.put(seduction.getSkillName(), seduction);
		empathySkills.put(social.getSkillName(), social);
		empathySkills.put(persuasionAndFastTalk.getSkillName(), persuasionAndFastTalk);
		empathySkills.put(perform.getSkillName(), perform);
		skillCatelog.put(EMP, empathySkills);

		intelligenceSkills.put(accounting.getSkillName(), accounting);
		intelligenceSkills.put(anthropology.getSkillName(), anthropology);
		intelligenceSkills.put(awarenessAndNotice.getSkillName(), awarenessAndNotice);
		intelligenceSkills.put(biology.getSkillName(), biology);
		intelligenceSkills.put(botany.getSkillName(), botany);
		intelligenceSkills.put(chemistry.getSkillName(), chemistry);
		intelligenceSkills.put(composition.getSkillName(), composition);
		intelligenceSkills.put(diagnoseIllness.getSkillName(), diagnoseIllness);
		intelligenceSkills.put(educationAndGeneralKnowledge.getSkillName(), educationAndGeneralKnowledge);
		intelligenceSkills.put(expert.getSkillName(), expert);
		intelligenceSkills.put(gamble.getSkillName(), gamble);
		intelligenceSkills.put(geology.getSkillName(), geology);
		intelligenceSkills.put(hideAndEvade.getSkillName(), hideAndEvade);
		intelligenceSkills.put(history.getSkillName(), history);
		intelligenceSkills.put(language1.getSkillName(), language1);
		intelligenceSkills.put(language2.getSkillName(), language2);
		intelligenceSkills.put(language3.getSkillName(), language3);
		intelligenceSkills.put(librarySearch.getSkillName(), librarySearch);
		intelligenceSkills.put(mathematics.getSkillName(), mathematics);
		intelligenceSkills.put(physics.getSkillName(), physics);
		intelligenceSkills.put(programming.getSkillName(), programming);
		intelligenceSkills.put(shadowAndTrack.getSkillName(), shadowAndTrack);
		intelligenceSkills.put(stockMarket.getSkillName(), stockMarket);
		intelligenceSkills.put(systemKnowledge.getSkillName(), systemKnowledge);
		intelligenceSkills.put(teaching.getSkillName(), teaching);
		intelligenceSkills.put(wildernessSurvival.getSkillName(), wildernessSurvival);
		intelligenceSkills.put(zoology.getSkillName(), zoology);
		skillCatelog.put(INT, intelligenceSkills);

		reflexesSkills.put(archery.getSkillName(), archery);
		reflexesSkills.put(athletics.getSkillName(), athletics);
		reflexesSkills.put(brawling.getSkillName(), brawling);
		reflexesSkills.put(dance.getSkillName(), dance);
		reflexesSkills.put(dodgeAndEscape.getSkillName(), dodgeAndEscape);
		reflexesSkills.put(driving.getSkillName(), driving);
		reflexesSkills.put(fencing.getSkillName(), fencing);
		reflexesSkills.put(handgun.getSkillName(), handgun);
		reflexesSkills.put(heavyWeapons.getSkillName(), heavyWeapons);
		reflexesSkills.put(martialArt1.getSkillName(), martialArt1);
		reflexesSkills.put(martialArt2.getSkillName(), martialArt2);
		reflexesSkills.put(martialArt3.getSkillName(), martialArt3);
		reflexesSkills.put(melee.getSkillName(), melee);
		reflexesSkills.put(motorcycle.getSkillName(), motorcycle);
		reflexesSkills.put(operateHeavyMachinery.getSkillName(), operateHeavyMachinery);
		reflexesSkills.put(pilotGyro.getSkillName(), pilotGyro);
		reflexesSkills.put(pilotFixedWing.getSkillName(), pilotFixedWing);
		reflexesSkills.put(pilotDirigible.getSkillName(), pilotDirigible);
		reflexesSkills.put(pilotVectorThrustVehicle.getSkillName(), pilotVectorThrustVehicle);
		reflexesSkills.put(rifle.getSkillName(), rifle);
		reflexesSkills.put(stealth.getSkillName(), stealth);
		reflexesSkills.put(submachinegun.getSkillName(), submachinegun);
		skillCatelog.put(REF, reflexesSkills);

		technicalAbilitySkills.put(aeroTech.getSkillName(), aeroTech);
		technicalAbilitySkills.put(avTech.getSkillName(), avTech);
		technicalAbilitySkills.put(basicTech.getSkillName(), basicTech);
		technicalAbilitySkills.put(cryotankOperation.getSkillName(), cryotankOperation);
		technicalAbilitySkills.put(cyberdeckDesign.getSkillName(), cyberdeckDesign);
		technicalAbilitySkills.put(cyberTech.getSkillName(), cyberTech);
		technicalAbilitySkills.put(demolition.getSkillName(), demolition);
		technicalAbilitySkills.put(disguise.getSkillName(), disguise);
		technicalAbilitySkills.put(electronics.getSkillName(), electronics);
		technicalAbilitySkills.put(electronicSecurity.getSkillName(), electronicSecurity);
		technicalAbilitySkills.put(firstAid.getSkillName(), firstAid);
		technicalAbilitySkills.put(forgery.getSkillName(), forgery);
		technicalAbilitySkills.put(gyroTech.getSkillName(), gyroTech);
		technicalAbilitySkills.put(paintOrDraw.getSkillName(), paintOrDraw);
		technicalAbilitySkills.put(photoAndFilm.getSkillName(), photoAndFilm);
		technicalAbilitySkills.put(pharmacuticals.getSkillName(), pharmacuticals);
		technicalAbilitySkills.put(pickLock.getSkillName(), pickLock);
		technicalAbilitySkills.put(pickPocket.getSkillName(), pickPocket);
		technicalAbilitySkills.put(playInstrument.getSkillName(), playInstrument);
		technicalAbilitySkills.put(weaponsmith.getSkillName(), weaponsmith);
		skillCatelog.put(TECH, technicalAbilitySkills);

		roles.put(cop.getRoleName(), cop);
		roles.put(corporate.getRoleName(), corporate);
		roles.put(fixer.getRoleName(), fixer);
		roles.put(media.getRoleName(), media);
		roles.put(netrunner.getRoleName(), netrunner);
		roles.put(nomad.getRoleName(), nomad);
		roles.put(rockerboy.getRoleName(), rockerboy);
		roles.put(solo.getRoleName(), solo);
		roles.put(techie.getRoleName(), techie);

		weapons.put(budgetArmsC13.getName(), budgetArmsC13);
		weapons.put(daiLungCybermag15.getName(), daiLungCybermag15);
		weapons.put(federatedArmsX22.getName(), federatedArmsX22);
		weapons.put(militechArmsAvenger.getName(), militechArmsAvenger);
		weapons.put(daiLungStreetmaster.getName(), daiLungStreetmaster);
		weapons.put(federatedArmsX9mm.getName(), federatedArmsX9mm);
		weapons.put(budgetArmsAuto3.getName(), budgetArmsAuto3);
		weapons.put(sternmeyerType35.getName(), sternmeyerType35);
		weapons.put(armalite44.getName(), armalite44);
		weapons.put(coltAmtModel2000.getName(), coltAmtModel2000);
		weapons.put(uziMiniauto9.getName(), uziMiniauto9);
		weapons.put(hkMp2013.getName(), hkMp2013);
		weapons.put(fedArmsTechAssaultII.getName(), fedArmsTechAssaultII);
		weapons.put(arasakaMinami10.getName(), arasakaMinami10);
		weapons.put(hkMpk9.getName(), hkMpk9);
		weapons.put(sternmeyerSmg21.getName(), sternmeyerSmg21);
		weapons.put(hkMpk11.getName(), hkMpk11);
		weapons.put(ingramMac14.getName(), ingramMac14);
		weapons.put(militechRoninLightAssault.getName(), militechRoninLightAssault);
		weapons.put(akr20MediumAssault.getName(), akr20MediumAssault);
		weapons.put(fnRalHeavyAssaultRifle.getName(), fnRalHeavyAssaultRifle);
		weapons.put(kalishnikovA80HeavyRifle.getName(), kalishnikovA80HeavyRifle);
		weapons.put(arasakaRapidAssault12.getName(), arasakaRapidAssault12);
		weapons.put(sternmeyerStakeout10.getName(), sternmeyerStakeout10);
		weapons.put(barrettArasakaLight20mm.getName(), barrettArasakaLight20mm);
		weapons.put(scorpion16MissileLauncher.getName(), scorpion16MissileLauncher);
		weapons.put(militechArmsRpgA.getName(), militechArmsRpgA);
		weapons.put(fragmentationGrenade.getName(), fragmentationGrenade);
		weapons.put(incendiaryGrenade.getName(), incendiaryGrenade);
		weapons.put(stunGrenade.getName(), stunGrenade);
		weapons.put(dazzleGrenade.getName(), dazzleGrenade);
		weapons.put(sonicGrenade.getName(), sonicGrenade);
		weapons.put(nauseaGasGrenade.getName(), nauseaGasGrenade);
		weapons.put(teargasGrenade.getName(), teargasGrenade);
		weapons.put(sleepDrugGrenade.getName(), sleepDrugGrenade);
		weapons.put(biotoxin1Grenade.getName(), biotoxin1Grenade);
		weapons.put(biotoxinIIGrenade.getName(), biotoxinIIGrenade);
		weapons.put(nerveGasGrenade.getName(), nerveGasGrenade);
		weapons.put(plastiqueExplosive.getName(), plastiqueExplosive);
		weapons.put(c6PlasticExplosive.getName(), c6PlasticExplosive);
		weapons.put(tnt.getName(), tnt);
		weapons.put(molotovCocktail.getName(), molotovCocktail);
		weapons.put(landMine.getName(), landMine);
		weapons.put(claymore.getName(), claymore);
		weapons.put(kaF253Flamethrower.getName(), kaF253Flamethrower);
		weapons.put(techtronica15Microwaver.getName(), techtronica15Microwaver);
		weapons.put(militechElectricLaserCannon.getName(), militechElectricLaserCannon);
		weapons.put(avanteP1135Needlegun.getName(), avanteP1135Needlegun);
		weapons.put(enertexAkmPowerSquirt.getName(), enertexAkmPowerSquirt);
		weapons.put(nelspotWombat.getName(), nelspotWombat);
		weapons.put(militechElectronicsTaser.getName(), militechElectronicsTaser);
		weapons.put(eagleTechTomcatCBow.getName(), eagleTechTomcatCBow);
		weapons.put(eagleTechStrykerXBow.getName(), eagleTechStrykerXBow);
		weapons.put(kendachiMonoknife.getName(), kendachiMonoknife);
		weapons.put(kendachiMonoKatana.getName(), kendachiMonoKatana);
		weapons.put(spm1Battleglove.getName(), spm1Battleglove);
		weapons.put(club.getName(), club);
		weapons.put(knife.getName(), knife);
		weapons.put(sword.getName(), sword);
		weapons.put(axe.getName(), axe);
		weapons.put(nunchaku.getName(), nunchaku);
		weapons.put(naginata.getName(), naginata);
		weapons.put(shiriken.getName(), shiriken);
		weapons.put(switchblade.getName(), switchblade);
		weapons.put(brassKnuckles.getName(), brassKnuckles);
		weapons.put(sledgehammer.getName(), sledgehammer);
		weapons.put(chainsaw.getName(), chainsaw);
		weapons.put(swCombatMagnum.getName(), swCombatMagnum);
		weapons.put(llamaCommanche.getName(), llamaCommanche);
		weapons.put(colt45Peacemaker.getName(), colt45Peacemaker);
		weapons.put(colt38Detective.getName(), colt38Detective);
		weapons.put(cop357Derringer.getName(), cop357Derringer);
		weapons.put(uzi.getName(), uzi);
		weapons.put(vz61Skorpion.getName(), vz61Skorpion);
		weapons.put(ingramMac10.getName(), ingramMac10);
		weapons.put(hkMp5.getName(), hkMp5);
		weapons.put(thompsonM1.getName(), thompsonM1);
		weapons.put(bushmaster.getName(), bushmaster);
		weapons.put(fnFal.getName(), fnFal);
		weapons.put(ak47.getName(), ak47);
		weapons.put(m16A.getName(), m16A);
		weapons.put(styerAug.getName(), styerAug);
		weapons.put(winchesterM70.getName(), winchesterM70);
		weapons.put(caws.getName(), caws);

		gear.put(techscanner.getType(), techscanner);
		gear.put(cuttingTorch.getType(), cuttingTorch);
		gear.put(techToolKit.getType(), techToolKit);
		gear.put(electronicsToolKit.getType(), electronicsToolKit);
		gear.put(protectiveGoggles.getType(), protectiveGoggles);
		gear.put(pocketFlashlight.getType(), pocketFlashlight);
		gear.put(flashlight.getType(), flashlight);
		gear.put(glowstik.getType(), glowstik);
		gear.put(flashPaint.getType(), flashPaint);
		gear.put(flashTape.getType(), flashTape);
		gear.put(rope.getType(), rope);
		gear.put(breathingMask.getType(), breathingMask);
		gear.put(breathingMaskFilter.getType(), breathingMaskFilter);
		gear.put(holoGenerator.getType(), holoGenerator);
		gear.put(videoBoard.getType(), videoBoard);
		gear.put(dataChip.getType(), dataChip);
		gear.put(logcompass.getType(), logcompass);
		gear.put(digitalRecorder.getType(), digitalRecorder);
		gear.put(digitalCamera.getType(), digitalCamera);
		gear.put(videoCam.getType(), videoCam);
		gear.put(videoAudioTapePlayer.getType(), videoAudioTapePlayer);
		gear.put(videoTape.getType(), videoTape);
		gear.put(pocketTV.getType(), pocketTV);
		gear.put(digitalChipPlayer.getType(), digitalChipPlayer);
		gear.put(digitalMusicChip.getType(), digitalMusicChip);
		gear.put(electricGuitar.getType(), electricGuitar);
		gear.put(electronicKeyboard.getType(), electronicKeyboard);
		gear.put(drumSynthesizer.getType(), drumSynthesizer);
		gear.put(amplifier.getType(), amplifier);
		gear.put(mastoidCommo.getType(), mastoidCommo);
		gear.put(pocketCommo.getType(), pocketCommo);
		gear.put(cellularPhone.getType(), cellularPhone);
		gear.put(miniCellPhone.getType(), miniCellPhone);
		gear.put(binoglasses.getType(), binoglasses);
		gear.put(binoculars.getType(), binoculars);
		gear.put(lightBoosterGoggles.getType(), lightBoosterGoggles);
		gear.put(irGoggles.getType(), irGoggles);
		gear.put(irFlash.getType(), irFlash);
		gear.put(dermalStapler.getType(), dermalStapler);
		gear.put(spraySkin.getType(), spraySkin);
		gear.put(medkit.getType(), medkit);
		gear.put(surgicalKit.getType(), surgicalKit);
		gear.put(firstAidKit.getType(), firstAidKit);
		gear.put(medscanner.getType(), medscanner);
		gear.put(drugAnalyser.getType(), drugAnalyser);
		gear.put(airhypo.getType(), airhypo);
		gear.put(nylonCarrybag.getType(), nylonCarrybag);
		gear.put(sleepingBag.getType(), sleepingBag);
		gear.put(inflatableBed.getType(), inflatableBed);

		armors.put(cloth.getType(), cloth);
		armors.put(heavyLeatherJacket.getType(), heavyLeatherJacket);
		armors.put(heavyLeatherPants.getType(), heavyLeatherPants);
		armors.put(kevlarVest.getType(), kevlarVest);
		armors.put(steelHelmet.getType(), steelHelmet);
		armors.put(lightArmorJacket.getType(), lightArmorJacket);
		armors.put(mediumArmorJacket.getType(), mediumArmorJacket);
		armors.put(flackVest.getType(), flackVest);
		armors.put(nylonHelmet.getType(), nylonHelmet);
		armors.put(heavyArmorJacket.getType(), heavyArmorJacket);
		armors.put(doorGunnersVest.getType(), doorGunnersVest);
		armors.put(metalGear.getType(), metalGear);

		ammos.put(regularLightAmmo.getType(), regularLightAmmo);
		ammos.put(armorPiercingLightAmmo.getType(), armorPiercingLightAmmo);
		ammos.put(regularLightBrassAmmo.getType(), regularLightBrassAmmo);
		ammos.put(armorPiercingLightBrassAmmo.getType(), armorPiercingLightBrassAmmo);
		ammos.put(regularMediumAmmo.getType(), regularMediumAmmo);
		ammos.put(armorPiercingMediumAmmo.getType(), armorPiercingMediumAmmo);
		ammos.put(regularMediumBrassAmmo.getType(), regularMediumBrassAmmo);
		ammos.put(armorPiercingMediumBrassAmmo.getType(), armorPiercingMediumBrassAmmo);
		ammos.put(regularHeavyAmmo.getType(), regularHeavyAmmo);
		ammos.put(armorPiercingHeavyAmmo.getType(), armorPiercingHeavyAmmo);
		ammos.put(regularHeavyBrassAmmo.getType(), regularHeavyBrassAmmo);
		ammos.put(armorPiercingHeavyBrassAmmo.getType(), armorPiercingHeavyBrassAmmo);
		ammos.put(regularVeryHeavyAmmo.getType(), armorPiercingVeryHeavyAmmo);
		ammos.put(regularVeryHeavyBrassAmmo.getType(), regularVeryHeavyBrassAmmo);
		ammos.put(armorPiercingVeryHeavyBrassAmmo.getType(), armorPiercingVeryHeavyBrassAmmo);
		ammos.put(regularAssaultRifleAmmo.getType(), regularAssaultRifleAmmo);
		ammos.put(armorPiercingAssaultRifleAmmo.getType(), armorPiercingAssaultRifleAmmo);
		ammos.put(regularAssaultRifleBrassAmmo.getType(), regularAssaultRifleBrassAmmo);
		ammos.put(armorPiercingAssaultRifleBrassAmmo.getType(), armorPiercingAssaultRifleBrassAmmo);
		ammos.put(regularShotgunAmmo.getType(), regularShotgunAmmo);
		ammos.put(regularShotgunPlasticAmmo.getType(), regularShotgunPlasticAmmo);
		ammos.put(cannonRound.getType(), cannonRound);
		ammos.put(arrow.getType(), arrow);
		ammos.put(crossbowBolt.getType(), crossbowBolt);
		ammos.put(airgunPaintPellet.getType(), airgunPaintPellet);
		ammos.put(airgunHallucinogenPellet.getType(), airgunHallucinogenPellet);
		ammos.put(airgunNauseaDrugPellet.getType(), airgunNauseaDrugPellet);
		ammos.put(airgunSleepDrugPellet.getType(), airgunSleepDrugPellet);
		ammos.put(airgunBiotoxinIPellet.getType(), airgunBiotoxinIPellet);
		ammos.put(airgunBiotoxinIIPellet.getType(), airgunBiotoxinIIPellet);
		ammos.put(airgunNerveGasPellet.getType(), airgunNerveGasPellet);
		ammos.put(airgunAcidPellet.getType(), airgunAcidPellet);
		ammos.put(needlegunHallucinogenRound.getType(), needlegunHallucinogenRound);
		ammos.put(needlegunNauseaDrugRound.getType(), needlegunNauseaDrugRound);
		ammos.put(needlegunSleepDrugRound.getType(), needlegunSleepDrugRound);
		ammos.put(needlegunBiotoxinIRound.getType(), needlegunBiotoxinIRound);
		ammos.put(needlegunBiotoxinIIRound.getType(), needlegunBiotoxinIIRound);
		ammos.put(needlegunNerveGasRound.getType(), needlegunNerveGasRound);
		ammos.put(flamethrowerReload.getType(), flamethrowerReload);
		ammos.put(microMissileReload.getType(), microMissileReload);
	}

	private double calculateRunDistance() {
		return movementAllowanceLevel * 3.0;
	}

	private double calculateLeapDistance() {
		return (movementAllowanceLevel * 3.0) / 4.0;
	}

	private double calculateCarryCapacity() {
		return bodyLevel * 10.0;
	}

	private double calculateLiftCapacity() {
		return bodyLevel * 40.0;
	}

	public int calculateSaveModifier() {
		return bodyLevel - (int) ((injuryPoints - 0.5) / CharacterCreationView.MAXIMUM_CELLS_PER_GAUGE);
	}

	public int calculateBodyTypeModifier() {
		if (bodyLevel == 2) {
			return 0;
		} else if (3 <= bodyLevel && bodyLevel <= 4) {
			return -1;
		} else if (5 <= bodyLevel && bodyLevel <= 7) {
			return -2;
		} else if (8 <= bodyLevel && bodyLevel <= 9) {
			return -3;
		} else if (bodyLevel == 10) {
			return -4;
		} else if (bodyLevel > 10) {
			return -5;
		} else {
			return 0;
		}
	}

	public String getHandle() {
		return characterName;
	}

	public Role getRole() {
		return role;
	}

	public int getCharacterPoints() {
		return characterPoints;
	}

	public int getIntelligenceLevel() {
		return intelligenceLevel;
	}
	
	public int getGeneralEncumbranceValue() {
		return generalEncumbranceValue;
	}

	public int getHeadEncumbranceValue() {
		return headEncumbranceValue;
	}
	
	public int getTorsoEncumbranceValue() {
		return torsoEncumbranceValue;
	}
	
	public int getRightArmEncumbranceValue() {
		return rightArmEncumbranceValue;
	}
	
	public int getLeftArmEncumbranceValue() {
		return leftArmEncumbranceValue;
	}
	
	public int getRightLegEncumbranceValue() {
		return rightLegEncumbranceValue;
	}
	
	public int getLeftLegEncumbranceValue() {
		return leftLegEncumbranceValue;
	}

	public int getUnmodifiedReflexesLevel() {
		return unmodifiedReflexesLevel;
	}

	public int getModifiedReflexesLevel() {
		return modifiedReflexesLevel;
	}

	public int getTechnicalAbilityLevel() {
		return technicalAbilityLevel;
	}

	public int getCoolLevel() {
		return coolLevel;
	}

	public int getAttractivenessLevel() {
		return attractivenessLevel;
	}

	public int getLuckLevel() {
		return luckLevel;
	}

	public int getMovementAllowanceLevel() {
		return movementAllowanceLevel;
	}

	public int getBodyLevel() {
		return bodyLevel;
	}

	public int getCurrentEmpathyLevel() {
		return currentEmpathyLevel;
	}

	public int getTotalEmpathyLevel() {
		return totalEmpathyLevel;
	}

	public double getRunLevel() {
		return runDistance;
	}

	public double getLeapLevel() {
		return leapDistance;
	}

	public double getLiftCapacity() {
		return liftCapacity;
	}

	public double getCarryCapacity() {
		return carryCapacity;
	}

	public int getHeadArmorStoppingPower() {
		return headArmorSP;
	}

	public int getTorsoArmorStoppingPower() {
		return torsoArmorSP;
	}

	public int getRightArmArmorStoppingPower() {
		return rightArmArmorSP;
	}

	public int getLeftArmArmorStoppingPower() {
		return leftArmArmorSP;
	}

	public int getRightLegArmorStoppingPower() {
		return rightLegArmorSP;
	}

	public int getLeftLegArmorStoppingPower() {
		return leftLegArmorSP;
	}

	public int getSaveModifier() {
		return saveModifier;
	}

	public int getBodyTypeModifier() {
		return bodyTypeModifier;
	}

	public double getInjuryPoints() {
		return injuryPoints;
	}

	public Map<String, Map<String, Skill>> getSkillCatelog() {
		return skillCatelog;
	}

	public Skill getSkill(String skillCode) {
		Map<String, Skill> skillCategory;
		String categoryCode = skillCode.substring(0, 3);
		String skillName = skillCode.substring(4);
		switch (categoryCode) {
		case "SPE":
			skillCategory = specialAbilitySkills;
			break;
		case "ATT":
			skillCategory = attractivenessSkills;
			break;
		case "BOD":
			skillCategory = bodySkills;
			break;
		case "COO":
			skillCategory = coolSkills;
			break;
		case "EMP":
			skillCategory = empathySkills;
			break;
		case "INT":
			skillCategory = intelligenceSkills;
			break;
		case "REF":
			skillCategory = reflexesSkills;
			break;
		case "TEC":
			skillCategory = technicalAbilitySkills;
			break;
		default:
			skillCategory = null;
			break;
		}

		for (Skill skill : skillCategory.values()) {
			String formattedSkillName = skill.getSkillName().replaceAll(" ", "_").toLowerCase();
			if (formattedSkillName.equals(skillName)) {
				return skill;
			}
		}
		return null;
	}

	public Map<String, Weapon> getWeapons() {
		return weapons;
	}

	public Map<String, Gear> getGear() {
		return gear;
	}

	public Map<String, Armor> getArmors() {
		return armors;
	}

	public Map<String, Ammo> getAmmos() {
		return ammos;
	}

	public void setCharacterName(String name) {
		characterName = name;
	}

	public void setRole(String newRole) {
		for (String key : roles.keySet()) {
			if (key.equals(newRole)) {
				role = roles.get(newRole);
			}
		}
	}

	public void setCharacterPoints(int points) {
		characterPoints = points;
	}

	public void setIntelligenceLevel(int level) {
		intelligenceLevel = level;
	}

	public void setGeneralEncumbranceValue(int value) {
		generalEncumbranceValue = value;
	}
	
	public void setHeadEncumbranceValue(int value) {
		headEncumbranceValue = value;
	}
	
	public void setTorsoEncumbranceValue(int value) {
		torsoEncumbranceValue = value;
	}
	
	public void setRightArmEncumbranceValue(int value) {
		rightArmEncumbranceValue = value;
	}
	
	public void setLeftArmEncumbranceValue(int value) {
		leftArmEncumbranceValue = value;
	}
	
	public void setRightLegEncumbranceValue(int value) {
		rightLegEncumbranceValue = value;
	}
	
	public void setLeftLegEncumbranceValue(int value) {
		leftLegEncumbranceValue = value;
	}

	public void setUnmodifiedReflexesLevel(int level) {
		unmodifiedReflexesLevel = level;
	}

	public void setModifiedReflexesLevel(int level) {
		modifiedReflexesLevel = level;
	}

	public void setTechnicalAbilityLevel(int level) {
		technicalAbilityLevel = level;
	}

	public void setCoolLevel(int level) {
		coolLevel = level;
	}

	public void setAttractivenessLevel(int level) {
		attractivenessLevel = level;
	}

	public void setLuckLevel(int level) {
		luckLevel = level;
	}

	public void setMovementAllowanceLevel(int level) {
		movementAllowanceLevel = level;

		runDistance = calculateRunDistance();
		leapDistance = calculateLeapDistance();
	}

	public void setBodyLevel(int level) {
		bodyLevel = level;

		liftCapacity = calculateLiftCapacity();
		carryCapacity = calculateCarryCapacity();
	}

	public void setCurrentEmpathyLevel(int level) {
		currentEmpathyLevel = level;
	}

	public void setTotalEmpathyLevel(int level) {
		totalEmpathyLevel = level;
	}

	public void setRunDistance(double distance) {
		runDistance = distance;
	}

	public void setLeapDistance(double distance) {
		leapDistance = distance;
	}

	public void setLiftCapacity(double capacity) {
		liftCapacity = capacity;
	}

	public void setCarryCapacity(double capacity) {
		carryCapacity = capacity;
	}

	public void setHeadArmorStoppingPower(int stoppingPower) {
		headArmorSP = stoppingPower;
	}

	public void setTorsoArmorStoppingPower(int stoppingPower) {
		torsoArmorSP = stoppingPower;
	}

	public void setRightArmArmorStoppingPower(int stoppingPower) {
		rightArmArmorSP = stoppingPower;
	}

	public void setLeftArmArmorStoppingPower(int stoppingPower) {
		leftArmArmorSP = stoppingPower;
	}

	public void setRightLegArmorStoppingPower(int stoppingPower) {
		rightLegArmorSP = stoppingPower;
	}

	public void setLeftLegArmorStoppingPower(int stoppingPower) {
		leftLegArmorSP = stoppingPower;
	}

	public void setSaveModifier(int modifier) {
		saveModifier = modifier;
	}

	public void setBodyTypeModifier(int modifier) {
		bodyTypeModifier = modifier;
	}

	public void setInjuryPoints(double points) {
		injuryPoints = points;
	}

	public static class Ammo {
		private String type;
		private boolean isCaseless;
		private int quantity;
		private int quantityPerBox;
		private int cost;
		private double weight;

		private Ammo(AmmoBuilder ammoBuilder) {
			this.type = ammoBuilder.type;
			this.isCaseless = ammoBuilder.isCaseless;
			this.quantity = ammoBuilder.quantity;
			this.quantityPerBox = ammoBuilder.quantityPerBox;
			this.cost = ammoBuilder.cost;
			this.weight = ammoBuilder.weight;
		}

		public String getType() {
			return type;
		}

		public boolean isCaseless() {
			return isCaseless;
		}

		public int getQuantity() {
			return quantity;
		}

		public int getQuantityPerBox() {
			return quantityPerBox;
		}

		public int getCost() {
			return cost;
		}

		public double getWeight() {
			return weight;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public static class AmmoBuilder {
			private String type = "Unknown";
			private boolean isCaseless = false;
			private int quantity = 0;
			private int quantityPerBox = 0;
			private int cost = 0;
			private double weight = 0.0;

			public AmmoBuilder withType(String type) {
				this.type = type;
				return this;
			}

			public AmmoBuilder isCaseless(Boolean isCaseless) {
				this.isCaseless = isCaseless;
				return this;
			}

			public AmmoBuilder withQuantity(int quantity) {
				this.quantity = quantity;
				return this;
			}

			public AmmoBuilder withQuantityPerBox(int quantityPerBox) {
				this.quantityPerBox = quantityPerBox;
				return this;
			}

			public AmmoBuilder withCost(int cost) {
				this.cost = cost;
				return this;
			}

			public AmmoBuilder withWeight(double weight) {
				this.weight = weight;
				return this;
			}

			public Ammo build() {
				return new Ammo(this);
			}
		}
	}

	public static class Armor {
		private String type;
		private String armorClass;
		private Map<String, Boolean> covers;
		private int stoppingPower;
		private int encumbranceValue;
		private double weight;
		private int cost;

		private Armor(ArmorBuilder armorBuilder) {
			this.type = armorBuilder.type;
			this.armorClass = armorBuilder.armorClass;
			this.covers = armorBuilder.covers;
			this.stoppingPower = armorBuilder.stoppingPower;
			this.encumbranceValue = armorBuilder.encumbranceValue;
			this.weight = armorBuilder.weight;
			this.cost = armorBuilder.cost;
		}

		public String getType() {
			return type;
		}

		public String getArmorClass() {
			return armorClass;
		}

		public String getCovers() {
			String coverString = "";
			for (Map.Entry<String, Boolean> entry : covers.entrySet()) {
				if (entry.getValue()) {
					coverString += entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1) + ", ";
				}
			}
			coverString = coverString.substring(0, coverString.length() - 2);

			return coverString;
		}

		public int getStoppingPower() {
			return stoppingPower;
		}

		public int getEncumbranceValue() {
			return encumbranceValue;
		}

		public double getWeight() {
			return weight;
		}

		public int getCost() {
			return cost;
		}

		public static class ArmorBuilder {
			private String type = "Unknown";
			private String armorClass = "Unknown";
			private Map<String, Boolean> covers;
			private int stoppingPower = 0;
			private int encumbranceValue = 0;
			private double weight = 0.0;
			private int cost = 0;

			public ArmorBuilder withType(String type) {
				this.type = type;
				return this;
			}

			public ArmorBuilder withArmorClass(String armorClass) {
				this.armorClass = armorClass;
				return this;
			}

			public ArmorBuilder thatCovers(String coveredLocations) {
				covers = new TreeMap<String, Boolean>();
				covers.put("head", false);
				covers.put("arms", false);
				covers.put("torso", false);
				covers.put("legs", false);

				String[] locations = coveredLocations.split(",");
				for (String location : locations) {
					switch (location.toLowerCase().trim()) {
					case "head":
						covers.put("head", true);
						break;
					case "arms":
						covers.put("arms", true);
						break;
					case "torso":
						covers.put("torso", true);
						break;
					case "legs":
						covers.put("legs", true);
						break;
					default:
						break;
					}
				}
				return this;
			}

			public ArmorBuilder withStoppingPower(int stoppingPower) {
				this.stoppingPower = stoppingPower;
				return this;
			}

			public ArmorBuilder withEncumbranceValue(int encumbranceValue) {
				this.encumbranceValue = encumbranceValue;
				return this;
			}

			public ArmorBuilder withWeight(double weight) {
				this.weight = weight;
				return this;
			}

			public ArmorBuilder withCost(int cost) {
				this.cost = cost;
				return this;
			}

			public Armor build() {
				return new Armor(this);
			}
		}
	}

	// TODO finish the builder and plan on putting all cyberware into the store then
	// adding the cyberware to equipped when bought
	public static class Cyberware {
		private String cyberware;
		private String surgeryCode;
		private String idCode;
		private String description;
		private int cost;
		private String humanityLoss;

		private Cyberware(CyberwareBuilder cyberBuilder) {
			this.cyberware = cyberBuilder.cyberware;
			this.surgeryCode = cyberBuilder.surgeryCode;
			this.idCode = cyberBuilder.idCode;
			this.description = cyberBuilder.description;
			this.cost = cyberBuilder.cost;
			this.humanityLoss = cyberBuilder.humanityLoss;
		}

		public String getCyberware() {
			return cyberware;
		}

		public String getSurgeryCode() {
			return surgeryCode;
		}

		public String getIdCode() {
			return idCode;
		}

		public String getDescription() {
			return description;
		}

		public int getCost() {
			return cost;
		}

		public String getHumanityLoss() {
			return humanityLoss;
		}

		public static class CyberwareBuilder {
			private String cyberware = "Unkown";
			private String surgeryCode = "-";
			private String idCode = "--";
			private String description = "-";
			private int cost = 0;
			private String humanityLoss = "0";

			public CyberwareBuilder withCyberware(String cyberware) {
				this.cyberware = cyberware;
				return this;
			}

			public CyberwareBuilder withSurgeryCode(String surgeryCode) {
				this.surgeryCode = surgeryCode;
				return this;
			}

			public CyberwareBuilder withIdCode(String idCode) {
				this.idCode = idCode;
				return this;
			}

			public CyberwareBuilder withDescription(String description) {
				this.description = description;
				return this;
			}

			public CyberwareBuilder withCost(int cost) {
				this.cost = cost;
				return this;
			}

			public CyberwareBuilder withHumanityLoss(String humanityLoss) {
				this.humanityLoss = humanityLoss;
				return this;
			}

			public Cyberware build() {
				return new Cyberware(this);
			}
		}
	}

	public static class Gear {
		private String type;
		private double quantity;
		private double weight;
		private int cost;

		public String getType() {
			return type;
		}

		public double getQuantity() {
			return quantity;
		}

		public double getWeight() {
			return weight;
		}

		public int getCost() {
			return cost;
		}

		public void setQuantity(double quantity) {
			this.quantity = quantity;
		}

		private Gear(GearBuilder gearBuilder) {
			this.type = gearBuilder.type;
			this.quantity = gearBuilder.quantity;
			this.weight = gearBuilder.weight;
			this.cost = gearBuilder.cost;
		}

		public static class GearBuilder {
			private String type = "Unkown";
			private double quantity = 0.0;
			private double weight = 0;
			private int cost = 0;

			public GearBuilder withType(String type) {
				this.type = type;
				return this;
			}

			public GearBuilder withQuantitiy(double quantity) {
				this.quantity = quantity;
				return this;
			}

			public GearBuilder withWeight(double weight) {
				this.weight = weight;
				return this;
			}

			public GearBuilder withCost(int cost) {
				this.cost = cost;
				return this;
			}

			public Gear build() {
				return new Gear(this);
			}
		}
	}

	public static class Role {
		private String name = "N/A";
		private String blurb = "";
		private String description = "";
		private List<Skill[]> careerSkills = new ArrayList<Skill[]>();

		private Role(RoleBuilder roleBuilder) {
			this.name = roleBuilder.name;
			this.blurb = roleBuilder.blurb;
			this.description = roleBuilder.description;
			this.careerSkills = roleBuilder.careerSkills;
		}

		public String getRoleName() {
			return name;
		}

		public String getBlurb() {
			return blurb;
		}

		public String getDescription() {
			return description;
		}

		public List<Skill[]> getCareerSkills() {
			return careerSkills;
		}

		public static class RoleBuilder {
			private List<Skill[]> careerSkills = new ArrayList<Skill[]>();
			private String name;
			private String blurb;
			private String description;

			public RoleBuilder addCareerSkill(Skill... skills) {
				this.careerSkills.add(skills);
				return this;

			}

			public RoleBuilder withName(String name) {
				this.name = name;
				return this;
			}

			public RoleBuilder withBlurb(String blurb) {
				this.blurb = blurb;
				return this;
			}

			public RoleBuilder withDescription(String description) {
				this.description = description;
				return this;
			}

			public Role build() {
				return new Role(this);
			}
		}

	}

	public static class Skill {
		private String type = "N/A";
		private String skillName = "NIL";
		private String specifiedSkill = "";
		private String description = "";
		private int rank = 0;

		private Skill(SkillBuilder skillBuilder) {
			this.type = skillBuilder.type;
			this.skillName = skillBuilder.skill;
			this.specifiedSkill = skillBuilder.specifiedSkill;
			this.description = skillBuilder.description;
			this.rank = skillBuilder.rank;
		}

		public String getType() {
			return type;
		}

		public String getSkillName() {
			return skillName;
		}

		public String getSpecifiedSkill() {
			return specifiedSkill;
		}

		public String getDescription() {
			return description;
		}

		public int getRank() {
			return rank;
		}

		public void setSpecifiedSkill(String newSpecifiedSkill) {
			specifiedSkill = newSpecifiedSkill;
		}

		public void setRank(int newRank) {
			rank = newRank;
		}

		public static class SkillBuilder {
			private String skill = "NIL";
			private String type = "N/A";
			private String specifiedSkill = "";
			private int rank = 0;
			private String description = "";

			public SkillBuilder withType(String type) {
				this.type = type;
				return this;
			}

			public SkillBuilder withSkillName(String skill) {
				this.skill = skill;
				return this;
			}

			public SkillBuilder withSpecifiedSkill(String specifiedSkill) {
				this.specifiedSkill = specifiedSkill;
				return this;
			}

			public SkillBuilder withDescription(String description) {
				this.description = description;
				return this;
			}

			public SkillBuilder withRank(int rank) {
				this.rank = rank;
				return this;
			}

			public Skill build() {
				return new Skill(this);
			}

		}

	}

	public static class Weapon {
		private String name;
		private String type;
		private String category;
		private int accuracy;
		private String concealability;
		private String availability;
		private String damageAndAmmo;
		private int numberOfShots;
		private int rateOfFire;
		private String reliability;
		private String range;
		private double weight;
		private int cost;

		public String getName() {
			return name;
		}

		public String getType() {
			return type;
		}

		public String getCategory() {
			return category;
		}

		public int getAccuracy() {
			return accuracy;
		}

		public String getConcealability() {
			return concealability;
		}

		public String getAvailability() {
			return availability;
		}

		public String getDamageAndAmmo() {
			return damageAndAmmo;
		}

		public int getRateOfFire() {
			return rateOfFire;
		}

		public int getNumberOfShots() {
			return numberOfShots;
		}

		public String getReliability() {
			return reliability;
		}

		public String getRange() {
			return range;
		}

		public double getWeight() {
			return weight;
		}

		public int getCost() {
			return cost;
		}

		private Weapon(WeaponBuilder weaponBuilder) {
			this.name = weaponBuilder.name;
			this.type = weaponBuilder.type;
			this.category = weaponBuilder.category;
			this.accuracy = weaponBuilder.accuracy;
			this.concealability = weaponBuilder.concealability;
			this.availability = weaponBuilder.availability;
			this.damageAndAmmo = weaponBuilder.damageAndAmmo;
			this.numberOfShots = weaponBuilder.numberOfShots;
			this.rateOfFire = weaponBuilder.rateOfFire;
			this.reliability = weaponBuilder.reliability;
			this.range = weaponBuilder.range;
			this.weight = weaponBuilder.weight;
			this.cost = weaponBuilder.cost;
		}

		public static class WeaponBuilder {
			private String name = "Unkown";
			private String type = "Unknown";
			private String category = "Unknown";
			private int accuracy = 0;
			private String concealability = "";
			private String availability = "";
			private String damageAndAmmo = "";
			private int numberOfShots = 0;
			private int rateOfFire = 0;
			private String reliability = "";
			private String range = "0m";
			private double weight = 0.0;
			private int cost = 0;

			public WeaponBuilder withName(String name) {
				this.name = name;
				return this;
			}

			public WeaponBuilder withType(String type) {
				this.type = type;
				return this;
			}

			public WeaponBuilder withCategory(String category) {
				switch (category.toUpperCase()) {
				case "P":
					this.category = "Pistol";
					break;
				case "LP":
					this.category = "Lt Pistol";
					break;
				case "MP":
					this.category = "Med Pistol";
					break;
				case "HP":
					this.category = "Hvy Pistol";
					break;
				case "VHP":
					this.category = "Very Hvy Pistol";
					break;
				case "LSMG":
					this.category = "Lt SMG";
					break;
				case "MSMG":
					this.category = "Med SMG";
					break;
				case "HSMG":
					this.category = "Hvy SMG";
					break;
				case "RIF":
					this.category = "AR";
					break;
				case "SHG":
					this.category = "Shotgun";
					break;
				case "HVY":
					this.category = "Hvy Weapon";
					break;
				case "M":
					this.category = "Melee";
					break;
				default:
					this.category = category;
					break;
				}
				return this;
			}

			public WeaponBuilder withAccuracy(int accuracy) {
				this.accuracy = accuracy;
				return this;
			}

			public WeaponBuilder withConcealability(String concealability) {
				switch (concealability.toUpperCase()) {
				case "P":
					this.concealability = "Pocket, Pants Leg or Sleeve";
					break;
				case "J":
					this.concealability = "Jacket, Coat or Shoulder Rig";
					break;
				case "L":
					this.concealability = "Long Coat";
					break;
				case "N":
					this.concealability = "Cannot be Hidden";
					break;
				default:
					this.concealability = concealability;
					break;
				}
				return this;
			}

			public WeaponBuilder withAvailability(String availability) {
				switch (availability.toUpperCase()) {
				case "E":
					this.availability = "Excellent";
					break;
				case "C":
					this.availability = "Common";
					break;
				case "P":
					this.availability = "Poor";
					break;
				case "R":
					this.availability = "Rare";
					break;
				default:
					this.availability = availability;
					break;
				}
				return this;
			}

			public WeaponBuilder withDamageAndAmmo(String damageAndAmmo) {
				this.damageAndAmmo = damageAndAmmo;
				return this;
			}

			public WeaponBuilder withNumberOfShots(int numberOfShots) {
				this.numberOfShots = numberOfShots;
				return this;
			}

			public WeaponBuilder withRateOfFire(int rateOfFire) {
				this.rateOfFire = rateOfFire;
				return this;
			}

			public WeaponBuilder withReliability(String reliability) {
				switch (reliability.toUpperCase()) {
				case "VR":
					this.reliability = "Very Reliable";
					break;
				case "ST":
					this.reliability = "Standard";
					break;
				case "UR":
					this.reliability = "Unreliable";
					break;
				default:
					this.reliability = reliability;
					break;
				}
				return this;
			}

			public WeaponBuilder withRange(String range) {
				this.range = range;
				return this;
			}

			public WeaponBuilder withWeight(double weight) {
				this.weight = weight;
				return this;
			}

			public WeaponBuilder withCost(int cost) {
				this.cost = cost;
				return this;
			}

			public Weapon build() {
				return new Weapon(this);
			}
		}
	}

}
