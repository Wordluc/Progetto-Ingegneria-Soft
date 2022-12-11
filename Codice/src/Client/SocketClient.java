package Client;

import Common.PacketClient;
import Common.PacketServer;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
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
                        System.out.println(p.getMessage());
                    } catch (IOException e) {
                        close();
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                }

            }
        }).start();
    }

    public void sendMessage() throws IOException {
        Scanner sc = new Scanner(System.in);
        while (socket.isConnected()) {
            System.out.print("> ");
            String msg = sc.nextLine();
            try {
                action(msg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    private void action(String msg) throws Exception {
        PacketClient p = new PacketClient(msg);
        switch (p.getCommand()) {
            default:
                output.writeObject(new PacketClient(msg));
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
        Socket socket = new Socket("localhost", 3333);
        SocketClient socketClient = new SocketClient(socket);
        socketClient.listenMessage();
        socketClient.sendMessage();
    }
}
