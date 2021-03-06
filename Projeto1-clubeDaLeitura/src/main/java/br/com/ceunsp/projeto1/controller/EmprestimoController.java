package br.com.ceunsp.projeto1.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.ceunsp.projeto1.dao.AmiguinhoDAO;
import br.com.ceunsp.projeto1.dao.EmprestimoDAO;
import br.com.ceunsp.projeto1.dao.RevistaDAO;
import br.com.ceunsp.projeto1.modelo.Amiguinho;
import br.com.ceunsp.projeto1.modelo.Emprestimo;
import br.com.ceunsp.projeto1.modelo.Revista;
import br.com.ceunsp.projeto1.util.AlertHelper;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class EmprestimoController {
	@FXML
	ComboBox<Amiguinho> cbAmiguinho;
	@FXML
	ComboBox<Revista> cbRevista;
	@FXML
	DatePicker dtDevolucao;

	Emprestimo emprestimo;

	@FXML
	private void initialize() {
		try {
			AmiguinhoDAO amiguinhoDAO = new AmiguinhoDAO();
			List<Amiguinho> amiguinhos = amiguinhoDAO.listar();

			RevistaDAO revistaDAO = new RevistaDAO();
			List<Revista> revistas = revistaDAO.listar();

			cbAmiguinho.getItems().clear();
			cbAmiguinho.getItems().addAll(amiguinhos);

			cbRevista.getItems().clear();
			cbRevista.getItems().addAll(revistas);
		} catch (RuntimeException e) {
			e.printStackTrace();
			AlertHelper.ErrorAlert("Ops! Ocorreu um erro", "Erro ao inicializar emprestimo");
		}
	}

	@FXML
	public void salvar() {

		try {
			EmprestimoDAO dao = new EmprestimoDAO();

			// novo emprestimo
			if (emprestimo == null) {
				emprestimo = new Emprestimo();

				if (dao.AmiguinhotemPendincias(cbAmiguinho.getValue().getId())) {
					AlertHelper.InfoAlert("Ops! Parece que tem pendencias",
							"O Amiguinho tem pendencia! n�o � possivel fazer emprestimo");
					emprestimo = null;
					return;
				}
				if (dao.RevistatemPendincias(cbRevista.getValue().getId())) {
					AlertHelper.InfoAlert("Ops! Parece que tem pendencias",
							"Essa Revista n�o est� disponivel no momento!");
					emprestimo = null;
					return;
				}

				// converter data
				LocalDate ld = dtDevolucao.getValue();
				Instant instant = ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
				Date dtDevolucao = Date.from(instant);
				Date dtEmprestimo = new Date();
				// pega a data atual
				dtEmprestimo.getTime();

				if (dtDevolucao.getTime() < dtEmprestimo.getTime()) {
					AlertHelper.ErrorAlert("Erro ",
							"A data de devolu��o n�o pode ser menor que a data de emprestimo! ");
					emprestimo = null;//para n�o ir pro editar na proxima tentativa
					return;
				}

				emprestimo.setDataDevolucao(dtDevolucao);
				emprestimo.setDataEmprestimo(dtEmprestimo);
				emprestimo.setEntregue(false);
				emprestimo.setAmiguinho(cbAmiguinho.getValue());
				emprestimo.setRevista(cbRevista.getValue());

				dao.merge(emprestimo);
				emprestimo = null;
			}
			// editar emprestimo
			else {
				// converter data
				LocalDate ld = dtDevolucao.getValue();
				Instant instant = ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
				Date dtDevolucao = Date.from(instant);

				if (dtDevolucao.getTime() < emprestimo.getDataEmprestimo().getTime()) {
					AlertHelper.ErrorAlert("Erro ",
							"A data de devolu��o n�o pode ser menor que a data de emprestimo! ");
					return;
					
				}
				
				emprestimo.setDataDevolucao(dtDevolucao);
				emprestimo.setAmiguinho(cbAmiguinho.getValue());
				emprestimo.setRevista(cbRevista.getValue());

				dao.merge(emprestimo);
			}

			AlertHelper.InfoAlert("Salvo", "Salvo com sucesso!");
			limparCampos();
		} catch (RuntimeException e) {
			e.printStackTrace();
			AlertHelper.ErrorAlert("Ops! Ocorreu um erro", "Erro ao salvar emprestimo");
		}

	}

	public void limparCampos() {
		cbAmiguinho.setValue(null);
		cbRevista.setValue(null);
		dtDevolucao.setValue(null);
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
		cbAmiguinho.setValue(emprestimo.getAmiguinho());
		cbRevista.setValue(emprestimo.getRevista());
		// converter date para localdate
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(emprestimo.getDataDevolucao());
		LocalDate date = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.DAY_OF_MONTH));
		dtDevolucao.setValue(date);

	}
}
