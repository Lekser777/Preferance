public class RostovRules implements Rules {
    private int kwinnerhill;
    private double kvisterhill;
    private int kvist;

    public RostovRules(){
        this.kvist=1;
        this.kvisterhill=0.5;
        this.kwinnerhill=1;
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
