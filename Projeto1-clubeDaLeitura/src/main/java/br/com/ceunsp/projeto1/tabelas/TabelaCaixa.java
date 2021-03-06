package br.com.ceunsp.projeto1.tabelas;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class TabelaCaixa {
	private final SimpleLongProperty numero;
	private final SimpleStringProperty cor;
	private final SimpleStringProperty etiqueta;

	public TabelaCaixa(Long numero, String cor, String etiqueta) {

		this.numero = new SimpleLongProperty(numero);
		this.cor = new SimpleStringProperty(cor);
		this.etiqueta = new SimpleStringProperty(etiqueta);

	}

	public Long getNumero() {
		return numero.get();
	}

	public String getCor() {
		return cor.get();
	}

	public String getEtiqueta() {
		return etiqueta.get();
	}
}