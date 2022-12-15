package Common;

import Client.SocketClient;

import java.io.Serializable;

public class PacketClient implements Serializable {
    private String msg;
    private SocketClient socketClient;
    public PacketClient(SocketClient socketClient, String msg){
        this.socketClient = socketClient;
        this.msg = msg;
    }
    public PacketClient(String msg){
        this(null, msg);
    }
    public String getMessage(){
        return msg;
    }

}
