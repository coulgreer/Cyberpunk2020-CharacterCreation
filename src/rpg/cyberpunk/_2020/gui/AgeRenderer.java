package rpg.cyberpunk._2020.gui;

import javax.swing.table.DefaultTableCellRenderer;
import rpg.cyberpunk._2020.Age;

/**
 * An instance of DefaultTableCellRenderer that renders an instance of Age.
 */
public class AgeRenderer extends DefaultTableCellRenderer {
  private static final long serialVersionUID = 1L;

  /**
   * Constructs a renderer that centers and unwraps the age.
   */
  public AgeRenderer() {
    super();

    setHorizontalAlignment(CENTER);
  }

  @Override
  public void setValue(Object value) {
    Age age = (Age) value;

    setText(Integer.toString(age.toInt()));
  }

}
