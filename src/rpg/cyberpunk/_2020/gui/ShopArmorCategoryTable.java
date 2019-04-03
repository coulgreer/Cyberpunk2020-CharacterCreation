package rpg.cyberpunk._2020.gui;

import java.util.Iterator;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import rpg.cyberpunk._2020.combat.CyberpunkArmor;

public class ShopArmorCategoryTable extends JTable {

	public ShopArmorCategoryTable(Set<CyberpunkArmor> armorSet) {
		super(new ShopArmorTableModel(armorSet));
	}

	public static class ShopArmorTableModel extends DefaultTableModel {
		public static final int COVERS_INDEX = 0;
		public static final int NAME_INDEX = 1;
		public static final int STOPPING_POWER_INDEX = 2;
		public static final int ENCUMBRANCE_VALUE_INDEX = 3;
		public static final int COST_INDEX = 4;
		public static final int OBJECT_INDEX = 5;

		public static final String[] COLUMN_NAMES = { //
				"", //
				"Name", //
				"SP", //
				"EV", //
				"Cost" };

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

			row[NAME_INDEX] = armor.getName();
			row[STOPPING_POWER_INDEX] = armor.getProtectionScore();
			row[ENCUMBRANCE_VALUE_INDEX] = armor.getEncumbranceValue();
			row[COST_INDEX] = armor.getCost();
			row[OBJECT_INDEX] = armor;

			return row;
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
