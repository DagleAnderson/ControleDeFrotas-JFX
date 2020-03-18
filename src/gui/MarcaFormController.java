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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entities.Marca;
import model.exceptions.ValidationException;
import model.services.MarcaService;

public class MarcaFormController implements Initializable {
	
	//Injeção de dependências
	private Marca entity;
	private MarcaService service;
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

	//componentes FXML
		@FXML
		private TextField txtId;
		@FXML
		private TextField txtDesc;
		@FXML
		private Button btnGravar;
		@FXML
		private Button btnCancelar;	
		
	//************* Injeções de Dependência ******************************
	public void setMarca(Marca obj) {
		this.entity = obj;
	}	
	
	public void setMarcaService(MarcaService marcaService) {
		this.service = marcaService;
	}
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		this.dataChangeListeners.add(listener);
	}
	// *******************************************************************
		
	
	public void onBtnGravarAction(ActionEvent event) {
		if(entity == null) {
			throw new IllegalStateException("entity was null");
		};
		if(service == null) {
			throw new IllegalStateException("service was null");
		};
	
		try {
		 entity = getFormData();
		this.service.saveOrupdate(entity);
		notifyDataChangeListener();
		Utils.currentStage(event).close();
		}catch(ValidationException e){
			this.setErroMenssage(e.getErrors());
			Alerts.showAlert("Alerta", "Campos obrigatórios não informados", e.getMessage(), AlertType.ERROR);
		}catch(DBException e) {
			Alerts.showAlert("Erro ao salvar nova marca", "Alerta", e.getMessage(), AlertType.ERROR);
		}
		
	}	

	public void onBtnCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	private Marca getFormData() {
		ValidationException exception = new ValidationException("validation error");
		Marca obj = new Marca();
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		if(txtDesc.getText() == null || txtDesc.getText().trim().equals("")) {
			exception.addError("descricao", "");
		}
		obj.setDescricao(txtDesc.getText());
		
		if(exception.getErrors().size() > 0 ) {
			throw exception;
		}
		return obj;
	}
	
	
	private void setErroMenssage(Map<String,String> errors) {
		Set<String> fields = errors.keySet();
		
		if(fields.contains("descricao")){
			txtDesc.setStyle("-fx-border-color:#f00");;
		}
	}
	
	private void notifyDataChangeListener() {
		for(DataChangeListener listener : dataChangeListeners){
			listener.onDataChanged();
		}
	}
	
	
	public void updateFormData() {
		txtId.setText(String.valueOf(entity.getId()));
		txtDesc.setText(entity.getDescricao());
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//regras de negócio da classe Constraints de gui/utils
				Constraints.setTextFieldInteger(txtId);
				Constraints.setTextFieldMaxLength(txtDesc, 30);
			
		
	}
	
	
}
