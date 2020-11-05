package ftt.ec.webapi;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ftt.ec.beans.Acesso;
import ftt.ec.beans.Endereco;
import ftt.ec.beans.Foto;
import ftt.ec.beans.Perfil;
import ftt.ec.beans.Pessoa;
import ftt.ec.model.AcessoDao;
import ftt.ec.model.EnderecoDao;
import ftt.ec.model.FotoDao;
import ftt.ec.model.PerfilDao;
import ftt.ec.model.PessoaDao;

/**
 * Servlet implementation class AcessoApi
 */
@WebServlet("/AcessoApi")
public class AcessoApi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AcessoApi() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Acesso get - " + new Date());

		int enderecoId = insertEndereco(request);

		int fotoId = insertFoto(request);

		int perfilId = insertPerfil(request);

		int pessoaId = insertPessoa(request, enderecoId, fotoId);

		insertAcesso(request, perfilId, pessoaId);

		if (request.getContentLength() < 1) {
			response.getWriter().append("Erro ao inserir um usuário");
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

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Client delete - " + new Date());

		Acesso acesso = new Acesso();
		acesso.setId(Integer.parseInt(request.getParameter("Id")));

		System.out.println("Delete: " + acesso.getId());

		AcessoDao acessoDao = new AcessoDao();

		acessoDao.delete(acesso);

		response.getWriter().append("Cliente Apagado...");

	}

	private void insertAcesso(HttpServletRequest request, int perfilId, int pessoaId) {
		Acesso acesso = new Acesso();
		acesso.setUsuario(request.getParameter("usuario"));
		acesso.setSenha(request.getParameter("senha"));
		acesso.setPessoaId(pessoaId);
		acesso.setPerfilId(perfilId);

		AcessoDao acessoDao = new AcessoDao();
		acessoDao.insert(acesso);
	}

	private int insertPessoa(HttpServletRequest request, int enderecoId, int fotoId) {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(request.getParameter("nome"));
		pessoa.setTelefone(request.getParameter("telefone"));
		pessoa.setEmail(request.getParameter("email"));
		pessoa.setCpf(request.getParameter("cpf"));
		pessoa.setSexo(request.getParameter("sexo"));
		pessoa.setEnderecoId(enderecoId);
		pessoa.setFotoId(fotoId);

		PessoaDao pessoaDao = new PessoaDao();
		int pessoaId = pessoaDao.insert(pessoa);
		return pessoaId;
	}

	private int insertPerfil(HttpServletRequest request) {
		Perfil perfil = new Perfil();
		perfil.setTipo(Integer.parseInt(request.getParameter("tipoPerfil")));
		perfil.setServico(Integer.parseInt(request.getParameter("servicoPerfil")));
		perfil.setDisponivel(Integer.parseInt(request.getParameter("disponivelPerfil")));

		PerfilDao perfilDao = new PerfilDao();
		int perfilId = perfilDao.insert(perfil);
		return perfilId;
	}

	private int insertFoto(HttpServletRequest request) {
		Foto foto = new Foto();
		foto.setArquivo(request.getParameter("arquivo"));
		foto.setArquivo(request.getParameter("extensao"));
		foto.setArquivo(request.getParameter("nomeArquivo"));

		FotoDao fotoDao = new FotoDao();
		int fotoId = fotoDao.insert(foto);
		return fotoId;
	}

	private int insertEndereco(HttpServletRequest request) {
		Endereco endereco = new Endereco();
		endereco.setRua(request.getParameter("rua"));
		endereco.setNumero(request.getParameter("numero"));
		endereco.setCep(request.getParameter("cep"));
		endereco.setCidade(request.getParameter("cidade"));
		endereco.setEstado(request.getParameter("estado"));
		endereco.setComplemento(request.getParameter("complemento"));

		EnderecoDao enderecoDao = new EnderecoDao();
		int enderecoId = enderecoDao.insert(endereco);
		return enderecoId;
	}

}
