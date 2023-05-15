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
}
