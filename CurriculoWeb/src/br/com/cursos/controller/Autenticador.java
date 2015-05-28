package br.com.cursos.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.cursos.entidades.Usuario;
import br.com.cursos.jdbc.UsuarioDAO;

/**
 * Servlet implementation class Autenticador
 */
@WebServlet(name = "AutenticadorController", urlPatterns = { "/autcontroller.do" })
public class Autenticador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Autenticador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sessao = request.getSession(false);//capturar a sessão sem criar
		
		
		if (sessao!=null){
			sessao.invalidate();
		}
		response.sendRedirect("login.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuRetorno =  usuarioDAO.autenticar(usuario);
		if (usuRetorno!=null){
			//criando uma sessão
			HttpSession sessao = request.getSession();
			sessao.setMaxInactiveInterval(3000);
			sessao.setAttribute("usuLogado", usuRetorno);
			
			//encaminhando para o index
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else{
			response.sendRedirect("login.html");
		}
	}

}

