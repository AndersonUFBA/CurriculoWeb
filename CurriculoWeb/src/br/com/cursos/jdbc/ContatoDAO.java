package br.com.cursos.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.cursos.entidades.Contato;

public class ContatoDAO {
private Connection con = Conexao.getConnection();
	
	public void cadastrar (Contato contato){
		//monta sql
		String sql = "INSERT INTO CONTATO (nome, email ) values (?,?)";
		
		//constre o PreparedStatement com o sql 
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, contato.getNome());
			preparador.setString(2, contato.getEmail());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			System.out.println("erro!");
			e.printStackTrace();
		}
	}
	
	public void alterar(Contato contato){
		//monta sql
		String sql = "UPDATE CONTATO SET nome=?, email=? WHERE  id=?";
		
		//constre o PreparedStatement com o sql 
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, contato.getNome());
			preparador.setString(2, contato.getEmail());
		
			preparador.setInt(4, contato.getId());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Alterado com sucesso!");
		} catch (SQLException e) {
			System.out.println("erro!");
			e.printStackTrace();
		}
	}
	
	public void excluir (Contato contato){
		//monta sql
		String sql = "DELETE FROM CONTATO WHERE  id=?";
		
		//constre o PreparedStatement com o sql 
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, contato.getId());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("EXCLUIDO com sucesso!");
		} catch (SQLException e) {
			System.out.println("erro!");
			e.printStackTrace();
		}
	}
	
	public java.util.List<Contato> buscarTodos (){
		//monta sql
		String sql = "SELECT * FROM CONTATO ORDER BY ID";
		
		java.util.List<Contato> lista = new ArrayList<Contato>(); //cria a listas onde serão ordanados os objetos Contatos
						
		//constre o PreparedStatement com o sql 
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			
			ResultSet resultado = preparador.executeQuery(); // executar consulta por que neste caso estamos usando o Resultset
			
			
			while (resultado.next()){
				
				Contato usu = new Contato();
				
				usu.setId(resultado.getInt("id")); //exibe o valor da coluna id
				usu.setNome(resultado.getString("nome"));
				usu.setEmail(resultado.getString("email"));
				
				
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
	
	public Contato buscaPorId(Integer id ) {
		
		String sql = "SELECT * FROM Contato WHERE ID = ?";
		Contato contato = null;
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id); 
			
			ResultSet resultado = preparador.executeQuery();
			
			if (resultado.next()){// vai para o primeiro registro do resultado e reorna true ou false
			contato = new Contato();
			contato.setId(resultado.getInt("id"));
			contato.setNome(resultado.getString("nome"));
			contato.setEmail(resultado.getString("email"));
			
			}
			preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contato;
	}
	
	public java.util.List<Contato> buscaPorNome(String nome ) {
		
		String sql = "SELECT * FROM Contato WHERE nome like ?";
		java.util.List<Contato> lista = new ArrayList<Contato>();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1,"%" + nome + "%"); // poderá estar concatenado com em alguma string
			
			ResultSet resultado = preparador.executeQuery();
			
			while (resultado.next()){// vai para o primeiro registro do resultado e reorna true ou false
			Contato contato = new Contato();
			contato.setId(resultado.getInt("id"));
			contato.setNome(resultado.getString("nome"));
			contato.setEmail(resultado.getString("email"));
			
			lista.add(contato);
			}
			preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	

/*este metodo seve para verificar email e senha conferem e retorna false ou true*/
	
	
	/*esse metodo tem por finalidade alterar ou cadastrar contato*/
	public void salvar(Contato contato) {
		if (contato.getId()!= null && contato.getId()!=0){
			alterar(contato);			
		}else{
			cadastrar(contato);
		}
	}
	
	
	
}
