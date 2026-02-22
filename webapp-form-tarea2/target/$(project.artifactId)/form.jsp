<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%
 Map<String, String> errores = (Map<String, String>)request.getAttribute("errores");
 %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Formulario de Usuarios</title>
</head>
<body>
<h3>Formulario de Usuarios</h3>
<div class="px-5">
<form action="/webapp-form-tarea2/crear" method="post">

    <div class="row mb-3">
        <label for="nombre" class="col-form-label col-sm-2">Nombre</label>
        <div class="col-sm-4"><input type="text" name="nombre" id="nombre" class="form-control" value="${param.nombre}"></div>
        <%
        if(errores != null && errores.containsKey("nombre")){
            out.println("<small class='alert alert-danger col-sm-4' style='color: red'>"+ errores.get("nombre")+"</small>");
        }
        %>

    </div>
    <div class="row mb-3">
            <label for="precio" class="col-form-label col-sm-2">Precio</label>
            <div class="col-sm-4"><input type="text" name="precio" id="precio" class="form-control" value="${param.precio}"></div>
    <%
            if(errores != null && errores.containsKey("precio")){
                out.println("<small class='alert alert-danger col-sm-4' style='color: red'>"+ errores.get("precio")+"</small>");
            }
            %>
        </div>
        <div class="row mb-3">
            <label for="fabricante" class="col-form-label col-sm-2">Fabricante</label>
            <div class="col-sm-4"><input type="text" name="fabricante" id="fabricante" class="form-control" value="${param.fabricante}"></div>
    <%
            if(errores != null && errores.containsKey("fabricante")){
                out.println("<small class='alert alert-danger col-sm-4' style='color: red'>"+ errores.get("fabricante")+"</small>");
            }
            %>
        </div>
        <div class="row mb-3">
                <label for="categorias" class="col-form-label col-sm-2">Categorias</label>
                <div class="col-sm-4 ">
                    <select name="categorias" id="categorias" class="form-select">
                        <option value="">-- seleccionar --</option>
                        <option value="TEC" ${param.categorias.equals("TEC") ? "selected" : ""}>Tecnología</option>
                        <option value="HOG" ${param.categorias.equals("HOG") ? "selected" : ""}>Hogar</option>
                        <option value="DEP" ${param.categorias.equals("DEP") ? "selected" : ""}>Deportes</option>
                        <option value="MOD" ${param.categorias.equals("MOD") ? "selected" : ""}>Moda</option>
                        <option value="ALI" ${param.categorias.equals("ALI") ? "selected" : ""}>Alimentos</option>
                        <option value="BEL" ${param.categorias.equals("BEL") ? "selected" : ""}>Belleza</option>
                        <option value="JUG" ${param.categorias.equals("JUG") ? "selected" : ""}>Juguetes</option>
                        <option value="SAL" ${param.categorias.equals("SAL") ? "selected" : ""}>Salud</option>
                        <option value="AUT" ${param.categorias.equals("AUT") ? "selected" : ""}>Automotriz</option>
                        <option value="LIB" ${param.categorias.equals("LIB") ? "selected" : ""}>Libros</option>
                    </select>
                </div>
                 <%
                                if(errores != null && errores.containsKey("categorias")){
                                    out.println("<small class='alert alert-danger col-sm-4' style='color: red'>"+ errores.get("categorias")+"</small>");
                                }
                                %>
        </div>
        <div class="row mb-3">
                <div>
                    <input type="submit" value="Enviar" class="btn btn-primary">
                </div>
            </div>
            </form>
            </div>
            </body>
            </html>