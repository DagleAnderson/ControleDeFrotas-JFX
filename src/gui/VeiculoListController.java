package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


import db.DBIntegrityException;
import gui.listeners.DataChangeListener;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Veiculo;
import model.services.ModeloService;
import model.services.VeiculoService;

public class VeiculoListController implements Initializable,DataChangeListener {
	
	//Injeção de dependências
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
	private ObservableList<Veiculo> obsList;
	
	
	//injeção de dependencia
	public void setVeiculoService( VeiculoService service) {
		this.service = service;
	}
	
	//Métodos Action
	@FXML
	public void onBtnNovoAction( ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Veiculo obj = new Veiculo();
		createDialogForm(obj, "/gui/VeiculoForm.fxml", parentStage);
	}
	
	@FXML
	public void onBtnEditarAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Veiculo obj = getSelectedVeiculo();
		createDialogForm(obj, "/gui/VeiculoForm.fxml", parentStage);
	}

	@FXML
	public void onBtnExcluirAction(ActionEvent event) {
		 Optional<ButtonType> result = Alerts.showConfirmation("Confirmation","Deseja confirmar essa operação?");
		 if(result.get() == ButtonType.OK) {
			 Veiculo obj= getSelectedVeiculo();
			
			 if(service == null) {
				 throw new IllegalStateException("service was null");
			 }
			 
			 try {
				 service.remove(obj);
				 this.updateTableView();
			 }catch (DBIntegrityException e) {
				 Alerts.showAlert("Erro ao remover objeto", null, e.getMessage(), AlertType.ERROR);
			}
		 }
		 
	}
	
	@FXML
	public void onBtnSairAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	
	private void InicializeNodes() {
		//Inicialização das colunas da tabela 
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
			tableViewVeiculo.refresh();
	}
	
	//pegar veiculo selecionado
	private Veiculo getSelectedVeiculo() {
		if(service == null) {
			throw new IllegalStateException("service was null");
		}
		
		if(tableViewVeiculo.getSelectionModel().getSelectedItem() == null) {
			Alerts.showAlert("Item not selected", null,"Ops! você esqueceu de selecionar um veículo!", AlertType.INFORMATION);
		}
		
		 Veiculo obj = tableViewVeiculo.getSelectionModel().getSelectedItem(); 
		
		return service.findById(obj);
	}
	
	private void createDialogForm(Veiculo obj,String absoluteName,Stage parentStage /*stage pai*/ ) {
		try {
			
			//FXMLLoader é classe principal do JavaFX para carregar um novo form.fxml
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			//Tipo Ancorpane
			Pane pane  = loader.load();
			
			//pega o controlador do form que irá sofrer a injeção
			VeiculoFormController controller = loader.getController();
			controller.setVeiculo(obj);
			controller.setServices(new VeiculoService(),new ModeloService());
			controller.loadAssociatedObject();
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();
			
			Stage dialogStage = new Stage(); //Novo Palco 
			dialogStage.setTitle("Dados de Veiculo"); //titulo da Stage
			dialogStage.setScene(new Scene(pane)); //Nova cena  passando o palco como parâmetro
			dialogStage.setResizable(false); //bloquear redimensionamento
			dialogStage.initOwner(parentStage); // Stage Pai que da origem ao Stage que será chamado
			dialogStage.initModality(Modality.WINDOW_MODAL); //Model - Enquanto  janela estiver aberta a inferir estará inacessível
			dialogStage.showAndWait(); // carregar form na tela
			
		}catch(IOException e) {
			e.printStackTrace();
			Alerts.showAlert("IOExeption", "Erro loading View", e.getMessage(), AlertType.ERROR );
		}
	}
	
	@Override
	public void onDataChanged() {
		this.updateTableView();
	}

	@Override //funciona com uma especie de construtor para inicialização de nodes no javaFX
	public void initialize(URL uri, ResourceBundle rb) {
		InicializeNodes();
		
	}



	
}
