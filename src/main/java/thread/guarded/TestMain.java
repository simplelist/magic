package thread.guarded;

public class TestMain {

    public static void main(String args[]) {
        RequestQueue queue = new RequestQueue();
        new ClientThread("Alice", queue, 500).start();
        new ServerThread("Bob", queue, 500).start();
    }
}
