package rpg.general.commerce;

import java.io.Serializable;

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
	public double getWeight();
}
