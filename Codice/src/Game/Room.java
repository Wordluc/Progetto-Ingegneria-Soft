package Game;

import Server.Client;

import java.util.ArrayList;
import java.util.UUID;

public class Room extends RoomManager {
    public ArrayList<Client> clients;
    private String codice;
    private String name;
    private Client admin;


    public Room(String name, Client admin) throws Exception {
        if (name == null || admin == null)
            throw new Exception(name + " " + admin);
        else {
            this.name = name;
            this.codice = randomCode();
            this.admin = admin;
            clients = new ArrayList<>();
            addClient(admin);
        }
    }

    public String getName() {
        return name;
    }

    public boolean addClient(Client client) {
        for (Client c : clients) {
            if (c.equals(client))
                return false;
        }
        clients.add(client);
        return true;
    }

    public String getCode() {
        return codice;
    }

    public boolean removeClient(Client request, Client client) {
        if (request.equals(admin)) {
            if (quitClient(client))
                return true;
        }
        return false;
    }

    public Client getAdmin() {
        return admin;
    }

    public boolean quitClient(Client client) {
        if (client.equals(admin)) {
            if (clients.size() > 1)
                admin = clients.get(1);
            else
                admin = null;
        }
        if (clients.remove(client))
            return true;
        return false;


    }

    public void broadCast(String msg) {
        for (Client c : clients) {
            c.sendMessage(msg);
        }
    }

    public ArrayList<Client> getAllUser() {
        return clients;
    }

    public void newCode() {
        codice = randomCode();
    }


    protected String randomCode() {
        UUID code = UUID.randomUUID();
        return code.toString().substring(0, 8);
    }

}
