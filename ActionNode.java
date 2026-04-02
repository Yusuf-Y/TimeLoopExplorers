/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp3proje;

class ActionNode {
    String explorerName;
    String actionType;  // "move", "use_echo", "use_paradox"
    int roomNumber;
    int stoneNumber;
    int actionId;
    ActionNode next;
    ActionNode branchChild;  // For timeline branching
    
    public ActionNode(String explorerName, String actionType, int roomNumber, int stoneNumber, int actionId) {
        this.explorerName = explorerName;
        this.actionType = actionType;
        this.roomNumber = roomNumber;
        this.stoneNumber = stoneNumber;
        this.actionId = actionId;
        this.next = null;
        this.branchChild = null;
    }
}
