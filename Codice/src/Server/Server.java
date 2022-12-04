package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    protected ServerSocket serverSocket;
    protected ArrayList<Client>client;
    protected Room room;
    protected ClientHandeler clientHandeler;

    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
        client = new ArrayList<>();
        room = new Room("Alberto");

        clientHandeler = new ClientHandeler(this);
        new Thread(clientHandeler).start();

    }
    public void serverStart(){
        //--- Attesa connesione client
        while (!serverSocket.isClosed()){
            try {
                System.out.println("[SERVER] Waiting client connection");
                Socket socket = serverSocket.accept();
                System.out.println("[SERVER] Client connected");

                client.add(new Client(socket, this));
                new Thread(client.get(client.size()-1)).start();


            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2222);
        Server server = new Server(serverSocket);
        server.serverStart();
    }
}
