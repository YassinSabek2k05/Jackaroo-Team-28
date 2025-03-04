package model.card;
import engine.GameManager;
import engine.board.BoardManager;
import java.util.ArrayList;
import java.util.Collections;

import model.card.standard.*;
import model.card.wild.*;

/*
 * CSV to List Example
 * Generated with AI assistance (ChatGPT)
 * Session Log:
 * - User asked how to import a CSV file into a Java project.
 * - AI provided methods using BufferedReader and OpenCSV.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Deck
{
    private static final String CARDS_FILE = "Cards.csv";
    private static ArrayList<Card> cardsPool; 
    private Deck(){
        //private default constructor to prevent instantiation of Deck object
    }
    public static void loadCardPool(BoardManager boardManager, GameManager gameManager) throws IOException{
        String filePath = CARDS_FILE;
        cardsPool = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int code = Integer.parseInt(values[0]);
                int frequency = Integer.parseInt(values[1]);

                for(;frequency>0;frequency--)
                    switch(code)
                    {
                        case 1:
                            cardsPool.add(new Ace(values[2], values[3], Suit.valueOf(values[5]), boardManager, gameManager));
                            break;
                        case 4: 
                            cardsPool.add(new Four(values[2], values[3], Suit.valueOf(values[5]), boardManager, gameManager));
                            break;
                        case 5: 
                            cardsPool.add(new Five(values[2], values[3], Suit.valueOf(values[5]), boardManager, gameManager));
                            break;
                        case 7: 
                            cardsPool.add(new Seven(values[2], values[3], Suit.valueOf(values[5]), boardManager, gameManager));
                            break;
                        case 10: 
                            cardsPool.add(new Ten(values[2], values[3], Suit.valueOf(values[5]), boardManager, gameManager));
                            break;
                        case 11: 
                            cardsPool.add(new Jack(values[2], values[3], Suit.valueOf(values[5]), boardManager, gameManager));
                            break;
                        case 12: 
                            cardsPool.add(new Queen(values[2], values[3], Suit.valueOf(values[5]), boardManager, gameManager));
                            break;
                        case 13: 
                            cardsPool.add(new King(values[2], values[3], Suit.valueOf(values[5]), boardManager, gameManager));
                            break;
                        case 14:                    
                            cardsPool.add(new Burner(values[2], values[3], boardManager, gameManager));
                            break;
                        case 15: 
                            cardsPool.add( new Saver(values[2], values[3], boardManager, gameManager));
                            break;
                        case 0: 
                            cardsPool.add(new Standard(values[2], values[3], Integer.parseInt(values[4]), Suit.valueOf(values[5]), boardManager, gameManager));
                            break;
                    }
                // System.out.println("Loaded card: " + values[2] + " with code: " + code+" frequency: "+frequency);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static ArrayList<Card> drawCards(){
        Collections.shuffle(cardsPool);
        ArrayList<Card> cardsDrawn = new ArrayList<Card>();
        for (int i = 0; i < 4 && !cardsPool.isEmpty(); i++) {
            cardsDrawn.add(cardsPool.remove(0));
        }
        return cardsDrawn;
    }
//     public static void main(String[] args) throws IOException {
//         //testing

//         loadCardPool(new BoardManager(){
//             @Override
//             public int getSplitDistance() {return 4;}},new GameManager(){});
//         System.out.println(cardsPool.size());
//         ArrayList<Card>a=cardsPool;
//         for(int i=0;i<a.size();i++){
//             System.out.println("Name: "+a.get(i).getName() +" Description: "+ a.get(i).getDescription()+" number:"+(i+1));
//         }
//         while(!cardsPool.isEmpty()){
//             drawCards();
//             System.out.println(cardsPool.size());

//         }
//  }
}
/*
                switch(code){
                    case 1: card = new Ace(values[2], values[3], Suit.valueOf(values[5]), boardManager, gameManager);break;
                    case 4: card = new Four(values[2], values[3], Suit.valueOf(values[5]), boardManager, gameManager);break;
                    case 5: card = new Five(values[2], values[3], Suit.valueOf(values[5]), boardManager, gameManager);break;
                    case 7: card = new Seven(values[2], values[3], Suit.valueOf(values[5]), boardManager, gameManager);break;
                    case 10: card = new Ten(values[2], values[3], Suit.valueOf(values[5]), boardManager, gameManager);break;
                    case 11: card = new Jack(values[2], values[3], Suit.valueOf(values[5]), boardManager, gameManager);break;
                    case 12: card = new Queen(values[2], values[3], Suit.valueOf(values[5]), boardManager, gameManager);break;
                    case 13: card = new King(values[2], values[3], Suit.valueOf(values[5]), boardManager, gameManager);break;
                    case 14: card = new Burner(values[2], values[3], boardManager, gameManager);break;
                    case 15: card = new Saver(values[2], values[3], boardManager, gameManager);break;
                    default: card = new Standard(values[2], values[3], Integer.parseInt(values[4]), Suit.valueOf(values[5]), boardManager, gameManager);break;//code=0
                }
                
 */
