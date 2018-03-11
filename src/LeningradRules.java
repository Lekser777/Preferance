public class LeningradRules implements Rules {
    private int kwinnerhill;
    private double kvisterhill;
    private int kvist;

    public LeningradRules(){
        this.kvist=2;
        this.kvisterhill=1;
        this.kwinnerhill=2;
    }

    @Override
    public int getKWinnerHill() {
        return this.kwinnerhill;
    }

    @Override
    public double getKVisterHill() {
        return this.kvisterhill;
    }

    @Override
    public int getKVist() {
        return this.kvist;
    }
}
