package eu.epitech.Jcoinche.Client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Connection;
import eu.epitech.Jcoinche.Server.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public class ClientConnection extends Listener {
    static Client client;
    static int udpPort = 22960, tcpPort = 22960;
    static String ip = "localhost";
    final PacketMessage packet = new PacketMessage();
    final public Manage_HandCard Hand = new Manage_HandCard();
    final public DataPlayer turn = new DataPlayer();
    public boolean turn_bool = false;

    public static void main(String[] args) throws Exception {

        System.out.println("Connecting to the client ...");

        if (args.length != 0) {
            ip = args[0];
        }
        client = new Client();
        client.getKryo().register(PacketMessage.class);
        client.getKryo().register(Card.class);
        client.getKryo().register(Card.Type.class);
        client.getKryo().register(Card.Value.class);
        client.getKryo().register(Deck.class);
        client.getKryo().register(ArrayList.class);
        client.getKryo().register(Manage_HandCard.class);
        client.getKryo().register(DataPlayer.class);
        client.getKryo().register(Players.class);
        client.getKryo().register(Game.class);
        client.getKryo().register(Random.class);
        client.getKryo().register(AtomicLong.class);

        client.start();
        client.addListener(new ClientConnection());
        client.connect(5000, ip, tcpPort, udpPort);

        System.out.println("Connected! The client program is now waiting for a packet ...\n");
        Thread.sleep(Long.MAX_VALUE);
    }


    public void received(Connection c, Object p){

        if (p instanceof PacketMessage){
            PacketMessage packet = (PacketMessage) p;
            System.out.println(packet.message);
        }
        if (p instanceof Manage_HandCard){
            Hand.Set_hand(((Manage_HandCard) p).Init_Hand);
        }
        if (p instanceof DataPlayer){
            DataPlayer turn = (DataPlayer) p;
            turn_bool = turn.Turn;
        }
    }

    public void connected(final Connection c) {

        final Scanner sc = new Scanner(System.in);
        final Manage_cmd cmd = new Manage_cmd();

        new Thread() {
            public void run () {
                try {
                    if (sc.hasNext()) {
                       while (true)
                         cmd.Parse_cmd(sc.nextLine(), sc, Hand, turn_bool);
                       }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        }.start();
    }
}