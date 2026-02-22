package org.rpalacios.webapptarea1.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@WebServlet("/index.html")
public class IndexServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate fecha = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd 'de' MMMM, yyyy");
        String fechaFormato = fecha.format(formato);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        out.print("<!DOCTYPE html>");
        out.print("    <head>");
        out.print("<html>");
        out.print("        <meta charset=\"UTF-8\">");
        out.print("        <title>Tarea 1: Servelt y envío de parámetros</title>");
        out.print("    </head>");
        out.print("    <body>");
        out.print("        <h1>Tarea 1: Servelt y envío de parámetros</h1>");
        if(nombre != null && apellido!=null){
            out.println("        <h2>Hola mi nombre es: " + nombre +" "+apellido + "</h2>");
            out.println("        <h2>La fecha actual es :"+fechaFormato);
        }else {
            out.println("        <h2>Los datos de Nombre y Apellido no fueron ingresados correctamente!</h2>");
            out.println("        <h2>Ejemplo de URL con valores correctos <ul> <li><a href=\"/webapptarea1/index.html?nombre=Rael&apellido=Juarez\">indexhtml?nombre=Rael&apellido=Juarez</a> </li></ul></h2>");

        }
        out.print("    </body>");
        out.print("</html>");
        out.close();
    }
}
