public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Runnable r = new MyThread(i);
            Thread t = new Thread(r);
            t.start();
        }
    }
}
