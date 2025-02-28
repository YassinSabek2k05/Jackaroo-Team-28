package model.card;
import engine.GameManager;
import engine.board.BoardManager;
import java.util.ArrayList;
import java.util.Collections;

import model.card.standard.*;
import model.card.wild.*;

/**
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
    static final String CARDS_FILE = "C:\\Users\\pc\\Desktop\\Cs\\cs4\\java\\Jackaroo\\Cards.csv";
    static ArrayList<Card> cardsPool = new ArrayList<>(); 

    public Deck()
    {
        super();
    }
    public static void loadCardPool(BoardManager boardManager, GameManager gameManager) throws IOException{
        String filePath = CARDS_FILE; 
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Card card;
                String[] values = line.split(",");
                switch(Integer.parseInt(values[0]))
                {
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
                int frequency = Integer.parseInt(values[1]);
                if(card!=null)for(;frequency>=0;frequency--){
                    cardsPool.add(card);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static ArrayList<Card> drawCards(){
        Collections.shuffle(cardsPool);
        ArrayList<Card> cardsDrawn = new ArrayList<>();
        for(int i=4;i>=0;i--){
            cardsDrawn.add(cardsPool.removeFirst());
        }
        return cardsDrawn;
    }
    public static void main(String[] args) throws IOException {
        
        loadCardPool(new BoardManager(){
            @Override
            public int getSplitDistance() {
                return 4;
            }
        },new GameManager(){

        });
        System.out.println(cardsPool.size());
        ArrayList<Card> a = drawCards();
        for(int i=0;i<a.size();i++){
            System.out.println(a.get(i).getName());
        }
        System.out.println(cardsPool.size());


    }
}
