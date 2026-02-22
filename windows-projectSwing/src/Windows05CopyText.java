import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Windows05CopyText extends JFrame {

     JTextField fV,result;

    public Windows05CopyText() throws HeadlessException {
        super("Ejemplo Copy Text");
        JPanel p = new JPanel();
        setContentPane(p);
        p.setLayout(new FlowLayout());
        p.add(new JLabel("Valor: "));
        fV = new JTextField(10);
        p.add(fV);

        JButton b = new JButton("Copiar");
        b.addActionListener(new CopyTextActionListener());
        p.add(b);
        result = new JTextField(10);
        p.add(result);

        setSize(600,150);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        JFrame f = new Windows05CopyText();


    }

    private class CopyTextActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String v = fV.getText();
            result.setText(v);
        }
    }
}
