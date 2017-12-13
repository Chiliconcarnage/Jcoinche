package eu.epitech.Jcoinche.Server;

import com.esotericsoftware.kryonet.Server;
import eu.epitech.Jcoinche.Client.Manage_HandCard;

public class Game {

    public Game(){}

    public void GameLoop(Server server, Deck deck, Players players){
        int i = 1;
        int j = 0;

        Card selectAt = new Card();
        Card.Type Atout = selectAt.getRandom();
        while (j < deck.cards.size()){
            if (deck.cards.get(j).type.id == selectAt.getRandom().id)
                deck.cards.get(j).isAtout = true;
            j++;
        }

        while (i <= 4) {
            final Manage_HandCard Hand = new Manage_HandCard();
            Hand.Init_Hand = deck.CardsDistribution();
            server.sendToTCP(i, Hand);
            i++;
        }
        server.sendToTCP(1, players.clients.get(0));
        server.sendToTCP(2, players.clients.get(1));
        server.sendToTCP(3, players.clients.get(2));
        server.sendToTCP(4, players.clients.get(3));

        PacketMessage packet = new PacketMessage();
        packet.message = Atout.name + " est à l'atout.";
        server.sendToAllTCP(packet);
    }

    public void EndRound(Server server, Deck deck, Players players){
        int i = 0;
        int maxValue = 0;
        int totValue = 0;
        int winner = 0;
        while (i < 4) {
            if (players.clients.get(i).cardPlay.isAtout){
                if (players.clients.get(i).cardPlay.APoints > maxValue){
                    maxValue = players.clients.get(i).cardPlay.APoints;
                }
                totValue += players.clients.get(i).cardPlay.APoints;
            }else{
                if (players.clients.get(i).cardPlay.Points > maxValue){
                    maxValue = players.clients.get(i).cardPlay.Points;
                }
                totValue += players.clients.get(i).cardPlay.Points;
            }
            i++;
        }
        i = 0;
        while (i < 4){
            if (maxValue == players.clients.get(i).cardPlay.APoints ||
                    maxValue == players.clients.get(i).cardPlay.Points){
                winner = i;
            }
            i++;
        }
        PacketMessage packet = new PacketMessage();
        if (winner == 0 || winner == 2){
            packet.message = "Le joueur 1 et 3 remporte le pli avec " + totValue + " points.";
            server.sendToAllTCP(packet);
            players.clients.get(0).setPointsGame(players.clients.get(0).PointsGame + totValue);
            players.clients.get(2).setPointsGame(players.clients.get(2).PointsGame + totValue);
        }else{
            packet.message = "Le joueur 2 et 4 remporte le pli avec " + totValue + " points.";
            server.sendToAllTCP(packet);
            players.clients.get(1).setPointsGame(players.clients.get(1).PointsGame + totValue);
            players.clients.get(3).setPointsGame(players.clients.get(3).PointsGame + totValue);
        }
        packet.message = "Equipe Joueurs 1 et 3: " + players.clients.get(0).PointsGame;
        server.sendToAllTCP(packet);
        packet.message = "Equipe Joueurs 2 et 4: " + players.clients.get(1).PointsGame;
        server.sendToAllTCP(packet);
    }
}
