package rpg.cyberpunk._2020.gui.items;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import rpg.cyberpunk._2020.Player;
import rpg.cyberpunk._2020.combat.CyberpunkArmor;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.cyberpunk._2020.commerce.Box;
import rpg.cyberpunk._2020.commerce.CyberpunkVendor;
import rpg.cyberpunk._2020.gui.items.ShopAmmunitionCategoryTable.ShopAmmunitionTableModel;
import rpg.cyberpunk._2020.gui.items.ShopArmorCategoryTable.ShopArmorTableModel;
import rpg.cyberpunk._2020.gui.items.ShopWeaponCategoryTable.ShopWeaponTableModel;
import rpg.general.combat.Ammunition;

/**
 * The parent to the displays for all objects that a <code>Player</code> can buy from a
 * <code>Vendor</code>.
 */
public class ShopTab extends JPanel {
  private static final long serialVersionUID = 1L;

  private Player player;
  private CyberpunkVendor vendor;

  /**
   * Constructs a panel that holds a tabbed pane that holds a <code>ShopCategoryPanel</code> for
   * weapons, armor, and ammunition.
   * 
   * @param player the object that purchases items from the vendor
   */
  public ShopTab(Player player, CyberpunkVendor vendor) {
    super(new BorderLayout());
    this.player = player;
    this.vendor = vendor;

    JPanel infoPanel = new InventoryInfoPane(player);
    add(infoPanel, BorderLayout.NORTH);

    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.setTabPlacement(JTabbedPane.LEFT);
    add(tabbedPane, BorderLayout.CENTER);

    JTable weaponTable = new ShopWeaponCategoryTable(vendor);
    tabbedPane.addTab( //
        "Weapons", //
        createShopCategoryContainer(weaponTable, evt -> buyWeapon(weaponTable)));
    JTable armorTable = new ShopArmorCategoryTable(vendor);
    tabbedPane.addTab( //
        "Armors", //
        createShopCategoryContainer(armorTable, evt -> buyArmor(armorTable)));
    JTable ammunitionTable = new ShopAmmunitionCategoryTable(vendor);
    tabbedPane.addTab( //
        "Ammunition", //
        createShopCategoryContainer(ammunitionTable, evt -> buyAmmunition(ammunitionTable)));

  }

  private Component createShopCategoryContainer(JTable table, ActionListener listener) {
    JPanel panel = new JPanel(new BorderLayout());

    table.setFillsViewportHeight(true);
    table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    panel.add(new JScrollPane(table), BorderLayout.CENTER);
    panel.add(createButtonComponent(listener), BorderLayout.SOUTH);

    return panel;
  }

  private Component createButtonComponent(ActionListener listener) {
    JPanel panel = new JPanel();

    JButton button = new JButton("Buy");
    button.addActionListener(listener);
    panel.add(button);

    return panel;
  }

  private void buyWeapon(JTable table) {
    int selectedRowIndex = table.getSelectedRow();
    if (selectedRowIndex != -1) {
      int actualSelectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

      if (actualSelectedRowIndex >= 0) {
        CyberpunkWeapon weapon = (CyberpunkWeapon) table.getModel()
            .getValueAt(actualSelectedRowIndex, ShopWeaponTableModel.OBJECT_INDEX);

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
        Box<Ammunition> ammunition = (Box<Ammunition>) table.getModel()
            .getValueAt(actualSelectedRowIndex, ShopAmmunitionTableModel.OBJECT_INDEX);

        player.buy(vendor.sellBoxOfAmmunition(ammunition), vendor.getAskPrice(ammunition));
      }
    }
  }

}
