import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Objects;
import java.util.Random;

public class ServerBot {

    private final DatagramSocket datagramSocket;
    private byte[] buffer = new byte[256];
    private InetAddress inetClientAddress;
    private int clientPort;
    public boolean botActive = false;
    private String username = "Bot";
    private int minRange = 1;
    private int maxRange = 100;
    private int valBot;
    private boolean guessing = false;

    public ServerBot(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    public void receiveMessage() {
        while (true) {
            try {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);

                datagramSocket.receive(datagramPacket);
                this.inetClientAddress = datagramPacket.getAddress();
                this.clientPort = datagramPacket.getPort();

                String messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());

                if (messageFromClient.split(" ")[1].equals("@bot")) {
                    this.botActive = true;
                    System.out.println("Bot activated");
                } else {
                    System.out.println(messageFromClient);
                }

                if (botActive && !guessing) {
                    startGuessingGame();
                }

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void sendMessage(String messageToClient) {
        try {
            this.buffer = messageToClient.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            InetAddress clientAddress = this.inetClientAddress;
            String messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
            if (Objects.equals(messageToClient.split(" ")[0], "@name")) {
                this.username = messageToClient.split(" ")[1];
            } else if (Objects.equals(messageToClient.split(" ")[0], "@quit")) {
                messageToClient = "\u001B[0m\u001B[44m SERVER will be shutdown... \u001B[0m";
                buffer = messageToClient.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, this.inetClientAddress, this.clientPort);
                datagramSocket.send(sendPacket);
                System.out.println("\u001B[0m\u001B[44m SERVER will be shutdown... \u001B[0m");
                System.exit(130);
            } else {
                messageToClient = "\033[92m" + this.username + ":\033[39m " + messageFromClient;
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

    private void startGuessingGame() {
        valBot = 50;
        this.minRange = 1;
        this.maxRange = 100;
        this.guessing = true;

        sendMessage("Think of a number between 1 and 100.");
        sendMessage("Is the number greater than " + valBot + "? (yes/no)");
        waitForUserResponse();
    }

    private void waitForUserResponse() {
        while (true) {
            try {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);

                String messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength()).toLowerCase();

                if (messageFromClient.contains("yes")) {
                    minRange = valBot + 1;
                    System.out.println(">");
                } else if (messageFromClient.contains("no")) {
                    maxRange = valBot;
                    System.out.println("<");
                } else {
                    sendMessage("Please enter 'yes' or 'no'.");
                    continue;
                }

                if (minRange > maxRange) {
                    sendMessage("You seem to be confused. Let's start over.");
                    startGuessingGame();
                } else if (minRange == maxRange) {
                    sendMessage("Your number is " + minRange + "! I guessed it!");
                    guessing = false;
                } else {
                    valBot = (minRange + maxRange) / 2;
                    sendMessage("Is the number greater than " + valBot + "? (yes/no)");
                    waitForUserResponse();
                }

                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws SocketException {
        if (args.length != 1) {
            System.out.println("\033[101mERROR: missing arguments:\033[49m --port");
            System.exit(-1);
        } else {
            try {
                DatagramSocket datagramSocket = new DatagramSocket(Integer.parseInt(args[0]));
                ServerBot serverBot = new ServerBot(datagramSocket);
                System.out.println("Server is running...");

                serverBot.receiveMessage();
            } catch (NumberFormatException e) {
                System.out.println("\033[101mERROR: invalid arguments:\033[49m --port");
                System.exit(-1);
            }
        }
    }
}
