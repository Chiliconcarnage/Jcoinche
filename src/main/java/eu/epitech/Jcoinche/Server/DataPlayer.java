package eu.epitech.Jcoinche.Server;


public class DataPlayer {
    public int          Id;
    public boolean      Turn;
    public Card         cardPlay;
    public int          PointsGame = 0;

    public DataPlayer(int Id, boolean Turn){
        this.Id = Id;
        this.Turn = Turn;
    }

    public DataPlayer() {}

    public void setTurn(boolean turn) {
        Turn = turn;
    }

    public void setPointsGame(int pointsGame) {
        PointsGame = pointsGame;
    }
}
