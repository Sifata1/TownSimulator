import javax.swing.*;
import java.awt.*;

public class MainScreen {
    JFrame frame;//Creating object of JFrame

    MainScreen()//Creating constructor of the class
    {
        createGUI();

    }
    public void createGUI(){
        frame=new JFrame();
        frame.getContentPane().setLayout(null);//setting layout to null
        frame.setUndecorated(true);//Turning off Title bar
        frame.setSize(600,400);//Setting size
        frame.setLocationRelativeTo(null);//Setting location to the center of screen
        frame.getContentPane().setBackground(Color.white);//setting background color
        frame.setVisible(true);//setting visibility

    }
}
