package epi;

import java.util.ArrayList;
import java.util.List;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IntAsArrayMultiply {
  @EpiTest(testDataFile = "int_as_array_multiply.tsv")
  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
    boolean isNegative = num1.get(0) * num2.get(0) < 0;
    num1.set(0, Math.abs(num1.get(0)));
    num2.set(0, Math.abs(num2.get(0)));

    List<Integer> result = new ArrayList<>();

    List<Integer> first, second;
    if (num1.size() < num2.size()) {
      first = num1;
      second = num2;
    } else {
      first = num2;
      second = num1;
    }

    if (first.get(0) == 0) {
      return List.of(0);
    }

    int shift = 0, carry = 0;
    for (int j = first.size() - 1; j >= 0; j--) {
      Integer f = first.get(j);
      carry = 0;
      int i = result.size() - 1 - shift;

      for (int k = second.size() - 1; k >= 0; k--) {
        Integer s = second.get(k);
        int num = f * s + carry;

        if (i < 0) {
          result.add(0, num % 10);
        } else {
          num += result.get(i);
          result.set(i, num % 10);
        }

        carry = num / 10;
        i--;
      }

      if (carry != 0) {
        result.add(0, carry);
      }


      shift++;
    }

    if (isNegative) {
      result.set(0, result.get(0) * -1);
    }

    return result;
  }

  public static void main(String[] args) {
//    multiply(Arrays.asList(9, 2, 9), Arrays.asList(2));

    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayMultiply.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
