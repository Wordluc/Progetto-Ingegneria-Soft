package Game;

import java.util.ArrayList;

public class RoomManager {
    private ArrayList<Room> rooms;

    public RoomManager() {
        rooms = new ArrayList<>();
    }

    public boolean addRoom(Room room) {
        for (Room r : rooms) {
            if (r.getCode().equals(room.getCode())) {
                room.newCode();
                addRoom(room);
            }
        }
        rooms.add(room);
        return true;
    }

    public boolean removeRoom(Room room) {

        if (rooms.remove(room))
            return true;
        else
            return false;
    }

    public Room findRoom(String codice) {
        for (Room r : rooms) {
            if (r.getCode().equals(codice))
                return r;
        }
        return null;
    }


    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void broadCast(String msg) {
        for (Room r : rooms) {
            r.broadCast(msg);
        }
    }
}
