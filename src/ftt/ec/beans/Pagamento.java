package ftt.ec.beans;

public class Pagamento {
	private int id;
	private float valor;
	private int servicoId;
	private String descricao;
	private String comentario;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public float getValor() {
		return valor;
	}
	
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public int getServico() {
		return servicoId;
	}
	
	public void setServico(int servicoId) {
		this.servicoId = servicoId;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
