public class Cards {
    static public boolean isMore(String bet1, String bet2){
        String number1=bet1.split("/")[0];
        String color1=bet1.split("/")[1];
        String number2=bet2.split("/")[0];
        String color2=bet2.split("/")[1];
        boolean result;
            if(numberToInt(number1)>numberToInt(number2)){
                result=true;
            }else if(numberToInt(number1)==numberToInt(number2)){
                if(colorPower(color1) == colorPower(color2)){
                    result=false;
                }else result = colorPower(color1) > colorPower(color2);
            } else result=false;
      return result;
    }
    static public boolean cardIsBetter(Card card1,Card card2,String highestcolor){
        String number1=card1.getNumber();
        String color1=card1.getColor();
        String number2=card2.getNumber();
        String color2=card2.getColor();
        boolean result;
        if(color1.equals(color2)){
            result = cardNumberPower(number1) > cardNumberPower(number2);
        }else if(color1.equals(highestcolor)){result=true;}else result=false;
        return result;
    }
    static public boolean cardIsBetter(Card card1,Card card2,Card firstcard){
        String number1=card1.getNumber();
        String color1=card1.getColor();
        String number2=card2.getNumber();
        String color2=card2.getColor();
        String color3=firstcard.getColor();
        boolean result;
        if(color1.equals(color2)){
            result = cardNumberPower(number1) > cardNumberPower(number2);
        }else if(color1.equals(color3)){result=true;}
        else result=false;
        return result;
    }
    static public int numberToInt(String number){
        if(number.equals("mizer")){ return 8;
        }else if (number.equals("pass")){return 0;
        }else return Integer.parseInt(number);
    }
    static public void bubblesort(Card[]handwithbuyin,int[]handpower,boolean isdowncast) {
        for (int i = handpower.length - 1; i > 0; i--) {
            for (int n = 0; n < i; n++) {
                if (!isdowncast) {
                    if (handpower[n] < handpower[n + 1]) {
                        int tmp1 = handpower[n];
                        handpower[n] = handpower[n + 1];
                        handpower[n + 1] = tmp1;
                        Card tmp2 = handwithbuyin[n];
                        handwithbuyin[n] = handwithbuyin[n + 1];
                        handwithbuyin[n + 1] = tmp2;
                    }
                } else {
                    if (handpower[n] > handpower[n + 1]) {
                        int tmp1 = handpower[n];
                        handpower[n] = handpower[n + 1];
                        handpower[n + 1] = tmp1;
                        Card tmp2 = handwithbuyin[n];
                        handwithbuyin[n] = handwithbuyin[n + 1];
                        handwithbuyin[n + 1] = tmp2;

                    }
                }
            }

        }
    }

    static public int nextPlayerId(int i){
        if(i<2){i++;}
        else{i=0;}
        return i;
    }
    static public int numberPower(String number){
        int result=0;
        if(number.equals("10")){result=1;}
        if(number.equals("В")){result=2;}
        if(number.equals("Д")){result=3;}
        if(number.equals("К")){result=4;}
        if(number.equals("Т")){result=5;}
        return result;
    }
    static public int cardNumberPower(String number){
        int result=0;
        if(number.equals("7")){result=7;}
        if(number.equals("8")){result=8;}
        if(number.equals("9")){result=9;}
        if(number.equals("10")){result=10;}
        if(number.equals("В")){result=11;}
        if(number.equals("Д")){result=12;}
        if(number.equals("К")){result=13;}
        if(number.equals("Т")){result=14;}
        return result;
    }
    static public int colorPower(String color){
        int result=0;
        if(color.equals("П")){result=1;}
        if(color.equals("Т")){result=2;}
        if(color.equals("Б")){result=3;}
        if(color.equals("Ч")){result=4;}
        if(color.equals("БК")){result=5;}
        return result;
    }
    static public Player[] changeorder(Player[]players,Player winner){
        int winnerid=0;
        for(int i=0;i<players.length;i++){
            if(winner.equals(players[i])){
                winnerid=i;
            }
        }
        Player[]players1=players;
        if(winnerid==2) {
            Player temp = players[1];
            Player temp2=players[0];
            players1[0] = players[2];
            players1[1] = temp2;
            players1[2] = temp;
        }else if (winnerid==1){
            Player temp = players[0];
            players1[0] = players[1];
            players1[1] = players[2];
            players1[2] = temp;
        }
        return players1;
    }

}
