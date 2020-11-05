package ftt.ec.beans;

public class Comentario {
	private int id;
	private String descricao;
	private int trabalho;
	private int usuario;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public int getTrabalho() {
		return trabalho;
	}
	
	public void setTrabalho(int trabalho) {
		this.trabalho = trabalho;
	}
	
	public int getUsuario() {
		return usuario;
	}
	
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
}
