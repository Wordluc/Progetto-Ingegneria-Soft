package Server;

import Common.PacketClient;
import Common.PacketServer;

import java.io.*;
import java.net.Socket;

public class Client {
    Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private OutputStream outputStream;
    private InputStream inputStream;
    private ClientManager clientManager;
    public Client(Socket socket, ClientManager clientManager) throws IOException {
        this.socket = socket;
        this.clientManager=clientManager;
        outputStream = socket.getOutputStream();
        output = new ObjectOutputStream(outputStream);
        inputStream = socket.getInputStream();
        input = new ObjectInputStream(inputStream);
    }
    public void sendMessage(String msg) throws IOException {
        output.writeObject(new PacketServer(msg));
    }
    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()){
                    try {
                        PacketClient p = (PacketClient) input.readObject();
                        clientManager.broardCast(p.getMessage());
                        
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }


                }
            }
        }).start();
    }

}
