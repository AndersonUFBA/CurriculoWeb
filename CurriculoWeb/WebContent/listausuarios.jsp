
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function confirmarExclusao(id){
		if(window.confirm("Tem certeza da exclusão do usuario "+id)){
			location.href="usucontroller.do?acao=excluirUsuario&id="+id;
		}
	}
</script>
</head>
<body>
<c:import url="includes/menu.jsp"></c:import>
		<table border="1">
			<tr bgcolor="#EAEAEA"> <th>Id</th> <th>Nome</th> <th>Login</th> <th>Senha</th> <th>Ação</th> </tr>
<!-- "<% %>" essa tag é chamada delimitador, serve para separar java de html -->

<c:forEach items="${requestScope.lista}" var="usu">
	<tr>
    	<td>${usu.id}</td>
    	<td>${usu.nome}</td>
		<td>${usu.login}</td>
		<td>${usu.senha}</td>   
		<td>
			<a href="javascript:confirmarExclusao(${usu.id})">Excluir</a>
			|
			<a href="usucontroller.do?acao=alterarUsuario&id=${usu.id}">Alterar</a>
		</td>   
    </tr>
	
</c:forEach>
</table>
<img src="imagens/i.jpg" />
</body>
</html>