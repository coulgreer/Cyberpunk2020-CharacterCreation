package rpg.cyberpunk._2020.gui;

import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import rpg.Player;
import rpg.cyberpunk._2020.combat.CyberpunkArmor;
import rpg.general.combat.BodyLocation;

/**
 * An instance of JTable that uses <code>EquippedArmorTableModel</code> to
 * acquire the data.
 * 
 * @author Coul Greer
 */
public class EquippedArmorTable extends JTable {
	/**
	 * Constructs a table that displays the equipped armor of a <code>Player</code>.
	 * 
	 * @param player the wearer of the armor to display
	 */
	public EquippedArmorTable(Player player) {
		super(new EquippedArmorTableModel(player));

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(getModel());
		setRowSorter(sorter);

		setRowHeight(ArmorCoveringRenderer.ICON_HEIGHT);
		getColumnModel().removeColumn(getColumnModel().getColumn(EquippedArmorTableModel.OBJECT_INDEX));

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

	/**
	 * An instance of AbstractTableModel that has 9 columns used to display the
	 * Icon, Name, Stopping Power of various BodyLocations, and the Object that all
	 * the data is derived from. It's worth noting that the Object column is hidden
	 * from view by the EquippedArmorTable.
	 * 
	 * @author Coul Greer
	 */
	public static class EquippedArmorTableModel extends AbstractTableModel implements PropertyChangeListener {
		public static final int ICON_INDEX = 0;
		public static final int NAME_INDEX = 1;
		public static final int HEAD_SP_INDEX = 2;
		public static final int LEFT_ARM_SP_INDEX = 3;
		public static final int RIGHT_ARM_SP_INDEX = 4;
		public static final int TORSO_SP_INDEX = 5;
		public static final int LEFT_LEG_SP_INDEX = 6;
		public static final int RIGHT_LEG_SP_INDEX = 7;
		public static final int OBJECT_INDEX = 8;

		/**
		 * A collection of the names used to head columns.
		 */
		public static final String[] COLUMN_NAMES = { //
				"", //
				"Name", //
				"Head", //
				"L. Arm", //
				"R. Arm", //
				"Torso", //
				"L. Leg", //
				"R. Leg", //
				"Object" };

		private Player player;
		private List<CyberpunkArmor> armor;

		/**
		 * Constructs an EquippedArmorTableModel that uses a <code>Player</code> for
		 * getting data.
		 * 
		 * @param player the object used to acquire data for display to the JTable
		 */
		public EquippedArmorTableModel(Player player) {
			this.player = player;
			this.armor = new ArrayList<CyberpunkArmor>(player.createCarriedArmorCollection());

			player.addPropertyChangeListener(Player.PROPERTY_NAME_ARMOR_EQUIPPED, this);
		}

		@Override
		public int getColumnCount() {
			return COLUMN_NAMES.length;
		}

		@Override
		public String getColumnName(int columnIndex) {
			return COLUMN_NAMES[columnIndex];
		}

		@Override
		public int getRowCount() {
			return armor.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			CyberpunkArmor armorPiece = armor.get(rowIndex);

			switch (columnIndex) {
			case ICON_INDEX:
				return armorPiece.getArmorType();
			case NAME_INDEX:
				return armorPiece.getName();
			case HEAD_SP_INDEX:
				return armorPiece.getDurabilityAt(BodyLocation.HEAD);
			case LEFT_ARM_SP_INDEX:
				return armorPiece.getDurabilityAt(BodyLocation.LEFT_ARM);
			case RIGHT_ARM_SP_INDEX:
				return armorPiece.getDurabilityAt(BodyLocation.RIGHT_ARM);
			case TORSO_SP_INDEX:
				return armorPiece.getDurabilityAt(BodyLocation.TORSO);
			case LEFT_LEG_SP_INDEX:
				return armorPiece.getDurabilityAt(BodyLocation.LEFT_LEG);
			case RIGHT_LEG_SP_INDEX:
				return armorPiece.getDurabilityAt(BodyLocation.RIGHT_LEG);
			case OBJECT_INDEX:
				return armorPiece;
			default:
				throw new IllegalArgumentException(
						"The index " + columnIndex + " does not have a constant associated with it.");
			}
		}

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Object source = evt.getSource();

			if (source == player) {
				armor = new ArrayList<CyberpunkArmor>(player.createEquippedArmorCollection());
				fireTableDataChanged();
			}
		}

	}
}
