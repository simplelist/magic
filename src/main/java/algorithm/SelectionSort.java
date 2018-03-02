package algorithm;

/**
 * 选择排序
 */
public class SelectionSort {
    public static void main(String args[]) {

        int[] arr = {82, 56, 1, 89, 53, 63, 71};
        sort(arr);
    }

    private static void sort(int[] arr) {
        print(arr);
        System.out.println();
        for (int i = 0; i < arr.length - 1; i++) {
            int k = i;
            for (int j = k + 1; j < arr.length; j++) {
                if (arr[j] < arr[k]) {
                    k = j;
                }
            }
            if (i != k) {
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
            }
            System.out.print(" 第" + i + "趟:");
            print(arr);
            System.out.println();
        }
    }

    private static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " , ");
        }
    }
}
