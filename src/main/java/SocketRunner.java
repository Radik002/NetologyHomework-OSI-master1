import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class SocketRunner implements Runnable {

    @Override
    public void run() {
        InetAddress inetAddress = null;
        try {
            inetAddress = Inet4Address.getByName("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        try (var socket = new Socket(inetAddress, 7777);
             var outputStream = new DataOutputStream(socket.getOutputStream());
             var inputStream = new DataInputStream(socket.getInputStream());
             var scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
//                System.out.println("\nInput you message (Type <end> for exit) >> ");
                var request = scanner.nextLine();
                outputStream.writeUTF(request);
                System.out.println("CLIENT: Receive from server: " + inputStream.readUTF());
            }
        } catch (IOException e) {
            System.out.println("Сервер отвалился");
        }

    }
}
