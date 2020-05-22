package gui;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import db.DBException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.MaskFieldUtils;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.entities.Endereco;
import model.entities.Motorista;
import model.exceptions.ValidationException;
import model.services.MotoristaService;

public class MotoristaFormController implements Initializable{
	
	private Motorista entity;
	
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
	private TextField txtCnh;
	@FXML
	private TextField txtTelefone;
	@FXML
	private TextField txtEmail;

	
	
	//buttons
	@FXML
	private Button btnGravar;
	@FXML
	private Button btnCancelar;
	
	
//injeção de dependecia
	public void setMotorista(Motorista motor) {
		this.entity = motor;
	}
	
	public void setMotoristaService(MotoristaService service) {
		this.service = service;
	}

//*********************
	
	@FXML
	public void onBtnGravarAction(ActionEvent event){
		try {
			 this.entity= getFormDataMotor();
			//Endereco objEnd = getFormDataEnd(objMotor);
			 this.service.saveOrUpdate(entity);
		}catch(ValidationException e){
			//this.setErroMenssage(e.getErrors());
			Alerts.showAlert("Alerta", "Campos obrigatórios não informados", e.getMessage(), AlertType.ERROR);
		}catch(DBException e) {
			Alerts.showAlert("Alerta", "Erro ao Salvar Novo Modelo", e.getMessage(), AlertType.ERROR);
		}
	}
	
	private Motorista getFormDataMotor() {
		Motorista obj = new Motorista();
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		obj.setNome(txtNome.getText());
		obj.setSobreNome(txtSobrenome.getText());
			//particularidade datePicker para pegar valor do campo
			Instant instant = Instant.from(dpDataNasc.getValue().atStartOfDay(ZoneId.systemDefault()));
			obj.setDataNascimento(Date.from(instant));
		obj.setCpf(txtCpf.getText());
		obj.setCnh(txtCnh.getText());
		obj.setTelefone(txtTelefone.getText());
		obj.setEmail(txtEmail.getText());
			
		return obj;
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
	
	public void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtNome, 15);
		Constraints.setTextFieldMaxLength(txtSobrenome, 20);
		Utils.formatDatePicker(dpDataNasc, "dd/MM/yyyy");
		
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.MaskInitialize();
		this.initializeNodes();
		
	}
	
	
	
	
}
