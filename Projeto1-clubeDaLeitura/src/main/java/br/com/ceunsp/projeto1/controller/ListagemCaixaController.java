package br.com.ceunsp.projeto1.controller;

import java.util.List;

import br.com.ceunsp.projeto1.dao.CaixaDAO;
import br.com.ceunsp.projeto1.modelo.Caixa;
import br.com.ceunsp.projeto1.tabelas.TabelaCaixa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListagemCaixaController {
	@FXML
	private TableView<TabelaCaixa> tabela;
	@FXML
	private TableColumn<TabelaCaixa, Long> numero;
	@FXML
	private TableColumn<TabelaCaixa, String> cor;
	@FXML
	private TableColumn<TabelaCaixa, String> etiqueta;

	private List<Caixa> caixas = getCaixa();

	private ObservableList<TabelaCaixa> list = FXCollections.observableArrayList();

	@FXML
	public void initialize() {
		// populando
		for (Caixa caixa : caixas) {
			TabelaCaixa tabelaCaixa = new TabelaCaixa(caixa.getNumero(), caixa.getCor(),caixa.getEtiqueta());
			list.add(tabelaCaixa);
		}

		// colunas
		numero.setCellValueFactory(new PropertyValueFactory<TabelaCaixa, Long>("numero"));
		cor.setCellValueFactory(new PropertyValueFactory<TabelaCaixa, String>("cor"));
		etiqueta.setCellValueFactory(new PropertyValueFactory<TabelaCaixa, String>("etiqueta"));

		tabela.setItems(list);
	}

	public List<Caixa> getCaixa() {
		try {
			CaixaDAO dao = new CaixaDAO();
			return dao.listar();
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

}