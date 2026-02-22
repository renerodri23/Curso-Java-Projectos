package org.rplacios.java_jdbc.util;

import org.rplacios.java_jdbc.modelo.Categoria;
import org.rplacios.java_jdbc.modelo.Producto;
import org.rplacios.java_jdbc.repositorio.CategoriaRepositorioImpl;
import org.rplacios.java_jdbc.repositorio.ProductoRepositorioImpl;
import org.rplacios.java_jdbc.repositorio.Repositorio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class ejemploJdbcCategoria {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);


        try (Connection conn = ConexionBaseDatos.getInstance()
        ) {
            Repositorio<Categoria> repo = new CategoriaRepositorioImpl();
            System.out.println("============= listar =============");
            repo.listar().forEach(System.out::println);
            System.out.println("============= Obtener por Id =============");
            System.out.println(repo.porId(2L));



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

