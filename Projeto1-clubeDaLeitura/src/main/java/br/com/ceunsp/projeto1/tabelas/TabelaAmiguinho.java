package br.com.ceunsp.projeto1.tabelas;

import javafx.beans.property.SimpleStringProperty;

public class TabelaAmiguinho {
	private final SimpleStringProperty nome;
	private final SimpleStringProperty nomeMae;
	private final SimpleStringProperty telefone;
	private final SimpleStringProperty local;

	public TabelaAmiguinho(String nome, String nomeMae, String telefone, String local) {

		this.nome = new SimpleStringProperty(nome);
		this.nomeMae = new SimpleStringProperty(nomeMae);
		this.telefone = new SimpleStringProperty(telefone);
		this.local = new SimpleStringProperty(local);

	}

	public String getNome() {
		return nome.getValue();
	}

	public String getNomeMae() {
		return nomeMae.getValue();
	}

	public String getTelefone() {
		return telefone.getValue();
	}

	public String getLocal() {
		return local.getValue();
	}

	

}