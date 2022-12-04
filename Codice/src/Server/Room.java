package Server;

import java.util.ArrayList;

public class Room {
    private ArrayList<Client>client;
    public String nome;
    public Room(String nome){
        this.nome = nome;
        client = new ArrayList<>();
    }
    public void addClient(Client client){

        this.client.add(client);
    }
    public void broadCast(String msg){

        for(Client c : client){
            c.sendMessage(msg);
        }
    }
}
