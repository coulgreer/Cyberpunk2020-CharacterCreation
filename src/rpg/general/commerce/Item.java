package rpg.general.commerce;

import java.io.Serializable;
import rpg.util.Measurement;

/**
 * An interface that is allowed to be bought or sold because of the ability to obtain the cost
 * associated. There is also a name and description to help identify this item. Also, a weight is
 * associated for use when storing and carrying this object.
 */
public interface Item extends Tradeable, Serializable {
  /**
   * Gets the name of this product.
   * 
   * @return a string representing the name of this product
   */
  public String getName();

  /**
   * Gets the description of this product.
   * 
   * @return a string used to give a description of this product
   */
  public String getDescription();

  /**
   * Gets the weight of this product.
   * 
   * @return the weight of this product
   */
  public Measurement getWeight();
}
