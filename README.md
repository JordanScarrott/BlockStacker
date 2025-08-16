# Block Stacker

This is a simple Tetris game implemented in Java using the Swing library.

## How to Run

1.  **Compile the code:**
    Open a terminal or command prompt, navigate to the project's root directory, and run the following command to compile all the Java source files:
    ```bash
    javac --source-path . -d out */*.java *.java
    ```
    This will compile all `.java` files from the root directory and the `core`, `shape`, and `colorThings` subdirectories, placing the compiled `.class` files into a new `out` directory.

2.  **Run the game:**
    After compilation, run the game with the following command:
    ```bash
    java -cp out Main
    ```

## Controls

-   **A**: Move the piece left
-   **D**: Move the piece right
-   **S**: Move the piece down
-   **Q**: Rotate the piece

## Project Structure

The project is organized into several packages and classes:

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
