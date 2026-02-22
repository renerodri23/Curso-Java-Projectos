package org.rplacios.java.swing.jdbc;

import org.rplacios.java.swing.jdbc.models.Product;
import org.rplacios.java.swing.jdbc.repositories.ProductRepositorio;
import org.rplacios.java.swing.jdbc.repositories.ProductRepositoryImpl;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;


public class JdbcSwingCrudProject extends JFrame {

    private Container p;
    public JTextField nameField = new JTextField();
    public JTextField priceField= new JTextField();
    public JTextField quantityField= new JTextField();
    private ProductTableModel tableModel = new ProductTableModel();
    private ProductRepositorio repo;

    private long id;
    private int row;
    public JdbcSwingCrudProject() {
        super("Swing: GUI con Base de Datos MySql");
        p = getContentPane();
        p.setLayout(new BorderLayout(20,10));

        repo = new ProductRepositoryImpl();

        JPanel formPanel =new JPanel(new GridLayout(4,2,20,10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        JButton b = new JButton("Guardar");

        formPanel.add(new JLabel("Nombre :"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Precio :"));
        formPanel.add(priceField);

        formPanel.add(new JLabel("Cantidad :"));
        formPanel.add(quantityField);

        formPanel.add(new JLabel(""));
        formPanel.add(b);
        b.addActionListener(e -> {
            String name = nameField.getText();
            int price = 0;
            int quantity = 0;
            try{
                price = Integer.parseInt(priceField.getText());
            }catch (NumberFormatException numberFormatException){}
            try{
                quantity = Integer.parseInt(quantityField.getText());

            }catch (NumberFormatException numberFormatException){}

            List<String> errors = new ArrayList<>();
            if(name.isEmpty()){
                errors.add("Debe Ingresar el nombre");

            }
            if (price==0){
                errors.add("El precio es requerido y es numerico");

            }
            if (quantity==0){
                errors.add("La cantidad no debe ser cero y numerica");
            }
            if(errors.size()>0){
                JOptionPane.showMessageDialog(null,errors.toArray(),"Error de validacion",JOptionPane.ERROR_MESSAGE);

            } else {
                Product product1 = repo.save(new Product(id==0?null: id, name,price,quantity));
                if (id == 0) {
                    Object[] products = new Object[]{product1.getId(), name, price, quantity,"remove"};
                    tableModel.getRows().add(products);
                    tableModel.fireTableDataChanged();
                    System.out.println(products[0]);
                    System.out.println(products[1]);
                    System.out.println(products[2]);
                    System.out.println(products[3]);
                } else if (id > 0) {
                    tableModel.setValueAt(id, row, 0);
                    tableModel.setValueAt(name, row, 1);
                    tableModel.setValueAt(price, row, 2);
                    tableModel.setValueAt(quantity, row, 3);
                }
                repo.save(new Product(id==0?null: id,name,price,quantity));
                reset();
            }});
        JPanel tablePanel = new JPanel(new FlowLayout());


        JTable jtable = new JTable();
        jtable.setModel(this.tableModel);
        jtable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row =jtable.rowAtPoint(e.getPoint());
                int column = jtable.columnAtPoint(e.getPoint());

                if (row >-1 && column ==4){
                    int option = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar este registro "+tableModel.getValueAt(row,1).toString()+"?","Confirmacion",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);

                    if(option == JOptionPane.OK_OPTION){
                        repo.delete((long) tableModel.getValueAt(row,0));
                        tableModel.getRows().remove(row);
                        tableModel.fireTableDataChanged();

                        return;
                    }
                    reset();

                }
                if (row>-1 && column>-1){
                    id = (long) tableModel.getValueAt(row,0);
                    nameField.setText(tableModel.getValueAt(row,1).toString());
                    priceField.setText(tableModel.getValueAt(row,2).toString());
                    quantityField.setText(tableModel.getValueAt(row,3).toString());
                }
            }
        });

        JScrollPane s = new JScrollPane(jtable);
        tablePanel.add(s);

        p.add(tablePanel,BorderLayout.SOUTH);
        p.add(formPanel, BorderLayout.NORTH);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void reset() {
        id=0;
        row=-1;
        nameField.setText("");
        priceField.setText("");
        quantityField.setText("");
    }

    public static void main(String[] args) {
        new JdbcSwingCrudProject();

    }



    private class ProductTableModel extends AbstractTableModel {
        private String[] columns = new String[]{"Id","Nombre","Precio","Cantidad","Delete"};
        private List<Object[]> rows = new ArrayList<>();

        public ProductTableModel() {
            ProductRepositorio repo = new ProductRepositoryImpl();
            List<Product> products = repo.findAll();
            for (Product product: products){
                Object[] row = {product.getId(),product.getName(),product.getPrice(),product.getQuantity(), "remove"};
                rows.add(row);
            }
        }

        public List<Object[]> getRows() {
            return rows;
        }

        @Override
        public int getRowCount() {
            return rows.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return rows.get(rowIndex)[columnIndex];
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            rows.get(rowIndex)[columnIndex]= aValue;
            fireTableCellUpdated(rowIndex, columnIndex);
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }
    }
}
