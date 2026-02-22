import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Windows07CurrencyConventor extends JFrame implements ActionListener {

    Container p;
    JButton euro,usd,reset;
    JTextField t;

    public static  final String CURRENCY_DOLAR = "Dolar";
    public static final String CURRENCY_EUROS = "Euros";

    public Windows07CurrencyConventor() throws HeadlessException {
        super("Conversor de Monedas");
        p = getContentPane();
        p.setLayout(new FlowLayout());

        euro = new JButton(CURRENCY_EUROS);
        euro.addActionListener(this);
        usd = new JButton(CURRENCY_DOLAR);
        usd.addActionListener(this);
        reset = new JButton("Reset");
        reset.addActionListener(this);
        t = new JTextField(10);
        p.add(euro);
        p.add(usd);
        p.add(reset);
        p.add(t);
        setSize(300,300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new Windows07CurrencyConventor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Float v = Float.parseFloat(t.getText());
        String textCurrency = e.getActionCommand();

        if (textCurrency.equals(CURRENCY_EUROS)){
            v = (v/1029.19f);
            p.setBackground(Color.CYAN);
        }else if (textCurrency.equals(CURRENCY_DOLAR)){
            v = (v/975.38f);
            p.setBackground(Color.BLUE);
        }else {
            v = 0.00f;
            p.setBackground(Color.white);
        }

        t.setText(String.valueOf(v));
    }
}
