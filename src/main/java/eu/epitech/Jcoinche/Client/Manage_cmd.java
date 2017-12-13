package eu.epitech.Jcoinche.Client;

import java.util.Scanner;

public class Manage_cmd {
    public Manage_cmd() {}

    public void Parse_cmd(String cmd, Scanner sc, Manage_HandCard Hand, boolean turn) {
        if (turn == true) {
            if (cmd.equals("Show")) {
                Hand.Print_Hand();
            } else if (cmd.equals("Send")) {
                String card;
                System.out.println("What card would you like to play ?\n");
                card = sc.nextLine();
                if ((Hand.Check_card_validity(card)) == 1)
                    Hand.Send_card(card);
                else
                    System.out.println("This card is not in your hand.");
            } else
                System.out.println("Command unknown.\n");
        }
        else
            System.out.println("Ce n'est pas à vous de jouer.");
    }
}
