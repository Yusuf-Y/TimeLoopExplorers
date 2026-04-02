# TimeLoopExplorers 🌀

A Java-based adventure engine that simulates time-loop mechanics through custom data structures.

## 🛠 Technical Highlights
- **Custom Linked Lists:** Built without standard Java Collections. Implements manual Node-based lists for deep logic control:
    - `ExplorerList`: A **Circular Linked List** used to manage infinite turn rotations among players.
    - `ActionHistory`: A **Linear Linked List** used to log game events.
- **Recursive Algorithms:** Utilizes recursion in `ActionHistory` to navigate and display complex branching player timelines.
- **Game State Management:** Handles HP tracking, treasure collection, and room-based event triggers through a decoupled `RoomList`.

## 🚀 How to Run
```bash
javac *.java
java TimeLoopExplorers
