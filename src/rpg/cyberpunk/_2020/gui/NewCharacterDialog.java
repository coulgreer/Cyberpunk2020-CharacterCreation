package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import rpg.Gender;
import rpg.cyberpunk._2020.Age;
import rpg.cyberpunk._2020.Player;
import rpg.cyberpunk._2020.Sibling;
import rpg.cyberpunk._2020.Sibling.RelativeAge;
import rpg.cyberpunk._2020.gui.stats.FastCharacterPointsPane;
import rpg.cyberpunk._2020.gui.stats.RandomCharacterPointsPane;
import rpg.cyberpunk._2020.stats.CyberpunkAttribute;
import rpg.cyberpunk._2020.stats.Role;
import rpg.cyberpunk._2020.stats.RoleFactory;
import rpg.cyberpunk._2020.stats.StatisticFactory;
import rpg.cyberpunk._2020.systems.CharacterPointsManager;
import rpg.cyberpunk._2020.systems.FastCharacterPointsManager;
import rpg.cyberpunk._2020.systems.RandomCharacterPointsManager;
import rpg.general.stats.Attribute;
import rpg.util.Die;
import rpg.util.Name;

/**
 * An instance of JDialog that takes inputs from various cards that allows a <code>Player</code> to
 * set its background info and sets the Player's initial <code>Attribute</code>s.
 */
public class NewCharacterDialog extends JDialog {
  private static final long serialVersionUID = 1L;

  private static final Font TITLE1_FONT = new Font("Serif", Font.PLAIN, 30);
  private static final Font SUBTITLE1_FONT = new Font("Serif", Font.PLAIN, 16);
  private static final Font TITLE2_FONT = new Font("Serif", Font.PLAIN, 18);
  private static final Font SUBTITLE2_FONT = new Font("Serif", Font.PLAIN, 12);

  // Card Names
  private static final String ESSENTIAL_INFO_PANE = "Essential Info Pane";
  private static final String CHARACTER_POINT_PANE = "Character Point Pane";
  private static final String RANDOM_POINT_PANE = "Random Points";
  private static final String FAST_POINT_PANE = "Fast Points";
  private static final String FAMILY_RANKING_PANE = "Family Ranking Pane";
  private static final String PARENT_PANE = "Parent Pane";
  private static final String PARENT_TRAGEDY_PANE = "Parent Tragedy Pane";
  private static final String FAMILY_STATUS_PANE = "Family Status Pane";
  private static final String FAMILY_TRAGEDY_PANE = "Family Tragedy Pane";
  private static final String CHILDHOOD_ENVIRONMENT_PANE = "Childhood Environment Pane";
  private static final String SIBLINGS_PANE = "Siblings Pane";
  private static final String ROLE_PANE = "Role Pane";

  private Player player;
  private JPanel contentPane;

  // Statistics
  private Map<String, CharacterPointsManager> managersByCardName;
  private String activePointManagerName;

  // Essential
  private JLabel portraitLabel;
  private JTextField aliasTextField;
  private JTextField ageTextField;
  private JComboBox<Gender> genderComboBox;

  // Motivations
  private JComboBox<String> familyRankingComboBox;
  private JRadioButton[] parentStatusRadioButtons;
  private JComboBox<String> parentTragedyComboBox;
  private JRadioButton[] familyStatusRadioButtons;
  private JComboBox<String> familyTragedyComboBox;
  private JComboBox<String> childhoodEnvironmentComboBox;

  // Siblings
  private int siblingCount;
  private JRadioButton[] siblingCountRadioButtons;
  private Map<Integer, JPanel> siblingPanelsByInteger;
  private Map<Integer, JTextField> siblingNameTextFieldsByInteger;
  private Map<Integer, JComboBox<Gender>> siblingGenderComboBoxesByInteger;
  private Map<Integer, JComboBox<RelativeAge>> siblingAgeComboBoxesByInteger;
  private Map<Integer, JComboBox<String>> siblingRelationshipComboBoxesByInteger;

  // Roles
  private JComboBox<Role> roleComboBox;
  private Map<Role, List<JComboBox<String>>> comboBoxesByRole;

  /**
   * Constructs a JDialog that allows the manipulation of a <code>Player</code>'s stats and
   * background.
   * 
   * @param owner the Frame from which the dialog is displayed
   * @param title the String to display in the dialog's title bar
   * @param modal specifies whether dialog blocks user input to other top-level windows when shown.
   *        If true, the modality type property is set to DEFAULT_MODALITY_TYPE otherwise the dialog
   *        is modaless
   * @param player the provider of the <code>Statistic</code>s and owner of the user given
   *        background
   */
  public NewCharacterDialog(Frame owner, String title, boolean modal, Player player) {
    super(owner, title, modal);
    setPlayer(player);

    initializeAlias();
    initializeAge();
    initializeGender();
    initializePortrait();
    initializeFamilyRanking();
    initializeParentStatus();
    initializeParentTragedy();
    initializeFamilyStatus();
    initializeFamilyTragedy();
    initializeChildhoodEnvironment();
    initializeSiblings();
    initializeRoles();

    initializeContentPane();
  }

  private void setPlayer(Player player) {
    if (player == null) {
      throw new NullPointerException();
    } else {
      this.player = player;
    }
  }

  private void initializeAlias() {
    aliasTextField = new JTextField("UNKNOWN");
  }

  private void initializeAge() {
    ageTextField = new JTextField(Integer.toString(Player.MIN_AGE));
  }

  private void initializeGender() {
    genderComboBox = new JComboBox<Gender>(Gender.values());
    genderComboBox.addItemListener(evt -> {
      if (evt.getStateChange() == ItemEvent.SELECTED) {
        Gender item = (Gender) evt.getItem();
        player.setGender(item);
      }
    });

    genderComboBox.setSelectedIndex(0);
  }

  private void initializePortrait() {
    portraitLabel = new JLabel("PLACEHOLDER PORTRAIT");
  }

  private void initializeFamilyRanking() {
    familyRankingComboBox = new JComboBox<String>(new String[] { //
        "Corporate Executive", //
        "Corporate Manager", //
        "Corporate Technician", //
        "Nomad Pack", //
        "Pirate Fleet", //
        "Gang Family", //
        "Crime Lord", //
        "Combat Zone Poor", //
        "Urban Homeless", //
        "Arcology Family"});
    familyRankingComboBox.addItemListener(evt -> {
      if (evt.getStateChange() == ItemEvent.SELECTED) {
        String item = (String) evt.getItem();
        player.setFamilyRanking(item);
      }
    });

    familyRankingComboBox.setSelectedIndex(0);
  }

  private void initializeParentStatus() {
    parentStatusRadioButtons = new JRadioButton[2];

    parentStatusRadioButtons[0] = new JRadioButton("Both parents are living.");
    parentStatusRadioButtons[0].setActionCommand(FAMILY_STATUS_PANE);
    parentStatusRadioButtons[0].addActionListener(evt -> {
      player.setParentStatus("Both parents are living.");
      player.setParentTragedy(false);
    });

    parentStatusRadioButtons[1] =
        new JRadioButton("Something has happened to one or both parents.");
    parentStatusRadioButtons[1].setActionCommand(PARENT_TRAGEDY_PANE);
    parentStatusRadioButtons[1].addActionListener(evt -> {
      player.setParentStatus("Something has happened to one or both parents.");
      player.setParentTragedy(true);
    });

    parentStatusRadioButtons[0].setSelected(true);
  }

  private void initializeParentTragedy() {
    parentTragedyComboBox = new JComboBox<String>(new String[] { //
        "Your parent(s) died in warfare.", //
        "Your parent(s) died in an accident.", //
        "Your parent(s) were murdered.", //
        "Your parent(s) have amnesia and don't remember you.", //
        "You never knew your parent(s).", //
        "Your parent(s) are in hiding to protect you.", //
        "You were left with relatives for safekeeping.", //
        "You grew up on the Street and never had parents.", //
        "Your parent(s) gave you up for adoption.", //
        "Your parent(s) sold you for money."});
    parentTragedyComboBox.addItemListener(evt -> {
      if (evt.getStateChange() == ItemEvent.SELECTED) {
        String item = (String) evt.getItem();
        player.setParentTragedy(item);
      }
    });

    parentTragedyComboBox.setSelectedIndex(0);
  }

  private void initializeFamilyStatus() {
    familyStatusRadioButtons = new JRadioButton[2];

    familyStatusRadioButtons[0] = new JRadioButton(
        "Family status in danger, and you risk losing everything (if you haven't already).");
    familyStatusRadioButtons[0].setActionCommand(FAMILY_TRAGEDY_PANE);
    familyStatusRadioButtons[0].addActionListener(evt -> {
      player.setFamilyStatus(
          "Family status in danger, and you risk losing everything (if you haven't already).");
      player.setFamilyTragedy(true);
    });

    familyStatusRadioButtons[1] =
        new JRadioButton("Family status is OK, even if parents are missing or dead.");
    familyStatusRadioButtons[1].setActionCommand(CHILDHOOD_ENVIRONMENT_PANE);
    familyStatusRadioButtons[1].addActionListener(evt -> {
      player.setFamilyStatus("Family status is OK, even if parents are missing or dead.");
      player.setFamilyTragedy(false);
    });

    familyStatusRadioButtons[0].setSelected(true);
  }

  private void initializeFamilyTragedy() {
    familyTragedyComboBox = new JComboBox<String>(new String[] { //
        "Family lost everything through betrayal.", //
        "Family lost everything through bad management.", //
        "Family exiled or otherwise driven from their original home/nation/corporation.", //
        "Family is imprisoned and you alone escaped.", //
        "Family vanished. You are the only remaining member.", //
        "Family was murdered/killed and you were the only survivor.", //
        "Family is involved in a longterm conspiracy, organization or association, such as a crime"
            + " family or revolutionary group.", //
        "Your family was scattered to the winds due to misfortune.", //
        "Your family is cursed with a hereditary feud that has lasted for generations.", //
        "You are the inheritor of a family debt; you must honor this debt before moving on with"
            + " your life."});
    familyTragedyComboBox.addItemListener(evt -> {
      if (evt.getStateChange() == ItemEvent.SELECTED) {
        String item = (String) evt.getItem();
        player.setFamilyTragedy(item);
      }
    });

    familyTragedyComboBox.setSelectedIndex(0);
  }

  private void initializeChildhoodEnvironment() {
    childhoodEnvironmentComboBox = new JComboBox<String>(new String[] { //
        "Spent on the Street, with no adult supervision.", //
        "Spent in a safe Corporate Sububia.", //
        "In a Nomad Pack moving from town to town.", //
        "In a decaying, once upscale neighborhood.", //
        "In a defended Corporate Zone in the central City.", //
        "In the heart of the Combat Zone.", //
        "In a small village or town far from the City.", //
        "In a large arcoloty city.", //
        "In an aquatic Pirate Pack.", //
        "In a Corporate controlled Farm or Research Facility."});
    childhoodEnvironmentComboBox.addItemListener(evt -> {
      if (evt.getStateChange() == ItemEvent.SELECTED) {
        String item = (String) evt.getItem();
        player.setChildhoodEnvironment(item);
      }
    });

    childhoodEnvironmentComboBox.setSelectedIndex(0);
  }

  private void initializeSiblings() {
    siblingCount = 0;
    initializeSiblingsPanels();
    initializeSiblingsRadioButtons();
  }

  private void initializeSiblingsPanels() {
    siblingPanelsByInteger = new HashMap<Integer, JPanel>();
    siblingNameTextFieldsByInteger = new HashMap<Integer, JTextField>();
    siblingGenderComboBoxesByInteger = new HashMap<Integer, JComboBox<Gender>>();
    siblingAgeComboBoxesByInteger = new HashMap<Integer, JComboBox<RelativeAge>>();
    siblingRelationshipComboBoxesByInteger = new HashMap<Integer, JComboBox<String>>();

    for (int i = 0; i < 5; i++) {
      final Integer key = new Integer(i);
      JPanel p = new JPanel();

      JTextField nameTextField = new JTextField("UNKNOWN");
      p.add(createNameComponent(nameTextField));

      Gender[] genderOptions = Gender.values();
      JComboBox<Gender> genderComboBox = new JComboBox<Gender>(genderOptions);
      p.add(createGenderComponent(genderComboBox));

      RelativeAge[] ageOptions = RelativeAge.values();
      JComboBox<RelativeAge> ageComboBox = new JComboBox<RelativeAge>(ageOptions);
      p.add(createAgeComponent(ageComboBox));

      JComboBox<String> relationshipComboBox = new JComboBox<String>(new String[] { //
          "Sibling dislikes you.", //
          "Sibling likes you.", //
          "Sibling neutral.", //
          "They worship you.", //
          "They hate you."});
      p.add(createRelationshipComponent(relationshipComboBox));

      siblingPanelsByInteger.put(key, p);
      siblingNameTextFieldsByInteger.put(key, nameTextField);
      siblingGenderComboBoxesByInteger.put(key, genderComboBox);
      siblingAgeComboBoxesByInteger.put(key, ageComboBox);
      siblingRelationshipComboBoxesByInteger.put(key, relationshipComboBox);
    }
  }

  private JComponent createNameComponent(JTextField textField) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    panel.add( //
        createTitleComponent( //
            "Name", TITLE2_FONT, //
            null, null));
    panel.add(textField);

    return panel;
  }

  private JComponent createGenderComponent(JComboBox<Gender> comboBox) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    panel.add( //
        createTitleComponent( //
            "Sex", TITLE2_FONT, //
            "Roll or choose one.", SUBTITLE2_FONT));
    panel.add(comboBox);

    return panel;
  }

  private JComponent createAgeComponent(JComboBox<RelativeAge> comboBox) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    panel.add( //
        createTitleComponent( //
            "Relative Age", TITLE2_FONT, //
            "Roll or choose one.", SUBTITLE2_FONT));
    panel.add(comboBox);

    return panel;
  }

  private JComponent createRelationshipComponent(JComboBox<String> comboBox) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    panel.add( //
        createTitleComponent( //
            "Relationship", TITLE2_FONT, //
            "Roll or choose one.", SUBTITLE2_FONT));
    panel.add(comboBox);

    return panel;
  }

  private void initializeSiblingsRadioButtons() {
    siblingCountRadioButtons = new JRadioButton[8];

    siblingCountRadioButtons[0] = new JRadioButton("1 Sibling");
    siblingCountRadioButtons[0].addItemListener(evt -> setSiblingCount(1));

    siblingCountRadioButtons[1] = new JRadioButton("2 Siblings");
    siblingCountRadioButtons[1].addItemListener(evt -> setSiblingCount(2));

    siblingCountRadioButtons[2] = new JRadioButton("3 Siblings");
    siblingCountRadioButtons[2].addItemListener(evt -> setSiblingCount(3));

    siblingCountRadioButtons[3] = new JRadioButton("4 Siblings");
    siblingCountRadioButtons[3].addItemListener(evt -> setSiblingCount(4));

    siblingCountRadioButtons[4] = new JRadioButton("5 Siblings");
    siblingCountRadioButtons[4].addItemListener(evt -> setSiblingCount(5));

    siblingCountRadioButtons[5] = new JRadioButton("6 Siblings");
    siblingCountRadioButtons[5].addItemListener(evt -> setSiblingCount(6));

    siblingCountRadioButtons[6] = new JRadioButton("7 Siblings");
    siblingCountRadioButtons[6].addItemListener(evt -> setSiblingCount(7));

    siblingCountRadioButtons[7] = new JRadioButton("Only Child");
    siblingCountRadioButtons[7].addItemListener(evt -> setSiblingCount(0));

    siblingCountRadioButtons[0].setSelected(true);
  }

  private void setSiblingCount(int count) {
    siblingCount = count;

    for (int i = 0; i < siblingPanelsByInteger.size(); i++) {
      JPanel panel = siblingPanelsByInteger.get(new Integer(i));

      if (i < count) {
        panel.setVisible(true);
      } else {
        panel.setVisible(false);
      }
    }
  }

  private void initializeRoles() {
    Iterator<String> nameIterator = RoleFactory.createNameIterator();

    ArrayList<Role> roles = new ArrayList<Role>();
    comboBoxesByRole = new HashMap<Role, List<JComboBox<String>>>();
    while (nameIterator.hasNext()) {
      Role r = RoleFactory.createRole(nameIterator.next());
      roles.add(r);
      initializeRoleSkills(r);
    }

    roleComboBox = new JComboBox<Role>(roles.toArray(new Role[0]));
    roleComboBox.addActionListener(evt -> player.setRole((Role) roleComboBox.getSelectedItem()));
    roleComboBox.setRenderer(new ListCellRenderer<Role>() {

      @Override
      public Component getListCellRendererComponent( //
          JList<? extends Role> list, //
          Role value, //
          int index, //
          boolean isSelected, //
          boolean cellHasFocus) {

        JLabel label = new JLabel(value.getName());
        label.setOpaque(true);
        if (isSelected) {
          label.setBackground(list.getSelectionBackground());
          label.setForeground(list.getSelectionForeground());
        } else {
          label.setBackground(list.getBackground());
          label.setForeground(list.getForeground());
        }

        return label;
      }

    });

    roleComboBox.setSelectedIndex(0);
  }

  private void initializeRoleSkills(Role role) {
    ArrayList<JComboBox<String>> comboBoxes = new ArrayList<JComboBox<String>>(Role.OPTION_COUNT);

    for (List<String> options : role.getSkillNameOptions()) {
      JComboBox<String> cb = new JComboBox<String>(options.toArray(new String[0]));
      cb.setEnabled(options.size() > 1);
      comboBoxes.add(cb);
    }

    comboBoxesByRole.put(role, comboBoxes);
  }

  private void initializeContentPane() {
    contentPane = new JPanel(new CardLayout());

    contentPane.add(createEssentialInfoContainer(), ESSENTIAL_INFO_PANE);
    contentPane.add(createCharacterPointContainer(), CHARACTER_POINT_PANE);
    contentPane.add(createFamilyRankingContainer(), FAMILY_RANKING_PANE);
    contentPane.add(createParentStatusContainer(), PARENT_PANE);
    contentPane.add(createParentTragedyContainer(), PARENT_TRAGEDY_PANE);
    contentPane.add(createFamilyStatusContainer(), FAMILY_STATUS_PANE);
    contentPane.add(createFamilyTragedyContainer(), FAMILY_TRAGEDY_PANE);
    contentPane.add(createChildhoodEnvironmentContainer(), CHILDHOOD_ENVIRONMENT_PANE);
    contentPane.add(createSiblingsContainer(), SIBLINGS_PANE);
    contentPane.add(createRoleContainer(), ROLE_PANE);

    setContentPane(contentPane);
  }

  private JComponent createEssentialInfoContainer() {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add( //
        createTitleComponent( //
            "ESSENTIAL INFO", TITLE1_FONT, //
            "Provide the basic info.", SUBTITLE1_FONT), //
        BorderLayout.NORTH);

    panel.add(createEssentialInfoComponent(), BorderLayout.CENTER);

    panel.add( //
        createNavigationComponent( //
            "<<= Previous", null, //
            "Next =>>", evt -> {
              if (!hasValidAlias()) {
                JOptionPane.showMessageDialog( //
                    this, //
                    "Make sure the alias is at least one character long.");
              } else if (!hasValidAge()) {
                JOptionPane.showMessageDialog( //
                    this, //
                    "Make sure the age is a number over " + Player.MIN_AGE);
              } else {
                switchCardTo(FAMILY_RANKING_PANE);
              }
            }),
        BorderLayout.SOUTH);

    return panel;
  }

  private Component createTitleComponent( //
      String title, Font titleFont, //
      String subtitle, Font subtitleFont) {

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    JLabel titleLabel = new JLabel(title);
    titleLabel.setFont(titleFont);
    titleLabel.setAlignmentX(CENTER_ALIGNMENT);
    panel.add(titleLabel);

    JLabel subtitleLabel = new JLabel(subtitle);
    subtitleLabel.setFont(subtitleFont);
    subtitleLabel.setAlignmentX(CENTER_ALIGNMENT);
    panel.add(subtitleLabel);

    return panel;
  }

  private JComponent createEssentialInfoComponent() {
    JPanel panel = new JPanel(new GridLayout(0, 2));

    panel.add(createPortraitComponent());
    panel.add(createEssentialTextInfoComponent());

    return panel;
  }

  private JComponent createPortraitComponent() {
    JPanel panel = new JPanel(new GridBagLayout());

    panel.add(portraitLabel);

    return panel;
  }

  private JComponent createEssentialTextInfoComponent() {
    int paddingWidth = 6;
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBorder(BorderFactory.createEmptyBorder( //
        paddingWidth, //
        paddingWidth, //
        paddingWidth, //
        paddingWidth));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;

    gbc.gridx = 0;
    gbc.gridy = 0;
    JComponent c = createLabeledComponent("Alias", aliasTextField);
    c.setBorder(BorderFactory.createEmptyBorder( //
        paddingWidth, //
        paddingWidth, //
        paddingWidth, //
        paddingWidth));
    panel.add(c, gbc);

    gbc.gridy = 1;
    c = createLabeledComponent("Age", ageTextField);
    c.setBorder(BorderFactory.createEmptyBorder( //
        paddingWidth, //
        paddingWidth, //
        paddingWidth, //
        paddingWidth));
    panel.add(c, gbc);

    gbc.gridy = 2;
    c = createLabeledComponent("Gender", genderComboBox);
    c.setBorder(BorderFactory.createEmptyBorder( //
        paddingWidth, //
        paddingWidth, //
        paddingWidth, //
        paddingWidth));
    panel.add(c, gbc);

    return panel;
  }

  private JComponent createLabeledComponent(String title, JComponent component) {
    JPanel panel = new JPanel(new BorderLayout());

    JLabel label = new JLabel(title);
    label.setFont(TITLE2_FONT);
    panel.add(label, BorderLayout.NORTH);

    component.setFont(TITLE2_FONT);
    panel.add(component, BorderLayout.CENTER);

    return panel;
  }

  private boolean hasValidAlias() {
    String txt = aliasTextField.getText();
    return txt.length() >= Name.MIN_NAME_LENGTH;
  }

  private boolean hasValidAge() {
    String txt = ageTextField.getText();

    if (!txt.matches("\\d+")) {
      return false;
    } else if (Player.MIN_AGE > Integer.parseInt(txt)) {
      return false;
    } else {
      return true;
    }
  }

  private JComponent createNavigationComponent(Action prevAction, Action nextAction) {
    JPanel panel = new JPanel();

    panel.add(new JButton(prevAction));
    panel.add(new JButton(nextAction));

    return panel;
  }

  private JComponent createNavigationComponent( //
      String prevText, ActionListener prevListener, //
      String nextText, ActionListener nextListener) {

    JPanel panel = new JPanel();

    panel.add(createNavigationButton(prevText, prevListener));
    panel.add(createNavigationButton(nextText, nextListener));

    return panel;
  }

  private JButton createNavigationButton(String text, ActionListener listener) {
    JButton button = new JButton(text);

    if (listener == null) {
      button.setEnabled(false);
    } else {
      button.addActionListener(listener);
    }

    return button;
  }

  private Container createCharacterPointContainer() {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add( //
        createTitleComponent( //
            "CHARACTER POINTS (CP)", TITLE1_FONT, //
            "Select a method of acquiring CP.", SUBTITLE1_FONT), //
        BorderLayout.NORTH);

    panel.add(createCharacterPointContent(), BorderLayout.CENTER);

    panel.add( //
        createNavigationComponent( //
            "<<= Previous", evt -> switchCardTo(ESSENTIAL_INFO_PANE), //
            "Next =>>", evt -> {
              CharacterPointsManager manager = managersByCardName.get(activePointManagerName);
              if (!manager.isValid()) {
                JOptionPane.showMessageDialog( //
                    contentPane, //
                    "Make sure all points are appropriately spent.");
              } else {
                switchCardTo(FAMILY_RANKING_PANE);
              }
            }),
        BorderLayout.SOUTH);

    return panel;
  }

  private Component createCharacterPointContent() {
    JPanel panel = new JPanel(new BorderLayout());

    Container cards = createMethodCards();
    panel.add(createMethodSelectionComponent(cards), BorderLayout.NORTH);
    panel.add(cards, BorderLayout.CENTER);

    return panel;
  }

  private Container createMethodCards() {
    JPanel cards = new JPanel(new CardLayout());

    managersByCardName = new HashMap<String, CharacterPointsManager>();
    Iterable<Attribute> iterable =
        () -> player.createAttributeByTypeIterator(StatisticFactory.INDEPENDENT_ATTRIBUTE);
    List<Attribute> attributes = StreamSupport.stream(iterable.spliterator(), false) //
        .collect(Collectors.toList());
    cards.add(createRandomPointManager(attributes), RANDOM_POINT_PANE);
    cards.add(createFastPointManager(attributes), FAST_POINT_PANE);
    // TODO (Coul Greer): Create a component that allows the user to assign character points using
    // the cinematic method.

    return cards;
  }

  private Component createRandomPointManager(List<Attribute> attributes) {
    RandomCharacterPointsManager manager = new RandomCharacterPointsManager(attributes);
    JPanel panel = new RandomCharacterPointsPane(manager);

    managersByCardName.put(RANDOM_POINT_PANE, manager);

    return panel;
  }

  private Component createFastPointManager(List<Attribute> attributes) {
    FastCharacterPointsManager manager = new FastCharacterPointsManager(attributes);
    JPanel panel = new FastCharacterPointsPane(manager);

    managersByCardName.put(FAST_POINT_PANE, manager);

    return panel;
  }

  private JComponent createMethodSelectionComponent(Container container) {
    JPanel panel = new JPanel();

    JComboBox<String> comboBox = new JComboBox<String>(new String[] { //
        RANDOM_POINT_PANE, //
        FAST_POINT_PANE});
    comboBox.addItemListener(evt -> {
      if (evt.getStateChange() == ItemEvent.SELECTED) {
        activePointManagerName = (String) evt.getItem();
        CardLayout layout = (CardLayout) container.getLayout();
        layout.show(container, activePointManagerName);

        if (FAST_POINT_PANE.equals(activePointManagerName)) {
          managersByCardName.get(activePointManagerName) //
              .rollPoints(new Die( //
                  StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT, //
                  10));
        } else if (RANDOM_POINT_PANE.equals(activePointManagerName)) {
          managersByCardName.get(activePointManagerName) //
              .rollPoints( //
                  CyberpunkAttribute.MIN_LEVEL * StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT, //
                  CyberpunkAttribute.MAX_LEVEL * StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT);
        }

      }
    });

    // Forces the item listener to be fired.
    comboBox.setSelectedItem(FAST_POINT_PANE);
    comboBox.setSelectedItem(RANDOM_POINT_PANE);
    panel.add(comboBox);

    return panel;
  }

  private Container createFamilyRankingContainer() {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add( //
        createTitleComponent( //
            "FAMILY RANKING", TITLE1_FONT, //
            "Choose one or roll.", SUBTITLE1_FONT), //
        BorderLayout.NORTH);

    panel.add(createFamilyRankingComponent(), BorderLayout.CENTER);

    panel.add( //
        createNavigationComponent( //
            new SwitchToCardAction( //
                "<<= Previous", null, //
                null, null, //
                contentPane, CHARACTER_POINT_PANE), //
            new SwitchToCardAction( //
                "Next =>>", null, //
                null, null, //
                contentPane, PARENT_PANE)), //
        BorderLayout.SOUTH);

    return panel;
  }

  private JComponent createFamilyRankingComponent() {
    JPanel panel = new JPanel();

    panel.add(familyRankingComboBox);

    JButton randomButton = new JButton("Roll");
    randomButton.addActionListener(evt -> selectRandomItemFrom(familyRankingComboBox));
    panel.add(randomButton);

    return panel;
  }

  private void selectRandomItemFrom(JComboBox<String> comboBox) {
    int randomNum = ThreadLocalRandom.current().nextInt(0, comboBox.getItemCount());
    comboBox.setSelectedIndex(randomNum);
  }

  private Container createParentStatusContainer() {
    JPanel panel = new JPanel(new BorderLayout());

    ButtonGroup group = new ButtonGroup();
    for (JRadioButton rb : parentStatusRadioButtons) {
      group.add(rb);
    }

    panel.add( //
        createTitleComponent( //
            "PARENTS", TITLE1_FONT, //
            "Choose one or roll.", SUBTITLE1_FONT), //
        BorderLayout.NORTH);

    panel.add(createParentStatusComponent(), BorderLayout.CENTER);

    panel.add( //
        createNavigationComponent( //
            "<<= Previous", evt -> switchCardTo(FAMILY_RANKING_PANE), //
            "Next =>>", evt -> switchCardTo(group)), //
        BorderLayout.SOUTH);

    return panel;
  }

  private JComponent createParentStatusComponent() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    panel.add(createRadioButtonContainer(parentStatusRadioButtons));

    JButton button = new JButton("Roll");
    button.addActionListener(evt -> {
      int randomNum = ThreadLocalRandom.current().nextInt(0, 10);

      if (0 <= randomNum && randomNum <= 6) {
        parentStatusRadioButtons[0].setSelected(true);
      } else if (7 <= randomNum && randomNum <= 9) {
        parentStatusRadioButtons[1].setSelected(true);
      }
    });
    button.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    panel.add(button);

    return panel;
  }

  private Container createRadioButtonContainer(JRadioButton[] radioButtons) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    for (JRadioButton rb : radioButtons) {
      panel.add(rb);
      rb.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    }

    return panel;
  }

  private void switchCardTo(String cardName) {
    CardLayout layout = (CardLayout) contentPane.getLayout();
    layout.show(contentPane, cardName);
  }

  private void switchCardTo(ButtonGroup group) {
    CardLayout layout = (CardLayout) contentPane.getLayout();
    String actionCommand = group.getSelection().getActionCommand();
    layout.show(contentPane, actionCommand);
  }

  private Container createParentTragedyContainer() {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add( //
        createTitleComponent( //
            "PARENT TRAGEDY", TITLE1_FONT, //
            "Choose one or roll.", SUBTITLE1_FONT), //
        BorderLayout.NORTH);

    panel.add(createParentTragedyComponent(), BorderLayout.CENTER);

    panel.add( //
        createNavigationComponent( //
            new SwitchToCardAction( //
                "<<= Previous", null, //
                null, null, //
                contentPane, PARENT_PANE), //
            new SwitchToCardAction( //
                "Next =>>", null, //
                null, null, //
                contentPane, FAMILY_STATUS_PANE)),
        BorderLayout.SOUTH);

    return panel;
  }

  private JComponent createParentTragedyComponent() {
    JPanel panel = new JPanel();

    panel.add(parentTragedyComboBox);

    JButton button = new JButton("Roll");
    button.addActionListener(evt -> selectRandomItemFrom(parentTragedyComboBox));
    panel.add(button);

    return panel;
  }

  private Container createFamilyStatusContainer() {
    JPanel panel = new JPanel(new BorderLayout());

    ButtonGroup group = new ButtonGroup();
    for (JRadioButton rb : familyStatusRadioButtons) {
      group.add(rb);
    }

    panel.add( //
        createTitleComponent( //
            "FAMILY STATUS", TITLE1_FONT, //
            "Choose one or roll.", SUBTITLE1_FONT), //
        BorderLayout.NORTH);

    panel.add(createFamilyStatusComponent(), BorderLayout.CENTER);

    panel.add( //
        createNavigationComponent( //
            "<<= Previous", evt -> switchCardTo(PARENT_PANE), //
            "Next =>>", evt -> switchCardTo(group)), //
        BorderLayout.SOUTH);

    return panel;
  }

  private JComponent createFamilyStatusComponent() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    panel.add(createRadioButtonContainer(familyStatusRadioButtons));

    JButton button = new JButton("Roll");
    button.addActionListener(evt -> {
      int randomNum = ThreadLocalRandom.current().nextInt(0, 10);

      if (0 <= randomNum && randomNum <= 6) {
        familyStatusRadioButtons[0].setSelected(true);
      } else if (7 <= randomNum && randomNum <= 9) {
        familyStatusRadioButtons[1].setSelected(true);
      }
    });
    button.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    panel.add(button);

    return panel;
  }

  private Container createFamilyTragedyContainer() {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add( //
        createTitleComponent( //
            "FAMILY TRAGEDY", TITLE1_FONT, //
            "Choose one or roll.", SUBTITLE1_FONT), //
        BorderLayout.NORTH);

    panel.add(createFamilyTragedyComponent(), BorderLayout.CENTER);

    panel.add( //
        createNavigationComponent( //
            new SwitchToCardAction( //
                "<<= Previous", null, //
                null, null, //
                contentPane, FAMILY_STATUS_PANE),
            new SwitchToCardAction( //
                "Next =>>", null, //
                null, null, //
                contentPane, CHILDHOOD_ENVIRONMENT_PANE)), //
        BorderLayout.SOUTH);

    return panel;
  }

  private JComponent createFamilyTragedyComponent() {
    JPanel panel = new JPanel();

    panel.add(familyTragedyComboBox);

    JButton randomButton = new JButton("Roll");
    randomButton.addActionListener(evt -> selectRandomItemFrom(familyTragedyComboBox));
    panel.add(randomButton);

    return panel;
  }

  private Container createChildhoodEnvironmentContainer() {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add( //
        createTitleComponent( //
            "CHILDHOOD ENVIRONMENT", TITLE1_FONT, //
            "Your Childhood was (choose or roll one):", SUBTITLE1_FONT), //
        BorderLayout.NORTH);

    panel.add(createChildhoodEnvironmentComponent(), BorderLayout.CENTER);

    panel.add( //
        createNavigationComponent( //
            new SwitchToCardAction( //
                "<<= Previous", null, //
                null, null, //
                contentPane, FAMILY_STATUS_PANE), //
            new SwitchToCardAction( //
                "Next =>>", null, //
                null, null, //
                contentPane, SIBLINGS_PANE)), //
        BorderLayout.SOUTH);

    return panel;
  }

  private JComponent createChildhoodEnvironmentComponent() {
    JPanel panel = new JPanel();


    panel.add(childhoodEnvironmentComboBox);

    JButton button = new JButton("Roll");
    button.addActionListener(evt -> selectRandomItemFrom(childhoodEnvironmentComboBox));
    panel.add(button);

    return panel;
  }

  private Container createSiblingsContainer() {
    JPanel panel = new JPanel(new BorderLayout());

    ButtonGroup group = new ButtonGroup();
    for (JRadioButton rb : siblingCountRadioButtons) {
      group.add(rb);
    }

    panel.add( //
        createTitleComponent( //
            "SIBLINGS", TITLE1_FONT, //
            "You may have up to 7 brothers/sisters.", SUBTITLE1_FONT), //
        BorderLayout.NORTH);

    panel.add(createSiblingsComponent(), BorderLayout.CENTER);

    panel.add( //
        createNavigationComponent( //
            "<<= Previous", evt -> switchCardTo(CHILDHOOD_ENVIRONMENT_PANE), //
            "Next =>>", evt -> {
              if (!hasValidSiblingNames()) {
                JOptionPane.showMessageDialog( //
                    this, //
                    "Make sure that all siblings have a name.", //
                    "Invalid Sibling Present!", //
                    JOptionPane.ERROR_MESSAGE);
              } else {
                switchCardTo(ROLE_PANE);
              }
            }), //
        BorderLayout.SOUTH);

    return panel;
  }

  private JComponent createSiblingsComponent() {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add(createRadioButtonContainer(siblingCountRadioButtons), BorderLayout.NORTH);
    panel.add(createSiblingForumContainer(), BorderLayout.CENTER);

    return panel;
  }

  private Container createSiblingForumContainer() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    for (JPanel p : siblingPanelsByInteger.values()) {
      panel.add(p);
    }

    return new JScrollPane(panel);
  }

  private boolean hasValidSiblingNames() {
    for (int i = 0; i < siblingCount; i++) {
      String txt = siblingNameTextFieldsByInteger //
          .get(new Integer(i)) //
          .getText();

      if (txt.length() < Name.MIN_NAME_LENGTH) {
        return false;
      }
    }

    return true;
  }

  private Container createRoleContainer() {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add( //
        createTitleComponent( //
            "ROLES", TITLE1_FONT, //
            "The core of CYBERPUNK Role-playing", SUBTITLE1_FONT),
        BorderLayout.NORTH);

    panel.add(createRoleComponent(), BorderLayout.CENTER);

    panel.add( //
        createNavigationComponent( //
            "<<= Previous", evt -> switchCardTo(SIBLINGS_PANE), //
            "Done!", evt -> {
              updatePlayer();
              dispose();
            }), //
        BorderLayout.SOUTH);

    return panel;
  }

  private JComponent createRoleComponent() {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add(createRoleSelectorComponent(), BorderLayout.NORTH);
    panel.add(createRoleSkillsComponent(), BorderLayout.CENTER);

    return panel;
  }

  private JComponent createRoleSelectorComponent() {
    JPanel panel = new JPanel(new GridBagLayout());

    panel.add(roleComboBox);

    return panel;
  }

  private JComponent createRoleSkillsComponent() {
    JPanel panel = new JPanel(new CardLayout());

    for (int i = 0; i < roleComboBox.getItemCount(); i++) {
      Role r = roleComboBox.getItemAt(i);
      List<JComboBox<String>> comboBoxes = comboBoxesByRole.get(r);
      JPanel p = new JPanel();
      p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));

      for (JComboBox<String> cb : comboBoxes) {
        cb.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        p.add(cb);
      }

      panel.add(p, r.getName());
    }

    roleComboBox.addActionListener(evt -> {
      Role r = (Role) roleComboBox.getSelectedItem();
      CardLayout layout = (CardLayout) panel.getLayout();
      layout.show(panel, r.getName());
    });

    return panel;
  }

  private void updatePlayer() {
    String str = aliasTextField.getText();
    player.setAlias(new Name(str));

    str = ageTextField.getText();
    player.setAge(new Age(Integer.parseInt(str)));

    for (int i = 0; i < siblingCount; i++) {
      final Integer key = new Integer(i);
      JTextField nameTextField = siblingNameTextFieldsByInteger.get(key);
      JComboBox<Gender> genderComboBox = siblingGenderComboBoxesByInteger.get(key);
      JComboBox<RelativeAge> ageComboBox = siblingAgeComboBoxesByInteger.get(key);
      JComboBox<String> relationshipComboBox = siblingRelationshipComboBoxesByInteger.get(key);

      player.addSibling(new Sibling( //
          nameTextField.getText(), //
          (Gender) genderComboBox.getSelectedItem(), //
          (RelativeAge) ageComboBox.getSelectedItem(), //
          (String) relationshipComboBox.getSelectedItem()));
    }

    Role r = (Role) roleComboBox.getSelectedItem();
    List<JComboBox<String>> comboBoxes = comboBoxesByRole.get(r);
    String[] skillNames = new String[Role.OPTION_COUNT];
    for (int i = 0; i < comboBoxes.size(); i++) {
      skillNames[i] = (String) comboBoxes.get(i).getSelectedItem();
    }
    player.setCareerSkillNames(Arrays.asList(skillNames));
  }

}
