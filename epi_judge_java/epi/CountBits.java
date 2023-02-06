package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class CountBits {
  @EpiTest(testDataFile = "count_bits.tsv")
  public static short countBits(int x) {
    short bitsCounts = 0;

    while (x != 0) {
      x &= x - 1;
      bitsCounts++;
    }

    return bitsCounts;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CountBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
