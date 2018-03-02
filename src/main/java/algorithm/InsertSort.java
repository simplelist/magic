package algorithm;

import java.util.Arrays;


/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String args[]) {

        int[] arr = {8, 5, 1, 89, 53, 63, 71};
        Arrays.stream(sort(arr)).forEach(System.out::println);
    }

    private static int[] sort(int[] arr) {
        int resultArray[] = new int[arr.length];
        int min = arr[0], minIndex = 0;
        for (int i = 0; i < resultArray.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            resultArray[i] = arr[minIndex];
        }

        return resultArray;
    }
}
