

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;


import javafx.application.Application;
import javafx.stage.Stage;
import uk.ac.aber.dcs.cs12320.cards.gui.javafx.CardTable;

/**
 * The Game of patience main class
 * @author Jamaine Christian
 * @version 2.0
 */
public class Game extends Application {

    private CardTable cardTable;
    private final Scanner scan;
    public ArrayList<Card> deck;
    public ArrayList<Card> cardsOnTable;
    public ArrayList<Player> players;

    public int score = 1000; /* int for the score */
    int y = 0; /* int used for checking whether moves are available*/

    private final static String file1 = "cards.txt";
    private final static String file2 = "scoreBoard.txt";

    public Game() {
        scan = new Scanner(System.in);
    }

    @Override
    public void start(Stage stage) {
        cardTable = new CardTable(stage);

        // The interaction with this game is from a command line
        // menu. We need to create a separate non-GUI thread
        // to run this in. DO NOT REMOVE THIS.
        Runnable commandLineTask = () -> {
            // REPLACE THE FOLLOWING  EXAMPLE WITH YOUR CODE
            ArrayList<String> cardStrings = new ArrayList<>();
            cardStrings.add("as.gif");
            cardStrings.add("tc.gif");
            cardStrings.add("js.gif");
            cardStrings.add("4d.gif");
            cardTable.cardDisplay(cardStrings);
        };
        Thread commandLineThread = new Thread(commandLineTask);
        // This is how we start the thread.
        // This causes the run method to execute.
        commandLineThread.start();
    }

    /**
     * method for printing out the rules
     */
    private void printRules(){
        System.out.println("*************************RULES OF THE GAME***************************");
        System.out.println("1) Deal cards on to the table.");
        System.out.println("2) If a card is next to a card which shares the same suit or number");
        System.out.println("   as the card to its left, then you can place this card on top of ");
        System.out.println("   the left one. ");
        System.out.println("3) If a card is 3 places away from one that shares the same suit or");
        System.out.println("   number then you can place the right one on top of the one to its");
        System.out.println("   left.");
        System.out.println("4) Your score starts at 1000 and decreases with every move, so make");
        System.out.println("   sure you don't make mistakes");
        System.out.println("5) If 'play for me once' is used then an additional 10 point will ");
        System.out.println("   be deducted.");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("                      PRESS ENTER TO BEGIN");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("*********************************************************************");


    }

    /**
     * method for running the menu
     */
    private void runMenu() {
        scan.nextLine(); /* takes any key to start app*/
        String choice;
        do {
          //  checkForMoves(); /* this is causing an error so is commented out*/
            printMenu();
            choice = scan.nextLine().toUpperCase();
            switch (choice) {
                case "1":
                    showThePack();
                    break;
                case "2":
                    shuffleDeck();
                    break;
                case "3":
                    dealCard();
                    break;
                case "4":
                    makeMove();
                    break;
                case "5":
                    playOnce();
                    break;
                case "6":
                    System.out.println(cardsOnTable);
                    break;
                case "7":
                    topTen();
                    break;
                case "Q":
                    break;
                default:
                    System.out.println("Not a valid input");
            }
        } while (!(choice.equals("Q")|| y == 2));
    }


    /**
     * method for printing out the menu on command line
     */
    private static void printMenu(){
        System.out.println("***********************Patience By JAC105****************************");
        System.out.println("**************************MAKE A CHOICE******************************");
        System.out.println("1 - Show the pack");
        System.out.println("2 - Shuffle the deck");
        System.out.println("3 - Deal a card");
        System.out.println("4 - Make a move");
        System.out.println("5 - Play for me once ");
        System.out.println("6 - Display the cards on the table");
        System.out.println("7 - Display top 10 results");
        System.out.println("Q - quit (remember to close the graphical interface)");
        System.out.println("************************ENTER CHOICE BELOW***************************");
    }


    /**
     * method for showing all cards left in the deck
     */
    private void showThePack() {
        System.out.println(deck);
    }

    /**
     * method for shuffling the deck
     */
    private void shuffleDeck() {
        Collections.shuffle(deck);
        System.out.println("CARDS HAVE BEEN SHUFFLED");
    }

    /**
     * method for dealing a card on to the table
     */
    private void dealCard() {
        if (deck.size() > 0) {
            int topCard = deck.size() - 1;
            cardsOnTable.add(deck.get(topCard));
            deck.remove(topCard);
            System.out.println(cardsOnTable);
        }
        else {
            System.out.println(cardsOnTable);
            System.err.println("ALL CARDS ARE ON THE TABLE");
        }
    }


    /**
     * method for checking if there are any moves to be made
     */
    private void checkForMoves(){
        int x = 0;

        for (int i = 0; i<cardsOnTable.size() - 4; i++) {
            if (cardsOnTable.get(i).getValue().equals(cardsOnTable.get(i + 1).getValue()) && cardsOnTable.get(i).getValue().equals(cardsOnTable.get(i + 3).getValue())
                    || (cardsOnTable.get(i).getSuit().equals(cardsOnTable.get(i + 1).getSuit()) && cardsOnTable.get(i).getSuit().equals(cardsOnTable.get(i + 3).getSuit()))){
                x = x + 10;
            }
        }
        for (int i = cardsOnTable.size() - 4; i<cardsOnTable.size() - 1; i++){
            if ( cardsOnTable.get(i).getValue().equals(cardsOnTable.get(i+1).getValue()) || cardsOnTable.get(i).getSuit().equals(cardsOnTable.get(i+1).getSuit())){
                x = x + 10;
            }
            else {
                x = x - 1;
            }
        }

        //if every card has no moves, the amount of cardsOnTable * -1 will be the x value
        if ((deck.size() == 0) && (cardsOnTable.size() > 2) && (x == -cardsOnTable.size())) {
            y = -1;
        }

    }


    /**
     * method for moving last card on to the one before it
     */
    private void moveLast1() {
        if (cardsOnTable.size() > 1) {
            Card lastCard = cardsOnTable.get(cardsOnTable.size() - 1);
            Card secondLastCard = cardsOnTable.get(cardsOnTable.size() - 2);

            if (lastCard.getSuit().equals(secondLastCard.getSuit()) || lastCard.getValue().equals(secondLastCard.getValue())) {
                cardsOnTable.remove(secondLastCard);
            } else {
                System.err.println("THIS IS AN ILLEGAL MOVE, CHECK THE RULES");
            }
        }
    }

    /**
     * method for moving last card on top of one three places to its left
     */
    private void moveLast3() {
        if (cardsOnTable.size() > 3) {
            Card lastCard = cardsOnTable.get(cardsOnTable.size() - 1);
            Card thirdLast = cardsOnTable.get(cardsOnTable.size() - 4);
            if (lastCard.getSuit().equals(thirdLast.getSuit()) || lastCard.getValue().equals(thirdLast.getValue())) {
                int thirdCardLeft = cardsOnTable.size() - 4;
                cardsOnTable.remove(thirdCardLeft);
                cardsOnTable.add(cardsOnTable.size() - 3, lastCard);
                cardsOnTable.remove(cardsOnTable.size() - 1);
            } else {
                System.err.println("THIS IS AN ILLEGAL MOVE, CHECK THE RULES");
            }
        }
    }

    /**
     * method for merging piles in the middle
     */
    private void amalgamate(){
        System.out.println("Which card do you want to put on top of one three places to its left? (type its position");
        System.out.println(", they are numbered from left to right, starting at 1)");
        int input = scan.nextInt() -1; // card on right
        scan.nextLine();
        System.out.println("What is the card you are placing this on (type its position starting at 1, from left to ");
        System.out.println("right");
        int input2 = scan.nextInt() -1; // card on left
        scan.nextLine();
        if(input2 > 0 && input>input2 && input2 == input - 3 ){
            Card rightCard = cardsOnTable.get(input);
            Card leftCard = cardsOnTable.get(input2);
            if ((rightCard.getSuit().equals(leftCard.getSuit())) || rightCard.getValue().equals(leftCard.getValue())) {
                cardsOnTable.remove(input2);
                cardsOnTable.add(input2, rightCard);
                cardsOnTable.remove(input);
            }
            else {
                System.err.println("THIS IS AN ILLEGAL MOVE, CHECK THE RULES");
            }
        }
        else {
            System.err.println("THIS IS NOT AN OPTION");
        }
    }

    /**
     * method for amalgamating automatically
     * @param input is the input of the right card
     * @param input2 is the input of the left card
     */
    private void amalgamateAuto(int input,int input2){

        if(input2 > 0 && (input>input2 || input2 == input - 3) ) {
            Card rightCard = cardsOnTable.get(input);
            Card leftCard = cardsOnTable.get(input2);
            if (rightCard.getSuit().equals(leftCard.getSuit()) || rightCard.getValue().equals(leftCard.getValue())) {
                cardsOnTable.remove(input2);
                cardsOnTable.add(input2, rightCard);
                cardsOnTable.remove(input);
            } else {
                System.err.println("THIS IS AN ILLEGAL MOVE, CHECK THE RULES");
            }
        }
        else {
            System.err.println("THIS MOVE IS IMPOSSIBLE");
        }
    }

    /**
     * method for making a move and choosing which type.
     */
    private void makeMove() {
        score = score - 10; //score goes down as you play, so mistakes will cost you
    System.out.println("*************WHAT TYPE OF MOVE WOULD YOU LIKE TO DO******************");
    System.out.println("Enter '1' to move last card onto the one on its left");
    System.out.println("Enter '2' to move last card onto the one three places to its left");
    System.out.println("Enter '3' to move a card in the middle onto the one three places to");
    System.out.println("its left");
    System.out.println("*************************ENTER BELOW*********************************");
    int choice = scan.nextInt();
    scan.nextLine();
    if (choice == 1) {
        moveLast1();

    } else if (choice == 2) {
        moveLast3();

    } else if (choice == 3) {
        amalgamate();

    }
      else {
        System.err.println("INVALID INPUT");
    }
    }


    //this method isn't functioning properly
    /**
     * method for autoplay
     */
    private void playOnce() {
        Random choice = new Random();
        int number1;
        int number2;
        int number3;

            number1 = 1 + choice.nextInt();
            number2 = 1 + choice.nextInt();
            number3 = 1 + choice.nextInt(cardsOnTable.size());
            if (number1 == 1) {
                moveLast1();
                System.out.println("LAST CARD HAS BEEN PLACED ON TOP OF THE THE ONE TO ITS LEFT");
                score = score - 20; /* extra 10 as a penalty for using playOnce */
            } else if (number1 == 2) {
                moveLast3();
                System.out.println("LAST CARD HAS BEEN PLACED ON THE CARD THREE PLACES TO ITS LEFT");
                score = score - 20;
            } else if (number1 == 3) {
                amalgamateAuto(number2, number3);
                score = score - 20;
                System.out.println("AMALGAMATION HAS OCCURRED");
            } else {
                System.err.println("METHOD IS NOT FUNCTIONING PROPERLY");
            }

    }

    /**
     * method for showing top ten results
     */
    private void topTen() {
        Collections.sort(players);
        System.out.println("Here are the top ten scores");
        for (int i = 0; i < 10; i++){
            System.out.println(players.get(i));
        }
    }


    /**
     * method for saving score to the leaderboards
     * @param filename is the txt file of the scoreboard
     * @throws IOException in case of an input/output error
     */
    public void saveScoreBoard(String filename) throws IOException {
        if (players.size() > 0) {
            System.out.println("=====YOUR SCORE IS: " + score + "=====");
            System.out.println("Enter your name: ");
            Player newPlayer = new Player(scan.next(),score);
            players.add(newPlayer);
            PrintWriter outfile = new PrintWriter(new FileWriter(filename));
            for (Player playerS : players) {
                outfile.print(playerS.getName() + " " + playerS.getScore() + " ");
            }
            outfile.close();
            System.out.println("SAVE SUCCESSFUL");
        }
    }

    /**
     * method for loading in the scoreboard data
     * @param filename is the file of the scoreboard
     * @throws IOException in case of an input/output error
     * @throws NullPointerException in case it reads the file at the wrong place
     */
    public void loadScoreBoard(String filename) throws IOException, NullPointerException{
        players = new ArrayList<>();

        try (FileReader fr2 = new FileReader(filename);
             Scanner scan = new Scanner(fr2)) {
            // scan.nextLine();
            while (scan.hasNext()) {
                this.players.add(new Player(scan.next(), scan.nextInt()));
            }
        }
    }

    /**
     * method for loading the deck of cards from a txt file
     * @param filename txt file containing all the cards
     * @throws IOException in case of an input/output error
     */
    public void loadDeck(String filename) throws IOException {
        deck = new ArrayList<>(52);

        try (FileReader fr = new FileReader(filename);
             Scanner scan = new Scanner(fr)) {
            while (scan.hasNextLine()) {
                deck.add(new Card(scan.nextLine(), scan.nextLine()));
            }
        }
    }

    /**
     * method to play the game
     * @throws IOException in case of an input/output error
     */
    public void playGame() throws IOException {
        loadDeck(file1);
        loadScoreBoard(file2);
        printRules();
        cardsOnTable = new ArrayList<>();
        runMenu();
        saveScoreBoard(file2);
    }



    // //////////////////////////////////////////////////////////////
    public static void main(String[] args) throws IOException {

        Game patience = new Game();
        patience.playGame();
        Application.launch(args);
    }
}
