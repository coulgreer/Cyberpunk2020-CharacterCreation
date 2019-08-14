package rpg.cyberpunk._2020.gui;

import javax.swing.JTabbedPane;
import rpg.cyberpunk._2020.Player;
import rpg.cyberpunk._2020.commerce.CyberpunkVendor;
import rpg.cyberpunk._2020.commerce.VendorTrader;
import rpg.cyberpunk._2020.gui.items.EquipmentTab;
import rpg.cyberpunk._2020.gui.items.InventoryTab;
import rpg.cyberpunk._2020.gui.items.ShopTab;
import rpg.cyberpunk._2020.gui.stats.SkillTab;

/**
 * A tabbed panel used to hold all other panels that a user can navigate to. Used to keep other
 * classes clean that use this object.
 */
public class CharacterDisplay extends JTabbedPane {
  private static final long serialVersionUID = 1L;

  /**
   * Constructs a JTabbedPane with a default set of tabs. It has tabs for Skills, Equipment,
   * Inventory, and Shop.
   * 
   * @param player the object used to derive information to display on the GUI
   */
  public CharacterDisplay(Player player) {
    CyberpunkVendor vendor = new CyberpunkVendor(new VendorTrader());

    addTab("Bio", new BiographyTab(player));
    addTab("Skill", new SkillTab(player));
    addTab("Equipment", new EquipmentTab(player));
    addTab("Inventory", new InventoryTab(player, vendor));
    addTab("Shop", new ShopTab(player, vendor));
  }

}
