package eu.epitech.Jcoinche.Client;

import eu.epitech.Jcoinche.Server.Card;
import eu.epitech.Jcoinche.Server.DataPlayer;
import eu.epitech.Jcoinche.Server.Players;

import java.util.ArrayList;
import static eu.epitech.Jcoinche.Client.ClientConnection.client;

public class Manage_HandCard{

    public ArrayList<Card> Init_Hand;

    public Manage_HandCard(){}

    public void Print_Hand() {
        if (Init_Hand.get(0).value != null)
            System.out.println("0 : "+Init_Hand.get(0).value+" de "+Init_Hand.get(0).type+".");
         if (Init_Hand.get(1).value != null)
            System.out.println("1 : "+Init_Hand.get(1).value+" de "+Init_Hand.get(1).type+".");
         if (Init_Hand.get(2).value != null)
              System.out.println("2 : "+Init_Hand.get(2).value+" de "+Init_Hand.get(2).type+".");
         if (Init_Hand.get(3).value != null)
             System.out.println("3 : "+Init_Hand.get(3).value+" de "+Init_Hand.get(3).type+".");
         if (Init_Hand.get(4).value != null)
            System.out.println("4 : "+Init_Hand.get(4).value+" de "+Init_Hand.get(4).type+".");
         if (Init_Hand.get(5).value != null)
             System.out.println("5 : "+Init_Hand.get(5).value+" de "+Init_Hand.get(5).type+".");
         if (Init_Hand.get(6).value != null)
             System.out.println("6 : "+Init_Hand.get(6).value+" de "+Init_Hand.get(6).type+".");
         if (Init_Hand.get(7).value != null)
            System.out.println("7 : "+Init_Hand.get(7).value+" de "+Init_Hand.get(7).type+".");
    }

    public int Check_card_validity(String cmd){
        int value;

        value = Integer.parseInt(cmd);
        if (value <= 7 && Init_Hand.get(value).value != null) {
            return (1);
        }
        return (0);
    }

    public void Send_card(String card){
        int value;

        value = Integer.parseInt(card);
        client.sendTCP(Init_Hand.get(value));
        System.out.println("Vous venez de jouer votre "+Init_Hand.get(value).value+" de "+Init_Hand.get(value).type+".");
        Init_Hand.get(value).value = null;
    }

    public void Set_hand(ArrayList<Card> Hand){
        Init_Hand = Hand;
    }
}