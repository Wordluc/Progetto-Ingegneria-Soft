package Common;

import Client.SocketClient;

import java.io.Serializable;
import java.util.ArrayList;

public class PacketClient extends msgFormat implements Serializable {
    private SocketClient socketClient;


    public PacketClient(SocketClient socketClient, String msg) {
        super(msg);
        this.socketClient = socketClient;
    }

    public PacketClient(String msg) {
        this(null, msg);
    }

    @Override
    public void setArrayObj(Object obj) {
        super.setArrayObj(obj);
    }

    @Override
    public void setObj(ArrayList<Object> obj) {
        super.setObj(obj);
    }

    @Override
    public Object getObj() {
        return super.getObj();
    }

    @Override
    public ArrayList<Object> getArrayObj() {
        return super.getArrayObj();
    }
}
