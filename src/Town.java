import java.util.Random;
import java.io.Serializable;

public class Town implements Serializable {
    private int revenue;
    private int costs;
    private int profit;
    private int sizeInMiles;
    private String stats;
    private int value;
    private int population;
    private int deathCount;

    public Town(int cost) {
        value = 1000;
        sizeInMiles = 1;
        population = 1000;
        deathCount = 0;
    }

    public String getStats() {
        stats = "Value: " + value + "\nCost: " + costs + "\nSize: " + sizeInMiles + "\nPopulation: " + population + "\nDeath Count: " + deathCount;
        return stats;
    }

    public void increasePopulation() {
        int increase = (int) ((Math.random()*250) + 50);
        population += increase;
    }

    public void incrementDeathCount() {
        Random random = new Random();
        int deathIncrease = random.nextInt(10) + 1; // Randomly increase death count between 1 and 10
        deathCount += deathIncrease;
    }
    public int buyLand(int miles) {
        sizeInMiles+=miles;
        int cost = miles * 1000;
        value+=cost;
        return cost;
    }

    public int sellLand(int miles) {
        sizeInMiles-=miles;
        int cost = miles * 1000;
        value-=cost;
        return cost;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getCosts() {
        return costs;
    }

    public void setCosts(int costs) {
        this.costs = costs;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public int getSizeInMiles() {
        return sizeInMiles;
    }

    public void setSizeInMiles(int sizeInMiles) {
        this.sizeInMiles = sizeInMiles;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getDeathCount() {
        return deathCount;
    }

    public int getPopulation() {
        return population;
    }
}
