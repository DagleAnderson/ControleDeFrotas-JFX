package gui;


import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
		
		
	public void onBtnGravarAction() {
		 entity = getFormData();
		this.service.saveOrupdate(entity);
	}	

	public void onBtnCancelarAction() {
		System.out.println("cancelado");
	}
	
	private Marca getFormData() {
		Marca obj = new Marca();
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		obj.setDescricao(txtDesc.getText());
		
		return obj;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
}
