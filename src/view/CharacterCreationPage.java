package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CharacterCreationPage extends JFrame {
	private static int SMALL_TEXT_FIELD = 3;
	private static int WINDOW_WIDTH = 900;
	private static int WINDOW_HEIGHT = 950;
	private static int SMALL_DIV_WIDTH = (WINDOW_WIDTH / 2);
	private static int SMALL_DIV_HEIGHT = (WINDOW_HEIGHT / 36);
	private static int MEDIUM_DIV_WIDTH = (WINDOW_WIDTH / 2);
	private static int MEDIUM_DIV_HEIGHT = (WINDOW_HEIGHT / 12);
	private static int LARGE_DIV_WIDTH = (WINDOW_WIDTH / 2);
	private static int LARGE_DIV_HEIGHT = 2 * (WINDOW_HEIGHT / 3);
	private static int STUN_PANEL_WIDTH = 3 * (MEDIUM_DIV_WIDTH / 4);
	private static int SAVE_PANEL_WIDTH = (MEDIUM_DIV_WIDTH / 8);
	private static int NUMBER_OF_SKILL_COLUMNS = 3;

	private JPanel characterCreationPanel;

	private JPanel handlePanel;
	private JLabel handleLabel;
	private JTextField handleField;

	private JPanel rolePanel;
	private JLabel roleLabel;
	private JComboBox<String> roleDropdown;

	private JPanel characterPointsPanel;
	private JLabel characterPointsLabel;
	private JTextField characterPointsField;
	private JButton characterPointsDecreaseButton;
	private JButton characterPointsIncreaseButton;

	private JPanel statsPanel;
	private JLabel statsLabel;
	private JLabel intelligenceLabel;
	private JTextField intelligenceField;
	private JLabel reflexesLabel;
	private JTextField reflexesField;
	private JLabel technicalAbilityLabel;
	private JTextField technicalAbilityField;
	private JLabel coolLabel;
	private JTextField coolField;
	private JLabel attractivenessLabel;
	private JTextField attractivenessField;
	private JLabel luckLabel;
	private JTextField luckField;
	private JLabel movementAllowanceLabel;
	private JTextField movementAllowanceField;
	private JLabel bodyLabel;
	private JTextField bodyField;
	private JLabel empathyLabel;
	private JTextField empathyField;
	private JLabel runLabel;
	private JTextField runField;
	private JLabel leapLabel;
	private JTextField leapField;
	private JLabel liftLabel;
	private JTextField liftField;

	private JPanel armorPanel;
	private JLabel locationLabel;
	private JLabel headLabel;
	private JLabel torsoLabel;
	private JLabel rightArmLabel;
	private JLabel leftArmLabel;
	private JLabel rightLegLabel;
	private JLabel leftLegLabel;
	private JLabel armorSPLabel;
	private JTextField headSPField;
	private JTextField torsoSPField;
	private JTextField rightArmSPField;
	private JTextField leftArmSPField;
	private JTextField rightLegSPField;
	private JTextField leftLegSPField;

	private JPanel woundsPanel;
	private JPanel savePanel;
	private JLabel saveLabel;
	private JTextField saveField;
	private JPanel bodyTypeModifierPanel;
	private JLabel bodyTypeModifierLabel;
	private JTextField bodyTypeModifierField;

	private JPanel stunPanel;
	private JPanel lightStunProgressPanel;
	private JPanel seriousStunProgressPanel;
	private JPanel criticalStunProgressPanel;
	private JPanel mortal0StunProgressPanel;
	private JPanel mortal1StunProgressPanel;
	private JPanel mortal2StunProgressPanel;
	private JPanel mortal3StunProgressPanel;
	private JPanel mortal4StunProgressPanel;
	private JPanel mortal5StunProgressPanel;
	private JPanel mortal6StunProgressPanel;

	private JPanel skillsPanel;
	private List<JPanel> skillColumns;
	private JLabel skillsLabel;
	private JPanel specialAbilitiesPanel;
	private JLabel specialAbilitiesTitle;
	private JPanel attractivenessPanel;
	private JLabel attractivenessTitle;
	private JPanel bodyPanel;
	private JLabel bodyTitle;
	private JPanel coolPanel;
	private JLabel coolTitle;
	private JPanel empathyPanel;
	private JLabel empathyTitle;
	private JPanel intelligencePanel;
	private JLabel intelligenceTitle;
	private JPanel reflexesPanel;
	private JLabel reflexesTitle;
	private JPanel technicalAbilitiesPanel;
	private JLabel technicalAbilitiesTitle;

	private JLabel portraitLabel;

	// Figure a way to add all abilities dynamically

	public CharacterCreationPage() {
		characterCreationPanel = new JPanel(new GridBagLayout());
		characterCreationPanel.setBackground(Color.WHITE);
		characterCreationPanel.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

		handlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 2));
		handlePanel.setPreferredSize(new Dimension(SMALL_DIV_WIDTH, SMALL_DIV_HEIGHT));
		handlePanel.setBackground(Color.ORANGE);
		handleLabel = new JLabel("HANDLE");
		formatCategoryTitle(handleLabel);
		handleField = new JTextField(20);
		createHandleArea();

		rolePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 2));
		rolePanel.setPreferredSize(new Dimension(SMALL_DIV_WIDTH, SMALL_DIV_HEIGHT));
		rolePanel.setBackground(Color.BLUE);
		roleLabel = new JLabel("ROLE");
		formatCategoryTitle(roleLabel);
		roleDropdown = new JComboBox<String>(new String[] { "Cop", "Corporate", "Fixer", "Media", "Netrunner", "Nomad",
				"Rockerboy", "Solo", "Techie" });
		createRoleArea();

		characterPointsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 2));
		characterPointsPanel.setPreferredSize(new Dimension(SMALL_DIV_WIDTH, SMALL_DIV_HEIGHT));
		characterPointsPanel.setBackground(Color.RED);
		characterPointsLabel = new JLabel("CHARACTER POINTS");
		formatCategoryTitle(characterPointsLabel);
		characterPointsField = new JTextField(SMALL_TEXT_FIELD);
		characterPointsDecreaseButton = new JButton("<");
		characterPointsDecreaseButton.setMargin(new Insets(1, 1, 1, 1));
		characterPointsIncreaseButton = new JButton(">");
		characterPointsIncreaseButton.setMargin(new Insets(1, 1, 1, 1));
		createCharacterPointArea();

		statsPanel = new JPanel(new GridBagLayout());
		statsPanel.setPreferredSize(new Dimension(MEDIUM_DIV_WIDTH, MEDIUM_DIV_HEIGHT));
		statsPanel.setBackground(Color.YELLOW);
		statsLabel = new JLabel("STATS");
		formatCategoryTitle(statsLabel);
		intelligenceLabel = new JLabel("INT");
		intelligenceField = new JTextField(SMALL_TEXT_FIELD);
		reflexesLabel = new JLabel("REF");
		reflexesField = new JTextField(SMALL_TEXT_FIELD);
		technicalAbilityLabel = new JLabel("TECH");
		technicalAbilityField = new JTextField(SMALL_TEXT_FIELD);
		coolLabel = new JLabel("COOL");
		coolField = new JTextField(SMALL_TEXT_FIELD);
		attractivenessLabel = new JLabel("ATTR");
		attractivenessField = new JTextField(SMALL_TEXT_FIELD);
		luckLabel = new JLabel("LUCK");
		luckField = new JTextField(SMALL_TEXT_FIELD);
		movementAllowanceLabel = new JLabel("MA");
		movementAllowanceField = new JTextField(SMALL_TEXT_FIELD);
		bodyLabel = new JLabel("BODY");
		bodyField = new JTextField(SMALL_TEXT_FIELD);
		empathyLabel = new JLabel("EMP");
		empathyField = new JTextField(SMALL_TEXT_FIELD);
		runLabel = new JLabel("Run");
		runField = new JTextField(SMALL_TEXT_FIELD);
		leapLabel = new JLabel("Leap");
		leapField = new JTextField(SMALL_TEXT_FIELD);
		liftLabel = new JLabel("Lift");
		liftField = new JTextField(SMALL_TEXT_FIELD);
		createStatArea();

		armorPanel = new JPanel(new GridBagLayout());
		armorPanel.setPreferredSize(new Dimension(MEDIUM_DIV_WIDTH, MEDIUM_DIV_HEIGHT));
		armorPanel.setBackground(Color.WHITE);
		locationLabel = new JLabel("Location");
		locationLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 3, Color.WHITE));
		formatCategoryTitle(locationLabel);
		headLabel = generateArmorLabel("Head", "1");
		torsoLabel = generateArmorLabel("Torso", "2-4");
		rightArmLabel = generateArmorLabel("R. Arm", "5");
		leftArmLabel = generateArmorLabel("L. Arm", "6");
		rightLegLabel = generateArmorLabel("R. Leg", "7-8");
		leftLegLabel = generateArmorLabel("L. Leg", "9-0");
		armorSPLabel = new JLabel("Armor SP");
		armorSPLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 3, Color.WHITE));
		formatCategoryTitle(armorSPLabel);
		headSPField = new JTextField(SMALL_TEXT_FIELD);
		headSPField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		torsoSPField = new JTextField(SMALL_TEXT_FIELD);
		torsoSPField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		rightArmSPField = new JTextField(SMALL_TEXT_FIELD);
		rightArmSPField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		leftArmSPField = new JTextField(SMALL_TEXT_FIELD);
		leftArmSPField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		rightLegSPField = new JTextField(SMALL_TEXT_FIELD);
		rightLegSPField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		leftLegSPField = new JTextField(SMALL_TEXT_FIELD);
		leftLegSPField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		createArmorArea();

		woundsPanel = new JPanel(new GridBagLayout());
		woundsPanel.setPreferredSize(new Dimension(MEDIUM_DIV_WIDTH, MEDIUM_DIV_HEIGHT));
		woundsPanel.setBackground(Color.PINK);

		savePanel = new JPanel();
		savePanel.setPreferredSize(new Dimension(SAVE_PANEL_WIDTH, MEDIUM_DIV_HEIGHT));
		savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.PAGE_AXIS));
		saveLabel = new JLabel("SAVE");
		saveField = new JTextField(SMALL_TEXT_FIELD);

		bodyTypeModifierPanel = new JPanel();
		bodyTypeModifierPanel.setPreferredSize(new Dimension(SAVE_PANEL_WIDTH, MEDIUM_DIV_HEIGHT));
		bodyTypeModifierPanel.setLayout(new BoxLayout(bodyTypeModifierPanel, BoxLayout.PAGE_AXIS));
		bodyTypeModifierLabel = new JLabel("BTM");
		bodyTypeModifierField = new JTextField(SMALL_TEXT_FIELD);

		stunPanel = new JPanel(new GridLayout(2, 5));
		stunPanel.setPreferredSize(new Dimension(STUN_PANEL_WIDTH, MEDIUM_DIV_HEIGHT));
		lightStunProgressPanel = generateStunGauge("LIGHT", 0, new Point(0, 0));
		seriousStunProgressPanel = generateStunGauge("SERIOUS", -1, new Point(0, 0));
		criticalStunProgressPanel = generateStunGauge("CRITICAL", -2, new Point(0, 0));
		mortal0StunProgressPanel = generateStunGauge("MORTAL0", -3, new Point(0, 0));
		mortal1StunProgressPanel = generateStunGauge("MORTAL1", -4, new Point(0, 0));
		mortal2StunProgressPanel = generateStunGauge("MORTAL2", -5, new Point(0, 0));
		mortal3StunProgressPanel = generateStunGauge("MORTAL3", -6, new Point(0, 0));
		mortal4StunProgressPanel = generateStunGauge("MORTAL4", -7, new Point(0, 0));
		mortal5StunProgressPanel = generateStunGauge("MORTAL5", -8, new Point(0, 0));
		mortal6StunProgressPanel = generateStunGauge("MORTAL6", -9, new Point(0, 0));
		createWoundsArea();

		skillsPanel = new JPanel(new GridBagLayout());
		skillsPanel.setPreferredSize(new Dimension(LARGE_DIV_WIDTH, LARGE_DIV_HEIGHT));

		skillColumns = new ArrayList<JPanel>();
		generateSkillColumns(skillsPanel, NUMBER_OF_SKILL_COLUMNS);

		skillsLabel = new JLabel("SKILLS");
		formatCategoryTitle(skillsLabel);
		specialAbilitiesPanel = new JPanel();
		specialAbilitiesPanel.setLayout(new BoxLayout(specialAbilitiesPanel, BoxLayout.PAGE_AXIS));
		specialAbilitiesTitle = new JLabel("SPECIAL ABILITIES");
		attractivenessPanel = new JPanel();
		attractivenessPanel.setLayout(new BoxLayout(attractivenessPanel, BoxLayout.PAGE_AXIS));
		attractivenessTitle = new JLabel("ATTRACTIVENESS");
		bodyPanel = new JPanel();
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.PAGE_AXIS));
		bodyTitle = new JLabel("BODY");
		coolPanel = new JPanel();
		coolPanel.setLayout(new BoxLayout(coolPanel, BoxLayout.PAGE_AXIS));
		coolTitle = new JLabel("COOL");
		empathyPanel = new JPanel();
		empathyPanel.setLayout(new BoxLayout(empathyPanel, BoxLayout.PAGE_AXIS));
		empathyTitle = new JLabel("EMPATHY");
		intelligencePanel = new JPanel();
		intelligencePanel.setLayout(new BoxLayout(intelligencePanel, BoxLayout.PAGE_AXIS));
		intelligenceTitle = new JLabel("INTELLIGENCE");
		reflexesPanel = new JPanel();
		reflexesPanel.setLayout(new BoxLayout(reflexesPanel, BoxLayout.PAGE_AXIS));
		reflexesTitle = new JLabel("REFLEXES");
		technicalAbilitiesPanel = new JPanel();
		technicalAbilitiesPanel.setLayout(new BoxLayout(technicalAbilitiesPanel, BoxLayout.PAGE_AXIS));
		technicalAbilitiesTitle = new JLabel("TECHNICAL ABILITIES");
		createSkillsArea();

		portraitLabel = new JLabel(new ImageIcon(CharacterCreationView.class.getResource("/img/Adam_MD_bust.png")));
		portraitLabel.setPreferredSize(new Dimension(300, 300));
		createPortraitArea();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setLocationRelativeTo(null); // Centers JFrame
		this.setContentPane(characterCreationPanel);
		this.pack();
	}

	public void initializeGUI() {
		populateArmorArea();
		populateSkillArea();
		populateStatArea();
		populateWoundsArea();
	}

	public void addSkill(String skillCategory, String skill, int rank) {
		JPanel targetPanel;
		JLabel targetTitle;
		JPanel skillPanel = new JPanel(new BorderLayout());
		JLabel skillLabel = new JLabel(skill);
		skillLabel.setFont(new Font("Garamond", Font.PLAIN, 10));
		JTextField rankField = new JTextField(Integer.toString(rank), SMALL_TEXT_FIELD);

		switch (skillCategory.toUpperCase()) {
		case "SPECIAL ABILITIES":
			targetPanel = specialAbilitiesPanel;
			targetTitle = specialAbilitiesTitle;
			break;
		case "ATTRACTIVENESS":
			targetPanel = attractivenessPanel;
			targetTitle = attractivenessTitle;
			break;
		case "BODY":
			targetPanel = bodyPanel;
			targetTitle = bodyTitle;
			break;
		case "COOL":
			targetPanel = coolPanel;
			targetTitle = coolTitle;
			break;
		case "EMPATHY":
			targetPanel = empathyPanel;
			targetTitle = empathyTitle;
			break;
		case "INTELLIGENCE":
			targetPanel = intelligencePanel;
			targetTitle = intelligenceTitle;
			break;
		case "REFLEXES":
			targetPanel = reflexesPanel;
			targetTitle = reflexesTitle;
			break;
		case "TECHNICAL ABILITY":
			targetPanel = technicalAbilitiesPanel;
			targetTitle = technicalAbilitiesTitle;
			break;
		default:
			targetPanel = null;
			targetTitle = null;
			break;
		}

		targetTitle.setFont(new Font("Garamond", Font.PLAIN, 12));
		targetTitle.setBackground(Color.MAGENTA);
		targetTitle.setOpaque(true);
		gbc.fill = GridBagConstraints.BOTH;

		// Adds Title to section if section is empty
		if (targetPanel.getComponentCount() == 0) {
			targetPanel.add(targetTitle);
			targetPanel.setBackground(Color.PINK);
			targetPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		}

		skillLabel.setBackground(Color.CYAN);
		skillLabel.setOpaque(true);
		skillPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		skillPanel.add(skillLabel, BorderLayout.CENTER);
		skillPanel.add(rankField, BorderLayout.LINE_END);
		targetPanel.add(skillPanel);

		if (targetPanel.getHeight() + targetPanel.getY() < skillColumns.get(0).getHeight()
				+ skillColumns.get(0).getY()) {
			skillColumns.get(0).add(targetPanel);
		} else if (targetPanel.getHeight() + targetPanel.getY() < skillColumns.get(1).getHeight()
				+ skillColumns.get(1).getY()) {
			skillColumns.get(1).add(targetPanel);
		} else {
			skillColumns.get(2).add(targetPanel);
		}
	}

	private void createArmorArea() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = (1 / 2);
		c.weighty = (3 / 36);
		characterCreationPanel.add(armorPanel, c);
	}

	private void createArmorComponent(Point point, Dimension dimension, Component component) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = point.x;
		c.gridy = point.y;
		c.gridwidth = dimension.width;
		c.gridheight = dimension.height;
		c.fill = GridBagConstraints.BOTH;
		armorPanel.add(component, c);
	}

	private void createCharacterPointArea() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = (1 / 2);
		c.weighty = (1 / 36);
		characterCreationPanel.add(characterPointsPanel, c);
		characterPointsPanel.add(characterPointsLabel);
		characterPointsPanel.add(characterPointsField);
		characterPointsPanel.add(characterPointsDecreaseButton);
		characterPointsPanel.add(characterPointsIncreaseButton);
	}

	private void createHandleArea() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = (1 / 2);
		c.weighty = (1 / 36);
		handlePanel.add(handleLabel);
		handlePanel.add(handleField);
		characterCreationPanel.add(handlePanel, c);
	}

	private void createPortraitArea() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 5;
		c.weightx = (1 / 2);
		c.weighty = (1 / 3);
		characterCreationPanel.add(portraitLabel, c);
	}

	private void createRoleArea() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = (1 / 2);
		c.weighty = (1 / 36);
		rolePanel.add(roleLabel);
		rolePanel.add(roleDropdown);
		characterCreationPanel.add(rolePanel, c);
	}

	private void createSkillsArea() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = (1 / 2);
		c.weighty = (24 / 36);
		characterCreationPanel.add(skillsPanel, c);
	}

	private void createStatArea() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = (1 / 2);
		c.weighty = (3 / 36);
		characterCreationPanel.add(statsPanel, c);
	}

	private void createStatAreaComponent(Point point, Dimension dimension, JLabel label, JTextField textField) {
		JPanel panel = new JPanel(new BorderLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = point.x;
		c.gridy = point.y;
		c.gridwidth = dimension.width;
		c.gridheight = dimension.height;

		panel.add(label, BorderLayout.CENTER);
		panel.add(textField, BorderLayout.EAST);
		statsPanel.add(panel, c);
	}

	private void createWoundsArea() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = (1 / 2);
		c.weighty = (3 / 36);
		characterCreationPanel.add(woundsPanel, c);
	}

	private void populateArmorArea() {
		GridBagConstraints c = new GridBagConstraints();

		createArmorComponent(new Point(0, 0), new Dimension(1, 1), locationLabel);
		createArmorComponent(new Point(1, 0), new Dimension(1, 1), headLabel);
		createArmorComponent(new Point(2, 0), new Dimension(1, 1), torsoLabel);
		createArmorComponent(new Point(3, 0), new Dimension(1, 1), rightArmLabel);
		createArmorComponent(new Point(4, 0), new Dimension(1, 1), leftArmLabel);
		createArmorComponent(new Point(5, 0), new Dimension(1, 1), rightLegLabel);
		createArmorComponent(new Point(6, 0), new Dimension(1, 1), leftLegLabel);
		createArmorComponent(new Point(0, 1), new Dimension(1, 1), armorSPLabel);
		createArmorComponent(new Point(1, 1), new Dimension(1, 1), headSPField);
		createArmorComponent(new Point(2, 1), new Dimension(1, 1), torsoSPField);
		createArmorComponent(new Point(3, 1), new Dimension(1, 1), rightArmSPField);
		createArmorComponent(new Point(4, 1), new Dimension(1, 1), leftArmSPField);
		createArmorComponent(new Point(5, 1), new Dimension(1, 1), rightLegSPField);
		createArmorComponent(new Point(6, 1), new Dimension(1, 1), leftLegSPField);
		c.gridx = 7;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.5;
		c.weighty = 0.0;
		armorPanel.add(Box.createHorizontalGlue(), c);
		armorPanel.revalidate();
	}

	private void populateSkillArea() {
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1.0;
		c.weighty = 0.0;
		skillsPanel.add(skillsLabel, c);

		for (int i = 0; i < skillColumns.size(); i++) {
			c.gridx = i;
			c.gridy = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 1 / skillColumns.size();
			c.weighty = 0.0;
			skillsPanel.add(skillColumns.get(i), c);
		}
		skillsPanel.revalidate();
	}

	private void populateStatArea() {
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.anchor = GridBagConstraints.WEST;
		statsPanel.add(statsLabel, c);

		createStatAreaComponent(new Point(0, 1), new Dimension(1, 1), intelligenceLabel, intelligenceField);
		createStatAreaComponent(new Point(1, 1), new Dimension(1, 1), reflexesLabel, reflexesField);
		createStatAreaComponent(new Point(2, 1), new Dimension(1, 1), technicalAbilityLabel, technicalAbilityField);
		createStatAreaComponent(new Point(3, 1), new Dimension(1, 1), coolLabel, coolField);
		createStatAreaComponent(new Point(0, 2), new Dimension(1, 1), attractivenessLabel, attractivenessField);
		createStatAreaComponent(new Point(1, 2), new Dimension(1, 1), luckLabel, luckField);
		createStatAreaComponent(new Point(2, 2), new Dimension(1, 1), movementAllowanceLabel, movementAllowanceField);
		createStatAreaComponent(new Point(3, 2), new Dimension(1, 1), bodyLabel, bodyField);
		createStatAreaComponent(new Point(0, 3), new Dimension(1, 1), empathyLabel, empathyField);
		createStatAreaComponent(new Point(1, 3), new Dimension(1, 1), runLabel, runField);
		createStatAreaComponent(new Point(2, 3), new Dimension(1, 1), leapLabel, leapField);
		createStatAreaComponent(new Point(3, 3), new Dimension(1, 1), liftLabel, liftField);
		c.gridx = 4;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.5;
		c.weighty = 0.0;
		statsPanel.add(Box.createHorizontalGlue(), c);
		statsPanel.revalidate();
	}

	private void populateWoundsArea() {
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = (1 / 8);
		c.weighty = 1.0;
		woundsPanel.add(savePanel, c);
		savePanel.add(saveLabel);
		savePanel.add(saveField);

		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = (1 / 8);
		c.weighty = 1.0;
		woundsPanel.add(bodyTypeModifierPanel, c);
		bodyTypeModifierPanel.add(bodyTypeModifierLabel);
		bodyTypeModifierPanel.add(bodyTypeModifierField);

		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = (6 / 8);
		c.weighty = 1.0;
		woundsPanel.add(stunPanel, c);
		stunPanel.add(lightStunProgressPanel);
		stunPanel.add(seriousStunProgressPanel);
		stunPanel.add(criticalStunProgressPanel);
		stunPanel.add(mortal0StunProgressPanel);
		stunPanel.add(mortal1StunProgressPanel);
		stunPanel.add(mortal2StunProgressPanel);
		stunPanel.add(mortal3StunProgressPanel);
		stunPanel.add(mortal4StunProgressPanel);
		stunPanel.add(mortal5StunProgressPanel);
		stunPanel.add(mortal6StunProgressPanel);
		woundsPanel.revalidate();
	}

	private void formatCategoryTitle(JLabel label) {
		label.setFont(new Font("SansSerif", Font.BOLD, 14));
		label.setForeground(Color.WHITE);
		label.setBackground(Color.BLACK);
		label.setOpaque(true);
	}

	private JLabel generateArmorLabel(String topText, String bottomText) {
		JLabel label = new JLabel(
				"<html><div style='text-align: center;'>" + topText + "<br>" + bottomText + "</div></html>");
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return label;

	}

	private void generateSkillColumns(JPanel panel, int numberOfColumns) {
		int columnWidth = panel.getPreferredSize().width / numberOfColumns;
		int columnHeight = panel.getPreferredSize().height;

		for (int i = 0; i < numberOfColumns; i++) {
			JPanel column = new JPanel();
			column.setLayout(new BoxLayout(column, BoxLayout.PAGE_AXIS));
			column.setMinimumSize(new Dimension(columnWidth, columnHeight));
			column.setMaximumSize(new Dimension(columnWidth, columnHeight));
			column.setPreferredSize(new Dimension(columnWidth, columnHeight));
			if (i == 0) {
				column.setBackground(Color.RED);
			} else if (i == 1) {
				column.setBackground(Color.GRAY);
			} else {
				column.setBackground(Color.BLUE);
			}

			skillColumns.add(column);
		}
	}

	private JPanel generateStunGauge(String gaugeName, int stunModifier, Point point) {
		JPanel panel = new JPanel(new GridLayout(3, 1));
		panel.setPreferredSize(new Dimension(67, 45));
		JLabel categoryLabel = new JLabel(gaugeName);
		JLabel stunModifierLabel = new JLabel("Stun   " + stunModifier);

		JPanel progressBar = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.RED);
				g.fillRect(point.x, point.y, 10, 10);

				int x = point.x;
				int y = point.y;
				int width = 10;
				int height = 10;
				g.setColor(Color.BLACK);
				for (int i = 0; i < 4; i++) {
					g.drawRect(x, y, width, height);
					x += 10;
				}
			}
		};

		panel.add(categoryLabel);
		panel.add(progressBar);
		panel.add(stunModifierLabel);

		return panel;
	}

	GridBagConstraints gbc = new GridBagConstraints();
	int columnCount = 0;
}
