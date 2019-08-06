package rpg.cyberpunk._2020.gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import rpg.Gender;
import rpg.cyberpunk._2020.Player;
import rpg.cyberpunk._2020.Sibling;
import rpg.cyberpunk._2020.Sibling.RelativeAge;

/**
 * A table used to display all the data stored within an instance of Sibling.
 */
public class SiblingTable extends JTable {
  private static final long serialVersionUID = 1L;

  /**
   * Constructs a table that uses <code>SiblingTable.Model</code> as its default model.
   * 
   * @param player the provider of the data to display. Handed off to the default
   *        <code>SiblingTable.Model</code> for use.
   */
  public SiblingTable(Player player) {
    super(new Model(player));

    getTableHeader().setReorderingAllowed(false);
    getTableHeader().setResizingAllowed(false);

    TableColumn genderColumn = getColumnModel().getColumn(Model.GENDER_INDEX);
    genderColumn.setCellEditor(new DefaultCellEditor( //
        new JComboBox<Gender>(Gender.values())));

    TableColumn ageColumn = getColumnModel().getColumn(Model.AGE_INDEX);
    ageColumn.setCellEditor(new DefaultCellEditor( //
        new JComboBox<RelativeAge>(RelativeAge.values())));
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

  /**
   * An instance of AbstractTableModel that listens to lists of Siblings.
   */
  public static class Model extends AbstractTableModel implements ListChangeListener<Sibling> {
    public static final int NAME_INDEX = 0;
    public static final int GENDER_INDEX = 1;
    public static final int AGE_INDEX = 2;
    public static final int RELATIONSHIP_INDEX = 3;

    private static final long serialVersionUID = 1L;
    private static final String[] columnName = { //
        "Name", //
        "Gender", //
        "Age", //
        "Relationship"};

    private Player player;
    private ObservableList<Sibling> siblings;

    /**
     * Constructs an instance of SiblingModel that takes in a non-null Player. The Player then
     * provides a list for this model to observe and use to populate a table.
     * 
     * @param player the provider of data to be displayed
     */
    public Model(Player player) {
      setPlayer(player);
    }

    private void setPlayer(Player player) {
      if (player == null) {
        throw new NullPointerException();
      } else {
        this.player = player;
        siblings = player.getSiblings();
        siblings.addListener(this);
      }
    }

    @Override
    public int getColumnCount() {
      return columnName.length;
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
    public void setValueAt(Object data, int rowIndex, int columnIndex) {
      Sibling oldSibling = siblings.get(rowIndex);

      String name = oldSibling.getName();
      Gender gender = oldSibling.getGender();
      RelativeAge age = oldSibling.getAge();
      String relationship = oldSibling.getRelationship();

      switch (columnIndex) {
        case NAME_INDEX:
          name = (String) data;
          break;
        case GENDER_INDEX:
          gender = (Gender) data;
          break;
        case AGE_INDEX:
          age = (RelativeAge) data;
          break;
        case RELATIONSHIP_INDEX:
          relationship = (String) data;
          break;
        default:
          throw new IllegalArgumentException(
              "Index " + columnIndex + " is invalid as a column index.");
      }

      // TODO (Coul Greer): Think about a better way to update the collection of siblings. Currently
      // adding and removing siblings causes a new one to be placed at the bottom causing the user
      // to always look at the bottom element in the table. Can become annoying/confusing when the
      // list of siblings gets longer or when updating multiple things for one sibling.
      player.removeSibling(oldSibling);
      player.addSibling(new Sibling(name, gender, age, relationship));
    }

    @Override
    public String getColumnName(int col) {
      return columnName[col];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
      return true;
    }

    @Override
    public void onChanged(Change<? extends Sibling> c) {
      while (c.next()) {
        if (c.wasRemoved() || c.wasAdded()) {
          siblings = player.getSiblings();
          fireTableDataChanged();
        }
      }
    }
  }

}
