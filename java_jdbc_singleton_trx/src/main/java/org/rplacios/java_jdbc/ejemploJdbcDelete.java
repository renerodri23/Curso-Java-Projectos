package org.rplacios.java_jdbc;

import org.rplacios.java_jdbc.modelo.Producto;
import org.rplacios.java_jdbc.repositorio.ProductoRepositorioImpl;
import org.rplacios.java_jdbc.repositorio.Repositorio;
import org.rplacios.java_jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ejemploJdbcDelete {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);


        try (Connection conn = ConexionBaseDatos.getInstance()
        ) {
            Repositorio<Producto> repo = new ProductoRepositorioImpl();
            System.out.println("============= Actualizar Datos =============");
            System.out.println("Ingrese la id del producto que quiere eliminar");

            if (s.hasNextLong()) {
                long id = s.nextInt();
                s.nextLine();

                Producto p = repo.porId(id);
                if (p != null){
                   repo.eliminar(id);
                    System.out.println("Listan Base de Datos Actualizada");
                    repo.listar().forEach(System.out::println);
                }
                else {
                    System.out.println("No existe producto con el id "+id);}
            }else {
                System.out.println("Id invalido");
            }




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

