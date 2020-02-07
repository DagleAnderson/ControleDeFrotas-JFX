package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Veiculo;
import model.services.VeiculoService;

public class VeiculoListController implements Initializable {
	
	
	private VeiculoService service;
	
	//Referencias de Tabelas e Colunas 
	@FXML
	private TableView<Veiculo> tableViewVeiculo;

	@FXML
	private TableColumn<Veiculo, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Veiculo, String> tableColumnDesc;
	
	@FXML
	private TableColumn<Veiculo, String> tableColumnPlaca;
	
	@FXML
	private TableColumn<Veiculo, String> tableColumnRenavam;
	
	@FXML
	private TableColumn<Veiculo, String> tableColumnMod;
	
	// Referencias de Bot�es
	
	@FXML
	private Button btnNovo;
	
	@FXML
	private Button btnEditar;
	
	@FXML
	private Button btnExcluir;
	
	@FXML
	private Button btnSair;
	
	//Lista de dados do javaFX
	private ObservableList<Veiculo> obsList;
	
	
	//M�todos Action
	@FXML
	public void onBtnNovoAction( ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		createDialogForm("/gui/VeiculoForm.fxml", parentStage);
	}
	
	@FXML
	public void onBtnEditarAction() {
		System.out.println("Editar");
	}
	
	@FXML
	public void onBtnExcluirAction() {
		System.out.println("Excluir");
	}
	
	@FXML
	public void onBtnSairAction() {
		System.out.println("Sair");
	}
	
	//inje��o de dependencia
	public void setVeiculoService( VeiculoService service) {
		this.service = service;
	}
	
	
	private void InicializeNodes() {
		//Inicializa��o das colunas da tabela 
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		tableColumnPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
		tableColumnRenavam.setCellValueFactory(new PropertyValueFactory<>("renavam"));
		tableColumnMod.setCellValueFactory(new PropertyValueFactory<>("modelo"));
	}
	
	//atualizador de dados do banco para a View
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		
		List<Veiculo> list  = service.findAll();
			obsList = FXCollections.observableArrayList(list);
			
			tableViewVeiculo.setItems(obsList);
		
	}
	
	private void createDialogForm(String absoluteName,Stage parentStage /*stage pai*/ ) {
		try {
			
			//FXMLLoader � classe principal do JavaFX para carregar um novo form.fxml
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			//Tipo Ancorpane
			Pane pane  = loader.load();
			
			Stage dialogStage = new Stage(); //Novo Palco 
			dialogStage.setTitle("Dados de Veiculo"); //titulo da Stage
			dialogStage.setScene(new Scene(pane)); //Nova cena  passando o palco como par�metro
			dialogStage.setResizable(false); //bloquear redimensionamento
			dialogStage.initOwner(parentStage); // Stage Pai que da origem ao Stage que ser� chamado
			dialogStage.initModality(Modality.WINDOW_MODAL); //Model - Enquanto  janela estiver aberta a inferir estar� inacess�vel
			dialogStage.showAndWait(); // carregar form na tela
			
		}catch(IOException e) {
			Alerts.showAlert("IOExeption", "Erro loading View", e.getMessage(), AlertType.ERROR );
		}
	}
	
	
	@Override //funciona com uma especie de construtor para inicializa��o de nodes no javaFX
	public void initialize(URL uri, ResourceBundle rb) {
		InicializeNodes();
		
	}

	
}
