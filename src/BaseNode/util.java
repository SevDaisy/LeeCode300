package BaseNode;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class util {

  public static void main(String[] args) {
    ArrayList<Integer> arrayList = new ArrayList<Integer>() {
      {
        for (int i = 0; i < 3; i++) {
          add(i);
        }
      }
    };
    arrayList.forEach(x -> System.err.print(" " + x));
    System.err.println();
    errPrintList(Arrays.asList(new int[] { 1, 2, 3 }), "test");
  }

  /**
   * 打印 可遍历 的对象
   * @param <E> 节点·数据类型
   * @param <T> <code>T extends Iterable<E></code> 集合·数据类型
   * @param list 如果参数是数组，应该改为 <code>Arrays.asList(array)</code>
   * @param name <code>name + " is "</code>
   */
  public static <E, T extends Iterable<E>> void errPrintList(
    T list,
    String name
  ) {
    System.err.print(name + " is ");
    list.forEach(x -> System.err.print(" " + (E) x));
    System.err.println();
  }
}
