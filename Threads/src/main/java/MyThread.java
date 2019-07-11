public class MyThread implements Runnable {

    private Integer number;

    public void run() {
        Printer p = new Printer();
        System.out.println("Номер потока: " + this.number);
        if (this.number == 0) {
            System.out.println("Состояние 0-го потока во время работы run(): " + Thread.currentThread().getState());
        }
        p.ink();
        try {
            Phil ph = new Phil();
            ph.eat();
        } catch (InterruptedException e) {}
    }

    public MyThread(Integer i) {
        this.number = i;
    }
}
