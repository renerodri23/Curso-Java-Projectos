package org.rplacios.java_jdbc;

import org.rplacios.java_jdbc.Repositorio.Repositorio;
import org.rplacios.java_jdbc.Repositorio.UsuarioRepositorioImplementacion;
import org.rplacios.java_jdbc.modelo.Usuarios;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class UsuarioService {

    public Repositorio<Usuarios> repo = new UsuarioRepositorioImplementacion();

    public void listUser(){
        List<Usuarios> usuarios = repo.listar();

        StringBuilder sb = new StringBuilder();
        sb.append("==== Listado de Usuarios ====\n");
        sb.append("|ID | Username | Email |");
        sb.append("\n");

        for (Usuarios u : usuarios) {
            sb.append(u.getId()).append(" | ")
                    .append(u.getUsername()).append(" | ")
                    .append(u.getEmail()).append(" | ");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Listado de Usuarios", JOptionPane.INFORMATION_MESSAGE);
    }

    public void searchId(){
        String input = JOptionPane.showInputDialog("Ingrese el ID del Usuario a buscar: ");
        if (input == null) return;

        try {
            int id = Integer.parseInt(input);
            Usuarios u = repo.searchId(id);

            if (u != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("======== Usuario Encontrado ========\n");
                sb.append("ID: ").append(u.getId()).append("\n");
                sb.append("Username: ").append(u.getUsername()).append("\n");
                sb.append("Email: ").append(u.getEmail()).append("\n");

                JOptionPane.showMessageDialog(null, sb.toString(), "Resultado Búsqueda", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró usuario con ID " + id);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addUser(){
        Usuarios u = new Usuarios();

        String username = JOptionPane.showInputDialog("Ingrese el Username:");
        if (username == null) return;
        u.setUsername(username);

        String password = JOptionPane.showInputDialog("Ingrese la Contraseña:");
        if (password == null) return;
        u.setPassword(password);

        String email = JOptionPane.showInputDialog("Ingrese el Email:");
        if (email == null) return;
        u.setEmail(email);

        repo.save(u);
        JOptionPane.showMessageDialog(null, "Usuario agregado correctamente.");
    }

    public void updateUser(){
        String input = JOptionPane.showInputDialog("Ingrese la Id del Usuario que quiere Actualizar: ");
        if (input == null) return;

        try {
            int id = Integer.parseInt(input);
            Usuarios u = repo.searchId(id);

            if (u != null) {
                String actual = "ID: " + u.getId() +
                        "\nUsername: " + u.getUsername() +
                        "\nEmail: " + u.getEmail();
                JOptionPane.showMessageDialog(null, actual, "Usuario Encontrado", JOptionPane.INFORMATION_MESSAGE);

                u.setUsername(JOptionPane.showInputDialog("Ingrese el nuevo username:", u.getUsername()));
                u.setPassword(JOptionPane.showInputDialog("Ingrese la nueva contraseña:", u.getPassword()));
                u.setEmail(JOptionPane.showInputDialog("Ingrese el nuevo email:", u.getEmail()));

                repo.save(u);
                JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró usuario con ID " + id);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteUser(){
        String input = JOptionPane.showInputDialog("Ingrese la Id del Usuario que quiere Eliminar: ");
        if (input == null) return;

        try {
            int id = Integer.parseInt(input);
            Usuarios u = repo.searchId(id);

            if (u != null) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "¿Seguro que desea eliminar al usuario con ID " + id + "?",
                        "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        repo.delete((long) id);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró usuario con ID " + id);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
