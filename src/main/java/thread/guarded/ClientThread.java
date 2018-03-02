package thread.guarded;

import java.util.Random;

public class ClientThread extends Thread {

    private final RequestQueue queue;

    private final Random random;

    public ClientThread(String name, RequestQueue queue, long seed) {
        super(name);
        this.queue = queue;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Request request = new Request("No." + i);
            queue.putRequest(request);
            System.out.println(request + " has been put ");
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
