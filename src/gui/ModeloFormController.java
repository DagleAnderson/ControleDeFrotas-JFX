package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.entities.Marca;
import model.entities.Modelo;
import model.services.ModeloService;

public class ModeloFormController implements Initializable {
	
	//injeções de dependência
	private Modelo entity;
	private ModeloService service;
	
	
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
	
	public void onBtnGravarAction() {
		this.entity = getFormData();
		service.SaveOrUpdate(entity);
	}


	public void onBtnCancelarAction() {
		System.out.println("cancelar");
	}
	
	private Modelo getFormData() {
		Modelo obj = new Modelo();
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		obj.setDescricao(txtDesc.getText());
		
		return obj;
	}

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
