package org.rplacios.java_jdbc.repositorio;

import org.rplacios.java_jdbc.modelo.Categoria;
import org.rplacios.java_jdbc.modelo.Producto;
import org.rplacios.java_jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.rplacios.java_jdbc.repositorio.CategoriaRepositorioImpl.crearCategoria;

public class ProductoRepositorioImpl implements Repositorio<Producto> {
    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getInstance();
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> pr = new ArrayList<>();
        String sql = "SELECT p.*, c.nombreCategoria AS nombreCategoria, c.idCategoria AS idCategoria " +
                "FROM productos p INNER JOIN categoria c ON (p.categoria_id = c.idCategoria)";
        try (Statement s = getConnection().createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) {
                Producto p = crearProducto(rs);
                pr.add(p);
            }
        }
        return pr;
    }

    @Override
    public Producto porId(long id) throws SQLException {
        Producto producto = null;
        String sql = "SELECT p.*, c.nombreCategoria AS nombreCategoria, c.idCategoria AS idCategoria " +
                "FROM productos p INNER JOIN categoria c ON (p.categoria_id = c.idCategoria) " +
                "WHERE p.id = ?";
        try (PreparedStatement st = getConnection().prepareStatement(sql)) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    producto = crearProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void save(Producto producto) throws SQLException {
        String sql;
        boolean esUpdate = producto.getId() != null && producto.getId() > 0;

        if (esUpdate) {
            sql = "UPDATE productos SET nombre=?, precio=?, categoria_id=? WHERE id=?";
        } else {
            sql = "INSERT INTO productos(nombre, precio, categoria_id, fecha_registro) VALUES(?,?,?,?)";
        }

        try (PreparedStatement st = getConnection().prepareStatement(sql)) {
            st.setString(1, producto.getNombre());
            st.setLong(2, producto.getPrecio());
            st.setLong(3, producto.getCategoria().getId());

            if (esUpdate) {
                st.setLong(4, producto.getId());
            } else {
                st.setDate(4, new Date(producto.getFechaRegistro().getTime()));
            }

            st.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        try (PreparedStatement st = getConnection().prepareStatement("DELETE FROM productos WHERE id=?")) {
            st.setLong(1, id);
            st.executeUpdate();
        }
    }

    private static Producto crearProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setFechaRegistro(rs.getDate("fecha_registro"));
        p.setCategoria(crearCategoria(rs));
        return p;
    }
}
