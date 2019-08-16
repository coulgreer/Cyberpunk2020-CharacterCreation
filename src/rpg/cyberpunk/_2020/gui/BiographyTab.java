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
import java.util.ArrayList;
import java.util.List;
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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import rpg.Gender;
import rpg.cyberpunk._2020.Age;
import rpg.cyberpunk._2020.Player;
import rpg.cyberpunk._2020.Sibling;
import rpg.cyberpunk._2020.Sibling.RelativeAge;
import rpg.cyberpunk._2020.gui.stats.AttributePane;
import rpg.cyberpunk._2020.stats.CyberpunkAttribute;
import rpg.util.Measurement;
import rpg.util.Name;

/**
 * A panel that displays the data that makes up a player's background and main attributes.
 */
public class BiographyTab extends JPanel {
  private static final int CATEGORY_PADDING = 12;
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
  private JTable lifeEventTable;
  private JTable siblingTable;
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

    initializeDataComponents();
    JComponent c = createContentComponent();
    c.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
    add(c, BorderLayout.CENTER);
    add(createUpdateComponent(), BorderLayout.SOUTH);
  }

  private void setPlayer(Player player) {
    if (player == null) {
      throw new NullPointerException();
    } else {
      this.player = player;
    }
  }

  // TODO (Coul Greer): Sync the essential data with the results of creating a new character.
  private void initializeDataComponents() {
    aliasTextField = new JTextField(player.getAlias().getValue());
    ageTextField = new JTextField(Integer.toString( //
        player.getAge() //
            .toInt()));
    genderComboBox = new JComboBox<Gender>(Gender.values());

    eyeTextField = new JTextField("Eyes Placeholder");
    heightTextField = new JTextField("Height Placeholder");
    hairTextField = new JTextField("Hair Placeholder");
    skinToneTextField = new JTextField("Skin Placeholder");
    weightTextField = new JTextField("Weight Placeholder");

    personalityComboBox = new JComboBox<String>(new String[] { //
        "Shy and secretive", //
        "Rebellious, antisocial, violent", //
        "Arrogant, proud and aloof", //
        "Moody, rash and headstrong", //
        "Picky, fussy and nervous", //
        "Stable and serious", //
        "Silly and fluffheaded", //
        "Sneaky and deceptive", //
        "Intellectual and detached", //
        "Friendly and outgoing"});
    valuedPersonComboBox = new JComboBox<String>(new String[] { //
        "A parent", //
        "Brother or sister", //
        "Lover", //
        "Friend", //
        "Yourself", //
        "A pet", //
        "Teacher or mentor", //
        "Public figure", //
        "A personal hero", //
        "No one"});
    valuedConceptComboBox = new JComboBox<String>(new String[] { //
        "Money", //
        "Honor", //
        "Your word", //
        "Honestly", //
        "Knowledge", //
        "Vengeance", //
        "Love", //
        "Power", //
        "Having a good time", //
        "Friendship"});
    feelingsTowardOthersComboBox = new JComboBox<String>(new String[] { //
        "Neutral", //
        "I like almost everyone", //
        "I hate almost everyone", //
        "People are tools", //
        "Everyone is a valuable individual", //
        "People are obstacles to be destroyed", //
        "People are untrustworthy", //
        "Wipe 'em all out", //
        "People are wonderful"});
    valuedPosessionComboBox = new JComboBox<String>(new String[] { //
        "A weapon", //
        "A tool", //
        "A piece of clothing", //
        "A photograph", //
        "A book or diary", //
        "A recording", //
        "A musical instrument", //
        "A piece of jewelry", //
        "A toy", //
        "A letter"});

    backstoryTextArea = new JTextArea(10, 0);
    backstoryTextArea.setLineWrap(true);
    backstoryTextArea.setWrapStyleWord(true);
  }

  private JComponent createContentComponent() {
    JPanel panel = new JPanel(new GridLayout(0, 2));
    int paddingWidth = 3;
    int dividerWidth = 1;

    JComponent c = createEssentialInfoComponent();
    c.setBorder(BorderFactory.createCompoundBorder( //
        BorderFactory.createMatteBorder(0, 0, 0, dividerWidth, Color.BLACK), //
        BorderFactory.createEmptyBorder(0, 0, 0, paddingWidth)));
    panel.add(c);

    JScrollPane scrollPane = new JScrollPane(createOptionalInfoComponent());
    scrollPane.setBorder(BorderFactory.createCompoundBorder( //
        BorderFactory.createMatteBorder(0, dividerWidth, 0, 0, Color.BLACK), //
        BorderFactory.createEmptyBorder(0, paddingWidth, 0, 0)));
    panel.add(scrollPane);

    return panel;
  }

  private JComponent createEssentialInfoComponent() {
    JPanel panel = new JPanel(new GridLayout(2, 0));

    JComponent c = createEssentialBiographyComponent();
    c.setBorder(BorderFactory.createEmptyBorder(0, 0, CATEGORY_PADDING, 0));
    panel.add(c);
    panel.add(createCategoryComponent("ATTRIBUTES", createAttributeComponent()));

    return panel;
  }

  private JComponent createEssentialBiographyComponent() {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add(createPortraitComponent(), BorderLayout.CENTER);
    panel.add(createEssentialTextInfoComponent(), BorderLayout.SOUTH);

    return panel;
  }

  private Component createPortraitComponent() {
    JPanel panel = new JPanel();

    // TODO (Coul Greer): Add the ability for user to provide a picture as well as a default picture
    // if one was not provided. Also, use a custom border to beautify the portrait.
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

  private Component createEssentialTextInfoComponent() {
    int paddingWidth = 3;
    int dividerWidth = 1;
    JPanel panel = new JPanel(new GridLayout(1, 0));
    panel.setBorder(BorderFactory.createEmptyBorder( //
        paddingWidth, //
        paddingWidth, //
        paddingWidth, //
        paddingWidth));

    JComponent c = createTitledInfoComponent( //
        "Alias", //
        aliasTextField);
    c.setBorder(BorderFactory.createCompoundBorder( //
        BorderFactory.createMatteBorder(0, 0, 0, dividerWidth, Color.BLACK), //
        BorderFactory.createEmptyBorder(paddingWidth, paddingWidth, paddingWidth, paddingWidth)));
    panel.add(c);

    c = createTitledInfoComponent( //
        "Age", //
        ageTextField);
    c.setBorder(BorderFactory.createCompoundBorder( //
        BorderFactory.createMatteBorder(0, 0, 0, dividerWidth, Color.BLACK), //
        BorderFactory.createEmptyBorder(paddingWidth, paddingWidth, paddingWidth, paddingWidth)));
    panel.add(c);

    c = createTitledInfoComponent( //
        "Gender", //
        genderComboBox);
    c.setBorder(
        BorderFactory.createEmptyBorder(paddingWidth, paddingWidth, paddingWidth, paddingWidth));
    panel.add(c);

    return panel;
  }

  private JComponent createTitledInfoComponent(String title, Component dataComponent) {
    JPanel panel = new JPanel(new BorderLayout());

    JLabel titleLabel = new JLabel(title, SwingConstants.LEFT);
    titleLabel.setFont(new Font("Serif", Font.PLAIN, 14));
    panel.add(titleLabel, BorderLayout.NORTH);

    dataComponent.setFont(new Font("Serif", Font.PLAIN, 20));
    panel.add(dataComponent, BorderLayout.CENTER);

    return panel;
  }

  private JComponent createCategoryComponent(String title, Component dataComponent) {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add(createCategoryTitleComponent(title), BorderLayout.NORTH);
    panel.add(dataComponent, BorderLayout.CENTER);

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
    int gridBorderWidth = 2;
    int paddingWidth = 6;
    Font font = new Font("Serif", Font.PLAIN, 16);

    // Row 1
    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.INTELLIGENCE), //
        font, //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createCompoundBorder( //
        BorderFactory.createMatteBorder(0, 0, 0, gridBorderWidth, Color.BLACK), //
        BorderFactory.createEmptyBorder(paddingWidth, paddingWidth, paddingWidth, paddingWidth)));
    panel.add(c);

    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.REFLEXES), //
        font, //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createCompoundBorder( //
        BorderFactory.createMatteBorder(0, 0, 0, gridBorderWidth, Color.BLACK), //
        BorderFactory.createEmptyBorder(paddingWidth, paddingWidth, paddingWidth, paddingWidth)));
    panel.add(c);

    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.TECHNICAL_ABILITY), //
        font, //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createCompoundBorder( //
        BorderFactory.createMatteBorder(0, 0, 0, gridBorderWidth, Color.BLACK), //
        BorderFactory.createEmptyBorder(paddingWidth, paddingWidth, paddingWidth, paddingWidth)));
    panel.add(c);

    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.COOL), //
        font, //
        BorderLayout.WEST);
    c.setBorder(
        BorderFactory.createEmptyBorder(paddingWidth, paddingWidth, paddingWidth, paddingWidth));
    panel.add(c);

    // Row 2
    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.ATTRACTIVENESS), //
        font, //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createCompoundBorder( //
        BorderFactory.createMatteBorder(gridBorderWidth, 0, 0, gridBorderWidth, Color.BLACK), //
        BorderFactory.createEmptyBorder(paddingWidth, paddingWidth, paddingWidth, paddingWidth)));
    panel.add(c);

    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.LUCK), //
        font, //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createCompoundBorder( //
        BorderFactory.createMatteBorder(gridBorderWidth, 0, 0, gridBorderWidth, Color.BLACK), //
        BorderFactory.createEmptyBorder(paddingWidth, paddingWidth, paddingWidth, paddingWidth)));
    panel.add(c);

    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.MOVEMENT_ALLOWANCE), //
        font, //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createCompoundBorder( //
        BorderFactory.createMatteBorder(gridBorderWidth, 0, 0, gridBorderWidth, Color.BLACK), //
        BorderFactory.createEmptyBorder(paddingWidth, paddingWidth, paddingWidth, paddingWidth)));
    panel.add(c);

    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.BODY_TYPE), //
        font, //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createCompoundBorder( //
        BorderFactory.createMatteBorder(gridBorderWidth, 0, 0, 0, Color.BLACK), //
        BorderFactory.createEmptyBorder(paddingWidth, paddingWidth, paddingWidth, paddingWidth)));
    panel.add(c);

    // Row 3
    c = new AttributePane.Spinner( //
        player.getAttribute(CyberpunkAttribute.EMPATHY), //
        font, //
        BorderLayout.WEST);
    c.setBorder(BorderFactory.createCompoundBorder( //
        BorderFactory.createMatteBorder(gridBorderWidth, 0, 0, gridBorderWidth, Color.BLACK), //
        BorderFactory.createEmptyBorder(paddingWidth, paddingWidth, paddingWidth, paddingWidth)));
    panel.add(c);

    c = new MeasurementPane( //
        player.getAttribute(CyberpunkAttribute.RUN), //
        Measurement.Type.LENGTH, //
        Measurement.Unit.METER);
    c.setBorder(BorderFactory.createCompoundBorder( //
        BorderFactory.createMatteBorder(gridBorderWidth, 0, 0, gridBorderWidth, Color.BLACK), //
        BorderFactory.createEmptyBorder(paddingWidth, paddingWidth, paddingWidth, paddingWidth)));
    panel.add(c);

    c = new MeasurementPane( //
        player.getAttribute(CyberpunkAttribute.LEAP), //
        Measurement.Type.LENGTH, //
        Measurement.Unit.METER);
    c.setBorder(BorderFactory.createCompoundBorder( //
        BorderFactory.createMatteBorder(gridBorderWidth, 0, 0, gridBorderWidth, Color.BLACK), //
        BorderFactory.createEmptyBorder(paddingWidth, paddingWidth, paddingWidth, paddingWidth)));
    panel.add(c);

    c = new MeasurementPane( //
        player.getAttribute(CyberpunkAttribute.CARRY), //
        Measurement.Type.MASS, //
        Measurement.Unit.KILOGRAM);
    c.setBorder(BorderFactory.createCompoundBorder( //
        BorderFactory.createMatteBorder(gridBorderWidth, 0, 0, 0, Color.BLACK), //
        BorderFactory.createEmptyBorder(paddingWidth, paddingWidth, paddingWidth, paddingWidth)));
    panel.add(c);

    return panel;
  }

  private Component createOptionalInfoComponent() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    JComponent characteristicsComponent =
        createCategoryComponent("CHARACTERISTICS", createCharacteristicsComponent());
    characteristicsComponent.setBorder(BorderFactory.createCompoundBorder( //
        BorderFactory.createEmptyBorder(6, 3, 6, 3), //
        BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)));
    panel.add(characteristicsComponent);

    JComponent siblingsComponent = createCategoryComponent("SIBLINGS", createSiblingComponent());
    siblingsComponent.setBorder(BorderFactory.createCompoundBorder( //
        BorderFactory.createEmptyBorder(6, 3, 6, 3), //
        BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)));
    panel.add(siblingsComponent);

    JComponent motivationComponent =
        createCategoryComponent("MOTIVATIONS", createMotivationComponent());
    motivationComponent.setBorder(BorderFactory.createCompoundBorder( //
        BorderFactory.createEmptyBorder(6, 3, 6, 3), //
        BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)));
    panel.add(motivationComponent);

    JComponent lifeEventComponent =
        createCategoryComponent("LIFE EVENTS", createLifeEventComponent());
    lifeEventComponent.setBorder(BorderFactory.createCompoundBorder( //
        BorderFactory.createEmptyBorder(6, 3, 6, 3), //
        BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)));
    panel.add(lifeEventComponent);

    JComponent backstoryComponent =
        createCategoryComponent("BACKSTORY", createBackstoryComponent());
    backstoryComponent.setBorder(BorderFactory.createEmptyBorder(0, 3, 12, 3));
    panel.add(backstoryComponent);

    return panel;
  }

  private Component createCharacteristicsComponent() {
    int paddingWidth = 3;
    JPanel panel = new JPanel(new GridLayout(0, 3));
    panel.setBorder(BorderFactory.createEmptyBorder( //
        paddingWidth, //
        paddingWidth, //
        paddingWidth, //
        paddingWidth));

    JComponent c = createTitledInfoComponent("Eyes", eyeTextField);
    c.setBorder(BorderFactory.createEmptyBorder( //
        paddingWidth, //
        paddingWidth, //
        paddingWidth, //
        paddingWidth));
    panel.add(c);

    c = createTitledInfoComponent("Height", heightTextField);
    c.setBorder(BorderFactory.createEmptyBorder( //
        paddingWidth, //
        paddingWidth, //
        paddingWidth, //
        paddingWidth));
    panel.add(c);

    c = createTitledInfoComponent("Hair", hairTextField);
    c.setBorder(BorderFactory.createEmptyBorder( //
        paddingWidth, //
        paddingWidth, //
        paddingWidth, //
        paddingWidth));
    panel.add(c);

    c = createTitledInfoComponent("Skin Tone", skinToneTextField);
    c.setBorder(BorderFactory.createEmptyBorder( //
        paddingWidth, //
        paddingWidth, //
        paddingWidth, //
        paddingWidth));
    panel.add(c);

    c = createTitledInfoComponent("Weight", weightTextField);
    c.setBorder(BorderFactory.createEmptyBorder( //
        paddingWidth, //
        paddingWidth, //
        paddingWidth, //
        paddingWidth));
    panel.add(c);

    return panel;
  }

  private Component createSiblingComponent() {
    JPanel panel = new JPanel(new BorderLayout());

    siblingTable = new SiblingTable(player);
    panel.add(new JScrollPane(siblingTable), BorderLayout.CENTER);
    panel.add(createSiblingButtonComponent(), BorderLayout.SOUTH);

    return panel;
  }

  private Component createSiblingButtonComponent() {
    JPanel panel = new JPanel();

    JButton addButton = new JButton("Add Sibling");
    addButton.addActionListener(evt -> {
      player.addSibling(new Sibling( //
          "Unknown", //
          Gender.MALE, //
          RelativeAge.TWIN, //
          "Unknown"));
    });
    panel.add(addButton);

    JButton removeButton = new JButton("Remove Sibling");
    removeButton.addActionListener(evt -> {
      int rowIndex = siblingTable.getSelectedRow();

      try {
        player.removeSibling(player //
            .getSiblings().get(rowIndex));
      } catch (ArrayIndexOutOfBoundsException ex) {
        JOptionPane.showMessageDialog( //
            this, //
            "You must select an element in order to remove a Sibling.");
      }

    });
    panel.add(removeButton);

    return panel;
  }

  private Component createMotivationComponent() {
    JPanel panel = new JPanel(new GridLayout(0, 1));
    int paddingWidth = 6;

    JComponent c = createTitledInfoComponent("Personality Traits", personalityComboBox);
    c.setBorder(BorderFactory.createEmptyBorder( //
        paddingWidth, //
        paddingWidth, //
        paddingWidth, //
        paddingWidth));
    panel.add(c);

    c = createTitledInfoComponent("Valued Person", valuedPersonComboBox);
    c.setBorder(BorderFactory.createEmptyBorder( //
        paddingWidth, //
        paddingWidth, //
        paddingWidth, //
        paddingWidth));
    panel.add(c);

    c = createTitledInfoComponent("Valued Concept", valuedConceptComboBox);
    c.setBorder(BorderFactory.createEmptyBorder( //
        paddingWidth, //
        paddingWidth, //
        paddingWidth, //
        paddingWidth));
    panel.add(c);

    c = createTitledInfoComponent("Feelings Towards Others", feelingsTowardOthersComboBox);
    c.setBorder(BorderFactory.createEmptyBorder( //
        paddingWidth, //
        paddingWidth, //
        paddingWidth, //
        paddingWidth));
    panel.add(c);

    c = createTitledInfoComponent("Valued Posession", valuedPosessionComboBox);
    c.setBorder(BorderFactory.createEmptyBorder( //
        paddingWidth, //
        paddingWidth, //
        paddingWidth * 2, //
        paddingWidth));
    panel.add(c);

    return panel;
  }

  private Component createLifeEventComponent() {
    lifeEventTable = new LifeEventTable(player);
    JScrollPane scrollPane = new JScrollPane(lifeEventTable);
    return scrollPane;
  }

  private Component createBackstoryComponent() {
    backstoryTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
      player.setAge(new Age(Integer.parseInt(ageTextField.getText())));
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
    updateSiblings();
    player.setPersonalityTrait((String) personalityComboBox.getSelectedItem());
    player.setValuedPerson((String) valuedPersonComboBox.getSelectedItem());
    player.setValuedConcept((String) valuedConceptComboBox.getSelectedItem());
    player.setFeelingsTowardOthers((String) feelingsTowardOthersComboBox.getSelectedItem());
    player.setValuedPosession((String) valuedPosessionComboBox.getSelectedItem());
    updateLifeEvent();
    player.setBackstory(backstoryTextArea.getText());
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

    player.setAge(new Age(Integer.parseInt(age)));
  }

  private boolean isValidAgeInput(String age) {
    if (!(age.matches("\\d+"))) {
      return false;
    }

    int number = Integer.parseInt(age);
    return (number >= Player.MIN_AGE);
  }

  private void updateSiblings() {
    // TODO (Coul Greer): Create renderer for value objects.
    List<Sibling> tempSiblings = new ArrayList<Sibling>(siblingTable.getRowCount());
    for (int i = 0; i < siblingTable.getRowCount(); i++) {
      tempSiblings.add(new Sibling( //
          (String) siblingTable.getValueAt(i, SiblingTable.Model.NAME_INDEX), //
          (Gender) siblingTable.getValueAt(i, SiblingTable.Model.GENDER_INDEX), //
          (RelativeAge) siblingTable.getValueAt(i, SiblingTable.Model.AGE_INDEX), //
          (String) siblingTable.getValueAt(i, SiblingTable.Model.RELATIONSHIP_INDEX)));
    }

    player.clearSiblings();

    for (Sibling s : tempSiblings) {
      player.addSibling(s);
    }
  }

  private void updateLifeEvent() {
    for (int i = 0; i < lifeEventTable.getRowCount(); i++) {
      player.updateEvent( //
          (Age) lifeEventTable.getValueAt(i, LifeEventTable.Model.AGE_INDEX), //
          (String) lifeEventTable.getValueAt(i, LifeEventTable.Model.EVENT_INDEX));
    }
  }

}
