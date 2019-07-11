public class MyThread  implements Runnable{

    static private Integer number;

    static public Integer a = 0;

    public void run() {
        System.out.println("Номер потока: " + this.number);
        a += 1;
    }

    public MyThread(Integer i) {
        this.number = i;
    }
}
