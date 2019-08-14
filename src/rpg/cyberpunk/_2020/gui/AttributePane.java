package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import rpg.cyberpunk._2020.systems.CharacterPointsManager;
import rpg.general.stats.Attribute;

/**
 * A JPanel that listens to an <code>Attribute</code> and allows the manipulation of its level.
 */
public abstract class AttributePane extends JPanel {
  public static final Font DEFAULT_FONT = new Font("Serif", Font.PLAIN, 20);

  private static final String DEFAULT_CONSTRAINT = BorderLayout.NORTH;
  private static final long serialVersionUID = 1L;

  private Attribute attribute;

  private AttributePane(Attribute attribute, Font font, String titleConstraint) {
    super(new BorderLayout());
    setAttribute(attribute);
    setTitlePosition(font, titleConstraint);
  }

  private void setAttribute(Attribute attribute) {
    if (attribute == null) {
      throw new NullPointerException();
    } else {
      this.attribute = attribute;
    }
  }

  private void setTitlePosition(Font font, String constraint) {
    if (font == null) {
      throw new NullPointerException();
    }

    if (constraint.equals(BorderLayout.CENTER) || constraint.equals(null)) {
      constraint = DEFAULT_CONSTRAINT;
    }

    add(createTitleContainer(font, constraint), constraint);
  }

  private Container createTitleContainer(Font font, String constraint) {
    JPanel panel = new JPanel(new GridBagLayout());

    JTextArea textArea = new JTextArea(attribute.getName());
    textArea.setOpaque(false);
    textArea.setEditable(false);
    textArea.setFocusable(false);
    textArea.setBackground(UIManager.getColor("Label.background"));
    textArea.setFont(font);
    textArea.setBorder(UIManager.getBorder("Label.border"));

    if ((BorderLayout.EAST).equals(constraint) //
        || (BorderLayout.WEST).equals(constraint)) {
      textArea.setLineWrap(true);
      textArea.setWrapStyleWord(true);
    }

    panel.add(textArea);

    return panel;
  }

  /**
   * An editor for AttributePane that allows for the incrementation and decrementation of an
   * associated Attribute using a set of buttons.
   */
  public static class Spinner extends AttributePane implements PropertyChangeListener {
    private static final long serialVersionUID = 1L;

    private JLabel valueLabel;

    public Spinner(Attribute attribute) {
      this(attribute, DEFAULT_FONT, DEFAULT_CONSTRAINT);
    }

    public Spinner(Attribute attribute, Font font) {
      this(attribute, font, DEFAULT_CONSTRAINT);
    }

    public Spinner(Attribute attribute, String constraint) {
      this(attribute, DEFAULT_FONT, constraint);
    }

    public Spinner(Attribute attribute, Font font, String constraint) {
      super(attribute, font, constraint);

      attribute.addPropertyChangeListener(this);

      JPanel panel = new JPanel(new GridBagLayout());
      GridBagConstraints gc = new GridBagConstraints();
      gc.anchor = GridBagConstraints.LINE_END;
      panel.add(createEditorComponent(font), gc);
      super.add(panel, BorderLayout.CENTER);
    }

    private JComponent createEditorComponent(Font font) {
      JPanel panel = new JPanel(new GridLayout(0, 2));
      panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

      valueLabel = new JLabel(getValueText());
      valueLabel.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));
      valueLabel.setFont(font);
      panel.add(valueLabel);

      panel.add(createButtonContainer());

      return panel;
    }

    private JComponent createButtonContainer() {
      JPanel panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

      panel.add(createButton( //
          "img/increase-arrow-minor-64x64.png", //
          evt -> super.attribute.incrementLevel()));
      panel.add(createButton( //
          "img/decrease-arrow-minor-64x64.png", //
          evt -> super.attribute.decrementLevel()));

      return panel;
    }

    private JButton createButton(String fileName, ActionListener listener) {
      JButton button = null;

      try {
        Font f = valueLabel.getFont();
        FontMetrics fm = valueLabel.getFontMetrics(f);

        BufferedImage bufferedImage = ImageIO.read(new File(fileName));
        ImageIcon icon = new ImageIcon(bufferedImage.getScaledInstance( //
            fm.getHeight() / 2, //
            fm.getHeight() / 2, //
            Image.SCALE_SMOOTH));
        button = new JButton(icon);
        button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        button.addActionListener(listener);
      } catch (IOException ex) {
        // TODO (Coul Greer): Add a logger.
        System.out.println(ex);
      }

      return button;
    }

    private String getValueText() {
      return Integer.toString(super.attribute.getModifier());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
      String propertyName = evt.getPropertyName();

      if (propertyName.equals(Attribute.PROPERTY_NAME_ATTRIBUTE_LEVEL)) {
        valueLabel.setText(getValueText());
      }
    }

  }

  /**
   * An editor for AttributePane that allows for the incrementation and decrementation of an
   * associated Attribute using a set of values provided.
   */
  public static class ComboBox extends AttributePane implements PropertyChangeListener {
    private static final int MIN_INDEX = 0;
    private static final long serialVersionUID = 1L;

    private int defaultIndex;
    private CharacterPointsManager manager;
    private JComboBox<Integer> comboBox;

    public ComboBox( //
        Attribute attribute, //
        Font font, String titleConstraint, //
        int defaultIndex, //
        CharacterPointsManager manager) {

      super(attribute, font, titleConstraint);

      setDefaultIndex(defaultIndex);
      setManager(manager);
      super.add(createEditorContainer(font), BorderLayout.CENTER);
    }

    private void setDefaultIndex(int defaultIndex) {
      if (defaultIndex < MIN_INDEX) {
        throw new IllegalArgumentException("index = " + defaultIndex + "; min = " + MIN_INDEX);
      } else {
        this.defaultIndex = defaultIndex;
      }
    }

    private void setManager(CharacterPointsManager manager) {
      if (manager == null) {
        throw new NullPointerException();
      } else {
        this.manager = manager;
        manager.addPropertyChangeListener(CharacterPointsManager.PROPERTY_NAME_POINTS, this);
      }
    }

    private Container createEditorContainer(Font font) {
      List<Integer> items = manager.getRolledPoints();

      if (items.contains(null)) {
        throw new IllegalArgumentException("No null values are allowed in the list");
      } else {
        JPanel panel = new JPanel();

        comboBox = new JComboBox<Integer>(items.toArray(new Integer[0]));
        comboBox.setFont(font);
        comboBox.addItemListener(evt -> {
          if (evt.getStateChange() == ItemEvent.SELECTED) {
            Integer item = (Integer) evt.getItem();
            adjustLevel(item);
          }
        });
        panel.add(comboBox);

        return panel;
      }
    }

    private void adjustLevel(int newLevel) {
      while (super.attribute.getLevel() > newLevel) {
        super.attribute.decrementLevel();
      }

      while (super.attribute.getLevel() < newLevel) {
        super.attribute.incrementLevel();
      }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
      Object source = evt.getSource();

      if (manager == source) {
        Integer[] items = manager.getRolledPoints().toArray(new Integer[0]);
        comboBox.setModel(new DefaultComboBoxModel<Integer>(items));
        comboBox.setSelectedIndex(defaultIndex);
      }
    }

    public static class Builder {
      private Attribute attribute;
      private Font font;
      private String titleConstraint;
      private int defaultIndex;
      private CharacterPointsManager manager;

      public Builder(Attribute attribute, CharacterPointsManager manager) {
        this.attribute = attribute;
        this.font = DEFAULT_FONT;
        this.titleConstraint = DEFAULT_CONSTRAINT;
        this.defaultIndex = MIN_INDEX;
        this.manager = manager;
      }

      public Builder withTitleFont(Font font) {
        this.font = font;

        return this;
      }

      public Builder withTitleConstraint(String constraint) {
        this.titleConstraint = constraint;

        return this;
      }

      public Builder withDefaultIndex(int defaultIndex) {
        this.defaultIndex = defaultIndex;

        return this;
      }

      public ComboBox build() {
        return new ComboBox(attribute, font, titleConstraint, defaultIndex, manager);
      }

    }

  }

}
