package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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

import model.CharacterCreationModel;
import view.CharacterCreationView;
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

		this.characterView.drawLoadedInjuryPoints(this.characterModel.getInjuryPoints());
		this.characterView.setCharacterPoints(Integer.toString(this.characterModel.getCharacterPoints()
				- this.characterModel.getIntelligenceLevel() - this.characterModel.getUnmodifiedReflexesLevel()
				- this.characterModel.getTechnicalAbilityLevel() - this.characterModel.getCoolLevel()
				- this.characterModel.getAttractivenessLevel() - this.characterModel.getLuckLevel()
				- this.characterModel.getMovementAllowanceLevel() - this.characterModel.getBodyLevel()
				- this.characterModel.getTotalEmpathyLevel()));
		this.characterView.setLeapLevel(Double.toString(this.characterModel.getLeapLevel()));
		this.characterView.setLiftLevel(Double.toString(this.characterModel.getLiftLevel()));
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
	}

	private void populateSkillPanels() {
		for (Map<String, CharacterCreationModel.Skill> skillCategory : characterModel.getSkillCatelog().values()) {
			for (CharacterCreationModel.Skill skill : skillCategory.values()) {
				characterView.drawBasicSkillPanel(skill.getType(), skill.getSkillName(), skill.getRank(),
						skill.getSpecifiedSkill(), new SkillTableModelListener());
			}
		}
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
			characterView.setLiftLevel(Double.toString(characterModel.getLiftLevel()));
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
								characterView.setLiftLevel(value);
								characterModel.setLiftCapacity(Double.parseDouble(value));
								break;
							case "carry":
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
									String formattedSkillName = skill.getSkillName().replace(" ", "_")
											.replace("...", ".").toLowerCase();
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
						+ "lift\t" + characterModel.getLiftLevel() + "\n" //
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
								: (typeCode + ":" + skillCode.replace("...", ".") + specifiedSkill + "\t" + skillRank);
						fw.write(completeSkillCode + "\n");

					}
				}
				String encodedData = convertImageToBase64(characterView.getCharacterPortrait());
				fw.write("portrait\t" + encodedData);

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
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "gif", "png");
			chooser.setFileFilter(filter);
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
