package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import db.DBIntegrityException;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Endereco;
import model.entities.Motorista;
import model.services.MotoristaService;

public class MotoristaListController implements Initializable {

	//Injeção de dependência
	private MotoristaService service;

	
	
	//Referencias de Tabelas e Colunas Motorista 
		
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
	
	public void onBtnNovoAction(ActionEvent event){
		Stage parentStage = Utils.currentStage(event);
		Endereco endereco = new Endereco();
		Motorista obj = new Motorista();
		obj.setEndereco(endereco);
		
		
		createDialogForm(obj,"/gui/MotoristaForm.fxml",parentStage);
	}	
	
	public void onBtnEditarAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Motorista obj = getSelectedMotorista();
		
		createDialogForm(obj, "/gui/MotoristaForm.fxml", parentStage);
	}
	
	public void onBtnExcluirAction() {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmation","Deseja confirmar essa operação?");
		if(result.get() == ButtonType.OK) {
			Motorista obj=getSelectedMotorista();
			
			if(service == null) {
				throw new IllegalArgumentException("Service was null");
			}
			try {
				this.service.remove(obj);
			}catch (DBIntegrityException e) {
				 Alerts.showAlert("Erro ao remover objeto", null, e.getMessage(), AlertType.ERROR);
			}
		
		}
	}
	
	
	private Motorista getSelectedMotorista() {
		if(service == null) {
			throw new  IllegalStateException("Service was null");
		}
		if(tableViewMotorista.getSelectionModel().getSelectedItem() == null) {
			Alerts.showAlert("Item not selected", null,"Ops! você esqueceu de selecionar um motorista!", AlertType.INFORMATION);
		}
		
		Motorista obj = tableViewMotorista.getSelectionModel().getSelectedItem();
			
		return this.service.findById(obj);

	}
	
	
	public void updateTableView() {
		List<Motorista> motoristas = service.findAll();
		obsList = FXCollections.observableArrayList(motoristas);
		
		tableViewMotorista.setItems(obsList);
		tableViewMotorista.refresh();
	}
	
	private void createDialogForm(Motorista obj, String absoluteName, Stage parentStage) {
		
		try {
		FXMLLoader loader  = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane =  loader.load();
			
			MotoristaFormController controller = loader.getController();
				controller.setMotorista(obj);
				controller.setMotoristaService(service);
				controller.updateFormData();
				
				Stage dialogStage = new Stage();//Novo Palco 
				dialogStage.setTitle("Dados de Motorista");//titulo da Stage
				dialogStage.setScene(new Scene(pane));//Nova cena  passando o palco como parâmetro
				dialogStage.setResizable(false);//bloquear redimensionamento
				dialogStage.initOwner(parentStage); // Stage Pai que da origem ao Stage que será chamado
				dialogStage.initModality(Modality.WINDOW_MODAL);//Model - Enquanto  janela estiver aberta a inferir estará inacessível
				dialogStage.showAndWait(); // carregar form na tela
			
		}catch (IOException e) {
			Alerts.showAlert("IOExeption", "Erro loading View", e.getMessage(), AlertType.ERROR );
		}
			
		
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
