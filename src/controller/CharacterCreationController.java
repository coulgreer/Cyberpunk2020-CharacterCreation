package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.codec.binary.Base64;

import components.ImagePreview;
import model.CharacterCreationModel;
import view.CharacterCreationView;
import view.CharacterCreationView.InventoryAmmoTableModel;
import view.CharacterCreationView.InventoryArmorTableModel;
import view.CharacterCreationView.InventoryGearTableModel;
import view.CharacterCreationView.InventoryWeaponTableModel;
import view.CharacterCreationView.SkillTableModel;

public class CharacterCreationController {
	private CharacterCreationModel characterModel;
	private CharacterCreationView characterView;

	public CharacterCreationController(CharacterCreationModel characterModel, CharacterCreationView characterView) {
		this.characterModel = characterModel;
		this.characterView = characterView;

		this.characterView.addHandleDocumentListener(new HandleDocumentListener());
		this.characterView.addRoleChangeListener(new RoleChangeListener());
		this.characterView.addLoadCharacterButtonActionListener(new LoadCharacterActionListener());
		this.characterView.addSaveCharacterButtonActionListener(new SaveCharacterActionListener());
		this.characterView.addIntelligenceStatChangeListener(new IntelligenceChangeListener());
		this.characterView.addUnmodifiedReflexesStatChangeListener(new UnmodifiedReflexesChangeListener());
		this.characterView.addTechnicalAbilityStatChangeListener(new TechnicalAbilityChangeListener());
		this.characterView.addCoolStatChangeListener(new CoolChangeListener());
		this.characterView.addAttractivenessStatChangeListener(new AttractivenessChangeListener());
		this.characterView.addLuckStatChangeListener(new LuckChangeListener());
		this.characterView.addMovementAllowanceStatChangeListener(new MovementAllowanceChangeListener());
		this.characterView.addBodyStatChangeListener(new BodyChangeListener());
		this.characterView.addTotalEmpathyStatChangeListener(new TotalEmpathyChangeListener());
		this.characterView.addIncreaseInjuryActionListener(new IncreaseInjuryActionListener());
		this.characterView.addMinorlyIncreaseInjuryActionListener(new MinorlyIncreaseInjuryActionListener());
		this.characterView.addMinorlyDecreaseInjuryActionListener(new MinorlyDecreaseInjuryActionListener());
		this.characterView.addDecreaseInjuryActionListener(new DecreaseInjuryActionListener());
		this.characterView.addSelectAllTextFocusListeners(new SelectAllTextFocusListener());
		this.characterView.addListSelectionListeners(new TableListSelectionListener());
		this.characterView.addRandomCharacterPointButtonActionListener(new RandomCharacterPointActionListener());
		this.characterView.addFastCharacterPointButtonActionListener(new FastCharacterPointActionListener());
		this.characterView.addManualCharacterPointButtonActionListener(new ManualCharacterPointActionListener());
		this.characterView.addChangePortraitButtonActionListener(new ChangeCharacterPortrait());
		this.characterView.addInventoryChangeItemListener(new InventoryChangeListener());
		this.characterView.addStoreChangeItemListener(new StoreChangeListener());
		this.characterView.addAddToInventoryActionListener(new AddToInventoryActionListener());
		this.characterView.addCalculateActionListener(new AddMoneyActionListener());
		this.characterView.addEquipButtonActionListener(new EquipActionListener());
		this.characterView.addEquippedTableModelListener(new EquippedTableModelListener());

		this.characterView.drawLoadedInjuryPoints(this.characterModel.getInjuryPoints());
		this.characterView.setModifiedReflexesLevel(Integer.toString(this.characterModel.getModifiedReflexesLevel()));
		this.characterView.setCurrentEmpathyLevel(Integer.toString(this.characterModel.getCurrentEmpathyLevel()));
		this.characterView.setCharacterPoints(Integer.toString(this.characterModel.getCharacterPoints()
				- this.characterModel.getIntelligenceLevel() - this.characterModel.getUnmodifiedReflexesLevel()
				- this.characterModel.getTechnicalAbilityLevel() - this.characterModel.getCoolLevel()
				- this.characterModel.getAttractivenessLevel() - this.characterModel.getLuckLevel()
				- this.characterModel.getMovementAllowanceLevel() - this.characterModel.getBodyLevel()
				- this.characterModel.getTotalEmpathyLevel()));
		this.characterView.setLeapLevel(Double.toString(this.characterModel.getLeapLevel()));
		this.characterView.setLiftCapacity(Double.toString(this.characterModel.getLiftCapacity()));
		this.characterView.setCarryCapacity(Double.toString(this.characterModel.getCarryCapacity()));
		this.characterView.setRunLevel(Double.toString(this.characterModel.getRunLevel()));
		this.characterView.setSaveModifier(Integer.toString(this.characterModel.getSaveModifier()));
		this.characterView.setBodyTypeModifier(Integer.toString(this.characterModel.getBodyTypeModifier()));
		this.characterView.setHeadArmorStoppingPower(Integer.toString(characterModel.getHeadArmorStoppingPower()));
		this.characterView
				.setTorsoArmorStoppingPower(Integer.toString(this.characterModel.getTorsoArmorStoppingPower()));
		this.characterView
				.setRightArmArmorStoppingPower(Integer.toString(this.characterModel.getRightArmArmorStoppingPower()));
		this.characterView
				.setLeftArmArmorStoppingPower(Integer.toString(this.characterModel.getLeftArmArmorStoppingPower()));
		this.characterView
				.setRightLegArmorStoppingPower(Integer.toString(this.characterModel.getRightLegArmorStoppingPower()));
		this.characterView
				.setLeftLegArmorStoppingPower(Integer.toString(this.characterModel.getLeftLegArmorStoppingPower()));

		populateSkillPanels();
		populateStoreWeaponPanels();
		populateStoreGearPanel();
		populateStoreArmorTable();
		populateAmmoTable();
	}

	private void populateSkillPanels() {
		for (Map<String, CharacterCreationModel.Skill> skillCategory : characterModel.getSkillCatelog().values()) {
			for (CharacterCreationModel.Skill skill : skillCategory.values()) {
				characterView.addBasicSkillToPanel(skill.getType(), skill.getSkillName(), skill.getRank(),
						skill.getSpecifiedSkill(), new SkillTableModelListener());
			}
		}
		for (JTable table : characterView.getSkillTables().values()) {
			characterView.resizeColumnWidth(table);
		}
	}

	private void populateStoreWeaponPanels() {
		for (CharacterCreationModel.Weapon weapon : characterModel.getWeapons().values()) {
			characterView.addStoreWeaponToTable(weapon.getName(), weapon.getCategory(), weapon.getAccuracy(),
					weapon.getConcealability(), weapon.getAvailability(), weapon.getDamageAndAmmo(),
					weapon.getNumberOfShots(), weapon.getRateOfFire(), weapon.getReliability(), weapon.getRange(),
					weapon.getCost(), new StoreWeaponListSelectionListener());
		}
		characterView.resizeColumnWidth(characterView.getStoreWeaponTable());
	}

	private void populateStoreGearPanel() {
		for (CharacterCreationModel.Gear gear : characterModel.getGear().values()) {
			characterView.addStoreGearToTable(gear.getType(), gear.getCost(), gear.getWeight(),
					new StoreGearListSelectionListener());
		}
		characterView.resizeColumnWidth(characterView.getStoreGearTable());
	}

	private void populateStoreArmorTable() {
		for (CharacterCreationModel.Armor armor : characterModel.getArmors().values()) {
			characterView.addStoreArmorToTable(armor.getType(), armor.getArmorClass(), armor.getCovers(),
					armor.getStoppingPower(), armor.getEncumbranceValue(), armor.getWeight(), armor.getCost(),
					new StoreArmorListSelectionListener());
		}
		characterView.resizeColumnWidth(characterView.getStoreArmorTable());
	}

	private void populateAmmoTable() {
		for (CharacterCreationModel.Ammo ammo : characterModel.getAmmos().values()) {
			characterView.addAmmoToTable(ammo.getType(), ammo.getQuantityPerBox(), ammo.isCaseless(), ammo.getCost(),
					ammo.getWeight(), new StoreAmmoListSelectionListener());
		}
		characterView.resizeColumnWidth(characterView.getStoreAmmoTable());
	}

	private void resetStats() {
		characterModel.setIntelligenceLevel(CharacterCreationModel.DEFAULT_STAT_LEVEL);
		characterModel.setUnmodifiedReflexesLevel(CharacterCreationModel.DEFAULT_STAT_LEVEL);
		characterModel.setTechnicalAbilityLevel(CharacterCreationModel.DEFAULT_STAT_LEVEL);
		characterModel.setCoolLevel(CharacterCreationModel.DEFAULT_STAT_LEVEL);
		characterModel.setAttractivenessLevel(CharacterCreationModel.DEFAULT_STAT_LEVEL);
		characterModel.setLuckLevel(CharacterCreationModel.DEFAULT_STAT_LEVEL);
		characterModel.setMovementAllowanceLevel(CharacterCreationModel.DEFAULT_STAT_LEVEL);
		characterModel.setBodyLevel(CharacterCreationModel.DEFAULT_STAT_LEVEL);
		characterModel.setTotalEmpathyLevel(CharacterCreationModel.DEFAULT_STAT_LEVEL);

		characterView.setIntelligenceLevel(Integer.toString(characterModel.getIntelligenceLevel()));
		characterView.setUnmodifiedReflexesLevel(Integer.toString(characterModel.getUnmodifiedReflexesLevel()));
		characterView.setTechnicalAbilityLevel(Integer.toString(characterModel.getTechnicalAbilityLevel()));
		characterView.setCoolLevel(Integer.toString(characterModel.getCoolLevel()));
		characterView.setAttractivenessLevel(Integer.toString(characterModel.getAttractivenessLevel()));
		characterView.setLuckLevel(Integer.toString(characterModel.getLuckLevel()));
		characterView.setMovementAllowanceLevel(Integer.toString(characterModel.getMovementAllowanceLevel()));
		characterView.setBodyLevel(Integer.toString(characterModel.getBodyLevel()));
		characterView.setTotalEmpathyLevel(Integer.toString(characterModel.getTotalEmpathyLevel()));
	}

	class AddMoneyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			int currentMoney = characterView.getMoney();
			int moneyModifier = characterView.getMoneyModifier();

			characterView.setMoneyModifier("0");
			characterView.setMoney(String.valueOf(currentMoney + moneyModifier));

		}

	}

	class AddToInventoryActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JTable storeWeaponTable = characterView.getStoreWeaponTable();
			JTable storeGearTable = characterView.getStoreGearTable();
			JTable storeArmorTable = characterView.getStoreArmorTable();
			JTable storeAmmoTable = characterView.getStoreAmmoTable();
			JTable inventoryWeaponTable = characterView.getInventoryWeaponTable();
			JTable inventoryGearTable = characterView.getInventoryGearTable();
			JTable inventoryArmorTable = characterView.getInventoryArmorTable();
			JTable inventoryAmmoTable = characterView.getInventoryAmmoTable();

			for (int rowIndex : storeWeaponTable.getSelectedRows()) {
				Object[] row;
				String type = (String) storeWeaponTable.getValueAt(rowIndex, 0);
				CharacterCreationModel.Weapon weapon = characterModel.getWeapons().get(type);

				row = new Object[] { weapon.getName(), weapon.getCategory(), weapon.getAccuracy(),
						weapon.getConcealability(), weapon.getDamageAndAmmo(), weapon.getNumberOfShots(),
						weapon.getRateOfFire(), weapon.getReliability(), weapon.getRange() };

				((InventoryWeaponTableModel) inventoryWeaponTable.getModel()).addRow(row);
			}
			characterView.resizeColumnWidth(inventoryWeaponTable);

			for (int rowIndex : storeGearTable.getSelectedRows()) {
				Object[] row;
				String type = (String) storeGearTable.getValueAt(rowIndex, 0);
				CharacterCreationModel.Gear gear = characterModel.getGear().get(type);
				gear.setQuantity(gear.getQuantity() + 1);
				characterModel.getGear().put(type, gear);

				row = new Object[] { gear.getType(), gear.getQuantity(), (gear.getWeight() * gear.getQuantity()) };

				boolean entryExists = false;
				int duplicateRowIndex = 0;
				for (int i = 0; i < inventoryGearTable.getRowCount(); i++) {
					String tempType = (String) inventoryGearTable.getValueAt(i, 0);
					if (tempType.equals(type)) {
						duplicateRowIndex = i;
						entryExists = true;
						break;
					}
				}
				if (entryExists) {
					((InventoryGearTableModel) inventoryGearTable.getModel()).removeRow(duplicateRowIndex);
				}
				((InventoryGearTableModel) inventoryGearTable.getModel()).addRow(row);
			}
			characterView.resizeColumnWidth(inventoryGearTable);

			for (int rowIndex : storeArmorTable.getSelectedRows()) {
				Object[] row;
				String type = (String) storeArmorTable.getValueAt(rowIndex, 0);
				CharacterCreationModel.Armor armor = characterModel.getArmors().get(type);

				row = new Object[] { armor.getType(), armor.getArmorClass(), armor.getCovers(),
						armor.getStoppingPower(), armor.getEncumbranceValue(), armor.getWeight() };

				((InventoryArmorTableModel) inventoryArmorTable.getModel()).addRow(row);
			}
			characterView.resizeColumnWidth(inventoryArmorTable);

			for (int rowIndex : storeAmmoTable.getSelectedRows()) {
				Object[] row;

				String type = (String) storeAmmoTable.getValueAt(rowIndex, 0);
				CharacterCreationModel.Ammo ammo = characterModel.getAmmos().get(type);
				ammo.setQuantity(ammo.getQuantity() + ammo.getQuantityPerBox());
				characterModel.getAmmos().put(type, ammo);

				row = new Object[] { ammo.getType(), ammo.getQuantity(), ammo.isCaseless(),
						((ammo.getQuantity() / ammo.getQuantityPerBox()) * ammo.getWeight()) };

				boolean entryExists = false;
				int duplicateRowIndex = 0;
				for (int i = 0; i < inventoryAmmoTable.getRowCount(); i++) {
					String tempType = (String) inventoryAmmoTable.getValueAt(i, 0);
					if (tempType.equals(type)) {
						duplicateRowIndex = i;
						entryExists = true;
						break;
					}
				}
				if (entryExists) {
					((InventoryAmmoTableModel) inventoryAmmoTable.getModel()).removeRow(duplicateRowIndex);
				}
				((InventoryAmmoTableModel) inventoryAmmoTable.getModel()).addRow(row);
			}
			characterView.resizeColumnWidth(inventoryAmmoTable);

			characterView.setMoney(String.valueOf(characterView.getMoney() + totalCost));

			storeWeaponTable.clearSelection();
			storeGearTable.clearSelection();
			storeArmorTable.clearSelection();
			storeAmmoTable.clearSelection();
		}
	}

	class EquipActionListener implements ActionListener {
		private static final int EQUIPPED_HEAD_INDEX = 0;
		private static final int EQUIPPED_TORSO_INDEX = 1;
		private static final int EQUIPPED_RIGHT_ARM_INDEX = 2;
		private static final int EQUIPPED_LEFT_ARM_INDEX = 3;
		private static final int EQUIPPED_RIGHT_LEG_INDEX = 4;
		private static final int EQUIPPED_LEFT_LEG_INDEX = 5;
		private static final int ARMOR_TYPE_INDEX = 0;
		private static final int MAX_ARMOR_LAYERS = 3;

		@Override
		public void actionPerformed(ActionEvent event) {
			JTable inventoryArmorTable = characterView.getInventoryArmorTable();
			JTable equippedTable = characterView.getEquippedTable();

			for (int rowIndex : inventoryArmorTable.getSelectedRows()) {
				String type = (String) inventoryArmorTable.getValueAt(rowIndex, ARMOR_TYPE_INDEX);
				CharacterCreationModel.Armor selectedArmor = characterModel.getArmors().get(type);
				String selectedArmorArmorClass = selectedArmor.getArmorClass();
				boolean isConflicted = false;

				for (int i = 0; i < equippedTable.getRowCount(); i++) {
					CharacterCreationModel.Armor tempArmor;
					String tempArmorArmorClass;

					if (selectedArmor.getCovers().contains("Head")) {
						if (!equippedTable.getValueAt(i, EQUIPPED_HEAD_INDEX).equals("")) {
							tempArmor = characterModel.getArmors()
									.get(equippedTable.getValueAt(i, EQUIPPED_HEAD_INDEX));
							tempArmorArmorClass = tempArmor.getArmorClass();
							if (selectedArmorArmorClass.equals("Hard Armor")
									&& tempArmorArmorClass.equals("Hard Armor")) {
								JOptionPane.showMessageDialog(characterView,
										"You cannot equip " + tempArmor.getType() + " and " + selectedArmor.getType()
												+ " because they are both Hard Armor Class.");
								isConflicted = true;
							}
						}

					}

					if (selectedArmor.getCovers().contains("Torso")) {
						if (!equippedTable.getValueAt(i, EQUIPPED_TORSO_INDEX).equals("")) {
							tempArmor = characterModel.getArmors()
									.get(equippedTable.getValueAt(i, EQUIPPED_TORSO_INDEX));
							tempArmorArmorClass = tempArmor.getArmorClass();
							if (selectedArmorArmorClass.equals("Hard Armor")
									&& tempArmorArmorClass.equals("Hard Armor")) {
								JOptionPane.showMessageDialog(characterView,
										"You cannot equip " + tempArmor.getType() + " and " + selectedArmor.getType()
												+ " because they are both Hard Armor Class.");
								isConflicted = true;
							}
						}

					}

					if (selectedArmor.getCovers().contains("Arms")) {
						if (!equippedTable.getValueAt(i, EQUIPPED_RIGHT_ARM_INDEX).equals("")) {
							tempArmor = characterModel.getArmors()
									.get(equippedTable.getValueAt(i, EQUIPPED_RIGHT_ARM_INDEX));
							tempArmorArmorClass = tempArmor.getArmorClass();
							if (selectedArmorArmorClass.equals("Hard Armor")
									&& tempArmorArmorClass.equals("Hard Armor")) {
								JOptionPane.showMessageDialog(characterView,
										"You cannot equip " + tempArmor.getType() + " and " + selectedArmor.getType()
												+ " because they are both Hard Armor Class.");
								isConflicted = true;
							}
						}
					}

					if (selectedArmor.getCovers().contains("Legs")) {
						if (!equippedTable.getValueAt(i, EQUIPPED_RIGHT_LEG_INDEX).equals("")) {
							tempArmor = characterModel.getArmors()
									.get(equippedTable.getValueAt(i, EQUIPPED_RIGHT_LEG_INDEX));
							tempArmorArmorClass = tempArmor.getArmorClass();
							if (selectedArmorArmorClass.equals("Hard Armor")
									&& tempArmorArmorClass.equals("Hard Armor")) {
								JOptionPane.showMessageDialog(characterView,
										"You cannot equip " + tempArmor.getType() + " and " + selectedArmor.getType()
												+ " because they are both Hard Armor Class.");
								isConflicted = true;
							}
						}
					}
				}

				if (selectedArmor.getCovers().contains("Head")) {
					int filledSlots = 0;
					for (int i = 0; i < equippedTable.getRowCount(); i++) {
						String equippedArmorType = (String) equippedTable.getValueAt(i, EQUIPPED_HEAD_INDEX);
						if (!equippedArmorType.equals("")) {
							filledSlots++;
						}
					}
					if (filledSlots >= MAX_ARMOR_LAYERS) {
						isConflicted = true;
					}
				}

				if (selectedArmor.getCovers().contains("Torso")) {
					int filledSlots = 0;
					for (int i = 0; i < equippedTable.getRowCount(); i++) {
						String equippedArmorType = (String) equippedTable.getValueAt(i, EQUIPPED_TORSO_INDEX);
						if (!equippedArmorType.equals("")) {
							filledSlots++;
						}
					}
					if (filledSlots >= MAX_ARMOR_LAYERS) {
						isConflicted = true;
					}
				}

				if (selectedArmor.getCovers().contains("Arms")) {
					int filledSlots = 0;
					for (int i = 0; i < equippedTable.getRowCount(); i++) {
						String equippedArmorType = (String) equippedTable.getValueAt(i, EQUIPPED_RIGHT_ARM_INDEX);
						if (!equippedArmorType.equals("")) {
							filledSlots++;
						}
					}
					if (filledSlots >= MAX_ARMOR_LAYERS) {
						isConflicted = true;
					}
				}

				if (selectedArmor.getCovers().contains("Legs")) {
					int filledSlots = 0;
					for (int i = 0; i < equippedTable.getRowCount(); i++) {
						String equippedArmorType = (String) equippedTable.getValueAt(i, EQUIPPED_RIGHT_LEG_INDEX);
						if (!equippedArmorType.equals("")) {
							filledSlots++;
						}
					}
					if (filledSlots >= MAX_ARMOR_LAYERS) {
						isConflicted = true;
					}
				}

				if (!isConflicted) {
					characterModel.setGeneralEncumbranceValue(
							characterModel.getGeneralEncumbranceValue() + selectedArmor.getEncumbranceValue());

					if (selectedArmor.getCovers().contains("Head")) {
						for (int i = 0; i < equippedTable.getRowCount(); i++) {
							if (equippedTable.getValueAt(i, EQUIPPED_HEAD_INDEX).equals("")) {
								equippedTable.setValueAt(selectedArmor.getType(), i, EQUIPPED_HEAD_INDEX);
								break;
							}
						}
					}

					if (selectedArmor.getCovers().contains("Torso")) {
						for (int i = 0; i < equippedTable.getRowCount(); i++) {
							if (equippedTable.getValueAt(i, EQUIPPED_TORSO_INDEX).equals("")) {
								equippedTable.setValueAt(selectedArmor.getType(), i, EQUIPPED_TORSO_INDEX);
								break;
							}
						}

					}

					if (selectedArmor.getCovers().contains("Arms")) {
						for (int i = 0; i < equippedTable.getRowCount(); i++) {
							if (equippedTable.getValueAt(i, EQUIPPED_RIGHT_ARM_INDEX).equals("")) {
								equippedTable.setValueAt(selectedArmor.getType(), i, EQUIPPED_RIGHT_ARM_INDEX);
								break;
							}
						}
						for (int i = 0; i < equippedTable.getRowCount(); i++) {
							if (equippedTable.getValueAt(i, EQUIPPED_LEFT_ARM_INDEX).equals("")) {
								equippedTable.setValueAt(selectedArmor.getType(), i, EQUIPPED_LEFT_ARM_INDEX);
								break;
							}
						}

					}

					if (selectedArmor.getCovers().contains("Legs")) {
						for (int i = 0; i < equippedTable.getRowCount(); i++) {
							if (equippedTable.getValueAt(i, EQUIPPED_RIGHT_LEG_INDEX).equals("")) {
								equippedTable.setValueAt(selectedArmor.getType(), i, EQUIPPED_RIGHT_LEG_INDEX);
								break;
							}
						}
						for (int i = 0; i < equippedTable.getRowCount(); i++) {
							if (equippedTable.getValueAt(i, EQUIPPED_LEFT_LEG_INDEX).equals("")) {
								equippedTable.setValueAt(selectedArmor.getType(), i, EQUIPPED_LEFT_LEG_INDEX);
								break;
							}
						}

					}
				}

			}
		}

	}

	class HandleDocumentListener implements DocumentListener {
		@Override
		public void changedUpdate(DocumentEvent event) {
			characterModel.setCharacterName(characterView.getCharacterName());
		}

		@Override
		public void insertUpdate(DocumentEvent event) {
			characterModel.setCharacterName(characterView.getCharacterName());
		}

		@Override
		public void removeUpdate(DocumentEvent event) {
			characterModel.setCharacterName(characterView.getCharacterName());
		}

	}

	class InventoryChangeListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			JPanel cardsPanel = characterView.getInventoryCardPanel();
			CardLayout layout = (CardLayout) cardsPanel.getLayout();
			layout.show(cardsPanel, (String) event.getItem());
		}

	}

	class StoreChangeListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			JPanel cardsPanel = characterView.getStoreCardPanel();
			CardLayout layout = (CardLayout) cardsPanel.getLayout();
			layout.show(cardsPanel, (String) event.getItem());
		}
	}

	class RoleChangeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JComboBox comboBox = (JComboBox) event.getSource();
			String selectedRole = (String) comboBox.getSelectedItem();
			characterModel.setRole(selectedRole);
		}
	}

	class IntelligenceChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent event) {
			int newLevel = Integer.parseInt(((JSpinner) event.getSource()).getValue().toString());
			characterModel.setIntelligenceLevel(newLevel);

			int usedCharacterPoints = characterModel.getIntelligenceLevel()
					+ characterModel.getUnmodifiedReflexesLevel() + characterModel.getTechnicalAbilityLevel()
					+ characterModel.getCoolLevel() + characterModel.getAttractivenessLevel()
					+ characterModel.getLuckLevel() + characterModel.getMovementAllowanceLevel()
					+ characterModel.getBodyLevel() + characterModel.getTotalEmpathyLevel();

			int remainingPoints = characterModel.getCharacterPoints() - usedCharacterPoints;
			int intelligenceLevel = characterModel.getIntelligenceLevel();
			while (remainingPoints < 0 && characterModel.getIntelligenceLevel() > 2) {
				intelligenceLevel = characterModel.getIntelligenceLevel() - 1;
				remainingPoints++;
				characterModel.setIntelligenceLevel(intelligenceLevel);
			}

			characterView.setIntelligenceLevel(String.valueOf(intelligenceLevel));
			characterView.setCharacterPoints(String.valueOf(remainingPoints));
		}

	}

	class UnmodifiedReflexesChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent event) {
			int newLevel = Integer.parseInt(((JSpinner) event.getSource()).getValue().toString());
			characterModel.setUnmodifiedReflexesLevel(newLevel);

			int usedCharacterPoints = characterModel.getIntelligenceLevel()
					+ characterModel.getUnmodifiedReflexesLevel() + characterModel.getTechnicalAbilityLevel()
					+ characterModel.getCoolLevel() + characterModel.getAttractivenessLevel()
					+ characterModel.getLuckLevel() + characterModel.getMovementAllowanceLevel()
					+ characterModel.getBodyLevel() + characterModel.getTotalEmpathyLevel();

			int remainingPoints = characterModel.getCharacterPoints() - usedCharacterPoints;
			int unmodifiedReflexesLevel = characterModel.getUnmodifiedReflexesLevel();
			while (remainingPoints < 0 && characterModel.getUnmodifiedReflexesLevel() > 2) {
				unmodifiedReflexesLevel = characterModel.getUnmodifiedReflexesLevel() - 1;
				remainingPoints++;
				characterModel.setUnmodifiedReflexesLevel(unmodifiedReflexesLevel);
			}

			characterView.setUnmodifiedReflexesLevel(String.valueOf(unmodifiedReflexesLevel));
			characterView.setCharacterPoints(String.valueOf(remainingPoints));

			int encumbranceValue = characterModel.getGeneralEncumbranceValue()
					+ characterModel.getHeadEncumbranceValue() + characterModel.getTorsoEncumbranceValue()
					+ characterModel.getRightArmEncumbranceValue() + characterModel.getLeftArmEncumbranceValue()
					+ characterModel.getRightLegEncumbranceValue() + characterModel.getLeftLegEncumbranceValue();
			characterModel.setModifiedReflexesLevel(characterModel.getUnmodifiedReflexesLevel() - encumbranceValue);
			characterView.setModifiedReflexesLevel(
					Integer.toString(characterModel.getUnmodifiedReflexesLevel() - encumbranceValue));
		}

	}

	class TechnicalAbilityChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent event) {
			int newLevel = Integer.parseInt(((JSpinner) event.getSource()).getValue().toString());
			characterModel.setTechnicalAbilityLevel(newLevel);

			int usedCharacterPoints = characterModel.getIntelligenceLevel()
					+ characterModel.getUnmodifiedReflexesLevel() + characterModel.getTechnicalAbilityLevel()
					+ characterModel.getCoolLevel() + characterModel.getAttractivenessLevel()
					+ characterModel.getLuckLevel() + characterModel.getMovementAllowanceLevel()
					+ characterModel.getBodyLevel() + characterModel.getTotalEmpathyLevel();

			int remainingPoints = characterModel.getCharacterPoints() - usedCharacterPoints;
			int technicalAbilityLevel = characterModel.getTechnicalAbilityLevel();
			while (remainingPoints < 0 && characterModel.getTechnicalAbilityLevel() > 2) {
				technicalAbilityLevel = characterModel.getTechnicalAbilityLevel() - 1;
				characterModel.setTechnicalAbilityLevel(technicalAbilityLevel);
				remainingPoints++;
			}

			characterView.setTechnicalAbilityLevel(String.valueOf(technicalAbilityLevel));
			characterView.setCharacterPoints(String.valueOf(remainingPoints));
		}

	}

	class CoolChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent event) {
			int newLevel = Integer.parseInt(((JSpinner) event.getSource()).getValue().toString());
			characterModel.setCoolLevel(newLevel);

			int usedCharacterPoints = characterModel.getIntelligenceLevel()
					+ characterModel.getUnmodifiedReflexesLevel() + characterModel.getTechnicalAbilityLevel()
					+ characterModel.getCoolLevel() + characterModel.getAttractivenessLevel()
					+ characterModel.getLuckLevel() + characterModel.getMovementAllowanceLevel()
					+ characterModel.getBodyLevel() + characterModel.getTotalEmpathyLevel();

			int remainingPoints = characterModel.getCharacterPoints() - usedCharacterPoints;
			int coolLevel = characterModel.getCoolLevel();
			while (remainingPoints < 0 && characterModel.getCoolLevel() > 2) {
				coolLevel = characterModel.getCoolLevel() - 1;
				characterModel.setCoolLevel(coolLevel);
				remainingPoints++;
			}

			characterView.setCoolLevel(String.valueOf(coolLevel));
			characterView.setCharacterPoints(String.valueOf(remainingPoints));
		}

	}

	class AttractivenessChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent event) {
			int newLevel = Integer.parseInt(((JSpinner) event.getSource()).getValue().toString());
			characterModel.setAttractivenessLevel(newLevel);

			int usedCharacterPoints = characterModel.getIntelligenceLevel()
					+ characterModel.getUnmodifiedReflexesLevel() + characterModel.getTechnicalAbilityLevel()
					+ characterModel.getCoolLevel() + characterModel.getAttractivenessLevel()
					+ characterModel.getLuckLevel() + characterModel.getMovementAllowanceLevel()
					+ characterModel.getBodyLevel() + characterModel.getTotalEmpathyLevel();

			int remainingPoints = characterModel.getCharacterPoints() - usedCharacterPoints;
			int attractivenessLevel = characterModel.getAttractivenessLevel();
			while (remainingPoints < 0 && characterModel.getAttractivenessLevel() > 2) {
				attractivenessLevel = characterModel.getAttractivenessLevel() - 1;
				characterModel.setAttractivenessLevel(attractivenessLevel);
				remainingPoints++;
			}

			characterView.setAttractivenessLevel(String.valueOf(attractivenessLevel));
			characterView.setCharacterPoints(String.valueOf(remainingPoints));
		}

	}

	class LuckChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent event) {
			int newLevel = Integer.parseInt(((JSpinner) event.getSource()).getValue().toString());
			characterModel.setLuckLevel(newLevel);

			int usedCharacterPoints = characterModel.getIntelligenceLevel()
					+ characterModel.getUnmodifiedReflexesLevel() + characterModel.getTechnicalAbilityLevel()
					+ characterModel.getCoolLevel() + characterModel.getAttractivenessLevel()
					+ characterModel.getLuckLevel() + characterModel.getMovementAllowanceLevel()
					+ characterModel.getBodyLevel() + characterModel.getTotalEmpathyLevel();

			int remainingPoints = characterModel.getCharacterPoints() - usedCharacterPoints;
			int luckLevel = characterModel.getLuckLevel();
			while (remainingPoints < 0 && characterModel.getLuckLevel() > 2) {
				luckLevel = characterModel.getLuckLevel() - 1;
				characterModel.setLuckLevel(luckLevel);
				remainingPoints++;
			}

			characterView.setLuckLevel(String.valueOf(luckLevel));
			characterView.setCharacterPoints(String.valueOf(remainingPoints));
		}

	}

	class MovementAllowanceChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent event) {
			int newLevel = Integer.parseInt(((JSpinner) event.getSource()).getValue().toString());
			characterModel.setMovementAllowanceLevel(newLevel);

			int usedCharacterPoints = characterModel.getIntelligenceLevel()
					+ characterModel.getUnmodifiedReflexesLevel() + characterModel.getTechnicalAbilityLevel()
					+ characterModel.getCoolLevel() + characterModel.getAttractivenessLevel()
					+ characterModel.getLuckLevel() + characterModel.getMovementAllowanceLevel()
					+ characterModel.getBodyLevel() + characterModel.getTotalEmpathyLevel();

			int remainingPoints = characterModel.getCharacterPoints() - usedCharacterPoints;
			int movementAllowanceLevel = characterModel.getMovementAllowanceLevel();
			while (remainingPoints < 0 && characterModel.getMovementAllowanceLevel() > 2) {
				movementAllowanceLevel = characterModel.getMovementAllowanceLevel() - 1;
				characterModel.setMovementAllowanceLevel(movementAllowanceLevel);
				remainingPoints++;
			}

			characterView.setMovementAllowanceLevel(String.valueOf(movementAllowanceLevel));
			characterView.setCharacterPoints(String.valueOf(remainingPoints));
			characterView.setRunLevel(Double.toString(characterModel.getRunLevel()));
			characterView.setLeapLevel(Double.toString(characterModel.getLeapLevel()));
		}
	}

	class BodyChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent event) {
			int newLevel = Integer.parseInt(((JSpinner) event.getSource()).getValue().toString());
			characterModel.setBodyLevel(newLevel);

			int usedCharacterPoints = characterModel.getIntelligenceLevel()
					+ characterModel.getUnmodifiedReflexesLevel() + characterModel.getTechnicalAbilityLevel()
					+ characterModel.getCoolLevel() + characterModel.getAttractivenessLevel()
					+ characterModel.getLuckLevel() + characterModel.getMovementAllowanceLevel()
					+ characterModel.getBodyLevel() + characterModel.getTotalEmpathyLevel();

			int remainingPoints = characterModel.getCharacterPoints() - usedCharacterPoints;
			int bodyLevel = characterModel.getBodyLevel();
			while (remainingPoints < 0 && characterModel.getBodyLevel() > 2) {
				bodyLevel = characterModel.getBodyLevel() - 1;
				characterModel.setBodyLevel(bodyLevel);
				remainingPoints++;
			}

			characterView.setBodyLevel(String.valueOf(bodyLevel));
			characterView.setCharacterPoints(String.valueOf(remainingPoints));
			characterView.setLiftCapacity(Double.toString(characterModel.getLiftCapacity()));
			characterView.setCarryCapacity(Double.toString(characterModel.getCarryCapacity()));
			characterView.setSaveModifier(Integer.toString(characterModel.calculateSaveModifier()));

			int characterBodyTypeModifier = characterModel.calculateBodyTypeModifier();
			characterModel.setBodyTypeModifier(characterBodyTypeModifier);
			characterView.setBodyTypeModifier(Integer.toString(characterBodyTypeModifier));
		}
	}

	class TotalEmpathyChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent event) {
			int newLevel = Integer.parseInt(((JSpinner) event.getSource()).getValue().toString());
			characterModel.setTotalEmpathyLevel(newLevel);

			int usedCharacterPoints = characterModel.getIntelligenceLevel()
					+ characterModel.getUnmodifiedReflexesLevel() + characterModel.getTechnicalAbilityLevel()
					+ characterModel.getCoolLevel() + characterModel.getAttractivenessLevel()
					+ characterModel.getLuckLevel() + characterModel.getMovementAllowanceLevel()
					+ characterModel.getBodyLevel() + characterModel.getTotalEmpathyLevel();

			int remainingPoints = characterModel.getCharacterPoints() - usedCharacterPoints;
			int totalEmpathyLevel = characterModel.getTotalEmpathyLevel();
			while (remainingPoints < 0 && characterModel.getTotalEmpathyLevel() > 2) {
				totalEmpathyLevel = characterModel.getTotalEmpathyLevel() - 1;
				characterModel.setTotalEmpathyLevel(totalEmpathyLevel);
				remainingPoints++;
			}

			characterView.setTotalEmpathyLevel(String.valueOf(totalEmpathyLevel));
			characterView.setCharacterPoints(String.valueOf(remainingPoints));
		}
	}

	class LoadCharacterActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			int returnVal = characterView.getFileChooser().showOpenDialog(characterView);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File originalFile = characterView.getFileChooser().getSelectedFile();
				File formattedFile = new File(originalFile.toString() + ".char");
				Scanner sc;
				try {
					if (originalFile.toString().endsWith(".char")) {
						sc = new Scanner(originalFile);
					} else {
						sc = new Scanner(formattedFile);
					}

					while (sc.hasNextLine()) {
						try {
							String[] strings = sc.nextLine().split("\t");
							String key = strings[0];
							String value = strings[1];
							switch (key) {
							case "handle":
								characterView.setHandle(value);
								characterModel.setCharacterName(value);
								break;
							case "role":
								characterView.setRole(value);
								characterModel.setRole(value);
								break;
							case "character_points":
								characterView.setCharacterPoints(value);
								characterModel.setCharacterPoints(Integer.parseInt(value));
								break;
							case "intelligence":
								characterView.setIntelligenceLevel(value);
								characterModel.setIntelligenceLevel(Integer.parseInt(value));
								break;
							case "unmodified_reflexes":
								characterView.setUnmodifiedReflexesLevel(value);
								characterModel.setUnmodifiedReflexesLevel(Integer.parseInt(value));
								break;
							case "modified_reflexes":
								characterView.setModifiedReflexesLevel(value);
								characterModel.setModifiedReflexesLevel(Integer.parseInt(value));
								break;
							case "technical_ability":
								characterView.setTechnicalAbilityLevel(value);
								characterModel.setTechnicalAbilityLevel(Integer.parseInt(value));
								break;
							case "cool":
								characterView.setCoolLevel(value);
								characterModel.setCoolLevel(Integer.parseInt(value));
								break;
							case "attractiveness":
								characterView.setAttractivenessLevel(value);
								characterModel.setAttractivenessLevel(Integer.parseInt(value));
								break;
							case "luck":
								characterView.setLuckLevel(value);
								characterModel.setLuckLevel(Integer.parseInt(value));
								break;
							case "movement_allowance":
								characterView.setMovementAllowanceLevel(value);
								characterModel.setMovementAllowanceLevel(Integer.parseInt(value));
								break;
							case "body":
								characterView.setBodyLevel(value);
								characterModel.setBodyLevel(Integer.parseInt(value));
								break;
							case "current_empathy":
								characterView.setCurrentEmpathyLevel(value);
								characterModel.setCurrentEmpathyLevel(Integer.parseInt(value));
								break;
							case "total_empathy":
								characterView.setTotalEmpathyLevel(value);
								characterModel.setTotalEmpathyLevel(Integer.parseInt(value));
								break;
							case "run":
								characterView.setRunLevel(value);
								characterModel.setRunDistance(Double.parseDouble(value));
								break;
							case "leap":
								characterView.setLeapLevel(value);
								characterModel.setLeapDistance(Double.parseDouble(value));
								break;
							case "lift":
								characterView.setLiftCapacity(value);
								characterModel.setLiftCapacity(Double.parseDouble(value));
								break;
							case "carry":
								characterView.setCarryCapacity(value);
								characterModel.setCarryCapacity(Double.parseDouble(value));
								break;
							case "head_armor_sp":
								characterView.setHeadArmorStoppingPower(value);
								characterModel.setHeadArmorStoppingPower(Integer.parseInt(value));
								break;
							case "torso_armor_sp":
								characterView.setTorsoArmorStoppingPower(value);
								characterModel.setTorsoArmorStoppingPower(Integer.parseInt(value));
								break;
							case "right_arm_armor_sp":
								characterView.setRightArmArmorStoppingPower(value);
								characterModel.setRightArmArmorStoppingPower(Integer.parseInt(value));
								break;
							case "left_arm_armor_sp":
								characterView.setLeftArmArmorStoppingPower(value);
								characterModel.setLeftLegArmorStoppingPower(Integer.parseInt(value));
								break;
							case "right_leg_armor_sp":
								characterView.setRightLegArmorStoppingPower(value);
								characterModel.setRightLegArmorStoppingPower(Integer.parseInt(value));
								break;
							case "left_leg_armor_sp":
								characterView.setLeftLegArmorStoppingPower(value);
								characterModel.setLeftLegArmorStoppingPower(Integer.parseInt(value));
								break;
							case "save_modifier":
								characterView.setSaveModifier(value);
								characterModel.setSaveModifier(Integer.parseInt(value));
								break;
							case "body_type_modifier":
								characterView.setBodyTypeModifier(value);
								characterModel.setBodyTypeModifier(Integer.parseInt(value));
								break;
							case "injury_points":
								characterModel.setInjuryPoints(Double.parseDouble(value));
								characterView.drawLoadedInjuryPoints(Double.parseDouble(value));
								break;
							case "portrait":
								convertBase64ToImage(value);
								break;
							default:
								String typeCode = key.substring(0, 3);
								String specificSkill = (key.matches("\\w+:\\w+\\.\\w+")) ? key.split("\\.")[1] : "";
								Map<String, CharacterCreationModel.Skill> targetCategory;

								switch (typeCode) {
								case "SPE":
									targetCategory = characterModel.getSkillCatelog().get(CharacterCreationModel.SPEC);
									break;
								case "ATT":
									targetCategory = characterModel.getSkillCatelog().get(CharacterCreationModel.ATT);
									break;
								case "BOD":
									targetCategory = characterModel.getSkillCatelog().get(CharacterCreationModel.BOD);
									break;
								case "COO":
									targetCategory = characterModel.getSkillCatelog().get(CharacterCreationModel.CL);
									break;
								case "EMP":
									targetCategory = characterModel.getSkillCatelog().get(CharacterCreationModel.EMP);
									break;
								case "INT":
									targetCategory = characterModel.getSkillCatelog().get(CharacterCreationModel.INT);
									break;
								case "REF":
									targetCategory = characterModel.getSkillCatelog().get(CharacterCreationModel.REF);
									break;
								case "TEC":
									targetCategory = characterModel.getSkillCatelog().get(CharacterCreationModel.TECH);
									break;
								default:
									targetCategory = null;
									break;
								}

								String skillCode = key.substring(4).toLowerCase();
								for (CharacterCreationModel.Skill skill : targetCategory.values()) {
									String formattedSkillName = skill.getSkillName().replace(" ", "_").replace("*", ".")
											.toLowerCase();
									if (skillCode.contains(formattedSkillName)) {
										skill.setRank(Integer.parseInt(value));
										skill.setSpecifiedSkill(specificSkill);
									}
								}
								break;
							}
						} catch (ArrayIndexOutOfBoundsException exception) {
							JOptionPane.showMessageDialog(characterView, "Error with loading from save file");
						}

					}
					characterView.clearSkillTables();
					populateSkillPanels();

					sc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		private void convertBase64ToImage(String base64String) {
			byte[] decodedByte = Base64.decodeBase64(base64String);
			InputStream in = new ByteArrayInputStream(decodedByte);
			BufferedImage image;
			try {
				image = ImageIO.read(in);
				characterView.setCharacterPortrait(image);
			} catch (IOException exception) {
				JOptionPane.showMessageDialog(characterView,
						"There was an error when trying to load the character portrait.");
			}
		}

	}

	class SaveCharacterActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			int returnVal = characterView.getFileChooser().showSaveDialog(characterView);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File originalFile = characterView.getFileChooser().getSelectedFile();
				File formattedFile = new File(originalFile.toString() + ".char");

				if (originalFile.exists() || formattedFile.exists()) {
					returnVal = JOptionPane.showConfirmDialog(
							characterView, "Do you want to overwrite "
									+ characterView.getFileChooser().getSelectedFile().getName() + " ?",
							"Confirm Overwrite", JOptionPane.YES_NO_OPTION);
					if (returnVal == JOptionPane.YES_OPTION) {
						writeDataToFile();
					}
				} else {
					writeDataToFile();
				}

			}
		}

		private void writeDataToFile() {
			File file = characterView.getFileChooser().getSelectedFile();
			String filename = file.toString();
			FileWriter fw;

			try {
				if (!filename.endsWith(".char")) {
					filename += ".char";
					fw = new FileWriter(filename);
				} else {
					fw = new FileWriter(filename);
				}
				fw.write("handle\t" + characterModel.getHandle() + "\n" //
						+ "role\t" + characterModel.getRole().getRoleName() + "\n" //
						+ "character_points\t" + characterModel.getCharacterPoints() + "\n" //
						+ "intelligence\t" + characterModel.getIntelligenceLevel() + "\n" //
						+ "unmodified_reflexes\t" + characterModel.getUnmodifiedReflexesLevel() + "\n" //
						+ "modified_reflexes\t" + characterModel.getModifiedReflexesLevel() + "\n" //
						+ "technical_ability\t" + characterModel.getTechnicalAbilityLevel() + "\n" //
						+ "cool\t" + characterModel.getCoolLevel() + "\n" //
						+ "attractiveness\t" + characterModel.getAttractivenessLevel() + "\n" //
						+ "luck\t" + characterModel.getLuckLevel() + "\n" //
						+ "movement_allowance\t" + characterModel.getMovementAllowanceLevel() + "\n" //
						+ "body\t" + characterModel.getBodyLevel() + "\n" //
						+ "current_empathy\t" + characterModel.getCurrentEmpathyLevel() + "\n" //
						+ "total_empathy\t" + characterModel.getTotalEmpathyLevel() + "\n" //
						+ "run\t" + characterModel.getRunLevel() + "\n" //
						+ "leap\t" + characterModel.getLeapLevel() + "\n" //
						+ "lift\t" + characterModel.getLiftCapacity() + "\n" //
						+ "carry\t" + characterModel.getCarryCapacity() + "\n" //
						+ "head_armor_sp\t" + characterModel.getHeadArmorStoppingPower() + "\n" //
						+ "torso_armor_sp\t" + characterModel.getTorsoArmorStoppingPower() + "\n" //
						+ "right_arm_armor_sp\t" + characterModel.getRightArmArmorStoppingPower() + "\n" //
						+ "left_arm_armor_sp\t" + characterModel.getLeftArmArmorStoppingPower() + "\n" //
						+ "right_leg_armor_sp\t" + characterModel.getRightLegArmorStoppingPower() + "\n" //
						+ "left_leg_armor_sp\t" + characterModel.getLeftLegArmorStoppingPower() + "\n" //
						+ "save_modifier\t" + characterModel.getSaveModifier() + "\n" //
						+ "body_type_modifier\t" + characterModel.getBodyTypeModifier() + "\n" //
						+ "injury_points\t" + characterModel.getInjuryPoints() + "\n");

				for (Map<String, CharacterCreationModel.Skill> skillCategory : characterModel.getSkillCatelog()
						.values()) {
					for (CharacterCreationModel.Skill skill : skillCategory.values()) {
						String skillName = skill.getSkillName();
						String skillType = skill.getType();
						int skillRank = skill.getRank();
						String specifiedSkill = skill.getSpecifiedSkill();
						String skillCode = skillName.replaceAll(" ", "_").toLowerCase();
						String typeCode = skillType.substring(0, 3);

						String completeSkillCode = specifiedSkill.equals("")
								? (typeCode + ":" + skillCode + "\t" + skillRank)
								: (typeCode + ":" + skillCode.replace("*", ".") + specifiedSkill + "\t" + skillRank);
						fw.write(completeSkillCode + "\n");

					}
				}
				String encodedData = convertImageToBase64(characterView.getCharacterPortrait());
				fw.write("portrait\t" + encodedData + "\n");

				fw.close();
			} catch (IOException exception) {
				System.err.println("File does not exist.");
			}
		}

		private String convertImageToBase64(BufferedImage image) {
			String imageString = null;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
				ImageIO.write(image, "png", bos);
				byte[] bytes = bos.toByteArray();
				imageString = Base64.encodeBase64String(bytes);
				bos.close();
			} catch (IOException exception) {
				JOptionPane.showMessageDialog(characterView, "The file does not exist.");
			}

			return imageString;
		}
	}

	class IncreaseInjuryActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			double currentInjuryPoints = characterModel.getInjuryPoints();
			currentInjuryPoints += 1;
			if (currentInjuryPoints <= CharacterCreationModel.MAXIMUM_INJURY_POINTS) {
				characterModel.setInjuryPoints(currentInjuryPoints);
				characterView.drawIncreasedInjuryPoints(characterModel.getInjuryPoints());

				characterModel.setSaveModifier(characterModel.calculateSaveModifier());
				characterView.setSaveModifier(Integer.toString(characterModel.calculateSaveModifier()));
			}
		}
	}

	class MinorlyIncreaseInjuryActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			double currentInjuryPoints = characterModel.getInjuryPoints();
			currentInjuryPoints += 0.5;
			if (currentInjuryPoints <= CharacterCreationModel.MAXIMUM_INJURY_POINTS) {
				characterModel.setInjuryPoints(currentInjuryPoints);
				characterView.drawMinorlyIncreasedInjuryPoints(characterModel.getInjuryPoints());

				characterModel.setSaveModifier(characterModel.calculateSaveModifier());
				characterView.setSaveModifier(Integer.toString(characterModel.calculateSaveModifier()));
			}
		}
	}

	class MinorlyDecreaseInjuryActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			double currentInjuryPoints = characterModel.getInjuryPoints();
			currentInjuryPoints -= 0.5;
			if (currentInjuryPoints >= CharacterCreationModel.MINIMUM_INJURY_POINTS) {
				characterModel.setInjuryPoints(currentInjuryPoints);
				characterView.drawMinorlyDecreaseInjuryPoints(characterModel.getInjuryPoints());

				characterModel.setSaveModifier(characterModel.calculateSaveModifier());
				characterView.setSaveModifier(Integer.toString(characterModel.calculateSaveModifier()));
			}
		}
	}

	class DecreaseInjuryActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			double currentInjuryPoints = characterModel.getInjuryPoints();
			currentInjuryPoints -= 1;
			if (currentInjuryPoints >= CharacterCreationModel.MINIMUM_INJURY_POINTS) {
				characterModel.setInjuryPoints(currentInjuryPoints);
				characterView.drawDecreaseInjuryPoints(characterModel.getInjuryPoints());

				characterModel.setSaveModifier(characterModel.calculateSaveModifier());
				characterView.setSaveModifier(Integer.toString(characterModel.calculateSaveModifier()));
			}
		}
	}

	class SkillTableModelListener implements TableModelListener {
		@Override
		public void tableChanged(TableModelEvent event) {
			if (event.getType() == TableModelEvent.UPDATE) {
				SkillTableModel model = (SkillTableModel) event.getSource();
				String skillName = (String) model.getValueAt(event.getFirstRow(), 0);
				String specifiedSkill = (String) model.getValueAt(event.getFirstRow(), 1);
				int rank = (Integer) model.getValueAt(event.getFirstRow(), 2);

				for (Map<String, CharacterCreationModel.Skill> skillCategory : characterModel.getSkillCatelog()
						.values()) {
					if (skillCategory.containsKey(skillName)) {
						CharacterCreationModel.Skill skill = skillCategory.get(skillName);
						skill.setRank(rank);
						skill.setSpecifiedSkill(specifiedSkill);
					}
				}
			}
		}

	}

	class EquippedTableModelListener implements TableModelListener {
		private static final int EQUIPPED_HEAD_INDEX = 0;
		private static final int EQUIPPED_TORSO_INDEX = 1;
		private static final int EQUIPPED_RIGHT_ARM_INDEX = 2;
		private static final int EQUIPPED_LEFT_ARM_INDEX = 3;
		private static final int EQUIPPED_RIGHT_LEG_INDEX = 4;
		private static final int EQUIPPED_LEFT_LEG_INDEX = 5;
		private static final int EQUIPPED_LAYER_1_INDEX = 0;
		private static final int EQUIPPED_LAYER_2_INDEX = 1;
		private static final int EQUIPPED_LAYER_3_INDEX = 2;

		@Override
		public void tableChanged(TableModelEvent event) {
			CharacterCreationModel.Armor armorLayer1 = null;
			CharacterCreationModel.Armor armorLayer2 = null;
			CharacterCreationModel.Armor armorLayer3 = null;

			for (int i = 0; i < 6; i++) {
				int layers = 0;
				int stoppingPower = 0;
				String armorLayer1Type = (String) characterView.getEquippedTable().getValueAt(EQUIPPED_LAYER_1_INDEX,
						i);
				String armorLayer2Type = (String) characterView.getEquippedTable().getValueAt(EQUIPPED_LAYER_2_INDEX,
						i);
				String armorLayer3Type = (String) characterView.getEquippedTable().getValueAt(EQUIPPED_LAYER_3_INDEX,
						i);
				List<Integer> stoppingPowers = new ArrayList<Integer>();

				if (!armorLayer1Type.equals("")) {
					armorLayer1 = characterModel.getArmors().get(armorLayer1Type);
					stoppingPowers.add(armorLayer1.getStoppingPower());
					layers++;
				}

				if (!armorLayer2Type.equals("")) {
					armorLayer2 = characterModel.getArmors().get(armorLayer2Type);
					stoppingPowers.add(armorLayer2.getStoppingPower());
					layers++;
				}

				if (!armorLayer3Type.equals("")) {
					armorLayer3 = characterModel.getArmors().get(armorLayer3Type);
					stoppingPowers.add(armorLayer3.getStoppingPower());
					layers++;
				}

				stoppingPower = calculateLayeredStoppingPower(stoppingPowers);

				switch (i) {
				case EQUIPPED_HEAD_INDEX:
					if (layers == 1) {
						characterModel.setHeadEncumbranceValue(0);
					} else if (layers == 2) {
						characterModel.setHeadEncumbranceValue(1);
					} else if (layers == 3) {
						characterModel.setHeadEncumbranceValue(3);
					}
					characterModel.setHeadArmorStoppingPower(stoppingPower);
					characterView.setHeadArmorStoppingPower(String.valueOf(stoppingPower));
					break;
				case EQUIPPED_TORSO_INDEX:
					if (layers == 1) {
						characterModel.setTorsoEncumbranceValue(0);
					} else if (layers == 2) {
						characterModel.setTorsoEncumbranceValue(1);
					} else if (layers == 3) {
						characterModel.setTorsoEncumbranceValue(3);
					}
					characterModel.setTorsoArmorStoppingPower(stoppingPower);
					characterView.setTorsoArmorStoppingPower(String.valueOf(stoppingPower));
					break;
				case EQUIPPED_RIGHT_ARM_INDEX:
					if (layers == 1) {
						characterModel.setRightArmEncumbranceValue(0);
					} else if (layers == 2) {
						characterModel.setRightArmEncumbranceValue(1);
					} else if (layers == 3) {
						characterModel.setRightArmEncumbranceValue(3);
					}
					characterModel.setRightArmArmorStoppingPower(stoppingPower);
					characterView.setRightArmArmorStoppingPower(String.valueOf(stoppingPower));
					break;
				case EQUIPPED_LEFT_ARM_INDEX:
					if (layers == 1) {
						characterModel.setLeftArmEncumbranceValue(0);
					} else if (layers == 2) {
						characterModel.setLeftArmEncumbranceValue(1);
					} else if (layers == 3) {
						characterModel.setLeftArmEncumbranceValue(3);
					}
					characterModel.setLeftArmArmorStoppingPower(stoppingPower);
					characterView.setLeftArmArmorStoppingPower(String.valueOf(stoppingPower));
					break;
				case EQUIPPED_RIGHT_LEG_INDEX:
					if (layers == 1) {
						characterModel.setRightLegEncumbranceValue(0);
					} else if (layers == 2) {
						characterModel.setRightLegEncumbranceValue(1);
					} else if (layers == 3) {
						characterModel.setRightLegEncumbranceValue(3);
					}
					characterModel.setRightLegArmorStoppingPower(stoppingPower);
					characterView.setRightLegArmorStoppingPower(String.valueOf(stoppingPower));
					break;
				case EQUIPPED_LEFT_LEG_INDEX:
					if (layers == 1) {
						characterModel.setLeftLegEncumbranceValue(0);
					} else if (layers == 2) {
						characterModel.setLeftLegEncumbranceValue(1);
					} else if (layers == 3) {
						characterModel.setLeftLegEncumbranceValue(3);
					}
					characterModel.setLeftLegArmorStoppingPower(stoppingPower);
					characterView.setLeftLegArmorStoppingPower(String.valueOf(stoppingPower));
					break;
				default:
					break;
				}

			}

			int encumbranceValue = characterModel.getGeneralEncumbranceValue()
					+ characterModel.getHeadEncumbranceValue() + characterModel.getTorsoEncumbranceValue()
					+ characterModel.getRightArmEncumbranceValue() + characterModel.getLeftArmEncumbranceValue()
					+ characterModel.getRightLegEncumbranceValue() + characterModel.getLeftLegEncumbranceValue();
			characterModel.setModifiedReflexesLevel(characterModel.getUnmodifiedReflexesLevel() - encumbranceValue);
			characterView.setModifiedReflexesLevel(
					Integer.toString((characterModel.getUnmodifiedReflexesLevel() - encumbranceValue)));
		}

		private int calculateLayeredStoppingPower(List<Integer> list) {
			list = sortList(list);
			if (list.size() > 0) {
				int stoppingPower = list.get(0);
				for (int i = 1; i < list.size(); i++) {
					int difference = stoppingPower - list.get(i);
					if (0 <= difference && difference <= 4) {
						stoppingPower += 5;
					} else if (5 <= difference && difference <= 8) {
						stoppingPower += 4;
					} else if (9 <= difference && difference <= 14) {
						stoppingPower += 3;
					} else if (15 <= difference && difference <= 20) {
						stoppingPower += 2;
					} else if (21 <= difference && difference <= 26) {
						stoppingPower += 1;
					} else if (27 <= difference) {
						stoppingPower += 0;
					}
				}

				return stoppingPower;
			} else {
				return 0;
			}
		}

		private List<Integer> sortList(List<Integer> list) {
			int length = list.size();
			for (int i = 0; i < length - 1; i++) {
				int minId = i;
				for (int j = i + 1; j < length; j++) {
					if (list.get(i) < list.get(minId)) {
						minId = j;
					}
					int temp = list.get(minId);
					list.set(minId, list.get(i));
					list.set(i, temp);
				}
			}
			return list;
		}

	}

	private static int totalCost = 0;
	private static int totalWeaponCost = 0;
	private static int totalGearCost = 0;
	private static int totalArmorCost = 0;
	private static int totalAmmoCost = 0;

	class StoreWeaponListSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent event) {
			int totalWeaponCost = 0;
			JTable storeWeaponTable = characterView.getStoreWeaponTable();

			if (event.getValueIsAdjusting()) {
				return;
			}
			for (int rowIndex : storeWeaponTable.getSelectedRows()) {
				totalWeaponCost -= (Integer) storeWeaponTable.getValueAt(rowIndex, 10);
			}

			CharacterCreationController.totalWeaponCost = totalWeaponCost;
			totalCost = CharacterCreationController.totalWeaponCost + totalGearCost + totalArmorCost + totalAmmoCost;

			characterView.setTotalCost(totalCost);
		}
	}

	class StoreGearListSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent event) {
			int totalGearCost = 0;
			JTable storeGearTable = characterView.getStoreGearTable();

			if (event.getValueIsAdjusting()) {
				return;
			}
			for (int rowIndex : storeGearTable.getSelectedRows()) {
				totalGearCost -= (Integer) storeGearTable.getValueAt(rowIndex, 1);
			}

			CharacterCreationController.totalGearCost = totalGearCost;
			totalCost = totalWeaponCost + CharacterCreationController.totalGearCost + totalArmorCost + totalAmmoCost;

			characterView.setTotalCost(totalCost);
		}
	}

	class StoreArmorListSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent event) {
			int totalArmorCost = 0;
			JTable storeArmorTable = characterView.getStoreArmorTable();

			if (event.getValueIsAdjusting()) {
				return;
			}
			for (int rowIndex : storeArmorTable.getSelectedRows()) {
				totalArmorCost -= (Integer) storeArmorTable.getValueAt(rowIndex, 6);
			}

			CharacterCreationController.totalArmorCost = totalArmorCost;
			totalCost = totalWeaponCost + totalGearCost + CharacterCreationController.totalArmorCost + totalAmmoCost;

			characterView.setTotalCost(totalCost);
		}
	}

	class StoreAmmoListSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent event) {
			int totalAmmoCost = 0;
			JTable storeAmmoTable = characterView.getStoreAmmoTable();

			if (event.getValueIsAdjusting()) {
				return;
			}
			for (int rowIndex : storeAmmoTable.getSelectedRows()) {
				totalAmmoCost -= (Integer) storeAmmoTable.getValueAt(rowIndex, 3);
			}

			CharacterCreationController.totalAmmoCost = totalAmmoCost;
			totalCost = totalWeaponCost + totalGearCost + totalArmorCost + CharacterCreationController.totalAmmoCost;

			characterView.setTotalCost(totalCost);
		}
	}

	class SelectAllTextFocusListener extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent fe) {
			// Do nothing
		}

		@Override
		public void focusGained(FocusEvent fe) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					JTextField txt = (JTextField) fe.getSource();
					txt.selectAll();
				}
			});
		}
	}

	class TableListSelectionListener implements ListSelectionListener {
		Map<String, JTable> tables;
		Map<String, JTextArea> textAreas;

		public TableListSelectionListener() {
			tables = characterView.getSkillTables();
			textAreas = characterView.getSkillTextAreas();
		}

		@Override
		public void valueChanged(ListSelectionEvent event) {
			ListSelectionModel listSelectionModel = (ListSelectionModel) event.getSource();

			String key = getKeyFromListSelectionModel(listSelectionModel);
			setTextFieldText(listSelectionModel, key);

		}

		private String getKeyFromListSelectionModel(ListSelectionModel listSelectionModel) {
			for (Map.Entry<String, JTable> entry : tables.entrySet()) {
				ListSelectionModel tempSelectionModel = entry.getValue().getSelectionModel();
				if (listSelectionModel.equals(tempSelectionModel)) {
					return entry.getKey();
				}
			}
			return null;
		}

		private void setTextFieldText(ListSelectionModel listSelectionModel, String key) {
			if (!listSelectionModel.isSelectionEmpty()) {
				String categoryCode = key.substring(0, 3);
				int index = listSelectionModel.getLeadSelectionIndex();
				SkillTableModel tableModel = (SkillTableModel) tables.get(key).getModel();
				String skillName = (String) tableModel.getValueAt(index, 0);
				String skillCode = skillName.replace(" ", "_").toLowerCase();
				CharacterCreationModel.Skill skill = characterModel.getSkill(categoryCode + ":" + skillCode);
				textAreas.get(key).setText(skill.getDescription());

			}
		}

	}

	class RandomCharacterPointActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			resetStats();

			for (JSpinner spinner : characterView.getSkillSpinners()) {
				spinner.setEnabled(true);
			}

			Random rnd = new Random();
			int die1 = rnd.nextInt(10) + 1;
			int die2 = rnd.nextInt(10) + 1;
			int die3 = rnd.nextInt(10) + 1;
			int die4 = rnd.nextInt(10) + 1;
			int die5 = rnd.nextInt(10) + 1;
			int die6 = rnd.nextInt(10) + 1;
			int die7 = rnd.nextInt(10) + 1;
			int die8 = rnd.nextInt(10) + 1;
			int die9 = rnd.nextInt(10) + 1;

			int points = die1 + die2 + die3 + die4 + die5 + die6 + die7 + die8 + die9;

			int usedCharacterPoints = characterModel.getIntelligenceLevel()
					+ characterModel.getUnmodifiedReflexesLevel() + characterModel.getTechnicalAbilityLevel()
					+ characterModel.getCoolLevel() + characterModel.getAttractivenessLevel()
					+ characterModel.getLuckLevel() + characterModel.getMovementAllowanceLevel()
					+ characterModel.getBodyLevel() + characterModel.getTotalEmpathyLevel();

			characterModel.setCharacterPoints(points);
			characterView.setCharacterPoints(Integer.toString(points - usedCharacterPoints));
		}
	}

	class FastCharacterPointActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			resetStats();

			for (JSpinner spinner : characterView.getSkillSpinners()) {
				spinner.setEnabled(false);
			}

			JComboBox<Integer>[] comboBoxes = characterView.getStatComboBoxes();
			JLabel[] labels = characterView.getStatComboBoxLabels();
			JPanel row1 = new JPanel();
			JPanel row2 = new JPanel();

			int count = comboBoxes.length == labels.length ? comboBoxes.length : 0;
			for (int i = 0; i < count; i++) {
				if (i < count / 2) {
					row1.add(labels[i]);
					row1.add(comboBoxes[i]);
				} else {
					row2.add(labels[i]);
					row2.add(comboBoxes[i]);
				}

			}

			Object[] array = { row1, row2 };
			int returnVal = JOptionPane.showConfirmDialog(characterView, array, "Distribute Rolled Points",
					JOptionPane.OK_CANCEL_OPTION);

			if (returnVal == JOptionPane.OK_OPTION) {
				int totalPoints = 0;
				for (int i = 0; i < comboBoxes[0].getItemCount(); i++) {
					totalPoints += comboBoxes[0].getItemAt(i);
				}
				characterModel.setCharacterPoints(totalPoints);

				Map<Integer, Integer> expectedCount = calculateExpectedInventory(comboBoxes[0]);

				Map<Integer, Integer> actualCount = calculateActualInventory(comboBoxes);

				if (expectedCount.equals(actualCount)) {
					setDataAndView(comboBoxes);

				} else {
					calculateDifference(expectedCount, actualCount);
				}

			}
		}

		private Map<Integer, Integer> calculateExpectedInventory(JComboBox<Integer> comboBox) {
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			for (int i = 0; i < comboBox.getItemCount(); i++) {
				Integer key = comboBox.getItemAt(i);
				if (map.containsKey(key)) {
					map.put(key, map.get(key) + 1);
				} else {
					map.put(key, 1);
				}
			}
			return map;
		}

		private Map<Integer, Integer> calculateActualInventory(JComboBox<Integer>[] comboBoxes) {
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			int comboBoxCount = characterView.getStatComboBoxes().length;
			for (int i = 0; i < comboBoxCount; i++) {
				Integer key = (Integer) comboBoxes[i].getSelectedItem();
				if (map.containsKey(key)) {
					map.put(key, map.get(key) + 1);
				} else {
					map.put(key, 1);
				}
			}
			return map;
		}

		private void setDataAndView(JComboBox<Integer>[] comboBoxes) {
			characterModel.setIntelligenceLevel((Integer) comboBoxes[0].getSelectedItem());
			characterView.setIntelligenceLevel(comboBoxes[0].getSelectedItem().toString());

			characterModel.setUnmodifiedReflexesLevel((Integer) comboBoxes[1].getSelectedItem());
			characterView.setUnmodifiedReflexesLevel(comboBoxes[1].getSelectedItem().toString());

			characterModel.setTechnicalAbilityLevel((Integer) comboBoxes[2].getSelectedItem());
			characterView.setTechnicalAbilityLevel(comboBoxes[2].getSelectedItem().toString());

			characterModel.setCoolLevel((Integer) comboBoxes[3].getSelectedItem());
			characterView.setCoolLevel(comboBoxes[3].getSelectedItem().toString());

			characterModel.setAttractivenessLevel((Integer) comboBoxes[4].getSelectedItem());
			characterView.setAttractivenessLevel(comboBoxes[4].getSelectedItem().toString());

			characterModel.setLuckLevel((Integer) comboBoxes[5].getSelectedItem());
			characterView.setLuckLevel(comboBoxes[5].getSelectedItem().toString());

			characterModel.setMovementAllowanceLevel((Integer) comboBoxes[6].getSelectedItem());
			characterView.setMovementAllowanceLevel(comboBoxes[6].getSelectedItem().toString());

			characterModel.setBodyLevel((Integer) comboBoxes[7].getSelectedItem());
			characterView.setBodyLevel(comboBoxes[7].getSelectedItem().toString());

			characterModel.setTotalEmpathyLevel((Integer) comboBoxes[8].getSelectedItem());
			characterView.setTotalEmpathyLevel(comboBoxes[8].getSelectedItem().toString());

			int encumbranceValue = characterModel.getGeneralEncumbranceValue()
					+ characterModel.getHeadEncumbranceValue() + characterModel.getTorsoEncumbranceValue()
					+ characterModel.getRightArmEncumbranceValue() + characterModel.getLeftArmEncumbranceValue()
					+ characterModel.getRightLegEncumbranceValue() + characterModel.getLeftLegEncumbranceValue();
			characterModel.setModifiedReflexesLevel(characterModel.getUnmodifiedReflexesLevel() - encumbranceValue);
			characterView.setModifiedReflexesLevel(
					Integer.toString(characterModel.getUnmodifiedReflexesLevel() - encumbranceValue));
		}

		private void calculateDifference(Map<Integer, Integer> expectedCount, Map<Integer, Integer> actualCount) {
			Map<Integer, Integer> countDifference = expectedCount;
			for (Integer key : actualCount.keySet()) {
				int newValue = expectedCount.get(key) - actualCount.get(key);
				countDifference.put(key, newValue);
			}

			String missing = "You are missing: ";
			String additional = "\nYou have an additional: ";

			for (Map.Entry<Integer, Integer> entry : countDifference.entrySet()) {
				if (entry.getValue() < 0) {
					additional += ("(" + Math.abs(entry.getValue()) + ") " + entry.getKey() + ", ");
				} else if (entry.getValue() > 0) {
					missing += ("(" + entry.getValue() + ") " + entry.getKey() + ", ");
				}
			}

			JOptionPane.showMessageDialog(characterView,
					missing + additional + "\nClick 'FAST' again to fix the error.", "Use All Rolled Values",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	class ManualCharacterPointActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			resetStats();

			for (JSpinner spinner : characterView.getSkillSpinners()) {
				spinner.setEnabled(true);
			}

			String characterPoints = JOptionPane.showInputDialog("Enter the given amount of Character Points.");
			try {
				characterModel.setCharacterPoints(Integer.parseInt(characterPoints));
				characterView.setCharacterPoints(String.valueOf(
						(Integer.parseInt(characterPoints) - (CharacterCreationModel.DEFAULT_STAT_LEVEL * 9))));
			} catch (NumberFormatException exception) {
				JOptionPane.showMessageDialog(characterView, "Please enter a number");
			}

		}

	}

	class ChangeCharacterPortrait implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpeg", "jpg", "gif", "tiff",
					"tif", "png");
			chooser.setFileFilter(filter);
			chooser.setAccessory(new ImagePreview(chooser));
			int returnVal = chooser.showOpenDialog(characterView);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				try {
					BufferedImage image = ImageIO.read(file);
					characterView.setCharacterPortrait(image);
				} catch (IOException exception) {
					JOptionPane.showMessageDialog(characterView, "That file does not exist.");
				}

			}

		}

	}

}
