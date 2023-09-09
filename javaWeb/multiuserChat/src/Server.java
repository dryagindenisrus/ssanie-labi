import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Integer.parseInt;

public class Server {

    private final ServerSocket serverSocket;
    private int clientCounter = 1;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("SERVER: " + socket.getRemoteSocketAddress() + " connected");
                ClientHandler clientHandler = new ClientHandler(socket, clientCounter);
                clientCounter++;
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {

        }
    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.out.println("\033[101mERROR: missing arguments:\033[49m --port");
            System.exit(-1);
        } else {
            try {
                ServerSocket serverSocket = new ServerSocket(parseInt(args[0]));
                Server server = new Server(serverSocket);
                System.out.println("Server is running...");
                server.startServer();
            } catch (NumberFormatException e) {
                System.out.println("\033[101mERROR: invalid arguments:\033[49m --port");
                System.exit(-1);
            }

        }
    }
}