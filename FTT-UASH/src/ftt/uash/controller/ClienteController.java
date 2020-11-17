package ftt.uash.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ftt.uash.dao.AcessoDao;
import ftt.uash.dao.EnderecoDao;
import ftt.uash.dao.FotoDao;
import ftt.uash.dao.PerfilDao;
import ftt.uash.dao.PessoaDao;
import ftt.uash.model.AcessoModel;
import ftt.uash.model.EnderecoModel;
import ftt.uash.model.FotoModel;
import ftt.uash.model.PerfilModel;
import ftt.uash.model.PessoaModel;
import ftt.uash.service.ClienteService;

/**
 * Servlet implementation class ClienteController
 */
@WebServlet("/ClienteController")
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteService clienteService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClienteController() {
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
