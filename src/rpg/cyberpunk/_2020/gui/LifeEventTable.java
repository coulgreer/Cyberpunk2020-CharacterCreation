package rpg.cyberpunk._2020.gui;

import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import rpg.cyberpunk._2020.Age;
import rpg.cyberpunk._2020.Player;

/**
 * A table that displays events based on age.
 */
public class LifeEventTable extends JTable {
  private static final long serialVersionUID = 1L;

  /**
   * Constructs an instance of LifeEventTable that uses a default <code>LifeEvent.Model</code>. Does
   * not allow header resizing or reordering.
   * 
   * @param player the provider of the data. Gets passed to the default model for its use.
   */
  public LifeEventTable(Player player) {
    super(new Model(player));

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
      case Model.AGE_INDEX:
        return new AgeRenderer();
      default:
        return super.getCellRenderer(rowIndex, columnIndex);
    }
  }

  /**
   * An instance of AbstractTableModel that allows for the organization of age with its
   * corresponding event.
   */
  public static class Model extends AbstractTableModel implements PropertyChangeListener {
    public static final int AGE_INDEX = 0;
    public static final int EVENT_INDEX = 1;

    private static final long serialVersionUID = 1L;
    private static final String[] COLUMN_NAMES = { //
        "Age", //
        "Event"};

    private Player player;
    private Map<Age, String> eventsByAge;
    private Age[] ages;

    /**
     * Constructs an instance of <code>LifeEventTable.Model</code>. Takes in a non-null Player and
     * adds a PropertyChangeListener for the Player's age and life events.
     * 
     * @param player the object observed for updates needed
     */
    public Model(Player player) {
      setPlayer(player);
    }

    private void setPlayer(Player player) {
      if (player == null) {
        throw new NullPointerException();
      } else {
        this.player = player;
        eventsByAge = player.getEventsByAge();
        ages = eventsByAge.keySet() //
            .toArray(new Age[eventsByAge.size()]);

        player.addPropertyChangeListener(Player.PROPERTY_NAME_AGE, this);
        player.addPropertyChangeListener(Player.PROPERTY_NAME_LIFE_EVENT, this);
      }
    }

    @Override
    public int getColumnCount() {
      return COLUMN_NAMES.length;
    }

    @Override
    public int getRowCount() {
      return eventsByAge.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      switch (columnIndex) {
        case AGE_INDEX:
          return ages[rowIndex];
        case EVENT_INDEX:
          return eventsByAge.get(ages[rowIndex]);
        default:
          throw new IllegalArgumentException(
              "Index " + columnIndex + " is invalid as a column index.");
      }
    }

    @Override
    public void setValueAt(Object data, int rowIndex, int columnIndex) {
      player.updateEvent(ages[rowIndex], (String) data);
      fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public String getColumnName(int col) {
      return COLUMN_NAMES[col];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
      return col == EVENT_INDEX;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
      Object source = evt.getSource();

      if (source == player) {
        eventsByAge = player.getEventsByAge();
        ages = eventsByAge.keySet() //
            .toArray(new Age[eventsByAge.size()]);
        fireTableDataChanged();
      }
    }

  }

}
