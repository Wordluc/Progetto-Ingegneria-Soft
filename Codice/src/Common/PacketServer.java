package Common;

import Server.Client;

import java.io.Serializable;
import java.util.ArrayList;

public class PacketServer extends MsgFormat implements Serializable {
    private Client client;
    private ArrayList<Client> socketClient;

    public PacketServer(Client client, String msg) {
        super(msg);
        this.client = client;
        socketClient = new ArrayList<>();
    }


    public PacketServer(String msg, ArrayList<Client> c) {
        super(msg);
        socketClient = c;

    }

    public PacketServer() {

    }

    public PacketServer(String msg) {
        this(null, msg);
    }

    public void setClient(ArrayList<Client> socketClient) {
        this.socketClient = socketClient;
    }

    public ArrayList<Client> getClients() {
        return socketClient;
    }

}
