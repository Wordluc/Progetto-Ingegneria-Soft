package TestConnessione;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    protected ServerSocket serverSocket;
    protected ServerConnection serverConnection;
    protected ArrayList<ClientHandeler>clientHandelers;

    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
        clientHandelers = new ArrayList<>();

    }
    public void serverStart(){
        serverConnection = new ServerConnection(Server.this);
        new Thread(serverConnection).start();
        //--- Attesa connesione client
        while (!serverSocket.isClosed()){
            try {
                serverConnection.console("[SERVER] Waiting client connection");
                Socket client = serverSocket.accept();
                serverConnection.console("[SERVER] Client connected");

                //ClientHandeler c = new ClientHandeler(client, serverConnection);
                //clientHandelers.add(c);
                //Thread clientHandeler = new Thread(c);
                //clientHandeler.start();


                clientHandelers.add(new ClientHandeler(client, serverConnection));
                new Thread(clientHandelers.get(clientHandelers.size()-1)).start();

                //serverConnection.broardCast("[SERVER] " + c.name + " has joined the chat", c);

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
