package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import rpg.Player;
import rpg.cyberpunk._2020.commerce.CyberpunkVendor;
import rpg.cyberpunk._2020.gui.ShopWeaponCategoryTable.ShopWeaponTableModel;

/**
 * A panel that holds elements relevant to the weapon category that a vendor
 * has. Helps organize all children elements for layout managers.
 * 
 * @author Coul Greer
 */
public class ShopWeaponCategoryPanel extends JPanel {

	/**
	 * Constructs a panel populated by the weapon's that a vendor holds.
	 * 
	 * @param player a customer of vendor
	 * @param vendor the owner off all products that can be sold to player
	 */
	public ShopWeaponCategoryPanel(Player player, CyberpunkVendor vendor) {
		setLayout(new BorderLayout());

		JTable table = new ShopWeaponCategoryTable(vendor.getStoredWeapons());
		table.setFillsViewportHeight(true);
		table.getRowSorter().toggleSortOrder(ShopWeaponTableModel.TYPE_COLUMN_INDEX);
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new BuyButtonPanel(player, vendor, table);
		add(buttonPanel, BorderLayout.SOUTH);
	}

}
