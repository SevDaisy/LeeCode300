package BaseNode;

public abstract class util {

  public static <E, T extends Iterable<E>> void errPrintList(
    T list,
    String name
  ) {
    System.err.print(name + " is ");
    for (E x : list) {
      System.err.print(" " + x);
    }
    System.err.println();
  }
}
