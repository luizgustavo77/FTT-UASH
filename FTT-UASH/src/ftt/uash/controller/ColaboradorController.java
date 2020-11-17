package ftt.uash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ftt.uash.service.ClienteService;

/**
 * Servlet implementation class ColaboradorController
 */
@WebServlet("/ColaboradorController")
public class ColaboradorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteService clienteService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ColaboradorController() {
		super();
		clienteService = new ClienteService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int enderecoId = clienteService.insertEndereco(request);
		int fotoId = clienteService.insertFoto(request);
		int perfilId = clienteService.insertPerfil(request);
		int pessoaId = clienteService.insertPessoa(request, enderecoId, fotoId);
		clienteService.insertAcesso(request, perfilId, pessoaId);

		if (request.getContentLength() < 1) {
			response.getWriter().append("Erro ao inserir um usuario");
		} else {
			response.getWriter().append("Usuario criado com sucesso");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int enderecoId = clienteService.updateEndereco(request);
		int fotoId = clienteService.updateFoto(request);
		int perfilId = clienteService.updatePerfil(request);
		int pessoaId = clienteService.updatePessoa(request, enderecoId, fotoId);
		clienteService.updateAcesso(request, perfilId, pessoaId);

		if (request.getContentLength() < 1) {
			response.getWriter().append("Erro ao inserir um usuario");
		} else {
			response.getWriter().append("Usuario criado com sucesso");
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		clienteService.deleteAcesso(Integer.parseInt(request.getParameter("id")));
		clienteService.deletePessoa(Integer.parseInt(request.getParameter("id")));
		clienteService.deletePerfil(Integer.parseInt(request.getParameter("id")));
		clienteService.deleteFoto(Integer.parseInt(request.getParameter("id")));
		clienteService.deleteEndereco(Integer.parseInt(request.getParameter("id")));
		
		if (request.getContentLength() < 1) {
			response.getWriter().append("Erro ao excluir um usuario");
		} else {
			response.getWriter().append("Usuario excluido com sucesso");
		}
	}

}
