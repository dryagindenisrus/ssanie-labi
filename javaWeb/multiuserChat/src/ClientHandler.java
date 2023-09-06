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

    private final String[] colors = {
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
            broadcastMessage("\u001B[0m\u001B[44m SERVER:: " + clientUsername + " enter. \u001B[0m", clientHandlers);
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

        if (socket.isConnected()) {

        }

        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                System.out.println("MESSAGE FROM CLIENT: " + messageFromClient);
                this.bufferedWriter.write(messageFromClient);
                this.bufferedWriter.newLine();
                this.bufferedWriter.flush();
                broadcastMessage(messageFromClient, clientHandlers);
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    public void broadcastMessage(String messageToSend, ArrayList<ClientHandler> clients) {
        if (!clients.isEmpty()) {
            for (ClientHandler clientHandler: clients) {
                try {
                    if (!clientHandler.clientUsername.equals(clientUsername)) {
                        String messageForSending = colors[clientNumber % 7] + this.clientUsername + "-> you: \u001B[0m" + messageToSend;
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
        broadcastMessage("\u001B[0m\u001B[44m SERVER:: " + clientUsername + " left. \u001B[0m", clientHandlers);
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
