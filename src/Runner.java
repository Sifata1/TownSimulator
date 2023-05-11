public class Runner {
    public static void main(String[] args) {
        // create instances of all the GUI classes
        MainScreen mainScreen = new MainScreen();
        OwnerGUI ownerGUI = new OwnerGUI(new Owner("John"));
        TownScreen townScreen = new TownScreen(new Owner("John"));
    }
}
