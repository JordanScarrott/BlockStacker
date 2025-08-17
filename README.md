# Block Stacker

This is a simple Tetris game implemented in Java using the Swing library.

## How to Build and Run

This project is built using Gradle. The Gradle wrapper is included in the repository, so you don't need to install Gradle yourself.

### Running the game

To run the game, open a terminal or command prompt, navigate to the project's root directory, and run the following command:

```bash
./gradlew run
```

This will automatically compile the code and start the game.

### Building the project

To build the project and create a distributable archive (in `app/build/distributions`), run:

```bash
./gradlew build
```

The `build` command will compile the code, run tests, and package the application.

## Controls

-   **A**: Move the piece left
-   **D**: Move the piece right
-   **S**: Move the piece down
-   **Q**: Rotate the piece
