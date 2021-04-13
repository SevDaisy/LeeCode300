package bytedance_11;

import java.util.Scanner;

public class Main {

  static int beforeA = 'A' - 1;

  static int getStep(char x) {
    if (x >= 'A' && x <= 'Z') {
      return x - beforeA;
    } else if (x >= '0' && x <= '9') {
      return (x - '0');
    } else return 1;
  }

  static char getOne(int step, char x) {
    if (x >= 'A' && x <= 'Z') {
      return (char) (beforeA + (x - beforeA + step) % 26);
    } else if (x >= '0' && x <= '9') {
      return (char) ('0' + (x - '0' + step) % 10);
    } else return x;
  }

  public static void main(String[] args) {
    StringBuilder primBuilder = new StringBuilder();
    try (Scanner in = new Scanner(System.in)) {
      while (in.hasNextLine()) {
        if (primBuilder.toString().length() > 0) {
          primBuilder.append('\n');
        }
        primBuilder.append(in.nextLine());
      }
    }
    String primLine = primBuilder.toString();
    if (primLine.length() < 1) return;

    int step = getStep(primLine.charAt(0));
    StringBuilder outBuilder = new StringBuilder();
    for (int i = 0; i < primLine.length(); i++) {
      char t = getOne(step, primLine.charAt(i));
      outBuilder.append(t);
    }
    System.out.println(outBuilder.toString());
  }
}
