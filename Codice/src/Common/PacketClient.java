package Common;

import Client.SocketClient;

import java.io.Serializable;
import java.util.ArrayList;

public class PacketClient extends MsgFormat implements Serializable {
    private SocketClient socketClient;
    public ArrayList<SocketClient> clients;

    public PacketClient(SocketClient socketClient, String msg) {
        super(msg);
        this.socketClient = socketClient;

    }

    public void setClient(ArrayList<SocketClient> socketClient) {
        clients = socketClient;
    }

    public ArrayList<SocketClient> getClients() {
        return clients;
    }

    public PacketClient() {
        this(null, null);
    }


    public PacketClient(String msg) {
        this(null, msg);
    }


}
