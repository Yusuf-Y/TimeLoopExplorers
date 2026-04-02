/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp3proje;

class RoomNode {
    int roomNumber;
    String eventType;  // "echo", "paradox", "harmful", "helpful", "reverse", "none"
    int stoneNumber;   // For echo/paradox stones
    String eventDescription;
    RoomNode next;
    
    public RoomNode(int roomNumber) {
        this.roomNumber = roomNumber;
        this.eventType = "none";
        this.stoneNumber = 0;
        this.eventDescription = "";
        this.next = null;
    }
}
