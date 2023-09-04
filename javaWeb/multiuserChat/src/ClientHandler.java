import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;
    private int clientNumber;

    private String[] colors = {
            "\u001B[31m",
            "\u001B[32m",
            "\u001B[33m",
            "\u001B[34m",
            "\u001B[35m",
            "\u001B[36m"
    };

    public ClientHandler(Socket socket, int clientNumber) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            this.clientUsername = bufferedReader.readLine();
            this.clientNumber = clientNumber;
            clientHandlers.add(this);
            sendMessageAll("\u001B[0m\u001B[44m SERVER:: " + clientUsername + " enter. \u001B[0m");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    private String getUsername() {
        return this.clientUsername;
    }

    @Override
    public void run() {
        String messageFromClient;

        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    public void broadcastMessage(String messageToSend) {
        if (messageToSend.split("::")[1].startsWith("@")) {
            List<ClientHandler> filtered = clientHandlers.stream()
                    .filter(clientHandler -> clientHandler.getUsername().equals(messageToSend.split("::")[1].substring(1).split(" ")[0]))
                    .toList();
            sendMessage(messageToSend.split("::")[0], messageToSend.split("::")[1], filtered);
        } else {
            sendMessageAll(messageToSend);
        }
//        Server.printToConsole(messageToSend);
//        LOGS to server
    }

    public void sendMessageAll(String messageToSend) {
        for (ClientHandler clientHandler: clientHandlers) {
            try {
                if (!clientHandler.clientUsername.equals(clientUsername)) {
                    String messageForSending = colors[clientNumber % colors.length] +
                            messageToSend.split("::")[0] + ": \u001B[0m" +
                            messageToSend.split("::")[1];
                    clientHandler.bufferedWriter.write(messageForSending);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public void sendMessage(String initiator, String messageToSend, List<ClientHandler> clients) {
        if (!clients.isEmpty()) {
            for (ClientHandler clientHandler: clients) {
                try {
                    if (!clientHandler.clientUsername.equals(clientUsername)) {
                        String messageForSending = colors[clientNumber % 7] + initiator + "-> you: \u001B[0m" + messageToSend;
                        clientHandler.bufferedWriter.write(messageForSending);
                        clientHandler.bufferedWriter.newLine();
                        clientHandler.bufferedWriter.flush();
                    }
                } catch (IOException e) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        }

    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
        sendMessageAll("\u001B[0m\u001B[44m SERVER:: " + clientUsername + " left. \u001B[0m");
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClientHandler();
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
