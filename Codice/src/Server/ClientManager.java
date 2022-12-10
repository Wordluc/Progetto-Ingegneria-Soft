package Server;

import java.io.IOException;
import java.util.Scanner;

public class ClientManager implements Runnable {
    private static Server server;

    public ClientManager(Server server){
        this.server = server;

    }
    public static void broardCast(String msg) throws IOException {
        for(Client c : server.clients ) {

            c.sendMessage(msg);
        }
    }
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String msg = sc.nextLine();
            try {
                broardCast(msg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
