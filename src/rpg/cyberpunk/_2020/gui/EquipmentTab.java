package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import rpg.Player;

/**
 * An instance of JPanel that sets up a panel for armor, primary weapon, and
 * secondary weapon from a Player to be displayed.
 */
public class EquipmentTab extends JPanel {
	private static final long serialVersionUID = 1L;

	private Player player;
	private SelectionMediator selectionMediator;
	private ArmorPanel armorPanel;
	private WeaponPanel primaryWeaponPanel;
	private WeaponPanel secondaryWeaponPanel;

	/**
	 * Constructs a EquipmentTab that uses a Player to make a main display.
	 * 
	 * @param player the source of the displayed information such as the armor and
	 *               weapons equipped
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

		armorPanel = new ArmorPanel(player, selectionMediator);
		mainPanel.add(armorPanel, BorderLayout.CENTER);
		mainPanel.add(createWeaponPanels(), BorderLayout.SOUTH);

		return mainPanel;
	}

	private Component createWeaponPanels() {
		JPanel weaponPanel = new JPanel(new GridLayout(1, 2));

		primaryWeaponPanel = new WeaponPanel(this, player, Player.PRIMARY_SLOT, selectionMediator);
		secondaryWeaponPanel = new WeaponPanel(this, player, Player.SECONDARY_SLOT, selectionMediator);
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
