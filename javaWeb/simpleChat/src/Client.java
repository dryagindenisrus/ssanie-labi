import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Client {

    private final DatagramSocket datagramSocket;
    private final InetAddress serverAddress;
    private byte[] buffer;
    private int port;
    String username = "Client";

    public Client(DatagramSocket datagramSocket, InetAddress serverAddress, int port) {
        this.datagramSocket = datagramSocket;
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public void sendMessages() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String messageToSend = scanner.nextLine();
                if (Objects.equals(messageToSend.split(" ")[0], "@name")) {
                    this.username = messageToSend.split(" ")[1];
                } else if (Objects.equals(messageToSend.split(" ")[0], "@quit")) {
                    messageToSend = "\u001B[0m\u001B[44m CLIENT will be shutdown... \u001B[0m";
                    buffer = messageToSend.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, serverAddress, this.port);
                    datagramSocket.send(sendPacket);
                    System.out.println("\u001B[0m\u001B[44m CLIENT will be shutdown... \u001B[0m");
                    System.exit(130);
                } else {
                    messageToSend = "\033[93m" + this.username + ":\033[39m " + messageToSend;
                    buffer = messageToSend.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, serverAddress, this.port);
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

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("\033[101mERROR: missing arguments:\033[49m --host --port");
            System.exit(-1);
        } else {
            try {
                DatagramSocket datagramSocket = new DatagramSocket();
                InetAddress serverAddress = InetAddress.getByName(args[0]);
                Client client = new Client(datagramSocket, serverAddress, parseInt(args[1]));

                System.out.println("Client starting... ");

                // Создаем и запускаем отдельный поток для отправки сообщений
                Thread sendThread = new Thread(client::sendMessages);
                sendThread.start();

                // Запускаем поток для приема сообщений
                client.receiveMessages();
            } catch (NumberFormatException e) {
                System.out.println("\033[101mERROR: invalid arguments:\033[49m --port");
                System.exit(-1);
            }
        }
    }
}
