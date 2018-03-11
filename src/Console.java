import java.util.Scanner;

public class Console {
    public static void main(String args[]){
        /*
        Player player1=new Player("Vania");
        Player player2=new Player("Petya");
        Player player3=new Player("Masha");
        Player[] players={player1,player2,player3};
        Turn firstturn=new Turn(players);
        firstturn.showhands();
        firstturn.trade();
        firstturn.order();
        firstturn.play();
        System.out.println("---------------------------------------------------------------------------------------");
        for(int i=0;i<players.length;i++){
            System.out.println("Игрок "+players[i].getName()+" набрал "+players[i].getBribes()+" взяток");
        }
        */
   /*
        Game newgame=new Game(1,"Вася","Мася","Алина");
        Turn firstturn=new Turn(newgame.players);
        firstturn.showhands();
        firstturn.trade();
        firstturn.order();
        firstturn.play();
        newgame.calcTurn(firstturn);
        newgame.calcPull(firstturn);
        for (int i=0;i<firstturn.getPlayers().length;i++){
            System.out.println("Очки игрока "+firstturn.getPlayers()[i].getName()+" : "+firstturn.getPlayers()[i].getPoints());
        }


      /*Game newgame=new Game(1,"Вася","Мася","Алина");
        Turn firstturn=new Turn(newgame.players);
        Turn[]turns=new Turn[2];
        for(int i=0;i<firstturn.getPlayers().length;i++){
            firstturn.getPlayers()[i].setPull(15);
        }
        turns[0]=new Turn(firstturn);
        firstturn=new Turn(newgame.players);
        turns[0].getPlayers()[0].setPull(0);
        System.out.println(" из игры = "+firstturn.getPlayers()[0].getPull());
        System.out.println(" из массива = "+turns[0].getPlayers()[0].getPull());
*/
        Scanner scanner=new Scanner(System.in);
        System.out.println("Выберите вариант правил: ");
        System.out.println("1 - Ленинград");
        System.out.println("2 - Сочи");
        System.out.println("3 - Ростов");
        int rules=scanner.nextInt();
        System.out.println("Введите имя для 1 игрока: ");
        String name1=scanner.next();
        System.out.println("Введите имя для 2 игрока: ");
        String name2=scanner.next();
        System.out.println("Введите имя для 3 игрока: ");
        String name3=scanner.next();
        Game newgame=new Game(rules,name1,name2,name3);
        newgame.fullgame(12);
        System.out.println("Выберите сценарий: ");
        System.out.println("1 - S1 ");
        System.out.println("2 - S2 ");
        System.out.println("0 - выход ");
        int scen=scanner.nextInt();
        /*
        вызвать метод API1 для 2-ой и 5-ой раздачи.
                - вызвать метод API2 для 4-ой раздачи.
                - вызвать метод API3 для 3-ей раздачи.
                - вызвать метод API4 для 7-ей раздачи.
                - вызвать метод API5 для 6-ой раздачи.
                - вызвать метод API7 для 8-ой раздачи и 1-го игрока.
                - вызвать метод API8 для 10-ой раздачи и 2-го игрока.
                - вызвать метод API9 для 10-ой раздачи и 3-го игрока.
                - вызвать метод API10 после окончания игры.
                */
        switch (scen){
            case 1:

                newgame.loging.getSpreading(2);
                newgame.loging.getSpreading(5);

                newgame.loging.getTradingInfo(4);

                newgame.loging.getOrderingInfo(3);

//                newgame.loging.getPlayingInfo(7);

                newgame.loging.getPlayingResultsInfo(6);

                newgame.loging.getPlayerPullInfo(8,0);

                newgame.loging.getPlayerResultInfo(10,1);

                newgame.loging.getStat(10,2);

                newgame.loging.getWinner();
                break;

            case 2:

                for(int i=0;i<newgame.loging.getTurns().size();i++){
                    newgame.loging.getTurnInfo(i);
                }
                break;

            case 0: break;




        }

        /*
        for (int i=0;i<newgame.players.length;i++){
            System.out.println("Очки игрока "+newgame.players[i].getName()+" : "+newgame.players[i].getPoints());
        }
        System.out.println("________________________________1");
        newgame.loging.getTurn(0).showhands();
        System.out.println("_________________________________");
        System.out.println("________________________________2");
        newgame.loging.getTurn(1).showhands();
        System.out.println("_________________________________");
        System.out.println("________________________________3");
        newgame.loging.getTurn(2).showhands();
        System.out.println("_________________________________");
        */

    }
}
