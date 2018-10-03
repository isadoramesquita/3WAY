<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charsert="UTF-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<title>Inserir novo usuario</title>

</head>

<body>
	<div class="row"></div>
	<div class="col-md-5"></div>
	<div class="panel panel-primary">
		<div class="panel-heading">Cadastro de Usuarios</div>
		<div class="panel-body">
			<form method="POST" action='UsuarioController' name="frmAddUsuario">
			
			
				<fieldset>
					<div class="form-group">
						<label>ID</label> <input type="text" readonly="readonly"
							class="form-control" name="UsuarioCodigo" value="${usuario.id}">

					</div>

					<div class="form-group">
						<label> Login </label> <input type="text" class="form-control"
							name="login" value="${usuario.login}">
					</div>
					
					<div class="form-group">
						<label> Nome </label> <input type="text" class="form-control"
							name="nome" value="${usuario.nome}">
					</div>
					
					<div class="form-group">
						<label> Matricula </label> <input type="text" class="form-control"
							name="matricula" value="${usuario.matricula}">
					</div>
					

				</fieldset>
				<input type="submit" value="Enviar" class="btn btn-primary">
			</form>
		</div>
	</div>

</body>
</html>