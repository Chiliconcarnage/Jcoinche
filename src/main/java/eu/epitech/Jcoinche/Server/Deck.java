package eu.epitech.Jcoinche.Server;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    public ArrayList<Card> cards = new ArrayList<Card>();
    static int i = 8;
    static int j = 0;

    public ArrayList<Card> CardsDistribution(){
        ArrayList<Card> hand = new ArrayList<Card>();
        while (j < i && !cards.isEmpty()){
            hand.add(cards.get(j));
            j++;
        }
        i += 8;
        return hand;
    }

    public Card FirstCard(){
        Card card = new Card();

        card = cards.get(j);
        j++;
        return card;
    }

    public Deck() {

        cards.add(new Card(Card.Type.TREFLE, Card.Value.SEPT, 0, 0, false));
        cards.add(new Card(Card.Type.TREFLE, Card.Value.HUIT, 0, 0, false));
        cards.add(new Card(Card.Type.TREFLE, Card.Value.NEUF, 0, 14, false));
        cards.add(new Card(Card.Type.TREFLE, Card.Value.DIX, 10, 10, false));
        cards.add(new Card(Card.Type.TREFLE, Card.Value.VALET, 2, 20, false));
        cards.add(new Card(Card.Type.TREFLE, Card.Value.DAME, 3, 3, false));
        cards.add(new Card(Card.Type.TREFLE, Card.Value.ROI, 4, 4, false));
        cards.add(new Card(Card.Type.TREFLE, Card.Value.AS, 11, 11, false));

        cards.add(new Card(Card.Type.PIQUE, Card.Value.SEPT, 0, 0, false));
        cards.add(new Card(Card.Type.PIQUE, Card.Value.HUIT, 0, 0, false));
        cards.add(new Card(Card.Type.PIQUE, Card.Value.NEUF, 0, 14, false));
        cards.add(new Card(Card.Type.PIQUE, Card.Value.DIX, 10, 10, false));
        cards.add(new Card(Card.Type.PIQUE, Card.Value.VALET, 2, 20, false));
        cards.add(new Card(Card.Type.PIQUE, Card.Value.DAME, 3, 3, false));
        cards.add(new Card(Card.Type.PIQUE, Card.Value.ROI, 4, 4, false));
        cards.add(new Card(Card.Type.PIQUE, Card.Value.AS, 11, 11, false));

        cards.add(new Card(Card.Type.COEUR, Card.Value.SEPT, 0, 0, false));
        cards.add(new Card(Card.Type.COEUR, Card.Value.HUIT, 0, 0, false));
        cards.add(new Card(Card.Type.COEUR, Card.Value.NEUF, 0, 14, false));
        cards.add(new Card(Card.Type.COEUR, Card.Value.DIX, 10, 10, false));
        cards.add(new Card(Card.Type.COEUR, Card.Value.VALET, 2, 20, false));
        cards.add(new Card(Card.Type.COEUR, Card.Value.DAME, 3, 3, false));
        cards.add(new Card(Card.Type.COEUR, Card.Value.ROI, 4, 4, false));
        cards.add(new Card(Card.Type.COEUR, Card.Value.AS, 11, 11, false));

        cards.add(new Card(Card.Type.CARREAU, Card.Value.SEPT, 0, 0, false));
        cards.add(new Card(Card.Type.CARREAU, Card.Value.HUIT, 0, 0, false));
        cards.add(new Card(Card.Type.CARREAU, Card.Value.NEUF, 0, 14, false));
        cards.add(new Card(Card.Type.CARREAU, Card.Value.DIX, 10, 10, false));
        cards.add(new Card(Card.Type.CARREAU, Card.Value.VALET, 2, 20, false));
        cards.add(new Card(Card.Type.CARREAU, Card.Value.DAME, 3, 3, false));
        cards.add(new Card(Card.Type.CARREAU, Card.Value.ROI, 4, 4, false));
        cards.add(new Card(Card.Type.CARREAU, Card.Value.AS, 11, 11, false));
        Collections.shuffle(this.cards);
    }
}
