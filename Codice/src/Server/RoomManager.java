package Server;

import java.util.ArrayList;

public class RoomManager {
    protected ArrayList<Room>rooms;
    public RoomManager(){
        rooms = new ArrayList<>();
    }
    public boolean addRoom(Room room){
        for(Room r : rooms){
            if(r.equals(room))
                return false;
        }
        rooms.add(room);
        return true;
    }
    public boolean removeRoom(Room room){
        for(Room r : rooms){
            if(r.equals(room)){
                rooms.remove(room);
                return true;
            }
        }
        return false;
    }
    private void broadCast(String msg){
        for(Room r : rooms)
            r.broadCast(msg);
    }
}
