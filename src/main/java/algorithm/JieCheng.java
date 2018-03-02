package algorithm;

/**
 * 递归,阶乘
 */
public class JieCheng {
    public static void main(String args[]) {

        System.out.println(calcute(6));
    }

    private static int calcute(int item) {
        if (item == 1) {
            return 1;
        } else {
            return item * calcute(item - 1);
        }
    }
}
