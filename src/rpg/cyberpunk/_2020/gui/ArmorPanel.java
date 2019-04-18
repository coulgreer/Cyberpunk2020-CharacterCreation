package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import rpg.Player;
import rpg.general.combat.BodyLocation;

/**
 * A panel that displays both a model of the character and displays a list of
 * armor pieces the character is wearing.
 * 
 * @author Coul Greer
 */
public class ArmorPanel extends JPanel implements PropertyChangeListener {
	private Player player;
	private JLabel label;

	public ArmorPanel(Player player) {
		super(new BorderLayout());

		this.player = player;
		player.addPropertyChangeListener(Player.PROPERTY_NAME_ARMOR_EQUIPPED, this);

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
		JTable table = new EquippedArmorTable(player);
		table.setFillsViewportHeight(true);
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(table);

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

}
