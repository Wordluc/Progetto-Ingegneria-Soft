package TestConnessione;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    private ArrayList<ClientHandeler>clientHandelers;

    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
        clientHandelers = new ArrayList<>();
    }

    public void serverStart(){
        while (!serverSocket.isClosed()){
            try {
                serverMsg("[SERVER] Waiting client connection");
                Socket client = serverSocket.accept();
                serverMsg("[SERVER] Client connected");

                ClientHandeler c = new ClientHandeler(client, Server.this);
                clientHandelers.add(c);
                Thread clientHandeler = new Thread(c);
                clientHandeler.start();
                
                broardCast("[SERVER] " + c.name + " has joined the chat", c);

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void quit(ClientHandeler client){
        String msg = client.name+" has left the chat";
        broardCast(msg, client);
        serverMsg("[SERVER] Client "+client.name+" disconnected");
        clientHandelers.remove(client);

    }
    public void serverMsg(String msg){
        System.out.println(msg);
    }
    public void broardCast(String msg, ClientHandeler client){
        for(ClientHandeler c : clientHandelers ) {
            //System.out.println(msg);
            if(!c.equals(client))
                c.sendMessage(msg);
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1111);
        Server server = new Server(serverSocket);
        server.serverStart();
    }
}
