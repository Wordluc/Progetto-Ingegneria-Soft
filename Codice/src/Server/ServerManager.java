package Server;

import java.util.Scanner;

public class ServerManager implements Runnable {
    private Server server;

    public ServerManager(Server server) {
        this.server = server;
        new Thread(this).start();
    }

    public void broardCast(String msg) {
        for (Client c : server.clients)
            c.sendMessage(msg);
    }


    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (!server.serverSocket.isClosed()) {
            String msg = sc.nextLine();
            broardCast(msg);
        }
    }
}
