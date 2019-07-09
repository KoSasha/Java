import java.util.concurrent.locks.ReentrantLock;

public class Printer {

    // sinchronized

//    public synchronized void ink() {
//        a += 1;
//    }
//
//    static public Integer a = 0;


    // volatile

//    public void ink() {
//        a += 1;
//    }
//
//    static public volatile Integer a = 0;


    // Reentrantlock

    public void ink() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        a += 1;
        lock.unlock();
    }

    static public Integer a = 0;
}
