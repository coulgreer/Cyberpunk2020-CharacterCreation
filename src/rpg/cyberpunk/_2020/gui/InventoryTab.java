package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.NoSuchElementException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import rpg.Player;
import rpg.cyberpunk._2020.combat.CyberpunkArmor;
import rpg.cyberpunk._2020.combat.CyberpunkCombatant;
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
	private static final long serialVersionUID = 1L;

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
		tabbedPane.addTab("Weapons", new InventoryCategoryPanel(weaponTable, createWeaponMenuComponent(weaponTable)));

		JTable armorTable = new InventoryArmorTable(player);
		tabbedPane.addTab("Armor", new InventoryCategoryPanel(armorTable, createArmorMenuComponent(armorTable)));

		JTable ammunitionTable = new InventoryAmmunitionTable(player);
		tabbedPane.addTab("Ammunition",
				new InventoryCategoryPanel(ammunitionTable, createAmmunitionMenuComponent(ammunitionTable)));

		JTable itemTable = new InventoryItemTable(player);
		tabbedPane.addTab("Items", new InventoryCategoryPanel(itemTable, createItemMenuComponent(itemTable)));
	}

	private Component createWeaponMenuComponent(JTable table) {
		JPanel panel = new JPanel();

		JButton sellButton = new JButton("Sell");
		sellButton.addActionListener(evt -> sellWeapon(table));
		panel.add(sellButton);

		JButton equipButton = new JButton("Equip");
		equipButton.addActionListener(evt -> equipWeapon(table));
		panel.add(equipButton);

		return panel;
	}

	private void sellWeapon(JTable table) {
		int selectedRowIndex = table.getSelectedRow();

		if (selectedRowIndex >= 0) {
			int actualSelectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

			CyberpunkWeapon weapon = (CyberpunkWeapon) table.getModel().getValueAt(actualSelectedRowIndex,
					InventoryWeaponTableModel.OBJECT_INDEX);

			try {
				player.sell(weapon, vendor.getBidPrice(weapon));
			} catch (NoSuchElementException ex) {
				JOptionPane.showMessageDialog(this, "The weapon, " + weapon + ", is not in your carried inventory.");
			} catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(this, "The weapon being sold is of null value.");
			}
		}
	}

	private void equipWeapon(JTable table) {
		int selectedRowIndex = table.getSelectedRow();

		if (selectedRowIndex >= 0) {
			int actualSelectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

			CyberpunkWeapon weapon = (CyberpunkWeapon) table.getModel().getValueAt(actualSelectedRowIndex,
					InventoryWeaponTableModel.OBJECT_INDEX);

			Object[] options = { "Primary Weapon", "Secondary Weapon", "Cancel" };
			int result = JOptionPane.showOptionDialog(this, "Choose a slot to equip the weapon to.", "Equip",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

			switch (result) {
			case JOptionPane.YES_OPTION:
				equipWeapon(CyberpunkCombatant.PRIMARY_SLOT, weapon);
				break;
			case JOptionPane.NO_OPTION:
				equipWeapon(CyberpunkCombatant.SECONDARY_SLOT, weapon);
				break;
			default:
				break;
			}

		}
	}

	private void equipWeapon(int slot, CyberpunkWeapon weapon) {
		try {
			player.equip(slot, weapon);
		} catch (NoSuchElementException ex) {
			JOptionPane.showMessageDialog(this, "The weapon, " + weapon + ", is not in your carried inventory.");
		} catch (NullPointerException ex) {
			JOptionPane.showMessageDialog(this, "The weapon being equipped is of null value.");
		}
	}

	private Component createArmorMenuComponent(JTable table) {
		JPanel panel = new JPanel();

		JButton sellButton = new JButton("Sell");
		sellButton.addActionListener(evt -> sellArmor(table));
		panel.add(sellButton);

		JButton equipButton = new JButton("Equip");
		equipButton.addActionListener(evt -> equipArmor(table));
		panel.add(equipButton);

		return panel;
	}

	private void sellArmor(JTable table) {
		int selectedRowIndex = table.getSelectedRow();

		if (selectedRowIndex >= 0) {
			int actualSelectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

			CyberpunkArmor armor = (CyberpunkArmor) table.getModel().getValueAt(actualSelectedRowIndex,
					InventoryArmorTableModel.OBJECT_INDEX);

			try {
				player.sell(armor, vendor.getBidPrice(armor));
			} catch (NoSuchElementException ex) {
				JOptionPane.showMessageDialog(this, "The armor, " + armor + ", is not in your carried inventory.");
			} catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(this, "The armor attempting to be sold is of null value.");
			}
		}
	}

	private void equipArmor(JTable table) {
		int selectedRowIndex = table.getSelectedRow();

		if (selectedRowIndex >= 0) {
			int actualSelectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

			CyberpunkArmor armor = (CyberpunkArmor) table.getModel().getValueAt(actualSelectedRowIndex,
					InventoryArmorTableModel.OBJECT_INDEX);
			try {
				player.equip(armor);
			} catch (NoSuchElementException ex) {
				JOptionPane.showMessageDialog(this, "The armor, " + armor + ", is not in your carried inventory.");
			} catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(this, "The armor attempting to be equipped is of null value.");
			}
		}
	}

	private Component createAmmunitionMenuComponent(JTable table) {
		JPanel panel = new JPanel();

		JButton sellButton = new JButton("Sell");
		sellButton.addActionListener(evt -> sellAmmunition(table));
		panel.add(sellButton);

		return panel;
	}

	private void sellAmmunition(JTable table) {
		int selectedRowIndex = table.getSelectedRow();

		if (selectedRowIndex >= 0) {
			int actualSelectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

			Ammunition ammunition = (Ammunition) table.getModel().getValueAt(actualSelectedRowIndex,
					InventoryAmmunitionTableModel.OBJECT_INDEX);
			try {
				player.sell(ammunition, vendor.getBidPrice(ammunition));
			} catch (NoSuchElementException ex) {
				JOptionPane.showMessageDialog(this,
						"The ammunition, " + ammunition + ", is not in your carried inventory.");
			} catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(this, "The ammunition attempting to be sold is of null value.");
			}
		}
	}

	private Component createItemMenuComponent(JTable table) {
		JPanel panel = new JPanel();

		JButton sellButton = new JButton("Sell");
		sellButton.addActionListener(evt -> sellItem(table));
		panel.add(sellButton);

		return panel;
	}

	private void sellItem(JTable table) {
		int selectedRowIndex = table.getSelectedRow();

		if (selectedRowIndex >= 0) {
			int actualSelectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

			Item item = (Item) table.getModel().getValueAt(actualSelectedRowIndex,
					InventoryItemTableModel.OBJECT_INDEX);

			try {
				player.sell(item, vendor.getBidPrice(item));
			} catch (NoSuchElementException ex) {
				JOptionPane.showMessageDialog(this, "The item, " + item + ", is not in your carried inventory.");
			} catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(this, "The item attempting to be sold is of null value.");
			}
		}
	}

}
