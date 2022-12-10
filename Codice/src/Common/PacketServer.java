package Common;
import Server.Client;

import java.io.Serializable;

public class PacketServer implements Serializable {
    private String msg;
    private Client client;
    public PacketServer(Client client, String msg){
        this.msg = msg;
        this.client = client;
    }
    public PacketServer(String msg){
        this(null, msg);
    }
    public String getMessage(){
        return msg;
    }
}
