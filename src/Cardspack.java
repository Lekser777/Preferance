import java.util.Random;

public class Cardspack {
   Card cardspack[];
   boolean istakeen[];

   public Cardspack(){
       Card pack[]={new Card("7/П"),new Card("7/Т"),new Card("7/Б"),new Card("7/Ч"),new Card("8/П"),
               new Card("8/Т"),new Card("8/Б"),new Card("8/Ч"),new Card("9/П"),new Card("9/Т"),new Card("9/Б"),
               new Card("9/Ч"),new Card("10/П"),new Card("10/Т"),new Card("10/Б"),new Card("10/Ч"),
               new Card("В/П"),new Card("В/Т"),new Card("В/Б"),new Card("В/Ч"), new Card("Д/П"),
               new Card("Д/Т"),new Card("Д/Б"),new Card("Д/Ч"),new Card("К/П"),new Card("К/Т"),new Card("К/Б"),
               new Card("К/Ч"),new Card("Т/П"),new Card("Т/Т"),new Card("Т/Б"),new Card("Т/Ч")};
       this.cardspack=pack;
       this.istakeen=new boolean[32];
   }
   public Card[] randomhand(Card[]cards,int n){
       for (int i=0;i<n;i++){
           Random random=new Random();
           int ind=random.nextInt(32);
           while (istakeen[ind]){
               random=new Random();
               ind=random.nextInt(32);
           }
           cards[i]= cardspack[ind];
           istakeen[ind]=true;
       }
       return cards;
   }
}
