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
import rpg.Player;
import rpg.cyberpunk._2020.combat.CyberpunkCombatant;
import rpg.cyberpunk._2020.combat.CyberpunkWeapon;
import rpg.cyberpunk._2020.gui.ShopWeaponCategoryTable.ShopWeaponTableModel;

/**
 * A table that displays a set of weapons that is owned by the given player.
 */
public class InventoryWeaponTable extends JTable {
  private static final long serialVersionUID = 1L;

  /**
   * Constructs a table used to display a player's collection of weapons held in their inventory.
   * 
   * @param player the owner of the displayed inventory
   */
  public InventoryWeaponTable(Player player) {
    super(new InventoryWeaponTableModel(player));

    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(getModel());
    setRowSorter(sorter);
    sorter.setComparator(InventoryWeaponTableModel.TYPE_INDEX, new WeaponTypeComparator());

    setRowHeight(WeaponTypeRenderer.ICON_HEIGHT);
    getColumnModel()
        .removeColumn(getColumnModel().getColumn(InventoryWeaponTableModel.OBJECT_INDEX));
    getColumnModel().getColumn(InventoryWeaponTableModel.TYPE_INDEX)
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

    if (!component.getBackground().equals(getSelectionBackground())) {
      component.setBackground(row % 2 == 0 ? secondaryColor : primaryColor);
    }
  }

  @Override
  public TableCellRenderer getCellRenderer(int rowIndex, int columnIndex) {
    switch (convertColumnIndexToModel(columnIndex)) {
      case InventoryWeaponTableModel.TYPE_INDEX:
        return new WeaponTypeRenderer();
      case InventoryWeaponTableModel.RANGE_INDEX:
        return new MetricDistanceRenderer();
      case InventoryWeaponTableModel.COST_INDEX:
        return new USCurrencyRenderer();
      case InventoryWeaponTableModel.WEIGHT_INDEX:
        return new MetricWeightRenderer();
      case ShopWeaponTableModel.DAMAGE_INDEX:
        return new DamageRenderer();
      default:
        return super.getCellRenderer(rowIndex, columnIndex);
    }
  }

  /**
   * The underlying model used by a table that displays a player's weapon stock.
   * 
   * @author Coul Greer
   */
  public static class InventoryWeaponTableModel extends AbstractTableModel
      implements PropertyChangeListener {
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
     * The index of the column used to hold the current amount of ammunition held out of the maximum
     * amount of ammunition for a weapon to hold.
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
     * The index of the column used to hold the weight of a weapon.
     */
    public static final int WEIGHT_INDEX = 12;

    /**
     * The index of the column used to hold the quantity of a weapon.
     */
    public static final int QUANTITY_INDEX = 13;

    /**
     * The index of the column used to hold the object representing a weapon.
     */
    public static final int OBJECT_INDEX = 14;

    private static final String[] columnNames = { //
        "", //
        "Name", //
        "W.A.", //
        "Con.", //
        "Avail.", //
        "Dmg", //
        "Ammo", //
        "# Shots", //
        "RoF", //
        "Rel.", //
        "Range", //
        "Cost", //
        "Wt.", //
        "Qty.", //
        "Object"};

    private static final long serialVersionUID = 1L;

    private Player player;
    private Set<CyberpunkWeapon> weaponSet;

    /**
     * Creates a model that uses a set of weapons to populate the table view.
     * 
     * @param player the provider of the weapons that populate the table view
     */
    public InventoryWeaponTableModel(Player player) {
      this.player = player;
      this.weaponSet = new HashSet<CyberpunkWeapon>(player.createCarriedWeaponCollection());

      player.addPropertyChangeListener(Player.PROPERTY_NAME_INVENTORY_WEAPON_MANIPULATED, this);
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
      CyberpunkCombatant combatant = new CyberpunkCombatant(player);
      CyberpunkWeapon weapon = rows.get(rowIndex);
      int quantity = player.getQuantity(weapon);

      switch (columnIndex) {
        case TYPE_INDEX:
          return weapon.getWeaponType();
        case NAME_INDEX:
          return weapon.getName();
        case WEAPON_ACCURACY_INDEX:
          return combatant.getTotalAttackChance(weapon).getModifier();
        case CONCEALABILITY_INDEX:
          return weapon.getConcealability();
        case AVAILABILITY_INDEX:
          return weapon.getAvailability();
        case DAMAGE_INDEX:
          return combatant.getTotalDamageChance(weapon);
        case AMMO_INDEX:
          return weapon.getAmmunitionType();
        case NUMBER_OF_SHOTS_INDEX:
          return weapon.getAmmunitionCount() + " / " + weapon.getAmmunitionCapacity();
        case RATE_OF_FIRE_INDEX:
          return weapon.getRateOfFire();
        case RELIABILITY_INDEX:
          return weapon.getReliability();
        case RANGE_INDEX:
          return combatant.getRangeScore(weapon);
        case COST_INDEX:
          return weapon.getCost();
        case WEIGHT_INDEX:
          return weapon.getWeight();
        case QUANTITY_INDEX:
          return quantity;
        case OBJECT_INDEX:
          return weapon;
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
        weaponSet = new HashSet<CyberpunkWeapon>(player.createCarriedWeaponCollection());
        fireTableDataChanged();
      }
    }

  }

}
