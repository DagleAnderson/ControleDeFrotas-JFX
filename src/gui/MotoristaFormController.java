package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.MaskFieldUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.entities.Motorista;
import model.services.MotoristaService;

public class MotoristaFormController implements Initializable{
	
	private Motorista motorista;
	
	private MotoristaService service;
	
	//campos
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtSobrenome;
	@FXML
	private DatePicker dpDataNasc;
	@FXML
	private TextField txtCpf;
	@FXML
	private TextField txtRg;
	@FXML
	private TextField txtTelefone;
	@FXML
	private TextField txtCnh;
	
	
	//buttons
	@FXML
	private Button btnGravar;
	@FXML
	private Button btnCancelar;
	
	
//injeção de dependecia
	public void setMotorista(Motorista motor) {
		this.motorista = motor;
	}
	
	public void setMotoristaService(MotoristaService service) {
		this.service = service;
	}

//*********************
	
	@FXML
	public void onBtnGravarAction(ActionEvent event){
		
	}
	
	@FXML
	public void onBtnCancelarAction(ActionEvent event) {
		
	}

		
	public void MaskInitialize(){
		//MaskFieldUtils.dateField((TextField) dpDataNasc);
		MaskFieldUtils.cpfField(txtCpf);
		MaskFieldUtils.foneField(txtTelefone);
		//MaskFieldUtils.cepField(txtCep);
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
	
	
	
}
