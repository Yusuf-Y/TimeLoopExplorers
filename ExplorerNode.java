
package com.mycompany.comp3proje;

class ExplorerNode {
    String name;
    int hitPoints;
    int treasurePoints;
    int currentRoomNumber;
    ExplorerNode next;
    
    public ExplorerNode(String name, int roomNumber) {
        this.name = name;
        this.hitPoints = 3;
        this.treasurePoints = 0;
        this.currentRoomNumber = roomNumber;
        this.next = null;
    }
    
    public boolean isAlive() {
        return hitPoints > 0;
    }
}
