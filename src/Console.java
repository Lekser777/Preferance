import java.util.Scanner;

public class Console {
    public static void main(String args[]){
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


        int scen=-1;
        while (scen!=0) {
            System.out.println("Выберите сценарий: ");
            System.out.println("1 - S1 ");
            System.out.println("2 - S2 ");
            System.out.println("0 - выход ");
            scen=scanner.nextInt();
            switch (scen) {
                case 1:

                    newgame.loging.getSpreading(2);
                    newgame.loging.getSpreading(5);

                    newgame.loging.getTradingInfo(4);

                    newgame.loging.getOrderingInfo(3);

                    newgame.loging.getPlayingResultsInfo(6);

                    newgame.loging.getPlayerPullInfo(8, 0);

                    newgame.loging.getPlayerResultInfo(10, 1);

                    newgame.loging.getStat(10, 2);

                    newgame.loging.getWinner();
                    break;

                case 2:

                    for (int i = 0; i < newgame.loging.getTurns().size(); i++) {
                        newgame.loging.getTurnInfo(i);
                    }
                    break;

                case 0:
                    break;


            }
        }

    }
}
