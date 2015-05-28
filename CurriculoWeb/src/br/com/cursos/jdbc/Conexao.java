package br.com.cursos.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {//se concta ao banco de dados bancocjweb1 
	
	
	public static Connection getConnection(){
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");/*carrega o driver na memoria, pq tava dando erro*/
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bancocjweb1","postgres","r@@t");
			System.out.println("Conectado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao conectar!: " + e.getMessage());
			
		} catch (ClassNotFoundException e) {
			System.out.println("driver nao encontrado");
		}
		return con;
	}

}
