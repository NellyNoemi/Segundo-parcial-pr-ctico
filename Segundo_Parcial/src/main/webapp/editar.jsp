<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <c:if test="${aviso.id == 0}">Nuevo</c:if>
            <c:if test="${aviso.id != 0}">Editar</c:if>
            registro
        </h1>
            <form action="Controller" method="post">
                <input type="hidden" name="id" value="${aviso.id}" />
            <label>Titulo:</label>
            <input type="text" name="titulo" value="${aviso.titulo}" />
            <br>
            <label>Exposicion:</label>
            <textarea name="exposicion">${aviso.exposicion}</textarea>
            <br>
            <label>Fecha:</label>
            <input type="text" name="fecha" value="${aviso.fecha}" />
            <br>
            <label>Hora:</label>
            <input type="text" name="hora" value="${aviso.hora}" />
            <br>
            <label>Cupo:</label>
            <input type="number" name="cupo" value="${aviso.cupo}" />
            <br>
            <input type="submit" value="Enviar" />
            </form>
    </body>
</html>
