package matter;

import exceptions.UnexpectedTimeException;

public class Time {
    private String curTime;
    public Time(String curTime){
        this.curTime=curTime;
    }

    public String getCurTime() {
        return curTime;
    }

    public void setCurTime(String curTime) {
        this.curTime = curTime;
    }
    private class Season{
        private String seasonName;
        public Season(String seasonName) {
            this.seasonName = seasonName;
        }

        public void summerGoesToEnd() {
            System.out.print("Лето подходит к концу, поэтому  ");
        }

        public String getSeasonName() {
            return seasonName;
        }
    }
    public void time(String season, Flower flower) throws UnexpectedTimeException {
        Season currentSeason = new Season(season);

        if (!currentSeason.getSeasonName().equals("лето")) {
            throw new UnexpectedTimeException("Не лето, цветок не может расти");
        }

        currentSeason.summerGoesToEnd();
        flower.bloom();
    }
}