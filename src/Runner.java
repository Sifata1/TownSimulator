public class Runner {
    public static void main(String[] args) {
        // create instances of all the GUI classes
        MainScreen mainScreen = new MainScreen();
        GameScreen townScreen = new GameScreen(new Owner("John"));
    }
}
