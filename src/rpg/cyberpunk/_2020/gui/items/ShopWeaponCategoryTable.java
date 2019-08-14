package rpg.cyberpunk._2020.gui.items;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.cyberpunk._2020.commerce.CyberpunkVendor;
import rpg.cyberpunk._2020.gui.MeasurementRenderer;
import rpg.cyberpunk._2020.gui.USCurrencyRenderer;
import rpg.util.Probability;

/**
 * The display for a collection of <code>CyberpunkWeapon</code>s represented as a table of the
 * derived data from the weapons.
 */
public class ShopWeaponCategoryTable extends JTable {
  private static final long serialVersionUID = 1L;

  /**
   * Constructs a table using a set of <code>CyberpunkWeapon</code>s provided by a
   * <code>Vendor</code>. The vendor's weapons are then allowed to be sorted using this table and
   * not allowed to reorder or resize the columns.
   * 
   * @param vendor the owner of the set of weapons displayed on the table
   */
  public ShopWeaponCategoryTable(CyberpunkVendor vendor) {
    super(new ShopWeaponTableModel(vendor));

    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(getModel());
    setRowSorter(sorter);
    sorter.setComparator(ShopWeaponTableModel.TYPE_INDEX, new WeaponTypeComparator());

    setRowHeight(WeaponTypeRenderer.ICON_HEIGHT);
    getColumnModel().removeColumn(getColumnModel().getColumn(ShopWeaponTableModel.OBJECT_INDEX));
    getColumnModel().getColumn(ShopWeaponTableModel.TYPE_INDEX) //
        .setPreferredWidth(WeaponTypeRenderer.ICON_HEIGHT);

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
      case ShopWeaponTableModel.TYPE_INDEX:
        return new WeaponTypeRenderer();
      case ShopWeaponTableModel.RANGE_INDEX:
        return new MeasurementRenderer();
      case ShopWeaponTableModel.COST_INDEX:
        return new USCurrencyRenderer();
      case ShopWeaponTableModel.DAMAGE_INDEX:
        return new DamageRenderer();
      default:
        return super.getCellRenderer(rowIndex, columnIndex);
    }
  }

  @Override
  public String getToolTipText(MouseEvent evt) {
    String tip = null;

    Point point = evt.getPoint();
    int rowIndex = rowAtPoint(point);
    int colIndex = columnAtPoint(point);

    if (convertColumnIndexToModel(colIndex) == ShopWeaponTableModel.TYPE_INDEX) {
      tip = (String) getValueAt(rowIndex, colIndex);
    }

    return tip;
  }

  /**
   * The underlying model used by <code>ShopWeaponTable</code> that displays a <code>Vendor</code>'s
   * weapon stock.
   */
  public static class ShopWeaponTableModel extends AbstractTableModel {
    /**
     * The index of the column used to hold the type of a weapon.
     */
    public static final int TYPE_INDEX = 0;

    /**
     * The index of the column used to hold the name of a weapon.
     */
    public static final int NAME_INDEX = 1;

    /**
     * The index of the column used to hold the flat bonus to accuracy of a weapon.
     */
    public static final int WEAPON_ACCURACY_INDEX = 2;

    /**
     * The index of the column used to hold the concealability rating of a weapon.
     */
    public static final int CONCEALABILITY_INDEX = 3;

    /**
     * The index of the column used to hold the availability rating of a weapon.
     */
    public static final int AVAILABILITY_INDEX = 4;

    /**
     * The index of the column used to hold the damage of a weapon.
     */
    public static final int DAMAGE_INDEX = 5;

    /**
     * The index of the column used to hold the type of ammunition that a weapon uses.
     */
    public static final int AMMO_INDEX = 6;

    /**
     * The index of the column used to hold the maximum amount of ammunition a weapon can store
     * inside itself.
     */
    public static final int NUMBER_OF_SHOTS_INDEX = 7;

    /**
     * The index of the column used to hold the amount of shots a weapon can make per turn.
     */
    public static final int RATE_OF_FIRE_INDEX = 8;

    /**
     * The index of the column used to hold the reliability rating of a weapon.
     */
    public static final int RELIABILITY_INDEX = 9;

    /**
     * The index of the column used to hold the range of attack of a weapon.
     */
    public static final int RANGE_INDEX = 10;

    /**
     * The index of the column used to hold the cost of a weapon.
     */
    public static final int COST_INDEX = 11;

    /**
     * The index of the column used to hold the object representing a weapon.
     */
    public static final int OBJECT_INDEX = 12;

    private static final String[] columnNames = { //
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
        "Object"};

    private static final long serialVersionUID = 1L;

    private Set<CyberpunkWeapon> weaponSet;

    /**
     * Constructs a model that uses a set of <code>CyberpunkWeapon</code>s provided by a
     * <code>Vendor</code> to populate the table view.
     * 
     * @param vendor the provider of the set of weapons
     */
    public ShopWeaponTableModel(CyberpunkVendor vendor) {
      this.weaponSet = vendor.getStoredWeapons();
    }

    @Override
    public int getColumnCount() {
      return columnNames.length;
    }

    @Override
    public int getRowCount() {
      return weaponSet.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      List<CyberpunkWeapon> rows = new ArrayList<>(weaponSet);
      CyberpunkWeapon weapon = rows.get(rowIndex);

      switch (columnIndex) {
        case TYPE_INDEX:
          return weapon.getWeaponType();
        case NAME_INDEX:
          return weapon.getName();
        case WEAPON_ACCURACY_INDEX:
          return weapon.getAttackModifier();
        case CONCEALABILITY_INDEX:
          return weapon.getConcealability();
        case AVAILABILITY_INDEX:
          return weapon.getAvailability();
        case DAMAGE_INDEX:
          return new Probability(weapon.getDamageDice(), weapon.getDamageModifier());
        case AMMO_INDEX:
          return weapon.getAmmunitionType().getName();
        case NUMBER_OF_SHOTS_INDEX:
          return weapon.getAmmunitionCapacity();
        case RATE_OF_FIRE_INDEX:
          return weapon.getRateOfFire();
        case RELIABILITY_INDEX:
          return weapon.getReliability();
        case RANGE_INDEX:
          return weapon.getRangeModifier();
        case COST_INDEX:
          return weapon.getCost();
        case OBJECT_INDEX:
          return weapon;
        default:
          throw new IllegalArgumentException(
              "The index " + columnIndex + " does not have a constant associated with it.");
      }
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
