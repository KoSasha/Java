public class MyThread implements Runnable {

    private Integer number;

    public void run() {
        System.out.println(this.number);
    }

    public MyThread(Integer i) {
        this.number = i;
    }
}
