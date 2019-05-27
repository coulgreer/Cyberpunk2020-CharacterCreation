package rpg.cyberpunk._2020.gui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

/**
 * Allows for an object that takes in an instance of an AbstractAction to switch a card of an
 * instance of Container.
 */
public class SwitchToCardAction extends AbstractAction {
  private static final long serialVersionUID = 1L;

  private Container container;
  private String cardName;

  /**
   * Constructs an Action that allows for an instance of Container to switch to a specified card
   * using the given card name. The container passed in MUST use an instance of CardLayout.
   * 
   * @param text the text to be put on the Component
   * @param icon the image to be put on the Component if applicable
   * @param description a short blurb giving info on the concept of the Component
   * @param mnemonic a shortcut to be used when accessing the Component
   * @param container the parent of the cards to switch to
   * @param cardName the name of the specific Component to switch to
   */
  public SwitchToCardAction( //
      String text, ImageIcon icon, //
      String description, Integer mnemonic, //
      Container container, String cardName) {

    super(text, icon);
    putValue(SHORT_DESCRIPTION, description);
    putValue(MNEMONIC_KEY, mnemonic);

    setCardName(cardName);
    setCardLayout(container);
  }

  private void setCardLayout(Container container) {
    if (!(container.getLayout() instanceof CardLayout)) {
      throw new IllegalArgumentException(
          "Only instances of Container using instances of CardLayout are applicable for this action.");
    } else {
      this.container = container;
    }
  }

  private void setCardName(String cardName) {
    if (cardName == null) {
      setEnabled(false);
    } else {
      this.cardName = cardName;
    }
  }

  @Override
  public void actionPerformed(ActionEvent evt) {
    CardLayout layout = (CardLayout) container.getLayout();
    layout.show(container, cardName);
  }

}
