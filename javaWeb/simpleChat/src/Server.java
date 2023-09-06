import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Objects;
import java.util.Scanner;

public class Server {

    private final DatagramSocket datagramSocket;
    private byte[] buffer = new byte[256];
    private InetAddress inetClientAddress;
    private int clientPort;
    private String username = "Server";

    public Server(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    public void receiveMessages() {
        while (true) {
            try {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);

                datagramSocket.receive(datagramPacket);
                this.inetClientAddress = datagramPacket.getAddress();
                this.clientPort = datagramPacket.getPort();

                System.out.println(this.inetClientAddress + ":" + this.clientPort);
                String messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println(messageFromClient);

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void sendMessages() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                String messageToClient = scanner.nextLine();

                this.buffer = messageToClient.getBytes();

                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                InetAddress clientAddress = this.inetClientAddress;
                String messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());



                if (Objects.equals(messageToClient.split(" ")[0], "@name")) {
                    this.username = messageToClient.split(" ")[1];
                } else if (Objects.equals(messageToClient.split(" ")[0], "@quit")) {
                    System.out.println("\u001B[0m\u001B[44m SERVER will be shutdown... \u001B[0m");
                    System.exit(130);
                } else {
                    messageToClient = this.username + ": " + messageFromClient;
                    byte[] messageBytes = messageToClient.getBytes();
                    DatagramPacket replyPacket = new DatagramPacket(
                            messageBytes,
                            messageBytes.length,
                            this.inetClientAddress,
                            this.clientPort
                    );
                    datagramSocket.send(replyPacket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws SocketException {
        DatagramSocket datagramSocket = new DatagramSocket(1234);
        Server server = new Server(datagramSocket);
        System.out.println("Server is running...");

        Thread sendThread = new Thread(server::sendMessages);
        sendThread.start();

        // Запускаем поток для приема сообщений
        server.receiveMessages();
    }
}
