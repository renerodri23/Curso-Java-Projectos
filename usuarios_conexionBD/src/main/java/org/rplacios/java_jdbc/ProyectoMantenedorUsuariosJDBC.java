package org.rplacios.java_jdbc;

import javax.swing.*;

public class ProyectoMantenedorUsuariosJDBC {
    public static void main(String[] args) {
        UsuarioService service = new UsuarioService();
        boolean salir = false;

        while (!salir) {
            Object[] opciones = {"Listar", "Buscar", "Agregar", "Actualizar", "Eliminar", "Salir"};
            Object opcion = JOptionPane.showInputDialog(null,
                    "Seleccione una Operación",
                    "Mantenedor de Usuarios",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            if (opcion == null) {
                salir = true; // si dan cancelar o cierran con la X → salir
                continue;
            }

            String seleccion = opcion.toString();

            switch (seleccion) {
                case "Listar":
                    service.listUser();
                    break;
                case "Buscar":
                    service.searchId();
                    break;
                case "Agregar":
                    service.addUser();
                    break;
                case "Actualizar":
                    service.updateUser();
                    break;
                case "Eliminar":
                    service.deleteUser();
                    break;
                case "Salir":
                    salir = true;
                    break;
            }
        }

        JOptionPane.showMessageDialog(null, "Programa finalizado. ¡Hasta luego!");
    }
}
