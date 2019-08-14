package rpg.cyberpunk._2020.gui.items;

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

import rpg.cyberpunk._2020.combat.CyberpunkArmor;
import rpg.cyberpunk._2020.commerce.CyberpunkVendor;
import rpg.cyberpunk._2020.gui.USCurrencyRenderer;
import rpg.general.combat.BodyLocation;

/**
 * A table that displays a collection of armor that a player can buy.
 * 
 * @author Coul Greer
 */
public class ShopArmorCategoryTable extends JTable {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a table using a set of armor provided by a vendor.
	 * 
	 * @param vendor the owner of the set of armor displayed on the table
	 */
	public ShopArmorCategoryTable(CyberpunkVendor vendor) {
		super(new ShopArmorTableModel(vendor));

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(getModel());
		setRowSorter(sorter);

		setRowHeight(ArmorCoveringRenderer.ICON_HEIGHT);
		getColumnModel().removeColumn(getColumnModel().getColumn(ShopArmorTableModel.OBJECT_INDEX));
		getColumnModel().getColumn(ShopArmorTableModel.COVERS_INDEX)
				.setPreferredWidth(ArmorCoveringRenderer.ICON_HEIGHT);

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
		case ShopArmorTableModel.COVERS_INDEX:
			return new ArmorCoveringRenderer();
		case ShopArmorTableModel.COST_INDEX:
			return new USCurrencyRenderer();
		default:
			return super.getCellRenderer(rowIndex, columnIndex);
		}
	}

	/**
	 * The underlying model used by a table that displays a <code>Vendor</code>'s
	 * <code>CyberpunkArmor</code> stock.
	 */
	public static class ShopArmorTableModel extends AbstractTableModel {
		/**
		 * The index of the column used to hold the icon showing what body locations are
		 * covered.
		 */
		public static final int COVERS_INDEX = 0;

		/**
		 * The index of the column used to hold the name of an armor.
		 */
		public static final int NAME_INDEX = 1;

		/**
		 * The index of the column used to hold the type of an armor (hard or soft).
		 */
		public static final int TYPE_INDEX = 2;

		/**
		 * The index of the column used to hold the stopping power of an armor.
		 */
		public static final int STOPPING_POWER_INDEX = 3;

		/**
		 * The index of the column used to hold the modifier for encumbrance of an
		 * armor.
		 */
		public static final int ENCUMBRANCE_VALUE_INDEX = 4;

		/**
		 * The index of the column used to hold the cost of an armor.
		 */
		public static final int COST_INDEX = 5;

		/**
		 * The index of the column used to hold the object representing an armor.
		 */
		public static final int OBJECT_INDEX = 6;

		/**
		 * A collection of the names of the table headers.
		 */
		private static final String[] columnNames = { //
				"", //
				"Name", //
				"Type", //
				"SP", //
				"EV", //
				"Cost", //
				"Object" };

		private static final long serialVersionUID = 1L;

		private Set<CyberpunkArmor> armorSet;

		/**
		 * Creates a model that uses a set of armor provided by a vendor to populate the
		 * table view.
		 * 
		 * @param vendor the provider of the armor that populate the table view
		 */
		public ShopArmorTableModel(CyberpunkVendor vendor) {
			this.armorSet = vendor.getStoredArmors();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return armorSet.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			List<CyberpunkArmor> rows = new ArrayList<>(armorSet);
			CyberpunkArmor armor = rows.get(rowIndex);

			switch (columnIndex) {
			case COVERS_INDEX:
				return getCovers(armor);
			case NAME_INDEX:
				return armor.getName();
			case TYPE_INDEX:
				return armor.getArmorType();
			case STOPPING_POWER_INDEX:
				return armor.getProtectionScore();
			case ENCUMBRANCE_VALUE_INDEX:
				return armor.getEncumbranceValue();
			case COST_INDEX:
				return armor.getCost();
			case OBJECT_INDEX:
				return armor;
			default:
				throw new IllegalArgumentException(
						"The index " + columnIndex + " does not have a constant associated with it.");
			}
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
		public String getColumnName(int columnIndex) {
			return columnNames[columnIndex];
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
