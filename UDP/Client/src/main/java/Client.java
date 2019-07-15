import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) throws Exception {
        byte[] data = "Hello".getBytes();
        InetAddress address = InetAddress.getLocalHost();
        DatagramPacket pack = new DatagramPacket(data, data.length, address, 4040);
        DatagramSocket ds = new DatagramSocket();
        ds.send(pack);
        ds.close();
    }
}