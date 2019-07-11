import java.util.concurrent.Semaphore;

class Phil {
    static public Integer c = 0;

    static private Semaphore sem = new Semaphore(1);

    public void eat() throws InterruptedException {
        sem.acquire();
        c += 1;
        sem.release();
    }
}