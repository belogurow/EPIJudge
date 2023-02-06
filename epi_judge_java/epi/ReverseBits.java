package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseBits {
  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
    long newX = 0;

    int bitCountInLong = 64;

    while (bitCountInLong != 0) {
      newX <<= 1;
      long bit = x & 1;
      newX |= bit;
      x >>= 1;

      bitCountInLong--;
    }

    return newX;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
