import javax.swing.*;
import java.awt.*;

public class LoadingScreen {
    JFrame frame;//Creating object of JFrame
    JLabel text=new JLabel("Town Simulator");
    JProgressBar progressBar=new JProgressBar();
    JLabel message=new JLabel();
    LoadingScreen()//Creating constructor of the class
    {
        createGUI();
        addText();
        addProgressBar();
        addMessage();
        runningPBar();
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

    public void addMessage()
    {
        message.setBounds(250,320,200,40);//Setting the size and location of the label
        message.setForeground(Color.black);//Setting foreground Color
        message.setFont(new Font("arial",Font.BOLD,15));//Setting font properties
        frame.add(message);//adding label to the frame
    }

    public void addText()
    {
        text.setFont(new Font("arial",Font.BOLD,30));//Setting font size of text
        text.setBounds(170,220,600,40);//Setting size and location
        text.setForeground(Color.BLUE);//Setting foreground color
        frame.add(text);//adding text to the frame
    }

    public void addProgressBar(){
        progressBar.setBounds(100,280,400,30);//Setting Location and size
        progressBar.setBorderPainted(true);//Setting border painted property
        progressBar.setStringPainted(true);//Setting String painted property
        progressBar.setBackground(Color.WHITE);//setting background color
        progressBar.setForeground(Color.BLACK);//setting foreground color
        progressBar.setValue(0);//setting progress bar current value
        frame.add(progressBar);//adding progress bar to frame
    }

    public void runningPBar(){
        int i=0;//Creating an integer variable and intializing it to 0

        while( i<=100)
        {
            try{
                Thread.sleep(50);//Pausing execution for 50 milliseconds
                progressBar.setValue(i);//Setting value of Progress Bar
                message.setText("LOADING "+Integer.toString(i)+"%");//Setting text of the message JLabel
                i++;
                if(i==100)
                    frame.dispose();
            }catch(Exception e){
                e.printStackTrace();
            }



        }
    }



}