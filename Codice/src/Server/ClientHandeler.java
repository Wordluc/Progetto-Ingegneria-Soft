package Server;

import java.io.IOException;
import java.util.Scanner;

//--- "Interfaccia"
public class ClientHandeler implements Runnable{
    private static Server server;
    public ClientHandeler(Server server){
        this.server = server;
    }

    public Client checkClient(Client client){
        for(Client c : server.client ) {
            if(c.equals(client))
                return c;
        }
        return null;
    }
    //--- Disconnessione client
    public static void quit(Client client){
        server.client.remove(client);
    }
    public static void console(String msg){
        System.out.println(msg);
    }
    public static void broardCast(String msg){
        for(Client c : server.client ) {
            c.sendMessage(msg);
        }
    }



    public void serverClose(){
        try {
            String msg = "!close";
            broardCast(msg);
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
            broardCast(msg);
        }
    }
}
