package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;

import model.CharacterCreationModel;

public class CharacterCreationView extends JFrame {
	private static final int FRAME_WIDTH = 900;
	private static final int FRAME_HEIGHT = 600;
	private static final int TABBED_PANEL_WIDTH = FRAME_WIDTH;
	private static final int TABBED_PANEL_HEIGHT = FRAME_HEIGHT / 3;
	private static final int CHARACTER_PANEL_WIDTH = FRAME_WIDTH;
	private static final int CHARACTER_PANEL_HEIGHT = 2 * (FRAME_WIDTH / 3);
	private static final int NUMBER_OF_TEXTFIELD_COLUMNS = 5;
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

	private static final Font TITLE_FONT = new Font("Tahoma", Font.BOLD, 14);

	public static final int MAXIMUM_CELLS_PER_GAUGE = 4;

	private JPanel contentPane;
	private JTextField handleTextField;
	private JSpinner intelligenceSpinner;
	private JSpinner unmodifiedReflexesSpinner;
	private JSpinner technicalAbilitySpinner;
	private JSpinner coolSpinner;
	private JSpinner attractivenessSpinner;
	private JSpinner luckSpinner;
	private JSpinner bodySpinner;
	private JTextField characterPointsTextField;
	private JSpinner movementAllowanceSpinner;
	private JTextField currentEmpathyTextField;
	private JTextField runTextField;
	private JTextField leapTextField;
	private JTextField liftTextField;
	private JTextField headArmorTextField;
	private JTextField torsoArmorTextField;
	private JTextField rightArmArmorTextField;
	private JTextField leftArmArmorTextField;
	private JTextField rightLegArmorTextField;
	private JTextField leftLegArmorTextField;
	private JTextField saveTextField;
	private JTextField bodyTypeModifierTextField;
	private JButton randomCharacterPointsButton;
	private JButton fastCharacterPointsButton;
	private JButton manualCharacterPointsButton;
	private JButton saveButton;
	private JButton loadButton;
	private JButton increaseInjuryButton;
	private JButton minorlyIncreaseInjuryButton;
	private JButton minorlyDecreaseInjuryButton;
	private JButton decreaseInjuryButton;
	private JButton btnChangePortrait;
	private JLabel lblPortrait;
	private JComboBox<String> roleComboBox;
	private JTextField modifiedReflexesTextField;
	private JSpinner totalEmpathySpinner;
	private JComboBox<Integer>[] statComboBoxes;
	private JLabel[] statComboBoxLabels;
	private JFileChooser fileChooser;

	private List<StunGaugePanel> stunProgressPanels;
	private JSpinner[] skillSpinners;
	private Map<String, JTable> skillTables;
	private Map<String, JTextArea> skillTextAreas;
	private JTable specialAbilitiesSkillTable;
	private JTable attractivenessSkillTable;
	private JTable bodySkillTable;
	private JTable coolSkillTable;
	private JTable empathySkillTable;
	private JTable intelligenceSkillTable;
	private JTable reflexesSkillTable;
	private JTable technicalAbilitiesSkillTable;

	/**
	 * Create the frame.
	 */
	public CharacterCreationView() {
		stunProgressPanels = new ArrayList<StunGaugePanel>();
		skillSpinners = new JSpinner[9];
		skillTables = new HashMap<String, JTable>();
		skillTextAreas = new HashMap<String, JTextArea>();

		setTitle("Cyberpunk 2020 Character Creation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, FRAME_WIDTH, FRAME_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel toolbarPanel = new JPanel();
		toolbarPanel.setPreferredSize(new Dimension(900, 30));
		FlowLayout flowLayout = (FlowLayout) toolbarPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(toolbarPanel, BorderLayout.NORTH);

		btnChangePortrait = new JButton("Change Portrait");
		toolbarPanel.add(btnChangePortrait);

		saveButton = new JButton("Save Character");
		saveButton.setRolloverEnabled(false);
		toolbarPanel.add(saveButton);

		loadButton = new JButton("Load Character");
		toolbarPanel.add(loadButton);

		JPanel characterStatPanel = new JPanel();
		characterStatPanel.setPreferredSize(new Dimension(900, 370));
		contentPane.add(characterStatPanel);
		GridBagLayout gbl_characterStatPanel = new GridBagLayout();
		gbl_characterStatPanel.columnWeights = new double[] { 0.0, 0.0 };
		gbl_characterStatPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		characterStatPanel.setLayout(gbl_characterStatPanel);

		JPanel handlePanel = new JPanel();
		GridBagConstraints gbc_handlePanel = new GridBagConstraints();
		gbc_handlePanel.fill = GridBagConstraints.BOTH;
		gbc_handlePanel.weighty = 0.1111;
		gbc_handlePanel.weightx = 0.6667;
		gbc_handlePanel.insets = new Insets(0, 0, 3, 0);
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
		lblHandle.setFont(TITLE_FONT);
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
		handleTextField.setFont(new Font("Tahoma", Font.PLAIN, 11));
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
		gbc_rolePanel.weighty = 0.1111;
		gbc_rolePanel.weightx = 0.6667;
		gbc_rolePanel.insets = new Insets(0, 0, 3, 0);
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
		lblRole.setFont(TITLE_FONT);
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

		roleComboBox = new JComboBox<String>();
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
		gbc_characterPointsPanel.weighty = 0.1111;
		gbc_characterPointsPanel.weightx = 0.6667;
		gbc_characterPointsPanel.insets = new Insets(0, 0, 3, 0);
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
		lblCharacterPoints.setFont(TITLE_FONT);
		lblCharacterPoints.setOpaque(true);
		lblCharacterPoints.setBackground(Color.BLACK);
		lblCharacterPoints.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCharacterPoints = new GridBagConstraints();
		gbc_lblCharacterPoints.weightx = 0.1111;
		gbc_lblCharacterPoints.ipadx = 10;
		gbc_lblCharacterPoints.fill = GridBagConstraints.BOTH;
		gbc_lblCharacterPoints.insets = new Insets(0, 0, 0, 5);
		gbc_lblCharacterPoints.gridx = 0;
		gbc_lblCharacterPoints.gridy = 0;
		characterPointsPanel.add(lblCharacterPoints, gbc_lblCharacterPoints);

		characterPointsTextField = new JTextField();
		characterPointsTextField.setEditable(false);
		characterPointsTextField.setHorizontalAlignment(SwingConstants.CENTER);
		characterPointsTextField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_characterPointsTextField = new GridBagConstraints();
		gbc_characterPointsTextField.weightx = 0.1111;
		gbc_characterPointsTextField.weighty = 1.0;
		gbc_characterPointsTextField.fill = GridBagConstraints.BOTH;
		gbc_characterPointsTextField.insets = new Insets(0, 0, 0, 5);
		gbc_characterPointsTextField.gridx = 1;
		gbc_characterPointsTextField.gridy = 0;
		characterPointsPanel.add(characterPointsTextField, gbc_characterPointsTextField);
		characterPointsTextField.setColumns(NUMBER_OF_TEXTFIELD_COLUMNS);

		randomCharacterPointsButton = new JButton("Random");
		GridBagConstraints gbc_randomCharacterPointsButton = new GridBagConstraints();
		gbc_randomCharacterPointsButton.weightx = 0.1111;
		gbc_randomCharacterPointsButton.fill = GridBagConstraints.BOTH;
		gbc_randomCharacterPointsButton.insets = new Insets(0, 0, 0, 5);
		gbc_randomCharacterPointsButton.gridx = 2;
		gbc_randomCharacterPointsButton.gridy = 0;
		characterPointsPanel.add(randomCharacterPointsButton, gbc_randomCharacterPointsButton);

		fastCharacterPointsButton = new JButton("Fast");
		GridBagConstraints gbc_fastCharacterPointsButton = new GridBagConstraints();
		gbc_fastCharacterPointsButton.insets = new Insets(0, 0, 0, 5);
		gbc_fastCharacterPointsButton.weightx = 0.1111;
		gbc_fastCharacterPointsButton.fill = GridBagConstraints.BOTH;
		gbc_fastCharacterPointsButton.gridx = 3;
		gbc_fastCharacterPointsButton.gridy = 0;
		characterPointsPanel.add(fastCharacterPointsButton, gbc_fastCharacterPointsButton);

		manualCharacterPointsButton = new JButton("Manual");
		GridBagConstraints gbc_manualCharacterPointsButton = new GridBagConstraints();
		gbc_manualCharacterPointsButton.gridx = 4;
		gbc_manualCharacterPointsButton.gridy = 0;
		characterPointsPanel.add(manualCharacterPointsButton, gbc_manualCharacterPointsButton);

		JPanel statPanel = new JPanel();
		GridBagConstraints gbc_statPanel = new GridBagConstraints();
		gbc_statPanel.fill = GridBagConstraints.BOTH;
		gbc_statPanel.weighty = 0.3333;
		gbc_statPanel.weightx = 0.6667;
		gbc_statPanel.insets = new Insets(0, 0, 3, 0);
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
		lblStats.setFont(TITLE_FONT);
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
		lblTech.setFont(new Font("Tahoma", Font.PLAIN, 10));
		technicalAbilityStatPanel.add(lblTech, BorderLayout.CENTER);

		technicalAbilitySpinner = new JSpinner();
		technicalAbilitySpinner.setFont(new Font("Tahoma", Font.PLAIN, 11));
		technicalAbilitySpinner.setModel(new SpinnerNumberModel(2, 2, 10, 1));
		technicalAbilityStatPanel.add(technicalAbilitySpinner, BorderLayout.EAST);
		skillSpinners[0] = technicalAbilitySpinner;

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
		lblIntelligence.setFont(new Font("Tahoma", Font.PLAIN, 10));
		intelligenceStatPanel.add(lblIntelligence, BorderLayout.CENTER);

		intelligenceSpinner = new JSpinner();
		intelligenceSpinner.setFont(new Font("Tahoma", Font.PLAIN, 11));
		intelligenceSpinner.setModel(new SpinnerNumberModel(2, 2, 10, 1));
		intelligenceStatPanel.add(intelligenceSpinner, BorderLayout.EAST);
		skillSpinners[1] = intelligenceSpinner;

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
		lblReflexes.setFont(new Font("Tahoma", Font.PLAIN, 10));
		reflexesStatPanel.add(lblReflexes, BorderLayout.CENTER);

		JPanel reflexesPointPanel = new JPanel();
		reflexesStatPanel.add(reflexesPointPanel, BorderLayout.EAST);
		reflexesPointPanel.setLayout(new BoxLayout(reflexesPointPanel, BoxLayout.X_AXIS));

		modifiedReflexesTextField = new JTextField();
		modifiedReflexesTextField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		reflexesPointPanel.add(modifiedReflexesTextField);
		modifiedReflexesTextField.setColumns(3);

		JLabel lblReflexesDivider = new JLabel("/");
		lblReflexesDivider.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblReflexesDivider.setBorder(new EmptyBorder(0, 3, 0, 3));
		reflexesPointPanel.add(lblReflexesDivider);

		unmodifiedReflexesSpinner = new JSpinner();
		unmodifiedReflexesSpinner.setFont(new Font("Tahoma", Font.PLAIN, 11));
		unmodifiedReflexesSpinner.setModel(new SpinnerNumberModel(2, 2, 10, 1));
		reflexesPointPanel.add(unmodifiedReflexesSpinner);
		skillSpinners[2] = unmodifiedReflexesSpinner;

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
		lblCool.setFont(new Font("Tahoma", Font.PLAIN, 10));
		coolStatPanel.add(lblCool, BorderLayout.CENTER);

		coolSpinner = new JSpinner();
		coolSpinner.setFont(new Font("Tahoma", Font.PLAIN, 11));
		coolSpinner.setModel(new SpinnerNumberModel(2, 2, 10, 1));
		coolStatPanel.add(coolSpinner, BorderLayout.EAST);
		skillSpinners[3] = coolSpinner;

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
		lblAttractiveness.setFont(new Font("Tahoma", Font.PLAIN, 10));
		attractivenessStatPanel.add(lblAttractiveness, BorderLayout.CENTER);

		attractivenessSpinner = new JSpinner();
		attractivenessSpinner.setFont(new Font("Tahoma", Font.PLAIN, 11));
		attractivenessSpinner.setModel(new SpinnerNumberModel(2, 2, 10, 1));
		attractivenessStatPanel.add(attractivenessSpinner, BorderLayout.EAST);
		skillSpinners[4] = attractivenessSpinner;

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
		lblLuck.setFont(new Font("Tahoma", Font.PLAIN, 10));
		luckPanel.add(lblLuck, BorderLayout.CENTER);

		luckSpinner = new JSpinner();
		luckSpinner.setFont(new Font("Tahoma", Font.PLAIN, 11));
		luckSpinner.setModel(new SpinnerNumberModel(2, 2, 10, 1));
		luckPanel.add(luckSpinner, BorderLayout.EAST);
		skillSpinners[5] = luckSpinner;

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
		lblBody.setFont(new Font("Tahoma", Font.PLAIN, 10));
		bodyStatPanel.add(lblBody, BorderLayout.CENTER);

		bodySpinner = new JSpinner();
		bodySpinner.setFont(new Font("Tahoma", Font.PLAIN, 11));
		bodySpinner.setModel(new SpinnerNumberModel(2, 2, 10, 1));
		bodyStatPanel.add(bodySpinner, BorderLayout.EAST);
		skillSpinners[6] = bodySpinner;

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
		lblMovementAllowance.setFont(new Font("Tahoma", Font.PLAIN, 10));
		movementAllowancePanel.add(lblMovementAllowance);

		movementAllowanceSpinner = new JSpinner();
		movementAllowanceSpinner.setFont(new Font("Tahoma", Font.PLAIN, 11));
		movementAllowanceSpinner.setModel(new SpinnerNumberModel(2, 2, 10, 1));
		movementAllowancePanel.add(movementAllowanceSpinner, BorderLayout.EAST);
		skillSpinners[7] = movementAllowanceSpinner;

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
		lblEmpathy.setFont(new Font("Tahoma", Font.PLAIN, 10));
		empathyStatPanel.add(lblEmpathy, BorderLayout.CENTER);

		JPanel empathyPointPanel = new JPanel();
		empathyStatPanel.add(empathyPointPanel, BorderLayout.EAST);
		empathyPointPanel.setLayout(new BoxLayout(empathyPointPanel, BoxLayout.X_AXIS));

		currentEmpathyTextField = new JTextField();
		currentEmpathyTextField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		empathyPointPanel.add(currentEmpathyTextField);
		currentEmpathyTextField.setColumns(3);

		JLabel lblEmpathyDivider = new JLabel("/");
		lblEmpathyDivider.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpathyDivider.setBorder(new EmptyBorder(0, 3, 0, 3));
		empathyPointPanel.add(lblEmpathyDivider);

		totalEmpathySpinner = new JSpinner();
		totalEmpathySpinner.setFont(new Font("Tahoma", Font.PLAIN, 11));
		totalEmpathySpinner.setModel(new SpinnerNumberModel(2, 2, 10, 1));
		empathyPointPanel.add(totalEmpathySpinner);
		skillSpinners[8] = totalEmpathySpinner;

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
		lblRun.setFont(new Font("Tahoma", Font.PLAIN, 10));
		runPanel.add(lblRun, BorderLayout.CENTER);

		runTextField = new JTextField();
		runTextField.setFont(new Font("Tahoma", Font.PLAIN, 10));
		runTextField.setHorizontalAlignment(SwingConstants.CENTER);
		runTextField.setEditable(false);
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
		lblLeap.setFont(new Font("Tahoma", Font.PLAIN, 10));
		leapPanel.add(lblLeap, BorderLayout.CENTER);

		leapTextField = new JTextField();
		leapTextField.setFont(new Font("Tahoma", Font.PLAIN, 10));
		leapTextField.setHorizontalAlignment(SwingConstants.CENTER);
		leapTextField.setEditable(false);
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
		lblLift.setFont(new Font("Tahoma", Font.PLAIN, 10));
		liftPanel.add(lblLift);

		liftTextField = new JTextField();
		liftTextField.setFont(new Font("Tahoma", Font.PLAIN, 10));
		liftTextField.setHorizontalAlignment(SwingConstants.CENTER);
		liftTextField.setEditable(false);
		liftPanel.add(liftTextField, BorderLayout.EAST);
		liftTextField.setColumns(NUMBER_OF_TEXTFIELD_COLUMNS);

		JPanel armorPanel = new JPanel();
		GridBagConstraints gbc_armorPanel = new GridBagConstraints();
		gbc_armorPanel.anchor = GridBagConstraints.WEST;
		gbc_armorPanel.fill = GridBagConstraints.VERTICAL;
		gbc_armorPanel.weighty = 0.1667;
		gbc_armorPanel.weightx = 0.6667;
		gbc_armorPanel.insets = new Insets(0, 0, 3, 0);
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
		lblLocation.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLocation.setOpaque(true);
		lblLocation.setBackground(Color.BLACK);
		lblLocation.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblLocation = new GridBagConstraints();
		gbc_lblLocation.weighty = 0.6667;
		gbc_lblLocation.fill = GridBagConstraints.BOTH;
		gbc_lblLocation.insets = new Insets(0, 0, 1, 4);
		gbc_lblLocation.gridx = 0;
		gbc_lblLocation.gridy = 0;
		armorPanel.add(lblLocation, gbc_lblLocation);

		JLabel lblHead = new JLabel("<html><div style='text-align: center;'>Head<br>1</div></html>");
		lblHead.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblHead.setHorizontalAlignment(SwingConstants.CENTER);
		lblHead.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_lblHead = new GridBagConstraints();
		gbc_lblHead.weighty = 0.6667;
		gbc_lblHead.ipadx = 4;
		gbc_lblHead.fill = GridBagConstraints.BOTH;
		gbc_lblHead.gridx = 1;
		gbc_lblHead.gridy = 0;
		armorPanel.add(lblHead, gbc_lblHead);

		JLabel lblTorso = new JLabel("<html><div style='text-align: center;'>Torso<br>2-4</div></html>");
		lblTorso.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblTorso.setHorizontalAlignment(SwingConstants.CENTER);
		lblTorso.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_lblTorso = new GridBagConstraints();
		gbc_lblTorso.weighty = 0.6667;
		gbc_lblTorso.ipadx = 4;
		gbc_lblTorso.fill = GridBagConstraints.BOTH;
		gbc_lblTorso.gridy = 0;
		gbc_lblTorso.gridx = 2;
		armorPanel.add(lblTorso, gbc_lblTorso);

		JLabel lblRightArm = new JLabel("<html><div style='text-align: center;'>R.Arm<br>5</div></html>");
		lblRightArm.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRightArm.setHorizontalAlignment(SwingConstants.CENTER);
		lblRightArm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_lblRightArm = new GridBagConstraints();
		gbc_lblRightArm.weighty = 0.6667;
		gbc_lblRightArm.ipadx = 4;
		gbc_lblRightArm.fill = GridBagConstraints.BOTH;
		gbc_lblRightArm.gridx = 3;
		gbc_lblRightArm.gridy = 0;
		armorPanel.add(lblRightArm, gbc_lblRightArm);

		JLabel lblLeftArm = new JLabel("<html><div style='text-align: center;'>L.Arm<br>6</div></html>");
		lblLeftArm.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblLeftArm.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeftArm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_lblLeftArm = new GridBagConstraints();
		gbc_lblLeftArm.weighty = 0.6667;
		gbc_lblLeftArm.ipadx = 4;
		gbc_lblLeftArm.fill = GridBagConstraints.BOTH;
		gbc_lblLeftArm.gridy = 0;
		gbc_lblLeftArm.gridx = 4;
		armorPanel.add(lblLeftArm, gbc_lblLeftArm);

		JLabel lblRightLeg = new JLabel("<html><div style='text-align: center;'>R.Leg<br>7-8</div></html>");
		lblRightLeg.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRightLeg.setHorizontalAlignment(SwingConstants.CENTER);
		lblRightLeg.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_lblRightLeg = new GridBagConstraints();
		gbc_lblRightLeg.weighty = 0.6667;
		gbc_lblRightLeg.ipadx = 4;
		gbc_lblRightLeg.fill = GridBagConstraints.BOTH;
		gbc_lblRightLeg.gridy = 0;
		gbc_lblRightLeg.gridx = 5;
		armorPanel.add(lblRightLeg, gbc_lblRightLeg);

		JLabel lblLeftLeg = new JLabel("<html><div style='text-align: center;'>L.Leg<br>9-0</div></html>");
		lblLeftLeg.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblLeftLeg.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeftLeg.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_lblLeftLeg = new GridBagConstraints();
		gbc_lblLeftLeg.weighty = 0.6667;
		gbc_lblLeftLeg.ipadx = 4;
		gbc_lblLeftLeg.fill = GridBagConstraints.BOTH;
		gbc_lblLeftLeg.gridy = 0;
		gbc_lblLeftLeg.gridx = 6;
		armorPanel.add(lblLeftLeg, gbc_lblLeftLeg);

		JLabel lblArmorSp = new JLabel("Armor SP");
		lblArmorSp.setHorizontalAlignment(SwingConstants.CENTER);
		lblArmorSp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblArmorSp.setOpaque(true);
		lblArmorSp.setBackground(Color.BLACK);
		lblArmorSp.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblArmorSp = new GridBagConstraints();
		gbc_lblArmorSp.weighty = 0.3333;
		gbc_lblArmorSp.fill = GridBagConstraints.VERTICAL;
		gbc_lblArmorSp.insets = new Insets(1, 0, 0, 4);
		gbc_lblArmorSp.gridx = 0;
		gbc_lblArmorSp.gridy = 1;
		armorPanel.add(lblArmorSp, gbc_lblArmorSp);

		headArmorTextField = new JTextField();
		headArmorTextField.setHorizontalAlignment(SwingConstants.CENTER);
		headArmorTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_headSPTextField = new GridBagConstraints();
		gbc_headSPTextField.weighty = 0.3333;
		gbc_headSPTextField.ipadx = 4;
		gbc_headSPTextField.fill = GridBagConstraints.BOTH;
		gbc_headSPTextField.gridx = 1;
		gbc_headSPTextField.gridy = 1;
		armorPanel.add(headArmorTextField, gbc_headSPTextField);
		headArmorTextField.setColumns(10);

		torsoArmorTextField = new JTextField();
		torsoArmorTextField.setHorizontalAlignment(SwingConstants.CENTER);
		torsoArmorTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_torsoSPTextField = new GridBagConstraints();
		gbc_torsoSPTextField.weighty = 0.3333;
		gbc_torsoSPTextField.ipadx = 4;
		gbc_torsoSPTextField.fill = GridBagConstraints.BOTH;
		gbc_torsoSPTextField.gridx = 2;
		gbc_torsoSPTextField.gridy = 1;
		armorPanel.add(torsoArmorTextField, gbc_torsoSPTextField);
		torsoArmorTextField.setColumns(10);

		rightArmArmorTextField = new JTextField();
		rightArmArmorTextField.setHorizontalAlignment(SwingConstants.CENTER);
		rightArmArmorTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_rightArmSPTextField = new GridBagConstraints();
		gbc_rightArmSPTextField.weighty = 0.3333;
		gbc_rightArmSPTextField.ipadx = 4;
		gbc_rightArmSPTextField.fill = GridBagConstraints.BOTH;
		gbc_rightArmSPTextField.gridx = 3;
		gbc_rightArmSPTextField.gridy = 1;
		armorPanel.add(rightArmArmorTextField, gbc_rightArmSPTextField);
		rightArmArmorTextField.setColumns(10);

		leftArmArmorTextField = new JTextField();
		leftArmArmorTextField.setHorizontalAlignment(SwingConstants.CENTER);
		leftArmArmorTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_leftArmSPTextField = new GridBagConstraints();
		gbc_leftArmSPTextField.weighty = 0.3333;
		gbc_leftArmSPTextField.ipadx = 4;
		gbc_leftArmSPTextField.fill = GridBagConstraints.BOTH;
		gbc_leftArmSPTextField.gridx = 4;
		gbc_leftArmSPTextField.gridy = 1;
		armorPanel.add(leftArmArmorTextField, gbc_leftArmSPTextField);
		leftArmArmorTextField.setColumns(10);

		rightLegArmorTextField = new JTextField();
		rightLegArmorTextField.setHorizontalAlignment(SwingConstants.CENTER);
		rightLegArmorTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_rightLegSPTextField = new GridBagConstraints();
		gbc_rightLegSPTextField.weighty = 0.3333;
		gbc_rightLegSPTextField.ipadx = 4;
		gbc_rightLegSPTextField.fill = GridBagConstraints.BOTH;
		gbc_rightLegSPTextField.gridx = 5;
		gbc_rightLegSPTextField.gridy = 1;
		armorPanel.add(rightLegArmorTextField, gbc_rightLegSPTextField);
		rightLegArmorTextField.setColumns(10);

		leftLegArmorTextField = new JTextField();
		leftLegArmorTextField.setHorizontalAlignment(SwingConstants.CENTER);
		leftLegArmorTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc_leftLegSPTextField = new GridBagConstraints();
		gbc_leftLegSPTextField.weighty = 0.3333;
		gbc_leftLegSPTextField.ipadx = 4;
		gbc_leftLegSPTextField.fill = GridBagConstraints.BOTH;
		gbc_leftLegSPTextField.gridx = 6;
		gbc_leftLegSPTextField.gridy = 1;
		armorPanel.add(leftLegArmorTextField, gbc_leftLegSPTextField);
		leftLegArmorTextField.setColumns(10);

		JPanel woundsPanel = new JPanel();
		GridBagConstraints gbc_woundsPanel = new GridBagConstraints();
		gbc_woundsPanel.fill = GridBagConstraints.BOTH;
		gbc_woundsPanel.weightx = 0.6667;
		gbc_woundsPanel.weighty = 0.1667;
		gbc_woundsPanel.insets = new Insets(0, 0, 3, 0);
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
		lblSave.setFont(TITLE_FONT);
		lblSave.setHorizontalAlignment(SwingConstants.CENTER);
		savePanel.add(lblSave, BorderLayout.NORTH);

		saveTextField = new JTextField();
		saveTextField.setEditable(false);
		saveTextField.setHorizontalAlignment(SwingConstants.CENTER);
		saveTextField.setBorder(new LineBorder(Color.BLACK));
		saveTextField.setFont(new Font("Tahoma", Font.PLAIN, 22));
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
		lblBodyTypeModifier.setFont(TITLE_FONT);
		lblBodyTypeModifier.setHorizontalAlignment(SwingConstants.CENTER);
		bodyTypeModifierPanel.add(lblBodyTypeModifier, BorderLayout.NORTH);

		bodyTypeModifierTextField = new JTextField();
		bodyTypeModifierTextField.setEditable(false);
		bodyTypeModifierTextField.setHorizontalAlignment(SwingConstants.CENTER);
		bodyTypeModifierTextField.setBorder(new LineBorder(Color.BLACK));
		bodyTypeModifierTextField.setFont(new Font("Tahoma", Font.PLAIN, 22));
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
		stunPanel.setLayout(new BorderLayout(0, 0));

		JPanel stunGaugePanel = new JPanel();
		stunPanel.add(stunGaugePanel);
		stunGaugePanel.setLayout(new GridLayout(2, 5, 0, 0));

		JPanel lightPanel = new JPanel();
		stunGaugePanel.add(lightPanel);
		lightPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblLightStun = new JLabel("LIGHT");
		lblLightStun.setFont(new Font("Tahoma", Font.BOLD, 8));
		lblLightStun.setHorizontalAlignment(SwingConstants.LEFT);
		lightPanel.add(lblLightStun, BorderLayout.NORTH);

		StunGaugePanel lightStunProgressPanel = new StunGaugePanel();
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
		stunGaugePanel.add(seriousPanel);
		seriousPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblSerious = new JLabel("SERIOUS");
		lblSerious.setFont(new Font("Tahoma", Font.BOLD, 8));
		lblSerious.setHorizontalAlignment(SwingConstants.LEFT);
		seriousPanel.add(lblSerious, BorderLayout.NORTH);

		StunGaugePanel seriousStunProgressPanel = new StunGaugePanel();
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
		stunGaugePanel.add(criticalPanel);
		criticalPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblCritical = new JLabel("CRITICAL");
		lblCritical.setFont(new Font("Tahoma", Font.BOLD, 8));
		lblCritical.setHorizontalAlignment(SwingConstants.LEFT);
		criticalPanel.add(lblCritical, BorderLayout.NORTH);

		StunGaugePanel criticalStunProgressPanel = new StunGaugePanel();
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
		stunGaugePanel.add(mortal0Panel);
		mortal0Panel.setLayout(new BorderLayout(0, 0));

		JLabel lblMortal0 = new JLabel("MORTAL0");
		lblMortal0.setFont(new Font("Tahoma", Font.BOLD, 8));
		lblMortal0.setHorizontalAlignment(SwingConstants.LEFT);
		mortal0Panel.add(lblMortal0, BorderLayout.NORTH);

		StunGaugePanel mortal0StunProgressPanel = new StunGaugePanel();
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
		stunGaugePanel.add(mortal1Panel);
		mortal1Panel.setLayout(new BorderLayout(0, 0));

		JLabel lblMortal1 = new JLabel("MORTAL1");
		lblMortal1.setFont(new Font("Tahoma", Font.BOLD, 8));
		lblMortal1.setHorizontalAlignment(SwingConstants.LEFT);
		mortal1Panel.add(lblMortal1, BorderLayout.NORTH);

		StunGaugePanel mortal1StunProgressPanel = new StunGaugePanel();
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
		stunGaugePanel.add(mortal2Panel);
		mortal2Panel.setLayout(new BorderLayout(0, 0));

		JLabel lblMortal2 = new JLabel("MORTAL2");
		lblMortal2.setFont(new Font("Tahoma", Font.BOLD, 8));
		lblMortal2.setHorizontalAlignment(SwingConstants.LEFT);
		mortal2Panel.add(lblMortal2, BorderLayout.NORTH);

		StunGaugePanel mortal2StunProgressPanel = new StunGaugePanel();
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
		stunGaugePanel.add(mortal3Panel);
		mortal3Panel.setLayout(new BorderLayout(0, 0));

		JLabel lblMortal3 = new JLabel("MORTAL3");
		lblMortal3.setHorizontalAlignment(SwingConstants.LEFT);
		lblMortal3.setFont(new Font("Tahoma", Font.BOLD, 8));
		mortal3Panel.add(lblMortal3, BorderLayout.NORTH);

		StunGaugePanel mortal3StunProgressPanel = new StunGaugePanel();
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
		stunGaugePanel.add(mortal4Panel);
		mortal4Panel.setLayout(new BorderLayout(0, 0));

		JLabel lblMortal4 = new JLabel("MORTAL4");
		lblMortal4.setHorizontalAlignment(SwingConstants.LEFT);
		lblMortal4.setFont(new Font("Tahoma", Font.BOLD, 8));
		mortal4Panel.add(lblMortal4, BorderLayout.NORTH);

		StunGaugePanel mortal4StunProgressPanel = new StunGaugePanel();
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
		stunGaugePanel.add(mortal5Panel);
		mortal5Panel.setLayout(new BorderLayout(0, 0));

		JLabel lblMortal5 = new JLabel("MORTAL5");
		lblMortal5.setHorizontalAlignment(SwingConstants.LEFT);
		lblMortal5.setFont(new Font("Tahoma", Font.BOLD, 8));
		mortal5Panel.add(lblMortal5, BorderLayout.NORTH);

		StunGaugePanel mortal5ProgressPanel = new StunGaugePanel();
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
		stunGaugePanel.add(mortal6Panel);
		mortal6Panel.setLayout(new BorderLayout(0, 0));

		JLabel lblMortal6 = new JLabel("MORTAL6");
		lblMortal6.setHorizontalAlignment(SwingConstants.LEFT);
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

		JPanel stunControlPanel = new JPanel();
		stunPanel.add(stunControlPanel, BorderLayout.EAST);
		GridBagLayout gbl_stunControlPanel = new GridBagLayout();
		gbl_stunControlPanel.columnWidths = new int[] { 17, 0 };
		gbl_stunControlPanel.rowHeights = new int[] { 17, 17, 17, 17, 0 };
		gbl_stunControlPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_stunControlPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		stunControlPanel.setLayout(gbl_stunControlPanel);

		increaseInjuryButton = new JButton(
				new ImageIcon(new ImageIcon(CharacterCreationView.class.getResource("/img/increase-arrow-64x64.png"))
						.getImage().getScaledInstance(8, 8, Image.SCALE_SMOOTH)));
		increaseInjuryButton.setPreferredSize(new Dimension(8, 8));
		GridBagConstraints gbc_increaseInjuryButton = new GridBagConstraints();
		gbc_increaseInjuryButton.fill = GridBagConstraints.BOTH;
		gbc_increaseInjuryButton.weighty = 0.3333;
		gbc_increaseInjuryButton.weightx = 1.0;
		gbc_increaseInjuryButton.gridx = 0;
		gbc_increaseInjuryButton.gridy = 0;
		stunControlPanel.add(increaseInjuryButton, gbc_increaseInjuryButton);

		minorlyIncreaseInjuryButton = new JButton(new ImageIcon(
				new ImageIcon(CharacterCreationView.class.getResource("/img/increase-arrow-minor-64x64.png")).getImage()
						.getScaledInstance(8, 8, Image.SCALE_SMOOTH)));
		minorlyIncreaseInjuryButton.setPreferredSize(new Dimension(8, 8));
		GridBagConstraints gbc_slightIncreaseInjuryButton = new GridBagConstraints();
		gbc_slightIncreaseInjuryButton.fill = GridBagConstraints.BOTH;
		gbc_slightIncreaseInjuryButton.weighty = 0.1667;
		gbc_slightIncreaseInjuryButton.weightx = 1.0;
		gbc_slightIncreaseInjuryButton.gridx = 0;
		gbc_slightIncreaseInjuryButton.gridy = 1;
		stunControlPanel.add(minorlyIncreaseInjuryButton, gbc_slightIncreaseInjuryButton);

		minorlyDecreaseInjuryButton = new JButton(new ImageIcon(
				new ImageIcon(CharacterCreationView.class.getResource("/img/decrease-arrow-minor-64x64.png")).getImage()
						.getScaledInstance(8, 8, Image.SCALE_SMOOTH)));
		minorlyDecreaseInjuryButton.setPreferredSize(new Dimension(8, 8));
		GridBagConstraints gbc_slightDecreaseInjuryButton = new GridBagConstraints();
		gbc_slightDecreaseInjuryButton.fill = GridBagConstraints.BOTH;
		gbc_slightDecreaseInjuryButton.weighty = 0.1667;
		gbc_slightDecreaseInjuryButton.weightx = 1.0;
		gbc_slightDecreaseInjuryButton.gridx = 0;
		gbc_slightDecreaseInjuryButton.gridy = 2;
		stunControlPanel.add(minorlyDecreaseInjuryButton, gbc_slightDecreaseInjuryButton);

		decreaseInjuryButton = new JButton(
				new ImageIcon(new ImageIcon(CharacterCreationView.class.getResource("/img/decrease-arrow-64x64.png"))
						.getImage().getScaledInstance(8, 8, Image.SCALE_SMOOTH)));
		decreaseInjuryButton.setPreferredSize(new Dimension(8, 8));

		GridBagConstraints gbc_decreaseInjuryButton = new GridBagConstraints();
		gbc_decreaseInjuryButton.fill = GridBagConstraints.BOTH;
		gbc_decreaseInjuryButton.weighty = 0.3333;
		gbc_decreaseInjuryButton.weightx = 1.0;
		gbc_decreaseInjuryButton.gridx = 0;
		gbc_decreaseInjuryButton.gridy = 3;
		stunControlPanel.add(decreaseInjuryButton, gbc_decreaseInjuryButton);

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

		lblPortrait = new JLabel(new ImageIcon(CharacterCreationView.class.getResource("/img/Adam_MD_bust.png")));
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
		cards.addTab(CharacterCreationModel.SPEC, null, cardSpecialAbilities, null);
		cardSpecialAbilities.setLayout(new BorderLayout(0, 0));

		JSplitPane specialAbilitiesSplitPane = new JSplitPane();
		cardSpecialAbilities.add(specialAbilitiesSplitPane);

		JScrollPane specialAbilitiesSkillScrollPane = new JScrollPane();
		specialAbilitiesSplitPane.setLeftComponent(specialAbilitiesSkillScrollPane);

		specialAbilitiesSkillTable = new JTable(new SkillTableModel());
		specialAbilitiesSkillTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		specialAbilitiesSkillTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		specialAbilitiesSkillTable.setFont(new Font("Tahoma", Font.PLAIN, 10));
		specialAbilitiesSkillTable.getColumnModel().getColumn(2).setCellEditor(new SpinnerEditor());
		specialAbilitiesSkillTable.setFillsViewportHeight(true);
		specialAbilitiesSkillScrollPane.setViewportView(specialAbilitiesSkillTable);
		skillTables.put(CharacterCreationModel.SPEC, specialAbilitiesSkillTable);

		JScrollPane specialAbilitiesDescriptionScrollPane = new JScrollPane();
		specialAbilitiesDescriptionScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		specialAbilitiesSplitPane.setRightComponent(specialAbilitiesDescriptionScrollPane);

		JTextArea specialAbilitiesTextArea = new JTextArea();
		specialAbilitiesTextArea.setWrapStyleWord(true);
		specialAbilitiesTextArea.setLineWrap(true);
		specialAbilitiesTextArea.setFont(new Font("Tahoma", Font.PLAIN, 10));
		specialAbilitiesTextArea.setEditable(false);
		specialAbilitiesDescriptionScrollPane.setViewportView(specialAbilitiesTextArea);
		skillTextAreas.put(CharacterCreationModel.SPEC, specialAbilitiesTextArea);

		JPanel cardAttractiveness = new JPanel();
		cards.addTab(CharacterCreationModel.ATT, null, cardAttractiveness, null);
		cardAttractiveness.setLayout(new BorderLayout(0, 0));

		JSplitPane attractivenessSplitPane = new JSplitPane();
		attractivenessSplitPane.setResizeWeight(0.25);
		cardAttractiveness.add(attractivenessSplitPane);

		JScrollPane attractivenessSkillScrollPane = new JScrollPane();
		attractivenessSplitPane.setLeftComponent(attractivenessSkillScrollPane);

		attractivenessSkillTable = new JTable(new SkillTableModel());
		attractivenessSkillTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		attractivenessSkillTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		attractivenessSkillTable.setFont(new Font("Tahoma", Font.PLAIN, 10));
		attractivenessSkillTable.getColumnModel().getColumn(2).setCellEditor(new SpinnerEditor());
		attractivenessSkillTable.setFillsViewportHeight(true);
		attractivenessSkillScrollPane.setViewportView(attractivenessSkillTable);
		skillTables.put(CharacterCreationModel.ATT, attractivenessSkillTable);

		JScrollPane attractivenessDescriptionScrollPane = new JScrollPane();
		attractivenessDescriptionScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		attractivenessSplitPane.setRightComponent(attractivenessDescriptionScrollPane);

		JTextArea attractivenessTextArea = new JTextArea();
		attractivenessTextArea.setWrapStyleWord(true);
		attractivenessTextArea.setLineWrap(true);
		attractivenessTextArea.setFont(new Font("Tahoma", Font.PLAIN, 10));
		attractivenessTextArea.setEditable(false);
		attractivenessDescriptionScrollPane.setViewportView(attractivenessTextArea);
		skillTextAreas.put(CharacterCreationModel.ATT, attractivenessTextArea);

		JPanel cardBody = new JPanel();
		cards.addTab(CharacterCreationModel.BOD, null, cardBody, null);
		cardBody.setLayout(new BorderLayout(0, 0));

		JSplitPane bodySplitPane = new JSplitPane();
		bodySplitPane.setResizeWeight(0.25);
		cardBody.add(bodySplitPane);

		JScrollPane bodySkillScrollPane = new JScrollPane();
		bodySplitPane.setLeftComponent(bodySkillScrollPane);

		bodySkillTable = new JTable(new SkillTableModel());
		bodySkillTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bodySkillTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		bodySkillTable.setFont(new Font("Tahoma", Font.PLAIN, 10));
		bodySkillTable.getColumnModel().getColumn(2).setCellEditor(new SpinnerEditor());
		bodySkillTable.setFillsViewportHeight(true);
		bodySkillScrollPane.setViewportView(bodySkillTable);
		skillTables.put(CharacterCreationModel.BOD, bodySkillTable);

		JScrollPane bodyDescriptionScrollPane = new JScrollPane();
		bodySplitPane.setRightComponent(bodyDescriptionScrollPane);

		JTextArea bodyTextArea = new JTextArea();
		bodyTextArea.setFont(new Font("Tahoma", Font.PLAIN, 10));
		bodyTextArea.setWrapStyleWord(true);
		bodyTextArea.setLineWrap(true);
		bodyDescriptionScrollPane.setViewportView(bodyTextArea);
		skillTextAreas.put(CharacterCreationModel.BOD, bodyTextArea);

		JPanel cardCool = new JPanel();
		cards.addTab(CharacterCreationModel.CL, null, cardCool, null);
		cardCool.setLayout(new BorderLayout(0, 0));

		JSplitPane coolSplitPane = new JSplitPane();
		coolSplitPane.setResizeWeight(0.25);
		cardCool.add(coolSplitPane);

		JScrollPane coolSkillScrollPane = new JScrollPane();
		coolSplitPane.setLeftComponent(coolSkillScrollPane);

		coolSkillTable = new JTable(new SkillTableModel());
		coolSkillTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		coolSkillTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		coolSkillTable.setFont(new Font("Tahoma", Font.PLAIN, 10));
		coolSkillTable.getColumnModel().getColumn(2).setCellEditor(new SpinnerEditor());
		coolSkillTable.setFillsViewportHeight(true);
		coolSkillScrollPane.setViewportView(coolSkillTable);
		skillTables.put(CharacterCreationModel.CL, coolSkillTable);

		JScrollPane coolDescriptionScrollPane = new JScrollPane();
		coolSplitPane.setRightComponent(coolDescriptionScrollPane);

		JTextArea coolTextArea = new JTextArea();
		coolTextArea.setFont(new Font("Tahoma", Font.PLAIN, 10));
		coolTextArea.setLineWrap(true);
		coolTextArea.setWrapStyleWord(true);
		coolDescriptionScrollPane.setViewportView(coolTextArea);
		skillTextAreas.put(CharacterCreationModel.CL, coolTextArea);

		JPanel cardEmpathy = new JPanel();
		cards.addTab(CharacterCreationModel.EMP, null, cardEmpathy, null);
		cardEmpathy.setLayout(new BorderLayout(0, 0));

		JSplitPane empathySplitPane = new JSplitPane();
		empathySplitPane.setResizeWeight(0.25);
		cardEmpathy.add(empathySplitPane);

		JScrollPane empathySkillScrollPane = new JScrollPane();
		empathySplitPane.setLeftComponent(empathySkillScrollPane);

		empathySkillTable = new JTable(new SkillTableModel());
		empathySkillTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		empathySkillTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		empathySkillTable.setFont(new Font("Tahoma", Font.PLAIN, 10));
		empathySkillTable.getColumnModel().getColumn(2).setCellEditor(new SpinnerEditor());
		empathySkillTable.setFillsViewportHeight(true);
		empathySkillScrollPane.setViewportView(empathySkillTable);
		skillTables.put(CharacterCreationModel.EMP, empathySkillTable);

		JScrollPane empathyDescriptionScrollPane = new JScrollPane();
		empathySplitPane.setRightComponent(empathyDescriptionScrollPane);

		JTextArea empathyTextArea = new JTextArea();
		empathyTextArea.setFont(new Font("Tahoma", Font.PLAIN, 10));
		empathyTextArea.setWrapStyleWord(true);
		empathyTextArea.setLineWrap(true);
		empathyDescriptionScrollPane.setViewportView(empathyTextArea);
		skillTextAreas.put(CharacterCreationModel.EMP, empathyTextArea);

		JPanel cardIntelligence = new JPanel();
		cards.addTab(CharacterCreationModel.INT, null, cardIntelligence, null);
		cardIntelligence.setLayout(new BorderLayout(0, 0));

		JSplitPane intelligenceSplitPane = new JSplitPane();
		intelligenceSplitPane.setResizeWeight(0.25);
		cardIntelligence.add(intelligenceSplitPane);

		JScrollPane intelligenceSkillScrollPane = new JScrollPane();
		intelligenceSplitPane.setLeftComponent(intelligenceSkillScrollPane);

		intelligenceSkillTable = new JTable(new SkillTableModel());
		intelligenceSkillTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		intelligenceSkillTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		intelligenceSkillTable.setFont(new Font("Tahoma", Font.PLAIN, 10));
		intelligenceSkillTable.getColumnModel().getColumn(2).setCellEditor(new SpinnerEditor());
		intelligenceSkillTable.setFillsViewportHeight(true);
		intelligenceSkillScrollPane.setViewportView(intelligenceSkillTable);
		skillTables.put(CharacterCreationModel.INT, intelligenceSkillTable);

		JScrollPane intelligenceDescriptionScrollPane = new JScrollPane();
		intelligenceSplitPane.setRightComponent(intelligenceDescriptionScrollPane);

		JTextArea intelligenceTextArea = new JTextArea();
		intelligenceTextArea.setFont(new Font("Tahoma", Font.PLAIN, 10));
		intelligenceTextArea.setWrapStyleWord(true);
		intelligenceTextArea.setLineWrap(true);
		intelligenceDescriptionScrollPane.setViewportView(intelligenceTextArea);
		skillTextAreas.put(CharacterCreationModel.INT, intelligenceTextArea);

		JPanel cardReflexes = new JPanel();
		cards.addTab(CharacterCreationModel.REF, null, cardReflexes, null);
		cardReflexes.setLayout(new BorderLayout(0, 0));

		JSplitPane reflexesSplitPane = new JSplitPane();
		reflexesSplitPane.setResizeWeight(0.25);
		cardReflexes.add(reflexesSplitPane);

		JScrollPane reflexesSkillScrollPane = new JScrollPane();
		reflexesSplitPane.setLeftComponent(reflexesSkillScrollPane);

		reflexesSkillTable = new JTable(new SkillTableModel());
		reflexesSkillTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		reflexesSkillTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		reflexesSkillTable.setFont(new Font("Tahoma", Font.PLAIN, 10));
		reflexesSkillTable.getColumnModel().getColumn(2).setCellEditor(new SpinnerEditor());
		reflexesSkillTable.setFillsViewportHeight(true);
		reflexesSkillScrollPane.setViewportView(reflexesSkillTable);
		skillTables.put(CharacterCreationModel.REF, reflexesSkillTable);

		JScrollPane reflexesDescriptionScrollPane = new JScrollPane();
		reflexesSplitPane.setRightComponent(reflexesDescriptionScrollPane);

		JTextArea reflexesTextArea = new JTextArea();
		reflexesTextArea.setFont(new Font("Tahoma", Font.PLAIN, 10));
		reflexesTextArea.setWrapStyleWord(true);
		reflexesTextArea.setLineWrap(true);
		reflexesDescriptionScrollPane.setViewportView(reflexesTextArea);
		skillTextAreas.put(CharacterCreationModel.REF, reflexesTextArea);

		JPanel cardTechnicalAbilities = new JPanel();
		cards.addTab(CharacterCreationModel.TECH, null, cardTechnicalAbilities, null);
		cardTechnicalAbilities.setLayout(new BorderLayout(0, 0));

		JSplitPane technicalAbilitiesSplitPane = new JSplitPane();
		technicalAbilitiesSplitPane.setResizeWeight(0.25);
		cardTechnicalAbilities.add(technicalAbilitiesSplitPane);

		JScrollPane technicalAbilitiesSkillScrollPane = new JScrollPane();
		technicalAbilitiesSplitPane.setLeftComponent(technicalAbilitiesSkillScrollPane);

		technicalAbilitiesSkillTable = new JTable(new SkillTableModel());
		technicalAbilitiesSkillTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		technicalAbilitiesSkillTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		technicalAbilitiesSkillTable.setFont(new Font("Tahoma", Font.PLAIN, 10));
		technicalAbilitiesSkillTable.getColumnModel().getColumn(2).setCellEditor(new SpinnerEditor());
		technicalAbilitiesSkillTable.setFillsViewportHeight(true);
		technicalAbilitiesSkillScrollPane.setViewportView(technicalAbilitiesSkillTable);
		skillTables.put(CharacterCreationModel.TECH, technicalAbilitiesSkillTable);

		JScrollPane technicalDescriptionScrollPane = new JScrollPane();
		technicalAbilitiesSplitPane.setRightComponent(technicalDescriptionScrollPane);

		JTextArea technicalAbilitiesTextArea = new JTextArea();
		technicalAbilitiesTextArea.setWrapStyleWord(true);
		technicalAbilitiesTextArea.setLineWrap(true);
		technicalAbilitiesTextArea.setFont(new Font("Tahoma", Font.PLAIN, 10));
		technicalDescriptionScrollPane.setViewportView(technicalAbilitiesTextArea);
		skillTextAreas.put(CharacterCreationModel.TECH, technicalAbilitiesTextArea);

		JPanel cyberneticsPanel = new JPanel();
		tabbedPane.addTab("Cybernetics", null, cyberneticsPanel, null);

		JPanel lifepathPanel = new JPanel();
		tabbedPane.addTab("Lifepath", null, lifepathPanel, null);

		JSplitPane inventoryPane = new JSplitPane();
		inventoryPane.setResizeWeight(0.5);
		tabbedPane.addTab("Inventory", null, inventoryPane, null);

		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new CharFilter());

		setUpStatComboBoxes();
	}

	public void setUpStatComboBoxes() {
		String labelText;
		final int totalStatCount = 9;
		statComboBoxes = new JComboBox[totalStatCount];
		statComboBoxLabels = new JLabel[totalStatCount];

		Random rnd = new Random();
		int die1 = rnd.nextInt(8) + 3;
		int die2 = rnd.nextInt(8) + 3;
		int die3 = rnd.nextInt(8) + 3;
		int die4 = rnd.nextInt(8) + 3;
		int die5 = rnd.nextInt(8) + 3;
		int die6 = rnd.nextInt(8) + 3;
		int die7 = rnd.nextInt(8) + 3;
		int die8 = rnd.nextInt(8) + 3;
		int die9 = rnd.nextInt(8) + 3;
		Integer[] dice = new Integer[] { die1, die2, die3, die4, die5, die6, die7, die8, die9 };

		for (int i = 0; i < totalStatCount; i++) {
			statComboBoxes[i] = new JComboBox<Integer>(dice);
			statComboBoxes[i].setSelectedIndex(i);

			switch (i) {
			case 0:
				labelText = "INT";
				break;
			case 1:
				labelText = "REF";
				break;
			case 2:
				labelText = "TECH";
				break;
			case 3:
				labelText = "COOL";
				break;
			case 4:
				labelText = "ATTR";
				break;
			case 5:
				labelText = "LUCK";
				break;
			case 6:
				labelText = "MA";
				break;
			case 7:
				labelText = "BODY";
				break;
			case 8:
				labelText = "EMP";
				break;
			default:
				labelText = "";
				break;
			}

			statComboBoxLabels[i] = new JLabel(labelText);
		}
	}

	public void addAttractivenessStatChangeListener(ChangeListener listenerForStatChange) {
		attractivenessSpinner.addChangeListener(listenerForStatChange);
	}

	public void addBodyStatChangeListener(ChangeListener listenerForStatChange) {
		bodySpinner.addChangeListener(listenerForStatChange);
	}

	public void addCoolStatChangeListener(ChangeListener listenerForStatChange) {
		coolSpinner.addChangeListener(listenerForStatChange);
	}

	public void addDecreaseInjuryActionListener(ActionListener listenerForDecreaseInjuryButton) {
		decreaseInjuryButton.addActionListener(listenerForDecreaseInjuryButton);
	}

	public void addHandleDocumentListener(DocumentListener listenerForHandleDocument) {
		handleTextField.getDocument().addDocumentListener(listenerForHandleDocument);
	}

	public void addIncreaseInjuryActionListener(ActionListener listenerForIncreaseInjuryButton) {
		increaseInjuryButton.addActionListener(listenerForIncreaseInjuryButton);
	}

	public void addIntelligenceStatChangeListener(ChangeListener listenerForStatChange) {
		intelligenceSpinner.addChangeListener(listenerForStatChange);
	}

	public void addLoadCharacterButtonActionListener(ActionListener listenerForLoadButton) {
		loadButton.addActionListener(listenerForLoadButton);
	}

	public void addLuckStatChangeListener(ChangeListener listenerForStatChange) {
		luckSpinner.addChangeListener(listenerForStatChange);
	}

	public void addMinorlyDecreaseInjuryActionListener(ActionListener listenerForMinorlyDecreaseInjuryButton) {
		minorlyDecreaseInjuryButton.addActionListener(listenerForMinorlyDecreaseInjuryButton);
	}

	public void addMinorlyIncreaseInjuryActionListener(ActionListener listenerForMinorlyIncreaseInjuryButton) {
		minorlyIncreaseInjuryButton.addActionListener(listenerForMinorlyIncreaseInjuryButton);
	}

	public void addMovementAllowanceStatChangeListener(ChangeListener listenerForStatChange) {
		movementAllowanceSpinner.addChangeListener(listenerForStatChange);
	}

	public void addRoleChangeListener(ActionListener listenerForRoleChange) {
		roleComboBox.addActionListener(listenerForRoleChange);
	}

	public void addSaveCharacterButtonActionListener(ActionListener listenerForSaveButton) {
		saveButton.addActionListener(listenerForSaveButton);
	}

	public void addSelectAllTextFocusListeners(FocusAdapter adapter) {
		JSpinner[] skillSpinners = new JSpinner[] { intelligenceSpinner, unmodifiedReflexesSpinner,
				technicalAbilitySpinner, coolSpinner, attractivenessSpinner, luckSpinner, movementAllowanceSpinner,
				bodySpinner, totalEmpathySpinner };
		for (JSpinner spinner : skillSpinners) {
			JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinner.getEditor();
			JTextField textField = editor.getTextField();
			textField.addFocusListener(adapter);
		}
	}

	public void addListSelectionListeners(ListSelectionListener listener) {
		for (JTable table : skillTables.values()) {
			ListSelectionModel selectionModel = table.getSelectionModel();
			selectionModel.addListSelectionListener(listener);
		}
	}

	public void addTechnicalAbilityStatChangeListener(ChangeListener listenerForStatChange) {
		technicalAbilitySpinner.addChangeListener(listenerForStatChange);
	}

	public void addTotalEmpathyStatChangeListener(ChangeListener listenerForStatChange) {
		totalEmpathySpinner.addChangeListener(listenerForStatChange);
	}

	public void addUnmodifiedReflexesStatChangeListener(ChangeListener listenerForStatChange) {
		unmodifiedReflexesSpinner.addChangeListener(listenerForStatChange);
	}

	public void addRandomCharacterPointButtonActionListener(ActionListener listenerForRandomPoints) {
		randomCharacterPointsButton.addActionListener(listenerForRandomPoints);
	}

	public void addFastCharacterPointButtonActionListener(ActionListener listenerForFastPoints) {
		fastCharacterPointsButton.addActionListener(listenerForFastPoints);
	}

	public void addManualCharacterPointButtonActionListener(ActionListener listenerForManualPoints) {
		manualCharacterPointsButton.addActionListener(listenerForManualPoints);
	}

	public void addUploadButtonActionListener(ActionListener listener) {
		btnChangePortrait.addActionListener(listener);
	}

	public void clearSkillTables() {
		Map<String, JTable> skillTables = new HashMap<String, JTable>();
		skillTables.put(CharacterCreationModel.SPEC, specialAbilitiesSkillTable);
		skillTables.put(CharacterCreationModel.ATT, attractivenessSkillTable);
		skillTables.put(CharacterCreationModel.BOD, bodySkillTable);
		skillTables.put(CharacterCreationModel.CL, coolSkillTable);
		skillTables.put(CharacterCreationModel.EMP, empathySkillTable);
		skillTables.put(CharacterCreationModel.INT, intelligenceSkillTable);
		skillTables.put(CharacterCreationModel.REF, reflexesSkillTable);
		skillTables.put(CharacterCreationModel.TECH, technicalAbilitiesSkillTable);
		for (JTable table : skillTables.values()) {
			table.setModel(new SkillTableModel());
			table.getColumnModel().getColumn(2).setCellEditor(new SpinnerEditor());
			table.revalidate();
			table.repaint();
		}

	}

	public void drawBasicSkillPanel(String skillCategory, String skill, int rank, String specificSkill,
			TableModelListener listener) {
		JTable targetTable;
		Object[] row;

		switch (skillCategory.toUpperCase()) {
		case CharacterCreationModel.SPEC:
			targetTable = specialAbilitiesSkillTable;
			break;
		case CharacterCreationModel.ATT:
			targetTable = attractivenessSkillTable;
			break;
		case CharacterCreationModel.BOD:
			targetTable = bodySkillTable;
			break;
		case CharacterCreationModel.CL:
			targetTable = coolSkillTable;
			break;
		case CharacterCreationModel.EMP:
			targetTable = empathySkillTable;
			break;
		case CharacterCreationModel.INT:
			targetTable = intelligenceSkillTable;
			break;
		case CharacterCreationModel.REF:
			targetTable = reflexesSkillTable;
			break;
		case CharacterCreationModel.TECH:
			targetTable = technicalAbilitiesSkillTable;
			break;
		default:
			targetTable = null;
			break;
		}

		SkillTableModel model = (SkillTableModel) targetTable.getModel();
		model.addTableModelListener(listener);
		row = new Object[] { skill, specificSkill, Integer.valueOf(rank) };
		model.addRow(row);

	}

	public void drawDecreaseInjuryPoints(double points) {
		int targetIndex = (int) (points / MAXIMUM_CELLS_PER_GAUGE);
		StunGaugePanel targetPanel = stunProgressPanels.get(targetIndex);
		if ((points + 1) % MAXIMUM_CELLS_PER_GAUGE < 1 && (points + 1) % MAXIMUM_CELLS_PER_GAUGE > 0) {
			targetPanel.minorlyDecreaseStunGauge();
			targetPanel.revalidate();
			targetPanel.repaint();
			targetPanel = stunProgressPanels.get(targetIndex + 1);
			targetPanel.minorlyDecreaseStunGauge();
			targetPanel.revalidate();
			targetPanel.repaint();
		} else {
			targetPanel.decreaseStunGauge();
			targetPanel.revalidate();
			targetPanel.repaint();
		}
	}

	public void drawIncreasedInjuryPoints(double points) {
		int targetIndex = (int) ((points - 1) / MAXIMUM_CELLS_PER_GAUGE);
		StunGaugePanel targetPanel = stunProgressPanels.get(targetIndex);
		if (points % MAXIMUM_CELLS_PER_GAUGE < 1 && points % MAXIMUM_CELLS_PER_GAUGE > 0) {
			targetPanel.minorlyIncreaseStunGauge();
			targetPanel.revalidate();
			targetPanel.repaint();
			targetPanel = stunProgressPanels.get(targetIndex + 1);
			targetPanel.minorlyIncreaseStunGauge();
			targetPanel.revalidate();
			targetPanel.repaint();
		} else {
			targetPanel.increaseStunGauge();
			targetPanel.revalidate();
			targetPanel.repaint();
		}
	}

	public void drawLoadedInjuryPoints(double points) {
		for (StunGaugePanel panel : stunProgressPanels) {
			panel.rect.setRect(new Rectangle2D.Double(0, 0, 0, 10));
			panel.revalidate();
			panel.repaint();
		}

		int fullStunGauges = (int) (points / CharacterCreationView.MAXIMUM_CELLS_PER_GAUGE) - 1;
		for (int i = 0; i <= fullStunGauges; i++) {
			fillStunGauge(i);
		}

		double remainderStunGauges = points % CharacterCreationView.MAXIMUM_CELLS_PER_GAUGE;
		for (int j = 0; j < remainderStunGauges; j++) {
			if (remainderStunGauges % 1 == 0) {
				stunProgressPanels.get(fullStunGauges + 1).increaseStunGauge();
			} else if ((remainderStunGauges - j) >= 1) {
				stunProgressPanels.get(fullStunGauges + 1).increaseStunGauge();
			} else {
				stunProgressPanels.get(fullStunGauges + 1).minorlyIncreaseStunGauge();
			}
		}
	}

	public void drawMinorlyDecreaseInjuryPoints(double points) {
		int targetIndex = (int) (points / MAXIMUM_CELLS_PER_GAUGE);
		StunGaugePanel targetPanel = stunProgressPanels.get(targetIndex);
		targetPanel.minorlyDecreaseStunGauge();

		targetPanel.revalidate();
		targetPanel.repaint();
	}

	public void drawMinorlyIncreasedInjuryPoints(double points) {
		int targetIndex = (int) ((points - 0.5) / MAXIMUM_CELLS_PER_GAUGE);
		StunGaugePanel targetPanel = stunProgressPanels.get(targetIndex);
		targetPanel.minorlyIncreaseStunGauge();

		targetPanel.revalidate();
		targetPanel.repaint();
	}

	public String getCharacterName() {
		return handleTextField.getText();
	}

	public String getRole() {
		return (String) roleComboBox.getSelectedItem();
	}

	public int getCharacterPoints() {
		return Integer.parseInt(characterPointsTextField.getText());
	}

	public int getIntelligenceLevel() {
		return (Integer) intelligenceSpinner.getValue();
	}

	public int getUnmodifiedReflexesLevel() {
		return (Integer) unmodifiedReflexesSpinner.getValue();
	}

	public int getModifiedReflexesLevel() {
		return Integer.parseInt(modifiedReflexesTextField.getText());
	}

	public int getTechnicalAbilityLevel() {
		return (Integer) technicalAbilitySpinner.getValue();
	}

	public int getCoolLevel() {
		return (Integer) coolSpinner.getValue();
	}

	public int getAttractivenessLevel() {
		return (Integer) attractivenessSpinner.getValue();
	}

	public int getLuckLevel() {
		return (Integer) luckSpinner.getValue();
	}

	public int getMovementAllowanceLevel() {
		return (Integer) movementAllowanceSpinner.getValue();
	}

	public int getBodyLevel() {
		return (Integer) bodySpinner.getValue();
	}

	public int getCurrentEmpathyLevel() {
		return Integer.parseInt(currentEmpathyTextField.getText());
	}

	public int getTotalEmpathyLevel() {
		return (Integer) totalEmpathySpinner.getValue();
	}

	public int getRunLevel() {
		return Integer.parseInt(runTextField.getText());
	}

	public int getLeapLevel() {
		return Integer.parseInt(leapTextField.getText());
	}

	public int getLiftLevel() {
		return Integer.parseInt(liftTextField.getText());
	}

	public int getHeadArmor() {
		return Integer.parseInt(headArmorTextField.getText());
	}

	public int getTorsoArmor() {
		return Integer.parseInt(torsoArmorTextField.getText());
	}

	public int getRightArmArmor() {
		return Integer.parseInt(rightArmArmorTextField.getText());
	}

	public int getLeftArmArmor() {
		return Integer.parseInt(leftArmArmorTextField.getText());
	}

	public int getRightLegArmor() {
		return Integer.parseInt(rightLegArmorTextField.getText());
	}

	public int getLeftLegArmor() {
		return Integer.parseInt(leftLegArmorTextField.getText());
	}

	public int getSaveModifier() {
		return Integer.parseInt(saveTextField.getText());
	}

	public int getBodyTypeModifier() {
		return Integer.parseInt(bodyTypeModifierTextField.getText());
	}

	public BufferedImage getCharacterPortrait() {
		Icon icon = lblPortrait.getIcon();

		BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.createGraphics();
		icon.paintIcon(null, g, 0, 0);
		g.dispose();

		return bi;
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	public List<StunGaugePanel> getStunProgress() {
		return stunProgressPanels;
	}

	public JSpinner[] getSkillSpinners() {
		return skillSpinners;
	}

	public Map<String, JTable> getSkillTables() {
		return skillTables;
	}

	public Map<String, JTextArea> getSkillTextAreas() {
		return skillTextAreas;
	}

	public JComboBox<Integer>[] getStatComboBoxes() {
		return statComboBoxes;
	}

	public JLabel[] getStatComboBoxLabels() {
		return statComboBoxLabels;
	}

	public void setHandle(String handle) {
		handleTextField.setText(handle);
	}

	public void setRole(String role) {
		roleComboBox.setSelectedItem(role);
	}

	public void setCharacterPoints(String characterPoints) {
		characterPointsTextField.setText(characterPoints);
	}

	public void setIntelligenceLevel(String intelligenceLevel) {
		intelligenceSpinner.setValue(Integer.parseInt(intelligenceLevel));
	}

	public void setUnmodifiedReflexesLevel(String unmodifiedReflexesLevel) {
		unmodifiedReflexesSpinner.setValue(Integer.parseInt(unmodifiedReflexesLevel));
	}

	public void setModifiedReflexesLevel(String modifiedReflexesLevel) {
		modifiedReflexesTextField.setText(modifiedReflexesLevel);
	}

	public void setTechnicalAbilityLevel(String technicalAbilityLevel) {
		technicalAbilitySpinner.setValue(Integer.parseInt(technicalAbilityLevel));
	}

	public void setCoolLevel(String coolLevel) {
		coolSpinner.setValue(Integer.parseInt(coolLevel));
	}

	public void setAttractivenessLevel(String attractivenessLevel) {
		attractivenessSpinner.setValue(Integer.parseInt(attractivenessLevel));
	}

	public void setLuckLevel(String luckLevel) {
		luckSpinner.setValue(Integer.parseInt(luckLevel));
	}

	public void setMovementAllowanceLevel(String movementAllowanceLevel) {
		movementAllowanceSpinner.setValue(Integer.parseInt(movementAllowanceLevel));
	}

	public void setBodyLevel(String bodyLevel) {
		bodySpinner.setValue(Integer.parseInt(bodyLevel));
	}

	public void setCurrentEmpathyLevel(String currentEmpathyLevel) {
		currentEmpathyTextField.setText(currentEmpathyLevel);
	}

	public void setTotalEmpathyLevel(String totalEmpathyLevel) {
		totalEmpathySpinner.setValue(Integer.parseInt(totalEmpathyLevel));
	}

	public void setRunLevel(String runLevel) {
		runTextField.setText(runLevel + "m");
	}

	public void setLeapLevel(String leapLevel) {
		leapTextField.setText(leapLevel + "m");
	}

	public void setLiftLevel(String liftLevel) {
		liftTextField.setText(liftLevel + "kg");
	}

	public void setHeadArmorStoppingPower(String headArmor) {
		headArmorTextField.setText(headArmor);
	}

	public void setTorsoArmorStoppingPower(String torsoArmor) {
		torsoArmorTextField.setText(torsoArmor);
	}

	public void setRightArmArmorStoppingPower(String rightArmArmor) {
		rightArmArmorTextField.setText(rightArmArmor);
	}

	public void setLeftArmArmorStoppingPower(String leftArmArmor) {
		leftArmArmorTextField.setText(leftArmArmor);
	}

	public void setRightLegArmorStoppingPower(String rightLegArmor) {
		rightLegArmorTextField.setText(rightLegArmor);
	}

	public void setLeftLegArmorStoppingPower(String leftLegArmor) {
		leftLegArmorTextField.setText(leftLegArmor);
	}

	public void setSaveModifier(String saveModifier) {
		saveTextField.setText("<=" + saveModifier);
	}

	public void setBodyTypeModifier(String bodyTypeModifier) {
		bodyTypeModifierTextField.setText(bodyTypeModifier);
	}

	public void setCharacterPortrait(BufferedImage image) {
		try {
			int x = (image.getWidth() / 2) - (PORTRAIT_WIDTH / 2);
			int y = (image.getHeight() / 2) - (PORTRAIT_HEIGHT / 2);
			image = image.getSubimage(x, y, PORTRAIT_WIDTH, PORTRAIT_HEIGHT);
			lblPortrait.setIcon(new ImageIcon(image));
		} catch (NullPointerException exception) {
			JOptionPane.showMessageDialog(this, "Image file formatting error accord.");
		}
	}

	private void fillStunGauge(int index) {
		stunProgressPanels.get(index).increaseStunGauge();
		stunProgressPanels.get(index).increaseStunGauge();
		stunProgressPanels.get(index).increaseStunGauge();
		stunProgressPanels.get(index).increaseStunGauge();

	}

	class CharFilter extends FileFilter {
		@Override
		public boolean accept(File file) {
			String filename = file.getName();
			return filename.endsWith(".char") | file.isDirectory();
		}

		@Override
		public String getDescription() {
			return "Character Files";
		}
	}

	public class SkillTableModel extends AbstractTableModel {
		private String[] columnNames = { "Skill", "Specific Skill", "Rank" };
		private List<Object[]> data = new ArrayList<Object[]>();

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public Object getValueAt(int row, int column) {
			return data.get(row)[column];
		}

		public void addRow(Object[] rowData) {
			data.add(rowData);
		}

		public String getColumnName(int column) {
			return columnNames[column];
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			String skillName = (String) getValueAt(row, 0);
			if (column == 1) {
				int rank = (Integer) getValueAt(row, 2);
				if (rank > 0 && skillName.contains("...")) {
					return true;
				} else {
					return false;
				}
			} else {
				if (column > 0) {
					return true;
				} else {
					return false;
				}
			}
		}

		public void setValueAt(Object value, int row, int col) {
			data.get(row)[col] = value;
			fireTableCellUpdated(row, col);
		}
	}

	class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
		JSpinner spinner;

		public SpinnerEditor() {
			spinner = new JSpinner();
		}

		@Override
		public Object getCellEditorValue() {
			return spinner.getValue();
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			spinner.setValue(value);

			JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinner.getEditor();
			editor.getTextField().addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent fe) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							JTextField txt = (JTextField) fe.getSource();
							txt.selectAll();
						}
					});
				}
			});

			return spinner;
		}

	}

	class StunGaugePanel extends JPanel {
		private static final int STUN_PROGRESS_CELL_WIDTH = 15;
		private static final int STUN_PROGRESS_CELL_HEIGHT = 10;

		public Rectangle2D rect = new Rectangle2D.Double(0, 0, 0, STUN_PROGRESS_CELL_HEIGHT);

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			int x = 0;
			int y = 0;
			int width = STUN_PROGRESS_CELL_WIDTH;
			int height = STUN_PROGRESS_CELL_HEIGHT;
			g.setColor(Color.BLACK);
			for (int i = 0; i < MAXIMUM_CELLS_PER_GAUGE; i++) {
				g.drawRect(x, y, width, height);
				x += STUN_PROGRESS_CELL_WIDTH;
			}
			g2.setColor(Color.RED);
			g2.fill(rect);
		}

		public void increaseStunGauge() {
			double newWidth = rect.getWidth() + STUN_PROGRESS_CELL_WIDTH;
			double newHeight = rect.getHeight();
			rect.setRect(new Rectangle2D.Double(0, 0, newWidth, newHeight));
		}

		public void minorlyIncreaseStunGauge() {
			double newWidth = rect.getWidth() + (STUN_PROGRESS_CELL_WIDTH / 2.0);
			double newHeight = rect.getHeight();
			rect.setRect(new Rectangle2D.Double(0, 0, newWidth, newHeight));
		}

		public void decreaseStunGauge() {
			double newWidth = rect.getWidth() - STUN_PROGRESS_CELL_WIDTH;
			double newHeight = rect.getHeight();
			rect = new Rectangle2D.Double(0, 0, newWidth, newHeight);
		}

		public void minorlyDecreaseStunGauge() {
			double newWidth = rect.getWidth() - (STUN_PROGRESS_CELL_WIDTH / 2.0);
			double newHeight = rect.getHeight();
			rect = new Rectangle2D.Double(0, 0, newWidth, newHeight);
		}
	}
}
