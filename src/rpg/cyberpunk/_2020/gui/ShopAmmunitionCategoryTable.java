package rpg.cyberpunk._2020.gui;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import rpg.cyberpunk._2020.commerce.Box;
import rpg.cyberpunk._2020.commerce.CyberpunkVendor;
import rpg.general.combat.Ammunition;

public class ShopAmmunitionCategoryTable extends JTable {

	public ShopAmmunitionCategoryTable(CyberpunkVendor vendor) {
		super(new ShopAmmunitionTableModel(vendor));

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(getModel());
		setRowSorter(sorter);

		// TODO
		setRowHeight(ArmorCoveringRenderer.ICON_HEIGHT);
		getColumnModel().removeColumn(getColumnModel().getColumn(ShopAmmunitionTableModel.OBJECT_INDEX));

		getTableHeader().setReorderingAllowed(false);
		getTableHeader().setResizingAllowed(false);
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

	@Override
	public TableCellRenderer getCellRenderer(int rowIndex, int columnIndex) {
		switch (convertColumnIndexToModel(columnIndex)) {
		case ShopAmmunitionTableModel.DAMAGE_INDEX:
			return new DamageRenderer();
		case ShopAmmunitionTableModel.COST_INDEX:
			return new CurrencyRenderer();
		default:
			return super.getCellRenderer(rowIndex, columnIndex);
		}
	}

	public static class ShopAmmunitionTableModel extends AbstractTableModel {
		public static final int ICON_INDEX = 0;
		public static final int NAME_INDEX = 1;
		public static final int AMMUNITION_TYPE_INDEX = 2;
		public static final int DAMAGE_INDEX = 3;
		public static final int COST_INDEX = 4;
		public static final int BOX_INDEX = 5;
		public static final int OBJECT_INDEX = 6;

		public static final String[] COLUMN_NAMES = { //
				"", //
				"Name", //
				"Type", //
				"Damage", //
				"Cost", //
				"Ammo/Box", //
				"Object" };

		private Set<Box<Ammunition>> ammunitionSet;

		public ShopAmmunitionTableModel(CyberpunkVendor vendor) {
			ammunitionSet = vendor.getStoredAmmunition();
		}

		@Override
		public int getColumnCount() {
			return COLUMN_NAMES.length;
		}

		@Override
		public int getRowCount() {
			return ammunitionSet.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			List<Box<Ammunition>> rows = new ArrayList<>(ammunitionSet);
			Box<Ammunition> box = rows.get(rowIndex);
			Ammunition ammunition = box.getItems().get(0);

			switch (columnIndex) {
			case ICON_INDEX:
				return ammunition.getAmmunitionType();
			case NAME_INDEX:
				return box.getName();
			case AMMUNITION_TYPE_INDEX:
				return ammunition.getAmmunitionType();
			case DAMAGE_INDEX:
				return ammunition.getDamage();
			case COST_INDEX:
				return box.getCost();
			case BOX_INDEX:
				return box.getQuantity();
			case OBJECT_INDEX:
				return box;
			default:
				throw new IllegalArgumentException();
			}
		}

		@Override
		public String getColumnName(int columnIndex) {
			return COLUMN_NAMES[columnIndex];
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			Object value = getRowCount() > 0 ? getValueAt(0, columnIndex) : null;

			return value == null ? Object.class : value.getClass();
		}

	}

}
