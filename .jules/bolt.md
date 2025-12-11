## 2024-05-22 - [Swing Game Loop Optimization]
**Learning:** In Swing-based game loops, unconditional `repaint()` calls can waste significant CPU/GPU resources, especially when the game logic update rate (e.g. falling blocks) is much slower than the loop rate.
**Action:** Use conditional repainting. Ensure that both the game loop (time-based updates) and input handlers (event-based updates) trigger repaints only when the game state actually changes.

## 2024-10-27 - [Simulation vs Calculation]
**Learning:** The codebase was validating rotations by cloning the entire Shape and Block structure to "test" the rotation. This caused significant object allocation on user input.
**Action:** Replace "clone-and-simulate" validation with mathematical prediction. Calculate the hypothetical coordinates using primitives to check validity without allocating objects.
