import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Client {
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private String username;
    private ArrayList<String> banned;
    private int userId = 1;

    private final String[] colors = {
            "\u001B[31m",
            "\u001B[32m",
            "\u001B[33m",
            "\u001B[34m",
            "\u001B[35m",
            "\u001B[36m"
    };

    public Client(Socket socket) {
        try {
            this.socket = socket;
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            this.banned = new ArrayList<String>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageText = scanner.nextLine();

                if (messageText.equals("@quit")) {
                    Message messageToSend = new Message(
                            this.username,
                            "@quit",
                            this.userId);
                    objectOutputStream.writeObject(messageToSend);
                    objectOutputStream.flush();
                    System.exit(130);
                } else if (messageText.startsWith("@ban")) {
                    this.banned.add(messageText.split(" ")[1]);
                } else {
                    Message messageToSend = new Message(
                            this.username,
                            messageText,
                            this.userId);
                    objectOutputStream.writeObject(messageToSend);
                    objectOutputStream.flush();
                }
            }

        } catch (IOException e) {
            // closeEverything(socket, objectOutputStream, objectInputStream);
        }
    }

    public void listenForMessage() {
        new Thread(() -> {
            Message receivedMessage;

            while (true) {
                try {
                    receivedMessage = (Message) objectInputStream.readObject();
                    if (receivedMessage.usernameId == 0) {
                        if (receivedMessage.messageText.startsWith("@name")) {
                            this.username = receivedMessage.messageText.split(":")[1];
                        } else if (receivedMessage.messageText.startsWith("@init")) {
                            this.username = receivedMessage.messageText.split(":")[1];
                            this.userId = parseInt(receivedMessage.messageText.split(":")[2]);
                            System.out.println(
                                    "\u001B[0m\u001B[44m " +
                                            receivedMessage.username +
                                            ": You authorized how " +
                                            receivedMessage.messageText.split(":")[1] +
                                            ". \u001B[0m"

                            );
                        } else if (receivedMessage.messageText.startsWith("@enter")) {
                            System.out.println(
                                    "\u001B[0m\u001B[44m " +
                                            receivedMessage.username +
                                            " " +
                                            receivedMessage.messageText.split(":")[1] +
                                            " enter. \u001B[0m");
                        } else if (this.banned.contains(receivedMessage.username)) {
                            break;
                            // короче проверить есить ли имя отправителя в забаненных и не принимать
                            // сообщение и не выводить
                        } else if (receivedMessage.messageText.startsWith("@left")) {
                            System.out.println(
                                    "\033[97m\033[103m " +
                                            receivedMessage.username +
                                            ": " +
                                            receivedMessage.messageText.split(":")[1] +
                                            " left. \u001B[0m\033[49m");
                        }
                    } else {
                        if (receivedMessage.messageText.startsWith("@" + this.username)) {
                            if (!this.banned.contains(receivedMessage.username)) {
                                System.out.println(
                                        colors[receivedMessage.usernameId % 6] +
                                                receivedMessage.username +
                                                "(-> you): \033[39m" +
                                                receivedMessage.messageText.substring(this.username.length() + 1));
                            }
                        } else {
                            if (!this.banned.contains(receivedMessage.username)) {
                                System.out.println(
                                        colors[receivedMessage.usernameId % 6] +
                                                receivedMessage.username + ":\033[39m " +
                                                receivedMessage.messageText);
                            }

                        }
                    }
                } catch (IOException e) {
                    closeEverything(socket, objectOutputStream, objectInputStream);
                    break;
                } catch (ClassNotFoundException e) {
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, ObjectOutputStream objectOutputStream,
            ObjectInputStream objectInputStream) {
        try {
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.out.println("\033[101mERROR: missing arguments:\033[49m --host --port");
            System.exit(-1);
        } else {
            try {
                System.out.println("================================");
                Scanner scanner = new Scanner(System.in);
                Socket socket = new Socket(args[0], parseInt(args[1]));
                Client client = new Client(socket);
                System.out.println("\u001B[34;43m Client entering... \u001B[0m");
                System.out.println("================================");

                client.listenForMessage();
                client.sendMessage();
            } catch (NumberFormatException e) {
                System.out.println("\033[101mERROR: invalid arguments:\033[49m --port");
                System.exit(-1);
            }
        }
    }
}
