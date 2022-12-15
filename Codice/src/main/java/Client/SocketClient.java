package Client;

import Common.PacketClient;
import Common.PacketServer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient  {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private OutputStream outputStream;
    private InputStream inputStream;
    public SocketClient(Socket socket) throws IOException {
        this.socket = socket;
        outputStream = socket.getOutputStream();
        output = new ObjectOutputStream(outputStream);
        inputStream = socket.getInputStream();
        input = new ObjectInputStream(inputStream);
    }
    public void listenMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(socket.isConnected()){
                    try {
                        PacketServer p = (PacketServer) input.readObject();
                        System.out.println(p.getMessage());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                }

            }
        }).start();
    }
    public void sendMessage() throws IOException {
        Scanner sc = new Scanner(System.in);
        while (socket.isConnected()){
            System.out.print("> ");
            String msg = sc.nextLine();
            output.writeObject(new PacketClient(msg));
        }
    }


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 2222);
        SocketClient socketClient = new SocketClient(socket);
        socketClient.listenMessage();
        socketClient.sendMessage();
    }
}
