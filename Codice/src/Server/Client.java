package Server;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable {
    private Socket client;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Server server;
    private Player player;
    private Room room;

    public Client(Socket client, Server server){
        try {
            this.client = client;
            this.reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            this.server = server;
            player = new Player(reader.readLine());
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
        boolean wait = true;
        while(client.isConnected()&&wait) {
            try {
                String msg =  reader.readLine();
                switch(msg){
                    case "!quit":{
                        closeClient();
                        ClientHandeler.quit(this);
                        break;
                    }
                    /*
                    case "!createRoom":{
                        break;
                    }
                    case "!createRoom":{
                        break;
                    }
                    case "!join":{

                        break;
                    }
                    */

                    default:
                        if(player != null) {
                            room.broadCast(player.nome+": "+msg);

                        }

                }

            } catch (IOException e) {
                closeClient();
            }
        }
    }

    public void joinRoom(){

    }
}
