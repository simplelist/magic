package currency;

import com.xiaoleilu.hutool.util.NumberUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class PrimerFinder {
    /**
     * 是否是素数
     *
     * @param num
     * @return
     */
    private static boolean isPrime(int num) {
        return NumberUtil.isPrimes(num);
    }

    protected static int countPrimesInRange(int start, int end) {
        int total = 0;
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                total += 1;
            }
        }
        return total;
    }

    private static int availableCPU() {
        return Runtime.getRuntime().availableProcessors();
    }

    protected static int countPrimesInRangeMulti(int start, int end) {
        int total = 0;
        final int numberOfPart = availableCPU();
        final List<Callable<Integer>> partitions = new ArrayList<>();
        int chunksPer = end / numberOfPart;
        for (int i = 0; i < numberOfPart; i++) {
            final int lower = (i * chunksPer) + 1;
            final int upper = (i == chunksPer - 1) ? end : lower + chunksPer - 1;
            partitions.add(() -> countPrimesInRange(lower, upper));
        }

        final ExecutorService executorService = Executors.newFixedThreadPool(numberOfPart);
        try {
            List<Future<Integer>> futures = executorService.invokeAll(partitions, 10000, TimeUnit.SECONDS);
            executorService.shutdown();
            for (Future<Integer> future : futures) {
                total += future.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    private static void timeAndCompute(final int lower, final int higher) {
        long start = System.nanoTime();
        int countPrimes = countPrimesInRangeMulti(lower, higher);
//        int countPrimes = countPrimesInRange(lower,higher);
        long end = System.nanoTime();

        System.out.printf("number of under %d is %d \n", higher, countPrimes);
        long token = end - start;
        System.out.println("Time token is " + token + " >>  " + token / 1.e9);
    }

    public static void main(String args[]) {
        timeAndCompute(1, 10000000);
    }


}
