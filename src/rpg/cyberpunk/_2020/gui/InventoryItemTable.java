package rpg.cyberpunk._2020.gui;

import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import rpg.cyberpunk._2020.Player;
import rpg.general.commerce.Item;

/**
 * A JTable that houses the Items currently in a Player's Inventory.
 */
public class InventoryItemTable extends JTable {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a table used to display a player's collection of items held in
	 * their inventory.
	 * 
	 * @param player the owner of the displayed inventory
	 */
	public InventoryItemTable(Player player) {
		super(new InventoryItemTableModel(player));

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(getModel());
		setRowSorter(sorter);

		setRowHeight(WeaponTypeRenderer.ICON_HEIGHT);
		getColumnModel().removeColumn(getColumnModel().getColumn(InventoryItemTableModel.OBJECT_INDEX));

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
		Color primaryColor = Color.WHITE;
		Color secondaryColor = new Color(220, 220, 220); // gainsboro

		if (!getSelectionBackground().equals(component.getBackground())) {
			component.setBackground(row % 2 == 0 ? secondaryColor : primaryColor);
		}
	}

	@Override
	public TableCellRenderer getCellRenderer(int rowIndex, int columnIndex) {
		switch (convertColumnIndexToModel(columnIndex)) {
		case InventoryItemTableModel.COST_INDEX:
			return new USCurrencyRenderer();
		case InventoryItemTableModel.WEIGHT_INDEX:
			return new MetricWeightRenderer();
		default:
			return super.getCellRenderer(rowIndex, columnIndex);
		}
	}

	/**
	 * An instance of AbstractTableModel that has a Name, Cost, Weight, and Object
	 * column.
	 */
	public static class InventoryItemTableModel extends AbstractTableModel implements PropertyChangeListener {
		/**
		 * The index of the column used to hold the name of an item.
		 */
		public static final int NAME_INDEX = 0;

		/**
		 * The index of the column used to hold the cost of an item.
		 */
		public static final int COST_INDEX = 1;

		/**
		 * The index of the column used to hold the weight of an item.
		 */
		public static final int WEIGHT_INDEX = 2;

		/**
		 * The index of the column used to hold the quantity of an item.
		 */
		public static final int QUANTITY_INDEX = 3;

		/**
		 * The index of the column used to hold the entire item object.
		 */
		public static final int OBJECT_INDEX = 4;

		private static final String[] columnNames = { //
				"Name", //
				"Cost", //
				"Weight", //
				"Quantity", //
				"Object" };

		private static final long serialVersionUID = 1L;

		private Player player;
		private Set<Item> itemSet;

		/**
		 * Constructs an InventoryItemTableModel that listens to when any item is added
		 * or removed from player as well as using player's item set to display to the
		 * GUI.
		 * 
		 * @param player the object to derive the displayed data from as well as observe
		 *               for changes to notify an update
		 */
		public InventoryItemTableModel(Player player) {
			this.player = player;
			this.itemSet = new HashSet<Item>(player.createCarriedItemCollection());

			player.addPropertyChangeListener(Player.PROPERTY_NAME_INVENTORY_ITEM_MANIPULATED, this);
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return itemSet.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			List<Item> rows = new ArrayList<>(itemSet);
			Item item = rows.get(rowIndex);
			int quantity = player.getQuantity(item);

			switch (columnIndex) {
			case NAME_INDEX:
				return item.getName();
			case COST_INDEX:
				return item.getCost();
			case WEIGHT_INDEX:
				return item.getWeight();
			case QUANTITY_INDEX:
				return quantity;
			case OBJECT_INDEX:
				return item;
			default:
				throw new IllegalArgumentException(
						"The index " + columnIndex + " does not have a constant associated with it.");
			}
		}

		@Override
		public String getColumnName(int col) {
			return columnNames[col];
		}

		@Override
		public boolean isCellEditable(int row, int col) {
			return false;
		}

		@Override
		public Class<?> getColumnClass(int col) {
			Object value = getRowCount() > 0 ? getValueAt(0, col) : null;

			return value == null ? Object.class : value.getClass();
		}

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Object source = evt.getSource();

			if (source == player) {
				itemSet = new HashSet<Item>(player.createCarriedItemCollection());
				fireTableDataChanged();
			}
		}

	}

}
