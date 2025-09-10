package matter;

import enums.Emotion;
import enums.Luggage;

public class Thing extends Item{
    Luggage position;
    public Thing(String name){
        super(name);
    }

    public Luggage getPosition(){
        return position;
    }
    private void setPosition(Luggage position) {
        this.position= position;
        System.out.printf(this.name, position.getIncase());
    }

}