package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.CharacterCreationController;
import model.Character;

public class CharacterCreationView extends JFrame {
	private static final int FRAME_WIDTH = 900;
	private static final int FRAME_HEIGHT = 600;
	private static final int TABBED_PANEL_WIDTH = FRAME_WIDTH;
	private static final int TABBED_PANEL_HEIGHT = FRAME_HEIGHT / 3;
	private static final int CHARACTER_PANEL_WIDTH = FRAME_WIDTH;
	private static final int CHARACTER_PANEL_HEIGHT = 2 * (FRAME_WIDTH / 3);
	private static final int NUMBER_OF_TEXTFIELD_COLUMNS = 3;
	private static final int MEDIUM_DIV_WIDTH = 2 * (CHARACTER_PANEL_WIDTH / 3);
	private static final int MEDIUM_DIV_HEIGHT = (CHARACTER_PANEL_HEIGHT / 4);
	private static final int SMALL_DIV_WIDTH = 2 * (CHARACTER_PANEL_WIDTH / 3);
	private static final int SMALL_DIV_HEIGHT = (MEDIUM_DIV_HEIGHT / 3);
	private static final int WOUND_DIV_WIDTH = MEDIUM_DIV_WIDTH;
	private static final int WOUND_DIV_HEIGHT = MEDIUM_DIV_HEIGHT;
	private static final int BODY_TYPE_MODIFIER_DIV_WIDTH = WOUND_DIV_WIDTH / 6;
	private static final int BODY_TYPE_MODIFIER_DIV_HEIGHT = WOUND_DIV_HEIGHT;
	private static final int SAVE_DIV_WIDTH = WOUND_DIV_WIDTH / 6;
	private static final int SAVE_DIV_HEIGHT = WOUND_DIV_HEIGHT;
	private static final int STUN_DIV_WIDTH = 2 * (WOUND_DIV_WIDTH / 3);
	private static final int STUN_DIV_HEIGHT = WOUND_DIV_HEIGHT;
	private static final int PORTRAIT_WIDTH = 300;
	private static final int PORTRAIT_HEIGHT = 300;

	private JPanel contentPane;
	private JTextField handleTextField;
	private JTextField intelligenceTextField;
	private JTextField reflexesTextField;
	private JTextField technicalAbilityTextField;
	private JTextField coolTextField;
	private JTextField attractivenessTextField;
	private JTextField luckTextField;
	private JTextField bodyTextField;
	private JTextField characterPointsTextField;
	private JTextField movementAllowanceTextField;
	private JTextField empathyTextField;
	private JTextField runTextField;
	private JTextField leapTextField;
	private JTextField liftTextField;
	private JTextField headSPTextField;
	private JTextField torsoSPTextField;
	private JTextField rightArmSPTextField;
	private JTextField leftArmSPTextField;
	private JTextField rightLegSPTextField;
	private JTextField leftLegSPTextField;
	private JTextField saveTextField;
	private JTextField bodyTypeModifierTextField;
	private JPanel specialAbilitiesSkillPanel;
	private JPanel attractivenessSkillPanel;
	private JPanel bodySkillPanel;
	private JPanel coolSkillPanel;
	private JPanel empathySkillPanel;
	private JPanel reflexesSkillPanel;
	private JPanel technicalAbilitiesSkillPanel;
	private JPanel intelligenceSkillPanel;
	private List<JPanel> stunProgressPanels = new ArrayList<JPanel>();

	
	/**
	 * Create the frame.
	 */
	public CharacterCreationView() {
		setTitle("Cyberpunk 2020 Character Creation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, FRAME_WIDTH, FRAME_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel characterStatPanel = new JPanel();
		contentPane.add(characterStatPanel);
		GridBagLayout gbl_characterStatPanel = new GridBagLayout();
		gbl_characterStatPanel.columnWeights = new double[] { 0.0, 0.0 };
		gbl_characterStatPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		characterStatPanel.setLayout(gbl_characterStatPanel);

		JPanel handlePanel = new JPanel();
		GridBagConstraints gbc_handlePanel = new GridBagConstraints();
		gbc_handlePanel.fill = GridBagConstraints.BOTH;
		gbc_handlePanel.weighty = 0.0833;
		gbc_handlePanel.weightx = 0.6667;
		gbc_handlePanel.insets = new Insets(0, 0, 5, 0);
		gbc_handlePanel.gridx = 0;
		gbc_handlePanel.gridy = 0;
		characterStatPanel.add(handlePanel, gbc_handlePanel);
		handlePanel.setPreferredSize(new Dimension(SMALL_DIV_WIDTH, SMALL_DIV_HEIGHT));
		GridBagLayout gbl_handlePanel = new GridBagLayout();
		gbl_handlePanel.columnWidths = new int[] { 59, 434, 0 };
		gbl_handlePanel.rowHeights = new int[] { 25, 0 };
		gbl_handlePanel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_handlePanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		handlePanel.setLayout(gbl_handlePanel);

		JLabel lblHandle = new JLabel("HANDLE");
		lblHandle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHandle.setOpaque(true);
		lblHandle.setBackground(Color.BLACK);
		lblHandle.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblHandle = new GridBagConstraints();
		gbc_lblHandle.ipadx = 10;
		gbc_lblHandle.insets = new Insets(0, 0, 0, 3);
		gbc_lblHandle.weightx = 0.25;
		gbc_lblHandle.fill = GridBagConstraints.BOTH;
		gbc_lblHandle.gridx = 0;
		gbc_lblHandle.gridy = 0;
		handlePanel.add(lblHandle, gbc_lblHandle);

		handleTextField = new JTextField();
		GridBagConstraints gbc_handleTextField = new GridBagConstraints();
		gbc_handleTextField.weightx = 0.75;
		gbc_handleTextField.fill = GridBagConstraints.BOTH;
		gbc_handleTextField.gridx = 1;
		gbc_handleTextField.gridy = 0;
		handlePanel.add(handleTextField, gbc_handleTextField);
		handleTextField.setColumns(10);

		JPanel rolePanel = new JPanel();
		GridBagConstraints gbc_rolePanel = new GridBagConstraints();
		gbc_rolePanel.fill = GridBagConstraints.BOTH;
		gbc_rolePanel.weighty = 0.0833;
		gbc_rolePanel.weightx = 0.6667;
		gbc_rolePanel.insets = new Insets(0, 0, 5, 0);
		gbc_rolePanel.gridx = 0;
		gbc_rolePanel.gridy = 1;
		characterStatPanel.add(rolePanel, gbc_rolePanel);
		rolePanel.setPreferredSize(new Dimension(SMALL_DIV_WIDTH, SMALL_DIV_HEIGHT));
		GridBagLayout gbl_rolePanel = new GridBagLayout();
		gbl_rolePanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_rolePanel.rowHeights = new int[] { 25, 0 };
		gbl_rolePanel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_rolePanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		rolePanel.setLayout(gbl_rolePanel);

		JLabel lblRole = new JLabel("ROLE");
		lblRole.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRole.setOpaque(true);
		lblRole.setBackground(Color.BLACK);
		lblRole.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblRole = new GridBagConstraints();
		gbc_lblRole.ipadx = 10;
		gbc_lblRole.fill = GridBagConstraints.BOTH;
		gbc_lblRole.insets = new Insets(0, 0, 0, 3);
		gbc_lblRole.gridx = 0;
		gbc_lblRole.gridy = 0;
		rolePanel.add(lblRole, gbc_lblRole);

		JComboBox<String> roleComboBox = new JComboBox<String>();
		roleComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Cop", "Corporate", "Fixer", "Media",
				"Netrunner", "Nomad", "Rockerboy", "Solo", "Techie" }));
		GridBagConstraints gbc_roleComboBox = new GridBagConstraints();
		gbc_roleComboBox.fill = GridBagConstraints.BOTH;
		gbc_roleComboBox.insets = new Insets(0, 0, 0, 3);
		gbc_roleComboBox.gridx = 1;
		gbc_roleComboBox.gridy = 0;
		rolePanel.add(roleComboBox, gbc_roleComboBox);

		JPanel characterPointsPanel = new JPanel();
		GridBagConstraints gbc_characterPointsPanel = new GridBagConstraints();
		gbc_characterPointsPanel.fill = GridBagConstraints.BOTH;
		gbc_characterPointsPanel.weighty = 0.0833;
		gbc_characterPointsPanel.weightx = 0.6667;
		gbc_characterPointsPanel.insets = new Insets(0, 0, 5, 0);
		gbc_characterPointsPanel.gridx = 0;
		gbc_characterPointsPanel.gridy = 2;
		characterStatPanel.add(characterPointsPanel, gbc_characterPointsPanel);
		characterPointsPanel.setPreferredSize(new Dimension(SMALL_DIV_WIDTH, SMALL_DIV_HEIGHT));
		GridBagLayout gbl_characterPointsPanel = new GridBagLayout();
		gbl_characterPointsPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_characterPointsPanel.rowHeights = new int[] { 25, 0 };
		gbl_characterPointsPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_characterPointsPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		characterPointsPanel.setLayout(gbl_characterPointsPanel);

		JLabel lblCharacterPoints = new JLabel("CHARACTER POINTS");
		lblCharacterPoints.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCharacterPoints.setOpaque(true);
		lblCharacterPoints.setBackground(Color.BLACK);
		lblCharacterPoints.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCharacterPoints = new GridBagConstraints();
		gbc_lblCharacterPoints.ipadx = 10;
		gbc_lblCharacterPoints.fill = GridBagConstraints.BOTH;
		gbc_lblCharacterPoints.insets = new Insets(0, 0, 0, 3);
		gbc_lblCharacterPoints.gridx = 0;
		gbc_lblCharacterPoints.gridy = 0;
		characterPointsPanel.add(lblCharacterPoints, gbc_lblCharacterPoints);

		characterPointsTextField = new JTextField();
		GridBagConstraints gbc_characterPointsTextField = new GridBagConstraints();
		gbc_characterPointsTextField.fill = GridBagConstraints.BOTH;
		gbc_characterPointsTextField.insets = new Insets(0, 0, 0, 5);
		gbc_characterPointsTextField.gridx = 1;
		gbc_characterPointsTextField.gridy = 0;
		characterPointsPanel.add(characterPointsTextField, gbc_characterPointsTextField);
		characterPointsTextField.setColumns(NUMBER_OF_TEXTFIELD_COLUMNS);

		JButton decreaseCharacterPointsButton = new JButton("<");
		GridBagConstraints gbc_decreaseCharacterPointsButton = new GridBagConstraints();
		gbc_decreaseCharacterPointsButton.fill = GridBagConstraints.BOTH;
		gbc_decreaseCharacterPointsButton.insets = new Insets(0, 0, 0, 5);
		gbc_decreaseCharacterPointsButton.gridx = 2;
		gbc_decreaseCharacterPointsButton.gridy = 0;
		characterPointsPanel.add(decreaseCharacterPointsButton, gbc_decreaseCharacterPointsButton);

		JButton increaseCharacterPointsButton = new JButton(">");
		GridBagConstraints gbc_increaseCharacterPointsButton = new GridBagConstraints();
		gbc_increaseCharacterPointsButton.fill = GridBagConstraints.BOTH;
		gbc_increaseCharacterPointsButton.gridx = 3;
		gbc_increaseCharacterPointsButton.gridy = 0;
		characterPointsPanel.add(increaseCharacterPointsButton, gbc_increaseCharacterPointsButton);

		JPanel statPanel = new JPanel();
		GridBagConstraints gbc_statPanel = new GridBagConstraints();
		gbc_statPanel.fill = GridBagConstraints.BOTH;
		gbc_statPanel.weighty = 0.25;
		gbc_statPanel.weightx = 0.6667;
		gbc_statPanel.insets = new Insets(0, 0, 5, 0);
		gbc_statPanel.gridx = 0;
		gbc_statPanel.gridy = 3;
		characterStatPanel.add(statPanel, gbc_statPanel);
		statPanel.setPreferredSize(new Dimension(MEDIUM_DIV_WIDTH, MEDIUM_DIV_HEIGHT));
		GridBagLayout gbl_statPanel = new GridBagLayout();
		gbl_statPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_statPanel.rowHeights = new int[] { 25, 0, 0, 0, 0, 0 };
		gbl_statPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_statPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		statPanel.setLayout(gbl_statPanel);

		JLabel lblStats = new JLabel("STATS");
		lblStats.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStats.setOpaque(true);
		lblStats.setBackground(Color.BLACK);
		lblStats.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblStats = new GridBagConstraints();
		gbc_lblStats.ipadx = 10;
		gbc_lblStats.fill = GridBagConstraints.VERTICAL;
		gbc_lblStats.anchor = GridBagConstraints.WEST;
		gbc_lblStats.insets = new Insets(0, 0, 5, 0);
		gbc_lblStats.gridx = 0;
		gbc_lblStats.gridy = 0;
		statPanel.add(lblStats, gbc_lblStats);

		JPanel technicalAbilityStatPanel = new JPanel();
		technicalAbilityStatPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_technicalAbilityStatPanel = new GridBagConstraints();
		gbc_technicalAbilityStatPanel.weightx = 0.25;
		gbc_technicalAbilityStatPanel.insets = new Insets(0, 0, 5, 5);
		gbc_technicalAbilityStatPanel.fill = GridBagConstraints.BOTH;
		gbc_technicalAbilityStatPanel.gridx = 2;
		gbc_technicalAbilityStatPanel.gridy = 1;
		statPanel.add(technicalAbilityStatPanel, gbc_technicalAbilityStatPanel);
		technicalAbilityStatPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblTech = new JLabel("TECH");
		technicalAbilityStatPanel.add(lblTech, BorderLayout.CENTER);

		technicalAbilityTextField = new JTextField();
		technicalAbilityStatPanel.add(technicalAbilityTextField, BorderLayout.EAST);
		technicalAbilityTextField.setColumns(NUMBER_OF_TEXTFIELD_COLUMNS);

		JPanel intelligenceStatPanel = new JPanel();
		intelligenceStatPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_intelligenceStatPanel = new GridBagConstraints();
		gbc_intelligenceStatPanel.weightx = 0.25;
		gbc_intelligenceStatPanel.insets = new Insets(0, 0, 5, 5);
		gbc_intelligenceStatPanel.fill = GridBagConstraints.BOTH;
		gbc_intelligenceStatPanel.gridx = 0;
		gbc_intelligenceStatPanel.gridy = 1;
		statPanel.add(intelligenceStatPanel, gbc_intelligenceStatPanel);
		intelligenceStatPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblIntelligence = new JLabel("INT");
		intelligenceStatPanel.add(lblIntelligence, BorderLayout.CENTER);

		intelligenceTextField = new JTextField();
		intelligenceStatPanel.add(intelligenceTextField, BorderLayout.EAST);
		intelligenceTextField.setColumns(NUMBER_OF_TEXTFIELD_COLUMNS);

		JPanel reflexesStatPanel = new JPanel();
		reflexesStatPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_reflexesStatPanel = new GridBagConstraints();
		gbc_reflexesStatPanel.weightx = 0.25;
		gbc_reflexesStatPanel.insets = new Insets(0, 0, 5, 5);
		gbc_reflexesStatPanel.fill = GridBagConstraints.BOTH;
		gbc_reflexesStatPanel.gridx = 1;
		gbc_reflexesStatPanel.gridy = 1;
		statPanel.add(reflexesStatPanel, gbc_reflexesStatPanel);
		reflexesStatPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblReflexes = new JLabel("REF");
		reflexesStatPanel.add(lblReflexes, BorderLayout.CENTER);

		reflexesTextField = new JTextField();
		reflexesStatPanel.add(reflexesTextField, BorderLayout.EAST);
		reflexesTextField.setColumns(NUMBER_OF_TEXTFIELD_COLUMNS);

		JPanel coolStatPanel = new JPanel();
		coolStatPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_coolStatPanel = new GridBagConstraints();
		gbc_coolStatPanel.weightx = 0.25;
		gbc_coolStatPanel.insets = new Insets(0, 0, 5, 0);
		gbc_coolStatPanel.fill = GridBagConstraints.BOTH;
		gbc_coolStatPanel.gridx = 3;
		gbc_coolStatPanel.gridy = 1;
		statPanel.add(coolStatPanel, gbc_coolStatPanel);
		coolStatPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblCool = new JLabel("COOL");
		coolStatPanel.add(lblCool, BorderLayout.CENTER);

		coolTextField = new JTextField();
		coolStatPanel.add(coolTextField, BorderLayout.EAST);
		coolTextField.setColumns(NUMBER_OF_TEXTFIELD_COLUMNS);

		JPanel attractivenessStatPanel = new JPanel();
		attractivenessStatPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_attractivenessStatPanel = new GridBagConstraints();
		gbc_attractivenessStatPanel.weightx = 0.25;
		gbc_attractivenessStatPanel.insets = new Insets(0, 0, 5, 5);
		gbc_attractivenessStatPanel.fill = GridBagConstraints.BOTH;
		gbc_attractivenessStatPanel.gridx = 0;
		gbc_attractivenessStatPanel.gridy = 2;
		statPanel.add(attractivenessStatPanel, gbc_attractivenessStatPanel);
		attractivenessStatPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblAttractiveness = new JLabel("ATTR");
		attractivenessStatPanel.add(lblAttractiveness, BorderLayout.CENTER);

		attractivenessTextField = new JTextField();
		attractivenessStatPanel.add(attractivenessTextField, BorderLayout.EAST);
		attractivenessTextField.setColumns(NUMBER_OF_TEXTFIELD_COLUMNS);

		JPanel luckPanel = new JPanel();
		luckPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_luckPanel = new GridBagConstraints();
		gbc_luckPanel.weightx = 0.25;
		gbc_luckPanel.insets = new Insets(0, 0, 5, 5);
		gbc_luckPanel.fill = GridBagConstraints.BOTH;
		gbc_luckPanel.gridx = 1;
		gbc_luckPanel.gridy = 2;
		statPanel.add(luckPanel, gbc_luckPanel);
		luckPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblLuck = new JLabel("LUCK");
		luckPanel.add(lblLuck, BorderLayout.CENTER);

		luckTextField = new JTextField();
		luckPanel.add(luckTextField, BorderLayout.EAST);
		luckTextField.setColumns(NUMBER_OF_TEXTFIELD_COLUMNS);

		JPanel bodyStatPanel = new JPanel();
		bodyStatPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_bodyStatPanel = new GridBagConstraints();
		gbc_bodyStatPanel.insets = new Insets(0, 0, 5, 0);
		gbc_bodyStatPanel.fill = GridBagConstraints.BOTH;
		gbc_bodyStatPanel.gridx = 3;
		gbc_bodyStatPanel.gridy = 2;
		statPanel.add(bodyStatPanel, gbc_bodyStatPanel);
		bodyStatPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblBody = new JLabel("BODY");
		bodyStatPanel.add(lblBody, BorderLayout.CENTER);

		bodyTextField = new JTextField();
		bodyStatPanel.add(bodyTextField, BorderLayout.EAST);
		bodyTextField.setColumns(NUMBER_OF_TEXTFIELD_COLUMNS);

		JPanel movementAllowancePanel = new JPanel();
		movementAllowancePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_movementAllowancePanel = new GridBagConstraints();
		gbc_movementAllowancePanel.insets = new Insets(0, 0, 5, 5);
		gbc_movementAllowancePanel.fill = GridBagConstraints.BOTH;
		gbc_movementAllowancePanel.gridx = 2;
		gbc_movementAllowancePanel.gridy = 2;
		statPanel.add(movementAllowancePanel, gbc_movementAllowancePanel);
		movementAllowancePanel.setLayout(new BorderLayout(0, 0));

		JLabel lblMovementAllowance = new JLabel("MA");
		movementAllowancePanel.add(lblMovementAllowance);

		movementAllowanceTextField = new JTextField();
		movementAllowancePanel.add(movementAllowanceTextField, BorderLayout.EAST);
		movementAllowanceTextField.setColumns(NUMBER_OF_TEXTFIELD_COLUMNS);

		JPanel empathyStatPanel = new JPanel();
		empathyStatPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_empathyStatPanel = new GridBagConstraints();
		gbc_empathyStatPanel.insets = new Insets(0, 0, 5, 5);
		gbc_empathyStatPanel.fill = GridBagConstraints.BOTH;
		gbc_empathyStatPanel.gridx = 0;
		gbc_empathyStatPanel.gridy = 3;
		statPanel.add(empathyStatPanel, gbc_empathyStatPanel);
		empathyStatPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblEmpathy = new JLabel("EMP");
		empathyStatPanel.add(lblEmpathy, BorderLayout.CENTER);

		empathyTextField = new JTextField();
		empathyStatPanel.add(empathyTextField, BorderLayout.EAST);
		empathyTextField.setColumns(NUMBER_OF_TEXTFIELD_COLUMNS);

		JPanel runPanel = new JPanel();
		runPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_runPanel = new GridBagConstraints();
		gbc_runPanel.insets = new Insets(0, 0, 5, 5);
		gbc_runPanel.fill = GridBagConstraints.BOTH;
		gbc_runPanel.gridx = 1;
		gbc_runPanel.gridy = 3;
		statPanel.add(runPanel, gbc_runPanel);
		runPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblRun = new JLabel("Run");
		runPanel.add(lblRun, BorderLayout.CENTER);

		runTextField = new JTextField();
		runPanel.add(runTextField, BorderLayout.EAST);
		runTextField.setColumns(NUMBER_OF_TEXTFIELD_COLUMNS);

		JPanel leapPanel = new JPanel();
		leapPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_leapPanel = new GridBagConstraints();
		gbc_leapPanel.insets = new Insets(0, 0, 5, 5);
		gbc_leapPanel.fill = GridBagConstraints.BOTH;
		gbc_leapPanel.gridx = 2;
		gbc_leapPanel.gridy = 3;
		statPanel.add(leapPanel, gbc_leapPanel);
		leapPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblLeap = new JLabel("Leap");
		leapPanel.add(lblLeap, BorderLayout.CENTER);

		leapTextField = new JTextField();
		leapPanel.add(leapTextField, BorderLayout.EAST);
		leapTextField.setColumns(NUMBER_OF_TEXTFIELD_COLUMNS);

		JPanel liftPanel = new JPanel();
		liftPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_liftPanel = new GridBagConstraints();
		gbc_liftPanel.insets = new Insets(0, 0, 5, 0);
		gbc_liftPanel.fill = GridBagConstraints.BOTH;
		gbc_liftPanel.gridx = 3;
		gbc_liftPanel.gridy = 3;
		statPanel.add(liftPanel, gbc_liftPanel);
		liftPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblLift = new JLabel("Lift");
		liftPanel.add(lblLift);

		liftTextField = new JTextField();
		liftPanel.add(liftTextField, BorderLayout.EAST);
		liftTextField.setColumns(NUMBER_OF_TEXTFIELD_COLUMNS);

		JPanel armorPanel = new JPanel();
		GridBagConstraints gbc_armorPanel = new GridBagConstraints();
		gbc_armorPanel.anchor = GridBagConstraints.WEST;
		gbc_armorPanel.fill = GridBagConstraints.VERTICAL;
		gbc_armorPanel.weighty = 0.25;
		gbc_armorPanel.weightx = 0.6667;
		gbc_armorPanel.insets = new Insets(0, 0, 5, 0);
		gbc_armorPanel.gridx = 0;
		gbc_armorPanel.gridy = 4;
		characterStatPanel.add(armorPanel, gbc_armorPanel);
		armorPanel.setPreferredSize(new Dimension(MEDIUM_DIV_WIDTH, MEDIUM_DIV_HEIGHT));
		GridBagLayout gbl_armorPanel = new GridBagLayout();
		gbl_armorPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_armorPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_armorPanel.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gbl_armorPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		armorPanel.setLayout(gbl_armorPanel);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLocation.setOpaque(true);
		lblLocation.setBackground(Color.BLACK);
		lblLocation.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblLocation = new GridBagConstraints();
		gbc_lblLocation.fill = GridBagConstraints.BOTH;
		gbc_lblLocation.insets = new Insets(0, 0, 1, 4);
		gbc_lblLocation.gridx = 0;
		gbc_lblLocation.gridy = 0;
		armorPanel.add(lblLocation, gbc_lblLocation);

		JLabel lblHead = new JLabel("<html><div style='text-align: center;'>Head<br>1</div></html>");
		lblHead.setHorizontalAlignment(SwingConstants.CENTER);
		lblHead.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_lblHead = new GridBagConstraints();
		gbc_lblHead.ipadx = 4;
		gbc_lblHead.fill = GridBagConstraints.BOTH;
		gbc_lblHead.gridx = 1;
		gbc_lblHead.gridy = 0;
		armorPanel.add(lblHead, gbc_lblHead);

		JLabel lblTorso = new JLabel("<html><div style='text-align: center;'>Torso<br>2-4</div></html>");
		lblTorso.setHorizontalAlignment(SwingConstants.CENTER);
		lblTorso.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_lblTorso = new GridBagConstraints();
		gbc_lblTorso.ipadx = 4;
		gbc_lblTorso.fill = GridBagConstraints.BOTH;
		gbc_lblTorso.gridy = 0;
		gbc_lblTorso.gridx = 2;
		armorPanel.add(lblTorso, gbc_lblTorso);

		JLabel lblRightArm = new JLabel("<html><div style='text-align: center;'>R.Arm<br>5</div></html>");
		lblRightArm.setHorizontalAlignment(SwingConstants.CENTER);
		lblRightArm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_lblRightArm = new GridBagConstraints();
		gbc_lblRightArm.ipadx = 4;
		gbc_lblRightArm.fill = GridBagConstraints.BOTH;
		gbc_lblRightArm.gridx = 3;
		gbc_lblRightArm.gridy = 0;
		armorPanel.add(lblRightArm, gbc_lblRightArm);

		JLabel lblLeftArm = new JLabel("<html><div style='text-align: center;'>L.Arm<br>6</div></html>");
		lblLeftArm.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeftArm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_lblLeftArm = new GridBagConstraints();
		gbc_lblLeftArm.ipadx = 4;
		gbc_lblLeftArm.fill = GridBagConstraints.BOTH;
		gbc_lblLeftArm.gridy = 0;
		gbc_lblLeftArm.gridx = 4;
		armorPanel.add(lblLeftArm, gbc_lblLeftArm);

		JLabel lblRightLeg = new JLabel("<html><div style='text-align: center;'>R.Leg<br>7-8</div></html>");
		lblRightLeg.setHorizontalAlignment(SwingConstants.CENTER);
		lblRightLeg.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_lblRightLeg = new GridBagConstraints();
		gbc_lblRightLeg.ipadx = 4;
		gbc_lblRightLeg.fill = GridBagConstraints.BOTH;
		gbc_lblRightLeg.gridy = 0;
		gbc_lblRightLeg.gridx = 5;
		armorPanel.add(lblRightLeg, gbc_lblRightLeg);

		JLabel lblLeftLeg = new JLabel("<html><div style='text-align: center;'>L.Leg<br>9-0</div></html>");
		lblLeftLeg.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeftLeg.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_lblLeftLeg = new GridBagConstraints();
		gbc_lblLeftLeg.ipadx = 4;
		gbc_lblLeftLeg.fill = GridBagConstraints.BOTH;
		gbc_lblLeftLeg.gridy = 0;
		gbc_lblLeftLeg.gridx = 6;
		armorPanel.add(lblLeftLeg, gbc_lblLeftLeg);

		JLabel lblArmorSp = new JLabel("Armor SP");
		lblArmorSp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblArmorSp.setOpaque(true);
		lblArmorSp.setBackground(Color.BLACK);
		lblArmorSp.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblArmorSp = new GridBagConstraints();
		gbc_lblArmorSp.fill = GridBagConstraints.VERTICAL;
		gbc_lblArmorSp.insets = new Insets(1, 0, 0, 4);
		gbc_lblArmorSp.gridx = 0;
		gbc_lblArmorSp.gridy = 1;
		armorPanel.add(lblArmorSp, gbc_lblArmorSp);

		headSPTextField = new JTextField();
		headSPTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_headSPTextField = new GridBagConstraints();
		gbc_headSPTextField.ipadx = 4;
		gbc_headSPTextField.fill = GridBagConstraints.BOTH;
		gbc_headSPTextField.gridx = 1;
		gbc_headSPTextField.gridy = 1;
		armorPanel.add(headSPTextField, gbc_headSPTextField);
		headSPTextField.setColumns(10);

		torsoSPTextField = new JTextField();
		torsoSPTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_torsoSPTextField = new GridBagConstraints();
		gbc_torsoSPTextField.ipadx = 4;
		gbc_torsoSPTextField.fill = GridBagConstraints.BOTH;
		gbc_torsoSPTextField.gridx = 2;
		gbc_torsoSPTextField.gridy = 1;
		armorPanel.add(torsoSPTextField, gbc_torsoSPTextField);
		torsoSPTextField.setColumns(10);

		rightArmSPTextField = new JTextField();
		rightArmSPTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_rightArmSPTextField = new GridBagConstraints();
		gbc_rightArmSPTextField.ipadx = 4;
		gbc_rightArmSPTextField.fill = GridBagConstraints.BOTH;
		gbc_rightArmSPTextField.gridx = 3;
		gbc_rightArmSPTextField.gridy = 1;
		armorPanel.add(rightArmSPTextField, gbc_rightArmSPTextField);
		rightArmSPTextField.setColumns(10);

		leftArmSPTextField = new JTextField();
		leftArmSPTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_leftArmSPTextField = new GridBagConstraints();
		gbc_leftArmSPTextField.ipadx = 4;
		gbc_leftArmSPTextField.fill = GridBagConstraints.BOTH;
		gbc_leftArmSPTextField.gridx = 4;
		gbc_leftArmSPTextField.gridy = 1;
		armorPanel.add(leftArmSPTextField, gbc_leftArmSPTextField);
		leftArmSPTextField.setColumns(10);

		rightLegSPTextField = new JTextField();
		rightLegSPTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_rightLegSPTextField = new GridBagConstraints();
		gbc_rightLegSPTextField.ipadx = 4;
		gbc_rightLegSPTextField.fill = GridBagConstraints.BOTH;
		gbc_rightLegSPTextField.gridx = 5;
		gbc_rightLegSPTextField.gridy = 1;
		armorPanel.add(rightLegSPTextField, gbc_rightLegSPTextField);
		rightLegSPTextField.setColumns(10);

		leftLegSPTextField = new JTextField();
		leftLegSPTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_leftLegSPTextField = new GridBagConstraints();
		gbc_leftLegSPTextField.ipadx = 4;
		gbc_leftLegSPTextField.fill = GridBagConstraints.BOTH;
		gbc_leftLegSPTextField.gridx = 6;
		gbc_leftLegSPTextField.gridy = 1;
		armorPanel.add(leftLegSPTextField, gbc_leftLegSPTextField);
		leftLegSPTextField.setColumns(10);

		JPanel woundsPanel = new JPanel();
		GridBagConstraints gbc_woundsPanel = new GridBagConstraints();
		gbc_woundsPanel.fill = GridBagConstraints.BOTH;
		gbc_woundsPanel.weightx = 0.6667;
		gbc_woundsPanel.weighty = 0.25;
		gbc_woundsPanel.insets = new Insets(0, 0, 5, 0);
		gbc_woundsPanel.gridx = 0;
		gbc_woundsPanel.gridy = 5;
		characterStatPanel.add(woundsPanel, gbc_woundsPanel);
		woundsPanel.setPreferredSize(new Dimension(WOUND_DIV_WIDTH, WOUND_DIV_HEIGHT));
		GridBagLayout gbl_woundsPanel = new GridBagLayout();
		gbl_woundsPanel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_woundsPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_woundsPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_woundsPanel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		woundsPanel.setLayout(gbl_woundsPanel);

		JPanel savePanel = new JPanel();
		savePanel.setPreferredSize(new Dimension(SAVE_DIV_WIDTH, SAVE_DIV_HEIGHT));
		GridBagConstraints gbc_savePanel = new GridBagConstraints();
		gbc_savePanel.weighty = 1.0;
		gbc_savePanel.fill = GridBagConstraints.BOTH;
		gbc_savePanel.weightx = 0.1667;
		gbc_savePanel.insets = new Insets(0, 0, 0, 2);
		gbc_savePanel.gridx = 0;
		gbc_savePanel.gridy = 0;
		woundsPanel.add(savePanel, gbc_savePanel);
		savePanel.setLayout(new BorderLayout(0, 0));

		JLabel lblSave = new JLabel("SAVE");
		lblSave.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSave.setHorizontalAlignment(SwingConstants.CENTER);
		savePanel.add(lblSave, BorderLayout.NORTH);

		saveTextField = new JTextField();
		saveTextField.setBorder(new LineBorder(Color.BLACK));
		saveTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		savePanel.add(saveTextField);
		saveTextField.setColumns(3);

		JPanel bodyTypeModifierPanel = new JPanel();
		bodyTypeModifierPanel
				.setPreferredSize(new Dimension(BODY_TYPE_MODIFIER_DIV_WIDTH, BODY_TYPE_MODIFIER_DIV_HEIGHT));
		GridBagConstraints gbc_bodyTypeModifierPanel = new GridBagConstraints();
		gbc_bodyTypeModifierPanel.insets = new Insets(0, 2, 0, 4);
		gbc_bodyTypeModifierPanel.weighty = 1.0;
		gbc_bodyTypeModifierPanel.weightx = 0.1667;
		gbc_bodyTypeModifierPanel.fill = GridBagConstraints.BOTH;
		gbc_bodyTypeModifierPanel.gridx = 1;
		gbc_bodyTypeModifierPanel.gridy = 0;
		woundsPanel.add(bodyTypeModifierPanel, gbc_bodyTypeModifierPanel);
		bodyTypeModifierPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblBodyTypeModifier = new JLabel("BTM");
		lblBodyTypeModifier.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblBodyTypeModifier.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBodyTypeModifier.setHorizontalAlignment(SwingConstants.CENTER);
		bodyTypeModifierPanel.add(lblBodyTypeModifier, BorderLayout.NORTH);

		bodyTypeModifierTextField = new JTextField();
		bodyTypeModifierTextField.setBorder(new LineBorder(Color.BLACK));
		bodyTypeModifierTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		bodyTypeModifierPanel.add(bodyTypeModifierTextField);
		bodyTypeModifierTextField.setColumns(3);

		JPanel stunPanel = new JPanel();
		stunPanel.setPreferredSize(new Dimension(STUN_DIV_WIDTH, STUN_DIV_HEIGHT));
		GridBagConstraints gbc_stunPanel = new GridBagConstraints();
		gbc_stunPanel.weighty = 1.0;
		gbc_stunPanel.weightx = 0.6667;
		gbc_stunPanel.fill = GridBagConstraints.BOTH;
		gbc_stunPanel.gridx = 2;
		gbc_stunPanel.gridy = 0;
		woundsPanel.add(stunPanel, gbc_stunPanel);
		stunPanel.setLayout(new GridLayout(2, 5, 0, 0));

		JPanel lightPanel = new JPanel();
		stunPanel.add(lightPanel);
		lightPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblLightStun = new JLabel("LIGHT");
		lblLightStun.setFont(new Font("Tahoma", Font.BOLD, 8));
		lblLightStun.setHorizontalAlignment(SwingConstants.CENTER);
		lightPanel.add(lblLightStun, BorderLayout.NORTH);

		JPanel lightStunProgressPanel = new StunGaugePanel();
		lightStunProgressPanel.setBorder(new EmptyBorder(2, 3, 2, 2));
		lightPanel.add(lightStunProgressPanel, BorderLayout.CENTER);
		stunProgressPanels.add(0, lightStunProgressPanel);

		JLabel lblLightStunModifier = new JLabel("Stun 0");
		lblLightStunModifier.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblLightStunModifier.setHorizontalAlignment(SwingConstants.CENTER);
		lblLightStunModifier.setOpaque(true);
		lblLightStunModifier.setBackground(Color.BLACK);
		lblLightStunModifier.setForeground(Color.WHITE);
		lightPanel.add(lblLightStunModifier, BorderLayout.SOUTH);

		JPanel seriousPanel = new JPanel();
		stunPanel.add(seriousPanel);
		seriousPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblSerious = new JLabel("SERIOUS");
		lblSerious.setFont(new Font("Tahoma", Font.BOLD, 8));
		lblSerious.setHorizontalAlignment(SwingConstants.CENTER);
		seriousPanel.add(lblSerious, BorderLayout.NORTH);

		JPanel seriousStunProgressPanel = new StunGaugePanel();
		seriousStunProgressPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		seriousPanel.add(seriousStunProgressPanel, BorderLayout.CENTER);
		stunProgressPanels.add(1, seriousStunProgressPanel);

		JLabel lblSeriousStunModifier = new JLabel("Stun -1");
		lblSeriousStunModifier.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblSeriousStunModifier.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeriousStunModifier.setOpaque(true);
		lblSeriousStunModifier.setBackground(Color.BLACK);
		lblSeriousStunModifier.setForeground(Color.WHITE);
		seriousPanel.add(lblSeriousStunModifier, BorderLayout.SOUTH);

		JPanel criticalPanel = new JPanel();
		stunPanel.add(criticalPanel);
		criticalPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblCritical = new JLabel("CRITICAL");
		lblCritical.setFont(new Font("Tahoma", Font.BOLD, 8));
		lblCritical.setHorizontalAlignment(SwingConstants.CENTER);
		criticalPanel.add(lblCritical, BorderLayout.NORTH);

		JPanel criticalStunProgressPanel = new StunGaugePanel();
		criticalStunProgressPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		criticalPanel.add(criticalStunProgressPanel, BorderLayout.CENTER);
		stunProgressPanels.add(2, criticalStunProgressPanel);

		JLabel lblCriticalStunModifier = new JLabel("Stun -2");
		lblCriticalStunModifier.setHorizontalAlignment(SwingConstants.CENTER);
		lblCriticalStunModifier.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblCriticalStunModifier.setOpaque(true);
		lblCriticalStunModifier.setBackground(Color.BLACK);
		lblCriticalStunModifier.setForeground(Color.WHITE);
		criticalPanel.add(lblCriticalStunModifier, BorderLayout.SOUTH);

		JPanel mortal0Panel = new JPanel();
		stunPanel.add(mortal0Panel);
		mortal0Panel.setLayout(new BorderLayout(0, 0));

		JLabel lblMortal0 = new JLabel("MORTAL0");
		lblMortal0.setFont(new Font("Tahoma", Font.BOLD, 8));
		lblMortal0.setHorizontalAlignment(SwingConstants.CENTER);
		mortal0Panel.add(lblMortal0, BorderLayout.NORTH);

		JPanel mortal0StunProgressPanel = new StunGaugePanel();
		mortal0StunProgressPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		mortal0Panel.add(mortal0StunProgressPanel, BorderLayout.CENTER);
		stunProgressPanels.add(3, mortal0StunProgressPanel);

		JLabel lblMortal0StunModifier = new JLabel("Stun -3");
		lblMortal0StunModifier.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblMortal0StunModifier.setHorizontalAlignment(SwingConstants.CENTER);
		lblMortal0StunModifier.setOpaque(true);
		lblMortal0StunModifier.setBackground(Color.BLACK);
		lblMortal0StunModifier.setForeground(Color.WHITE);
		mortal0Panel.add(lblMortal0StunModifier, BorderLayout.SOUTH);

		JPanel mortal1Panel = new JPanel();
		stunPanel.add(mortal1Panel);
		mortal1Panel.setLayout(new BorderLayout(0, 0));

		JLabel lblMortal1 = new JLabel("MORTAL1");
		lblMortal1.setFont(new Font("Tahoma", Font.BOLD, 8));
		lblMortal1.setHorizontalAlignment(SwingConstants.CENTER);
		mortal1Panel.add(lblMortal1, BorderLayout.NORTH);

		JPanel mortal1StunProgressPanel = new StunGaugePanel();
		mortal1StunProgressPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		mortal1Panel.add(mortal1StunProgressPanel, BorderLayout.CENTER);
		stunProgressPanels.add(4, mortal1StunProgressPanel);

		JLabel lblMortal1StunModifier = new JLabel("Stun -4");
		lblMortal1StunModifier.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblMortal1StunModifier.setHorizontalAlignment(SwingConstants.CENTER);
		lblMortal1StunModifier.setOpaque(true);
		lblMortal1StunModifier.setBackground(Color.BLACK);
		lblMortal1StunModifier.setForeground(Color.WHITE);
		mortal1Panel.add(lblMortal1StunModifier, BorderLayout.SOUTH);

		JPanel mortal2Panel = new JPanel();
		stunPanel.add(mortal2Panel);
		mortal2Panel.setLayout(new BorderLayout(0, 0));

		JLabel lblMortal2 = new JLabel("MORTAL2");
		lblMortal2.setFont(new Font("Tahoma", Font.BOLD, 8));
		lblMortal2.setHorizontalAlignment(SwingConstants.CENTER);
		mortal2Panel.add(lblMortal2, BorderLayout.NORTH);

		JPanel mortal2StunProgressPanel = new StunGaugePanel();
		mortal2StunProgressPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		mortal2Panel.add(mortal2StunProgressPanel, BorderLayout.CENTER);
		stunProgressPanels.add(5, mortal2StunProgressPanel);

		JLabel lblMortal2StunModifier = new JLabel("Stun -5");
		lblMortal2StunModifier.setHorizontalAlignment(SwingConstants.CENTER);
		lblMortal2StunModifier.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblMortal2StunModifier.setOpaque(true);
		lblMortal2StunModifier.setBackground(Color.BLACK);
		lblMortal2StunModifier.setForeground(Color.WHITE);
		mortal2Panel.add(lblMortal2StunModifier, BorderLayout.SOUTH);

		JPanel mortal3Panel = new JPanel();
		stunPanel.add(mortal3Panel);
		mortal3Panel.setLayout(new BorderLayout(0, 0));

		JLabel lblMortal3 = new JLabel("MORTAL3");
		lblMortal3.setHorizontalAlignment(SwingConstants.CENTER);
		lblMortal3.setFont(new Font("Tahoma", Font.BOLD, 8));
		mortal3Panel.add(lblMortal3, BorderLayout.NORTH);

		JPanel mortal3StunProgressPanel = new StunGaugePanel();
		mortal3StunProgressPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		mortal3Panel.add(mortal3StunProgressPanel, BorderLayout.CENTER);
		stunProgressPanels.add(6, mortal3StunProgressPanel);

		JLabel lblMortal3StunModifier = new JLabel("Stun -6");
		lblMortal3StunModifier.setHorizontalAlignment(SwingConstants.CENTER);
		lblMortal3StunModifier.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblMortal3StunModifier.setOpaque(true);
		lblMortal3StunModifier.setBackground(Color.BLACK);
		lblMortal3StunModifier.setForeground(Color.WHITE);
		mortal3Panel.add(lblMortal3StunModifier, BorderLayout.SOUTH);

		JPanel mortal4Panel = new JPanel();
		stunPanel.add(mortal4Panel);
		mortal4Panel.setLayout(new BorderLayout(0, 0));

		JLabel lblMortal4 = new JLabel("MORTAL4");
		lblMortal4.setHorizontalAlignment(SwingConstants.CENTER);
		lblMortal4.setFont(new Font("Tahoma", Font.BOLD, 8));
		mortal4Panel.add(lblMortal4, BorderLayout.NORTH);

		JPanel mortal4StunProgressPanel = new StunGaugePanel();
		mortal4StunProgressPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		mortal4Panel.add(mortal4StunProgressPanel, BorderLayout.CENTER);
		stunProgressPanels.add(7, mortal4StunProgressPanel);

		JLabel lblMortal4StunModifier = new JLabel("Stun -7");
		lblMortal4StunModifier.setHorizontalAlignment(SwingConstants.CENTER);
		lblMortal4StunModifier.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblMortal4StunModifier.setOpaque(true);
		lblMortal4StunModifier.setBackground(Color.BLACK);
		lblMortal4StunModifier.setForeground(Color.WHITE);
		mortal4Panel.add(lblMortal4StunModifier, BorderLayout.SOUTH);

		JPanel mortal5Panel = new JPanel();
		stunPanel.add(mortal5Panel);
		mortal5Panel.setLayout(new BorderLayout(0, 0));

		JLabel lblMortal5 = new JLabel("MORTAL5");
		lblMortal5.setHorizontalAlignment(SwingConstants.CENTER);
		lblMortal5.setFont(new Font("Tahoma", Font.BOLD, 8));
		mortal5Panel.add(lblMortal5, BorderLayout.NORTH);

		JPanel mortal5ProgressPanel = new StunGaugePanel();
		mortal5ProgressPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		mortal5Panel.add(mortal5ProgressPanel, BorderLayout.CENTER);
		stunProgressPanels.add(8, mortal5ProgressPanel);

		JLabel lblMortal5StunModifier = new JLabel("Stun -8");
		lblMortal5StunModifier.setHorizontalAlignment(SwingConstants.CENTER);
		lblMortal5StunModifier.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblMortal5StunModifier.setOpaque(true);
		lblMortal5StunModifier.setBackground(Color.BLACK);
		lblMortal5StunModifier.setForeground(Color.WHITE);
		mortal5Panel.add(lblMortal5StunModifier, BorderLayout.SOUTH);

		JPanel mortal6Panel = new JPanel();
		stunPanel.add(mortal6Panel);
		mortal6Panel.setLayout(new BorderLayout(0, 0));

		JLabel lblMortal6 = new JLabel("MORTAL6");
		lblMortal6.setHorizontalAlignment(SwingConstants.CENTER);
		lblMortal6.setFont(new Font("Tahoma", Font.BOLD, 8));
		mortal6Panel.add(lblMortal6, BorderLayout.NORTH);

		StunGaugePanel mortal6ProgressPanel = new StunGaugePanel();
		mortal6ProgressPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		mortal6Panel.add(mortal6ProgressPanel, BorderLayout.CENTER);
		stunProgressPanels.add(9, mortal6ProgressPanel);

		JLabel lblMortal6StunModifier = new JLabel("Stun -9");
		lblMortal6StunModifier.setHorizontalAlignment(SwingConstants.CENTER);
		lblMortal6StunModifier.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblMortal6StunModifier.setOpaque(true);
		lblMortal6StunModifier.setBackground(Color.BLACK);
		lblMortal6StunModifier.setForeground(Color.WHITE);
		mortal6Panel.add(lblMortal6StunModifier, BorderLayout.SOUTH);

		JPanel portraitPanel = new JPanel();
		GridBagConstraints gbc_portraitPanel = new GridBagConstraints();
		gbc_portraitPanel.fill = GridBagConstraints.BOTH;
		gbc_portraitPanel.weightx = 0.3333;
		gbc_portraitPanel.weighty = 1.0;
		gbc_portraitPanel.gridheight = 6;
		gbc_portraitPanel.gridx = 1;
		gbc_portraitPanel.gridy = 0;
		characterStatPanel.add(portraitPanel, gbc_portraitPanel);
		portraitPanel.setBackground(Color.WHITE);
		portraitPanel.setPreferredSize(new Dimension(PORTRAIT_WIDTH, PORTRAIT_HEIGHT));
		portraitPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblPortrait = new JLabel(
				new ImageIcon(CharacterCreationView.class.getResource("/img/Adam_MD_bust.png")));
		lblPortrait.setPreferredSize(new Dimension(PORTRAIT_WIDTH, PORTRAIT_HEIGHT));
		portraitPanel.add(lblPortrait);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setPreferredSize(new Dimension(TABBED_PANEL_WIDTH, TABBED_PANEL_HEIGHT));
		contentPane.add(tabbedPane, BorderLayout.SOUTH);

		JPanel skillPanel = new JPanel();
		tabbedPane.addTab("Skills", null, skillPanel, null);
		skillPanel.setLayout(new BorderLayout(0, 0));

		JTabbedPane cards = new JTabbedPane(JTabbedPane.LEFT);
		skillPanel.add(cards, BorderLayout.CENTER);

		JPanel cardSpecialAbilities = new JPanel();
		cards.addTab("SPECIAL ABILITIES", null, cardSpecialAbilities, null);
		cardSpecialAbilities.setLayout(new BorderLayout(0, 0));

		JSplitPane specialAbilitiesSplitPane = new JSplitPane();
		specialAbilitiesSplitPane.setResizeWeight(0.25);
		cardSpecialAbilities.add(specialAbilitiesSplitPane);

		JScrollPane specialAbilitiesSkillScrollPane = new JScrollPane();
		specialAbilitiesSplitPane.setLeftComponent(specialAbilitiesSkillScrollPane);

		specialAbilitiesSkillPanel = new JPanel();
		specialAbilitiesSkillScrollPane.setViewportView(specialAbilitiesSkillPanel);
		specialAbilitiesSkillPanel.setLayout(new BoxLayout(specialAbilitiesSkillPanel, BoxLayout.Y_AXIS));

		JScrollPane specialAbilitiesDescriptionScrollPane = new JScrollPane();
		specialAbilitiesSplitPane.setRightComponent(specialAbilitiesDescriptionScrollPane);

		JPanel specialAbilitiesDescriptionPanel = new JPanel();
		specialAbilitiesDescriptionScrollPane.setViewportView(specialAbilitiesDescriptionPanel);
		specialAbilitiesDescriptionPanel.setLayout(new BoxLayout(specialAbilitiesDescriptionPanel, BoxLayout.Y_AXIS));

		JPanel cardAttractiveness = new JPanel();
		cards.addTab("ATTRACTIVENESS", null, cardAttractiveness, null);
		cardAttractiveness.setLayout(new BorderLayout(0, 0));

		JSplitPane attractivenessSplitPane = new JSplitPane();
		attractivenessSplitPane.setResizeWeight(0.25);
		cardAttractiveness.add(attractivenessSplitPane);

		JScrollPane attractivenessSkillScrollPane = new JScrollPane();
		attractivenessSplitPane.setLeftComponent(attractivenessSkillScrollPane);

		attractivenessSkillPanel = new JPanel();
		attractivenessSkillScrollPane.setViewportView(attractivenessSkillPanel);
		attractivenessSkillPanel.setLayout(new BoxLayout(attractivenessSkillPanel, BoxLayout.Y_AXIS));

		JScrollPane attractivenessDescriptionScrollPane = new JScrollPane();
		attractivenessSplitPane.setRightComponent(attractivenessDescriptionScrollPane);

		JPanel attractivenessDescriptionPanel = new JPanel();
		attractivenessDescriptionScrollPane.setViewportView(attractivenessDescriptionPanel);
		attractivenessDescriptionPanel.setLayout(new BoxLayout(attractivenessDescriptionPanel, BoxLayout.Y_AXIS));

		JPanel cardBody = new JPanel();
		cards.addTab("BODY", null, cardBody, null);
		cardBody.setLayout(new BorderLayout(0, 0));

		JSplitPane bodySplitPane = new JSplitPane();
		bodySplitPane.setResizeWeight(0.25);
		cardBody.add(bodySplitPane);

		JScrollPane bodySkillScrollPane = new JScrollPane();
		bodySplitPane.setLeftComponent(bodySkillScrollPane);

		bodySkillPanel = new JPanel();
		bodySkillScrollPane.setViewportView(bodySkillPanel);
		bodySkillPanel.setLayout(new BoxLayout(bodySkillPanel, BoxLayout.Y_AXIS));

		JScrollPane bodyDescriptionScrollPane = new JScrollPane();
		bodySplitPane.setRightComponent(bodyDescriptionScrollPane);

		JPanel bodyDescriptionPanel = new JPanel();
		bodyDescriptionScrollPane.setViewportView(bodyDescriptionPanel);
		bodyDescriptionPanel.setLayout(new BoxLayout(bodyDescriptionPanel, BoxLayout.Y_AXIS));

		JPanel cardCool = new JPanel();
		cards.addTab("COOL", null, cardCool, null);
		cardCool.setLayout(new BorderLayout(0, 0));

		JSplitPane coolSplitPane = new JSplitPane();
		coolSplitPane.setResizeWeight(0.25);
		cardCool.add(coolSplitPane);

		JScrollPane coolSkillScrollPane = new JScrollPane();
		coolSplitPane.setLeftComponent(coolSkillScrollPane);

		coolSkillPanel = new JPanel();
		coolSkillScrollPane.setViewportView(coolSkillPanel);
		coolSkillPanel.setLayout(new BoxLayout(coolSkillPanel, BoxLayout.Y_AXIS));

		JScrollPane coolDescriptionScrollPane = new JScrollPane();
		coolSplitPane.setRightComponent(coolDescriptionScrollPane);

		JPanel coolDescriptionPanel = new JPanel();
		coolDescriptionScrollPane.setViewportView(coolDescriptionPanel);
		coolDescriptionPanel.setLayout(new BoxLayout(coolDescriptionPanel, BoxLayout.Y_AXIS));

		JPanel cardEmpathy = new JPanel();
		cards.addTab("EMPATHY", null, cardEmpathy, null);
		cardEmpathy.setLayout(new BorderLayout(0, 0));

		JSplitPane empathySplitPane = new JSplitPane();
		empathySplitPane.setResizeWeight(0.25);
		cardEmpathy.add(empathySplitPane);

		JScrollPane empathySkillScrollPane = new JScrollPane();
		empathySplitPane.setLeftComponent(empathySkillScrollPane);

		empathySkillPanel = new JPanel();
		empathySkillScrollPane.setViewportView(empathySkillPanel);
		empathySkillPanel.setLayout(new BoxLayout(empathySkillPanel, BoxLayout.Y_AXIS));

		JScrollPane empathyDescriptionScrollPane = new JScrollPane();
		empathySplitPane.setRightComponent(empathyDescriptionScrollPane);

		JPanel empathyDescriptionPanel = new JPanel();
		empathyDescriptionScrollPane.setViewportView(empathyDescriptionPanel);
		empathyDescriptionPanel.setLayout(new BoxLayout(empathyDescriptionPanel, BoxLayout.Y_AXIS));

		JPanel cardIntelligence = new JPanel();
		cards.addTab("INTELLIGENCE", null, cardIntelligence, null);
		cardIntelligence.setLayout(new BorderLayout(0, 0));

		JSplitPane intelligenceSplitPane = new JSplitPane();
		intelligenceSplitPane.setResizeWeight(0.25);
		cardIntelligence.add(intelligenceSplitPane);

		JScrollPane intelligenceSkillScrollPane = new JScrollPane();
		intelligenceSplitPane.setLeftComponent(intelligenceSkillScrollPane);

		intelligenceSkillPanel = new JPanel();
		intelligenceSkillScrollPane.setViewportView(intelligenceSkillPanel);
		intelligenceSkillPanel.setLayout(new BoxLayout(intelligenceSkillPanel, BoxLayout.Y_AXIS));

		JScrollPane intelligenceDescriptionScrollPane = new JScrollPane();
		intelligenceSplitPane.setRightComponent(intelligenceDescriptionScrollPane);

		JPanel intelligenceDescriptionPanel = new JPanel();
		intelligenceDescriptionScrollPane.setViewportView(intelligenceDescriptionPanel);
		intelligenceDescriptionPanel.setLayout(new BoxLayout(intelligenceDescriptionPanel, BoxLayout.Y_AXIS));

		JPanel cardReflexes = new JPanel();
		cards.addTab("REFLEXES", null, cardReflexes, null);
		cardReflexes.setLayout(new BorderLayout(0, 0));

		JSplitPane reflexesSplitPane = new JSplitPane();
		reflexesSplitPane.setResizeWeight(0.25);
		cardReflexes.add(reflexesSplitPane);

		JScrollPane reflexesSkillScrollPane = new JScrollPane();
		reflexesSplitPane.setLeftComponent(reflexesSkillScrollPane);

		reflexesSkillPanel = new JPanel();
		reflexesSkillScrollPane.setViewportView(reflexesSkillPanel);
		reflexesSkillPanel.setLayout(new BoxLayout(reflexesSkillPanel, BoxLayout.Y_AXIS));

		JScrollPane reflexesDescriptionScrollPane = new JScrollPane();
		reflexesSplitPane.setRightComponent(reflexesDescriptionScrollPane);

		JPanel reflexesDescriptionPanel = new JPanel();
		reflexesDescriptionScrollPane.setViewportView(reflexesDescriptionPanel);
		reflexesDescriptionPanel.setLayout(new BoxLayout(reflexesDescriptionPanel, BoxLayout.Y_AXIS));

		JPanel cardTechnicalAbilities = new JPanel();
		cards.addTab("TECHNICAL ABILITIES", null, cardTechnicalAbilities, null);
		cardTechnicalAbilities.setLayout(new BorderLayout(0, 0));

		JSplitPane technicalAbilitiesSplitPane = new JSplitPane();
		technicalAbilitiesSplitPane.setResizeWeight(0.25);
		cardTechnicalAbilities.add(technicalAbilitiesSplitPane);

		JScrollPane technicalAbilitiesSkillScrollPane = new JScrollPane();
		technicalAbilitiesSplitPane.setLeftComponent(technicalAbilitiesSkillScrollPane);

		technicalAbilitiesSkillPanel = new JPanel();
		technicalAbilitiesSkillScrollPane.setViewportView(technicalAbilitiesSkillPanel);
		technicalAbilitiesSkillPanel.setLayout(new BoxLayout(technicalAbilitiesSkillPanel, BoxLayout.Y_AXIS));

		JScrollPane technicalDescriptionScrollPane = new JScrollPane();
		technicalAbilitiesSplitPane.setRightComponent(technicalDescriptionScrollPane);

		JPanel technicalAbilitiesDescriptionPanel = new JPanel();
		technicalDescriptionScrollPane.setViewportView(technicalAbilitiesDescriptionPanel);
		technicalAbilitiesDescriptionPanel
				.setLayout(new BoxLayout(technicalAbilitiesDescriptionPanel, BoxLayout.Y_AXIS));

		JPanel cyberneticsPanel = new JPanel();
		tabbedPane.addTab("Cybernetics", null, cyberneticsPanel, null);

		JPanel lifepathPanel = new JPanel();
		tabbedPane.addTab("Lifepath", null, lifepathPanel, null);

		JSplitPane inventoryPane = new JSplitPane();
		inventoryPane.setResizeWeight(0.5);
		tabbedPane.addTab("Inventory", null, inventoryPane, null);
	}

	public void addSkill(String skillCategory, String skill, String description, int rank) {
		JPanel targetPanel;
		JPanel skillPanel = new JPanel(new BorderLayout());
		JLabel skillLabel = new JLabel(skill);
		skillLabel.setFont(new Font("Garamond", Font.PLAIN, 10));
		JTextField rankField = new JTextField(Integer.toString(rank), 3);

		switch (skillCategory.toUpperCase()) {
		case "SPECIAL ABILITIES":
			targetPanel = specialAbilitiesSkillPanel;
			break;
		case "ATTRACTIVENESS":
			targetPanel = attractivenessSkillPanel;
			break;
		case "BODY":
			targetPanel = bodySkillPanel;
			break;
		case "COOL":
			targetPanel = coolSkillPanel;
			break;
		case "EMPATHY":
			targetPanel = empathySkillPanel;
			break;
		case "INTELLIGENCE":
			targetPanel = intelligenceSkillPanel;
			break;
		case "REFLEXES":
			targetPanel = reflexesSkillPanel;
			break;
		case "TECHNICAL ABILITY":
			targetPanel = technicalAbilitiesSkillPanel;
			break;
		default:
			targetPanel = null;
			break;
		}

		skillPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		skillPanel.add(skillLabel, BorderLayout.CENTER);
		skillPanel.add(rankField, BorderLayout.LINE_END);
		targetPanel.add(skillPanel);
	}

	public static class StunGaugePanel extends JPanel {
		private static final int STUN_PROGRESS_CELL_WIDTH = 15;
		private static final int STUN_PROGRESS_CELL_HEIGHT = 10;

		private Rectangle2D rect = new Rectangle(new Point(0, 0), new Dimension(0, STUN_PROGRESS_CELL_HEIGHT));

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			int x = 0;
			int y = 0;
			int width = STUN_PROGRESS_CELL_WIDTH;
			int height = STUN_PROGRESS_CELL_HEIGHT;
			g.setColor(Color.BLACK);
			for (int i = 0; i < 4; i++) {
				g.drawRect(x, y, width, height);
				x += STUN_PROGRESS_CELL_WIDTH;
			}
			g2.setColor(Color.RED);
			g2.fill(rect);

		}

		public void increaseStunGauge() {
			int newWidth = (int) (rect.getWidth() + STUN_PROGRESS_CELL_WIDTH);
			int newHeight = (int) (rect.getHeight());
			rect = new Rectangle(new Point(0, 0), new Dimension(newWidth, newHeight));
		}

		public void decreaseStunGauge() {
			int newWidth = (int) (rect.getWidth() - STUN_PROGRESS_CELL_WIDTH);
			int newHeight = (int) (rect.getHeight());
			rect = new Rectangle(new Point(0, 0), new Dimension(newWidth, newHeight));

		}
	}
}
