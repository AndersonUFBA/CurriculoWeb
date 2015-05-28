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

import br.com.cursos.entidades.Contato;
import br.com.cursos.jdbc.ContatoDAO;

/**
 * Servlet implementation class ContatoController
 */
@WebServlet("/concontroller.do")
public class ContatoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContatoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 *o request.Parameter serve para definir a queryString, 
		 ou seja o que será exibido ou requsitado ao pelo comando get, 
		 é sempre depois da "?" 
		 neste caso sera exibido no console, abaixo no metodo PrintWriter 
		 que sairá no browser */
		System.out.println("Chamando o metodo Get");
		
		String acao = request.getParameter("acao");
		ContatoDAO contatoDAO = new ContatoDAO();
		
		
		if(acao!=null && acao.equals("excluirContato")){
			
			String id = request.getParameter("id");
			
			Contato contato = new Contato();
			contato.setId(Integer.parseInt(id));
			contatoDAO.excluir(contato);
			
			//redirecionando para o cliente (browser)
			//response.sendRedirect("usucontroller.do?acao=listarcontatos");
			//ou
			acao="listarContatos";
		}
		
		if(acao!=null && acao.equals("alterarContato")){
			
			String id = request.getParameter("id");//captura da tela
			
			Contato contato = contatoDAO.buscaPorId(Integer.parseInt(id));//busca objeto no banco
			request.setAttribute("contato", contato);//captura a informação do banco e coloca no objeto contato
			//encaminha para o jsp(tela)
			RequestDispatcher saida = request.getRequestDispatcher("frmcontato.jsp");
			saida.forward(request, response);
			
		}
		
		if(acao!=null && acao.equals("cadastrarContato")){
			
				
			Contato contato = new Contato();//cria nov objeto contato
			
			contato.setNome("");
			contato.setEmail("");
			request.setAttribute("contato", contato);//captura a informação do banco e coloca no objeto contato
			//encaminha para o jsp(tela)
			RequestDispatcher saida = request.getRequestDispatcher("frmcontato.jsp");
			saida.forward(request, response);
			
		}
		
		if(acao!=null && acao.equals("listarContatos")){
		List<Contato> lista = contatoDAO.buscarTodos();// como esse metodo retorna uma lista, criou-se uma variavel lista do tipo list
		
		//recebe a lista
		request.setAttribute("lista", lista); //lança os dados do banco na lista?
		
		//encaminha para o jsp
		RequestDispatcher saida = request.getRequestDispatcher("listacontatos.jsp");
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
		String email = request.getParameter("emailF");
		
		
		//cria objeto contato  e seta valores vindos da tela
		Contato contato = new Contato();
		if(id!=null && id!="0" && id!=""){
			contato.setId(Integer.parseInt(id));//Integer.parseInt converte a string em inteiro
		}
		contato.setNome(nome);
		contato.setEmail(email);
		
		
		// solicita para contatoDAO cadastrar um novo contato no banco
		ContatoDAO contatoDAO = new ContatoDAO();
		contatoDAO.salvar(contato);
		
		//saida para o browser
		PrintWriter saida = response.getWriter();
		saida.println("Salvo com sucesso!");
	}

}
