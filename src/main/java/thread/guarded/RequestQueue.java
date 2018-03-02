package thread.guarded;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RequestQueue {
    private Queue<Request> q = new ConcurrentLinkedQueue<>();

    public synchronized Request getRequest() {
        try {
            while (q.peek() == null) {
                wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return q.remove();
    }

    public synchronized void putRequest(Request request) {
        notify();
        q.offer(request);
    }
}
