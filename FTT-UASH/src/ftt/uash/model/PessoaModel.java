package ftt.uash.model;

public class PessoaModel {
	private int id;
	private int fotoId;
	private String nome;
	private String telefone;
	private String email;
	private String cpf;
	private String sexo;
	private int enderecoId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFotoId() {
		return fotoId;
	}

	public void setFotoId(int fotoId) {
		this.fotoId = fotoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(int enderecoId) {
		this.enderecoId = enderecoId;
	}
}
