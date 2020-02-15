package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Modelo;
import model.services.ModeloService;

public class ModeloListController implements Initializable{
	
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
		
		
		public void setModeloService(ModeloService modeloService) {
			this.service = modeloService;
		}

		@FXML 
		public void OnBtnNovoAction() {
			System.out.println("novo Modelo");
		}
		
		@FXML 
		public void OnBtnEditarAction() {
			System.out.println("editar Modelo");
		}
		
		@FXML 
		public void OnBtnExcluirAction() {
			System.out.println("excluir Modelo");
		}
		
		@FXML 
		public void OnBtnSairAction() {
			System.out.println("sair Modelo");
		}
		
		//atualizador de dados do banco para a View
		public void updateTableView() {
			if(service == null) {
				throw new IllegalStateException("Service was null");
			}
			
			List<Modelo> list  = service.findAll();
				obsList = FXCollections.observableArrayList(list);
				
				tableViewModelo.setItems(obsList);
			
		}
		
		private void InicializeNodes() {
			//Inicialização das colunas da tabela 
			tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
			tableColumnDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
			tableColumnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			InicializeNodes();
			
		}
}
