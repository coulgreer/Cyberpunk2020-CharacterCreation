package rpg.cyberpunk._2020.stats;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import rpg.cyberpunk._2020.Player;
import rpg.general.stats.Attribute;

/**
 * A factory object used to create a set of Skills and Attributes from Cyberpunk 2020.
 */
public class StatisticFactory {
  public static final String INDEPENDENT_ATTRIBUTE = "Independent";
  public static final int INDEPENDENT_ATTRIBUTE_COUNT = 9;
  public static final String DEPENDENT_ATTRIBUTE = "Dependent";

  private StatisticFactory() {
    // Do Nothing
  }

  /**
   * Creates a Map that contains two other Maps of Attributes. One Map is of dependent Attributes
   * and the other is of independent Attributes.
   * 
   * @return all Attributes in the game Cyberpunk 2020
   */
  public static Map<String, Map<String, Attribute>> createAttributesByNameByType() {
    Map<String, Map<String, Attribute>> attributesByNameByType =
        new HashMap<String, Map<String, Attribute>>();
    attributesByNameByType.put(INDEPENDENT_ATTRIBUTE, new LinkedHashMap<String, Attribute>());
    attributesByNameByType.put(DEPENDENT_ATTRIBUTE, new LinkedHashMap<String, Attribute>());

    addIndependentAttributesTo(attributesByNameByType);
    addDependentAttributesTo(attributesByNameByType);

    return attributesByNameByType;
  }

  private static void addIndependentAttributesTo(
      Map<String, Map<String, Attribute>> attributesByNameByType) {

    Map<String, Attribute> attributesByName = attributesByNameByType.get(INDEPENDENT_ATTRIBUTE);

    CyberpunkAttribute attribute = new CyberpunkAttribute( //
        CyberpunkAttribute.INTELLIGENCE,
        "This is a measure of your problem solving ability; figuring out problems, noticing"
            + " things, remembering information. Almost every character type will need a high"
            + " Intelligence, with Netrunners and Corporates requiring the highest of all.");
    attributesByName.put(attribute.getName(), attribute);

    attribute = new CyberpunkAttribute( //
        CyberpunkAttribute.REFLEXES,
        "This is a combined index, covering not only your basic dexterity, but also how your level"
            + " of physical coordination will affect feats of driving, piloting, fighting, and"
            + " athletics. Characters who intend to engage in a great deal of combat (such as"
            + " Solos, Nomads, or Rockerboys) should always invest in the highest possible"
            + " Reflex.");
    attributesByName.put(attribute.getName(), attribute);

    attribute = new CyberpunkAttribute( //
        CyberpunkAttribute.COOL,
        "This index measures how well the character stands up to stress, fear, pressure, physical"
            + " pain and/or torture. In determining your willingness to fight on despite wounds or"
            + " your fighting ability under fire, Cool is essential. It is also the measure of how"
            + " \"together\" your character is and how tough he appears to others. Rockerboys and"
            + " Fixers should always have a high Cool, with Solos and Nomads having the highest of"
            + " all.");
    attributesByName.put(attribute.getName(), attribute);

    attribute = new CyberpunkAttribute( //
        CyberpunkAttribute.TECHNICAL_ABILITY,
        "This is an index of how well you relate to hardware and other technically oriented"
            + " things. In Cyberpunk, the abililty to use and repair technology is of paramount"
            + " importance--TECH will be the Stat used when fixing, repairing and attempting to"
            + " use unfamiliar tech. While all characters should have a decent Tech Stat,"
            + " potential Techies should always opt for the highest possible score in this area.");
    attributesByName.put(attribute.getName(), attribute);

    attribute = new CyberpunkAttribute( //
        CyberpunkAttribute.LUCK,
        "This is the intangible \"something\" that throws the balance of events into your favor."
            + " Your luck represents how many points you may use each game to influence the outcome"
            + " of a critical event. To use Luck, you may add any or all of the points of luck a"
            + " character has to a critical die roll (declaring your intention to use Luck before"
            + " the roll is made) until all of your Luck stat is used up. Luck is always restored"
            + " at the end of each game session.");
    attributesByName.put(attribute.getName(), attribute);

    attribute = new CyberpunkAttribute( //
        CyberpunkAttribute.ATTRACTIVENESS,
        "This is how good-looking you are. In Cyberpunk, it's not enough to be good--you have to"
            + " look good while you're doing it (Attitude is Everything). Attractiveness is"
            + " especially important to Medias and Rockerboys, as being good-looking is part of"
            + " their jobs.");
    attributesByName.put(attribute.getName(), attribute);

    attribute = new CyberpunkAttribute( //
        CyberpunkAttribute.MOVEMENT_ALLOWANCE,
        "This is an index of how fast your character can run (important in combat situations). The"
            + " higher your Movement Allowance, the more distance you can cover in a turn.");
    attributesByName.put(attribute.getName(), attribute);

    attribute = new CyberpunkAttribute( //
        CyberpunkAttribute.EMPATHY,
        "This Stat represents how well you relate to other living things--a measure of charisma"
            + " and sympathetic emotions. In a world of alienated, future-shocked survivors, the"
            + " ability to be \"human\" can no longer be taken for granted. Empathy is critical"
            + " when leading, convincing, seducing or perceiving emotional undercurrents. Empathy"
            + " is also a measure of how close he/she is to the line between feeling human being"
            + " and cold blooded cyber-monster.");
    attributesByName.put(attribute.getName(), attribute);

    attribute = new CyberpunkAttribute( //
        CyberpunkAttribute.BODY_TYPE,
        "Strength, Endurance and Constitution are all based on the character's Body Type. Body"
            + " Type determines how much damage you can take in wounds, how much you can lift or"
            + " carry, how far you can throw, how well you recover from shock, and how much"
            + " additional damage you cause with physical attacks. Body Type is important to all"
            + " character types, but to Solos, Rockerboys and Nomads most of all.");
    attributesByName.put(attribute.getName(), attribute);
  }

  private static void addDependentAttributesTo(
      Map<String, Map<String, Attribute>> attributesByNameByType) {

    Map<String, Attribute> independentAttributesByName =
        attributesByNameByType.get(INDEPENDENT_ATTRIBUTE);
    Map<String, Attribute> dependentAttributesByName =
        attributesByNameByType.get(DEPENDENT_ATTRIBUTE);

    Attribute attribute = new RunAttribute( //
        CyberpunkAttribute.RUN,
        "To determine how far your character can run in a single combat round (@3.2 seconds) in"
            + " meters, multiply your MA by 3. The character can run three times this distance in"
            + " a full 10 second turn.",
        independentAttributesByName.get(CyberpunkAttribute.MOVEMENT_ALLOWANCE));
    dependentAttributesByName.put(attribute.getName(), attribute);

    attribute = new LeapAttribute( //
        CyberpunkAttribute.LEAP,
        "To determine how far your character can leap (from a running start), divide your RUN by"
            + " 4.",
        independentAttributesByName.get(CyberpunkAttribute.MOVEMENT_ALLOWANCE));
    dependentAttributesByName.put(attribute.getName(), attribute);

    attribute = new CarryAttribute( //
        CyberpunkAttribute.CARRY, //
        "The amount of weight you can carry on your back.",
        independentAttributesByName.get(CyberpunkAttribute.BODY_TYPE));
    dependentAttributesByName.put(attribute.getName(), attribute);
  }

  /**
   * Creates a Map that contains other Maps of CyberpunkSkill. Each child Map represents the
   * categories of CyberpunkSkills grouped by shared Attribute or restrictions.
   * 
   * @param attributesByName the attributes used by CyberpunkSkills in order to get the skill's
   *        total value
   * @param player the provider of the role needed to enable some CyberpunkSkills
   * @return all CyberpunkSkills in the game CyberpunkSkill 2020
   */
  public static Map<String, Map<String, CyberpunkSkill>> createSkillByNameByCategoryName(
      Map<String, Attribute> attributesByName, //
      Player player) {

    Map<String, Map<String, CyberpunkSkill>> skillsByNameByCategoryName =
        new LinkedHashMap<String, Map<String, CyberpunkSkill>>();

    /*
     * The order of when the skills are created is relevant. Because of the dependency of some
     * skills on others, the following methods should be called BEFORE createDependantSkills and
     * createRoleSkills: createAttractivenessSkills, createBodySkills, createCoolSkills,
     * createIntelligenceSkills, createReflexSkills, createTechnicalSkills
     */
    createRoleSkills(attributesByName, skillsByNameByCategoryName, player);
    createAttractivenessSkills(attributesByName, skillsByNameByCategoryName);
    createBodyTypeSkills(attributesByName, skillsByNameByCategoryName);
    createCoolSkills(attributesByName, skillsByNameByCategoryName);
    createEmpathySkills(attributesByName, skillsByNameByCategoryName);
    createIntelligenceSkills(attributesByName, skillsByNameByCategoryName);
    createReflexSkills(attributesByName, skillsByNameByCategoryName);
    createTechnicalSkills(attributesByName, skillsByNameByCategoryName);
    createDependantSkills(attributesByName, skillsByNameByCategoryName);

    return skillsByNameByCategoryName;
  }

  private static void createRoleSkills( //
      Map<String, Attribute> attributesByName,
      Map<String, Map<String, CyberpunkSkill>> skillsByNameByCategoryName, //
      Player player) {

    Map<String, CyberpunkSkill> skillsByName = new TreeMap<String, CyberpunkSkill>();
    String categoryName = "Role Skills";

    CyberpunkSkill skill = new RoleSkill( //
        attributesByName.get(CyberpunkAttribute.COOL), //
        CyberpunkSkill.AUTHORITY,
        "The ability to intimidate or control others through your position as a lawman. This"
            + " attribute represents the Cop's ability to call on the forces of the Law and"
            + " Government to get what he wants. Cops can use Authority to question suspects,"
            + " arrest wrongdoers, and  defend innocents. Backed by the power of Authority, a cop"
            + " can arrest, detain, confiscate and enter nearly anywhere, as long as he has the"
            + " proper arrest or search warrants to back his play. However, quthority is only as"
            + " good as the guy holding the badge--if the cop appears uncertain of his Authority,"
            + " there's a good chance he'll get nailed by the peoiple he's trying to confront. The"
            + " higher your Authority, the more able you are to face down criminals, particularly"
            + " high level mobsters and officials. Authority is applied to your Cool stat.",
        player);
    skillsByName.put(skill.getName(), skill);

    skill = new RoleSkill( //
        attributesByName.get(CyberpunkAttribute.COOL), //
        CyberpunkSkill.CHARISMATIC_LEADERSHIP,
        "This skill allows the Rocker to sway crowds equal to his level squared time 200. This"
            + " ability (added to your Cool stat) allows the Rockerboy to control, incite and"
            + " charm large number of people through his or her performance skills. When under the"
            + " Rocker's control, this group can easily be persuaded to act on his suggestion. For"
            + " example, a Rocker could convince a concert crowd to riot in the streets or attack"
            + " a heavily fortified police line. Charismatic Leadership will only work with groups"
            + " of ten or more people as it is primarily a mob leadership ability. The higher your"
            + " Charismatic Leadership, the larger a crowd you can control and the more direct and"
            + " complex the instructions you can get them to follow. For example, a Level +3"
            + " Leadership could incite a nightclub crowd to get rowdy. A Level +5 or +6 could"
            + " provoke a concert crowd of thousands to trash a neighborhood, if the area wasn't"
            + " too far from hall. At Level +9, and higher, you have the same sort of mesmeric"
            + " ability as an Adolph Hitler - you can raise armies, start movements. And destroy"
            + " nations.",
        player);
    skillsByName.put(skill.getName(), skill);

    skill = new RoleSkill( //
        CyberpunkSkill.COMBAT_SENSE, //
        "This ability is based on Solo's constant training and professionalism. Combat Sense"
            + " allows the Solo to perceive danger, notice traps, and have an almost unearthly"
            + " ability to avoid harm. Your Combat Sense gives you a bonus on both your Awareness"
            + " skill and your Initiative equal to your level in the Combat Sense skill.",
        player);
    skillsByName.put(skill.getName(), skill);

    skill = new RoleSkill( //
        attributesByName.get(CyberpunkAttribute.INTELLIGENCE), //
        CyberpunkSkill.CREDIBILITY,
        "This is the ability to be believed: by your viewers, by the police, by important and"
            + " powerful people. This is critical to getting your story heard and acted upon, as well"
            + " as convincing people to tell you things, give you information, or get you into where"
            + " the story is really happening. The higher your Credibility, the more people you can"
            + " convince, and the easier it is to convince high level authorities of the truth of your"
            + " information. With a level +3 Credibility, you can convince most people of minor"
            + " scandals. With a level +5 or +6, you can convince local officials of military"
            + " atrocities, undercover dealings and other front page stuff. At level +9, you can"
            + " successfully expose a scandal of Watergate proportions, or convince the President of"
            + " the EuroMarket Finance Board that aliens are secretly influencing world leaders."
            + " Credibility applies to your INT stat.",
        player);
    skillsByName.put(skill.getName(), skill);

    skill = new RoleSkill( //
        attributesByName.get(CyberpunkAttribute.INTELLIGENCE), //
        CyberpunkSkill.FAMILY,
        "This is the ability to call upon the resources and help of any of the members of the"
            + " Nomad's large, extended tribal family. This can be in the form of weapons, cash,"
            + " information, or a small army of relatives. The threat of a Nomad family's"
            + " vengeance may in itself stop harm to the Nomad. The higher your Family ability,"
            + " the more important you are to the Family and the more help you can call upon. With"
            + " a Family status of +2, you might  be able to get several of the Pack to help you"
            + " wreck a town, for example. With a status of +7 or +8, you are able to make major"
            + " Pack decisions and lead troops. At +10, you may be the Leader of your Pack. Family"
            + " is applied to your INT stat.",
        player);
    skillsByName.put(skill.getName(), skill);

    skill = new RoleSkill( //
        attributesByName.get(CyberpunkAttribute.INTELLIGENCE), //
        CyberpunkSkill.INTERFACE,
        "This skill reflects the Netrunner's ability to manipulate Interface programs, and is the"
            + " Skill used when operating Menu functions such as Locate Remote, Run Software,"
            + " Control Remote, Downlink, Load, Create and Delete. Other player can enter the Net,"
            + " but cannot use the Menu. Interface is based on the INT Stat. Note for Cyberpunk 1"
            + " players--you may elect to swap your original INT and REF stats for characters"
            + " generated with the old rule.",
        player);
    skillsByName.put(skill.getName(), skill);

    skill = new RoleSkill( //
        CyberpunkSkill.JURY_RIG,
        "This general repair skill allows the Techie to temporarily repair or alter anything for"
            + " 1D6 turns per level of skill. This is not a permanent repair; after the elapsed"
            + " time, the jury rig will break down.",
        player);
    skillsByName.put(skill.getName(), skill);

    skill = new RoleSkill( //
        CyberpunkSkill.MEDICAL_TECH,
        "This skill assumes that the character has studied medicine in a professional setting."
            + " This gives him the ability to perform surgery, prescribe drugs, and know the"
            + " proper treatment of injuries. They can replace damaged organs with vatgrown"
            + " pieces, graft on new limbs, or install cyberlimbs. You cannot perform Medical Tech"
            + " skills on yourself.",
        player);
    skillsByName.put(skill.getName(), skill);

    skill = new RoleSkill( //
        attributesByName.get(CyberpunkAttribute.INTELLIGENCE), //
        CyberpunkSkill.RESOURCES,
        "This represents the Corporate's ability to command corporation resources. It is used as a"
            + " persuasion skill, based on the scale of resources requested. This could include"
            + " bodyguards, weapons, vehicles, buildings, money, etc. Obviously, the more powerful"
            + " the Corporate, the more he can call upon at any one time. Your level of Resources"
            + " determines exactly how much you can request from the Corporation without"
            + " overreaching yourself. A Resource ability of +2 might get you access to a Company"
            + " car. An ability of +6 might allow you to use a Company jet or hire a Solo team"
            + " from the Corporate Security Division. A Resource of +9 would allow you access to"
            + " almost all levels of the Corporation, as well as the ability to requisition almost"
            + " any Company resource. Your Resource ability is applied to your INT stat.",
        player);
    skillsByName.put(skill.getName(), skill);

    skill = new RoleSkill( //
        attributesByName.get(CyberpunkAttribute.COOL), //
        CyberpunkSkill.STREETDEAL,
        "This is the ability to deal with the underground information network. With Streetdeal, a"
            + " Fixer can uncover rumors and information, locate missing persons or things, put"
            + " gossip out on the Street, pick up clues and score big deals. The higher your"
            + " Streetdeal ability, the more information you can gather about things happening"
            + " around you, the more informants you have, and the more secretive the information"
            + " you can dig up. A level +3 Streetdeal can get you contacts for weapons, tools, or"
            + " minor illegal operations. At level +5, you can penetrate the secrets of all but"
            + " the most powerful crime families. At level +9, you are the equivalent of a Mafia"
            + " crimelord yourself, privy to every secret that's on the Street. Apply Streetdeal"
            + " to your Cool stat.",
        player);
    skillsByName.put(skill.getName(), skill);

    skillsByNameByCategoryName.put(categoryName, skillsByName);
  }

  private static void createAttractivenessSkills( //
      Map<String, Attribute> attributesByName,
      Map<String, Map<String, CyberpunkSkill>> skillsByNameByCategoryName) {

    Map<String, CyberpunkSkill> skillsByName = new TreeMap<String, CyberpunkSkill>();
    String attributeName = CyberpunkAttribute.ATTRACTIVENESS;
    Attribute attribute = attributesByName.get(attributeName);

    CyberpunkSkill skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.PERSONAL_GROOMING, //
        "This is the skill of knowing proper grooming, hair styling, etc., to maximize your"
            + " physical attractiveness. Use of this skill allows players to increase their"
            + " Attractiveness, and thus their chances of successful Relationships or Persuasions."
            + " A basically good looking person would be at +2. A fashion model might have a"
            + " Personal Grooming of +5 or +6. At +8 or better, you could be a major fashion"
            + " model, film star, or trendsetter. You are always \"together\". And you know it.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.WARDROBE_AND_STYLE, //
        "The skill of knowing the right clothes to wear, when to wear them, and how to look cool"
            + " even in a spacesuit. With a Wardrobe of +2 or better, you are good at choosing"
            + " clothes off the rack. At +6, your friends ask you for wardrobe tips, and you never"
            + " buy anything off the rack. At +8 or better, you are one of those rare people whose"
            + " personal style influences major fashion trends.",
        1);
    skillsByName.put(skill.getName(), skill);

    skillsByNameByCategoryName.put(attributeName, skillsByName);
  }

  private static void createBodyTypeSkills( //
      Map<String, Attribute> attributeByName,
      Map<String, Map<String, CyberpunkSkill>> skillsByNameByCategoryName) {

    Map<String, CyberpunkSkill> skillsByName = new TreeMap<String, CyberpunkSkill>();
    String attributeName = CyberpunkAttribute.BODY_TYPE;
    Attribute attribute = attributeByName.get(attributeName);

    CyberpunkSkill skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.ENDURANCE, //
        "This is the ability to withstand pain or hardship, particularly over long periods of"
            + " time, by knowing the best ways to conserve strength and energy. Endurance Skill"
            + " checks would be made whenever a character must continue to be active after a long"
            + " period without food, sleep or water.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.STRENGTH_FEAT, //
        "The user of this skill has practived the art of bending bars, crushing objects, ripping"
            + " phone books apart and other useful parlor tricks. At +2 you can crush cans, rip"
            + " thin books in half, and bend thin rods. At +8, no phonebook is safe, you can bend"
            + " thin rebar, and snap handcuffs. At +10, you can bend prison bars, rip up the"
            + " Gutenberg Bible, and dent car fenders with one blow.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.SWIMMING,
        "This skill is required to know how to swim (see Athletics for details).", //
        1);
    skillsByName.put(skill.getName(), skill);

    skillsByNameByCategoryName.put(attributeName, skillsByName);
  }

  private static void createCoolSkills( //
      Map<String, Attribute> attributeByName,
      Map<String, Map<String, CyberpunkSkill>> skillsByNameByCategoryName) {

    Map<String, CyberpunkSkill> skillsByName = new TreeMap<String, CyberpunkSkill>();
    String attributeName = CyberpunkAttribute.COOL;
    Attribute attribute = attributeByName.get(attributeName);

    CyberpunkSkill skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.INTERROGATION,
        "The skill of drawing information from a subject and forcing his secrets into the open."
            + " An Interrogation of +2 or better will allow you to infallibly find out if your"
            + " boyfriend is lying to you. At +5, you are a professional level interrogator--"
            + "equivalent to a skilled detective grilling a suspect. Mike Wallace of 60 Minutes"
            + " has an Interrogation of +9, allowing him to make even the most  powerful people"
            + " squirm.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.INTIMIDATE,
        "The skill of getting people to do what youy want by force of personality or physical"
            + " coercion. At +3, you can frighten almost any typical citizen, politician or low-"
            + "level thug. At +6, you can intimidate Sylvester Stallone or any moderate \"tough"
            + " guy\". At +9, you could intimidate Arnold Schwartzenegger.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.ORATORY,
        "The skill of public speaking. At +2, you can wing high school speech contests. At +6, you"
            + " can be paid to speak in public. At +10, you are capable of delivering a speech to"
            + " rival Kennedy's \"Ich Bin Ein Berliner\" or Lincoln's Gettysburg Address. Rockers"
            + " with an Oratory Skill of +6 or better can add +1 when using their Charasmatc"
            + " Leadership ability.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.RESIST_TORTURE_AND_DRUGS,
        "Characters with this skill are especially toughened against interrogation, torture and"
            + " mind control drugs. A successful use of this skill will automatically increase the"
            + " difficulty of any interrogation attempt made by another party by one level",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.STREETWISE,
        "The knowledge of the \"seamy\" side of life--where to get illegal and contraband things,"
            + " how to talk to the criminal element, and avoiding bad situations in bad"
            + " neighborhoods. With a Streetwise of +2 or better, you can get \"hot\" items, score"
            + " drugs, etc. A Streetwise of +5 would allow you to arrange a murder contract, know"
            + " a few mobsters who might owe you favors, and be able to call on muscle when you"
            + " need it. At +8 or better, you could become a major crimelord yourself and skip the"
            + " middlemen.",
        1);
    skillsByName.put(skill.getName(), skill);

    skillsByNameByCategoryName.put(attributeName, skillsByName);
  }

  private static void createEmpathySkills( //
      Map<String, Attribute> attributeByName,
      Map<String, Map<String, CyberpunkSkill>> skillsByNameByCategoryName) {

    Map<String, CyberpunkSkill> skillsByName = new TreeMap<String, CyberpunkSkill>();
    String attributeName = CyberpunkAttribute.EMPATHY;
    Attribute attribute = attributeByName.get(attributeName);

    CyberpunkSkill skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.HUMAN_PERCEPTION,
        "The skill of detecting lies, evasions, moods and other emotional clues from others. At"
            + " +2, you can usually tell when you're not getting the whole truth. At +6, you can"
            + " detect subtle evasions and mood swings. At +8, you can not only detect subtle"
            + " emotional clues, but can usually tell what the subject is hiding in a general"
            + " way.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.INTERVIEW,
        "The skill of eliciting interesting annecdotes from an interview subject. This information"
            + " will be of a more non-specific and personal nature rather than specific knowledge"
            + " (distinguishing this skill from the skill of Interrogation, where the user is"
            + " trying to extract exact information. Example: Barbara Walters interviews, Mike"
            + " Wallace interrogates). At +3 or better, the subject will usually tell you only"
            + " infromation relating to what he/she is well known for. At +6 or better, the"
            + " subject will tell you anecdotes about the past, pontificate about favorite"
            + " interests and philosophies, etc. At +9 or better, he/she tells you everything--"
            + "including personal information about their illegitimate son, the time they stole a"
            + " cookie at age 4, and the fact that no one ever loved them.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.LEADERSHIP,
        "The skill of leading and convincing people to follow you. A leader with a skill of +2 can"
            + " manage a small office successfully and be respected for it. A leader with a skill"
            + " of +4 or better can lead a small band of troops into battle and not get backshot."
            + " A leader with a skill of +7 or better can lead the entire Gamelon Empire into"
            + " battle and look good doing it. James Kirk of Star Trek has a Leadership of +11,"
            + " but you never will.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.SEDUCTION,
        "The skill of forming and maintaining romantic relationships (this includes your abilities"
            + " as a lover). This skill may be used to determine whether or not players can form"
            + " relationships with other non-player characters and the intensity of these"
            + " relationships. In certain cases, Referees may want to average this skill with a"
            + " player's Attractiveness to get a more realistic outcome.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.SOCIAL,
        "The ability to deal with social situations, like knowing the right fork to use or when"
            + " not to tell the joke about the farmer's daughter and the travelling cyberware"
            + " salesman. A social Skill of +2 or better will allow you to get by at any fine"
            + " restaurant or social function. At +5, you can lunch with the President with"
            + " aplomb. No soical situation will faze you, no matter what. At +8 or above, you can"
            + " lecture Emily Post on what's proper.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.PERSUASION,
        "The ability to talk others into doing what you want. This may be used individually or on"
            + " large groups. At +3, you can win most debates or convince your girlfriend that the"
            + " blonde you were with was your sister. At +5, you are a smooth talker of"
            + " professional caliber. Ronald Reagan has a Persuasion of +7. Hitler had a"
            + " Persuasion of +9.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.PERFORM,
        "The skill of trained acting, singing, etc. A trained performer of +4 or greater can"
            + " successfully sing for payment at weddings or small clubs. Performers +6 or greater"
            + " will be considered to be of professional caliber, and may have lucrative contracts"
            + " and fans. Performers of +9 or greater are of \"star\" caliber, have a large number"
            + " of fans, and may be recognized on the street.",
        1);
    skillsByName.put(skill.getName(), skill);

    skillsByNameByCategoryName.put(attributeName, skillsByName);
  }

  private static void createIntelligenceSkills( //
      Map<String, Attribute> attributeByName,
      Map<String, Map<String, CyberpunkSkill>> skillsByNameByCategoryName) {

    Map<String, CyberpunkSkill> skillsByName = new TreeMap<String, CyberpunkSkill>();
    String attributeName = CyberpunkAttribute.INTELLIGENCE;
    Attribute attribute = attributeByName.get(attributeName);

    CyberpunkSkill skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.ACCOUNTING,
        "The ability to balance books (or create false books), juggle numbers, create budgets and"
            + " handle day to day business operations.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.ANTHROPOLOGY,
        "The knowledge of human cultures, habits and customs. Unlike Streetwise (which covers only"
            + " the cultures and customs of the Street), or Social (which covers only what you"
            + " should do in a given situation), Anthropology covers general customs and"
            + " background of a culture. For example, with Streetwise, you know what alleys to"
            + " avoid and what gangs are dangerous. With Social, you know the proper forms of"
            + " address for a high ranking Japanese zaibatsu head. With Anthropology, you know"
            + " that the customs of a N'Tanga tribesman require that a young man kill a lion in"
            + " order to be accepted as an adult male.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.AWARENESS,
        "This is the equivalent of a \"trained observer\" skill, allowing characters to notice or"
            + " be aware of clues, shadowers and other events. With an Awareness of +2 you will"
            + " usually spot small pieces of paper with notes on them, doors left ajar, and"
            + " obvious expressions of lying or dislike. An Awareness of +5 or better allows you"
            + " to spot fairly well hidden clues, notice small changes in expression, and fairly"
            + " sophisticated attempts to \"shadow\" you. With an Awareness of +8 or greater, you"
            + " routinely perform the sorts of deductive reasoning seen in the average TV cop show"
            + " (\"The murderer was left handed because this knife has a specialized handle\")."
            + " Sherlock Holmes has a +10 Awareness. Players without this skill may only use their"
            + " INT Stat.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.BIOLOGY,
        "General knowledge of animals, plants, and other biological systems. At level +3, you know"
            + " most types of common animals, plants. At +6, you have a general understanding of"
            + " genetics, cellular biology, etc. At +10, you can perform most bio-lab procedures,"
            + " including gene mapping and splicing.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.BOTANY,
        "The general knowledge of plants and plant identification. At level +3, you know most"
            + " common plants and can identify which ones are dangerous and why. At a +6, you can"
            + " identify most importaant plants found worldwide and have a working knowledge of"
            + " their uses. At +8, you have the equivalent of a doctorate in Botany and know all"
            + " about rare poisons, exotic orchids and other useful plants.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.CHEMISTRY,
        "The required skill for mixing chemicals and creating various compounds. A level +2"
            + " Chemistry is equal to high school chemistry. A level +4 is equal to a trained"
            + " pharmacist or college level chemist. A +8 is a trained laboratory chemist.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.COMPOSITION,
        "The required skill for writing songs, articles, or stories. A Composing Skill of +4 or"
            + " greater gives your character the ability to produce salable work. A Skill of +8 or"
            + " more produces work of such a high caliber that the creator may have a strong"
            + " literary following and not a little critical acclaim.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.DIAGNOSE_ILLNESS,
        "The skill of clinically diagnosing symptoms and medical problems. A +3 is the equivalent"
            + " of a high school nurse--you can recognize most common injuries and complaints. At"
            + " +6, you would be equivalent to a trained intern, you can recognize many uncommon"
            + " illnesses and know how to treat most common ones. A +9 is the equivalent of a"
            + " skilled diagnostician; other physicians come to you to get a diagnosis.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.EDUCATION,
        "This skill is the equivalent of a basic public school education, allowing you to know how"
            + " to read, write, use basic math, and know enough history to get by. In effect, it"
            + " is a \"lore\" or trivia skill. A level of +1 is a basic grade school education. A"
            + " skill of +2 is equal to a high school equivalency. A Knowledge Skill of +3 is"
            + " equal to a college education, +4 or higher is equal to a Masters or Doctorate. At"
            + " +7, you are an extremely well-educated person, and are asked to play Trivial"
            + " Pursuit a lot. At +9 and above, you are one of those people who knows a lot about"
            + " everything (and hopefully has the good sense to keep his mouth shut).",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.EXPERT,
        "You may use this skill to be an expert on one specific subject, such as rare postage"
            + " stamps, obscure weapons, a foreign language, etc. At +3, you are the local expert."
            + " At +6, you know enough to publish a few books on the subject. At +8 or better,"
            + " your books are recognized as major texts on the subject, and you could do the"
            + " talk-show circuit if you wanted to.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.GAMBLE,
        "The skill of knowing how to make bets, figure odds, and play games of chance"
            + " successfully. As any professional gambler knows, this is not a luck skill. At +2,"
            + " you are the local card shark at the Saturday night poker game. At +6, you can make"
            + " a living at the tables in Vegas and Monte Carlo. At +9 or better, you can take on"
            + " James Bond at roulette and stand a good chance of breaking the bank.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.GEOLOGY,
        "A functional knowledge of rocks, minerals and geologic structures. At +3, you can"
            + " identify most common rocks and minerals. At +6, you have the equivalent of a"
            + " college degree in Geology and can identify minerals and geological structures"
            + " with ease. At +8, you can teach geology in high school.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.HIDE_AND_EVADE,
        "The skill of losing pursuers, covering tracks and otherwise evading people on your trail."
            + " At +3, you can lose most booster-gangers on the rampage. At +6, you can ditch cops"
            + " and private eyes. At +8, you can ditch most Solos.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.HISTORY,
        "The knowledge of facts and figures of past events. In game play, this might be used to"
            + " determine if a character is familiar with a particular clue related to a past"
            + " event. At +2, you have the equivalent of a grade school history education. At +6,"
            + " you would have the equivalent of a college grasp on the subject. At +8, you could"
            + " teach history in high school. At +9, you may have written a few of the most"
            + " oftused texts on a particular historical personage or epoch.",
        1);
    skillsByName.put(skill.getName(), skill);

    // Known Language --- start
    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.ENGLISH, //
        "Part of the Indo-European family. Some countries that speak English are: the United"
            + " States, the United Kingdom, and Ireland. The knowledge of a foreign tongue. At +2,"
            + " you can \"get by\" with speaking the language. At +3, you can actually read a"
            + " written form of it. At +6 and above, you are fairly fluent, although no native"
            + " will be fooled by your ability. At +8 and above, you speak and read the language"
            + " like a native. Each language known requires a separate Know Language Skill,"
            + " however, one may use the knowledge of a particular Language with up to 1/2 (round"
            + " down) proficiency with any language in the same linguistic family (example:"
            + " knowing Wu Chinese at +4 will give you the ability to understand and speak"
            + " Mandarin Chinese at +2).",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.MANDARIN, //
        "Part of the Sino-Tibetan family. Some countries that speak Chinese Mandarin are: China,"
            + " Taiwan, and Singapore." + "The knowledge of a foreign tongue. At +2, you can \"get"
            + " by\" with speaking the language. At +3, you can actually read a written form of"
            + " it. At +6 and above, you are fairly fluent, although no native will be fooled by"
            + " your ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.HINDI, //
        "Part of the Indo-European family. Some countries that speak Hindi are: India, Nepal, and"
            + " Fiji. The knowledge of a foreign tongue. At +2, you can \"get by\" with speaking"
            + " the language. At +3, you can actually read a written form of it. At +6 and above,"
            + " you are fairly fluent, although no native will be fooled by your ability. At +8"
            + " and above, you speak and read the language like a native. Each language known"
            + " requires a separate Know Language Skill, however, one may use the knowledge of a"
            + " particular Language with up to 1/2 (round down) proficiency with any language in"
            + " the same linguistic family (example: knowing Wu Chinese at +4 will give you the"
            + " ability to understand and speak Mandarin Chinese at +2).",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.SPANISH, //
        "Part of the Indo-European family. Some countries that speak Spanish are: Spain, Mexico,"
            + " and Colombia. The knowledge of a foreign tongue. At +2, you can \"get by\" with"
            + " speaking the language. At +3, you can actually read a written form of it. At +6"
            + " and above, you are fairly fluent, although no native will be fooled by your"
            + " ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.FRENCH,
        "Part of the Indo-European family. Some countries that speak French are: Belgium, Canada,"
            + " and France. The knowledge of a foreign tongue. At +2, you can \"get by\" with"
            + " speaking the language. At +3, you can actually read a written form of it. At +6"
            + " and above, you are fairly fluent, although no native will be fooled by your"
            + " ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.ARABIC,
        "Part of the Afro-Asiatic family. Some countries that speak Arabic are: Egypt, Algeria,"
            + " and Sudan. The knowledge of a foreign tongue. At +2, you can \"get by\" with"
            + " speaking the language. At +3, you can actually read a written form of it. At +6"
            + " and above, you are fairly fluent, although no native will be fooled by your"
            + " ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.BENGALI,
        "Part of the Indo-European family. Some countries that speak Bengali are: Bangladesh,"
            + " India, and Saudi Arabia. The knowledge of a foreign tongue. At +2, you can \"get"
            + " by\" with speaking the language. At +3, you can actually read a written form of"
            + " it. At +6 and above, you are fairly fluent, although no native will be fooled by"
            + " your ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.RUSSIAN,
        "Part of the Indo-European family. Some coutries that speak Russian are: Russia, Belarus,"
            + " and Krygyzstan. The knowledge of a foreign tongue. At +2, you can \"get by\" with"
            + " speaking the language. At +3, you can actually read a written form of it. At +6"
            + " and above, you are fairly fluent, although no native will be fooled by your"
            + " ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        3);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.PORTUGUESE,
        "Part of the Indo-European family. Some of the countries that speak Portuguese are:"
            + " Portugal, Angola, and Brazil. The knowledge of a foreign tongue. At +2, you can"
            + " \"get by\" with speaking the language. At +3, you can actually read a written form"
            + " of it. At +6 and above, you are fairly fluent, although no native will be fooled by"
            + " your ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.INDONESIAN,
        "Part of the Austronesian family. A country that speaks Indonesian is: Indonesia. The"
            + " knowledge of a foreign tongue. At +2, you can \"get by\" with speaking the"
            + " language. At +3, you can actually read a written form of it. At +6 and above, you"
            + " are fairly fluent, although no native will be fooled by your ability. At +8 and"
            + " above, you speak and read the language like a native. Each language known requires"
            + " a separate Know Language Skill, however, one may use the knowledge of a particular"
            + " Language with up to 1/2 (round down) proficiency with any language in the same"
            + " linguistic family (example: knowing Wu Chinese at +4 will give you the ability to"
            + " understand and speak Mandarin Chinese at +2).",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.URDU,
        "Part of the Indo-European family. Some countries that speak Urdu are: India and"
            + " Pakistan. The knowledge of a foreign tongue. At +2, you can \"get by\" with"
            + " speaking the language. At +3, you can actually read a written form of it. At +6"
            + " and above, you are fairly fluent, although no native will be fooled by your"
            + " ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.GERMAN,
        "Part of the Indo-European family. Some countries that speak German are: Germany, Austria,"
            + " and Switzerland. The knowledge of a foreign tongue. At +2, you can \"get by\" with"
            + " speaking the language. At +3, you can actually read a written form of it. At +6"
            + " and above, you are fairly fluent, although no native will be fooled by your"
            + " ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.JAPANESE,
        "Part of the Japonic family. Some countries that speak Japanese are: Japan and Palau. The"
            + " knowledge of a foreign tongue. At +2, you can \"get by\" with speaking the"
            + " language. At +3, you can actually read a written form of it. At +6 and above, you"
            + " are fairly fluent, although no native will be fooled by your ability. At +8 and"
            + " above, you speak and read the language like a native. Each language known requires"
            + " a separate Know Language Skill, however, one may use the knowledge of a particular"
            + " Language with up to 1/2 (round down) proficiency with any language in the same"
            + " linguistic family (example: knowing Wu Chinese at +4 will give you the ability to"
            + " understand and speak Mandarin Chinese at +2).",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.SWAHILI,
        "Part of the Niger-Congo family. Some countries that speak Swahili are: Kenya, Uganda, and"
            + " Tanzania. The knowledge of a foreign tongue. At +2, you can \"get by\" with"
            + " speaking the language. At +3, you can actually read a written form of it. At +6"
            + " and above, you are fairly fluent, although no native will be fooled by your"
            + " ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.MARATHI,
        "Part of the Indo-European family. A country that speaks Marathi is: India. The knowledge"
            + " of a foreign tongue. At +2, you can \"get by\" with speaking the language. At +3,"
            + " you can actually read a written form of it. At +6 and above, you are fairly"
            + " fluent, although no native will be fooled by your ability. At +8 and above, you"
            + " speak and read the language like a native. Each language known requires a separate"
            + " Know Language Skill, however, one may use the knowledge of a particular Language"
            + " with up to 1/2 (round down) proficiency with any language in the same linguistic"
            + " family (example: knowing Wu Chinese at +4 will give you the ability to understand"
            + " and speak Mandarin Chinese at +2).",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.TELUGU,
        "Part of the Dravidian family. Some countries that speak Telugu are: India, Andhra"
            + " Pradesh, and Telangana. The knowledge of a foreign tongue. At +2, you can \"get"
            + " by\" with speaking the language. At +3, you can actually read a written form of"
            + " it. At +6 and above, you are fairly fluent, although no native will be fooled by"
            + " your ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.LAHNDA,
        "Part of the Indo-European family. Some countries that speak Lahnda are: Pakistan and"
            + " India. The knowledge of a foreign tongue. At +2, you can \"get by\" with"
            + " speaking the language. At +3, you can actually read a written form of it. At +6"
            + " and above, you are fairly fluent, although no native will be fooled by your"
            + " ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.WU,
        "Part of the Sino-Tibetan family. Some countries that speak Wu Chinese are: Shanghai and"
            + " China. The knowledge of a foreign tongue. At +2, you can \"get by\" with"
            + " speaking the language. At +3, you can actually read a written form of it. At +6"
            + " and above, you are fairly fluent, although no native will be fooled by your"
            + " ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.TAMIL,
        "Part of the Dravidian family. Some countries that speak Tamil are: Sri Lanka, Singapore,"
            + " and India. The knowledge of a foreign tongue. At +2, you can \"get by\" with"
            + " speaking the language. At +3, you can actually read a written form of it. At +6"
            + " and above, you are fairly fluent, although no native will be fooled by your"
            + " ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.TURKISH,
        "Part of the Turkic family. Some countries that speak Turkish are: Turkey, Cyprus, and"
            + " Romania. The knowledge of a foreign tongue. At +2, you can \"get by\" with"
            + " speaking the language. At +3, you can actually read a written form of it. At +6"
            + " and above, you are fairly fluent, although no native will be fooled by your"
            + " ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.KOREAN,
        "Part of the Koreanic family. Some countries that speak Korean are: South Korea, North"
            + " Korea, and China. The knowledge of a foreign tongue. At +2, you can \"get by\""
            + " with speaking the language. At +3, you can actually read a written form of it. At"
            + " +6 and above, you are fairly fluent, although no native will be fooled by your"
            + " ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.VIETNAMESE,
        "Part of the Austroasiatic family. Some countries that speak Vietnamese are: Vietnam,"
            + " Laos, and Cambodia. The knowledge of a foreign tongue. At +2, you can \"get by\""
            + " with speaking the language. At +3, you can actually read a written form of it. At"
            + " +6 and above, you are fairly fluent, although no native will be fooled by your"
            + " ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        3);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.YUE,
        "Part of the Sino-Tibetan family. The country that speaks Yue Chinese is: China. The"
            + " knowledge of a foreign tongue. At +2, you can \"get by\" with speaking the"
            + " language. At +3, you can actually read a written form of it. At +6 and above, you"
            + " are fairly fluent, although no native will be fooled by your ability. At +8 and"
            + " above, you speak and read the language like a native. Each language known requires"
            + " a separate Know Language Skill, however, one may use the knowledge of a particular"
            + " Language with up to 1/2 (round down) proficiency with any language in the same"
            + " linguistic family (example: knowing Wu Chinese at +4 will give you the ability to"
            + " understand and speak Mandarin Chinese at +2).",
        3);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.JAVANESE,
        "Part of the Austronesian family. The country that speaks Javanese is: Indonesia. The"
            + " knowledge of a foreign tongue. At +2, you can \"get by\" with speaking the"
            + " language. At +3, you can actually read a written form of it. At +6 and above, you"
            + " are fairly fluent, although no native will be fooled by your ability. At +8 and"
            + " above, you speak and read the language like a native. Each language known requires"
            + " a separate Know Language Skill, however, one may use the knowledge of a particular"
            + " Language with up to 1/2 (round down) proficiency with any language in the same"
            + " linguistic family (example: knowing Wu Chinese at +4 will give you the ability to"
            + " understand and speak Mandarin Chinese at +2).",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.ITALIAN,
        "Part of the Indo-European family. Some countries that speak Italian are: Italy,"
            + " Switzerland, and Vatican City. The knowledge of a foreign tongue. At +2, you can"
            + " \"get by\" with speaking the language. At +3, you can actually read a written form"
            + " of it. At +6 and above, you are fairly fluent, although no native will be fooled"
            + " by your ability. At +8 and above, you speak and read the language like a native."
            + " Each language known requires a separate Know Language Skill, however, one may use"
            + " the knowledge of a particular Language with up to 1/2 (round down) proficiency"
            + " with any language in the same linguistic family (example: knowing Wu Chinese at +4"
            + " will give you the ability to understand and speak Mandarin Chinese at +2).",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.HAUSA,
        "Part of the Afro-Asiatic family. Some countries that speak Hausa are: Niger, Ghana, and"
            + " Cameroon. The knowledge of a foreign tongue. At +2, you can \"get by\" with"
            + " speaking the language. At +3, you can actually read a written form of it. At +6"
            + " and above, you are fairly fluent, although no native will be fooled by your"
            + " ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.THAI,
        "Part of the Kra-Dai family. Some countries that speak Thai are: Thailand, Cambodia, and"
            + " Laos. The knowledge of a foreign tongue. At +2, you can \"get by\" with"
            + " speaking the language. At +3, you can actually read a written form of it. At +6"
            + " and above, you are fairly fluent, although no native will be fooled by your"
            + " ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        3);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.GUJARATI,
        "Part of the Indo-European family. Some countries that speak Gujarati are: India,"
            + " Bangladesh, and Fiji. The knowledge of a foreign tongue. At +2, you can \"get"
            + " by\" with speaking the language. At +3, you can actually read a written form of"
            + " it. At +6 and above, you are fairly fluent, although no native will be fooled by"
            + " your ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill(attribute, CyberpunkSkill.PERSIAN,
        "Part of the Indo-European family. Some countries that speak Persian are: Iran, Russia,"
            + " and Iraq. The knowledge of a foreign tongue. At +2, you can \"get by\" with"
            + " speaking the language. At +3, you can actually read a written form of it. At +6"
            + " and above, you are fairly fluent, although no native will be fooled by your"
            + " ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.BHOJPURI, //
        "Part of the Indo-European family. Some countries that speak Bhojpuri are: India, Fiji,"
            + " and Nepal. The knowledge of a foreign tongue. At +2, you can \"get by\" with"
            + " speaking the language. At +3, you can actually read a written form of it. At +6"
            + " and above, you are fairly fluent, although no native will be fooled by your"
            + " ability. At +8 and above, you speak and read the language like a native. Each"
            + " language known requires a separate Know Language Skill, however, one may use the"
            + " knowledge of a particular Language with up to 1/2 (round down) proficiency with"
            + " any language in the same linguistic family (example: knowing Wu Chinese at +4 will"
            + " give you the ability to understand and speak Mandarin Chinese at +2).",
        2);
    skillsByName.put(skill.getName(), skill);
    // Known Language --- end

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.LIBRARY_SEARCH,
        "The skill of using databases, DataTerms, libraries and other compiled information cources"
            + " to find facts. With a skill of +2 you can use most simple databases. With a skill"
            + " of +6, you can easily access the Library Congress. At +9, you can comprehend"
            + " almost any public database and find very obscure facts.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.MATHEMATICS,
        "The skill of understanding calculations and mathematical formulas. At +3, you have the"
            + " ability to add, subtract, divide and multiply. At +4, you can do algebra and"
            + " geometry. At +6, you can perform calculus. At +9 you can deduce your own"
            + " mathematical formulas.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.PROGRAMMING,
        "The required skill to write programs and to re-program computer systems. This skill does"
            + " not allow players to actually do repairs on a computer (this requires"
            + " Electronics). With a Programming Skill of +1, you can do simple EBASIC programs. A"
            + " Programming Skill of +3 or better allows you to know some higher level languages"
            + " and be able to write reasonably complex programs (including video games). Players"
            + " with a Programming Skill +6 or better are considered to be professionals, who can"
            + " build operating software, design mainframe systems, and hold down a steady job at"
            + " your average Silicon Valley firm. With a Programming Skill of +9 or better, other"
            + " programmers speak your name with reverence (\"You invented Q? Wow!\"), young"
            + " hackers set out to crack your systems, and any computer software you design"
            + " instantly gets used by every business application in the world.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.SHADOW_AND_TRACK,
        "The skill of shadowing and following people. This skill is primarily used in urban or"
            + " inhabited areas rather than in wilderness (where the skill of Survival"
            + " incorporates tracking game in the wilds).",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.STOCK_MARKET,
        "The ability to play the stock market, engage in routine stock transactions and manipulate"
            + " stocks profitably. At +2, you know enough to invest in junk bonds and lose your"
            + " shirt. At +6, your investments pay off 75% of the time. At +9, you are a major"
            + " heavy on the Market, routinely dabble in international stocks, and can write"
            + " learned articles on the subject of investment.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.SYSTEM_KNOWLEDGE,
        "Basic knowledge of the geography of the Net, it's lore and history, as well as knowledge"
            + " of the important computer systems, their strengths and their weaknesses. At +2,"
            + " you can generally navigate around the Net and know where all the local places are."
            + " At +6, you know the locations of most places in the Net, and have a working"
            + " understanding of its largest and most well known systems. At +9, you know the"
            + " entire Net like the back of your hand, know the general layouts of the important"
            + " systems cold, and are aware of the layouts for the rest of them.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.TEACHING,
        "The skill of imparting knowledge to someone else (if you don't think this is a skill, you"
            + " ought to try it sometime). Players may not teach any skill unless they have a"
            + " higher skill level than the student. The referee is the final arbiter of how long"
            + " it takes to teach a skill. At a Teaching Skill of +3 or better, you can"
            + " professionally teach students up to High School. At +6, you know enough to be a"
            + " college professor (if you wanted). At +9 or greater, you are recognized by others"
            + " in the field as good enough to guest lecture at MIT or Cal Tech; your texts on the"
            + " subject are quoted as the major references, and you might have a TV show on the"
            + " equivalent of the PBS channel.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.WILDERNESS_SURVIVAL,
        "The required skill for knowing how to survive in the wilds. Knowledge includes how to set"
            + " traps, forage for wood, track game, build shelters, make fires. The average Boy"
            + " Scout has a Survival of +3. A Special Forces Green Beret has a Survival of +6 or"
            + " above. Grizzly Adams, Mountain Man of the Wilderness, would have a +9 or +10"
            + " Survival Skill.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.ZOOLOGY,
        "Knowledge of lifeforms, biological processes and their relation to the environment. At"
            + " +2, you know most common animals. At +5, you know not only well known animals, but"
            + " also about many exotics and endangered species. At +8, you are knowledgable on"
            + " almost all animals, know their habits well, and have a +1 advantage to any"
            + " Wilderness Survival Skills (you know where to find the game).",
        1);
    skillsByName.put(skill.getName(), skill);

    skillsByNameByCategoryName.put(attributeName, skillsByName);
  }

  private static void createReflexSkills( //
      Map<String, Attribute> attributeByName,
      Map<String, Map<String, CyberpunkSkill>> skillsByNameByCategoryName) {

    Map<String, CyberpunkSkill> skillsByName = new TreeMap<String, CyberpunkSkill>();
    String attributeName = CyberpunkAttribute.REFLEXES;
    Attribute attribute = attributeByName.get(attributeName);

    CyberpunkSkill skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.ARCHERY,
        "The skill required to use bows, crossbows and other arrow-based ranged weapons. See"
            + " Handgun for details.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.ATHLETICS,
        "This Skill is required for accurate throwing, climbing, and balancing. It combines the"
            + " basic elements of any high school level sports program. At +3 and above, you are"
            + " the equivalent of a real high school \"jock\". At +5 and above, you can perform in"
            + " college level competitions. At +8 and above, you are of Olympic or Professional"
            + " caliber.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.BRAWLING,
        "The skill of fighting man to man with fist, feet and other parts of the body. Brawling is"
            + " not a trained skill--it is learned on the Street by getting into a lot of fights."
            + " Unlike Martial Arts, there are no specialized attacks an no damage bonuses based"
            + " on level of mastery.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.DANCE,
        "The specific skill needed to become a professional dancer. A trained dancer +4 or greater"
            + " can successfully dance for payment in small clubs or dance troupes. Dancers +6 or"
            + " greater will be considered to be of professional caliber, and regularly give"
            + " performances and have fans. Dancers +9 or greater are of \"star\" caliber, have a"
            + " large number of fans, and may be recognized on the street.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.DODGE_AND_ESCAPE,
        "This skill is required to dodge attacks and escape grapples and holds. If an attack is"
            + " made without your knowledge, you may not apply this skill to your Defense roll.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.DRIVING,
        "This skill allows you to pilot all ground vehicles like cars, trucks, tanks and"
            + " hovercraft. This skill is not useable for piloting aircraft. A skill of +3 is"
            + " equal to that of a very good non-professional driver. A skill of +6 allows you to"
            + " drive with the skill of a moderately skilled race driver. A driver with a skill of"
            + " +8 or greater will be nationally known as a racer, regularly win championship"
            + " races, and possibly have access to the most adbanced ground vehicles available (as"
            + " long as he makes an endorsement).",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.FENCING,
        "The mastery of swords, rapiers and monoblades. A Fencing Skill of +3 allows you to be"
            + " competent with a blade. A Skill of +5 makes you fairly skilled. A Fencing Skill of"
            + " +6 might win you the National Fencing Competitions. A Skill of +8 will get you a"
            + " reputation for being a true swordsman of duelist caliber. People like D'Artagnan"
            + " or Miyamoto Musashi have Skills of +10. They are legendary masters of the blade;"
            + " the mention of whom will cause all but the stupidest young bravo to run for"
            + " cover.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.HANDGUN,
        "You must have this skill to effectively use handguns of any type, including cyberware"
            + " types. At +2, you ca use a handgun on a target range, through combat will still"
            + " rattle you. At +5, you are as skilled as most military officers or policemen. At"
            + " +7, you can do the sort of fancy shooting you see on TV, and have begun to get a"
            + " reputation of being \"good with gun\". At +8, you are a recognized gunslinger with"
            + " a \"rep\". The very sound of your name makes some people back down in fear. At"
            + " +10, you are a legendary gunslinger, feared by all except the stupid young punks"
            + " who keep trying to \"take\" you in innumerable gunfight challenges.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.HEAVY_WEAPONS,
        "The required skill for using grenade launchers, autocannon, mortars, heavy machine guns,"
            + " missiles and rocket launchers. A level +5 skill would be equivalent to a general"
            + " military \"Heavy Weapons\" training course, giving the user the ability to use any"
            + " or all of these weapon types.",
        1);
    skillsByName.put(skill.getName(), skill);

    // Martial Arts --- start
    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.MARTIAL_ARTS_AIKIDO,
        "This form relies on using the opponent's strength and momentum against him. It is a"
            + " perfect form for stopping an opponent peacefully while making yourself very hard"
            + " to hit. Key attacks are: blocks & parries, dodges, throws, holds, escapes, chokes,"
            + " sweeps, trips & sweeps, grapples.",
        3);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.MARTIAL_ARTS_ANIMAL_KUNG_FU,
        "There are forms based on animal movements, such as crane, mantis, tiger, leopard and"
            + " dragon forms. These attacks are fast and dangerous, with a style that is exciting"
            + " and flashy. Key attacks include: strikes, punches, kicks, blocks & parries, sweeps"
            + " & trips.",
        3);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.MARTIAL_ARTS_BOXING,
        "The manly art of fisticuffs, this form delivers lightning punches and tight blocking"
            + " defense. Key attacks are: punches, blocks & parries.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.MARTIAL_ARTS_CAPOERIA,
        "Created by Caribbean slaves, this form combines dancelike movements with fast kicks and"
            + " low line sweeps. It is a relatively unknown form and can be combined with dance"
            + " moves to disguise it's true power. Key attacks are: punches, kicks, blocks &"
            + " parries, dodges, and sweeps & trips.",
        3);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.MARTIAL_ARTS_CHOI_LI_FUT,
        "Descended directly form the ancient Shaolin temples, this form combines powerful"
            + " roundhouse blows and sweeping kicks into dynamic fighting style. Key attacks are:"
            + " strikes, punches, kicks, blocks & parries, dodges, throws, and sweeps & trips.",
        3);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.MARTIAL_ARTS_JUDO,
        "This system was designed as a sport form, but is very effective in combat as well. It"
            + " uses throws and sweeps to knock down the opponent. Key attacks include dodges,"
            + " throws, holds, escape sweeps & trips and grappling.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.MARTIAL_ARTS_KARATE,
        "The Japanese version of kung fu, this style uses straight line movements and powerful"
            + " blows. Variations include shotokan and kenpo, each with their own special moves."
            + " Key attacks are: punches, kicks, and blocks & parries.",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.MARTIAL_ARTS_TAE_KWON_DO,
        "A very fast and precise form, with graceful movements and some aerial kicks. Key attacks"
            + " include: strikes, punches, kicks, blocks & parries, dodges.",
        3);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.MARTIAL_ARTS_THAI_KICK_BOXING,
        "One of the deadliest form in existence, this style is known for blinding kicks delivered"
            + " with incredible power. Key moves include: strikes, punches, kicks, blocks &"
            + " parries.",
        4);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.MARTIAL_ARTS_WRESTLING,
        "This form combines techniques of Olympic and Professional wrestling. The style uses a"
            + " wide variety of throws and holds to incapacitate the opponent. Key attacks"
            + " include: throws, holds, escapes, chokes, sweeps, trips, and grapple.",
        1);
    skillsByName.put(skill.getName(), skill);
    // Martial Arts --- end

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.MELEE,
        "The ability to use knives, axes, clubs and other hand to hand weapons in combat. Note:"
            + " when using non-ranged cyberweapons such as rippers, scratchers, slice n'dices,"
            + " cyberbeasts, and battlegloves, you must use this skill.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.MOTORCYCLE,
        "The required skill to operate motorcycles, cyberbikes and other two and three-wheeled"
            + " vehicles.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.OPERATE_HEAVY_MACHINERY,
        "The required skill to operate tractors, tanks, very large trucks and construction"
            + " equipment.",
        1);
    skillsByName.put(skill.getName(), skill);

    // Piloting --- start
    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.PILOT_GYRO,
        "The ability to pilot all types of rotorwing aircraft, including gyros, copters and"
            + " Osperys.",
        3);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.PILOT_FIXED_WING,
        "The ability to pilot fixed wing jets and light aircraft. Ospreys may be flown with this"
            + " skill, but only in the straight ahead (non-hover) mode.",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.PILOT_DIRIGIBLE,
        "The ability to pilot all lighter than air vehicles, including cargo dirigibles, blimps"
            + " and powered balloons",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.PILOT_VECTORED_THRUST_VEHICLE,
        "The skill of piloting all types of vectored thrust vehicles, including hovercars,"
            + " hovercrafts and AV-4, 6 and 7 vehicles.",
        3);
    skillsByName.put(skill.getName(), skill);
    // Piloting --- end

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.RIFLE,
        "You must have this skill to use rifles/shotguns effectively (see Handgun limitations and"
            + " modifiers).",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.STEALTH,
        "The skill of hiding in the shadows, moving silently, evading guards, etc. A Stealth Skill"
            + " of +1 is about the level of a very sneaky 10 year old stealing cookies. At +3, you"
            + " are able to get past most guards, or your parents if you've been grounded. At +6,"
            + " you are good enough to slip smoothly from shadow to shadow and not make any noise."
            + " At +8 you are the equal of most Ninja warriors. At +10, you move as silently as a"
            + " shadow, making the Ninja sound like elephants.",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.SUBMACHINEGUN,
        "You must have this skill to use any type of submachine gun effectively (see Handgun for"
            + " limitations and modifiers).",
        1);
    skillsByName.put(skill.getName(), skill);

    skillsByNameByCategoryName.put(attributeName, skillsByName);
  }

  private static void createTechnicalSkills( //
      Map<String, Attribute> attributeByName,
      Map<String, Map<String, CyberpunkSkill>> skillsByNameByCategoryName) {

    Map<String, CyberpunkSkill> skillsByName = new TreeMap<String, CyberpunkSkill>();
    String attributeName = CyberpunkAttribute.TECHNICAL_ABILITY;
    Attribute attribute = attributeByName.get(attributeName);

    CyberpunkSkill skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.AERO_TECH,
        "The required skill for repairing fixed wing aircraft, including Ospreys, jets, and light"
            + " aircraft. With a Skill of +3, you can perform most routine maintenance tasks. With"
            + " a Skill of +6, you can do engine teaerdowns and major structural repairs. With a"
            + " Skill of +9 or better you are capable of designing and building your own.",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.AV_TECH,
        "The required skill for repairing all ducted fan aerodyne vehicles. At +3, you can perform"
            + " routine maintenance. At +6, you can tear down engines and modify an AV. At +10,"
            + " you can design your own AVs on common airframe.",
        3);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.BASIC_TECH,
        "The required skillsByName for building or repairing simple mechanical and electrical"
            + " devices, such as car engines, television sets, etc. With a Basic Tech Skill of +3"
            + " or better, you can fix minor car problems, repair basic wiring, etc. A Basic Tech"
            + " Skill of +9 or better can build a simple computer from scratch, put together a"
            + " race car engine and maintain any kind of industrial machinery. However, they do"
            + " not know enough specialized knowledge to apply it to complex things such as"
            + " aircraft (just like Mr. Goodwrench doesn't know how to build and service an"
            + " F-16).",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.CRYOTANK_OPERATION,
        "The required skill for operating, repairing and maintaining life suspension and body"
            + " chilling devices. A minimum skill of +4 is required to chill down a healthy peron."
            + " A minimum skill of +6 for chilling a wounded person.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.CYBERDECK_DESIGN,
        "The required skill for designing cyberdecks. At level +4, you can modify an existing"
            + " cyberdeck for greater speed or memory. At level +6, you can design a deck equal to"
            + " most existing designs. At +8, you can design decks that are substantially improved"
            + " over existing designs.",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.CYBERTECH,
        "The required skill for repairing and maintaining cyberwear. At level +2, you can keep"
            + " your cyberwear tuned up and can replace its power batteries. At level +6, you can"
            + " strip down most cyberwear and even make simple modifications. At level +8, you can"
            + " design your own cyberwear to order.",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.DEMOLITIONS,
        "This skill allows the character to be knowledgeable in the use of explosives, as well as"
            + " knowing the best explosives to use for which jobs, how to set timers and"
            + " detonators, and how much explosive to use to accomplish a desired result.",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.DISGUISE,
        "The skill of disguising your character to resemble someone else, whether real or"
            + " fictitious. This skill incorporates elements of both makeup and acting, although"
            + " it is not the same as the ability to actually be an actor.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.ELECTRONICS,
        "The required skill for maintaining, repairing and modifying electronic security systems,"
            + " cameras and monitors.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill(attribute, CyberpunkSkill.ELECTRONIC_SECURITY,
        "The skill of installing or countering electronic eyes, electronic locks, bugs and"
            + " tracers, security cameras, pressure plates, etc. At level +3 you can jimmy or"
            + " install most apartment locks and security cams. At +6, you can override most"
            + " corporate office locks and traps. At +9, you can enter most high security areas"
            + " with impunity.",
        2);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.FIRST_AID,
        "This skill allows the user to bind wounds, stop bleeding, and revive a stunned patient."
            + " When a character makes a successful First Aid skill check, the patient will"
            + " recover at the rate of 0.5 points per day. Only one check need be made. You may"
            + " (within reason and at Referee's discretion), perform first aid on yourself. On an"
            + " unsuccessful roll, the patient regains no points. New attempts may be made once"
            + " per day until a successful roll is made.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.FORGERY,
        "The skill of copying and creating false documents and identifications. This skill may"
            + " also be applied to the detection of same; if you can fake it, you can usually tell"
            + " a fake as well.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.GYRO_TECH,
        "The skill of repairing and maintaining rotorwing aircraft such as helicopters and"
            + " gyrocopters.",
        3);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.PAINT_OR_DRAW, //
        "The skill of producing professional drawings. A Skill of +3 allows you to produce salable"
            + " \"modern\" art. A Skill of +6 will produce artwork that is recognizable and"
            + " extremely pleasant to the eye--as well as salable. An artist with a Skill of +8 or"
            + " greater will be nationally known, have exhibits in galleries, and have other"
            + " lesser artists studying his style in art school.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.PHOTO_AND_FILM,
        "The skill of producing professional-caliber photographs or motion pictures. A Skill of +2"
            + " allows you to make decent home movies. A Skill of +4 or better creates work"
            + " capable of winning amateur contests. A Skill of +6 or better will produce work of"
            + " the level of the average Playboy cover or rock video. A photographer or"
            + " cinematographer with a Skill of +8 or better will be nationally known and probably"
            + " famous.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.PICK_LOCK,
        "The skill required to pick locks and break into sealed containers and rooms. At +3, you"
            + " can jimmy most simple locks. At +6 you can crack most safes. At +9 or better you"
            + " have a rep as a master cracksman, and are known to all the major players in the"
            + " Cyberpunk world.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.PICK_POCKET,
        "The required skill for picking pockets without being noticed, as well as \"shoplifting\""
            + " small items. For ideas on levels of ability, see Pick Lock.",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.PLAY_INSTRUMENT,
        "The skill of knowing how to play a musical instrument. You must take this skill"
            + " separately for each type of instrument played. A Skill of +4 or higher will"
            + " qualify your character to play professional \"gigs\". A skill of +8 and above will"
            + " gain the musician some professional acclaim, possibly with recording contracts and"
            + " command performances. At +10, you are widely acclaimed, have lots of Grammys, and"
            + " regularly jam with Kerry Eurodyne",
        1);
    skillsByName.put(skill.getName(), skill);

    skill = new SpecializedSkill( //
        attribute, //
        CyberpunkSkill.WEAPONSMITH,
        "The required skill for repairing an maintaining weapons of all types. At level +2, you"
            + " can do repairs and field stripping. At level +6, you can repair all types of"
            + " weapons and make simple modifications. At level +8, you can design your own"
            + " weapons to order.",
        2);
    skillsByName.put(skill.getName(), skill);

    skillsByNameByCategoryName.put(attributeName, skillsByName);
  }

  private static void createDependantSkills( //
      Map<String, Attribute> attributeByName,
      Map<String, Map<String, CyberpunkSkill>> skillsByNameByCategoryName) {

    /* Requires Mathematics >= 4 */
    CyberpunkSkill independentSkill = skillsByNameByCategoryName //
        .get(CyberpunkAttribute.INTELLIGENCE).get(CyberpunkSkill.MATHEMATICS);
    skillsByNameByCategoryName.get(CyberpunkAttribute.INTELLIGENCE).put( //
        CyberpunkSkill.PHYSICS, //
        new SpecializedSkill( //
            attributeByName.get(CyberpunkAttribute.INTELLIGENCE), //
            CyberpunkSkill.PHYSICS, //
            "The ability to calculate physical principles, such as gas pressure, mechanical"
                + " energies, etc. This skill requires a basic Mathematics Skill of +4.",
            1, //
            new SkillRestriction(independentSkill, 4)));

    /* Requires Chemistry >= 4 */
    independentSkill = skillsByNameByCategoryName //
        .get(CyberpunkAttribute.INTELLIGENCE).get(CyberpunkSkill.CHEMISTRY);
    skillsByNameByCategoryName.get(CyberpunkAttribute.TECHNICAL_ABILITY).put( //
        CyberpunkSkill.PHARMACEUTICALS, //
        new SpecializedSkill( //
            attributeByName.get(CyberpunkAttribute.TECHNICAL_ABILITY), //
            CyberpunkSkill.PHARMACEUTICALS,
            "The skill of designing and manufacturing drugs and medicines. A minimum Chemistry"
                + " skill of +4 is required. At +4 you can make asprin. At +6, you can make"
                + " hallucinogenics or antibiotics. At level +9 you can build designer drugs"
                + " tailored to individual body chemistries.",
            2, //
            new SkillRestriction(independentSkill, 4)));
  }

}
