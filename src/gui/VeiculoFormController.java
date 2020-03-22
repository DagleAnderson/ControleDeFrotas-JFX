package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DBException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.entities.Marca;
import model.entities.Modelo;
import model.entities.Veiculo;
import model.exceptions.ValidationException;
import model.services.ModeloService;
import model.services.VeiculoService;

public class VeiculoFormController implements Initializable {
	
// Injeções de Dependência
	
	private Veiculo entity;
	
	private VeiculoService service;
	private ModeloService modeloService;

	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
// Injeções de Dependência	
	
		@FXML
		private TextField txtId;
		@FXML
		private TextField txtDescricao;
		@FXML
		private TextField txtAno;
		@FXML
		private ComboBox<Modelo> cbxModelo;
		@FXML
		private TextField txtMarca;
		@FXML
		private TextField txtKmRodado;
		@FXML
		private TextField txtPlaca;
		@FXML
		private TextField txtChassi;
		@FXML
		private TextField txtRenavam;
		
		
		
		@FXML
		private Button btnAddModelo;
		@FXML
		private Button btnGravar;
		@FXML
		private Button btnCancelar;
		
		private ObservableList<Modelo> obsListModelo;
	
		
//métodos de injeção
		
		//recebe obj do parent pane
		public void setVeiculo(Veiculo veic) {
			this.entity = veic;
		}

		//recebe service do parent pane
		public void setServices(VeiculoService servico,ModeloService modeloService) {
			this.service = servico;
			this.modeloService = modeloService;
		}
		
//métodos de injeção
		
		@FXML
		public void onBtnAddModelo(){
			System.out.println("Add modelo");
		}
		
		@FXML
		public void onBtnGravarAction(ActionEvent event) {
			if(entity == null) {
				throw new IllegalArgumentException("Entity was null");
			}
			if(service == null) {
				throw new IllegalArgumentException("Sergice was null");
			}
			try {
				entity  = getFormData();
				service.saveOrUpdate(entity);
				notifyDatachangeListener();
				Utils.currentStage(event).close();
			}catch(ValidationException e){
				this.setErrorMenssage(e.getErrors());
				Alerts.showAlert("Alerta", "Campos obrigatórios não informados",e.getMessage(), AlertType.INFORMATION);
			}catch(DBException e) {
				Alerts.showAlert("Alerta", "Erro ao salvar novo Veículo",e.getMessage(), AlertType.ERROR);
			}
		}
		
		public void onBtnCancelarAction(ActionEvent event){
			Utils.currentStage(event).close();
		}
		

		//pega dados do Form 
		private Veiculo getFormData() {
			
			ValidationException exception = new ValidationException("validation error");
			
			
			Veiculo obj = new Veiculo();
			obj.setId(Utils.tryParseToInt(txtId.getText()));
				
			//Validation - Campos obrigatórios
				if(txtDescricao.getText() == null || txtDescricao.getText().trim().equals("")) {
					exception.addError("descricao", "");
				}
				if(txtPlaca.getText() == null || txtPlaca.getText().trim().equals("")) {
					exception.addError("placa", "");
				}
			
			
			obj.setDescricao(txtDescricao.getText());
			obj.setModelo(new Modelo(1,"Truck",new Marca(1,"Wolksvagem"))/*ação temporária*/);
			obj.setAno(txtAno.getText());
			obj.setChassi(txtChassi.getText());
			obj.setKmRodado(Utils.tryParseToDouble(txtKmRodado.getText()));
			obj.setRenavam(txtRenavam.getText());
			obj.setPlaca(txtPlaca.getText());
			
			if(exception.getErrors().size() > 0 ) {
				throw exception;
			}
			
			return obj;
		}
		
		//Carregamento Inicial ComboBox Marca
		public void loadAssociatedObject() {
			
			if(modeloService == null) {
				throw new IllegalStateException("Departamento was null");
			}
			List<Modelo> listMod = modeloService.findAll();
			obsListModelo = FXCollections.observableArrayList(listMod);
			cbxModelo.setItems(obsListModelo);
		}
		
		public void subscribeDataChangeListener(DataChangeListener listener) {
			this.dataChangeListeners.add(listener);
		}
		
		private void notifyDatachangeListener() {
			for(DataChangeListener listener : dataChangeListeners) {
				listener.onDataChanged();
			}
			
		}
		
		private void setErrorMenssage(Map<String,String> errors) {
			Set<String> fields = errors.keySet();
			
			if(fields.contains("descricao")) {
				//lblErrorDescricao.setText(errors.get("descricao"));
				txtDescricao.setStyle("-fx-border-color: #f00");
			}
			
			if(fields.contains("placa")) {
				txtPlaca.setStyle("-fx-border-color: #f00");
			}
		}

		@FXML
		public void onBtnCancelarAction() {
			System.out.println("cancelar");
		}
		
		public void updateFormData(){ // atualizar form ao abrir edição de veiculo cadastrado
			if(entity == null) {
				throw new IllegalStateException("Entity new null");
			}
			
			txtId.setText(String.valueOf(entity.getId()));
			txtDescricao.setText(entity.getDescricao());
			//comboModelo.setItems();
			txtMarca.setText(entity.getModelo().getMarca().getDescricao());
			txtAno.setText(entity.getAno());
			txtKmRodado.setText(String.valueOf(entity.getKmRodado()));
			txtPlaca.setText(entity.getPlaca());
			txtChassi.setText(entity.getChassi());
			txtRenavam.setText(entity.getRenavam());
			if(entity.getModelo() == null) {
				cbxModelo.getSelectionModel().selectFirst();
			}
			cbxModelo.setValue(entity.getModelo());
			
		}
		
		
		//Controlar tipo e quantidade dos nodes(campos)
		public void initicalizeNodes() {
			
			//regras de negócio da classe Constraints de gui/utils
			Constraints.setTextFieldInteger(txtId);
			Constraints.setTextFieldMaxLength(txtDescricao, 30);
			Constraints.setTextFieldMaxLength(txtMarca, 25);
			//Constraints.setTextFieldMaxLength(comboModelo, 25);
			Constraints.setTextFieldMaxLength(txtAno, 8);
			Constraints.setTextFieldMaxLength(txtKmRodado, 8);
			Constraints.setTextFieldMaxLength(txtPlaca, 8);
			Constraints.setTextFieldMaxLength(txtChassi, 8);
			Constraints.setTextFieldMaxLength(txtRenavam, 8);
					
		}
		
		//Inicialização do Combobox
		private void initializeComboBoxModelo() { 
			Callback<ListView<Modelo>, ListCell<Modelo>> factory = lv -> new ListCell<Modelo>() {  
				@Override      
				protected void updateItem(Modelo item, boolean empty) { 
					super.updateItem(item, empty);     
					setText(empty ? "" : item.getDescricao());  
					} 
				}; 
		
		 cbxModelo.setCellFactory(factory);  
		 cbxModelo.setButtonCell(factory.call(null));  
	}
		
		

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			this.initicalizeNodes();
			this.initializeComboBoxModelo();
			
			
		}
		
		
		
}
