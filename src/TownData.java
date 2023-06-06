import java.io.*;

public class TownData implements Serializable {
    private static final long serialVersionUID = 1L;

    private int sizeInMiles;
    private int value;
    private int population;
    private int deathCount;

    public TownData(int sizeInMiles, int value, int population, int deathCount) {
        this.sizeInMiles = sizeInMiles;
        this.value = value;
        this.population = population;
        this.deathCount = deathCount;
    }

    public int getSizeInMiles() {
        return sizeInMiles;
    }

    public int getValue() {
        return value;
    }

    public int getPopulation() {
        return population;
    }

    public int getDeathCount() {
        return deathCount;
    }

    // Save town data to a file
    public void save(String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(this);
            System.out.println("Town data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving town data: " + e.getMessage());
        }
    }

    // Load town data from a file
    public static TownData load(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            TownData townData = (TownData) inputStream.readObject();
            System.out.println("Town data loaded successfully.");
            return townData;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading town data: " + e.getMessage());
            return null;
        }
    }
}
