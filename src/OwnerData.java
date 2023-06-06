import java.io.*;

public class OwnerData implements Serializable {
    private static final long serialVersionUID = 1L;

    private int balance;
    private String name;
    private boolean employed;
    private int wage;
    private int numTimesWorkedInOneYear;
    private int age;
    private int year;
    private int deathAge;

    public OwnerData(int balance, String name, boolean employed, int wage, int numTimesWorkedInOneYear,
                     int age, int year, int deathAge) {
        this.balance = balance;
        this.name = name;
        this.employed = employed;
        this.wage = wage;
        this.numTimesWorkedInOneYear = numTimesWorkedInOneYear;
        this.age = age;
        this.year = year;
        this.deathAge = deathAge;
    }

    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public boolean isEmployed() {
        return employed;
    }

    public int getWage() {
        return wage;
    }

    public int getNumTimesWorkedInOneYear() {
        return numTimesWorkedInOneYear;
    }

    public int getAge() {
        return age;
    }

    public int getYear() {
        return year;
    }

    public int getDeathAge() {
        return deathAge;
    }

    // Save owner data to a file
    public void save(String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(this);
            System.out.println("Owner data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving owner data: " + e.getMessage());
        }
    }

    // Load owner data from a file
    public static OwnerData load(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            OwnerData ownerData = (OwnerData) inputStream.readObject();
            System.out.println("Owner data loaded successfully.");
            return ownerData;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading owner data: " + e.getMessage());
            return null;
        }
    }
}