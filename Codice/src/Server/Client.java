package Server;

import Common.PacketClient;
import Common.PacketServer;
import Game.Room;

import java.io.*;
import java.net.Socket;

public class Client {
    Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private OutputStream outputStream;
    private InputStream inputStream;
    private ClientManager clientManager;
    protected Room room;
    private boolean wait;

    public Client(Socket socket, Server server) throws IOException {

        this.socket = socket;

        clientManager = new ClientManager(server, this);

        outputStream = socket.getOutputStream();
        output = new ObjectOutputStream(outputStream);
        inputStream = socket.getInputStream();
        input = new ObjectInputStream(inputStream);
        wait = false;

    }

    public void sendMessage(String msg) {
        try {
            output.writeObject(new PacketServer(msg));
        } catch (IOException e) {
            closeClient();
        }
    }

    public void sendMessage(PacketServer p) {
        try {

            output.writeObject(p);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            closeClient();
        }
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected() && !wait) {
                    try {
                        PacketClient p = (PacketClient) input.readObject();
                        if (p.getCommand() != null) {
                            // System.out.println(p.toString());
                            action(p);
                        }
                    } catch (IOException e) {
                        closeClient();
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (Exception e) {
                        sendMessage(e.getMessage());
                    }
                }
            }
        }).start();
    }

    //--- lista comandi
    private void action(PacketClient p) throws Exception {

        switch (p.getCommand().toLowerCase()) {
            case "room": {
                roomOption(p);
                break;
            }
            case "test": {
                System.out.println(p.toString());
                sendMessage("!test Server -> b");
                p = (PacketClient) input.readObject();
                System.out.println(p.toString());
                sendMessage("Server -> d");
                p = (PacketClient) input.readObject();
                System.out.println(p.toString());
                sendMessage("Server -> f");

            }

        }
    }

    //--- Comandi stanza
    private void roomOption(PacketClient p) throws Exception {
        switch (p.getOption(0)) {
            case "list": {
                onReceiveListRoom();
                break;
            }
            case "create": {
                onReceiveCreateRoom(p);
                break;
            }
            case "remove": {
                onReceiveRemoveRoom();
                break;
            }
            case "join": {
                onReceiveJoinRoom(p);
                break;
            }
            case "quit": {
                onReceiveQuitRoom();
                break;
            }
            case "userlist": {
                onReceiveclientInRoom();
                break;
            }
            case "removeuser": {
                onReceiveRemoveClient(p);
            }
        }
    }

    public void onReceiveclientInRoom() {
        String s = clientManager.clientInRoom();
        sendMessage(s);
    }

    private void onReceiveQuitRoom() {
        if (clientManager.quitRoom()) {
            this.room = null;
            sendMessage("Sei uscito dalla stanza");
        } else
            sendMessage("Errore");
    }

    private void onReceiveListRoom() {
        String list = clientManager.listRoom();
        sendMessage(list);
    }

    public void onReceiveRemoveClient(PacketClient p) throws Exception {
        if (clientManager.removeClient(p.getOption(1)))
            sendMessage("Utente rimosso");
        else
            sendMessage("Errore");


    }

    private void onReceiveRemoveRoom() {
        if (clientManager.removeRoom(this.room)) {
            sendMessage("Stanza " + room.getName() + " " + room.getCode() + " rimossa");
            this.room = null;
        } else {
            sendMessage("Errore");
        }
    }

    private void onReceiveJoinRoom(PacketClient p) {
        try {
            Room room = clientManager.joinRoom(p.getOption(1));
            if (room != null) {
                sendMessage("Ti sei unito nella stanza: " + this.room.getName());
            } else
                sendMessage("Impossibile unirsi");

        } catch (Exception e) {
            sendMessage(e.getMessage());
        }
    }

    private void onReceiveCreateRoom(PacketClient p) throws Exception {

        String name = p.getOption(1);
        Room room = clientManager.createRoom(name);

        if (room != null) {
            sendMessage("Stanza creata: " + room.getName() + " " + room.getCode());
        } else
            sendMessage("Errore creazione stanza");

    }

    private void closeClient() {
        try {
            output.close();
            outputStream.close();
            input.close();
            inputStream.close();
            socket.close();
            clientManager.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
