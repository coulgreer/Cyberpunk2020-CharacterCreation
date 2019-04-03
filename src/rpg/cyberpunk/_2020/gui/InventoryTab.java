package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import rpg.Player;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.cyberpunk._2020.commerce.CyberpunkVendor;
import rpg.cyberpunk._2020.gui.InventoryWeaponCategoryTable.InventoryWeaponTableModel;

/**
 * A panel used to keep all the inventory GUI elements organized for layout
 * mangers.
 * 
 * @author Coul Greer
 */
public class InventoryTab extends JPanel {

	/**
	 * Constructs a panel filled with elements needed under the 'Inventory' tab.
	 * 
	 * @param player the object from which data is drawn from and passed to while
	 *               also displaying its contents to the GUI
	 */
	public InventoryTab(Player player, CyberpunkVendor vendor) {
		super(new BorderLayout());

		JPanel infoPanel = new InventoryInfoPanel(player);
		add(infoPanel, BorderLayout.NORTH);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		add(tabbedPane, BorderLayout.CENTER);

		JTable weaponTable = new InventoryWeaponCategoryTable(player);
		tabbedPane.addTab("Weapons",
				new InventoryCategoryPanel(weaponTable, evt -> sellWeapon(weaponTable, player, vendor)));
	}

	private void sellWeapon(JTable table, Player player, CyberpunkVendor vendor) {
		int selectedRowIndex = table.getSelectedRow();
		int actualSelectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

		if (actualSelectedRowIndex >= 0) {
			CyberpunkWeapon weapon = (CyberpunkWeapon) table.getModel().getValueAt(actualSelectedRowIndex,
					InventoryWeaponTableModel.OBJECT_INDEX);

			player.sell(weapon, vendor.getBidPrice(weapon));
		}
	}

}
