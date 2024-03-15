package enums;

public enum Time {
    TODAY("cегодня"),
    TOMMOROW("На другой день "),
    YESTERDAY("вчера");

    private final String incase;

    public String getIncase() {
        return incase;
    }
    Time(String incase) {
        this.incase = incase;
    }
}