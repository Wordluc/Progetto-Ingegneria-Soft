package Server;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable {
    private Socket client;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Server server;
    public Client(Socket client, Server server){
        try {
            this.client = client;
            this.reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            this.server = server;
        }catch (IOException e){
            closeClient();
        }
    }

    public void sendMessage(String msg){
        switch (msg){
            case "!close":{
                write(msg);
                closeClient();
            }
            default:
                write(msg);
            }
    }
    public void write(String msg){
        try {
            writer.write(msg);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void closeClient(){
        try {
            client.close();
            reader.close();
            writer.close();
        }catch (IOException e){
            System.out.println(e.getStackTrace());
        }
    }
    //--- Ricevo richieste dal client
    @Override
    public void run() {
        while(client.isConnected()) {
            try {
                String msg = reader.readLine();

                switch(msg){
                    case "!quit":{
                        closeClient();
                        ClientHandeler.quit(this);
                        break;
                    }
                    default:
                        server.clientHandeler.broardCast(msg);
                }

            } catch (IOException e) {
                closeClient();
            }
        }
    }
}
