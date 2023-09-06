import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Objects;
import java.util.Scanner;

public class Client {

    private final DatagramSocket datagramSocket;
    private final InetAddress serverAddress;
    private byte[] buffer;
    String username = "Client";

    public Client(DatagramSocket datagramSocket, InetAddress serverAddress) {
        this.datagramSocket = datagramSocket;
        this.serverAddress = serverAddress;
    }

    public void sendMessages() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String messageToSend = scanner.nextLine();
                if (Objects.equals(messageToSend.split(" ")[0], "@name")) {
                    this.username = messageToSend.split(" ")[1];
                } else if (Objects.equals(messageToSend.split(" ")[0], "@quit")) {
                    System.out.println("\u001B[0m\u001B[44m CLIENT will be shutdown... \u001B[0m");
                    System.exit(130);
                } else {
                    messageToSend = this.username + ": " + messageToSend;
                    buffer = messageToSend.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, serverAddress, 1234);
                    datagramSocket.send(sendPacket);
                }

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void receiveMessages() {
        while (true) {
            try {
                buffer = new byte[1024]; // Clear the buffer for receiving
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(receivePacket);
                String messageFromServer = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println(messageFromServer);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");
        Client client = new Client(datagramSocket, serverAddress);

        System.out.println("Send message: ");

        // Создаем и запускаем отдельный поток для отправки сообщений
        Thread sendThread = new Thread(client::sendMessages);
        sendThread.start();

        // Запускаем поток для приема сообщений
        client.receiveMessages();
    }
}
