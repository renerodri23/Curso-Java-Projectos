import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Windo02FlowLayout extends JFrame implements ActionListener {

    public Windo02FlowLayout(){
        super("Ejemplos de Boton y eventos");
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,20));


        for(int i=1;i<=10;i++){
            JButton button = new JButton("Aceptar".concat(String.valueOf(i)));
            button.setSize(50,50);
            button.addActionListener(event -> System.out.println("Boton pulsado 2"));
            button.addActionListener(this);
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        setContentPane(panel);
        setSize(600,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new Windo02FlowLayout();

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
