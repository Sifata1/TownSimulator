import java.util.ArrayList;
import java.util.Arrays;

public class Owner {
    private int balance;
    private String name;
    private boolean employed;
    private int wage;
    private int workCount;
    private Town town;


    public Owner(String name) {
        this.name = name;
        balance = 1000;
        employed = false;
        wage = 0;
        town = new Town(0);
    }

    public String work() {
        if (employed == false) return "You don't have a job.";
        else {
            if (workCount > 7) {
                return "Take a break from working";
            } else {
                int n =(int) (Math.random()*13) + 1;
                if (n == 13) {
                    employed = false;
                    return "You got fired for stealing your boss' children.";
                }
                balance += n*wage;
                workCount++;
                return "You worked for " + n + " hours and earned $" + (n*wage) + ".";
            }
        }
    }

    public String job() {
        String[] jobs = new String[] {"Teacher", "Uber Driver", "Doctor", "Bathroom Cleaner", "Circus Clown"};
        if (employed == false) {
            int n =(int) (Math.random()*5) + 1;
            int w = (int) (Math.random()*100) + 1;
            wage = w;
            return "You are now employed as a " + jobs[n] + " with a salary of $" + w + ".";
        } else {
            return "You already have a job.";
        }
    }

    public int gamble() {
        int bal = balance;
        int random = (int) (Math.random()*2) + 1;
        if (random == 1) {
            balance*=1.5;
            return bal*=1.5;
        } else {
            balance*=0.5;
            return bal*=0.5;
        }
    }


    public String buyLand(int miles) {
        balance-=town.buyLand(miles);
        return "You have bought " + miles + " miles of land.";
    }


}
