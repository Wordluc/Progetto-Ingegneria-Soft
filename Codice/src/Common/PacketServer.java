package Common;

import Server.Client;

import java.io.Serializable;
import java.util.ArrayList;

public class PacketServer extends msgFormat implements Serializable {
    private Client client;

    public PacketServer(Client client, String msg) {
        super(msg);
        this.client = client;
    }

    public PacketServer(String msg) {
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
