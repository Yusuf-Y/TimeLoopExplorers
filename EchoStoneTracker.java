/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp3proje;

class EchoStoneTracker {
    private EchoStoneNode head;
    private int nextExpectedStone;
    
    public EchoStoneTracker() {
        this.head = null;
        this.nextExpectedStone = 1;
    }
    
    public void recordStoneUsed(int stoneNumber) {
        EchoStoneNode newNode = new EchoStoneNode(stoneNumber);
        newNode.used = true;
        
        if (head == null) {
            head = newNode;
        } else {
            EchoStoneNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }
    
    public boolean isCorrectSequence(int stoneNumber) {
        return stoneNumber == nextExpectedStone;
    }
    
    public void incrementExpected() {
        nextExpectedStone++;
    }
    
    public int getNextExpected() {
        return nextExpectedStone;
    }
    
    public void reset() {
        nextExpectedStone = 1;
        head = null;
    }
    
    public void display() {
        System.out.print("Echo stones used in order: ");
        EchoStoneNode temp = head;
        while (temp != null) {
            System.out.print(temp.stoneNumber);
            if (temp.next != null) System.out.print(" -> ");
            temp = temp.next;
        }
        System.out.println("\nNext expected: " + nextExpectedStone);
    }
}