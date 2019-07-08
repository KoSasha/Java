package kosasha;

import java.io.FileWriter;
import java.math.BigInteger;

public class BigI {
    public static void main(String[] args) throws Exception {
        BigInteger a = new BigInteger("2");
        BigInteger result = a.pow(1000);
        FileWriter fw = new FileWriter("src/main/resources/bigi.txt", false);
        fw.write("Результат возведения числа 2 в степень 1000:\n\n");
        fw.write(result.toString());
        fw.close();
    }
}
