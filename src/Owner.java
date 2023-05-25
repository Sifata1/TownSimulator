public class Owner {
    private int balance;
    private String name;
    private boolean employed;
    private int wage;
    private int workCount;
    private Town town;
    private int age;
    private int year;



    public Owner(String name) {
        this.name = name;
        balance = 1000;
        employed = true;
        wage = 15;
        town = new Town(0);
        age = 25;
        year = 2000;
    }


    public String work() {
            if (workCount > 7) {
                return "Take a break from working";
            } else if (workCount == 6) {
                int n = (int) (Math.random() * 5) + 1;
                int oldWage = wage;
                wage = wage * 2;
                balance += n * wage;
                workCount++;
                return "You earned a raise! You now have an hourly wage of $" + wage + "You worked for " + n + " hours and earned $" + (n * wage) + ".";
            } else {
                int n = (int) (Math.random() * 5) + 1;
                balance += n * wage;
                workCount++;
                return "You worked for " + n + " hours and earned $" + (n * wage) + ".";
            }
    }

    public int getBalance() {
        return balance;
    }

    public void progressYear() {
        age++;
        year++;
    }

    public String die() {
        int random =(int) ((Math.random()*70) + 55);
        if (age == random) return "You unfortunately you died at the age of " + age + " .";
        else return "";
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

    public void setWage(int wage) {
        this.wage = wage;
    }

    public int getWorkCount() {
        return workCount;
    }

    public void setWorkCount(int workCount) {
        this.workCount = workCount;
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

    public String getStats() {
        return "Year: " + year + "\n\nOwner Stats: \nName: " + name + " \nAge: "+ age + "\nBalance: $" + balance + "\nEmployed: " + employed + "\nWage: $" + wage + "\n\nTown Stats:\n" + town.getStats();
    }



}
