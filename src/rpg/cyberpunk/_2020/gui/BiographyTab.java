package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import rpg.Gender;
import rpg.cyberpunk._2020.Player;
import rpg.cyberpunk._2020.stats.CyberpunkAttribute;
import rpg.general.stats.Attribute;
import rpg.util.Name;

/**
 * A panel that displays the data that makes up a player's background and main attributes.
 */
public class BiographyTab extends JPanel {
  private static final long serialVersionUID = 1L;

  private Player player;
  private JTextField aliasTextField;
  private JTextField ageTextField;
  private JComboBox<Gender> genderComboBox;
  private JTextField eyeTextField;
  private JTextField heightTextField;
  private JTextField hairTextField;
  private JTextField skinToneTextField;
  private JTextField weightTextField;
  private JComboBox<String> personalityComboBox;
  private JComboBox<String> valuedPersonComboBox;
  private JComboBox<String> valuedConceptComboBox;
  private JComboBox<String> feelingsTowardOthersComboBox;
  private JComboBox<String> valuedPosessionComboBox;
  private JTextArea backstoryTextArea;

  /**
   * Constructs an instance of BiographyTab that has two sections: 1) General statistics such as
   * Attributes, age, etc. as well as a portrait of the character.
   * 
   * @param player the provider of all data displayed under this tab
   */
  public BiographyTab(Player player) {
    super(new BorderLayout());
    setPlayer(player);

    add(createContentComponent(), BorderLayout.CENTER);
    add(createUpdateComponent(), BorderLayout.SOUTH);
  }

  private void setPlayer(Player player) {
    if (player == null) {
      throw new NullPointerException();
    } else {
      this.player = player;
    }
  }

  private Component createContentComponent() {
    JPanel panel = new JPanel(new GridLayout(0, 2));

    panel.add(createStatComponent());
    panel.add(new JScrollPane(createBackgroundView()));

    return panel;
  }

  private Component createStatComponent() {
    JPanel panel = new JPanel(new GridLayout(2, 0));

    panel.add(createDetailStatComponent());
    panel.add(createAttributesParentComponent());

    return panel;
  }

  private Component createDetailStatComponent() {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add(createPictureComponent(), BorderLayout.CENTER);
    panel.add(createBasicInfoComponent(), BorderLayout.SOUTH);

    return panel;
  }

  private Component createPictureComponent() {
    JPanel panel = new JPanel();

    // TODO (Coul Greer): Add the ability for user to provide a picture as well as a default picture
    // if one was not provided.
    try {
      BufferedImage bufferedImage = ImageIO.read(new File("img/default_portrait.png"));
      ImageIcon icon = new ImageIcon(bufferedImage.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
      JLabel imageLabel = new JLabel(icon);
      imageLabel.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
      panel.add(imageLabel);
    } catch (IOException ex) {
      // TODO (Coul Greer): Add a logger.
      System.out.println(ex);
    }

    return panel;
  }

  private Component createBasicInfoComponent() {
    JPanel panel = new JPanel(new GridLayout(1, 0));

    Border divider = BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK);

    aliasTextField = new JTextField(player.getAlias().getValue());
    JComponent p = createInfoComponent( //
        "Alias", //
        aliasTextField);
    p.setBorder(divider);
    panel.add(p);

    ageTextField = new JTextField(Integer.toString(player.getAge()));
    p = createInfoComponent( //
        "Age", //
        ageTextField);
    p.setBorder(divider);
    panel.add(p);

    genderComboBox = new JComboBox<Gender>(Gender.values());
    p = createInfoComponent( //
        "Gender", //
        genderComboBox);
    panel.add(p);

    return panel;
  }

  private JComponent createInfoComponent(String title, Component dataComponent) {
    JPanel panel = new JPanel(new BorderLayout());

    JLabel titleLabel = new JLabel(title, SwingConstants.LEFT);
    titleLabel.setFont(new Font("Serif", Font.PLAIN, 14));
    panel.add(titleLabel, BorderLayout.NORTH);

    dataComponent.setFont(new Font("Serif", Font.PLAIN, 20));
    panel.add(dataComponent, BorderLayout.CENTER);

    return panel;
  }

  private Component createParentComonpent(String title, Component dataComponent) {
    JPanel panel = new JPanel(new BorderLayout());

    JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
    titleLabel.setFont(new Font("Serif", Font.PLAIN, 24));
    panel.add(titleLabel, BorderLayout.NORTH);

    panel.add(dataComponent, BorderLayout.CENTER);

    return panel;
  }

  private Component createAttributesParentComponent() {
    JPanel panel = new JPanel(new BorderLayout());

    // TODO (Coul Greer): DRY for Title and components.
    panel.add(createCategoryTitleComponent("ATTRIBUTES"), BorderLayout.NORTH);
    panel.add(createAttributeComponent(), BorderLayout.CENTER);

    return panel;
  }

  private Component createCategoryTitleComponent(String title) {
    JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
    titleLabel.setFont(new Font("Serif", Font.PLAIN, 24));
    return titleLabel;
  }

  private Component createAttributeComponent() {
    JPanel panel = new JPanel(new GridLayout(0, 4));
    panel.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));

    JComponent c;
    int borderWidth = 2;

    // Row 1
    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.INTELLIGENCE), //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createMatteBorder(0, 0, 0, borderWidth, Color.BLACK));
    panel.add(c);

    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.REFLEXES), //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createMatteBorder(0, 0, 0, borderWidth, Color.BLACK));
    panel.add(c);

    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.TECHNICAL_ABILITY), //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createMatteBorder(0, 0, 0, borderWidth, Color.BLACK));
    panel.add(c);

    panel.add(new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.COOL), //
        BorderLayout.WEST));

    // Row 2
    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.ATTRACTIVENESS), //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createMatteBorder(borderWidth, 0, 0, borderWidth, Color.BLACK));
    panel.add(c);

    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.LUCK), //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createMatteBorder(borderWidth, 0, 0, borderWidth, Color.BLACK));
    panel.add(c);

    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.MOVEMENT_ALLOWANCE), //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createMatteBorder(borderWidth, 0, 0, borderWidth, Color.BLACK));
    panel.add(c);

    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.BODY_TYPE), //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createMatteBorder(borderWidth, 0, 0, 0, Color.BLACK));
    panel.add(c);

    // Row 3
    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.EMPATHY), //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createMatteBorder(borderWidth, 0, 0, borderWidth, Color.BLACK));
    panel.add(c);

    c = createDerivedAttributeContainer( //
        player.getAttribute(CyberpunkAttribute.RUN));
    c.setBorder(BorderFactory.createMatteBorder(borderWidth, 0, 0, borderWidth, Color.BLACK));
    panel.add(c);

    c = createDerivedAttributeContainer( //
        player.getAttribute(CyberpunkAttribute.LEAP));
    c.setBorder(BorderFactory.createMatteBorder(borderWidth, 0, 0, borderWidth, Color.BLACK));
    panel.add(c);

    c = createDerivedAttributeContainer( //
        player.getAttribute(CyberpunkAttribute.CARRY));
    c.setBorder(BorderFactory.createMatteBorder(borderWidth, 0, 0, 0, Color.BLACK));
    panel.add(c);

    return panel;
  }

  private JComponent createDerivedAttributeContainer(Attribute attribute) {
    JPanel panel = new JPanel(new BorderLayout());

    JLabel titleLabel = new JLabel(attribute.getName());
    titleLabel.setFont(AttributePane.DEFAULT_FONT);
    titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
    panel.add(titleLabel, BorderLayout.WEST);

    JLabel valueLabel = new JLabel(Integer.toString(attribute.getModifier()));
    valueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    panel.add(valueLabel, BorderLayout.CENTER);

    return panel;
  }

  private Component createBackgroundView() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    panel.add(createLanguageComponent());
    panel.add(createParentComonpent("CHARACTERISTICS", createCharacteristicsComponent()));
    panel.add(createParentComonpent("SIBLINGS", new JScrollPane(new SiblingTable(player))));
    panel.add(createParentComonpent("MOTIVATIONS", createMotivationComponent()));
    panel.add(createParentComonpent("LIFE EVENTS", new JScrollPane(new LifeEventTable(player))));
    panel.add(createParentComonpent("BACKSTORY", createBackstoryComponent()));

    return panel;
  }

  private Component createLanguageComponent() {
    JPanel panel = new JPanel();

    // TODO (Coul Greer): Add language display. This should update a language skill.

    return panel;
  }

  private Component createCharacteristicsComponent() {
    JPanel panel = new JPanel(new GridLayout(0, 3));

    eyeTextField = new JTextField("Eyes Placeholder");
    panel.add(createInfoComponent("Eyes", eyeTextField));

    heightTextField = new JTextField("Height Placeholder");
    panel.add(createInfoComponent("Height", heightTextField));

    hairTextField = new JTextField("Hair Placeholder");
    panel.add(createInfoComponent("Hair", hairTextField));

    skinToneTextField = new JTextField("Skin Placeholder");
    panel.add(createInfoComponent("Skin Tone", skinToneTextField));

    weightTextField = new JTextField("Weight Placeholder");
    panel.add(createInfoComponent("Weight", weightTextField));

    return panel;
  }

  private Component createMotivationComponent() {
    JPanel panel = new JPanel(new GridLayout(0, 1, 0, 6));

    String[] personalityOptions = { //
        "Shy and secretive", //
        "Rebellious, antisocial, violent", //
        "Arrogant, proud and aloof", //
        "Moody, rash and headstrong", //
        "Picky, fussy and nervous", //
        "Stable and serious", //
        "Silly and fluffheaded", //
        "Sneaky and deceptive", //
        "Intellectual and detached", //
        "Friendly and outgoing"};
    personalityComboBox = new JComboBox<String>(personalityOptions);
    panel.add(createInfoComponent("Personality Traits", personalityComboBox));

    String[] valuedPersonOptions = { //
        "A parent", //
        "Brother or sister", //
        "Lover", //
        "Friend", //
        "Yourself", //
        "A pet", //
        "Teacher or mentor", //
        "Public figure", //
        "A personal hero", //
        "No one"};
    valuedPersonComboBox = new JComboBox<String>(valuedPersonOptions);
    panel.add(createInfoComponent("Valued Person", valuedPersonComboBox));

    String[] valuedConceptOptions = { //
        "Money", //
        "Honor", //
        "Your word", //
        "Honestly", //
        "Knowledge", //
        "Vengeance", //
        "Love", //
        "Power", //
        "Having a good time", //
        "Friendship"};
    valuedConceptComboBox = new JComboBox<String>(valuedConceptOptions);
    panel.add(createInfoComponent("Valued Concept", valuedConceptComboBox));

    String[] feelingsTowardOthersOptions = { //
        "Neutral", //
        "I like almost everyone", //
        "I hate almost everyone", //
        "People are tools", //
        "Everyone is a valuable individual", //
        "People are obstacles to be destroyed", //
        "People are untrustworthy", //
        "Wipe 'em all out", //
        "People are wonderful"};
    feelingsTowardOthersComboBox = new JComboBox<String>(feelingsTowardOthersOptions);
    panel.add(createInfoComponent("Feelings Towards Others", feelingsTowardOthersComboBox));

    String[] valuedPosessionOptions = { //
        "A weapon", //
        "A tool", //
        "A piece of clothing", //
        "A photograph", //
        "A book or diary", //
        "A recording", //
        "A musical instrument", //
        "A piece of jewelry", //
        "A toy", //
        "A letter"};
    valuedPosessionComboBox = new JComboBox<String>(valuedPosessionOptions);
    panel.add(createInfoComponent("Valued Posession", valuedPosessionComboBox));

    return panel;
  }

  private Component createBackstoryComponent() {
    backstoryTextArea = new JTextArea(10, 0);
    backstoryTextArea.setLineWrap(true);
    backstoryTextArea.setWrapStyleWord(true);
    return backstoryTextArea;
  }

  private Component createUpdateComponent() {
    JPanel panel = new JPanel();

    JButton button = new JButton("Update");
    button.addActionListener(evt -> updatePlayer());
    panel.add(button);

    return panel;
  }

  private void updatePlayer() {

    try {
      player.setAlias(new Name(aliasTextField.getText()));
    } catch (IllegalArgumentException ex) {
      String alias = null;

      do {
        alias = JOptionPane.showInputDialog( //
            this, //
            "Please enter a value with at least one character.", //
            "Error", //
            JOptionPane.PLAIN_MESSAGE);
      } while (alias.length() <= 0);

      player.setAlias(new Name(alias));
    }

    try {
      player.setAge(Integer.parseInt(ageTextField.getText()));
    } catch (NumberFormatException ex) {
      validateAndSetAge();
    } catch (IllegalArgumentException ex) {
      validateAndSetAge();
    }

    player.setGender((Gender) genderComboBox.getSelectedItem());
    player.setEyes(eyeTextField.getText());
    player.setHeight(heightTextField.getText());
    player.setHair(hairTextField.getText());
    player.setSkinTone(skinToneTextField.getText());
    player.setWeight(weightTextField.getText());
    // TODO (Coul Greer): Allow for the setting of all motivation fields.
  }

  private void validateAndSetAge() {
    String age = null;

    do {
      age = JOptionPane.showInputDialog( //
          this, //
          "Please enter a value that is an Integer and is " + Player.MIN_AGE + " or higher.", //
          "Error", //
          JOptionPane.PLAIN_MESSAGE);
    } while (!isValidAgeInput(age));

    player.setAge(Integer.parseInt(age));
  }

  private boolean isValidAgeInput(String age) {
    if (!(age.matches("\\d+"))) {
      return false;
    }

    int number = Integer.parseInt(age);
    return (number >= Player.MIN_AGE);
  }

}
