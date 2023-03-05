import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class SocketServerRunner implements Runnable {
    @Override
    public void run() {
        try (var serverSocket = new ServerSocket(7777);
             var socket = serverSocket.accept();
             var outputStream = new DataOutputStream(socket.getOutputStream());
             var inputStream = new DataInputStream(socket.getInputStream());
             var scanner = new Scanner(System.in)) {
            var request = inputStream.readUTF();
            while (!"end".equals(request)) {
                System.out.println("SERVER: Received from Client: " + request + " Port: " + socket.getPort());
//                var response = scanner.nextLine();
                outputStream.writeUTF("Server FWD: " + request);
                request = inputStream.readUTF();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
