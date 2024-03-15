package matter;

public abstract class Item {
    String name;

    public Item(String name){
        this.name=name;

    }
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}