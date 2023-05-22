public class Town {
    private int revenue;
    private int costs;
    private int profit;
    private int sizeInMiles;
    private String stats;
    private int value;

    public Town(int cost) {
        value = cost;
        sizeInMiles = 1;
    }

    public String getStats() {
        stats = "Value: " + value + "Cost: " + costs + "\nSize: " + sizeInMiles;
        return stats;
    }

    public int buyLand(int miles) {
        sizeInMiles+=miles;
        int cost = miles * 1000;
        value+=cost;
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
}
