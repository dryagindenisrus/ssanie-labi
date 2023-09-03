import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static DatagramSocket socket;
    private static InetAddress clientAddress;
    private static int clientPort;
    private static String userName = "";
    private static boolean isClientConnected = false;

    private static List<InetAddress> clientAddresses = new ArrayList<>();
    private static List<Integer> clientPorts = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Использование: java Server <порт-сервера>");
            return;
        }

        int serverPort = Integer.parseInt(args[0]);

        try {
            socket = new DatagramSocket(serverPort);
            System.out.println("Сервер запущен на порту " + serverPort);

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
                clientAddress = receivePacket.getAddress();
                clientPort = receivePacket.getPort();

                processMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

    private static void processMessage(String message) throws IOException {
        if (message.startsWith("@name")) {
            userName = message.split(" ")[1];
            isClientConnected = true;
            System.out.println("Вы присоединились к чату как " + userName);
            broadcast("Пользователь " + userName + " присоединился к чату.");
        } else if (message.startsWith("@quit")) {
            isClientConnected = false;
            System.out.println(userName + " покинул чат.");
            broadcast("Пользователь " + userName + " покинул чат.");
            userName = "";
        } else {
            if (isClientConnected) {
                System.out.println(userName + ": " + message);
                broadcast(userName + ": " + message);
            } else {
                System.out.println("Пожалуйста, используйте команду '@name' для установки имени.");
            }
        }
    }

    private static void broadcast(String message) throws IOException {
        if (isClientConnected) {
            byte[] sendData = message.getBytes("UTF-8");
            for (int i = 0; i < clientAddresses.size(); i++) {
                InetAddress address = clientAddresses.get(i);
                int port = clientPorts.get(i);
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
                socket.send(sendPacket);
            }
        }
    }
}
