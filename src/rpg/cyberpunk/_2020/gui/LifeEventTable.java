package rpg.cyberpunk._2020.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import rpg.cyberpunk._2020.Player;

public class LifeEventTable extends JTable {
  private static final long serialVersionUID = 1L;

  public LifeEventTable(Player player) {
    super(new LifeEventModel(player));
  }

  public static class LifeEventModel extends AbstractTableModel implements PropertyChangeListener {
    public static final int AGE_INDEX = 0;
    public static final int EVENT_INDEX = 1;

    private static final long serialVersionUID = 1L;
    private static final String[] COLUMN_NAMES = { //
        "Age", //
        "Event"};

    private Player player;
    private Map<Integer, String> eventsByAge;
    private Integer[] ages;

    public LifeEventModel(Player player) {
      setPlayer(player);
    }

    private void setPlayer(Player player) {
      if (player == null) {
        throw new NullPointerException();
      } else {
        this.player = player;
        eventsByAge = player.getEventsByAge();
        ages = eventsByAge.keySet() //
            .toArray(new Integer[eventsByAge.size()]);

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
            .toArray(new Integer[eventsByAge.size()]);
        fireTableDataChanged();
      }
    }

  }

}
