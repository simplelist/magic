package algorithm;

/**
 * 二分查找法
 */
public class BinarySearch {
    public static void main(String args[]) {
        int[] arr = {1, 2, 3, 4, 5, 6, 9, 11, 15};
        System.out.println(search(arr, 7));
    }

    private static int search(int[] array, int item) {
        int low = 0;
        int high = array.length - 1;
        int mid, guess;
        while (low <= high) {
            mid = (low + high) / 2;
            guess = array[mid];
            if (guess == item) {//猜测的刚好就是要搜索的
                return mid;
            }
            if (guess < item) {
                //猜的小了
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
