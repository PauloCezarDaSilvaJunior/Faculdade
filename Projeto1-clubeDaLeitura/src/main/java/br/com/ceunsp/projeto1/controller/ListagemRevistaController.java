package br.com.ceunsp.projeto1.controller;

import java.util.List;

import br.com.ceunsp.projeto1.dao.RevistaDAO;
import br.com.ceunsp.projeto1.modelo.Revista;
import br.com.ceunsp.projeto1.tabelas.TabelaRevistas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListagemRevistaController {
	@FXML
	private TableView<TabelaRevistas> tabela;
	@FXML
	private TableColumn<TabelaRevistas, String> colecao;
	@FXML
	private TableColumn<TabelaRevistas, Long> edicao;
	@FXML
	private TableColumn<TabelaRevistas, String> ano;
	@FXML
	private TableColumn<TabelaRevistas, String> caixa;

	private List<Revista> revistas = getRevistas();

	private ObservableList<TabelaRevistas> list = FXCollections.observableArrayList();

	@FXML
	public void initialize() {
		// populando
		for (Revista revista : revistas) {

			TabelaRevistas tabelaRevistas = new TabelaRevistas(revista.getColecao(), revista.getAno(),
					revista.getCaixa().getEtiqueta(), revista.getNumeroEdicao());
			list.add(tabelaRevistas);
		}

		// colunas
		colecao.setCellValueFactory(new PropertyValueFactory<TabelaRevistas, String>("Colecao"));
		ano.setCellValueFactory(new PropertyValueFactory<TabelaRevistas, String>("Ano"));
		caixa.setCellValueFactory(new PropertyValueFactory<TabelaRevistas, String>("Caixa"));
		edicao.setCellValueFactory(new PropertyValueFactory<TabelaRevistas, Long>("Edicao"));

		tabela.setItems(list);
	}

	public List<Revista> getRevistas() {
		try {
			RevistaDAO dao = new RevistaDAO();
			return dao.listar();
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

}