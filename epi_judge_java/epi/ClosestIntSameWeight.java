package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ClosestIntSameWeight {
  @EpiTest(testDataFile = "closest_int_same_weight.tsv")
  public static long closestIntSameBitCount(long x) {
    int bitCount = bitCount(x);

    if ((x & 1) == 0) {

      // идем вверх
      while (bitCount != bitCount(--x)) {
//        x += 1;
      }
    } else {
      // вниз
      while (bitCount != bitCount(++x)) {
        //
      }
    }
    return x;
  }

  private static int bitCount(long x) {
    int bitCount = 0;

    while (x != 0) {
      bitCount += x & 1;
      x >>= 1;
    }

    return bitCount;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
