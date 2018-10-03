<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Livro</title>


</head>
<body>
	<form method="post" action="UsuarioController" name="frmBuscarUsuario">
	<input type="text" name="buscarUsuario" placeholder="Buscar por nome"/>
	<input type="submit" value="Buscar"/>
	<br>
	
	</form>
	<table border="1">
		<thead>
			<tr>
				<th> ID</th>
				<th> Login</th>
				<th> Nome</th>
				<th> Matrícula</th>
				<th colspan="2"> Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${usuarios}" var="usuario">
				<tr>
				<td><c:out value="${usuario.id}"/></td>
				<td><c:out value="${usuario.login}"/></td>
				<td><c:out value="${usuario.nome}"/></td>
				<td><c:out value="${usuario.matricula}"/></td>
				<td><a href='UsuarioController?action=editar&usuarioId=${usuario.id}'>Update</a>				
				<td><a href='UsuarioController?action=deletar&usuarioId=${usuario.id}'>Delete</a>	
			</td>
			</tr>		
		</c:forEach>
	</tbody>
</table>


<p><a href="UsuarioController?action=insert">Usuario</a></p>
</body>
</html>