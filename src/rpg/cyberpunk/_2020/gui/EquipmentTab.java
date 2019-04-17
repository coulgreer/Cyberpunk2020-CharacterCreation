package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JPanel;

import rpg.Player;
import rpg.cyberpunk._2020.combat.CyberpunkCombatant;

// TODO Make the colors into the corresponding panels. 
// Pink = unequip button panel
// Red = display character and give information based on body location
// Blue = list of equipped armor
public class EquipmentTab extends JPanel {
	private Player player;

	public EquipmentTab(Player player) {
		super(new BorderLayout());

		this.player = player;

		add(createMainPanel(), BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.PINK);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	private Component createMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());

		JPanel armorPanel = new JPanel(new GridLayout(1, 2));
		mainPanel.add(armorPanel, BorderLayout.CENTER);

		JPanel armorDisplayPanel = new JPanel();
		armorDisplayPanel.setBackground(Color.RED);
		armorPanel.add(armorDisplayPanel);

		JPanel armorListPanel = new JPanel();
		armorListPanel.setBackground(Color.BLUE);
		armorPanel.add(armorListPanel);

		mainPanel.add(createWeaponPanels(), BorderLayout.SOUTH);

		return mainPanel;
	}

	private Component createWeaponPanels() {
		JPanel weaponPanel = new JPanel(new GridLayout(1, 2));

		weaponPanel.add(new WeaponPanel(this, player, CyberpunkCombatant.PRIMARY_SLOT));
		weaponPanel.add(new WeaponPanel(this, player, CyberpunkCombatant.SECONDARY_SLOT));

		return weaponPanel;
	}

}
