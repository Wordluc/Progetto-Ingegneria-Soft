import java.io.*;
import java.net.Socket;

public class ClientHandeler implements Runnable {
    private Socket client;
    private BufferedReader reader;
    private BufferedWriter writer;
    public String name;
    private Server server;
    public ClientHandeler (Socket client, Server server){
        try {
            this.client = client;
            this.reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            this.name = reader.readLine();
            this.server = server;
        }catch (IOException e){
            closeClientHandeler();
        }
    }
    public void sendMessage(String msg){
        try{
            writer.write(msg);
            writer.newLine();
            writer.flush();
        }catch(IOException E){
            closeClientHandeler();
        }

    }
    public void closeClientHandeler(){
        try {
            client.close();
            reader.close();
            writer.close();
        }catch (IOException e){
            System.out.println(e.getStackTrace());
        }
    }
    @Override
    public void run() {
        while(client.isConnected()) {
            try {
                String msg = reader.readLine();
               // if(msg.contains("!say")){
                    server.broardCast(name+": "+msg, this);
                //}
                //System.out.println(msg);
            } catch (IOException e) {
                closeClientHandeler();
            }
        }

    }
}
