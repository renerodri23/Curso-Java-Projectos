import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HelloWorldWindows {

    public static void main(String[] args) {


    }

    public static void createAndShowWindow(){
        JFrame frame = new JFrame("Hola Mundo Swing");
        JLabel lbl = new JLabel("Hola Mundo!");

        frame.getContentPane().add(lbl);
        frame.setVisible(true);
        frame.pack();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });

    }
}
