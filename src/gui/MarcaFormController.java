package gui;


import java.net.URL;
import java.util.ResourceBundle;

import db.DBException;
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
import model.services.MarcaService;

public class MarcaFormController implements Initializable {
	
	//Injeção de dependências
	private Marca entity;
	private MarcaService service;

	//componentes FXML
		@FXML
		private TextField txtId;
		@FXML
		private TextField txtDesc;
		@FXML
		private Button btnGravar;
		@FXML
		private Button btnCancelar;	
		
	
	public void setMarca(Marca obj) {
		this.entity = obj;
	}	
	
	public void setMarcaService(MarcaService marcaService) {
		this.service = marcaService;
	}
		
		
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
		Utils.currentStage(event).close();
		}catch(DBException e) {
			Alerts.showAlert("Erro ao salvar nova marca", "Alerta", e.getMessage(), AlertType.ERROR);
		}
		
	}	

	public void onBtnCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	private Marca getFormData() {
		Marca obj = new Marca();
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		obj.setDescricao(txtDesc.getText());
		
		return obj;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//regras de negócio da classe Constraints de gui/utils
				Constraints.setTextFieldInteger(txtId);
				Constraints.setTextFieldMaxLength(txtDesc, 30);
			
		
	}
	
	
}
