package matter;

import java.util.ArrayList;

public class Normal extends Entity {
    public Normal(String name, ArrayList<String> items) {
        super(name, items);
    }
    public void toSpeak(String phrase){
        System.out.println(super.name+ phrase);
    }
    public void toSee(Shorty a){
        System.out.println(this.name+ " смотрели на " + a.name);
    }

}