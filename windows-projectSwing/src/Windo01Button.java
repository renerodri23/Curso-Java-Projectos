import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Windo01Button  extends JFrame implements ActionListener {

    public Windo01Button(){
        super("Ejemplos de Boton y eventos");

        JButton button = new JButton("Aceptar");
        button.setSize(50,50);
        button.addActionListener(event -> System.out.println("Boton pulsado 2"));
        button.addActionListener(this);
        button.addActionListener(new ButtonClickListener());
        getContentPane().add(button);
        setSize(200,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new Windo01Button();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Boton Pulsado :)");

    }

    private static class ButtonClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Boton Pulsado 3");
            Toolkit.getDefaultToolkit().beep();


        }
    }
}
