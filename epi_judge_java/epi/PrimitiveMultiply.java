package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveMultiply {
  @EpiTest(testDataFile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {
    long result = 0;
    long shift = 0;

    while (y != 0) {
      if ((y & 1) == 1) {
        result = sum(result, x << shift);
      }
      y >>= 1;
      shift++;
    }

    return result;
  }

  private static long sum(long x, long y) {
    if (x == 0) {
      return y;
    } else if (y == 0) {
      return x;
    }

    long carryOut = 0;
    long result = 0;
    long shift = 0;
    while (x != 0 || y != 0) {
      long newBit = (x & 1) ^ (y & 1);
      if (carryOut == 1) {
        if (newBit == 1) {
          // 1 + 1 = 0, 1 бит запоминаем
        } else {
          // 1 + 0 = 1, 0 бит запоминаем
          result |= carryOut << shift;
          carryOut = (x & 1) & (y & 1);
        }
      } else {
        // если переноса нет, то просто добавляем новый бит
        carryOut = (x & 1) & (y & 1);
        result |= newBit << shift;
      }


      x >>= 1;
      y >>= 1;
      shift++;
    }

    result |= carryOut << shift;

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
