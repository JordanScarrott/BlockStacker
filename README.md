# Block Stacker

This is a simple Tetris game implemented in Java using the Swing library.

## Building and Running

This project uses Gradle as a build tool. To build and run the project, you can use the included Gradle wrapper (`gradlew`).

### Prerequisites
- Java Development Kit (JDK) 17 or higher.

### Building
To compile the source code and build the application, run the following command in the project's root directory:
```bash
./gradlew build
```

### Running
To run the game, use the following command:
```bash
./gradlew run
```

## Controls

-   **A**: Move the piece left
-   **D**: Move the piece right
-   **S**: Move the piece down
-   **Q**: Rotate the piece

## Project Structure

The project is organized into several packages and classes under `src/main/java/com/jordan/blockstacker`:

-   `Main.java`: The main entry point of the application. It creates and configures the game window.
-   `Display.java`: The main `JFrame` for the game. It handles rendering the game state (the grid and the pieces) and listens for keyboard input.
-   `Scene.java`: Contains the core game logic, including managing the game board, handling piece movement, collision detection, and clearing completed lines.
-   `shape/`: This package contains classes related to the Tetris pieces.
    -   `Shape.java`: Defines the structure and behavior of a Tetris piece (like I, O, T, L, J blocks), including rotation.
    -   `Block.java`: Represents a single square block, which is the basic building unit of a shape.
-   `core/`: This package contains helper classes.
    -   `MyVector.java`: A custom 2D vector class used for managing the positions and movements of blocks and shapes.
-   `colorThings/`: This package is for color-related utilities.
    -   `ColourUtils.java`: A simple utility class to provide random colors for the Tetris pieces.
