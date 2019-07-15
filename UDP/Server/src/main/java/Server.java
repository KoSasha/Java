import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(4040);
        while (true) {
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
            ds.receive(packet);
            System.out.println(new String(packet.getData()));
        }
    }
}
