package ftt.ec.beans;

public class Perfil {
	private int id;
	private int tipo;
	private int servico;
	private int disponivel;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getTipo() {
		return tipo;
	}
	
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public int getServico() {
		return servico;
	}
	
	public void setServico(int servico) {
		this.servico = servico;
	}
	
	public int getDisponivel() {
		return disponivel;
	}
	
	public void setDisponivel(int disponivel) {
		this.disponivel = disponivel;
	}
}
