package rpg.cyberpunk._2020.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rpg.Player;
import rpg.cyberpunk._2020.combat.CyberpunkCombatant;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.cyberpunk._2020.combat.HomogeneousMagazine;
import rpg.general.combat.Ammunition;
import rpg.general.combat.AmmunitionContainer;

public class WeaponPanel extends JPanel implements PropertyChangeListener {
	private static final int BORDER_WIDTH = 1;
	private static final int PADDING_WIDTH = 3;

	private JPanel panel;
	private Player player;
	private int slot;
	private CyberpunkWeapon weapon;
	private JLabel weaponNameLabel;
	private JLabel weaponDetailLabel;
	private JButton fireButton;
	private JButton reloadButton;

	public WeaponPanel(JPanel panel, Player player, int slot) {
		super(new GridLayout(3, 1));

		this.panel = panel;
		this.player = player;
		this.slot = slot;
		this.weapon = player.getWeapon(slot);

		player.addPropertyChangeListener(Player.PROPERTY_NAME_WEAPON_EQUIPPED, this);

		if (slot == CyberpunkCombatant.PRIMARY_SLOT) {
			setBorder(BorderFactory.createMatteBorder(0, 0, 0, BORDER_WIDTH, Color.BLACK));
		}

		if (slot == CyberpunkCombatant.SECONDARY_SLOT) {
			setBorder(BorderFactory.createMatteBorder(0, BORDER_WIDTH, 0, 0, Color.BLACK));
		}

		add(createWeaponSummaryPanel());
		add(createWeaponDetailPanel());
		add(createButtonPanel());
	}

	private Component createWeaponSummaryPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2));

		String weaponSlotTitle;
		switch (slot) {
		case CyberpunkCombatant.PRIMARY_SLOT:
			weaponSlotTitle = "Primary";
			break;
		case CyberpunkCombatant.SECONDARY_SLOT:
			weaponSlotTitle = "Secondary";
			break;
		default:
			weaponSlotTitle = "UNKNOWN";
			break;
		}

		JLabel titleLabel = new JLabel(weaponSlotTitle);
		titleLabel.setHorizontalAlignment(JLabel.LEFT);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(0, PADDING_WIDTH, 0, 0));
		panel.add(titleLabel);

		weaponNameLabel = new JLabel(weapon.getName());
		weaponNameLabel.setHorizontalAlignment(JLabel.RIGHT);
		weaponNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, PADDING_WIDTH));
		panel.add(weaponNameLabel);

		return panel;
	}

	private Component createWeaponDetailPanel() {
		JPanel panel = new JPanel();

		weaponDetailLabel = new JLabel(generateWeaponDetails());
		panel.add(weaponDetailLabel);

		return panel;
	}

	private String generateWeaponDetails() {
		return weapon.getWeaponType() + " \u2022 " + weapon.getHitScore() + " \u2022 " + weapon.getConcealability()
				+ " \u2022 " + weapon.getAvailability() + " \u2022 " + weapon.getDamage() + " \u2022 "
				+ weapon.getAmmunitionType() + " \u2022 " + weapon.getAmmunitionCount() + "/"
				+ weapon.getAmmunitionCapacity() + " \u2022 " + weapon.getRateOfFire() + " \u2022 "
				+ weapon.getReliability();
	}

	private Component createButtonPanel() {
		JPanel panel = new JPanel();

		fireButton = new JButton("Fire");
		fireButton.setEnabled(canFire());
		fireButton.addActionListener(evt -> fireWeapon());
		panel.add(fireButton);
		reloadButton = new JButton("Reload");
		reloadButton.setEnabled(canReload());
		reloadButton.addActionListener(evt -> reloadWeapon());
		panel.add(reloadButton);

		return panel;
	}

	private void fireWeapon() {
		player.attack(slot, 1);
		weaponDetailLabel.setText(generateWeaponDetails());
		fireButton.setEnabled(canFire());
	}

	private boolean canFire() {
		return weapon.getAmmunitionCount() > AmmunitionContainer.EMPTY
				|| weapon.getAmmunitionCapacity() == AmmunitionContainer.EMPTY;
	}

	private void reloadWeapon() {
		Set<Ammunition> ammunitionSet = player.createCarriedAmmunitionSet();
		Map<String, Ammunition> filteredAmmunitionMap = ammunitionSet.stream()
				.filter(ammunition -> weapon.getAmmunitionType().equals(ammunition.getAmmunitionType()))
				.collect(Collectors.toMap(Ammunition::getName, Function.identity()));
		Object[] options = filteredAmmunitionMap.keySet().toArray();

		String result = (String) JOptionPane.showInputDialog(panel, "Select ammunition:", "Ammo Selection",
				JOptionPane.PLAIN_MESSAGE, null, options, null);

		Ammunition targetAmmunition = filteredAmmunitionMap.get(result);
		AmmunitionContainer ammunitionStorage = new HomogeneousMagazine(weapon.getAmmunitionType(),
				weapon.getAmmunitionCapacity());
		while (!ammunitionStorage.isFull() && player.getQuantity(targetAmmunition) > 0) {
			ammunitionStorage.depositAmmunition(targetAmmunition);
			player.removeFromInventory(targetAmmunition, 1);
		}

		player.reload(slot, ammunitionStorage);
		weaponDetailLabel.setText(generateWeaponDetails());
		fireButton.setEnabled(canFire());
	}

	private boolean canReload() {
		return weapon.getAmmunitionCapacity() > AmmunitionContainer.EMPTY;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();

		if (source == player) {
			weapon = player.getWeapon(slot);

			weaponNameLabel.setText(weapon.getName());
			weaponDetailLabel.setText(generateWeaponDetails());
			reloadButton.setEnabled(canReload());
			fireButton.setEnabled(canFire());
		}
	}

}
