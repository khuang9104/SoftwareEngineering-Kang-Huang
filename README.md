# Backgammon - Software Engineering Group Project
### Course name: Software Engineering
### Course code: COMP41670
### Group 6
### Group member: Kang Huang(20211924),  
<br>

### Sprint 1: Display & Rolls
#### 1. A feature whereby the Backgammon board is displayed with the Checkers in their initial positions.<br> (Done. Checked on Nov.10.2022 by Kang)
Oct.21.2022 Kang:<br>
The backgammon gameboard UI design and UI display function are nearly done. Waiting for the Bar class and BearOffCalculator class to complete the entire UI component (UI version.1.0). 
<br>Nov.10.2022 Kang:<br>
Updated the UI components (UI version.2.0). 
   
#### 2. A feature that prompts the players to enter their names. Their names should be used in later prompts. (Done. Checked on Oct.27.2022 by Kang)
Oct.27.2022 Kang:<br>
Basic input and output component added and tested. Prompts feature works well.

#### 3. A feature that allows the players to take turns rolling the dice. The players should be prompted to enter a command. Entering the “roll” command should cause the result of a two dice roll to displayed on the board panel and reported in the log panel. The dice should be generated randomly. (Done. Checked on Oct.27.2022 by Kang)
Oct.27.2022 Kang:<br>
Dice rolling components added and tested. The feature that allows the players to take turns rolling the dice works well.

#### 4. A feature whereby a “quit” command causes termination of the program. (Done. Checked on Oct.27.2022 by Kang)
Oct.27.2022 Kang:<br>
Basic input and output components added and tested, 'quit' command works well.


### Sprint 2: Game
#### 1. A feature whereby when the game starts, the program rolls one die for each player to determine which player goes first. The result is used for the first move. (Done. Checked on Oct.28.2022 by Kang)
Oct.28.2022 Kang:<br>
Feature added and tested, each player can roll a dice to determined who go first when game start. (When equal, ask to reroll).
   
#### 2. A feature whereby the current player is indicated on the display. (Done. Checked on Oct.28.2022 by Kang)
Oct.28.2022 Kang:<br>
The feature was added and tested and the prompt will show whose turn it is.

#### 3. A feature that displays the pip number of every point on the board. The pip numbers should change depending on which player’s turn it is.


#### 4. A feature which lists all legal moves after the dice roll and allows the user to enter a letter code for the desired move. The board should be updated to reflect the move selected. All hits and bear off should be applied. All rules of backgammon should be considered.

#### 5. A “pip” command which reports the pip count for both players.

#### 6. A “hint” command which lists all allowed commands. The list should exclude the commands for testing.

#### 7. A feature whereby the syntax of the commands entered are checked. An appropriate error message is generated if the command is invalid.

#### 8. A feature that detects when the game is over and announces the winner of a game.
