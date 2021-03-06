package br.com.ceunsp.projeto1.controller;

import java.io.IOException;

import org.hibernate.Session;

import br.com.ceunsp.projeto1.util.AlertHelper;
import br.com.ceunsp.projeto1.util.HibernateUtil;
import br.com.ceunsp.projeto1.visao.CadastroAmiguinhoMain;
import br.com.ceunsp.projeto1.visao.CadastroCaixaMain;
import br.com.ceunsp.projeto1.visao.CadastroRevistaMain;
import br.com.ceunsp.projeto1.visao.DevolucaoMain;
import br.com.ceunsp.projeto1.visao.EmprestimoMain;
import br.com.ceunsp.projeto1.visao.ListaAmiguinhoMain;
import br.com.ceunsp.projeto1.visao.ListagemCaixaMain;
import br.com.ceunsp.projeto1.visao.ListagemEmprestimoMain;
import br.com.ceunsp.projeto1.visao.ListagemRevistaMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class TelaInicialController {
	Stage primaryStage = new Stage();
	
	@FXML
	public void initialize() {
		
		//fazer conex�o com o banco quando abrir a aplica��o pra n� odemorar para carregar
		try{
			Session session =  HibernateUtil.getSessionFactory().openSession();
			session.close();
		}catch(RuntimeException e){
			e.printStackTrace();
			AlertHelper.ErrorAlert("Erro", "Erro ao conectar com o banco!");
		}
	}

	@FXML
	public void cadastrarAm(ActionEvent event) throws IOException {
		CadastroAmiguinhoMain cadastraram = new CadastroAmiguinhoMain();
		cadastraram.start(primaryStage);
	}

	@FXML
	public void cadastrarCaixa(ActionEvent event) {
		CadastroCaixaMain caCaixa = new CadastroCaixaMain();
		caCaixa.start(primaryStage);
	}

	@FXML
	public void cadastrarRevista(ActionEvent event) {
		CadastroRevistaMain caRevista = new CadastroRevistaMain();
		caRevista.start(primaryStage);
	}

	@FXML
	public void listAmiguinho(ActionEvent event) {
		ListaAmiguinhoMain listamiguinho = new ListaAmiguinhoMain();
		listamiguinho.start(primaryStage);
	}

	@FXML
	public void listCaixa(ActionEvent event) {
		ListagemCaixaMain listCaixa = new ListagemCaixaMain();
		listCaixa.start(primaryStage);
	}

	@FXML
	public void listEmprstimo(ActionEvent event) {
		ListagemEmprestimoMain listEmprestimo = new ListagemEmprestimoMain();
		listEmprestimo.start(primaryStage);
	}

	@FXML
	public void listRevista(ActionEvent event) {
		ListagemRevistaMain listRevista = new ListagemRevistaMain();
		listRevista.start(primaryStage);
	}

	@FXML
	public void emprestimo(ActionEvent event) {
		EmprestimoMain emprestimo = new EmprestimoMain();
		emprestimo.start(primaryStage);
	}

	@FXML
	public void devolucao(ActionEvent event) {
		DevolucaoMain devolucao = new DevolucaoMain();
		devolucao.start(primaryStage);
	}
	
}