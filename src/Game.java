public class Game {
    Rules rules;
    Player[]players;
    int kpull;
    GameInfo loging;
    Game(int rules,String player1name,String player2name,String player3name){
        switch (rules){
            case 1: this.rules=new LeningradRules(); break;
            case 2: this.rules=new SochiRules(); break;
            case 3: this.rules=new RostovRules(); break;
        }
        Player firstplayer=new Player(player1name);
        Player secondplayer=new Player(player2name);
        Player thirdplayer=new Player(player3name);
        players=new Player[3];
        players[0]=firstplayer;
        players[1]=secondplayer;
        players[2]=thirdplayer;
        this.loging=new GameInfo(this);
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
    public void fullgame(int countturns){
        Turn thisturn = new Turn(this.players);
        int turns=0;
        while (turns<countturns) {
            System.out.println("------------------------------------------------------------");
            System.out.println("---------------------РАЗДАЧА №"+(turns+1)+"----------------------------");
            Turn turntosave = new Turn(thisturn);
            thisturn.showhands();
            thisturn.trade();
            Player winner=new Player(thisturn.getTradewinner());
            turntosave.setCurrentbet(thisturn.getCurrentbet());
            turntosave.setTradewinner(winner);
            turntosave.setExistMizer(thisturn.existMizer);

            thisturn.order();
            thisturn.play();
            this.calcTurn(thisturn);

            for(int i=0;i<turntosave.getPlayers().length;i++){
                turntosave.getPlayers()[i].setBribes(thisturn.getPlayers()[i].getBribes());
                turntosave.getPlayers()[i].setOrderstate(thisturn.getPlayers()[i].getOrderstate());
                turntosave.getPlayers()[i].turnswins=thisturn.getPlayers()[i].turnswins;
                turntosave.getPlayers()[i].mizerswins=thisturn.getPlayers()[i].mizerswins;
                turntosave.getPlayers()[i].passeswins=thisturn.getPlayers()[i].passeswins;
            }

            loging.addTurn(turntosave);
            for(int i=0;i<thisturn.getPlayers().length;i++){
                thisturn.getPlayers()[i].setBribes(0);
            }
            thisturn=new Turn(this.players);
            turns++;
        }
        this.calcPull(thisturn);
    }

    public void calcTurn(Turn turn){
        Player[]turnplayers=turn.getPlayers();
        calcKPull(turn.getCurrentbet());
        int betneeded=calcBridesNeeded(turn.getCurrentbet());
        int betneededvist=calcBridesNeededVist(turn.getCurrentbet());
        int vistsum=0;
        int cons=0;

        for (int i=0;i<turnplayers.length;i++){
            if (turnplayers[i].getOrderstate().equals("vist")){
                vistsum=vistsum+turnplayers[i].getBribes();
            }
        }
        players=Cards.changeorder(players,turn.getTradewinner());
        for (int i=0;i<turnplayers.length;i++){
            if(players[i].equals(turn.getTradewinner())){
                if(betneeded==11) {
                    if (players[i].getBribes() > 0) {
                        players[i].setHill(players[i].getHill() + players[i].getBribes() * kpull * rules.getKWinnerHill());
                    } if(players[i].getBribes()==0) {
                        players[i].setPull(players[i].getPull()+kpull);
                        players[i].mizerswins++;
                    }
                }else if(betneeded>players[i].getBribes()){
                    players[i].setHill(players[i].getHill()+(betneeded-players[i].getBribes())*kpull*rules.getKWinnerHill());
                    cons=kpull*rules.getKVist()*(betneeded-players[i].getBribes());
                } else {
                    players[i].setPull(players[i].getPull()+kpull);
                    players[i].turnswins++;
                }
            }else if(players[i].getOrderstate().equals("vist")){
                if(!(betneeded==11)){
                    if(betneededvist>vistsum){
                        players[i].setHill(players[i].getHill()+((betneededvist-vistsum)*kpull*rules.getKVisterHill())/2);
                    }
                    System.out.println(" консоляция "+cons);
                    turn.whomToAdd(players[i].getBribes()*kpull*rules.getKVist(),players[i]);
                    turn.whomToAdd(cons,players[i]);


                }


            }

        }

        for(int i=0;i<players.length;i++){
            System.out.println(players[i].getName()+" "+players[i].getBribes());
        }
        System.out.println("----------------------------------ПОДСЧЕТ-----------------------------------");
        viewResults(turnplayers);
    }
    public void calcKPull(String bet){
        bet=bet.split("/")[0];
        switch (bet){
            case "6": this.kpull=2; break;
            case "7": this.kpull=4; break;
            case "8": this.kpull=6; break;
            case "mizer": this.kpull=10; break;
            case "9": this.kpull=8; break;
            case "10": this.kpull=10; break;
        }

    }
    public int calcBridesNeeded(String bet){
        bet=bet.split("/")[0];
        if(bet.equals("mizer")){
            return 11;
        }else { return Cards.numberToInt(bet);}
    }
    public int calcBridesNeededVist(String bet){
        bet=bet.split("/")[0];
        int result=0;
        switch (bet){
            case "6": result= 4; break;
            case "7": result= 2; break;
            case "8": result= 1; break;
            case "9": result= 1; break;
            case "10": result=1; break;
        }
        return result;
    }
    public void viewResults(Player[]players){
        for(int i=0;i<players.length;i++){
            System.out.println("Гора игрока "+players[i].getName()+" на данный момент "+players[i].getHill());
            System.out.println("Левый вист игрока "+players[i].getName()+" на данный момент "+players[i].getLeftvist());
            System.out.println("Правый вист игрока "+players[i].getName()+" на данный момент "+players[i].getRightvist());
            System.out.println("А пуля равна "+players[i].getPull());
            System.out.println("----------------------------------------------------------------------------------------");
        }
    }
    public void calcPull(Turn last){
        Player[]players=last.getPlayers();
        double minhill=players[0].getHill();
        for(int i=0;i<players.length;i++){
            if(players[i].getHill()<minhill){
                minhill=players[i].getHill();
            }
        }
        for(int i=0;i<players.length;i++){
            players[i].setHill( players[i].getHill()-minhill);
        }
        last.hillToVist();
        int[]rights=new int[3];
        int[]lefts=new int[3];
        for(int i=0;i<players.length;i++){
            rights[i]=players[i].getRightvist()-players[last.findRightPlayerId(i)].getLeftvist();
            lefts[i]=players[i].getLeftvist()-players[last.findLeftPlayerId(i)].getRightvist();
        }
        for(int i=0;i<players.length;i++) {
            players[i].setPoints(rights[i] + lefts[i]);
        }
    }


}
