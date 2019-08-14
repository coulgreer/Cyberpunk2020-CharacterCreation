package rpg.cyberpunk._2020.gui.items;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import rpg.cyberpunk._2020.Player;
import rpg.cyberpunk._2020.gui.SelectionMediator;

/**
 * An instance of JPanel that sets up a panel for armor, primary weapon, and secondary weapon from a
 * Player to be displayed.
 */
public class EquipmentTab extends JPanel {
  private static final long serialVersionUID = 1L;

  private Player player;
  private SelectionMediator selectionMediator;
  private ArmorPane armorPanel;
  private WeaponPane primaryWeaponPanel;
  private WeaponPane secondaryWeaponPanel;

  /**
   * Constructs a EquipmentTab that uses a Player to make a main display.
   * 
   * @param player the source of the displayed information such as the armor and weapons equipped
   */
  public EquipmentTab(Player player) {
    super(new BorderLayout());

    this.player = player;
    this.selectionMediator = new SelectionMediator();

    add(createMainPanel(), BorderLayout.CENTER);
    add(createUnequipButtonPanel(), BorderLayout.SOUTH);
  }

  private Component createMainPanel() {
    JPanel mainPanel = new JPanel(new BorderLayout());

    armorPanel = new ArmorPane(player, selectionMediator);
    mainPanel.add(armorPanel, BorderLayout.CENTER);
    mainPanel.add(createWeaponPanels(), BorderLayout.SOUTH);

    return mainPanel;
  }

  private Component createWeaponPanels() {
    JPanel weaponPanel = new JPanel(new GridLayout(1, 2));

    primaryWeaponPanel = new WeaponPane(this, player, Player.PRIMARY_SLOT, selectionMediator);
    secondaryWeaponPanel = new WeaponPane(this, player, Player.SECONDARY_SLOT, selectionMediator);
    weaponPanel.add(primaryWeaponPanel);
    weaponPanel.add(secondaryWeaponPanel);

    return weaponPanel;
  }

  private Component createUnequipButtonPanel() {
    JPanel unequipPanel = new JPanel();

    JButton unequipButton = new JButton("Unequip");
    unequipButton.addActionListener(e -> unequip());
    unequipPanel.add(unequipButton);

    return unequipPanel;
  }

  private void unequip() {
    Object selected = selectionMediator.getSelected();

    if (selected == armorPanel) {
      player.unequip(armorPanel.getSelectedArmor());
    } else if (selected == primaryWeaponPanel) {
      player.unequip(Player.PRIMARY_SLOT);
    } else if (selected == secondaryWeaponPanel) {
      player.unequip(Player.SECONDARY_SLOT);
    }
  }

}
