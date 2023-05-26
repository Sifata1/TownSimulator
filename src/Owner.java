public class Owner {
    private int balance;
    private String name;
    private boolean employed;
    private int wage;
    private int numTimesWorkedInOneYear;
    private Town town;
    private int age;
    private int year;
    private int deathAge;



    public Owner(String name) {
        this.name = name;
        balance = 1000;
        employed = true;
        wage = 15;
        town = new Town(1000);
        age = 25;
        year = 2000;
        deathAge = (int) ((Math.random()*70) + 30);
    }


    public String work() {
            if (numTimesWorkedInOneYear > 4) {
                return "Take a break from working!";
            } else {
                int n = (int) (Math.random() * 5) + 1;
                balance += n * wage;
                numTimesWorkedInOneYear++;
                return "You worked for " + n + " hours and earned $" + (n * wage) + ".";
            }
    }

    public int getBalance() {
        return balance;
    }

    public void progressYear() {
        age++;
        year++;
        numTimesWorkedInOneYear = 0;
    }

    public boolean die() {
        if (age == deathAge) return true;
        else return false;
    }


    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEmployed() {
        return employed;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
    }

    public int getWage() {
        return wage;
    }
    public int getAge() {
        return age;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }



    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public int gamble(int amount) {
        int random = (int) (Math.random()*2) + 1;
        if (random == 1) {
            balance += amount;
            return amount;
        } else {
            balance -= amount;
            return -1 * amount;
        }
    }

    public boolean canGamble(int amount) {
        int bal = balance;
        if (amount > balance) {
            return false; // return -1 to indicate that the user does not have enough balance to gamble
        }
        return true;
    }


    public String buyLand(int miles) {
        int cost = miles * 1000;
        if (balance < cost) {
            return "You do not have enough balance to buy " + miles + " miles of land.";
        }
        balance -= cost;
        town.buyLand(miles);
        return "You have bought " + miles + " miles of land.";
    }

    public String sellLand(int miles) {
        if (miles > town.getSizeInMiles()) {
            return "You do not have that amount of miles to sell.";
        } else {
            balance += miles * 1000;
            town.sellLand(miles);
            return "You have sold " + miles + " miles of land.";
        }
    }

    public String getStats() {
        return "Year: " + year + "\n\nOwner Stats: \nName: " + name + " \nAge: "+ age + "\nBalance: $" + balance + "\nEmployed: " + employed + "\nWage: $" + wage + "\n\nTown Stats:\n" + town.getStats();
    }



}
