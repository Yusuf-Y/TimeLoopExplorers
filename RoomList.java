
package com.mycompany.comp3proje;

class RoomList {
    private RoomNode head;
    private int roomCount;
    
    public RoomList() {
        this.head = null;
        this.roomCount = 0;
    }
    
    public void addRoom(int roomNumber, String eventType, int stoneNumber, String description) {
        RoomNode newRoom = new RoomNode(roomNumber);
        newRoom.eventType = eventType;
        newRoom.stoneNumber = stoneNumber;
        newRoom.eventDescription = description;
        
        if (head == null) {
            head = newRoom;
        } else {
            RoomNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newRoom;
        }
        roomCount++;
    }
    
    public RoomNode getRoom(int roomNumber) {
        RoomNode temp = head;
        while (temp != null) {
            if (temp.roomNumber == roomNumber) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
    
    public int getMaxRoomNumber() {
        int max = 0;
        RoomNode temp = head;
        while (temp != null) {
            if (temp.roomNumber > max) {
                max = temp.roomNumber;
            }
            temp = temp.next;
        }
        return max;
    }
}