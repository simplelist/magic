package currency;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinFile {

    private final static ForkJoinPool forkJoinPool = new ForkJoinPool();

    public static void main(String args[]) throws InterruptedException {
        long start = System.nanoTime();
        long totalSizeOfFile = forkJoinPool.invoke(new FileSizeFinder(new File("/Users/kun")));

        long end = System.nanoTime();
        System.out.println("Total size " + totalSizeOfFile);
        long token = end - start;
        System.out.println("Time token is " + token + " >>  " + token / 1.e9);
    }

    private static class FileSizeFinder extends RecursiveTask<Long> {

        final File file;

        public FileSizeFinder(File file) {
            this.file = file;
        }

        @Override
        protected Long compute() {
            long size = 0;
            if (file.isFile()) {
                size = file.length();
            } else {
                final File[] children = file.listFiles();
                if (children != null) {
                    ArrayList<ForkJoinTask<Long>> tasks = new ArrayList<>();
                    for (File child : children) {
                        if (child.isFile()) {
                            size += child.length();
                        } else {
                            tasks.add(new FileSizeFinder(child));
                        }
                    }

                    for (ForkJoinTask<Long> task : invokeAll(tasks)) {
                        size += task.join();
                    }
                }

            }

            return size;
        }
    }
}
