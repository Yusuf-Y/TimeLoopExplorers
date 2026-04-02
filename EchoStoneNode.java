/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp3proje;

class EchoStoneNode {
    int stoneNumber;
    boolean used;
    EchoStoneNode next;
    
    public EchoStoneNode(int stoneNumber) {
        this.stoneNumber = stoneNumber;
        this.used = false;
        this.next = null;
    }
}