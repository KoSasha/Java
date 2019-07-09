public class Main {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Runnable r = new MyThread(i);
            Thread t = new Thread(r);
            if (i == 0) {
                System.out.println("Состояние 0-го потока до run(): " + t.getState() + " ");
            }
            t.start();
        }
        Thread.sleep(100);
        System.out.println("\na = " + Printer.a);
    }
}
