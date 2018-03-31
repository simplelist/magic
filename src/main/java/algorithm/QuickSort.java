package algorithm;

public class QuickSort {

    public static void main(String args[]) {
        int[] arr = {82, 56, 1, 89, 53, 63, 71};
        sort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " , ");
        }
    }


    private static void sort(int[] array, int left, int right) {
        int f, t;
        int rtemp, ltemp;
        ltemp = left;
        rtemp = right;

        f = array[(left + right) / 2];

        while (ltemp < rtemp) {
            while (array[ltemp] < f) {
                ++ltemp;
            }
            while (array[rtemp] > f) {
                --rtemp;
            }

            if (ltemp <= rtemp) {
                t = array[ltemp];
                array[ltemp] = array[rtemp];
                array[rtemp] = t;
                --rtemp;
                ++ltemp;
            }
        }

        if (ltemp == rtemp) {
            ltemp++;
        }
        if (left < rtemp) {
            sort(array, left, ltemp - 1);
        }
        if (ltemp < right) {
            sort(array, rtemp + 1, right);
        }
    }
}
