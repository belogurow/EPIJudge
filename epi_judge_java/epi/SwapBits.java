package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SwapBits {
  @EpiTest(testDataFile = "swap_bits.tsv")
  public static long swapBits(long x, int i, int j) {
    long iBit = x >> i & 1;
    long jBit = x >> j & 1;

    if (iBit == jBit) {
      return x;
    }

    long bitMask = (1L << i) | (1L << j);
    return x ^ bitMask;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SwapBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
