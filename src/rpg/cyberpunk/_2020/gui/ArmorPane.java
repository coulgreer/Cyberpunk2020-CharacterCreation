package rpg.cyberpunk._2020.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import rpg.cyberpunk._2020.Player;
import rpg.cyberpunk._2020.combat.CyberpunkArmor;
import rpg.general.combat.BodyLocation;

/**
 * A panel that displays both a model of the character and displays a list of armor pieces the
 * character is wearing.
 */
public class ArmorPane extends JPanel implements PropertyChangeListener, Selectable {
  private static final int borderThickness = 2;
  private static final long serialVersionUID = 1L;

  private Player player;
  private JLabel label;
  private JTable table;
  private SelectionMediator mediator;

  /**
   * Constructs an ArmorPanel that updates when notified by a Player or the SelectionMediator is
   * changed. Also, creates child panels that create a display of the player and the list of a
   * player's armor pieces.
   * 
   * @param player the owner of the equipped armor set
   * @param selectionMediator manages the selection of panels and allows this ArmorPanel to add
   *        itself to the selectionMediator
   */
  public ArmorPane(Player player, SelectionMediator selectionMediator) {
    super(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder( //
        borderThickness, borderThickness, //
        borderThickness, borderThickness));
    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent evt) {
        selectionMediator.setActive(ArmorPane.this);
      }
    });
    setPlayer(player);
    setSelectionMediator(selectionMediator);

    add(createArmorDisplayComponent(), BorderLayout.WEST);
    add(createArmorListComponent(player), BorderLayout.CENTER);
    add(createArmorSummaryContent(), BorderLayout.SOUTH);
  }

  private void setPlayer(Player player) {
    if (player == null) {
      throw new NullPointerException();
    } else {
      this.player = player;
      player.addPropertyChangeListener(Player.PROPERTY_NAME_EQUIPMENT_ARMOR, this);
    }
  }

  private void setSelectionMediator(SelectionMediator mediator) {
    if (mediator == null) {
      throw new NullPointerException();
    } else {
      this.mediator = mediator;
      mediator.registerColleague(this);
    }
  }

  private Component createArmorDisplayComponent() {
    JPanel panel = new JPanel();

    BufferedImage myPicture;
    try {
      myPicture = ImageIO.read(new File("./img/armor-display-512.png"));
      JLabel picLabel = new JLabel(new ImageIcon(myPicture));
      panel.add(picLabel);
    } catch (IOException e) {
      // TODO (Coul Greer): Add a logger to help handle this exception.
      e.printStackTrace();
    }

    return panel;
  }

  private Component createArmorListComponent(Player player) {
    table = new EquippedArmorTable(player);
    table.setFillsViewportHeight(true);
    table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    JScrollPane scrollPane = new JScrollPane(table);
    for (MouseListener listener : getMouseListeners()) {
      table.addMouseListener(listener);
    }

    return scrollPane;
  }

  private Component createArmorSummaryContent() {
    JPanel panel = new JPanel();

    label = new JLabel(generateArmorSummary());
    panel.add(label);

    return panel;
  }

  private String generateArmorSummary() {
    return "TOTAL Encumbrance Value: " + player.getEncumbranceValue() //
        + " \u2022 " + "Head SP: " + player.getLocationDurability(BodyLocation.HEAD) //
        + " \u2022 " + "L. Arm SP: " + player.getLocationDurability(BodyLocation.LEFT_ARM) //
        + " \u2022 " + "R. Arm SP: " + player.getLocationDurability(BodyLocation.RIGHT_ARM) //
        + " \u2022 " + "Torso SP: " + player.getLocationDurability(BodyLocation.TORSO) //
        + " \u2022 " + "L. Leg SP: " + player.getLocationDurability(BodyLocation.LEFT_LEG) //
        + " \u2022 " + "R. Leg SP: " + player.getLocationDurability(BodyLocation.RIGHT_LEG);
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    Object source = evt.getSource();

    if (source == player) {
      label.setText(generateArmorSummary());
    }
  }

  @Override
  public boolean isSelected() {
    return this == mediator.getSelected();
  }

  @Override
  public void setSelected(boolean isSelected) {
    if (isSelected) {
      setBorder(BorderFactory.createMatteBorder( //
          borderThickness, borderThickness, borderThickness, borderThickness, //
          Color.CYAN));
    } else {
      setBorder(BorderFactory.createEmptyBorder( //
          borderThickness, borderThickness, //
          borderThickness, borderThickness));
    }
  }

  /**
   * Returns the CyberpunkArmor selected from the table of equipped armors.
   * 
   * @return the selected CyberpunkArmor from the child table
   */
  public CyberpunkArmor getSelectedArmor() {
    return (CyberpunkArmor) table.getModel().getValueAt(table.getSelectedRow(),
        EquippedArmorTable.EquippedArmorTableModel.OBJECT_INDEX);
  }

}
