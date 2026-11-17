<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <h2>Registrar Nuevo Cliente</h2>
    <hr>
    <form action="${pageContext.request.contextPath}/ClienteControlador" method="POST">
        <div class="mb-3">
            <label class="form-label">DNI:</label>
            <input type="text" name="dni" class="form-control" maxlength="8" required placeholder="Ej: 74125896">
        </div>

        <div class="mb-3">
            <label class="form-label">Apellidos:</label>
            <input type="text" name="apellidos" class="form-control" required placeholder="Ej: Mendoza Ruiz">
        </div>

        <div class="mb-3">
            <label class="form-label">Nombres:</label>
            <input type="text" name="nombres" class="form-control" required placeholder="Ej: Carlos Alberto">
        </div>

        <div class="mb-3">
            <label class="form-label">Email:</label>
            <input type="email" name="email" class="form-control" required placeholder="Ej: cmendoza@correo.com">
        </div>

        <div class="mb-3">
            <label class="form-label">Celular:</label>
            <input type="text" name="celular" class="form-control" maxlength="9" required placeholder="Ej: 987654321">
        </div>

        <div class="mb-3">
            <label class="form-label">Dirección:</label>
            <input type="text" name="direccion" class="form-control" required placeholder="Ej: Jr. Amazonas 450">
        </div>

        <button type="submit" name="btnRegistrar" value="Registrar" class="btn btn-primary">Registrar</button>
        <a href="${pageContext.request.contextPath}/ClienteControlador?accion=listar" class="btn btn-secondary">Volver al Listado</a>
    </form>
</body>
</html>