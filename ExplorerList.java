
package com.mycompany.comp3proje;

class ExplorerList {
    private ExplorerNode head;
    private ExplorerNode current;
    private int count;
    
    public ExplorerList() {
        this.head = null;
        this.current = null;
        this.count = 0;
    }
    
    public void addExplorer(String name, int startRoom) {
        ExplorerNode newExplorer = new ExplorerNode(name, startRoom);
        if (head == null) {
            head = newExplorer;
            head.next = head; // Circular
            current = head;
        } else {
            ExplorerNode temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newExplorer;
            newExplorer.next = head;
        }
        count++;
    }
    
    public ExplorerNode getCurrentExplorer() {
        return current;
    }
    
    public void nextTurn() {
        if (current != null) {
            do {
                current = current.next;
            } while (!current.isAlive() && countAlive() > 0);
        }
    }
    
    public int countAlive() {
        if (head == null) return 0;
        int alive = 0;
        ExplorerNode temp = head;
        do {
            if (temp.isAlive()) alive++;
            temp = temp.next;
        } while (temp != head);
        return alive;
    }
    
    public void displayAll() {
        if (head == null) return;
        ExplorerNode temp = head;
        System.out.println("\n=== EXPLORERS STATUS ===");
        do {
            String status = temp.isAlive() ? "ALIVE" : "DEAD";
            String marker = (temp == current) ? " <- CURRENT TURN" : "";
            System.out.println(temp.name + " | HP: " + temp.hitPoints + 
                             " | Treasure: " + temp.treasurePoints + 
                             " | Room: " + temp.currentRoomNumber + 
                             " | " + status + marker);
            temp = temp.next;
        } while (temp != head);
        System.out.println("========================\n");
    }
}