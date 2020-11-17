package ftt.uash.model;

import java.util.Date;

public class TrabalhoModel {
	private int id;
	private int colaborador;
	private int usuario;
	private Date dataAbertura;
	private Date dataFechamento;
	private int status;
	private int notaServico;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getColaborador() {
		return colaborador;
	}

	public void setColaborador(int colaborador) {
		this.colaborador = colaborador;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getNotaServico() {
		return notaServico;
	}

	public void setNotaServico(int notaServico) {
		this.notaServico = notaServico;
	}
}
