package enums;

public enum TypeOfTransport {
    LEG(" пешком"),
    PLANE("с помощью самолета"),
    CAR("с помощью машины"),
    BOAT("с помощью лодки"),
    ROCKET("с помощью рокеты"),
    BALLOON("с помощью воздушного шара");
    private final String incase;

    public String getIncase() {
        return incase;
    }
    TypeOfTransport(String incase) {
        this.incase = incase;
    }
}