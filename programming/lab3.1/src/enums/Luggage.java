package enums;

public enum Luggage {
    BAG("В сумке лежали"),
    POCKET(" в карманах"),
    BACKPACK("в рюкзаке");
    private final String incase;

    public String getIncase() {
        return incase;
    }

    Luggage(String incase) {
        this.incase = incase;
    }
}