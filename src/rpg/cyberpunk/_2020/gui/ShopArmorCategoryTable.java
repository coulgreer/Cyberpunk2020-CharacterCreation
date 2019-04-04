package rpg.cyberpunk._2020.gui;

import java.awt.Color;
import java.awt.Component;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import rpg.cyberpunk._2020.combat.CyberpunkArmor;
import rpg.general.combat.BodyLocation;

public class ShopArmorCategoryTable extends JTable {

	public ShopArmorCategoryTable(Set<CyberpunkArmor> armorSet) {
		super(new ShopArmorTableModel(armorSet));

		setupRenderers();

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(getModel());
		setRowSorter(sorter);

		setRowHeight(ArmorCoveringRenderer.ICON_HEIGHT);
		getColumnModel().removeColumn(getColumnModel().getColumn(ShopArmorTableModel.OBJECT_INDEX));
		getColumnModel().getColumn(ShopArmorTableModel.COVERS_INDEX)
				.setPreferredWidth(ArmorCoveringRenderer.ICON_HEIGHT);
	}

	private void setupRenderers() {
		TableColumnModel columnModel = getColumnModel();
		columnModel.getColumn(ShopArmorTableModel.COVERS_INDEX).setCellRenderer(new ArmorCoveringRenderer());
		columnModel.getColumn(ShopArmorTableModel.COST_INDEX).setCellRenderer(new CurrencyRenderer());
	}

	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component component = super.prepareRenderer(renderer, row, column);

		packColumnWidth(component, column);
		alternateRowColors(component, row);

		return component;
	}

	private void packColumnWidth(Component component, int column) {
		int rendererWidth = component.getPreferredSize().width;
		TableColumn tableColumn = getColumnModel().getColumn(column);
		tableColumn.setPreferredWidth(
				Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
	}

	private void alternateRowColors(Component component, int row) {
		Color primeColor = Color.WHITE;
		Color secondaryColor = new Color(220, 220, 220); // gainsboro

		if (!component.getBackground().equals(getSelectionBackground())) {
			Color bg = (row % 2 == 0 ? secondaryColor : primeColor);
			component.setBackground(bg);
		}
	}

	public static class ShopArmorTableModel extends DefaultTableModel {
		public static final int COVERS_INDEX = 0;
		public static final int NAME_INDEX = 1;
		public static final int TYPE_INDEX = 2;
		public static final int STOPPING_POWER_INDEX = 3;
		public static final int ENCUMBRANCE_VALUE_INDEX = 4;
		public static final int COST_INDEX = 5;
		public static final int OBJECT_INDEX = 6;

		public static final String[] COLUMN_NAMES = { //
				"", //
				"Name", //
				"Type", //
				"SP", //
				"EV", //
				"Cost", //
				"Object" };

		private Set<CyberpunkArmor> armorSet;

		public ShopArmorTableModel(Set<CyberpunkArmor> armorSet) {
			this.armorSet = armorSet;

			populateModel();
		}

		private void populateModel() {
			Iterator<CyberpunkArmor> iterator = armorSet.iterator();

			while (iterator.hasNext()) {
				addRow(createRow(iterator.next()));
			}
		}

		private Object[] createRow(CyberpunkArmor armor) {
			Object[] row = new Object[COLUMN_NAMES.length];

			row[COVERS_INDEX] = getCovers(armor);
			row[NAME_INDEX] = armor.getName();
			row[TYPE_INDEX] = armor.getArmorType();
			row[STOPPING_POWER_INDEX] = armor.getProtectionScore();
			row[ENCUMBRANCE_VALUE_INDEX] = armor.getEncumbranceValue();
			row[COST_INDEX] = armor.getCost();
			row[OBJECT_INDEX] = armor;

			return row;
		}

		private String getCovers(CyberpunkArmor armor) {
			String covering = CyberpunkArmor.COVERING_NOTHING;

			if (isOnlyCoveringHead(armor)) {
				covering = CyberpunkArmor.COVERING_HEAD;
			} else if (isOnlyCoveringArmsAndTorso(armor)) {
				covering = CyberpunkArmor.COVERING_ARMS_AND_TORSO;
			} else if (isOnlyCoveringTorso(armor)) {
				covering = CyberpunkArmor.COVERING_TORSO;
			} else if (isOnlyCoveringArms(armor)) {
				covering = CyberpunkArmor.COVERING_ARMS;
			} else if (isOnlyCoveringLegs(armor)) {
				covering = CyberpunkArmor.COVERING_LEGS;
			} else if (isCoveringEverything(armor)) {
				covering = CyberpunkArmor.COVERING_EVERYTHING;
			}

			return covering;
		}

		private boolean isOnlyCoveringHead(CyberpunkArmor armor) {
			return armor.isCovering(BodyLocation.HEAD) //
					&& !(armor.isCovering(BodyLocation.RIGHT_ARM) && armor.isCovering(BodyLocation.LEFT_ARM)) //
					&& !(armor.isCovering(BodyLocation.RIGHT_LEG) && armor.isCovering(BodyLocation.LEFT_LEG)) //
					&& !armor.isCovering(BodyLocation.TORSO);
		}

		private boolean isOnlyCoveringArmsAndTorso(CyberpunkArmor armor) {
			return !armor.isCovering(BodyLocation.HEAD) //
					&& (armor.isCovering(BodyLocation.RIGHT_ARM) && armor.isCovering(BodyLocation.LEFT_ARM)) //
					&& !(armor.isCovering(BodyLocation.RIGHT_LEG) && armor.isCovering(BodyLocation.LEFT_LEG)) //
					&& armor.isCovering(BodyLocation.TORSO);
		}

		private boolean isOnlyCoveringTorso(CyberpunkArmor armor) {
			return !armor.isCovering(BodyLocation.HEAD) //
					&& !(armor.isCovering(BodyLocation.RIGHT_ARM) && armor.isCovering(BodyLocation.LEFT_ARM)) //
					&& !(armor.isCovering(BodyLocation.RIGHT_LEG) && armor.isCovering(BodyLocation.LEFT_LEG)) //
					&& armor.isCovering(BodyLocation.TORSO);
		}

		private boolean isOnlyCoveringArms(CyberpunkArmor armor) {
			return !armor.isCovering(BodyLocation.HEAD) //
					&& (armor.isCovering(BodyLocation.RIGHT_ARM) && armor.isCovering(BodyLocation.LEFT_ARM)) //
					&& !(armor.isCovering(BodyLocation.RIGHT_LEG) && armor.isCovering(BodyLocation.LEFT_LEG)) //
					&& !armor.isCovering(BodyLocation.TORSO);
		}

		private boolean isOnlyCoveringLegs(CyberpunkArmor armor) {
			return !armor.isCovering(BodyLocation.HEAD) //
					&& !(armor.isCovering(BodyLocation.RIGHT_ARM) && armor.isCovering(BodyLocation.LEFT_ARM)) //
					&& (armor.isCovering(BodyLocation.RIGHT_LEG) && armor.isCovering(BodyLocation.LEFT_LEG)) //
					&& !armor.isCovering(BodyLocation.TORSO);
		}

		private boolean isCoveringEverything(CyberpunkArmor armor) {
			return armor.isCovering(BodyLocation.HEAD) //
					&& (armor.isCovering(BodyLocation.RIGHT_ARM) && armor.isCovering(BodyLocation.LEFT_ARM)) //
					&& (armor.isCovering(BodyLocation.RIGHT_LEG) && armor.isCovering(BodyLocation.LEFT_LEG)) //
					&& armor.isCovering(BodyLocation.TORSO);
		}

		@Override
		public int getColumnCount() {
			return COLUMN_NAMES.length;
		}

		@Override
		public String getColumnName(int col) {
			return COLUMN_NAMES[col];
		}

		@Override
		public boolean isCellEditable(int row, int col) {
			return false;
		}

		@Override
		public Class<?> getColumnClass(int col) {
			return getValueAt(0, col).getClass();
		}
	}

}
