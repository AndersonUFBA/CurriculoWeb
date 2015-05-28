
package br.com.cursos.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.cursos.entidades.Usuario;
/*esta classe é responsavel pelos metodos de comunicação com o banco de dados*/
public class UsuarioDAO {
	private Connection con = Conexao.getConnection();
	
	public void cadastrar (Usuario usuario){
		//monta sql
		String sql = "INSERT INTO USUARIO (nome, login, senha) values (?,?,?)";
		
		//constre o PreparedStatement com o sql 
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			System.out.println("erro!");
			e.printStackTrace();
		}
	}
	
	public void alterar(Usuario usuario){
		//monta sql
		String sql = "UPDATE USUARIO SET nome=?, login=?, senha=? WHERE  id=?";
		
		//constre o PreparedStatement com o sql 
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			preparador.setInt(4, usuario.getId());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Alterado com sucesso!");
		} catch (SQLException e) {
			System.out.println("erro!");
			e.printStackTrace();
		}
	}
	
	public void excluir (Usuario usuario){
		//monta sql
		String sql = "DELETE FROM USUARIO WHERE  id=?";
		
		//constre o PreparedStatement com o sql 
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, usuario.getId());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("EXCLUIDO com sucesso!");
		} catch (SQLException e) {
			System.out.println("erro!");
			e.printStackTrace();
		}
	}
	
	public java.util.List<Usuario> buscarTodos (){
		//monta sql
		String sql = "SELECT * FROM USUARIO ORDER BY ID";
		
		java.util.List<Usuario> lista = new ArrayList<Usuario>(); //cria a listas onde serão ordanados os objetos usuarios
						
		//constre o PreparedStatement com o sql 
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			
			ResultSet resultado = preparador.executeQuery(); // executar consulta por que neste caso estamos usando o Resultset
			
			
			while (resultado.next()){
				
				Usuario usu = new Usuario();
				
				usu.setId(resultado.getInt("id")); //exibe o valor da coluna id
				usu.setNome(resultado.getString("nome"));
				usu.setLogin(resultado.getString("login"));
				usu.setSenha(resultado.getString("senha"));
				
				lista.add(usu);
			}
			
			
			preparador.close();
			
			System.out.println("Busca concluida!");
			
		} catch (SQLException e) {
			System.out.println("erro!");
			e.printStackTrace();
		}
		return lista;
	}
	
	public Usuario buscaPorId(Integer id ) {
		
		String sql = "SELECT * FROM Usuario WHERE ID = ?";
		Usuario usuario = null;
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id); 
			
			ResultSet resultado = preparador.executeQuery();
			
			if (resultado.next()){// vai para o primeiro registro do resultado e reorna true ou false
			usuario = new Usuario();
			usuario.setId(resultado.getInt("id"));
			usuario.setNome(resultado.getString("nome"));
			usuario.setLogin(resultado.getString("login"));
			usuario.setSenha(resultado.getString("senha"));
			}
			preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuario;
	}
	
	public java.util.List<Usuario> buscaPorNome(String nome ) {
		
		String sql = "SELECT * FROM Usuario WHERE nome like ?";
		java.util.List<Usuario> lista = new ArrayList<Usuario>();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1,"%" + nome + "%"); // poderá estar concatenado com em alguma string
			
			ResultSet resultado = preparador.executeQuery();
			
			while (resultado.next()){// vai para o primeiro registro do resultado e reorna true ou false
			Usuario usuario = new Usuario();
			usuario.setId(resultado.getInt("id"));
			usuario.setNome(resultado.getString("nome"));
			usuario.setLogin(resultado.getString("login"));
			usuario.setSenha(resultado.getString("senha"));
			lista.add(usuario);
			}
			preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
public Usuario autenticar(Usuario usuario ) {
		
		String sql = "SELECT * FROM Usuario WHERE login = ? and senha = ?";
		Usuario usuarioRetorno = null;
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usuario.getLogin());
			preparador.setString(2, usuario.getSenha());
			
			ResultSet resultado = preparador.executeQuery();
			
			if (resultado.next()){// vai para o primeiro registro do resultado e reorna true ou false
			usuarioRetorno = new Usuario();
			usuarioRetorno.setId(resultado.getInt("id"));
			usuarioRetorno.setNome(resultado.getString("nome"));
			usuarioRetorno.setLogin(resultado.getString("login"));
			usuarioRetorno.setSenha(resultado.getString("senha"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarioRetorno;
	}
	
/*este metodo seve para verificar login e senha conferem e retorna false ou true*/
	public Boolean existeUsuario(Usuario usuario ) {
		
		String sql = "SELECT * FROM Usuario WHERE login = ? and senha = ?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usuario.getLogin());
			preparador.setString(2, usuario.getSenha());
			
			ResultSet resultado = preparador.executeQuery();
			
			return resultado.next();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/*esse metodo tem por finalidade alterar ou cadastrar usuario*/
	public void salvar(Usuario usuario) {
		if (usuario.getId()!= null && usuario.getId()!=0){
			alterar(usuario);			
		}else{
			cadastrar(usuario);
		}
	}
	
	
	
}

