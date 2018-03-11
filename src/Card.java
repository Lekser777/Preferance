public class Card {
    private String number;
    private String color;
    public Card(String card){
        this.number=card.split("/")[0];
        this.color=card.split("/")[1];
    }
    public String getNumber() {
        return number;
    }

    public String getColor() {
        return color;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number+"/"+color;
    }
}
