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
import javafx.scene.control.cell.PropertyValueFactory;
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
	
	// Referencias de Botões
	
	@FXML
	private Button btnIncluir;
	
	@FXML
	private Button btnEditar;
	
	@FXML
	private Button btnExcluir;
	
	@FXML
	private Button btnSair;
	
	private ObservableList<Veiculo> obsList;
	
	
	//Métidos Action
	@FXML
	public void onBtnIncluirAction() {
		System.out.println("Incluir");
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
	
	//injeção de dependencia
	
	public void setVeiculoService( VeiculoService service) {
		this.service = service;
	}
	
	
	private void InicializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		tableColumnPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
		tableColumnRenavam.setCellValueFactory(new PropertyValueFactory<>("renavam"));
		tableColumnMod.setCellValueFactory(new PropertyValueFactory<>("modelo"));
	}

	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		
		List<Veiculo> list  = service.findAll();
			obsList = FXCollections.observableArrayList(list);
			
			tableViewVeiculo.setItems(obsList);
		
	}
	
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		InicializeNodes();
		
	}

	
}
