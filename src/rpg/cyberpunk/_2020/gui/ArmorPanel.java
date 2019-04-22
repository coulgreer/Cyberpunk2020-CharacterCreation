package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import rpg.Player;
import rpg.cyberpunk._2020.combat.CyberpunkArmor;
import rpg.general.combat.BodyLocation;

/**
 * A panel that displays both a model of the character and displays a list of
 * armor pieces the character is wearing.
 * 
 * @author Coul Greer
 */
public class ArmorPanel extends JPanel implements PropertyChangeListener, Selectable {
	public static final int BORDER_SIZE = 3;

	private static final long serialVersionUID = 1L;

	private Player player;
	private JLabel label;
	private JTable table;
	private SelectionMediator selectionMediator;

	/**
	 * Constructs an ArmorPanel that updates when notified by a Player or the
	 * SelectionMediator is changed. Also, creates child panels that create a
	 * display of the player and the list of a player's armor pieces.
	 * 
	 * @param player            the owner of the equipped armor set
	 * @param selectionMediator manages the selection of panels and allows this
	 *                          ArmorPanel to add itself to the selectionMediator
	 */
	public ArmorPanel(Player player, SelectionMediator selectionMediator) {
		super(new BorderLayout());

		this.player = player;
		player.addPropertyChangeListener(Player.PROPERTY_NAME_ARMOR_EQUIPPED, this);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				selectionMediator.setActive(ArmorPanel.this);
			}
		});

		this.selectionMediator = selectionMediator;
		selectionMediator.registerColleague(this);

		setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

		add(createArmorDisplayPanel(), BorderLayout.WEST);
		add(createArmorListPanel(player), BorderLayout.CENTER);
		add(createArmorSummaryPanel(), BorderLayout.SOUTH);
	}

	private Component createArmorDisplayPanel() {
		JPanel panel = new JPanel();

		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("./img/armor-display-512.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			panel.add(picLabel);
		} catch (IOException e) {
			// TODO Make this meaningful for the dev.
			e.printStackTrace();
		}

		return panel;
	}

	private Component createArmorListPanel(Player player) {
		table = new EquippedArmorTable(player);
		table.setFillsViewportHeight(true);
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(table);
		for (MouseListener listener : getMouseListeners()) {
			table.addMouseListener(listener);
		}

		return scrollPane;
	}

	private Component createArmorSummaryPanel() {
		JPanel panel = new JPanel();

		label = new JLabel(generateArmorSummary());
		panel.add(label);

		return panel;
	}

	private String generateArmorSummary() {
		return "TOTAL Encumbrance Value: " + player.getEncumbranceValue() + " \u2022 " + "Head SP: "
				+ player.getLocationDurability(BodyLocation.HEAD) + " \u2022 " + "L. Arm SP: "
				+ player.getLocationDurability(BodyLocation.LEFT_ARM) + " \u2022 " + "R. Arm SP: "
				+ player.getLocationDurability(BodyLocation.RIGHT_ARM) + " \u2022 " + "Torso SP: "
				+ player.getLocationDurability(BodyLocation.TORSO) + " \u2022 " + "L. Leg SP: "
				+ player.getLocationDurability(BodyLocation.LEFT_LEG) + " \u2022 " + "R. Leg SP: "
				+ player.getLocationDurability(BodyLocation.RIGHT_LEG);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();

		if (source == player) {
			label.setText(generateArmorSummary());
		}
	}

	@Override
	public boolean isSelected() {
		return this == selectionMediator.getSelected();
	}

	@Override
	public void setSelected(boolean isSelected) {
		if (isSelected) {
			setBorder(BorderFactory.createMatteBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, Color.CYAN));
		} else {
			setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));
		}
	}

	/**
	 * Returns the CyberpunkArmor selected from the table of equipped armors.
	 * 
	 * @return the selected CyberpunkArmor from the child table
	 */
	public CyberpunkArmor getSelectedArmor() {
		return (CyberpunkArmor) table.getModel().getValueAt(table.getSelectedRow(),
				EquippedArmorTable.EquippedArmorTableModel.OBJECT_INDEX);
	}

}
