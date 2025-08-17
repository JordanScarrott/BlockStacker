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
 * **Scene.java**: Contains the core game logic, including managing the game board, handling piece movement, collision detection, and clearing completed lines.
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
