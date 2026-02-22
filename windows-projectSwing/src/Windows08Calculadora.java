import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Windows08Calculadora extends JFrame implements ActionListener {
    Container c;
    JTextField nA,nB,r;

    public Windows08Calculadora() throws HeadlessException {
        super("Calculadora");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        JMenu menu = new JMenu("Operaciones");
        JMenuItem itemAdd = new JMenuItem("Suma");
        JMenuItem itemSub = new JMenuItem("Restar");
        JMenuItem itemDiv = new JMenuItem("Division");
        JMenuItem itemM = new JMenuItem("Multiplicar");
        JMenuItem itemClose = new JMenuItem("Salir");

        menu.add(itemAdd);
        menu.add(itemSub);
        menu.add(itemDiv);
        menu.add(itemM);

        JMenu cMenu = new JMenu("Cerrar");

        cMenu.add(itemClose);

        bar.add(menu);
        bar.add(cMenu);

        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());

        p.add(nA = new JTextField(3));
        p.add(new JLabel("Numero 1"));

        p.add(nB = new JTextField(3));
        p.add(new JLabel("Numero 2"));

        p.add(r = new JTextField(5));
        p.add(new JLabel("Resultado"));

        r.setEditable(false);
        c.add(p,BorderLayout.CENTER);
        setVisible(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
