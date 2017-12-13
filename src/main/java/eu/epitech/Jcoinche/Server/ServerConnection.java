package eu.epitech.Jcoinche.Server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import eu.epitech.Jcoinche.Client.Manage_HandCard;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class ServerConnection extends Listener {

    static Server server;
    final Deck deck = new Deck();
    final Game game = new Game();
    static int udpPort = 22960, tcpPort = 22960;
    final PacketMessage packet = new PacketMessage();
    int roundPlayers = 1;
    final Players players = new Players();
    int pli = 1;

    public static void main(String[] args) throws Exception {

        System.out.println("Creating the server ...");

        server = new Server();
        server.start();
        server.bind(tcpPort, udpPort);
        server.addListener(new ServerConnection());
        server.getKryo().register(PacketMessage.class);
        server.getKryo().register(Card.class);
        server.getKryo().register(Card.Type.class);
        server.getKryo().register(Card.Value.class);
        server.getKryo().register(Deck.class);
        server.getKryo().register(ArrayList.class);
        server.getKryo().register(Manage_HandCard.class);
        server.getKryo().register(DataPlayer.class);
        server.getKryo().register(Players.class);
        server.getKryo().register(Game.class);
        server.getKryo().register(Random.class);
        server.getKryo().register(AtomicLong.class);

        System.out.println("Server is operational!");

    }

    public void connected(final Connection c) {

        if (c.getID() > 4) {
            PacketMessage packet = new PacketMessage();
            packet.message = "La partie est pleine !";
            c.sendTCP(packet);
            c.close();
        } else {
            if (c.getID() == 4){
                game.GameLoop(server, deck, players);
            }else {
                final PacketMessage packet = new PacketMessage();
                packet.message = "En attente d'autres joueurs ...";
                c.sendTCP(packet);
            }

        }
    }

    int i = 0;
    int j = 0;

    public void received(Connection c, Object p) {
        if (c.getID() == roundPlayers) {
            if (i < 1) {
                packet.message = "C'est à vous de jouer";
                packet.protValue = 1;
                server.sendToTCP(c.getID(), packet);
                i++;
            }
            if (p instanceof PacketMessage) {
                PacketMessage packet = (PacketMessage) p;
                System.out.println(packet.message);
            }
            if (p instanceof Card){
                Card card = (Card) p;
                players.clients.get(c.getID()-1).cardPlay = card;
                PacketMessage packet = new PacketMessage();
                packet.message = "Le joueur " + c.getID() + " a joué " + card.value.name + " de " + card.type.name;
                server.sendToAllTCP(packet);
                players.clients.get(c.getID()-1).setTurn(false);
                server.sendToTCP(c.getID(), players.clients.get(c.getID()-1));
                if (c.getID() == 4) {
                    players.clients.get(0).setTurn(true);
                    server.sendToTCP(1, players.clients.get(0));
                    game.EndRound(server, deck, players);
                } else {
                    players.clients.get(c.getID()).setTurn(true);
                    server.sendToTCP(c.getID()+1, players.clients.get(c.getID()));
                }
                roundPlayers++;
                i = 0;
                j = 0;
            }
        }else{
            if (j < 3) {
                packet.message = "C'est au joueur " + roundPlayers + " de jouer!";
                packet.protValue = 404;
                server.sendToTCP(c.getID(), packet);
                j++;
            }
        }
        if (roundPlayers == 5){
            pli++;
            roundPlayers = 1;
            PacketMessage packetEnd = new PacketMessage();
            if (pli == 9){
                if (players.clients.get(0).PointsGame > players.clients.get(1).PointsGame){
                    packetEnd.message = "Equipe Joueurs 1 et 3 remporte la partie avec " + players.clients.get(0).PointsGame + " points !";
                }else{
                    packetEnd.message = "Equipe Joueurs 1 et 3 remporte la partie avec " + players.clients.get(1).PointsGame + " points !";
                }
                server.close();
            }
            packetEnd.message = "Début du " + pli + "ème pli.";
            server.sendToAllTCP(packetEnd);
        }
    }

    public void disconnected(Connection c) {
        System.out.println("A client disconnected!");
    }
}
