package Client;

import Common.PacketClient;
import Common.PacketServer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private OutputStream outputStream;
    private InputStream inputStream;
    private boolean wait;


    public SocketClient(Socket socket) throws IOException {
        this.socket = socket;
        outputStream = socket.getOutputStream();
        output = new ObjectOutputStream(outputStream);
        inputStream = socket.getInputStream();
        input = new ObjectInputStream(inputStream);
        wait = false;
    }

    public void listenMessage() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (socket.isConnected() && !wait) {

                    try {

                        PacketServer p = (PacketServer) input.readObject();

                        if (p.getCommand() != null) {

                            action(p);
                        }
                    } catch (IOException e) {
                        close();
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }

            }
        }).start();
    }

    public void readLine() throws IOException {
        Scanner sc = new Scanner(System.in);
        while (socket.isConnected()) {

            String msg = sc.nextLine();
            try {
                sendMessage(msg);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void sendMessage(PacketClient p) {
        try {
            output.writeObject(p);
        } catch (IOException e) {
            close();
        }
    }

    public void sendMessage(String msg) {
        try {
            output.writeObject(new PacketClient(msg));
        } catch (IOException e) {
            close();
        }
    }

    //--- Azioni
    private void action(PacketServer p) throws Exception {
        switch (p.getCommand()) {
            case "test": {
                System.out.println(p.toString());
                sendMessage("Client -> c");
                p = (PacketServer) input.readObject();
                System.out.println(p.toString());
                sendMessage("Client -> e");
                p = (PacketServer) input.readObject();
                System.out.println(p.toString());
                break;
            }


            default:
                System.out.println(p.toString());
        }
    }


    public void close() {
        try {
            output.close();
            outputStream.close();
            input.close();
            inputStream.close();
            socket.close();
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 2222);
        SocketClient socketClient = new SocketClient(socket);
        socketClient.listenMessage();
        socketClient.readLine();
    }
}
