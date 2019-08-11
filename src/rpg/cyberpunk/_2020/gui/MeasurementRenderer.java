package rpg.cyberpunk._2020.gui;

import java.text.DecimalFormat;
import javax.swing.table.DefaultTableCellRenderer;
import rpg.util.Measurement;

public class MeasurementRenderer extends DefaultTableCellRenderer {
  private static final long serialVersionUID = 1L;

  /**
   * Constructs a renderer that forces data to be right aligned.
   */
  public MeasurementRenderer() {
    super();

    setHorizontalAlignment(RIGHT);
  }

  @Override
  public void setValue(Object value) {
    Measurement m = (Measurement) value;
    double amount = m.getAmount();
    String str = "";

    if (Math.abs(amount) <= 0.99) {
      DecimalFormat df = new DecimalFormat("#.##");
      str = df.format(amount);
    } else if (Math.abs(amount) <= 0.9) {
      DecimalFormat df = new DecimalFormat("#.#");
      str = df.format(amount);
    } else {
      DecimalFormat df = new DecimalFormat("#");
      str = df.format(amount);
    }

    setText(str + m.getUnit().getName());
  }

}
