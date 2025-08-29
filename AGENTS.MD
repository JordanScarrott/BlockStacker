# AI Agent Contributions (AGENTS.MD)

This document tracks the contributions made by AI agents to the BlockStacker project. It serves as a log for transparency, reproducibility, and a guide for future AI collaboration.

## Agent Profile
 * Name: Jules 2.0
 * Type: Large Language Model (Google Gemini Family)
 * Role: AI Software Development Assistant

## Contribution Log
This is a chronological log of major tasks performed by the AI. Each significant contribution should be recorded here using the following template.

(Copy the template below to add a new entry)
---
**Task Title Here**
 * **Task**: A brief, one-sentence summary of the goal.
 * **Description**: A more detailed explanation of the work that was done and the files that were affected.
 * **Status**: (e.g., Completed, In Progress)
---
**Add Bomb Block Game Rule**
 * **Task**: Add a new game rule where blocks can become bombs and explode.
 * **Description**: Implemented the "Bomb Block" feature. Blocks now have a chance to become bombs when they are placed. A bomb block flashes for a few seconds and then explodes, clearing a 3x3 area of surrounding blocks. Affected files: `src/main/java/com/jordan/blockstacker/shape/Block.java`, `src/main/java/com/jordan/blockstacker/Scene.java`, `src/main/java/com/jordan/blockstacker/GameConstants.java`, `src/test/java/com/jordan/blockstacker/BombBlockTest.java`, `GAME_RULES.md`.
 * **Status**: Completed
---
**Refactor Game Loop for Variable-Rate Updates**
 * **Task**: Improve the main game loop to support updating game components at different rates.
 * **Description**: The original game loop used a fixed `Thread.sleep()`, making it difficult to incorporate features like particle effects that need to update faster than the main game logic. The loop has been refactored to a variable-timestep model. An `Updatable` interface and a `GameTicker` class were introduced to manage game components. The `GameLoop` now calculates delta time and uses the `GameTicker` to update all registered `Updatable` components, allowing for different update frequencies. Affected files: `src/main/java/com/jordan/blockstacker/Main.java`, `src/main/java/com/jordan/blockstacker/GameLoop.java`, `src/main/java/com/jordan/blockstacker/Scene.java`, `src/main/java/com/jordan/blockstacker/Updatable.java`, `src/main/java/com/jordan/blockstacker/GameTicker.java`.
 * **Status**: Completed
---
**Fix Shape Rotation Bug**
 * **Task**: Prevent shapes from rotating out of the game grid bounds.
 * **Description**: The game crashed when a shape was rotated in a way that would move it out of bounds. The fix involved moving the rotation logic from the `Shape` class to the `Scene` class. The new logic in `Scene` now checks if a rotation is valid (i.e., within the grid boundaries and not colliding with other blocks) before applying it. This prevents the crash and makes the game more stable. Affected files: `src/main/java/com/jordan/blockstacker/shape/Shape.java`, `src/main/java/com/jordan/blockstacker/Scene.java`, `src/test/java/com/jordan/blockstacker/shape/ShapeTest.java`, `src/test/java/com/jordan/blockstacker/SceneTest.java`.
 * **Status**: Completed
---
**Add Score Counter**
 * **Task**: Add an in-game score counter.
 * **Description**: Added a score counter to the game. The score increases by 10 points for each cleared line. The score is displayed in the top-left corner of the screen. Affected files: `src/main/java/com/jordan/blockstacker/Scene.java`, `src/main/java/com/jordan/blockstacker/Display.java`.
 * **Status**: Completed
---
**Create Main Menu**
 * **Task**: Create a main menu with "Start" and "Exit" options.
 * **Description**: Created a `MainMenu` class to display the menu. Refactored `Display` from a `JFrame` to a `JPanel` and updated `Main` to use a `CardLayout` to switch between the menu and the game. Affected files: `src/main/java/com/jordan/blockstacker/Main.java`, `src/main/java/com/jordan/blockstacker/Display.java`, `src/main/java/com/jordan/blockstacker/MainMenu.java`.
 * **Status**: Completed
---

---
**Add Gradle Support**
 * **Task**: Add a complete Gradle build system to the project.
 * **Description**: Restructured the project to follow the standard Gradle layout, with sources in `src/main/java`. Created a `build.gradle.kts` file to define the build process, including the application plugin and main class. Added a Gradle wrapper (`gradlew`) for consistent builds. Updated the `README.md` with new build and run instructions. Refactored the `MyVector` class to remove a dependency on a non-standard API.
 * **Status**: Completed
---
**Create AGENTS.MD**
 * **Task**: Create a generic template for the AGENTS.MD file.
 * **Description**: Created the AGENTS.MD file to document AI contributions and provide context for future agents. Populated the file with information about the project's technology stack, architecture, and goals.
 * **Status**: Completed
---
**Create Test Guide**
 * **Task**: Create a test guide for the project, including a sample test.
 * **Description**: Added JUnit 5 dependency to `build.gradle.kts`. Created a `src/test/java` directory and a sample test for the `MyVector` class. Created a `TESTING.md` file to explain how to run the tests.
 * **Status**: Completed
---

## Context for Future Agents
This section contains important information to help future AI agents understand the project quickly and contribute effectively.

### 1. Technology Stack
 * **Language**: Java
 * **Build Tool**: Gradle
 * **UI Library**: Java Swing

### 2. Core Architecture
 * **Main.java**: The main entry point of the application. It creates and configures the game window.
 * **Display.java**: The main `JFrame` for the game. It handles rendering the game state (the grid and the pieces) and listens for keyboard input.
 * **GameLoop.java**: Manages the main game loop. It calculates the time between frames (delta time) and triggers the `GameTicker`.
 * **GameTicker.java**: Manages a list of `Updatable` components. On each tick from the `GameLoop`, it calls the `update()` method on all registered components.
 * **Updatable.java**: An interface for game components that need to be updated on each frame. It has a single method, `update(long deltaTime)`.
 * **Scene.java**: Contains the core game logic, including managing the game board, handling piece movement, collision detection, and clearing completed lines. It implements the `Updatable` interface to update the game state at a fixed interval.
 * **shape/**: This package contains classes related to the Tetris pieces.
    *   `Shape.java`: Defines the structure and behavior of a Tetris piece (like I, O, T, L, J blocks), including rotation.
    *   `Block.java`: Represents a single square block, which is the basic building unit of a shape.
 * **core/**: This package contains helper classes.
    *   `MyVector.java`: A custom 2D vector class used for managing the positions and movements of blocks and shapes.
 * **colorThings/**: This package is for color-related utilities.
    *   `ColourUtils.java`: A simple utility class to provide random colors for the Tetris pieces.

### 3. Project Goals & Rules of Engagement
 * **Primary Goal**: To serve as a showcase for human-AI software development collaboration.
 * **Contribution Style**: Each major task or feature should be completed in a separate branch and submitted as a pull request.
 * **Logging**: Upon completing a task, you must add an entry to the "Contribution Log" in this file.
