package org.rpalacios.hibernateapp;

import jakarta.persistence.EntityManager;
import org.rpalacios.hibernateapp.entity.Cliente;
import org.rpalacios.hibernateapp.entity.Factura;
import org.rpalacios.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesManyToONE {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();
            Cliente cliente = new Cliente("Catherine", "Zeta");
            cliente.setFormaPago("Credito");
            em.persist(cliente);

            Factura factura = new Factura("Compras de Oficina", 1000L);
            factura.setCliente(cliente);
            em.persist(factura);

            System.out.println(factura);


            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }
}
