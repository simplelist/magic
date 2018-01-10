package currency;

import java.io.File;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentTotalFileSizeWithQueue {
    AtomicLong pendingFileVisits = new AtomicLong();
    private ExecutorService service;
    private BlockingQueue<Long> fileSizes = new ArrayBlockingQueue<>(500);

    public static void main(String args[]) throws InterruptedException {
        long start = System.nanoTime();
        long totalSizeOfFile = new ConcurrentTotalFileSizeWithQueue().getTotalSizeOfFile("/Users/kun");

        long end = System.nanoTime();
        System.out.println("Total size " + totalSizeOfFile);
        long token = end - start;
        System.out.println("Time token is " + token + " >>  " + token / 1.e9);
    }

    private void startExploreDir(File file) {
        pendingFileVisits.incrementAndGet();
        service.execute(() -> exploreDir(file));
    }

    private void exploreDir(File file) {
        long fileSize = 0;
        if (file.isFile()) {
            fileSize = file.length();
        } else {
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    if (child.isFile()) {
                        fileSize += child.length();
                    } else {
                        startExploreDir(child);
                    }
                }
            }
        }
        try {
            fileSizes.put(fileSize);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pendingFileVisits.decrementAndGet();
    }

    private long getTotalSizeOfFile(final String fileName) throws InterruptedException {
        service = Executors.newFixedThreadPool(100);
        try {

            startExploreDir(new File(fileName));
            long totalSize = 0;
            while (pendingFileVisits.get() > 0 || fileSizes.size() > 0) {
                long size = fileSizes.poll(10, TimeUnit.SECONDS);
                totalSize += size;
            }
            return totalSize;
        } finally {
            service.shutdown();
        }
    }
}
