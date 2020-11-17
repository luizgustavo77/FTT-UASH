package ftt.uash.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ftt.uash.dao.AcessoDao;
import ftt.uash.dao.EnderecoDao;
import ftt.uash.model.AcessoModel;
import ftt.uash.model.EnderecoModel;

/**
 * Servlet implementation class AcessoController
 */
@WebServlet("/acessoController")
public class AcessoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AcessoController() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AcessoModel acessoModel = new AcessoModel();
		acessoModel.setUsuario(request.getParameter("usuario"));
		acessoModel.setSenha(request.getParameter("senha"));
		
		AcessoDao acessoDao = new AcessoDao();
		boolean usuarioExiste = acessoDao.findByUserAndPassword(acessoModel);
		
		if (usuarioExiste) {
			response.getWriter().append("Usuario valido");
		} else {
			response.getWriter().append("Usuario invalido");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)you
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
