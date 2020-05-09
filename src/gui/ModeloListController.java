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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Modelo;
import model.services.MarcaService;
import model.services.ModeloService;

public class ModeloListController implements Initializable,DataChangeListener{
	
//Injeção de dependências	
	private ModeloService service;
	
	//TextField
		@FXML
	private TextField txtPesquisar;
	//Table View
		@FXML
		private TableView<Modelo> tableViewModelo;
		@FXML
		private TableColumn<Modelo, Integer> tableColumnId;
		@FXML
		private TableColumn<Modelo,String> tableColumnDesc;
		@FXML
		private TableColumn<Modelo,String>tableColumnMarca;
		
		
		private ObservableList<Modelo> obsList;
		
	//Butons
		@FXML
		private Button btnNovo;
		@FXML
		private Button btnEditar;
		@FXML
		private Button btnExcuir;
		@FXML
		private Button btnSair;
		
	//Injeção de dependência
		
		public void setModeloService(ModeloService modeloService) {
			this.service = modeloService;
		}

		@FXML 
		public void onBtnNovoAction(ActionEvent event) {
			//Método currentStage Pega o palco pai para passar como parâmetro nas telas de diálogo
			Stage parentStage = Utils.currentStage(event);
			Modelo obj = new Modelo();
			createDialogForm(obj,"/gui/ModeloForm.fxml", parentStage);
		}
		
		@FXML 
		public void onBtnEditarAction(ActionEvent event) {
			Stage parentStage = Utils.currentStage(event);
			Modelo obj = getSelectedModelo();
			
			createDialogForm(obj,"/gui/ModeloForm.fxml", parentStage);
		}
		

		@FXML 
		public void onBtnExcluirAction() {
			 Optional<ButtonType> result = Alerts.showConfirmation("Confirmation","Deseja confirmar essa operação?");
			if(result.get() == ButtonType.OK) {
				Modelo obj = getSelectedModelo();
				
				if(service == null) {
					throw  new IllegalArgumentException("Service was null");
				}
				
				try {
					this.service.remove(obj);
					this.updateTableView();
				}catch (DBIntegrityException e) {//DBIntegrityException
					 Alerts.showAlert("Erro ao remover objeto", null, e.getMessage(), AlertType.ERROR);
				}
				
			}	
		}
		
		@FXML 
		public void onBtnSairAction(ActionEvent event) {
			Utils.currentStage(event).close();
		}
		
		public void createDialogForm(Modelo obj,String absoluteName, Stage parentStage){
			try {
				//FXMLLoader é classe principal do JavaFX para carregar um novo form.fxml
				FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
				//Tipo Ancorpane
				Pane pane = loader.load();
				
				//pega o controlador do form que irá sofrer a injeção
				ModeloFormController controller = loader.getController();
				controller.setModelo(obj);
				controller.setServices(new ModeloService(),new MarcaService());
				controller.loadAssociatedObject();
				controller.subscribeDataChangeListener(this);
				controller.updateFormData();
				
				Stage dialogStage = new Stage();//Novo Palco 
				dialogStage.setTitle("Dados de Modelo");//titulo da Stage
				dialogStage.setScene(new Scene(pane));//Nova cena  passando o palco como parâmetro
				dialogStage.setResizable(false);//bloquear redimensionamento
				dialogStage.initOwner(parentStage); // Stage Pai que da origem ao Stage que será chamado
				dialogStage.initModality(Modality.WINDOW_MODAL);//Model - Enquanto  janela estiver aberta a inferir estará inacessível
				dialogStage.showAndWait(); // carregar form na tela
				
				
			}catch (IOException e) {
				 Alerts.showAlert("IOExeption", "Erro loading View", e.getMessage(), AlertType.ERROR );
			}
			
		}
		
		//atualizador de dados do banco para a View . Metodo chamado na class anterior (MainViewController)
		public void updateTableView() {  
			if(service == null) {
				throw new IllegalStateException("Service was null");
			}
			
			List<Modelo> modelos = service.findAll();
				obsList = FXCollections.observableArrayList(modelos);
				
				tableViewModelo.setItems(obsList);
				tableViewModelo.refresh();
			
		}
		
		private Modelo getSelectedModelo() {
			if(service == null) {
				throw new  IllegalStateException("Service was null");
			}
			if(tableViewModelo.getSelectionModel().getSelectedItem() == null) {
				Alerts.showAlert("Item not selected", null,"Ops! você esqueceu de selecionar um veículo!", AlertType.INFORMATION);
			}
			
			Modelo obj = tableViewModelo.getSelectionModel().getSelectedItem();
			
			return service.findById(obj);
		}
		
		private void InicializeNodes() {
			//Inicialização das colunas da tabela 
			tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
			tableColumnDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
			tableColumnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		}
		
		@Override
		public void onDataChanged() {
			this.updateTableView();
		}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			InicializeNodes();
			
		}

	
}
