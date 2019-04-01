package rpg.cyberpunk._2020.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import rpg.cyberpunk._2020.combat.CyberpunkWeapon;

/**
 * A table that displays a collection of weapons that a player can buy.
 * 
 * @author Coul Greer
 */
public class ShopWeaponCategoryTable extends JTable {

	/**
	 * Constructs a table using a set of weapons.
	 * 
	 * @param weaponSet a collection of weapons a user can buy
	 */
	public ShopWeaponCategoryTable(Set<CyberpunkWeapon> weaponSet) {
		super(new ShopWeaponTableModel(weaponSet));

		setupRenderers();

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(getModel());
		setRowSorter(sorter);
		sorter.setComparator(ShopWeaponTableModel.TYPE_COLUMN_INDEX, new WeaponTypeComparator());

		setRowHeight(WeaponTypeRenderer.ICON_HEIGHT);
		getColumnModel().removeColumn(getColumnModel().getColumn(ShopWeaponTableModel.OBJECT_INDEX));
		getColumnModel().getColumn(ShopWeaponTableModel.TYPE_COLUMN_INDEX)
				.setPreferredWidth(WeaponTypeRenderer.ICON_HEIGHT);
	}

	private void setupRenderers() {
		TableColumnModel columnModel = getColumnModel();
		columnModel.getColumn(ShopWeaponTableModel.TYPE_COLUMN_INDEX).setCellRenderer(new WeaponTypeRenderer());
		columnModel.getColumn(ShopWeaponTableModel.RANGE_COLUMN_INDEX).setCellRenderer(new DistanceRenderer());
		columnModel.getColumn(ShopWeaponTableModel.COST_COLUMN_INDEX).setCellRenderer(new CurrencyRenderer());
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
	public String getToolTipText(MouseEvent e) {
		String tip = null;
		java.awt.Point p = e.getPoint();
		int rowIndex = rowAtPoint(p);
		int colIndex = columnAtPoint(p);
		int realColumnIndex = convertColumnIndexToModel(colIndex);

		if (realColumnIndex == ShopWeaponTableModel.TYPE_COLUMN_INDEX) {
			tip = (String) getValueAt(rowIndex, colIndex);
		}

		return tip;
	}

	/**
	 * The underlying model used by a table that displays a vendor's weapon stock.
	 * 
	 * @author Coul Greer
	 */
	public static class ShopWeaponTableModel extends DefaultTableModel {
		/**
		 * The index of the column used to hold the type of a weapon.
		 */
		public static final int TYPE_COLUMN_INDEX = 0;

		/**
		 * The index of the column used to hold the name of a weapon.
		 */
		public static final int NAME_COLUMN_INDEX = 1;

		/**
		 * The index of the column used to hold the flat bonus to accuracy of a weapon.
		 */
		public static final int WEAPON_ACCURACY_COLUMN_INDEX = 2;

		/**
		 * The index of the column used to hold the concealability rating of a weapon.
		 */
		public static final int CONCEALABILITY_COLUMN_INDEX = 3;

		/**
		 * The index of the column used to hold the availability rating of a weapon.
		 */
		public static final int AVAILABILITY_COLUMN_INDEX = 4;

		/**
		 * The index of the column used to hold the damage of a weapon.
		 */
		public static final int DAMAGE_COLUMN_INDEX = 5;

		/**
		 * The index of the column used to hold the type of ammunition that a weapon
		 * uses.
		 */
		public static final int AMMO_COLUMN_INDEX = 6;

		/**
		 * The index of the column used to hold the maximum amount of ammunition a
		 * weapon can store inside itself.
		 */
		public static final int NUMBER_OF_SHOTS_COLUMN_INDEX = 7;

		/**
		 * The index of the column used to hold the amount of shots a weapon can make
		 * per turn.
		 */
		public static final int RATE_OF_FIRE_COLUMN_INDEX = 8;

		/**
		 * The index of the column used to hold the reliability rating of a weapon.
		 */
		public static final int RELIABILITY_COLUMN_INDEX = 9;

		/**
		 * The index of the column used to hold the range of attack of a weapon.
		 */
		public static final int RANGE_COLUMN_INDEX = 10;

		/**
		 * The index of the column used to hold the cost of a weapon.
		 */
		public static final int COST_COLUMN_INDEX = 11;

		/**
		 * The index of the column used to hold the object representing a weapon.
		 */
		public static final int OBJECT_INDEX = 12;

		public static final String[] COLUMN_NAMES = { //
				"", //
				"Name", //
				"W.A.", //
				"Con.", //
				"Avail.", //
				"Damage", //
				"Ammo", //
				"# Shots", //
				"RoF", //
				"Rel.", //
				"Range", //
				"Cost", //
				"Object" };

		private Set<CyberpunkWeapon> weaponSet;

		public ShopWeaponTableModel(Set<CyberpunkWeapon> weaponSet) {
			this.weaponSet = weaponSet;

			populateModel();
		}

		private void populateModel() {
			Iterator<CyberpunkWeapon> iterator = weaponSet.iterator();

			while (iterator.hasNext()) {
				addRow(createRow(iterator.next()));
			}
		}

		private Object[] createRow(CyberpunkWeapon weapon) {
			Object[] row = new Object[COLUMN_NAMES.length];

			row[NAME_COLUMN_INDEX] = weapon.getName();
			row[TYPE_COLUMN_INDEX] = weapon.getWeaponType();
			row[WEAPON_ACCURACY_COLUMN_INDEX] = weapon.getHitModifier();
			row[CONCEALABILITY_COLUMN_INDEX] = weapon.getConcealability();
			row[AVAILABILITY_COLUMN_INDEX] = weapon.getAvailability();
			row[DAMAGE_COLUMN_INDEX] = weapon.getHitDice() + "+" + weapon.getDamageScore();
			row[AMMO_COLUMN_INDEX] = weapon.getAmmunitionType();
			row[NUMBER_OF_SHOTS_COLUMN_INDEX] = weapon.getAmmunitionCapacity();
			row[RATE_OF_FIRE_COLUMN_INDEX] = weapon.getRateOfFire();
			row[RELIABILITY_COLUMN_INDEX] = weapon.getReliability();
			row[RANGE_COLUMN_INDEX] = weapon.getRangeModifier();
			row[COST_COLUMN_INDEX] = weapon.getCost();
			row[OBJECT_INDEX] = weapon;

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
