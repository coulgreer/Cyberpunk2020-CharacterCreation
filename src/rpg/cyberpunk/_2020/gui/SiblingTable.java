package rpg.cyberpunk._2020.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import rpg.cyberpunk._2020.Player;
import rpg.cyberpunk._2020.Sibling;

public class SiblingTable extends JTable {
  private static final long serialVersionUID = 1L;

  public SiblingTable(Player player) {
    super(new SiblingModel(player));
  }

  public static class SiblingModel extends AbstractTableModel implements PropertyChangeListener {
    public static final int NAME_INDEX = 0;
    public static final int GENDER_INDEX = 1;
    public static final int AGE_INDEX = 2;
    public static final int RELATIONSHIP_INDEX = 3;

    private static final long serialVersionUID = 1L;
    private static final String[] COLUMN_NAMES = { //
        "Name", //
        "Gender", //
        "Age", //
        "Relationship"};

    private Player player;
    private List<Sibling> siblings;

    public SiblingModel(Player player) {
      setPlayer(player);
    }

    private void setPlayer(Player player) {
      if (player == null) {
        throw new NullPointerException();
      } else {
        this.player = player;
        siblings = player.getSiblings();

        player.addPropertyChangeListener(Player.PROPERTY_NAME_SIBLINGS, this);
      }
    }

    @Override
    public int getColumnCount() {
      return COLUMN_NAMES.length;
    }

    @Override
    public int getRowCount() {
      return siblings.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      Sibling sibling = siblings.get(rowIndex);

      switch (columnIndex) {
        case NAME_INDEX:
          return sibling.getName();
        case GENDER_INDEX:
          return sibling.getGender();
        case AGE_INDEX:
          return sibling.getAge();
        case RELATIONSHIP_INDEX:
          return sibling.getRelationship();
        default:
          throw new IllegalArgumentException(
              "Index " + columnIndex + " is invalid as a column index.");
      }
    }

    @Override
    public String getColumnName(int col) {
      return COLUMN_NAMES[col];
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
      Object source = evt.getSource();

      if (source == player) {
        siblings = player.getSiblings();
        fireTableDataChanged();
      }
    }

  }

}
