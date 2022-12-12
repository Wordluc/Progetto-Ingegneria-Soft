package Server;

import Game.Room;

import java.io.IOException;
import java.util.ArrayList;

public class ClientManager {
    private static Server server;
    private Client client;

    public ClientManager(Server server, Client client) {
        this.client = client;
        this.server = server;

    }

    public static void broardCast(String msg) throws IOException {
        for (Client c : server.clients) {
            c.sendMessage(msg);
        }
    }

    //--- Azioni stanze
    public Room createRoom(String name) throws Exception {
        Room room = new Room(name, client);
        if (client.room != null)
            return null;
        if (server.roomManagers.addRoom(room)) {
            client.room = room;
            System.out.println("Stanza creata: " + room.getName() + " " + room.getCode());
            return room;
        } else {
            System.out.println("Errore creazione stanza");
            return null;
        }
    }

    public Boolean removeRoom(Room room) {
        if (server.roomManagers.removeRoom(room)) {
            System.out.println("Stanza " + room.getName() + " " + room.getCode() + " rimossa");
            return true;
        }
        System.out.println("Errore");
        return false;
    }

    public Room joinRoom(String codice) {
        Room room = server.roomManagers.findRoom(codice);
        if (client.room != null)
            return null;
        if (room != null) {
            if (room.addClient(client)) {
                client.room = room;
                client.room.broadCast("Join the chat");
                return room;
            }
        }
        return null;
    }

    public boolean quitRoom() {
        if (client.room == null)
            return false;
        if (client.room.quitClient(client)) {
            if (client.room.clients.size() >= 1) {
                if (client.room != null)
                    client.room.broadCast("Left the chat");
            } else
                removeRoom(client.room);
            return true;
        }
        return false;
    }

    public String listRoom() {
        ArrayList<Room> rooms = server.roomManagers.getRooms();
        String list = "";
        for (Room r : rooms) {
            list += "Nome: " + r.getName() + " Codice: " + r.getCode() + "\n";
        }
        System.out.println(list);
        return list;
    }

    public String clientInRoom() {
        if (client.room != null) {
            String s = "";
            for (Client c : client.room.getAllUser()) {
                System.out.println(c.socket);
                s += c.socket + "\n";
            }
            return s;
        }
        return null;
    }

    public boolean removeClient(String socket) {
        if (client.room == null)
            return false;
        for (Client c : client.room.getAllUser()) {
            int port = Integer.parseInt(socket);
            if (c.socket.getPort() == port) {

                if (client.room.removeClient(this.client, c)) {
                    c.sendMessage("Sei stato rimosso");
                    c.room = null;
                    return true;
                }
                return false;
            }

        }
        return false;
    }


    public void close() {
        server.removeClient(client);
    }


}
