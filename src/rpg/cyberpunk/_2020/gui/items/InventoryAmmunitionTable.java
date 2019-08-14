package rpg.cyberpunk._2020.gui.items;

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
import rpg.cyberpunk._2020.gui.MeasurementRenderer;
import rpg.cyberpunk._2020.gui.USCurrencyRenderer;
import rpg.general.combat.Ammunition;
import rpg.util.Measurement;

/**
 * An instance of JTable that uses a <code>InventoryAmmunitionTableModel</code>.
 */
public class InventoryAmmunitionTable extends JTable {
  private static final long serialVersionUID = 1L;

  /**
   * Constructs a table that does not allow the columns to be resized or headers to be reordered. As
   * well as having a hidden column that hosts the object that the information is derived from.
   * While also using a player to get data for the table model.
   * 
   * @param player the owner of the displayed inventory
   */
  public InventoryAmmunitionTable(Player player) {
    super(new InventoryAmmunitionTableModel(player));

    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(getModel());
    setRowSorter(sorter);

    getColumnModel()
        .removeColumn(getColumnModel().getColumn(InventoryAmmunitionTableModel.OBJECT_INDEX));

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
      case InventoryAmmunitionTableModel.DAMAGE_INDEX:
        return new DamageRenderer();
      case InventoryAmmunitionTableModel.COST_INDEX:
        return new USCurrencyRenderer();
      case InventoryAmmunitionTableModel.WEIGHT_INDEX:
        return new MeasurementRenderer();
      default:
        return super.getCellRenderer(rowIndex, columnIndex);
    }
  }

  /**
   * An instance of AbstractTableModel that has a Name, Type, Damage, Cost, Weight, Quantity, and
   * Object column.
   */
  public static class InventoryAmmunitionTableModel extends AbstractTableModel
      implements PropertyChangeListener {
    /**
     * The index for the column that holds the data for an ammunition's name.
     */
    public static final int NAME_INDEX = 0;

    /**
     * The index for the column that holds the data for an ammunition's type.
     */
    public static final int TYPE_INDEX = 1;

    /**
     * The index for the column that holds the data for an ammunition's damage probability.
     */
    public static final int DAMAGE_INDEX = 2;

    /**
     * The index for the column that holds the data for an ammunition's cost.
     */
    public static final int COST_INDEX = 3;

    /**
     * The index for the column that holds the data for an ammunition's weight.
     */
    public static final int WEIGHT_INDEX = 4;

    /**
     * The index for the column that holds the amount of that ammunition held.
     */
    public static final int QUANTITY_INDEX = 5;

    /**
     * The index for the column that holds the ammunition object.
     */
    public static final int OBJECT_INDEX = 6;

    private static final String[] columnNames = { //
        "Name", //
        "Type", //
        "Damage", //
        "Cost", //
        "Weight", //
        "Quantity", //
        "Object"};

    private static final long serialVersionUID = 1L;

    private Player player;
    private Set<Ammunition> ammunitionSet;

    /**
     * Constructs a model that uses player to get data for the model while also observing the player
     * for changes in their inventory related to ammunition.
     * 
     * @param player the provider of the model data.
     */
    public InventoryAmmunitionTableModel(Player player) {
      this.player = player;
      this.ammunitionSet = new HashSet<Ammunition>(player.createCarriedAmmunitionCollection());

      player.addPropertyChangeListener(Player.PROPERTY_NAME_INVENTORY_AMMUNITION_MANIPULATED, this);
    }

    @Override
    public int getColumnCount() {
      return columnNames.length;
    }

    @Override
    public int getRowCount() {
      return ammunitionSet.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      List<Ammunition> rows = new ArrayList<>(ammunitionSet);
      Ammunition ammunition = rows.get(rowIndex);
      int quantity = player.getQuantity(ammunition);

      switch (columnIndex) {
        case NAME_INDEX:
          return ammunition.getName();
        case TYPE_INDEX:
          return ammunition.getType().getName();
        case DAMAGE_INDEX:
          return ammunition.getDamage();
        case COST_INDEX:
          return ammunition.getCost() * quantity;
        case WEIGHT_INDEX:
          Measurement weight = ammunition.getWeight();
          return new Measurement( //
              weight.getType(), //
              weight.getAmount() * quantity, //
              weight.getUnit());
        case QUANTITY_INDEX:
          return quantity;
        case OBJECT_INDEX:
          return ammunition;
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
      Object source = evt.getSource();

      if (source == player) {
        ammunitionSet = new HashSet<Ammunition>(player.createCarriedAmmunitionCollection());
        fireTableDataChanged();
      }
    }

  }

}
