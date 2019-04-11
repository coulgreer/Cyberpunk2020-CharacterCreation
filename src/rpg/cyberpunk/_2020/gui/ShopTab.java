package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import rpg.Player;
import rpg.cyberpunk._2020.combat.CyberpunkArmor;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.cyberpunk._2020.commerce.Box;
import rpg.cyberpunk._2020.commerce.CyberpunkVendor;
import rpg.cyberpunk._2020.gui.ShopAmmunitionCategoryTable.ShopAmmunitionTableModel;
import rpg.cyberpunk._2020.gui.ShopArmorCategoryTable.ShopArmorTableModel;
import rpg.cyberpunk._2020.gui.ShopWeaponCategoryTable.ShopWeaponTableModel;
import rpg.general.combat.Ammunition;

/**
 * A panel used to hold all elements used to display all items that a vendor can
 * sell.
 * 
 * @author Coul Greer
 */
public class ShopTab extends JPanel {
	private Player player;
	private CyberpunkVendor vendor;

	/**
	 * Constructs a panel that helps organize elements used for the shopping GUI
	 * experience.
	 * 
	 * @param player the object that purchases items from the vendor
	 */
	public ShopTab(Player player, CyberpunkVendor vendor) {
		super(new BorderLayout());
		this.player = player;
		this.vendor = vendor;

		JPanel infoPanel = new InventoryInfoPanel(player);
		add(infoPanel, BorderLayout.NORTH);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		add(tabbedPane, BorderLayout.CENTER);

		JTable weaponTable = new ShopWeaponCategoryTable(vendor);
		tabbedPane.addTab("Weapons", new ShopCategoryPanel(weaponTable, evt -> buyWeapon(weaponTable)));

		JTable armorTable = new ShopArmorCategoryTable(vendor);
		tabbedPane.addTab("Armors", new ShopCategoryPanel(armorTable, evt -> buyArmor(armorTable)));

		JTable ammunitionTable = new ShopAmmunitionCategoryTable(vendor);
		tabbedPane.addTab("Ammunition", new ShopCategoryPanel(ammunitionTable, evt -> buyAmmunition(ammunitionTable)));

	}

	private void buyWeapon(JTable table) {
		int selectedRowIndex = table.getSelectedRow();
		if (selectedRowIndex != -1) {
			int actualSelectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

			if (actualSelectedRowIndex >= 0) {
				CyberpunkWeapon weapon = (CyberpunkWeapon) table.getModel().getValueAt(actualSelectedRowIndex,
						ShopWeaponTableModel.OBJECT_INDEX);

				player.buy(vendor.sellWeapon(weapon), vendor.getAskPrice(weapon));
			}
		}
	}

	private void buyArmor(JTable table) {
		int selectedRowIndex = table.getSelectedRow();
		if (selectedRowIndex != -1) {
			int actualSelectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

			if (actualSelectedRowIndex >= 0) {
				CyberpunkArmor armor = (CyberpunkArmor) table.getModel().getValueAt(actualSelectedRowIndex,
						ShopArmorTableModel.OBJECT_INDEX);

				player.buy(vendor.sellArmor(armor), vendor.getAskPrice(armor));
			}
		}
	}

	private void buyAmmunition(JTable table) {
		int selectedRowIndex = table.getSelectedRow();
		if (selectedRowIndex != -1) {
			int actualSelectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

			if (actualSelectedRowIndex >= 0) {
				@SuppressWarnings("unchecked")
				Box<Ammunition> ammunition = (Box<Ammunition>) table.getModel().getValueAt(actualSelectedRowIndex,
						ShopAmmunitionTableModel.OBJECT_INDEX);

				player.buy(vendor.sellBoxOfAmmunition(ammunition), vendor.getAskPrice(ammunition));
			}
		}
	}

}
