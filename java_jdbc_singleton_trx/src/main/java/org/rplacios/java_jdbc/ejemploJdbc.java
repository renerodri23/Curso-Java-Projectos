package org.rplacios.java_jdbc;

import org.rplacios.java_jdbc.modelo.Categoria;
import org.rplacios.java_jdbc.modelo.Producto;
import org.rplacios.java_jdbc.repositorio.CategoriaRepositorioImpl;
import org.rplacios.java_jdbc.repositorio.ProductoRepositorioImpl;
import org.rplacios.java_jdbc.repositorio.Repositorio;
import org.rplacios.java_jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ejemploJdbc {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);


        try (Connection conn = ConexionBaseDatos.getInstance()
        ) {
            Repositorio<Producto> repo = new ProductoRepositorioImpl();
            System.out.println("============= listar =============");
            repo.listar().forEach(System.out::println);
            System.out.println("============= Obtener por Id =============");
            System.out.println(repo.porId(2L));

            System.out.println("============= Insertar nuevo Producto =============");
            Producto p = new Producto();
            System.out.println("Ingrese el nombre del producto");
            p.setNombre(s.nextLine());

            System.out.println("Ingrese el precio del producto");

            while (!s.hasNextInt()){
                System.out.println("Precio invalido, ingrese un numero: ");
                s.next();
            }
            p.setPrecio(s.nextInt());
            s.nextLine();

            System.out.println("Se utilizara la fecha de actual como fecha de registro");
            p.setFechaRegistro(new Date());

            Repositorio<Categoria> repo2 = new CategoriaRepositorioImpl();
            System.out.println("Ingresa la categoria(categoria validas):");
            repo2.listar().forEach(System.out::println);
            int cata_id = s.nextInt();
            Categoria ctS = null;

            for (Categoria c : repo2.listar()){
                if (c.getId() == cata_id){
                    ctS = c;
                    break;
                }
            }
            if (ctS != null){
                p.setCategoria(ctS);
            }else {
                System.out.println("La categoria ingresada no es valida");
            }

            repo.save(p);
            System.out.println("Lista Actualizada:");
            repo.listar().forEach(System.out::println);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

