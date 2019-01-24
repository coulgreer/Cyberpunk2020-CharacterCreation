package rpg.cyberpunk._2020.stats;

import rpg.cyberpunk._2020.AttributeName;
import rpg.general.stats.Attribute;

public class SkillManager {
	private Attribute intelligence;
	private Attribute reflexes;
	private Attribute cool;
	private Attribute technicalAbility;
	private Attribute attractiveness;
	private Attribute empathy;
	private Attribute bodyType;

	private BroadSkill allSkills;
	private BroadSkill specialSkills;
	private BroadSkill attractivenessSkills;
	private BroadSkill bodyTypeSkills;
	private BroadSkill coolSkills;
	private BroadSkill empathySkills;
	private BroadSkill intelligenceSkills;
	private BroadSkill knowLanguage;
	private BroadSkill nigerCongoFamily;
	private BroadSkill finnicFamily;
	private BroadSkill balticFamily;
	private BroadSkill celticFamily;
	private BroadSkill germanicFamily;
	private BroadSkill pacificIslandGroupFamily;
	private BroadSkill romanticFamily;
	private BroadSkill semeticFamily;
	private BroadSkill sinoTibetanAndSouthEastAsianFamily;
	private BroadSkill slavicFamily;
	private BroadSkill reflexSkills;
	private BroadSkill martialArt;
	private BroadSkill piloting;
	private BroadSkill technicalSkills;

	public SkillManager(AttributeManager attributeManager) {
		this.intelligence = attributeManager.get(AttributeName.INTELLIGENCE);
		this.reflexes = attributeManager.get(AttributeName.REFLEXES);
		this.cool = attributeManager.get(AttributeName.COOL);
		this.technicalAbility = attributeManager.get(AttributeName.TECHNICAL_ABILITY);
		this.attractiveness = attributeManager.get(AttributeName.ATTRACTIVENESS);
		this.empathy = attributeManager.get(AttributeName.EMPATHY);
		this.bodyType = attributeManager.get(AttributeName.BODY_TYPE);

		allSkills = new BroadSkill(CyberpunkSkill.NONE, "");
		initializeSpecialSkills();
		initializeAttractivenessSkills();
		initializeBodyTypeSkills();
		initializeCoolSkills();
		initializeEmpathySkills();
		initializeIntelligenceSkills();
		initializeReflexSkills();
		initializeTechnicalSkills();
	}

	private void initializeSpecialSkills() {
		specialSkills = new BroadSkill(CyberpunkSkill.SPECIAL_SKILLS, "");
		allSkills.add(specialSkills);
		specialSkills.add(new RoleCyberpunkSkill(cool, CyberpunkSkill.AUTHORITY,
				"The ability to intimidate or control others through your position as a lawman." //
						+ " This attribute represents the Cop's ability to call on the forces of the Law and Government to get what he wants." //
						+ " Cops can use Authority to question suspects, arrest wrongdoers, and  defend innocents." //
						+ " Backed by the power of Authority, a cop can arrest, detain, confiscate and enter nearly anywhere, as long as he has the proper arrest or search warrants to back his play." //
						+ " However, quthority is only as good as the guy holding the badge--if the cop appears uncertain of his Authority, there's a good chance he'll get nailed by the peoiple he's trying to confront." //
						+ " The higher your Authority, the more able you are to face down criminals, particularly high level mobsters and officials." //
						+ " Authority is applied to your Cool stat."));
		specialSkills.add(new RoleCyberpunkSkill(cool, CyberpunkSkill.CHARISMATIC_LEADERSHIP,
				"This skill allows the Rocker to sway crowds equal to his level squared time 200." //
						+ " This ability (added to your Cool stat) allows the Rockerboy to control, incite and charm large number of people through his or her performance skills." //
						+ " When under the Rocker's control, this group can easily be persuaded to act on his suggestion." //
						+ " For example, a Rocker could convince a concert crowd to riot in the streets or attack a heavily fortified police line." //
						+ " Charismatic Leadership will only work with groups of ten or more people as it is primarily a mob leadership ability." //
						+ " The higher your Charismatic Leadership, the larger a crowd you can control and the more direct and complex the instructions you can get them to follow." //
						+ " For example, a Level +3 Leadership could incite a nightclub crowd to get rowdy." //
						+ " A Level +5 or +6 could provoke a concert crowd of thousands to trash a neighborhood, if the area wasn't too far from hall." //
						+ " At Level +9, and higher, you have the same sort of mesmeric ability as an Adolph Hitler - you can raise armies, start movements." //
						+ " And destroy nations."));
		specialSkills.add(new RoleCyberpunkSkill(CyberpunkSkill.COMBAT_SENSE,
				"This ability is based on Solo's constant training and professionalism." //
						+ " Combat Sense allows the Solo to perceive danger, notice traps, and have an almost unearthly ability to avoid harm." //
						+ " Your Combat Sense gives you a bonus on both your Awareness skill and your Initiative equal to your level in the Combat Sense skill."));
		specialSkills.add(new RoleCyberpunkSkill(intelligence, CyberpunkSkill.CREDIBILITY,
				"This is the ability to be believed: by your viewers, by the police, by important and powerful people." //
						+ " This is critical to getting your story heard and acted upon, as well as convincing people to tell you things, give you information, or get you into where the story is really happening." //
						+ " The higher your Credibility, the more people you can convince, and the easier it is to convince high level authorities of the truth of your information." //
						+ " With a level +3 Credibility, you can convince most people of minor scandals." //
						+ " With a level +5 or +6, you can convince local officials of military atrocities, undercover dealings and other front page stuff." //
						+ " At level +9, you can successfully expose a scandal of Watergate proportions, or convince the President of the EuroMarket Finance Board that aliens are secretly influencing world leaders." //
						+ " Credibility applies to your INT stat."));
		specialSkills.add(new RoleCyberpunkSkill(intelligence, CyberpunkSkill.FAMILY,
				"This is the ability to call upon the resources and help of any of the members of the Nomad's large, extended tribal family." //
						+ " This can be in the form of weapons, cash, information, or a small army of relatives." //
						+ " The threat of a Nomad family's vengeance may in itself stop harm to the Nomad." //
						+ " The higher your Family ability, the more important you are to the Family and the more help you can call upon." //
						+ " With a Family status of +2, you might  be able to get several of the Pack to help you wreck a town, for example." //
						+ " With a status of +7 or +8, you are able to make major Pack decisions and lead troops." //
						+ " At +10, you may be the Leader of your Pack." //
						+ " Family is applied to your INT stat."));
		specialSkills.add(new RoleCyberpunkSkill(intelligence, CyberpunkSkill.INTERFACE,
				"This skill reflects the Netrunner's ability to manipulate Interface programs," //
						+ " and is the Skill used when operating Menu functions such as Locate Remote, Run Software, Control Remote, Downlink, Load, Create and Delete." //
						+ " Other player can enter the Net, but cannot use the Menu." //
						+ " Interface is based on the INT Stat." //
						+ " Note for Cyberpunk 1 players--you may elect to swap your original INT and REF stats for characters generated with the old rule."));
		specialSkills.add(new RoleCyberpunkSkill(CyberpunkSkill.JURY_RIG,
				"This general repair skill allows the Techie to temporarily repair or alter anything for 1D6 turns per level of skill." //
						+ " This is not a permanent repair; after the elapsed time, the jury rig will break down."));
		specialSkills.add(new RoleCyberpunkSkill(CyberpunkSkill.MEDICAL_TECH,
				"This skill assumes that the character has studied medicine in a professional setting." //
						+ " This gives him the ability to perform surgery, prescribe drugs, and know the proper treatment of injuries." //
						+ " They can replace damaged organs with vatgrown pieces, graft on new limbs, or install cyberlimbs." //
						+ " You cannot perform Medical Tech skills on yourself."));
		specialSkills.add(new RoleCyberpunkSkill(intelligence, CyberpunkSkill.RESOURCES,
				"This represents the Corporate's ability to command corporation resources." //
						+ " It is used as a persuasion skill, based on the scale of resources requested." //
						+ " This could include bodyguards, weapons, vehicles, buildings, money, etc." //
						+ " Obviously, the more powerful the Corporate, the more he can call upon at any one time." //
						+ " Your level of Resources determines exactly how much you can request from the Corporation without overreaching yourself." //
						+ " A Resource ability of +2 might get you access to a Company car." //
						+ " An ability of +6 might allow you to use a Company jet or hire a Solo team from the Corporate Security Division." //
						+ " A Resource of +9 would allow you access to almost all levels of the Corporation, as well as the ability to requisition almost any Company resource." //
						+ " Your Resource ability is applied to your INT stat."));
		specialSkills.add(new RoleCyberpunkSkill(cool, CyberpunkSkill.STREETDEAL,
				"This is the ability to deal with the underground information network." //
						+ " With Streetdeal, a Fixer can uncover rumors and information, locate missing persons or things, put gossip out on the Street, pick up clues and score big deals." //
						+ " The higher your Streetdeal ability, the more information you can gather about things happening around you, the more informants you have, and the more secretive the information you can dig up." //
						+ " A level +3 Streetdeal can get you contacts for weapons, tools, or minor illegal operations." //
						+ " At level +5, you can penetrate the secrets of all but the most powerful crime families." //
						+ " At level +9, you are the equivalent of a Mafia crimelord yourself, privy to every secret that's on the Street." //
						+ " Apply Streetdeal to your Cool stat."));
	}

	private void initializeAttractivenessSkills() {
		Attribute attribute = attractiveness;
		attractivenessSkills = new BroadSkill(CyberpunkSkill.ATTRACTIVENESS_SKILLS, "");
		allSkills.add(attractivenessSkills);
		attractivenessSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.PERSONAL_GROOMING,
				"This is the skill of knowing proper grooming, hair styling, etc., to maximize your physical attractiveness." //
						+ " Use of this skill allows players to increase their Attractiveness, and thus their chances of successful Relationships or Persuasions." //
						+ " A basically good looking person would be at +2." //
						+ " A fashion model might have a Personal Grooming of +5 or +6." //
						+ " At +8 or better, you could be a major fashion model, film star, or trendsetter." //
						+ " You are always \"together\". And you know it.",
				1));
		attractivenessSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.WARDROBE_AND_STYLE,
				"The skill of knowing the right clothes to wear, when to wear them, and how to look cool even in a spacesuit." //
						+ " With a Wardrobe of +2 or better, you are good at choosing clothes off the rack." //
						+ " At +6, your friends ask you for wardrobe tips, and you never buy anything off the rack." //
						+ " At +8 or better, you are one of those rare people whose personal style influences major fashion trends.",
				1));
	}

	private void initializeBodyTypeSkills() {
		Attribute attribute = bodyType;
		bodyTypeSkills = new BroadSkill(CyberpunkSkill.BODY_TYPE_SKILLS, "");
		allSkills.add(bodyTypeSkills);
		bodyTypeSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.ENDURANCE,
				"This is the ability to withstand pain or hardship, particularly over long periods of time, by knowing the best ways to conserve strength and energy." //
						+ " Endurance Skill checks would be made whenever a character must continue to be active after a long period without food, sleep or water.",
				1));
		bodyTypeSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.STRENGTH_FEAT,
				"The user of this skill has practived the art of bending bars, crushing objects, ripping phone books apart and other useful parlor tricks." //
						+ " At +2 you can crush cans, rip thin books in half, and bend thin rods." //
						+ " At +8, no phonebook is safe, you can bend thin rebar, and snap handcuffs." //
						+ " At +10, you can bend prison bars, rip up the Gutenberg Bible, and dent car fenders with one blow.",
				1));
		bodyTypeSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.SWIMMING,
				"This skill is required to know how to swim (see Athletics for details).", 1));
	}

	private void initializeCoolSkills() {
		Attribute attribute = cool;
		coolSkills = new BroadSkill(CyberpunkSkill.COOL_SKILLS, "");
		allSkills.add(coolSkills);
		coolSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.INTERROGATION,
				"The skill of drawing information from a subject and forcing his secrets into the open." //
						+ " An Interrogation of +2 or better will allow you to infallibly find out if your boyfriend is lying to you." //
						+ " At +5, you are a professional level interrogator--equivalent to a skilled detective grilling a suspect." //
						+ " Mike Wallace of 60 Minutes has an Interrogation of +9, allowing him to make even the most  powerful people squirm.",
				1));
		coolSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.INTIMIDATE,
				"The skill of getting people to do what youy want by force of personality or physical coercion." //
						+ " At +3, you can frighten almost any typical citizen, politician or low-level thug." //
						+ " At +6, you can intimidate Sylvester Stallone or any moderate \"tough guy\"." //
						+ " At +9, you could intimidate Arnold Schwartzenegger.",
				1));
		coolSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.ORATORY,
				"The skill of public speaking. At +2, you can wing high school speech contests." //
						+ " At +6, you can be paid to speak in public." //
						+ " At +10, you are capable of delivering a speech to rival Kennedy's \"Ich Bin Ein Berliner\" or Lincoln's Gettysburg Address." //
						+ " Rockers with an Oratory Skill of +6 or better can add +1 when using their Charasmatc Leadership ability.",
				1));
		coolSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.RESIST_TORTURE_AND_DRUGS,
				"Characters with this skill are especially toughened against interrogation, torture and mind control drugs." //
						+ " A successful use of this skill will automatically increase the difficulty of any interrogation attempt made by another party by one level",
				1));
		coolSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.STREETWISE,
				"The knowledge of the \"seamy\" side of life--where to get illegal and contraband things, how to talk to the criminal element, and avoiding bad situations in bad neighborhoods." //
						+ " With a Streetwise of +2 or better, you can get \"hot\" items, score drugs, etc." //
						+ " A Streetwise of +5 would allow you to arrange a murder contract, know a few mobsters who might owe you favors, and be able to call on muscle when you need it." //
						+ " At +8 or better, you could become a major crimelord yourself and skip the middlemen.",
				1));
	}

	private void initializeEmpathySkills() {
		Attribute attribute = empathy;
		empathySkills = new BroadSkill(CyberpunkSkill.EMPATHY_SKILLS, "");
		allSkills.add(empathySkills);
		empathySkills.add(new SpecializedSkill(attribute, CyberpunkSkill.HUMAN_PERCEPTION,
				"The skill of detecting lies, evasions, moods and other emotional clues from others." //
						+ " At +2, you can usually tell when you're not getting the whole truth." //
						+ " At +6, you can detect subtle evasions and mood swings." //
						+ " At +8, you can not only detect subtle emotional clues, but can usually tell what the subject is hiding in a general way.",
				1));
		empathySkills.add(new SpecializedSkill(attribute, CyberpunkSkill.INTERVIEW,
				"The skill of eliciting interesting annecdotes from an interview subject." //
						+ " This information will be of a more non-specific and personal nature rather than specific knowledge" //
						+ " (distinguishing this skill from the skill of Interrogation, where the user is trying to extract exact information." //
						+ " Example: Barbara Walters interviews, Mike Wallace interrogates)." //
						+ " At +3 or better, the subject will usually tell you only infromation relating to what he/she is well known for." //
						+ " At +6 or better, the subject will tell you anecdotes about the past, pontificate about favorite interests and philosophies, etc." //
						+ " At +9 or better, he/she tells you everything--including personal information about their illegitimate son, the time they stole a cookie at age 4, and the fact that no one ever loved them.",
				1));
		empathySkills.add(new SpecializedSkill(attribute, CyberpunkSkill.LEADERSHIP,
				"The skill of leading and convincing people to follow you." //
						+ " A leader with a skill of +2 can manage a small office successfully and be respected for it." //
						+ " A leader with a skill of +4 or better can lead a small band of troops into battle and not get backshot." //
						+ " A leader with a skill of +7 or better can lead the entire Gamelon Empire into battle and look good doing it." //
						+ " James Kirk of Star Trek has a Leadership of +11, but you never will.",
				1));
		empathySkills.add(new SpecializedSkill(attribute, CyberpunkSkill.SEDUCTION,
				"The skill of forming and maintaining romantic relationships (this includes your abilities as a lover)." //
						+ " This skill may be used to determine whether or not players can form relationships with other non-player characters and the intensity of these relationships." //
						+ " In certain cases, Referees may want to average this skill with a player's Attractiveness to get a more realistic outcome.",
				1));
		empathySkills.add(new SpecializedSkill(attribute, CyberpunkSkill.SOCIAL,
				"The ability to deal with social situations, like knowing the right fork to use or when not to tell the joke about the farmer's daughter and the travelling cyberware salesman." //
						+ " A social Skill of +2 or better will allow you to get by at any fine restaurant or social function." //
						+ " At +5, you can lunch with the President with aplomb. No soical situation will faze you, no matter what." //
						+ " At +8 or above, you can lecture Emily Post on what's proper.",
				1));
		empathySkills.add(new SpecializedSkill(attribute, CyberpunkSkill.PERSUASION,
				"The ability to talk others into doing what you want." //
						+ " This may be used individually or on large groups." //
						+ " At +3, you can win most debates or convince your girlfriend that the blonde you were with was your sister." //
						+ " At +5, you are a smooth talker of professional caliber." //
						+ " Ronald Reagan has a Persuasion of +7." //
						+ " Hitler had a Persuasion of +9.",
				1));
		empathySkills.add(new SpecializedSkill(attribute, CyberpunkSkill.PERFORM,
				"The skill of trained acting, singing, etc." //
						+ " A trained performer of +4 or greater can successfully sing for payment at weddings or small clubs." //
						+ " Performers +6 or greater will be considered to be of professional caliber, and may have lucrative contracts and fans." //
						+ " Performers of +9 or greater are of \"star\" caliber, have a large number of fans, and may be recognized on the street.",
				1));
	}

	private void initializeIntelligenceSkills() {
		Attribute attribute = intelligence;
		intelligenceSkills = new BroadSkill(CyberpunkSkill.INTELLIGENCE_SKILLS, "");
		allSkills.add(intelligenceSkills);
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.ACCOUNTING,
				"The ability to balance books (or create false books), juggle numbers, create budgets and handle day to day business operations.",
				1));
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.ANTHROPOLOGY,
				"The knowledge of human cultures, habits and customs." //
						+ " Unlike Streetwise (which covers only the cultures and customs of the Street), or Social (which covers only what you should do in a given situation), Anthropology covers general customs and background of a culture." //
						+ " For example, with Streetwise, you know what alleys to avoid and what gangs are dangerous." //
						+ " With Social, you know the proper forms of address for a high ranking Japanese zaibatsu head." //
						+ " With Anthropology, you know that the customs of a N'Tanga tribesman require that a young man kill a lion in order to be accepted as an adult male.",
				1));
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.AWARENESS,
				"This is the equivalent of a \"trained observer\" skill, allowing characters to notice or be aware of clues, shadowers and other events." //
						+ " With an Awareness of +2 you will usually spot small pieces of paper with notes on them, doors left ajar, and obvious expressions of lying or dislike." //
						+ " An Awareness of +5 or better allows you to spot fairly well hidden clues, notice small changes in expression, and fairly sophisticated attempts to \"shadow\" you." //
						+ " With an Awareness of +8 or greater, you routinely perform the sorts of deductive reasoning seen in the average TV cop show (\"The murderer was left handed because this knife has a specialized handle\")." //
						+ " Sherlock Holmes has a +10 Awareness. Players without this skill may only use their INT Stat.",
				1));
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.BIOLOGY,
				"General knowledge of animals, plants, and other biological systems." //
						+ " At level +3, you know most types of common animals, plants." //
						+ " At +6, you have a general understanding of genetics, cellular biology, etc." //
						+ " At +10, you can perform most bio-lab procedures, including gene mapping and splicing.",
				1));
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.BOTANY,
				"The general knowledge of plants and plant identification." //
						+ " At level +3, you know most common plants and can identify which ones are dangerous and why." //
						+ " At a +6, you can identify most importaant plants found worldwide and have a working knowledge of their uses." //
						+ " At +8, you have the equivalent of a doctorate in Botany and know all about rare poisons, exotic orchids and other useful plants.",
				1));
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.CHEMISTRY,
				"The required skill for mixing chemicals and creating various compounds." //
						+ " A level +2 Chemistry is equal to high school chemistry." //
						+ " A level +4 is equal to a trained pharmacist or college level chemist." //
						+ " A +8 is a trained laboratory chemist.",
				1));
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.COMPOSITION,
				"The required skill for writing songs, articles, or stories." //
						+ " A Composing Skill of +4 or greater gives your character the ability to produce salable work." //
						+ " A Skill of +8 or more produces work of such a high caliber that the creator may have a strong literary following and not a little critical acclaim.",
				1));
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.DIAGNOSE_ILLNESS,
				"The skill of clinically diagnosing symptoms and medical problems." //
						+ " A +3 is the equivalent of a high school nurse--you can recognize most common injuries and complaints." //
						+ " At +6, you would be equivalent to a trained intern, you can recognize many uncommon illnesses and know how to treat most common ones." //
						+ " A +9 is the equivalent of a skilled diagnostician; other physicians come to you to get a diagnosis.",
				1));
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.EDUCATION,
				"This skill is the equivalent of a basic public school education, allowing you to know how to read, write, use basic math, and know enough history to get by." //
						+ " In effect, it is a \"lore\" or trivia skill." //
						+ " A level of +1 is a basic grade school education." //
						+ " A skill of +2 is equal to a high school equivalency." //
						+ " A Knowledge Skill of +3 is equal to a college education, +4 or higher is equal to a Masters or Doctorate." //
						+ " At +7, you are an extremely well-educated person, and are asked to play Trivial Pursuit a lot." //
						+ " At +9 and above, you are one of those people who knows a lot about everything (and hopefully has the good sense to keep his mouth shut).",
				1));
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.EXPERT,
				"You may use this skill to be an expert on one specific subject, such as rare postage stamps, obscure weapons, a foreign language, etc." //
						+ " At +3, you are the local expert." //
						+ " At +6, you know enough to publish a few books on the subject." //
						+ " At +8 or better, your books are recognized as major texts on the subject, and you could do the talk-show circuit if you wanted to.",
				1));
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.GAMBLE,
				"The skill of knowing how to make bets, figure odds, and play games of chance successfully." //
						+ " As any professional gambler knows, this is not a luck skill." //
						+ " At +2, you are the local card shark at the Saturday night poker game." //
						+ " At +6, you can make a living at the tables in Vegas and Monte Carlo." //
						+ " At +9 or better, you can take on James Bond at roulette and stand a good chance of breaking the bank.",
				1));
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.GEOLOGY,
				"A functional knowledge of rocks, minerals and geologic structures."
						+ " At +3, you can identify most common rocks and minerals."
						+ " At +6, you have the equivalent of a college degree in Geology and can identify minerals and geological structures with ease."
						+ " At +8, you can teach geology in high school.",
				1));
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.HIDE_AND_EVADE,
				"The skill of losing pursuers, covering tracks and otherwise evading people on your trail." //
						+ " At +3, you can lose most booster-gangers on the rampage." //
						+ " At +6, you can ditch cops and private eyes." + " At +8, you can ditch most Solos.",
				1));
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.HISTORY,
				"The knowledge of facts and figures of past events." //
						+ " In game play, this might be used to determine if a character is familiar with a particular clue related to a past event." //
						+ " At +2, you have the equivalent of a grade school history education." //
						+ " At +6, you would have the equivalent of a college grasp on the subject." //
						+ " At +8, you could teach history in high school." //
						+ " At +9, you may have written a few of the most oftused texts on a particular historical personage or epoch.",
				1));
		initializeLanguageSkills(attribute);
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.LIBRARY_SEARCH,
				"The skill of using databases, DataTerms, libraries and other compiled information cources to find facts." //
						+ " With a skill of +2 you can use most simple databases." //
						+ " With a skill of +6, you can easily access the Library Congress." //
						+ " At +9, you can comprehend almost any public database and find very obscure facts.",
				1));
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.MATHEMATICS,
				"The skill of understanding calculations and mathematical formulas." //
						+ " At +3, you have the ability to add, subtract, divide and multiply." //
						+ " At +4, you can do algebra and geometry. At +6, you can perform calculus." //
						+ " At +9 you can deduce your own mathematical formulas.",
				1));
		// TODO Depends on Mathematics
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.PHYSICS,
				"The ability to calculate physical principles, such as gas pressure, mechanical energies, etc." //
						+ " This skill requires a basic Mathematics Skill of +4.",
				1));
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.PROGRAMMING,
				"The required skill to write programs and to re-program computer systems." //
						+ " This skill does not allow players to actually do repairs on a computer (this requires Electronics)." //
						+ " With a Programming Skill of +1, you can do simple EBASIC programs." //
						+ " A Programming Skill of +3 or better allows you to know some higher level languages and be able to write reasonably complex programs (including video games)." //
						+ " Players with a Programming Skill +6 or better are considered to be professionals, who can build operating software, design mainframe systems, and hold down a steady job at your average Silicon Valley firm." //
						+ " With a Programming Skill of +9 or better, other programmers speak your name with reverence (\"You invented Q? Wow!\"), young hackers set out to crack your systems," //
						+ " and any computer software you design instantly gets used by every business application in the world.",
				1));
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.SHADOW_AND_TRACK,
				"The skill of shadowing and following people. " //
						+ "This skill is primarily used in urban or inhabited areas rather than in wilderness (where the skill of Survival incorporates tracking game in the wilds).",
				1));
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.STOCK_MARKET,
				"The ability to play the stock market, engage in routine stock transactions and manipulate stocks profitably." //
						+ " At +2, you know enough to invest in junk bonds and lose your shirt." //
						+ " At +6, your investments pay off 75% of the time." //
						+ " At +9, you are a major heavy on the Market, routinely dabble in international stocks, and can write learned articles on the subject of investment.",
				1));
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.SYSTEM_KNOWLEDGE,
				"Basic knowledge of the geography of the Net, it's lore and history, as well as knowledge of the important computer systems, their strengths and their weaknesses." //
						+ " At +2, you can generally navigate around the Net and know where all the local places are." //
						+ " At +6, you know the locations of most places in the Net, and have a working understanding of its largest and most well known systems." //
						+ " At +9, you know the entire Net like the back of your hand, know the general layouts of the important systems cold, and are aware of the layouts for the rest of them.",
				1));
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.TEACHING,
				"The skill of imparting knowledge to someone else (if you don't think this is a skill, you ought to try it sometime)." //
						+ " Players may not teach any skill unless they have a higher skill level than the student." //
						+ " The referee is the final arbiter of how long it takes to teach a skill." //
						+ " At a Teaching Skill of +3 or better, you can professionally teach students up to High School." //
						+ " At +6, you know enough to be a college professor (if you wanted)." //
						+ " At +9 or greater, you are recognized by others in the field as good enough to guest lecture at MIT or Cal Tech; your texts on the subject are quoted as the major references, and you might have a TV show on the equivalent of the PBS channel.",
				1));
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.WILDERNESS_SURVIVAL,
				"The required skill for knowing how to survive in the wilds." //
						+ " Knowledge includes how to set traps, forage for wood, track game, build shelters, make fires." //
						+ " The average Boy Scout has a Survival of +3." //
						+ " A Special Forces Green Beret has a Survival of +6 or above." //
						+ " Grizzly Adams, Mountain Man of the Wilderness, would have a +9 or +10 Survival Skill.",
				1));
		intelligenceSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.ZOOLOGY,
				"Knowledge of lifeforms, biological processes and their relation to the environment." //
						+ " At +2, you know most common animals." //
						+ " At +5, you know not only well known animals, but also about many exotics and endangered species." //
						+ " At +8, you are knowledgable on almost all animals, know their habits well, and have a +1 advantage to any Wilderness Survival Skills (you know where to find the game).",
				1));
	}

	private void initializeLanguageSkills(Attribute attribute) {
		knowLanguage = new BroadSkill(CyberpunkSkill.KNOWN_LANGUAGE, "The knowledge of a foreign tongue."
				+ " At +2, you can \"get by\" with speaking the language." //
				+ " At +3, you can actually read a written form of it. At +6 and above, you are fairly fluent, although no native will be fooled by your ability." //
				+ " At +8 and above, you speak and read the language like a native." //
				+ " Each language known requires a separate Know Language Skill," //
				+ " however, one may use the knowledge of a particular Language with up to 1/2 (round down) proficiency with any language in the same linguistic family" //
				+ " (example: knowing Cantonese at +4 will give you the ability to understand and speak Mandarin at +2).");
		intelligenceSkills.add(knowLanguage);
		initializeNigerCongoLanguageFamily(attribute);
		initializeBalticLanguageFamily(attribute);
		initializeFinnicLanguageFamily(attribute);
		initializeCelticLanguageFamily(attribute);
		knowLanguage.add(new SpecializedSkill(attribute, CyberpunkSkill.PERSIAN,
				"Persian is a member of the Western Iranian branch of the Indo-European language family." //
						+ " It is spoken by about 110 million people, mainly in Iran, Afghanistan and Tajikistan, and also in Uzbekistan, Iraq, Russia and Azerbaijan." //
						+ " In 2009 there were about 60 million native speakers of Persian, and another 50 million second language speakers.",
				1));
		initializeGermanicLanguageFamily(attribute);
		knowLanguage.add(new SpecializedSkill(attribute, CyberpunkSkill.GREEK,
				"Greek belongs to the Hellenic branch of the Indo-European language family," //
						+ " and is spoken by about 13 million people mainly in Greece and Cyprus, where it is an official language." //
						+ " Greek is also recognised as a minority language in parts of Italy, and in Albania, Armenia, Romania and Ukraine.",
				1));
		knowLanguage.add(new SpecializedSkill(attribute, CyberpunkSkill.JAPANESE,
				"Japanese is a Japonic or Japanese-Ryukyuan language spoken mainly in Japan." //
						+ " According to the 2010 census there are 125 million Japanese speakers in Japan." //
						+ " There are another 3 million Japanese speakers elsewhere, particularly in Brazil, the USA, Peru, Argentina, Australia, Canada, the Philippines and Taiwan.",
				1));
		knowLanguage.add(new SpecializedSkill(attribute, CyberpunkSkill.KOREAN,
				"Korean is spoken by about 63 million people in South Korea, North Korea, China, Japan, Uzbekistan, Kazakhstan and Russia." //
						+ " The relationship between Korean and other languages is not known for sure, though some linguists believe it to be a member of the Altaic family of languages." //
						+ " Grammatically Korean is very similar to Japanese and about 70% of its vocabulary comes from Chinese.",
				1));
		initializePacificIslanderLanguageFamily(attribute);
		initializeRomanticLanguageFamily(attribute);
		initializeSemeticLanguageFamily(attribute);
		initializeSouthEastAsianLanguageFamily(attribute);
		initializeSlavicLanguageFamily(attribute);
	}

	private void initializeNigerCongoLanguageFamily(Attribute attribute) {
		nigerCongoFamily = new BroadSkill(CyberpunkSkill.NIGER_CONGO,
				"The Niger-Congo languages constitute one of the world's major language families and Africa's largest in terms of geographical area, number of speakers and number of distinct languages." //
						+ " It is generally considered to be the world's largest language family in terms of distinct languages, ahead of Austronesian." //
						+ " The number of named Niger-Congo languages listed is 1540." //
						+ " It is the third largest language family in the world by number of native speakers.");
		knowLanguage.add(nigerCongoFamily);
		nigerCongoFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.SHONA,
				"Shona is a member of the Bantu branch of the Niger-Congo language family." //
						+ " It is spoken by about 9 million people mainly in Zimbabwe, where more than 80% of the population are Shona speakers." //
						+ " There are also speaker in Zambia, Mozambique and Botswana.",
				1));
		nigerCongoFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.AKAN,
				"The Akan languages are part of the Kwa branch of the Niger-Congo language family." //
						+ " There are about 52 million speakers of Akan languages (in 2014) in eastern Ivory Coast, south-central Ghana, and central Togo." //
						+ " There are numerous dialects of Akan, including Twi, Fante, Bono, Wasa, Nzema, Baule and Anyi, with a high level of mutual intelligibility between them.",
				1));
		nigerCongoFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.KONGO,
				"Kongo is a Bantu language spoken in the Democratic Republic of the Congo, the Republic of the Congo and Angola." //
						+ " The people who speak Kongo are known as Bakongo and Bandundu." //
						+ " Creolized versions of the language are used in rituals in Brazil, Buba and Haiti, and Gullah, and Palenquero creole of Colmbia also contain Knogo elements." //
						+ " There are an estimated 6.5 million native speakers of Kongo (in 2012), and there are another 2 million who speak it as a second language.",
				1));
		nigerCongoFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.ZULU,
				"One of the official languages of South Africa and is a member of the Bantu/Nguni family of languages." //
						+ " It is spoken by about 9 million people mainly in Zululand and northern Natal in South Africa and also in Botswana, Lesotho, Malawi, Mozambique and Swaziland.",
				1));
		nigerCongoFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.SWAHILI,
				"Swahili is a Bantu language spoken in Tanzania Burundi, Congo (Kinshasa) Kenya, Mayotte, Mozambique, Oman, Rwanda, Somalia, South Africa, Uganda, UAE and the USA." //
						+ " Around 5 million people speak Swahili as a native language, and a further 135 million speak is as a second language." //
						+ " Swahili is an official language of Tanzania, Uganda and Kenya, and is used as a lingua franca throughout East Africa.",
				1));
	}

	private void initializeBalticLanguageFamily(Attribute attribute) {
		balticFamily = new BroadSkill(CyberpunkSkill.BALTIC,
				"The Baltic languages belong to the Balto-Slavic branch of the Indo-European language family." //
						+ " Baltic languages are spoken by the Balts, mainly in areas extending east and southeast of the Baltic Sea in Northern Europe.");
		knowLanguage.add(balticFamily);
		balticFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.LATVIAN,
				"Latvian is a Baltic language related to Lithuanian and Old Prussian with about 1.4 million speakers in Latvia." //
						+ " There are also Latvian speakers in the USA, Russia, Australia, Canada, Germany, the UK, Sweden, Lithuania, Ukraine, Estonia, Brazil and Belarus.",
				1));
		balticFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.LITHUANIAN,
				"Lithuanian is a Baltic language related to Latvian and Old Prussian with about 3.2 million speakers in Lithuania." //
						+ " There are also Lithuanian speakers in Poland, the USA, Brazil, Argentina, Canada, the UK and Uruguay.",
				1));
	}

	private void initializeFinnicLanguageFamily(Attribute attribute) {
		finnicFamily = new BroadSkill(CyberpunkSkill.FINNIC,
				"Finnic languages are a branch of the Uralic language family spoken around the Baltic Sea by Finnic peoples, mainly in Finland and Estonia, by about 7 million people.");
		knowLanguage.add(finnicFamily);
		finnicFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.ESTONIAN,
				"Estonian is a Finnic language spoken by about 1.1 million people in Estonia." //
						+ " It is closely related to Finnish," //
						+ " and the main difference between these two languages is that Finnish has many loanwords from Swedish, while Estonian contains many words of German origin, plus some from Russian, Latin, Greek and English." //
						+ " There is considerable mutual intelligibility between Estonian and Finnish.",
				1));
		finnicFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.FINNISH,
				"Finnish is a Finnic language spoken by about 5 million poeple, mainly in Finland and Sweden, and also in Norway, Estonia, Canada and Russia." //
						+ " It is closely related to Esthonian.",
				1));
	}

	private void initializeCelticLanguageFamily(Attribute attribute) {
		celticFamily = new BroadSkill(CyberpunkSkill.CELTIC,
				"The Celtic languages are a language family inside of Indo-European languages." //
						+ " There are six Celtic languages still spoken in the world today, spoken in north-west Europe." //
						+ " They are divided into two groups, Goidelic (or Gaelic) and the Brythonic (or British).");
		knowLanguage.add(celticFamily);
		celticFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.GAELIC,
				"Scottish Gaelic is spoke in Scotland, mainly in the Highlands and in the Western Isles, but also in Glasgow, Edinburgh and Inverness." //
						+ " There are also small Gaelic-speaking communities in Canada, particularly in Nova Scotia and Prince Edward Island." //
						+ " Other speakers can be found in Australia, New Zealand and the USA.",
				1));
		celticFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.WELSH,
				"Wesh is a Celtic language spoken in Wales by about 74,000 people, and in the Welsh colony in Patagonia, Argentina by several hundred people." //
						+ " There are also Welsh speakers in England, Scotland, Canada, the USA, Australia and New Zealand.",
				1));
		celticFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.BRETON,
				"Breton is a Celtic language spoken mainly in Brittany by about 210,000 people, about 35,000 of whom speak use it as an everyday language." //
						+ " It is spoken mainly in western parts of Brittany, and is also spoken, to some extent, in parts of eastern Brittany, and by Breton immigrants in other parts of France, and in other countries.",
				1));
	}

	private void initializeGermanicLanguageFamily(Attribute attribute) {
		germanicFamily = new BroadSkill(CyberpunkSkill.GERMANIC,
				"Germanic languages, branch of the Indo-European language family." //
						+ " Scholars often divide the Germanic languages into three groups: West Germanic, including English, German, and Netherlandic (Dutch);" //
						+ " North Germanic, including Danish, Swedish, Icelandic, Norwegian, and Faroese;" //
						+ " and East Germanic, now extinct, comprising only Gothic and the languages of the Vandals, Burgundians, and a few other tribes.");
		knowLanguage.add(germanicFamily);
		germanicFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.DANISH,
				"Danish is a North Germanic language with around 5.5 million speakers mainly in Denmark, Greenland and the Faroe Islands." //
						+ " There are also Danish speakers in Schleswig-Holstein in norhtern Germany, and in Norway, Sweden, Spanish, the USA, Canada, Brazil and Argentina.",
				1));
		germanicFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.DUTCH,
				"Dutch is a West Germanic language with about 28 million speakers (in 2012), mainly in the Netherlands and Belgium." //
						+ " There are small Dutch-speaking communities in northern France, around Dunkerque. Dutch is also spoken in Aruba, the Netherlands Antilles, Suriname and in Indonesia.",
				1));
		germanicFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.ENGLISH,
				"English is a West Germanic language related to Scots, Dutch, Frisian and German." //
						+ " It has a significant amount of vocabulary from Old Norse, Norman French, Latin and Greek, and loanwords from many other languages.",
				1));
		germanicFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.GERMAN,
				"German is a West Germanic language spoken mainly in Germany, Austria, Switzerland, Liechtenstein, Belgium, Luxenbourg and Italy." //
						+ " It is recognised as a minority language in Czech Republic, Denmark, Hungary, Kazakhstan, Ukraine, Namibia, Poland, Romania, Russia, Slovakia, Slovenia, Croatia, Serbia, South Africa, Vatican City and Venexuela." //
						+ " There are also significant German-speaking communities in the USA, Canada, Brazil, Argentina, Mexico, Australia, South Africa, Chile, Paraguay, New Zealand and Peru.",
				1));
		germanicFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.NORWEGIAN,
				"Norwegian is a North Germanic language with around 5 million speakers in mainly in Norway. There are also some speakers of Norwegian in Denmark, Sweden, Germany, the UK, Spain, Canada and the USA.",
				1));
		germanicFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.SWEDISH,
				"Swedish is a North Germanic language spoken by about 10 million people in Sweden." //
						+ " In 2007 there were 290,000 native speakers of Swedish in Finland, and 2.4 million second-language speakers." //
						+ " In 2010 there were an estimated 300,000 Swedish speakers in countries other than Sweden or Finland." //
						+ " Many live in the USA, UK, Spain and Germany, and also in other Scandinavian countries, France, Switerland, Belgium, the Netherlands, Canada and Australia.",
				1));
		germanicFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.YIDDISH,
				"Yiddish is a Germanic language with about three million speakers, mainly Ashkenazic Jews, in the USA, Israel, Russia, Ukraine and many other countries." //
						+ " The name Yiddish is probably an abbreviated version of yidish-taytsh, which means \"Jewish German\".",
				1));
	}

	private void initializePacificIslanderLanguageFamily(Attribute attribute) {
		pacificIslandGroupFamily = new BroadSkill(CyberpunkSkill.PACIFIC_ISLAND_GROUP,
				"Melanesian Pidgin, Hawaiian, Polynesian languages, Tahitian, Maori, are all languages spoken throughout island nations in the South Pacific Ocean." //
						+ " Aboriginal and Torres Strait Islander languages of Australia are endangered, Australia is the continent where languages are disappearing the fastest.");
		knowLanguage.add(pacificIslandGroupFamily);
		pacificIslandGroupFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.CHUUKESE,
				"Chuukese a member of the Micronesian branch of the Austronesian languages." //
						+ " The language is also known as Chuuk, Lagoon Chuukese, Ruk, Truk or Trukese," //
						+ " and is spoken by about 48,000 people on the islands of Chuuk in the Caroline Islands in Micronesia.",
				1));
		pacificIslandGroupFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.TAGALOG,
				"Tagalog is a Philippine language spoken in the Philippines, particularly in Manila, central and southern parts of Luzon, and also on the islands of Lubang, Marinduque, and the northern and eastern parts of Mindoro." //
						+ " Tagalog speakers can also be found in many other countries, including Canada, Guam, Midway Islands, Saudi Arabia, the UAE, UK and USA.",
				1));
		pacificIslandGroupFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.SAMOAN,
				"Samoan is a Polynesian language spoken mainly in Western Samoa and American Samoa by about 420,000 people." //
						+ " There are also speakers of Samoan in Fiji, Tonga, Hawaii, New Zealand, Australia and the USA." //
						+ " Samoan is closely related to the other languages of Polynesia, particularly to Tongan.",
				1));
		pacificIslandGroupFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.JAVANESE,
				"The earliest known writing in Javanese dates from the 4th Century AD, at which time Javanese was written with the Pallava alphabet." //
						+ " By the 10th Century the Kawi alphabet, which developed from Pallava, had a distinct Javanese form.",
				1));
		pacificIslandGroupFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.MALAY,
				"Malay is a Malayic language spoken in Malaysia, Indonesia, Singapore, Brunei and Thailand." //
						+ " The total number of speakers of Standard Malay is about 18 million." //
						+ " There are also about 170 million people who speak Indonesian, which is a form of Malay.",
				1));
		pacificIslandGroupFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.SUNDANESE,
				"Sundanese is a Malayo-Polynesian language spoken by about 39 million people mainly in western Java in Indonesia." //
						+ " There are also speakers in Banten, Jakarta, parts of western Central Java and southern Lampung." //
						+ " It is the third most-spoken language in Indonesia." //
						+ " Sundanese is closely related to Madurese and Malay, and more distantly related to Javanese.",
				1));
		pacificIslandGroupFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.INDONESIAN,
				"Indonesian, an Austronesian language, is a standardized form of Malay and is spoken throughout Indonesia."
						+ " About 30 million people speak Indonesian as their first language and a further 140 million speak it as a second language."
						+ " Indonesia is a linguistically diverse region where the Indonesian language acts as a lingua franca, even though there are more native speakers of Javanese - about 75 million.",
				1));
		pacificIslandGroupFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.HAWAIIAN,
				"Hawaiian is an Austronesian language spoken by about 8,000 people on the Hawaiian islands." //
						+ " Hawaiian first appeared in writing in the early 19th century in a version of the Latin alphabet developed by missionaries, who started to visit the Hawaiian islands from 1820 onwards." //
						+ " Literacy among the Hawaiian people was widespread during the 19th century when Hawaii was an independent kingdom." //
						+ " Dozens of Hawaiian language newspapers were published, together with Hawaiian translations of religious works and novels and Hawaiian transcriptions of traditional stories.",
				1));
	}

	private void initializeRomanticLanguageFamily(Attribute attribute) {
		romanticFamily = new BroadSkill(CyberpunkSkill.ROMANTIC,
				"The Romance languages (also sometimes called Romanic languages) are a language family in the Indo-European languages." //
						+ " They started from Vulgar Latin (In the Latin Language, \"vulgar\" is the word for \"common,\" so \"Vulgar Latin\" means \"Common Latin\")." //
						+ " The biggest Romance languages are Spanish, Portuguese, French, Italian and Romanian." //
						+ " They are called \"romance languages\" because they originate from Latin, the language spoken by the western Roman Empire.");
		knowLanguage.add(romanticFamily);
		romanticFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.FRENCH,
				"French is a Romance language spoken by about 354 million people." //
						+ " It is the third most spoken language in Europe, after German and English, and is also spoken in parts of Africa, North America, South America, Asia and Oceania.",
				1));
		romanticFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.ITALIAN,
				"Italian is a Romance language spoken by about 60 million people in Italy, Switzerland, San Marino, Vatican City, Malta and Eritrea." //
						+ " There are also Italian speakers in Chile, Argentina, Brazil, Australia, Canada, the USA and the UK.",
				1));
		romanticFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.SPANISH,
				"Spanish is a Romance language with approximately 470 million speakers, 410 of whom speak it as a first language while the remainder speak it as a second language. A significant number of people also speak Spanish as a foreign language. Spanish is spoken in Spain and 22 other countries including: Andorra, Argentina, Belize, Bolivia, Cayman Islands, Chile, Colombia, Costa Rica, Cuba, Dominican Republic, Ecuador, El Salvador, Equatorial Guinea, Guatemala, Honduras, Mexico, Nicaragua, Panama, Paraguay, Peru, Uruguay, the USA and Venezuela.",
				1));
		romanticFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.PORTUGUESE,
				"Portuguese is a Romance language spoken by about 220 million people mainly in Portugal and Brazil," //
						+ " and also in Angola, Mozambique, Cape Verde, Guinea-Bissau, São Tomé e Principe, East Timor, Equatorial Guinea and Macau." //
						+ " There are also communities of Portuguese speakers in Goa, Daman and Diu in India, and in Malacca in Malaysia.",
				1));
		romanticFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.LATIN,
				"Latin has been called a dead language, because nobody speaks it conversationally anymore." //
						+ " That moniker may not be entirely appropriate." + " Latin is more like a ghostly language." //
						+ " It's technically dead, but it still pops up every now and then to haunt us." //
						+ " For example, have you ever been asked to fill a quota, finished a list with etcetera or explained something vice versa?" //
						+ " All of these commonly used idioms come from the Latin language.\r\n" //
						+ "\r\n" //
						+ "However we define it, Latin played a major role in European history." //
						+ " It was, for a long time, the primary language of the Western world." //
						+ " It was one of the first great literary languages of Europe." //
						+ " It was spoken by millions, and influenced the rise of other written languages." //
						+ " It's a bona fide part of our past.",
				1));
	}

	private void initializeSemeticLanguageFamily(Attribute attribute) {
		semeticFamily = new BroadSkill(CyberpunkSkill.SEMETIC,
				"The Semitic languages are a branch of the Afroasiatic language family originating in the Middle East." //
						+ " Semitic languages are spoken by more than 330 million people across much of Western Asia, North Africa and the Horn of Africa," //
						+ " as well as in often large expatriate communities in North America and Europe, with smaller communities in the Caucasus and Central Asia.");
		knowLanguage.add(semeticFamily);
		semeticFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.ARABIC,
				"Egyptian Arabic is spoken by about 50 million people in Egypt as well as by immigrant Egyptian communities in the Middle East, Europe and North America." //
						+ " Egyptian Arabic is perhaps the most widely understood variety of Arabic, thanks to the popularity of Egyptian-made films and TV shows",
				1));
		semeticFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.HEBREW,
				"Hebrew is a member of the Canaanite group of Semitic languages." //
						+ " It was the language of the early Jews, but from 586 BC it started to be replaced by Aramaic." //
						+ " By 200 AD use of Hebrew as an everyday language had largely ceased," //
						+ " but it continued to be used for literary and religious functions, as well as a lingua franca among Jews from different countries.",
				1));
	}

	private void initializeSouthEastAsianLanguageFamily(Attribute attribute) {
		sinoTibetanAndSouthEastAsianFamily = new BroadSkill(CyberpunkSkill.SINO_TIBETAN_AND_SE_ASIAN,
				"The Sino-Tibetan languages, in a few sources also known as Trans-Himalayan, are a family of more than 400 languages spoken in East Asia, Southeast Asia and South Asia." //
						+ " The family is second only to Indo-European in terms of the number of native speakers." //
						+ " The Sino-Tibetan languages with the most native speakers are the varieties of Chinese (1.3 billion), Burmese (33 million), and the Tibetic languages (6 million)," //
						+ " but many Sino-Tibetan languages are spoken by small communities in remote mountain areas and as such are poorly documented." //
						+ " Unlike Western linguists, Chinese linguists generally include Kra–Dai and Hmong-Mien languages within Sino-Tibetan.");
		knowLanguage.add(sinoTibetanAndSouthEastAsianFamily);
		sinoTibetanAndSouthEastAsianFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.BURMESE,
				"Burmese is the Sino-Tibetan language spoken in Myanmar where it is an official language and the language of the Bamar people, the country's principal ethnic group." //
						+ " Although the Constitution of Myanmar officially recognizes the English name of the language as the Myanmar language," //
						+ " most English speakers continue to refer to the language as Burmese, after Burma, the older name for Myanmar." //
						+ " In 2007, it was spoken as a first language by 33 million, primarily the Bamar people and related ethnic groups," //
						+ " and as a second language by 10 million, particularly ethnic minorities in Myanmar and neighboring countries.",
				1));
		sinoTibetanAndSouthEastAsianFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.CANTONESE,
				"Cantonese is spoken by about 72 million people, mainly in the south east of China, particularly in Hong Kong, Macau, Guangdong, Guangxi and Hainan." //
						+ " It is also spoken in Vietnam, Malaysia, Indonesia, Thailand, Singapore, Philippines and among Overseas Chinese communities in many other countries.",
				1));
		sinoTibetanAndSouthEastAsianFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.MANDARIN,
				"Mandarin is a variety of Chinese spoken mainly in China, Taiwan, and Singapore by just over 1 billion people (in 2013)." //
						+ " It is the main language of government, the media and education in China and Taiwan, and one of the four official languages in Singapore." //
						+ " Mandarin is also spoken in Malaysia, Indonesia, Mongolia, Brunei, Thailand, the Philippines, Russia, the USA, Vietnam, Laos, the UK and Mauritius.",
				1));
		sinoTibetanAndSouthEastAsianFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.THAI,
				"Thai is a Tai-Kadai language spoken by about 65 million people mainly in Thailand," //
						+ " and also in the Midway Islands, Singapore, the UAE and the USA,",
				1));
		sinoTibetanAndSouthEastAsianFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.TIBETAN,
				"Tibetan is Tibetic language spoken mainly in the Tibet Autonomous Region of the People's Republic of China," //
						+ " and also in parts of India and Nepal." //
						+ " According to the 1990 census, there are 1.2 million speakers of Standard Tibetan, which is also known as Lhasa Tibetan," //
						+ " and is the Tibetic language with the most speakers.",
				1));
		sinoTibetanAndSouthEastAsianFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.VIETNAMESE,
				"Vietnamese is a member of the Vietic branch of the Austroasiatic language family." //
						+ " It is spoken mainly in Vietnam, and in Guangxi Province in southern China, and in Cambodia and Laos." //
						+ " There are also significant numbers of Vietnamese speakers in France, Australia, and the USA." //
						+ " In 2007 there were about 75 million speakers of Vietnamese.",
				1));
	}

	private void initializeSlavicLanguageFamily(Attribute attribute) {
		slavicFamily = new BroadSkill(CyberpunkSkill.SLAVIC,
				"The Slavic languages (also called Slavonic languages) are the Indo-European languages spoken by the Slavic peoples." //
						+ " They are thought to descend from a proto-language called Proto-Slavic spoken during the Early Middle Ages," //
						+ " which in turn is thought to have descended from the earlier Proto-Balto-Slavic language, linking the Slavic languages to the Baltic languages in a Balto-Slavic group within the Indo-European family.");
		knowLanguage.add(slavicFamily);
		slavicFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.BULGARIAN,
				"Bulgarian is a Southern Slavic language with about 12 million speakers mainly in Bulgaria, but also in Ukraine, Macedonia, Serbia, Turkey, Greece, Romania, Canada, USA, Australia, Germany and Spain." //
						+ " Bulgarian is mutually intelligible with Macedonian, and fairly closely related to Serbian, Croatian, Bosnian and Slovenian.",
				1));
		slavicFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.RUSSIAN,
				"Russian is an Eastern Slavic language spoken mainly in Russia and many other countries by about 260 million people, 150 million of whom are native speakers." //
						+ " Russian is an official language in Russian, Belarus, Kazakhstan and Kyrgyzstan," //
						+ " and in a number of other countries, territories and international organisations, including Tajikistan, Moldova, Gagauzia, Abkhazia, South Ossetia, Transnistria, and the UN." //
						+ " It is also recognised as a minority language in Romania, Finland, Norway, Armenia, Poland, the Czech Republic, Slovakia, Turkmenistan and Uzbekistan.",
				1));
		slavicFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.CZECH,
				"Czech is a Western Slavic language spoken by about 10 million people in the Czech Republic." //
						+ " There are also Czech speakers in Portugal, Poland, Germany and the USA. Czech is closely related to Slovak, Polish and Sorbian.",
				1));
		slavicFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.POLISH,
				"Polish is a Western Slavonic language with about 40 million speakers mainly in Poland." //
						+ " There are also significant Polish communities in Lithuania, Belarus and Ukraine," //
						+ " and significant numbers of Polish speakers in many other countries, including the Czech Republic, Germany, Slovakia, Latvia, Romania, the UK and USA." //
						+ " Polish is closely related to Kashubian, Lower Sorbian, Upper Sorbian, Czech and Slovak.",
				1));
		slavicFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.UKRANIAN,
				"Ukrainian is an Eastern Slavonic language closely related to Belarusian, Polish, and Serbian." //
						+ " It is spoken by about 51 million people mainly in Ukraine, and also in many other countries," //
						+ " including Argentina, Armenia, Azerbaijan, Belarus, Brazil, Canada, Estonia, Georgia, Hungary, Kazakhstan, Kyrgyzstan, Latvia, Lithuania, Moldova, Paraguay, Poland, Romania, Russia and Slovakia.",
				1));
		slavicFamily.add(new SpecializedSkill(attribute, CyberpunkSkill.SLOVAK,
				"Slovak is a Western Slavonic language spoken by about 5.6 million people in Slovakia and also in Canada, Hungary, Poland, Romania, Ukraine and the USA." //
						+ " Slovak is closely related to Czech, Polish, and Sorbian.",
				1));
	}

	private void initializeReflexSkills() {
		Attribute attribute = reflexes;
		reflexSkills = new BroadSkill(CyberpunkSkill.REFLEX_SKILLS, "");
		allSkills.add(reflexSkills);
		reflexSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.ARCHERY,
				"The skill required to use bows, crossbows and other arrow-based ranged weapons." //
						+ " See Handgun for details.",
				1));
		reflexSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.ATHLETICS,
				"This Skill is required for accurate throwing, climbing, and balancing." //
						+ " It combines the basic elements of any high school level sports program." //
						+ " At +3 and above, you are the equivalent of a real high school \"jock\"." //
						+ " At +5 and above, you can perform in college level competitions." //
						+ " At +8 and above, you are of Olympic or Professional caliber.",
				1));
		reflexSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.BRAWLING,
				"The skill of fighting man to man with fist, feet and other parts of the body." //
						+ " Brawling is not a trained skill--it is learned on the Street by getting into a lot of fights." //
						+ " Unlike Martial Arts, there are no specialized attacks an no damage bonuses based on level of mastery.",
				1));
		reflexSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.DANCE,
				"The specific skill needed to become a professional dancer." //
						+ " A trained dancer +4 or greater can successfully dance for payment in small clubs or dance troupes." //
						+ " Dancers +6 or greater will be considered to be of professional caliber, and regularly give performances and have fans." //
						+ " Dancers +9 or greater are of \"star\" caliber, have a large number of fans, and may be recognized on the street.",
				1));
		reflexSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.DODGE_AND_ESCAPE,
				"This skill is required to dodge attacks and escape grapples and holds." //
						+ " If an attack is made without your knowledge, you may not apply this skill to your Defense roll.",
				1));
		reflexSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.DRIVING,
				"This skill allows you to pilot all ground vehicles like cars, trucks, tanks and hovercraft." //
						+ " This skill is not useable for piloting aircraft. A skill of +3 is equal to that of a very good non-professional driver." //
						+ " A skill of +6 allows you to drive with the skill of a moderately skilled race driver." //
						+ " A driver with a skill of +8 or greater will be nationally known as a racer, regularly win championship races, and possibly have access to the most adbanced ground vehicles available (as long as he makes an endorsement).",
				1));
		reflexSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.FENCING,
				"The mastery of swords, rapiers and monoblades." //
						+ " A Fencing Skill of +3 allows you to be competent with a blade." //
						+ " A Skill of +5 makes you fairly skilled." //
						+ " A Fencing Skill of +6 might win you the National Fencing Competitions." //
						+ " A Skill of +8 will get you a reputation for being a true swordsman of duelist caliber." //
						+ " People like D'Artagnan or Miyamoto Musashi have Skills of +10." //
						+ " They are legendary masters of the blade; the mention of whom will cause all but the stupidest young bravo to run for cover.",
				1));
		reflexSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.HANDGUN,
				"You must have this skill to effectively use handguns of any type, including cyberware types." //
						+ " At +2, you ca use a handgun on a target range, through combat will still rattle you." //
						+ " At +5, you are as skilled as most military officers or policemen." //
						+ " At +7, you can do the sort of fancy shooting you see on TV, and have begun to get a reputation of being \"good with gun\"." //
						+ " At +8, you are a recognized gunslinger with a \"rep\"." //
						+ " The very sound of your name makes some people back down in fear." //
						+ " At +10, you are a legendary gunslinger, feared by all except the stupid young punks who keep trying to \"take\" you in innumerable gunfight challenges.",
				1));
		reflexSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.HEAVY_WEAPONS,
				"The required skill for using grenade launchers, autocannon, mortars, heavy machine guns, missiles and rocket launchers." //
						+ " A level +5 skill would be equivalent to a general military \"Heavy Weapons\" training course, giving the user the ability to use any or all of these weapon types.",
				1));
		initializeMartialArtSkills(attribute);
		reflexSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.MELEE,
				"The ability to use knives, axes, clubs and other hand to hand weapons in combat." //
						+ " Note: when using non-ranged cyberweapons such as rippers, scratchers, slice n'dices, cyberbeasts, and battlegloves, you must use this skill.",
				1));
		reflexSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.MOTORCYCLE,
				"The required skill to operate motorcycles, cyberbikes and other two and three-wheeled vehicles.", 1));
		reflexSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.OPERATE_HEAVY_MACHINERY,
				"The required skill to operate tractors, tanks, very large trucks and construction equipment.", 1));
		initializePilotingSkills(attribute);
		reflexSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.RIFLE,
				"You must have this skill to use riffle/shotguns effectively (see Handgun limitations and modifiers).",
				1));
		reflexSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.STEALTH,
				"The skill of hiding in the shadows, moving silently, evading guards, etc." //
						+ " A Stealth Skill of +1 is about the level of a very sneaky 10 year old stealing cookies." //
						+ " At +3, you are able to get past most guards, or your parents if you've been grounded." //
						+ " At +6, you are good enough to slip smoothly from shadow to shadow and not make any noise." //
						+ " At +8 you are the equal of most Ninja warriors." //
						+ " At +10, you move as silently as a shadow, making the Ninja sound like elephants.",
				2));
		reflexSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.SUBMACHINEGUN,
				"You must have this skill to use any type of submachine gun effectively (see Handgun for limitations and modifiers).",
				1));
	}

	private void initializeMartialArtSkills(Attribute attribute) {
		martialArt = new BroadSkill(CyberpunkSkill.MARTIAL_ART,
				"This skill covers any type of trained fighting style using hands, feet, or specialized \"martial arts\" weapons." //
						+ " You must elect a style of marial art and take a seperate skill for each style" //
						+ " (for example, you would have to take Karate and Judo seperately, spending points for each one.");
		reflexSkills.add(martialArt);
		martialArt.add(new SpecializedSkill(attribute, CyberpunkSkill.AIKIDO,
				"This form relies on using the opponent's strength and momentum against him." //
						+ " It is a perfect form for stopping an opponent peacefully while making yourself very hard to hit." //
						+ " Key attacks are: blocks & parries, dodges, throws, holds, escapes, chokes, sweeps, trips & sweeps, grapples.",
				3));
		martialArt.add(new SpecializedSkill(attribute, CyberpunkSkill.ANIMAL_KUNG_FU,
				"There are forms based on animal movements, such as crane, mantis, tiger, leopard and dragon forms." //
						+ " These attacks are fast and dangerous, with a style that is exciting and flashy." //
						+ " Key attacks include: strikes, punches, kicks, blocks & parries, sweeps & trips.",
				3));
		martialArt.add(new SpecializedSkill(attribute, CyberpunkSkill.BOXING,
				"The manly art of fisticuffs, this form delivers lightning punches and tight blocking defense." //
						+ " Key attacks are: punches, blocks & parries.",
				1));
		martialArt.add(new SpecializedSkill(attribute, CyberpunkSkill.CAPOERIA,
				"Created by Caribbean slaves, this form combines dancelike movements with fast kicks and low line sweeps." //
						+ " It is a relatively unknown form and can be combined with dance moves to disguise it's true power." //
						+ " Key attacks are: punches, kicks, blocks & parries, dodges, and sweeps & trips.",
				3));
		martialArt.add(new SpecializedSkill(attribute, CyberpunkSkill.CHOI_LI_FUT,
				"Descended directly form the ancient Shaolin temples, this form combines powerful roundhouse blows and sweeping kicks into dynamic fighting style." //
						+ " Key attacks are: strikes, punches, kicks, blocks & parries, dodges, throws, and sweeps & trips.",
				3));
		martialArt.add(new SpecializedSkill(attribute, CyberpunkSkill.JUDO,
				"This system was designed as a sport form, but is very effective in combat as well." //
						+ " It uses throws and sweeps to knock down the opponent." //
						+ " Key attacks include dodges, throws, holds, escape sweeps & trips and grappling.",
				1));
		martialArt.add(new SpecializedSkill(attribute, CyberpunkSkill.KARATE,
				"The Japanese version of kung fu, this style uses straight line movements and powerful blows." //
						+ " Variations include shotokan and kenpo, each with their own special moves." //
						+ " Key attacks are: punches, kicks, and blocks & parries.",
				2));
		martialArt.add(new SpecializedSkill(attribute, CyberpunkSkill.TAE_KWON_DO,
				"A very fast and precise form, with graceful movements and some aerial kicks." //
						+ " Key attacks include: strikes, punches, kicks, blocks & parries, dodges.",
				3));
		martialArt.add(new SpecializedSkill(attribute, CyberpunkSkill.THAI_KICK_BOXING,
				"One of the deadliest form in existence, this style is known for blinding kicks delivered with incredible power." //
						+ " Key moves include: strikes, punches, kicks, blocks & parries.",
				4));
		martialArt.add(new SpecializedSkill(attribute, CyberpunkSkill.WRESTLING,
				"This form combines techniques of Olympic and Professional wrestling." //
						+ " The style uses a wide variety of throws and holds to incapacitate the opponent." //
						+ " Key attacks include: throws, holds, escapes, chokes, sweeps, trips, and grapple.",
				1));
	}

	private void initializePilotingSkills(Attribute attribute) {
		piloting = new BroadSkill(CyberpunkSkill.PILOTING, "In general, this is the skill of controlling aircraft." //
				+ " Aircraft are broken into categories: Gyro and Rotorcraft, Fixed Wing Aircraft, Dirigibles and Vectored Thrust Aerodynes (AVs)." //
				+ " A Piloting skill of +1 allows you to take off and land safely in good weather conditions." //
				+ " A Piloting Skill of +3 or more makes you a trained pilot, able to engage in most combat situations or bad weather." //
				+ " Pilots with a Skill of +6 or greater are veteran pilots able to handle themselves in almost any situation, including aerobatic manuevers." //
				+ " Pilots with a Skill of +9 or greater are so good, they have a rep as pilots, and are widely known among the piloting fraternity for having the \"right stuff\".");
		reflexSkills.add(piloting);
		piloting.add(new SpecializedSkill(attribute, CyberpunkSkill.PILOT_GYRO,
				"The ability to pilot all types of rotorwing aircraft, including gyros, copters and Osperys.", 3));
		piloting.add(new SpecializedSkill(attribute, CyberpunkSkill.PILOT_FIXED_WING,
				"The ability to pilot fixed wing jets and light aircraft. Ospreys may be flown with this skill, but only in the straight ahead (non-hover) mode.",
				2));
		piloting.add(new SpecializedSkill(attribute, CyberpunkSkill.PILOT_DIRIGIBLE,
				"The ability to pilot all lighter than air vehicles, including cargo dirigibles, blimps and powered balloons",
				2));
		piloting.add(new SpecializedSkill(attribute, CyberpunkSkill.PILOT_VECTORED_THRUST_VEHICLE,
				"The skill of piloting all types of vectored thrust vehicles, including hovercars, hovercrafts and AV-4, 6 and 7 vehicles.",
				3));
	}

	private void initializeTechnicalSkills() {
		Attribute attribute = technicalAbility;
		technicalSkills = new BroadSkill(CyberpunkSkill.TECHNICAL_ABILITY_SKILLS, "");
		allSkills.add(technicalSkills);
		technicalSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.AERO_TECH,
				"The required skill for repairing fixed wing aircraft, including Ospreys, jets, and light aircraft." //
						+ " With a Skill of +3, you can perform most routine maintenance tasks." //
						+ " With a Skill of +6, you can do engine teaerdowns and major structural repairs." //
						+ " With a Skill of +9 or better you are capable of designing and building your own.",
				2));
		technicalSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.AV_TECH,
				"The required skill for repairing all ducted fan aerodyne vehicles." //
						+ " At +3, you can perform routine maintenance." //
						+ " At +6, you can tear down engines and modify an AV." //
						+ " At +10, you can design your own AVs on common airframe.",
				3));
		technicalSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.BASIC_TECH,
				"The required skills for building or repairing simple mechanical and electrical devices, such as car engines, television sets, etc." //
						+ " With a Basic Tech Skill of +3 or better, you can fix minor car problems, repair basic wiring, etc." //
						+ " A Basic Tech Skill of +9 or better can build a simple computer from scratch, put together a race car engine and maintain any kind of industrial machinery." //
						+ " However, they do not know enough specialized knowledge to apply it to complex things such as aircraft (just like Mr. Goodwrench doesn't know how to build and service an F-16).",
				2));
		technicalSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.CRYOTANK_OPERATION,
				"The required skill for operating, repairing and maintaining life suspension and body chilling devices." //
						+ " A minimum skill of +4 is required to chill down a healthy peron." //
						+ " A minimum skill of +6 for chilling a wounded person.",
				1));
		technicalSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.CYBERDECK_DESIGN,
				"The required skill for designing cyberdecks." //
						+ " At level +4, you can modify an existing cyberdeck for greater speed or memory." //
						+ " At level +6, you can design a deck equal to most existing designs." //
						+ " At +8, you can design decks that are substantially improved over existing designs.",
				2));
		technicalSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.CYBERTECH,
				"The required skill for repairing and maintaining cyberwear." //
						+ " At level +2, you can keep your cyberwear tuned up and can replace its power batteries." //
						+ " At level +6, you can strip down most cyberwear and even make simple modifications." //
						+ " At level +8, you can design your own cyberwear to order.",
				2));
		technicalSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.DEMOLITIONS,
				"This skill allows the character to be knowledgeable in the use of explosives, as well as knowing the best explosives to use for which jobs, how to set timers and detonators, and how much explosive to use to accomplish a desired result.",
				2));
		technicalSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.DISGUISE,
				"The skill of disguising your character to resemble someone else, whether real or fictitious." //
						+ " This skill incorporates elements of both makeup and acting, although it is not the same as the ability to actually be an actor.",
				1));
		technicalSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.ELECTRONICS,
				"The required skill for maintaining, repairing and modifying electronic security systems, cameras and monitors.",
				1));
		technicalSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.ELECTRONIC_SECURITY,
				"The skill of installing or countering electronic eyes, electronic locks, bugs and tracers, security cameras, pressure plates, etc." //
						+ " At level +3 you can jimmy or install most apartment locks and security cams." //
						+ " At +6, you can override most corporate office locks and traps." //
						+ " At +9, you can enter most high security areas with impunity.",
				2));
		technicalSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.FIRST_AID,
				"This skill allows the user to bind wounds, stop bleeding, and revive a stunned patient." //
						+ " When a character makes a successful First Aid skill check, the patient will recover at the rate of 0.5 points per day." //
						+ " Only one check need be made." //
						+ " You may (within reason and at Referee's discretion), perform first aid on yourself." //
						+ " On an unsuccessful roll, the patient regains no points." //
						+ " New attempts may be made once per day until a successful roll is made.",
				1));
		technicalSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.FORGERY,
				"The skill of copying and creating false documents and identifications."
						+ " This skill may also be applied to the detection of same; if you can fake it, you can usually tell a fake as well.",
				1));
		technicalSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.GYRO_TECH,
				"The skill of repairing and maintaining rotorwing aircraft such as helicopters and gyrocopters.", 3));
		technicalSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.PAINT_OR_DRAW,
				"The skill of producing professional drawings." //
						+ " A Skill of +3 allows you to produce salable \"modern\" art." //
						+ " A Skill of +6 will produce artwork that is recognizable and extremely pleasant to the eye--as well as salable." //
						+ " An artist with a Skill of +8 or greater will be nationally known, have exhibits in galleries, and have other lesser artists studying his style in art school.",
				1));
		technicalSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.PHOTO_AND_FILM,
				"The skill of producing professional-caliber photographs or motion pictures." //
						+ " A Skill of +2 allows you to make decent home movies." //
						+ " A Skill of +4 or better creates work capable of winning amateur contests." //
						+ " A Skill of +6 or better will produce work of the level of the average Playboy cover or rock video." //
						+ " A photographer or cinematographer with a Skill of +8 or better will be nationally known and probably famous.",
				1));
		// TODO Requires a prerequisite skill... Plan for that.
		technicalSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.PHARMACEUTICALS,
				"The skill of designing and manufacturing drugs and medicines." //
						+ " A minimum Chemistry skill of +4 is required." //
						+ " At +4 you can make asprin. At +6, you can make hallucinogenics or antibiotics." //
						+ " At level +9 you can build designer drugs tailored to individual body chemistries.",
				2));
		technicalSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.PICK_LOCK,
				"The skill required to pick locks and break into sealed containers and rooms." //
						+ " At +3, you can jimmy most simple locks." //
						+ " At +6 you can crack most safes." //
						+ " At +9 or better you have a rep as a master cracksman, and are known to all the major players in the Cyberpunk world.",
				1));
		technicalSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.PICK_POCKET,
				"The required skill for picking pockets without being noticed, as well as \"shoplifting\" small items." //
						+ " For ideas on levels of ability, see Pick Lock.",
				1));
		technicalSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.PLAY_INSTRUMENT,
				"The skill of knowing how to play a musical instrument." //
						+ " You must take this skill seperately for each type of instrument played." //
						+ " A Skill of +4 or higher will qualify your character to play professional \"gigs\"." //
						+ " A skill of +8 and above will gain the musician some professional acclaim, possibly with recording contracts and command performances." //
						+ " At +10, you are widely acclaimed, have lots of Grammys, and regularly jam with Kerry Eurodyne",
				1));
		technicalSkills.add(new SpecializedSkill(attribute, CyberpunkSkill.WEAPONSMITH,
				"The required skill for repairing an maintaining weapons of all types." //
						+ " At level +2, you can do repairs and field stripping." //
						+ " At level +6, you can repair all types of weapons and make simple modifications." //
						+ " At level +8, you can design your own weapons to order.",
				2));
	}

	public String getDescription(String skillName) {
		CyberpunkSkill skill = allSkills.getChild(skillName);
		return skill.getDescription();
	}

	public int getTotalValue(String skillName) {
		CyberpunkSkill skill = allSkills.getChild(skillName);
		return skill.getTotalValue();
	}

	public void increaseSkill(String skillName) {
		CyberpunkSkill skill = allSkills.getChild(skillName);
		skill.increaseLevel();
	}
}
