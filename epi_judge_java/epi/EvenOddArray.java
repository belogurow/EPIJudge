package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class EvenOddArray {

  public static void evenOdd(List<Integer> A) {
    Integer oddIdx = null;
    for (int i = 0, len = A.size(); i < len - 1; i++) {
      if (A.get(i) % 2 == 1 && oddIdx == null) {
        oddIdx = i;
      }

      if (A.get(i + 1) % 2 == 0) {
        if (oddIdx != null) {
          int temp = A.get(oddIdx);
          A.set(oddIdx, A.get(i + 1));
          A.set(i + 1, temp);

          oddIdx++;
        }
      }
    }
    return;
  }

  @EpiTest(testDataFile = "even_odd_array.tsv")
  public static void evenOddWrapper(TimedExecutor executor, List<Integer> A)
      throws Exception {
    List<Integer> before = new ArrayList<>(A);
    executor.run(() -> evenOdd(A));

    boolean inOdd = false;
    for (int i = 0; i < A.size(); i++) {
      if (A.get(i) % 2 == 0) {
        if (inOdd) {
          throw new TestFailure("Even elements appear in odd part");
        }
      } else {
        inOdd = true;
      }
    }
    List<Integer> after = new ArrayList<>(A);
    Collections.sort(before);
    Collections.sort(after);
    if (!before.equals(after)) {
      throw new TestFailure("Elements mismatch");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvenOddArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
