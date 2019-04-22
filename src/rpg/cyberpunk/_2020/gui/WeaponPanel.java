package rpg.cyberpunk._2020.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.Map;
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

public class WeaponPanel extends JPanel implements PropertyChangeListener, Selectable {
	private static final int borderSize = 1;
	private static final int paddingSize = 3;
	private static final long serialVersionUID = 1L;

	private JPanel parentPanel;
	private Player player;
	private int slot;
	private CyberpunkWeapon weapon;
	private JLabel weaponNameLabel;
	private JLabel weaponDetailLabel;
	private JButton fireButton;
	private JButton reloadButton;
	private SelectionMediator selectionMediator;

	public WeaponPanel(JPanel parentPanel, Player player, int slot, SelectionMediator selectionMediator) {
		super(new GridLayout(3, 1));

		this.parentPanel = parentPanel;
		this.player = player;
		this.slot = slot;
		this.weapon = player.getWeapon(slot);

		player.addPropertyChangeListener(Player.PROPERTY_NAME_WEAPON_EQUIPPED, this);

		if (slot == CyberpunkCombatant.PRIMARY_SLOT) {
			setBorder(BorderFactory.createMatteBorder(0, 0, 0, borderSize, Color.BLACK));
		}

		if (slot == CyberpunkCombatant.SECONDARY_SLOT) {
			setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, Color.BLACK));
		}

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectionMediator.setActive(WeaponPanel.this);
			}
		});

		this.selectionMediator = selectionMediator;
		selectionMediator.registerColleague(this);

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
		titleLabel.setBorder(BorderFactory.createEmptyBorder(0, paddingSize, 0, 0));
		panel.add(titleLabel);

		weaponNameLabel = new JLabel(weapon.getName());
		weaponNameLabel.setHorizontalAlignment(JLabel.RIGHT);
		weaponNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, paddingSize));
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

		for (MouseListener listener : getMouseListeners()) {
			fireButton.addMouseListener(listener);
			reloadButton.addMouseListener(listener);
		}

		return panel;
	}

	private void fireWeapon() {
		player.attack(slot, 1);
		weaponDetailLabel.setText(generateWeaponDetails());
		updateButtons();
	}

	private void updateButtons() {
		fireButton.setEnabled(canFire());
		reloadButton.setEnabled(canReload());
	}

	private boolean canFire() {
		return weapon.getAmmunitionCount() > AmmunitionContainer.EMPTY
				|| weapon.getAmmunitionCapacity() == AmmunitionContainer.EMPTY;
	}

	private boolean canReload() {
		return weapon.getAmmunitionCapacity() > AmmunitionContainer.EMPTY;
	}

	private void reloadWeapon() {
		Collection<Ammunition> ammunitionSet = player.createCarriedAmmunitionCollection();
		Map<String, Ammunition> filteredAmmunitionMap = ammunitionSet.stream()
				.filter(ammunition -> weapon.getAmmunitionType().equals(ammunition.getAmmunitionType()))
				.collect(Collectors.toMap(Ammunition::getName, Function.identity()));
		Object[] options = filteredAmmunitionMap.keySet().toArray();

		if (options.length > 0) {
			String result = (String) JOptionPane.showInputDialog(parentPanel, "Select ammunition:", "Ammo Selection",
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
			updateButtons();
		} else {
			JOptionPane.showMessageDialog(parentPanel, "There is no compatible ammunition in your inventory.");
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();

		if (source == player) {
			weapon = player.getWeapon(slot);

			weaponNameLabel.setText(weapon.getName());
			weaponDetailLabel.setText(generateWeaponDetails());
			updateButtons();
		}
	}

	@Override
	public boolean isSelected() {
		return this == selectionMediator.getSelected();
	}

	@Override
	public void setSelected(boolean isSelected) {
		if (isSelected) {
			setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.CYAN));
		} else {
			if (slot == CyberpunkCombatant.PRIMARY_SLOT) {
				setBorder(BorderFactory.createMatteBorder(0, 0, 0, borderSize, Color.BLACK));
			}

			if (slot == CyberpunkCombatant.SECONDARY_SLOT) {
				setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, Color.BLACK));
			}
		}
	}

}
