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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import rpg.cyberpunk._2020.combat.CyberpunkWeapon;

public class ShopWeaponCategoryTable extends JTable {
	private TableCellRenderer typeRenderer;

	public ShopWeaponCategoryTable(Set<CyberpunkWeapon> weaponSet) {
		super(new WeaponShopTableModel(weaponSet));
		typeRenderer = new WeaponTypeRenderer();

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(getModel());
		setRowSorter(sorter);
		sorter.setComparator(WeaponShopTableModel.TYPE_COLUMN_INDEX, new WeaponTypeComparator());

		setRowHeight(WeaponTypeRenderer.ICON_HEIGHT);
		getColumnModel().removeColumn(getColumnModel().getColumn(WeaponShopTableModel.OBJECT_INDEX));
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
	public TableCellRenderer getCellRenderer(int row, int column) {
		if (column == WeaponShopTableModel.TYPE_COLUMN_INDEX) {
			return typeRenderer;
		} else {
			return super.getCellRenderer(row, column);
		}
	}

	@Override
	public String getToolTipText(MouseEvent e) {
		String tip = null;
		java.awt.Point p = e.getPoint();
		int rowIndex = rowAtPoint(p);
		int colIndex = columnAtPoint(p);
		int realColumnIndex = convertColumnIndexToModel(colIndex);

		if (realColumnIndex == WeaponShopTableModel.TYPE_COLUMN_INDEX) {
			tip = (String) getValueAt(rowIndex, colIndex);
		}

		return tip;
	}

	public static class WeaponShopTableModel extends DefaultTableModel {
		public static final int TYPE_COLUMN_INDEX = 0;
		public static final int NAME_COLUMN_INDEX = 1;
		public static final int WEAPON_ACCURACY_COLUMN_INDEX = 2;
		public static final int CONCEALABILITY_COLUMN_INDEX = 3;
		public static final int AVAILABILITY_COLUMN_INDEX = 4;
		public static final int DAMAGE_COLUMN_INDEX = 5;
		public static final int AMMO_COLUMN_INDEX = 6;
		public static final int NUMBER_OF_SHOTS_COLUMN_INDEX = 7;
		public static final int RATE_OF_FIRE_COLUMN_INDEX = 8;
		public static final int RELIABILITY_COLUMN_INDEX = 9;
		public static final int RANGE_COLUMN_INDEX = 10;
		public static final int COST_COLUMN_INDEX = 11;
		public static final int OBJECT_INDEX = 12;

		public static final String[] COLUMN_NAMES = { //
				"Type", //
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

		public WeaponShopTableModel(Set<CyberpunkWeapon> weaponSet) {
			this.weaponSet = weaponSet;

			populateModel();
		}

		private void populateModel() {
			Iterator<CyberpunkWeapon> iterator = weaponSet.iterator();

			while (iterator.hasNext()) {
				addRow(buildRow(iterator.next()));
			}
		}

		private Object[] buildRow(CyberpunkWeapon weapon) {
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
