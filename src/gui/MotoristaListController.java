package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Motorista;
import model.services.MotoristaService;

public class MotoristaListController implements Initializable {

	//Injeção de dependência
	private MotoristaService service;
	
	
	//Referencias de Tabelas e Colunas 
		
	@FXML
	private TableView<Motorista> tableViewMotorista;

	@FXML
	private TableColumn<Motorista, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Motorista, String> tableColumnNome;
	
	@FXML
	private TableColumn<Motorista, String> tableColumnTelefone;
	
	
	// Referencias de Botões
	
	@FXML
	private Button btnNovo;
	
	@FXML
	private Button btnEditar;
	
	@FXML
	private Button btnExcluir;
	
	@FXML
	private Button btnSair;
	
	//Lista de dados do javaFX
	private ObservableList<Motorista> obsList;
	

	public void setMotoristaService(MotoristaService service) {
		this.service = service;
	}
	
	public void onBtnNovoAction() {
		
	}	
	
	public void onBtnEditarAction() {
		
	}
	
	public void onBtnExcluirAction() {
		
	}
	
	
	public void updateTableView() {
		List<Motorista> motoristas = service.findAll();
		obsList = FXCollections.observableArrayList(motoristas);
		
		tableViewMotorista.setItems(obsList);
		tableViewMotorista.refresh();
	}
	
	
	public void initializeNodes(){
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnTelefone.setCellValueFactory(new  PropertyValueFactory<>("telefone"));
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { // Inicializa table,columns  e qualquer outro método de carregamento
		 this.initializeNodes();
		
	}

}
