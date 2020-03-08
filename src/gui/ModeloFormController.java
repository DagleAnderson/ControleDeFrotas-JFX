package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import db.DBException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.entities.Marca;
import model.entities.Modelo;
import model.services.ModeloService;

public class ModeloFormController implements Initializable {
	
	//injeções de dependência
	private Modelo entity;
	
	private ModeloService service;
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	
	//componentes FXML
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtDesc;
	@FXML
	private ComboBox<Marca> cbxMarca;
	@FXML
	private Button btnAddMarca;
	@FXML
	private Button btnGravar;
	@FXML
	private Button btnCancelar;
	
	
	//Injeção de dependência ************
	public void setModelo(Modelo modelo) {
		this.entity = modelo;
	}
	
	public void setModeloService(ModeloService modService) {
		this.service = modService;
	}
	
	//***********************************
	
	public void onBtnAddMarcaAction() {
		
	}
	
	public void onBtnGravarAction( ActionEvent event) {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if(service == null) {
			throw new IllegalStateException("Sevice was null");
		}
		try {
			this.entity = getFormData();
			service.SaveOrUpdate(entity);
			notifyDataChangeListeners();
			Utils.currentStage(event).close();
			
		}catch(DBException e) {
			Alerts.showAlert("Erro ao Salvar Novo Veiculo", "Alerta", e.getMessage(), AlertType.ERROR);
		}
	}


	public void onBtnCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	private Modelo getFormData() {
		Modelo obj = new Modelo();
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		obj.setDescricao(txtDesc.getText());
		
		return obj;
	}
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}


	private void notifyDataChangeListeners() {
		for(DataChangeListener Listener : dataChangeListeners) {
			Listener.onDataChanged();
		}
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//regras de negócio da classe Constraints de gui/utils
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtDesc, 30);
	
	}

}
