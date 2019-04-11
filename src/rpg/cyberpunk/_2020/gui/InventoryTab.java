package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import rpg.Player;
import rpg.cyberpunk._2020.combat.CyberpunkArmor;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.cyberpunk._2020.commerce.CyberpunkVendor;
import rpg.cyberpunk._2020.gui.InventoryAmmunitionTable.InventoryAmmunitionTableModel;
import rpg.cyberpunk._2020.gui.InventoryArmorTable.InventoryArmorTableModel;
import rpg.cyberpunk._2020.gui.InventoryItemTable.InventoryItemTableModel;
import rpg.cyberpunk._2020.gui.InventoryWeaponTable.InventoryWeaponTableModel;
import rpg.general.combat.Ammunition;
import rpg.general.commerce.Item;

/**
 * A panel used to keep all the inventory GUI elements organized for layout
 * mangers.
 * 
 * @author Coul Greer
 */
public class InventoryTab extends JPanel {
	private Player player;
	private CyberpunkVendor vendor;

	/**
	 * Constructs a panel filled with elements needed under the 'Inventory' tab.
	 * 
	 * @param player the object from which data is drawn from and passed to while
	 *               also displaying its contents to the GUI
	 */
	public InventoryTab(Player player, CyberpunkVendor vendor) {
		super(new BorderLayout());

		this.player = player;
		this.vendor = vendor;

		JPanel infoPanel = new InventoryInfoPanel(player);
		add(infoPanel, BorderLayout.NORTH);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		add(tabbedPane, BorderLayout.CENTER);

		JTable weaponTable = new InventoryWeaponTable(player);
		weaponTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tabbedPane.addTab("Weapons", new InventoryCategoryPanel(weaponTable, evt -> sellWeapon(weaponTable)));

		JTable armorTable = new InventoryArmorTable(player);
		tabbedPane.addTab("Armor", new InventoryCategoryPanel(armorTable, evt -> sellArmor(armorTable)));

		JTable ammunitionTable = new InventoryAmmunitionTable(player);
		tabbedPane.addTab("Ammunition",
				new InventoryCategoryPanel(ammunitionTable, evt -> sellAmmunition(ammunitionTable)));

		JTable itemTable = new InventoryItemTable(player);
		tabbedPane.addTab("Items", new InventoryCategoryPanel(itemTable, evt -> sellItem(itemTable)));
	}

	private void sellWeapon(JTable table) {
		int selectedRowIndex = table.getSelectedRow();

		if (selectedRowIndex >= 0) {
			int actualSelectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

			CyberpunkWeapon weapon = (CyberpunkWeapon) table.getModel().getValueAt(actualSelectedRowIndex,
					InventoryWeaponTableModel.OBJECT_INDEX);

			player.sell(weapon, vendor.getBidPrice(weapon));
		}
	}

	private void sellArmor(JTable table) {
		int selectedRowIndex = table.getSelectedRow();

		if (selectedRowIndex >= 0) {
			int actualSelectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

			CyberpunkArmor armor = (CyberpunkArmor) table.getModel().getValueAt(actualSelectedRowIndex,
					InventoryArmorTableModel.OBJECT_INDEX);

			player.sell(armor, vendor.getBidPrice(armor));
		}
	}

	private void sellAmmunition(JTable table) {
		int selectedRowIndex = table.getSelectedRow();

		if (selectedRowIndex >= 0) {
			int actualSelectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

			Ammunition ammunition = (Ammunition) table.getModel().getValueAt(actualSelectedRowIndex,
					InventoryAmmunitionTableModel.OBJECT_INDEX);

			player.sell(ammunition, vendor.getBidPrice(ammunition));
		}
	}

	private void sellItem(JTable table) {
		int selectedRowIndex = table.getSelectedRow();

		if (selectedRowIndex >= 0) {
			int actualSelectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

			Item item = (Item) table.getModel().getValueAt(actualSelectedRowIndex,
					InventoryItemTableModel.OBJECT_INDEX);

			player.sell(item, vendor.getBidPrice(item));
		}
	}

}
