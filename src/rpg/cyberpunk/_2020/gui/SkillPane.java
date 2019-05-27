package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;

/**
 * A Panel used to display the details of a skill.
 */
public class SkillPane extends JPanel implements PropertyChangeListener {
  private static final int iconHeight = 16;
  private static final long serialVersionUID = 1L;

  private CyberpunkSkill skill;
  private JLabel valueLabel;

  /**
   * Constructs a SkillPanel that uses a <code>CyberpunkSkill</code> to populate its display. This
   * includes the skill's name, current level, and improvement points data. The level may be
   * manipulated by the buttons provided there are no restrictions stopping it.
   * 
   * @param skill the object used to derive data for display and that is manipulated by the buttons
   */
  public SkillPane(CyberpunkSkill skill) {
    super(new GridLayout(1, 2));
    setSkill(skill);

    add(createSkillSummaryContent());
    add(createSkillDetailContainer());
  }

  private void setSkill(CyberpunkSkill skill) {
    if (skill == null) {
      throw new NullPointerException();
    } else {
      this.skill = skill;
      skill.addPropertyChangeListener(this);
    }
  }

  private Component createSkillSummaryContent() {
    JPanel panel = new JPanel(new BorderLayout());

    JLabel label;
    String text = skill.getName();
    final int maxCharacters = 22;

    if (text.length() > maxCharacters) {
      text = text.substring(0, maxCharacters - 2) + "...";
      label = new JLabel(text, SwingConstants.LEFT);
      label.setToolTipText(skill.getName());
    } else {
      label = new JLabel(text, SwingConstants.LEFT);
    }

    panel.add(label);

    return panel;
  }

  private Container createSkillDetailContainer() {
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;

    constraints.gridx = 0;
    constraints.gridy = 0;
    panel.add(createLevelComponent(), constraints);

    constraints.gridx = 1;
    panel.add(createIpComponent(), constraints);

    return panel;
  }

  private Component createLevelComponent() {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createTitledBorder( //
        BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK), //
        "Level"));

    valueLabel = new JLabel(getValueText());
    valueLabel.setFont(new Font("Serif", Font.PLAIN, 20));
    valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(valueLabel);
    panel.add(createButtonContainer());

    return panel;
  }

  private String getValueText() {
    return Integer.toString(skill.getTotalValue());
  }

  private Container createButtonContainer() {
    JPanel panel = new JPanel(new GridLayout(2, 1));

    try {
      BufferedImage bufferedImage = ImageIO.read(new File("img/increase-arrow-minor-64x64.png"));
      ImageIcon icon = new ImageIcon(
          bufferedImage.getScaledInstance(iconHeight, iconHeight, Image.SCALE_SMOOTH));
      JButton button = new JButton(icon);
      button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
      button.addActionListener(evt -> skill.increaseLevel());
      panel.add(button);
    } catch (IOException ex) {
      // TODO (Coul Greer): Add a logger.
      System.out.println(ex);
    }

    try {
      BufferedImage bufferedImage = ImageIO.read(new File("img/decrease-arrow-minor-64x64.png"));
      ImageIcon icon = new ImageIcon(
          bufferedImage.getScaledInstance(iconHeight, iconHeight, Image.SCALE_SMOOTH));
      JButton button = new JButton(icon);
      button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
      button.addActionListener(evt -> skill.decreaseLevel());
      panel.add(button);
    } catch (IOException ex) {
      // TODO (Coul Greer): Add a logger.
      System.out.println(ex);
    }

    return panel;
  }

  private Component createIpComponent() {
    JPanel panel = new JPanel(new GridLayout(1, 3));
    panel.setBorder(BorderFactory.createTitledBorder( //
        BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK), //
        "IP"));

    panel.add(createCurrentIpContent());
    panel.add(createDividerContent());
    panel.add(createGoalIpContent());

    return panel;
  }

  private Component createCurrentIpContent() {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add(new JLabel("Current", SwingConstants.CENTER), BorderLayout.NORTH);

    JLabel label =
        new JLabel(Integer.toString(skill.getCurrentImprovementPoints()), SwingConstants.CENTER);
    panel.add(label, BorderLayout.CENTER);

    return panel;
  }

  private Component createDividerContent() {
    JPanel panel = new JPanel(new BorderLayout());

    JLabel label = new JLabel("/", SwingConstants.CENTER);
    label.setFont(new Font("Serif", Font.PLAIN, 25));
    panel.add(label);

    return panel;
  }

  private Component createGoalIpContent() {
    JPanel panel = new JPanel(new BorderLayout());

    panel.add(new JLabel("Goal", SwingConstants.CENTER), BorderLayout.NORTH);
    JLabel label =
        new JLabel(Integer.toString(skill.getNeededImprovementPoints()), SwingConstants.CENTER);
    panel.add(label);

    return panel;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    Object source = evt.getSource();

    if (source == skill) {
      valueLabel.setText(getValueText());
    }
  }

}
