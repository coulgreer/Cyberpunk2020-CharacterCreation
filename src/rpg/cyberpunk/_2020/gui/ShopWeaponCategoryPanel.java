package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import rpg.Player;
import rpg.cyberpunk._2020.commerce.CyberpunkVendor;
import rpg.cyberpunk._2020.gui.ShopWeaponCategoryTable.WeaponShopTableModel;

public class ShopWeaponCategoryPanel extends JPanel {

	public ShopWeaponCategoryPanel(Player player, CyberpunkVendor vendor) {
		setLayout(new BorderLayout());

		JTable table = new ShopWeaponCategoryTable(vendor.getStoredWeapons());
		table.setFillsViewportHeight(true);
		table.getRowSorter().toggleSortOrder(WeaponShopTableModel.TYPE_COLUMN_INDEX);
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);

		BuyButtonPanel buttonPanel = new BuyButtonPanel(player, vendor, table);
		add(buttonPanel, BorderLayout.SOUTH);
	}

}
