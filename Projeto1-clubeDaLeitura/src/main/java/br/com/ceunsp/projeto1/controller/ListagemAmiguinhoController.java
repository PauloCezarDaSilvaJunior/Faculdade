package br.com.ceunsp.projeto1.controller;

import java.util.List;

import br.com.ceunsp.projeto1.dao.AmiguinhoDAO;
import br.com.ceunsp.projeto1.modelo.Amiguinho;
import br.com.ceunsp.projeto1.tabelas.TabelaAmiguinho;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListagemAmiguinhoController {
	@FXML
	private TableView<TabelaAmiguinho> tabela;
	@FXML
	private TableColumn<TabelaAmiguinho, String> nome;
	@FXML
	private TableColumn<TabelaAmiguinho, String> nomeMae;
	@FXML
	private TableColumn<TabelaAmiguinho, String> telefone;
	@FXML
	private TableColumn<TabelaAmiguinho, String> local;

	private List<Amiguinho> amiguinhos = getAmiguinho();

	private ObservableList<TabelaAmiguinho> list = FXCollections.observableArrayList();

	@FXML
	public void initialize() {
		// populando
		for (Amiguinho amiguinho : amiguinhos) {
			String local;
			if(amiguinho.getLocal() == 'e'){
				local = "escola";
			}else if(amiguinho.getLocal() == 'p'){
				local = "predio";
			}else{
				local = "Desconhecido";
			}
			TabelaAmiguinho tabelaAmiguinho = new TabelaAmiguinho(amiguinho.getNome(), amiguinho.getNomeMae(),
					amiguinho.getTelefone(), local);
			list.add(tabelaAmiguinho);
		}

		// colunas
		nome.setCellValueFactory(new PropertyValueFactory<TabelaAmiguinho, String>("Nome"));
		nomeMae.setCellValueFactory(new PropertyValueFactory<TabelaAmiguinho, String>("NomeMae"));
		telefone.setCellValueFactory(new PropertyValueFactory<TabelaAmiguinho, String>("Telefone"));
		local.setCellValueFactory(new PropertyValueFactory<TabelaAmiguinho, String>("Local"));

		tabela.setItems(list);
	}

	public List<Amiguinho> getAmiguinho() {
		try {
			AmiguinhoDAO dao = new AmiguinhoDAO();
			return dao.listar();
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

}
