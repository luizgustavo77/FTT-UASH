package ftt.ec.beans;

public class TabelaGeral {
	private int id;
	private String descricao;
	private ItemTabelaGeral itemTabela;
	
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

	public ItemTabelaGeral getItemTabela() {
		return itemTabela;
	}

	public void setItemTabela(ItemTabelaGeral itemTabela) {
		this.itemTabela = itemTabela;
	}
}
