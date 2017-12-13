package eu.epitech.Jcoinche.Server;

import java.util.ArrayList;

public class Players {
    public ArrayList<DataPlayer> clients = new ArrayList<DataPlayer>();

    public Players(){
        clients.add(new DataPlayer(1, true));
        clients.add(new DataPlayer(2, false));
        clients.add(new DataPlayer(3, false));
        clients.add(new DataPlayer(4, false));
    }
}