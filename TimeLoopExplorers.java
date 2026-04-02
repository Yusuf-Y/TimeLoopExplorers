/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp3proje;
import java.util.Scanner;
import java.util.Random;
public class TimeLoopExplorers {
    private ExplorerList explorers;
    private RoomList rooms;
    private ActionHistory history;
    private EchoStoneTracker echoTracker;
    private Random random;
    private Scanner scanner;
    private boolean gameWon;
    private boolean gameLost;
    private int totalEchoStones;
    
    public TimeLoopExplorers() {
        explorers = new ExplorerList();
        rooms = new RoomList();
        history = new ActionHistory();
        echoTracker = new EchoStoneTracker();
        random = new Random();
        scanner = new Scanner(System.in);
        gameWon = false;
        gameLost = false;
        totalEchoStones = 0;
    }
    
    public void initializeGame() {
        System.out.println("=== TIME LOOP EXPLORERS ===\n");
        
        // Get number of explorers
        System.out.print("Enter number of explorers (3-5): ");
        int numExplorers = scanner.nextInt();
        scanner.nextLine();
        
        while (numExplorers < 3 || numExplorers > 5) {
            System.out.print("Invalid! Enter number between 3 and 5: ");
            numExplorers = scanner.nextInt();
            scanner.nextLine();
        }
        
        // Create explorers
        for (int i = 1; i <= numExplorers; i++) {
            System.out.print("Enter name for Explorer " + i + ": ");
            String name = scanner.nextLine();
            explorers.addExplorer(name, 1);
        }
        
        // Create rooms with events
        createRooms();
        
        System.out.println("\nGame initialized! All explorers start in Room 1.");
        System.out.println("Goal: Use echo stones in sequence 1, 2, 3, ... to break the time loop!\n");
    }
    
    private void createRooms() {
        // Room 1 - Starting room
        rooms.addRoom(1, "none", 0, "The entrance chamber. Time feels strange here.");
        
        // Create 15 rooms with various events
        String[] harmfulEvents = {
            "A trap springs! Poisoned darts fly.",
            "The floor collapses partially!",
            "A spectral guardian attacks!",
            "Ancient magic drains your life force!"
        };
        
        String[] helpfulEvents = {
            "You find ancient healing herbs!",
            "A mysterious light restores your vitality!",
            "You discover a treasure chest!",
            "A friendly spirit blesses you!"
        };
        
        int echoStoneNum = 1;
        
        for (int i = 2; i <= 16; i++) {
            int eventRoll = random.nextInt(10);
            
            if (eventRoll < 3 && echoStoneNum <= 5) {
                // Echo stone
                rooms.addRoom(i, "echo", echoStoneNum, 
                    "You find Echo Stone #" + echoStoneNum + " glowing with temporal energy!");
                totalEchoStones++;
                echoStoneNum++;
            } else if (eventRoll == 3 && i > 5) {
                // Paradox stone
                rooms.addRoom(i, "paradox", 0, 
                    "The legendary Paradox Stone pulses with reality-bending power!");
            } else if (eventRoll < 6) {
                // Harmful event
                String event = harmfulEvents[random.nextInt(harmfulEvents.length)];
                rooms.addRoom(i, "harmful", 0, event);
            } else if (eventRoll < 8) {
                // Helpful event
                String event = helpfulEvents[random.nextInt(helpfulEvents.length)];
                rooms.addRoom(i, "helpful", 0, event);
            } else if (eventRoll == 8 && i > 8) {
                // Reverse direction event
                rooms.addRoom(i, "reverse", 0, 
                    "A temporal anomaly reverses the turn order!");
            } else {
                // Safe room
                rooms.addRoom(i, "none", 0, "An empty chamber. Dust swirls in the air.");
            }
        }
    }
    
    public void playGame() {
        while (!gameWon && !gameLost) {
            explorers.displayAll();
            
            ExplorerNode current = explorers.getCurrentExplorer();
            if (current == null || !current.isAlive()) {
                checkGameOver();
                continue;
            }
            
            System.out.println(">>> " + current.name + "'s turn <<<");
            System.out.println("Current room: " + current.currentRoomNumber);
            
            RoomNode currentRoom = rooms.getRoom(current.currentRoomNumber);
            if (currentRoom != null && !currentRoom.eventDescription.isEmpty()) {
                System.out.println("Room: " + currentRoom.eventDescription);
            }
            
            System.out.println("\nActions:");
            System.out.println("1. Move to next room");
            System.out.println("2. Use stone (if available)");
            System.out.println("3. View timeline");
            System.out.println("4. View echo sequence status");
            System.out.print("Choose action: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    moveExplorer(current);
                    break;
                case 2:
                    useStone(current, currentRoom);
                    break;
                case 3:
                    history.displayTimeline();
                    continue; // Don't end turn
                case 4:
                    echoTracker.display();
                    continue; // Don't end turn
                default:
                    System.out.println("Invalid choice!");
                    continue;
            }
            
            checkGameOver();
            explorers.nextTurn();
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
        
        displayGameResult();
    }
    
    private void moveExplorer(ExplorerNode explorer) {
        int maxRoom = rooms.getMaxRoomNumber();
        int newRoom = explorer.currentRoomNumber + 1;
        
        if (newRoom > maxRoom) {
            System.out.println("You've reached the end of the ruin! Looping back...");
            newRoom = 1;
        }
        
        explorer.currentRoomNumber = newRoom;
        history.addAction(explorer.name, "move", newRoom, 0);
        
        System.out.println(explorer.name + " moved to room " + newRoom);
        
        // Trigger room event
        RoomNode room = rooms.getRoom(newRoom);
        if (room != null) {
            triggerRoomEvent(explorer, room);
        }
    }
    
    private void triggerRoomEvent(ExplorerNode explorer, RoomNode room) {
        if (room.eventType.equals("harmful")) {
            explorer.hitPoints--;
            System.out.println("HARMFUL EVENT! " + explorer.name + " loses 1 HP! (HP: " + explorer.hitPoints + ")");
        } else if (room.eventType.equals("helpful")) {
            int roll = random.nextInt(2);
            if (roll == 0) {
                explorer.hitPoints = Math.min(3, explorer.hitPoints + 1);
                System.out.println("HELPFUL EVENT! " + explorer.name + " gains 1 HP! (HP: " + explorer.hitPoints + ")");
            } else {
                explorer.treasurePoints++;
                System.out.println("HELPFUL EVENT! " + explorer.name + " gains 1 treasure point! (TP: " + explorer.treasurePoints + ")");
            }
        } else if (room.eventType.equals("reverse")) {
            System.out.println("TEMPORAL ANOMALY! Turn order would reverse (simplified in this version).");
        }
    }
    
    private void useStone(ExplorerNode explorer, RoomNode room) {
        if (room == null) {
            System.out.println("No stone available in this room!");
            return;
        }
        
        if (room.eventType.equals("echo")) {
            useEchoStone(explorer, room);
        } else if (room.eventType.equals("paradox")) {
            useParadoxStone(explorer);
        } else {
            System.out.println("No stone available in this room!");
        }
    }
    
    private void useEchoStone(ExplorerNode explorer, RoomNode room) {
        int stoneNum = room.stoneNumber;
        history.addAction(explorer.name, "use_echo", room.roomNumber, stoneNum);
        
        System.out.println(explorer.name + " used Echo Stone #" + stoneNum + "!");
        
        if (echoTracker.isCorrectSequence(stoneNum)) {
            echoTracker.recordStoneUsed(stoneNum);
            echoTracker.incrementExpected();
            System.out.println("✓ CORRECT SEQUENCE! Next expected: " + echoTracker.getNextExpected());
            
            // Check win condition
            if (echoTracker.getNextExpected() > totalEchoStones) {
                gameWon = true;
            }
        } else {
            System.out.println("✗ WRONG SEQUENCE! Expected stone #" + echoTracker.getNextExpected());
            System.out.println("The time loop tightens... Sequence reset!");
            echoTracker.reset();
        }
        
        // Remove stone from room
        room.eventType = "none";
        room.stoneNumber = 0;
    }
    
    private void useParadoxStone(ExplorerNode explorer) {
        System.out.println("\n=== PARADOX STONE ACTIVATED ===");
        System.out.println("Options:");
        System.out.println("1. View Timelines (see action history)");
        System.out.println("2. Forge New Path (not implemented - would allow branching)");
        System.out.print("Choose: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice == 1) {
            history.displayTimeline();
        } else {
            System.out.println("Timeline branching simplified in this version.");
        }
        
        history.addAction(explorer.name, "use_paradox", explorer.currentRoomNumber, 0);
    }
    
    private void checkGameOver() {
        if (explorers.countAlive() == 0) {
            gameLost = true;
        }
    }
    
    private void displayGameResult() {
        System.out.println("\n" + "=".repeat(40));
        if (gameWon) {
            System.out.println("🎉 VICTORY! 🎉");
            System.out.println("The explorers used the echo stones in perfect sequence!");
            System.out.println("The time loop shatters, and reality stabilizes.");
            System.out.println("The explorers escape the ancient ruin!");
        } else {
            System.out.println("💀 DEFEAT 💀");
            System.out.println("All explorers have perished in the time loop.");
            System.out.println("The ruin claims more victims...");
        }
        System.out.println("=".repeat(40) + "\n");
        
        history.displayTimeline();
        echoTracker.display();
    }
    
    public static void main(String[] args) {
        TimeLoopExplorers game = new TimeLoopExplorers();
        game.initializeGame();
        game.playGame();
    }
}