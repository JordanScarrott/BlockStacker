# Block Stacker Game Rules

This document outlines the rules of the Block Stacker game, as implemented in the source code.

## 1. Game Objective

The primary objective of Block Stacker is to score points by completing horizontal lines of blocks. The game is endless, and the goal is to play as long as possible and achieve the highest score.

## 2. The Game Board

*   The game is played on a square grid (e.g., 10x10 or 20x20, defined at the start of the game).
*   The board starts empty.

## 3. Shapes (Pieces)

*   The game pieces are called "Shapes," which are based on the seven classic Tetris pieces (I, O, T, L, J, S, Z).
*   Each shape is composed of four individual "Blocks."
*   A new, random shape spawns at the top center of the game board.

## 4. Player Controls

Players can manipulate the currently falling shape using the following controls:

*   **Move Left:** `A`
*   **Move Right:** `D`
*   **Move Down (Soft Drop):** `S`
*   **Rotate:** `Q` (rotates the shape 90 degrees clockwise)

## 5. Game Progression

1.  **Automatic Descent:** The active shape automatically moves down one grid unit at a regular interval (a "step").
2.  **Collision:** A shape cannot move outside the boundaries of the game board (left, right, or bottom walls). It also cannot move into a grid cell that is already occupied by a previously locked block.
3.  **Locking:** When a shape attempts to move down but is blocked by either the bottom of the board or another locked block, it stops moving and becomes a permanent part of the grid.
4.  **Spawning a New Shape:** Immediately after a shape locks into place, a new random shape spawns at the starting position at the top of the board.

## 6. Clearing Lines

*   When a horizontal row on the grid is completely filled with blocks, that row is "cleared."
*   All blocks in the cleared row are removed.
*   All blocks in the rows above the cleared row shift down by one grid unit to fill the empty space.
*   It is possible to clear multiple lines with a single piece.

## 7. Game Over

*   The game ends when a new shape is spawned, but its starting position is already obstructed by locked blocks on the grid.
*   This typically happens when the stack of blocks reaches the top of the board where new pieces spawn.
