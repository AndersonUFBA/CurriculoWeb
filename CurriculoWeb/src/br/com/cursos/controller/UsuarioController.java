package br.com.cursos.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.cursos.entidades.Usuario;
import br.com.cursos.jdbc.UsuarioDAO;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/usucontroller.do")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 *o request.Parameter serve para definir a queryString, 
		 ou seja o que será exibi ou requsitado ao pelo comando get, 
		 é sempre depois da "?" 
		 neste caso sera exibido no console, abaixo no metodo PrintWriter 
		 que sairá no browser */
		System.out.println("Chamando o metodo Get");
		
		String acao = request.getParameter("acao");
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		
		if(acao!=null && acao.equals("excluirUsuario")){
			
			String id = request.getParameter("id");
			
			Usuario usuario = new Usuario();
			usuario.setId(Integer.parseInt(id));
			usuarioDAO.excluir(usuario);
			
			//redirecionando para o cliente (browser)
			//response.sendRedirect("usucontroller.do?acao=listarUsuarios");
			//ou
			acao="listarUsuarios";
		}
		
		if(acao!=null && acao.equals("alterarUsuario")){
			
			String id = request.getParameter("id");//captura da tela
			
			Usuario usuario = usuarioDAO.buscaPorId(Integer.parseInt(id));//busca objeto no banco
			request.setAttribute("usuario", usuario);//captura a informação do banco e coloca no objeto usuario
			//encaminha para o jsp(tela)
			RequestDispatcher saida = request.getRequestDispatcher("frmusuario.jsp");
			saida.forward(request, response);
			
		}
		
		if(acao!=null && acao.equals("cadastrarUsuario")){
			
				
			Usuario usuario = new Usuario();//cria nov objeto usuario
			
			usuario.setNome("");
			usuario.setLogin("");
			usuario.setSenha("");
			request.setAttribute("usuario", usuario);//captura a informação do banco e coloca no objeto usuario
			//encaminha para o jsp(tela)
			RequestDispatcher saida = request.getRequestDispatcher("frmusuario.jsp");
			saida.forward(request, response);
			
		}
		
		if(acao!=null && acao.equals("listarUsuarios")){
		List<Usuario> lista = usuarioDAO.buscarTodos();// como esse metodo retorna uma lista, criou-se uma variavel lista do tipo list
		
		//recebe a lista
		request.setAttribute("lista", lista); //lança os dados do banco na lista?
		
		//encaminha para o jsp
		RequestDispatcher saida = request.getRequestDispatcher("listausuarios.jsp");
		saida.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recebe dados da tela
		System.out.println("Chamando o metodo Post");
		String id = request.getParameter("idF");
		String nome = request.getParameter("nomeF");
		String login = request.getParameter("loginF");
		String senha = request.getParameter("senhaF");
		
		//cria objeto usuario  e seta valores vindos da tela
		Usuario usuario = new Usuario();
		if(id!=null && id!="0" && id!=""){
			usuario.setId(Integer.parseInt(id));//Integer.parseInt converte a string em inteiro
		}
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		// solicita para usuarioDAO cadastrar um novo usuario no banco
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
		
		//saida para o browser
		PrintWriter saida = response.getWriter();
		saida.println("Salvo com sucesso!");
	}

}
