package ftt.ec.beans;

public class Acesso {
	private int id;
	private String usuario;
	private String senha;
	private int pessoaId;
	private int perfilId;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public int getPessoaId() {
		return pessoaId;
	}
	
	public void setPessoaId(int pessoaId) {
		this.pessoaId = pessoaId;
	}
	
	public int getPerfilId() {
		return perfilId;
	}
	
	public void setPerfilId(int perfilId) {
		this.perfilId = perfilId;
	}
}
