import java.util.ArrayList;
import java.util.List;

public class GameInfo {
    private List<Turn> turns;
    private Game game;
    public GameInfo(Game game){
        this.turns=new ArrayList<>();
        this.game=game;
    }
    public void addTurn(Turn turn){
        turns.add(turn);
    }

    public List<Turn> getTurns() {
        return turns;
    }

    //API1
    public void getSpreading(int i){
        Turn turn=turns.get(i);
        turn.showhands();
        System.out.println("Первым ходит игрок "+turn.getPlayers()[0].getName());
    }
    //API2
    public void getTradingInfo(int i){
        Turn turn=turns.get(i);
        for (int n=0;n<turn.getPlayers().length;n++){
            System.out.println(" Игрок "+turn.getPlayers()[n].getName()+" объявил "+turn.getPlayers()[n].getTradestate());
        }
    }
    //API3
    public void getOrderingInfo(int i){
        Turn turn=turns.get(i);
        for (int n=0;n<turn.getPlayers().length;n++){
            System.out.println(" Игрок "+turn.getPlayers()[n].getName()+" объявил "+turn.getPlayers()[n].getOrderstate());
        }
    }
    //API4
    public void getPlayingInfo(int i){
        Turn turn=turns.get(i);
        /*
        turn.fillHand(turn.getCards1(),turn.getPlayers()[0]);
        turn.fillHand(turn.getCards2(),turn.getPlayers()[1]);
        turn.fillHand(turn.getCards3(),turn.getPlayers()[2]);
        */
        turn.trade();
        turn.order();
        turn.play();
    }
    //API5
    public void getPlayingResultsInfo(int i){
        Turn turn=turns.get(i);
        for(int n=0;n<turn.getPlayers().length;n++){
            System.out.println("Игрок "+turn.getPlayers()[n].getName()+" взял "+turn.getPlayers()[n].getBribes()+" взяток");
            System.out.println("Гора "+turn.getPlayers()[n].getHill());
            System.out.println("Правый вист "+turn.getPlayers()[n].getRightvist());
            System.out.println("Левый вист "+turn.getPlayers()[n].getLeftvist());
        }
    }
    //API6
    public void getTurnInfo(int i){
        Turn turn=turns.get(i);
        turn.setCurrentbet("none");
        turn.setCurrentbetmadeby(null);
        turn.trade();
        turn.order();
        turn.play();
        getPlayingResultsInfo(i);
    }
    //API7
    public void getPlayerPullInfo(int i,int playeri){
        Turn turn=turns.get(i);
            System.out.println("_____________________"+turn.getPlayers()[playeri].getName()+"_____________________");
            System.out.println("Гора "+turn.getPlayers()[playeri].getHill());
            System.out.println("Правый вист "+turn.getPlayers()[playeri].getRightvist());
            System.out.println("Левый вист "+turn.getPlayers()[playeri].getLeftvist());

    }
    //API8
    public void getPlayerResultInfo(int i,int playeri){
        Turn turn=turns.get(i);
        this.game.calcTurn(turn);
        this.game.calcPull(turn);
        System.out.println("Игрок "+turn.getPlayers()[playeri].getName()+" имеет "+turn.getPlayers()[playeri].getPoints()+" очков.");
    }
    //API9
    public void getStat(int i,int playeri){
        Turn turn=turns.get(i);
        System.out.println(" Игрок "+turn.getPlayers()[playeri].getName()+" выиграл "+turn.getPlayers()[playeri].turnswins+" ходов");
        System.out.println(" Игрок "+turn.getPlayers()[playeri].getName()+" выиграл "+turn.getPlayers()[playeri].mizerswins+" мизеров");
        System.out.println(" Игрок "+turn.getPlayers()[playeri].getName()+" выиграл "+turn.getPlayers()[playeri].passeswins+" распасов");
    }
    //API10
    public void getWinner(){
        Turn turn=turns.get(turns.size()-1);
        this.game.calcTurn(turn);
        this.game.calcPull(turn);
        double maxpoints=turn.getPlayers()[0].getPoints();
        int winneri=0;
        for(int n=0;n<turn.getPlayers().length;n++){
            if(turn.getPlayers()[n].getPoints()>maxpoints){
                maxpoints=turn.getPlayers()[n].getPoints();
                winneri=n;
            }
        }
        System.out.println("Игрок "+turn.getPlayers()[winneri].getName()+" выиграл ");
    }


}
