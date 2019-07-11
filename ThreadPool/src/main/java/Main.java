import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Runnable[] r = new MyThread[10];

        // newCachedThreadPool

//        ExecutorService exec = Executors.newCachedThreadPool();
//        for (int i = 0; i < 10; i++) {
//            r[i] = new MyThread(i);
//            exec.submit(r[i]);
//        }

        // newFixedThreadPool

//        ExecutorService exec = Executors.newFixedThreadPool(10);
//        for (int i = 0; i < 10; i++) {
//            r[i] = new MyThread(i);
//            exec.submit(r[i]);
//        }

        // newScheduledThreadPool

        ExecutorService exec = Executors.newScheduledThreadPool(1000);
        for (int i = 0; i < 10; i++) {
            r[i] = new MyThread(i);
            exec.submit(r[i]);
        }

        exec.shutdown();
        Thread.sleep(2000);
        System.out.println(MyThread.a);
    }
}
