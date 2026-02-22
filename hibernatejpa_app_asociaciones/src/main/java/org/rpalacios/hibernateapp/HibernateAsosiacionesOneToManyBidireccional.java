package org.rpalacios.hibernateapp;

import jakarta.persistence.EntityManager;
import org.rpalacios.hibernateapp.entity.Cliente;
import org.rpalacios.hibernateapp.entity.Factura;
import org.rpalacios.hibernateapp.util.JpaUtil;

public class HibernateAsosiacionesOneToManyBidireccional {

    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try{

            em.getTransaction().begin();
            Cliente cliente = new Cliente("Rene", "Palacios");
            cliente.setFormaPago("Paypal");

            Factura factura1 = new Factura("Compra de computadoras", 1500L);
            Factura factura2 = new Factura("Compra de celulares", 800L);

            cliente.addFactura(factura1).addFactura(factura2);

            factura1.setCliente(cliente);
            factura2.setCliente(cliente);

            em.persist(cliente);

            em.getTransaction().commit();
            System.out.println(cliente);
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }
    }
}
