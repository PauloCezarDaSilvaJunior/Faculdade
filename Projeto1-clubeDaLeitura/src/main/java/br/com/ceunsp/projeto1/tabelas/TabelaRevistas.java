package br.com.ceunsp.projeto1.tabelas;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class TabelaRevistas {
	private final SimpleStringProperty colecao;
	private final SimpleStringProperty ano;
	private final SimpleStringProperty caixa;
	private final SimpleLongProperty edicao;

	public TabelaRevistas(String colecao, String ano, String caixa, Long edicao) {

		this.colecao = new SimpleStringProperty(colecao);
		this.ano = new SimpleStringProperty(ano);
		this.caixa = new SimpleStringProperty(caixa);
		this.edicao = new SimpleLongProperty(edicao);

	}

	public String getColecao() {
		return colecao.get();
	}

	public String getAno() {
		return ano.get();
	}

	public String getCaixa() {
		return caixa.get();
	}

	public Long getEdicao() {
		return edicao.get();
	}

}