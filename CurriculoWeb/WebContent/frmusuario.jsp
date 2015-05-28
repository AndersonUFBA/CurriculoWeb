<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%@page import="br.com.cursos.entidades.Usuario"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Usuário com Express Ling</title>
</head>
<body>
	
	<c:import url="includes/menu.jsp"></c:import>
	
	<form action="usucontroller.do" method="post"><!-- action chama usucontroller que é o servlet que irá executar o metodo post neste caso -->
			
			<label>Nome: </label>
			<input type="text" name="nomeF" value="${requestScope.usuario.nome}" size="20"/><!-- value diz qual valor inicial será exibido, neste caso vazio. size define o tamanho do quadro -->
			
			<label>Login: </label>
			<input type="text" name="loginF" value="${requestScope.usuario.login}"/>
			
			<label>Senha: </label>
			<input type="password" name="senhaF" value="${requestScope.usuario.senha}" maxlength="6"/><!-- maxleng é o número maximo de caracter --> 
			
			<input type="submit" value="Salvar	"/>
		
	</form>
<img src="imagens/i.jpg" />
</body>
</html>