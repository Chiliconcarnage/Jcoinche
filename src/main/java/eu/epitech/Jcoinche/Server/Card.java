package eu.epitech.Jcoinche.Server;

import java.util.Random;

public class Card {

    public Type type;
    public Value value;
    public int Points;
    public int APoints;
    public boolean isAtout;
    private Random random = new Random();

    public Card(Type type, Value value, int Points, int APoints, boolean isAtout)
    {
        this.type = type;
        this.value = value;
        this.Points = Points;
        this.APoints = APoints;
        this.isAtout = isAtout;
    }

    public Card(){}

    public enum Type {
        TREFLE(0, "Trefle"),
        PIQUE(1, "Pique"),
        COEUR(2, "Coeur"),
        CARREAU(3, "Carreau");

        public int id;
        public String name;

        Type(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public enum Value {
        SEPT(0, "Sept"),
        HUIT(1, "Huit"),
        NEUF(2, "Neuf"),
        VALET(3, "Dix"),
        DAME(4, "Dame"),
        ROI(5, "Roi"),
        DIX(6, "Dix"),
        AS(7, "As");


        public int id;
        public String name;

        Value(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public Type getRandom() {
        return Type.values()[(int) (Math.random() * Type.values().length)];
    }
}