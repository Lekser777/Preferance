import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private String name;
    private List<Card> cards;
    private String tradestate;
    private String orderstate;
    private int bribes;
    private double hill;
    private int leftvist;
    private int rightvist;
    private int pull;
    private  double points;
    public int turnswins;
    public int passeswins;
    public int mizerswins;

    public Player(String name){
        this.name=name;
        cards=new ArrayList<>();
        tradestate="";
        this.turnswins=0;
        this.mizerswins=0;
        this.passeswins=0;
    }
    public Player(Player other){
        this.name=other.name;
        this.cards=other.cards;
        this.tradestate=other.tradestate;
        this.orderstate=other.orderstate;
        this.hill=other.hill;
        this.leftvist=other.leftvist;
        this.rightvist=other.rightvist;
        this.pull=other.pull;
        this.turnswins=other.turnswins;
        this.mizerswins=other.mizerswins;
        this.passeswins=other.passeswins;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public String getTradestate() {
        return tradestate;
    }

    public void setTradestate(String tradestate) {
        this.tradestate = tradestate;
    }

    public int getBribes() {
        return bribes;
    }

    public void setBribes(int bribes) {
        this.bribes = bribes;
    }

    public String getOrderstate() {
        return orderstate;
    }

    public void setOrderstate(String orderstate) {
        this.orderstate = orderstate;
    }

    public double getHill() {
        return hill;
    }

    public void setHill(double hill) {
        this.hill = hill;
    }

    public int getLeftvist() {
        return leftvist;
    }

    public void setLeftvist(int leftvist) {
        this.leftvist = leftvist;
    }

    public int getRightvist() {
        return rightvist;
    }

    public void setRightvist(int rightvist) {
        this.rightvist = rightvist;
    }

    public int getPull() {
        return pull;
    }

    public void setPull(int pull) {
        this.pull = pull;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    //Решение о размере ставки
    public String tradeAnalyse(){
        String number;
        String color;
        //коф
        int Pknumber=0;
        int Tknumber=0;
        int Bknumber=0;
        int CHknumber=0;

        int[]colorspower=new int[4];

        String result="";

        for(int i=0;i<10;i++){
            number=cards.get(i).getNumber();
            color=cards.get(i).getColor();
            if(color.equals("П")){
                Pknumber=Pknumber+Cards.numberPower(number);
            }
            if(color.equals("Т")){
               Tknumber=Tknumber+Cards.numberPower(number);
            }
            if(color.equals("Б")){
                Bknumber=Bknumber+Cards.numberPower(number);
            }
            if(color.equals("Ч")){
                CHknumber=CHknumber+Cards.numberPower(number);
            }
        }
        colorspower[0]=Pknumber;
        colorspower[1]=Tknumber;
        colorspower[2]=Bknumber;
        colorspower[3]=CHknumber;
        int max=colorspower[0];
        int maxi=0;
        for (int i=0;i<4;i++){
            if(colorspower[i]>max){
                max=colorspower[i];
                maxi=i;
            }
        }

        int calcnumber=calcNumberOfBet(colorspower);

        if (calcnumber<3){
            result="mizer/БК";
            System.out.println("Игрок "+name+" решил: mizer");
        }else if (calcnumber<6){
            result="pass/БК";
            System.out.println("Игрок "+name+" решил: pass");
        }else {
            if(max<3) {
                result=calcnumber + "/"+"БК";
            }else {
                if (maxi == 0) {
                    result = calcnumber + "/" + "П";
                }
                if (maxi == 1) {
                    result = calcnumber + "/" + "Т";
                }
                if (maxi == 2) {
                    result = calcnumber + "/" + "Б";
                }
                if (maxi == 3) {
                    result = calcnumber + "/" + "Ч";
                }
            }
        }
        System.out.println("Игрок "+name+" решил: "+result);
     return result;
    }
    //Решение повысить или пас
    public String tradeDecision(String currentbet){
        String mybet= tradeAnalyse();
        if(currentbet.equals("none")){
            System.out.println("Игрок "+name+" повышает ставку "+currentbet+" до "+mybet);
            currentbet=mybet;setTradestate(mybet);
        } else if(Cards.isMore(currentbet,mybet)){
            System.out.println("Игрок "+name+" пассует ");
            setTradestate("pass");
        } else if(currentbet.equals(mybet)){
            System.out.println("Игрок "+name+" пассует ");
            setTradestate("pass");
        }
        else{
            System.out.println("Игрок "+name+" повышает ставку "+currentbet+" до "+mybet);
            currentbet=mybet;setTradestate(mybet);
        }
        return currentbet;
    }
    //
   public void orderDecision(String currentbet){
       String bet[]= tradeAnalyse().split("/");
       int maxorder;
       int order;
       if(currentbet.split("/")[0].equals("mizer")){
           order =4;
       }else order=Cards.numberToInt(currentbet.split("/")[0]);
       if(bet[0].equals("mizer")){
           maxorder = 4;
       }else maxorder = Cards.numberToInt(bet[0]) + 1;
       if (maxorder >= order) {
           System.out.println("Игрок " + name + " объявляет вист");
           setOrderstate("vist");
           //вист
       } else {System.out.println("Игрок " + name + " пассует"); setOrderstate("pass");}


   }
    private int calcNumberOfBet(int numbers[]){
        int number=0;
        for (int i=0;i<4;i++){
        if(numbers[i]==15){number=number+5;}
        if(numbers[i]==14){number=number+4;}
        if(numbers[i]==13){number=number+4;}
        if(numbers[i]==12){number=number+3;}
        if(numbers[i]==11){number=number+3;}
        if(numbers[i]==10){number=number+3;}
        if(numbers[i]==9){number=number+2;}
        if(numbers[i]==8){number=number+2;}
        if(numbers[i]==7){number=number+2;}
        if(numbers[i]==6){number=number+1;}
        if(numbers[i]==5){number=number+1;}

        }
        if(number==5||number==4||number==3||number==2){number=6;}//
        if(number>10){number=10;}
        return number;
    }
    public Card putCard(){
        sortCards();
        Card cardtoremove=getCards().get(0);
        getCards().remove(0);
        return cardtoremove;
    }
    public Card putCard(Card firstcard,String highcolor){
        sortCards();
        Card cardtoremove=null;
        int cardid=-1;
        boolean condition;
        for (int i=0;i<getCards().size();i++) {
            if (!highcolor.equals("none")) {
                condition=Cards.cardIsBetter(cards.get(i), firstcard, highcolor);
            }else condition=Cards.cardIsBetter(cards.get(i),firstcard,firstcard);
                if (condition) {
                    cardid = i;
                    cardtoremove = getCards().get(cardid);
                    cards.remove(cardid);
                break;
                }
        }
        if (cardid<0){
            cardtoremove=getCards().get(0);
            cards.remove(0);
        }//least

        return cardtoremove;
    }
    public void sortCards(){
        List<String>Pcards=new ArrayList<>();
        List<String>Tcards=new ArrayList<>();
        List<String>Bcards=new ArrayList<>();
        List<String>CHcards=new ArrayList<>();

        for(int i=0;i<cards.size();i++){
            if(cards.get(i).getColor().equals("П")){
                Pcards.add(cards.get(i).toString());
            }else if(cards.get(i).getColor().equals("Т")){
                Tcards.add(cards.get(i).toString());
            }else if(cards.get(i).getColor().equals("Б")){
                Bcards.add(cards.get(i).toString());
            }else {CHcards.add(cards.get(i).toString());}
        }
        Collections.sort(Pcards);
        Collections.sort(Tcards);
        Collections.sort(Bcards);
        Collections.sort(CHcards);
        for(int ind=0;ind<cards.size();ind=0) {
            cards.remove(ind);
        }
        for(int i=0;i<Pcards.size();i++) {
            cards.add(new Card(Pcards.get(i).toString()));
        }
        for(int i=0;i<Tcards.size();i++) {
            cards.add(new Card(Tcards.get(i).toString()));
        }
        for(int i=0;i<Bcards.size();i++) {
            cards.add(new Card(Bcards.get(i).toString()));
        }
        for(int i=0;i<CHcards.size();i++) {
            cards.add(new Card(CHcards.get(i).toString()));
        }

    }

}
