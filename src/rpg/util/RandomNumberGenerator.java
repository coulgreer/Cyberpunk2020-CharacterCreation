package rpg.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberGenerator {

  public static int getRandomWeightedIntegerFrom(Map<Integer, Integer> weightsByInteger) {
    Map<Integer, Integer> accumulatedWeightsByInteger =
        new LinkedHashMap<Integer, Integer>(weightsByInteger.size());
    int totalWeight = 0;

    for (Map.Entry<Integer, Integer> entry : weightsByInteger.entrySet()) {
      int weight = entry.getValue();
      totalWeight += weight;
      accumulatedWeightsByInteger.put(entry.getKey(), totalWeight);
    }

    double randomNum = ThreadLocalRandom.current().nextInt(0, totalWeight);
    for (Map.Entry<Integer, Integer> entry : accumulatedWeightsByInteger.entrySet()) {
      int accumulatedWeight = entry.getValue();
      if ((randomNum - accumulatedWeight) < 0) {
        return entry.getKey();
      }
    }

    return 0;
  }

}
