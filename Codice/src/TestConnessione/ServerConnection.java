package TestConnessione;

import java.io.IOException;
import java.util.Scanner;


public class ServerConnection implements Runnable{
    private static Server server;
    public ServerConnection(Server server){
        this.server = server;
    }
    //--- Disconnessione client
    public static void quit(ClientHandeler client){
        String msg = client.name+" has left the chat";
        broardCast(msg, client);
        console("[SERVER] Client "+client.name+" disconnected");
        server.clientHandelers.remove(client);

    }
    public static void console(String msg){
        System.out.println(msg);
    }
    public static void broardCast(String msg, ClientHandeler client){
        for(ClientHandeler c : server.clientHandelers ) {
            //System.out.println(msg);
            if(!c.equals(client))
                c.sendMessage(msg);
        }
    }
    public void serverClose(){
        try {
            String msg = "!close";
            broardCast(msg, null);
            server.serverSocket.close();
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String msg = sc.nextLine();
            System.out.println(msg);
        }
    }
}
