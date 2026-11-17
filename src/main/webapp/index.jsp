<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Clientes y Cursos</title>
</head>
<body>
    <h1>GESTIÓN DE CLIENTES</h1>
    <ul>
        <li><a href="vistas/frmRegistrarcliente.jsp">Registrar Cliente</a></li>
        <li><a href="ClienteControlador?accion=listar">Lista de Clientes</a></li>
    </ul>

    <h1>GESTIÓN DE CURSOS</h1>
    <ul>
        <li><a href="vistas/frmRegistrarcurso.jsp">Registrar Curso</a></li>
        <li><a href="CursoControlador?accion=listar">Lista de Cursos</a></li>
    </ul>
</body>
</html>