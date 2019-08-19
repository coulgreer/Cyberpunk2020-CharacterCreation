package rpg.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A factory object used to create a random number based on a set of rules within the method.
 */
public class RandomNumberGenerator {

  private RandomNumberGenerator() {}

  /**
   * Takes the min probability and max probability which is determined by the number of faces. Min
   * probability will be the number of die while the max probability is the number of faces
   * multiplied by the number of die. The randomly generated result is then divided by the dividend
   * provided by the die.
   * 
   * @param die the provider of the bounds for the random number generated
   * @return a random double provided
   */
  public static double getRandomDoubleFrom(Die die) {
    int min = die.getDieCount();
    int max = die.getDieCount() * die.getFaceCount();
    int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

    return randomNum / die.getDividend();
  }

  /**
   * Takes a collection of integers with a weighted value associated. Uses the entry's weight out of
   * the total weight in order to provide a value for rolling a random value. The weighted integer
   * is then returned. Returns a negative value if the passed map is empty.
   * 
   * @param weightsByInteger a list of integers with associated weights. There may only be unique
   *        values of integer, however, the weights may be repeated.
   * @return a random integer
   */
  public static int getRandomWeightedIntegerFrom(Map<Integer, Integer> weightsByInteger) {
    Map<Integer, Integer> accumulatedWeightsByInteger =
        new LinkedHashMap<Integer, Integer>(weightsByInteger.size());
    int totalWeight = 0;

    for (Map.Entry<Integer, Integer> entry : weightsByInteger.entrySet()) {
      int weight = entry.getValue();
      totalWeight += weight;
      accumulatedWeightsByInteger.put(entry.getKey(), totalWeight);
    }

    int randomNum = ThreadLocalRandom.current().nextInt(0, totalWeight);
    for (Map.Entry<Integer, Integer> entry : accumulatedWeightsByInteger.entrySet()) {
      int accumulatedWeight = entry.getValue();
      if ((randomNum - accumulatedWeight) < 0) {
        return entry.getKey();
      }
    }

    return -1;
  }

}
