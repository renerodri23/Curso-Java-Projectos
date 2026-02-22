package org.rpalacios.hibernateapp;

import jakarta.persistence.EntityManager;
import org.rpalacios.hibernateapp.entity.Cliente;
import org.rpalacios.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchResultList {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        List<Cliente> clientes = em.createQuery("SELECT C FROM Cliente C", Cliente.class).getResultList();;

        clientes.forEach(System.out::println);

        em.close();
    }
}
