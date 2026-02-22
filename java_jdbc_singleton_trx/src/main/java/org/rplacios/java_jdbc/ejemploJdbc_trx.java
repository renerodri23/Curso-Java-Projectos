package org.rplacios.java_jdbc;

import org.rplacios.java_jdbc.modelo.Producto;
import org.rplacios.java_jdbc.repositorio.ProductoRepositorioImpl;
import org.rplacios.java_jdbc.repositorio.Repositorio;
import org.rplacios.java_jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class ejemploJdbc_trx {
    public static void main(String[] args) throws SQLException {
        Scanner s = new Scanner(System.in);

        try (Connection conn = ConexionBaseDatos.getInstance()) {
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try {
                Repositorio<Producto> repo = new ProductoRepositorioImpl();

                System.out.println("============= Actualizar Datos =============");
                System.out.println("Ingrese la id del producto que quiere modificar: ");

                if (s.hasNextLong()) {
                    long id = s.nextLong();
                    s.nextLine(); // limpiar buffer

                    Producto p = repo.porId(id);
                    if (p != null) {
                        System.out.println("Producto Actual: " + p);

                        System.out.println("Ingrese nuevo nombre (enter para mantener):");
                        String nuevoNombre = s.nextLine();
                        if (!nuevoNombre.isBlank()) {
                            p.setNombre(nuevoNombre);
                        }

                        System.out.println("Ingrese nuevo precio (0 para mantener):");
                        int nuevoPrecio = s.nextInt();
                        s.nextLine();
                        if (nuevoPrecio > 0) {
                            p.setPrecio(nuevoPrecio);
                        }

                        // opcional, fecha de modificación
                        p.setFechaRegistro(new Date());

                        repo.save(p);
                        System.out.println("✅ Producto actualizado con éxito");

                        System.out.println("============= Lista Actualizada =============");
                        repo.listar().forEach(System.out::println);
                        conn.commit();

                    } else {
                        System.out.println("No existe producto con el id " + id);
                    }
                } else {
                    System.out.println("Id inválido");
                }
            } catch (SQLException exception){
                conn.rollback();
                exception.printStackTrace();
            }
        }
    }
}
