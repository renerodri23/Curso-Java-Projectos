import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Windows06Counter extends JFrame implements ActionListener {
    private int counter;
    private JButton b = new JButton("Count");
    private JButton bd = new JButton("Decrement");
    private JLabel label = new JLabel("Conter: ");
    public Windows06Counter() throws HeadlessException {
        super("Ejemplo Contador");

        Container p = getContentPane();
        p.add(b);
        p.add(bd);
        p.add(label);
        p.setLayout(new FlowLayout());
        b.addActionListener(this);
        bd.addActionListener(this);
        setVisible(true);
        setSize(400,100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Windows06Counter();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource()==b){
           counter++;
       } else if (e.getSource()==bd) {
           counter--;

       }
       label.setText("Counter: "+ counter);

    }

}
