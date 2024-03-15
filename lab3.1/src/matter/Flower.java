package matter;

import places.Location;

public class Flower extends Plant {


    public Flower(String name) {
        super(name);
    }

    public void bloom(){
        System.out.print(this.name+" зацветает");
    }
    public void dazzle(Location loc){
        System.out.print(this.name+" пестрели в "+ loc.getName());
    }
    public void curl(Location loc){
        System.out.print(this.name+" вились по "+loc.getName());
    }
}
