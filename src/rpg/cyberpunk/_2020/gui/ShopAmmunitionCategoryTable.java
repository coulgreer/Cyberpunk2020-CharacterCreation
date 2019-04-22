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

/**
 * An instance of JTable that uses a <code>ShopAmmunitionTableModel</code>.
 * 
 * @author Coul Greer
 */
public class ShopAmmunitionCategoryTable extends JTable {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a table that does not allow the columns to be resized or headers
	 * to be reordered. As well as having a hidden column that hosts the object that
	 * the information is derived from.
	 * 
	 * @param vendor the owner of the data given to the model
	 */
	public ShopAmmunitionCategoryTable(CyberpunkVendor vendor) {
		super(new ShopAmmunitionTableModel(vendor));

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(getModel());
		setRowSorter(sorter);

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

	/**
	 * An instance of AbstractTableModel that uses <code>CyberpunkVendor</code> to
	 * populate the model with stored <code>Ammunition</code>. Has a Name, Type,
	 * Damage, Cost,Ammo per Box, and Object column.
	 * 
	 * @author Coul Greer
	 */
	public static class ShopAmmunitionTableModel extends AbstractTableModel {
		/**
		 * The index for the column that holds the data for an ammunition's name.
		 */
		public static final int NAME_INDEX = 0;

		/**
		 * The index for the column that holds the data for an ammunition's type.
		 */
		public static final int TYPE_INDEX = 1;

		/**
		 * The index for the column that holds the data for an ammunition's damage
		 * probability.
		 */
		public static final int DAMAGE_INDEX = 2;

		/**
		 * The index for the column that holds the data for an ammunition's cost.
		 */
		public static final int COST_INDEX = 3;

		/**
		 * The index for the column that holds the data for how much ammunition is
		 * bought at once.
		 */
		public static final int BOX_INDEX = 4;

		/**
		 * The index for the column that holds the ammunition object.
		 */
		public static final int OBJECT_INDEX = 5;

		/**
		 * The identifier of all the columns.
		 */
		public static final String[] COLUMN_NAMES = { //
				"Name", //
				"Type", //
				"Damage", //
				"Cost", //
				"Ammo/Box", //
				"Object" };

		private static final long serialVersionUID = 1L;

		private Set<Box<Ammunition>> ammunitionSet;

		/**
		 * Constructs a model that uses CyberpunkVendor to get a collection of
		 * Ammunition.
		 * 
		 * @param vendor the owner of the collection of ammunition
		 */
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
			case NAME_INDEX:
				return box.getName();
			case TYPE_INDEX:
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
				throw new IllegalArgumentException(
						"The index " + columnIndex + " does not have a constant associated with it.");
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
