# Patience Card Game – Java & OOP  
## Project Overview  
This project is a **Object-Oriented Programming** assignment from **Aberystwyth University**, focusing on **Java-based game development** using **OOP principles** like **encapsulation, inheritance, and file I/O**. The game is a **command-line version of Patience (Solitaire)**, where players manage a deck of cards with **shuffling, card drawing, move validation, automated moves, and scoring mechanics**. The project also includes a **UML diagram**, **testing documentation**, and **file handling for a persistent scoreboard**.  

## Prerequisites  
Java 17 or later, JavaFX (for graphical display), IDE such as IntelliJ IDEA or Eclipse.  

## Installation & Setup  
Clone the repository: `git clone https://github.com/your-repo/patience-java.git && cd patience-java`  
Compile and run the game: `javac Game.java && java Game`  

## Game Features  
The game starts with a **menu-based interface**, allowing players to **shuffle the deck, draw cards, make moves, and view the top 10 scores**. The **Card** class represents individual cards with suits and values, while the **Player** class manages **player data and sorting for the leaderboard**. The **Game** class controls **game logic, move execution, and file I/O operations**, ensuring scores are saved and loaded. A **Graphical UI using JavaFX** is partially implemented but not yet functional for gameplay.  

## Core Components  
The **Game** class handles **menu interactions, deck management, move execution, and leaderboard operations**. The **Card** class provides **suit and value management**, and the **Player** class supports **score tracking and ranking**.  

## Troubleshooting  
If the **game does not compile**, ensure **JavaFX is correctly installed**. If **scores are not saved**, check **file paths and I/O exceptions**. If **moves do not execute correctly**, verify **the logic in move validation methods**.  

## Future Improvements  
Enhancements could include **a fully functional JavaFX interface**, **improved AI for automated moves**, and **better rule validation to prevent illegal moves**.  

## License  
This project is released for **educational and research purposes**. Contributions and improvements are welcome!  
