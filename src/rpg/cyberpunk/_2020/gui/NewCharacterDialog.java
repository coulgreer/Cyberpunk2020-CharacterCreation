package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import rpg.cyberpunk._2020.Player;
import rpg.cyberpunk._2020.Sibling;
import rpg.cyberpunk._2020.Sibling.RelativeAge;
import rpg.cyberpunk._2020.Sibling.Sex;
import rpg.cyberpunk._2020.stats.CyberpunkAttribute;
import rpg.cyberpunk._2020.stats.Role;
import rpg.cyberpunk._2020.stats.RoleFactory;
import rpg.cyberpunk._2020.stats.StatisticFactory;
import rpg.cyberpunk._2020.systems.CharacterPointsManager;
import rpg.cyberpunk._2020.systems.FastAttribute;
import rpg.cyberpunk._2020.systems.FastCharacterPointsManager;
import rpg.cyberpunk._2020.systems.RandomAttribute;
import rpg.cyberpunk._2020.systems.RandomCharacterPointsManager;
import rpg.general.stats.Attribute;
import rpg.util.Die;

/**
 * An instance of JDialog that takes inputs from various cards that allows a <code>Player</code> to
 * set its background info and sets the Player's initial <code>Attribute</code>s.
 */
public class NewCharacterDialog extends JDialog {
  private static final long serialVersionUID = 1L;

  private static final String name = "Name";
  private static final String sex = "Sex";
  private static final String age = "Age";
  private static final String relationship = "Relationship";

  private static final String characterPointPane = "Character Point Pane";
  private static final String randomPointPane = "Random Points";
  private static final String fastPointPane = "Fast Points";
  private static final String familyRankingPane = "Family Ranking Pane";
  private static final String parentPane = "Parent Pane";
  private static final String parentTragedyPane = "Parent Tragedy Pane";
  private static final String familyStatusPane = "Family Status Pane";
  private static final String familyTragedyPane = "Family Tragedy Pane";
  private static final String childhoodEnvironmentPane = "Childhood Environment Pane";
  private static final String siblingsPane = "Siblings Pane";
  private static final String rolePane = "Role Pane";

  private Player player;
  private JPanel contentPane;
  private Map<String, CharacterPointsManager> managersByCardName;
  private String activeCardName;
  private Map<JPanel, Map<String, Object>> propertiesByNameByJPanel;

  /**
   * Constructs a JDialog that allows the manipulation of a <code>Player</code>'s stats and
   * background.
   * 
   * @param owner the Frame from which the dialog is displayed
   * @param title the String to display in the dialog's title bar
   * @param modal specifies whether dialog blocks user input to other top-level windows when shown.
   *        If true, the modality type property is set to DEFAULT_MODALITY_TYPE otherwise the dialog
   *        is modeless
   * @param player the provider of the <code>Statistic</code>s and owner of the user given
   *        background
   */
  public NewCharacterDialog(Frame owner, String title, boolean modal, Player player) {
    super(owner, title, modal);
    setPlayer(player);

    contentPane = new JPanel(new CardLayout());
    contentPane.add(createCharacterPointContainer(), characterPointPane);
    contentPane.add(createFamilyRankingContainer(), familyRankingPane);
    contentPane.add(createParentStatusContainer(), parentPane);
    contentPane.add(createParentTragedyContainer(), parentTragedyPane);
    contentPane.add(createFamilyStatusContainer(), familyStatusPane);
    contentPane.add(createFamilyTragedyContainer(), familyTragedyPane);
    contentPane.add(createChildhoodEnvironmentContainer(), childhoodEnvironmentPane);
    contentPane.add(createSiblingsContainer(), siblingsPane);
    contentPane.add(createRoleContainer(), rolePane);

    setContentPane(contentPane);
  }

  private void setPlayer(Player player) {
    if (player == null) {
      throw new NullPointerException();
    } else {
      this.player = player;
    }
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

  private Component createNavigationComponent(Action prevAction, Action nextAction) {
    JPanel panel = new JPanel();

    panel.add(new JButton(prevAction));
    panel.add(new JButton(nextAction));

    return panel;
  }

  private Component createNavigationComponent( //
      String prevText, ActionListener prevListener, //
      String nextText, ActionListener nextListener) {

    JPanel panel = new JPanel();

    JButton previousButton = new JButton(prevText);
    if (prevListener == null) {
      previousButton.setEnabled(false);
    } else {
      previousButton.addActionListener(prevListener);
    }
    panel.add(previousButton);

    JButton nextButton = new JButton(nextText);
    if (nextListener == null) {
      nextButton.setEnabled(false);
    } else {
      nextButton.addActionListener(nextListener);
    }
    panel.add(nextButton);

    return panel;
  }

  private Container createCharacterPointContainer() {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add( //
        createTitleComponent( //
            "CHARACTER POINTS (CP)", new Font("Serif", Font.PLAIN, 30), //
            "Select a method of acquiring CP.", new Font("Serif", Font.PLAIN, 16)), //
        BorderLayout.NORTH);
    panel.add(createCharacterPointContent(), BorderLayout.CENTER);
    panel.add( //
        createNavigationComponent( //
            "<<= Previous", null, //
            "Next =>>", evt -> {
              CharacterPointsManager manager = managersByCardName.get(activeCardName);
              if (manager.isValid()) {
                switchCardTo(familyRankingPane);
              } else {
                JOptionPane.showMessageDialog( //
                    contentPane, "Make sure all points are appropriately spent.");
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
    cards.add(createRandomPointManager(attributes), randomPointPane);
    cards.add(createFastPointManager(attributes), fastPointPane);
    // TODO (Coul Greer): Create a component that allows the user to assign character points using
    // the cinematic method.

    return cards;
  }

  private Component createRandomPointManager(List<Attribute> attributes) {
    List<Attribute> decoratedAttributes = new ArrayList<Attribute>(attributes.size());
    RandomCharacterPointsManager manager = new RandomCharacterPointsManager();

    for (Attribute a : attributes) {
      Attribute decoratedAttribute = new RandomAttribute(a, manager);
      decoratedAttributes.add(decoratedAttribute);
      manager.add(decoratedAttribute);
    }

    JPanel panel = new RandomCharacterPointsPane( //
        decoratedAttributes, //
        manager);

    managersByCardName.put(randomPointPane, manager);

    return panel;
  }

  private Component createFastPointManager(List<Attribute> attributes) {
    List<Attribute> decoratedAttributes = new ArrayList<Attribute>(attributes.size());
    FastCharacterPointsManager manager = new FastCharacterPointsManager();

    for (Attribute a : attributes) {
      Attribute decoratedAttribute = new FastAttribute(a, manager);
      decoratedAttributes.add(decoratedAttribute);
      manager.add(decoratedAttribute);
    }

    JPanel panel = new FastCharacterPointsPane( //
        decoratedAttributes, //
        manager);

    managersByCardName.put(fastPointPane, manager);

    return panel;
  }

  private Component createMethodSelectionComponent(Container container) {
    JPanel panel = new JPanel();

    String[] items = { //
        randomPointPane, //
        fastPointPane};
    activeCardName = items[0];

    JComboBox<String> comboBox = new JComboBox<String>(items);
    comboBox.addItemListener(evt -> {
      if (evt.getStateChange() == ItemEvent.SELECTED) {
        activeCardName = (String) evt.getItem();
        CardLayout layout = (CardLayout) container.getLayout();
        layout.show(container, activeCardName);

        if (activeCardName.equals(fastPointPane)) {
          managersByCardName.get(activeCardName)
              .rollPoints(new Die(StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT, 10));
        } else {
          int minPoints =
              CyberpunkAttribute.MIN_LEVEL * StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT;
          int maxPoints =
              CyberpunkAttribute.MAX_LEVEL * StatisticFactory.INDEPENDENT_ATTRIBUTE_COUNT;
          managersByCardName.get(activeCardName).rollPoints(minPoints, maxPoints);
        }

      }
    });
    panel.add(comboBox);

    return panel;
  }

  private Container createFamilyRankingContainer() {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add( //
        createTitleComponent( //
            "FAMILY RANKING", new Font("Serif", Font.PLAIN, 30), //
            "Choose one or roll.", new Font("Serif", Font.PLAIN, 16)), //
        BorderLayout.NORTH);
    panel.add(createFamilyRankingContent(), BorderLayout.CENTER);
    panel.add( //
        createNavigationComponent( //
            new SwitchToCardAction( //
                "<<= Previous", null, //
                null, null, //
                contentPane, characterPointPane), //
            new SwitchToCardAction( //
                "Next =>>", null, //
                null, null, //
                contentPane, parentPane)), //
        BorderLayout.SOUTH);

    return panel;
  }

  private Component createFamilyRankingContent() {
    JPanel panel = new JPanel();

    String[] items = { //
        "Corporate Executive", //
        "Corporate Manager", //
        "Corporate Technician", //
        "Nomad Pack", //
        "Pirate Fleet", //
        "Gang Family", //
        "Crime Lord", //
        "Combat Zone Poor", //
        "Urban Homeless", //
        "Arcology Family"};
    player.setFamilyRanking(items[0]);

    JComboBox<String> comboBox = new JComboBox<String>(items);
    comboBox.addItemListener(evt -> {
      if (evt.getStateChange() == ItemEvent.SELECTED) {
        String item = (String) evt.getItem();
        player.setFamilyRanking(item);
      }
    });
    panel.add(comboBox);

    JButton randomButton = new JButton("Roll");
    randomButton.addActionListener(evt -> {
      selectRandomItemFrom(comboBox);
    });
    panel.add(randomButton);

    return panel;
  }

  private void selectRandomItemFrom(JComboBox<String> comboBox) {
    int randomNum = ThreadLocalRandom.current().nextInt(0, comboBox.getItemCount());
    comboBox.setSelectedIndex(randomNum);
  }

  private Container createParentStatusContainer() {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add( //
        createTitleComponent( //
            "PARENTS", new Font("Serif", Font.PLAIN, 30), //
            "Choose one or roll.", new Font("Serif", Font.PLAIN, 16)), //
        BorderLayout.NORTH);
    ButtonGroup group = new ButtonGroup();
    panel.add(createParentStatusContent(group), BorderLayout.CENTER);
    panel.add( //
        createNavigationComponent( //
            "<<= Previous", evt -> switchCardTo(familyRankingPane), //
            "Next =>>", evt -> switchCardTo(group)), //
        BorderLayout.SOUTH);

    return panel;
  }

  private Component createParentStatusContent(ButtonGroup group) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    JRadioButton[] radioButtons = new JRadioButton[2];

    radioButtons[0] = new JRadioButton("Both parents are living.");
    radioButtons[0].setActionCommand(familyStatusPane);
    radioButtons[0].addActionListener(evt -> {
      player.setParentStatus("Both parents are living.");
      player.setParentTragedy(false);
    });

    radioButtons[1] = new JRadioButton("Something has happened to one or both parents.");
    radioButtons[1].setActionCommand(parentTragedyPane);
    radioButtons[1].addActionListener(evt -> {
      player.setParentStatus("Something has happened to one or both parents.");
      player.setParentTragedy(true);
    });

    for (JRadioButton rb : radioButtons) {
      group.add(rb);
    }
    radioButtons[0].setSelected(true);

    panel.add(createRadioButtonContainer(radioButtons));

    JButton button = new JButton("Roll");
    button.addActionListener(evt -> {
      int randomNum = ThreadLocalRandom.current().nextInt(0, 10);

      if (0 <= randomNum && randomNum <= 6) {
        radioButtons[0].setSelected(true);
      } else if (7 <= randomNum && randomNum <= 9) {
        radioButtons[1].setSelected(true);
      }
    });
    button.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    panel.add(button);

    return panel;
  }

  private Container createRadioButtonContainer(JRadioButton[] radioButtons) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    panel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

    for (JRadioButton rb : radioButtons) {
      panel.add(rb);
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
            "PARENT TRAGEDY", new Font("Serif", Font.PLAIN, 30), //
            "Choose one or roll.", new Font("Serif", Font.PLAIN, 16)), //
        BorderLayout.NORTH);
    panel.add(createParentTragedyContent(), BorderLayout.CENTER);
    panel.add( //
        createNavigationComponent( //
            new SwitchToCardAction( //
                "<<= Previous", null, //
                null, null, //
                contentPane, parentPane), //
            new SwitchToCardAction( //
                "Next =>>", null, //
                null, null, //
                contentPane, familyStatusPane)),
        BorderLayout.SOUTH);

    return panel;
  }

  private Component createParentTragedyContent() {
    JPanel panel = new JPanel();

    String[] items = { //
        "Your parent(s) died in warfare.", //
        "Your parent(s) died in an accident.", //
        "Your parent(s) were murdered.", //
        "Your parent(s) have amnesia and don't remember you.", //
        "You never knew your parent(s).", //
        "Your parent(s) are in hiding to protect you.", //
        "You were left with relatives for safekeeping.", //
        "You grew up on the Street and never had parents.", //
        "Your parent(s) gave you up for adoption.", //
        "Your parent(s) sold you for money."};
    player.setParentTragedy(items[0]);

    JComboBox<String> comboBox = new JComboBox<String>(items);
    comboBox.addItemListener(evt -> {
      if (evt.getStateChange() == ItemEvent.SELECTED) {
        String item = (String) evt.getItem();
        player.setParentTragedy(item);
      }
    });
    panel.add(comboBox);

    JButton button = new JButton("Roll");
    button.addActionListener(evt -> selectRandomItemFrom(comboBox));
    panel.add(button);

    return panel;
  }

  private Container createFamilyStatusContainer() {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add( //
        createTitleComponent( //
            "FAMILY STATUS", new Font("Serif", Font.PLAIN, 30), //
            "Choose one or roll.", new Font("Serif", Font.PLAIN, 16)), //
        BorderLayout.NORTH);
    ButtonGroup group = new ButtonGroup();
    panel.add(createFamilyStatusContent(group), BorderLayout.CENTER);
    panel.add( //
        createNavigationComponent( //
            "<<= Previous", evt -> switchCardTo(parentPane), //
            "Next =>>", evt -> switchCardTo(group)), //
        BorderLayout.SOUTH);

    return panel;
  }

  private Component createFamilyStatusContent(ButtonGroup group) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    JRadioButton[] radioButtons = new JRadioButton[2];

    radioButtons[0] = new JRadioButton(
        "Family status in danger, and you risk losing everything (if you haven't already).");
    radioButtons[0].setActionCommand(familyTragedyPane);
    radioButtons[0].addActionListener(evt -> {
      player.setFamilyStatus(
          "Family status in danger, and you risk losing everything (if you haven't already).");
      player.setFamilyTragedy(true);
    });

    radioButtons[1] = new JRadioButton("Family status is OK, even if parents are missing or dead.");
    radioButtons[1].setActionCommand(childhoodEnvironmentPane);
    radioButtons[1].addActionListener(evt -> {
      player.setFamilyStatus("Family status is OK, even if parents are missing or dead.");
      player.setFamilyTragedy(false);
    });

    for (JRadioButton radioButton : radioButtons) {
      group.add(radioButton);
    }
    radioButtons[0].setSelected(true);

    panel.add(createRadioButtonContainer(radioButtons));

    JButton button = new JButton("Roll");
    button.addActionListener(evt -> {
      int randomNum = ThreadLocalRandom.current().nextInt(0, 10);

      if (0 <= randomNum && randomNum <= 6) {
        radioButtons[0].setSelected(true);
      } else if (7 <= randomNum && randomNum <= 9) {
        radioButtons[1].setSelected(true);
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
            "FAMILY TRAGEDY", new Font("Serif", Font.PLAIN, 30), //
            "Choose one or roll.", new Font("Serif", Font.PLAIN, 16)), //
        BorderLayout.NORTH);
    panel.add(createFamilyTragedyContent(), BorderLayout.CENTER);
    panel.add( //
        createNavigationComponent( //
            new SwitchToCardAction( //
                "<<= Previous", null, //
                null, null, //
                contentPane, familyStatusPane),
            new SwitchToCardAction( //
                "Next =>>", null, //
                null, null, //
                contentPane, childhoodEnvironmentPane)), //
        BorderLayout.SOUTH);

    return panel;
  }

  private Component createFamilyTragedyContent() {
    JPanel panel = new JPanel();

    String[] items = { //
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
            + " your life."};
    player.setFamilyTragedy(items[0]);

    JComboBox<String> comboBox = new JComboBox<String>(items);
    comboBox.addItemListener(evt -> {
      if (evt.getStateChange() == ItemEvent.SELECTED) {
        String item = (String) evt.getItem();
        player.setFamilyTragedy(item);
      }
    });
    panel.add(comboBox);

    JButton randomButton = new JButton("Roll");
    randomButton.addActionListener(evt -> selectRandomItemFrom(comboBox));
    panel.add(randomButton);

    return panel;
  }

  private Container createChildhoodEnvironmentContainer() {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add( //
        createTitleComponent( //
            "CHILDHOOD ENVIRONMENT", new Font("Serif", Font.PLAIN, 30), //
            "Your Childhood was (choose or roll one):", new Font("Serif", Font.PLAIN, 16)), //
        BorderLayout.NORTH);
    panel.add(createChildhoodEnvironmentContent(), BorderLayout.CENTER);
    panel.add( //
        createNavigationComponent( //
            new SwitchToCardAction( //
                "<<= Previous", null, //
                null, null, //
                contentPane, familyStatusPane), //
            new SwitchToCardAction( //
                "Next =>>", null, //
                null, null, //
                contentPane, siblingsPane)), //
        BorderLayout.SOUTH);

    return panel;
  }

  private Component createChildhoodEnvironmentContent() {
    JPanel panel = new JPanel();

    String[] items = { //
        "Spent on the Street, with no adult supervision.", //
        "Spent in a safe Corporate Sububia.", //
        "In a Nomad Pack moving from town to town.", //
        "In a decaying, once upscale neighborhood.", //
        "In a defended Corporate Zone in the central City.", //
        "In the heart of the Combat Zone.", //
        "In a small village or town far from the City.", //
        "In a large arcoloty city.", //
        "In an aquatic Pirate Pack.", //
        "In a Corporate controlled Farm or Research Facility."};
    player.setChildhoodEnvironment(items[0]);

    JComboBox<String> comboBox = new JComboBox<String>(items);
    comboBox.addItemListener(evt -> {
      if (evt.getStateChange() == ItemEvent.SELECTED) {
        String item = (String) evt.getItem();
        player.setChildhoodEnvironment(item);
      }
    });
    panel.add(comboBox);

    JButton button = new JButton("Roll");
    button.addActionListener(evt -> selectRandomItemFrom(comboBox));
    panel.add(button);

    return panel;
  }

  private Container createSiblingsContainer() {
    JPanel panel = new JPanel(new BorderLayout());
    propertiesByNameByJPanel = new HashMap<JPanel, Map<String, Object>>();
    int maxNumberOfSiblings = 7;

    panel.add( //
        createTitleComponent( //
            "SIBLINGS", new Font("Serif", Font.PLAIN, 30), //
            "You may have up to 7 brothers/sisters.", new Font("Serif", Font.PLAIN, 16)), //
        BorderLayout.NORTH);
    panel.add( //
        new JScrollPane(createSiblingsContent(panel, maxNumberOfSiblings)), //
        BorderLayout.CENTER);
    panel.add( //
        createNavigationComponent( //
            "<<= Previous", evt -> switchCardTo(childhoodEnvironmentPane), //
            "Next =>>", evt -> {
              List<Sibling> siblings = new ArrayList<Sibling>(maxNumberOfSiblings);

              for (Map<String, Object> propertiesByName : propertiesByNameByJPanel.values()) {
                String siblingName = (String) propertiesByName.get(name);
                Sex siblingSex = (Sex) propertiesByName.get(sex);
                RelativeAge siblingAge = (RelativeAge) propertiesByName.get(age);
                String siblingRelationship = (String) propertiesByName.get(relationship);

                siblings.add(new Sibling(siblingName, siblingSex, siblingAge, siblingRelationship));
              }
              player.setSiblings(siblings);
              switchCardTo(rolePane);
            }), //
        BorderLayout.SOUTH);

    return panel;
  }

  private Component createSiblingsContent(Container parent, int maxNumberOfSiblings) {
    JPanel panel = new JPanel(new BorderLayout());

    ButtonGroup group = new ButtonGroup();
    JRadioButton[] radioButtons = new JRadioButton[maxNumberOfSiblings + 1];
    Deque<Component> stack = new ArrayDeque<Component>(maxNumberOfSiblings);

    JPanel siblingsPanel = new JPanel();
    siblingsPanel.setLayout(new BoxLayout(siblingsPanel, BoxLayout.PAGE_AXIS));
    panel.add(siblingsPanel, BorderLayout.SOUTH);

    radioButtons[0] = new JRadioButton("1 Sibling");
    radioButtons[0].addItemListener(evt -> {
      setSiblingCount(siblingsPanel, stack, 1);
      parent.revalidate();
      parent.repaint();
    });
    radioButtons[0].setSelected(true);

    radioButtons[1] = new JRadioButton("2 Siblings");
    radioButtons[1].addItemListener(evt -> {
      setSiblingCount(siblingsPanel, stack, 2);
      parent.revalidate();
      parent.repaint();
    });

    radioButtons[2] = new JRadioButton("3 Siblings");
    radioButtons[2].addItemListener(evt -> {
      setSiblingCount(siblingsPanel, stack, 3);
      parent.revalidate();
      parent.repaint();
    });

    radioButtons[3] = new JRadioButton("4 Siblings");
    radioButtons[3].addItemListener(evt -> {
      setSiblingCount(siblingsPanel, stack, 4);
      parent.revalidate();
      parent.repaint();
    });

    radioButtons[4] = new JRadioButton("5 Siblings");
    radioButtons[4].addItemListener(evt -> {
      setSiblingCount(siblingsPanel, stack, 5);
      parent.revalidate();
      parent.repaint();
    });

    radioButtons[5] = new JRadioButton("6 Siblings");
    radioButtons[5].addItemListener(evt -> {
      setSiblingCount(siblingsPanel, stack, 6);
      parent.revalidate();
      parent.repaint();
    });

    radioButtons[6] = new JRadioButton("7 Siblings");
    radioButtons[6].addItemListener(evt -> {
      setSiblingCount(siblingsPanel, stack, 7);
      parent.revalidate();
      parent.repaint();
    });

    radioButtons[7] = new JRadioButton("Only Child");
    radioButtons[7].addItemListener(evt -> {
      setSiblingCount(siblingsPanel, stack, 0);
      parent.revalidate();
      parent.repaint();
    });

    for (JRadioButton radioButton : radioButtons) {
      group.add(radioButton);
    }
    panel.add(createRadioButtonContainer(radioButtons), BorderLayout.CENTER);

    return panel;
  }

  private void setSiblingCount(Container parent, Deque<Component> stack, int count) {
    while (stack.size() < count) {
      Component siblingPanel = createSiblingContent();
      stack.push(siblingPanel);
      parent.add(siblingPanel);
    }

    while (stack.size() > count) {
      Component siblingPanel = stack.pop();
      propertiesByNameByJPanel.remove(siblingPanel);
      parent.remove(siblingPanel);
    }
  }

  private Component createSiblingContent() {
    JPanel panel = new JPanel();
    Map<String, Object> propertiesByName = new HashMap<String, Object>();
    propertiesByNameByJPanel.put(panel, propertiesByName);

    panel.add(createNameComponent(propertiesByName));
    panel.add(createSexComponent(propertiesByName));
    panel.add(createAgeComponent(propertiesByName));
    panel.add(createRelationshipComponent(propertiesByName));

    return panel;
  }

  private Component createNameComponent(Map<String, Object> propertiesByName) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    panel.add( //
        createTitleComponent( //
            "Name", new Font("Serif", Font.PLAIN, 24), //
            null, null));

    propertiesByName.put(name, "Unknown");
    JTextField textField = new JTextField(20);
    textField.getDocument().addDocumentListener(new DocumentListener() {

      @Override
      public void removeUpdate(DocumentEvent evt) {
        propertiesByName.replace(name, textField.getText());
      }

      @Override
      public void insertUpdate(DocumentEvent evt) {
        propertiesByName.replace(name, textField.getText());
      }

      @Override
      public void changedUpdate(DocumentEvent evt) {
        // Do nothing.
      }
    });
    panel.add(textField);

    return panel;
  }

  private Component createSexComponent(Map<String, Object> propertiesByName) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    panel.add( //
        createTitleComponent( //
            "Sex", new Font("Serif", Font.PLAIN, 24), //
            "Roll or choose one.", new Font("Serif", Font.PLAIN, 12)));

    Sex[] items = {Sex.MALE, Sex.FEMALE};
    propertiesByName.put(sex, items[0]);
    JComboBox<Sex> comboBox = new JComboBox<Sex>(items);
    comboBox.addItemListener(evt -> {
      if (evt.getStateChange() == ItemEvent.SELECTED) {
        propertiesByName.replace(sex, evt.getItem());
      }
    });
    panel.add(comboBox);

    return panel;
  }

  private Component createAgeComponent(Map<String, Object> propertiesByName) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    panel.add( //
        createTitleComponent( //
            "Relative Age", new Font("Serif", Font.PLAIN, 24), //
            "Roll or choose one.", new Font("Serif", Font.PLAIN, 12)));

    RelativeAge[] items = {RelativeAge.OLDER, RelativeAge.YOUNGER, RelativeAge.TWIN};
    propertiesByName.put(age, items[0]);
    JComboBox<RelativeAge> comboBox = new JComboBox<RelativeAge>(items);
    comboBox.addItemListener(evt -> {
      if (evt.getStateChange() == ItemEvent.SELECTED) {
        propertiesByName.replace(age, evt.getItem());
      }
    });
    panel.add(comboBox);

    return panel;
  }

  private Component createRelationshipComponent(Map<String, Object> propertiesByName) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    panel.add( //
        createTitleComponent( //
            "Relationship", new Font("Serif", Font.PLAIN, 24), //
            "Roll or choose one.", new Font("Serif", Font.PLAIN, 12)));

    String[] items = { //
        "Sibling dislikes you.", //
        "Sibling likes you.", //
        "Sibling neutral.", //
        "They worship you.", //
        "They hate you."};
    propertiesByName.put(relationship, items[0]);
    JComboBox<String> comboBox = new JComboBox<String>(items);
    comboBox.addItemListener(evt -> {
      if (evt.getStateChange() == ItemEvent.SELECTED) {
        propertiesByName.replace(relationship, evt.getItem());
      }
    });
    panel.add(comboBox);

    return panel;
  }

  private Container createRoleContainer() {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add( //
        createTitleComponent( //
            "ROLES", new Font("Serif", Font.PLAIN, 30), //
            "The core of CYBERPUNK Role-playing", new Font("Serif", Font.PLAIN, 16)),
        BorderLayout.NORTH);
    panel.add(createRoleComponent(), BorderLayout.CENTER);
    panel.add( //
        createNavigationComponent( //
            "<<= Previous", evt -> switchCardTo(siblingsPane), //
            "Done!", evt -> {
              dispose();
            }), //
        BorderLayout.SOUTH);

    return panel;
  }

  private Component createRoleComponent() {
    JPanel panel = new JPanel(new BorderLayout());

    Map<String, Role> rolesByName = createRoles();
    String[] items = rolesByName.keySet().toArray(new String[0]);

    Role initRole = rolesByName.get(items[0]);
    player.setRole(initRole);
    panel.add(createSkillOptionContent(initRole), BorderLayout.CENTER);

    JComboBox<String> comboBox = new JComboBox<String>(items);
    comboBox.addItemListener(evt -> {
      if (evt.getStateChange() == ItemEvent.SELECTED) {
        Role role = rolesByName.get(evt.getItem());
        player.setRole(role);

        BorderLayout layout = (BorderLayout) panel.getLayout();
        panel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
        panel.add(createSkillOptionContent(role), BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
      }
    });
    panel.add(comboBox, BorderLayout.NORTH);

    return panel;
  }

  private Map<String, Role> createRoles() {
    Map<String, Role> rolesByName = new HashMap<String, Role>();

    Iterator<String> iterator = RoleFactory.createNameIterator();
    while (iterator.hasNext()) {
      Role role = RoleFactory.createRole(iterator.next());
      rolesByName.put(role.getName(), role);
    }

    return rolesByName;
  }

  private Component createSkillOptionContent(Role role) {
    JPanel panel = new JPanel();
    JPanel comboBoxPane = new JPanel(new GridLayout(Role.OPTION_COUNT, 0, 0, 3));

    List<String> pickupSkillNames = new ArrayList<String>(Role.OPTION_COUNT);
    List<List<String>> skillNameOptions = role.getSkillNameOptions();

    for (int i = 0; i < skillNameOptions.size(); i++) {
      int index = i;
      List<String> options = skillNameOptions.get(i);

      String[] items = options.toArray(new String[0]);
      pickupSkillNames.add(items[0]);

      JComboBox<String> comboBox = new JComboBox<String>(items);
      comboBox.addItemListener(evt -> {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
          String item = (String) evt.getItem();
          pickupSkillNames.set(index, item);
          player.setPickupSkillNames(pickupSkillNames);
        }
      });
      comboBox.setEnabled(items.length > 1);

      comboBoxPane.add(comboBox);
    }
    player.setPickupSkillNames(pickupSkillNames);

    panel.add(comboBoxPane);
    JScrollPane scrollPane = new JScrollPane(panel);
    return scrollPane;
  }

}
