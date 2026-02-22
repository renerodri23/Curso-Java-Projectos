package org.rpalacios.webappform.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@WebServlet({"/crear",""})
public class RegistroProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/form.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String nombre = req.getParameter("nombre");
        String precioStr = req.getParameter("precio");
        int precio = 0;
        String fabricante = req.getParameter("fabricante");
        String[] categorias = req.getParameterValues("categorias");

        Map<String, String> errores = new HashMap<>();

        if (nombre == null || nombre.isBlank()) {

            errores.put("nombre","El nombre es requerido!");
        }

        if (precioStr != null && !precioStr.isBlank()) {
            try{
                precio = Integer.parseInt(precioStr);
            }catch (NumberFormatException e) {
                errores.put("precio","El precio debe ser un número válido!");
            }
        }else {
            errores.put("precio","El precio es requerido!");
        }

        if(fabricante != null && !fabricante.isBlank() ){
            if (fabricante.length()<4 || fabricante.length()>10){
                errores.put("fabricante", "El fabricante debe tener entre 4 y 10 caracteres.");
            }
        } else {
            errores.put("fabricante","El fabricante es requerido!");
        }
        if (categorias==null || categorias.length==0){
            errores.put("categorias","Debe Seleccionar una Categoria!");
        }

        if (errores.isEmpty()){
            try (PrintWriter out = resp.getWriter()) {
                out.print("<!DOCTYPE html>");
                out.print("<html>");
                out.print("    <head>");
                out.print("        <meta charset=\"UFT-8\">");
                out.print("        <title>Resultado Form</title>");
                out.print("    </head>");
                out.print("    <body>");
                out.print("        <h1>Resultado Form</h1>");

                out.println("      <ul>");

                out.println("         <li> Nombre: " + nombre + "</li>");
                out.println("         <li> Precio: " + precio + "</li>");
                out.println("         <li> Fabricante: " + fabricante + "</li>");
                out.println("         <li>Categorias: <ul>");
                Arrays.asList(categorias).forEach(c -> {
                    out.println("             <li>" + c + "</li>");
                });
                out.println("         </ul></li>");
                out.println("      </ul>");
                out.print("    </body>");
                out.print("</html>");
            }
        } else {
            req.setAttribute("errores" , errores);
            getServletContext().getRequestDispatcher("/form.jsp").forward(req,resp);
        }
    }
}
