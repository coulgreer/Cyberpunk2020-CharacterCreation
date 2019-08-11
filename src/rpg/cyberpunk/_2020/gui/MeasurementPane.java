package rpg.cyberpunk._2020.gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import rpg.general.stats.Attribute;
import rpg.util.Measurement;
import rpg.util.Measurement.Type;
import rpg.util.Measurement.Unit;

public class MeasurementPane extends JPanel implements PropertyChangeListener {
  public static final Font DEFAULT_FONT = new Font("Serif", Font.PLAIN, 20);

  private static final long serialVersionUID = 1L;

  private JLabel dataLabel;
  private Attribute attribute;
  private Type type;
  private Unit unit;

  public MeasurementPane(Attribute attribute, Type type, Unit unit) {
    super(new GridLayout(0, 2));
    setAttribute(attribute);
    setType(type);
    setUnit(unit);

    add(createTitleComponent());
    add(createDataComponent());
  }

  private void setAttribute(Attribute attribute) {
    if (attribute == null) {
      throw new NullPointerException();
    } else {
      this.attribute = attribute;
      attribute.addPropertyChangeListener(this);
    }
  }

  private void setType(Type type) {
    if (type == null) {
      throw new NullPointerException();
    } else {
      this.type = type;
    }
  }

  private void setUnit(Unit unit) {
    if (unit == null) {
      throw new NullPointerException();
    } else {
      this.unit = unit;
    }
  }

  private Component createTitleComponent() {
    JLabel label = new JLabel(attribute.getName(), SwingConstants.LEFT);
    label.setFont(DEFAULT_FONT);
    return label;
  }

  private Component createDataComponent() {
    dataLabel = new JLabel(createFormattedMeasurement(), SwingConstants.RIGHT);
    dataLabel.setFont(DEFAULT_FONT);
    return dataLabel;
  }

  private String createFormattedMeasurement() {
    Measurement m = new Measurement(type, attribute.getModifier(), unit);
    DecimalFormat df = new DecimalFormat("#.##");
    return df.format(m.getAmount()) + m.getUnit().getName();
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    Object source = evt.getSource();

    if (source == attribute) {
      dataLabel.setText(createFormattedMeasurement());
    }
  }

}
