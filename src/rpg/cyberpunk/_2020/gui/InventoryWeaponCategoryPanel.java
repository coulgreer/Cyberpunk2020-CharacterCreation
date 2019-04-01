package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import rpg.Player;

/**
 * A panel used to organize elements related to a player's weapon cache.
 * Provides housing for all elements to help ease the effort needed when using a
 * layout manager.
 * 
 * @author Coul Greer
 */
public class InventoryWeaponCategoryPanel extends JPanel {

	/**
	 * Constructs a panel that uses a given player to display data of what is stored
	 * in that player's inventory.
	 * 
	 * @param player the provider of the set of weapons needed to be display
	 */
	public InventoryWeaponCategoryPanel(Player player) {
		setLayout(new BorderLayout());

		JTable table = new InventoryWeaponCategoryTable(player);
		table.setFillsViewportHeight(true);
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
	}

}
