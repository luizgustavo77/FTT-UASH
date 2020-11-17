package ftt.uash.service;

import javax.servlet.http.HttpServletRequest;

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

public class ClienteService {
	public void insertAcesso(HttpServletRequest request, int perfilId, int pessoaId) {
		AcessoModel acesso = createAcessoModel(request, perfilId, pessoaId);

		AcessoDao acessoDao = new AcessoDao();
		acessoDao.insert(acesso);
	}

	public int insertPessoa(HttpServletRequest request, int enderecoId, int fotoId) {
		PessoaModel pessoa = createPessoaModel(request, enderecoId, fotoId);

		PessoaDao pessoaDao = new PessoaDao();
		int pessoaId = pessoaDao.insert(pessoa);
		return pessoaId;
	}

	public int insertPerfil(HttpServletRequest request) {
		PerfilModel perfil = createPerfilModel(request);

		PerfilDao perfilDao = new PerfilDao();
		int perfilId = perfilDao.insert(perfil);
		return perfilId;
	}

	public int insertFoto(HttpServletRequest request) {
		FotoModel foto = createFotoModel(request);

		FotoDao fotoDao = new FotoDao();
		int fotoId = fotoDao.insert(foto);
		return fotoId;
	}

	public int insertEndereco(HttpServletRequest request) {
		EnderecoModel endereco = createEnderecoModel(request);

		EnderecoDao enderecoDao = new EnderecoDao();
		int enderecoId = enderecoDao.insert(endereco);
		return enderecoId;
	}

	public void updateAcesso(HttpServletRequest request, int perfilId, int pessoaId) {
		AcessoModel acesso = createAcessoModel(request, perfilId, pessoaId);

		AcessoDao acessoDao = new AcessoDao();
		acessoDao.update(acesso);
	}

	public int updatePessoa(HttpServletRequest request, int enderecoId, int fotoId) {
		PessoaModel pessoa = createPessoaModel(request, enderecoId, fotoId);

		PessoaDao pessoaDao = new PessoaDao();
		int pessoaId = pessoaDao.update(pessoa);
		return pessoaId;
	}

	public int updatePerfil(HttpServletRequest request) {
		PerfilModel perfil = createPerfilModel(request);

		PerfilDao perfilDao = new PerfilDao();
		int perfilId = perfilDao.update(perfil);
		return perfilId;
	}

	public int updateFoto(HttpServletRequest request) {
		FotoModel foto = createFotoModel(request);

		FotoDao fotoDao = new FotoDao();
		int fotoId = fotoDao.update(foto);
		return fotoId;
	}

	public int updateEndereco(HttpServletRequest request) {
		EnderecoModel endereco = createEnderecoModel(request);

		EnderecoDao enderecoDao = new EnderecoDao();
		int enderecoId = enderecoDao.update(endereco);
		return enderecoId;
	}

	private AcessoModel createAcessoModel(HttpServletRequest request, int perfilId, int pessoaId) {
		AcessoModel acesso = new AcessoModel();
		acesso.setId(Integer.parseInt(request.getParameter("id")));
		acesso.setUsuario(request.getParameter("usuario"));
		acesso.setSenha(request.getParameter("senha"));
		acesso.setPessoaId(pessoaId);
		acesso.setPerfilId(perfilId);
		return acesso;
	}

	private PessoaModel createPessoaModel(HttpServletRequest request, int enderecoId, int fotoId) {
		PessoaModel pessoa = new PessoaModel();
		pessoa.setId(Integer.parseInt(request.getParameter("id")));
		pessoa.setNome(request.getParameter("nome"));
		pessoa.setTelefone(request.getParameter("telefone"));
		pessoa.setEmail(request.getParameter("email"));
		pessoa.setCpf(request.getParameter("cpf"));
		pessoa.setSexo(request.getParameter("sexo"));
		pessoa.setEnderecoId(enderecoId);
		pessoa.setFotoId(fotoId);
		return pessoa;
	}

	private PerfilModel createPerfilModel(HttpServletRequest request) {
		PerfilModel perfil = new PerfilModel();
		perfil.setId(Integer.parseInt(request.getParameter("id")));
		perfil.setTipo(Integer.parseInt(request.getParameter("tipoPerfil")));
		perfil.setServico(Integer.parseInt(request.getParameter("servicoPerfil")));
		perfil.setDisponivel(Integer.parseInt(request.getParameter("disponivelPerfil")));
		return perfil;
	}

	private FotoModel createFotoModel(HttpServletRequest request) {
		FotoModel foto = new FotoModel();
		foto.setId(Integer.parseInt(request.getParameter("id")));
		foto.setArquivo(request.getParameter("arquivo"));
		foto.setArquivo(request.getParameter("extensao"));
		foto.setArquivo(request.getParameter("nomeArquivo"));
		return foto;
	}

	private EnderecoModel createEnderecoModel(HttpServletRequest request) {
		EnderecoModel endereco = new EnderecoModel();
		endereco.setId(Integer.parseInt(request.getParameter("id")));
		endereco.setRua(request.getParameter("rua"));
		endereco.setNumero(request.getParameter("numero"));
		endereco.setCep(request.getParameter("cep"));
		endereco.setCidade(request.getParameter("cidade"));
		endereco.setEstado(request.getParameter("estado"));
		endereco.setComplemento(request.getParameter("complemento"));
		return endereco;
	}
	
	public void deleteAcesso(int id) {
		AcessoDao acessoDao = new AcessoDao();
		acessoDao.delete(id);
	}

	public void deletePessoa(int id) {
		PessoaDao pessoaDao = new PessoaDao();
		pessoaDao.delete(id);
	}

	public void deletePerfil(int id) {
		PerfilDao perfilDao = new PerfilDao();
		perfilDao.delete(id);
	}

	public void deleteFoto(int id) {
		FotoDao fotoDao = new FotoDao();
		fotoDao.delete(id);
	}

	public void deleteEndereco(int id) {
		EnderecoDao enderecoDao = new EnderecoDao();
		enderecoDao.delete(id);
	}
}
