package org.rplacios.java_jdbc.repositorio;

import org.rplacios.java_jdbc.modelo.Categoria;
import org.rplacios.java_jdbc.modelo.Producto;
import org.rplacios.java_jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositorioImpl implements Repositorio<Categoria>{
    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getInstance();
    }
    @Override
    public List<Categoria> listar() {
        List<Categoria> c = new ArrayList<>();
        try (Statement s = getConnection().createStatement()){
            ResultSet r = s.executeQuery("SELECT * FROM categoria");
            while (r.next()){
                Categoria ct = crearCategoria(r);
                c.add(ct);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    @Override
    public Categoria porId(long id) {
        Categoria categoria = null;
        try(PreparedStatement st = getConnection().prepareStatement("SELECT * FROM categoria WHERE idCategoria = ?")){
            st.setLong(1,id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    categoria = crearCategoria(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categoria;
    }

    @Override
    public void save(Categoria categoria) {
        String sql = null;
        if (categoria.getId() != null && categoria.getId()>0) {
            sql = "UPDATE categoria SET nombreCategoria=? WHERE idCategoria=?";
        } else {
            sql = "INSERT INTO categoria(nombreCategoria) VALUES(?)";
        }
        try(PreparedStatement st = getConnection().prepareStatement(sql)){
            st.setString(1, categoria.getNombre());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void eliminar(Long id) {
        try(PreparedStatement st = getConnection().prepareStatement("DELETE FROM categoria WHERE idCategoria=?")){
            st.setLong(1,id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Categoria crearCategoria(ResultSet r) throws SQLException {
        Categoria c = new Categoria();
        c.setId(r.getLong("idCategoria"));
        c.setNombre(r.getString("nombreCategoria"));
        return c;
    }
}
