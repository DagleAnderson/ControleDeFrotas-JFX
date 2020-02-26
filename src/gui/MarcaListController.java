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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Marca;
import model.services.MarcaService;

public class MarcaListController implements Initializable {
	
	//injeção de dependência
		private MarcaService service;
	
	
	//TextField
			@FXML
		private TextField txtPesquisar;
		//Table View
			@FXML
			private TableView<Marca> tableViewMarca;
			@FXML
			private TableColumn<Marca, Integer> tableColumnId;
			@FXML
			private TableColumn<Marca,String> tableColumnDesc;
		
			//Butons
			@FXML
			private Button btnNovo;
			@FXML
			private Button btnEditar;
			@FXML
			private Button btnExcuir;
			@FXML
			private Button btnSair;
	
	//List Observable
		
			private ObservableList<Marca> obsList;
			
    // Métodos de injeção de dependência ****************** 
	
	public void setMarcaService(MarcaService marcaService) {
		this.service = marcaService;
	}
	//*****************************************************
	
	public void onBtnNovoAction(ActionEvent event){
		Stage parentStage = Utils.currentStage(event);
		Marca obj = new Marca();
		createDialogForm(obj,"/gui/MarcaForm.fxml",parentStage);
	}

	public void onBtnEditarAction() {
		System.out.println("editar");
	}
	
	public void onBtnExcluirAction() {
		System.out.println("excluir");
	}
	
	public void onBtnSairAction() {
		System.out.println("Sair");
	}
	
	//atualizador de dados do banco para a View
			public void updateTableView() {
				if(service == null) {
					throw new IllegalStateException("Service was null");
				}
				
				List<Marca> list  = service.findAll();
					obsList = FXCollections.observableArrayList(list);
					
					tableViewMarca.setItems(obsList);
				
			}
	
	public void InitializeNodes() {
		//Inicialização das colunas da tabela 
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		}
	
	private void createDialogForm(Marca obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
				
				MarcaFormController controller = loader.getController();
				controller.setMarca(obj);
				controller.setMarcaService(new MarcaService());
						
				Stage dialogForm = new Stage();
				dialogForm.setTitle("Dados de Marca");
				dialogForm.setScene(new Scene(pane));
				dialogForm.initOwner(parentStage);
				dialogForm.initModality(Modality.WINDOW_MODAL);
				dialogForm.showAndWait();
			}catch(IOException e) {
				Alerts.showAlert("IOExeption", "Erro loading View", e.getMessage(), AlertType.ERROR );
			}
		
	}
			
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.InitializeNodes();
	}

}
