package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

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
		this.characterView.addLoadCharacterActionListener(new LoadCharacterActionListener());
		this.characterView.addSaveCharacterActionListener(new SaveCharacterActionListener());
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
		this.characterView.drawLoadedInjuryPoints(this.characterModel.getInjuryPoints());
		this.characterView.setLeapLevel(Double.toString(this.characterModel.getLeapLevel()));
		this.characterView.setLiftLevel(Double.toString(this.characterModel.getLiftLevel()));
		this.characterView.setRunLevel(Double.toString(this.characterModel.getRunLevel()));
		this.characterView.setSaveModifier(Integer.toString(this.characterModel.getSaveModifier()));
		this.characterView.setBodyTypeModifier(Integer.toString(this.characterModel.getBodyTypeModifier()));

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
		}

	}

	class UnmodifiedReflexesChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent event) {
			int newLevel = Integer.parseInt(((JSpinner) event.getSource()).getValue().toString());
			characterModel.setUnmodifiedReflexesLevel(newLevel);
		}

	}

	class TechnicalAbilityChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent event) {
			int newLevel = Integer.parseInt(((JSpinner) event.getSource()).getValue().toString());
			characterModel.setTechnicalAbilityLevel(newLevel);
		}

	}

	class CoolChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent event) {
			int newLevel = Integer.parseInt(((JSpinner) event.getSource()).getValue().toString());
			characterModel.setCoolLevel(newLevel);
		}

	}

	class AttractivenessChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent event) {
			int newLevel = Integer.parseInt(((JSpinner) event.getSource()).getValue().toString());
			characterModel.setAttractivenessLevel(newLevel);
		}

	}

	class LuckChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent event) {
			int newLevel = Integer.parseInt(((JSpinner) event.getSource()).getValue().toString());
			characterModel.setLuckLevel(newLevel);
		}

	}

	class MovementAllowanceChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent event) {
			int newLevel = Integer.parseInt(((JSpinner) event.getSource()).getValue().toString());
			characterModel.setMovementAllowance(newLevel);
			characterView.setRunLevel(Double.toString(characterModel.getRunLevel()));
			characterView.setLeapLevel(Double.toString(characterModel.getLeapLevel()));
		}
	}

	class BodyChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent event) {
			int newLevel = Integer.parseInt(((JSpinner) event.getSource()).getValue().toString());
			characterModel.setBodyLevel(newLevel);

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
			characterModel.setBodyLevel(newLevel);
			characterView.setLiftLevel(Double.toString(characterModel.getLiftLevel()));
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
							characterModel.setMovementAllowance(Integer.parseInt(value));
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
								String formattedSkillName = skill.getSkillName().replace(" ", "_").replace("...", ".")
										.toLowerCase();
								if (skillCode.contains(formattedSkillName)) {
									skill.setRank(Integer.parseInt(value));
									skill.setSpecifiedSkill(specificSkill);
								}
							}
							break;
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
						+ "movement_allowance\t" + characterModel.getMovementAllowance() + "\n" //
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

				fw.close();
			} catch (IOException exception) {
				System.err.println("File does not exist.");
			}
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
}
