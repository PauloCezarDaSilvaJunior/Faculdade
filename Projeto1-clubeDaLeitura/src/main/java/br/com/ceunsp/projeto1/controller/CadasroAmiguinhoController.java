package br.com.ceunsp.projeto1.controller;

import br.com.ceunsp.projeto1.dao.AmiguinhoDAO;
import br.com.ceunsp.projeto1.modelo.Amiguinho;
import br.com.ceunsp.projeto1.util.AlertHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class CadasroAmiguinhoController {
	@FXML
	private ToggleGroup toggle;
	@FXML
	private TextField tfNome;
	@FXML
	private TextField tfNomeMae;
	@FXML
	private TextField tfTelefone;
	@FXML
	private RadioButton rbPredio;
	@FXML
	private RadioButton rbEscola;

	private Amiguinho amiguinho;

	@FXML
	public void salvar(ActionEvent event) {
		try {
			if (tfNome.getText().equals("") || tfNomeMae.getText().equals("") || tfTelefone.getText().equals("")) {
				AlertHelper.ErrorAlert("Ops! ocorreu um erro", "Por favor preencha todos os campos");
				return;
			}

			if(amiguinho == null){
				amiguinho = new Amiguinho();
				amiguinho.setNome(tfNome.getText());
				amiguinho.setNomeMae(tfNomeMae.getText());
				amiguinho.setTelefone(tfTelefone.getText());
				if (toggle.getSelectedToggle() == rbPredio) {
					amiguinho.setLocal('p');
				} else if (toggle.getSelectedToggle() == rbEscola) {
					amiguinho.setLocal('e');

				}
			}else{
				amiguinho.setNome(tfNome.getText());
				amiguinho.setNomeMae(tfNomeMae.getText());
				amiguinho.setTelefone(tfTelefone.getText());
				if (toggle.getSelectedToggle() == rbPredio) {
					amiguinho.setLocal('p');
				} else if (toggle.getSelectedToggle() == rbEscola) {
					amiguinho.setLocal('e');

				}
			}
		
			AmiguinhoDAO dao = new AmiguinhoDAO();
			dao.merge(amiguinho);
			AlertHelper.InfoAlert("Salvo", "Amiguinho salvo com sucesso!");
			limparCampos();
		} catch (RuntimeException e) {
			AlertHelper.ErrorAlert("Ops! ocorreu um erro", "Erro ao tentar salvar!");
			e.printStackTrace();
		}
	}

	public void setAmiguinho(Amiguinho amiguinho) {
		this.amiguinho = amiguinho;
		tfNome.setText(amiguinho.getNome());
		tfNomeMae.setText(amiguinho.getNomeMae());
		tfTelefone.setText(amiguinho.getTelefone());
		if (amiguinho.getLocal().equals('p')) {
			rbPredio.setSelected(true);
		} else if (amiguinho.getLocal().equals('e')) {
			rbEscola.setSelected(true);

		}

	}

	public void limparCampos() {
		tfNome.setText("");
		tfNomeMae.setText("");
		tfTelefone.setText("");
		toggle.selectToggle(null);
	}
}