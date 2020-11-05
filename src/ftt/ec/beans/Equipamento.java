package ftt.ec.beans;


public class Equipamento {
	private int id;
	private String modelo;
	private String marca;
	private String comentario;
	private int colaborador;
	private int situacao;
	private int funcoes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public int getColaborador() {
		return colaborador;
	}
	
	public void setColaborador(int colaborador) {
		this.colaborador = colaborador;
	}
	
	public int getSituacao() {
		return situacao;
	}
	
	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}
	
	public int getFuncoes() {
		return funcoes;
	}
	
	public void setFuncoes(int funcoes) {
		this.funcoes = funcoes;
	}
}
