import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private String clientUsername;
    private int clientId;


    public ClientHandler(Socket socket, int clientId) {
        try {
            this.socket = socket;
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            this.clientId = clientId;
            this.clientUsername = "anonymous" + this.clientId;
            clientHandlers.add(this);

            Message messageServer = new Message(
                    "SERVER",
                    "@enter " + clientUsername,
                    0
            );
            broadcastMessage(messageServer);
        } catch (IOException e) {
            closeEverything(socket, objectOutputStream, objectInputStream);
            System.out.println("\033[93mWARNING: " + this.socket.getRemoteSocketAddress() + " has invalid client\033[39m");
        }
    }


    @Override
    public void run() {
        Message messageFromClient;

        if (socket.isConnected()) {
            try {
                Message initMessage = new Message(
                        "SERVER",
                        "@init:" + this.clientUsername + ":" + this.clientId,
                        0
                );
                this.objectOutputStream.writeObject(initMessage);
                this.objectOutputStream.flush();
            } catch (IOException e) {
                System.out.println("\033[93mWARNING: " + this.socket.getRemoteSocketAddress() + " has invalid client\033[39m");
                closeEverything(socket, objectOutputStream, objectInputStream);
            }
        }

        while (socket.isConnected()) {
            try {
                messageFromClient = (Message) objectInputStream.readObject();
                System.out.println(messageFromClient);
                if (messageFromClient.usernameId == 0) {
                    System.out.println("\033[91mWARNING: " + this.socket.getRemoteSocketAddress() + " has invalid ID\033[39m");
                    closeEverything(socket, objectOutputStream, objectInputStream);
                }
                broadcastMessage(messageFromClient);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("\033[93mWARNING: " + this.socket.getRemoteSocketAddress() + " has invalid client\033[39m");
                closeEverything(socket, objectOutputStream, objectInputStream);
                break;
            }
        }
    }


    public void broadcastMessage(Message messageToSend) {
        ArrayList<ClientHandler> clientsForSending = clientHandlers;
        // QUIT command
        if (messageToSend.messageText.equals("@quit")) {
            Message enterMessage = new Message(
                    "SERVER",
                    "@left:" + this.clientUsername,
                    0
            );
            for (ClientHandler clientHandler: clientsForSending) {
                try {
                    clientHandler.objectOutputStream.writeObject(enterMessage);
                    clientHandler.objectOutputStream.flush();
                } catch (IOException e) {
                    closeEverything(socket, objectOutputStream, objectInputStream);
                }
            }
            closeEverything(socket, objectOutputStream, objectInputStream);
            // NAME command
        } else if (messageToSend.messageText.startsWith("@name ")) {
            this.clientUsername = messageToSend.messageText.split(" ")[1];
            Message initMessage = new Message(
                    "SERVER",
                    "@name:" + this.clientUsername,
                    0
            );
            try {
                this.objectOutputStream.writeObject(initMessage);
                this.objectOutputStream.flush();
            } catch (IOException e) {
                closeEverything(socket, objectOutputStream, objectInputStream);
            }
        } else if (messageToSend.messageText.startsWith("@enter ")) {
            this.clientUsername = messageToSend.messageText.split(" ")[1];
            Message enterMessage = new Message(
                    "SERVER",
                    "@enter:" + this.clientUsername,
                    0
            );
            for (ClientHandler clientHandler: clientsForSending) {
                try {
                    clientHandler.objectOutputStream.writeObject(enterMessage);
                    clientHandler.objectOutputStream.flush();
                } catch (IOException e) {
                    closeEverything(socket, objectOutputStream, objectInputStream);
                }
            }
        } else {
            Message message = new Message(
                    this.clientUsername,
                    messageToSend.messageText,
                    this.clientId
            );
            if (messageToSend.messageText.startsWith("@")) {
                String reciender = messageToSend.messageText.split(" ")[0].substring(1);
                int findedUsernames = 0;
                for (ClientHandler clientHandler: clientsForSending) {
                    try {
                        if (!clientHandler.clientUsername.equals(clientUsername) &&
                                clientHandler.clientUsername.equals(reciender)
                        ) {
                            findedUsernames++;
                            clientHandler.objectOutputStream.writeObject(message);
                            clientHandler.objectOutputStream.flush();
                        }
                    } catch (IOException e) {
                        closeEverything(socket, objectOutputStream, objectInputStream);
                    }
                }
                if (findedUsernames == 0) {
                    for (ClientHandler clientHandler: clientsForSending) {
                        try {
                            if (!clientHandler.clientUsername.equals(clientUsername)) {
                                clientHandler.objectOutputStream.writeObject(message);
                                clientHandler.objectOutputStream.flush();
                            }
                        } catch (IOException e) {
                            closeEverything(socket, objectOutputStream, objectInputStream);
                        }
                    }
                }
            } else {
                for (ClientHandler clientHandler: clientsForSending) {
                    try {
                        if (!clientHandler.clientUsername.equals(clientUsername)) {
                            clientHandler.objectOutputStream.writeObject(message);
                            clientHandler.objectOutputStream.flush();
                        }
                    } catch (IOException e) {
                        closeEverything(socket, objectOutputStream, objectInputStream);
                    }
                }
            }
        }
    }


    public void removeClientHandler() {
        clientHandlers.remove(this);
    }


    public void closeEverything(
            Socket socket,
            ObjectOutputStream objectOutputStream,
            ObjectInputStream objectInputStream
    ) {
        removeClientHandler();
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
}
