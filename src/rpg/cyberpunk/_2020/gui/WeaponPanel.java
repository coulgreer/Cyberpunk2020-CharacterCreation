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
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.cyberpunk._2020.combat.HomogeneousMagazine;
import rpg.general.combat.Ammunition;
import rpg.general.combat.AmmunitionContainer;

/**
 * A panel that allows the manipulation of a <code>CyberpunkWeapon</code> as
 * well as the display of its information in regards to its name, weapon type,
 * ammunition count, reliability, etc.
 */
public class WeaponPanel extends JPanel implements PropertyChangeListener, Selectable {
	private static final int borderSize = 1;
	private static final int paddingSize = 3;
	private static final long serialVersionUID = 1L;

	private JPanel parentPanel;
	private Player player;
	private int slotIndex;
	private CyberpunkWeapon weapon;
	private JLabel weaponNameLabel;
	private JLabel weaponDetailLabel;
	private JButton fireButton;
	private JButton reloadButton;
	private SelectionMediator selectionMediator;

	/**
	 * Constructs a panel that displays the data of a <code>CyberpunkWeapon</code>
	 * provided by the passed Player and slot index. The derived information from
	 * the CyberpunkWeapon is shown as: a summary panel that displays the slot's
	 * name and the weapon's name, a detail panel that displays all other
	 * information not provided by the summary, and a panel that allows the user to
	 * manipulate the weapon's state by firing and reloading the weapon if
	 * permitted. Also, adds this panel to a selection group that allows for only
	 * one of the selected items to be active at a time.
	 * 
	 * @param parentPanel       the panel used to display the error messages on
	 * @param player            the provider of the CyberpunkWeapon given the slot
	 *                          index
	 * @param slotIndex         the numerical value used to access the
	 *                          CyberpunkWeapon from player
	 * @param selectionMediator the manager that tells this panel if it is selected
	 *                          or not
	 */
	public WeaponPanel(JPanel parentPanel, Player player, int slotIndex, SelectionMediator selectionMediator) {
		super(new GridLayout(3, 1));

		this.parentPanel = parentPanel;
		this.player = player;
		this.slotIndex = slotIndex;
		this.weapon = player.getWeapon(slotIndex);

		player.addPropertyChangeListener(Player.PROPERTY_NAME_EQUIPMENT_WEAPON, this);

		createStartingBorder(slotIndex);
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

	private void createStartingBorder(int slot) {
		if (slot == Player.PRIMARY_SLOT) {
			setBorder(BorderFactory.createMatteBorder(0, 0, 0, borderSize, Color.BLACK));
		}

		if (slot == Player.SECONDARY_SLOT) {
			setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, Color.BLACK));
		}
	}

	private Component createWeaponSummaryPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2));

		String weaponSlotTitle;
		switch (slotIndex) {
		case Player.PRIMARY_SLOT:
			weaponSlotTitle = "Primary";
			break;
		case Player.SECONDARY_SLOT:
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
		return weapon.getWeaponType() //
				+ " \u2022 " + player.getTotalAttackChance(slotIndex).getModifier() //
				+ " \u2022 " + weapon.getConcealability() //
				+ " \u2022 " + weapon.getAvailability() //
				+ " \u2022 " + player.getTotalDamageChance(slotIndex) //
				+ " \u2022 " + weapon.getAmmunitionType() //
				+ " \u2022 " + weapon.getAmmunitionCount() + "/" + weapon.getAmmunitionCapacity() //
				+ " \u2022 " + weapon.getRateOfFire() //
				+ " \u2022 " + weapon.getReliability();
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
		player.attack(slotIndex, 1);
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

			player.reload(slotIndex, ammunitionStorage);

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
			weapon = player.getWeapon(slotIndex);

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
			createStartingBorder(slotIndex);
		}
	}

}
