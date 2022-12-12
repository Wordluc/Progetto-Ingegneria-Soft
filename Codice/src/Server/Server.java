package Server;

import Game.RoomManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    protected ServerSocket serverSocket;
    protected ArrayList<Client> clients;
    protected RoomManager roomManagers;
    private ServerManager serverManager;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        clients = new ArrayList<>();
        roomManagers = new RoomManager();
        serverManager = new ServerManager(this);
    }

    public void serverStart() throws IOException {
        while (!serverSocket.isClosed()) {
            System.out.println("[SERVER] Attesa connessione client");
            Socket socket = serverSocket.accept();
            System.out.println("[SERVER] Client connesso");

            clients.add(new Client(socket, this));
            clients.get(clients.size() - 1).listenForMessage();
        }
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2222);
        Server server = new Server(serverSocket);
        server.serverStart();
    }
}
