AI Agent Contributions (AGENTS.MD)
This document tracks the contributions made by AI agents to the BlockStacker project. It serves as a log for transparency, reproducibility, and a guide for future AI collaboration.
Agent Profile
 * Name: Jules 2.0
 * Type: Large Language Model (Google Gemini Family)
 * Role: AI Software Development Assistant
Contribution Log
This is a chronological log of major tasks performed by the AI. Each significant contribution should be recorded here using the following template.

---
**Task: Add Gradle Build System**
 * Description: Added a complete Gradle build system to the project. This involved refactoring the source code into a standard directory structure, creating a `build.gradle.kts` file, and updating the README with new build instructions. The project was discovered to be a Swing application, not a Slick2D one, and the build was configured accordingly.
 * Status: Completed
---

(Copy the template below to add a new entry)
---
**Task Title Here**
 * Task: A brief, one-sentence summary of the goal.
 * Description: A more detailed explanation of the work that was done and the files that were affected.
 * Status: (e.g., Completed, In Progress)
---

Context for Future Agents
This section contains important information to help future AI agents understand the project quickly and contribute effectively.
1. Technology Stack
 * Language: Java 21
 * Build Tool: Gradle with Kotlin DSL (build.gradle.kts)
 * UI Library: Java Swing
 * Testing: JUnit 5 (no tests currently implemented)
2. Core Architecture
 * com.jordan.blockstacker.Main: The main entry point of the application. It creates and configures the main `JFrame`.
 * com.jordan.blockstacker.Display: The main `JFrame` for the game. It handles rendering the game state (the grid and the pieces) and listens for keyboard input.
 * com.jordan.blockstacker.Scene: Contains the core game logic, including managing the game board, handling piece movement, collision detection, and clearing completed lines.
 * com.jordan.blockstacker.shape: This package contains classes related to the Tetris pieces (`Shape.java` and `Block.java`).
 * com.jordan.blockstacker.core: This package contains helper classes like `MyVector.java`.
 * com.jordan.blockstacker.colorThings: This package contains the `ColourUtils.java` utility for piece colors.
3. Project Goals & Rules of Engagement
 * Primary Goal: To serve as a showcase for human-AI software development collaboration.
 * Contribution Style: Each major task or feature should be completed in a separate branch and submitted as a pull request.
 * Logging: Upon completing a task, you must add an entry to the "Contribution Log" in this file.
