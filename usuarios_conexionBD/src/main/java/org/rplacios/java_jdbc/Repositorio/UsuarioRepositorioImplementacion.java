package org.rplacios.java_jdbc.Repositorio;

import org.rplacios.java_jdbc.modelo.Usuarios;
import org.rplacios.java_jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorioImplementacion implements Repositorio<Usuarios> {
    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getInstance();
    }
    @Override
    public List listar() {
        List<Usuarios> u = new ArrayList<>();
        try(Statement s = getConnection().createStatement()){
            ResultSet r = s.executeQuery("SELECT * FROM usuarios");
            while (r.next()){
                Usuarios user = crearUsuario(r);
                u.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return u;
    }

    private static Usuarios crearUsuario(ResultSet r) throws SQLException {
        Usuarios u = new Usuarios();
        u.setId(r.getLong("id"));
        u.setUsername(r.getString("username"));
        u.setPassword(r.getString("password"));
        u.setEmail(r.getString("email"));
        return u;

    }

    @Override
    public Usuarios searchId(long id) {
        Usuarios u = null;
        try(PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM usuarios WHERE id = ?")){
            ps.setLong(1,id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    u = crearUsuario(rs);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return u;
    }

    @Override
    public void save(Usuarios u) {
        String sql = null;
        if (u.getId() != null && u.getId()>0){
            sql = "UPDATE usuarios SET username = ?, password = ?, email = ? WHERE id =?";
        }else {
            sql = "INSERT INTO usuarios(username,password,email) VALUES(?,?,?)";
        }
        try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, u.getUsername());
            ps.setString(2,u.getPassword());
            ps.setString(3, u.getEmail());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps = getConnection().prepareStatement("DELETE FROM usuarios WHERE id = ?")){
            ps.setLong(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
