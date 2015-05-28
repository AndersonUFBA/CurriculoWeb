<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%@page import="br.com.cursos.entidades.Contato"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Contato com Express Ling</title>
</head>
<body>
	
	
	<form action="concontroller.do" method="post"><!-- action chama concontroller que é o servlet que irá executar o metodo post neste caso -->
			
			
			
			<label>Nome: </label>
			<input type="text" name="nomeF" value="${requestScope.contato.nome}" size="20"/><!-- value diz qual valor inicial será exibido, neste caso vazio. size define o tamanho do quadro -->
			
			<label>Email: </label>
			<input type="email" name="emailF" value="${requestScope.contato.email}"/>
					
			
			<input type="submit" value="Salvar	"/>
		
	</form>

</body>
</html>