package ftt.uash.model;

public class ItemTabelaGeralModel {
	private int id;
	private String descricao;
	private String sigla;
	private int tabelaGeralId;

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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public int getTabelaGeralId() {
		return tabelaGeralId;
	}

	public void setTabelaGeralId(int tabelaGeralId) {
		this.tabelaGeralId = tabelaGeralId;
	}
}
