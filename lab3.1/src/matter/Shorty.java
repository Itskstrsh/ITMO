package matter;

import places.Location;

import java.util.ArrayList;

public class Shorty extends Entity{
    public Shorty(String name, ArrayList<String> items) {
        super(name, items);
    }

    public String getSkinDescription() {
        class Skin {
            private String condition;
            private String name;

            public Skin(String condition, String name) {
                this.condition = condition;
                this.name=name;
            }

            @Override
            public String toString() {
                return (this.name+" очень " + condition + " от долгих странствий, что сначала их никто не узнал");
            }
        }

        Skin skin = new Skin("загорели", Shorty.this.getName());
        return skin.toString();
    }
    public void toGoWithGroup(){
        System.out.print(this.name+" выступили в поход");
    }
    public void toCry(){
        System.out.print(this.name + " плакали");
    }
    public void goBehind(){
        System.out.print(this.name+" шёл позади");
    }
    public void toSew(Thing thing){
        System.out.print(this.name+" сшили " +thing.getName());
    }

    public void toHave(Thing thing){
        System.out.print(this.name+" имел "+thing.getName());
    }

    public void toSayGoodbye(Shorty a){
        System.out.print(this.name+" вышли провожать " +a.name);
    }
    public void toStop(Location location){
        System.out.print(this.name+" остановились на "+ location.getName());
    }
    public void toHug(Shorty a){
        System.out.print(this.name+ " обнимали "+ a.name);
    }
    public void toGo(Location loc){
        System.out.print(this.name + " шагали по "+ loc.getName());
    }

}