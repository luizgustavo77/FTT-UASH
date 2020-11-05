package ftt.ec.beans;

public class Orcamento {
	private int id;
	private int pagamentoId;
	private int equipamentoId;
	private int trabalhoId;
	private int lavagens;
	private int formaPagamento;
	private boolean pago;
	private String comentario;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPagamentoId() {
		return pagamentoId;
	}
	
	public void setPagamentoId(int pagamentod) {
		this.pagamentoId = pagamentod;
	}
	
	public int getEquipamentoId() {
		return equipamentoId;
	}
	
	public void setEquipamentoId(int equipamentoId) {
		this.equipamentoId = equipamentoId;
	}
	
	public int getTrabalhoId() {
		return trabalhoId;
	}
	
	public void setTrabalhoId(int trabalhoId) {
		this.trabalhoId = trabalhoId;
	}
	
	public int getLavagens() {
		return lavagens;
	}
	
	public void setLavagens(int lavagens) {
		this.lavagens = lavagens;
	}
	
	public int getFormaPagamento() {
		return formaPagamento;
	}
	
	public void setFormaPagamento(int formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public boolean isPago() {
		return pago;
	}
	
	public void setPago(boolean pago) {
		this.pago = pago;
	}
	
	public String getComentarioId() {
		return comentario;
	}
	
	public void setComentarioId(String comentarioId) {
		this.comentario = comentarioId;
	}
}
