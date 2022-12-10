package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    protected ArrayList<Client>clients;
    private ClientManager clientManager;
    public Server(ServerSocket serverSocket){
        this.serverSocket=serverSocket;
        clients = new ArrayList<>();
        clientManager = new ClientManager(this);
        new Thread(clientManager).start();
    }
    public void serverStart() throws IOException {
        while(!serverSocket.isClosed()){
            System.out.println("[SERVER] Attesa connessione client");
            Socket socket = serverSocket.accept();
            System.out.println("[SERVER] Client connesso");

            clients.add(new Client(socket, clientManager));
            clients.get(clients.size()-1).listenForMessage();

        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2222);
        Server server = new Server(serverSocket);
        server.serverStart();
    }
}
