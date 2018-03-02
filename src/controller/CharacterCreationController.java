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
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.apache.commons.codec.binary.Base64;

import components.ImagePreview;
import model.CharacterCreationModel;
import model.CharacterCreationModel.Sibling;
import model.CharacterCreationModel.Sibling.SiblingBuilder;
import view.CharacterCreationView;
import view.CharacterCreationView.EquippedTableModel;
import view.CharacterCreationView.InventoryAmmoTableModel;
import view.CharacterCreationView.InventoryArmorTableModel;
import view.CharacterCreationView.InventoryGearTableModel;
import view.CharacterCreationView.InventoryWeaponTableModel;
import view.CharacterCreationView.SiblingPanel;
import view.CharacterCreationView.SkillTableModel;

public class CharacterCreationController {
	private CharacterCreationModel characterModel;
	private CharacterCreationView characterView;

	public CharacterCreationController(CharacterCreationModel characterModel, CharacterCreationView characterView) {
		this.characterModel = characterModel;
		this.characterView = characterView;

		this.characterView.addHandleDocumentListener(new HandleDocumentListener());
		this.characterView.addRoleChangeListener(new RoleChangeActionListener());
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
		this.characterView.addChangePortraitButtonActionListener(new ChangeCharacterPortraitActionListener());
		this.characterView.addInventoryItemListener(new InventoryItemListener());
		this.characterView.addStoreItemListener(new StoreItemListener());
		this.characterView.addAddToInventoryActionListener(new AddToInventoryActionListener());
		this.characterView.addCalculateActionListener(new AddMoneyActionListener());
		this.characterView.addEquipButtonActionListener(new EquipActionListener());
		this.characterView.addEquippedTableModelListener(new EquippedTableModelListener());
		this.characterView.addUnequipButtonActionListener(new UnequipActionListener());
		this.characterView.addClothingRandomizerActionListener(new RandomizeClothesActionListener());
		this.characterView.addHairstyleRandomizerActionListener(new RandomizeHairstyleActionListener());
		this.characterView.addAffectionsRandomizerActionListener(new RandomizeAffectationsActionListener());
		this.characterView.addEthnicityRandomizerActionListener(new RandomizeEthnicityActionListener());
		this.characterView.addClothingDocumentListener(new ClothingDocumentListener());
		this.characterView.addHairstyleDocumentListener(new HairstyleDocumentListener());
		this.characterView.addAffectationsDocumentListener(new AffectationsDocumentListener());
		this.characterView.addEthnicityItemListener(new EthnicityItemListener());

		this.characterView.addFamilyRankingItemListener(new FamilyRankingItemListener());
		this.characterView.addFamilyRankingRandomizerActionListener(new RandomizeFamilyRankingActionListener());
		this.characterView.addParentsFateItemListener(new ParentsFateItemListener());
		this.characterView.addParentsFateRandomizerActionListener(new RandomizeParentsFateActionListener());
		this.characterView.addParentEventItemListener(new ParentEventItemListener());
		this.characterView.addParentEventRandomizerActionListener(new RandomizeParentEventActionListener());
		this.characterView.addFamilyStatusItemListener(new FamilyStatusItemListener());
		this.characterView.addFamilyStatusRandomizerActionListener(new RandomizeFamilyStatusActionListener());
		this.characterView.addFamilyTragedyItemListener(new FamilyTragedyItemListener());
		this.characterView.addFamilyTragedyRandomizerActionListener(new RandomizeFamilyTragedyActionListener());
		this.characterView.addChildhoodEnvironmentItemListener(new ChildhoodEnvironmentItemListener());
		this.characterView
				.addChildhoodEnvironmentRandomizerActionListener(new RandomizeChildhoodEnvironmentActionListener());
		this.characterView.addSiblingCountItemListener(new SiblingCountItemListener());
		this.characterView.addSiblingAmountRandomizerActionListener(new SiblingCountRandomizerActionListener());

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
				characterModel.getInventoryWeapons().put(type, weapon);

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
				characterModel.getInventoryGear().put(type, gear);

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
				characterModel.getInventoryArmors().put(type, armor);

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
				characterModel.getInventoryAmmos().put(type, ammo);

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

	class ChangeCharacterPortraitActionListener implements ActionListener {

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

			for (int rowIndex : inventoryArmorTable.getSelectedRows()) {
				String type = (String) inventoryArmorTable.getValueAt(rowIndex, ARMOR_TYPE_INDEX);
				CharacterCreationModel.Armor selectedArmor = characterModel.getArmors().get(type);
				boolean hasHardArmor = false;
				boolean hasMaxLayers = false;

				if (!hasHardArmor && selectedArmor.getCovers().contains("Head")) {
					hasHardArmor = hasHardArmor(selectedArmor, EQUIPPED_HEAD_INDEX);
				} else if (!hasHardArmor && selectedArmor.getCovers().contains("Torso")) {
					hasHardArmor = hasHardArmor(selectedArmor, EQUIPPED_TORSO_INDEX);
				} else if (!hasHardArmor && selectedArmor.getCovers().contains("Arms")) {
					hasHardArmor = hasHardArmor(selectedArmor, EQUIPPED_RIGHT_ARM_INDEX);
				} else if (!hasHardArmor && selectedArmor.getCovers().contains("Arms")) {
					hasHardArmor = hasHardArmor(selectedArmor, EQUIPPED_LEFT_ARM_INDEX);
				} else if (!hasHardArmor && selectedArmor.getCovers().contains("Legs")) {
					hasHardArmor = hasHardArmor(selectedArmor, EQUIPPED_RIGHT_LEG_INDEX);
				} else if (!hasHardArmor && selectedArmor.getCovers().contains("Legs")) {
					hasHardArmor = hasHardArmor(selectedArmor, EQUIPPED_LEFT_LEG_INDEX);
				}

				if (!hasMaxLayers && selectedArmor.getCovers().contains("Head")) {
					hasMaxLayers = hasMaxLayers(EQUIPPED_HEAD_INDEX);
				} else if (!hasMaxLayers && selectedArmor.getCovers().contains("Torso")) {
					hasMaxLayers = hasMaxLayers(EQUIPPED_TORSO_INDEX);
				} else if (!hasMaxLayers && selectedArmor.getCovers().contains("Arms")) {
					hasMaxLayers = hasMaxLayers(EQUIPPED_RIGHT_ARM_INDEX);
				} else if (!hasMaxLayers && selectedArmor.getCovers().contains("Arms")) {
					hasMaxLayers = hasMaxLayers(EQUIPPED_LEFT_ARM_INDEX);
				} else if (!hasMaxLayers && selectedArmor.getCovers().contains("Legs")) {
					hasMaxLayers = hasMaxLayers(EQUIPPED_RIGHT_LEG_INDEX);
				} else if (!hasMaxLayers && selectedArmor.getCovers().contains("Legs")) {
					hasMaxLayers = hasMaxLayers(EQUIPPED_LEFT_LEG_INDEX);
				}

				if (!hasHardArmor && !hasMaxLayers) {
					characterModel.setGeneralEncumbranceValue(
							characterModel.getGeneralEncumbranceValue() + selectedArmor.getEncumbranceValue());

					if (selectedArmor.getCovers().contains("Head")) {
						addTypeToEquippedTable(selectedArmor.getType(), EQUIPPED_HEAD_INDEX);
					}

					if (selectedArmor.getCovers().contains("Torso")) {
						addTypeToEquippedTable(selectedArmor.getType(), EQUIPPED_TORSO_INDEX);
					}

					if (selectedArmor.getCovers().contains("Arms")) {
						addTypeToEquippedTable(selectedArmor.getType(), EQUIPPED_RIGHT_ARM_INDEX);
						addTypeToEquippedTable(selectedArmor.getType(), EQUIPPED_LEFT_ARM_INDEX);
					}

					if (selectedArmor.getCovers().contains("Legs")) {
						addTypeToEquippedTable(selectedArmor.getType(), EQUIPPED_RIGHT_LEG_INDEX);
						addTypeToEquippedTable(selectedArmor.getType(), EQUIPPED_LEFT_LEG_INDEX);
					}
				}

			}
		}

		private boolean hasHardArmor(CharacterCreationModel.Armor selectedArmor, int column) {
			JTable equippedTable = characterView.getEquippedTable();
			String selectedArmorArmorClass = selectedArmor.getArmorClass();
			CharacterCreationModel.Armor tempArmor;
			String tempArmorArmorClass;

			for (int i = 0; i < equippedTable.getRowCount(); i++) {
				String tempArmorType = (String) equippedTable.getValueAt(i, column);
				if (!tempArmorType.equals("")) {
					tempArmor = characterModel.getArmors().get(tempArmorType);
					tempArmorArmorClass = tempArmor.getArmorClass();
					if (selectedArmorArmorClass.equals("Hard Armor") && tempArmorArmorClass.equals("Hard Armor")) {
						JOptionPane.showMessageDialog(characterView, "You cannot equip " + tempArmor.getType() + " and "
								+ selectedArmor.getType() + " because they are both Hard Armor Class.");
						return true;
					}
				}
			}
			return false;
		}

		private boolean hasMaxLayers(int column) {
			JTable equippedTable = characterView.getEquippedTable();
			int filledSlots = 0;
			for (int i = 0; i < equippedTable.getRowCount(); i++) {
				String equippedArmorType = (String) equippedTable.getValueAt(i, column);
				if (!equippedArmorType.equals("")) {
					filledSlots++;
				}
			}

			if (filledSlots >= MAX_ARMOR_LAYERS) {
				JOptionPane.showMessageDialog(characterView,
						"You cannot equip anymore armor for your " + equippedTable.getColumnName(column));
				return true;
			} else {
				return false;
			}
		}

		private void addTypeToEquippedTable(String armorType, int column) {
			JTable equippedTable = characterView.getEquippedTable();
			for (int i = 0; i < equippedTable.getRowCount(); i++) {
				if (equippedTable.getValueAt(i, column).equals("")) {
					equippedTable.setValueAt(armorType, i, column);
					break;
				}
			}
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
				fw.write("  handle=" + formatStringValue(characterModel.getHandle()) + ",\n" //
						+ "  role=" + formatStringValue(characterModel.getRole().getRoleName()) + ",\n" //
						+ "  character_points=" + characterModel.getCharacterPoints() + ",\n" //
						+ "  attributes={\n" //
						+ "    intelligence=" + characterModel.getIntelligenceLevel() + ",\n" //
						+ "    unmodified_reflexes=" + characterModel.getUnmodifiedReflexesLevel() + ",\n" //
						+ "    modified_reflexes=" + characterModel.getModifiedReflexesLevel() + ",\n" //
						+ "    technical_ability=" + characterModel.getTechnicalAbilityLevel() + ",\n" //
						+ "    cool=" + characterModel.getCoolLevel() + ", \n" //
						+ "    attractiveness=" + characterModel.getAttractivenessLevel() + ",\n" //
						+ "    luck=" + characterModel.getLuckLevel() + ",\n" //
						+ "    movement_allowance=" + characterModel.getMovementAllowanceLevel() + ",\n" //
						+ "    body=" + characterModel.getBodyLevel() + ",\n" //
						+ "    current_empathy=" + characterModel.getCurrentEmpathyLevel() + ",\n" //
						+ "    total_empathy=" + characterModel.getTotalEmpathyLevel() + ",\n" //
						+ "    run=" + characterModel.getRunLevel() + ",\n" //
						+ "    leap=" + characterModel.getLeapLevel() + ",\n" //
						+ "    lift=" + characterModel.getLiftCapacity() + ",\n" //
						+ "    carry=" + characterModel.getCarryCapacity() + "\n" //
						+ "  },\n" //
						+ "  armor_stopping_power={\n" //
						+ "    head_armor_sp=" + characterModel.getHeadArmorStoppingPower() + ",\n" //
						+ "    torso_armor_sp=" + characterModel.getTorsoArmorStoppingPower() + ",\n" //
						+ "    right_arm_armor_sp=" + characterModel.getRightArmArmorStoppingPower() + ",\n" //
						+ "    left_arm_armor_sp=" + characterModel.getLeftArmArmorStoppingPower() + ",\n" //
						+ "    right_leg_armor_sp=" + characterModel.getRightLegArmorStoppingPower() + ",\n" //
						+ "    left_leg_armor_sp=" + characterModel.getLeftLegArmorStoppingPower() + "\n" //
						+ "  },\n" //
						+ "  save_modifier=" + characterModel.getSaveModifier() + ",\n" //
						+ "  body_type_modifier=" + characterModel.getBodyTypeModifier() + ",\n" //
						+ "  injury_points=" + characterModel.getInjuryPoints() + ",\n" //
						+ "  skills={\n" //
						+ convertSkillsDataToString(characterModel.getSkillCatelog()) //
						+ "  },\n" //
						+ "  inventory={\n" //
						+ convertInventoryDataToString(characterModel.getInventoryWeapons(),
								characterModel.getInventoryAmmos(), characterModel.getInventoryArmors(),
								characterModel.getInventoryGear()) //
						+ "  },\n" //
						+ "  equipped_armor={\n" //
						+ convertEquipmentDataToString(characterView.getEquippedTable()) //
						+ "  },\n" //
						+ "  style={\n" //
						+ "    hairstyle=" + formatStringValue(characterModel.getHairstyle()) + ",\n" //
						+ "    clothing=" + formatStringValue(characterModel.getClothing()) + ",\n" //
						+ "    affectations=" + formatStringValue(characterModel.getAffectations()) + ",\n" //
						+ "    ethnicity=" + formatStringValue(characterModel.getEthnicity()) + "\n" //
						+ "  },\n" //
						+ "  family={\n" //
						+ "    family_rank=" + formatStringValue(characterModel.getFamilyRanking()) + "\n" //
						+ "    parents_fate=" + formatStringValue(characterModel.getParentsFate()) + "\n" //
						+ "    parents_event=" + formatStringValue(characterModel.getParentEvent()) + "\n" //
						+ "    family_status=" + formatStringValue(characterModel.getFamilyStatus()) + "\n" //
						+ "    family_tragedy=" + formatStringValue(characterModel.getFamilyTragedy()) + "\n" //
						+ "    childhood_environment=" + formatStringValue(characterModel.getChildhoodEnvironment())
						+ "\n" //
						+ "    sibling_number=" + formatStringValue(characterModel.getSiblingCount()) + "\n" //
						+ "    siblings={\n" //
						+ convertSiblingDataToString(characterModel.getSiblingCount(), characterModel.getSiblings()) //
						+ "    }\n" //
						+ "  },\n" //
						+ "  portrait=" + convertImageToBase64(characterView.getCharacterPortrait()) + "\n");

				fw.close();
			} catch (IOException exception) {
				System.err.println("File does not exist.");
			}
		}

		private String convertSkillsDataToString(
				Map<String, Map<String, CharacterCreationModel.Skill>> skillCatelogMap) {
			String skillData = "";
			for (Map.Entry<String, Map<String, CharacterCreationModel.Skill>> skillCategoryEntry : skillCatelogMap
					.entrySet()) {
				String skillCategory = skillCategoryEntry.getKey();
				skillCategory = skillCategory.replaceAll(" ", "_").toLowerCase();
				skillData += "    " + skillCategory + "={\n";

				Map<String, CharacterCreationModel.Skill> skills = skillCategoryEntry.getValue();
				for (Map.Entry<String, CharacterCreationModel.Skill> skillEntry : skills.entrySet()) {
					String skillTag = skillEntry.getKey();
					skillTag = skillTag.replaceAll(" ", "_").toLowerCase();

					CharacterCreationModel.Skill skill = skillEntry.getValue();
					String skillType = skill.getType();
					int skillRank = skill.getRank();
					String specifiedSkill = skill.getSpecifiedSkill();

					skillData += "      " + skillTag + "={\n" //
							+ "        skill_type=" + formatStringValue(skillType) + ",\n" //
							+ "        specified_skill=" + formatStringValue(specifiedSkill) + ",\n" //
							+ "        skill_rank=" + skillRank + "\n" //
							+ "      },\n";
				}
				skillData += "    },\n";
			}

			skillData = skillData.substring(0, skillData.length() - 2) + "\n";
			return skillData;
		}

		private String convertInventoryDataToString(Map<String, CharacterCreationModel.Weapon> inventoryWeaponMap,
				Map<String, CharacterCreationModel.Ammo> inventoryAmmoMap,
				Map<String, CharacterCreationModel.Armor> inventoryArmorMap,
				Map<String, CharacterCreationModel.Gear> inventoryGearMap) {
			String inventoryData = "";

			inventoryData += "    weapons={\n";
			for (Map.Entry<String, CharacterCreationModel.Weapon> weaponEntry : inventoryWeaponMap.entrySet()) {
				String weaponTag = weaponEntry.getKey();
				weaponTag = weaponTag.replaceAll(" ", "_").toLowerCase();

				CharacterCreationModel.Weapon weapon = weaponEntry.getValue();
				String weaponName = weapon.getName();

				inventoryData += "      " + weaponTag + "={\n" //
						+ "        weapon_name=" + formatStringValue(weaponName) + "\n" //
						+ "      },\n";
			}
			if (inventoryWeaponMap.size() > 0) {
				inventoryData = inventoryData.substring(0, inventoryData.length() - 2) + "\n";
			}
			inventoryData += "    },\n";

			inventoryData += "    ammo={\n";
			for (Map.Entry<String, CharacterCreationModel.Ammo> ammoEntry : inventoryAmmoMap.entrySet()) {
				String ammoTag = ammoEntry.getKey();
				ammoTag = ammoTag.replaceAll(" ", "_").toLowerCase();

				CharacterCreationModel.Ammo ammo = ammoEntry.getValue();
				String ammoType = ammo.getType();
				int ammoQuantity = ammo.getQuantity();

				inventoryData += "      " + ammoTag + "={\n" //
						+ "        ammo_type=" + formatStringValue(ammoType) + ",\n" //
						+ "        quantity=" + ammoQuantity + "\n" //
						+ "      },\n";
			}
			if (inventoryAmmoMap.size() > 0) {
				inventoryData = inventoryData.substring(0, inventoryData.length() - 2) + "\n";
			}
			inventoryData += "    },\n";

			inventoryData += "    armor={\n";
			for (Map.Entry<String, CharacterCreationModel.Armor> armorEntry : inventoryArmorMap.entrySet()) {
				String armorTag = armorEntry.getKey();
				armorTag = armorTag.replaceAll(" ", "_").toLowerCase();

				CharacterCreationModel.Armor armor = armorEntry.getValue();
				String armorType = armor.getType();

				inventoryData += "      " + armorTag + "={\n" //
						+ "        armor_type=" + formatStringValue(armorType) + "\n" //
						+ "      },\n";
			}
			if (inventoryArmorMap.size() > 0) {
				inventoryData = inventoryData.substring(0, inventoryData.length() - 2) + "\n";
			}

			inventoryData += "    },\n";

			inventoryData += "    gear={\n";
			for (Map.Entry<String, CharacterCreationModel.Gear> gearEntry : inventoryGearMap.entrySet()) {
				String gearTag = gearEntry.getKey();
				gearTag = gearTag.replaceAll(" ", "_").toLowerCase();

				CharacterCreationModel.Gear gear = gearEntry.getValue();
				String gearType = gear.getType();
				int gearQuantity = gear.getQuantity();

				inventoryData += "      " + gearTag + "={\n" //
						+ "        gear_type=" + formatStringValue(gearType) + ",\n" //
						+ "        quantity=" + gearQuantity + ",\n" //
						+ "      },\n";
			}
			if (inventoryGearMap.size() > 0) {
				inventoryData = inventoryData.substring(0, inventoryData.length() - 2) + "\n";
			}
			inventoryData += "    }\n";

			return inventoryData;
		}

		private String convertEquipmentDataToString(JTable table) {
			String equipmentData = "";

			equipmentData += "    head={\n" //
					+ "      layer1=" + formatStringValue((String) table.getValueAt(0, 0)) + ",\n" //
					+ "      layer2=" + formatStringValue((String) table.getValueAt(1, 0)) + ",\n" //
					+ "      layer3=" + formatStringValue((String) table.getValueAt(2, 0)) + "\n" //
					+ "    },\n" //
					+ "    torso={\n" //
					+ "      layer1=" + formatStringValue((String) table.getValueAt(0, 1)) + ",\n" //
					+ "      layer2=" + formatStringValue((String) table.getValueAt(1, 1)) + ",\n" //
					+ "      layer3=" + formatStringValue((String) table.getValueAt(2, 1)) + "\n" //
					+ "    },\n" //
					+ "    right_arm={\n" //
					+ "      layer1=" + formatStringValue((String) table.getValueAt(0, 2)) + ",\n" //
					+ "      layer2=" + formatStringValue((String) table.getValueAt(1, 2)) + ",\n" //
					+ "      layer3=" + formatStringValue((String) table.getValueAt(2, 2)) + "\n" //
					+ "    },\n" //
					+ "    left_arm={\n" //
					+ "      layer1=" + formatStringValue((String) table.getValueAt(0, 3)) + ",\n" //
					+ "      layer2=" + formatStringValue((String) table.getValueAt(1, 3)) + ",\n" //
					+ "      layer3=" + formatStringValue((String) table.getValueAt(2, 3)) + "\n" //
					+ "    },\n" //
					+ "    right_leg={\n" //
					+ "      layer1=" + formatStringValue((String) table.getValueAt(0, 4)) + ",\n" //
					+ "      layer2=" + formatStringValue((String) table.getValueAt(1, 4)) + ",\n" //
					+ "      layer3=" + formatStringValue((String) table.getValueAt(2, 4)) + "\n" //
					+ "    },\n" //
					+ "    left_leg={\n" //
					+ "      layer1=" + formatStringValue((String) table.getValueAt(0, 5)) + ",\n" //
					+ "      layer2=" + formatStringValue((String) table.getValueAt(1, 5)) + ",\n" //
					+ "      layer3=" + formatStringValue((String) table.getValueAt(2, 5)) + "\n" //
					+ "    }\n";

			return equipmentData;
		}

		private String convertSiblingDataToString(String siblingCount, List<CharacterCreationModel.Sibling> siblings) {
			String siblingData = "";
			try {
				Integer count = Integer.valueOf(siblingCount);
				for (int i = 0; i < count; i++) {
					CharacterCreationModel.Sibling sibling = siblings.get(i);
					if (!sibling.equals(null)) {
						siblingData += "      sibling_" + i + "={\n" //
								+ "        name=" + formatStringValue(sibling.getName()) + ",\n" //
								+ "        gender=" + formatStringValue(sibling.getGender()) + ",\n" //
								+ "        relative_age=" + formatStringValue(sibling.getAge()) + ",\n" //
								+ "        relationship=" + formatStringValue(sibling.getRelationship()) + "\n" //
								+ "      },\n";
					}
				}
				if (siblings.size() > 0) {
					siblingData = siblingData.substring(0, siblingData.length() - 2) + "\n";
				}
			} catch (NumberFormatException exception) {
				// do nothing
			}
			return siblingData;
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

		private String formatStringValue(String value) {
			if (value == null) {
				return value;
			} else {
				return "\"" + value + "\"";
			}
		}
	}

	class LoadCharacterActionListener implements ActionListener {
		private String line;
		private String[] dataPairStrings;
		private String key;
		private String value;
		private Scanner sc;

		private InventoryWeaponTableModel inventoryWeaponModel;
		private Map<String, CharacterCreationModel.Weapon> inventoryWeaponMap;
		private Map<String, CharacterCreationModel.Weapon> weaponMap;
		private InventoryGearTableModel inventoryGearModel;
		private Map<String, CharacterCreationModel.Gear> inventoryGearMap;
		private Map<String, CharacterCreationModel.Gear> gearMap;
		private InventoryAmmoTableModel inventoryAmmoModel;
		private Map<String, CharacterCreationModel.Ammo> inventoryAmmoMap;
		private Map<String, CharacterCreationModel.Ammo> ammoMap;
		private InventoryArmorTableModel inventoryArmorModel;
		private Map<String, CharacterCreationModel.Armor> inventoryArmorMap;
		private Map<String, CharacterCreationModel.Armor> armorMap;
		private EquippedTableModel equippedTableModel;

		public LoadCharacterActionListener() {
			inventoryWeaponModel = (InventoryWeaponTableModel) characterView.getInventoryWeaponTable().getModel();
			inventoryWeaponMap = characterModel.getInventoryWeapons();
			weaponMap = characterModel.getWeapons();
			inventoryGearModel = (InventoryGearTableModel) characterView.getInventoryGearTable().getModel();
			inventoryGearMap = characterModel.getInventoryGear();
			gearMap = characterModel.getGear();
			inventoryAmmoModel = (InventoryAmmoTableModel) characterView.getInventoryAmmoTable().getModel();
			inventoryAmmoMap = characterModel.getInventoryAmmos();
			ammoMap = characterModel.getAmmos();
			inventoryArmorModel = (InventoryArmorTableModel) characterView.getInventoryArmorTable().getModel();
			inventoryArmorMap = characterModel.getInventoryArmors();
			armorMap = characterModel.getArmors();
			equippedTableModel = (EquippedTableModel) characterView.getEquippedTable().getModel();
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			int returnVal = characterView.getFileChooser().showOpenDialog(characterView);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File originalFile = characterView.getFileChooser().getSelectedFile();
				File formattedFile = new File(originalFile.toString() + ".char");
				try {
					if (originalFile.toString().endsWith(".char")) {
						sc = new Scanner(originalFile);
					} else {
						sc = new Scanner(formattedFile);
					}

					clearInventory();

					while (sc.hasNextLine()) {
						try {
							line = sc.nextLine();
							dataPairStrings = line.split("=");
							if (line.contains("=")) {
								key = dataPairStrings[0].trim();
								value = dataPairStrings[1].replace(",", "").trim();
							}
							switch (key) {
							case "handle":
								characterView.setHandle(value.replace("\"", ""));
								characterModel.setCharacterName(value.replace("\"", ""));
								break;
							case "role":
								characterView.setRole(value.replace("\"", ""));
								characterModel.setRole(value.replace("\"", ""));
								break;
							case "character_points":
								characterView.setCharacterPoints(value);
								characterModel.setCharacterPoints(Integer.parseInt(value));
								break;
							case "attributes":
								parseAttributes();
								break;
							case "armor_stopping_power":
								parseArmorStoppingPower();
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
							case "skills":
								parseSkillCategory();
								break;
							case "inventory":
								parseInventory();
								break;
							case "equipped_armor":
								parseEquippedArmor();
								break;
							case "style":
								parseStyle();
								break;
							case "family":
								parseFamily();
								break;
							case "portrait":
								convertBase64ToImage(value);
								break;
							default:
								break;
							}
						} catch (ArrayIndexOutOfBoundsException exception) {
							exception.printStackTrace();
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

		private void clearInventory() {
			inventoryWeaponModel.clear();
			inventoryWeaponMap.clear();
			inventoryGearModel.clear();
			inventoryGearMap.clear();
			inventoryAmmoModel.clear();
			inventoryAmmoMap.clear();
			inventoryArmorModel.clear();
			inventoryArmorMap.clear();
			equippedTableModel.clear();
		}

		private void parseAttributes() {
			line = sc.next();
			while (line.contains("=")) {
				dataPairStrings = line.split("=");
				key = dataPairStrings[0].trim();
				value = dataPairStrings[1].replace(",", "").trim();
				switch (key) {
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
				}
				line = sc.next();
			}
		}

		private void parseArmorStoppingPower() {
			line = sc.nextLine();
			while (line.contains("=")) {
				dataPairStrings = line.split("=");
				key = dataPairStrings[0].trim();
				value = dataPairStrings[1].replace(",", "").trim();
				switch (key) {
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
				}
				line = sc.nextLine();
			}
		}

		private void parseSkillCategory() {
			line = sc.nextLine();
			while (line.contains("=")) {
				dataPairStrings = line.split("=");
				key = dataPairStrings[0].trim();
				value = dataPairStrings[1].replace(",", "").trim();

				Map<String, CharacterCreationModel.Skill> targetCategory;
				switch (key) {
				case "special_abilities":
					targetCategory = characterModel.getSkillCatelog().get(CharacterCreationModel.SPEC);
					break;
				case "attractiveness":
					targetCategory = characterModel.getSkillCatelog().get(CharacterCreationModel.ATT);
					break;
				case "body":
					targetCategory = characterModel.getSkillCatelog().get(CharacterCreationModel.BOD);
					break;
				case "cool":
					targetCategory = characterModel.getSkillCatelog().get(CharacterCreationModel.CL);
					break;
				case "empathy":
					targetCategory = characterModel.getSkillCatelog().get(CharacterCreationModel.EMP);
					break;
				case "intelligence":
					targetCategory = characterModel.getSkillCatelog().get(CharacterCreationModel.INT);
					break;
				case "reflexes":
					targetCategory = characterModel.getSkillCatelog().get(CharacterCreationModel.REF);
					break;
				case "technical_ability":
					targetCategory = characterModel.getSkillCatelog().get(CharacterCreationModel.TECH);
					break;
				default:
					targetCategory = null;
					break;
				}
				parseSkills(targetCategory);
				line = sc.nextLine();
			}
		}

		private void parseSkills(Map<String, CharacterCreationModel.Skill> targetCategory) {
			line = sc.nextLine();
			while (line.contains("=")) {
				dataPairStrings = line.split("=");
				key = dataPairStrings[0].trim();
				value = dataPairStrings[1].replace(",", "").trim();
				for (CharacterCreationModel.Skill skill : targetCategory.values()) {
					String formattedSkillName = skill.getSkillName().replace(" ", "_").toLowerCase();
					if (key.equals(formattedSkillName)) {
						parseSkillData(skill);
						line = sc.nextLine();
						break;
					}
				}
			}
		}

		private void parseSkillData(CharacterCreationModel.Skill skill) {
			line = sc.nextLine();
			while (line.contains("=")) {
				dataPairStrings = line.split("=");
				key = dataPairStrings[0].trim();
				value = dataPairStrings[1].replace(",", "").trim();
				switch (key) {
				case "specified_skill":
					skill.setSpecifiedSkill(value);
					break;
				case "skill_rank":
					skill.setRank(Integer.parseInt(value));
					break;
				default:
					break;
				}
				line = sc.nextLine();
			}
		}

		private void parseInventory() {
			line = sc.nextLine();
			while (line.contains("=")) {
				dataPairStrings = line.split("=");
				key = dataPairStrings[0].trim();
				value = dataPairStrings[1].replace(",", "").trim();
				switch (key) {
				case "weapons":
					parseWeapons();
					break;
				case "gear":
					parseGear();
					break;
				case "ammo":
					parseAmmo();
					break;
				case "armor":
					parseArmor();
					break;
				default:
					break;
				}

				line = sc.nextLine();
			}
		}

		private void parseWeapons() {
			line = sc.nextLine();
			while (line.contains("=")) {
				dataPairStrings = line.split("=");
				key = dataPairStrings[0].trim();
				value = dataPairStrings[1].replace(",", "").trim();
				parseThroughWeaponData();
				line = sc.nextLine();
			}
		}

		private void parseThroughWeaponData() {
			line = sc.nextLine();
			while (line.contains("=")) {
				dataPairStrings = line.split("=");
				key = dataPairStrings[0].trim();
				value = dataPairStrings[1].replace(",", "").trim();
				switch (key) {
				case "weapon_name":
					value = value.replace("\"", "");
					CharacterCreationModel.Weapon weapon = weaponMap.get(value.replace("\"", ""));
					inventoryWeaponMap.put(weapon.getName(), weapon);

					Object[] row = new Object[] { weapon.getName(), weapon.getCategory(), weapon.getAccuracy(),
							weapon.getConcealability(), weapon.getDamageAndAmmo(), weapon.getNumberOfShots(),
							weapon.getRateOfFire(), weapon.getReliability(), weapon.getRange() };
					inventoryWeaponModel.addRow(row);
					characterView.getInventoryWeaponTable().revalidate();
					characterView.getInventoryWeaponTable().repaint();
					break;
				default:
					break;
				}
				line = sc.nextLine();
			}
		}

		private void parseGear() {
			line = sc.nextLine();
			while (line.contains("=")) {
				dataPairStrings = line.split("=");
				key = dataPairStrings[0].trim();
				value = dataPairStrings[1].replace(",", "").trim();
				parseThroughGearData();
				line = sc.nextLine();
			}
		}

		private void parseThroughGearData() {
			String gearType = "";
			int gearQuantity = 0;
			line = sc.nextLine();
			while (line.contains("=")) {
				dataPairStrings = line.split("=");
				key = dataPairStrings[0].trim();
				value = dataPairStrings[1].replace(",", "").trim();
				switch (key) {
				case "gear_type":
					gearType = value.replace("\"", "");
					break;
				case "quantity":
					gearQuantity = Integer.parseInt(value);
					break;
				default:
					break;
				}
				line = sc.nextLine();
			}
			CharacterCreationModel.Gear gear = gearMap.get(gearType);
			gear.setQuantity(gearQuantity);
			inventoryGearMap.put(gear.getType(), gear);

			Object[] row = new Object[] { gear.getType(), gear.getQuantity(), (gear.getWeight() * gear.getQuantity()) };
			inventoryGearModel.addRow(row);
			characterView.getInventoryGearTable().revalidate();
			characterView.getInventoryGearTable().repaint();
		}

		private void parseAmmo() {
			line = sc.nextLine();
			while (line.contains("=")) {
				dataPairStrings = line.split("=");
				key = dataPairStrings[0].trim();
				value = dataPairStrings[1].replace(",", "").trim();
				parseThroughAmmoData();
				line = sc.nextLine();
			}
		}

		private void parseThroughAmmoData() {
			String ammoType = "";
			int ammoQuantity = 0;
			line = sc.nextLine();
			while (line.contains("=")) {
				dataPairStrings = line.split("=");
				key = dataPairStrings[0].trim();
				value = dataPairStrings[1].replace(",", "").trim();
				switch (key) {
				case "ammo_type":
					ammoType = value.replace("\"", "");
					break;
				case "quantity":
					ammoQuantity = Integer.parseInt(value);
					break;
				default:
					break;
				}
				line = sc.nextLine();
			}
			CharacterCreationModel.Ammo ammo = ammoMap.get(ammoType);
			ammo.setQuantity(ammoQuantity);
			inventoryAmmoMap.put(ammo.getType(), ammo);

			Object[] row = new Object[] { ammo.getType(), ammo.getQuantity(), ammo.isCaseless(),
					((ammo.getQuantity() / ammo.getQuantityPerBox()) * ammo.getWeight()) };
			inventoryAmmoModel.addRow(row);

			characterView.getInventoryAmmoTable().revalidate();
			characterView.getInventoryAmmoTable().repaint();
		}

		private void parseArmor() {
			line = sc.nextLine();
			while (line.contains("=")) {
				dataPairStrings = line.split("=");
				key = dataPairStrings[0].trim();
				value = dataPairStrings[1].replace(",", "").trim();
				parseArmorData();
				line = sc.nextLine();
			}
		}

		private void parseArmorData() {
			line = sc.nextLine();
			while (line.contains("=")) {
				dataPairStrings = line.split("=");
				key = dataPairStrings[0].trim();
				value = dataPairStrings[1].replace(",", "").trim();
				switch (key) {
				case "armor_type":
					CharacterCreationModel.Armor armor = armorMap.get(value.replace("\"", ""));
					inventoryArmorMap.put(armor.getType(), armor);

					Object[] row = new Object[] { armor.getType(), armor.getArmorClass(), armor.getCovers(),
							armor.getStoppingPower(), armor.getEncumbranceValue(), armor.getWeight() };
					inventoryArmorModel.addRow(row);
					characterView.getInventoryArmorTable().revalidate();
					characterView.getInventoryArmorTable().repaint();
					break;
				default:
					break;
				}
				line = sc.nextLine();
			}
		}

		private void parseEquippedArmor() {
			line = sc.nextLine();
			while (line.contains("=")) {
				dataPairStrings = line.split("=");
				key = dataPairStrings[0].trim();
				value = dataPairStrings[1].replace(",", "").trim();
				parseEquippedArmorLayers();
				line = sc.nextLine();
			}
		}

		private void parseEquippedArmorLayers() {
			int equipmentRow = -1;
			int equipmentColumn = -1;
			switch (key) {
			case "head":
				equipmentColumn = 0;
				break;
			case "torso":
				equipmentColumn = 1;
				break;
			case "right_arm":
				equipmentColumn = 2;
				break;
			case "left_arm":
				equipmentColumn = 3;
				break;
			case "right_leg":
				equipmentColumn = 4;
				break;
			case "left_leg":
				equipmentColumn = 5;
				break;
			default:
				break;
			}

			line = sc.nextLine();
			while (line.contains("=")) {
				dataPairStrings = line.split("=");
				key = dataPairStrings[0].trim();
				value = dataPairStrings[1].replace(",", "").trim();
				switch (key) {
				case "layer1":
					equipmentRow = 0;
					equippedTableModel.setValueAt(value.replace("\"", ""), equipmentRow, equipmentColumn);
					break;
				case "layer2":
					equipmentRow = 1;
					equippedTableModel.setValueAt(value.replace("\"", ""), equipmentRow, equipmentColumn);
					break;
				case "layer3":
					equipmentRow = 2;
					equippedTableModel.setValueAt(value.replace("\"", ""), equipmentRow, equipmentColumn);
					break;
				default:
					break;
				}
				line = sc.nextLine();
			}
		}

		private void parseStyle() {
			line = sc.nextLine();
			while (line.contains("=")) {
				dataPairStrings = line.split("=");
				key = dataPairStrings[0].trim();
				value = dataPairStrings[1].replace(",", "").trim();
				switch (key) {
				case "hairstyle":
					characterModel.setHairstyle(value);
					characterView.setHairstyle(value);
					break;
				case "clothing":
					characterModel.setClothing(value);
					characterView.setClothing(value);
					break;
				case "affectations":
					characterModel.setAffectations(value);
					characterView.setAffectations(value);
					break;
				case "ethnicity":
					characterModel.setEthnicity(value);
					characterView.setEthnicity(value);
					break;
				default:
					break;
				}
				line = sc.nextLine();
			}
		}

		private void parseFamily() {
			line = sc.nextLine();
			while (line.contains("=")) {
				dataPairStrings = line.split("=");
				key = dataPairStrings[0].trim();
				value = dataPairStrings[1].replace(",", "").trim();
				switch (key) {
				case "family_rank":
					characterModel.setFamilyRanking(value);
					characterView.setFamilyRanking(value);
					break;
				case "parents_fate":
					characterModel.setParentsFate(value);
					characterView.setParentsFate(value);
					break;
				case "parents_event":
					characterModel.setParentEvent(value);
					characterView.setParentEvent(value);
					break;
				case "family_status":
					characterModel.setFamilyStatus(value);
					characterView.setFamilyStatus(value);
					break;
				case "family_tragedy":
					characterModel.setFamilyTragedy(value);
					characterView.setFamilyTragedy(value);
					break;
				case "childhood_environment":
					characterModel.setChildhoodEnvironment(value);
					characterView.setChildhoodEnvironment(value);
					break;
				case "sibling_number":
					characterModel.setSiblingCount(value);
					characterView.setSiblingCount(value);
					break;
				case "siblings":
					parseSiblings();
					break;
				default:
					break;
				}
				line = sc.nextLine();
			}
		}

		private void parseSiblings() {
			line = sc.nextLine();
			for (int i = 0; line.contains("="); i++) {
				dataPairStrings = line.split("=");
				key = dataPairStrings[0].trim();
				value = dataPairStrings[1].replace(",", "").trim();
				parseSiblingData(i);
				line = sc.nextLine();
			}
		}

		private void parseSiblingData(int siblingIndex) {
			CharacterCreationModel.Sibling sibling = characterModel.getSiblings().get(siblingIndex);
			CharacterCreationView.SiblingPanel siblingPanel = characterView.getSiblingPanels().get(siblingIndex);
			line = sc.nextLine();
			while (line.contains("=")) {
				dataPairStrings = line.split("=");
				key = dataPairStrings[0].trim();
				value = dataPairStrings[1].replace(",", "").trim();
				switch (key) {
				case "name":
					sibling.setSiblingName(value);
					siblingPanel.setSiblingName(value);
					break;
				case "gender":
					sibling.setGender(value);
					siblingPanel.setGender(value);
					break;
				case "relative_age":
					sibling.setAge(value);
					siblingPanel.setAge(value);
					break;
				case "relationship":
					sibling.setRelationship(value);
					siblingPanel.setRelationship(value);
					break;
				}
				line = sc.nextLine();
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

	class RandomizeAffectationsActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			Random rnd = new Random();
			int seed = rnd.nextInt(10) + 1;
			String affectations = "";
			switch (seed) {
			case 1:
				affectations = "Tatoos";
				break;
			case 2:
				affectations = "Mirrorshades";
				break;
			case 3:
				affectations = "Ritual Scars";
				break;
			case 4:
				affectations = "Spiked gloves";
				break;
			case 5:
				affectations = "Nose Rings";
				break;
			case 6:
				affectations = "Earrings";
				break;
			case 7:
				affectations = "Long fingernails";
				break;
			case 8:
				affectations = "Spike heeled boots";
				break;
			case 9:
				affectations = "Weird Contact Lenses";
				break;
			case 10:
				affectations = "Fingerless gloves";
				break;
			default:
				break;
			}

			characterView.setAffectations(affectations);
			characterModel.setAffectations(affectations);
		}
	}

	class RandomizeClothesActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			Random rnd = new Random();
			int seed = rnd.nextInt(10) + 1;
			String clothing = "";
			switch (seed) {
			case 1:
				clothing = "Biker leathers";
				break;
			case 2:
				clothing = "Blue jeans";
				break;
			case 3:
				clothing = "Corporate Suits";
				break;
			case 4:
				clothing = "Jumpsuits";
				break;
			case 5:
				clothing = "Miniskirts";
				break;
			case 6:
				clothing = "High Fashion";
				break;
			case 7:
				clothing = "Cammos";
				break;
			case 8:
				clothing = "Normal clothes";
				break;
			case 9:
				clothing = "Nude";
				break;
			case 10:
				clothing = "Bag Lady chic";
				break;
			default:
				break;
			}

			characterView.setClothing(clothing);
			characterModel.setClothing(clothing);
		}
	}

	class RandomizeHairstyleActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			Random rnd = new Random();
			int seed = rnd.nextInt(10) + 1;
			String hairstyle = "";
			switch (seed) {
			case 1:
				hairstyle = "Mohawk";
				break;
			case 2:
				hairstyle = "Long & Ratty";
				break;
			case 3:
				hairstyle = "Short & Spiked";
				break;
			case 4:
				hairstyle = "Wild & all over";
				break;
			case 5:
				hairstyle = "Bald";
				break;
			case 6:
				hairstyle = "Striped";
				break;
			case 7:
				hairstyle = "Tinted";
				break;
			case 8:
				hairstyle = "Neat, short";
				break;
			case 9:
				hairstyle = "Short, curly";
				break;
			case 10:
				hairstyle = "Long, straight";
				break;
			default:
				break;
			}

			characterView.setHairstyle(hairstyle);
			characterModel.setHairstyle(hairstyle);
		}
	}

	class RandomizeEthnicityActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			Random rnd = new Random();
			int seed = rnd.nextInt(10);
			String ethnicity = characterView.getEthnicityComboBox().getItemAt(seed);

			characterView.setEthnicity(ethnicity);
			characterModel.setEthnicity(ethnicity);
		}
	}

	class FamilyRankingItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
			String familyRanking = (String) comboBox.getSelectedItem();
			characterModel.setFamilyRanking(familyRanking);
		}

	}

	class RandomizeFamilyRankingActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			Random rnd = new Random();
			int seed = (rnd.nextInt(10) + 1);
			String familyRanking = characterView.getFamilyRankingComboBox().getItemAt(seed);

			characterView.setFamilyRanking(familyRanking);
			characterModel.setFamilyRanking(familyRanking);
		}
	}

	class ParentsFateItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
			String parentsFate = (String) comboBox.getSelectedItem();
			characterModel.setParentsFate(parentsFate);

			if (comboBox.getSelectedIndex() == 2) {
				characterView.getParentEventComboBox().setEnabled(true);
				characterView.getRandomizeParentEventButton().setEnabled(true);
			} else {
				characterView.getParentEventComboBox().setEnabled(false);
				characterView.getParentEventComboBox().setSelectedIndex(0);
				characterView.getRandomizeParentEventButton().setEnabled(false);
			}

		}

	}

	class RandomizeParentsFateActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JComboBox<String> comboBox = characterView.getParentsFateComboBox();
			Random rnd = new Random();
			int seed = (rnd.nextInt(10) + 1);
			String parentsFate;
			if (0 < seed && seed <= 6) {
				parentsFate = comboBox.getItemAt(1);
			} else if (7 <= seed && seed <= 10) {
				parentsFate = comboBox.getItemAt(2);
			} else {
				parentsFate = "";
			}

			characterView.setParentsFate(parentsFate);
			characterModel.setParentsFate(parentsFate);

			if (comboBox.getSelectedIndex() == 2) {
				characterView.getParentEventComboBox().setEnabled(true);
			} else {
				characterView.getParentEventComboBox().setEnabled(false);
				characterView.getParentEventComboBox().setSelectedIndex(0);
			}
		}
	}

	class ParentEventItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
			String parentEvent = (String) comboBox.getSelectedItem();
			characterModel.setParentEvent(parentEvent);
		}

	}

	class RandomizeParentEventActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			Random rnd = new Random();
			int seed = (rnd.nextInt(10) + 1);
			String parentEvent = characterView.getParentEventComboBox().getItemAt(seed);

			characterView.setParentEvent(parentEvent);
			characterModel.setParentEvent(parentEvent);
		}
	}

	class FamilyStatusItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
			String familyStatus = (String) comboBox.getSelectedItem();
			characterModel.setFamilyStatus(familyStatus);

			if (comboBox.getSelectedIndex() == 1) {
				characterView.getFamilyTragedyComboBox().setEnabled(true);
				characterView.getRandomizeFamilyTragedyButton().setEnabled(true);
			} else {
				characterView.getFamilyTragedyComboBox().setEnabled(false);
				characterView.getRandomizeFamilyTragedyButton().setEnabled(false);
				characterView.getFamilyTragedyComboBox().setSelectedIndex(0);
			}
		}

	}

	class RandomizeFamilyStatusActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JComboBox<String> comboBox = characterView.getFamilyStatusComboBox();
			Random rnd = new Random();
			int seed = (rnd.nextInt(10) + 1);
			String familyStatus;
			if (0 < seed && seed <= 6) {
				familyStatus = comboBox.getItemAt(1);
			} else if (7 <= seed && seed <= 10) {
				familyStatus = comboBox.getItemAt(2);
			} else {
				familyStatus = "";
			}

			characterView.setFamilyStatus(familyStatus);
			characterModel.setFamilyStatus(familyStatus);

			if (comboBox.getSelectedIndex() == 1) {
				characterView.getFamilyTragedyComboBox().setEnabled(true);
				characterView.getRandomizeFamilyTragedyButton().setEnabled(true);
			} else {
				characterView.getFamilyTragedyComboBox().setEnabled(false);
				characterView.getRandomizeFamilyTragedyButton().setEnabled(false);
				characterView.getFamilyTragedyComboBox().setSelectedIndex(0);
			}
		}
	}

	class FamilyTragedyItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
			String childhoodEnvironment = (String) comboBox.getSelectedItem();
			characterModel.setChildhoodEnvironment(childhoodEnvironment);
		}

	}

	class RandomizeFamilyTragedyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			Random rnd = new Random();
			int seed = (rnd.nextInt(10) + 1);
			String familyTragedy = characterView.getFamilyTragedyComboBox().getItemAt(seed);

			characterView.setFamilyTragedy(familyTragedy);
			characterModel.setFamilyTragedy(familyTragedy);
		}
	}

	class ChildhoodEnvironmentItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {

		}

	}

	class RandomizeChildhoodEnvironmentActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			Random rnd = new Random();
			int seed = (rnd.nextInt(10) + 1);
			String childhoodEnvironment = characterView.getChildhoodEnvironmentComboBox().getItemAt(seed);

			characterView.setChildhoodEnvironment(childhoodEnvironment);
			characterModel.setChildhoodEnvironment(childhoodEnvironment);
		}
	}

	class SiblingAgeItemListener implements ItemListener {
		private int index;

		public SiblingAgeItemListener(int index) {
			this.index = index;
		}

		@Override
		public void itemStateChanged(ItemEvent event) {
			JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
			CharacterCreationModel.Sibling sibling = characterModel.getSiblings().get(index);
			String age = (String) comboBox.getSelectedItem();
			sibling.setAge(age);
			characterModel.getSiblings().set(index, sibling);
		}

	}

	class RandomizeSiblingAgeActionListener implements ActionListener {
		private int index;

		public RandomizeSiblingAgeActionListener(int index) {
			this.index = index;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			CharacterCreationModel.Sibling sibling = characterModel.getSiblings().get(index);
			JComboBox<String> ageComboBox = characterView.getSiblingPanels().get(index).getSiblingAgeComboBox();

			Random rnd = new Random();
			int seed = (rnd.nextInt(10) + 1);
			String siblingAge;
			if (0 < seed && seed <= 5) {
				siblingAge = (String) ageComboBox.getItemAt(1);
			} else if (6 <= seed && seed <= 9) {
				siblingAge = (String) ageComboBox.getItemAt(2);
			} else if (seed == 10) {
				siblingAge = (String) ageComboBox.getItemAt(3);
			} else {
				siblingAge = "";
			}

			sibling.setAge(siblingAge);
			ageComboBox.setSelectedItem(siblingAge);
		}
	}

	class SiblingGenderActionListener implements ActionListener {
		private int index;

		public SiblingGenderActionListener(int index) {
			this.index = index;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			CharacterCreationModel.Sibling sibling = characterModel.getSiblings().get(index);
			sibling.setGender(event.getActionCommand());
			characterModel.getSiblings().set(index, sibling);
		}

	}

	class RandomizeSiblingGenderActionListener implements ActionListener {
		private int index;

		public RandomizeSiblingGenderActionListener(int index) {
			this.index = index;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			CharacterCreationModel.Sibling sibling = characterModel.getSiblings().get(index);
			JRadioButton maleRadioButton = characterView.getSiblingPanels().get(index).getMaleRadioButton();
			JRadioButton femaleRadioButton = characterView.getSiblingPanels().get(index).getFemaleRadioButton();

			Random rnd = new Random();
			int seed = (rnd.nextInt(10) + 1);
			String gender;
			if (seed % 2 == 0) {
				gender = maleRadioButton.getActionCommand();
				maleRadioButton.setSelected(true);
			} else {
				gender = femaleRadioButton.getActionCommand();
				femaleRadioButton.setSelected(true);
			}

			sibling.setGender(gender);
		}

	}

	class SiblingRelationshipItemListener implements ItemListener {
		private int index;

		public SiblingRelationshipItemListener(int index) {
			this.index = index;
		}

		@Override
		public void itemStateChanged(ItemEvent event) {
			JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
			CharacterCreationModel.Sibling sibling = characterModel.getSiblings().get(index);
			String relationship = (String) comboBox.getSelectedItem();
			sibling.setRelationship(relationship);
			characterModel.getSiblings().set(index, sibling);
		}

	}

	class RandomizeSiblingRelationshipActionListener implements ActionListener {
		private int index;

		public RandomizeSiblingRelationshipActionListener(int index) {
			this.index = index;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			CharacterCreationModel.Sibling sibling = characterModel.getSiblings().get(index);
			JComboBox<String> relationshipComboBox = characterView.getSiblingPanels().get(index)
					.getSiblingRelationshipComboBox();

			Random rnd = new Random();
			int seed = (rnd.nextInt(10) + 1);
			String relationship;
			if (0 < seed && seed <= 2) {
				relationship = (String) relationshipComboBox.getItemAt(1);
			} else if (3 <= seed && seed <= 4) {
				relationship = (String) relationshipComboBox.getItemAt(2);
			} else if (5 <= seed && seed <= 6) {
				relationship = (String) relationshipComboBox.getItemAt(3);
			} else if (7 <= seed && seed <= 8) {
				relationship = (String) relationshipComboBox.getItemAt(4);
			} else if (9 <= seed && seed <= 10) {
				relationship = (String) relationshipComboBox.getItemAt(5);
			} else {
				relationship = "";
			}

			sibling.setRelationship(relationship);
			relationshipComboBox.setSelectedItem(relationship);
		}
	}

	class RoleChangeActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
			String selectedRole = (String) comboBox.getSelectedItem();
			characterModel.setRole(selectedRole);
		}
	}

	class SiblingCountItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
			String siblingCount = (String) comboBox.getSelectedItem();

			JPanel siblingsDetailPanel = characterView.getSiblingsDetailPanel();
			siblingsDetailPanel.removeAll();
			characterModel.getSiblings().clear();

			try {
				Integer count = Integer.parseInt(siblingCount);

				for (int i = 0; i < count; i++) {
					SiblingPanel siblingPanel = new SiblingPanel();
					siblingPanel.setBorder(new TitledBorder(null, "Sibling " + (i + 1), TitledBorder.LEADING,
							TitledBorder.TOP, null, null));
					siblingsDetailPanel.add(siblingPanel);
					characterView.getSiblingPanels().add(i, siblingPanel);
					Sibling sibling = new SiblingBuilder().build();
					characterModel.getSiblings().add(i, sibling);
					siblingPanel.addNameDocumentListener(new SiblingNameDocumentListener(i));
					siblingPanel.addAgeItemListener(new SiblingAgeItemListener(i));
					siblingPanel.addAgeRandomizerActionListener(new RandomizeSiblingAgeActionListener(i));
					siblingPanel.addMaleRadioButtonActionListener(new SiblingGenderActionListener(i));
					siblingPanel.addFemaleRadioButtonActionListener(new SiblingGenderActionListener(i));
					siblingPanel.addGenderRandomizerActionListener(new RandomizeSiblingGenderActionListener(i));
					siblingPanel.addRelationshipItemListener(new SiblingRelationshipItemListener(i));
					siblingPanel
							.addRelationshipRandomizerActionListener(new RandomizeSiblingRelationshipActionListener(i));
				}
				siblingsDetailPanel.revalidate();
			} catch (NumberFormatException exception) {
				// do nothing
			}
			characterModel.setSiblingCount(siblingCount);

		}

	}

	class SiblingCountRandomizerActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			Random rnd = new Random();
			int seed = (rnd.nextInt(10) + 1);

			JPanel siblingsDetailPanel = characterView.getSiblingsDetailPanel();
			siblingsDetailPanel.removeAll();
			characterModel.getSiblings().clear();

			String siblingCount;
			if (0 < seed && seed <= 7) {
				siblingCount = (String) characterView.getSiblingCountComboBox().getItemAt(seed);
			} else if (8 <= seed && seed <= 10) {
				siblingCount = (String) characterView.getSiblingCountComboBox().getItemAt(8);
			} else {
				siblingCount = "";
			}

			try {
				Integer count = Integer.parseInt(siblingCount);

				for (int i = 0; i < count; i++) {
					SiblingPanel siblingPanel = new SiblingPanel();
					siblingPanel.setBorder(new TitledBorder(null, "Sibling " + (i + 1), TitledBorder.LEADING,
							TitledBorder.TOP, null, null));
					siblingsDetailPanel.add(siblingPanel);
					characterView.getSiblingPanels().add(i, siblingPanel);
					Sibling sibling = new SiblingBuilder().build();
					characterModel.getSiblings().add(i, sibling);
					siblingPanel.addNameDocumentListener(new SiblingNameDocumentListener(i));
					siblingPanel.addAgeItemListener(new SiblingAgeItemListener(i));
					siblingPanel.addAgeRandomizerActionListener(new RandomizeSiblingAgeActionListener(i));
					siblingPanel.addMaleRadioButtonActionListener(new SiblingGenderActionListener(i));
					siblingPanel.addFemaleRadioButtonActionListener(new SiblingGenderActionListener(i));
					siblingPanel.addGenderRandomizerActionListener(new RandomizeSiblingGenderActionListener(i));
					siblingPanel.addRelationshipItemListener(new SiblingRelationshipItemListener(i));
					siblingPanel
							.addRelationshipRandomizerActionListener(new RandomizeSiblingRelationshipActionListener(i));
				}
				siblingsDetailPanel.revalidate();
			} catch (NumberFormatException exception) {
				// do nothing
			}

			characterView.setSiblingCount(siblingCount);
			characterModel.setSiblingCount(siblingCount);
		}
	}

	class UnequipActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JTable equippedTable = characterView.getEquippedTable();

			String selectedCellType;
			CharacterCreationModel.Armor selectedArmor;
			for (int row = 0; row < equippedTable.getRowCount(); row++) {
				for (int column = 0; column < equippedTable.getColumnCount(); column++) {
					if (equippedTable.isCellSelected(row, column)
							&& !((String) equippedTable.getValueAt(row, column)).equals("")) {
						selectedCellType = (String) equippedTable.getValueAt(row, column);
						selectedArmor = characterModel.getArmors().get(selectedCellType);
						characterModel.setGeneralEncumbranceValue(
								characterModel.getGeneralEncumbranceValue() - selectedArmor.getEncumbranceValue());

						clearDuplicateArmor(equippedTable, selectedCellType);
					}
				}
			}
		}

		private void clearDuplicateArmor(JTable table, String type) {
			for (int column = 0; column < table.getColumnCount(); column++) {
				for (int row = 0; row < table.getRowCount(); row++) {
					String tempArmorType = (String) table.getValueAt(row, column);
					if (type.equals(tempArmorType)) {
						EquippedTableModel model = (EquippedTableModel) table.getModel();
						table.setValueAt("", row, column);
						model.fireTableDataChanged();
						break;
					}
				}
			}
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

	class ClothingDocumentListener implements DocumentListener {
		@Override
		public void changedUpdate(DocumentEvent event) {
			characterModel.setClothing(characterView.getClothing());
		}

		@Override
		public void insertUpdate(DocumentEvent event) {
			characterModel.setClothing(characterView.getClothing());
		}

		@Override
		public void removeUpdate(DocumentEvent event) {
			characterModel.setClothing(characterView.getClothing());
		}

	}

	class HairstyleDocumentListener implements DocumentListener {
		@Override
		public void changedUpdate(DocumentEvent event) {
			characterModel.setHairstyle(characterView.getHairstyle());
		}

		@Override
		public void insertUpdate(DocumentEvent event) {
			characterModel.setHairstyle(characterView.getHairstyle());
		}

		@Override
		public void removeUpdate(DocumentEvent event) {
			characterModel.setHairstyle(characterView.getHairstyle());
		}
	}

	class AffectationsDocumentListener implements DocumentListener {
		@Override
		public void changedUpdate(DocumentEvent event) {
			characterModel.setAffectations(characterView.getAffectation());
		}

		@Override
		public void insertUpdate(DocumentEvent event) {
			characterModel.setAffectations(characterView.getAffectation());
		}

		@Override
		public void removeUpdate(DocumentEvent event) {
			characterModel.setAffectations(characterView.getAffectation());
		}
	}

	class EthnicityItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			characterModel.setEthnicity(characterView.getEthnicity());
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

	class SiblingNameDocumentListener implements DocumentListener {
		public int index;

		public SiblingNameDocumentListener(int index) {
			this.index = index;
		}

		@Override
		public void changedUpdate(DocumentEvent event) {
			Document document = (Document) event.getDocument();
			CharacterCreationModel.Sibling sibling = characterModel.getSiblings().get(index);
			try {
				sibling.setSiblingName(document.getText(0, document.getLength()));
			} catch (BadLocationException exception) {
				exception.printStackTrace();
			}
			characterModel.getSiblings().set(index, sibling);
		}

		@Override
		public void insertUpdate(DocumentEvent event) {
			Document document = (Document) event.getDocument();
			CharacterCreationModel.Sibling sibling = characterModel.getSiblings().get(index);
			try {
				sibling.setSiblingName(document.getText(0, document.getLength()));
			} catch (BadLocationException exception) {
				exception.printStackTrace();
			}
		}

		@Override
		public void removeUpdate(DocumentEvent event) {
			Document document = (Document) event.getDocument();
			CharacterCreationModel.Sibling sibling = characterModel.getSiblings().get(index);
			try {
				sibling.setSiblingName(document.getText(0, document.getLength()));
			} catch (BadLocationException exception) {
				exception.printStackTrace();
			}
		}

	}

	class InventoryItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			JPanel cardsPanel = characterView.getInventoryCardPanel();
			CardLayout layout = (CardLayout) cardsPanel.getLayout();
			layout.show(cardsPanel, (String) event.getItem());
		}

	}

	class StoreItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			JPanel cardsPanel = characterView.getStoreCardPanel();
			CardLayout layout = (CardLayout) cardsPanel.getLayout();
			layout.show(cardsPanel, (String) event.getItem());
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

}
