package org.rplacios.java.swing.jdbc.repositories;

import org.rplacios.java.swing.jdbc.db.ConnectionJdbc;
import org.rplacios.java.swing.jdbc.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepositorio{
    @Override
    public List<Product> findAll() {

        List<Product> products = new ArrayList<>();
        try(Connection conn = ConnectionJdbc.getConnection();
            Statement st = conn.createStatement();
            ResultSet set = st.executeQuery("Select * from products")){
            while (set.next()){
                Product product = new Product(set.getLong("id"),
                        set.getString("name"),
                        set.getInt("price"),
                        set.getInt("quantity"));

                products.add(product);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product findById(Long id) {
        Product product = null;
        try(Connection conn = ConnectionJdbc.getConnection();
            PreparedStatement st = conn.prepareStatement("Select * from products where id=?")){
               st.setLong(1,id);
               try( ResultSet set = st.executeQuery()) {

                   while (set.next()) {
                       product = new Product(set.getLong("id"),
                               set.getString("name"),
                               set.getInt("price"),
                               set.getInt("quantity"));

                   }
               }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return product;
    }


    @Override
    public Product save(Product product) {
        String sql = "";
        if (product.getId() != null && product.getId()>0){
            sql = "update products set name=?,price=?,quantity=? where id=?";
        } else {
            sql ="insert into products(name,price,quantity) values(?,?,?)";
        }

        try(Connection conn = ConnectionJdbc.getConnection();
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            st.setString(1,product.getName());
            st.setInt(2,product.getPrice());
            st.setInt(3,product.getQuantity());
            if (product.getId() != null && product.getId()>0){
                st.setLong(4,product.getId());

            }
            int affectedRows = st.executeUpdate();

            if (affectedRows>0 &&(product.getId() == null || product.getId() ==0)) {
                try (ResultSet set = st.getGeneratedKeys()) {
                    if (set.next()) {
                        product.setId(set.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public void delete(Long id) {
        try(Connection conn = ConnectionJdbc.getConnection();
            PreparedStatement st = conn.prepareStatement("delete from products where id=?")){
            st.setLong(1,id);
            st.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
