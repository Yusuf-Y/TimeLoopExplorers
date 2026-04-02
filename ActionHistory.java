/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp3proje;

class ActionHistory {
    private ActionNode head;
    private ActionNode current;
    private int actionCounter;
    
    public ActionHistory() {
        this.head = null;
        this.current = null;
        this.actionCounter = 0;
    }
    
    public void addAction(String explorerName, String actionType, int roomNumber, int stoneNumber) {
        actionCounter++;
        ActionNode newAction = new ActionNode(explorerName, actionType, roomNumber, stoneNumber, actionCounter);
        
        if (head == null) {
            head = newAction;
            current = newAction;
        } else {
            current.next = newAction;
            current = newAction;
        }
    }
    
    public void displayTimeline() {
        System.out.println("\n=== ACTION TIMELINE ===");
        displayTimelineRecursive(head, 0);
        System.out.println("=======================\n");
    }
    
    private void displayTimelineRecursive(ActionNode node, int depth) {
        if (node == null) return;
        
        String indent = "";
        for (int i = 0; i < depth; i++) indent += "  ";
        
        String actionDesc = "";
        if (node.actionType.equals("move")) {
            actionDesc = "moved to room " + node.roomNumber;
        } else if (node.actionType.equals("use_echo")) {
            actionDesc = "used Echo Stone #" + node.stoneNumber;
        } else if (node.actionType.equals("use_paradox")) {
            actionDesc = "used Paradox Stone";
        }
        
        System.out.println(indent + "[" + node.actionId + "] " + node.explorerName + " " + actionDesc);
        
        if (node.branchChild != null) {
            System.out.println(indent + "  |-- BRANCH --");
            displayTimelineRecursive(node.branchChild, depth + 1);
        }
        
        displayTimelineRecursive(node.next, depth);
    }
    
    public ActionNode getHead() {
        return head;
    }
    
    public int getActionCounter() {
        return actionCounter;
    }
}
