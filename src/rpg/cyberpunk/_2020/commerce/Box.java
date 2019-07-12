package rpg.cyberpunk._2020.commerce;

import java.util.List;
import rpg.general.commerce.Item;

/**
 * An interface that allows items to be stored together.
 * 
 * @param <E> an element that is an instance of <code>Item</code> that is allowed to be stored and
 *        quantified
 */
public interface Box<E extends Item> extends Item {

  /**
   * @return the number of the stored items
   */
  public int getQuantity();

  /**
   * @return the stored items as a List
   */
  public List<E> getItems();

}
