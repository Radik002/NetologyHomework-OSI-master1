import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        var threadForServer = new Thread(new SocketServerRunner());
        threadForServer.start();
        var threadForClient = new Thread(new SocketRunner());
        threadForClient.start();
        System.out.println("Для окончания работы введите <end>");
    }
}
