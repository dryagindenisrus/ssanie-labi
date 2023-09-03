import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Использование: java Client <адрес-сервера> <порт-сервера>");
            return;
        }

        String serverAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress serverInetAddress = InetAddress.getByName(serverAddress);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in, "Cp866"));

            System.out.println("Введите ваше имя с '@name': ");
            String username = userInput.readLine();
            sendMessage(username, serverInetAddress, serverPort, socket);
            System.out.println("You connected.");
            username = username.split(" ")[1];

            while (true) {
                System.out.print(username + " (You): ");
                String message = userInput.readLine();

                if (message.equals("@quit")) {
                    sendMessage(message, serverInetAddress, serverPort, socket);
                    break;
                } else {
                    sendMessage(message, serverInetAddress, serverPort, socket);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendMessage(String message, InetAddress serverAddress, int serverPort, DatagramSocket socket) throws IOException {
        byte[] sendData = message.getBytes(StandardCharsets.UTF_8);
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
        socket.send(sendPacket);
    }
}
