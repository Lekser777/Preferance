import java.util.ArrayList;
import java.util.List;

public class Turn {
    private Card cards1[];
    private Card cards2[];
    private Card cards3[];
    private Card cards4[];
    private String currentbet;
    private Player currentbetmadeby;
    private Player tradewinner;
    boolean existMizer;
    boolean existPassing;
    private Player[]players;



    public Turn(Player[]players){
        cards1=new Card[10];
        cards2=new Card[10];
        cards3=new Card[10];
        cards4=new Card[2];
        Cardspack fullpack=new Cardspack();
        currentbet="none";
        currentbetmadeby=null;
        tradewinner=null;
        existMizer=false;
        existPassing=false;
        this.players=players;
        fullpack.randomhand(cards1,10);
        fullpack.randomhand(cards2,10);
        fullpack.randomhand(cards3,10);
        fullpack.randomhand(cards4,2);
    }
    public Turn(Turn other){
        this.cards1=other.cards1;
        this.cards2=other.cards2;
        this.cards3=other.cards3;
        this.cards4=other.cards4;
        this.players=new Player[3];
        this.players[0]=new Player(other.players[0]);
        this.players[1]=new Player(other.players[1]);
        this.players[2]=new Player(other.players[2]);
    }


    public String getCurrentbet() {
        return currentbet;
    }

    public void setCurrentbet(String currentbet) {
        this.currentbet = currentbet;
    }

    public Player getCurrentbetmadeby() {
        return currentbetmadeby;
    }

    public void setCurrentbetmadeby(Player currentbetmadeby) {
        this.currentbetmadeby = currentbetmadeby;
    }

    public Card[] getCards1() {
        return cards1;
    }

    public Card[] getCards2() {
        return cards2;
    }

    public Card[] getCards3() {
        return cards3;
    }

    public Player getTradewinner() {
        return tradewinner;
    }

    public void setTradewinner(Player tradewinner) {
        this.tradewinner = tradewinner;
    }

    public void setExistMizer(boolean existMizer) {
        this.existMizer = existMizer;
    }

    public boolean isExistMizer() {
        return existMizer;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void showhands(){
        System.out.println("Карты игрока "+players[0].getName()+" : ");
        for (int i=0;i<10;i++){
            System.out.print(cards1[i]+" ");
        }
        System.out.println();
        System.out.println("Карты игрока "+players[1].getName()+" : ");
        for (int i=0;i<10;i++){
            System.out.print(cards2[i]+" ");
        }
        System.out.println();
        System.out.println("Карты игрока "+players[2].getName()+" : ");
        for (int i=0;i<10;i++){
            System.out.print(cards3[i]+" ");
        }
        System.out.println();
        System.out.println("Прикуп : ");
        for (int i=0;i<2;i++){
            System.out.println(cards4[i]+" ");
        }

    }

    public void trade(){
        System.out.println("-----------------------------------НАЧАЛО ФАЗЫ ТОРГОВ-----------------------------------");
        fillHand(getCards1(),players[0]);
        fillHand(getCards2(),players[1]);
        fillHand(getCards3(),players[2]);

        int j=0;
        while(getCurrentbetmadeby()!=players[j]){
                setCurrentbet(players[j].tradeDecision(currentbet));
                if(!players[j].getTradestate().equals("pass")) {
                    setCurrentbetmadeby(players[j]);
                }
                j=Cards.nextPlayerId(j);
        }
        for (Player player : players) {
            if (!player.getTradestate().equals("pass")) {
                tradewinner = player;
            }
        }
        setCurrentbet(tradewinner.tradeAnalyse());

        System.out.println("В торгах выиграл игрок: "+tradewinner.getName()+" со ставкой: "+ currentbet);
        System.out.println("-----------------------------------КОНЕЦ ФАЗЫ ТОРГОВ-----------------------------------");
    }
    public void order(){
        System.out.println("----------------------------------НАЧАЛО ФАЗЫ ЗАКАЗОВ----------------------------------");
        if(tradewinner.getTradestate().split("/")[0].equals("mizer")){this.existMizer=true;}
        if(tradewinner.getTradestate().split("/")[0].equals("pass")){this.existPassing=true;}

        if(existPassing){

            Card[] handwithbuyin=new Card[10];
            int[]handpower=new int[10];
            for (int i=0;i<handwithbuyin.length;i++){
                handwithbuyin[i]=tradewinner.getCards().get(i);
                }
            for(int i=0;i<handpower.length;i++){
                handpower[i]=Cards.cardNumberPower(handwithbuyin[i].getNumber());
            }
            Cards.bubblesort(handwithbuyin,handpower,true);
            List<Card> tencards=new ArrayList<>();
            for(int i=0;i<tradewinner.getCards().size();i++){
                tencards.add(handwithbuyin[i]);
            }
            tradewinner.setCards(tencards);
            System.out.println("БУДЕТ РАСПАССОВКА");
            for (int i = 0; i < players.length; i++) {
                players[i].setOrderstate("pass");
            }

        }else {
            chooseTen();
            setCurrentbet(tradewinner.tradeAnalyse());
            int j = 0;
            for (int i = 0; i < players.length; i++) {
                if (players[i].equals(tradewinner)) {
                    j = i;
                }
            }
            tradewinner.setOrderstate(getCurrentbet());
            int decisioncout = 0;
            j = Cards.nextPlayerId(j);

            while (decisioncout < 2) {
                players[j].orderDecision(getCurrentbet());
                decisioncout++;
                j = Cards.nextPlayerId(j);
            }
        }
        System.out.println("----------------------------------КОНЕЦ ФАЗЫ ЗАКАЗОВ-----------------------------------");
    }
    public void play(){
        int passcount=0;
        for(int ind=0;ind<players.length;ind++){
            if(players[ind].getOrderstate().equals("pass")){
                passcount++;
            }
        }
        wayToPlay(passcount);
    }
    private void wayToPlay(int passcount){
        switch (passcount){
            case 3: allPassed(); break;
            case 2: twoPassed(); break;
            case 1: oneOrNonePassed(); break;
            case 0: oneOrNonePassed(); break;
        }

    }
    private void allPassed(){
        String highcolor = "none";
        playing(highcolor);

    }

    private void twoPassed(){
        if (getCurrentbet().split("/")[0].equals("mizer")) {
            tradewinner.setBribes(10);
        } else tradewinner.setBribes(Cards.numberToInt(getCurrentbet().split("/")[0]));
    }
    private void oneOrNonePassed(){
        String highcolor;
        if (existMizer) {
            highcolor = "none";
        } else {
            highcolor = getCurrentbet().split("/")[1];
            if(highcolor.equals("БК")){highcolor="none";}
        }

        playing(highcolor);

        int temppoints=0;
        for (int i=0;i<players.length;i++){
            if(players[i].getOrderstate().equals("pass")){
                temppoints=players[i].getBribes();
                players[i].setBribes(0); }
        }
        for (int ii=0;ii<players.length;ii++) {
            if (players[ii].getOrderstate().equals("vist")) {
                players[ii].setBribes(players[ii].getBribes() + temppoints);
            }
        }

    }
    private void playing(String highcolor){
        int i = 1;
        Card firstcard;
        Card secondcard;
        Card thirdcard;
        Player [] playorder=new Player[3];
        for(int ind=0;ind<players.length;ind++){
            playorder[ind]=players[ind];
        }
        playorder = Cards.changeorder(playorder, getTradewinner());

        while (i < 11) {
            System.out.println("Ход номер: " + i);
            firstcard = playorder[0].putCard();
            secondcard = playorder[1].putCard(firstcard, highcolor);
            thirdcard = playorder[2].putCard(firstcard, highcolor);
            System.out.println("Игрок " + playorder[0].getName() + " кладет карту:" + firstcard.toString());
            System.out.println("Игрок " + playorder[1].getName() + " кладет карту:" + secondcard.toString());
            System.out.println("Игрок " + playorder[2].getName() + " кладет карту:" + thirdcard.toString());
            Player winner = whoWins(firstcard, secondcard, thirdcard,playorder);
            winner.setBribes(winner.getBribes() + 1);
            System.out.println("Выиграл игрок :" + winner.getName());
            playorder = Cards.changeorder(playorder, winner);
            i++;
        }

    }

    private void chooseTen(){

        Card[] handwithbuyin=new Card[12];
        int[]handpower=new int[12];
        int j=0;

        for (int i=0;i<handwithbuyin.length;i++){
            if(i<10){handwithbuyin[i]=tradewinner.getCards().get(i);}
            else{handwithbuyin[i]=cards4[j];j++;}
        }
        for(int i=0;i<handpower.length;i++){
            handpower[i]=Cards.cardNumberPower(handwithbuyin[i].getNumber());
        }

        if(this.existMizer){Cards.bubblesort(handwithbuyin,handpower,true);}
        else {Cards.bubblesort(handwithbuyin,handpower,false);}

        List<Card> tencards=new ArrayList<>();
        System.out.println("Карты у игрока "+tradewinner.getName()+" после выборки: ");
        for(int i=0;i<tradewinner.getCards().size();i++){
            tencards.add(handwithbuyin[i]);
            System.out.print(tencards.get(i)+" ");
        }
        System.out.println();
        System.out.println("Выкинул карты: "+handwithbuyin[10]+" и "+handwithbuyin[11]);
        tradewinner.setCards(tencards);

    }
    public void fillHand(Card[] cards,Player player){
        List<Card>temp=new ArrayList<>();
        for(int i=0;i<cards.length;i++){
            temp.add(cards[i]);
        }
        player.setCards(temp);
    }
    public Player whoWins(Card card1,Card card2,Card card3,Player[]currentplayers){
        String highcolor=tradewinner.getOrderstate().split("/")[1];
        if(existMizer||existPassing){highcolor="none";}
        if(!highcolor.equals("none")) {
            if (Cards.cardIsBetter(card1, card2, highcolor) && Cards.cardIsBetter(card1, card3, highcolor)) {
                return currentplayers[0];
            } else if (Cards.cardIsBetter(card2, card1, highcolor) && Cards.cardIsBetter(card2, card3, highcolor)) {
                return currentplayers[1];
            } else {
                return currentplayers[2];
            }
        }else {

            if (Cards.cardIsBetter(card1, card2,card1) && Cards.cardIsBetter(card1, card3,card1)) {
                return currentplayers[0];
            } else if (Cards.cardIsBetter(card2, card1,card1) && Cards.cardIsBetter(card2, card3,card1)) {
                return currentplayers[1];
            } else {
                return currentplayers[2];
            }

        }
    }
    public void whomToAdd(int vist,Player player){
        int winnerid=0;
        int currentid=0;
        for(int i=0;i<players.length;i++){
            if(players[i].equals(getTradewinner())){
                winnerid=i;
            }
            if(players[i].equals(player)){
                currentid=i;
            }
        }

        if(winnerid==0&&currentid==1){
            player.setRightvist(player.getRightvist()+vist);
        }
        if(winnerid==0&&currentid==2){
            player.setLeftvist(player.getLeftvist()+vist);
        }
        if(winnerid==1&&currentid==0){
            player.setLeftvist(player.getLeftvist()+vist);
        }
        if(winnerid==1&&currentid==2){
            player.setRightvist(player.getRightvist()+vist);
        }
        if(winnerid==2&&currentid==1){
            player.setLeftvist(player.getLeftvist()+vist);
        }
        if(winnerid==2&&currentid==0){
            player.setRightvist(player.getRightvist()+vist);
        }

    }
    public void hillToVist(){
        players[0].setRightvist(players[0].getRightvist()+(int)(players[2].getHill()/3*10));
        players[0].setLeftvist(players[0].getLeftvist()+(int)(players[1].getHill()/3*10));
        players[1].setRightvist(players[1].getRightvist()+(int)(players[0].getHill()/3*10));
        players[1].setLeftvist(players[1].getLeftvist()+(int)(players[2].getHill()/3*10));
        players[2].setRightvist(players[2].getRightvist()+(int)(players[1].getHill()/3*10));
        players[2].setLeftvist(players[2].getLeftvist()+(int)(players[0].getHill()/3*10));
    }
    public int findRightPlayerId(int current){
        int result=0;
        switch (current){
            case 0: result=2;break;
            case 1: result=0;break;
            case 2: result=1;break;
        }
        return result;
    }
    public int findLeftPlayerId(int current){
        int result=0;
        switch (current){
            case 0: result=1;break;
            case 1: result=2;break;
            case 2: result=0;break;
        }
        return result;
    }



}
